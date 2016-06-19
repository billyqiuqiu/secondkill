package org.seckill.service;

import java.util.Date;
import java.util.List;

import org.seckill.dao.ISeckillDao;
import org.seckill.dao.ISuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ISeckillDao seckillDao;
	
	@Autowired
	private ISuccessKilledDao successKilledDao;
	
	private String salt = "sfisoe$WER5654&93942*&&22!@dfjkd";
	
	public List<Seckill> getSeckillList() {
		
		return seckillDao.queryAll(0, 10);
	}
    
	
	public Seckill getSeckillById(Long seckillId) {
		
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(Long seckillId) {
		Seckill seckill = getSeckillById(seckillId);
		
		Exposer exposer = null;
		
		if(seckill == null){
			exposer = new Exposer(false,seckillId);
			return exposer;
		}
		
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date now = new Date();
		
		if(startTime.getTime() > now.getTime() || endTime.getTime() < now.getTime()){
			 exposer = new Exposer(false,seckillId,now.getTime(),startTime.getTime(),endTime.getTime());
			 return exposer;
		}
		
		String md5 = getMD5(seckillId);
		exposer = new Exposer(true,seckillId,md5);
		return exposer;
	}

	private String getMD5(Long seckillId) {
		
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Transactional
	public SeckillExecution executeSeckill(Long seckillId, Long userPhone, String md5)
			throws RepeatKillException, SeckillCloseException, SeckillException {
		
		if(md5 == null || !md5.equals(getMD5(seckillId))){
			throw new SeckillException("Seckill URL is invalid or rewritten.");
		}
		
		try
		{
			Date now = new Date();
			
			int updateCount = seckillDao.reduceNumber(seckillId, now);
			
			if(updateCount <= 0){
				
				throw new SeckillCloseException("Seckill is closed already.");
			}
			else{
				
				int insertCount = successKilledDao.insertSuccessKill(seckillId, userPhone);
				
				if(insertCount <= 0){
					throw new RepeatKillException("You have submit the seckill already, please don't submit it again.");
				}
				else{
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
					
				}
			}
		}
		catch(RepeatKillException e1){
			throw e1;
		}
		catch(SeckillCloseException e2){
			throw e2;
		}
		catch(Exception ex){
			
			logger.error(ex.getMessage(),ex);
			
			throw new SeckillException("Seckill failed with inner error: " + ex.getMessage());
		}
		
	}

}

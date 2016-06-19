package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

public interface SeckillService {

	List<Seckill> getSeckillList();
	
	Seckill getSeckillById(Long seckillId);
	
	Exposer exportSeckillUrl(Long seckillId);
	
	SeckillExecution executeSeckill(Long seckillId, Long userPhone, String md5) throws RepeatKillException,
	SeckillCloseException,SeckillException;
	
}

package org.seckill.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.annotation.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ISeckillDaoTest {

	@Resource
    private ISeckillDao seckillDao;	
	
	@Test
	public void testQueryById() {
		Long seckillId = 1001L;
		
		Seckill seckill = seckillDao.queryById(seckillId);
		
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}
	
	@Test
	public void testQueryAll() {
		
		int offset = 1;
		int limit = 3;
				
		
		List<Seckill> seckillList = seckillDao.queryAll(offset, limit);
		
		System.out.println("Item count" + seckillList.size());
		for(Seckill item : seckillList){
			
			System.out.println(item.getName());
		}
	}
	
	@Test
	public void testReduceNumber() {
		
		Long seckillId = 1001L;
		Date killTime = Date.valueOf("2016-06-10");
		
		int result = seckillDao.reduceNumber(seckillId, killTime);
		System.out.println("Kill OK " + result);
	}

}

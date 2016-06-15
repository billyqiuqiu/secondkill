package org.seckill.dao;

import static org.junit.Assert.*;
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
	public void testReduceNumber() {
		
	}
	
	@Test
	public void testQueryById() {
		Long seckillId = 1001L;
		
		Seckill seckill = seckillDao.queryById(seckillId);
		
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}
	
	@Test
	public void testQueryAll() {
		
	}

}

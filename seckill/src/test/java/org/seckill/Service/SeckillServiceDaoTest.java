package org.seckill.Service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.annotation.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.ISeckillDao;
import org.seckill.entity.Seckill;
import org.seckill.service.SeckillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillServiceDaoTest {

	@Autowired	
	SeckillServiceImpl seckillService;
	
	
	
}

package org.seckill.service;


import java.util.List;
import javax.annotation.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.service.SeckillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })

public class SeckillServiceImplTest {

	@Autowired	
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList(){
		List<Seckill> seckillList = seckillService.getSeckillList();
		
		System.out.println("Item count" + seckillList.size());
		for(Seckill item : seckillList){
			
			System.out.println(item.getName());
		}
	}
	
	@Test
	public void testGetSeckById(){
		Seckill seckill = seckillService.getSeckillById(1001L);
		
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}
	
	@Test
	public void testExportSeckillUrl(){
		Exposer exposer = seckillService.exportSeckillUrl(1001L);
		System.out.println("Expose URL : " + exposer.isExposed());
	}
	
	@Test
	public void testExecuteSeckill(){
		
		SeckillExecution seckillExe = seckillService.executeSeckill(1001L, 13771883967L, seckillService.getMD5(1001L));
		System.out.println("Seckill State : " + seckillExe.getStateInfo());
	}
}

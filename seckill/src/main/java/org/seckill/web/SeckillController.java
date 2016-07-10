package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/seckill")
public class SeckillController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(Model model){
		
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list",list);
		
		return "list";
	}
	
	
	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId,Model model){
		
		if(seckillId == null){
			return "redirect:/seckill/list";
		}
		
		Seckill seckill = seckillService.getSeckillById(seckillId);
		
		if(seckill == null){
			return "forward:/seckill/list";
		}
		
		model.addAttribute("seckill", seckill);
		
		return "detail";
	}
	
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
			       produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
		
		SeckillResult<Exposer> result;
		
		try{
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		}
		catch(Exception ex){
			logger.error(ex.getMessage(), ex);
			result = new SeckillResult<Exposer>(false,ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
			     	produces = {"application/json; charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5,
			@CookieValue(value = "killPhone",required = false) Long userPhone){
		
		if(userPhone == null){
			return new SeckillResult<SeckillExecution>(false, "ºÅÂëÎ´×¢²á£¬Çë×¢²á´ËºÅÂë¡£");
		}
		
		SeckillResult<SeckillExecution> result;
		
		try{
			SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
			result = new SeckillResult<SeckillExecution>(true, seckillExecution);
		}
		catch(RepeatKillException ex){
			
			SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillStateEnum.REPEATKILL);
			result = new SeckillResult<SeckillExecution>(true, seckillExecution);
		}
		catch (SeckillCloseException ex) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillStateEnum.END);
			result = new SeckillResult<SeckillExecution>(true, seckillExecution);
		}
		catch (SeckillException ex) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillStateEnum.DATA_REWRITE);
			result = new SeckillResult<SeckillExecution>(true, seckillExecution);
		}
		catch(Exception ex){
			logger.error(ex.getMessage(), ex);
			SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillStateEnum.INNER_ERROR);
			result = new SeckillResult<SeckillExecution>(true, seckillExecution);
			
		}
		return result;
	}
	
	@RequestMapping(value = "/time/now", method = RequestMethod.GET,
			        produces = {"application/json; charset=UTF-8"})
	
	@ResponseBody
	public SeckillResult<Long> time(){
		
		Date now = new Date();
		
		return new SeckillResult<Long>(true, now.getTime());
		
	}

}

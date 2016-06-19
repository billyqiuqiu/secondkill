package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.seckill.entity.Seckill;

public interface ISeckillDao {

	int reduceNumber(Long seckillId, Date killTime);
	
	Seckill queryById(Long seckillId);
	
	List<Seckill> queryAll(int offset, int limit);
}

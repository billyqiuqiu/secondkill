package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

public interface ISuccessKilledDao {
	
	int insertSuccessKill(Long seckillId, Long userPhone);
	
	SuccessKilled queryByIdWithSeckill(Long seckillId);

}

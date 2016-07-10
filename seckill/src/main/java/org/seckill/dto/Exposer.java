package org.seckill.dto;

public class Exposer {
	
	private boolean exposed;
	private Long seckillId;
	private String md5;
	
	private long now;
	private long start;
	private long end;
	
	public Exposer(boolean exposed, Long seckillId, String mD5) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
		md5 = mD5;
	}

	public Exposer(boolean exposed, Long seckillId,long now, long start, long end) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	public Exposer(boolean exposed, Long seckillId) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public Long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}

	public String getMD5() {
		return md5;
	}

	public void setMD5(String mD5) {
		md5 = mD5;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}		
	
}

package org.seckill.exception;

public class RepeatKillException extends SeckillException {

	private static final long serialVersionUID = 8397838588376825183L;

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatKillException(String message) {
		super(message);
	}
	
	
}

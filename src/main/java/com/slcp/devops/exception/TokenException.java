package com.slcp.devops.exception;

/**
 * Token处理异常
 *
 * @author devops
 */
public class TokenException extends RuntimeException {

	private static final long serialVersionUID = -109638013567525177L;

	public TokenException(String message) {
		super(message);
	}
}

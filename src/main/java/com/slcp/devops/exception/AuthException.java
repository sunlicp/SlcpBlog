package com.slcp.devops.exception;

import org.springframework.http.HttpStatus;

/**
 * 基础异常
 *
 * @author ruoyi
 */
public class AuthException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	/**
	 * 所属模块
	 */
	private String module;

	/**
	 * 错误码
	 */
	private int code;

	/**
	 * 错误码对应的参数
	 */
	private Object[] args;

	/**
	 * 错误消息
	 */
	private String defaultMessage;

	public AuthException(String module, int code, Object[] args, String defaultMessage)
	{
		this.module = module;
		this.code = code;
		this.args = args;
		this.defaultMessage = defaultMessage;
	}

	public AuthException(String module, int code, Object[] args)
	{
		this(module, code, args, null);
	}

	public AuthException(String module, String defaultMessage)
	{
		this(module, HttpStatus.UNAUTHORIZED.value(), null, defaultMessage);
	}

	public AuthException(int code, Object[] args)
	{
		this(null, code, args, null);
	}

	public AuthException(String defaultMessage)
	{
		this(null, HttpStatus.UNAUTHORIZED.value(), null, defaultMessage);
	}

	public String getModule()
	{
		return module;
	}

	public int getCode()
	{
		return code;
	}

	public Object[] getArgs()
	{
		return args;
	}

	public String getDefaultMessage()
	{
		return defaultMessage;
	}
}

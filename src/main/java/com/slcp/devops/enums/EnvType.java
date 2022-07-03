package com.slcp.devops.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 环境常量
 *
 * @author devops
 */

@Getter
@AllArgsConstructor
public enum EnvType {

	/**
	 * 环境变量
	 * LOCAL 本地
	 * DEV 开发
	 * TEST 测试
	 * PROD 生产
	 * DOCKER Docker
	 */
	LOCAL("local"),
	DEV("dev"),
	TEST("test"),
	PROD("prod"),
	DOCKER("docker");

	private final String value;
}

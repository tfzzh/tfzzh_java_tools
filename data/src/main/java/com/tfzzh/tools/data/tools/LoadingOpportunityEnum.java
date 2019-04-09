/**
 * @author Weijie Xu
 * @dateTime Aug 14, 2014 7:30:09 PM
 */
package com.tfzzh.tools.data.tools;

import com.tfzzh.exception.ConfigurationException;

/**
 * 加载时机的类型<br />
 * 针对系统对象的加载<br />
 * 需要时加载当前并未有效时间，需要独立的时间处理——2014-08-14<br />
 * 
 * @author Weijie Xu
 * @dateTime Aug 14, 2014 7:30:09 PM
 */
public enum LoadingOpportunityEnum {
	/**
	 * 初始化时加载
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:31:23 PM
	 */
	Init("i"),
	/**
	 * 在需要时加载<br />
	 * 当前并未有效实现该行为——2014-08-14：XuWeijie<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:31:42 PM
	 */
	ForGet("g"),
	/**
	 * 当起上层对象被创建时<br />
	 * 当前并未有效实现该行为——2014-08-16：XuWeijie<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 2:35:34 PM
	 */
	Create("c");

	/**
	 * 目标值
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:33:09 PM
	 */
	private final String val;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:33:08 PM
	 * @param val 目标值
	 */
	LoadingOpportunityEnum(final String val) {
		this.val = val;
	}

	/**
	 * 得到加载时机
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:37:26 PM
	 * @param val 值
	 * @return 目标加载时机
	 */
	public static LoadingOpportunityEnum getOpportunity(final String val) {
		for (final LoadingOpportunityEnum e : LoadingOpportunityEnum.values()) {
			if (e.val.equalsIgnoreCase(val)) {
				return e;
			}
		}
		throw new ConfigurationException("Not Exists LoadingOpportunity:" + val);
	}
}

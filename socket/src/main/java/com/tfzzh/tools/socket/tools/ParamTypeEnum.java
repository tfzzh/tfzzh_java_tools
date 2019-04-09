/**
 * @author Weijie Xu
 * @dateTime 2014年6月12日 下午4:16:19
 */
package com.tfzzh.tools.socket.tools;

import com.tfzzh.exception.ConfigurationException;

/**
 * 参数Bean类型
 * 
 * @author Weijie Xu
 * @dateTime 2014年6月12日 下午4:16:19
 */
public enum ParamTypeEnum {
	/**
	 * 正常的
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月12日 下午4:17:02
	 */
	Normal,
	/**
	 * 动态的，针对根据指定参数ID给目标属性赋值，此过程消息是无序的
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月12日 下午4:17:04
	 */
	Dynamic;

	/**
	 * 得到指定类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月12日 下午4:22:03
	 * @param type 类型
	 * @return 对应的类型
	 */
	public static ParamTypeEnum getType(final String type) {
		for (final ParamTypeEnum t : ParamTypeEnum.values()) {
			if (t.name().equalsIgnoreCase(type)) {
				return t;
			}
		}
		throw new ConfigurationException("Not exists Param type >> " + type);
	}
}

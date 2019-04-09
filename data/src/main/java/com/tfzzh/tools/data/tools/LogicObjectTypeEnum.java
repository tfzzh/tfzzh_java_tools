/**
 * @author Weijie Xu
 * @dateTime Aug 21, 2014 10:04:48 AM
 */
package com.tfzzh.tools.data.tools;

import com.tfzzh.exception.ConfigurationException;

/**
 * 逻辑对象类型
 * 
 * @author Weijie Xu
 * @dateTime Aug 21, 2014 10:04:48 AM
 */
public enum LogicObjectTypeEnum {
	/**
	 * 与数据相关的对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 10:07:00 AM
	 */
	Data,
	/**
	 * 过渡类型对象，与数据库无任何直接关系
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 10:07:01 AM
	 */
	Transition,
	/**
	 * 通过解析字段内容得到的对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 10:07:02 AM
	 */
	Analysis;

	/**
	 * 得到类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 10:50:51 AM
	 * @param type 类型值
	 * @return 得到的类型
	 */
	public static LogicObjectTypeEnum getType(final String type) {
		for (final LogicObjectTypeEnum e : LogicObjectTypeEnum.values()) {
			if (e.name().equalsIgnoreCase(type)) {
				return e;
			}
		}
		throw new ConfigurationException("Error Logic object type:" + type);
	}
}

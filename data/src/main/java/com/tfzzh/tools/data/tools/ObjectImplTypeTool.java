/**
 * @author Weijie Xu
 * @dateTime Sep 10, 2014 8:36:50 PM
 */
package com.tfzzh.tools.data.tools;

import com.tfzzh.exception.ConfigurationException;

/**
 * 对象实现类型工具
 * 
 * @author Weijie Xu
 * @dateTime Sep 10, 2014 8:36:50 PM
 */
public class ObjectImplTypeTool {

	/**
	 * 得到Map实现
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 28, 2014 7:00:47 PM
	 * @param type Map类型值
	 * @return Map实现名
	 */
	public static String getMapImpl(final int type) {
		switch (type) {
		case 1:
			return "HashMap";
		case 2:
			return "LinkedHashMap";
		case 3:
			return "TreeMap";
		case 4:
			return "ConcurrentHashMap";
		default:
			throw new ConfigurationException("Error Logic Map Param type: " + type + " limit 1~4");
		}
	}

	/**
	 * 得到List实现
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 28, 2014 7:02:25 PM
	 * @param type List类型值
	 * @return List实现名
	 */
	public static String getListImpl(final int type) {
		switch (type) {
		case 1:
			return "LinkedList";
		case 2:
			return "ArrayList";
		default:
			throw new ConfigurationException("Error Logic List Param type: " + type + " limit 1~2");
		}
	}
}

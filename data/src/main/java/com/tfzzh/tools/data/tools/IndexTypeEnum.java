/**
 * @author Weijie Xu
 * @dateTime Aug 13, 2014 10:33:05 AM
 */
package com.tfzzh.tools.data.tools;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.tools.data.bean.DataBeanTool.DataFieldTool;

/**
 * 索引类型
 * 
 * @author Weijie Xu
 * @dateTime Aug 13, 2014 10:33:05 AM
 */
public enum IndexTypeEnum {
	/**
	 * 普通索引
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 10:34:52 AM
	 */
	Normal("0") {

		@Override
		public Map<String, DataFieldTool[]> getRecodeMap() {
			return new TreeMap<>();
		}

		@Override
		public int getIndexByteTotal(final int byteTotal, final int byteCount) {
			return byteTotal + byteCount;
		}
	},
	/**
	 * 唯一索引
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 10:34:53 AM
	 */
	Unique("1") {

		@Override
		public int getIndexByteTotal(final int byteTotal, final int byteCount) {
			return byteTotal + byteCount;
		}
	},
	/**
	 * 全文索引
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 10:34:53 AM
	 */
	FullText("2") {

		@Override
		public int getIndexByteTotal(final int byteTotal, final int byteCount) {
			return byteTotal;
		}
	},
	/**
	 * 主键索引
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 10:34:53 AM
	 */
	Primary("3") {

		@Override
		public int getIndexByteTotal(final int byteTotal, final int byteCount) {
			return byteTotal;
		}
	};

	/**
	 * 值
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 10:36:05 AM
	 */
	private final String val;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 10:36:06 AM
	 * @param val 值
	 */
	IndexTypeEnum(final String val) {
		this.val = val;
	}

	/**
	 * 得到记录用的Map
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 2:08:26 PM
	 * @return 记录用的Map
	 */
	public Map<String, DataFieldTool[]> getRecodeMap() {
		return new LinkedHashMap<>();
	}

	/**
	 * 得到结果用的Map
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 2:14:52 PM
	 * @return 结果用的Map
	 */
	public Map<String, DataFieldTool[]> getResultMap() {
		return new LinkedHashMap<>();
	}

	/**
	 * 得到索引字节数总量
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 3:51:53 PM
	 * @param byteTotal 当前的总量
	 * @param byteCount 新的索引相关字节数
	 * @return 索引字节数总量
	 */
	public abstract int getIndexByteTotal(int byteTotal, int byteCount);

	/**
	 * 得到目标类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 11:29:38 AM
	 * @param type 类型值
	 * @return 目标类型
	 */
	public static IndexTypeEnum getType(final String type) {
		for (final IndexTypeEnum e : IndexTypeEnum.values()) {
			if (e.val.equals(type)) {
				return e;
			}
		}
		throw new ConfigurationException("Error Index type:" + type);
	}
}

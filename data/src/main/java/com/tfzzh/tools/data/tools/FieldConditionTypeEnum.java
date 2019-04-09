/**
 * @author Weijie Xu
 * @dateTime 2014-3-10 下午4:47:15
 */
package com.tfzzh.tools.data.tools;

import java.util.Arrays;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.tools.StringTools;
import com.tfzzh.tools.data.bean.DataBeanTool.DataFieldTool;
import com.tfzzh.tools.data.bean.LogicBeanTool.LogicFieldTool;
import com.tfzzh.tools.data.bean.tools.DataConditionBean;
import com.tfzzh.tools.data.bean.tools.LogicConditionBean;

/**
 * 条件类型
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-10 下午4:47:15
 */
public enum FieldConditionTypeEnum {
	/**
	 * 值类型<br />
	 * 对应的为目标值<br />
	 * 结构模型：val(属性Id,目标值)<br />
	 * 目标值：如为数值，直接写数字；如为字符串，请增加两边的双引号（半角）<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-10 下午4:48:07
	 */
	Value {

		@Override
		protected boolean isStartWith(final String content) {
			return content.startsWith("val(");
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 16, 2014 5:59:21 PM
		 * @see com.tfzzh.tools.data.tools.FieldConditionTypeEnum#analysisLogicContent(java.lang.String)
		 */
		@Override
		protected LogicConditionBean analysisLogicContent(final String content) {
			// 去掉前后缀
			final String[] d = StringTools.split(content.trim().substring(4, content.length() - 1), ",");
			if (d.length >= 2) {
				final LogicFieldTool lf = ToolBeanExcelPool.getInstance().getLogicFieldTool(Long.parseLong(d[0]));
				if (null == lf) {
					throw new ConfigurationException("Error Condition Content -- not exists logic field code:" + content);
				}
				return new LogicConditionBean(lf, d[1]);
			} else {
				throw new ConfigurationException("Error Condition Content -- contition length:" + content);
			}
		}

		@Override
		protected DataConditionBean analysisDataContent(final String content) {
			// 去掉前后缀
			final String[] d = StringTools.split(content.trim().substring(4, content.length() - 1), ",");
			if (d.length >= 2) {
				final DataFieldTool df = ToolBeanExcelPool.getInstance().getDataFieldTool(Long.parseLong(d[0]));
				if (null == df) {
					throw new ConfigurationException("Error Condition Content -- not exists data field code:" + content);
				}
				return new DataConditionBean(df, Arrays.copyOfRange(d, 1, d.length, String[].class));
			} else {
				throw new ConfigurationException("Error Condition Content -- contition length:" + content);
			}
		}
	},
	/**
	 * 仅是为了得到值<br />
	 * 针对一些规则解析数据<br />
	 * 结构模型：als(属性Id,目标值相关数据属性Id)<br />
	 * 目标值相关数据属性Id：一定是数据对象中相关属性Id，一定对应字符串类型，属于集合列，目的为根据改属性的值进行一些操作，如解析数据为对象<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月23日 下午12:38:36
	 */
	GetValue {

		@Override
		protected boolean isStartWith(final String content) {
			return content.startsWith("als(");
		}

		@Override
		protected LogicConditionBean analysisLogicContent(final String content) {
			// 去掉前后缀
			final String[] d = StringTools.split(content.trim().substring(4, content.length() - 1), ",");
			if (d.length == 2) {
				final LogicFieldTool lf = ToolBeanExcelPool.getInstance().getLogicFieldTool(Long.parseLong(d[0]));
				if (null == lf) {
					throw new ConfigurationException("Error Condition Content -- not exists logic field code:" + content);
				}
				final DataFieldTool tarDf = ToolBeanExcelPool.getInstance().getDataFieldTool(Long.parseLong(d[1]));
				if (null == tarDf) {
					throw new ConfigurationException("Error Condition Content -- not exists data field code:" + content);
				}
				return new LogicConditionBean(lf, tarDf);
			} else {
				throw new ConfigurationException("Error Condition Content -- contition length:" + content);
			}
		}

		/**
		 * 当前来说不可能被出现的情况
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 23, 2014 7:18:33 PM
		 * @see com.tfzzh.tools.data.tools.FieldConditionTypeEnum#analysisDataContent(java.lang.String)
		 */
		@Deprecated
		@Override
		protected DataConditionBean analysisDataContent(final String content) {
			throw new ConfigurationException("Not likely appear:" + content);
		}
	},
	/**
	 * 属性类型<br />
	 * 对应为当前对象的，对应编码属性的值<br />
	 * 结构模型：mch(属性Id,目标逻辑属性Id的当前值)<br />
	 * 目标逻辑属性Id的当前值：针对逻辑对象中的相关属性Id，目标属性类型与当前属性类型应该是相同的，为等值匹配操作<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-10 下午4:48:13
	 */
	Attribute {

		@Override
		protected boolean isStartWith(final String content) {
			return content.startsWith("mch(");
		}

		@Override
		protected LogicConditionBean analysisLogicContent(final String content) {
			// 去掉前后缀
			final String[] d = StringTools.split(content.trim().substring(4, content.length() - 1), ",");
			if (d.length == 2) {
				if (d[0].equals(d[1])) {
					throw new ConfigurationException("Error Condition Content -- the same logic field:" + content);
				}
				final LogicFieldTool lf = ToolBeanExcelPool.getInstance().getLogicFieldTool(Long.parseLong(d[0]));
				if (null == lf) {
					throw new ConfigurationException("Error Condition Content -- not exists logic field code:" + content);
				}
				final LogicFieldTool tarLf = ToolBeanExcelPool.getInstance().getLogicFieldTool(Long.parseLong(d[1]));
				if (null == tarLf) {
					throw new ConfigurationException("Error Condition Content -- not exists logic field code:" + content);
				}
				return new LogicConditionBean(lf, tarLf);
			} else {
				throw new ConfigurationException("Error Condition Content -- contition length:" + content);
			}
		}

		@Override
		protected DataConditionBean analysisDataContent(final String content) {
			// 去掉前后缀
			final String[] d = StringTools.split(content.trim().substring(4, content.length() - 1), ",");
			if (d.length == 2) {
				if (d[0].equals(d[1])) {
					throw new ConfigurationException("Error Condition Content -- the same data field:" + content);
				}
				final DataFieldTool df = ToolBeanExcelPool.getInstance().getDataFieldTool(Long.parseLong(d[0]));
				if (null == df) {
					throw new ConfigurationException("Error Condition Content -- not exists data field code:" + content);
				}
				final DataFieldTool tarDf = ToolBeanExcelPool.getInstance().getDataFieldTool(Long.parseLong(d[1]));
				if (null == tarDf) {
					throw new ConfigurationException("Error Condition Content -- not exists data field code:" + content);
				}
				return new DataConditionBean(df, tarDf);
			} else {
				throw new ConfigurationException("Error Condition Content -- contition length:" + content);
			}
		}
	};

	/**
	 * 是否匹配开始的字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 4:38:52 PM
	 * @param content 目标内容
	 * @return true，匹配；<br />
	 *         false，不匹配；<br />
	 */
	protected abstract boolean isStartWith(String content);

	/**
	 * 解析内容获得逻辑条件
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 5:06:47 PM
	 * @param content 目标内容
	 * @return 解析出的条件对象
	 */
	protected abstract LogicConditionBean analysisLogicContent(String content);

	/**
	 * 解析内容获得数据条件
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 7:16:56 PM
	 * @param content 目标内容
	 * @return 解析出的条件对象
	 */
	protected abstract DataConditionBean analysisDataContent(String content);

	/**
	 * 得到条件类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 4:42:03 PM
	 * @param content 条件内容
	 * @return 目标条件
	 */
	public static FieldConditionTypeEnum getConditionType(String content) {
		content = content.toLowerCase();
		for (final FieldConditionTypeEnum e : FieldConditionTypeEnum.values()) {
			if (e.isStartWith(content)) {
				return e;
			}
		}
		throw new ConfigurationException("Unknow content Prefix: " + content);
	}

	/**
	 * 根据内容得到逻辑条件对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 5:02:40 PM
	 * @param content 目标内容
	 * @return 条件对象
	 */
	public static LogicConditionBean getLogicConditionWithContent(String content) {
		content = content.toLowerCase();
		for (final FieldConditionTypeEnum e : FieldConditionTypeEnum.values()) {
			if (e.isStartWith(content)) {
				return e.analysisLogicContent(content);
			}
		}
		throw new ConfigurationException("Unknow Logic content Prefix: " + content);
	}

	/**
	 * 根据内容得到数据条件对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 7:17:34 PM
	 * @param content 目标内容
	 * @return 条件对象
	 */
	public static DataConditionBean getDataConditionWithContent(String content) {
		content = content.toLowerCase();
		for (final FieldConditionTypeEnum e : FieldConditionTypeEnum.values()) {
			if (e.isStartWith(content)) {
				return e.analysisDataContent(content);
			}
		}
		throw new ConfigurationException("Unknow Data content Prefix: " + content);
	}
}

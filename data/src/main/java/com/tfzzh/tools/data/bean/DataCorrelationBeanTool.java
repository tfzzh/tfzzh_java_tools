/**
 * @author Weijie Xu
 * @dateTime Aug 23, 2014 11:00:28 AM
 */
package com.tfzzh.tools.data.bean;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.tools.StringTools;
import com.tfzzh.tools.data.bean.DataBeanTool.DataFieldTool;
import com.tfzzh.tools.data.bean.tools.DataConditionBean;
import com.tfzzh.tools.data.bean.tools.DataCorrelationNode;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;
import com.tfzzh.tools.data.tools.FieldConditionTypeEnum;
import com.tfzzh.tools.data.tools.ToolBeanExcelPool;

/**
 * 数据关联关系工具
 * 
 * @author Weijie Xu
 * @dateTime Aug 23, 2014 11:00:28 AM
 */
public class DataCorrelationBeanTool extends TemplateObjectTool implements DataBean {

	/**
	 * 关系数据对象列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:15:48 PM
	 */
	private final Map<Long, DataCorrelationNode> correlationDatas;

	/**
	 * 主关系对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:15:48 PM
	 */
	private final DataCorrelationNode mainNode;

	// /**
	// * 是否保证在初始化时，相关各列表数据，只会被使用一次
	// * 当天被废弃
	// *
	// * @author Weijie Xu
	// * @dateTime Aug 27, 2014 8:40:16 PM
	// */
	// private final boolean useOnly;
	/**
	 * 获取关联数据对象Id列表用SQL语句
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 27, 2014 9:51:11 PM
	 */
	private final String sql;

	/**
	 * 源码包路径
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:18:05 PM
	 */
	private final String srcPath;

	/**
	 * 功能名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:18:05 PM
	 */
	private final String functionName;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:24:09 PM
	 * @param id 属性的ID
	 * @param name 属性名
	 * @param desc 属性的说明
	 * @param mainData 主对象ID
	 * @param condition 关联条件
	 * @param sql 是否保证在初始化时，相关各列表数据，只会被使用一次
	 * @param srcPath 源码包路径
	 * @param functionName 功能名
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 */
	public DataCorrelationBeanTool(final Long id, final String name, final String desc, final Long mainData, final String condition, final String sql, final String srcPath, final String functionName, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
		super(id, name, desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		this.correlationDatas = new LinkedHashMap<>();
		this.mainNode = this.analyticalConditions(mainData, condition);
		this.sql = sql;
		this.srcPath = srcPath.toLowerCase();
		this.functionName = functionName.toLowerCase();
	}

	/**
	 * 分析条件，在进入到其他初始化操作时执行
	 * 概念上，在此前，已经将对象所需要的所有属性字段都处理好了
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-8 下午5:32:25
	 * @param mainDataId 主节点ID
	 * @param condition 条件文字串
	 * @return 结果集
	 */
	private DataCorrelationNode analyticalConditions(final Long mainDataId, final String condition) {
		// 得到主节点
		final DataBeanTool mainData = ToolBeanExcelPool.getInstance().getDataTool(mainDataId);
		if (null == mainData) {
			throw new ConfigurationException("Not Exists DataBean[" + mainData + "]");
		}
		final DataCorrelationNode mainNode = new DataCorrelationNode(mainData);
		// 放入到节点池
		this.correlationDatas.put(mainNode.getId(), mainNode);
		// 数据解析提取
		final String[] lines = StringTools.split(condition, "&");
		final List<DataConditionBean> cl = new LinkedList<>();
		for (final String l : lines) {
			if (l.length() > 0) {
				// 解析内容，并放入列表
				cl.add(FieldConditionTypeEnum.getDataConditionWithContent(l));
			}
		}
		{ // 处理条件逻辑，前提是非有序
			DataConditionBean dc;
			DataFieldTool df1;
			DataCorrelationNode dcn1;
			DataFieldTool df2;
			DataCorrelationNode dcn2;
			final int max = cl.size();
			// 被连续的问题数量
			int consecutiveNumber = 0;
			while (cl.size() > 0) {
				if (consecutiveNumber == max) {
					throw new ConfigurationException("Error Analytical Condition[" + super.getId() + "] :" + condition);
				}
				dc = cl.remove(0);
				switch (dc.getConditionType()) {
				case Value: // 为固定值，直接找到目标对象，并放入进去
					df1 = dc.getField();
					dcn1 = this.correlationDatas.get(df1.getBelongData().getId());
					if (null == dcn1) {
						// 增加连续问题数量，并放回被移除的问题
						consecutiveNumber++;
						cl.add(dc);
						continue;
					} else {
						//
						consecutiveNumber = 0;
						// 放入到与上层条件
						dcn1.putCondition(dc);
					}
					break;
				case Attribute: // 与属性值对应，找到相关的两个对象，并分别处理
					df1 = dc.getField();
					df2 = dc.getTarField();
					dcn1 = this.correlationDatas.get(df1.getBelongData().getId());
					dcn2 = this.correlationDatas.get(df2.getBelongData().getId());
					if ((null == dcn1) && (null == dcn2)) {
						// 因为均不存在，增加连续问题数量，并放回被移除的问题
						consecutiveNumber++;
						cl.add(dc);
						continue;
					} else {
						consecutiveNumber = 0;
						if (null == dcn1) {
							// 类中的主不存在，而目标存在
							// 将主放于目标中
							dcn1 = dcn2.putChildNode(ToolBeanExcelPool.getInstance().getDataTool(df1.getBelongData().getId()), dc);
							// 进行位置互换
							dc.swop();
							// 放入到节点池
							this.correlationDatas.put(dcn1.getId(), dcn1);
						} else if (null == dcn2) {
							// 类中的主存在，而目标不存在
							// 直接将目标放于主中
							dcn2 = dcn1.putChildNode(ToolBeanExcelPool.getInstance().getDataTool(df2.getBelongData().getId()), dc);
							// 放入到节点池
							this.correlationDatas.put(dcn2.getId(), dcn2);
						} else {
							// 两个都存在的情况，判定是否为主动
							if (dcn1 == dcn2.getChildNode(df1.getBelongData().getId())) {
								// 主属于目标
								dcn1.putCondition(dc);
								// 进行位置互换
								dc.swop();
							} else if (dcn2 == dcn1.getChildNode(df2.getBelongData().getId())) {
								// 目标属于主
								dcn2.putCondition(dc);
							} else {
								// 这是不应该出现的情况
								throw new ConfigurationException("This's Should not be the Case. Already exists but is not related to the two data: " + condition);
							}
						}
					}
					break;
				default:
					// 这是不应该出现的情况
					throw new ConfigurationException("This's Should not be the Case. Error Data Correlation Type: " + condition);
				}
			}
			// 将处理好的条件，逐个放入到目标数据对象中
			for (final DataCorrelationNode n : this.correlationDatas.values()) {
				n.getDataBean().addSingleDataCondition(n.getWithUpper());
			}
		}
		return mainNode;
	}

	/**
	 * 得到关系数据对象列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 2:04:15 PM
	 * @return the correlationDatas
	 */
	public Collection<DataCorrelationNode> getCorrelationDatas() {
		return this.correlationDatas.values();
	}

	/**
	 * 得到关系主节点
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 2:04:15 PM
	 * @return the mainData
	 */
	public DataCorrelationNode getMainData() {
		return this.mainNode;
	}

	// /**
	// * 是否保证在初始化时，相关各列表数据，只会被使用一次
	// *
	// * @author Weijie Xu
	// * @dateTime Aug 27, 2014 8:41:31 PM
	// * @return the useOnly
	// */
	// public boolean isUseOnly() {
	// return this.useOnly;
	// }
	/**
	 * 得到获取关联数据对象Id列表用SQL语句
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 27, 2014 9:53:15 PM
	 * @return the sql
	 */
	public String getSql() {
		return this.sql;
	}

	/**
	 * 得到对象后缀名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月4日 下午8:41:15
	 * @see com.tfzzh.tools.data.bean.DataBean#getSuffix()
	 */
	@Override
	public String getSuffix() {
		return ConfigDataTemplateConstants.CLASSBEAN_ENTITY_CORRELATION_SUFFIX;
	}

	/**
	 * 得到作为内部的对像的后缀名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月5日 下午4:09:06
	 * @see com.tfzzh.tools.data.bean.DataBean#getInnerSuffix()
	 */
	@Override
	public String getInnerSuffix() {
		return ConfigDataTemplateConstants.CLASSBEAN_ENTITY_CORRELATION_SUFFIX;
	}

	/**
	 * 得到特殊字，在一些拼字命名中
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月5日 下午8:33:46
	 * @see com.tfzzh.tools.data.bean.DataBean#getSpecialWord()
	 */
	@Override
	public String getSpecialWord() {
		return ConfigDataTemplateConstants.CLASSBEAN_ENTITY_CORRELATION_SPECIAL;
	}

	/**
	 * 得到源码包路径
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 2:04:15 PM
	 * @return the srcPath
	 */
	public String getSrcPath() {
		return this.srcPath;
	}

	/**
	 * 得到功能名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 2:04:15 PM
	 * @return the functionName
	 */
	public String getFunctionName() {
		return this.functionName;
	}

	/**
	 * 是否源数据
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 7:39:51 PM
	 * @see com.tfzzh.tools.data.bean.DataBean#isSourceData()
	 */
	@Override
	public boolean isSourceData() {
		return false;
	}
}

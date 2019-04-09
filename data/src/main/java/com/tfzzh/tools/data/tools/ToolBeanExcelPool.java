/**
 * @author Weijie Xu
 * @dateTime 2014-3-6 下午8:51:38
 */
package com.tfzzh.tools.data.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.tools.data.bean.DataBean;
import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.bean.DataBeanTool.DataFieldTool;
import com.tfzzh.tools.data.bean.DataBeanTool.ParentDataTool;
import com.tfzzh.tools.data.bean.DataCorrelationBeanTool;
import com.tfzzh.tools.data.bean.LogicBeanTool;
import com.tfzzh.tools.data.bean.LogicBeanTool.LogicFieldTool;
import com.tfzzh.tools.data.bean.tools.LinkKeyBean;

/**
 * 工具Bean池
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-6 下午8:51:38
 */
public class ToolBeanExcelPool {

	/**
	 * 数据实体结构对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-18 下午2:08:45
	 */
	private final Map<Long, DataBeanTool> dataMap = new HashMap<>();

	/**
	 * 功能性数据实体结构对象列表
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月14日_上午10:00:37
	 */
	private final Map<String, List<DataBeanTool>> functionDataMap = new HashMap<>();

	/**
	 * 数据实体功能通用父类结构对象
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月20日_下午3:23:07
	 */
	private final Map<String, ParentDataTool> parentDataMap = new HashMap<>();

	/**
	 * 数据属性列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 7:17:13 PM
	 */
	private final Map<Long, DataFieldTool> dataFieldMap = new HashMap<>();

	/**
	 * 数据关联关系列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 12:17:37 PM
	 */
	private final Map<Long, DataCorrelationBeanTool> dataCorrelationMap = new HashMap<>();

	/**
	 * 逻辑结构对象总表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-18 下午2:08:45
	 */
	private final Map<Long, LogicBeanTool> logicMap = new HashMap<>();

	/**
	 * 数据逻辑对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 1:39:27 PM
	 */
	private final Map<Long, LogicBeanTool> dataLogicMap = new HashMap<>();

	/**
	 * 过渡逻辑对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 1:39:27 PM
	 */
	private final Map<Long, LogicBeanTool> transitionLogicMap = new HashMap<>();

	/**
	 * 解析逻辑对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 1:39:28 PM
	 */
	private final Map<Long, LogicBeanTool> analysisLogicMap = new HashMap<>();

	/**
	 * 逻辑属性列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 7:18:07 PM
	 */
	private final Map<Long, LogicFieldTool> logicFieldMap = new HashMap<>();

	/**
	 * 属性类型相关结构对象列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月19日 下午8:18:43
	 */
	private final Map<FieldTypeEnum, List<LogicBeanTool>> attrTypeStructureMap = new HashMap<>();

	/**
	 * 关联连接键列表，对象类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 5:05:19 PM
	 */
	private final Map<String, LinkKeyBean> linkKeyMap = new HashMap<>();

	/**
	 * 连接键列表，基础类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2015年1月14日 下午4:06:19
	 */
	private final Set<Class<?>> linkKeySet = new HashSet<>();

	/**
	 * 对象唯一实例
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午8:52:59
	 */
	private static ToolBeanExcelPool pool = new ToolBeanExcelPool();

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-18 下午2:09:17
	 */
	private ToolBeanExcelPool() {
		this.init();
	}

	/**
	 * 初始化方法
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-18 下午2:09:19
	 */
	private void init() {
		// 进行数据读取操作
	}

	/**
	 * 得到对象实例
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午8:52:52
	 * @return 对象唯一实例
	 */
	public static ToolBeanExcelPool getInstance() {
		return ToolBeanExcelPool.pool;
	}

	/**
	 * 放入一个数据实体结构对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-18 下午2:09:35
	 * @param tool 数据实体结构对象
	 */
	public void putDataBeanTool(final DataBeanTool tool) {
		final String fn = tool.getFunctionName();
		List<DataBeanTool> tmp = this.functionDataMap.get(fn);
		if (null == tmp) {
			tmp = new ArrayList<>();
			this.functionDataMap.put(fn, tmp);
		}
		tmp.add(tool);
		this.dataMap.put(tool.getId(), tool);
	}

	/**
	 * 放入一个数据属性对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 7:19:13 PM
	 * @param tool 数据属性对象
	 */
	public void putDataFieldTool(final DataFieldTool tool) {
		this.dataFieldMap.put(tool.getId(), tool);
	}

	/**
	 * 放入一个数据关联关系对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 12:18:04 PM
	 * @param tool 数据关联关系对象
	 */
	public void putDataCorrelationBeanTool(final DataCorrelationBeanTool tool) {
		this.dataCorrelationMap.put(tool.getId(), tool);
	}

	/**
	 * 放入一个数据类结构对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-18 下午2:09:36
	 * @param tool 结构对象
	 */
	public void putLogicBeanTool(final LogicBeanTool tool) {
		this.logicMap.put(tool.getId(), tool);
		switch (tool.getType()) {
		case Data:
			this.dataLogicMap.put(tool.getId(), tool);
			break;
		case Transition:
			this.transitionLogicMap.put(tool.getId(), tool);
			break;
		case Analysis:
			this.analysisLogicMap.put(tool.getId(), tool);
			break;
		}
	}

	/**
	 * 放入一个逻辑属性对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 7:19:52 PM
	 * @param tool 逻辑属性对象
	 */
	public void putLogicFieldTool(final LogicFieldTool tool) {
		this.logicFieldMap.put(tool.getId(), tool);
	}

	/**
	 * 放入一个属性类型相关结构对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月19日 下午8:25:55
	 * @param ft 输出属性类型
	 * @param sturcture 结构对象
	 */
	public void putAttributeTypeRelation(final FieldTypeEnum ft, final LogicBeanTool sturcture) {
		List<LogicBeanTool> list = this.attrTypeStructureMap.get(ft.getAttrbuteType());
		if (null == list) {
			list = new LinkedList<>();
			this.attrTypeStructureMap.put(ft.getAttrbuteType(), list);
			list.add(sturcture);
		} else {
			if (!list.contains(sturcture)) {
				list.add(sturcture);
			}
		}
	}

	/**
	 * 放入一个关联类型（对象）连接键信息
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 5:10:14 PM
	 * @param linkKey 关联连接键信息
	 */
	public void putObjectLinkKey(final LinkKeyBean linkKey) {
		// 放入到列表，不在意重复键
		this.linkKeyMap.put(linkKey.getKeyName(), linkKey);
	}

	/**
	 * 放入基本类型的连接键信息
	 * 
	 * @author Weijie Xu
	 * @dateTime 2015年1月14日 下午4:08:04
	 * @param clz 基本类型对象
	 */
	public void putBasicLinkKey(final Class<?> clz) {
		this.linkKeySet.add(clz);
	}

	/**
	 * 第二次整理数据
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 10:45:17 AM
	 */
	public void tidyData() {
		for (final DataBeanTool t : this.dataMap.values()) {
			t.tidyIndex();
		}
	}

	/**
	 * 进行一些初始化操作
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月16日 下午8:57:55
	 */
	public void initLogicTools() {
		// 第一次全部的关系对象初始化
		for (final LogicBeanTool t : this.logicMap.values()) {
			t.init();
		}
		// 第二次全部的关系对象初始化
		for (final LogicBeanTool t : this.logicMap.values()) {
			t.secondInit();
		}
	}

	/**
	 * 得到一个数据实体对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-18 下午2:10:22
	 * @param id 目标实体对象ID
	 * @return 目标数据实体对象
	 */
	public DataBeanTool getDataTool(final Long id) {
		final DataBeanTool result = this.dataMap.get(id);
		if (null != result) {
			return result;
		} else {
			throw new ConfigurationException("Not Exists 1:DataBean Id:" + id);
		}
	}

	/**
	 * 得到所有的数据实体对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月26日 下午4:34:44
	 * @return 所有的数据实体对象
	 */
	public Collection<DataBeanTool> getAllData() {
		return this.dataMap.values();
	}

	/**
	 * 得到功能性数据实体结构对象列表
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月14日_上午10:16:01
	 * @return the functionDataMap
	 */
	public Map<String, List<DataBeanTool>> getFunctionDataMap() {
		return this.functionDataMap;
	}

	/**
	 * 得到指定的数据字段信息
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 7:41:56 PM
	 * @param id 目标ID
	 * @return 目标数据字段信息
	 */
	public DataFieldTool getDataFieldTool(final Long id) {
		final DataFieldTool result = this.dataFieldMap.get(id);
		if (null != result) {
			return result;
		} else {
			throw new ConfigurationException("Not Exists 2:DataField Id:" + id);
		}
	}

	/**
	 * 得到指定数据关联关系对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月27日 上午10:08:43
	 * @param id 数据关联关系ID
	 * @return 数据关联关系对象
	 */
	public DataCorrelationBeanTool getDataCorrelation(final Long id) {
		return this.dataCorrelationMap.get(id);
	}

	/**
	 * 得到所有的父级数据实体对象
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月20日_下午8:07:21
	 * @return 所有的父级数据实体对象
	 */
	public Collection<ParentDataTool> getAllParentData() {
		return this.parentDataMap.values();
	}

	/**
	 * 得到所有的数据关联关系列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 12:42:52 PM
	 * @return 数据关联关系列表
	 */
	public Collection<DataCorrelationBeanTool> getAllDataCorrelation() {
		return this.dataCorrelationMap.values();
	}

	/**
	 * 得到目标数据对象（一般数据对象，数据关联关系对象）
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 3:50:02 PM
	 * @param id 目标ID
	 * @return 目标数据对象
	 */
	public DataBean getDataBean(final Long id) {
		DataBean db;
		if (null == (db = this.dataMap.get(id))) {
			if (null == (db = this.dataCorrelationMap.get(id))) {
				throw new ConfigurationException("Not Exists 5:DataBean/DataCorrelationBean Id:" + id);
			}
		}
		return db;
	}

	/**
	 * 得到父级数据对象
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月20日_下午3:32:35
	 * @param name 父级数据对象名
	 * @return 目标父级数据对象
	 */
	public ParentDataTool getParentDataBean(final String name) {
		return this.parentDataMap.get(name);
	}

	/**
	 * 放入一个父级数据对象
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月20日_下午7:15:16
	 * @param name 父级数据对象名
	 * @param pdt 目标父级数据对象
	 */
	public void putParentDataBean(final String name, final ParentDataTool pdt) {
		this.parentDataMap.put(name, pdt);
	}

	/**
	 * 得到一个逻辑实体对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-18 下午2:10:23
	 * @param id 目标类对象ID
	 * @return 目标逻辑实体对象
	 */
	public LogicBeanTool getLogicTool(final Long id) {
		final LogicBeanTool result = this.logicMap.get(id);
		if (null != result) {
			return result;
		} else if (id != 0) {
			throw new ConfigurationException("Not Exists 3:LogicBean Id:" + id);
		} else {
			return null;
		}
	}

	/**
	 * 得到所有逻辑实体对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 7:46:41 PM
	 * @return 所有逻辑实体对象
	 */
	public Collection<LogicBeanTool> getAllLogic() {
		return this.logicMap.values();
	}

	/**
	 * 得到数据逻辑对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 1:47:48 PM
	 * @return 数据逻辑对象
	 */
	public Collection<LogicBeanTool> getDataLogic() {
		return this.dataLogicMap.values();
	}

	/**
	 * 得到过渡逻辑对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 1:47:49 PM
	 * @return 过渡逻辑对象
	 */
	public Collection<LogicBeanTool> getTransitionLogic() {
		return this.transitionLogicMap.values();
	}

	/**
	 * 得到解析逻辑对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 1:47:50 PM
	 * @return 解析逻辑对象
	 */
	public Collection<LogicBeanTool> getAnalysisLogic() {
		return this.analysisLogicMap.values();
	}

	/**
	 * 得到指定的逻辑字段信息
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 7:46:57 PM
	 * @param id 目标ID
	 * @return 目标逻辑字段信息
	 */
	public LogicFieldTool getLogicFieldTool(final Long id) {
		final LogicFieldTool result = this.logicFieldMap.get(id);
		if (null != result) {
			return result;
		} else {
			throw new ConfigurationException("Not Exists 4:LogicField Id:" + id);
		}
	}

	/**
	 * 得到所有的数据类对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-18 下午2:10:26
	 * @param suffix 排序用名称的后缀
	 * @return 数据类对象列表
	 */
	public Collection<LogicBeanTool> getAllLogic(final String suffix) {
		final Collection<LogicBeanTool> coll = this.logicMap.values();
		final List<LogicBeanTool> list = new LinkedList<>(coll);
		Collections.sort(list, new Comparator<LogicBeanTool>() {

			@Override
			public int compare(final LogicBeanTool b1, final LogicBeanTool b2) {
				final String n1 = b1.getName() + suffix;
				final String n2 = b2.getName() + suffix;
				return n1.compareTo(n2);
			}
		});
		return list;
	}

	/**
	 * 得到属性类型相关结构对象列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月19日 下午8:25:29
	 * @return 属性类型相关结构对象列表
	 */
	public Set<Entry<FieldTypeEnum, List<LogicBeanTool>>> getAllAttrTypeSturcture() {
		return this.attrTypeStructureMap.entrySet();
	}

	/**
	 * 得到关联连接键列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 5:06:27 PM
	 * @return 关联（对象）连接键列表
	 */
	public Collection<LinkKeyBean> getObjectLinkKeys() {
		return this.linkKeyMap.values();
	}

	/**
	 * 得到基本类型的连接键信息
	 * 
	 * @author Weijie Xu
	 * @dateTime 2015年1月14日 下午4:18:23
	 * @return 基本类型的连接键信息
	 */
	public Collection<Class<?>> getBasicLinkKeys() {
		return this.linkKeySet;
	}
}

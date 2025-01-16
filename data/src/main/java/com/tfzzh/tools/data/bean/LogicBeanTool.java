/**
 * @author Weijie Xu
 * @dateTime Aug 12, 2014 6:46:43 PM
 */
package com.tfzzh.tools.data.bean;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.tools.StringTools;
import com.tfzzh.tools.data.bean.DataBeanTool.DataFieldTool;
import com.tfzzh.tools.data.bean.tools.LinkKeyBean;
import com.tfzzh.tools.data.bean.tools.LogicConditionBean;
import com.tfzzh.tools.data.bean.tools.SymbolsRule;
import com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;
import com.tfzzh.tools.data.tools.FieldConditionTypeEnum;
import com.tfzzh.tools.data.tools.FieldTypeEnum;
import com.tfzzh.tools.data.tools.LoadingOpportunityEnum;
import com.tfzzh.tools.data.tools.LogicObjectTypeEnum;
import com.tfzzh.tools.data.tools.ToolBeanExcelPool;

/**
 * 数据对象工具<br />
 * 实际在项目中被开发者调用的类<br />
 * 类中属于不与数据库直接相关，需要通过数据对象进行转代<br />
 * 
 * @author Weijie Xu
 * @dateTime Aug 12, 2014 6:46:43 PM
 */
public class LogicBeanTool extends TemplateObjectTool {

	/**
	 * 逻辑对象类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 12:12:12 PM
	 */
	private final LogicObjectTypeEnum type;

	/**
	 * 源码包路径
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:10:27 PM
	 */
	private final String srcPath;

	/**
	 * 功能名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:10:28 PM
	 */
	private final String functionName;

	/**
	 * 加载时机，默认为初始化时加载<br />
	 * 当前该功能还未实现<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:38:35 PM
	 */
	private LoadingOpportunityEnum loadOppo = LoadingOpportunityEnum.ForGet;

	/**
	 * 对象主列表的加载时机
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 2:56:34 PM
	 */
	private LoadingOpportunityEnum loadingOpportunity = LoadingOpportunityEnum.ForGet;

	/**
	 * 字段列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:11:33 PM
	 */
	private final Map<Long, LogicFieldTool> fieldMap = new LinkedHashMap<>();

	/**
	 * 列表类（Map，MapList，List）字段列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 4:54:55 PM
	 */
	private final Map<Long, LogicFieldTool> listFieldMap = new LinkedHashMap<>();

	/**
	 * 对象类（Bean，Map，MapList，List）字段列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 28, 2014 4:34:54 PM
	 */
	private final Map<Long, LogicFieldTool> beanFieldMap = new LinkedHashMap<>();

	/**
	 * 解析对象类字段列表，仅跟从数据相关对象存在
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 10, 2014 6:14:49 PM
	 */
	private final Map<Long, LogicFieldTool> analysisFieldMap = new LinkedHashMap<>();

	/**
	 * 闲置属性，与数据及关联都无关的属性
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月19日 下午1:45:38
	 */
	private final Map<Long, LogicFieldTool> idleFieldMap = new LinkedHashMap<>();

	/**
	 * 相关的属性数量
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月30日 下午4:45:36
	 */
	private int count = 0;

	/**
	 * 是否存在不能变更的字段
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月30日 下午4:48:58
	 */
	private boolean hasCanntChangeField = false;

	/**
	 * 属性集合
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 5:15:53 PM
	 */
	private final Map<FieldTypeEnum, List<LogicFieldTool>> attributeTypeMap = new LinkedHashMap<>();

	// /**
	// * 列表类属性集合
	// *
	// * @author Weijie Xu
	// * @dateTime Aug 19, 2014 9:34:03 PM
	// */
	// private final Map<FieldTypeEnum, List<LogicFieldTool>> listAttributeTypeMap = new HashMap<FieldTypeEnum,
	// List<LogicFieldTool>>();
	/**
	 * 所相关逻辑对象集合，针对列表和对象属性
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 10:37:35 AM
	 */
	private final Set<LogicBeanTool> childrenLogicTools = new LinkedHashSet<>();

	/**
	 * 相关的数据对象/数据关联关系对象，仅在为数据类对象中有效
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 2:06:35 PM
	 */
	private final DataBean dataTool;

	/**
	 * 相关的数据字段，仅在为解析类逻辑对象中有效
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 4:08:51 PM
	 */
	private final DataFieldTool dataField;

	// /**
	// * 所相关的数据对象集合
	// *
	// * @author Weijie Xu
	// * @dateTime Aug 14, 2014 7:14:27 PM
	// */
	// private final Set<DataBeanTool> childrenDataTools = new HashSet<DataBeanTool>();
	/**
	 * 与主数据列表数据关联键集合
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 12:42:49 PM
	 */
	private final List<LogicFieldTool> linkKeys = new LinkedList<>();

	/**
	 * 关联连接键对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 9:02:26 PM
	 */
	private LinkKeyBean linkKey = null;

	/**
	 * 后缀名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月5日 上午10:43:36
	 */
	private final String suffix;

	/**
	 * 得到作为内部的对像的后缀名<br />
	 * 针对element<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月5日 下午3:40:27
	 */
	private final String innerSuffix;

	/**
	 * 解析模版类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:56:57 PM
	 */
	private AnalysisTemplateTypeEnum analysisTemplate = null;

	/**
	 * 解析模板实现类型，针对特殊模板才有效
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 10, 2014 8:48:47 PM
	 */
	private int analysisTemplateImplType = 0;

	/**
	 * 解析符号规则
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:56:58 PM
	 */
	private SymbolsRule symbolsRule = null;

	/**
	 * 是否含有时间属性字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 5:01:48 PM
	 */
	private boolean hasDate = false;

	/**
	 * 是否含有时间转字串类属性字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 8:28:04 PM
	 */
	private boolean hasDateString = false;

	/**
	 * 是否存在Bean属性
	 * 
	 * @author Weijie Xu
	 * @dateTime 2015年1月17日 下午2:45:09
	 */
	private boolean hasBean = false;

	/**
	 * 是否存在Map属性
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 1, 2014 5:15:21 PM
	 */
	private boolean hasMap = false;

	/**
	 * 是否存在List属性
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 9:36:57 PM
	 */
	private boolean hasList = false;

	/**
	 * 是否存在Input类型的属性，在相关的数据字段中
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 2:39:17 PM
	 */
	private boolean hasInput = false;

	/**
	 * 是否存在条件属性（列表）字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 3, 2014 1:28:32 PM
	 */
	private boolean hasCondition = false;

	/**
	 * 是否需要进行第二轮的数据处理
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 28, 2014 3:08:21 PM
	 */
	private boolean hasSecond = false;

	/**
	 * 父级对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 2:34:46 PM
	 */
	private LogicBeanTool parent = null;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 2:26:57 PM
	 * @param id 被定义的ID
	 * @param name Bean名称
	 * @param desc 类的说明
	 * @param type 逻辑对象类型值
	 * @param dataId 数据ID，对象ID或字段ID
	 * @param srcPath 源码包路径
	 * @param functionName 功能名
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 */
	public LogicBeanTool(final Long id, final String name, final String desc, final String type, final Long dataId, final String srcPath, final String functionName, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
		super(id, name, desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		this.type = LogicObjectTypeEnum.getType(type);
		switch (this.type) {
		case Data: // 是数据对象ID
			this.dataTool = ToolBeanExcelPool.getInstance().getDataBean(dataId);
			this.dataField = null;
			this.suffix = ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
			this.innerSuffix = ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
			break;
		case Analysis: // 是数据字段ID，当前未实现，后处理
			this.dataTool = null;
			this.dataField = ToolBeanExcelPool.getInstance().getDataFieldTool(dataId);
			this.suffix = ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
			this.innerSuffix = ConfigDataTemplateConstants.CLASSBEAN_CLASS_ELEMENT_SUFFIX;
			break;
		default: // 其他情况
			this.dataTool = null;
			this.dataField = null;
			this.suffix = ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
			this.innerSuffix = ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
			break;
		}
		this.srcPath = srcPath;
		this.functionName = functionName;
	}

	/**
	 * 设置解析用信息，针对需要解析的逻辑对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:58:34 PM
	 * @param templateType 模板类型
	 * @param splitSymbols 拆分符
	 * @param analysisTemplateImplType 解析模板实现类型
	 */
	public void putAnalysisInfo(final String templateType, final String splitSymbols, final int analysisTemplateImplType) {
		if (this.type != LogicObjectTypeEnum.Analysis) {
			// 不是解析类型，直接报错
			throw new ConfigurationException("Error Logic[" + super.getId() + "] Type:" + this.type.name() + " Cann't Analysis.");
		}
		this.analysisTemplate = AnalysisTemplateTypeEnum.getType(templateType);
		if ((null != splitSymbols) && (splitSymbols.length() > 0)) {
			this.symbolsRule = new SymbolsRule(splitSymbols);
		}
		this.analysisTemplateImplType = analysisTemplateImplType;
	}

	/**
	 * 放入一个字段信息，解析属性字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 5:11:15 PM
	 * @param id 属性的ID
	 * @param name 属性名
	 * @param type 字段类型名
	 * @param desc 属性的说明
	 * @param isDataLinkKey 是否主数据列表数据关联键
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 * @return 新生成的字段
	 */
	public LogicFieldTool addField(final Long id, final String name, final String type, final String desc, final boolean isDataLinkKey, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
		final LogicFieldTool f = new LogicFieldTool(id, name, FieldTypeEnum.getType(type), desc, !isDataLinkKey, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		// 放入数据关联键
		if (isDataLinkKey) {
			this.linkKeys.add(f);
		}
		// 放入到类型列表
		if (null != this.fieldMap.put(f.getId(), f)) {
			throw new ConfigurationException("Exists LogicFieldId[" + f.getId() + "] in Logic:" + super.getId());
		}
		return f;
	}

	/**
	 * 放入一个字段信息，基本属性字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 7:21:11 PM
	 * @param id 属性的ID
	 * @param name 属性名
	 * @param dataFieldId 相关的数据对象中属性ID
	 * @param type 字段类型名
	 * @param desc 属性的说明
	 * @param isDataLinkKey 是否主数据列表数据关联键
	 * @param canChange 如果是数据关联属性是否可变
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 * @return 新生成的字段
	 *         --@param relatedLogicId 相关的逻辑对象ID
	 *         --@param isList 是否列表属性字段
	 */
	public LogicFieldTool addField(final Long id, final String name, final Long dataFieldId, final String type, final String desc, final boolean isDataLinkKey, final boolean canChange, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
		final LogicFieldTool f = new LogicFieldTool(id, name, dataFieldId, FieldTypeEnum.getType(type), desc, canChange, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		// 放入数据关联键
		if (isDataLinkKey) {
			this.linkKeys.add(f);
		}
		// 放入到类型列表 2014-09-04
		if (null != this.fieldMap.put(f.getId(), f)) {
			throw new ConfigurationException("Exists LogicFieldId[" + f.getId() + "] in Logic:" + super.getId());
		}
		return f;
	}

	/**
	 * 放入一个字段信息，解析对象属性字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 10, 2014 7:02:46 PM
	 * @param id 属性的ID
	 * @param name 属性名
	 * @param type 字段的类型
	 * @param desc 属性的说明
	 * @param relatedLogic 相关的逻辑对象，为解析类对象
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 * @return 被创建的逻辑属性
	 */
	public LogicFieldTool addField(final Long id, final String name, final String type, final String desc, final LogicBeanTool relatedLogic, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
		if (null == this.dataTool) {
			// 解析类属性，仅能存在于数据相关的逻辑对象中
			throw new ConfigurationException("Analysis Field Only in Data Related LogicFieldId[" + id + "] in Logic:" + super.getId());
		}
		final LogicFieldTool f = new LogicFieldTool(id, name, FieldTypeEnum.getType(type), desc, relatedLogic, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		// 放入到类型列表
		if (null != this.analysisFieldMap.put(f.getId(), f)) {
			throw new ConfigurationException("Exists LogicFieldId[" + f.getId() + "] in Logic:" + super.getId());
		}
		return f;
	}

	/**
	 * 放入一个字段信息，对象属性字段，树形结构的节点属性
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 12:49:57 PM
	 * @param id 属性的ID
	 * @param name 属性名
	 * @param condition 关联条件
	 * @param type 字段类型名
	 * @param keyFieldId 如果是Map属性，则Map的Key的类型
	 * @param desc 属性的说明
	 * @param relatedLogic 相关的逻辑对象
	 * @param objListType 对象列表类型，针对，Map，List，MapList
	 * @param loadOppo 加载时机字段
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 * @return 新生成的字段
	 */
	public LogicFieldTool addField(final Long id, final String name, final String condition, final String type, final Long keyFieldId, final String desc, final LogicBeanTool relatedLogic, final int objListType, final String loadOppo, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
		final LogicFieldTool f = new LogicFieldTool(id, name, condition, FieldTypeEnum.getType(type), keyFieldId, desc, relatedLogic, objListType, loadOppo, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		if (!this.hasSecond) {
			this.hasSecond = true;
		}
		if (null != this.beanFieldMap.put(f.getId(), f)) {
			throw new ConfigurationException("Exists LogicFieldId[" + f.getId() + "] in Logic:" + super.getId());
		}
		return f;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年12月19日 下午1:47:37
	 * @param id 属性的ID
	 * @param name 属性名
	 * @param type 字段的类型
	 * @param desc 属性的说明
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 * @return 被创建的逻辑属性
	 */
	public LogicFieldTool addIdleField(final Long id, final String name, final String type, final String desc, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
		final LogicFieldTool f = new LogicFieldTool(id, name, FieldTypeEnum.getType(type), desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		// 验证是否存在同ID数据
		if (this.fieldMap.containsKey(f.getId()) || (null != this.idleFieldMap.put(f.getId(), f))) {
			throw new ConfigurationException("Exists LogicFieldId[" + f.getId() + "] in Logic:" + super.getId());
		}
		return f;
	}

	/**
	 * 得到逻辑对象类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 12:13:06 PM
	 * @return the type
	 */
	public LogicObjectTypeEnum getType() {
		return this.type;
	}

	/**
	 * 得到相关的数据对象/数据关联关系对象，仅在为数据类对象中有效
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 4:16:40 PM
	 * @return the dataTool
	 */
	public DataBean getDataTool() {
		return this.dataTool;
	}

	/**
	 * 得到相关的数据字段，仅在为解析类逻辑对象中有效
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 4:16:40 PM
	 * @return the dataField
	 */
	public DataFieldTool getDataField() {
		return this.dataField;
	}

	/**
	 * 得到对象后缀名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月4日 下午8:41:15
	 * @return 对象后缀名
	 */
	public String getSuffix() {
		return this.suffix;
	}

	/**
	 * 得到作为内部的对像的后缀名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月5日 下午3:41:11
	 * @return 作为内部的对像的后缀名
	 */
	public String getInnerSuffix() {
		return this.innerSuffix;
	}

	/**
	 * 得到源码包路径
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 2:29:49 PM
	 * @return the srcPath
	 */
	public String getSrcPath() {
		return this.srcPath;
	}

	/**
	 * 得到功能名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 2:29:49 PM
	 * @return the functionName
	 */
	public String getFunctionName() {
		return this.functionName;
	}

	/**
	 * 得到加载时机，默认为初始化时加载<br />
	 * 当前该功能还未实现<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 2:29:49 PM
	 * @return the loadOppo
	 */
	public LoadingOpportunityEnum getLoadOppo() {
		return this.loadOppo;
	}

	/**
	 * 设置加载时机，默认为初始化时加载<br />
	 * 当前该功能还未实现<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 2:29:49 PM
	 * @param loadOppoVal the loadOppoVal to set
	 */
	public void setLoadOppo(final String loadOppoVal) {
		this.loadOppo = LoadingOpportunityEnum.getOpportunity(loadOppoVal);
	}

	/**
	 * 是否根节点<br />
	 * 当前根据父级对象的存在与否判定<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 29, 2014 9:56:24 AM
	 * @return true，是根节点
	 */
	public boolean isRootNode() {
		if (this.dataField == null) { // add by xuweijie 2015-01-07
			return null == this.parent;
		} else {
			return false;
		}
	}

	/**
	 * 得到父级对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 2:35:07 PM
	 * @return the parent
	 */
	public LogicBeanTool getParent() {
		return this.parent;
	}

	/**
	 * 设置父级对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 2:35:07 PM
	 * @param parent the parent to set
	 */
	public void setParent(final LogicBeanTool parent) {
		this.parent = parent;
	}

	/**
	 * 得到基础属性（非列表属性）字段列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 8:42:29 PM
	 * @return the fieldMap
	 */
	public Collection<LogicFieldTool> getFields() {
		return this.fieldMap.values();
	}

	/**
	 * 得到列表属性字段列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 8:42:29 PM
	 * @return the listFieldMap
	 */
	public Collection<LogicFieldTool> getListFields() {
		return this.listFieldMap.values();
	}

	/**
	 * 得到对象属性字段列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 28, 2014 4:36:35 PM
	 * @return the beanFieldMap
	 */
	public Collection<LogicFieldTool> getBeanFields() {
		return this.beanFieldMap.values();
	}

	/**
	 * 得到对象属性字段数量
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年9月19日 下午2:32:18
	 * @return 对象属性字段数量
	 */
	public int getBeanFieldCount() {
		return this.beanFieldMap.size();
	}

	/**
	 * 得到解析对象类字段列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 10, 2014 7:07:00 PM
	 * @return the analysisFieldMap
	 */
	public Collection<LogicFieldTool> getAnalysisFields() {
		return this.analysisFieldMap.values();
	}

	/**
	 * 得到所包含的数据实体列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 7:54:19 PM
	 * @return 包含的数据实体列表
	 */
	public Collection<LogicBeanTool> getChildrenLogics() {
		return this.childrenLogicTools;
	}

	/**
	 * 得到空闲字段列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月19日 下午2:46:16
	 * @return the idleFieldMap
	 */
	public Collection<LogicFieldTool> getIdleFields() {
		return this.idleFieldMap.values();
	}

	/**
	 * 得到相关的属性数量
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月30日 下午4:46:55
	 * @return the count
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * 是否存在不可变更的字段
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月30日 下午4:49:33
	 * @return the isHasCanntChangeField
	 */
	public boolean isHasCanntChangeField() {
		return this.hasCanntChangeField;
	}

	// /**
	// * 得到所包含的数据实体列表
	// *
	// * @author Weijie Xu
	// * @dateTime Aug 15, 2014 7:54:19 PM
	// * @return 包含的数据实体列表
	// */
	// public Collection<DataBeanTool> getChildrenData() {
	// return this.childrenDataTools;
	// }
	// /**
	// * 得到与主数据列表数据关联键集合
	// *
	// * @author Weijie Xu
	// * @dateTime Aug 18, 2014 8:42:29 PM
	// * @return the linkKeys
	// */
	// public List<LogicFieldTool> getLinkKeys() {
	// return this.linkKeys;
	// }
	/**
	 * 得到与主数据列表数据关联键
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 9:09:43 PM
	 * @return 数据关联键
	 */
	public LinkKeyBean getLinkKey() {
		return this.linkKey;
	}

	/**
	 * 得到相关属性集合列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 8:36:28 PM
	 * @return 相关属性集合列表
	 */
	public Set<Entry<FieldTypeEnum, List<LogicFieldTool>>> getAttributeTypes() {
		return this.attributeTypeMap.entrySet();
	}

	// /**
	// * 得到相关属性集合列表
	// *
	// * @author Weijie Xu
	// * @dateTime Aug 19, 2014 9:35:35 PM
	// * @return 相关属性集合列表
	// */
	// public Set<Entry<FieldTypeEnum, List<LogicFieldTool>>> getListAttributeTypes() {
	// return this.listAttributeTypeMap.entrySet();
	// }
	/**
	 * 得到对象主列表的加载时机
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 2:57:18 PM
	 * @return the loadingOpportunity
	 */
	public LoadingOpportunityEnum getLoadingOpportunity() {
		return this.loadingOpportunity;
	}

	/**
	 * 设置为自动启动，针对根节点对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 2:57:18 PM
	 */
	public void setAutomaticLoading() {
		this.loadingOpportunity = LoadingOpportunityEnum.Init;
	}

	/**
	 * 得到解析模版类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 5:04:13 PM
	 * @return the analysisTemplate
	 */
	public AnalysisTemplateTypeEnum getAnalysisTemplate() {
		return this.analysisTemplate;
	}

	/**
	 * 得到解析对象的类型名
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 3:44:29 PM
	 * @return 解析对象的类型名
	 */
	public String getAnalysisTypeName() {
		return this.analysisTemplate.getTypeName(super.getProName() + ConfigDataTemplateConstants.CLASSBEAN_CLASS_ELEMENT_SUFFIX, this.linkKey == null ? null : this.linkKey.getProName());
	}

	/**
	 * 得到解析对象类型的实现名
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 10, 2014 8:49:16 PM
	 * @return 解析对象类型的实现名
	 */
	public String getAnalysisTypeImplName() {
		return this.analysisTemplate.getTypeImplName(super.getProName() + ConfigDataTemplateConstants.CLASSBEAN_CLASS_ELEMENT_SUFFIX, this.linkKey == null ? null : this.linkKey.getProName(), this.analysisTemplateImplType);
	}

	/**
	 * 得到解析符号规则
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 5:04:13 PM
	 * @return the symbolsRule
	 */
	public SymbolsRule getSymbolsRule() {
		return this.symbolsRule;
	}

	/**
	 * 是否含有时间属性字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 5:02:43 PM
	 * @return the hasDate
	 */
	public boolean isHasDate() {
		return this.hasDate;
	}

	/**
	 * 是否含有时间转字串类属性字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 8:28:52 PM
	 * @return the hasDateString
	 */
	public boolean isHasDateString() {
		return this.hasDateString;
	}

	/**
	 * 是否存在Bean属性
	 * 
	 * @author Weijie Xu
	 * @dateTime 2015年1月17日 下午2:45:45
	 * @return the hasBean
	 */
	public boolean isHasBean() {
		return this.hasBean;
	}

	/**
	 * 是否存在Map属性
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 1, 2014 5:15:46 PM
	 * @return the hasMap
	 */
	public boolean isHasMap() {
		return this.hasMap;
	}

	/**
	 * 是否存在List属性
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 9:39:51 PM
	 * @return the hasList
	 */
	public boolean isHasList() {
		return this.hasList;
	}

	/**
	 * 是否存在Input类型的属性，在相关的数据字段中
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 2:39:56 PM
	 * @return the hasInput
	 */
	public boolean isHasInput() {
		return this.hasInput;
	}

	/**
	 * 是否存在条件属性（列表）字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 3, 2014 1:30:06 PM
	 * @return the hasCondition
	 */
	public boolean isHasCondition() {
		return this.hasCondition;
	}

	/**
	 * 是否需要进行第二轮的数据处理
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 28, 2014 3:09:16 PM
	 * @return the hasSecond
	 */
	public boolean isHasSecond() {
		return this.hasSecond;
	}

	/**
	 * 在对象都被初始化完成后，才被调用的方法
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 4:55:31 PM
	 */
	public void init() {
		// FIXME 需要相关逻辑，还需要补足一些逻辑
		for (final LogicFieldTool t : this.fieldMap.values()) {
			t.logicInit();
		}
		for (final LogicFieldTool t : this.listFieldMap.values()) {
			t.logicInit();
		}
		// 设置相关连接键
		this.linkKey = LinkKeyBean.setLinkKey(this.linkKeys);
	}

	/**
	 * 第二次初始化操作，需要在第一次的初始化完全进行完成之后才进行
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 5:11:44 PM
	 */
	public void secondInit() {
		// FIXME 需要相关逻辑
	}

	/**
	 * 系统属性工具
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:11:41 PM
	 */
	public class LogicFieldTool extends TemplateObjectTool implements Comparable<LogicFieldTool> {

		/**
		 * 原始名称
		 * 
		 * @author Weijie Xu
		 * @dateTime 2015年1月8日 下午3:42:53
		 */
		private final String originalNames;

		/**
		 * 数据字段ID<br />
		 * 为0，则不存在对应的数据字段<br />
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 15, 2014 6:24:37 PM
		 */
		private final Long dataFieldId;

		/**
		 * 相关的数据对象中属性<br />
		 * 可以为null，在第一次初始化时候获得<br />
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 14, 2014 7:17:11 PM
		 */
		private DataFieldTool dataField = null;

		/**
		 * 如果是数据关联属性是否可变
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 16, 2014 2:54:25 PM
		 */
		private final boolean canChange;

		/**
		 * 字段的类型
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 14, 2014 7:18:58 PM
		 */
		private final FieldTypeEnum type;

		// /**
		// * 字段形式
		// *
		// * @author Weijie Xu
		// * @dateTime Aug 26, 2014 10:25:58 AM
		// */
		// private final FieldFormEnum fieldForm;
		/**
		 * 关键字属性
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 1:05:21 PM
		 */
		private final LogicFieldTool keyField;

		/**
		 * 相关的逻辑对象<br />
		 * 在处理树状结构图时被加入<br />
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 15, 2014 5:01:08 PM
		 */
		private final LogicBeanTool relatedLogic;

		/**
		 * 对象列表类型，针对，Map，List，MapList
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 28, 2014 6:52:53 PM
		 */
		private final int objListType;

		/**
		 * 加载时机
		 * 
		 * @author Weijie Xu
		 * @dateTime Sep 3, 2014 3:45:23 PM
		 */
		private final LoadingOpportunityEnum loadOppo;

		// /**
		// * 条件字串
		// *
		// * @author Weijie Xu
		// * @dateTime Aug 16, 2014 3:02:09 PM
		// */
		// private final String condition;
		/**
		 * 条件集合
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 16, 2014 3:02:09 PM
		 */
		private final Map<LogicFieldTool, LogicConditionBean> conditionMap;

		/**
		 * 解析类对象的属性
		 * 
		 * @author Weijie Xu
		 * @dateTime Sep 5, 2014 5:08:28 PM
		 * @param id 属性的ID
		 * @param name 属性名
		 * @param type 字段的类型
		 * @param desc 属性的说明
		 * @param canChange 属性值是否可变
		 * @param createAuthor 创建人
		 * @param createDate 创建时间
		 * @param lastChangeAuthor 最后修改人
		 * @param lastChangeDate 最后修改时间
		 */
		public LogicFieldTool(final Long id, final String name, final FieldTypeEnum type, final String desc, final boolean canChange, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
			super(id, name, desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
			this.originalNames = super.getName();
			this.dataFieldId = 0l;
			this.conditionMap = null;
			this.type = type;
			this.keyField = null;
			this.relatedLogic = null;
			this.objListType = 0;
			this.loadOppo = LoadingOpportunityEnum.Create;
			// 所有字段均可变化
			this.canChange = canChange;
			if (!this.canChange) {
				LogicBeanTool.this.hasCanntChangeField = true;
			}
			// 这里都是基本类型数据，以及解析类数据在这里（需要之后处理）
			// this.fieldForm = FieldFormEnum.BasicData;
			this.dataInit();
		}

		/**
		 * 基本属性字段
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 15, 2014 2:34:02 PM
		 * @param id 属性的ID
		 * @param name 属性名
		 * @param dataFieldId 相关的数据对象中属性ID
		 * @param type 字段的类型
		 * @param desc 属性的说明
		 * @param canChange 如果是数据关联属性是否可变
		 * @param createAuthor 创建人
		 * @param createDate 创建时间
		 * @param lastChangeAuthor 最后修改人
		 * @param lastChangeDate 最后修改时间
		 *           --@param relatedLogicId 相关的逻辑对象ID
		 */
		public LogicFieldTool(final Long id, final String name, final Long dataFieldId, final FieldTypeEnum type, final String desc, final boolean canChange, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
			super(id, name, desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
			this.originalNames = super.getName();
			this.dataFieldId = dataFieldId;
			this.conditionMap = null;
			this.type = type;
			this.keyField = null;
			this.relatedLogic = null;
			this.objListType = 0;
			this.loadOppo = LoadingOpportunityEnum.Create;
			this.canChange = canChange;
			if (!this.canChange) {
				LogicBeanTool.this.hasCanntChangeField = true;
			}
			// 这里都是基本类型数据，以及解析类数据在这里（需要之后处理）
			// this.fieldForm = FieldFormEnum.BasicData;
			this.dataInit();
		}

		/**
		 * 逻辑对象中的解析类属性对象
		 * 
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 6:48:31 PM
		 * @param id 属性的ID
		 * @param name 属性名
		 * @param type 字段的类型
		 * @param desc 属性的说明
		 * @param relatedLogic 相关的逻辑对象，为解析类对象
		 * @param createAuthor 创建人
		 * @param createDate 创建时间
		 * @param lastChangeAuthor 最后修改人
		 * @param lastChangeDate 最后修改时间
		 */
		public LogicFieldTool(final Long id, final String name, final FieldTypeEnum type, final String desc, final LogicBeanTool relatedLogic, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
			super(id, name, desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
			this.originalNames = super.getName();
			this.dataFieldId = 0l;
			this.conditionMap = null;
			this.type = type;
			this.keyField = null;
			this.relatedLogic = relatedLogic;
			this.objListType = 0;
			this.loadOppo = LoadingOpportunityEnum.Create;
			this.canChange = false;
			LogicBeanTool.this.hasCanntChangeField = true;
			// if (null != LogicBeanTool.this.fieldMap.put(super.getId(), this)) {
			// throw new ConfigurationException("Exists LogicFieldId[" + super.getId() + "] in Logic:" + super.getId());
			// }
			this.dataInit();
		}

		/**
		 * 树形结构中的对象节点属性
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 16, 2014 3:00:13 PM
		 * @param id 属性的ID
		 * @param name 属性名
		 * @param condition 关联条件
		 * @param type 字段的类型
		 * @param keyFieldId 如果是Map属性，则Map的Key的类型
		 * @param desc 属性的说明
		 * @param relatedLogic 相关的逻辑对象
		 * @param objListType 对象列表类型，针对，Map，List，MapList
		 * @param loadOppo 加载时机字段
		 * @param createAuthor 创建人
		 * @param createDate 创建时间
		 * @param lastChangeAuthor 最后修改人
		 * @param lastChangeDate 最后修改时间
		 */
		public LogicFieldTool(final Long id, final String name, final String condition, final FieldTypeEnum type, final Long keyFieldId, final String desc, final LogicBeanTool relatedLogic, final int objListType, final String loadOppo, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
			super(id, name, desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
			this.originalNames = name;
			this.dataFieldId = 0l;
			this.conditionMap = this.analyticalConditions(condition);
			this.type = type;
			if (keyFieldId != 0) {
				this.keyField = ToolBeanExcelPool.getInstance().getLogicFieldTool(keyFieldId);
			} else {
				this.keyField = null;
			}
			// if (type.isList()) {
			// // 列表类型
			// this.fieldForm = FieldFormEnum.ListData;
			// } else {
			// // 对象类型
			// this.fieldForm = FieldFormEnum.ObjectData;
			// }
			this.relatedLogic = relatedLogic;
			this.relatedLogic.setParent(LogicBeanTool.this);
			this.objListType = objListType;
			this.loadOppo = LoadingOpportunityEnum.getOpportunity(loadOppo);
			this.canChange = false;
			LogicBeanTool.this.hasCanntChangeField = true;
			LogicBeanTool.this.childrenLogicTools.add(this.relatedLogic);
			if (!this.type.isList()) {
				if (null != LogicBeanTool.this.fieldMap.put(super.getId(), this)) {
					throw new ConfigurationException("Exists LogicFieldId[" + super.getId() + "] in Logic:" + super.getId());
				} else if (LogicBeanTool.this.listFieldMap.containsKey(super.getId())) {
					// 正常来说不会有该情况
					throw new ConfigurationException("Exists LogicListFieldId[" + super.getId() + "] in Logic:" + super.getId());
				}
			} else {
				if (null != LogicBeanTool.this.listFieldMap.put(super.getId(), this)) {
					throw new ConfigurationException("Exists LogicListFieldId[" + super.getId() + "] in Logic:" + super.getId());
				} else if (LogicBeanTool.this.fieldMap.containsKey(super.getId())) {
					throw new ConfigurationException("Exists LogicFieldId[" + super.getId() + "] in Logic:" + super.getId());
				}
			}
			this.dataInit();
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年12月19日 下午1:53:40
		 * @param id 属性的ID
		 * @param name 属性名
		 * @param type 字段的类型
		 * @param desc 属性的说明
		 * @param createAuthor 创建人
		 * @param createDate 创建时间
		 * @param lastChangeAuthor 最后修改人
		 * @param lastChangeDate 最后修改时间
		 */
		public LogicFieldTool(final Long id, final String name, final FieldTypeEnum type, final String desc, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
			super(id, name, desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
			this.originalNames = name;
			this.dataFieldId = 0l;
			this.conditionMap = null;
			this.type = type;
			// TODO 临时处理方案
			switch (this.type) {
			case AllList:
			case AllMap:
				// this.keyField = new LogicFieldTool(999999l, "tmpObj", FieldTypeEnum.OjObject, "", false, "", "", "", "");
				// break;
			default:
				this.keyField = null;
				break;
			}
			this.relatedLogic = null;
			this.objListType = 0;
			this.loadOppo = LoadingOpportunityEnum.Create;
			// 此为必然可变属性
			this.canChange = true;
			LogicBeanTool.this.hasCanntChangeField = true;
			// if (null != LogicBeanTool.this.fieldMap.put(super.getId(), this)) {
			// throw new ConfigurationException("Exists LogicFieldId[" + super.getId() + "] in Logic:" + super.getId());
			// }
			this.dataInit();
		}

		/**
		 * 分析条件，在进入到其他初始化操作时执行
		 * 概念上，在此前，已经将对象所需要的所有属性字段都处理好了
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-8 下午5:32:25
		 * @param conds 条件文字串
		 * @return 结果集
		 */
		private Map<LogicFieldTool, LogicConditionBean> analyticalConditions(final String conds) {
			final Map<LogicFieldTool, LogicConditionBean> map = new TreeMap<>();
			{ // 第一层提取
				final String[] lines = StringTools.split(conds, "&");
				LogicConditionBean cb;
				for (final String l : lines) {
					if (l.length() > 0) {
						// 解析内容
						cb = FieldConditionTypeEnum.getLogicConditionWithContent(l);
						// 放入列表
						map.put(cb.getLogicField(), cb);
					}
				}
			}
			LogicBeanTool.this.hasCondition = true;
			return map;
		}

		/**
		 * 一些数据初始化工作
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 1:31:10 PM
		 */
		private void dataInit() {
			LogicBeanTool.this.count++;
			{ // 属性类型列表处理
				List<LogicFieldTool> list = LogicBeanTool.this.attributeTypeMap.get(this.type.getAttrbuteType());
				if (null == list) {
					list = new LinkedList<>();
					LogicBeanTool.this.attributeTypeMap.put(this.type.getAttrbuteType(), list);
					// 此时向池中进行数据放入
					ToolBeanExcelPool.getInstance().putAttributeTypeRelation(this.type.getAttrbuteType(), LogicBeanTool.this);
				}
				list.add(this);
				// if (null != this.keyField) {
				// list = LogicBeanTool.this.listAttributeTypeMap.get(this.keyField.type.getAttrbuteType());
				// if (null == list) {
				// list = new LinkedList<LogicFieldTool>();
				// LogicBeanTool.this.listAttributeTypeMap.put(this.keyField.type.getAttrbuteType(), list);
				// // // 此时向池中进行数据放入
				// // ToolBeanExcelPool.getInstance().putAttributeTypeRelation(this.keyField.type.getAttrbuteType(),
				// // LogicBeanTool.this);
				// }
				// list.add(this);
				// }
			}
			// if (!this.type.isList()) {
			// if (null != LogicBeanTool.this.fieldMap.put(super.getId(), this)) {
			// throw new ConfigurationException("Exists LogicFieldId[" + super.getId() + "] in Logic:" + super.getId());
			// } else if (LogicBeanTool.this.listFieldMap.containsKey(super.getId())) {
			// // 正常来说不会有该情况
			// throw new ConfigurationException("Exists LogicListFieldId[" + super.getId() + "] in Logic:" + super.getId());
			// }
			// } else {
			// if (null != LogicBeanTool.this.listFieldMap.put(super.getId(), this)) {
			// throw new ConfigurationException("Exists LogicListFieldId[" + super.getId() + "] in Logic:" + super.getId());
			// } else if (LogicBeanTool.this.fieldMap.containsKey(super.getId())) {
			// throw new ConfigurationException("Exists LogicFieldId[" + super.getId() + "] in Logic:" + super.getId());
			// }
			// }
			switch (this.type) {
			case OjDate:
				LogicBeanTool.this.hasDate = true;
				break;
			case OjDateDay:
				LogicBeanTool.this.hasDateString = true;
				break;
			case OjDateTime:
				LogicBeanTool.this.hasDateString = true;
				break;
			case AllMap:
			case IfMap:
				LogicBeanTool.this.hasMap = true;
				break;
			case IfMapList:
				LogicBeanTool.this.hasList = true;
				LogicBeanTool.this.hasMap = true;
				break;
			case AllList:
			case IfList:
				LogicBeanTool.this.hasList = true;
				break;
			case PrBean:
				LogicBeanTool.this.hasBean = true;
				break;
			default:
				break;
			}
		}

		/**
		 * TODO 对象初始化，需要完善相关逻辑
		 */
		private void logicInit() {
			// 设置有相关的属性信息
			if (this.dataFieldId > 0) {
				this.dataField = ToolBeanExcelPool.getInstance().getDataFieldTool(this.dataFieldId);
				if (this.dataField.getType() == FieldTypeEnum.IfInputStream) {
					LogicBeanTool.this.hasInput = true;
				}
				// if (null != this.dataField) {
				// // 因为存在而放入到所在逻辑实体的，相关数据实体列表中
				// LogicBeanTool.this.childrenDataTools.add(this.dataField.getBelongData());
				// }
			}
		}

		/**
		 * 得到原始名称
		 * 
		 * @author Weijie Xu
		 * @dateTime 2015年1月8日 下午3:43:35
		 * @return the originalNames
		 */
		public String getOriginalNames() {
			return this.originalNames;
		}

		/**
		 * 得到相关的数据对象中属性，
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 15, 2014 3:27:14 PM
		 * @return the dataField
		 */
		public DataFieldTool getDataField() {
			return this.dataField;
		}

		/**
		 * 得到字段的类型
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 15, 2014 3:27:14 PM
		 * @return the type
		 */
		public FieldTypeEnum getType() {
			return this.type;
		}

		/**
		 * 得到索引键的hashCode，限定环境，针对索引类——CorrelationIndex
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 27, 2014 3:18:14 PM
		 * @return 索引键hashCode用显示代码
		 */
		public String getIndexKeyHashCodeContent() {
			return this.type.getIndexKeyHashCodeContent(super.getName());
		}

		/**
		 * 得到类型名
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 19, 2014 4:53:19 PM
		 * @return 类型名
		 */
		public String getTypeName() {
			if (!this.type.isBean()) {
				return this.type.getTypeName();
			} else if (this.type.isBasicData()) {
				return this.type.getTypeName();
			} else {
				return this.type.getObjectTypeName(this.relatedLogic.getProName(), this.keyField == null ? null : this.keyField.getType().getObjectTypeName());
			}
		}

		/**
		 * 得到类型的对象名
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月1日 下午8:03:36
		 * @return 类型的对象名
		 */
		public String getTypeObjectName() {
			try {
				if (!this.type.isBean()) {
					return this.type.getObjectTypeName();
				} else {
					return this.type.getObjectTypeName(null == this.relatedLogic ? null : this.relatedLogic.getProName(), this.keyField == null ? null : this.keyField.getType().getObjectTypeName());
				}
			} catch (final Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		/**
		 * 得到对象类型实现用名
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 28, 2014 6:57:50 PM
		 * @return 对象类型实现用名
		 */
		public String getObjectTypeImplName() {
			if (!this.type.isBean()) {
				return this.type.getTypeName();
			} else {
				return this.type.getObjectTypeImplName(this.objListType, this.relatedLogic.getProName(), this.keyField == null ? null : this.keyField.getType().getObjectTypeName());
			}
		}

		// /**
		// * 得到字段形式
		// *
		// * @author Weijie Xu
		// * @dateTime Aug 26, 2014 10:26:18 AM
		// * @return the fieldForm
		// */
		// public FieldFormEnum getFieldForm() {
		// return this.fieldForm;
		// }
		/**
		 * 得到关键字属性
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 1:20:20 PM
		 * @return the keyField
		 */
		public LogicFieldTool getKeyField() {
			return this.keyField;
		}

		/**
		 * 得到如果是数据关联属性是否可变
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 11:22:05 AM
		 * @return the canChange
		 */
		public boolean isCanChange() {
			return this.canChange;
		}

		/**
		 * 得到加载时机
		 * 
		 * @author Weijie Xu
		 * @dateTime Sep 3, 2014 3:51:25 PM
		 * @return the loadOppo
		 */
		public LoadingOpportunityEnum getLoadOppo() {
			return this.loadOppo;
		}

		/**
		 * 得到相关的逻辑对象
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 11:22:05 AM
		 * @return the relatedLogic
		 */
		public LogicBeanTool getRelatedLogic() {
			return this.relatedLogic;
		}

		/**
		 * 得到所从属逻辑实体
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 19, 2014 8:56:01 PM
		 * @return 所从属逻辑实体
		 */
		public LogicBeanTool getBelongLogic() {
			return LogicBeanTool.this;
		}

		/**
		 * 得到条件集合
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 11:22:05 AM
		 * @return the conditionMap
		 */
		public Map<LogicFieldTool, LogicConditionBean> getConditionMap() {
			return this.conditionMap;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年12月9日 上午11:19:33
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(final LogicFieldTool o) {
			return (int) (this.dataFieldId - o.dataFieldId);
		}
	}
}

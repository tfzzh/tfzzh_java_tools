/**
 * @author Weijie Xu
 * @dateTime Aug 12, 2014 6:45:44 PM
 */
package com.tfzzh.tools.data.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.tools.StringTools;
import com.tfzzh.tools.data.bean.tools.DataConditionBean;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;
import com.tfzzh.tools.data.tools.DataConfigConstants;
import com.tfzzh.tools.data.tools.DatabaseFieldTypeEnum;
import com.tfzzh.tools.data.tools.FieldTypeEnum;
import com.tfzzh.tools.data.tools.IndexTypeEnum;
import com.tfzzh.tools.data.tools.ToolBeanExcelPool;

/**
 * 数据对象工具<br />
 * 与数据库数据直接相关的对象<br />
 * 正常情况下，不会被开发者直接调用<br />
 * 约同与之前的与数据相关的结构类<br />
 * 
 * @author Weijie Xu
 * @dateTime Aug 12, 2014 6:45:44 PM
 */
public class DataBeanTool extends TemplateObjectTool implements DataBean {

	/**
	 * 对应的数据库名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:01:41 PM
	 */
	private final String databaseName;

	/**
	 * 对应的数据表名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:01:49 PM
	 */
	private final String dataTableName;

	/**
	 * 表中主键，针对数据更新用
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 8:16:49 PM
	 */
	private final PrimaryTool mainKey = new PrimaryTool();

	/**
	 * 表中自增键，针对数据库创建语句
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 10:04:25 AM
	 */
	private DataFieldTool incrementKey;

	/**
	 * 所相关父类名字
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月20日_下午5:58:44
	 */
	private String parentName = null;

	/**
	 * 所相关父类数据
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月20日_下午4:42:45
	 */
	private final ParentDataTool parentData = null;

	/**
	 * 是否存在UUID
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 5:56:42 PM
	 */
	private boolean hasUuid = false;

	/**
	 * 是否存在日期属性字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 1:43:27 PM
	 */
	private boolean hasDate = false;

	/**
	 * 是否存在Map类型字段
	 * 
	 * @author XuWeijie
	 * @datetime 2015年9月17日_下午2:36:47
	 */
	private boolean hasMap = false;

	/**
	 * 是否存在List类型字段
	 * 
	 * @author XuWeijie
	 * @datetime 2015年9月17日_下午2:43:05
	 */
	private boolean hasList = false;

	/**
	 * 是否存在输出字节，针对Blob的字段配置
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 2:02:22 PM
	 */
	private boolean hasInput = false;

	/**
	 * 是否存在非String属性
	 * 
	 * @author tfzzh
	 * @dateTime 2020年8月17日 上午12:24:59
	 */
	private boolean hasNonString = false;

	/**
	 * 源码包路径
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 9:08:41 PM
	 */
	private final String srcPath;

	/**
	 * 功能名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 9:08:42 PM
	 */
	private final String functionName;

	/**
	 * 字段列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:08:08 PM
	 */
	private final Map<String, DataFieldTool> fieldMap = new LinkedHashMap<>();

	/**
	 * 索引列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 12:39:10 PM
	 */
	private final Map<IndexTypeEnum, Map<String, DataFieldTool[]>> indexMap = new TreeMap<>();

	/**
	 * 单一结果查询相关的数据条件列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 5:14:33 PM
	 */
	private final List<List<DataConditionBean>> singleDataConditionList = new LinkedList<>();

	/**
	 * 表数据字段名称对应，生成表数据时用
	 * 
	 * @author XuWeijie
	 * @datetime 2015年11月12日_下午1:04:50
	 */
	private List<DataFieldTool> fieldOrder = null;

	// /**
	// * 主键所在数据信息中位置
	// *
	// * @author XuWeijie
	// * @datetime 2015年11月13日_下午3:18:04
	// */
	// private int dataKeyInd = -1;
	/**
	 * 表数据信息列表，生成表数据时用
	 * 
	 * @author XuWeijie
	 * @datetime 2015年11月12日_下午12:53:48
	 */
	private final List<List<String>> dataList = new ArrayList<>();

	/**
	 * Json用数据列表
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月14日_下午4:06:45
	 */
	private final List<List<String>> jsonDataList = new ArrayList<>();

	/**
	 * 是否已经整理
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月24日_下午3:13:20
	 */
	private boolean isTidy = false;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:02:39 PM
	 * @param id 被定义的ID
	 * @param name Bean名称
	 * @param databaseName 对应的数据库名
	 * @param dataTableName 对应的数据表名
	 * @param desc 类的说明
	 * @param srcPath 源码包路径
	 * @param functionName 功能名
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 */
	public DataBeanTool(final Long id, final String name, final String databaseName, final String dataTableName, final String desc, final String srcPath, final String functionName, final String createAuthor, final String createDate,
			final String lastChangeAuthor, final String lastChangeDate) {
		super(id, name.indexOf("(") == -1 ? name : name.substring(0, name.indexOf("(")), desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		this.databaseName = databaseName;
		this.dataTableName = dataTableName;
		final int pi = name.indexOf("(");
		if (pi != -1) {
			final int pie = name.indexOf(")", pi);
			if (pie == -1) {
				this.parentName = name.substring(pi + 1);
			} else {
				this.parentName = name.substring(pi + 1, pie);
			}
			if (this.parentName.length() == 0) {
				this.parentName = null;
			}
		}
		this.srcPath = srcPath.toLowerCase();
		this.functionName = functionName.toLowerCase();
	}

	/**
	 * 放入一个字段信息
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 8:18:41 PM
	 * @param id 属性的ID
	 * @param name 属性名
	 * @param dataFieldName 对应常量用名
	 * @param fieldType 数据字段的类型
	 * @param length 字段的长度内容
	 * @param desc 字段的说明
	 * @param isKey 是否主键
	 * @param isIncrementKey 是否自增字段
	 * @param isUnique 是否唯一索引字段
	 * @param isUuid 是否UUID
	 * @param isUnsigned 是否非负
	 * @param canNull 是否可null
	 * @param def 默认值
	 * @param index 索引内容
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 * @return 被创建的字段信息
	 */
	public DataFieldTool addField(final Long id, final String name, final String dataFieldName, final String fieldType, final String length, final String desc, final Integer isKey, final boolean isUuid, final boolean isIncrementKey, final boolean isUnique,
			final boolean isUnsigned, final boolean canNull, final String def, final String index, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
		final DataFieldTool f = new DataFieldTool(id, name, dataFieldName, fieldType, length, desc, null != isKey, isUuid, isIncrementKey, isUnsigned, canNull, def, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		// 放入列表
		if (null != this.fieldMap.put(name, f)) {
			throw new ConfigurationException("Exists DataFieldId[" + id + "] in Data:" + super.getId());
		}
		// 主键判定
		// if (isKey && (null == this.mainKey)) {
		// ToolBeanExcelPool.getInstance().putBasicLinkKey(f.getType().getObjectTypeClass());
		// this.mainKey = f;
		// f.indexList.add(new FieldIndexTool(IndexTypeEnum.Primary));
		// }
		if (null != isKey) {
			ToolBeanExcelPool.getInstance().putBasicLinkKey(f.getType().getObjectTypeClass());
			this.mainKey.putField(isKey, f);
		}
		// 自增判定
		if (isIncrementKey && (null == this.incrementKey)) {
			this.incrementKey = f;
		}
		if (isUuid) {
			this.hasUuid = true;
		}
		// 唯一判定
		if (isUnique) {
			this.putIndexData(IndexTypeEnum.Unique, dataFieldName, 1, f, 0);
		}
		// 开始进行索引判定
		this.putIndex(index, f);
		return f;
	}

	/**
	 * @author Xu Weijie
	 * @datetime 2018年1月23日_上午10:48:23
	 * @param id 属性的ID
	 * @param name 属性名
	 * @param dataFieldName 对应常量用名
	 * @param fieldType 数据字段的类型
	 * @param length 字段的长度内容
	 * @param desc 字段的说明
	 * @param isIncrementKey 是否自增字段
	 * @param isUuid 是否UUID
	 * @param isUnsigned 是否非负
	 * @param canNull 是否可null
	 * @param def 默认值
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 * @return 被创建的字段信息
	 */
	public DataFieldTool addField(final Long id, final String name, final String dataFieldName, final String fieldType, final String length, final String desc, final boolean isUuid, final boolean isIncrementKey, final boolean isUnsigned,
			final boolean canNull, final String def, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
		final DataFieldTool f = new DataFieldTool(id, name, dataFieldName, fieldType, length, desc, false, isUuid, isIncrementKey, isUnsigned, canNull, def, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		// 放入列表
		if (null != this.fieldMap.put(name, f)) {
			throw new ConfigurationException("Exists DataFieldId[" + id + "] in Data:" + super.getId());
		}
		// 主键判定
		// if (isKey && (null == this.mainKey)) {
		// ToolBeanExcelPool.getInstance().putBasicLinkKey(f.getType().getObjectTypeClass());
		// this.mainKey = f;
		// f.indexList.add(new FieldIndexTool(IndexTypeEnum.Primary));
		// }
		// if (null != isKey) {
		// ToolBeanExcelPool.getInstance().putBasicLinkKey(f.getType().getObjectTypeClass());
		// this.mainKey.putField(isKey, f);
		// }
		// 自增判定
		if (isIncrementKey && (null == this.incrementKey)) {
			this.incrementKey = f;
		}
		if (isUuid) {
			this.hasUuid = true;
		}
		// // 唯一判定
		// if (isUnique) {
		// this.putIndexData(IndexTypeEnum.Unique, datafieldName, 1, f, 0);
		// }
		// // 开始进行索引判定
		// this.putIndex(index, f);
		return f;
	}

	/**
	 * 得到对应的数据库名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:03:50 PM
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return this.databaseName;
	}

	/**
	 * 得到对应的数据表名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:03:50 PM
	 * @return the datatableName
	 */
	public String getDatatableName() {
		return this.dataTableName;
	}

	/**
	 * 得到表中主键，针对数据更新用
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 1:40:01 PM
	 * @return the mainKey
	 */
	public PrimaryTool getMainKey() {
		return this.mainKey;
	}

	/**
	 * 得到表中自增键，针对数据库创建语句
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 1:46:59 PM
	 * @return the incrementKey
	 */
	public DataFieldTool getIncrementKey() {
		return this.incrementKey;
	}

	/**
	 * 是否存在UUID
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 5:57:30 PM
	 * @return the hasUuid
	 */
	public boolean isHasUuid() {
		return this.hasUuid;
	}

	/**
	 * 是否存在日期属性字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 1:45:10 PM
	 * @return the hasData
	 */
	public boolean isHasDate() {
		return this.hasDate;
	}

	/**
	 * 是否存在Map类型字段
	 * 
	 * @author XuWeijie
	 * @datetime 2015年9月17日_下午2:43:40
	 * @return the hasMap
	 */
	public boolean isHasMap() {
		return this.hasMap;
	}

	/**
	 * 是否存在List类型字段
	 * 
	 * @author XuWeijie
	 * @datetime 2015年9月17日_下午2:43:40
	 * @return the hasList
	 */
	public boolean isHasList() {
		return this.hasList;
	}

	/**
	 * 是否存在输出字节，针对Blob的字段配置
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 2:02:52 PM
	 * @return the hasInput
	 */
	public boolean isHasInput() {
		return this.hasInput;
	}

	/**
	 * 是否存在非String属性
	 * 
	 * @author tfzzh
	 * @dateTime 2020年8月17日 上午12:25:56
	 * @return the hasNonString
	 */
	public boolean isHasNonString() {
		return this.hasNonString;
	}

	/**
	 * 处理父类
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月20日_下午6:05:05
	 */
	public void handleParent() {
		if (null != this.parentName) {
			// 得到目标名字父类对象
			ParentDataTool pdt = ToolBeanExcelPool.getInstance().getParentDataBean(this.parentName);
			if (null == pdt) {
				pdt = new ParentDataTool(this.parentName, this.getDesc(), this.srcPath, this.functionName, this);
				ToolBeanExcelPool.getInstance().putParentDataBean(this.parentName, pdt);
			} else {
				pdt.filterDataBean(this);
			}
		}
	}

	/**
	 * 得到所相关父类数据
	 *
	 * @author Xu Weijie
	 * @datetime 2017年9月20日_下午7:33:14
	 * @return the parentData
	 */
	public ParentDataTool getParentData() {
		return this.parentData;
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
		return ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX;
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
		return ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX;
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
		return ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SPECIAL;
	}

	/**
	 * 得到源码包路径
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 9:17:11 PM
	 * @return the srcPath
	 */
	public String getSrcPath() {
		return this.srcPath;
	}

	/**
	 * 得到功能名（包路径，“.”为名称间间隔）
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 9:17:11 PM
	 * @return the functionName
	 */
	public String getFunctionName() {
		return this.functionName;
	}

	/**
	 * 得到功能路径（路径类，“/”为名称间间隔）
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 9:41:49 AM
	 * @return the functionPath
	 */
	public String getFunctionPath() {
		return this.functionName.replaceAll("[.]", "/");
	}

	/**
	 * 放入一个属性信息
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 1:46:49 PM
	 * @param indexContent 索引内容
	 * @param datafield 数据字段
	 */
	private void putIndex(final String indexContent, final DataFieldTool datafield) {
		if (null == indexContent) {
			return;
		}
		String[] ds;
		for (final String ls : StringTools.split(indexContent, "|")) {
			if (ls.length() > 0) {
				// 进行字段消息拆分
				ds = StringTools.split(ls, ",");
				if (ds.length >= 4) {
					this.putIndexData(IndexTypeEnum.getType(ds[1]), ds[0], Integer.parseInt(ds[2]), datafield, Integer.parseInt(ds[3]) - 1);
				}
			}
		}
	}

	/**
	 * 放入一个具体的索引数据
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 12:56:54 PM
	 * @param type 索引类型
	 * @param key 索引key
	 * @param len 索引的字段数量
	 * @param datafield 相关的数据字段
	 * @param ind 所在索引的位置
	 */
	private void putIndexData(final IndexTypeEnum type, final String key, final int len, final DataFieldTool datafield, final int ind) {
		Map<String, DataFieldTool[]> map = this.indexMap.get(type);
		if (null == map) {
			// 都是直接创建
			map = type.getRecodeMap();
			this.indexMap.put(type, map);
			final DataFieldTool[] datas = new DataFieldTool[len];
			map.put(key, datas);
			datas[ind] = datafield;
		} else {
			DataFieldTool[] str = map.get(key);
			if (null == str) {
				str = new DataFieldTool[len];
				map.put(key, str);
			}
			str[ind] = datafield;
		}
		datafield.indexList.add(new FieldIndexTool(type, key, len, ind + 1));
	}

	/**
	 * 放入索引数据
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午3:04:38
	 * @param type 索引类型
	 * @param ind 索引位
	 * @param fields 所相关字段列表<字段名,排序方式:asc,desc>
	 */
	public void putIndexData(final IndexTypeEnum type, final Integer ind, final Map<String, String> fields) {
		// 数据表对象中的索引相关
		// this.indexMap;
		// 字段中相关的索引
		DataFieldTool df;
		final int c = fields.size();
		int i = 0;
		for (final Entry<String, String> ent : fields.entrySet()) {
			// 得到相关的字段对象
			df = this.fieldMap.get(ent.getKey());
			// 认为一定是存在的
			this.putIndexData(type, ind.toString(), c, df, i++);
		}
	}

	/**
	 * 放入主键索引
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午3:51:19
	 * @param fields 字段列表
	 */
	public void putKeyIndex(final Iterable<String> fields) {
		int i = 1;
		DataFieldTool df;
		for (final String f : fields) {
			df = this.fieldMap.get(f);
			ToolBeanExcelPool.getInstance().putBasicLinkKey(df.getType().getObjectTypeClass());
			this.mainKey.putField(i++, df);
			df.changeMainKey();
		}
	}

	// /**
	// * 放入主键索引
	// *
	// * @author Xu Weijie
	// * @datetime 2018年1月23日_上午10:53:36
	// * @param fields 字段列表
	// */
	// public void putKeyIndex(final List<String> fields) {
	// int i = 1;
	// DataFieldTool df;
	// for (String f : fields) {
	// df = this.fieldMap.get(f);
	// ToolBeanExcelPool.getInstance().putBasicLinkKey(df.getType().getObjectTypeClass());
	// this.mainKey.putField(i++, df);
	// }
	// }
	/**
	 * 得到所有字段信息
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 10:52:10 AM
	 * @return 所有字段信息
	 */
	public Collection<DataFieldTool> getFields() {
		return this.fieldMap.values();
	}

	/**
	 * 是否存在目标名称字段
	 * 
	 * @author XuWeijie
	 * @datetime 2015年11月12日_下午1:00:27
	 * @param txt 字段名
	 * @return true，是存在的
	 */
	public boolean existsField(final String txt) {
		return this.fieldMap.containsKey(txt);
	}

	/**
	 * 得到所拥有的字段的数量
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 27, 2014 6:49:56 PM
	 * @return 所拥有的字段的数量
	 */
	public int getFieldSize() {
		return this.fieldMap.size();
	}

	/**
	 * 得到普通索引列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 2:24:11 PM
	 * @return 普通索引列表
	 */
	public Collection<Entry<String, DataFieldTool[]>> getIndexs() {
		final Map<String, DataFieldTool[]> map = this.indexMap.get(IndexTypeEnum.Normal);
		return null == map ? new HashMap<String, DataFieldTool[]>(1).entrySet() : map.entrySet();
	}

	/**
	 * 得到唯一索引列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 2:24:12 PM
	 * @return 唯一索引列表
	 */
	public Collection<Entry<String, DataFieldTool[]>> getUniques() {
		final Map<String, DataFieldTool[]> map = this.indexMap.get(IndexTypeEnum.Unique);
		return null == map ? new HashMap<String, DataFieldTool[]>(1).entrySet() : map.entrySet();
	}

	/**
	 * 得到全文索引列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 2:24:14 PM
	 * @return 全文索引列表
	 */
	public Collection<Entry<String, DataFieldTool[]>> getFullTexts() {
		final Map<String, DataFieldTool[]> map = this.indexMap.get(IndexTypeEnum.FullText);
		return null == map ? new HashMap<String, DataFieldTool[]>(1).entrySet() : map.entrySet();
	}

	/**
	 * 首先整理
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月9日_下午2:48:21
	 */
	public void tidyFirst() {
	}

	/**
	 * 整理索引数据，主要作用修改索引名，改数字为索引必要的内容<br />
	 * 应该是在从表中完全读取完数据后再调用该方法<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 2:00:57 PM
	 */
	public void tidyIndex() {
		if (this.isTidy) {
			return;
		}
		this.isTidy = true;
		IndexTypeEnum type;
		Map<String, DataFieldTool[]> newMap;
		StringBuilder keyName;
		int len = 0;
		boolean isFirst;
		this.mainKey.init();
		final Set<DataFieldTool> ft = new HashSet<>(this.fieldMap.values());
		// 一般索引列表
		Map<String, DataFieldTool[]> normalMap = new LinkedHashMap<>();
		for (final Entry<IndexTypeEnum, Map<String, DataFieldTool[]>> e : this.indexMap.entrySet()) {
			type = e.getKey();
			if (type == IndexTypeEnum.Normal) {
				newMap = normalMap;
			} else {
				newMap = type.getResultMap();
			}
			for (final DataFieldTool[] ifs : e.getValue().values()) {
				keyName = new StringBuilder();
				isFirst = true;
				int cou = 0;
				for (final DataFieldTool s : ifs) {
					cou++;
					if (isFirst) {
						isFirst = false;
					} else {
						keyName.append('_');
					}
					if (ft.remove(s)) {
						// 从新计算长度
						len = type.getIndexByteTotal(len, s.getByteLength());
					}
					keyName.append(s.getDatafieldName());
					// 调整位置 xwj 2020-08-18
					if (cou == ifs.length) {
						newMap.put(keyName.toString(), ifs);
					} else {
						if (type == IndexTypeEnum.FullText) { // 针对非全文索引
							continue;
						}
						// 认为是一般索引
						DataFieldTool[] tifs = new DataFieldTool[cou];
						System.arraycopy(ifs, 0, tifs, 0, cou);
						normalMap.put(keyName.toString(), tifs);
					}
				}
			}
			if (type != IndexTypeEnum.Normal) {
				this.indexMap.put(type, newMap);
			}
		}
		if (normalMap.size() > 0) {
			this.indexMap.put(IndexTypeEnum.Normal, normalMap);
		}
		if (len > DataConfigConstants.INDEX_MAX_LENGTH) {
			// 如果超长，抛出异常
			throw new ConfigurationException("In Data[" + super.getId() + "] has Error With Index Length:" + len + ">750.");
		}
	}

	/**
	 * 增加一个单一数据结果的查询条件列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 5:16:42 PM
	 * @param dataConditions 单一数据结果的查询条件列表
	 */
	public void addSingleDataCondition(final List<DataConditionBean> dataConditions) {
		this.singleDataConditionList.add(dataConditions);
	}

	/**
	 * 得到单一结果查询相关的数据条件列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 5:16:02 PM
	 * @return the singleDataConditionList
	 */
	public List<List<DataConditionBean>> getSingleDataConditionList() {
		return this.singleDataConditionList;
	}

	/**
	 * 得到表数据字段名称对应，生成表数据时用
	 * 
	 * @author XuWeijie
	 * @datetime 2015年11月12日_下午1:05:25
	 * @return the fieldOrder
	 */
	public List<DataFieldTool> getFieldOrder() {
		return this.fieldOrder;
	}

	// /**
	// * 得到数据表Key所在索引位
	// *
	// * @author XuWeijie
	// * @datetime 2015年11月13日_下午3:22:44
	// * @return 数据表Key所在索引位
	// */
	// public int getDataKeyInd() {
	// return this.dataKeyInd;
	// }
	/**
	 * 设置表数据字段名称对应，生成表数据时用
	 * 
	 * @author XuWeijie
	 * @datetime 2015年11月12日_下午1:05:25
	 * @param fieldOrder the fieldOrder to set
	 */
	public void setFieldOrder(final List<String> fieldOrder) {
		final List<DataFieldTool> tmp = new ArrayList<>();
		DataFieldTool df;
		String f;
		for (int i = fieldOrder.size() - 1; i >= 0; i--) {
			f = fieldOrder.get(i);
			df = this.fieldMap.get(f);
			if (null == df) {
				throw new ConfigurationException("Not Exists DataFieldName[" + f + "] in Data:" + super.getId());
			}
			if (!"String".equals(df.datafieldType.getClassFieldType().getObjectTypeName())) {
				if (!this.hasNonString) {
					this.hasNonString = true;
				}
			}
			// if (df.isMainKey()) {
			// this.dataKeyInd = i;
			// }
			df.setDataOrderIndex(i);
			tmp.add(0, df);
		}
		// if (this.dataKeyInd == -1) {
		// // 是一个没有主键
		// throw new ConfigurationException("No Main Key int table[" + super.getId() + "] Data ... ");
		// }
		this.fieldOrder = tmp;
	}

	/**
	 * 增加数据信息
	 * 
	 * @author XuWeijie
	 * @datetime 2015年11月12日_下午12:54:32
	 * @param lineData 数据行消息
	 */
	public void addDataInfo(final List<String> lineData) {
		final List<String> classData = new ArrayList<>();
		for (int i = 0, n = lineData.size(); i < n; i++) {
			if (this.fieldOrder.get(i).isKey) {
				if (lineData.get(i).length() == 0) {
					return;
				}
			}
			lineData.set(i, lineData.get(i).replaceAll("\"", "\\\\\""));
			classData.add(this.fieldOrder.get(i).getType().stringToTypeShow(lineData.get(i)));
		}
		this.dataList.add(classData);
		this.jsonDataList.add(lineData);
	}

	/**
	 * 得到表数据信息列表
	 * 
	 * @author XuWeijie
	 * @datetime 2015年11月12日_下午12:54:32
	 * @return 表数据信息列表
	 */
	public List<List<String>> getDataList() {
		return this.dataList;
	}

	/**
	 * 得到用于记录为JSON的数据
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月14日_下午4:11:07
	 * @return 用于记录为JSON的数据
	 */
	public List<List<String>> getJsonDataList() {
		return this.jsonDataList;
	}

	/**
	 * 数据父类工具
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月20日_下午4:40:33
	 */
	public class ParentDataTool extends TemplateObjectTool {

		/**
		 * 说明
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月21日_上午9:57:37
		 */
		private final StringBuilder desc = new StringBuilder();

		/**
		 * 所相关子类
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午4:44:45
		 */
		private final Map<Long, DataBeanTool> children = new HashMap<>();

		/**
		 * 所相关主键
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午5:20:50
		 */
		private PrimaryTool mainKey;

		/**
		 * 源码包路径
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午4:54:23
		 */
		private final String srcPath;

		/**
		 * 功能名
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午4:54:25
		 */
		private final String functionName;

		/**
		 * 字段列表
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午4:54:26
		 */
		private Map<String, DataFieldTool> fieldMap;

		/**
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午4:40:36
		 * @param name 数据父类工具名
		 * @param desc 数据对象说明
		 * @param srcPath 源码包路径
		 * @param functionName 功能名
		 * @param child 所相关子类信息
		 */
		protected ParentDataTool(final String name, final String desc, final String srcPath, final String functionName, final DataBeanTool child) {
			super(0l, name, "", "", "", "", "");
			this.desc.append(desc);
			this.children.put(child.getId(), child);
			this.srcPath = srcPath;
			this.functionName = functionName;
			this.mainKey = child.mainKey;
			this.fieldMap = new LinkedHashMap<>(child.fieldMap);
		}

		/**
		 * 验证类对象相关<br />
		 * 该方法一定在目标类对象，操作完所有的字段及索引后，才可以进行<br />
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午5:22:06
		 * @param db 目标类对象
		 */
		protected void filterDataBean(final DataBeanTool db) {
			// 首先验证是否相同主键
			this.desc.append("；").append(db.getDesc());
			this.filterFields(db.fieldMap);
			this.filterMainKey(db.mainKey);
		}

		/**
		 * 验证字段列表内容
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午5:10:09
		 * @param fieldMap 比较用字段列表
		 */
		private void filterFields(final Map<String, DataFieldTool> fieldMap) {
			final Map<String, DataFieldTool> tmpFieldMap = new HashMap<>(fieldMap);
			final Map<String, DataFieldTool> newFieldMap = new LinkedHashMap<>(this.fieldMap.size());
			DataFieldTool pf, cf;
			for (final Entry<String, DataFieldTool> ent : this.fieldMap.entrySet()) {
				pf = ent.getValue();
				cf = tmpFieldMap.remove(ent.getKey());
				if (null != cf) {
					// 验证两个存在的字段的相同性
					// 数据在数据库中名
					if (!pf.getDatafieldName().equals(cf.getDatafieldName())) {
						continue;
					}
					// 数据在数据库中类型
					if (pf.getDatafieldType() != cf.getDatafieldType()) {
						continue;
					}
					// 数据在数据库中长度限制
					if (!pf.getLength().equals(cf.getLength())) {
						continue;
					}
					newFieldMap.put(ent.getKey(), pf);
				}
			}
			this.fieldMap = newFieldMap;
		}

		/**
		 * 验证主键
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午5:23:34
		 * @param mk 目标主键
		 */
		private void filterMainKey(final PrimaryTool mk) {
			if (null == this.mainKey) {
				return;
			}
			if (null == mk) {
				this.mainKey = null;
				return;
			}
			if (!this.mainKey.datafieldName.equals(mk.datafieldName)) {
				this.mainKey = null;
				return;
			}
			if (this.fieldMap.size() == 0) {
				this.mainKey = null;
				return;
			}
			// 验证当前字段列表中，是否还存在所有主键相关字段
			for (final DataFieldTool df : this.mainKey.fs) {
				// 有一个不存在，则认为不符合索引需求
				if (null == this.fieldMap.get(df.getName())) {
					this.mainKey = null;
					return;
				}
			}
		}

		/**
		 * 得到对象/属性说明
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月21日_上午9:57:22
		 * @return 对象说明
		 * @see com.tfzzh.tools.data.bean.TemplateObjectTool#getDesc()
		 */
		@Override
		public String getDesc() {
			return this.desc.toString();
		}

		/**
		 * 得到源码包路径
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午5:56:19
		 * @return the srcPath
		 */
		public String getSrcPath() {
			return this.srcPath;
		}

		/**
		 * 得到功能名（包路径，“.”为名称间间隔）
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午5:56:19
		 * @return the functionName
		 */
		public String getFunctionName() {
			return this.functionName;
		}

		/**
		 * 得到功能路径（路径类，“/”为名称间间隔）
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午5:56:19
		 * @return the functionPath
		 */
		public String getFunctionPath() {
			return this.functionName.replaceAll("[.]", "/");
		}

		/**
		 * 得到所相关主键
		 *
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午5:38:12
		 * @return the mainKey
		 */
		public PrimaryTool getMainKey() {
			return this.mainKey;
		}

		/**
		 * 得到字段列表
		 *
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午5:38:12
		 * @return the fieldMap
		 */
		public Collection<DataFieldTool> getFields() {
			return this.fieldMap.values();
		}

		/**
		 * 得到对象后缀名
		 * 
		 * @author Xu Weijie
		 * @datetime 2017年9月20日_下午8:21:27
		 * @return 对象后缀名
		 */
		public String getSuffix() {
			return DataBeanTool.this.getSuffix();
		}
	}

	/**
	 * 数据属性工具
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:07:27 PM
	 */
	public class DataFieldTool extends TemplateObjectTool {

		/**
		 * 数据中字段名
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 12, 2014 7:10:34 PM
		 */
		private final String datafieldName;

		/**
		 * 数据字段的类型
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 12, 2014 7:36:21 PM
		 */
		private final DatabaseFieldTypeEnum datafieldType;

		/**
		 * 所相关的数据对象ID
		 * 
		 * @author tfzzh
		 * @dateTime 2016年12月30日 下午4:23:18
		 */
		private final Long relatedDataId;

		/**
		 * 相关的数据对象<br />
		 * 在处理树状结构图时被加入<br />
		 * 
		 * @author tfzzh
		 * @dateTime 2016年12月30日 下午4:06:37
		 */
		private DataBeanTool relatedData;

		/**
		 * 字段的长度
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 10:17:18 AM
		 */
		private final String length;

		/**
		 * 字段的说明
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 12, 2014 8:07:41 PM
		 */
		private final String[] desc;

		/**
		 * 所相关到的索引列表
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午3:51:22
		 */
		private final List<FieldIndexTool> indexList = new LinkedList<>();

		/**
		 * 是否主键
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月8日_下午6:30:31
		 */
		private boolean isKey;

		/**
		 * 是否为UUID的字段
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 9:53:05 AM
		 */
		private final boolean isUuid;

		/**
		 * 是否自增字段
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 4:39:42 PM
		 */
		private final boolean isIncrement;

		/**
		 * 是否非负
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 4:39:44 PM
		 */
		private final boolean isUnsigned;

		/**
		 * 是否可null
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 4:39:45 PM
		 */
		private final boolean canNull;

		/**
		 * 默认值
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 4:39:46 PM
		 */
		private final String def;

		/**
		 * 数据内容排序索引
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午2:46:22
		 */
		private int dataOrderIndex = -1;

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 12, 2014 8:08:02 PM
		 * @param id 属性的ID
		 * @param name 属性名
		 * @param datafieldName 对应常量用名
		 * @param fieldType 数据字段名
		 * @param length 字段的长度内容
		 * @param desc 字段的说明
		 * @param isKey 是否主键
		 * @param isUuid 是否UUID
		 * @param isIncrement 是否自增
		 * @param isUnsigned 是否非负
		 * @param canNull 是否可空
		 * @param def 默认值
		 * @param createAuthor 创建人
		 * @param createDate 创建时间
		 * @param lastChangeAuthor 最后修改人
		 * @param lastChangeDate 最后修改时间
		 */
		public DataFieldTool(final Long id, final String name, final String datafieldName, final String fieldType, final String length, final String desc, final boolean isKey, final boolean isUuid, final boolean isIncrement, final boolean isUnsigned,
				final boolean canNull, final String def, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
			super(id, name, desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
			this.datafieldName = datafieldName;
			final List<String> fta = StringTools.splitToArray(fieldType, "|");
			final String datafieldType = fta.get(0);
			if (fta.size() > 1) {
				this.relatedDataId = Long.valueOf(fta.get(1));
			} else {
				this.relatedDataId = null;
			}
			this.datafieldType = DatabaseFieldTypeEnum.getType(datafieldType);
			if (this.datafieldType == DatabaseFieldTypeEnum.dfDatetime) {
				DataBeanTool.this.hasDate = true;
			} else if (this.datafieldType.getClassFieldType() == FieldTypeEnum.IfInputStream) {
				DataBeanTool.this.hasInput = true;
			}
			final String typeName = this.datafieldType.getClassFieldType().getTypeName().toLowerCase();
			if (typeName.indexOf("map") != -1) {
				// 存在Map类型字段
				DataBeanTool.this.hasMap = true;
			}
			if (typeName.indexOf("list") != -1) {
				// 存在List类型字段
				DataBeanTool.this.hasList = true;
			} else if (typeName.indexOf("arrays") != -1) {
				// 存在List类型字段
				DataBeanTool.this.hasList = true;
			}
			this.length = length;
			this.desc = super.getDesc().trim().split("[：；]");
			this.isKey = isKey;
			this.isUuid = isUuid;
			this.isIncrement = isIncrement;
			this.isUnsigned = isUnsigned;
			this.canNull = canNull;
			this.def = this.datafieldType.getDefValue(def);
		}

		/**
		 * 得到数据中字段名
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 12, 2014 8:09:51 PM
		 * @return the datafieldName
		 */
		public String getDatafieldName() {
			return this.datafieldName;
		}

		/**
		 * 得到数据字段的类型
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 12, 2014 8:09:51 PM
		 * @return the datafieldType
		 */
		public DatabaseFieldTypeEnum getDatafieldType() {
			return this.datafieldType;
		}

		/**
		 * 得到数据字段类型内容
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 14, 2014 10:55:35 AM
		 * @return 数据字段类型内容
		 */
		public String getDatafieldTypeContent() {
			return this.datafieldType.getDataFieldContent(this.length);
		}

		/**
		 * 得到java类字段相关的类型
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 6:54:15 PM
		 * @return 类字段相关的类型
		 */
		public FieldTypeEnum getType() {
			return this.datafieldType.getClassFieldType();
		}

		/**
		 * 得到显示用类型
		 * 
		 * @author tfzzh
		 * @dateTime 2016年12月30日 下午3:44:21
		 * @return 显示用类型
		 */
		public String getShowType() {
			if (null != this.relatedDataId) {
				if (null == this.relatedData) {
					// 需要进行设置
					this.relatedData = ToolBeanExcelPool.getInstance().getDataTool(this.relatedDataId);
				}
				return this.datafieldType.getClassFieldType().getObjectTypeName(this.relatedData.getProName(), null);
			} else {
				return this.datafieldType.getClassFieldType().getObjectTypeName();
			}
		}

		/**
		 * 是否存在关系对象
		 * 
		 * @author Weijie Xu
		 * @dateTime 2017年5月16日 下午7:00:33
		 * @return true，存在关系对象
		 */
		public boolean hasRelative() {
			return null != this.relatedDataId;
		}

		/**
		 * 得到所相关关系对象
		 * 
		 * @author Weijie Xu
		 * @dateTime 2017年5月17日 下午6:14:32
		 * @return 所相关关系对象
		 */
		public DataBeanTool getRelative() {
			if (null != this.relatedDataId) {
				if (null == this.relatedData) {
					// 需要进行设置
					this.relatedData = ToolBeanExcelPool.getInstance().getDataTool(this.relatedDataId);
				}
				return this.relatedData;
			} else {
				return null;
			}
		}

		/**
		 * 得到基础类型名称
		 * 
		 * @author tfzzh
		 * @dateTime 2016年12月31日 下午8:14:56
		 * @return 基础类型名称
		 */
		public String getBaseType() {
			if (null != this.relatedDataId) {
				if (null == this.relatedData) {
					// 需要进行设置
					this.relatedData = ToolBeanExcelPool.getInstance().getDataTool(this.relatedDataId);
				}
				return this.datafieldType.getClassFieldType().getBaseName(this.relatedData.getProName());
			} else {
				return this.datafieldType.getClassFieldType().getTypeName();
			}
		}

		/**
		 * 得到字段的长度
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 2:31:02 PM
		 * @return the length
		 */
		public String getLength() {
			if (null == this.length) {
				return "";
			} else {
				return this.length;
			}
		}

		/**
		 * 得到长度字段的数值内容
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 2:35:31 PM
		 * @return 字段相关的字节长度
		 */
		public int getByteLength() {
			return this.datafieldType.getLengthNum(this.length);
		}

		/**
		 * 得到字段的说明
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 12, 2014 8:09:51 PM
		 * @return the desc
		 */
		public String[] getDescs() {
			return this.desc;
		}

		/**
		 * 得到字段的说明，数据库相关用
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 14, 2014 4:20:19 PM
		 * @return 数据库相关字段说明
		 */
		public String getDescData() {
			final StringBuilder sb = new StringBuilder();
			int i = 0;
			for (final String str : this.desc) {
				sb.append(str);
				if (i++ == 0) {
					if (this.desc.length != 1) {
						sb.append("：");
					}
				} else {
					if (this.desc.length != i) {
						sb.append("；");
					} else {
						sb.append("。");
					}
				}
			}
			return sb.toString();
		}

		/**
		 * 得到所相关到的索引列表
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午4:00:38
		 * @return the indexList
		 */
		public List<FieldIndexTool> getIndexList() {
			return this.indexList;
		}

		/**
		 * 是否为UUID字段
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 9:56:59 AM
		 * @return the isUuid
		 */
		public boolean isUuid() {
			return this.isUuid;
		}

		/**
		 * 变更为主键
		 * 
		 * @author Xu Weijie
		 * @datetime 2018年1月22日_下午3:02:19
		 */
		protected void changeMainKey() {
			this.isKey = true;
		}

		/**
		 * 是否是主键
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午2:30:32
		 * @return true，是主键
		 */
		public boolean isMainKey() {
			return this.isKey;
		}

		/**
		 * 是否自增字段
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 4:43:02 PM
		 * @return the isIncrement
		 */
		public boolean isIncrementKey() {
			return this.isIncrement;
		}

		/**
		 * 是否非负
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 4:43:02 PM
		 * @return the isUnsigned
		 */
		public boolean isUnsigned() {
			return this.isUnsigned;
		}

		/**
		 * 是否可Null
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 4:43:02 PM
		 * @return the canNull
		 */
		public boolean isCanNull() {
			return this.canNull;
		}

		/**
		 * 是否可变，针对UI显示部分，是否为可变数据表字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2015年3月24日 下午5:43:36
		 * @return true，可变；<br />
		 *         false，不可变；<br />
		 */
		public boolean isCanChange() {
			// 是否为非Bean类型
			if (this.isUuid) {
				return false;
			}
			if (this.isIncrement) {
				return false;
			}
			return true;
		}

		/**
		 * 是否存在默认值
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 14, 2014 1:19:55 PM
		 * @return true，存在默认值；<br />
		 *         false，不存在默认值；<br />
		 */
		public boolean hasDef() {
			if ((null == this.def) || (this.def.length() == 0)) {
				return false;
			} else {
				return true;
			}
		}

		/**
		 * 得到默认值
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 13, 2014 4:43:02 PM
		 * @return the def
		 */
		public String getDefault() {
			return this.def;
		}

		/**
		 * 得到数据内容排序索引
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午2:46:54
		 * @return the dataOrderIndex
		 */
		public int getDataOrderIndex() {
			return this.dataOrderIndex;
		}

		/**
		 * 设置数据内容排序索引
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午2:46:54
		 * @param dataOrderIndex the dataOrderIndex to set
		 */
		private void setDataOrderIndex(final int dataOrderIndex) {
			this.dataOrderIndex = dataOrderIndex;
		}

		/**
		 * 得到所从属数据实体
		 * 
		 * @author Weijie Xu
		 * @dateTime Aug 15, 2014 7:50:01 PM
		 * @return 所从属数据实体
		 */
		public DataBeanTool getBelongData() {
			return DataBeanTool.this;
		}
	}

	/**
	 * 字段索引工具
	 * 
	 * @author XuWeijie
	 * @datetime 2015年9月17日_下午3:50:37
	 */
	public class FieldIndexTool {

		/**
		 * 索引类型
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午3:52:40
		 */
		private final IndexTypeEnum type;

		/**
		 * 索引名字
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午3:57:36
		 */
		private final String name;

		/**
		 * 所相关字段总量
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午3:57:40
		 */
		private final int max;

		/**
		 * 所在字段顺位
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午3:57:41
		 */
		private final int index;

		/**
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午4:08:10
		 * @param type 索引类型
		 */
		public FieldIndexTool(final IndexTypeEnum type) {
			this(type, null, 0, 0);
		}

		/**
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午3:59:23
		 * @param type 索引类型
		 * @param name 索引名字
		 * @param max 所相关字段总量
		 * @param index 所在字段顺位
		 */
		public FieldIndexTool(final IndexTypeEnum type, final String name, final int max, final int index) {
			this.type = type;
			this.name = name;
			this.max = max;
			this.index = index;
		}

		/**
		 * 得到索引类型
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午3:59:48
		 * @return the type
		 */
		public IndexTypeEnum getType() {
			return this.type;
		}

		/**
		 * 得到索引名字
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午3:59:48
		 * @return the name
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * 得到所相关字段总量
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午3:59:48
		 * @return the max
		 */
		public int getMax() {
			return this.max;
		}

		/**
		 * 得到所在字段顺位
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月17日_下午3:59:48
		 * @return the index
		 */
		public int getIndex() {
			return this.index;
		}
	}

	/**
	 * 主键工具
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月8日_下午8:43:49
	 */
	public class PrimaryTool {

		/**
		 * 字段列表
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月8日_下午8:47:44
		 */
		private final Map<Integer, DataFieldTool> datas = new TreeMap<>();

		/**
		 * 字段列表
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午1:30:24
		 */
		private final List<DataFieldTool> fs = new ArrayList<>(2);

		/**
		 * 拥有的总量
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月8日_下午8:47:43
		 */
		private int count;

		/**
		 * 数据的对象类型
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午1:48:22
		 */
		private String dataObjectType = "";

		/**
		 * 字段名，用来在某些特殊地方使用
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午1:25:06
		 */
		private String datafieldName = "";

		/**
		 * @author XuWeijie
		 * @datetime 2015年12月8日_下午8:55:23
		 */
		private PrimaryTool() {
		}

		/**
		 * 放入一个字段信息
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月8日_下午8:55:21
		 * @param index 所在位置
		 * @param field 字段信息
		 */
		private void putField(final Integer index, final DataFieldTool field) {
			this.count++;
			if (null != this.datas.put(index, field)) {
				throw new ConfigurationException("Exists DataField Primary key Index[" + DataBeanTool.this.dataTableName + "] in Data:" + field.datafieldName);
			}
		}

		/**
		 * 进行初始化操作
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午1:17:28
		 */
		private void init() {
			final StringBuilder fn = new StringBuilder(this.datas.size() * 16);
			boolean isFirst = true;
			int ind = 1;
			for (final DataFieldTool dft : this.datas.values()) {
				if (isFirst) {
					isFirst = false;
				} else {
					fn.append('_');
				}
				fn.append(dft.getDatafieldName());
				dft.indexList.add(new FieldIndexTool(IndexTypeEnum.Primary, "primary", this.count, ind++));
				this.fs.add(dft);
			}
			if (this.fs.size() == 1) {
				this.dataObjectType = this.fs.get(0).getType().getObjectTypeName();
			} else {
				// 默认的通用类型集合
				this.dataObjectType = "Map<String, Object>";
			}
			this.datafieldName = fn.toString();
		}

		/**
		 * 得到所相关字段列表
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午1:31:10
		 * @return the fs
		 */
		public List<DataFieldTool> getDataFields() {
			return this.fs;
		}

		/**
		 * 是否仅有一个字段
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午2:02:14
		 * @return true，仅有一个字段；
		 */
		public boolean isOnlyField() {
			return this.fs.size() == 1;
		}

		/**
		 * 得到字段类型，默认为varchar
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午1:40:50
		 * @return the datafieldType
		 */
		public String getTypeName() {
			return this.dataObjectType;
		}

		/**
		 * 得到字段字符串集合
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月8日_下午8:53:33
		 * @return 字段名（集合）
		 */
		public String getDatafieldName() {
			return this.datafieldName;
		}

		/**
		 * 在只有一个内容时，得到字段名
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午2:01:05
		 * @return 字段名
		 */
		public String getName() {
			return this.fs.get(0).getName();
		}

		/**
		 * 在只有一个内容时，得到说明
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午2:01:05
		 * @return 说明
		 */
		public String getDesc() {
			return this.fs.get(0).getDesc();
		}

		/**
		 * 得到对应常量用名
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午2:19:29
		 * @return 对应常量用名
		 */
		public String getConstantName() {
			return this.fs.get(0).getConstantName();
		}

		/**
		 * 得到数据内容排序索引
		 * 
		 * @author XuWeijie
		 * @datetime 2015年12月9日_下午3:00:54
		 * @return 数据内容排序索引
		 */
		public int getDataOrderIndex() {
			return this.fs.get(0).getDataOrderIndex();
		}
	}

	/**
	 * 是否源数据
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 7:39:55 PM
	 * @see com.tfzzh.tools.data.bean.DataBean#isSourceData()
	 */
	@Override
	public boolean isSourceData() {
		return true;
	}
}

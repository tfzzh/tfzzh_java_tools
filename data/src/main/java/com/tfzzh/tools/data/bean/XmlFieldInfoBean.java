/**
 * @author Xu Weijie
 * @datetime 2018年1月18日_上午11:55:09
 */
package com.tfzzh.tools.data.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * XML配置相关字段信息对象
 * 
 * @author Xu Weijie
 * @datetime 2018年1月18日_上午11:55:09
 */
public class XmlFieldInfoBean {

	/**
	 * 所属表信息
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:47:43
	 */
	@JSONField(serialize = false)
	private final XmlTableInfoBean parent;

	/**
	 * 字段ID
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:47:14
	 */
	private long id;

	/**
	 * 数据库中用名
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:47:15
	 */
	private String name;

	/**
	 * 字段类型名
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:47:17
	 */
	private String type;

	/**
	 * 字段长度
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:47:18
	 */
	private String length;

	/**
	 * 是否自增
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午12:01:37
	 */
	private boolean increment;

	/**
	 * 是否UUID，默认为不是UUID，单表正常最多仅有一个UUID
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午3:27:51
	 */
	private boolean uuid;

	/**
	 * 是否非负
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午5:48:41
	 */
	private boolean nonnegative;

	/**
	 * 是否可空
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午5:48:40
	 */
	private boolean nonnull;

	/**
	 * 项目中用名
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:47:20
	 */
	private String proName;

	/**
	 * 简介
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:47:21
	 */
	private String desc;

	/**
	 * 默认值
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午5:47:25
	 */
	private String defValue;

	/**
	 * 创建时间
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:47:22
	 */
	private String createTime;

	/**
	 * 创建用户
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:47:23
	 */
	private String createUser;

	/**
	 * 变更时间集合
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午4:25:38
	 */
	private String chagneTime;

	/**
	 * 变更用户集合
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午4:25:38
	 */
	private String changeUser;

	/**
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:48:09
	 * @param parent 所属表信息
	 */
	public XmlFieldInfoBean(final XmlTableInfoBean parent) {
		this.parent = parent;
	}

	/**
	 * 得到所属表信息
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @return the table
	 */
	public XmlTableInfoBean getParent() {
		return this.parent;
	}

	/**
	 * 得到字段ID
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * 设置字段ID
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @param id the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * 得到数据库中用名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置数据库中用名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * 得到字段类型名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * 设置字段类型名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * 得到字段长度
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @return the length
	 */
	public String getLength() {
		return this.length;
	}

	/**
	 * 设置字段长度
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @param length the length to set
	 */
	public void setLength(final String length) {
		this.length = length;
	}

// /**
// * 得到是否主键
// *
// * @author Xu Weijie
// * @datetime 2018年1月18日_下午1:49:52
// * @return the key
// */
// public boolean isKey() {
// return this.key;
// }
// /**
// * 设置是否主键
// *
// * @author Xu Weijie
// * @datetime 2018年1月18日_下午1:49:52
// * @param key the key to set
// */
// public void setKey(boolean key) {
// this.key = key;
// }
	/**
	 * 得到是否自增
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @return the increment
	 */
	public boolean isIncrement() {
		return this.increment;
	}

	/**
	 * 设置是否自增
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @param increment the increment to set
	 */
	public void setIncrement(final boolean increment) {
		this.increment = increment;
	}

	/**
	 * 得到是否UUID，默认为不是UUID，单表正常最多仅有一个UUID
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午3:28:17
	 * @return the uuid
	 */
	public boolean isUuid() {
		return this.uuid;
	}

	/**
	 * 设置是否UUID，默认为不是UUID，单表正常最多仅有一个UUID
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午3:28:17
	 * @param uuid the uuid to set
	 */
	public void setUuid(final boolean uuid) {
		this.uuid = uuid;
	}

	/**
	 * 得到是否非负
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午5:49:07
	 * @return the nonnegative
	 */
	public boolean isNonnegative() {
		return this.nonnegative;
	}

	/**
	 * 设置是否非负
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午5:49:07
	 * @param nonnegative the nonnegative to set
	 */
	public void setNonnegative(final boolean nonnegative) {
		this.nonnegative = nonnegative;
	}

	/**
	 * 得到是否可空
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午5:49:07
	 * @return the nonnull
	 */
	public boolean isNonnull() {
		return this.nonnull;
	}

	/**
	 * 设置是否可空
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午5:49:07
	 * @param nonnull the nonnull to set
	 */
	public void setNonnull(final boolean nonnull) {
		this.nonnull = nonnull;
	}

	/**
	 * 得到项目中用名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @return the proName
	 */
	public String getProName() {
		return this.proName;
	}

	/**
	 * 设置项目中用名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @param proName the proName to set
	 */
	public void setProName(final String proName) {
		this.proName = proName;
	}

	/**
	 * 得到简介
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @return the desc
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * 设置简介
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @param desc the desc to set
	 */
	public void setDesc(final String desc) {
		this.desc = desc;
	}

	/**
	 * 得到默认值
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午5:55:11
	 * @return the defValue
	 */
	public String getDefValue() {
		return this.defValue;
	}

	/**
	 * 设置默认值
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午5:55:11
	 * @param defValue the defValue to set
	 */
	public void setDefValue(final String defValue) {
		this.defValue = defValue;
	}

	/**
	 * 得到创建时间
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @return the createTime
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(final String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 得到创建用户
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @return the createUser
	 */
	public String getCreateUser() {
		return this.createUser;
	}

	/**
	 * 设置创建用户
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:49:52
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(final String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 得到变更时间集合
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午4:26:01
	 * @return the chagneTime
	 */
	public String getChagneTime() {
		return this.chagneTime;
	}

	/**
	 * 设置变更时间集合
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午4:26:01
	 * @param chagneTime the chagneTime to set
	 */
	public void setChagneTime(final String chagneTime) {
		this.chagneTime = chagneTime;
	}

	/**
	 * 得到变更用户集合
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午4:26:01
	 * @return the changeUser
	 */
	public String getChangeUser() {
		return this.changeUser;
	}

	/**
	 * 设置变更用户集合
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午4:26:01
	 * @param changeUser the changeUser to set
	 */
	public void setChangeUser(final String changeUser) {
		this.changeUser = changeUser;
	}

	/**
	 * 你懂的
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午2:46:52
	 * @return 你懂的
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

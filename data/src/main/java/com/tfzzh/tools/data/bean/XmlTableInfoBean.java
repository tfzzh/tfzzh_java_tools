/**
 * @author Xu Weijie
 * @datetime 2018年1月18日_上午11:54:41
 */
package com.tfzzh.tools.data.bean;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * XML配置相关表信息对象
 * 
 * @author Xu Weijie
 * @datetime 2018年1月18日_上午11:54:41
 */
public class XmlTableInfoBean {

	/**
	 * 对象ID
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:34:54
	 */
	private long id;

	/**
	 * 数据库表名
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:34:54
	 */
	private String name;

	/**
	 * 所相关模型ID
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:34:52
	 */
	private int modelId;

	/**
	 * 连接名
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午2:07:05
	 */
	private String connName;

	/**
	 * 项目中用名
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:34:51
	 */
	private String proName;

	/**
	 * 所属源码包
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午3:47:31
	 */
	private String src;

	/**
	 * 所相关功能名
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午3:38:03
	 */
	private String funName;

	/**
	 * 简介
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:34:51
	 */
	private String desc;

	/**
	 * 创建时间
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:34:50
	 */
	private String createTime;

	/**
	 * 创建用户
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:34:50
	 */
	private String createUser;

	/**
	 * 变更时间集合
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午11:58:45
	 */
	private String chagneTime;

	/**
	 * 变更用户集合
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午11:58:46
	 */
	private String changeUser;

	/**
	 * 相关属性列表
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:34:49
	 */
	private final List<XmlFieldInfoBean> fields = new ArrayList<>();

	/**
	 * 相关主键
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:30:28
	 */
	private XmlKeyInfoBean key;

	/**
	 * 相关索引列表
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:29:56
	 */
	private final List<XmlIndexInfoBean> indexs = new ArrayList<>();

	/**
	 * 得到对象ID
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * 设置对象ID
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @param id the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * 得到数据库表名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置数据库表名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * 得到所相关模型ID
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @return the modelId
	 */
	public int getModelId() {
		return this.modelId;
	}

	/**
	 * 设置所相关模型ID
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @param modelId the modelId to set
	 */
	public void setModelId(final int modelId) {
		this.modelId = modelId;
	}

	/**
	 * 得到连接名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午2:07:24
	 * @return the connName
	 */
	public String getConnName() {
		return this.connName;
	}

	/**
	 * 设置连接名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午2:07:24
	 * @param connName the connName to set
	 */
	public void setConnName(final String connName) {
		this.connName = connName;
	}

	/**
	 * 得到项目中用名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @return the proName
	 */
	public String getProName() {
		return this.proName;
	}

	/**
	 * 设置项目中用名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @param proName the proName to set
	 */
	public void setProName(final String proName) {
		this.proName = proName;
	}

	/**
	 * 得到所属源码包
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午3:48:00
	 * @return the src
	 */
	public String getSrc() {
		return this.src;
	}

	/**
	 * 设置所属源码包
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午3:48:00
	 * @param src the src to set
	 */
	public void setSrc(final String src) {
		this.src = src;
	}

	/**
	 * 得到所相关功能名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午3:38:28
	 * @return the funName
	 */
	public String getFunName() {
		if (null == this.funName) {
			return this.src;
		} else {
			return this.funName;
		}
	}

	/**
	 * 设置所相关功能名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_下午3:38:28
	 * @param funName the funName to set
	 */
	public void setFunName(final String funName) {
		this.funName = funName;
	}

	/**
	 * 得到简介
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @return the desc
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * 设置简介
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @param desc the desc to set
	 */
	public void setDesc(final String desc) {
		this.desc = desc;
	}

	/**
	 * 得到创建时间
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @return the createTime
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(final String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 得到创建用户
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @return the createUser
	 */
	public String getCreateUser() {
		return this.createUser;
	}

	/**
	 * 设置创建用户
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(final String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 得到变更时间集合
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午11:59:29
	 * @return the chagneTime
	 */
	public String getChagneTime() {
		return this.chagneTime;
	}

	/**
	 * 设置变更时间集合
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午11:59:29
	 * @param chagneTime the chagneTime to set
	 */
	public void setChagneTime(final String chagneTime) {
		this.chagneTime = chagneTime;
	}

	/**
	 * 得到变更用户集合
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午11:59:29
	 * @return the changeUser
	 */
	public String getChangeUser() {
		return this.changeUser;
	}

	/**
	 * 设置变更用户集合
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午11:59:29
	 * @param changeUser the changeUser to set
	 */
	public void setChangeUser(final String changeUser) {
		this.changeUser = changeUser;
	}

	/**
	 * 得到相关属性列表
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @return the fields
	 */
	public List<XmlFieldInfoBean> getFields() {
		return this.fields;
	}

	/**
	 * 增加相关属性
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月18日_下午1:33:59
	 * @param field the fields to set
	 */
	public void addFields(final XmlFieldInfoBean field) {
		this.fields.add(field);
	}

	/**
	 * 得到主键数据
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:30:49
	 * @return the key
	 */
	public XmlKeyInfoBean getKey() {
		return this.key;
	}

	/**
	 * 增加一个主键数据
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:31:23
	 * @param key 主键字段名
	 */
	public void setKey(final XmlKeyInfoBean key) {
		this.key = key;
	}

	/**
	 * 得到相关索引
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:30:49
	 * @return the indexs
	 */
	public List<XmlIndexInfoBean> getIndexs() {
		if (this.indexs.size() == 0) {
			return null;
		} else {
			return this.indexs;
		}
	}

	/**
	 * 增加相关索引
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:31:22
	 * @param indexs 相关索引
	 */
	public void addIndexs(final XmlIndexInfoBean indexs) {
		this.indexs.add(indexs);
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

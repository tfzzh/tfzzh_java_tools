/**
 * @author Xu Weijie
 * @datetime 2018年1月19日_下午3:22:38
 */
package com.tfzzh.tools.data.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ML配置相关索引字段信息对象
 * 
 * @author Xu Weijie
 * @datetime 2018年1月19日_下午3:22:38
 */
public class XmlIndexFieldInfoBean {

	/**
	 * 所属直系父级元素
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:24:00
	 */
	@JSONField(serialize = false)
	private final XmlIndexInfoBean parent;

	/**
	 * 相关字段名
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:23:20
	 */
	private String val;

	/**
	 * 排序方式
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:23:21
	 */
	private String order = "asc";

	/**
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:24:19
	 * @param parent 所属直系父级元素
	 */
	public XmlIndexFieldInfoBean(final XmlIndexInfoBean parent) {
		this.parent = parent;
	}

	/**
	 * 得到所属直系父级元素
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:24:47
	 * @return the parent
	 */
	public XmlIndexInfoBean getParent() {
		return this.parent;
	}

	/**
	 * 得到相关字段名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:24:47
	 * @return the val
	 */
	public String getVal() {
		return this.val;
	}

	/**
	 * 设置相关字段名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:24:47
	 * @param val the val to set
	 */
	public void setVal(final String val) {
		this.val = val;
	}

	/**
	 * 得到排序方式
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:24:47
	 * @return the order
	 */
	public String getOrder() {
		return this.order;
	}

	/**
	 * 设置排序方式
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:24:47
	 * @param order the order to set
	 */
	public void setOrder(final String order) {
		this.order = order;
	}

	/**
	 * 你懂的
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午4:11:30
	 * @return 你懂的
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

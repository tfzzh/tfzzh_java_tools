/**
 * @author Xu Weijie
 * @datetime 2018年1月19日_下午3:34:34
 */
package com.tfzzh.tools.data.bean;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Xu Weijie
 * @datetime 2018年1月19日_下午3:34:34
 */
public class XmlKeyInfoBean {

	/**
	 * 所属直系父级元素
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:35:36
	 */
	@JSONField(serialize = false)
	private final XmlTableInfoBean parent;

	/**
	 * 属性的项目名
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:34:43
	 */
	private final List<String> keyField = new ArrayList<>();

	/**
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:36:53
	 * @param parent 所属直系父级元素
	 */
	public XmlKeyInfoBean(final XmlTableInfoBean parent) {
		this.parent = parent;
	}

	/**
	 * 得到所属直系父级元素
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:37:54
	 * @return the parent
	 */
	public XmlTableInfoBean getParent() {
		return this.parent;
	}

	/**
	 * 得到属性的项目名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午9:24:49
	 * @return the keyField
	 */
	public List<String> getKeyField() {
		return this.keyField;
	}

	/**
	 * 设置属性的项目名
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午9:24:49
	 * @param keyField the keyField to set
	 */
	public void addKeyField(final String keyField) {
		this.keyField.add(keyField);
	}

	/**
	 * 你懂的
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:42:36
	 * @return 你懂的
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

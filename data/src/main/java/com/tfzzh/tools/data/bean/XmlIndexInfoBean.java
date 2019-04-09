/**
 * @author Xu Weijie
 * @datetime 2018年1月19日_下午2:46:37
 */
package com.tfzzh.tools.data.bean;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.tfzzh.tools.data.tools.IndexTypeEnum;

/**
 * XML配置相关索引信息对象
 * 
 * @author Xu Weijie
 * @datetime 2018年1月19日_下午2:46:37
 */
public class XmlIndexInfoBean {

	/**
	 * 所属直系父级元素
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:24:00
	 */
	@JSONField(serialize = false)
	private final XmlTableInfoBean parent;

	/**
	 * 索引类型
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:25:43
	 */
	private IndexTypeEnum type;

	/**
	 * 所相关索引字段
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:26:15
	 */
	private final List<XmlIndexFieldInfoBean> indexField = new ArrayList<>();

	/**
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:36:03
	 * @param parent 所属直系父级元素
	 */
	public XmlIndexInfoBean(final XmlTableInfoBean parent) {
		this.parent = parent;
	}

	/**
	 * 得到所属直系父级元素
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:36:17
	 * @return the parent
	 */
	public XmlTableInfoBean getParent() {
		return this.parent;
	}

	/**
	 * 得到索引类型
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:26:40
	 * @return the type
	 */
	public IndexTypeEnum getType() {
		return this.type;
	}

	/**
	 * 设置索引类型
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:26:40
	 * @param type the type to set
	 */
	public void setType(final String type) {
		switch (type) {
		case "unique":
			this.type = IndexTypeEnum.Unique;
			return;
		case "fulltext":
			this.type = IndexTypeEnum.FullText;
			return;
		default:
			this.type = IndexTypeEnum.Normal;
			return;
		}
	}

	/**
	 * 得到所相关索引字段
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:26:40
	 * @return the indexField
	 */
	public List<XmlIndexFieldInfoBean> getIndexField() {
		return this.indexField;
	}

	/**
	 * 设置所相关索引字段
	 *
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午3:26:40
	 * @param indexField the indexField to set
	 */
	public void addIndexField(final XmlIndexFieldInfoBean indexField) {
		this.indexField.add(indexField);
	}

	/**
	 * 你懂的
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月19日_下午4:11:35
	 * @return 你懂的
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

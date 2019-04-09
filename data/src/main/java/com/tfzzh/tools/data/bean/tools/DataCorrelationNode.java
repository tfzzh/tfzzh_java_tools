/**
 * @author Weijie Xu
 * @dateTime Aug 23, 2014 6:00:08 PM
 */
package com.tfzzh.tools.data.bean.tools;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tfzzh.tools.data.bean.DataBeanTool;

/**
 * 数据关联树的节点
 * 
 * @author Weijie Xu
 * @dateTime Aug 23, 2014 6:00:08 PM
 */
public class DataCorrelationNode {

	/**
	 * 所相关的数据对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 6:01:28 PM
	 */
	private final DataBeanTool dataBean;

	/**
	 * 相关子节点
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 6:33:26 PM
	 */
	private final Map<Long, DataCorrelationNode> childNodes;

	/**
	 * 与上层关系
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 6:33:24 PM
	 */
	private final List<DataConditionBean> withUpper;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 6:47:06 PM
	 * @param dataBean 所相关的数据对象
	 */
	public DataCorrelationNode(final DataBeanTool dataBean) {
		this.dataBean = dataBean;
		this.childNodes = new HashMap<>();
		this.withUpper = new LinkedList<>();
	}

	/**
	 * 放入与上一层的关系条件
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 6:55:19 PM
	 * @param condition 与上层关系条件
	 */
	public void putCondition(final DataConditionBean condition) {
		this.withUpper.add(condition);
	}

	/**
	 * 放入一个相关子节点
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 7:05:18 PM
	 * @param child 子数据对象
	 * @param condition 子数据对象，与当前节点对象关系条件
	 * @return 被创建的子节点对象
	 */
	public DataCorrelationNode putChildNode(final DataBeanTool child, final DataConditionBean condition) {
		final DataCorrelationNode childNode = new DataCorrelationNode(child);
		this.childNodes.put(child.getId(), childNode);
		// 对新增的子节点，放入与上层关系条件
		childNode.putCondition(condition);
		return childNode;
	}

	/**
	 * 得到相关对象的ID
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 7:02:20 PM
	 * @return 相关对象的ID
	 */
	public Long getId() {
		return this.dataBean.getId();
	}

	/**
	 * 得到所相关的数据对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 7:06:02 PM
	 * @return the dataBean
	 */
	public DataBeanTool getDataBean() {
		return this.dataBean;
	}

	/**
	 * 得到相关子节点
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 7:06:02 PM
	 * @return the childNodes
	 */
	public Collection<DataCorrelationNode> getChildNodes() {
		return this.childNodes.values();
	}

	/**
	 * 得到目标子节点
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 10:46:47 AM
	 * @param id 目标ID
	 * @return 目标子节点。<br />
	 *         null，不存在目标子节点，这里不要做异常处理。<br />
	 */
	public DataCorrelationNode getChildNode(final Long id) {
		return this.childNodes.get(id);
	}

	/**
	 * 得到与上层关系列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 7:06:02 PM
	 * @return the withUpper
	 */
	public List<DataConditionBean> getWithUpper() {
		return this.withUpper;
	}
}

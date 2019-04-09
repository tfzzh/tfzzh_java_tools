/**
 * @author Weijie Xu
 * @dateTime Aug 21, 2014 6:26:37 PM
 */
package com.tfzzh.tools.data.bean.tools;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tfzzh.tools.data.bean.LogicBeanTool.LogicFieldTool;
import com.tfzzh.tools.data.tools.ToolBeanExcelPool;

/**
 * 关联连接键对象
 * 
 * @author Weijie Xu
 * @dateTime Aug 21, 2014 6:26:37 PM
 */
public class LinkKeyBean {

	/**
	 * 关系连接键
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 7:17:57 PM
	 */
	private final List<LogicFieldTool> linkKey;

	/**
	 * 键名称
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 7:17:57 PM
	 */
	private final String keyName;

	/**
	 * 在项目中名称
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 7:17:59 PM
	 */
	private final String proName;

	/**
	 * 相关的属性名，更多是作为单独存在的时候使用
	 * 
	 * @author Weijie Xu
	 * @dateTime 2015年1月9日 下午5:45:35
	 */
	private final String paramName;

	/**
	 * 是否独立键
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 26, 2014 7:24:50 PM
	 */
	private final boolean isSingle;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 7:18:26 PM
	 * @param linkKey 关系连接键
	 */
	public LinkKeyBean(final List<LogicFieldTool> linkKey) {
		if (linkKey.size() > 1) {
			// 针对有一个以上的进行排序
			// 对数据关联键集合进行排序
			Collections.sort(linkKey, new Comparator<LogicFieldTool>() {

				@Override
				public int compare(final LogicFieldTool l1, final LogicFieldTool l2) {
					// 正常来说不可能为0
					return (int) (l1.getId() - l2.getId());
				}
			});
			//
			this.linkKey = linkKey;
			boolean isFirst = true;
			final StringBuilder kn = new StringBuilder();
			final StringBuilder pn = new StringBuilder();
			for (final LogicFieldTool k : linkKey) {
				if (isFirst) {
					isFirst = false;
				} else {
					kn.append('_');
					pn.append("And");
				}
				kn.append(k.getId());
				pn.append(k.getProName());
			}
			this.keyName = kn.toString();
			this.proName = pn.append("IndexKey").toString();
			this.paramName = this.proName;
			this.isSingle = false;
		} else {
			// 只有一个的情况
			this.linkKey = linkKey;
			this.proName = this.linkKey.get(0).getType().getObjectTypeName();
			this.keyName = String.valueOf(this.linkKey.get(0).getId());
			this.paramName = this.linkKey.get(0).getProName();
			this.isSingle = true;
		}
	}

	/**
	 * 得到关系连接键
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 7:41:24 PM
	 * @return the linkKey
	 */
	public List<LogicFieldTool> getLinkKey() {
		return this.linkKey;
	}

	/**
	 * 得到键名称
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 7:41:24 PM
	 * @return the keyName
	 */
	public String getKeyName() {
		return this.keyName;
	}

	/**
	 * 得到在项目中名称
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 7:41:24 PM
	 * @return the proName
	 */
	public String getProName() {
		return this.proName;
	}

	/**
	 * 得到相关的属性名，更多是作为单独存在的时候使用
	 * 
	 * @author Weijie Xu
	 * @dateTime 2015年1月9日 下午5:47:09
	 * @return the paramName
	 */
	public String getParamName() {
		return this.paramName;
	}

	/**
	 * 是否独立键
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 26, 2014 7:25:28 PM
	 * @return the isSingle
	 */
	public boolean isSingle() {
		return this.isSingle;
	}

	/**
	 * 设置相关连接键<br />
	 * 附带放入到工具Bean池的操作<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 7:48:17 PM
	 * @param linkKey 相关连接键
	 * @return 被创建的相关连接键对象，null，不存在相关连接键
	 */
	public static LinkKeyBean setLinkKey(final List<LogicFieldTool> linkKey) {
		if (linkKey.size() > 0) {
			final LinkKeyBean lkb = new LinkKeyBean(linkKey);
			if (linkKey.size() > 1) {
				ToolBeanExcelPool.getInstance().putObjectLinkKey(lkb);
			} else {
				ToolBeanExcelPool.getInstance().putBasicLinkKey(linkKey.get(0).getType().getObjectTypeClass());
			}
			return lkb;
		} else {
			return null;
		}
	}
}

/**
 * @author Weijie Xu
 * @dateTime Aug 23, 2014 1:42:13 PM
 */
package com.tfzzh.tools.data.bean;

import com.tfzzh.tools.StringTools;

/**
 * 模板对象工具
 * 
 * @author Weijie Xu
 * @dateTime Aug 23, 2014 1:42:13 PM
 */
public abstract class TemplateObjectTool {

	/**
	 * 属性的ID
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:16:21 PM
	 */
	private final Long id;

	/**
	 * 属性名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:16:25 PM
	 */
	private final String name;

	/**
	 * 项目用名称，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 20, 2014 6:47:19 PM
	 */
	private final String proName;

	/**
	 * 对应常量用名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:16:26 PM
	 */
	private final String constantName;

	/**
	 * 说明
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:24:41 PM
	 */
	private final String desc;

	/**
	 * 创建人
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:10:50 PM
	 */
	private final String createAuthor;

	/**
	 * 创建时间
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:10:50 PM
	 */
	private final String createDate;

	/**
	 * 最后修改人
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:10:50 PM
	 */
	private final String lastChangeAuthor;

	/**
	 * 最后修改时间
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:10:50 PM
	 */
	private final String lastChangeDate;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:44:14 PM
	 * @param id 属性的ID
	 * @param name 属性名
	 * @param desc 属性的说明
	 * @param createAuthor 创建人
	 * @param createDate 创建时间
	 * @param lastChangeAuthor 最后修改人
	 * @param lastChangeDate 最后修改时间
	 */
	protected TemplateObjectTool(final Long id, final String name, final String desc, final String createAuthor, final String createDate, final String lastChangeAuthor, final String lastChangeDate) {
		this.id = id;
		this.name = name.substring(0, 1).toLowerCase() + name.substring(1);
		this.proName = name.substring(0, 1).toUpperCase() + name.substring(1);
		this.constantName = StringTools.splitStringWhitUpper(name);
		this.desc = desc.replaceAll("\"", "\\\\\"");
		this.createAuthor = createAuthor;
		this.createDate = createDate;
		this.lastChangeAuthor = lastChangeAuthor;
		this.lastChangeDate = lastChangeDate;
	}

	/**
	 * 得到对象/属性的ID
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:45:59 PM
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 得到对象/属性名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:45:59 PM
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 得到项目用名称，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:45:59 PM
	 * @return the proName
	 */
	public String getProName() {
		return this.proName;
	}

	/**
	 * 得到对应常量用名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:45:59 PM
	 * @return the constantName
	 */
	public String getConstantName() {
		return this.constantName;
	}

	/**
	 * 得到对象/属性说明
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:45:59 PM
	 * @return the desc
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * 得到创建人
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:45:59 PM
	 * @return the createAuthor
	 */
	public String getCreateAuthor() {
		return this.createAuthor;
	}

	/**
	 * 得到创建时间
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:45:59 PM
	 * @return the createDate
	 */
	public String getCreateDate() {
		return this.createDate;
	}

	/**
	 * 得到最后修改人
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:45:59 PM
	 * @return the lastChangeAuthor
	 */
	public String getLastChangeAuthor() {
		return this.lastChangeAuthor;
	}

	/**
	 * 得到最后修改时间
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 1:45:59 PM
	 * @return the lastChangeDate
	 */
	public String getLastChangeDate() {
		return this.lastChangeDate;
	}
}

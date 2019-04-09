/**
 * @author Weijie Xu
 * @dateTime Aug 13, 2014 9:02:59 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * 表消息Bean相关模版生成
 * 
 * @author Weijie Xu
 * @dateTime Aug 13, 2014 9:02:59 PM
 */
public abstract class BaseTableTemplate extends BaseTemplate {

	/**
	 * 数据对象工具
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 9:02:59 PM
	 */
	protected DataBeanTool data;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 9:02:59 PM
	 * @param obj 数据对象工具
	 */
	protected BaseTableTemplate(final DataBeanTool obj) {
		super(obj);
	}

	/**
	 * 设置全局用参数
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 9:02:59 PM
	 * @param obj 参数对象
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.data = (DataBeanTool) obj;
	}

	/**
	 * 设置参数
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 9:02:59 PM
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("data", this.data);
	}
}

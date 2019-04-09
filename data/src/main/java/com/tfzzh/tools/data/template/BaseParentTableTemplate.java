/**
 * @author Xu Weijie
 * @datetime 2017年9月21日_上午11:06:27
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.DataBeanTool.ParentDataTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * 表消息Bean相关模版生成
 * 
 * @author Xu Weijie
 * @datetime 2017年9月21日_上午11:06:27
 */
public abstract class BaseParentTableTemplate extends BaseTemplate {

	/**
	 * 数据对象工具
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 */
	protected ParentDataTool data;

	/**
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 * @param obj 数据对象工具
	 */
	protected BaseParentTableTemplate(final ParentDataTool obj) {
		super(obj);
	}

	/**
	 * 设置全局用参数
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 * @param obj 参数对象
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.data = (ParentDataTool) obj;
	}

	/**
	 * 设置参数
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("data", this.data);
	}
}

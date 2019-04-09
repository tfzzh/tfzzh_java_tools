/**
 * @author Weijie Xu
 * @dateTime 2014-3-7 下午12:43:31
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.LogicBeanTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * @author Weijie Xu
 * @dateTime 2014-3-7 下午12:43:31
 */
public class LogicBeanTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午4:25:52
	 */
	private LogicBeanTool bean;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午12:45:53
	 * @param bean 类对象
	 */
	public LogicBeanTemplate(final LogicBeanTool bean) {
		super(bean);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午4:40:56
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("data", this.bean);
		super.params.put("classBeanPrefix", ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX);
		super.params.put("classBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX);
		super.params.put("entityBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午4:25:38
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.bean = (LogicBeanTool) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午12:43:31
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/" + ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX.replaceAll("[.]", "/") + "/";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午12:43:31
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.bean.getProName() + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX + ".java";
	}
}

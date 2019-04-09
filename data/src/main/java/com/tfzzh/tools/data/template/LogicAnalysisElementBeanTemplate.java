/**
 * @author Weijie Xu
 * @dateTime Sep 5, 2014 1:34:06 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.LogicBeanTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * @author Weijie Xu
 * @dateTime Sep 5, 2014 1:34:06 PM
 */
public class LogicAnalysisElementBeanTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 1:34:06 PM
	 */
	private LogicBeanTool bean;

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 1:34:06 PM
	 * @param bean 类对象
	 */
	public LogicAnalysisElementBeanTemplate(final LogicBeanTool bean) {
		super(bean);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 1:34:06 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("data", this.bean);
		super.params.put("classBeanPrefix", ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX);
		super.params.put("classBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX);
		super.params.put("classElementSuffix", ConfigDataTemplateConstants.CLASSBEAN_CLASS_ELEMENT_SUFFIX);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 1:34:06 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.bean = (LogicBeanTool) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 1:34:06 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/" + ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX.replaceAll("[.]", "/") + "/";
	}

	/**
	 * 得到模版名称
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 8:24:30 PM
	 * @return 模版名称
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getTemplateName()
	 */
	@Override
	protected String getTemplateName() {
		final String name = this.getClass().getSimpleName();
		if (this.bean.getAnalysisTemplate().isRelatedStream()) {
			return name.substring(0, name.length() - 8) + "Stream.ftl";
		} else {
			return name.substring(0, name.length() - 8) + ".ftl";
		}
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 1:34:06 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.bean.getProName() + ConfigDataTemplateConstants.CLASSBEAN_CLASS_ELEMENT_SUFFIX + ".java";
	}
}

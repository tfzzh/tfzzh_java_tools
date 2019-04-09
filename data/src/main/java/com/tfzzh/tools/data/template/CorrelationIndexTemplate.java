/**
 * @author Weijie Xu
 * @dateTime Aug 21, 2014 2:05:32 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.tools.LinkKeyBean;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * 关联索引类
 * 
 * @author Weijie Xu
 * @dateTime Aug 21, 2014 2:05:32 PM
 */
public class CorrelationIndexTemplate extends BaseTemplate {

	/**
	 * 关联索引列表
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 2:06:43 PM
	 */
	private LinkKeyBean linkKey;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 2:05:32 PM
	 * @param indexs 关联索引列表
	 */
	public CorrelationIndexTemplate(final LinkKeyBean indexs) {
		super(indexs);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 2:05:32 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("linkKey", this.linkKey);
		super.params.put("classBeanPrefix", ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 2:05:32 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.linkKey = (LinkKeyBean) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 2:05:32 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/" + ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX.replaceAll("[.]", "/") + "/index";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 21, 2014 2:05:32 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.linkKey.getProName() + ".java";
	}
}

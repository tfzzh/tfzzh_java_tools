/**
 * @author Weijie Xu
 * @dateTime Sep 2, 2014 1:04:32 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.DataBean;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * @author Weijie Xu
 * @dateTime Sep 2, 2014 1:04:32 PM
 */
public class DataCorrelationAttributeTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 1:04:32 PM
	 */
	private DataBean bean;

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 1:04:32 PM
	 * @param bean 类对象
	 */
	public DataCorrelationAttributeTemplate(final DataBean bean) {
		super(bean);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 1:04:32 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("data", this.bean);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 1:04:32 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.bean = (DataBean) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 1:04:32 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return this.getOutFilePath(ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH);
	}

	/**
	 * 实际的输出路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月13日 下午4:51:50
	 * @param srcPath 输出源码包路径
	 * @return 待输出的文件所在路径
	 */
	protected String getOutFilePath(final String srcPath) {
		return srcPath + ConfigDataTemplateConstants.BASE_EXCEL_CONFIG_PATH + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/" + ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX.replaceAll("[.]", "/") + "/../data/tools";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 1:04:32 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.bean.getProName() + "DataCorrelationAttribute.java";
	}
}

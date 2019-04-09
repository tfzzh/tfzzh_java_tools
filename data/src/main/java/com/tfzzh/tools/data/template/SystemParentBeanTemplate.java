/**
 * @author Xu Weijie
 * @datetime 2017年9月21日_上午11:06:27
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.DataBeanTool.ParentDataTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * 系统数据BEAN模版
 * 
 * @author Xu Weijie
 * @datetime 2017年9月21日_上午11:06:27
 */
public class SystemParentBeanTemplate extends BaseParentTableTemplate {

	/**
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 * @param bean 所需表消息
	 */
	public SystemParentBeanTemplate(final ParentDataTool bean) {
		super(bean);
	}

	/**
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("classBeanPrefix", ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX);
		super.params.put("systemIdentify", "data");
		super.params.put("classBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX);
		super.params.put("entityBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX);
	}

	/**
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return this.getOutFilePath(ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH);
	}

	/**
	 * 实际的输出路径
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 * @param srcPath 输出源码包路径
	 * @return 待输出的文件所在路径
	 */
	protected String getOutFilePath(final String srcPath) {
		return srcPath + "../" + this.data.getSrcPath() + "/" + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/" + this.data.getFunctionName() + "/model/bean";
	}

	/**
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.data.getProName() + ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX + ".java";
	}
}

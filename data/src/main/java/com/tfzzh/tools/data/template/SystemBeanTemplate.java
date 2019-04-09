/**
 * @author XuWeijie
 * @dateTime May 3, 2010 1:00:40 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * 系统数据BEAN模版
 * 
 * @author XuWeijie
 * @dateTime May 3, 2010 1:00:40 PM
 */
public class SystemBeanTemplate extends BaseTableTemplate {

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 3:02:45 PM
	 * @param bean 所需表消息
	 */
	public SystemBeanTemplate(final DataBeanTool bean) {
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
		super.params.put("classBeanPrefix", ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX);
		super.params.put("systemIdentify", "data");
		super.params.put("classBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX);
		super.params.put("entityBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX);
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 3:03:29 PM
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
		return srcPath + "../" + super.data.getSrcPath() + "/" + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/" + super.data.getFunctionName() + "/model/bean";
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 3:03:29 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return super.data.getProName() + ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX + ".java";
	}
}

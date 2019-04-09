/**
 * @author Xu Weijie
 * @datetime 2017年9月21日_上午11:06:27
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.DataBeanTool.ParentDataTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * 系统数据DAO模版
 * 
 * @author Xu Weijie
 * @datetime 2017年9月21日_上午11:06:27
 */
public class SystemParentDAOTemplate extends BaseParentTableTemplate {

	/**
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 * @param bean 所需表消息
	 */
	public SystemParentDAOTemplate(final ParentDataTool bean) {
		super(bean);
	}

	/**
	 * 设置参数
	 * 
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 * @see com.tfzzh.tools.data.template.BaseTableTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("dataCode", ConfigDataTemplateConstants.DATA_CODE);
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
		return srcPath + "../" + super.data.getSrcPath() + "/" + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/" + super.data.getFunctionName() + "/model/dao";
	}

	/**
	 * @author Xu Weijie
	 * @datetime 2017年9月21日_上午11:06:27
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return super.data.getProName() + "DAO.java";
	}
}

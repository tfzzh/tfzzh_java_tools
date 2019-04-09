/**
 * @author XuWeijie
 * @dateTime May 9, 2010 6:55:19 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * 系统数据DAO实现类模版
 * 
 * @author XuWeijie
 * @dateTime May 9, 2010 6:55:19 PM
 */
public class SystemDAOImplTemplate extends BaseTableTemplate {

	/**
	 * @author XuWeijie
	 * @dateTime May 9, 2010 6:55:48 PM
	 * @param bean 所需表消息
	 */
	public SystemDAOImplTemplate(final DataBeanTool bean) {
		super(bean);
	}

	/**
	 * 设置参数
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 6:58:28 PM
	 * @see com.tfzzh.tools.data.template.BaseTableTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("dataCode", ConfigDataTemplateConstants.DATA_CODE);
		super.params.put("entityBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX);
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 9, 2010 6:55:20 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return super.data.getProName() + "DAOImpl.java";
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 9, 2010 6:55:20 PM
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
		return srcPath + "../" + super.data.getSrcPath() + "/" + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/" + super.data.getFunctionName() + "/model/dao/impl";
	}
}

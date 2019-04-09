/**
 * @author XuWeijie
 * @dateTime May 3, 2010 4:35:38 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * @author XuWeijie
 * @dateTime May 3, 2010 4:35:38 PM
 */
public class CreateTableSQLTemplate extends BaseTableTemplate {

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 4:36:15 PM
	 * @param bean 相关表消息
	 */
	public CreateTableSQLTemplate(final DataBeanTool bean) {
		super(bean);
	}

	/**
	 * 设置参数
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 5:31:02 PM
	 * @see com.tfzzh.tools.data.template.BaseTableTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("dataCode", ConfigDataTemplateConstants.DATA_CODE);
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 4:35:38 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH + "../sql_mysql/";
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 4:35:38 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return super.data.getDatatableName() + "_create.sql";
	}
}

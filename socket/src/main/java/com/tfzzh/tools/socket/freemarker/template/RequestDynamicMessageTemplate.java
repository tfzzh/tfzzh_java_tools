/**
 * @author Weijie Xu
 * @dateTime 2014-3-27 下午8:47:12
 */
package com.tfzzh.tools.socket.freemarker.template;

import com.tfzzh.tools.socket.bean.MessageToolBean;
import com.tfzzh.tools.socket.tools.SocketTemplateConstants;

/**
 * @author Weijie Xu
 * @dateTime 2014-3-27 下午8:47:12
 */
public class RequestDynamicMessageTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午2:08:40
	 */
	private MessageToolBean bean;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-27 下午8:47:14
	 * @param projectPath 项目路径
	 * @param obj 相关对象
	 */
	public RequestDynamicMessageTemplate(final String projectPath, final MessageToolBean obj) {
		super(SocketTemplateConstants.PROJECT_TEMPLATE_PATH + "/../" + projectPath, obj);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午4:58:25
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", SocketTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("bean", this.bean);
		super.params.put("msgSuffix", SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午4:58:26
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.bean = (MessageToolBean) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-27 下午8:47:12
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return SocketTemplateConstants.PROJECT_PATH_PREFIX + this.bean.getScopePathName() + "/action/bean/";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-27 下午8:47:12
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.bean.getProName() + SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX + ".java";
	}
}

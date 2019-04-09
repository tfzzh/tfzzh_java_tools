/**
 * @author Weijie Xu
 * @dateTime 2014-3-27 下午8:46:40
 */
package com.tfzzh.tools.socket.freemarker.template;

import java.util.Collection;

import com.tfzzh.tools.socket.bean.iface.RequestTool;
import com.tfzzh.tools.socket.tools.SocketTemplateConstants;

/**
 * 对应关系映射模板
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-27 下午8:46:40
 */
public class CorrespondenceMappingTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 上午9:37:35
	 */
	private Collection<RequestTool> beans;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-27 下午8:46:47
	 * @param projectPath 项目路径
	 * @param obj 需求的对象
	 */
	public CorrespondenceMappingTemplate(final String projectPath, final Collection<RequestTool> obj) {
		super(SocketTemplateConstants.PROJECT_TEMPLATE_PATH + "/../" + projectPath, obj);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午4:58:06
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", SocketTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("beans", this.beans);
		super.params.put("scopeName", this.beans.iterator().next().getScope().getScopeName());
		super.params.put("proName", this.beans.iterator().next().getScope().getProName());
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午4:58:08
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setOverAllParam(final Object obj) {
		this.beans = (Collection<RequestTool>) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-27 下午8:46:40
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return SocketTemplateConstants.PROJECT_PATH_PREFIX + this.beans.iterator().next().getScopePathName() + "/action/tools/";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-27 下午8:46:40
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.beans.iterator().next().getScope().getProName() + "CorrespondenceMapping.java";
	}
}

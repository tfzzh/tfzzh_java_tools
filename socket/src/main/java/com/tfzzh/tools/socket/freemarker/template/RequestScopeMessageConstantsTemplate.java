/**
 * @author Weijie Xu
 * @dateTime 2014-3-27 下午8:46:40
 */
package com.tfzzh.tools.socket.freemarker.template;

import com.tfzzh.tools.socket.tools.RequestXMLTool;
import com.tfzzh.tools.socket.tools.ScopeType;
import com.tfzzh.tools.socket.tools.SocketTemplateConstants;

/**
 * 对应范围关系映射模板
 * 
 * @author Weijie Xu
 * @dateTime 2014年6月23日 下午4:17:04
 */
public class RequestScopeMessageConstantsTemplate extends RequestMessageConstantsTemplate {

	/**
	 * 作用域范围
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月30日_下午2:24:14
	 */
	private ScopeType st;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月23日 下午4:17:04
	 * @param projectPath 项目路径
	 * @param st 作用域范围
	 */
	public RequestScopeMessageConstantsTemplate(final String projectPath, final ScopeType st) {
		super(projectPath, st);
	}

	/**
	 * 设置全局用参数
	 * 
	 * @author XuWeijie
	 * @dateTime Aug 20, 2010 2:36:49 PM
	 * @param obj 参数对象
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.st = (ScopeType) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月23日 下午4:17:04
	 * @see com.tfzzh.tools.socket.freemarker.template.RequestMessageConstantsTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("beans", RequestXMLTool.getInstance().getOperationList(this.st));
		super.params.put("scope", this.st);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月23日 下午4:17:04
	 * @see com.tfzzh.tools.socket.freemarker.template.RequestMessageConstantsTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return SocketTemplateConstants.PROJECT_PATH_PREFIX + "/action/bean/tools/";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月23日 下午4:17:04
	 * @see com.tfzzh.tools.socket.freemarker.template.RequestMessageConstantsTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return "Request" + this.st.getProName() + "MessageConstants.java";
	}
}

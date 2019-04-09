/**
 * @author Weijie Xu
 * @dateTime 2014-3-7 下午1:14:18
 */
package com.tfzzh.tools.socket;

import java.util.Collection;

import com.tfzzh.tools.socket.bean.MessageToolBean;
import com.tfzzh.tools.socket.bean.iface.OperationTool;
import com.tfzzh.tools.socket.bean.iface.RequestTool;
import com.tfzzh.tools.socket.freemarker.template.CorrespondenceMappingTemplate;
import com.tfzzh.tools.socket.freemarker.template.MessageBeanConstantsTemplate;
import com.tfzzh.tools.socket.freemarker.template.MessageBeanFactoryTemplate;
import com.tfzzh.tools.socket.freemarker.template.OperationConstantsTemplate;
import com.tfzzh.tools.socket.freemarker.template.OperationTemplate;
import com.tfzzh.tools.socket.freemarker.template.RequestActionTemplate;
import com.tfzzh.tools.socket.freemarker.template.RequestDynamicMessageTemplate;
import com.tfzzh.tools.socket.freemarker.template.RequestMessageConstantsTemplate;
import com.tfzzh.tools.socket.freemarker.template.RequestNormalHttpMessageTemplate;
import com.tfzzh.tools.socket.freemarker.template.RequestNormalMessageTemplate;
import com.tfzzh.tools.socket.freemarker.template.RequestProxyMessageTemplate;
import com.tfzzh.tools.socket.tools.RequestXMLTool;
import com.tfzzh.tools.socket.tools.ScopeTool;
import com.tfzzh.tools.socket.tools.ScopeType;
import com.tfzzh.tools.socket.tools.SocketTemplateConstants;

/**
 * @author Weijie Xu
 * @dateTime 2014-3-7 下午1:14:18
 */
public class MakeFile {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午1:14:18
	 */
	public MakeFile() {
		this.make();
	}

	/**
	 * 进行模版生成
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午1:14:18
	 */
	private void make() {
		Collection<RequestTool> reqList;
		Collection<OperationTool> opeList;
		Collection<MessageToolBean> msgList;
		for (final ScopeType st : ScopeTool.getInstance().getAllScopeType()) {
			if (null != (reqList = RequestXMLTool.getInstance().getRequestList(st))) {
				for (final RequestTool t : reqList) {
					new RequestActionTemplate(st.getProjectChangePath(), t);
				}
				new CorrespondenceMappingTemplate(st.getProjectPath(), reqList);
			}
			if (null != (opeList = RequestXMLTool.getInstance().getOperationList(st))) {
				for (final OperationTool o : opeList) {
					new OperationTemplate(st.getProjectChangePath(), o);
				}
				new OperationConstantsTemplate(st.getProjectPath(), opeList);
			}
			if (null != (msgList = RequestXMLTool.getInstance().getNormalMessageList(st, SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX))) {
				for (final MessageToolBean t : msgList) {
					switch (t.getMsgType()) {
					case Socket:
						new RequestNormalMessageTemplate(st.getProjectPath(), t);
						continue;
					case Http:
						new RequestNormalHttpMessageTemplate(st.getProjectPath(), t);
						continue;
					}
				}
			}
			if (null != (msgList = RequestXMLTool.getInstance().getDynamicMessageList(st, SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX))) {
				for (final MessageToolBean t : msgList) {
					new RequestDynamicMessageTemplate(st.getProjectPath(), t);
				}
			}
			if (null != (msgList = RequestXMLTool.getInstance().getProxyMessageList(st, SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX))) {
				for (final MessageToolBean t : msgList) {
					new RequestProxyMessageTemplate(st.getProjectPath(), t);
				}
			}
		}
		// 比较特殊的部分
		new MessageBeanFactoryTemplate(ScopeTool.getInstance().getDefaultScopeType().getProjectPath(), RequestXMLTool.getInstance().getAllMessageList(SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX));
		new MessageBeanConstantsTemplate(ScopeTool.getInstance().getDefaultScopeType().getProjectPath(), RequestXMLTool.getInstance().getAllMessageListSortId());
		new RequestMessageConstantsTemplate(ScopeTool.getInstance().getDefaultScopeType().getProjectPath());
	}
}

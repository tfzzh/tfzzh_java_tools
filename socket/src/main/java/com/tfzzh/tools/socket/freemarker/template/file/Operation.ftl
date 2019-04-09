/**
 * @author ${author}
 */
package ${projectPath}${bean.scopePackageName}.operation;

import com.tfzzh.core.view.socket.RequestSession;
import com.tfzzh.core.view.socket.annotation.InitiativeOperation;
<#if bean.nullMsg>
import com.tfzzh.core.view.socket.bean.BaseMessageBean;
</#if>
import com.tfzzh.core.view.socket.bean.OutputMessageBean;
import com.tfzzh.core.view.socket.initiative.OperationAction;
import com.tfzzh.core.view.socket.tools.RequestMessageAttribute;
<#if bean.messages?size != 0>
	<#list bean.messages as msg>
		<#if msg.correspondenceClient??>
import ${projectPath}${msg.correspondenceClient.scopePackageName}.action.bean.${msg.correspondenceClient.proName}${msgSuffix};
		<#else>
import ${projectPath}${msg.scopePackageName}.action.bean.${msg.proName}${msgSuffix};
		</#if>
	</#list>
</#if>
import com.yqgame.socketserver.action.bean.tools.MessageBeanFactory;
import com.yqgame.socketserver.action.bean.tools.RequestMessageConstants;

/**
 * 去到“${bean.target.desc}”操作行为
 * 
 * @author ${author}
 */
@InitiativeOperation("to${bean.proName}")
public class To${bean.proName}Operation${tmpFilePostfix} implements OperationAction {
<#list bean.linkedList as link>

	/**
	 * 去到“${bean.target.desc}”的${link.desc}
	 * 
	 * @author ${author}
	 * @param session 会话信息
	<#if link.proxy>
	 * @param proxyMsg 代理消息
	</#if>
	 */
	public void <#if link.proxy>proxy<#elseif link.id[0] % 2 == 1>to<#else>back</#if>${link.methodName?cap_first}(final RequestSession session<#if link.proxy>, final ${link.msg.proName}${msgSuffix} proxyMsg</#if>) {
		// TODO 请自行修改参数内容，以及omb中的参数内容，或者自处理set
	<#if !link.proxy>
		final RequestMessageAttribute<<#if link.msg??>${link.msg.proName}${msgSuffix}<#else>BaseMessageBean</#if>> rm = RequestMessageConstants.RM_${link.id[0]?c};
		final OutputMessageBean omb = new OutputMessageBean(rm.getRquestCode(), MessageBeanFactory.getInstance().getMessageBean(
				rm.getMessageAttribute(<#if link.msg??>${link.msg.proName}${msgSuffix}<#else>BaseMessageBean</#if>.class)));
		session.sendMessage(omb);
	<#else>
		final OutputMessageBean omb = new OutputMessageBean(proxyMsg.getProxyMsg().getRequestCode(), proxyMsg);
		session.sendMessage(omb);
	</#if>
	}
</#list>
}

/**
 * @author ${author}
 */
package ${projectPath}.action.bean.tools;

<#list beans as b>
	<#if b.hasList>
import java.util.List;
	<#break>
	</#if>
</#list>

import com.tfzzh.core.view.socket.bean.BaseMessageBean;
import com.tfzzh.core.view.socket.tools.BaseMessageBeanAttribute;
<#assign con="|">
<#list beans as b>
	<#assign idd="|"+b.proName+"|">
	<#if con?index_of(idd) == -1>
		<#assign con=con+b.proName+"|">
import ${projectPath}.action.bean.${b.proName}${msgSuffix};
	</#if>
</#list>

/**
 * 消息Bean工厂
 * 
 * @author ${author}
 */
public class MessageBeanFactory {

	/**
	 * 对象唯一实例
	 * 
	 * @author ${author}
	 */
	private static MessageBeanFactory factory = new MessageBeanFactory();

	/**
	 * @author ${author}
	 */
	private MessageBeanFactory() {
	}

	/**
	 * 得到对象实例
	 * 
	 * @author ${author}
	 * @return 对象唯一实例
	 */
	public static MessageBeanFactory getInstance() {
		return MessageBeanFactory.factory;
	}

	/**
	 * 得到目标消息实例，无初始化参数
	 * 
	 * @author ${author}
	 * @param <M> 消息属性对象
	 * @param mba 消息Bean属性
	 * @return 目标消息实例
	 */
	@SuppressWarnings("unchecked")
	public <M extends BaseMessageBean> M getMessageBean(final BaseMessageBeanAttribute<M> mba) {
		if (null == mba) {
			return null;
		}
		switch (mba.getCode()) {
<#list beans as b>
	<#assign ind=0>
		case ${b.id?c}:
			return (M) new ${b.proName}${msgSuffix}();
</#list>
		default:
			return null;
		}
	}

	/**
	 * 得到目标消息实例
	 * 
	 * @author ${author}
	 * @param <M> 消息属性对象
	 * @param mba 消息Bean属性
	 * @param obj 参数
	 * @return 目标消息实例
	 */
	@SuppressWarnings("unchecked")
	public <M extends BaseMessageBean> M getMessageBean(final BaseMessageBeanAttribute<M> mba, final Object... obj) {
		if (null == mba) {
			return null;
		}
		switch (mba.getCode()) {
<#list beans as b>
	<#assign ind=0>
		case ${b.id?c}:
			return (M) new ${b.proName}${msgSuffix}(<#if b.hasParent><#list b.parentParams as param>(${param.completeTypeName}) obj[${ind}]<#if param_has_next>, <#elseif (b.params?size > 0)>, </#if><#assign ind=ind+1></#list></#if><#list b.params as param>(${param.completeTypeName}) obj[${ind}]<#if param_has_next>, </#if><#assign ind=ind+1></#list>);
</#list>
		default:
			return null;
		}
	}

	/**
	 * 得到目标消息类对象
	 * 
	 * @author ${author}
	 * @param <M> 消息属性对象
	 * @param mba 消息Bean属性
	 * @return 目标消息实例
	 */
	@SuppressWarnings("unchecked")
	public <M extends BaseMessageBean> Class<M> getMessageClass(final BaseMessageBeanAttribute<M> mba) {
		if (null == mba) {
			return null;
		}
		switch (mba.getCode()) {
<#list beans as b>
	<#assign ind=0>
		case ${b.id?c}:
			return (Class<M>) ${b.proName}${msgSuffix}.class;
</#list>
		default:
			return null;
		}
	}
}

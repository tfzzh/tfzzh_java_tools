/**
 * @author ${author}
 */
package ${projectPath}${bean.scopePackageName}.action.bean;

import java.io.IOException;
<#if bean.hasList>
<#if bean.hasListImpl>
import java.util.LinkedList;
</#if>
import java.util.List;
</#if>
import java.util.Map;

<#if bean.needValidate>
	<#if bean.hasNumberValidate>
import com.tfzzh.core.validate.NumberValidateTypeEnum;
	</#if>
import com.tfzzh.core.validate.RequestValidateException;
	<#if bean.hasStringValidate>
import com.tfzzh.core.validate.StringValidate;
	</#if>
</#if>
import com.tfzzh.core.view.socket.annotation.ActionMessage;
import com.tfzzh.core.view.socket.bean.BaseMessageBean;
import com.tfzzh.core.view.socket.bean.BaseProxyMessageBean;
import com.tfzzh.core.view.socket.tools.TfzzhDataInputStream;
import com.tfzzh.core.view.socket.tools.TfzzhDataOutputStream;
<#if !bean.inSameScope>
import ${projectPath}${bean.parent.scopePackageName}.action.bean.${bean.parent.proName}${msgSuffix};
</#if>

/**
 * ${bean.desc}消息
 * 
 * @author ${author}
 */
@ActionMessage(value = ${bean.id?c}, isProxy = true)
public class ${bean.proName}${msgSuffix} extends BaseMessageBean {

	/**
	 * @author ${author}
	 */
	private static final long serialVersionUID = 1L;
<#list bean.params as param>

	/**
	 * ${param.desc}
	 * 
	 * @author ${author}
	 */
	private ${param.typeName} ${param.name};
</#list>

	/**
	 * 必然存在的对象
	 * 
	 * @author ${author}
	 */
	private BaseProxyMessageBean proxyMsg = new BaseProxyMessageBean();

	/**
	 * 是否存在子类
	 * 
	 * @author ${author}
	 */
	private boolean hasChild;

	/**
	 * @author ${author}
	 */
	public ${bean.proName}${msgSuffix}() {
	}

	/**
	 * @author ${author}
<#if bean.hasParent>
	<#list bean.parent.params as param>
	 * @param ${param.name} ${param.desc}
	</#list>
</#if>
<#list bean.params as param>
	 * @param ${param.name} ${param.desc}
</#list>
	 */
	public ${bean.proName}${msgSuffix}(<#if bean.hasParent><#list bean.parent.params as param>final ${param.typeName} ${param.name}, </#list></#if><#list bean.params as param>final ${param.typeName} ${param.name}<#if param_has_next>, </#if></#list>) {
<#if bean.hasParent>
		// 首先完成父类数值工作
		super(<#list bean.parent.params as param>${param.name}<#if param_has_next>, </#if></#list>);
</#if>
<#list bean.params as param>
		this.${param.name} = ${param.name};
</#list>
	}

	/**
	 * @author ${author}
	 * @param buf 预从中读取消息的字节流对象
	 * @param problemMsg 问题消息列表
	 * @throws IOException 抛
	 */
	public ${bean.proName}${msgSuffix}(final TfzzhDataInputStream buf, final Map<String, String> problemMsg) throws IOException {
		this.read(buf, problemMsg);
	}
<#list bean.params as param>

	/**
	 * 得到${param.desc}
	 * 
	 * @author ${author}
	 * @return the ${param.name}
	 */
	public ${param.typeName} get${param.proName}() {
		return this.${param.name};
	}

	/**
	 * 设置${param.desc}
	 * 
	 * @author ${author}
	 * @param ${param.name} ${param.desc}
	 */
	public void set${param.proName}(final ${param.typeName} ${param.name}) {
		this.${param.name} = ${param.name};
	}
</#list>

	/**
	 * 得到被代理的消息
	 * 
	 * @author ${author}
	 * @return the proxyMsg
	 */
	public BaseProxyMessageBean getProxyMsg() {
		return this.proxyMsg;
	}

	/**
	 * @author ${author}
	 * @param buf 预从中读取消息的字节流对象
	 * @throws IOException 抛
	 * @see com.tfzzh.core.view.socket.bean.BaseMessageBean#write(com.tfzzh.core.view.socket.tools.TfzzhDataOutputStream)
	 */
	@Override
	public void write(final TfzzhDataOutputStream buf) throws IOException {
		if (!this.hasChild) {
<#if !bean.toClient>
	<#if bean.hasParent>
			super.write(buf);
	</#if>
	<#list bean.params as param>
		<#if param.objType??>
			<#switch param.typeObjectName>
				<#case "List">
			if (null == this.${param.name}) {
				buf.writeInt(0);
			} else {
				buf.writeInt(this.${param.name}.size());
				for (final ${param.obj.proName}${msgSuffix} bean : this.${param.name}) {
					bean.write(buf);
				}
			}
				<#break>
				<#case "Bean">
			this.${param.name}.write(buf);
				<#break>
			</#switch>
		<#else>
			buf.write${param.typeObjectName}(this.${param.name});
		</#if>
	</#list>
</#if>
			buf.write(this.proxyMsg.getProxyMsg());
		} else {
<#if bean.hasParent>
			super.write(buf);
</#if>
<#list bean.params as param>
	<#if param.objType??>
		<#switch param.typeObjectName>
			<#case "List">
			if (null == this.${param.name}) {
				buf.writeInt(0);
			} else {
				buf.writeInt(this.${param.name}.size());
				for (final ${param.obj.proName}${msgSuffix} bean : this.${param.name}) {
					bean.write(buf);
				}
			}
			<#break>
			<#case "Bean">
			this.${param.name}.write(buf);
			<#break>
		</#switch>
	<#else>
			buf.write${param.typeObjectName}(this.${param.name});
	</#if>
</#list>
		}
	}

	/**
	 * @author ${author}
	 * @param buf 预从中读取消息的字节流对象
	 * @param problemMsg 问题消息列表
	 * @throws IOException 抛
	 * @see com.tfzzh.core.view.socket.bean.BaseMessageBean#read(com.tfzzh.core.view.socket.tools.TfzzhDataInputStream,
	 *      java.util.Map)
	 */
	@Override
	public void read(final TfzzhDataInputStream buf, final Map<String, String> problemMsg) throws IOException {
		if (!this.hasChild) {
<#if bean.toClient>
	<#if bean.hasParent>
			super.read(buf, problemMsg);
	</#if>
	<#list bean.params as param>
		<#if param.objType??>
			<#switch param.typeObjectName>
				<#case "List">
			int size = buf.readInt();
			this.${param.name} = new LinkedList<${param.obj.proName}${msgSuffix}>();
			for (; size > 0; size--) {
				this.${param.name}.add(new ${param.obj.proName}${msgSuffix}(buf, problemMsg));
			}
				<#break>
				<#case "Bean">
			this.${param.name} = new ${param.obj.proName}${msgSuffix}(buf, problemMsg);
				<#break>
			</#switch>
		<#else>
			this.${param.name} = buf.read${param.typeObjectName}();
		</#if>
	</#list>
</#if>
			final byte[] bs = new byte[buf.available()];
			buf.read(bs);
			this.proxyMsg.setProxyMsg(bs);
		} else {
<#if bean.hasParent>
			super.read(buf, problemMsg);
</#if>
<#list bean.params as param>
	<#if param.objType??>
		<#switch param.typeObjectName>
			<#case "List">
			int size = buf.readInt();
			this.${param.name} = new LinkedList<${param.obj.proName}${msgSuffix}>();
			for (; size > 0; size--) {
				this.${param.name}.add(new ${param.obj.proName}${msgSuffix}(buf, problemMsg));
			}
			<#break>
			<#case "Bean">
			this.${param.name} = new ${param.obj.proName}${msgSuffix}(buf, problemMsg);
			<#break>
		</#switch>
	<#else>
			this.${param.name} = buf.read${param.typeObjectName}();
	</#if>
</#list>
		}
	}

	/**
	 * 存在子类
	 * 
	 * @author ${author}
	 */
	protected void hasChild() {
		this.hasChild = true;
	}

	/**
	 * 设置请求的编码<br />
	 * 认为调用该方法时，proxyMsg一定不为null<br />
	 * 
	 * @author ${author}
	 * @param code 请求的编码
	 * @see com.tfzzh.core.view.socket.bean.BaseMessageBean#putProxyCode(int)
	 */
	public void putProxyCode(final int code) {
		if (!this.hasChild) {
			this.proxyMsg.setRequestCode(code);
		}
	}
}

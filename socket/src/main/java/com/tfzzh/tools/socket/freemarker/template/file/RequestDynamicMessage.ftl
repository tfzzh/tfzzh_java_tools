/**
 * @author ${author}
 */
package ${projectPath}${bean.scopesPackageName}.action.bean;

import java.io.IOException;
import java.util.HashMap;
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
<#if !bean.hasParent>
import com.tfzzh.core.view.socket.bean.BaseMessageBean;
</#if>
import com.tfzzh.core.view.socket.tools.TfzzhDataInputStream;
import com.tfzzh.core.view.socket.tools.TfzzhDataOutputStream;
import com.tfzzh.exception.ConfigurationException;
<#if !bean.inSameScopes>
import ${projectPath}${bean.parent.scopesPackageName}.action.bean.${bean.parent.proName}${msgSuffix};
</#if>

/**
 * ${bean.desc}消息
 * 
 * @author ${author}
 */
@ActionMessage(${bean.id?c})
public class ${bean.proName}${msgSuffix} extends <#if bean.hasParent>${bean.parent.proName}${msgSuffix}<#else>BaseMessageBean</#if> {

	/**
	 * @author ${author}
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 动态属性的数量
	 * 
	 * @author ${author}
	 */
	private final int s = ${bean.params?size};

	/**
	 * 消息集
	 * 
	 * @author ${author}
	 */
	private final Map<Short, Object> msgSet = new HashMap<Short, Object>(this.s);
<#list bean.params as param>

	/**
	 * ${param.desc}
	 * 
	 * @author ${author}
	 */
	private ${param.typeName} ${param.name};
</#list>

	/**
	 * @author ${author}
	 */
	public ${bean.proName}${msgSuffix}() {
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
		if (this.msgSet.containsKey((short) ${param.dynamicId})) {
			return this.${param.name};
		} else {
			throw new ConfigurationException("Attribute ${param.name} not Exists In ${bean.proName}${msgSuffix}.");
		}
	}

	/**
	 * 设置${param.desc}
	 * 
	 * @author ${author}
	 * @param ${param.name} ${param.desc}
	 */
	public void set${param.proName}(final ${param.typeName} ${param.name}) {
		this.${param.name} = ${param.name};
		this.msgSet.put((short) ${param.dynamicId}, new Object());
	}
</#list>

	/**
	 * @author ${author}
	 * @param buf 预从中读取消息的字节流对象
	 * @throws IOException 抛
	 * @see com.tfzzh.core.view.socket.bean.BaseMessageBean#write(com.tfzzh.core.view.socket.tools.TfzzhDataOutputStream)
	 */
	@Override
	public void write(final TfzzhDataOutputStream buf) throws IOException {
<#if bean.hasParent>
		super.write(buf);
</#if>
		buf.writeShort(this.fieldSet.size());
		// 无聊的优化，少的时候走循环遍历；多的之后，直接逐个判断
		if (this.msgSet.size() < (this.s * 2 / 3)) {
			for (final short s : this.msgSet.keySet()) {
				switch (s) {
<#list bean.params as param>
				case (short) ${param.dynamicId}: { // ${param.desc}
	<#if param.objType??>
		<#switch param.typeObjectName>
			<#case "List">
					buf.writeShort(s);
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
					buf.writeShort(s);
					this.${param.name}.write(buf);
			<#break>
		</#switch>
	<#else>
					buf.writeShort(s);
					buf.write${param.typeObjectName}(this.${param.name});
	</#if>
				}
</#list>
				}
			}
		} else {
<#list bean.params as param>
			if (this.msgSet.containsKey((short) ${param.dynamicId})) {
	<#if param.objType??>
		<#switch param.typeObjectName>
			<#case "List">
				buf.writeShort((short) ${param.dynamicId});
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
				buf.writeShort((short) ${param.dynamicId});
				this.${param.name}.write(buf);
			<#break>
		</#switch>
	<#else>
				buf.writeShort((short) ${param.dynamicId});
				buf.write${param.typeObjectName}(this.${param.name});
	</#if>
			}
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
<#if bean.hasParent>
		super.read(buf, problemMsg);
</#if>
		short s;
		int ind = buf.readShort();
		while (ind-- > 0) {
			switch (s = buf.readShort()) {
<#list bean.params as param>
			case (short) ${param.dynamicId}: { // ${param.desc}
	<#if param.objType??>
		<#switch param.typeObjectName>
			<#case "List">
				int size = buf.readInt();
				this.${param.name} = new LinkedList<${param.obj.proName}${msgSuffix}>();
				for (; size > 0; size--) {
					this.${param.name}.add(new ${param.obj.proName}${msgSuffix}(buf, problemMsg));
				}
				this.msgSet.put((short) ${param.dynamicId}, new Object());
			<#break>
			<#case "Bean">
				this.${param.name} = new ${param.obj.proName}${msgSuffix}(buf, problemMsg);
				this.msgSet.put((short) ${param.dynamicId}, new Object());
			<#break>
		</#switch>
	<#else>
				this.${param.name} = buf.read${param.typeObjectName}();
				this.msgSet.put((short) ${param.dynamicId}, new Object());
	</#if>
				continue;
			}
</#list>
			default:
				throw new ConfigurationException("Attribute code " + s + " not Exists In ${bean.proName}${msgSuffix}.");
			}
		}
<#if bean.needValidate>
		// 值都处理OK后开始进行验证
		this.validate(problemMsg);
</#if>
	}
<#if bean.needValidate>

	/**
	 * 进行数据验证
	 * 
	 * @author ${author}
	 * @param problemMsg 问题消息列表
	 */
	private void validate(final Map<String, String> problemMsg) {
<#list bean.params as param>
	<#if param.needValidate>
		if (this.msgSet.containsKey((short) ${param.dynamicId})) {
			// 验证${param.desc}
			try {
		<#list param.validates as vali>
				${vali.key.methodPath}(this.${param.name}<#list vali.value as va>, ${va}</#list>);
		</#list>
			} catch (final RequestValidateException e) {
				problemMsg.put("${param.name}", e.getMessage());
			}
		}
	</#if>
</#list>
	}
</#if>
}

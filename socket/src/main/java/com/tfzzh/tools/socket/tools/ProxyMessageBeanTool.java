/**
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午3:55:43
 */
package com.tfzzh.tools.socket.tools;

import java.util.List;
import java.util.Map;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.tools.XMLAnalyse;
import com.tfzzh.tools.socket.bean.MessageToolBean;

/**
 * 消息信息池工具
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午3:55:43
 */
public class ProxyMessageBeanTool {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午4:14:36
	 */
	private final String xmlRoot = "proxys";

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午4:14:34
	 * @param basePath 基础路径
	 * @param dtdPath dtd文件名
	 * @param xmlPerfix xml前缀
	 */
	public ProxyMessageBeanTool(final String basePath, final String dtdPath, final String xmlPerfix) {
		final XMLAnalyse xa = new XMLAnalyse(basePath + dtdPath, this.xmlRoot);
		xa.readXML(basePath, xmlPerfix);
		final Map<String, Object> eles = xa.getXmlElements();
		if ((eles.size() > 0) && eles.containsKey(this.xmlRoot)) {
			this.analyse(xa.getXmlElements());
		}
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午4:14:57
	 * @param xmlEles xml元素列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void analyse(final Map<String, Object> xmlEles) {
		final Map<String, Map> rootElem = (Map<String, Map>) ((Map<String, Object>) xmlEles.get(this.xmlRoot)).get(XMLAnalyse.XML_TAG_ELEMENT);
		final List<Map<String, Map>> datas = (List<Map<String, Map>>) rootElem.get("proxy");
		// 主属性列表
		Map<String, Object> mats;
		// 字段属性列表
		Map<String, Object> pats;
		// ------------
		int mainCode;
		String scopes;
		ScopeType scopesType;
		String cscopes;
		ScopeType clientScopesType;
		boolean toClient;
		String mainName;
		String mainDesc;
		List<Map<String, Object>> mainParams;
		//
		String paramName;
		String paramType;
		String paramObj;
		String validate;
		String valData;
		String paramDesc;
		MessageToolBean mainMsg;
		List<Map<String, Map>> messages;
		// 在服务端编码
		int code;
		// 在客户端编码
		int ccode;
		// 在服务端继承的父类
		int parent;
		// 在客户端继承的父类
		int cparent;
		String name;
		String cname;
		String desc;
		// 字段列表
		List<Map<String, Object>> params;
		// 服务端用的消息
		MessageToolBean serMsg;
		// 客户端用的消息
		MessageToolBean cliMsg;
		for (final Map<String, Map> d : datas) {
			mats = d.get(XMLAnalyse.XML_TAG_ATTRIBUTE);
			mainCode = Integer.parseInt(mats.get("id").toString());
			scopes = mats.get("scope").toString();
			scopesType = ScopeTool.getInstance().getScopeType(scopes);
			if (null == scopesType) {
				throw new ConfigurationException("Exist scopes " + scopes + " in Mssage " + mainCode);
			}
			cscopes = mats.get("cscope").toString();
			clientScopesType = ScopeTool.getInstance().getScopeType(cscopes);
			if (null == clientScopesType) {
				throw new ConfigurationException("Exist cscopes " + cscopes + " in Mssage " + mainCode);
			}
			toClient = Boolean.parseBoolean(mats.get("toClient").toString());
			mainName = ((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("proxy-name")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
			mainDesc = ((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("proxy-desc")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
			// 代理主类不涉及到针对客户端的不同对应
			mainMsg = new MessageToolBean(mainCode, 0, mainName, mainDesc, scopesType, MessageTypeEnum.Socket, true, toClient, null);
			mainParams = (List<Map<String, Object>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("proxy-param");
			// 遍历属性
			if (null != mainParams) {
				for (final Map<String, Object> p : mainParams) {
					pats = (Map<String, Object>) p.get(XMLAnalyse.XML_TAG_ATTRIBUTE);
					paramName = (String) pats.get("name");
					paramType = (String) pats.get("type");
					paramObj = (String) pats.get("obj");
					paramDesc = (String) pats.get("desc");
					mainMsg.putNormalParam(paramName, paramType, paramObj, null, null, paramDesc);
				}
			}
			RequestXMLTool.getInstance().putProxyRequestMessage(scopesType, mainMsg);
			if (null != (messages = (List<Map<String, Map>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("message"))) {
				// 遍历子消息
				for (final Map<String, Map> m : messages) {
					mats = m.get(XMLAnalyse.XML_TAG_ATTRIBUTE);
					code = Integer.parseInt(mats.get("id").toString());
					ccode = Integer.parseInt(mats.get("cid").toString());
					name = ((List<Map<String, String>>) m.get(XMLAnalyse.XML_TAG_ELEMENT).get("name")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
					cname = "client" + name.substring(0, 1).toUpperCase() + name.substring(1);
					desc = ((List<Map<String, String>>) m.get(XMLAnalyse.XML_TAG_ELEMENT).get("desc")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
					parent = Integer.parseInt(mats.get("parent").toString());
					cparent = Integer.parseInt(mats.get("cparent").toString());
					if (toClient) {
						// 是去到客户端，
						// 先客户端，客户端本身不涉及到与客户端的对应
						cliMsg = new MessageToolBean(ccode, cparent == 0 ? 0 : cparent, cname, desc, clientScopesType, MessageTypeEnum.Socket, false, false, null);
						// 服务端，暂定一定存在与客户端的不同对应
						serMsg = new MessageToolBean(code, parent == 0 ? mainCode : parent, name, desc, scopesType, MessageTypeEnum.Socket, false, false, cliMsg);
					} else {
						// 正常的，去到服务端
						// 服务端，暂定一定存在与客户端的不同对应
						serMsg = new MessageToolBean(code, parent == 0 ? mainCode : parent, name, desc, scopesType, MessageTypeEnum.Socket, false, false, null);
						// 先客户端，客户端本身不涉及到与客户端的对应
						cliMsg = new MessageToolBean(ccode, cparent == 0 ? 0 : cparent, cname, desc, clientScopesType, MessageTypeEnum.Socket, false, false, serMsg);
					}
					params = (List<Map<String, Object>>) m.get(XMLAnalyse.XML_TAG_ELEMENT).get("param");
					// 遍历属性
					if (null != params) {
						for (final Map<String, Object> p : params) {
							pats = (Map<String, Object>) p.get(XMLAnalyse.XML_TAG_ATTRIBUTE);
							paramName = (String) pats.get("name");
							paramType = (String) pats.get("type");
							paramObj = (String) pats.get("obj");
							validate = (String) pats.get("validate");
							valData = (String) pats.get("valData");
							paramDesc = (String) pats.get("desc");
							// 在服务端消息加入
							serMsg.putNormalParam(paramName, paramType, paramObj, validate, valData, paramDesc);
							// 在客户端消息加入
							cliMsg.putNormalParam(paramName, paramType, paramObj, validate, valData, paramDesc);
						}
					}
					RequestXMLTool.getInstance().putNormalRequestMessage(scopesType, serMsg);
					RequestXMLTool.getInstance().putNormalRequestMessage(clientScopesType, cliMsg);
				}
			}
		}
	}
}

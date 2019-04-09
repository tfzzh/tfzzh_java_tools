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
public class MessageBeanTool {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午4:14:36
	 */
	private final String xmlRoot = "messages";

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午4:14:34
	 * @param basePath 基础路径
	 * @param dtdPath dtd文件名
	 * @param xmlPerfix xml前缀
	 */
	public MessageBeanTool(final String basePath, final String dtdPath, final String xmlPerfix) {
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
		final List<Map<String, Map>> datas = (List<Map<String, Map>>) rootElem.get("message");
		// 主属性列表
		Map<String, Object> mats;
		int code;
		int parentId;
		ParamTypeEnum type;
		String scopes;
		ScopeType scopesType;
		// 参数列表
		String name;
		String desc;
		// 字段列表
		List<Map<String, Object>> params;
		// 字段属性列表
		Map<String, Object> pats;
		//
		String paramName;
		String paramType;
		String paramObj;
		// 2016-04-27
		String messageType;
		String validate;
		String valData;
		String dynamicId;
		String paramDesc;
		MessageToolBean msg;
		for (final Map<String, Map> d : datas) {
			mats = d.get(XMLAnalyse.XML_TAG_ATTRIBUTE);
			code = Integer.parseInt(mats.get("id").toString());
			type = ParamTypeEnum.getType(mats.get("type").toString());
			scopes = mats.get("scope").toString();
			scopesType = ScopeTool.getInstance().getScopeType(scopes);
			if (null == scopesType) {
				throw new ConfigurationException("Exist scopes " + scopes + " in Mssage " + code);
			}
			try {
				parentId = Integer.parseInt(mats.get("parent").toString());
			} catch (final RuntimeException e) {
				parentId = 0;
			}
			name = ((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("name")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
			desc = ((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("desc")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
			params = (List<Map<String, Object>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("param");
			messageType = mats.get("messageType").toString();
			// 暂认为一般的消息对象，不设计到特殊的客户端对应类
			msg = new MessageToolBean(code, parentId, name, desc, scopesType, MessageTypeEnum.getType(messageType), false, false, null);
			if (null != params) {
				for (final Map<String, Object> p : params) {
					pats = (Map<String, Object>) p.get(XMLAnalyse.XML_TAG_ATTRIBUTE);
					paramName = (String) pats.get("name");
					paramType = (String) pats.get("type");
					paramObj = (String) pats.get("obj");
					validate = (String) pats.get("validate");
					valData = (String) pats.get("valData");
					dynamicId = (String) pats.get("dyid");
					paramDesc = (String) pats.get("desc");
					switch (type) {
					case Normal:
						msg.putNormalParam(paramName, paramType, paramObj, validate, valData, paramDesc);
						break;
					case Dynamic:
						msg.putDynamicParam(paramName, paramType, paramObj, validate, valData, dynamicId, paramDesc);
						break;
					}
				}
			}
			switch (type) {
			case Normal:
				// 得到消息信息对象实例
				RequestXMLTool.getInstance().putNormalRequestMessage(scopesType, msg);
				break;
			case Dynamic:
				RequestXMLTool.getInstance().putDynamicRequestMessage(scopesType, msg);
				break;
			}
		}
	}
}

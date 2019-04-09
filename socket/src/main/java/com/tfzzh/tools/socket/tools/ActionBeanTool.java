/**
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午3:55:43
 */
package com.tfzzh.tools.socket.tools;

import java.util.List;
import java.util.Map;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.tools.XMLAnalyse;
import com.tfzzh.tools.socket.bean.LinkedToolBean;

/**
 * 请求消息池工具
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午3:55:43
 */
public class ActionBeanTool {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午4:14:36
	 */
	private final String xmlRoot = "links";

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午4:14:34
	 * @param basePath 基础路径
	 * @param dtdPath dtd文件名
	 * @param xmlPerfix xml前缀
	 */
	public ActionBeanTool(final String basePath, final String dtdPath, final String xmlPerfix) {
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
		final List<Map<String, Map>> datas = (List<Map<String, Map>>) rootElem.get("link");
		// 主属性列表
		Map<String, Object> mats;
		int code;
		boolean isKeep;
		String scope;
		ScopeType scopeType;
		String from;
		ScopeType fromType;
		// 参数列表
		String beanName;
		String methodName;
		String validate;
		String desc;
		int paramId;
		for (final Map<String, Map> d : datas) {
			mats = d.get(XMLAnalyse.XML_TAG_ATTRIBUTE);
			code = Integer.parseInt(mats.get("id").toString());
			isKeep = Boolean.parseBoolean(mats.get("keep").toString());
			scope = mats.get("scope").toString();
			scopeType = ScopeTool.getInstance().getScopeType(scope);
			if (null == scopeType) {
				throw new ConfigurationException("Not Exist scope " + scope + " in Action " + code);
			}
			from = mats.get("from").toString();
			fromType = ScopeTool.getInstance().getScopeType(from);
			if (null == fromType) {
				throw new ConfigurationException("Not Exist from " + from + " in Action " + code);
			}
			beanName = ((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("bean")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
			methodName = ((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("method")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
			{
				List<Map<String, String>> tmp = (List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("validate");
				if (null != tmp) {
					validate = tmp.get(0).get(XMLAnalyse.XML_TAG_CONTENT);
				} else {
					validate = null;
				}
			}
			desc = ((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("desc")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
			try {
				paramId = Integer.parseInt(((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("param")).get(0).get(XMLAnalyse.XML_TAG_CONTENT));
			} catch (final Exception e) {
				paramId = 0;
			}
			if (paramId == 0) {
				RequestXMLTool.getInstance().putLinked(scopeType, fromType, beanName, new LinkedToolBean(new int[] { code }, scopeType, fromType, methodName, validate, desc, null, null, isKeep, false));
			} else {
				// 得到请求对象实例
				RequestXMLTool.getInstance().putLinked(scopeType, fromType, beanName,
						new LinkedToolBean(new int[] { code }, scopeType, fromType, methodName, validate, desc, RequestXMLTool.getInstance().getRequestMessage(scopeType, paramId), null, isKeep, false));
			}
		}
	}
}

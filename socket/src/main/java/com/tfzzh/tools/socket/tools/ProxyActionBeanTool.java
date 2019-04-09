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
import com.tfzzh.tools.socket.bean.MessageToolBean;
import com.tfzzh.tools.socket.bean.ProxyRequestToolBean;

/**
 * 请求消息池工具
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午3:55:43
 */
public class ProxyActionBeanTool {

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
	public ProxyActionBeanTool(final String basePath, final String dtdPath, final String xmlPerfix) {
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
		Map<String, Object> pats;
		int code;
		String from;
		ScopeType fromType;
		String proxy;
		ScopeType proxyType;
		String target;
		ScopeType targetType;
		String pbeanName;
		String pmethodName;
		String pdesc;
		int pparam;
		ProxyRequestToolBean prtb;
		// Link参数列表
		Map<String, Object> lats;
		int linkId;
		String lmethodName;
		String lvalidate;
		String ldesc;
		String lparamS;
		int lparam;
		LinkedToolBean ltb;
		// LinkedToolBean proxyLinked;
		int[] ids;
		int ind;
		for (final Map<String, Map> d : datas) {
			pats = d.get(XMLAnalyse.XML_TAG_ATTRIBUTE);
			code = Integer.parseInt(pats.get("id").toString());
			from = pats.get("from").toString();
			fromType = ScopeTool.getInstance().getScopeType(from);
			if (null == fromType) {
				throw new ConfigurationException("Not Exist from " + from + " in Proxy " + code);
			}
			proxy = pats.get("proxy").toString();
			proxyType = ScopeTool.getInstance().getScopeType(proxy);
			if (null == proxyType) {
				throw new ConfigurationException("Not Exist proxy " + from + " in Proxy " + code);
			}
			target = pats.get("target").toString();
			targetType = ScopeTool.getInstance().getScopeType(target);
			if (null == targetType) {
				throw new ConfigurationException("Not Exist target " + from + " in Proxy " + code);
			}
			final List<Map<String, String>> tl = (List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("proxy-bean");
			if (null != tl) {
				pbeanName = ((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("proxy-bean")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
			} else {
				pbeanName = "";
			}
			pmethodName = ((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("proxy-method")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
			pdesc = ((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("proxy-desc")).get(0).get(XMLAnalyse.XML_TAG_CONTENT);
			pparam = Integer.parseInt(((List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("proxy-param")).get(0).get(XMLAnalyse.XML_TAG_CONTENT));
			prtb = RequestXMLTool.getInstance().putProxy(code, fromType, proxyType, targetType, pbeanName);
			ind = 0;
			final Object tmp = d.get(XMLAnalyse.XML_TAG_ELEMENT).get("link");
			if (null != tmp) {
				// 有限定连接的代理
				// 因为是一次性工作，就不考虑什么性能或什么的东西了，就先重复计算两次了
				ids = new int[((List<Map<String, Object>>) tmp).size()];
				for (final Map<String, Object> l : (List<Map<String, Object>>) tmp) {
					lats = (Map<String, Object>) l.get(XMLAnalyse.XML_TAG_ATTRIBUTE);
					linkId = Integer.parseInt(lats.get("id").toString());
					lmethodName = ((Map<String, List<Map<String, String>>>) (l.get(XMLAnalyse.XML_TAG_ELEMENT))).get("link-method").get(0).get(XMLAnalyse.XML_TAG_CONTENT);
					{
						List<Map<String, String>> tmpVal = (List<Map<String, String>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("link-validate");
						if (null != tmpVal) {
							lvalidate = tmpVal.get(0).get(XMLAnalyse.XML_TAG_CONTENT);
						} else {
							lvalidate = null;
						}
					}
					ldesc = ((Map<String, List<Map<String, String>>>) (l.get(XMLAnalyse.XML_TAG_ELEMENT))).get("link-desc").get(0).get(XMLAnalyse.XML_TAG_CONTENT);
					lparamS = ((Map<String, List<Map<String, String>>>) (l.get(XMLAnalyse.XML_TAG_ELEMENT))).get("link-param").get(0).get(XMLAnalyse.XML_TAG_CONTENT);
					if (null != lparamS) {
						lparam = Integer.parseInt(lparamS);
						final MessageToolBean mtb = RequestXMLTool.getInstance().getMessage(lparam);
						ltb = new LinkedToolBean(new int[] { linkId }, proxyType, fromType, lmethodName, lvalidate, ldesc, mtb, mtb.getCorrespondenceClient(), false, false);
					} else {
						ltb = new LinkedToolBean(new int[] { linkId }, proxyType, fromType, lmethodName, lvalidate, ldesc, null, null, false, false);
					}
					ids[ind++] = linkId;
					prtb.putLinked(ltb);
				}
				prtb.putProxyLinked(new LinkedToolBean(ids, targetType, proxyType, pmethodName, null, pdesc, RequestXMLTool.getInstance().getMessage(pparam), null, false, true));
			} else {
				// 范围方式的代理
				final List<Map<String, Object>> plm = (List<Map<String, Object>>) d.get(XMLAnalyse.XML_TAG_ELEMENT).get("proxy-link");
				if (null == plm) {
					throw new ConfigurationException("Not Exist proxy Element \"proxy-link\" or \"link\" in Proxy " + code);
				}
				lats = (Map<String, Object>) plm.get(0).get(XMLAnalyse.XML_TAG_ATTRIBUTE);
				final int minId = Integer.parseInt(lats.get("minId").toString());
				final int maxId = Integer.parseInt(lats.get("maxId").toString());
				prtb.putProxyLinked(new LinkedToolBean(minId, maxId, proxyType, fromType, pmethodName, null, pdesc, RequestXMLTool.getInstance().getMessage(pparam), null, false, true));
			}
			// 独立放入主连接
			// 不要增加 RequestXMLTool.getInstance().putLinked(proxyType, fromType, proxyLinked);
		}
		// 增加对代理连接的整理
		RequestXMLTool.getInstance().tidyProxy();
	}
}

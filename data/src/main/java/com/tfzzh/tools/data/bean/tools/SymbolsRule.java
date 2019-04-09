/**
 * @author Weijie Xu
 * @dateTime Sep 5, 2014 4:52:33 PM
 */
package com.tfzzh.tools.data.bean.tools;

import com.tfzzh.exception.ConfigurationException;

/**
 * 分隔符规则
 * 
 * @author Weijie Xu
 * @dateTime Sep 5, 2014 4:52:33 PM
 */
public class SymbolsRule {

	/**
	 * 类数据分隔符
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:52:33 PM
	 */
	private final String d;

	/**
	 * 字段数据分隔符
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:52:33 PM
	 */
	private final String f;

	/**
	 * 主键字段数据分隔符
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:53:13 PM
	 */
	private final String k;

	/**
	 * 键值分隔符
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:52:33 PM
	 */
	private final String v;

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:52:33 PM
	 * @param txt 字段内容
	 */
	public SymbolsRule(final String txt) {
		// 进行分隔符解析
		String d = "", f = "", v = "", k = "";
		char c;
		for (int ind = 0, s = txt.length(); ind < s; ind++) {
			switch (txt.charAt(ind)) {
			case 'd': { // 类数据分隔符
				if (d.length() > 0) {
					throw new ConfigurationException("Repeat content with d ! ");
				}
				if ('`' == txt.charAt(++ind)) {
					while ((c = txt.charAt(++ind)) != '`') {
						if (ind == s) {
							// 已经是最大值，但确实还未结束，错误情况
							throw new ConfigurationException("Error content format. d must the end of `!");
						}
						d += String.valueOf(c);
					}
					if (d.length() == 0) {
						throw new ConfigurationException("Error content format. have d must set Value!");
					}
				} else {
					throw new ConfigurationException("Error content format. behind d must `!");
				}
				break;
			}
			case 'f': { // 字段分隔符
				if (f.length() > 0) {
					throw new ConfigurationException("Repeat content with f ! ");
				}
				if ('`' == txt.charAt(++ind)) {
					while ((c = txt.charAt(++ind)) != '`') {
						if (ind == s) {
							// 已经是最大值，但确实还未结束，错误情况
							throw new ConfigurationException("Error content format. f must the end of `!");
						}
						f += String.valueOf(c);
					}
					if (f.length() == 0) {
						throw new ConfigurationException("Error content format. have f must set Value!");
					}
				} else {
					throw new ConfigurationException("Error content format. behind f must `!");
				}
				break;
			}
			case 'v': { // 键值分隔符
				if (v.length() > 0) {
					throw new ConfigurationException("Repeat content with v ! ");
				}
				if ('`' == txt.charAt(++ind)) {
					while ((c = txt.charAt(++ind)) != '`') {
						if (ind == s) {
							// 已经是最大值，但确实还未结束，错误情况
							throw new ConfigurationException("Error content format. v must the end of `!");
						}
						v += String.valueOf(c);
					}
					if (v.length() == 0) {
						throw new ConfigurationException("Error content format. have v must set Value!");
					}
				} else {
					throw new ConfigurationException("Error content format. behind v must `!");
				}
				break;
			}
			case 'k': { // 作为Map键的字段名
				if (k.length() > 0) {
					throw new ConfigurationException("Repeat content with k ! ");
				}
				if ('`' == txt.charAt(++ind)) {
					while ((c = txt.charAt(++ind)) != '`') {
						if (ind == s) {
							// 已经是最大值，但确实还未结束，错误情况
							throw new ConfigurationException("Error content format. k must the end of `!");
						}
						k += String.valueOf(c);
					}
					if (k.length() == 0) {
						throw new ConfigurationException("Error content format. have k must set Value!");
					}
				} else {
					throw new ConfigurationException("Error content format. behind k must `!");
				}
				break;
			}
			}
		}
		if (f.length() == 0) {
			throw new ConfigurationException("Error content format. f must Exist!");
		}
		if (v.length() == 0) {
			throw new ConfigurationException("Error content format. v must Exist!");
		}
		// 进行值设置
		if (d.length() == 0) {
			this.d = null;
		} else {
			this.d = d;
		}
		if (k.length() == 0) {
			this.k = null;
		} else {
			this.k = k;
		}
		this.f = f;
		this.v = v;
	}

	/**
	 * 得到类数据分隔符
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:52:33 PM
	 * @return the d
	 */
	public String getD() {
		return this.d;
	}

	/**
	 * 得到主键字段数据分隔符
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:55:07 PM
	 * @return the k
	 */
	public String getK() {
		return this.k;
	}

	/**
	 * 得到字段数据分隔符
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:52:33 PM
	 * @return the f
	 */
	public String getF() {
		return this.f;
	}

	/**
	 * 得到键值分隔符
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:52:33 PM
	 * @return the v
	 */
	public String getV() {
		return this.v;
	}
}

/**
 * @author Weijie Xu
 * @dateTime Aug 25, 2014 3:43:08 PM
 */
package com.tfzzh.tools.data.bean;

/**
 * 数据对象接口
 * 
 * @author Weijie Xu
 * @dateTime Aug 25, 2014 3:43:08 PM
 */
public interface DataBean {

	/**
	 * 得到对象的ID
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 10:01:39 AM
	 * @return 对象的ID
	 */
	Long getId();

	/**
	 * 得到项目用名
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 1:19:20 PM
	 * @return 项目用名
	 */
	String getProName();

	/**
	 * 得到对象后缀名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月4日 下午8:40:34
	 * @return 对象后缀名
	 */
	String getSuffix();

	/**
	 * 得到作为内部的对像的后缀名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月5日 下午4:08:11
	 * @return 作为内部的对像的后缀名
	 */
	String getInnerSuffix();

	/**
	 * 得到特殊字，在一些拼字命名中
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月5日 下午8:32:46
	 * @return 特殊字
	 */
	String getSpecialWord();

	/**
	 * 是否源数据
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 7:38:56 PM
	 * @return true，是；<br />
	 *         false，不是；<br />
	 */
	boolean isSourceData();
}

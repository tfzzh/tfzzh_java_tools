/**
 * @author Weijie Xu
 * @dateTime Sep 5, 2014 4:40:03 PM
 */
package com.tfzzh.tools.data.tools;

import com.tfzzh.exception.ConfigurationException;

/**
 * 解析用模板类型
 * 
 * @author Weijie Xu
 * @dateTime Sep 5, 2014 4:40:03 PM
 */
public enum AnalysisTemplateTypeEnum {

	/**
	 * 模板类型1<br />
	 * 解析出为一个对象，有字段分割符“f”，内容分隔符“v”内容分：字段名、值、值类型三部分<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:41:37 PM
	 */
	T1("t1") {

		@Override
		public String getTypeName(final String beanName, final String keyName) {
			return beanName;
		}

		/**
		 * 仅为对象
		 * 
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 1:08:19 PM
		 * @see com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum#isOnlyBean()
		 */
		@Override
		public boolean isOnlyBean() {
			return true;
		}

		/**
		 * 是可操作Bean
		 * 
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 4:12:45 PM
		 * @see com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum#isOperableBean()
		 */
		@Override
		public boolean isOperableBean() {
			return true;
		}
	},
	/**
	 * 模板类型2<br />
	 * 解析出为一个Map对象并嵌套一个Map，有数据间（Map）分割“d”，键值分割“k”，字段间分割“f”，值内容分割“v”<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:41:37 PM
	 */
	T2("t2") {

		@Override
		public String getTypeName(final String beanName, final String keyName) {
			return "Map<" + keyName + ", " + beanName + ">";
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 8:37:55 PM
		 * @see com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum#getTypeImplName(java.lang.String, java.lang.String, int)
		 */
		@Override
		public String getTypeImplName(final String beanName, final String keyName, final int type) {
			return ObjectImplTypeTool.getMapImpl(type) + "<" + keyName + ", " + beanName + ">";
		}
	},
	/**
	 * 模板类型3<br />
	 * 解析出为一个Map对象嵌套一个对象，有数据间（Bean）分割“d”，字段Id与内容分割“k”，字段间分割“f”，键值分割“v”<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:41:38 PM
	 */
	T3("t3") {

		@Override
		public String getTypeName(final String beanName, final String keyName) {
			return "Map<" + keyName + ", " + beanName + ">";
		}

		/**
		 * 是可操作Bean
		 * 
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 4:12:45 PM
		 * @see com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum#isOperableBean()
		 */
		@Override
		public boolean isOperableBean() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 8:37:55 PM
		 * @see com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum#getTypeImplName(java.lang.String, java.lang.String, int)
		 */
		@Override
		public String getTypeImplName(final String beanName, final String keyName, final int type) {
			return ObjectImplTypeTool.getMapImpl(type) + "<" + keyName + ", " + beanName + ">";
		}
	},
	/**
	 * 模板类型4<br />
	 * 解析出为一个Map，键值对关系，字段分隔符“f”，键值分隔符“v”<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:41:38 PM
	 */
	T4("t4") {

		@Override
		public String getTypeName(final String beanName, final String keyName) {
			return "Map<" + keyName + ", " + beanName + ">";
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 8:37:55 PM
		 * @see com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum#getTypeImplName(java.lang.String, java.lang.String, int)
		 */
		@Override
		public String getTypeImplName(final String beanName, final String keyName, final int type) {
			return ObjectImplTypeTool.getMapImpl(type) + "<" + keyName + ", " + beanName + ">";
		}
	},
	/**
	 * 模板类型5<br />
	 * 解析出为一个任意结构的Map对象（应该是为应付当前项目历史而临时存在的东西）<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 4:25:25 PM
	 */
	T5("t5") {

		@Override
		public String getTypeName(final String beanName, final String keyName) {
			return "Map<Object, Object>";
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 9, 2014 8:27:42 PM
		 * @see com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum#isRelatedStream()
		 */
		@Override
		public boolean isRelatedStream() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 9:12:07 PM
		 * @see com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum#getTypeImplName(java.lang.String, java.lang.String, int)
		 */
		@Override
		public String getTypeImplName(final String beanName, final String keyName, final int type) {
			return ObjectImplTypeTool.getMapImpl(type) + "<Object, Object>";
		}
	},
	/**
	 * 模板类型6<br />
	 * 解析出为一个任意结构的List对象（应该是为应付当前项目历史而临时存在的东西）<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月23日 下午5:15:54
	 */
	T6("t6") {

		@Override
		public String getTypeName(final String beanName, final String keyName) {
			return "List<Object>";
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 9, 2014 8:27:42 PM
		 * @see com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum#isRelatedStream()
		 */
		@Override
		public boolean isRelatedStream() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 9:12:07 PM
		 * @see com.tfzzh.tools.data.tools.AnalysisTemplateTypeEnum#getTypeImplName(java.lang.String, java.lang.String, int)
		 */
		@Override
		public String getTypeImplName(final String beanName, final String keyName, final int type) {
			return ObjectImplTypeTool.getListImpl(type) + "<Object>";
		}
	};

	/**
	 * 类型名
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:41:34 PM
	 */
	private final String val;

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:41:31 PM
	 * @param val 类型名
	 */
	AnalysisTemplateTypeEnum(final String val) {
		this.val = val;
	}

	/**
	 * 得到类型名
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 3:43:31 PM
	 * @param beanName 对象的名字
	 * @param keyName 相关Key的名字，针对Map类属性
	 * @return 组合好的类型名
	 */
	public abstract String getTypeName(final String beanName, final String keyName);

	/**
	 * 得到类型的实现名
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 10, 2014 8:33:19 PM
	 * @param beanName 对象的名字
	 * @param keyName 相关Key的名字，针对Map类属性
	 * @param type 实现类型ID
	 * @return 组合好的类型的实现名
	 */
	public String getTypeImplName(final String beanName, final String keyName, final int type) {
		return this.getTypeName(beanName, keyName);
	}

	/**
	 * 是否流相关模版
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 8:25:54 PM
	 * @return true，是流相关；<br />
	 *         false，不是流相关；<br />
	 */
	public boolean isRelatedStream() {
		return false;
	}

	/**
	 * 是否仅有对象，而不存在Map或List等列表形式的数据
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 10, 2014 1:07:11 PM
	 * @return true，只有对象；<br />
	 *         false，包括列表等相关；<br />
	 */
	public boolean isOnlyBean() {
		return false;
	}

	/**
	 * 是否存在可操作的Bean对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 10, 2014 4:11:59 PM
	 * @return true，存在；<br />
	 *         false，不存在；<br />
	 */
	public boolean isOperableBean() {
		return false;
	}

	/**
	 * 得到类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 4:43:31 PM
	 * @param type 目标类型名
	 * @return 对应的类型
	 */
	public static AnalysisTemplateTypeEnum getType(final String type) {
		for (final AnalysisTemplateTypeEnum e : AnalysisTemplateTypeEnum.values()) {
			if (e.val.equals(type)) {
				return e;
			}
		}
		throw new ConfigurationException("Error Analysis Template type:" + type);
	}
}

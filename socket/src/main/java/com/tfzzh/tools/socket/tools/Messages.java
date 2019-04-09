/**
 * @author Xu Weijie
 * @dateTime 2012-4-26 下午2:21:34
 */
package com.tfzzh.tools.socket.tools;

import java.io.UnsupportedEncodingException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.tfzzh.tools.Constants;
import com.tfzzh.tools.FileTools;

/**
 * @author Xu Weijie
 * @dateTime 2012-4-26 下午2:21:34
 */
public class Messages {

	/**
	 * @author TFZZH
	 */
	private static final String BUNDLE_NAME = "socket_template_message";

	/**
	 * @author TFZZH
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = FileTools.getResourceBundle(Messages.BUNDLE_NAME);

	/**
	 * @author TFZZH
	 */
	private Messages() {
	}

	/**
	 * @author TFZZH
	 * @param key 键
	 * @return 对应值
	 */
	public static String getString(final String key) {
		try {
			return new String(Messages.RESOURCE_BUNDLE.getString(key).getBytes("ISO-8859-1"), null == Constants.SYSTEM_CODE ? "UTF-8" : Constants.SYSTEM_CODE);
		} catch (final MissingResourceException e) {
			return '!' + key + '!';
		} catch (final UnsupportedEncodingException e) {
			return '!' + key + '!';
		}
	}
}

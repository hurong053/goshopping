package com.shopping.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * Fastjson字符串与联系人对象自动转换
 */
public class FastjsonUtil
{

	/**
	 * 将ContactPo转换为fastJson字符串
	 * 
	 * @param ContactPo
	 * @return
	 */
	public static <T extends Object> String convertContact2FastJsonstr(T po)
	{
		String jsonstr = com.alibaba.fastjson.JSON.toJSONString(po);
		return jsonstr;

	}

	/**
	 * 将FastJSon字符串转换为ContactPo json字符串容错
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static <T extends Object> T convertFastJson2Contact(String jsonstr,
			Class<T> cls)
	{
		if (StringUtils.isEmpty(jsonstr))
			return null;
		/*return com.alibaba.fastjson.JSONObject.parseObject(jsonstr,
				ContactPo.class);
		*/
		return com.alibaba.fastjson.JSON.parseObject(jsonstr, cls);
	}

}

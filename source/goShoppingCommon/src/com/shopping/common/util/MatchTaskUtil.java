package com.shopping.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

public class MatchTaskUtil
{

	static Logger logger = Logger.getLogger(MatchTaskUtil.class);

	public static <E> boolean isIterable(List<E> list)
	{
		if (list != null && list.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static <k, v> boolean isIterable(Map<k, v> map)
	{
		if (map != null && map.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static <v> boolean isIterable(Set<v> set)
	{
		if (set != null && set.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 向一个map中关键字key添加list
	 * 
	 * @param map
	 * @param list
	 * @param key
	 */
	public static <E> void putListToMap(Map<String, List<E>> map, List<E> list,
			String key)
	{
		if (isIterable(list))
		{
			List<E> origList = map.get(key);
			if (!isIterable(origList))
			{
				origList = new ArrayList<E>();
				origList.addAll(list);
				map.put(key, origList);
			}
			else
			{
				origList.addAll(list);
			}
		}
	}

	/**
	 * 在json串msg中获取key对应的value
	 * 
	 * @param key
	 * @param msg
	 * @return
	 */
	public static String getKeyValue(String key, String userMsg,
			char[] userMsgChar)
	{
		int index = userMsg.indexOf(key);
		if (-1 != index)
		{
			int[] quoteIndex = new int[3];
			int quoteCount = 0;
			for (int mi = index + key.length(); mi < userMsgChar.length
					&& quoteCount < 3; mi++)
			{
				// 获取前三个双引号位置
				if (userMsgChar[mi] == '"')
				{
					quoteIndex[quoteCount] = mi;
					quoteCount++;
				}
			}
			String mail = userMsg.substring(quoteIndex[1] + 1, quoteIndex[2]);
			if (StringUtils.isNotBlank(mail))
			{
				return mail;
			}
		}
		return null;
	}

	/**
	 * 获取线程数
	 * 
	 * @param totalNum
	 *            需要处理的数据总量
	 * @param numPerThread
	 *            每个线程处理的数据量
	 * @return
	 */
	public static int getThreadNum(int totalNum, int numPerThread)
	{
		if (totalNum <= numPerThread)
		{
			return 1;
		}
		int addNum = totalNum % numPerThread > 0 ? 1 : 0;
		return totalNum / numPerThread + addNum;
	}

	/**
	 * 获取每个线程的均数据量
	 * 
	 * @param totalNum
	 *            数据总数
	 * @param totalThread
	 *            总线程数
	 * @return
	 */
	public static int getAvgDataPerThread(int totalNum, int totalThread)
	{
		int numPerThread = totalNum / totalThread
				+ (totalNum % totalThread > 0 ? 1 : 0);
		return numPerThread;
	}

	/**
	 * 每个线程起始及结束序号，将数据均分到各个线程中
	 * 
	 * @param threadNum
	 *            线程序号
	 * @param totalNum
	 *            数据总量
	 * @param totalThread
	 *            线程总量
	 * @return
	 */
	public static int[] getStartEndIndex(int threadNum, int totalNum,
			int numPerThread)
	{
		int start = threadNum * numPerThread;
		int end = (start + numPerThread) > totalNum ? totalNum
				: (start + numPerThread);
		int se[] = { start, end };
		return se;
	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException, InterruptedException
	{

		/*ObjectFactory.getInstance().setBean("httpService",hs);
		BatchSearchAccountPo bsa = new BatchSearchAccountPo();
		bsa.setBatchQueryUrl("http://accountapi.baikuceshi.com:81/BaikuUserCenter/ucService?service=batchSearchAccount&params=");
		bsa.setParams("{\"appid\":\"FS\",\"uid\":\"${uid}\",\"account\":[${account}]}");
		ObjectFactory.getInstance().setBean("batchSearchAccountBean",bsa);
		try {
		//			SearchAccountResponse sr = getUsrId("http://accountapi.baikuvoip.com/BaikuUserCenter/ucService?service=searchAccount&params=%7B%22appid%22%3A%22FS%22%2C%22uid%22%3A%2213000000001%22%2C%22account%22%3A%2213000000831%22%7D", null, null);
			Set<String> contacts = new HashSet<String>();
			contacts.add("15800000001");
			contacts.add("15800000002");
			BatchSearchAccountResponse sr = getUsrIdBatch("D13MF5606130000071e22575ea60e1d3", contacts, null, null);
			System.out.println(sr);
		}catch (Exception e) {
			e.printStackTrace();
		}*/
		/*
		for(int i = 812; i < 4999; i++){
			String number = ""+(15800000000l + i);
			String res = hs.doGetRequest("http://accountapi.baikuvoip.com/ucService?service=createAccount&" +
					"params=%7B%22appid%22:%22voip%22,%22mobile%22:%22"+number+"%22,%22password%22:%22f471e280b9f3f190e6100fe0695201dc%22%7D");
			System.out.println(res);
			Thread.sleep(1000);
		}
		
		System.out.println(isMobile(""+13000000000l));
		*/
		// int totalDataSize = 31;
		// int threadNum = getThreadNum(totalDataSize,30);
		// System.out.println("total thread:" +threadNum);
		// int dataSziePerThread = getAvgDataPerThread(totalDataSize,
		// threadNum);
		// for(int i = 0; i < threadNum; i++){
		// //子列表的在原列表中最后一个index(exclusive)
		// int[] fromToIndex = getStartEndIndex(i, totalDataSize,
		// dataSziePerThread);
		// System.out.println(fromToIndex[0]+":"+fromToIndex[1]);
		// }
		/*String contactinfo = "[13301116520, 01059359457, 01085900219]#[siqiang.qin@secneo.com]";
		List<String> conlist = getPhonesByContactInfo(contactinfo);
		System.out.println(conlist);*/
		// String msg =
		// "{\"areaCode\":\"\",\"city\":\"\",\"mail\":\"\",\"mobile\":\"13000000331\",\"modifydate\":\"1366617172229\",\"phone\":\"\",\"uid\":\"D13MF5252130000023fe3a04902af7cf\"}";
		// System.out.println("mail:"+getKeyValue("mail", msg,
		// msg.toCharArray()));
		// System.out.println("mobile:"+getKeyValue("mobile", msg,
		// msg.toCharArray()));
		// System.out.println("phone:"+getKeyValue("phone", msg,
		// msg.toCharArray()));
		//
		// System.out.println("isphone:"+isPhone("8004123456"));
		// System.out.println("==="+getMailsByContactInfo("[13910178665, 051851159807, 18610107055]#[]"));
		// System.out.println("hasareacode:"+hasAreacode("05511234567"));
		/*String con = "[11111, 12343215678, 01094738394, 0398309388]#[ssli@sina.com, lri@google.com, uuu@163.com]";
		System.out.println(getPhonesByContactInfo(con));
		System.out.println(getMailsByContactInfo(con));*/

		/*
		System.out.println(res);
		Pattern jsonpattern = Pattern.compile(UsrMsgMatchRegex.USR_MSG_REG.getValue(),Pattern.CASE_INSENSITIVE);
		Matcher jsonmatcher = jsonpattern.matcher(res);
		
		String userMsg = "";
		while(jsonmatcher.find()){ 
			//有更新用户数据
			userMsg = jsonmatcher.group();
			System.out.println(userMsg);
		}*/
		// "http://192.168.3.13:8080/BaikuUserCenter/ucService?service=searchAccount&params={appid:FS,mail:wangxm@channelsoft.com}"
		// "http://192.168.3.13:8080/BaikuUserCenter/ucService?service=searchAccount&params={appid:FS,mobile:13521044553}";
		/*String url = "http://58.83.210.69/BaikuUserCenter/ucService?service=searchAccount";
		FullMatchTask ft = new FullMatchTask();
		ft.setQueryUserUrl(url);
		ObjectFactory.getInstance().setBean("contactsMatchTask", ft);
		ObjectFactory.getInstance().setBean("httpService", hs);
		String urls = getSearchAccountUrl("L12OB21591352104f14ff240783cc465", "01088822201");
		
		AtomicLong totalQuery = new AtomicLong(0);
		AtomicLong httpTotalCost = new AtomicLong(0);
		try {
			System.out.println(getUsrId(urls, totalQuery, httpTotalCost));
		}catch (Exception e) {
			e.printStackTrace();
		}*/

		/*ContactPo.Builder cpb = ContactPo.newBuilder();
		cpb.setContactId("idc");
		cpb.setTimestamp(11l);
		ContactPo cp = cpb.build();
		ContactPo.Builder cpb2 = cp.toBuilder();
		cpb2.setTimestamp(22l);
		ContactPo cp2 = cpb2.build();
		System.out.println(cp2);
		byte[] cp2byte = cp2.toByteArray();
		System.out.println("==========");
		try {
			ContactPo cp3 = ContactPo.parseFrom(cp2byte);
			System.out.println(cp3);
			System.out.println("==========");
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}*/

	}
}

//package com.boot.demo.util;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.time.DateFormatUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;
//
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//import java.util.StringTokenizer;
//
//public class DateUtil {
//
//    public final static String FORMAT_YMD         =   "yyyyMMdd";
//
//	private static String sysChar;// 日期分隔符
//	static {
//		sysChar = "-";
//	}
//
//	static final Log log = LogFactory.getLog(DateUtil.class);
//
//	public static synchronized long getCurrentTime() {
//		return System.currentTimeMillis();
//	}
//
//	public static String getDayAdd(String date, int number) {
//		String format = "yyyy-MM-dd";
//		DateFormat format2 = new SimpleDateFormat();
//		try {
//			Date date1 = format2.parse(date);
//			return getDayAdd(date1, number, format);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return date;
//	}
//
//   public static String getDayAdd2(String date, int number) {
//        String format = "yyyy-MM-dd";
//        DateFormat format2 = new SimpleDateFormat(format);
//        try {
//            Date date1 = format2.parse(date);
//            return getDayAdd(date1, number, format);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
//
//	public int dateCompare(String date1, String date2) {
//		return 0;
//	}
//
//	public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.MINUTE, 10);
//		System.out.println(isBeforeXMinute(calendar.getTime(), 5));
//	}
//
//	public static boolean isBeforeXMinute(Date date, int xMinute) {
//		// Calendar calendar = Calendar.getInstance();
//		// calendar.setTime(date);
//		// Calendar now = Calendar.getInstance();
//		// return now.get(Calendar.MINUTE) - calendar.get(Calendar.MINUTE) >
//		// xMinute;
//		return (new Date().getTime() - date.getTime()) > (xMinute * 60 * 1000);
//
//	}
//
//	public static String getDayAdd(Date date, int number, String format) {
//		DateFormat format2 = new SimpleDateFormat(format);
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.DATE, number);
//		return format2.format(calendar.getTime());
//	}
//
//
//	/**
//	 * @Description 获取传入日期 number 后的日期，格式为 yyyy-MM-dd 00:00:00
//	 * @see 例子： <br>
//	 * date = 2017-07-28 10:40:58 <br>
//	 * number = -2 <br>
//	 * return = 2017-07-26 00:00:00 <br>
//	 * @param date
//	 *            日期
//	 * @param number
//	 *            相差的天数
//	 * @return
//	 */
//
//	public static String getDayAddWithFormat(Date date, int number)
//	{
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.DATE, number);
//
//		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
//		return formater.format(calendar.getTime());
//	}
//
//	public static String getSecondsAdd(String time, int seconds) {
//		try {
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date date = dateFormat.parse(time);
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(date);
//			calendar.add(Calendar.SECOND, seconds);
//			time = dateFormat.format(calendar.getTime());
//		} catch (ParseException e) {
//			e.printStackTrace();
//			log.error(e);
//		}
//		return time;
//	}
//
//	public static String getLastMonth(Date date) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.MONTH, -1);
//		return DateFormatUtils.format(calendar, "yyyy-MM");
//	}
//
//	public static String getDate() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		return sdf.format(new Date());
//	}
//
//	public static String getDateMMDD() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		return sdf.format(new Date());
//	}
//
//	public static String getDateMMDDMMSS() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//
//		return sdf.format(new Date());
//	}
//
//	public static int comparaDate(int first, Date date) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		int two = Integer.valueOf(sdf.format(date));
//		if (first > two) {
//			return 1;
//		}
//		if (first == two) {
//			return 0;
//		}
//		return -1;
//	}
//
//	/**
//	 * 日期转换成字符串
//	 *
//	 * @param date
//	 * @return str
//	 */
//	public static String dateToStr(Date date) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String str = format.format(date);
//		return str;
//	}
//
//	/**
//	 * 字符串转换成日期
//	 *
//	 * @param str
//	 * @return date
//	 */
//	public static Date strToDate(String str) throws Exception {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = format.parse(str);
//		return date;
//	}
//
//	public static Date strToDate(String str, String reg) throws Exception {
//		SimpleDateFormat format = new SimpleDateFormat(reg);
//		Date date = format.parse(str);
//		return date;
//	}
//
//	public static Timestamp getStatBeginDate(Timestamp workDate) {
//		return getFirstDayInMonth(workDate);
//	}
//
//	public static Timestamp getStatEndDate(Timestamp workDate) {
//		Timestamp endDate = getLastDayInMonth(workDate);
//		if (endDate.after(getCurrTime())) {
//			return getCurrTime();
//		} else {
//			return getLastDayInMonth(workDate);
//		}
//	}
//
//	public static Timestamp getFirstDayInMonth(Timestamp currDate) {
//		String yyyy = getYYYY(currDate);
//		String mm = getMM(currDate);
//		return getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 1);
//	}
//
//	public static Timestamp getLastDayInMonth(Timestamp currDate) {
//		String yyyy = getYYYY(currDate);
//		String mm = getMM(currDate);
//		Timestamp t = getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 31);
//		if (Integer.parseInt(getMM(currDate)) != Integer.parseInt(getMM(t))) {
//			t = getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 30);
//			if (Integer.parseInt(getMM(currDate)) != Integer.parseInt(getMM(t))) {
//				t = getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 29);
//				if (Integer.parseInt(getMM(currDate)) != Integer.parseInt(getMM(t))) {
//					t = getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 28);
//					if (Integer.parseInt(getMM(currDate)) != Integer.parseInt(getMM(t))) {
//						t = getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 27);
//					}
//				}
//			}
//		}
//		return t;
//	}
//
//	/** **********************取特定时间函数*********************** */
//	public static Timestamp getSomeDate(int space) {// 根据时间间隔来取得某个时间,例如：参数为1时，得到明天的日期，参数为30时，得到一个月后的日期，参数为0时，得到今天的日期，参数为-1时，得到昨天的日期，参数为-30时，得到一个月前的日期，
//		return getSomeDate(getCurrTime(), space);
//	}
//
//	public static Timestamp getSomeDate(Timestamp t, int space) {// 比上面的方法多一个日期参数，上面方法默认为当天，所以没此参数
//		Date someDate = (Date) t;
//		int sign = space < 0 ? -1 : 1;
//		space = space < 0 ? -space : space;
//		int s = space / 10;
//		int y = space % 10;
//		for (int i = 0; i < s; i++) {
//			someDate = new Date(someDate.getTime() + 3600 * 24 * 1000 * 10 * sign);
//		}
//		someDate = new Date(someDate.getTime() + 3600 * 24 * 1000 * y * sign);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.getDefault());
//		int someDateYear = Integer.parseInt(formatter.format(someDate));// 求一个月前是什么年
//		formatter = new SimpleDateFormat("M", Locale.getDefault());
//		int someDateMonth = Integer.parseInt(formatter.format(someDate));// 求一个月前是几月
//		formatter = new SimpleDateFormat("dd", Locale.getDefault());
//		int someDateDay = Integer.parseInt(formatter.format(someDate));// 求一个月前是几号
//		return getTimestamp(someDateYear, someDateMonth, someDateDay);
//	}
//
//	public static String getYYYY(Timestamp t) {// 取特定时间的年份，比如2003年
//		if (isEmptyTime(t))
//			return "";
//		String yyyy = getFM("yyyy", t);
//		yyyy = "0000" + yyyy;
//		return yyyy.substring(yyyy.length() - 4);
//	}
//
//	public static String getYYYY(String date) {// 取特定日期字符串(如2003-12-11)的年份
//		StringTokenizer st = new StringTokenizer(date, "-/");
//		return st.nextToken();
//	}
//
//	public static String getMM(Timestamp t) {// 取特定时间的月份，比如4月
//		if (isEmptyTime(t))
//			return "";
//		String mm = getFM("M", t);
//		mm = "00" + mm;
//		return mm.substring(mm.length() - 2);
//	}
//
//	public static String getMM(String date) {// 取特定日期字符串(如2003-12-11)的月份
//		StringTokenizer st = new StringTokenizer(date, "-/");
//		st.nextToken();
//		String mm = st.nextToken();
//		if (mm.length() == 1)
//			mm = "0" + mm;
//		return mm;
//	}
//
//	public static String getDD(Timestamp t) {// 取特定时间的日，比如3号
//		if (isEmptyTime(t))
//			return "";
//		String dd = getFM("dd", t);
//		dd = "00" + dd;
//		return dd.substring(dd.length() - 2);
//	}
//
//	public static String getDD(String date) {// 取特定日期字符串(如2003-12-11)的号数
//		StringTokenizer st = new StringTokenizer(date, "-/");
//		st.nextToken();
//		st.nextToken();
//		String dd = st.nextToken();
//		if (dd.length() == 1)
//			dd = "0" + dd;
//		return dd;
//	}
//
//	public static String getYYYYMM(Timestamp t) {// 取特定时间的年月份，比如2003-04
//		if (isEmptyTime(t))
//			return "";
//		return getYYYY(t) + sysChar + getMM(t);
//	}
//
//	public static String getYYYYMMDD(Timestamp t) {// 取特定时间的年月日，比如2003-04-03
//		if (isEmptyTime(t))
//			return "";
//		return getYYYYMM(t) + sysChar + getDD(t);
//	}
//
//	public static String getHH(Timestamp t) {
//		if (isEmptyTime(t))
//			return "";
//		String hh = getFM("H", t);
//		hh = "00" + hh;
//		return hh.substring(hh.length() - 2);
//	}
//
//	public static String getMI(Timestamp t) {
//		if (isEmptyTime(t))
//			return "";
//		String mi = getFM("m", t);
//		mi = "00" + mi;
//		return mi.substring(mi.length() - 2);
//	}
//
//	public static String getSS(Timestamp t) {
//		if (isEmptyTime(t))
//			return "";
//		String ss = getFM("s", t);
//		ss = "00" + ss;
//		return ss.substring(ss.length() - 2);
//	}
//
//	public static String getHHMISS(Timestamp t) {// 取特定时间的时分秒，比如12：45：30
//		if (isEmptyTime(t))
//			return "";
//		return getHH(t) + ":" + getMI(t) + ":" + getSS(t);
//	}
//
//	public static String getYYYYMMDDHHMISS(Timestamp t) {// 取特定时间的年月日时分秒，比如2003-04-03
//		// 12:45:30
//		if (isEmptyTime(t))
//			return "";
//		return getYYYYMMDD(t) + " " + getHHMISS(t);
//	}
//
//	public static Timestamp getTimestamp(int year, int month, int day, int hour, int minute, int second) {// 通过代入年月日时分秒构造Timestamp时间对象
//		Calendar cal = Calendar.getInstance(Locale.getDefault());
//		cal.set(year, month - 1, day, hour, minute, second);
//		return new Timestamp(cal.getTime().getTime());
//	}
//
//	public static Timestamp getTimestamp(int year, int month, int day) {// 通过代入年月日构造Timestamp时间对象
//		return getTimestamp(year, month, day, 0, 0, 0);
//	}
//
//	public static Timestamp getTimestamp(String date) {
//		return getTimestamp(Integer.parseInt(getYYYY(date)), Integer.parseInt(getMM(date)),
//				Integer.parseInt(getDD(date)), 0, 0, 0);
//	}
//
//	public static String DateToString(Timestamp t) {
//		if (t == null) {
//			return "1970" + sysChar + "01" + sysChar + "01" + " " + "00" + ":" + "00" + ":" + "00";
//		} else {
//			return getYYYYMMDDHHMISS(t);
//		}
//	}
//
//	/** **********************取当前时间函数*********************** */
//	public static Timestamp getCurrTime() {
//		return new Timestamp(System.currentTimeMillis());
//	}
//
//	public static String getYYYY() {// 取当前年份，比如2003年
//		return getYYYY(getCurrTime());
//	}
//
//	public static String getMM() {// 取当前月份，比如4月
//		return getMM(getCurrTime());
//	}
//
//	public static String getDD() {// 取当前日，比如03号
//		return getDD(getCurrTime());
//	}
//
//	public static String getYYYYMM() {// 取当前年月份，比如2003-04
//		return getYYYYMM(getCurrTime());
//	}
//
//	public static String getYYYYMMDD() {// 取当前年月日，比如2003-04-03
//		return getYYYYMMDD(getCurrTime());
//	}
//
//	public static String getHH() {
//		return getHH(getCurrTime());
//	}
//
//	public static String getMI() {
//		return getMI(getCurrTime());
//	}
//
//	public static String getSS() {
//		return getSS(getCurrTime());
//	}
//
//	public static String getHHMISS() {// 取当前时分秒，比如12：45：30
//		return getHHMISS(getCurrTime());
//	}
//
//	public static String getYYYYMMDDHHMISS() {// 取当前年月日时分秒，比如2003-04-03
//		// 12:45:30
//		return getYYYYMMDDHHMISS(getCurrTime());
//	}
//
//	public static boolean isEmptyTime(Timestamp t) {
//		return getTimestamp(1970, 01, 01, 00, 00, 00).getTime() == t.getTime();
//	}
//
//	/** **********************其它方法*********************** */
//	public static boolean isDate(String s) {// 检查日期格式是否正确
//		StringTokenizer st = new StringTokenizer(s, "-");
//		try {
//			if (st.hasMoreElements()) {
//				String strYear = (String) st.nextElement();
//				if (strYear.length() == 4) {
//					int year = Integer.parseInt(strYear);
//					if (year >= 1970 && year <= 2470) {
//						if (st.hasMoreElements()) {
//							String strMonth = (String) st.nextElement();
//							if (strMonth.length() == 1 || strMonth.length() == 2) {
//								int month = Integer.parseInt(strMonth);
//								if (month > 0 && month <= 12) {
//									if (st.hasMoreElements()) {
//										String strDay = (String) st.nextElement();
//										if (strDay.length() == 1 || strDay.length() == 2) {
//											int day = Integer.parseInt(strDay);
//											if (day > 0 && day <= 31) {
//												if (!st.hasMoreElements()) {
//													return true;
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//		}
//		return false;
//	}
//
//	public static boolean isTime(String s) {// 检查时间格式是否正确
//		StringTokenizer st = new StringTokenizer(s, ":");
//		try {
//			if (st.hasMoreElements()) {
//				String strHour = (String) st.nextElement();
//				if (strHour.length() == 2) {
//					int hour = Integer.parseInt(strHour);
//					if (hour >= 0 && hour < 24) {
//						if (st.hasMoreElements()) {
//							String strMinute = (String) st.nextElement();
//							if (strMinute.length() == 2) {
//								int minute = Integer.parseInt(strMinute);
//								if (minute >= 0 && minute < 60) {
//									if (st.hasMoreElements()) {
//										String strSecond = (String) st.nextElement();
//										if (strSecond.length() == 2) {
//											int second = Integer.parseInt(strSecond);
//											if (second >= 0 && second < 60) {
//												if (!st.hasMoreElements()) {
//													return true;
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//		}
//		return false;
//	}
//
//	public static Timestamp getLastMonthFirstDay() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Integer.parseInt(DateUtil.getYYYY()), Integer.parseInt(DateUtil.getMM()), 1, 0, 0, 0);
//		calendar.add(Calendar.MONTH, -2);
//		return new Timestamp(calendar.getTime().getTime());
//	}
//
//	public static Timestamp getCurrMonthFirstDay() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Integer.parseInt(DateUtil.getYYYY()), Integer.parseInt(DateUtil.getMM()), 1, 0, 0, 0);
//		calendar.add(Calendar.MONTH, -1);
//		return new Timestamp(calendar.getTime().getTime());
//	}
//
//	public static Timestamp getNextMonthFirstDay() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Integer.parseInt(DateUtil.getYYYY()), Integer.parseInt(DateUtil.getMM()), 1, 0, 0, 0);
//		calendar.add(Calendar.MONTH, 0);
//		return new Timestamp(calendar.getTime().getTime());
//	}
//
//	/** **********************私有方法*********************** */
//	private static String getFM(String flag, Date date) {
//		Date currentDate = date;
//		SimpleDateFormat formatter = new SimpleDateFormat(flag, Locale.getDefault());
//		String result = formatter.format(currentDate);
//		return result;
//	}
//
//	/*
//	 * 日期相减
//	 */
//	public static long getDaySub(String beginDateStr, String endDateStr) {
//		long day = 0;
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date beginDate;
//		Date endDate;
//		try {
//			beginDate = format.parse(beginDateStr);
//			endDate = format.parse(endDateStr);
//			day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
//			// System.out.println("相隔的天数="+day);
//		} catch (ParseException e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		}
//		return day;
//	}
//
//	public static Date getExpireByHour(int hour) {
//		Date d = new Date();
//		Calendar rightNow = Calendar.getInstance();
//		rightNow.setTime(d);
//		rightNow.add(Calendar.HOUR, hour);
//		return rightNow.getTime();
//	}
//
//	public static Date getExpireByMinute(int minute) {
//		Date d = new Date();
//		Calendar rightNow = Calendar.getInstance();
//		rightNow.setTime(d);
//		rightNow.add(Calendar.MINUTE, minute);
//		return rightNow.getTime();
//	}
//
//	/**
//	 * 20141030133525变为2014-10-30 13:35:25
//	 *
//	 * @param string
//	 * @return
//	 */
//	public static String formatTime(String t) {
//		if (StringUtils.isEmpty(t) || t.length() != 14)
//			return "";
//
//		String y = t.substring(0, 4);
//		String M = t.substring(4, 6);
//		String d = t.substring(6, 8);
//		String h = t.substring(8, 10);
//		String m = t.substring(10, 12);
//		String s = t.substring(12, 14);
//		return y + "-" + M + "-" + d + " " + h + ":" + m + ":" + s;
//	}
//
//	/**
//	 * 日期转换成字符串
//	 *
//	 * @param date
//	 * @return str
//	 */
//	public static String dateToYYYYMMDD(Date date) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//		String str = format.format(date);
//		return str;
//	}
//
//	/**
//     *
//     * @Description 获取前一天
//     * @author 卢涛（13480916731）
//     * @param date
//     * @return 返回String yyyyMMdd
//     */
//    public static String getAgoDaFormat() {
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_MONTH, -1);
//        date = calendar.getTime();
//        return format(date,"yyyy-MM-dd");
//    }
//
//    public static String getAgoDay() {
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_MONTH, -1);
//        date = calendar.getTime();
//        return formatYMD(date);
//    }
//
//    /**
//     *
//     * 获取后一天日期
//     * @author 卢涛（13480916731）
//     * @return
//     */
//    public static String getAfterDay() {
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_MONTH, 1);
//        date = calendar.getTime();
//        return formatYMD(date);
//    }
//
//    /**
//     *
//     * @Description 格式化日期
//     * @author 卢涛（13480916731）
//     * @param date
//     * @return 返回String yyyyMMdd
//     */
//    public static String formatYMD(Date date) {
//        return format(date, FORMAT_YMD);
//      }
//
//    /**
//     * 使用参数Format格式化Date成字符串
//     */
//    public static String format(Date date, String pattern) {
//        String returnValue = "";
//        if (date != null) {
//            SimpleDateFormat df = new SimpleDateFormat(pattern);
//            returnValue = df.format(date);
//        }
//
//        return (returnValue);
//    }
//
//    /**
//     *
//     * 用于某日期加几天
//     * @author 卢涛（13480916731）
//     * @param orgCutDayTime 当前时间，精确到天级别 yyyyMMdd
//     * @param nextDay
//     * @return 第N天
//     * @throws ParseException
//     */
//    public static String calculateNextNDay(final String orgCutDayTime, int nextDay) throws ParseException {
//        return DateTimeFormat.forPattern(FORMAT_YMD).parseDateTime(orgCutDayTime).plusDays(nextDay)
//                .toString(FORMAT_YMD);
//    }
//
//    /**
//     *
//     * 用于某日加几个月
//     * @author 卢涛（13480916731）
//     * @param orgCutDayTime
//     * @param nextMonth
//     * @return
//     * @throws ParseException
//     */
//    public static String calculateNextNMonth(final String orgCutDayTime, int nextMonth) throws ParseException {
//        return DateTimeFormat.forPattern(FORMAT_YMD).parseDateTime(orgCutDayTime).plusMonths(nextMonth)
//                .toString(FORMAT_YMD);
//    }
//
//    /**
//     *
//     * 用于某日加几年
//     * @author 卢涛（13480916731）
//     * @param orgCutDayTime
//     * @param nextMonth
//     * @return
//     * @throws ParseException
//     */
//    public static String calculateNextNYear(final String orgCutDayTime, int nextMonth) throws ParseException {
//        return DateTimeFormat.forPattern(FORMAT_YMD).parseDateTime(orgCutDayTime).plusYears(nextMonth)
//                .toString(FORMAT_YMD);
//    }
//
//
//    private static final String FORMAT_YMDMS = "yyyyMMddHH:mm";
//    private static final String FORMAT_HM = "HH:mm";
//    public static final String FORMAT_YMDHMS = "yyyyMMddHHmmss";
//    public static final String FORMAT_YMDHMS_FRONT = "yyyy-MM-dd HH:mm:ss";
//    public static final String YMD = "yyyy-MM-dd";
//    /**
//     *
//     * <p>将字符串时间格式转成成Date</p><p>支持的格式有：<br/>
//     * yyyyMMddHH:mm<br/>HH:mm<br/>yyyyMMddHHmmss<br/>yyyyMMdd<br/>yyyy-MM-dd HH:mm:ss</p>
//     * @author 卢涛（13480916731）
//     * @param time
//     * @return
//     * @throws ParseException
//     */
//    public static Date getDateFromString(final String time) throws ParseException {
//        return org.apache.commons.lang3.time.DateUtils.parseDate(time,
//                new String[] {FORMAT_YMDMS, FORMAT_HM, FORMAT_YMDHMS, FORMAT_YMD, FORMAT_YMDHMS_FRONT, YMD });
//    }
//
//    /**
//     *
//     * 和当前时间比较  <br />
//     * true：当前时间之后 <br />
//     * false：当前时间之前
//     * @author 卢涛（13480916731）
//     * @param date
//     * @return
//     */
//    public static boolean compareNow(String date) {
//        if (date == null) {
//            return false;
//        }
//        try {
//            return new DateTime(DateUtil.getDateFromString(date).getTime()).isAfterNow();
//        } catch (ParseException e) {
//            System.out.println(e);
//        }
//        return false;
//    }
//
//
//
//    public static SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("HH:mm:ss");
//    public static SimpleDateFormat DEFAULT_YYYMMDD = new SimpleDateFormat("yyyyMMddHHmmss");
//
//    public static Calendar getCalendar(String timeStr,SimpleDateFormat s) throws ParseException{
//        Date date = s.parse(timeStr);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//
//        return calendar;
//    }
//
//    /**
//     *
//     * HH:mm:ss 当前时间是否在两个时间 之间
//     * @author 卢涛（13480916731）
//     * @param time1
//     * @param time2
//     * @return
//     */
//    public static Boolean isBetween(String time1, String time2){
//        Boolean flag = false;
//        try {
//            Calendar calendar1 = getCalendar(time1,DEFAULT_SDF);
//            Calendar calendar2 = getCalendar(time2,DEFAULT_SDF);
//            String current = DEFAULT_SDF.format(new Date());
//            Calendar calendar = getCalendar(current,DEFAULT_SDF);
//
//            if(calendar.after(calendar1) && calendar.before(calendar2)){
//                flag = true;
//            }
//        } catch (ParseException e) {
//            log.error("时间格式配置有误！");
//        }
//
//        return flag;
//    }
//
//
//    /**
//     *
//     * yyyyMMddHHmmss 当前date时间是否在time1,time2两个时间 之间 <br>
//     * true: 是
//     * @author 卢涛（13480916731）
//     * @param time1 yyyyMMddHHmmss
//     * @param time2 yyyyMMddHHmmss
//     * @param date yyyyMMddHHmmss
//     * @return
//     */
//    public static Boolean isBetween(String time1, String time2,String date){
//        Boolean flag = false;
//        try {
//            Calendar calendar1 = getCalendar(time1,DEFAULT_YYYMMDD);
//            Calendar calendar2 = getCalendar(time2,DEFAULT_YYYMMDD);
//            Calendar calendar = getCalendar(date,DEFAULT_YYYMMDD);
//
//            if(calendar.after(calendar1) && calendar.before(calendar2)){
//                flag = true;
//            }
//        } catch (ParseException e) {
//            log.error("时间格式配置有误！");
//        }
//
//        return flag;
//    }
//
//
//    /**
//     * 判断nowDate 是否在startDate 和endDate 两个时间之内<br>
//     * true:是
//     *
//     * @author 卢涛（13480916731）
//     * @param startDate  yyyyMMddHHmmss
//     * @param endDate yyyyMMddHHmmss
//     * @param nowDate yyyyMMddHHmmss
//     * @return
//     */
//    public static boolean nowBetween(String startDate, String endDate, String nowDate) {
//        DateTime now;
//        DateTime beforeTime;
//        DateTime afterTime;
//        try {
//            now = new DateTime(DateUtil.getDateFromString(nowDate));
//            beforeTime = new DateTime(DateUtil.getDateFromString(startDate));
//            afterTime = new DateTime(DateUtil.getDateFromString(endDate));
//
//            return now.isAfter(beforeTime) && now.isBefore(afterTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            log.error("--->判断nowDate 是否在startDate 和endDate 两个时间之内处理异常", e);
//        }
//        return false;
//    }
//
//    public static String Now(){
//        Date date = new Date();
//        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String LgTime = sdformat.format(date);
//        return LgTime;
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//

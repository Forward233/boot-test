package com.boot.demo.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: yhl
 * @DateTime: 2020/2/17 6:07
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = format.parse("2020-02");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //2、获取本月第一天凌晨开始时间00:00:00和最后一天的最后一刻时间23:59:59
        Calendar calendar = Calendar.getInstance();
        //当前日期月份
        calendar.add(Calendar.MONTH, 0);
        //创建当前时间
        //设置时间格式为yyyy-MM-dd HH:mm:ss
        //设置当前时间,
        calendar.setTime(date);
        //打印当前时间
        System.out.println("当前时间：" + sdf.format(date));
        //获取到本月起始日
        int actualMinimum = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        //获取到本月结束日
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置本月起始日的年月日时分秒格式
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), actualMinimum, 0, 0, 0);
        //打印本月起始日的年月日时分秒格式
        System.out.println("这个月的第一天是： " + sdf.format(calendar.getTime()));
        //设置本月结束日的年月日时分秒格式

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), actualMaximum, 23, 59, 59);
        //打印本月结束日的年月日时分秒格式
        System.out.println(isInDate("2020-08-12 12:00:00","2020-08-15 12:00:00"));

    }

    public static boolean isInDate(String startTime, String endTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate;
        Date endDate;
        Date date = new Date();
        try {
            beginDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
            if (date.after(beginDate) && date.before(endDate)) {
                return true;
            }
        } catch (ParseException e) {
        }
        return false;
    }




}

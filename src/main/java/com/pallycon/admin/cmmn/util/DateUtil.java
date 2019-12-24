package com.pallycon.admin.cmmn.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author
 * @version 1.0
 * @Class Name : DateUtil.java
 * @Description : DateUtil Class
 * @Copyright ⓒ 2011
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일              수정자              수정내용
 *
 * ------------------------------------------------------------------
 *
 *
 * </pre>
 * @see
 * @since 2014. 09. 14.
 */
public class DateUtil {

    public static String changeDateFormat(String targetDate) throws ParseException {
        SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat formatOut = new SimpleDateFormat("yyyyMMddHHmmss");
        String resultDate;
        resultDate = formatOut.format(formatIn.parse(targetDate));

        return resultDate;
    }
    public static String changeResponseDateFormat(String targetDate) throws ParseException {
        SimpleDateFormat formatIn = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat formatOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String resultDate = "";
        if(null != targetDate){
            resultDate = formatOut.format(formatIn.parse(targetDate));
        }
        return resultDate;
    }

    /**
     * 오라클DB의 DATE형식 객체 생성(현재 년월일시간분초)
     *
     * @return Timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        Calendar calendar = Calendar.getInstance();

        return new Timestamp(calendar.getTime().getTime());
    }

    public static String getUnixUTCTimestamp(){
        Date date = new Date();
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(timeZone);
        cal.setTime(date);
        return String.valueOf(cal.getTime().getTime());
    }

    /**
     * 현재 날짜와 시각을  yyyyMMdd 형태로 반환한다.
     *
     * @return String
     */
    public static String getYyyymmdd() {
        Calendar calendar = Calendar.getInstance();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyyMMdd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(calendar.getTime());
    }

    /**
     * 현재 날짜와 시각을  yyyy.MM.dd 형태로 반환한다.
     *
     * @return String
     */
    public static String getYyyymmddOth() {
        Calendar calendar = Calendar.getInstance();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyy.MM.dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(calendar.getTime());
    }


    /**
     * 현재 날짜와 시각을 Yyyymmddhhmmss 형태로 반환한다.
     *
     * @return
     */
    public static String getYyyymmddhhmmss() {
        Calendar calendar = Calendar.getInstance();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(calendar.getTime());
    }

    /**
     * 현재GMT 날짜와 시각을 Yyyymmddhhmmss 형태로 반환한다.
     *
     * @return
     */
    public static String getGMTYyyymmddhhmmss() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(calendar.getTime());
    }

    /**
     * 현재 날짜와 시각을 Yyyymmdd 형태로 반환한다.
     *
     * @param cal
     * @return
     */
    public static String getYyyymmdd(Calendar cal) {
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(cal.getTime());
    }

    /**
     * 현재 날짜와 시각을 인자의 pattern으로 변환한다.
     *
     * @param
     * @return
     */
    public static String getNowDate(String pattern) {
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(Calendar.getInstance().getTime());
    }

    /**
     * getGregorianCalendar
     *
     * @param yyyymmdd
     * @return
     */
    public static GregorianCalendar getGregorianCalendar(String yyyymmdd) {

        int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
        int mm = Integer.parseInt(yyyymmdd.substring(4, 6));
        int dd = Integer.parseInt(yyyymmdd.substring(6));

        GregorianCalendar calendar = new GregorianCalendar(yyyy, mm - 1, dd, 0, 0, 0);

        return calendar;

    }

    /**
     * 지정된 플래그에 따라 연도 , 월 , 일자를 연산한다.
     * - 사용 예
     * String date = DateUtil.getOpDate(java.util.Calendar.DATE , 1, "20080101")
     *
     * @return String
     */
    public static String getOpDate(int field, int amount, String date) {

        GregorianCalendar calDate = getGregorianCalendar(date);

        if (field == Calendar.YEAR) {
            calDate.add(GregorianCalendar.YEAR, amount);
        } else if (field == Calendar.MONTH) {
            calDate.add(GregorianCalendar.MONTH, amount);
        } else {
            calDate.add(GregorianCalendar.DATE, amount);
        }

        return getYyyymmdd(calDate);

    }

    /**
     * 2009-03-10 String날짜변수를  2009-03-10 00:00:00 Timestamp 형식으로 반환한다.
     *
     * @param dateStr
     * @return
     */
    public static Timestamp replaceTimestamp(String dateStr) {
        if (dateStr == null || dateStr.length() != 10) return null;

        String year = dateStr.substring(0, 4);
        String month = dateStr.substring(5, 7);
        String day = dateStr.substring(8, 10);
//		String hh    = dateStr.substring(11, 13);
//		String mm    = dateStr.substring(14, 16);
//		String ss    = dateStr.substring(17, 18);

        Calendar calendar = Calendar.getInstance();

        calendar.set(
                Integer.parseInt(year),
                Integer.parseInt(month) - 1,
                Integer.parseInt(day),
                0,
                0,
                0
        );

        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 20101013112322 String날짜변수를  2009-03-10 11:23:22 Timestamp 형식으로 반환한다.
     *
     * @param dateStr
     * @return
     */
    public static Timestamp replaceTimestampType1(String dateStr) {
        if (dateStr == null || dateStr.length() != 14) return null;

        String year = dateStr.substring(0, 4);
        String month = dateStr.substring(4, 6);
        String day = dateStr.substring(6, 8);
        String hh = dateStr.substring(8, 10);
        String mm = dateStr.substring(10, 12);
        String ss = dateStr.substring(12, 14);

        Calendar calendar = Calendar.getInstance();

        calendar.set(
                Integer.parseInt(year),
                Integer.parseInt(month) - 1,
                Integer.parseInt(day),
                Integer.parseInt(hh),
                Integer.parseInt(mm),
                Integer.parseInt(ss)
        );

        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 지난 요일 일자 가져오기
     *
     * @param pYoil - 가져올 요일( 1:일 2:월 ~ 6:금 7:토 )
     * @return 해당요일의 일자 yyyyMMdd
     */
    public static String getBeforeYoilDate(int pYoil) {
        String strDate = "";
        Calendar cal = Calendar.getInstance();
        int nDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int nDayOfYoil = 0;

        try {

            for (int i = 1; i <= 7; i++) {
                if (nDayOfWeek == i) {
                    nDayOfYoil = pYoil - i;
                    break;
                }
            }

            if (nDayOfYoil > 0) nDayOfYoil -= 7;

            cal.add(Calendar.DATE, nDayOfYoil);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            strDate = sdf.format(cal.getTime());
        } catch (Exception e) {
        }

        return strDate;
    }


    /**
     * getConvertYyyymmdd
     *
     * @param tmp
     * @return
     */
    public static String getConvertYyyymmdd(Timestamp tmp, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.KOREA);

        return sdf.format(tmp);
    }


    /**
     * 데이터 형식이 맞는지 여부 확인(param : yyyy-MM-dd)
     *
     * @param dateString
     * @return
     * @throws Exception
     */
    public static boolean isDateFormat(String dateString) throws Exception {
        try {
            if (dateString == null || dateString.length() != 10) {
                return false;
            }

            String year = dateString.substring(0, 4);
            String month = dateString.substring(5, 7);
            String day = dateString.substring(8, 10);

            int yearInt = Integer.parseInt(year);
            int monthInt = Integer.parseInt(month);
            int dayInt = Integer.parseInt(day);

            Calendar calendar = Calendar.getInstance();
            calendar.set(yearInt, monthInt - 1, dayInt);

            if (yearInt < 0 || monthInt < 0 || dayInt < 0) {
                return false;
            }

            if (monthInt > 12 || dayInt > 31) {
                return false;
            }

            int endDay = getLastDayOfMon(yearInt, monthInt);

            if (Integer.parseInt(day) > endDay) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    /**
     * 입력된 년월의 마지막 날
     *
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
    public static int getLastDayOfMon(int year, int month) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);

        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 2개 날짜(Date)사이의 날짜들 리턴
     * ex) getDatesOfRange("2010-11-30", "2010-12-02", "yyyy-MM-dd")
     * getDatesOfRange
     *
     * @param beginDate
     * @param endDate
     * @param formatStr
     * @return
     * @throws Exception
     */
    public static List<Date> getDatesOfRange(String beginDate, String endDate, String formatStr) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return getDatesOfRange(format.parse(beginDate), format.parse(endDate));
    }

    /**
     * 2개 날짜(Date)사이의 날짜들 리턴
     * getDatesOfRange
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<Date> getDatesOfRange(Date beginDate, Date endDate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(beginDate);

        Calendar endDateCalendar = new GregorianCalendar();
        endDateCalendar.setTime(endDate);
        endDateCalendar.add(Calendar.DATE, 1);  // 마지막 날짜를 포함하기 위해 하루를 더함

        while (calendar.before(endDateCalendar)) {
            Date tempDate = calendar.getTime();
            dates.add(tempDate);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }


    public static List<String> getAddYyyy(int startYear, int addYear) {
        List<String> yearList = new ArrayList<String>();
        for (int i = 0; i < (Integer.parseInt(getYyyy()) + addYear - startYear); i++) {
            yearList.add(i, String.valueOf(startYear + i));
        }

        return yearList;
    }

    /**
     * 현재년도를  yyyy형태로 반환한다.
     *
     * @return String
     */
    public static String getYyyy() {
        Calendar calendar = Calendar.getInstance();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(calendar.getTime());
    }

    /**
     * 현재년도를  mm형태로 반환한다.
     *
     * @return String
     */
    public static String getMm() {
        Calendar calendar = Calendar.getInstance();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "MM";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(calendar.getTime());
    }

    /**
     * 현재년도를  dd형태로 반환한다.
     *
     * @return String
     */
    public static String getDd() {
        Calendar calendar = Calendar.getInstance();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(calendar.getTime());
    }

    /**
     * 날짜의 포멧을 변경한다.
     *
     * @param str             => 변환할 데이터
     * @param org_date_format => 변환하고자 하는 날짜의 포멧
     * @param chane_format    => 변환되는 날짜의 포멧
     * @return
     */
    public static String StringConvertDateFormat(String str, String org_date_format, String chane_format) {
        SimpleDateFormat org_format = new SimpleDateFormat(org_date_format, Locale.ENGLISH);
        SimpleDateFormat convert_format = new SimpleDateFormat(chane_format);
        Date convertString;
        try {
            convertString = org_format.parse(str);

            String resutlConvert = convert_format.format(convertString);

            return resutlConvert;
        } catch (ParseException e) {
            return e.toString();
        }
    }

    public static List<String> getMonthList() {
        List<String> monthList = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            monthList.add(i, String.valueOf(i + 1));
        }

        return monthList;
    }

    public static List<String> getDateList() {
        List<String> dateList = new ArrayList<String>();
        for (int i = 0; i < 31; i++) {
            dateList.add(i, String.valueOf(i + 1));
        }

        return dateList;
    }

    public static String getGMTTimeStampString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        TimeZone z = TimeZone.getTimeZone("GMT");
        formatter.setTimeZone(z);
        return formatter.format(new Date());
    }

}

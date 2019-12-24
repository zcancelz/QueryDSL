/*
 * INKA DRM Server Java Platform
 * Copyright ⓒ 2010 INKA ENTWORKS. All rights reserved.
 *
 * 본 소스 및 바이너리 파일에 대한 권한은 모두 잉카엔트웍스에 있습니다.
 * 저작자와의 협의 없이 수정 및 무단 배포를 금합니다.
 */
package com.pallycon.admin.cmmn.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Base64;
import java.util.*;

public class StringUtil {

    /** 로그 */
    private static final Log log = LogFactory.getLog(StringUtil.class);
    
    /** The empty String <code>""</code>.등 */
    public static final String EMPTY = "";
    public static final String SCRIPT = "(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)";
    public static final String OBJECT = "(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)";
    public static final String APPLET = "(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)";
    public static final String EMBED = "(E|e)(M|m)(B|b)(E|e)(D|d)";
    public static final String FORM = "(F|f)(O|o)(R|r)(M|m)";
    public static final String CL = "(C|c)(L|l)";
    public static final String LF = "(L|l)(F|f)";
    public static final String DOT1 = "..";
    public static final String DOT2 = "../";
    public static final String NOT_PERMITTED = "Not Permitted";

    public static String replaceHeaderFormat(String str, String repBR ) {
        String tmp = str.replaceAll( String.valueOf((char)39), "&#39;" );
        tmp = tmp.replaceAll( "=", NOT_PERMITTED );
        tmp = tmp.replaceAll( ";", NOT_PERMITTED );
        tmp = tmp.replaceAll( ":", NOT_PERMITTED );
        tmp = tmp.replaceAll( "\r\n|\n", NOT_PERMITTED );
        tmp = tmp.replaceAll(CL, "%0d|\n" );
        tmp = tmp.replaceAll(LF, "%0a|\r");

        return tmp;
    }

    public static String replaceURLFormat(String str ) {
        return replaceURLFormat( str, "<br>" );
    }

    public static String replaceURLFormat(String str, String repBR ) {
        String tmp = str.replaceAll( String.valueOf((char)39), "&#39;" );
        tmp = tmp.replaceAll( String.valueOf((char)34), "&quot;" );
        tmp = tmp.replaceAll( "&", "&amp;" );
        tmp = tmp.replaceAll( ">", "&gt;" );
        tmp = tmp.replaceAll( "<", "&lt;" );
        tmp = tmp.replaceAll( "\r\n|\n", repBR );

        tmp = tmp.replaceAll(SCRIPT, NOT_PERMITTED);
        tmp = tmp.replaceAll("/" + SCRIPT, NOT_PERMITTED);
        tmp = tmp.replaceAll(OBJECT, NOT_PERMITTED);
        tmp = tmp.replaceAll("/" + OBJECT, NOT_PERMITTED);
        tmp = tmp.replaceAll(APPLET, NOT_PERMITTED);
        tmp = tmp.replaceAll("/" + APPLET, NOT_PERMITTED);
        tmp = tmp.replaceAll(EMBED, NOT_PERMITTED);
        tmp = tmp.replaceAll("/" + EMBED, NOT_PERMITTED);
        tmp = tmp.replace(DOT1, NOT_PERMITTED);
        tmp = tmp.replace(DOT2, NOT_PERMITTED);

        return tmp;
    }

    /**
     * byte 배열을 구분자/공백 없는 16진수 문자열로 변환
     * 
     * @param array
     * @return 16진수 스트링
     */
    public static String byteArrayToHexStringNoSpace(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(Integer.toHexString(0x0100 + (array[i] & 0x00FF)).substring(1));
        }
        return sb.toString();
    }
    
    /**
     * request로부터 페이지의 language를 설정한다.<br/>
     * lang 이름으로 paramemter 가 있고, 그 값이 en 또는 ko 일 경우 그 값을 설정한다.<br/>
     * 만약 parameter가 없거나 올바르지 않을 경우 request의 Accept-Language 값을 가져와,<br/>
     * 한국어일 경우 ko 로 표기, 아닐 경우 en으로 표기한다.
     * 
     * @param
     * @return en 또는 ko 값의 language
     */
//    public static String getLanguage(HttpServletRequest request) {
//        String paramLang = request.getParameter("lang");
//
//        if ("en".equals(paramLang) || "ko".equals(paramLang)) {
//            return paramLang;
//        }
//
//        // request header 로 부터 Accept-Language 를 가져온다
//        // 한국어일 경우, 브라우저 별로 아래와 같은 형태로 가져온다
//        // FF: ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3
//        // IE: ko
//        String acceptLang = request.getHeader("Accept-Language");
//        String firstLang = acceptLang.split(",")[0];
//        if (firstLang.indexOf("ko") > -1) {
//            return "ko";
//        }
//
//        return "en";
//    }

    
    public static String f_get_parm(String val )
    {
      if ( val == null ) val = "";
      return  val;
    }
    
    
    /**
     * 입력값에 대한 XSS 방지
     * @param str, repBR
     * @return string
     */ 
    public static String replaceStringFormat(String str ) {
        return replaceStringFormat( str, "<br>" );
    }

    public static String replaceStringFormat(String str, String repBR ) {
        String tmp = str.replaceAll( String.valueOf((char)39), "&#39;" );
        tmp = tmp.replaceAll( String.valueOf((char)34), "&quot;" );
        tmp = tmp.replaceAll( "&", "&amp;" );
        tmp = tmp.replaceAll( ">", "&gt;" );
        tmp = tmp.replaceAll( "<", "&lt;" );
        tmp = tmp.replaceAll( "\r\n|\n", repBR );

        tmp = tmp.replaceAll(SCRIPT, NOT_PERMITTED);
        tmp = tmp.replaceAll("/" + SCRIPT, NOT_PERMITTED);
        tmp = tmp.replaceAll(OBJECT, NOT_PERMITTED);
        tmp = tmp.replaceAll("/" + OBJECT, NOT_PERMITTED);
        tmp = tmp.replaceAll(APPLET, NOT_PERMITTED);
        tmp = tmp.replaceAll("/" + APPLET, NOT_PERMITTED);
        tmp = tmp.replaceAll(EMBED, NOT_PERMITTED);
        tmp = tmp.replaceAll("/" + EMBED, NOT_PERMITTED);
        tmp = tmp.replaceAll(FORM, NOT_PERMITTED);
        tmp = tmp.replaceAll("/" + FORM, NOT_PERMITTED);
        
        return tmp;
    }
    
	/**
	 * 파일의 내용을 읽어온다.
	 * getFileContent
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileContent(String fileName){
		java.io.BufferedReader in = null;
		StringBuffer buf = new StringBuffer();
		String line;
		
		try {
			java.io.File file = new java.io.File(replaceStringFormat(fileName));
			in  = new java.io.BufferedReader(new java.io.FileReader(file));
			while( (line = in.readLine()) != null )	{
			    buf.append(line).append("\n");
			}
		}catch(Exception e){
			log.error(e.getMessage());
			return "";
		}finally {
			try { 
				if ( in != null ) in.close(); 
			} catch(Exception e){
				log.error(e.getMessage());
			}
		}
		return buf.toString();		
	}
	
	
	/**
	 * fixNull Null인 경우 "" 처리 메소드
	 *
	 * @param str
	 * @return
	*/
	public static String fixNull(String str)
	{
		String value = null;
		if ( str == null || "null".equals(str)) 
			value = "";
		else
			value = str;
		return value;
	}
	
	/**
	 * 대문자 A-Z 랜덤 알파벳 생성
	 *
	 * @param alphabetLength : 생성할 랜덤대문자알파벳 길이 
	 * @return String
	*/
	public static String getRandomAlphabet(int alphabetLength){
		if(alphabetLength < 1) return null;
		
		StringBuffer sb = new StringBuffer(alphabetLength);
		
        Random ranGen = new Random();
		for(int i=1; i<=alphabetLength; i++){
	        ranGen.setSeed((new Date()).getTime() * i);
            //sb.append( (char) ((ranGen.nextInt(26)) + 65) ); //웹보안진단으로 인한 수정
	        int ch = (ranGen.nextInt(75)) + 48;
	        while((ch >= 58 && ch <= 64) || (ch >=91 && ch <= 96)){
	        	ch = (ranGen.nextInt(75)) + 48;
	        }
	        sb.append( (char) (ch) ); //웹보안진단으로 인한 수정
		}
		return sb.toString();
	}
	public static String getRandomFromTime(int length){
        if(length < 1) return null;
        byte[] ret = new byte[length];

        Random ranGen = new Random();
        ranGen.setSeed(System.currentTimeMillis());
        ranGen.nextBytes(ret);
        return Base64.getEncoder().encodeToString(ret);

    }
    /**
     * 한글 인코딩(KSC5601)을 "8859_1" 인코딩으로 전환한다.
     * 
     * @param str
     * @return String
     * @throws Exception
     */
    public static String kscToAsc(String str) throws Exception {
        try {
            return str != null ? new String(str.getBytes("KSC5601"), "8859_1")
                    : str;
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }
    
    
    /**
	 * Mid 중간문자를 반환해준다.
	 *
	 * @param str
	 * @param i
	 * @param j
	 * @return
	*/
	public static String Mid(String str, int i, int j){
		
		String value = null;
		try{
			if ( str == null ) 
				return "";
			
			/*
			else
				str = str;
			*/
			if(str.length() < j)
				value = str;
			else
				value = str.substring(i, j);							
		} catch (Exception ex) {
			value = "";
		}

		return value;
	}  
    public static String objectToJson(Object object){
 		String errorString = "";
 		try {
 			ObjectMapper mapper = new ObjectMapper();
            errorString = mapper.writeValueAsString(object);

 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		return errorString;
 	}

    /**
     * 8859_1을 KSC5601로 인코딩한다.
     * 
     * @param str
     * @return String
     * @throws Exception
     */
    public static String ascToKsc(String str) throws Exception {
        try {
            return str != null ? new String(str.getBytes("8859_1"), "KSC5601")
                    : str;
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

    public static String kscToUtf(String str) throws Exception {
        try {
            return str != null ? new String(str.getBytes("8859_1"), "UTF-8")
                    : str;
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }
    /**
     * 스트링내의 특정문자를 Swap 시킴. <br>
     * 이를 사용하는 것보다는 정규식을 사용하는 것을 권장함. (String.replaceAll())
     * 
     * @param input
     * @param oldStr
     * @param newStr
     * @return String
     */
    public static String replaceAll(String input, String oldStr, String newStr) {
        int startIdx = 0;
        int idx = 0;
        int length = oldStr.length();

        StringBuffer sb = new StringBuffer((int) (input.length() * 1.2));
        while ((idx = input.indexOf(oldStr, startIdx)) >= 0) {
            sb.append(input.substring(startIdx, idx));
            sb.append(newStr);
            startIdx = idx + length;
        }
        sb.append(input.substring(startIdx));
        return sb.toString();
    }

    /**
     * 대상 문자열에서 특정 문자 제거.
     * 
     * @param input
     * @param oldStr
     * @return String
     */
    public static String remove(String input, String oldStr) {
        int startIdx = 0;
        int idx = 0;
        int length = oldStr.length();

        StringBuffer sb = new StringBuffer((int) (input.length() * 1.2));
        while ((idx = input.indexOf(oldStr, startIdx)) >= 0) {
            sb.append(input.substring(startIdx, idx));
            startIdx = idx + length;
        }
        sb.append(input.substring(startIdx));
        return sb.toString();
    }

    /**
     * <pre>
     *   String을 원하는 크기(byte 단위)로 줄여 마지막 접미사를 붙여 반환한다.
     *   (접미사도 length에 포함된다.)
     *   
     *   MS949 인코딩을 기준으로 byte[]를 얻은 후 이를 byte 단위로 나누면서 2바이트를 
     *   차지하는 글자에 대한 조정을 수행한다.
     * </pre>
     * 
     * @param content
     *            String 내용
     * @param length
     *            원하는 글자 길이 (Byte 수 기준)
     * @param suffix
     *            잘린 글자 뒤에 붙일 문자
     * @return length보다 길경우 suffix를 붙인 String
     */
    public static String fixLength(String content, int length, String suffix) {
        if (content == null) {
            return "";
        }
        if (content.getBytes().length > length) {
            int slen = 0, blen = 0;
            int realLength = length - suffix.getBytes().length;
            while (blen < realLength) {
                blen++;
                slen++;
                if (content.charAt(slen) > '\u00FF') {
                    blen++; // 2-byte character..
                }
            }
            return content.substring(0, slen) + suffix;
        }
        return content;
    }

    /**
     * <pre>
     *  String을 원하는 길이(byte 단위)로 줄여 마지막 접미사를 붙여 반환한다.
     *  
     *  접미사의 길이도 길이에 포함되며, 원하는 크기가 접미사의 길이보다 작으면 IllegalArgumentException을 던진다.
     *  
     *  주어진 인코딩 기준으로 byte[]를 얻은 후 이를  byte 단위로 나누며, 나누는 과정에 깨지는 문자는 버린다.
     * </pre>
     * 
     * @param content
     *            String 내용
     * @param maxWidth
     *            원하는 글자 길이 (byte 수 기준)\
     * @param enc
     *            인코딩
     * @param suffix
     *            잘린 글자 뒤에 붙일 문자
     * @return length보다 길경우 suffix를 붙인 String
     * @throws Exception
     * 
     * @see IllegalArgumentException
     */
    public static String abbreviate(String content, int maxWidth, String enc,
                                    String suffix) throws Exception {
        if (content == null)
            return "";
        if (maxWidth < suffix.length())
            throw new Exception(new IllegalArgumentException());
        int ptr = maxWidth - suffix.length();
        String str = null;
        try{
            byte[] bytes = content.getBytes(enc);
            str = new String(bytes, 0, (bytes.length < ptr)? bytes.length : ptr, enc);
        }catch(UnsupportedEncodingException e){
            throw new Exception(e);
        }
        // 인코딩 차이로 깨져 원문과 달라진 글자를 잘라낸다.
        ptr = ((ptr = str.length() - 4) < 0) ? 0 : ptr; // 끝에서 4글자 전부터 비교 시작
        while (ptr < str.length() && str.charAt(ptr) == content.charAt(ptr))
            ptr++;

        return str.substring(0, ptr) + suffix;
    }

    /**
     * 빈문자열 검사.
     */
    public static boolean isEmpty(String input) {
        return (input == null || input.trim().equals(""));
    }

    /**
     * 입력된 문자열을 화폐 단위로 표시한다.
     * @throws Exception
     */
    public static String toCurrency(String currency) throws Exception {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            return format.format(new Long(currency));
        } catch (IllegalArgumentException e) {
            throw new Exception(e);
        }
    }

    /**
     * 입력된 숫자를 화폐 단위표시
     * @throws Exception
     */
    public static String toCurrency(long currency) throws Exception {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            return format.format(new Long(currency));
        } catch (IllegalArgumentException e) {
            throw new Exception(e);
        }
    }

    /**
     * 화폐 단위 포맷을 숫자로 파싱한다.
     * @throws Exception
     */
    public static String parseCurrency(String myString) throws Exception {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            return format.parse(myString).toString();
        } catch (ParseException e) {
            throw new Exception(e);
        }
    }

    /**
     * Array 또는 List 등의 객체를 Delimiter로 구분된 문자로 반환한다.
     * 
     * @param obj
     * @param delimiter
     * @return String
     */
	public static String listToString(Object obj, String delimiter) {
        if (obj == null) {
            return "";
        }
        if (obj.getClass().isArray()) {
            StringBuffer buffer = new StringBuffer(512);
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                if (i != 0) {
                    buffer.append(delimiter);
                }
                buffer.append(Array.get(obj, i));
            }
            return buffer.toString();
        } else if (obj instanceof Collection) {
            return listToString(((Collection<?>) obj).iterator(), delimiter);
        } else if (obj instanceof Enumeration) {
            StringBuffer buffer = new StringBuffer(512);
            boolean started = false;
            Enumeration<?> it = (Enumeration<?>) obj;
            while (it.hasMoreElements()) {
                if (started) {
                    buffer.append(delimiter);
                } else {
                    started = true;
                }
                buffer.append(it.nextElement());
            }
            return buffer.toString();
        } else if (obj instanceof Iterator) {
            StringBuffer buffer = new StringBuffer(512);
            boolean started = false;
            Iterator<?> it = (Iterator<?>) obj;
            while (it.hasNext()) {
                if (started) {
                    buffer.append(delimiter);
                } else {
                    started = true;
                }
                buffer.append(it.next());
            }
            return buffer.toString();
        } else if (obj instanceof Map) {
            return listToString(((Map<?, ?>) obj).values(), delimiter);
        } else {
            return obj.toString();
        }
    }

    /**
     * source 가 null 일 경우 alernative 를 반환한다.
     * 
     * @param source
     * @param alernative
     * @return Object
     */
    public static Object nvl(final Object source, final Object alernative) {
        return Optional.ofNullable(source).orElse(alernative);
    }

    /**
     * original 이 Null이거나 공백 일 경우 replacement 를 반환한다.
     *
     * @param original
     * @param replacement
     * @return
     */
	public static String nvl(final String original, final String replacement)
	{
        return Optional.ofNullable(original).filter(s -> s.trim().length() != 0).orElse(replacement);
	}

    public static String jsonNvl(org.json.simple.JSONObject jsonObj, String keyName, String replacement){
        if( jsonObj.containsKey(keyName) ){
            return (String)jsonObj.get(keyName);
        }else{
            return replacement;
        }
    }

    public static int jsonNvl(org.json.simple.JSONObject jsonObj, String keyName, int replacement){
        if( jsonObj.containsKey(keyName) ){
            return (int)jsonObj.get(keyName);
        }else{
            return replacement;
        }
    }

    public static org.json.simple.JSONArray jsonNvl(org.json.simple.JSONObject jsonObj, String keyName, org.json.simple.JSONArray replacement){
        if( jsonObj.containsKey(keyName) ){
            return (org.json.simple.JSONArray)jsonObj.get(keyName);
        }else{
            return replacement;
        }
    }

    public static String isJsonNvl(org.json.simple.JSONObject jsonObj, String keyName, String replacement) {
        if (jsonObj.containsKey(keyName)) {
            return String.valueOf((boolean) jsonObj.get(keyName));
        } else {
            return replacement;
        }
    }
    public static long longJsonNvl(org.json.simple.JSONObject jsonObj, String keyName, long replacement) {
        if (jsonObj.containsKey(keyName)) {
            return Long.valueOf(jsonObj.get(keyName).toString());
        } else {
            return replacement;
        }
    }

    public static org.json.simple.JSONObject jsonNvl(org.json.simple.JSONObject jsonObj, String keyName, org.json.simple.JSONObject replacement){
        if( jsonObj.containsKey(keyName) ){
            return (org.json.simple.JSONObject) jsonObj.get(keyName);
        }else{
            return replacement;
        }
    }
	
    /**
     * 소수점이 있는 문자열에 , 처리
     * 
     */
    public static String formatNumber(String targetVal, String type) {

        int intVal = 0;
        double dblVal = 0;
        String rtnVal = "0";

        if (targetVal == null || targetVal.trim().length() == 0) {
            return "";
        }

        if (type.equals("INT")) { // 순수정수형

            intVal = Integer.parseInt(targetVal);

            DecimalFormat dfInt = new DecimalFormat("#,##0");

            rtnVal = dfInt.format(intVal);

        }
        if (type.equals("FINT")) { // 더블형에서 정수형

            intVal = Math.round(Float.parseFloat(targetVal));

            DecimalFormat dfInt = new DecimalFormat("#,##0");

            rtnVal = dfInt.format(intVal);

        } else if (type.equals("DBL")) { // 순수 더블형

            dblVal = new Double(targetVal).doubleValue();

            DecimalFormat dfDbl = new DecimalFormat("#,##0.00");

            rtnVal = dfDbl.format(dblVal);

        } else if (type.equals("IDBL")) { // 정수가 OVERFLOW(LONG TYPE 정수)

            dblVal = new Double(targetVal).doubleValue();

            DecimalFormat dfDbl = new DecimalFormat("#,##0");

            rtnVal = dfDbl.format(dblVal);

        } else if (type.equals("DDBL")) { // 더블형이 소수점 4자리인경우

            dblVal = new Double(targetVal).doubleValue();

            DecimalFormat dfDbl = new DecimalFormat("#,##0.0000");

            rtnVal = dfDbl.format(dblVal);
        } else if (type.equals("DDBL1")) { // 더블형이 소수점 1자리인경우

            dblVal = new Double(targetVal).doubleValue();

            DecimalFormat dfDbl = new DecimalFormat("#,##0.0");

            rtnVal = dfDbl.format(dblVal);
        } else if (type.equals("DDBL3")) { // 더블형이 소수점 3자리인경우

            dblVal = new Double(targetVal).doubleValue();

            DecimalFormat dfDbl = new DecimalFormat("##,###,###,##0.000");

            rtnVal = dfDbl.format(dblVal);
        } else if (type.equals("DDBL6")) { // 더블형이 소수점 6자리인경우

            dblVal = new Double(targetVal).doubleValue();

            DecimalFormat dfDbl = new DecimalFormat("##,###,###,##0.000000");

            rtnVal = dfDbl.format(dblVal);
        } else if (type.equals("DDBL7")) { // 더블형이 소수점 7자리인경우

            dblVal = new Double(targetVal).doubleValue();

            DecimalFormat dfDbl = new DecimalFormat(
                    "##,###,###,##0.0000000");

            rtnVal = dfDbl.format(dblVal);
        } else if (type.equals("INT4")) { // 정수지만 앞에 영이 붙는것 예) 0025

            int diff = 4 - targetVal.trim().length();
            for (int i = 0; i < diff; i++) {
                targetVal = "0" + targetVal;
            }
        }

        return rtnVal;
    }

    /**
     * Absolute Path에서 FileName 만 잘라서 반환한다. <br>
     * Windows 와 Unix 계열에서의 디렉토리 구분자가 다르므로, 동일한 데이터에 대해 다른 결과를 반환하므로, 개발/운용시 주의
     * 하기바람. <br>
     * 구분자는 System.getProperty("file.separator") 연산의 결과를 사용한다.
     * 
     */
    public static String getFileName(String fullFileName) {
        try {
            return fullFileName.substring(fullFileName.lastIndexOf(System
                    .getProperty("file.separator")) + 1);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 전달 받은 스트링 객체를 주어진 문자 세트로 디코딩 한다.
     * 
     * @param value
     *            디코딩될 값이 있는 스트링 객체.
     * @param charset
     *            디코딩 될 문자 세트.
     * @return 디코딩된 스트링 객체.
     */
    public static String decodeCharset(String value, String charset) {
        try {
            Charset set = Charset.forName(charset);
            return set.decode(ByteBuffer.wrap(value.getBytes())).toString();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 전달 받은 스트링 객체를 주어진 문자 세트로 인코딩 한다.
     * 
     * @param value
     *            인코딩될 값이 있는 스트링 객체.
     * @param charset
     *            인코딩 될 문자 세트.
     * @return 인코딩된 스트링 객체.
     */
    public static String encodeCharset(String value, String charset) {
        try {
            Charset set = Charset.forName(charset);
            ByteBuffer bb = set.encode(value);
            return new String(bb.array(), charset);

        } catch (Exception ex) {
            return null;
        }
    }
    
    /*
     * 평균 구하는 메소드 정의
    */
    public static double mean(double[] data) {   
    	double sum=0;          
    	double mean=0;        
    	
    	for(int i=0; i < data.length ; i++)
    	{
    	   sum += data[i];    
    	}
    	mean = sum / data.length;
    	return mean;        
    }
    /*
     * 분산 구하는 메소드 정의
    */
    public static double var(double[] data) {
    	double ss=0;        // (데이터-평균)^2 의 합
    	double var=0;       // 데이터의 분산
    	for(int i=0; i < data.length ; i++)
    	{
    	   ss += (data[i]-mean(data))*(data[i]-mean(data));   // 분산구하는식의 일부
    	}
    	var = ss / (data.length-1);  // 구해진 ss를 (데이터의 수-1)로 나누어 분산을 구함
    	return var;         		 // 분산 반환
    }
    /*
     * 표준편차 반환
    */
    public static double std(double[] data) {
    	return Math.sqrt(var(data));
    }
  //유형별 전화번호
	public String stringformat(String value){
		String returnValue = "";
		
		if(value != null){
			if(value.substring(0, 2).equals("02")){
				if(value.length() == 11){
					returnValue = value.substring(0,3)+"-"+value.substring(3,7)+"-"+value.substring(7,11);
				}else if(value.length() == 10){
					returnValue = value.substring(0,2)+"-"+value.substring(2,6)+"-"+value.substring(6,10);
				}else if(value.length() == 9){
					returnValue = value.substring(0,2)+"-"+value.substring(2,5)+"-"+value.substring(5,9);
				}
			}else{
				if(value.length() == 11){
					returnValue = value.substring(0,3)+"-"+value.substring(3,7)+"-"+value.substring(7,11);
				}else if(value.length() == 10){
					returnValue = value.substring(0,3)+"-"+value.substring(3,6)+"-"+value.substring(6,10);
				}else if(value.length() == 9){
					returnValue = value.substring(0,2)+"-"+value.substring(2,5)+"-"+value.substring(5,9);
				}
			}
			
		}
		return returnValue;
		
	}
  //Lpad
	public String stringLpad(String value, int length, String format){
		String data = "";
		if(length != value.length())
		{
			for(int i = 0;i<length;i++)
			{
				if(i < value.length())
				{
					data +=format;
				}		
			}
			data += value;
		}else{
			data = value;
		}
		return data;
	}
	//구분자를 제외한 값 불러오기
	public String splitDel(String value, String remove){
		String retValue = "";
		if(value != null){
			String valSub[] = value.split(remove);
			for(int i = 0; i < valSub.length; i++){
				retValue += valSub[i];
			}
		}
		return retValue;
	}
	
	public static String escape(String src) {
		  int i;   
		  char j;   
		  StringBuffer tmp = new StringBuffer();
		  tmp.ensureCapacity(src.length() * 6);   
		  for (i = 0; i < src.length(); i++) {   
		   j = src.charAt(i);   
		   if (Character.isDigit(j) || Character.isLowerCase(j)
		     || Character.isUpperCase(j))
		    tmp.append(j);   
		   else if (j < 256) {   
		    tmp.append("%");   
		    if (j < 16)   
		     tmp.append("0");   
		    tmp.append(Integer.toString(j, 16));
		   } else {   
		    tmp.append("%u");   
		    tmp.append(Integer.toString(j, 16));
		   }   
		  }   
		  return tmp.toString();   
		 }  

		 public static String unescape(String src) {
		  StringBuffer tmp = new StringBuffer();
		  tmp.ensureCapacity(src.length());   
		  int lastPos = 0, pos = 0;   
		  char ch;   
		  while (lastPos < src.length()) {   
		   pos = src.indexOf("%", lastPos);   
		   if (pos == lastPos) {   
		    if (src.charAt(pos + 1) == 'u') {   
		     ch = (char) Integer.parseInt(src
		       .substring(pos + 2, pos + 6), 16);   
		     tmp.append(ch);   
		     lastPos = pos + 6;   
		    } else {   
		     ch = (char) Integer.parseInt(src
		       .substring(pos + 1, pos + 3), 16);   
		     tmp.append(ch);   
		     lastPos = pos + 3;   
		    }   
		   } else {   
		    if (pos == -1) {   
		     tmp.append(src.substring(lastPos));   
		     lastPos = src.length();   
		    } else {   
		     tmp.append(src.substring(lastPos, pos));   
		     lastPos = pos;   
		    }   
		   }   
		  }   
		  return tmp.toString();   
		 }
    public static String getJsonObjectString(HashMap<String, Object> map)
    {
    	HashMap<String, Object> jsonMap = new HashMap<String, Object>();  //result map(json)
    	
    	@SuppressWarnings("rawtypes")
        Set set = map.keySet();
        Object[]hmKeys = set.toArray();
        
        for(int i = 0; i < hmKeys.length; i++)
        {
            String key = (String)hmKeys[i];
            
            jsonMap.put(key, map.get(key));
        }
    	
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return jsonObject.toString();
    }	 
    
	public static String urlSplit(String url)
	{
	    String addr[] = url.split("/");
	    
	    return addr[addr.length - 1];
	}
	
    public static String replaceRegDate(String value) {
    	
    	String sReturn = "";
    	
    	sReturn = value.substring(0,4) 
    	   + "/" + value.substring(4,6)
    	   + "/" + value.substring(6,8) 
    	   + " " + value.substring(9,11) 
    	   + ":" + value.substring(11,13)
    	   + ":" + value.substring(13,15) ;
    	
    	return sReturn;
    }
    
    /** 
     * 랜덤한 문자열을 원하는 길이만큼 반환합니다.
     *  
     * @param length 문자열 길이 
     * @return 랜덤문자열 
     * */
    public static String getRandomString(int length) {
    	StringBuffer buffer = new StringBuffer();
    	Random random = new Random();
    	String chars[] =     "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,1,2,3,4,5,6,7,8,9,0".split(",");
    	
    	for (int i=0 ; i<length ; i++)  {    
    		buffer.append(chars[random.nextInt(chars.length)]);  
    	}  
    	
    	return buffer.toString();
    }
}
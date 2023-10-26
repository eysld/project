package kr.co.roda.cmmn.util;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

    /**
     * XSS 방지 처리
     * @param String
     * @return  String
     * @exception
     */
    public static String unscript(String data) {
    	
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
        
        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

        return ret;
        
    }
	
    /**
     * 검색 파라미터 XSS 체크
     * @param String
     * @return  boolean
     * @exception
     */    
	public static boolean isSearchParamXss(String s) {
		if ("".equals(s) || s == null){
			return true;
		}else{
			if (s.toLowerCase().indexOf("script") >= 0){
				return false;
			}else if (s.indexOf("\"") >= 0){
				return false;
			}else if (s.indexOf("'") >= 0){
				return false;				
			}else if (s.indexOf(">") >= 0){
				return false;
			}else if (s.indexOf("<") >= 0){
				return false;
			}else if (s.indexOf("=") >= 0){
				return false;
			}else if (s.indexOf("--") >= 0){
				return false;
			}else{
				return true;
			}
		}
	}
	
    /**
     * 게시글 등록 파라미터 XSS 체크
     * @param String
     * @return  boolean
     * @exception
     */    
	public static boolean isAddParamXss(String s) {
		if ("".equals(s) || s == null){
			return true;
		}else{
			if (s.toLowerCase().indexOf("script") >= 0 && s.indexOf("\"") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("script") >= 0 && s.indexOf(">") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("script") >= 0 && s.indexOf("<") >= 0){
				return false;				
			}else if (s.toLowerCase().indexOf("iframe") >= 0 && s.indexOf("\"") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("iframe") >= 0 && s.indexOf(">") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("iframe") >= 0 && s.indexOf("<") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("applet") >= 0 && s.indexOf("\"") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("applet") >= 0 && s.indexOf(">") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("applet") >= 0 && s.indexOf("<") >= 0){
				return false;	
			}else if (s.toLowerCase().indexOf("object") >= 0 && s.indexOf("\"") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("object") >= 0 && s.indexOf(">") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("object") >= 0 && s.indexOf("<") >= 0){
				return false;	
			}else if (s.toLowerCase().indexOf("embed") >= 0 && s.indexOf("\"") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("embed") >= 0 && s.indexOf(">") >= 0){
				return false;
			}else if (s.toLowerCase().indexOf("embed") >= 0 && s.indexOf("<") >= 0){
				return false;		
			}else if (s.toLowerCase().indexOf("alert(") >= 0){
				return false;						
			}else if (s.toLowerCase().indexOf("document.") >= 0){
				return false;	
			}else if (s.toLowerCase().indexOf("location.") >= 0){
				return false;							
			}else{
				return true;
			}
		}
	}
	
    /**
     * 숫자형 체크
     * @param String
     * @return  boolean
     * @exception
     */    
	public static boolean isNumeric(String s) {
		if ("".equals(s) || s == null){
			return true;
		}else{
			try {
				Integer.parseInt(s);
				return true;
			} catch(NumberFormatException e) {
				return false;
			}
		}
	}
    
    /**
     * 리턴문자를 널체크해서 돌려줌.
     * @param String
     * @return  String
     * @exception
     */
	public static String isNotEmpty(String str) {
		if( str == null ){
			return "";
		}else{
			str = str.trim();
		}
		return str;
	}
	
    /**
     * 리턴문자를 br태그로 치환하여 돌려줌.
     * @param String
     * @return  String
     * @exception
     */
	public static String nl2br(String str) {
		if( str == null ) return "";
		str = str.replaceAll("\r\n", "<br/>");
		str = str.replaceAll("\n", "<br/>");
		str = str.replaceAll("\r", "<br/>");
		return str;
	}
	
    /**
     * XSS 방지와 리턴문자 br 치환
     * @param String
     * @return  String
     * @exception
     */
	public static String xssbr(String str) {
		
		str = unscript(str);
		str = nl2br(str);
		
		return str;
		
	}
	
    /**
     * 데이터가 비어있는지 유무를 판단하여 돌려줌
     * - isZeroCheck ( true : 0일때 return true )
     * @param Integer, boolean
     * @return  boolean
     * @exception
     */
	public static boolean isEmpty(Integer num, boolean isZeroCheck) {
		if( isZeroCheck ) {
			if( num == null ) {
				return true;
			} else {
				return num == 0 ? true : isEmpty(num);
			}
		} else {
			return isEmpty(num);
		}
	}
	
    /**
     * 데이터가 비어있는지 유무를 판단하여 돌려줌
     * @param Integer
     * @return  boolean
     * @exception
     */
	public static boolean isEmpty(Integer num) {
		return isEmpty(String.valueOf(num));
	}
	
    /**
     * 데이터가 비어있는지 유무를 판단하여 돌려줌
     * @param String
     * @return  boolean
     * @exception
     */
	public static boolean isEmpty(String str) {
		if( str == null ) return true;
		return str.trim().length() > 0 ? false : true;
	}
	
    /**
     * fill문자를 len의 길이만큼 앞에 붙여 돌려줌
     * @param int, int, String
     * @return  String
     * @exception
     */
	public static String zerofill(int num, int len, String fill) {
		return zerofill(String.valueOf(num), len, fill);
	}
	
    /**
     * fill문자를 len의 길이만큼 앞에 붙여 돌려줌
     * @param String, int, String
     * @return  String
     * @exception
     */
	public static String zerofill(String str, int len, String fill) {
		int strLen = str.length();
		StringBuffer tmp = new StringBuffer();
		for( int LoopI=0; LoopI<len-strLen; LoopI++ ) {
			tmp.append(fill);
		}
		tmp.append(str);
		return tmp.toString();
	}
	
    /**
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     * 
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    public static String getTimeStamp() {
		String rtnStr = null;
		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";
		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
	    Timestamp ts = new Timestamp(System.currentTimeMillis());
	    rtnStr = sdfCurrent.format(ts.getTime());
	    
		return rtnStr;
    }
	
    
    /** 
     * 스트링이 비어있으면 ""  있으면 입력 str 리턴
     * @param str
     * @return "" or  str
     */
    public static String nvl(String str){
    	if(isEmpty(str)){
    		return "";
    	}else{
    		return str;
    	}
    }
    
    /**
     * 스트링이 비어있다면 0을 리턴
     * 스트링 값이 있다면 Integer형으로 변환해서 리턴
     * Integer로 변환중 NumberFormatException이 발생되면 0을 리턴
     * @param str
     * @return 0 or Integer str
     */
    public static int nvlNum(String str){
    	if(isEmpty(str)){
    		return 0;
    	}else{
    		return Integer.parseInt(str);
    	}
    }
    
    /**
     * 스트링이 비어있다면 0을 리턴
     * 스트링 값이 있다면 Integer형으로 변환해서 리턴
     * Integer로 변환중 NumberFormatException이 발생되면 0을 리턴
     * @param obj
     * @return 0 or Integer str
     */
    public static int nvlNum(Object obj){
    	if(isEmpty(String.valueOf(obj))){
    		return 0;
    	}else{
    		return Integer.parseInt(String.valueOf(obj));
    	}
    }
    
    /**
     * 배열에 key값이 존재 여부 확인
     * @param key
     * @param array
     * @return
     */
    public static boolean isExistArray(String key, String[] array) {
    	
    	boolean result = false;
    	
    	if( array == null ) return result;
    	
    	int arrayCnt = array.length;
    	
    	for( int i=0; i<arrayCnt; i++ ) {
    		if( key.equals(array[i]) ) {
    			result = true;
    			break;
    		}
    	}
    	
    	return result;
    	
    }
    
    /**
     * 배열에 key값이 존재 여부 확인
     * @param key
     * @param array
     * @return
     */
    public static String isExistArray(int key, String[] array) {
    	
    	return isExistArray(String.valueOf(key), array) ? "1" : "0";
    	
    }
    
	/**
	 * html 을 text로 변환
	 * @param str
	 * @return
	 */
	public static String html2text(String str) {

		if( isEmpty(str) ) {
			return str; 
		} else {
//			//HTML = HTML.replaceAll("&", "&amp;");
//			str = str.replaceAll("<", "&lt;");
//			str = str.replaceAll(">", "&gt;");
//			//HTML = HTML.replaceAll("\"", "&quot;");
//			//HTML = HTML.replaceAll("'", "&#39;");
//			str = str.replaceAll("\n", "<br/>");
//			//HTML = HTML.replaceAll(" ", "&nbsp;");
			
			str = str.replaceAll( "&", "&amp;" );
			str = str.replaceAll( "'", "&#039;" );
			str = str.replaceAll( "\"", "&quot;" );
			str= str.replaceAll( "<", "&lt;" );
			str = str.replaceAll( ">", "&gt;" );
			str = str.replaceAll( " ", "&nbsp;" );
			str = str.replaceAll("\n", "<br/>");
			//
			return str;
		}

	}
	
	public static String htmlSpecialChars( String s ) {
		
		if(StringUtils.isNotEmpty(s)){
			s = s.replaceAll( "&amp;", "&" );
			//s = s.replaceAll( "&nbsp;", " " );
			s = s.replaceAll( "&quot;", "\"" );
			s = s.replaceAll( "&#039;", "'" );
			s = s.replaceAll( "`", "'" );
			s = s.replaceAll( "&lt;", "<" );
			s = s.replaceAll( "&gt;", ">" );

			s = s.replaceAll("&middot;", "·");

			s = s.replaceAll( "\\n", "<br/>" );
			s = s.replaceAll( "\r\n", "<br/>");
			
			/*
			s = s.replaceAll( "&", "&amp;" );
			s = s.replaceAll( "'", "&#039;" );
			s = s.replaceAll( "\"", "&quot;" );
			s = s.replaceAll( "<", "&lt;" );
			s = s.replaceAll( ">", "&gt;" );
			s = s.replaceAll( " ", "&nbsp;" );
			s = s.replaceAll( "&amp;&lt;", "<" );
			s = s.replaceAll( "&amp;&gt;", ">" );
			*/
			return s;
		}else{
			return new String();
		}
		
	}
	
	public static String insertHtmlSpecialChars( String s ) {
		
		if(StringUtils.isNotEmpty(s)){
			s = s.replaceAll( "&amp;", "&" );
			s = s.replaceAll( "&nbsp;", " " );
			s = s.replaceAll( "&quot;", "\"" );
			s = s.replaceAll( "&#039;", "'" );
			s = s.replaceAll( "&lt;", "<" );
			s = s.replaceAll( "&gt;", ">" );
			s = s.replaceAll("&middot;", "·");
			
			return s;
		}else{
			return new String();
		}
		
	}	

	/**
	 * 문자열을 특정 byteSize에 맞게 자르고 뒤에 문자열(trail)을 붙여줌
	 * @param str
	 * @param maxByteSize
	 * @param trail
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String cutOffUTF8String(String str, int maxByteSize, String trail) throws UnsupportedEncodingException {

		// 널일 경우에는 그냥 리턴
		if (str == null) return null;
		if (str.length() == 0) return str;

		byte strByte[] = str.getBytes("UTF-8");

		if (strByte.length <= maxByteSize) return str;

		// 마지막 줄임말
		int trailByteSize = 0;

		// 줄임말의 바이트 수 계산
		if (trail != null) trailByteSize = trail.getBytes("UTF-8").length;

		// 실질적으로 포함되는 최대 바이트 수는 trailByte를 뺀 것이다.
		maxByteSize = maxByteSize - trailByteSize;

		int endPos = 0; // 마지막 바이트 위치
		int currByte = 0; // 현재까지 조사한 바이트 수

		for (int i = 0; i < str.length(); i++) {
			// 순차적으로 문자들을 가져옴.
			char ch = str.charAt(i);

			// 이 문자가 몇 바이트로 구성된 UTF-8 코드인지를 검사하여 currByte에 누적 시킨다.
			currByte = currByte + availibleByteNum(ch);

			// 현재까지 조사된 바이트가 maxSize를 넘는다면 이전 단계 까지 누적된 바이트 까지를 유효한 바이트로 간주한다.
			if (currByte > maxByteSize) {
				endPos = currByte - availibleByteNum(ch);
				break;
			}
		}

		// 원래 문자열을 바이트로 가져와서 유효한 바이트 까지 배열 복사를 한다.
		byte newStrByte[] = new byte[endPos];

		System.arraycopy(strByte, 0, newStrByte, 0, endPos);

		String newStr = new String(newStrByte, "UTF-8");

		newStr += trail;

		return newStr;
	
	}
		
	/**
	 * 바이트 검사
	 * @param c
	 * @return
	 */
	public static int availibleByteNum(char c) {

		// UTF-8은 최대 4바이트를 사용하고 ASCII는 1바이트 그외의 문자들은 2~3바이트 까지 조합하여 사용한다.
		// 즉, 어느 나라 문자이냐에 따라서 몇 바이트를 사용하는지 모르기 때문에 하나의 charater가 몇 바이트 대역에
		// 있는지 조사하여 한문자의 바이트를 조사... 이를 더해 나가면 문자 단위로 몇 바이트를 차지 하는지 정확하게 조사할 수 있다.
		int ONE_BYTE_MIN = 0x0000;
		int ONE_BYTE_MAX = 0x007F;
	
		int TWO_BYTE_MIN = 0x0800;
		int TWO_BYTE_MAX = 0x07FF;
	
		int THREE_BYTE_MIN = 0x0800;
		int THREE_BYTE_MAX = 0xFFFF;
	
		int SURROGATE_MIN = 0x10000;
		int SURROGATE_MAX = 0x10FFFF;
	
		int digit = (int) c;
	
		if(ONE_BYTE_MIN <= digit && digit <= ONE_BYTE_MAX) return 1;
		else if(TWO_BYTE_MIN <= digit && digit <= TWO_BYTE_MAX) return 2;
		else if(THREE_BYTE_MIN <= digit && digit <= THREE_BYTE_MAX) return 3;
		else if(SURROGATE_MIN <= digit && digit <= SURROGATE_MAX) return 4;
	
		return -1;
		
	}
	
	/**
	 * 태그를 제거한다
	 * @param str
	 * @return
	 */
	public static String removeTag(String str){
		
		int lt = str.indexOf("<");
	  
		if ( lt != -1 ) {
			int gt = str.indexOf(">", lt);  
			if ( (gt != -1) ) {
				str = str.substring( 0, lt ) + str.substring( gt + 1 );
				str = removeTag(str);
			} 
		}

		return str;
		
	}
    
	/**
	 * 마이그레션 중 에러 유발하는 특이한 특수기호를 제거한다
	 * @param str
	 * @return
	 */
	public static String specialCharacterRemove(String str){
		
		//같아보여도 각기 다른 문자..
		str = str.replaceAll("․","·");
		str = str.replaceAll("󰊱","□");
		str = str.replaceAll("󰊲","□");
		str = str.replaceAll("󰊴","□");
		str = str.replaceAll("󰊱","□");
		str = str.replaceAll("󰊷","□");
		str = str.replaceAll("󰊰","□");
		str = str.replaceAll("󰊰","□");
		str = str.replaceAll("󰋫","□");
		str = str.replaceAll("󰡒","□");
		str = str.replaceAll("󰡓","□");
		str = str.replaceAll("󰏅","□");
		str = str.replaceAll("򬠤otum","dotum");
		str = str.replaceAll("󰁋","□");
		str = str.replaceAll("󰁵","□");
		str = str.replaceAll("󰡐","□");
		str = str.replaceAll("‧","·");
		str = str.replaceAll("󰡑","□");
		str = str.replaceAll("❍","○");
		str = str.replaceAll("󰏚","◎");
		str = str.replaceAll("󰋼","○");
		str = str.replaceAll("󰋻","○");
		str = str.replaceAll("󰋮","○");
		str = str.replaceAll("󰡔","□");
		str = str.replaceAll("󰡕","□");
		str = str.replaceAll("󰋯","□");
		str = str.replaceAll("󰊳","□");
		str = str.replaceAll("⇨","→");
		str = str.replaceAll("󰂕","□");
		
		
		str = str.replaceAll("․","·");
		str = str.replaceAll("'", "\\'");
		
		
		str = str.replace("\\\'", "");
		str = str.replace("\\\"", "");
		if (str.indexOf("javascript") > 0 && str.indexOf("function") > 0){
			str = "";
		}
		
		return str;
		
	}	
	
	/**
	 * 용량을 보기 쉽게 반환
	 * @param str
	 * @return
	 */
	public static String returnFileSize(String fileSize){
		
		String returnStr = "";
		
		int fileMg = Integer.parseInt(fileSize);
		
		if (fileMg >= 0 && fileMg < 1024){ //0보다 크거나 같고 1024바이트보다 작으면 
			
			returnStr = fileMg + "Byte";
			
		}else if (fileMg >= 1024 && fileMg < 1048576){ //1024바이트 보다 크거나 같고 1048576바이트(1MB)보다 작으면 
			
			float convertFileMg = fileMg / 1024;
			
			String returnConvertFileMg = convertFileMg + "";
			
			// ".0" 으로 소주점 한자리 리턴되면 그냥 소수점 뒤에 자름
			if (".0".equals(returnConvertFileMg.substring(returnConvertFileMg.indexOf("."), returnConvertFileMg.length()))){
				returnConvertFileMg = returnConvertFileMg.replace(".0", "");
			}
			
			returnStr = returnConvertFileMg + "Kb";
			
		}else if (fileMg >= 1048576 && fileMg < (1048576 * 1024)){ //1048576바이트(1MB) 보다 크거나 같고 1048576바이트(1MB)보다 작으면 
			
			float convertFileMg = fileMg / 1024 / 1024;
			
			String returnConvertFileMg = convertFileMg + "";
			
			// ".0" 으로 소주점 한자리 리턴되면 그냥 소수점 뒤에 자름
			if (".0".equals(returnConvertFileMg.substring(returnConvertFileMg.indexOf("."), returnConvertFileMg.length()))){
				returnConvertFileMg = returnConvertFileMg.replace(".0", "");
			}
			
			returnStr = returnConvertFileMg + "Mb";
			
		}else{
			
			returnStr = "1Gb 이상";
			
		}

		return returnStr;
		
	}	
	
	/**
	 * 포트를 포함한 접속한 아이피 혹은 도메인을 가져옴
	 * @param request
	 * @return
	 */
	public static String getRequestHost(HttpServletRequest request){
		
		String host = request.getRequestURL().toString();
		host = host.replace("http://", "");
		host = host.replace("https://", "");
		host = host.substring(0, host.indexOf("/"));

		if (host.indexOf(":") > 0){
			host = host.substring(0, host.lastIndexOf(":"));
		}
		
		int port = request.getLocalPort();
		if (port != 80){
			if (host.indexOf(":") < 0){ //기존 host에 포트가 안붙어있으면 포트 붙여라
				host = host + ":" + port;	
			}	
		}

		if (port == 80){
			if (":80".equals(host.substring(host.length() - 3, host.length()))){
				host = host.replace(":80", "");
			}
		}
		
		return host;
	}	
	
	/**
	 * 포트를 포함하지 않은 접속한 아이피 혹은 도메인을 가져옴
	 * @param request
	 * @return
	 */
	public static String getRequestNotPortHost(HttpServletRequest request){
		
		String host = request.getRequestURL().toString();
		host = host.replace("http://", "");
		host = host.replace("https://", "");
		host = host.substring(0, host.indexOf("/"));
		
		if (host.indexOf(":") > 0){
			host = host.substring(0, host.lastIndexOf(":"));
		}

		return host;
	}		
}

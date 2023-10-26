package kr.co.roda.cmmn.util;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class RodaCommonUtil {
	
	/**
     * @Method   		: getParameterToAtturibute
     * @Comment  		: request로 받은 Parameter 또는 Atturibute 정합성 확인 후 값 리턴
     * @Author 	 		: 김민
     * @Date 	 		: 2019. 11. 06.
     * @param 			: HttpServletRequest, String
     * @return 			: String 
     */
	public String getParameterToAtturibute(HttpServletRequest _request, String _strParamKey, String _strInitValue) {
		String strReturnValue = _request.getParameter(_strParamKey) == null ? _strInitValue : _request.getParameter(_strParamKey) == _strInitValue ? _strInitValue : _request.getParameter(_strParamKey).toString();
		
		if( strReturnValue.equals(null) || strReturnValue.length() <= 0 ) {
			strReturnValue = _request.getAttribute(_strParamKey) == null ? _strInitValue : _request.getAttribute(_strParamKey) == _strInitValue ? _strInitValue : _request.getAttribute(_strParamKey).toString();
		}
		
		return strReturnValue;
	}
	
	public String getSessionValue(HttpSession _session, String _strParamKey, String _strInitValue) {
		String strReturnValue = SessionUtil.getSessionValue(_session, _strParamKey) == null ? _strInitValue : SessionUtil.getSessionValue(_session, _strParamKey) == _strInitValue ? _strInitValue : SessionUtil.getSessionValue(_session, _strParamKey).toString();
		return strReturnValue;
	}
	
	public String getCurrentDate() {
		
		String strCurDate = "";
		String strTmp = "";
		
		strTmp = String.format("%d", Calendar.getInstance().get(Calendar.YEAR));
		if( strTmp.length() < 4 ) {
			strTmp += "0";
		}
		
		strCurDate += strTmp + "-";
		strTmp = String.format("%d", Calendar.getInstance().get(Calendar.MONTH)+1);
		if( strTmp.length() < 2 ) {
			strTmp = "0" + strTmp;
		}
		strCurDate += strTmp + "-";
		
		strTmp = String.format("%d", Calendar.getInstance().get(Calendar.DATE));
		if( strTmp.length() < 2 ) {
			strTmp = "0" + strTmp;
		}
		strCurDate += strTmp;
		
		return strCurDate;
	}
	
	public String getClientIP(HttpServletRequest request) throws UnknownHostException {
		
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("Proxy-Client-IP"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("HTTP_CLIENT_IP"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getRemoteAddr(); 
		}
		
		String strTmp = ip.substring(0, 1);
		if( "0".equals(strTmp) ) {
			ip = Inet4Address.getLocalHost().getHostAddress();
		}
		
		
		String uri = (String)request.getAttribute( "javax.servlet.forward.request_uri" );
		
		
		return ip;
    }
	
	public String getRequestUrl(HttpServletRequest request) {
		String requestURL = request.getScheme() + "://" + request.getServerName() + (80==request.getServerPort()?"":":" +request.getServerPort()) + request.getServletPath();
		return requestURL;
	}
	
	/**
	  * 성을 제외한 이름 마스킹 처리 
	  * @param username
	  * @return
	  */
	public String userNmMasking(String username)
	{
		String firstNm = username.substring(0,1);
		int LastNmStartPoint = username.indexOf(firstNm);
		String LastNm = username.substring(LastNmStartPoint+1, username.length());
		  
		String makers = "";
		for (int i = 0; i < LastNm.length(); i++) {
			makers += "O";
		}
		  
		LastNm=LastNm.replace(LastNm, makers);
		String returnValue  = firstNm + LastNm;
		  
		return returnValue;
	}
}

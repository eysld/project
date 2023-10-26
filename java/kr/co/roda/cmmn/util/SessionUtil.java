package kr.co.roda.cmmn.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {

	public static Object getSessionValue(HttpSession session, String value) {
		return session.getAttribute(value);
	}
	
	public static void setSessionValue(HttpSession session, String name, Object value) {
		session.setAttribute(name, value);
	}
	
	public static void removeSessonValue(HttpSession session, String name) {
		session.removeAttribute(name);		
		session.invalidate();		
	}
	
	public static void InvalidateSesson(HttpSession session) {
		session.invalidate();		
	}
}

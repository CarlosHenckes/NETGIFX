package br.com.netgifx.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	private HttpServletResponse resp;
	private HttpServletRequest req;
	static Cookie cookie;
	
	public CookieManager(HttpServletResponse resp, HttpServletRequest req){
		this.resp = resp;
		this.req = req;
	}
	
	public void createCookies(String name, String value){
		cookie = new Cookie(name, value);
		resp.addCookie(cookie);
	}
	
	public String recoverCookie(String name){
		String value = "";
		Cookie[] cookie = req.getCookies();
		if (cookie == null){
			value = "";
		} else {
			for (Cookie c : cookie) {
				if (c.getName().equals(name)){
					value = c.getValue();
				} else if (c.getName().equals(name)){
					value = c.getValue();
				} else if (c.getName().equals(name)){
					value = c.getValue();
				}
			}
		}
		
		return value;
	}
	
	public void destroyCookies(String[] names){
		Cookie[] cookies = req.getCookies();
		if (cookies != null) { 
	        for (int i = 0; i < cookies.length; i++) {
	 
	            Cookie cookie = cookies[i];
	            cookies[i].setValue(null);
	            cookies[i].setMaxAge(0);
	            resp.addCookie(cookie);
	        }
	    }
	}
}

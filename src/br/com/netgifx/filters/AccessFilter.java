package br.com.netgifx.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.netgifx.util.CookieManager;

public class AccessFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// check path
		HttpServletRequest r = (HttpServletRequest) req;
		String path = r.getRequestURI();
		
		if (path.contains("/resources/") || path.contains("/login") || path.contains("/register")){
			chain.doFilter(req, resp);
			
		} else {
			String cook_usr_role = "";
			
			Cookie[] c = r.getCookies();
			if (c != null){
				// recover cookie
				CookieManager cm = new CookieManager((HttpServletResponse) resp, (HttpServletRequest) req);
				cook_usr_role = cm.recoverCookie("netgrole");

				// validate authorization
				if (path.contains("/manager") && cook_usr_role.equalsIgnoreCase("ADMIN")){
					chain.doFilter(req, resp);			
				} else if ((path.contains("/content") || 
						path.contains("/profile") ||
						path.contains("/watch") ||
						path.contains("/browse")) && (cook_usr_role.equalsIgnoreCase("USER") || 
								cook_usr_role.equalsIgnoreCase("ADMIN"))){
						chain.doFilter(req, resp);
				} else {
					((HttpServletResponse) resp).sendRedirect(r.getContextPath() + "/login");
				}
			} else {
				((HttpServletResponse) resp).sendRedirect(r.getContextPath() + "/login");
			}			

		}
	}

}

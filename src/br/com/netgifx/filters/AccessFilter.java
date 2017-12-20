package br.com.netgifx.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import br.com.netgifx.entity.Role;
import br.com.netgifx.util.CookieManager;

public class AccessFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// check path
		HttpServletRequest r = (HttpServletRequest) req;
		String path = r.getRequestURI();
		
		if (path.equalsIgnoreCase("/Netgifx/") ||path.contains("/resources/") || path.contains("/login") || path.contains("/register")){
			chain.doFilter(req, resp);
			
		} else {
			String cook_usr_role = "";
			
			Cookie[] cookie = r.getCookies();
			if (cookie == null){
				((HttpServletResponse) resp).sendRedirect(r.getContextPath() + "/login");
				
			} else {
				// recover cookie
				CookieManager cm = new CookieManager((HttpServletResponse) resp, (HttpServletRequest) req);
				cook_usr_role = cm.recoverCookie("netgrole");
				
				if (cook_usr_role.equalsIgnoreCase(Role.ADMIN.name()) || cook_usr_role.equalsIgnoreCase(Role.USER.name())){
					// has cookie values
					if (path.contains("/manager") && cook_usr_role.equalsIgnoreCase(Role.ADMIN.name())){
						chain.doFilter(req, resp);
					} else if (path.contains("/manager") && cook_usr_role.equalsIgnoreCase(Role.USER.name())){
						((HttpServletResponse) resp).sendRedirect(r.getContextPath() + "/login");
					} else {
						chain.doFilter(req, resp);
					}
				} else 
				{
					((HttpServletResponse) resp).sendRedirect(r.getContextPath() + "/login");
					
				}
			}			

		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	@Override
	public void destroy() {
		
	}
}

package com.project;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/SessionController")
public class SessionController implements Filter {
    static Logger logger = Logger.getLogger("logger");

    public SessionController() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        
        logger.info("In session control filter");
        
        if (session == null) {   
        	logger.severe("User not logged in");	
        	req.getRequestDispatcher("/loginpage").forward(request, response);
        } 
        else {
        	logger.info("User logged in and redirected successfully");	
        	req.getRequestDispatcher("/").forward(request, response);
            chain.doFilter(request, response);
        }
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}

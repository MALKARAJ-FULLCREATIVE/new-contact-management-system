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

import org.mindrot.jbcrypt.BCrypt;

@WebFilter("/RegisterFilter")
public class RegisterFilter implements Filter {

static Logger log = Logger.getLogger("logger");
public RegisterFilter() {
}


public void destroy() {
}
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    if(req.getMethod().equalsIgnoreCase("POST"))
    {

        

String Origin=req.getHeader("Origin");
// String appId=req.getHeader("X-Appengine-Inbound-Appid");
// log.info(appId);

        if(Origin!=null && (Origin.equals("http://localhost:8080") || Origin.equals("https://malkarajtraining12.uc.r.appspot.com")))
        {
            log.info("register API request from same-origin");
            chain.doFilter(request, response);

        
}
else 
{

            String token=req.getHeader("Authorization");
            //log.info("token :"+token);

            if(token!=null &&  BCrypt.checkpw(SyncApp.receiveKey, token))
            {
                log.info("Authorization succesfull");
                chain.doFilter(request, response);
            }
            else
            {
                log.severe("register API request from unknown Origin ");
                res.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
            
        }
        

    }
    else
    {
        chain.doFilter(request, response);

    }


}

public void init(FilterConfig fConfig) throws ServletException {
}
}


package com.tsa.puridiom;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter to check paramters
 * 
 * @author steef
 * @author alexander
 *
 */
public class PuridiomProcureFilter implements Filter {
		 
		
		private String allowedPages = "index.jsp,error.jsp,processing.jsp";
		private String errorPage = "/system/error.jsp";
		
		/**
		 * 
		 */
	    public void init(FilterConfig filterConfig) throws ServletException {
	    	allowedPages = filterConfig.getInitParameter("allowedPages");
	    	errorPage = filterConfig.getInitParameter("error");
	    }
	 
	    /**
	     * 
	     */
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
	    	
	    	/*
	    	Encoder encoder = DefaultEncoder.getInstance();
	    	
//	    	String organizationId = "SRR10P";
	    	
	    	HttpServletRequest httpRequest = (HttpServletRequest) request;
	    	Map<String, String[]> paramters = httpRequest.getParameterMap(); 
//	    	HttpServletResponse httpResponse = (HttpServletResponse) response;
	    	
	    	Enumeration<String> parameterNames = httpRequest.getParameterNames();
	    	
	    	while (parameterNames.hasMoreElements()) {
	    		String parameterName = parameterNames.nextElement();
	    		
	    		String[] values = httpRequest.getParameterValues(parameterName);
	    		
	    		if (values != null) {
                  for (int j = 0; j < values.length; j++) {
                	  String value = values[j];
                	  String valueEncode = encoder.encodeForJavaScript(value);
                	  values[j] = valueEncode;
                  }
	    		}
	    	}
	    	
	    	paramters = httpRequest.getParameterMap();
	    	*/
	    	
	    	HttpServletRequest httpRequest = (HttpServletRequest) request;
	    	
	    	// Call next filter
	    	chain.doFilter(new PuridiomHttpRequest(httpRequest), response);
//    		chain.doFilter(request, response);
	    }
	    
	    /**
	     * 
	     */
	    public void destroy() {

	    }
	
}

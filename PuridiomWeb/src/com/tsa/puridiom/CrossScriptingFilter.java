package com.tsa.puridiom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;


/**
 * Filter to restrict to access to JSP
 * 
 * @author steef
 * @author alexander
 *
 */
public class CrossScriptingFilter implements Filter {


	private String allowedPages = "index.jsp,error.jsp,processing.jsp";
	private String errorPage = "/system/error.jsp";
	private String customPatterns = "";
	private String xssBadValue = "";
	private HashMap<Pattern, String> javaScriptHashMap = new HashMap<Pattern, String>();
	FilterConfig config;
	
	public void setFilterConfig(FilterConfig config) {
		this.config = config;
	}

	public FilterConfig getFilterConfig() {
		return config;
	}


	

	public void init(FilterConfig filterConfig) throws ServletException { 
		allowedPages = filterConfig.getInitParameter("allowedPages");
		errorPage = filterConfig.getInitParameter("error");
		customPatterns = filterConfig.getInitParameter("customPatterns");
		javaScriptHashMap.put(Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE), "");
		javaScriptHashMap.put(Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), "");
		javaScriptHashMap.put(Pattern.compile("</script>", Pattern.CASE_INSENSITIVE), "");
		javaScriptHashMap.put(Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), "");
		javaScriptHashMap.put(Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), "");
		javaScriptHashMap.put(Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), "");
		javaScriptHashMap.put(Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE), "");
		javaScriptHashMap.put(Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE), "");
		javaScriptHashMap.put(Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), "");
		if (customPatterns == null){
			customPatterns = "";
		}
		String[] customPatternsList = customPatterns.split(",");
		for (int i = 0; i < customPatternsList.length; i++) {
			String pattern = customPatternsList[i];
			if (pattern != null && pattern != ""){
				javaScriptHashMap.put(Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), "");
			}
		}
		setFilterConfig(filterConfig);
	}

	
	/**
	 *  Checks our request to find Possible XSS attacks and/or Host Header Attacks
	 *  and to restrict the pages that can be serverd directly
	 */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ServletContext context = getFilterConfig().getServletContext();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String organizationId = "SRR10P";


		boolean allowRequest = false;

		/**
		 * Host Header Attack
		 */
		String uri = httpRequest.getRequestURI();
		Log.debug(this, "starting filter with " + uri);
		if (uri == null) {
			uri = "";
		}
		try {
			errorPage = "/system/error.jsp";
			String hostNameEnable = DictionaryManager.getInstance("host-names", organizationId).getProperty(httpRequest.getServerName().toString(), "");
			if((httpRequest.getHeader("Host").indexOf(httpRequest.getServerName()) >= 0) && !hostNameEnable.equalsIgnoreCase("Y")) {
				hostNameEnable = "Y";
			}

			if (hostNameEnable.equalsIgnoreCase("Y")){
				// Check request base
				if (uri.equals("/puridiom/") || uri.equals("/puridiom/procure")) {
					allowRequest = true;
				}
				// If not allow check by allowed pages
				if (!allowRequest) {
					String[] arrayAllowedPages = allowedPages.split(",");
					for (int i = 0; i < arrayAllowedPages.length; i++) {
						String page = arrayAllowedPages[i];
						if (uri.indexOf(page) >= 0){
							allowRequest = true;
						}
					}
				}
			} else {
				allowRequest = false;				
			}
			Log.error(this, "Host Name: " + httpRequest.getHeader("Host").toString());
			Log.error(this, "Server Name: " + httpRequest.getServerName().toString());
		} catch (Exception e){
			Log.error(this, "ERROR: " + e.toString());
		}

		/**
		 * XSS scripting attack
		 */

		if (allowRequest){
			try {
				if (!checkXSScripting(httpRequest)){
					allowRequest = false;
					Log.error(this, "XSS vulnerability: " + xssBadValue);
					errorPage = "/system/errorXss.jsp";					
					request.setAttribute("XSS", xssBadValue);					
					xssBadValue = "";
					
				}
			} catch (Exception e) {
				Log.error(this, e.toString());
			}
		}
		if (allowRequest) {
			xssBadValue = "";
			// Call next filter
			chain.doFilter(request, response);
		} else {
			Log.info(this, "CrossScriptingFilter - prohibited page: " + uri);
			// redirect to error page
			//String errorPath = httpRequest.getContextPath() + errorPage;
			//httpResponse.sendRedirect(errorPath);
			ServletContext servaletContext = getFilterConfig().getServletContext();
			RequestDispatcher dispatcher = servaletContext.getRequestDispatcher(errorPage);
			dispatcher.forward(request, response);
		}
		
	}
	
	/**
	 * Filters all possible parameters for dangerous code and 
	 * exit if any is found.
	 * */

	private boolean checkXSScripting(HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		Map paramMap = httpRequest.getParameterMap();
		//String[] paramNames = (String[]) paramMap.keySet().toArray(STRING_ARRAY);
		Iterator paramsNamesIterator = paramMap.keySet().iterator();
		while (paramsNamesIterator.hasNext()){
			String paramName = (String)paramsNamesIterator.next();
			if (stripXSS(paramName)){
				String[] values = httpRequest.getParameterValues(paramName);
				if (values != null){
					for (int i = 0; i < values.length; i++)
					{
						String value = values[i];
						if (!stripXSS(value))
						{
							return false;
						}
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Compares the value with our previously defined Patterns
	 */
	private boolean stripXSS(String value) {
		if (value != null) {
			Iterator<Pattern> patternsIterator = javaScriptHashMap.keySet().iterator();
			while(patternsIterator.hasNext()){
				Pattern pattern = patternsIterator.next();
				Matcher matcher = pattern.matcher(value);
				boolean valueMatch = matcher.find();
                if (valueMatch){
                	xssBadValue = value;
                	return false;
                }                  
			}
		}
		return true;
	}

	public void destroy() {

	}

}
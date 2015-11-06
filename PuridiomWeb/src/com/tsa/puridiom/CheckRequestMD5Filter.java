package com.tsa.puridiom;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsa.puridiom.common.utility.TokenProcessor;
import com.tsagate.foundation.utility.Log;

/**
 * Filter to check paramters
 * 
 * @author steef
 * @author alexander
 *
 */
public class CheckRequestMD5Filter implements Filter {

	private String errorPage = "/system/errorXss.jsp";

	/**
	 * 
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		errorPage = filterConfig.getInitParameter("error");
	}

	/**
    * 
    */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestString = "";
		String requestFrontEndMd5 = "";
		Map paramMap = httpRequest.getParameterMap();
		//String[] paramNames = (String[]) paramMap.keySet().toArray(STRING_ARRAY);
		Iterator paramsNamesIterator = paramMap.keySet().iterator();
		boolean firstParameter = true;
		while (paramsNamesIterator.hasNext()){
			String paramName = (String)paramsNamesIterator.next();			
			String value = "";			
			String[] values = (String [])paramMap.get(paramName);
			if (values != null){				
				for (int i = 0; i < values.length; i++){
					if (firstParameter){
						firstParameter = false;
					} else {
						requestString = requestString + "&";
					}
					requestString = requestString + paramName + "=";
					value = values[i];
					if (value != null){
						//sfbm -- serializedFormByMd5
						if (paramName.equalsIgnoreCase("sfbm")){
							if (value.indexOf('M') >= 0){
								String tokenValue = value.substring(0, value.indexOf('M'));
								requestFrontEndMd5 = value.substring(value.indexOf('M') + 1, value.length());
								value = tokenValue;
							}
						}
						String encodedValue = URLEncoder.encode(value, "UTF-8");
						if (value.indexOf("'") >= 0 && encodedValue.indexOf("%27") >= 0){
							encodedValue = encodedValue.replaceAll("%27", "'");
						}
						value = encodedValue;
						
					} else {
						value = "null";
					}
				}								
			}
			//String value = values[i]
			requestString = requestString + value;
			
		}
		
		
		
		TokenProcessor tokenProcessor = TokenProcessor.getInstance();
		//String md5Token = tokenProcessor.generateMD5("nonce=null&auditTransactionId=&auditFields=%7B+ic%3A+'undefined'%7D&auditedFields=&auditTables=&strEnableAuditTrail=&userDateFormat=MM-dd-yyyy&com.tsa.puridiom.TOKEN=96dda7f591866b03721cd096e361e223&UserLog_status=11&ReturnURL=&handler=UserLoginHandler%3B&successPage=menu%2Fmain_menu.jsp&failurePage=%2Fsystem%2Ferror.jsp&userId=&userDepartment=&userLocale=&organizationId=&mailId=judi%40puridiom.com&browseName=&viewType=WIZARD&language=US&userTimeZone=&puridiomSessionId=&loginFailurePage=index.jsp&finalAttemptPage=user%2Fpswd_help_verification.jsp&passwordResetPage=user%2Fchg_pswd.jsp&passwordSecurityResetPage=user%2Fchg_security_pswd.jsp&securityResetPage=user%2Fchg_security_profile.jsp&reviewProfilePage=user%2Fuser_profile.jsp&srchcatalog=null&edoc=null&etype=null&loginId=judi%40puridiom.com&password=WELCOME%401");
		String md5Token = tokenProcessor.generateMD5(requestString);
		System.out.println(md5Token);
		// Call next filter
		//if (md5Token.equalsIgnoreCase(requestFrontEndMd5)){
			chain.doFilter(request, response);
		//} else {
			//String errorPath = httpRequest.getContextPath() + errorPage;
			//httpResponse.sendRedirect(errorPath);
		//}
	}

	/**
    * 
    */
	public void destroy() {

	}

}

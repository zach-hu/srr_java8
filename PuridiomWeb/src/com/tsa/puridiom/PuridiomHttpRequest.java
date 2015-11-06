package com.tsa.puridiom;

import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.esapi.Encoder;
import org.owasp.esapi.reference.DefaultEncoder;

/**
 * PuridiomHttpRequest  which allows you to wrap one request with another,
 * with the objective of check the parameters
 * 
 * @author alexander
 *
 */
public class PuridiomHttpRequest extends HttpServletRequestWrapper {

	Encoder encoder = null;
	
	
	/**
     * A substitution mapping (regular expression to match, replacement) that
     * is used to replace potentially dangerous JavaScript function calls with
     * escaped equivalents that can't be used for malicious purposes.
     */
    protected HashMap<String, String> javaScriptHashMap = new HashMap<String, String>();
	
	/**
	 * 
	 * @param request
	 */
	public PuridiomHttpRequest(HttpServletRequest request) {
		super(request);
		encoder = DefaultEncoder.getInstance();
		
		javaScriptHashMap.put("<(\\s*)(/\\s*)?script(\\s*)>", "<$2script-disabled>");
        javaScriptHashMap.put("%3Cscript%3E", "%3Cscript-disabled%3E");
        javaScriptHashMap.put("alert(\\s*)\\(", "alert[");
        javaScriptHashMap.put("alert%28", "alert%5B");
        javaScriptHashMap.put("document(.*)\\.(.*)cookie", "document cookie");
        javaScriptHashMap.put("eval(\\s*)\\(", "eval[");
        javaScriptHashMap.put("setTimeout(\\s*)\\(", "setTimeout$1[");
        javaScriptHashMap.put("setInterval(\\s*)\\(", "setInterval$1[");
        javaScriptHashMap.put("execScript(\\s*)\\(", "execScript$1[");
        javaScriptHashMap.put("(?i)javascript(?-i):", "javascript ");
        javaScriptHashMap.put("(?i)onclick(?-i)", "oncl1ck");
        javaScriptHashMap.put("(?i)ondblclick(?-i)", "ondblcl1ck");
        javaScriptHashMap.put("(?i)onmouseover(?-i)", "onm0useover");
        javaScriptHashMap.put("(?i)onmousedown(?-i)", "onm0usedown");
        javaScriptHashMap.put("(?i)onmouseup(?-i)", "onm0useup");
        javaScriptHashMap.put("(?i)onmousemove(?-i)", "onm0usemove");
        javaScriptHashMap.put("(?i)onmouseout(?-i)", "onm0useout");
        javaScriptHashMap.put("(?i)onchange(?-i)", "onchahge");
        javaScriptHashMap.put("(?i)onfocus(?-i)", "onf0cus");
        javaScriptHashMap.put("(?i)onblur(?-i)", "onb1ur");
        javaScriptHashMap.put("(?i)onkeypress(?-i)", "onkeyqress");
        javaScriptHashMap.put("(?i)onkeyup(?-i)", "onkeyuq");
        javaScriptHashMap.put("(?i)onkeydown(?-i)", "onkeyd0wn");
        javaScriptHashMap.put("(?i)onload(?-i)", "onl0ad");
        javaScriptHashMap.put("(?i)onreset(?-i)", "onrezet");
        javaScriptHashMap.put("(?i)onselect(?-i)", "onzelect");
        javaScriptHashMap.put("(?i)onsubmit(?-i)", "onsubm1t");
        javaScriptHashMap.put("(?i)onunload(?-i)", "onunl0ad");
        javaScriptHashMap.put("&#x61;&#x6C;&#x65;&#x72;&#x74;", "a1ert");
		
	}

	/**
	 * 
	 */
	public String getParameter(String paramName) {
		String value = super.getParameter(paramName);
		
		if (value != null && value.length() > 0) {
			// Loop through each of the substitution patterns.
			Iterator<String> escapesIterator = javaScriptHashMap.keySet().iterator();
			while (escapesIterator.hasNext()) {
				String patternString = escapesIterator.next();
				Pattern pattern = Pattern.compile(patternString);
			
				boolean valueMatch;
				Matcher matcher = pattern.matcher(value.toLowerCase());
				valueMatch = matcher.find();
				if (valueMatch) {
					value = matcher.replaceAll((String) javaScriptHashMap.get(patternString));
				}
			}
		}
		
		return value;
	}

	/**
	 * 
	 */
	public String[] getParameterValues(String paramName) {
		String values[] = super.getParameterValues(paramName);
		if (values != null) {
            for (int j = 0; j < values.length; j++) {
          	  	String value = values[j];
          	  	if (value != null && value.length() > 0) {
          		  
          	  		// Loop through each of the substitution patterns.
          	  		Iterator<String> escapesIterator = javaScriptHashMap.keySet().iterator();
          	        while (escapesIterator.hasNext()) {
          	        	String patternString = escapesIterator.next();
          	        	Pattern pattern = Pattern.compile(patternString);

          	        	boolean valueMatch;
          	        	Matcher matcher = pattern.matcher(value.toLowerCase());
                        valueMatch = matcher.find();
                        if (valueMatch) {
                        	 String newValue;
                             newValue = matcher.replaceAll((String) javaScriptHashMap.get(patternString));
                             values[j] = newValue;
                        }
//                 		values[j] = valueEncode;
          	        }
          	  }
//          	  String valueEncode = encoder.encodeForHTML(value);
//          	  String valueEncode = encoder.encodeForJavaScript(value);
            }
  		}
		
		return values;
	}
	
}

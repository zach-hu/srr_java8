package com.tsa.puridiom.tags;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.tagext.BodyTagSupport;

public abstract class PuridiomTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	protected String getModule() {
		ServletRequest request = pageContext.getRequest();
		
		return (String) request.getAttribute("module");
	}
	
	protected String getModuleType() {
		ServletRequest request = pageContext.getRequest();
		
		return (String) request.getAttribute("moduleType");
	}
}

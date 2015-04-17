/**
 * @author Jonathan Corbin
 */

package com.tsa.puridiom.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.owasp.esapi.Encoder;
import org.owasp.esapi.reference.DefaultEncoder;

public class HiddenTag extends PuridiomTag {
	private String name 		= null;
	private String value 		= null;
	private String id			= null;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setValue(Object value) {
		if (value != null) this.value = value.toString();
		else this.value = null;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Process the start tag for this instance. This method is invoked by the JSP page implementation object.
	 * The doStartTag method assumes that the properties pageContext and parent have been set. It also assumes that any properties exposed as attributes have been set too. When this method is invoked, the body has not yet been evaluated.

		This method returns Tag.EVAL_BODY_INCLUDE or BodyTag.EVAL_BODY_BUFFERED to indicate that the body of the action should be evaluated or SKIP_BODY to indicate otherwise.

		When a Tag returns EVAL_BODY_INCLUDE the result of evaluating the body (if any) is included into the current "out" JspWriter as it happens and then doEndTag() is invoked.

		BodyTag.EVAL_BODY_BUFFERED is only valid if the tag handler implements BodyTag.

		The JSP container will resynchronize the values of any AT_BEGIN and NESTED variables (defined by the associated TagExtraInfo or TLD) after the invocation of doStartTag(), except for a tag handler implementing BodyTag whose doStartTag() method returns BodyTag.EVAL_BODY_BUFFE

	 * @return - EVAL_BODY_INCLUDE if the tag wants to process body, SKIP_BODY if it does not want to process it.
	 * @throws - JspException - if an error occurred while processing this tag

	 */
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		try {
			Encoder encoder = DefaultEncoder.getInstance();
			out.print("<input type=\"hidden\" name=\"");
			out.print(name);
			out.print("\" value=\"");
			out.print(encoder.encodeForHTMLAttribute(value));
			out.print("\"");
			if (id != null && id.length() > 0) {
				out.print("id=\"" + id + "\"");
			}
			out.print("/>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}

	/**
	 * Process the end tag for this instance. This method is invoked by the JSP page implementation object on all Tag handlers.

		This method will be called after returning from doStartTag. The body of the action may or may not have been evaluated, depending on the return value of doStartTag.

		If this method returns EVAL_PAGE, the rest of the page continues to be evaluated. If this method returns SKIP_PAGE, the rest of the page is not evaluated, the request is completed, and the doEndTag() methods of enclosing tags are not invoked. If this request was forwarded or included from another page (or Servlet), only the current page evaluation is stopped.

		The JSP container will resynchronize the values of any AT_BEGIN and AT_END variables (defined by the associated TagExtraInfo or TLD) after the invocation of doEndTag().

	 * @return - indication of whether to continue evaluating the JSP page.
	 * @throws - JspException - if an error occurred while processing this tag

	 */
	@Override
	public int doEndTag() throws JspException {
		return SKIP_BODY;
	}
}
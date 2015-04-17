/**
 * @author Hardik Shah
 */
package com.tsa.puridiom.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.tsagate.html.HtmlWriter;

public class TdTag extends PuridiomTag {

	/**  */
	private static final long serialVersionUID = 1L;

	private String abbr 	= null;
	private String align 	= null;
	private String axis 	= null;
	private String bgcolor 	= null;
	private String tdChar 	= null;
	private String charoff 	= null;
	private String colspan	= null;
	private String headers 	= null;
	private String height 	= null;
	private String noWrap 	= null;
	private String rowspan 	= null;
	private String scope 	= null;
	private String valign 	= null;
	private String width 	= null;

	private String cssClass = null;
	private String dir 		= null;
	private String id		= null;
	private String lang		= null;
	private String style	= null;
	private String title	= null;

	private String onclick	= null;
	private String ondblclick	= null;
	private String onmousedown	= null;
	private String onmousemove	= null;
	private String onmouseout	= null;
	private String onmouseover	= null;
	private String onmouseup	= null;
	private String onkeydown	= null;
	private String onkeypress	= null;
	private String onkeyup	= null;

	private String field 	= null;
	private String docType	= null;

	/**
	 * Set abbr parameter for TD tag
	 *
	 * @param abbr - parameter to set for abbr
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	/**
	 * Set align parameter for TD tag
	 *
	 * @param align - parameter to set for align
	 */

	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * Set axis parameter for TD tag
	 *
	 * @param axis - parameter to set for axis
	 */
	public void setAxis(String axis) {
		this.axis = axis;
	}

	/**
	 * Set bgcolor parameter for TD tag
	 *
	 * @param bgcolor - parameter to set for bgcolor
	 */
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	/**
	 * Set Char parameter for TD tag
	 *
	 * @param tdChar - parameter to set for char
	 */
	public void setTdChar(String tdChar) {
		this.tdChar = tdChar;
	}

	/**
	 * Set charoff parameter for TD tag
	 *
	 * @param charoff - parameter to set for charoff
	 */
	public void setCharoff(String charoff) {
		this.charoff = charoff;
	}

	/**
	 * Set colspan parameter for TD tag
	 *
	 * @param colspan - parameter to set for colspan
	 */
	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	/**
	 * Set headers parameter for TD tag
	 *
	 * @param headers - parameter to set for headers
	 */
	public void setHeaders(String headers) {
		this.headers = headers;
	}

	/**
	 * Set height parameter for TD tag
	 *
	 * @param height - parameter to set for height
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * Set noWrap parameter for TD tag
	 *
	 * @param noWrap - parameter to set for noWrap
	 */
	public void setNoWrap(String noWrap) {
		this.noWrap = noWrap;
	}

	/**
	 * Set rowspan parameter for TD tag
	 *
	 * @param rowspan - parameter to set for rowspan
	 */
	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	/**
	 * Set scope parameter for TD tag
	 *
	 * @param scope - parameter to set for scope
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * Set valign parameter for TD tag
	 *
	 * @param valign - parameter to set for valign
	 */
	public void setValign(String valign) {
		this.valign = valign;
	}

	/**
	 * Set width parameter for TD tag
	 *
	 * @param width - parameter to set for width
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * Set class parameter for TD tag
	 *
	 * @param cssClass - parameter to set for Class
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * Set dir parameter for TD tag
	 *
	 * @param dir - parameter to set for dir
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * Set id parameter for TD tag
	 *
	 * @param id - parameter to set for id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Set lang parameter for TD tag
	 *
	 * @param lang - parameter to set for lang
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * Set style parameter for TD tag
	 *
	 * @param style - parameter to set for style
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * Set title parameter for TD tag
	 *
	 * @param title - parameter to set for title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set onclick parameter for TD tag event
	 *
	 * @param onclick - parameter to set for onclick
	 */
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	/**
	 * Set ondblclick parameter for TD tag event
	 *
	 * @param ondblclick - parameter to set for ondblclick
	 */
	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}

	/**
	 * Set onmousedown parameter for TD tag event
	 *
	 * @param onmousedown - parameter to set for onmousedown
	 */
	public void setOnmousedown(String onmousedown) {
		this.onmousedown = onmousedown;
	}

	/**
	 * Set onmousemove parameter for TD tag event
	 *
	 * @param onmousemove - parameter to set for onmousemove
	 */
	public void setOnmousemove(String onmousemove) {
		this.onmousemove = onmousemove;
	}

	/**
	 * Set onmouseout parameter for TD tag event
	 *
	 * @param onmouseout - parameter to set for onmouseout
	 */
	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}

	/**
	 * Set onmouseover parameter for TD tag event
	 *
	 * @param onmouseover - parameter to set for onmouseover
	 */
	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	/**
	 * Set onmouseup parameter for TD tag event
	 *
	 * @param onmouseup - parameter to set for onmouseup
	 */
	public void setOnmouseup(String onmouseup) {
		this.onmouseup = onmouseup;
	}

	/**
	 * Set onkeydown parameter for TD tag event
	 *
	 * @param onkeydown - parameter to set for onkeydown
	 */
	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	/**
	 * Set onkeypress parameter for TD tag event
	 *
	 * @param onkeypress - parameter to set for onkeypress
	 */
	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}

	/**
	 * Set onkeyup parameter for TD tag event
	 *
	 * @param onkeyup - parameter to set for onkeyup
	 */
	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	/**
	 * Set field parameter for display label of specified field name
	 *
	 * @param field - parameter to set for field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * Set docType parameter for display label of specified field name and docType
	 *
	 * @param docType - parameter to set for docType
	 */
	public void setDocType(String docType) {
		this.docType = docType;
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
		try {
			String oid = (String)pageContext.getAttribute("oid");
			JspWriter out = pageContext.getOut();

			StringBuffer stringBuffer = new StringBuffer("<td ");

			docType = getModuleType();
			stringBuffer.append(" ");
			String data = "";

			if (docType != null && docType != "") {
				data = HtmlWriter.isVisible(oid, field, docType);
			} else {
				data = HtmlWriter.isVisible(oid,field);
			}

			stringBuffer.append(data);

			if(id!=null)
				stringBuffer.append(" id=\"").append(id).append("\"");

			if(align!=null)
				stringBuffer.append(" align=\"").append(align).append("\"");

			if(abbr!=null)
				stringBuffer.append(" abbr=\"").append(abbr).append("\"");

			if(axis!=null)
				stringBuffer.append(" axis=\"").append(axis).append("\"");

			if(bgcolor!=null)
				stringBuffer.append(" bgcolor=\"").append(bgcolor).append("\"");

			if(tdChar!=null)
				stringBuffer.append(" char=\"").append(tdChar).append("\"");

			if(charoff!=null)
				stringBuffer.append(" charoff=\"").append(charoff).append("\"");

			if(colspan!=null)
				stringBuffer.append(" colspan=\"").append(colspan).append("\"");

			if(headers!=null)
				stringBuffer.append(" headers=\"").append(headers).append("\"");

			if(height!=null)
				stringBuffer.append(" height=\"").append(height).append("\"");

			if(rowspan!=null)
				stringBuffer.append(" rowspan=\"").append(rowspan).append("\"");

			if(scope!=null)
				stringBuffer.append(" scope=\"").append(scope).append("\"");

			if(valign!=null)
				stringBuffer.append(" valign=\"").append(valign).append("\"");

			if(width!=null)
				stringBuffer.append(" width=\"").append(width).append("\"");

			if(cssClass!=null)
				stringBuffer.append(" class=\"").append(cssClass).append("\"");

			if(dir!=null)
				stringBuffer.append(" dir=\"").append(dir).append("\"");

			if(lang!=null)
				stringBuffer.append(" lang=\"").append(lang).append("\"");

			if(style!=null && data.equals(""))
				stringBuffer.append(" style=\"").append(style).append("\"");

			if(title!=null)
				stringBuffer.append(" title=\"").append(title).append("\"");

			if(onclick!=null)
				stringBuffer.append(" onclick=\"").append(onclick).append("\"");

			if(ondblclick!=null)
				stringBuffer.append(" ondblclick=\"").append(ondblclick).append("\"");

			if(onmousedown!=null)
				stringBuffer.append(" onmousedown=\"").append(onmousedown).append("\"");

			if(onmousemove!=null)
				stringBuffer.append(" onmousemove=\"").append(onmousemove).append("\"");

			if(onmouseout!=null)
				stringBuffer.append(" onmouseout=\"").append(onmouseout).append("\"");

			if(onmouseover!=null)
				stringBuffer.append(" onmouseover=\"").append(onmouseover).append("\"");

			if(onmouseup!=null)
				stringBuffer.append(" onmouseup=\"").append(onmouseup).append("\"");

			if(onkeydown!=null)
				stringBuffer.append(" onkeydown=\"").append(onkeydown).append("\"");

			if(onkeypress!=null)
				stringBuffer.append(" onkeypress=\"").append(onkeypress).append("\"");

			if(onkeyup!=null)
				stringBuffer.append(" onkeyup=\"").append(onkeyup).append("\"");


			if(noWrap!=null)
				stringBuffer.append(" nowrap");

			stringBuffer.append(">");
			out.println(stringBuffer.toString());
		} catch (IOException ae) {
			System.out.println("Error : " + ae.getMessage());
		}

		return EVAL_BODY_INCLUDE;
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
		try {
			JspWriter out = pageContext.getOut();
			out.println("</td>");
		} catch (IOException ae) {
			System.out.println("Error : " + ae.getMessage());
		}
		return SKIP_BODY;
	}
}
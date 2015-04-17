
/**
 * @author Jigar Mistry
 */

package com.tsa.puridiom.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.tsagate.html.HtmlWriter;

public class TrTag extends PuridiomTag {

	
	private static final long serialVersionUID = 1L;
	
	private String align 	= null;
	private String bgcolor 	= null;
	private String trChar 	= null;
	private String charoff 	= null;
	private String valign 	= null;
	private String height 	= null;	
	private String width	= null;
	
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
	private String docType 	= null;
	
	/**
	 * Set align parameter for TR tag
	 * 
	 * @param align - parameter to set for align
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * Set bgcolor parameter for TR tag
	 * 
	 * @param bgcolor - parameter to set for bgcolor
	 */
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	/**
	 * Set Char parameter for TR tag
	 * 
	 * @param trChar - parameter to set for Char
	 */
	public void setTrChar(String trChar) {
		this.trChar = trChar;
	}

	/**
	 * Set charoff parameter for TR tag
	 * 
	 * @param charoff - parameter to set for charoff
	 */
	public void setCharoff(String charoff) {
		this.charoff = charoff;
	}

	/**
	 * Set valign parameter for TR tag
	 * 
	 * @param valign - parameter to set for valign
	 */
	public void setValign(String valign) {
		this.valign = valign;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	
	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * Sets the css class.
	 *
	 * @param cssClass the new css class
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
	/**
	 * Set dir parameter for TR tag
	 *  
	 * @param dir - parameter to set for dir
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	/**
	 * Set id parameter for TR tag
	 * 
	 * @param id - parameter to set for id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Set lang parameter for TR tag
	 *  
	 * @param lang - parameter to set for lang
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	/**
	 * Set style parameter for TR tag
	 * 
	 * @param style - parameter to set for style
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	
	/**
	 * Set title parameter for TR tag
	 * 
	 * @param title - parameter to set for title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Set onclick parameter for TR tag event
	 * 
	 * @param onclick - parameter to set for onclick
	 */
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	
	/**
	 * Set ondblclick parameter for TR tag event
	 * 
	 * @param ondblclick - parameter to set for ondblclick
	 */
	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}
	
	/**
	 * Set onmousedown parameter for TR tag event
	 * 
	 * @param onmousedown - parameter to set for onmousedown
	 */
	public void setOnmousedown(String onmousedown) {
		this.onmousedown = onmousedown;
	}
	
	/**
	 * Set onmousemove parameter for TR tag event
	 * 
	 * @param onmousemove - parameter to set for onmousemove
	 */
	public void setOnmousemove(String onmousemove) {
		this.onmousemove = onmousemove;
	}
	
	/**
	 * Set onmouseout parameter for TR tag event
	 * 
	 * @param onmouseout - parameter to set for onmouseout
	 */
	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}
	
	/**
	 * Set onmouseover parameter for TR tag event
	 * 
	 * @param onmouseover - parameter to set for onmouseover
	 */
	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}
	
	/**
	 * Set onmouseup parameter for TR tag event
	 * 
	 * @param onmouseup - parameter to set for onmouseup
	 */
	public void setOnmouseup(String onmouseup) {
		this.onmouseup = onmouseup;
	}
	
	/**
	 * Set onkeydown parameter for TR tag event
	 * 
	 * @param onkeydown - parameter to set for onkeydown
	 */
	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}
	
	/**
	 * Set onkeypress parameter for TR tag event
	 *  
	 * @param onkeypress - parameter to set for onkeypress
	 */
	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}
	
	/**
	 * Set onkeyup parameter for TR tag event
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
			
			StringBuffer stringBuffer = new StringBuffer("<tr ");
			//System.out.println("TR - 1 - " + pageContext.getAttribute("language", 2).toString());
			docType = getModuleType();
			String module = getModule();
			stringBuffer.append(" ");
			
			if (module != null && docType != null) {
				stringBuffer.append(HtmlWriter.isVisible(oid, field, module, docType));
			} else if (module != null) {
				stringBuffer.append(HtmlWriter.isVisible(oid, field, module));
			} else {
				stringBuffer.append(HtmlWriter.isVisible(oid,field));
			}
			
			if(align!=null)
				stringBuffer.append(" align=\"").append(align).append("\"");
			
			if(bgcolor!=null)
				stringBuffer.append(" bgcolor=\"").append(bgcolor).append("\"");
			
			if(trChar!=null)
				stringBuffer.append(" char=\"").append(trChar).append("\"");
			
			if(charoff!=null)
				stringBuffer.append(" charoff=\"").append(charoff).append("\"");
			
			if(valign!=null)
				stringBuffer.append(" valign=\"").append(valign).append("\"");
			
			if(height!=null)
				stringBuffer.append(" height=\"").append(height).append("\"");
			
			if(width!=null)
				stringBuffer.append(" width=\"").append(width).append("\"");
			
			if(dir!=null)
				stringBuffer.append(" dir=\"").append(dir).append("\"");
			
			if(id!=null)
				stringBuffer.append(" id=\"").append(id).append("\"");
			
			if(lang!=null)
				stringBuffer.append(" lang=\"").append(lang).append("\"");
			
			if(style!=null)
				stringBuffer.append(" style=\"").append(style).append("\"");
			
			if(title!=null)
				stringBuffer.append(" title=\"").append(title).append("\"");			
			
			if(cssClass!=null)
				stringBuffer.append(" class=\"").append(cssClass).append("\"");
			
			
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
			out.println("</tr>");
		} catch (IOException ae) {
			System.out.println("Error : " + ae.getMessage());
		}
		return SKIP_BODY;
	}
}
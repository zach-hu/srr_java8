/**
 * @author Jigar Mistry
 */

package com.tsa.puridiom.tags;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.owasp.esapi.Encoder;
import org.owasp.esapi.reference.DefaultEncoder;

import com.tsa.puridiom.browse.Browse;
import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.BrowseUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.properties.DictionaryManager;

public class InputTag extends PuridiomTag {


	private static final long serialVersionUID = 1L;

	Map browseMap = new HashMap();

	private String cols 		= null;
	private String rows 		= null;
	private String accesskey	= null;
	private String readonly 	= null;
	private String lang			= null;

	private String accept 		= null;
	private String align 		= null;
	private String alt	 		= null;
	private String checked		= null;
	private String disabled		= null;
	private String readOnly		= null;
	private String src			= null;
	private String type 		= null;
	private String name 		= null;
	private String size 		= null;
	private String maxLength 	= null;
	private Object value		= null;
	private String style		= null;

	private String browseName	= null;
	private String browseType	= null;

	private String cssClass		= null;
	private String id			= null;
	private String tabIndex 	= null;
	private String title 		= null;

	private String onchange		= null;
	private String onblur		= null;
	private String onclick		= null;
	private String ondblclick	= null;
	private String onfocus		= null;
	private String onmousedown	= null;
	private String onmousemove	= null;
	private String onmouseout	= null;
	private String onmouseover	= null;
	private String onmouseup	= null;
	private String onkeydown	= null;
	private String onkeypress	= null;
	private String onkeyup		= null;
	private String onselect		= null;

	private String field		= null;
	private String wrap			= null;
	private String yesnoradio	= null;
	private String labelName 	= null;

	/**
	 * Set cols parameter for Input tag
	 *
	 * @param cols - parameter to set number of column
	 */
	public void setCols(String cols) {
		this.cols = cols;
	}

	/**
	 * Set rows parameter for Input tag
	 *
	 * @param rows - parameter to set number of rows
	 */
	public void setRows(String rows) {
		this.rows = rows;
	}


	/**
	 * Set accesskey parameter for Input tag
	 *
	 * @param accesskey - parameter to set accesskey
	 */
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}


	/**
	 * Set readonly parameter for Input tag
	 *
	 * @param readonly - parameter to set textarea as readonly
	 */
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}


	/**
	 * Set lang parameter for Input tag
	 *
	 * @param lang - parameter to set lang
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * Set accept parameter for Input tag
	 *
	 * @param accept - parameter to set for accept
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}

	/**
	 * Set align parameter for Input tag
	 *
	 * @param align - parameter to set for align
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * Set alt parameter for Input tag
	 *
	 * @param alt - parameter to set for alt
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * Set checked parameter for Input tag
	 *
	 * @param checked - parameter to set for checked
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}

	/**
	 * Set disabled parameter for Input tag
	 *
	 * @param disabled - parameter to set for disabled
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * Set readOnly parameter for Input tag
	 *
	 * @param readOnly - parameter to set for readOnly
	 */
	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * Set src parameter for Input tag
	 *
	 * @param src - parameter to set for src
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * Set type parameter for Input tag
	 *
	 * @param type - parameter to set for type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Set name parameter for Input tag
	 *
	 * @param name - parameter to set for name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set size parameter for Input tag
	 *
	 * @param size - parameter to set for size
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Set maxLength parameter for Input tag
	 *
	 * @param maxLength - parameter to set for maxLength
	 */
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * Set value parameter for Input tag
	 *
	 * @param value - parameter to set for value
	 */
	public void setValue(Object value) {
		this.value =  value ;
	}

	/**
	 * Set style parameter for Input tag
	 *
	 * @param style - parameter to set for value
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * Set browseName parameter for Input tag
	 *
	 * @param browse - parameter to set for type
	 */
	public void setBrowseName(String browseName) {
		this.browseName = browseName;
	}

	/**
	 * Set Class parameter for Input tag
	 *
	 * @param Class - parameter to set for Class
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * Set id parameter for Input tag
	 *
	 * @param id - parameter to set for id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Set tabIndex parameter for Input tag
	 *
	 * @param tabIndex - parameter to set for tabIndex
	 */
	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}

	/**
	 * Set title parameter for Input tag
	 *
	 * @param title - parameter to set for title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set onchange parameter for Input tag event
	 *
	 * @param onchange - parameter to set for onchange
	 */
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	/**
	 * Set onchange parameter for Input tag event
	 *
	 * @param onchange - parameter to set for onchange
	 */
	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	/**
	 * Set onclick parameter for Input tag event
	 *
	 * @param onclick - parameter to set for onclick
	 */
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	/**
	 * Set ondblclick parameter for Input tag event
	 *
	 * @param ondblclick - parameter to set for ondblclick
	 */
	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}

	/**
	 * Set onfocus parameter for Input tag event
	 *
	 * @param onfocus - parameter to set for onfocus
	 */
	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}

	/**
	 * Set onmousedown parameter for Input tag event
	 *
	 * @param onmousedown - parameter to set for onmousedown
	 */
	public void setOnmousedown(String onmousedown) {
		this.onmousedown = onmousedown;
	}

	/**
	 * Set onmousemove parameter for Input tag event
	 *
	 * @param onmousemove - parameter to set for onmousemove
	 */
	public void setOnmousemove(String onmousemove) {
		this.onmousemove = onmousemove;
	}

	/**
	 * Set onmouseout parameter for Input tag event
	 *
	 * @param onmouseout - parameter to set for onmouseout
	 */
	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}

	/**
	 * Set onmouseover parameter for Input tag event
	 *
	 * @param onmouseover - parameter to set for onmouseover
	 */
	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	/**
	 * Set onmouseup parameter for Input tag event
	 *
	 * @param onmouseup - parameter to set for onmouseup
	 */
	public void setOnmouseup(String onmouseup) {
		this.onmouseup = onmouseup;
	}

	/**
	 * Set onkeydown parameter for Input tag event
	 *
	 * @param onkeydown - parameter to set for onkeydown
	 */
	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	/**
	 * Set onkeypress parameter for Input tag event
	 *
	 * @param onkeypress - parameter to set for onkeypress
	 */
	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}

	/**
	 * Set onkeyup parameter for Input tag event
	 *
	 * @param onkeyup - parameter to set for onkeyup
	 */
	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	/**
	 * Set onselect parameter for Input tag event
	 *
	 * @param onselect - parameter to set for onselect
	 */
	public void setOnselect(String onselect) {
		this.onselect = onselect;
	}

	public void setYesnoradio(String yesnoradio) {
		this.yesnoradio = yesnoradio;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * Gets the field.
	 *
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * Sets the field.
	 *
	 * @param field the new field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * Sets the wrap.
	 *
	 * @param wrap the new wrap
	 */
	public void setWrap(String wrap) {
		this.wrap = wrap;
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
			Encoder encoder = DefaultEncoder.getInstance();
			JspWriter out = pageContext.getOut();
			String oid = pageContext.getAttribute("oid").toString();
            String uid	= (String) pageContext.getAttribute("userId");
			browseMap.put("oid", oid);
			browseMap.put("uid", uid);

			String fieldTypeLabelCode = labelName + "-fieldType";
			type = DictionaryManager.getLabel(oid, fieldTypeLabelCode, type);
			InputType inputType = InputType.valueOf(type.toLowerCase());
			size = (!"text".equals(type)) ? inputType.getSize(inputType) : size;
			type = inputType.getType(inputType);
			StringBuffer stringBuffer=null;
			String moduleType = getModuleType();
			browseType = null;

			if ("dropdown".equalsIgnoreCase(type)){
				stringBuffer = new StringBuffer("<select ");
			}
			else if ("yesnoradio".equalsIgnoreCase(type)) {
				yesnoradio = "enabled";
				type = "radio";
			}
			else if("textarea".equals(type)){
				stringBuffer = new StringBuffer("<textarea ");
			} else {
				stringBuffer = new StringBuffer("<input ");
			}

			//stringBuffer.append(" ").append(docType == null ? HtmlWriter.isVisible(pageContext.getAttribute("oid").toString(),field) : (docType.equalsIgnoreCase("T"))? "" : HtmlWriter.isVisible(pageContext.getAttribute("oid").toString(),field)) ;
			if(type.equals("textarea"))
			{
				if(cols!=null)
					stringBuffer.append(" cols=\"").append(cols).append("\"");

				if(rows!=null)
					stringBuffer.append(" rows=\"").append(rows).append("\"");

				if(accesskey!=null)
					stringBuffer.append(" accesskey=\"").append(accesskey).append("\"");

				if(readonly!=null)
					stringBuffer.append(" readonly=\"").append(readonly).append("\"");

				if(lang!=null)
					stringBuffer.append(" lang=\"").append(lang).append("\"");

				if(wrap!=null)
					stringBuffer.append(" wrap=\"").append(wrap).append("\"");
			}

			//stringBuffer.append(" ").append(docType == null ? HtmlWriter.isVisible(pageContext.getAttribute("oid").toString(),field) : (docType.equalsIgnoreCase("T"))? "" : HtmlWriter.isVisible(pageContext.getAttribute("oid").toString(),field)) ;
			for (int index = 0; index < ((yesnoradio != null) ? 2 : 1); index++) {

				if(index == 0 && (yesnoradio != null)) {
					stringBuffer = new StringBuffer("<input ");
				} else if(yesnoradio != null)
					stringBuffer.append("&nbsp;<input ");

				if (yesnoradio != null) {
					if ("yes".equalsIgnoreCase((String)value)) {
						if (index == 0) {
							checked = "checked";
						} else {
							checked = null;
						}
					} else if ("no".equalsIgnoreCase((String)value)){
						if (index == 1) {
							checked = "checked";
						} else {
							checked = null;
						}
					}
				}

				if (field != null && field.length() > 0 &&
					!DictionaryManager.isReadOnly(pageContext.getRequest().getAttribute("organizationId").toString(), field)) {
					disabled = "true";
				}

				if(accept!=null && !type.equals("textarea"))
					stringBuffer.append(" accept=\"").append(accept).append("\"");

				if(alt!=null && !type.equals("textarea"))
					stringBuffer.append(" alt=\"").append(alt).append("\"");

				if(align!=null && !type.equals("textarea") )
					stringBuffer.append(" align=\"").append(align).append("\"");

				if(checked!=null && (type.equals("checkbox") || type.equals("radio")))
					stringBuffer.append(" checked=\"").append(checked).append("\"");

				if(disabled!=null && !disabled.equals(""))
					stringBuffer.append(" disabled=\"").append(disabled).append("\"");

				if(readOnly!=null)
					stringBuffer.append(" readOnly=\"").append(readOnly).append("\"");

				if(src!=null && !type.equals("textarea"))
					stringBuffer.append(" src=\"").append(src).append("\"");

				if(name!=null)
					stringBuffer.append(" name=\"").append(name).append("\"");

				if(size!=null && !type.equals("textarea") )
					stringBuffer.append(" size=\"").append(size).append("\"");

				if(maxLength!=null && !type.equals("textarea"))
					stringBuffer.append(" maxLength=\"").append(maxLength).append("\"");

				if(index == 0 && (yesnoradio != null))
					stringBuffer.append(" value=\"yes\"");
				else if(index == 1 && (yesnoradio != null))
					stringBuffer.append(" value=\"no\"");
				else if(value!=null && !type.equals("textarea"))
					stringBuffer.append(" value=\"").append( encoder.encodeForHTMLAttribute(String.valueOf(value))).append("\"");

				if(style!=null)
					stringBuffer.append(" style=\"").append( String.valueOf(style)).append("\"");

				if(id!=null)
					stringBuffer.append(" id=\"").append(id).append("\"");

				if(cssClass!=null)
					stringBuffer.append(" class=\"").append(cssClass).append("\"");

				if(tabIndex!=null)
					stringBuffer.append(" tabIndex=\"").append(tabIndex).append("\"");

				if(title!=null)
					stringBuffer.append(" title=\"").append(title).append("\"");

				if(type!=null)
					stringBuffer.append(" type=\"").append(type).append("\"");

				if (!"dropdown".equalsIgnoreCase(type)){
					if(onchange!=null)
						stringBuffer.append(" onchange=\"").append(onchange).append("\"");

					if(onblur!=null)
						stringBuffer.append(" onblur=\"").append(onblur).append("\"");

					if(onclick!=null)
						stringBuffer.append(" onclick=\"").append(onclick).append("\"");

					if(ondblclick!=null)
						stringBuffer.append(" ondblclick=\"").append(ondblclick).append("\"");

					if(onfocus!=null)
						stringBuffer.append(" onfocus=\"").append(onfocus).append("\"");

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

					if(onselect!=null)
						stringBuffer.append(" onselect=\"").append(onselect).append("\"");
				}
				else {
					String linkLabelName=(moduleType !=null && moduleType != "") ? linkLabelName=moduleType+"-"+labelName : labelName;
				        try
				    	{
				        	browseName = DictionaryManager.getLabel(oid, linkLabelName + "-browseName", browseName);
				        	browseType = DictionaryManager.getLabel(oid, linkLabelName + "-browseType", browseType);

				        	if (!"".equals(browseName)){
				        		if ("stdtable".equalsIgnoreCase(browseName)) {
				        			if (browseType != null) {
				        				browseMap = browseDropDown(browseMap);
				        			}
				        		}
				        		else
				        			browseMap = browseDropDown(browseMap);
				        	}
			            }
				        catch (Exception e)
				        {

				        }
				}

				stringBuffer.append("/>");
				if(index == 0 && (yesnoradio != null)) {
					stringBuffer.append("&nbsp;Yes");
				} else if(yesnoradio != null)
					stringBuffer.append("&nbsp;No");
			}
			out.println(stringBuffer.toString());
		} catch (Exception ae) {
			ae.printStackTrace();
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
		if("textarea".equals(type)){
			try {
				JspWriter out = pageContext.getOut();
				out.println("</textarea>");
			} catch (IOException ae) {
				ae.printStackTrace();
			}
		}
		// If it is a dropdown, then print an empty option and conduct the browse depending on browseName values
		else if ("dropdown".equals(type)){
			try {
				JspWriter out = pageContext.getOut();
				String html = "";

				if ("".equals(value))
					html = html + "<option SELECTED value=\"\"/></option>";
				else
					html = html + "<option value=\"\"/></option>";

				if (!"".equals(browseName)){
	        		if ("stdtable".equalsIgnoreCase(browseName)) {
	        			if (browseType != null) {
	        				html = printBrowse(html);
	        			}
	        		}
	        		else
	        			html = printBrowse(html);
	        	}
				out.print(html);

			} catch (IOException ae) {
				ae.printStackTrace();
			}
		}
		return SKIP_BODY;
	}

	//Conduct the dropdown Browse
	private Map browseDropDown(Map incomingRequest){
    	try
		{
    		incomingRequest.put("allowBrowse", "true");
    		incomingRequest.put("browseName", browseName);
    		incomingRequest.put("formField", labelName);
    		incomingRequest.put("pageSize", "100");
    		//Filter setup only if Standard table
    		if ("stdtable".equals(browseName.toLowerCase())) {
	            incomingRequest.put("colname", "StdTable_id_tableType");
	            incomingRequest.put("operator", "=");
	            incomingRequest.put("filter_txt", browseType);
	            incomingRequest.put("logicalOperator", "AND");
	            incomingRequest.put("originalFilter", "Y");
	            incomingRequest.put("sort", "N");
	            incomingRequest.put("organizationId", ((String)browseMap.get("oid")));
    		}

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)browseMap.get("oid"));
			PuridiomProcess process = processLoader.loadProcess("browse-retrieve.xml");
			process.executeProcess(incomingRequest);
		}
		catch (Exception exception)
		{
			System.err.println(exception.toString());
		}
		return incomingRequest;
    }

	// Print out the results from the dropdown browse
	private String printBrowse(String html) {
		try{
			String oid = (String) browseMap.get("oid");
	        String uid	= (String) browseMap.get("uid");

	        Browse browse = (Browse) browseMap.get("browse");
	        BrowseObject browseObject = (BrowseObject) browseMap.get("browseObject");
	        BrowseColumn browseColumns[] = browseObject.getBrowseColumns();

			List list = browse.getPageResults();

			for (int il = 0; il < list.size(); il++) {
				Object object = list.get(il);

	            for (int i = 1; i < browseColumns.length; i++){
	            	BrowseColumn column = browseColumns[i];
	            	Object result = null;
					String	resultString = "";
					String valueTrim = "";

					try {
						result = BrowseUtility.getTestColumnValue(column, object, oid, browseColumns, browse.getBrowseId(), uid);
					} catch (Exception e) {
						e.printStackTrace();
						return "<option value=\"\"/></option>";
					}

					if (result instanceof String) {
						resultString = (String) result;
					} else if (result instanceof BigDecimal) {
						resultString = ((BigDecimal) result).toString();
					}

					if (i == 1){
						// Truncates the resultString and the value so that they can be properly compared
						valueTrim = (String) value;
						if (valueTrim.length() > 15)
							valueTrim = valueTrim.substring(0, 15);
						if (resultString.length() > 15)
							resultString = resultString.substring(0,15);

						if (resultString.equalsIgnoreCase(valueTrim))
							html = html + "<option SELECTED value=" + resultString + ">";
						else
							html = html + "<option value=" + resultString + ">";
					}
					else if (i == 2)
						if (resultString.length() > 30)
							html = html + resultString.substring(0, 30) + "</option> ";
						else
							html = html + resultString;
	            	}
				}
		}
		catch (Exception e){
			System.err.println(e.toString());
			return "<option value=\"\"/></option>";
		}
		return html;

	}
}
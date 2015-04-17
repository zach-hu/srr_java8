/**
 *
 * @author Hardik Shah
 */
package com.tsa.puridiom.tags;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tsagate.properties.DictionaryManager;

public class LabelTag extends PuridiomTag {

	/**  */
	private static final long serialVersionUID = 1L;

	private String field 		  	= null;
	private String labelName      	= null;
	private String defaultString  	= null;
	private Boolean checkRequired 	= true;
	private String docType        	= null;
	private Boolean checkLink 		= false;
	private String noinstance      	= null;
	private String visible      	= null;
	private Boolean isLink    		= true;
	private Boolean isError 		= false;
	private String hoverHelp    	= null;
	private String browseName    	= null;
	private String browseType    	= null;
	private String fieldName    	= null;

	/**
	 * Set the labelName or fieldName for getting instance of label.
	 *
	 * @param field - fieldName or labelName
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * Set the labelName or fieldName for getting instance of label.
	 *
	 * @param labelName  - labelName for getting instance.
	 *
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 *  Set the defaultString for getting instance of label.
	 *
	 * @param defaultString - defaultString for getting instance.
	 */
	public void setDefaultString(String defaultString) {
		this.defaultString = defaultString;
	}

	/**
	 * set the checkRequired parameter for getting pageContext attribute
	 *
	 * @param checkRequired - String type of parameter for getting pageContext attribute
	 */
	public void setCheckRequired(String checkRequired) {
		if(!checkRequired.equalsIgnoreCase("true") && !checkRequired.equalsIgnoreCase("false"))
			checkRequired = pageContext.getAttribute(checkRequired, 2).toString();
		this.checkRequired = Boolean.valueOf(checkRequired);
	}

	/**
	 * Set the docType for getting label instance of that specific docType
	 *
	 * @param docType - parameter for getting label instance
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}

	/**
	 * Set the checkLink boolean parameter which return label instance if its true
	 *
	 * @param checkLink - getting label instance by set this parameter
	 */
	public void setCheckLink(String checkLink) {
		this.checkLink = Boolean.valueOf(checkLink);
	}

	/**
	 * Set noinstance parameter for getting label instance
	 * There are two ways to get label instance i.e using getLablesInstance method and without using getLabelsInstance method
	 *
	 * Example :
	 * if noinstance parameter is set than following method is called.
	 *
	 *  DictionaryManager.getLabelsInstance(oid, language).getLabel(String,String....)
	 *
	 *  and if noinstance parameter is not set than following method is called.
	 *
	 *  DictionaryManager.getLabel(String,String....)
	 *
	 * @param noinstance - parameter for getting label instance
	 */
	public void setNoinstance(String noinstance) {
		this.noinstance = noinstance;
	}

	/**
	 * Sets the visible.
	 *
	 * @param visible the new visible
	 */
	public void setVisible(String visible) {
		this.visible = visible;
	}

	/**
	 * Sets the link available.
	 *
	 * @param isLink the new link available
	 */
	public void setIsLink(Boolean isLink) {
		this.isLink = isLink;
	}

	/**
	 * Sets the checks if is error.
	 *
	 * @param isError the new checks if is error
	 */
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	/**
	 * Sets the hover help.
	 *
	 * @param hoverHelp the new hover help
	 */
	public void setHoverHelp(String hoverHelp) {
		this.hoverHelp = hoverHelp;
	}

	/**
	 * Sets the browse name.
	 *
	 * @param browseName the new browse name
	 */
	public void setBrowseName(String browseName) {
		this.browseName = browseName;
	}

	/**
	 * Sets the browse type.
	 *
	 * @param browseType the new browse type
	 */
	public void setBrowseType(String browseType) {
		this.browseType = browseType;
	}

	/**
	 * Sets the field name.
	 *
	 * @param fieldName the new field name
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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
			JspWriter out = pageContext.getOut();
			StringBuffer stringBuffer = new StringBuffer();

			String language = "";//(pageContext.getAttribute("language", 2) != null) ? pageContext.getAttribute("language", 2).toString() : pageContext.getAttribute("language").toString();
			String oid = pageContext.getAttribute("oid").toString();

			//String module = getModule();
			String moduleType = getModuleType();

			String label = "";

			if(checkLink)
			{
				 if(DictionaryManager.isLink(oid, field))
					 stringBuffer.append("<a href=\"javascript: browseStd('RequisitionHeader_udf4Code', 'RQ04'); void(0);\" title=\"Click here to choose the value for ").append(DictionaryManager.getLabel(oid, "req-RQ04", "UDF4", false)).append(" for this requisition or enter the value in the box on the right.\">").append(DictionaryManager.getLabel(oid, "req-RQ04", "UDF4", true)).append("</a>");
			}
			else
			{
				if(visible == null || !visible.equals("N"))
				{
					//labelName = module + "-" + labelName;

					if(moduleType != null)
						label = (noinstance == null) ? DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelName, defaultString, moduleType, checkRequired) : DictionaryManager.getLabel(oid, labelName, defaultString, moduleType, checkRequired);
					else
						label = (noinstance == null) ? DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, labelName, defaultString) : DictionaryManager.getLabel(oid, labelName, defaultString);

					if(isError)
						label = setDOMAttribute(label, "class", "error");

					if (hoverHelp !=null && hoverHelp.length() > 0)
						label = setDOMAttribute(label, "title", hoverHelp);

					String linkLabelName=(moduleType !=null && moduleType != "") ? linkLabelName=moduleType+"-"+labelName : labelName;
					if(DictionaryManager.isLink(oid, linkLabelName) && fieldName != null && fieldName.length() > 0)
					{
				        try
				    	{
				        	browseName = DictionaryManager.getLabel(oid, linkLabelName + "-browseName", browseName);
				        	browseType = DictionaryManager.getLabel(oid, linkLabelName + "-browseType", browseType);
			                if (browseType == null)
			                	browseType = "";

			                if (browseName != null && browseName.length() > 0) {
			                	label = "<a href=\"javascript:browseSetup(\'" + browseName + "\', \'" + browseType + "\', \'" + fieldName + "\'); void(0);\">" + label + "</a>";
			                }
			            }
				        catch (Exception e)
				        {

				        }
					}
					stringBuffer.append(label);
				}
			}
			out.print(stringBuffer.toString());
		}
		catch (IOException ae)
		{
			System.out.println("Error : " + ae.getMessage());
		}
		return SKIP_BODY;
	}

	/**
	 * Sets the dom attribute.
	 *
	 * @param dataObjectModel the dOM string
	 * @param attributeName the attribute name
	 * @param attributeValue the attribute value
	 * @return the string
	 */
	public String setDOMAttribute(String dataObjectModel, String attributeName,String attributeValue) {
		try {
			//String firstDOMString=dataObjectModel;
			if (!dataObjectModel.startsWith("<"))
				dataObjectModel = "<"+"font"+">" + dataObjectModel + "<"+"/"+"font>";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(dataObjectModel.getBytes()));
			NodeList nodeList = document.getElementsByTagName("font");

			String nodeContent []=new String[nodeList.getLength()];
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				if(element !=null)
					nodeContent[i] =getNodeTextContent(dataObjectModel);
				else
					nodeContent[i]="";
				if (element.getAttribute(attributeName) == null || element.getAttribute(attributeName).equals("")) {
					if(attributeName !=null && attributeName.length()>0)
						element.setAttribute(attributeName, attributeValue);
				}
			}

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node=nodeList.item(i);
				String nodeName =node.getNodeName();
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("<").append(nodeName);
				NamedNodeMap attrs = node.getAttributes();

			    if (attrs != null) {
			      for (int j = 0; j < attrs.getLength(); j++) {
			        Node attr = attrs.item(j);
			        stringBuffer.append(' ').append(attr.getNodeName()).append("=\"").append(attr.getNodeValue()).append("\"");
			      }
			    }
			    stringBuffer.append(">").append(nodeContent[i]);
			    stringBuffer.append("<"+"/").append(nodeName).append(">");
			    dataObjectModel =stringBuffer.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dataObjectModel;
	}
	public String getNodeTextContent(String nodeDOMString)
	{
		String nodeText="";
		try
		{
			if(nodeDOMString !=null && nodeDOMString.length()>0)
			{
				nodeText=nodeDOMString.substring(nodeDOMString.indexOf(">")+1);
				nodeText=nodeText.substring(0,nodeText.indexOf("<"));
			}
		}
		catch (Exception e)
		{

		}
		nodeText = (!nodeText.equals("")) ? nodeText : nodeDOMString;
		return nodeText;
	}
}

/*
 * Created on Aug 12, 2004
 *
 * @author  * renzo
 * project: HiltonWeb
 * package com.tsa.puridiom.html;.HtmlWriter.java
 *
 */
package com.tsagate.html;

import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 */
public class HtmlWriter
{
	public void replaceCharacter(String text)
	{
		StringBuffer sbText = new StringBuffer(text);

		text = text.replaceAll("~", "'");
		text = text.replaceAll("\r\n", " ");
		text = text.replaceAll("\r", " ");
		text = text.replaceAll("\n", " ");
	}
    public static String outDivVisible(String organizationId, String labelName)
    {
        StringBuffer divSB = new StringBuffer();
        divSB.append("<div style='visibility:");
        divSB.append(DictionaryManager.getLabel(organizationId, labelName + "-visible", "visible"));
        divSB.append(";'>");

        return divSB.toString();
    }


    public static String isVisible(String organizationId,   String labelName, String docType)
    {
    	String visible = DictionaryManager.getLabel(organizationId,
    			docType + "-" + labelName + "-visible", "visible");
    	
    	String style = "";
    	
    	if ("hidden".equalsIgnoreCase(visible)) {
    		style = "style=\"visibility:hidden; display:none;\"";
    	}

    	return style;
    }
    
    public static String isVisible(String organizationId,   String labelName, String module, String docType)
    {
    	String visible = DictionaryManager.getLabel(organizationId,
    			 labelName + "-visible", "", docType + "-" + module);

    	String style = "";
    	//System.out.println( docType + "-" + module + "-" + labelName + ":" + visible);
    	
    	if ("hidden".equalsIgnoreCase(visible)) {
    		style = "style=\"visibility:hidden; display:none;\"";
    	}

    	return style;
    }

    public static String isVisible(String organizationId, String labelName)
    {
      StringBuffer style = new StringBuffer();
      if(!DictionaryManager.isVisible(organizationId, labelName))
      {
          style.append("style=\"visibility:hidden; display:none;\"");
      }

      return style.toString();
    }

    public static String isReadOnly(String organizationId, String labelName, String docType)
    {
    	String readOnly = isReadOnly(organizationId,  docType + "-" + labelName);
    	if (readOnly.length() == 0)
    	{
    		readOnly = isReadOnly(organizationId, labelName);
    	}
    	return readOnly;
    }    
    
    public static String isReadOnly(String organizationId, String labelName)
    {
      StringBuffer readOnly = new StringBuffer();
      if(!DictionaryManager.isReadOnly(organizationId, labelName))
      {
    	  readOnly.append("disabled");
      }
      return readOnly.toString();
    }

    public static String cellVisibility(String organizationId, String labelName)
    {
        StringBuffer sb = new StringBuffer();
        if(!DictionaryManager.isVisible(organizationId, labelName))
        {
            sb.append("myCell.style.visibility = \"hidden\";");
        }
        return sb.toString();
    }
    public static String cellDisplay(String organizationId, String labelName)
    {
        StringBuffer sb = new StringBuffer();
        if(!DictionaryManager.isVisible(organizationId, labelName))
        {
            sb.append("myCell.style.display = \"none\";");
        }
        return sb.toString();
    }

    public static String onChangeUpdated()
    {
        StringBuffer sb = new StringBuffer(" onchange=\"updated();\"");
        return sb.toString();
    }
}

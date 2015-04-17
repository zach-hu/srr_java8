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
public class HtmlWriterLanguage
{
	public void replaceCharacter(String text)
	{
		StringBuffer sbText = new StringBuffer(text);

		text = text.replaceAll("~", "'");
		text = text.replaceAll("\r\n", " ");
		text = text.replaceAll("\r", " ");
		text = text.replaceAll("\n", " ");
	}
    public static String outDivVisible(String language, String organizationId, String labelName)
    {
        StringBuffer divSB = new StringBuffer();
        divSB.append("<div style='visibility:");
        divSB.append(DictionaryManager.getLabelsInstance(organizationId, language).getLabel(organizationId, labelName + "-visible", "visible"));
        divSB.append(";'>");

        return divSB.toString();
    }


    public static String isVisible(String language, String organizationId,   String labelName, String docType)
    {
//    	if (docType.equals("CT") || docType.equals("O") || docType.equals("P")) {
//    		return isVisible(organizationId, labelName + "-" + docType);
//    	} else {
//    		return isVisible(organizationId, labelName);
//    	}
    	String style = isVisible(language, organizationId, docType + "-" + labelName);

    	if (style.length() == 0)
    	{
    		style = isVisible(language, organizationId, labelName);
    	}

    	return style;
    }

    public static String isVisible(String language, String organizationId, String labelName)
    {
      StringBuffer style = new StringBuffer();
      if(!DictionaryManager.getLabelsInstance(organizationId, language).isVisible(organizationId, labelName))
      {
          style.append("style=\"visibility:hidden; display:none;\"");
      }

      return style.toString();
    }

    public static String cellVisibility(String language, String organizationId, String labelName)
    {
        StringBuffer sb = new StringBuffer();
        if(!DictionaryManager.getLabelsInstance(organizationId, language).isVisible(organizationId, labelName))
        {
            sb.append("myCell.style.visibility = \"hidden\";");
        }
        return sb.toString();
    }
    public static String cellDisplay(String language, String organizationId, String labelName)
    {
        StringBuffer sb = new StringBuffer();
        if(!DictionaryManager.getLabelsInstance(organizationId, language).isVisible(organizationId, labelName))
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

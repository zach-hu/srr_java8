/*
 * Created on Aug 4, 2004
 *
 * @author  * renzo
 * project: TsaProperties
 * package com.tsagate.properties.DictionaryManager.java
 *
 */
package com.tsagate.properties;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DictionaryManager
{
	private static Set validOrganizations;
	
	static {
		validOrganizations = new HashSet();
		validOrganizations.add("PURIDIOM");
		validOrganizations.add("puridiom");
		validOrganizations.add("SRR10P");
		validOrganizations.add("srr10p");
	}
    protected static DictionaryManager instance = null;
    protected HashMap bundleList = null;

    protected DictionaryManager()
    {
        this.bundleList = new HashMap();
    }

    protected static DictionaryManager getInstance()
    {
        if (DictionaryManager.instance == null)
        {
            DictionaryManager.instance = new DictionaryManager();
        }

        return DictionaryManager.instance;
    }
    public static void getInstance(boolean getNew)
    {
        DictionaryManager.instance = new DictionaryManager();
    }
    public static void refresh()
    {
        System.out.println("refreshing dictionaries!");
        DictionaryManager.instance = null;
    }
    public void refresh(String organizationId, String bundleName)
    {
    	if (!validOrganizations.contains(organizationId)) {
    		organizationId = "PURIDIOM";
    	}
    	
    	String name = "";
        if(bundleName.length() > 0)
        {
        	name = bundleName + organizationId;
        }
        name = name.toUpperCase();
        this.bundleList.put(name, null);
    }
/*
    public static Dictionary getInstance(String bundle)
    {
        String type = "";
        if(bundle.indexOf("labels") > 0)
        {
            type = "labels";
        }
        else
        {
            type = "host";
        }
        return DictionaryManager.getInstance(type, bundle);
    }
*/

    public static LabelsDictionary getLabelsInstance(String organizationId, String language)
    {
    	  return DictionaryManager.getInstance().getLabelsBundle(organizationId, language);

    }//end getLabelsInstance

   public LabelsDictionary getLabelsBundle(String organizationId, String language)
   {
	   organizationId = "SRR10P";
	   
	   String name = "";
       name = "labels" + organizationId + language;
       name = name.toUpperCase();
       //System.out.println("DictionaryManager looking for: " + name);

       LabelsDictionary loader = (LabelsDictionary)this.bundleList.get(name);

       if(loader == null)
       {
    	   loader = DictionaryFactory.getLabelsDictionary(organizationId, language);
           this.bundleList.put(name, loader);
       }
       return loader;
   }//END getLabelsBundle

    public static Dictionary getInstance(String bundle, String customer)
    {
        return DictionaryManager.getInstance().getbundle(bundle, customer);
    }

    public Dictionary getbundle(String bundleName, String customerId)
    {
    	if (!validOrganizations.contains(customerId)) {
    		customerId = "PURIDIOM";
    	}
    	String name = "";
        if(bundleName.length() > 0)
        {
            name = bundleName + customerId;
        }
        name = name.toUpperCase();
        //System.out.println("DictionaryManager looking for: " + name);

        Dictionary loader = (Dictionary)this.bundleList.get(name);

        if(loader == null)
        {
        	loader = DictionaryFactory.getDictionary(bundleName, customerId);

            this.bundleList.put(name, loader);
        }
        return loader;
    }

    public Dictionary getbundle(String bundleName)
    {
    	Dictionary loader = (Dictionary)this.bundleList.get(bundleName);

        if(loader == null)
        {
        	loader = new Dictionary(bundleName);
            this.bundleList.put(bundleName, loader);
        }
        return loader;
    }

    /**
     * gets a new Dictionary.
     * @param bundle
     * @param getNew
     * @return
     */
    public Dictionary getBundle(String bundle, boolean getNew)
    {
        Dictionary dictionary = (Dictionary)this.bundleList.get(bundle);

        if(dictionary == null || getNew)
        {
        	dictionary = new Dictionary(bundle);
            this.bundleList.put(bundle, dictionary);
        }

        return dictionary;
    }
    public static void main(String[] args)
    {
        //DictionaryManager properties = DictionaryManager.getInstance("tsagate", "axa");
//	  System.out.println(properties.getbundle("tsagate").getProperty("default-database-configuration", "default"));
        Dictionary dic = DictionaryManager.getInstance("host", "tsagate");
        //System.out.println(dic.getProperty("steps-xml-path", "axa"));
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
       return this.bundleList.toString();
    }
    public static String getLabel(String organizationId, String labelName, String defaultString)
    {
    	organizationId = "SRR10P";
        return DictionaryManager.getInstance("labels", organizationId).getLabelProperty(labelName, defaultString);
    }

    public static String getLabel(String organizationId,  String labelName, String defaultString, String docType,boolean checkRequired)
    {
    	return getLabel(organizationId, docType + "-" + labelName, getLabel(organizationId, labelName,defaultString,checkRequired),checkRequired) ;
    }

    public static String getLabel(String organizationId,  String labelName, String defaultString, String docType)
    {
    	return getLabel(organizationId, docType + "-" + labelName, getLabel(organizationId, labelName,defaultString)) ;
    }

    public static String getLabel(String organizationId, String labelName, String defaultString, boolean checkRequired)
    {
        String	label = DictionaryManager.getLabel(organizationId, labelName, defaultString);
        String	requiredIndicator = DictionaryManager.getInstance("labels", organizationId).getLabelProperty("required-field-indicator", "highlight");
        String	hoverHelp = "";
        try
        {
        	hoverHelp = DictionaryManager.getInstance("labels", organizationId).getLabelProperty(labelName + "-alt", "");
        }
        catch (Exception e) { }

        if (checkRequired)
        {
            boolean isRequired = false;
            try
            {
                isRequired = DictionaryManager.getInstance("labels", organizationId).getLabelProperty(labelName + "-required", "false").equalsIgnoreCase("true");
            }
            catch (Exception e) { }

            if (isRequired && (requiredIndicator != null || requiredIndicator.trim().length() > 0))
            {
                if (requiredIndicator.equalsIgnoreCase("highlight"))
                {
                    label = "<font class='requiredLabelHighLight' title='" + hoverHelp + "'>" + label + "</font>";
                }
                else if (requiredIndicator.equalsIgnoreCase("font-color"))
                {
                    label = "<font class='requiredLabelColor' title='" + hoverHelp + "'>" + label + "</font>";
                }
                else if (requiredIndicator.equalsIgnoreCase("both"))
                {
                    label = "<font class='requiredLabel' title='" + hoverHelp + "'>" + label + "</font>";
                }
                else if (hoverHelp.length() > 0)
                {
                    label = "<font title='" + hoverHelp + "'>" + requiredIndicator + label + "</font>";
                }
                else
                {
                    label = requiredIndicator + label;
                }
            }
            else if (hoverHelp.length() > 0)
            {
                label = "<font title='" + hoverHelp + "'>" + label + "</font>";
            }
        }
        else if (hoverHelp.length() > 0)
        {
        	label = "<font title='" + hoverHelp + "'>" + label + "</font>";
        }
        return label;
    }
    public static String getLabel(String organizationId, String labelName, String defaultString, boolean checkRequired, boolean buildLink, String fieldName)
    {
    	String	label = DictionaryManager.getLabel(organizationId, labelName, defaultString, checkRequired);
    	if (buildLink && DictionaryManager.isLink(organizationId, labelName))
        {
            try
    		{
                String browseName = DictionaryManager.getInstance("labels", organizationId).getLabelProperty(labelName + "-browseName", "");
                String browseType = DictionaryManager.getInstance("labels", organizationId).getLabelProperty(labelName + "-browseType", "");

                if (browseName != null && browseName.length() > 0) {
                	if (browseType == null) {
                		browseType = "";
                	}
                	label = "<a href=\"javascript:browseSetup('" + browseName + "', '" + browseType + "', '" + fieldName + "'); void(0);\">" + label + "</a>";
                }
            }
            catch (Exception e) { }
        }
    	return label;
    }
    public static boolean isVisible(String organizationId, String labelName)
    {
        return DictionaryManager.getInstance("labels", organizationId).isVisible(labelName);
    }
    public static boolean isVisible(String organizationId, String labelName, String docType)
    {
    	String visible = DictionaryManager.getInstance("labels", organizationId).getLabelProperty(docType + "-" + labelName,
    			DictionaryManager.getInstance("labels", organizationId).getLabelProperty(labelName, ""));
        if (visible.equalsIgnoreCase("hidden")) return false;
        else return true;
    }
    public static boolean isReadOnly(String organizationId, String labelName)
    {
        return DictionaryManager.getInstance("labels", organizationId).isReadOnly(labelName);
    }
    public static boolean isRequired(String organizationId, String labelName)
    {
        return DictionaryManager.getInstance("labels", organizationId).isRequired(labelName);
    }
    public static boolean isLink(String organizationId, String labelName)
    {
        return DictionaryManager.getInstance("labels", organizationId).isLink(labelName);
    }
}

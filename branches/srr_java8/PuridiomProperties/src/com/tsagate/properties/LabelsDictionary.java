/*
 * Created on Aug 10, 2004
 *
 * @author  * renzo
 * project: TsaProperties
 * package com.tsagate.properties;.LabelsDictionary.java
 *
 */
package com.tsagate.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.PropertyResourceBundle;
import com.tsagate.properties.exception.PropertiesException;


/**
 * @author renzo
 */
public class LabelsDictionary extends Dictionary
{
    public static String DEFAULT_DIRECTORY = "puridiom";
    public String language;

    public LabelsDictionary()
    {
        this("labels", "puridiom");
    }
    /**
     * @param bundleName
     */
    public LabelsDictionary(String organizationId)
    {
        this("labels", organizationId);
    }
    /**
     * @param bundleName
     * @param organizationId
     */
    public LabelsDictionary(String bundleName, String organizationId)
    {
        this.setName(bundleName);
        this.setOrganizationId(organizationId);
        this.setBundle(bundleName, organizationId);
    }

    public LabelsDictionary(String bundleName, String organizationId, String language)
    {
        this.setName(bundleName);
        this.setOrganizationId(organizationId);
        this.setLanguage(language);
        this.setBundle(bundleName, organizationId, language);
    }

    private String checkOrganizationId(String organizationId)
    {
        if(organizationId == null)
        {
            organizationId = LabelsDictionary.DEFAULT_DIRECTORY;
        }
        else
        {
            if(organizationId.length() < 0)
            {
                organizationId = LabelsDictionary.DEFAULT_DIRECTORY;
            }
        }

        return organizationId;
    }

    private void setBundle(String bundleName, String organizationId)
    {
    	this.setBundle(bundleName, organizationId, "");
    }

    private void setBundle(String bundleName, String organizationId, String language)
    {
        organizationId = this.checkOrganizationId(organizationId);
        String path = DictionaryManager.getInstance("host", organizationId).getProperty("properties-path", "properties.labels");
        String propertiesName = "";
        try
        {
            propertiesName = this.getPropertiesFileName(path, organizationId, bundleName, language);

            //System.out.println("LabelsDictionary.setBundle propsName = " + propertiesName);
            File props = new File(propertiesName);

            if(!props.exists())
            {
            	propertiesName = this.getPropertiesFileName(path, organizationId, bundleName, "");
            	props = new File(propertiesName);
            	if(!props.exists() && !bundleName.equalsIgnoreCase("missinglabels"))
                {
	                propertiesName = this.getPropertiesFileName(path, LabelsDictionary.DEFAULT_DIRECTORY, bundleName, "");
	                props = new File(propertiesName);

	                if(!props.exists())
	                {
	                    throw new PropertiesException(propertiesName);
	                }
                }
            }

            if (props.exists()) {
            	FileInputStream fis = new FileInputStream(props);
            	this.bundle = new PropertyResourceBundle(fis);
            } else {
            	this.bundle = null;
            }
        }
        catch (Exception e)
        {
            System.out.println("File: " + propertiesName + " was not found!" );
            e.printStackTrace();
        }
    }

    /**
     * This method just builds the pathName. It does NOT check if file exists.
     * @param defaultPath path to properties directory
     * @param organizationId organizationId
     * @param bundleName properties File Name
     * @return path to propertiesFilename.
     */
    private String getPropertiesFileName(String defaultPath, String organizationId, String bundleName, String language)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(defaultPath);
        sb.append(organizationId.toLowerCase());
        sb.append(File.separator);
        sb.append(bundleName.toLowerCase());
        if(language != null && language.length() > 0)
        {
        	sb.append("_" + language.toLowerCase());
        }
        sb.append(".properties");

        return sb.toString();
    }
    public String findIt()
    {
        //File props = new File("properties\\labels.properties");
        File props = new File("properties\\");
        //if(props.exists())
        //{
            //System.out.println("abs. path: " + props.getAbsolutePath());
            //System.out.println("path: " + props.getPath());
            ClassLoader loader = this.getClass().getClassLoader();
            URL url = loader.getSystemResource("properties\\labels.properties");
            //System.out.println("url: " + url.getFile());
            return url.getFile();

        //}
    }
    /**
     * @param labelName
     * @param defaultValue
     * @return
     */
    public String getProperty(String labelName, String defaultValue)
    {
        String label = super.getProperty(labelName, defaultValue);
        /* if we do it here.. we won't be able to use the same label for different pages.
         * if(!this.isVisible(labelName))
        {
            label = "";
        }
        */
        return label;
    }

    /**
     * default: true
     * @param label
     * @return
     */
    public boolean isVisible(String label)
    {
        boolean ret = true;
        try
        {
            String visible = this.getLabelProperty(label + "-visible", "");
            if (visible == "")
            {
           		visible = "visible";
            }
            if(visible.equalsIgnoreCase("hidden"))
            {
                ret = false;
            }
            else
            {
                ret = true;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error getting visible property for: " + label);
        }

        return ret;
    }

    /**
     * default: true
     * @param label
     * @return
     */
    public boolean isReadOnly(String label)
    {
        boolean ret = true;
        try
        {
            String readonly = this.getLabelProperty(label + "-readonly", "");
            if (readonly == "")
            {
            	readonly = "false";
            }
            if(readonly.equalsIgnoreCase("true"))
            {
                ret = false;
            }
            else
            {
                ret = true;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error getting readonly property for: " + label);
        }

        return ret;
    }
    /**
     * default: true
     * @param label
     * @return
     */
    public boolean isLink(String label)
    {
        boolean ret = true;
        try
        {
            String link = this.getLabelProperty(label + "-link", "true");
            if (link.equalsIgnoreCase("false"))
            {
                ret = false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error getting visible property for: " + label);
        }

        return ret;
    }

    protected String getLabelProperty(String property)
    {

        String propertyValue = "";
        try
        {
            propertyValue = this.bundle.getString(property.toLowerCase());
            propertyValue = new String(propertyValue.getBytes("ISO8859_1"), "UTF-8");
        }
        catch (Exception e)
        {
            int ind = property.indexOf("-");
            if (ind > 0 && property.length() >= (ind + 1))
            {
                String	subProperty = property.substring(ind + 1);
                if (!subProperty.equalsIgnoreCase("visible") && !subProperty.equalsIgnoreCase("required") && !subProperty.equalsIgnoreCase("alt"))
                {
                    propertyValue = this.getLabelProperty(subProperty);
                }
            }
        }
        return propertyValue;
    }

    public String getLabelProperty(String property, String defaultProp)
    {
        String propertyValue = "";
        boolean fromEnglish = false;
        try
        {
            propertyValue = this.bundle.getString(property.toLowerCase());
            propertyValue = new String(propertyValue.getBytes("ISO8859_1"), "UTF-8");

            String defaultValue = DictionaryManager.getLabelsInstance(this.getOrganizationId(), "").getLabelProperty(property);
        	if(!defaultValue.equalsIgnoreCase(propertyValue))
        	{
        		propertyValue = propertyValue + "/" + defaultValue;
        	}
        }
        catch (Exception e)
        {
    		/*
    		 * Label Property was not found.  Write record to LABELS table to allow for administration of the property.
    		 */
        	/*if (property != null && property.length() > 0) {
        		if (defaultProp != null && defaultProp.length() > 0) {
            		this.processMissingLabel(property, defaultProp);
        		}
        	}*/

            int ind = property.indexOf("-");
            String	subProperty = "";

            if (ind > 0 && property.length() >= (ind + 1))
            {
            	subProperty = property.substring(ind + 1);
                if (subProperty.equalsIgnoreCase("visible") || subProperty.equalsIgnoreCase("required") || subProperty.equalsIgnoreCase("alt"))
                {
                	subProperty = "";
                }
            }
            if (subProperty != null && subProperty.length() > 0) {
            	if (property.equals(defaultProp)) {
            		defaultProp = subProperty;
            	}
                propertyValue = this.getLabelProperty(subProperty, defaultProp);
            }
            else
            {
            	fromEnglish= true;
            	propertyValue = DictionaryManager.getLabelsInstance(this.getOrganizationId(), "").getLabelProperty(property);
            	if(propertyValue.length() < 1)
            	{
            		/*
            		 * Label Property was not found.  Write record to LABELS table to allow for administration of the property.
            		 */
            		this.processMissingLabel(property, defaultProp);

            		propertyValue = defaultProp;
            	}
            }
        }


        return propertyValue;
    }

	public String getLanguage()
	{
		if(language == null){		return "";		}
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	//Moved from the parent - 08/20/2008 - to allow for Language specific settings to be obtained directly from the LabelsDictionary vs the
	//standard dictionary which do now need them
	public String getLabel(String organizationId, String labelName, String defaultString)
    {
        return this.getLabelProperty(labelName, defaultString);
    }

    public String getLabel(String organizationId,  String labelName, String defaultString, String docType, boolean checkRequired)
    {
    	return this.getLabel(organizationId, docType + "-" + labelName, getLabel(organizationId, labelName,defaultString,checkRequired),checkRequired) ;
    }
    public String getLabel(String organizationId,  String labelName, String defaultString, String docType, boolean checkRequired, boolean buildLink, String fieldName)
    {
    	return this.getLabel(organizationId, docType + "-" + labelName, getLabel(organizationId, labelName,defaultString,checkRequired),checkRequired, buildLink, fieldName) ;
    }

    public String getLabel(String organizationId,  String labelName, String defaultString, String docType)
    {
    	return this.getLabel(organizationId, docType + "-" + labelName, this.getLabel(organizationId, labelName,defaultString)) ;
    }
    public String getLabel(String organizationId, String labelName, String defaultString, boolean checkRequired)
    {
        return this.getLabel(organizationId, labelName, defaultString, checkRequired, false, "", "");
    }
    public  String getLabel(String organizationId, String labelName, String defaultString, boolean checkRequired, boolean buildLink)
    {
    	return this.getLabel(organizationId, labelName, defaultString, checkRequired, buildLink, "", "");
    }
    public  String getLabel(String organizationId, String labelName, String defaultString, boolean checkRequired, boolean buildLink, String fieldName)
    {
    	return this.getLabel(organizationId, labelName, defaultString, checkRequired, buildLink, fieldName, "");
    }

    public  String getLabel(String organizationId, String labelName, String defaultString, boolean checkRequired, boolean buildLink, String fieldName, String className)
    {
        String	label = this.getLabel(organizationId, labelName, defaultString);
    	if (labelName == null || labelName.trim().length() == 0) {
    		return defaultString;
    	}
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

    	if (buildLink)
        {
            try
    		{
                boolean isALink = this.isLink(labelName);
                if (isALink) {
	            	String browseName = this.getLabelProperty(labelName + "-browsename", "");
	                String browseType = this.getLabelProperty(labelName + "-browsetype", "");


	                if (browseName != null && browseName.length() > 0) {
	                	if (browseType == null) {
	                		browseType = "";
	                	}
		                if (className == null || className.length() <= 0) {
		                	className = "";
		                } else {
		                	className = "class=" + className;
		                }
	                	label = "<a href=\"javascript:browseSetup('" + browseName + "', '" + browseType + "', '" + fieldName + "'); void(0);\" " + className + ">" + label + "</a>";
	                }
                }
            	label = "<span id=\"editLabel\" valign=middle style=\"visibility:hidden;display:none;\">&nbsp;<a href=\"javascript:editLabel('" + labelName + "'); void(0);\"><img src=\"/puridiom/images/cmd_edit.gif\" border=0></a></span>" + label;
            }
            catch (Exception e) { }
        }
    	return label;
    }

    public boolean isVisible(String organizationId, String labelName)
    {
    	return DictionaryManager.getInstance("labels", organizationId).isVisible(labelName);
    }
    public boolean isReadOnly(String organizationId, String labelName)
    {
    	return DictionaryManager.getInstance("labels", organizationId).isReadOnly(labelName);
    }
    public boolean isRequired(String organizationId, String labelName)
    {
    	boolean isRequired = DictionaryManager.getInstance("labels", organizationId).isRequired(labelName);
    	return isRequired;
    }
    public boolean isLink(String organizationId, String labelName)
    {
    	return DictionaryManager.getInstance("labels", organizationId).isLink(labelName);
    }
    private void processMissingLabel(String property, String defaultProp) {
		try {
			if (this.getOrganizationId() != null && this.getOrganizationId().length() > 0 && !this.getOrganizationId().equalsIgnoreCase("PURIDIOM")) {
				if (!(property.indexOf(' ') >= 0 || property.endsWith("-alt") || property.endsWith("-visible") || property.endsWith("-required") || property.endsWith("-readonly"))) {
		            PropertyResourceBundle missinglabelsbundle = (PropertyResourceBundle) DictionaryManager.getInstance("missinglabels", this.getOrganizationId()).bundle;
		        	if (missinglabelsbundle == null || !missinglabelsbundle.containsKey(property)) {
		                File missingLabelsFile = this.getMissingLabelsFile();

		        		PrintWriter writer = new PrintWriter(new FileWriter(missingLabelsFile, true));
		        		writer.println(property + "=" + defaultProp);
		                writer.close();
		                DictionaryManager.getInstance().refresh(this.getOrganizationId(), "missinglabels");
		            }
				}
			}
		}
		catch (Exception ex) {
			System.out.println("ERROR PROCESSING MISSING LABEL [" + property + "]");
			ex.printStackTrace();
		}

    }

	private File getMissingLabelsFile() throws Exception {
		String	path = DictionaryManager.getInstance("host", this.getOrganizationId()).getProperty("properties-path", "");

        if (path == null || path.trim().length() == 0) {
    		throw new Exception("The properties-path was not specified.");
    	}

        path = path + this.getOrganizationId().toLowerCase();

        File directory = new File(path);
        if (!directory.isDirectory()) {
        	if (!directory.mkdir()) {
                throw new Exception("The properties path [" + path + "] is not a valid directory and could not be created.");
        	}
        }
        if (!directory.canRead()) {
			throw new Exception("The labels properties file cannot be created/accessed because the application does not have read access for the properties path [" + path + "].");
		}
        if (!directory.canWrite()) {
            throw new Exception("The labels properties file cannot be created because the application does not have write access for the properties path[" + path + "].");
        }

        if (path.charAt(path.length() - 1) != File.separatorChar) {
        	path = path + File.separator;
        }

        String	newFilename = path + "missinglabels.properties";
		File file = new File(newFilename);

		if(!file.exists()) {
			file.createNewFile();
		}

		return file;
	}

	public void deleteMissingLabelsFile() throws Exception {
		File file = this.getMissingLabelsFile();

		if (file.exists()) {
			file.delete();
		}
		DictionaryManager.getInstance().refresh(this.getOrganizationId(), "missinglabels");
	}
}

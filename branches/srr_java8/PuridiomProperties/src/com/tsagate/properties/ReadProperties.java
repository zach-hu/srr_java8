package com.tsagate.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.tsagate.properties.exception.PropertiesException;

public class ReadProperties
{
	Properties propertiesFile = new Properties();

	public Map getPropertiesListByOid()
	{
		return this.getPropertiesListByOid(null);
	}

	/**
	 * A directory will be added to the list  if:
	 * an organization name is provided and the directory name matches that oid or
	 * no organization name is provided
	 * Hilton directory is always added.
	 * @param oid
	 * @param currentFile
	 * @return
	 */
	public boolean showMe(String oid, File currentFile)
	{
		boolean checkForOid = false;
		boolean showMe = false;
		if(oid != null)
		{
			checkForOid = true;
		}
		if(currentFile.isDirectory())
		{
			if(currentFile.getName().equalsIgnoreCase("puridiom") || (checkForOid && currentFile.getName().equalsIgnoreCase(oid)) || !checkForOid)
			{
				showMe = true;
			}
		}
		return showMe;
	}
	public Map getPropertiesListByOid(String oid)
	{
		String path = DictionaryManager.getInstance("host", "puridiom").getProperty("properties-path", "properties.labels");
		File propsDir = new File(path);
		File listProps[] = propsDir.listFiles();
		Map properties = new HashMap();

		for (int i = 0; i < listProps.length; i++)
		{
			if(this.showMe(oid, listProps[i]))
			{
				properties.put(listProps[i].getName(), listProps[i].listFiles());
			}
		}

		return properties;
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

	private File readFile(String organizationId, String bundleName)
	{
        organizationId = this.checkOrganizationId(organizationId);
        String path = DictionaryManager.getInstance("host", organizationId).getProperty("properties-path", "properties.labels");
        String propertiesName = "";
        File props = null;
        try
        {
            propertiesName = this.getPropertiesFileName(path, organizationId, bundleName);

            //System.out.println("LabelsDictionary.setBundle propsName = " + propertiesName);
            props = new File(propertiesName);

            if(!props.exists())
            {
                propertiesName = this.getPropertiesFileName(path, LabelsDictionary.DEFAULT_DIRECTORY, bundleName);
                props = new File(propertiesName);
                if(!props.exists())
                {
                    throw new PropertiesException(propertiesName);
                }
            }

            System.out.println("Dictionary for: " + bundleName  + "oid " + organizationId +  " .setBundle props = " + props.getAbsolutePath());


        }
        catch (Exception e)
        {
            System.out.println("File: " + propertiesName + " was not found!" );
            e.printStackTrace();
        }
        return props;
	}

	public void readProperties(String organizationId, String bundleName)
	{
		File props = this.readFile(organizationId, bundleName);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(props);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //          load existing .properties file
        try {
			this.propertiesFile.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveFromRequest(HttpServletRequest request)
	{
		String key, value;
		String oid = request.getParameter("oid");
        String propsFileName = request.getParameter("propsFileName");
		this.readProperties(oid, propsFileName);
        try
        {
            Enumeration enu = request.getParameterNames();
            while (enu.hasMoreElements())
            {
                key = (String) enu.nextElement();
                if(this.propertiesFile.containsKey(key))
                {
	                value = (String)request.getParameter(key);
	                this.propertiesFile.setProperty(key, value);
                }
            }
            int i = 0;
            this.storeProperties(oid, propsFileName);

        }
        catch (Exception exception)
        {

        }
	}

	public void storeProperties(String organizationId, String bundleName)
	{
		File props = this.readFile(organizationId, bundleName);
		//      store the modified Properties object to a file
        FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(props);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try
        {
			this.propertiesFile.store(fos, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
    private String getPropertiesFileName(String defaultPath, String organizationId, String bundleName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(defaultPath);
        sb.append(organizationId.toLowerCase());
        sb.append(File.separator);
        sb.append(bundleName.toLowerCase());
        //sb.append(".properties");

        return sb.toString();
    }

	public Properties getPropertiesFile() {
		return propertiesFile;
	}



	public static void main(String[] args)
	{
		String oid = "bsc04p";
		String fileName = "labels.properties";
		ReadProperties readProperties = new ReadProperties();
		readProperties.readProperties(oid, fileName);
		Properties props = readProperties.getPropertiesFile();
		Enumeration enu = props.propertyNames();

		while (enu.hasMoreElements())
        {
            String key = (String) enu.nextElement();
            String value = props.getProperty(key);
            System.out.println(key + ":\t" + value);
        }
	}
}

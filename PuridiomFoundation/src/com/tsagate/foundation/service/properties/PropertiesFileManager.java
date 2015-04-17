package com.tsagate.foundation.service.properties;

import java.io.*;
import java.util.*;

/**
 * Insert the type's description here.
 * Creation date: (4/8/2003 1:59:40 PM)
 * @author: Jon Strande
 */
public class PropertiesFileManager
{
	private static PropertiesFileManager INSTANCE = new PropertiesFileManager();
	private Map propertyFileList = new HashMap();
	private String pathToPropertiesFile;

	/**
	 * PropertiesFileManager constructor comment.
	 */
	private PropertiesFileManager()
	{
		super();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/8/2003 2:42:53 PM)
	 * @param file java.io.File
	 * @param properties java.util.Properties
	 * @exception java.lang.Throwable The exception description.
	 */
	private void addToPropertyFileList(String propertiesFileName, Properties properties) throws java.lang.Throwable
	{
		try
		{
			propertyFileList.put(propertiesFileName, properties);
		}
		catch (Throwable throwable)
		{
			throw throwable;
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/8/2003 2:02:37 PM)
	 * @return com.hersheys.common.service.properties.PropertiesFileManager
	 */
	public static PropertiesFileManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new PropertiesFileManager();
		}
		return INSTANCE;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/16/2003 1:15:57 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getPathToPropertiesFile()
	{
		return pathToPropertiesFile;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/16/2003 1:24:24 PM)
	 * @return java.util.Properties
	 * @param propertiesfileName java.lang.String
	 * @exception java.lang.Throwable The exception description.
	 */
	public Properties getProperties(String propertiesFileName) throws java.lang.Throwable
	{
		Properties properties = null;
		try
		{
			File file = new File(this.getPathToPropertiesFile() + propertiesFileName);
			if (propertyFileList.containsKey(propertiesFileName))
			{
				////system.out.println("its in there");
				properties = (Properties) propertyFileList.get(propertiesFileName);
				long lastModified = file.lastModified();
				long currentlyLoadedLastModified = Long.parseLong((String) properties.get("lastModifiedDate"));
				if (lastModified > currentlyLoadedLastModified)
				{
					properties = this.loadProperties(propertiesFileName);
					properties.put("lastModifiedDate", String.valueOf(file.lastModified()));
					this.addToPropertyFileList(propertiesFileName, properties);
				}
			}
			else
			{
				properties = this.loadProperties(propertiesFileName);
				if(properties != null)
				{
				    properties.put("lastModifiedDate", String.valueOf(file.lastModified()));
				}
				this.addToPropertyFileList(propertiesFileName, properties);
			}
		}
		catch (Throwable throwable)
		{
			//system.out.println("PropertiesFileManager.getProperties(" + propertiesFileName + ") - " + throwable.toString());
		}
		finally
		{
			return properties;
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/8/2003 2:34:01 PM)
	 * @return java.io.File
	 * @param file java.io.File
	 * @exception java.lang.Throwable The exception description.
	 */
	private String getPropertiesFileKey(String fileName) throws java.lang.Throwable
	{
		String currentlyLoadedFileKey = null;
		try
		{
			Set fileSet = propertyFileList.keySet();
			Iterator iterator = fileSet.iterator();
			boolean fileFound = false;
			while (iterator.hasNext() && fileFound == false)
			{
				String s = (String) iterator.next();
				if (s.equals(fileName))
				{
					currentlyLoadedFileKey = s;
					fileFound = true;
				}
			}
		}
		catch (Throwable throwable)
		{
			throw throwable;
		}
		finally
		{
			////system.out.println(currentlyLoadedFile.lastModified());
			return currentlyLoadedFileKey;
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/8/2003 2:39:23 PM)
	 * @return java.util.Properties
	 * @param file java.io.File
	 * @exception java.lang.Throwable The exception description.
	 */
	private Properties loadProperties(String fileName) throws java.lang.Throwable
	{
		Properties properties = null;
		try
		{
			if (this.getPathToPropertiesFile() != null && this.getPathToPropertiesFile().length() > 0)
			{
				File file = new File(this.getPathToPropertiesFile() + fileName);
				FileInputStream propertiesFileStream = new FileInputStream(file);
				properties = new Properties();
				properties.load(propertiesFileStream);
				propertiesFileStream.close();
			}
			else
			{
				//system.out.println("path to properties in PropertiesFileManager is set to: " + this.getPathToPropertiesFile());
			}
		}
		catch (Throwable throwable)
		{
			//system.out.println(throwable.toString());
			throw throwable;
		}
		finally
		{
			return properties;
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/16/2003 1:15:57 PM)
	 * @param newPathToPropertiesFile java.lang.String
	 */
	public void setPathToPropertiesFile(java.lang.String newPathToPropertiesFile)
	{
		pathToPropertiesFile = newPathToPropertiesFile;
	}
	/**
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the receiver
	 */
	public String toString()
	{
		// Insert code to print the receiver here.
		// This implementation forwards the message to super. You may replace or supplement this.
		return super.toString();
	}
}

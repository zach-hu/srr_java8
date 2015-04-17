/*
 * Created on Jun 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class EmailSentTo
{
    public static String BUYER = "GCS_BUYER";
    public static String BUYER_EMAIL = "GCS_EMAIL_BUYER";
    public static String BUYER_PHONE = "GCS_PHONE_BUYER";
    public static String BUYER_LOCALE = "GCS_LOCALE_BUYER";
    public static String GCS_DELIVERY_DATE = "GCS_DELIVERY_DATE";
    public static String GCS_TOTAL = "GCS_TOTAL";
    public static String GCS_DESCRIPTION = "GCS_DESCRIPTION";
    public static String GCS_VENDOR_NAME = "GCS_VENDOR_NAME";

    public static String NUMBER = "GCS_NUMBER";
    public static String PO_NUMBER = "GCS_PO_NUMBER";
    public static String OWNER = "GCS_OWNER";
    public static String GCS_FORMAT = "GCS_FORMAT";
    public static String COMMENT_START = "<!--";
    public static String COMMENT_END = "-->";
    public static String REPORT_URL = "GCS_REPORT_URL";
    public static String OWNER_IMAGE = "GCS_IMAGE_SERVLET";
    public static String GCS_TOO_MUCH_DATA_MESSAGE = "GCS_TOO_MUCH_DATA_MESSAGE";
    public static String ACKNOWLEDGEMENT_LINK = "GCS_ACKNOWLEDGEMENT_LINK";

    public static String GCS_COMPANY_NAME = "GCS_COMPANY_NAME";

    public static String NOTES = "NOTES";

    private String templatePath = "";
    private String organizationId = "";
    private String emailLink = "";
    private String locale = "";

    public EmailSentTo(String organizationId, String _templatePath)
    {
        Log.debug(this, "EmailSentTo [" + organizationId + "],[" + _templatePath + "]");
        this.setOrganizationId(organizationId);
        this.setTemplatePath(_templatePath);
    }

    public String getMessage(Object header)
    {
    	Log.debug(this, "starting to process message.");
        return this.processMsg(header);
     }

    protected String processMsg(Object header)
    {
        return "";
    }

    public StringBuffer readFile(String filePath)
    {
    	Log.debug(this, "reading file: " + filePath);
        StringBuffer sb = new StringBuffer();
        String fileName = this.fileName(filePath);
        sb.setLength(0);
        BufferedReader buff = null;
        try
        {
            buff  = new BufferedReader(new FileReader(fileName));
            String str;
            while((str = buff.readLine()) != null)
            {
                sb.append(str);
            }
        }
        catch (Exception e)
        {
            Log.error(this, "An error ocurred reading " + filePath + "\\" + fileName);
            e.printStackTrace();
        }
        finally
        {
            if(buff != null)
            {
                try
                {
                    buff.close();
                }
                catch (IOException e1)
                {
                    Log.error(this, "An error ocurred closing buffer for: " + filePath + "\\" + fileName);
                    e1.printStackTrace();
                }
            }
        }

        Log.debug(this, "file:" + sb.toString());
        return sb;
    }

    protected String getDirectory(String path, String fileName)
    {
        File file = new File(path + this.getOrganizationId().toLowerCase() + File.separator + fileName);
        if(!file.exists())
        {
            file = new File(path +  fileName);
        }
        return  file.getPath();
    }

    public String fileName(String path)
    {
        return path;
    }

    public String getOrganizationId()
    {
        return organizationId;
    }

    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }

    public String getTemplatePath()
    {
        return templatePath;
    }

    public void setTemplatePath(String templatePath)
    {
        this.templatePath = templatePath;
    }

    /**
	 * @return the emailURL
	 */
	public String getEmailLink()
	{
		return emailLink;
	}

	/**
	 * @param emailLink the emailURL to set
	 */
	public void setEmailLink(String emailLink)
	{
		this.emailLink = emailLink;
	}

	public String getLocale()
	{
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale)
	{
		this.locale = locale;
	}

    public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[PoSentToSupplier:");
		buffer.append(" templatePath: ");
		buffer.append(templatePath);
		buffer.append(" organizationId: ");
		buffer.append(organizationId);
		buffer.append(" emailURL: ");
		buffer.append(emailLink);
		buffer.append("]");
		return buffer.toString();
	}
}

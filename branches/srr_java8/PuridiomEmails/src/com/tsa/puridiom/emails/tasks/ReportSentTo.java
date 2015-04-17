/*
 * Created on Jun 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails.tasks;

import java.io.File;

import com.tsa.puridiom.emails.EmailUtils;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class ReportSentTo extends EmailSentTo
{
	private String userId = "";
	private String reportName = "";
	private String format = "";
	private String reportURL = "";
	private String imageURL = "";
	private String tooMuchDataMessage = "";

    public ReportSentTo(String organizationId, String _templatePath)
    {
        super(organizationId, _templatePath);
    }

    protected String processMsg(Object header)
    {
    	Log.debug(this, "ReportSentto process msg");
    	StringBuffer temp = this.readFile(this.getTemplatePath());
        String message = temp.toString();
        String owner = "";
        try
		{
			owner = UserManager.getInstance().getUser(super.getOrganizationId(), this.getUserId()).getDisplayName();
		}
		catch (Exception e)
		{
			owner = this.getUserId();
		}
        message = EmailUtils.replace(EmailSentTo.OWNER, message, owner);
        message = EmailUtils.replace(EmailSentTo.NUMBER, message, this.getReportName());
        message = EmailUtils.replace(EmailSentTo.GCS_FORMAT, message, this.getFormat());
        message = EmailUtils.replace(EmailSentTo.REPORT_URL, message, this.getReportURL());
        message = EmailUtils.replace(EmailSentTo.OWNER_IMAGE, message, this.getImageURL());
        message = EmailUtils.replace(EmailSentTo.GCS_TOO_MUCH_DATA_MESSAGE, message, this.getTooMuchDataMessage());

        message = EmailUtils.replace(EmailSentTo.GCS_COMPANY_NAME, message, PropertiesManager.getInstance(this.getOrganizationId()).getProperty("COMPANY", "Name", "Puridiom"));

        return message;
    }

    public String fileName(String path)
    {
        path = this.getDirectory(path, "report.htm");
        Log.debug(this, "file: " + path);

        return path;
    }

    protected String getDirectory(String path, String fileName)
    {
    	StringBuffer sb = new StringBuffer("getting directory");
    	sb.append("path: " + path);
    	sb.append("fileName: " + fileName);
    	Log.debug(this, sb.toString());
    	path = DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.htmpath");
    	Log.debug(this, "path: " + path);
    	fileName = path + this.getOrganizationId().toLowerCase() + File.separator + fileName;
    	Log.debug(this, "fileName: " + fileName);
    	File file = Utility.getOidFile(fileName, this.getOrganizationId());
    	Log.debug(this, "path is " + file.getPath());
    	return  file.getPath();
    }

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getReportName()
	{
		return reportName;
	}

	public void setReportName(String reportName)
	{
		this.reportName = reportName;
	}

	public String getFormat()
	{
		if (Utility.isEmpty(this.format))
		{
			this.format = "";
		}
		return format;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}

	/**
	 * @return the reportURL
	 */
	public String getReportURL()
	{
		return reportURL;
	}

	/**
	 * @param reportURL
	 *            the reportURL to set
	 */
	public void setReportURL(String reportURL)
	{
		this.reportURL = reportURL;
	}

	/**
	 * @return the imageURL
	 */
	public String getImageURL()
	{
		return imageURL;
	}

	/**
	 * @param imageURL
	 *            the imageURL to set
	 */
	public void setImageURL(String imageURL)
	{
		this.imageURL = imageURL;
	}

	/**
	 * @return the tooMuchDataMessage
	 */
	public String getTooMuchDataMessage()
	{
		return tooMuchDataMessage;
	}

	/**
	 * @param tooMuchDataMessage the tooMuchDataMessage to set
	 */
	public void setTooMuchDataMessage(String tooMuchDataMessage)
	{
		this.tooMuchDataMessage = tooMuchDataMessage;
	}
}

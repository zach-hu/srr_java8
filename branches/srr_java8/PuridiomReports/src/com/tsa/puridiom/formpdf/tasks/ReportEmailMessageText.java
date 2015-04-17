/*
 * Created on May 27, 2005 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.io.File;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.tasks.ReportSentTo;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 */
public class ReportEmailMessageText extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map) object;
		try
		{
			Log.debug(this, "starting to obtain Email Body.");
			
			String reportPath = (String) incomingRequest.get("report");
			String oid = (String) incomingRequest.get("organizationId");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(oid) ;
			String uid = (String) incomingRequest.get("userId");
			String language = (String) incomingRequest.get("language");
			String format = (String) incomingRequest.get("format");
			String pathToTemplate = (String) incomingRequest.get("pathToTemplate");
			String tooMuchData = (String) incomingRequest.get("tooMuchData");
			ReportSentTo message = new ReportSentTo(oid, pathToTemplate);
			BrowseObject browseObject = (BrowseObject) incomingRequest.get("browseObject");
			File reportFile = new File(reportPath);
			String reportName = reportFile.getName();
			
			message.setReportName(browseObject.getTitle());
			message.setUserId(uid);
			message.setFormat(format);
			
			String reportUrl = (String) propertiesManager.getProperty("APPLICATION", "EMAILREPORTURL","");
			if(HiltonUtility.isEmpty(reportUrl))
			{	
				reportUrl = (String)DictionaryManager.getInstance("host", oid).getProperty("reportsDisplay", "") ;
			}
			message.setReportURL(reportUrl + reportName);

			if (tooMuchData != null && tooMuchData.equalsIgnoreCase("Y"))
			{
				message.setTooMuchDataMessage(DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "tooMuchDataMessage", ""));
			}

			ret = message.getMessage(reportPath);
			Log.debug(this, "the body is: " + ret);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}

		return ret;
	}
}

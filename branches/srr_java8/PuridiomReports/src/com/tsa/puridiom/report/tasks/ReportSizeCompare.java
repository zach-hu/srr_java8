/**
 *
 */
package com.tsa.puridiom.report.tasks;

import java.io.File;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny Zapana
 */
public class ReportSizeCompare extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String organizationId = (String) incomingRequest.get("organizationId");
		String reportMaxSize = PropertiesManager.getInstance(organizationId).getProperty("REPORT OPTIONS", "REPORTSIZE", "2MB");
		File reportFile;
		String reportZipFile = "N";

		try
		{
			reportFile = new File((String) incomingRequest.get("report"));

			if (reportFile.length() > HiltonUtility.getBytesSizeFile(reportMaxSize))
			{
				reportZipFile = "Y";
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return reportZipFile;
	}
}

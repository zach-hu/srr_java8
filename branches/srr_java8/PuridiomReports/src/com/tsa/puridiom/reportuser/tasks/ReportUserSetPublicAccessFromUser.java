package com.tsa.puridiom.reportuser.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class ReportUserSetPublicAccessFromUser extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String sReportUserList = (String) incomingRequest.get("sReportUserList");

			if (!HiltonUtility.isEmpty(sReportUserList))
			{
				String reportUserList[] = sReportUserList.split("-");

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("reportuser-update.xml");

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("organizationId", organizationId);

				for (int x=0; x<reportUserList.length; x++) {
					String sReportUser[] = reportUserList[x].split(";");
					newIncomingRequest.put("ReportUser_icReportUser", sReportUser[0]);
					newIncomingRequest.put("ReportUser_publicView", sReportUser[1]);
					process.executeProcess(newIncomingRequest);
				}
			}
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
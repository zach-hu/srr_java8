package com.tsa.puridiom.reportuser.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.ReportUser;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class ReportUserSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			ReportUser originalReportUser = (ReportUser) incomingRequest.get("reportUser");
			ReportUser reportUser = new ReportUser();

			reportUser.setIcReportUser(new BigDecimal(ukg.getUniqueKey().toString()));
			reportUser.setIcReportQueue(originalReportUser.getIcReportQueue());
			reportUser.setUserId(originalReportUser.getUserId());
			reportUser.setDocTitle(originalReportUser.getDocTitle());
			reportUser.setDocFilename(originalReportUser.getDocFilename());
			reportUser.setDateSent(originalReportUser.getDateSent());
			reportUser.setTimeSent(originalReportUser.getTimeSent());

			incomingRequest.put("reportUser", reportUser);

			ReportUserAdd addTask = new ReportUserAdd();
			reportUser = (ReportUser) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = reportUser;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
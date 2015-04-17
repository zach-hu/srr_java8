package com.tsa.puridiom.stdcomment.tasks;

import com.tsagate.foundation.utility.Dates;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class StdCommentSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String icText = ukg.getUniqueKey().toString();
			incomingRequest.put("StdComment_icText", icText);
			incomingRequest.put("DocText_icText", icText);
			incomingRequest.put("DocText_referenceType", "STD");

			if (!incomingRequest.containsKey("StdComment_commentId")) {
			    incomingRequest.put("StdComment_commentId", "");
			}
			if (!incomingRequest.containsKey("StdComment_commentTitle")) {
			    incomingRequest.put("StdComment_commentTitle", "");
			}
			if (!incomingRequest.containsKey("StdComment_commentPrint")) {
			    incomingRequest.put("StdComment_commentPrint", "N");
			}
			if (!incomingRequest.containsKey("StdComment_dateEntered")) {
			    incomingRequest.put("StdComment_dateEntered", Dates.today("", userTimeZone));
			}
			if (!incomingRequest.containsKey("StdComment_dateExpires")) {
			    incomingRequest.put("StdComment_dateExpires", Dates.today("", userTimeZone));
			}
			if (!incomingRequest.containsKey("StdComment_owner")) {
			    incomingRequest.put("StdComment_owner", (String) incomingRequest.get("userId"));
			}
			if (!incomingRequest.containsKey("StdComment_status")) {
			    incomingRequest.put("StdComment_status", "02");
			}
			if (!incomingRequest.containsKey("StdComment_commentBold")) {
			    incomingRequest.put("StdComment_commentBold", "N");
			}
			if (!incomingRequest.containsKey("StdComment_commentPublic")) {
			    incomingRequest.put("StdComment_commentPublic", "N");
			}

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
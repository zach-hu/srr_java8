package com.tsa.puridiom.historylog.tasks;

import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class HistoryLogSetupEditAttachment extends Task
{
    public void setUp(Map incomingRequest)
	{
        DocAttachment docAttachment = (DocAttachment)incomingRequest.get("docAttachment");
    	String doAttachment_DocIc = docAttachment.getComp_id().getDocIc().toString();
    	String titleOldAttachment = docAttachment.getDocTitle();
    	String fullNameOldAttachment = docAttachment.getDocFilename();

    	int posOld = fullNameOldAttachment.lastIndexOf(".");
        String nameOldAttachment = fullNameOldAttachment.substring(0,posOld);
    	String	extOld = fullNameOldAttachment.substring(posOld+1);


    	String userId = (String)incomingRequest.get("userId");
        String icHeaderEdit = (String)incomingRequest.get("icHeaderEdit");

        String titleNewAttachment = (String)incomingRequest.get("DocAttachment_docTitle");
        String fullNameNewAttachment = (String)incomingRequest.get("DocAttachment_docFilename");

        int posNew = fullNameNewAttachment.lastIndexOf(".");
        String nameNewAttachment = fullNameNewAttachment.substring(0,posNew);
        String	extNew = fullNameNewAttachment.substring(posNew+1);
        String userTimeZone = (String) incomingRequest.get("userTimeZone");

        UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		incomingRequest.put("HistoryLog_icHistory",	ukg.getUniqueKey().toString());
		incomingRequest.put("HistoryLog_logDate", Dates.today("", userTimeZone));
		incomingRequest.put("HistoryLog_logTime",  Dates.getTimeString(Dates.today("", userTimeZone)));
		incomingRequest.put("HistoryLog_userid", userId);
		incomingRequest.put("HistoryLog_icHeader", icHeaderEdit);//icHeader of Req/Po/etc.
		incomingRequest.put("HistoryLog_icHeaderHistory", doAttachment_DocIc); //DocIc of DocAttachment
		incomingRequest.put("HistoryLog_icLine", nameOldAttachment); //Name Old Attachment without ext
		incomingRequest.put("HistoryLog_icLineHistory", nameNewAttachment); //Name New Document without ext
		incomingRequest.put("HistoryLog_description","User: " + userId + " Uploaded " + titleNewAttachment);//
		incomingRequest.put("HistoryLog_doctype", extNew);//ext New Document
        incomingRequest.put("HistoryLog_timeZone", userTimeZone);
	}

	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			this.setUp(incomingRequest);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
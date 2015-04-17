/*
 * Created on May 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailUtils;
import com.tsa.puridiom.emails.tasks.EmailSentTo;
import com.tsa.puridiom.emails.tasks.DsbPdfSentTo;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 */
public class DsbPdfMessageText extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map)object;
        try
        {
        	String notes = (String) incomingRequest.get("notes");
            Log.debug(this, "starting to obtain Email Body.");

            System.out.println("disbursementeHeader miincoming"+incomingRequest);
            DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");
            String oid = (String)incomingRequest.get("organizationId");
            String pathToTemplate = (String)incomingRequest.get("pathToTemplate");
            DsbPdfSentTo message = new DsbPdfSentTo(oid, pathToTemplate);

            ret = message.getMessage(disbHeader);
            ret = this.addHtmlNotes(ret.toString(), notes);
        	Log.debug(this, "the body is: " + ret);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }

        return ret;
    }

    private String addHtmlNotes(String message, String notes)
    {
    	if(notes == null) notes = "";
    	if(!HiltonUtility.isEmpty(notes))
    	{
    		notes = notes.replaceAll("\r\n", "<br>");
    		notes = "<p>" + notes + "</p>";
    	}
    	message = EmailUtils.replace(EmailSentTo.NOTES, message, notes);

    	return message;
    }
}

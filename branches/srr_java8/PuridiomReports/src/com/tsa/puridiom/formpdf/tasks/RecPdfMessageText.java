/*
 * Created on May 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.Map;

import com.tsa.puridiom.emails.tasks.PoSentToSupplier;
import com.tsa.puridiom.emails.tasks.RecSentTo;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 */
public class RecPdfMessageText extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map)object;
        try
        {
            Log.debug(this, "starting to obtain Email Body.");
            ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
            String oid = (String)incomingRequest.get("organizationId");
            String pathToTemplate = (String)incomingRequest.get("pathToTemplate");
            RecSentTo message = new RecSentTo(oid, pathToTemplate);

            ret = message.getMessage(receiptHeader);
            Log.debug(this, "the body is: " + ret);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }

        return ret;
    }
}

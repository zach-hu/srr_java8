/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.pp.tasks.PoCancelCheck.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.po.exceptions.PoCancelException;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class PoCancelCheck extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Log.debug(this, "PoCancelChecks");
            Map incomingRequest = (Map)object;
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            String poaction = (String) incomingRequest.get("poaction");

            String poStatus = poHeader.getStatus();
            String lastRevision = poHeader.getLastRevision();

            incomingRequest.put("cancelStatus", poStatus) ;

            Log.debug(this, "Po Number " + poHeader.getPoNumber() + ", revision " + poHeader.getRevisionNumber().toString()
                    + ", Release " + poHeader.getReleaseNumber().toString());
            Log.debug(this, "Status " + poStatus);
            Log.debug(this, "lastRevision " + lastRevision);

            this.setStatus(Status.FAILED);

            if(!lastRevision.equals("C"))
            {
                //this.setStatus(Status.FAILED);
                //throw new TsaException("There are Revisions against this Order!");
            }

            if (poaction.equals("close") && poStatus.equals(DocumentStatus.CLOSED))
            {
                this.setStatus(Status.FAILED);
                throw new PoCancelException("This order has already been closed!");
            } else if (poaction.equals("cancel") && poStatus.equals(DocumentStatus.CANCELLED))
            {
                this.setStatus(Status.FAILED);
                throw new PoCancelException("This order has already been cancelled!");
            }
            /*
            if((!HiltonUtility.isEmpty(poHeader.getPyStatus())) && (poHeader.getPyStatus().compareTo("6050") > 0))
            {
                this.setStatus(Status.FAILED);
                throw new PoCancelException("Payments have been made against this order!");
            }*/

            this.setStatus(Status.SUCCEEDED);
        }
        catch (PoCancelException e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }

}
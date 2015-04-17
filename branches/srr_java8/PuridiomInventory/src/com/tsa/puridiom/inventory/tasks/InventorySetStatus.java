package com.tsa.puridiom.inventory.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;

import java.util.Map;

public class InventorySetStatus extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            String newStatus = HiltonUtility.ckNull((String) incomingRequest.get("newStatus"));
            String invStatus = HiltonUtility.ckNull((String) incomingRequest.get("InvItem_status"));
            InvItem invItem = (InvItem) incomingRequest.get("invItem");
            if (HiltonUtility.isEmpty(newStatus)) {
                newStatus = DocumentStatus.INV_APPROVED;
            }
            if(newStatus.equalsIgnoreCase(DocumentStatus.INV_APPROVED) || invStatus.equalsIgnoreCase("02"))
            {
            	invItem.setStatus("02");
            	invItem.setApproveStatus(DocumentStatus.INV_APPROVED);
            }
            else if(newStatus.equalsIgnoreCase(DocumentStatus.INV_RETURNED) || newStatus.equalsIgnoreCase(DocumentStatus.INV_INPROGRESS))
            {
            	invItem.setStatus("03");
            	invItem.setApproveStatus(DocumentStatus.INV_INPROGRESS);
            }
            else
            {
            	invItem.setApproveStatus(DocumentStatus.INV_APPROVING);
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error ocurred submitting the current Invoice ", e);
        }
        return null ;
    }
}

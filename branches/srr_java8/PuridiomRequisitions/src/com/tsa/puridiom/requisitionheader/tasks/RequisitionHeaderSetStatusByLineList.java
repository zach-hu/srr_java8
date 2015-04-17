package com.tsa.puridiom.requisitionheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class RequisitionHeaderSetStatusByLineList extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
            String organizationId = (String) incomingRequest.get("organizationId");
            String poLineCancel = HiltonUtility.ckNull((String) incomingRequest.get("PoLineCancel"));

            List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
            if (requisitionHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new Exception("RequisitionHeader was not found");
            }
            Integer	reqStatus = new Integer(0);
            boolean partiallyReceived = false;
            boolean fullyReceived = false;
            boolean noneReceived = false;

            if (requisitionLineList != null) {
                for (int i = 0; i < requisitionLineList.size(); i++) {
                    RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);
                    if (requisitionLine.getStatus().compareTo(DocumentStatus.RCV_PARTIALLYRECEIVED) < 0  || requisitionLine.getStatus().equals(DocumentStatus.INV_BACKORDERED)) {
                        noneReceived = true;
                    } else if (requisitionLine.getStatus().equals(DocumentStatus.RCV_PARTIALLYRECEIVED)) {
                        partiallyReceived = true;
                    } else if (requisitionLine.getStatus().equals(DocumentStatus.RCV_RECEIVED)) {
                        fullyReceived = true;
                    }
                    String		lStat = requisitionLine.getStatus() ;
                    if (Utility.isEmpty(lStat)) {
                    	lStat = requisitionHeader.getStatus() ;
                    }

                    Integer lineStatus = new Integer(lStat);
                    if (i == 0) {
                        reqStatus = lineStatus;
                    } else if (lineStatus.compareTo(reqStatus) < 0) {
                        reqStatus = lineStatus;
                    }
                }

                if(!poLineCancel.equalsIgnoreCase("Y"))
                {
	                if (reqStatus.compareTo(new Integer(DocumentStatus.PO_INPROGRESS)) < 0) {
	                    
	                	if(reqStatus.intValue() < 1000 && reqStatus.intValue() >= 500) {
	                		requisitionHeader.setStatus(String.valueOf("0"+reqStatus));
	                	}
	                	else
	                	{
	                		requisitionHeader.setStatus(String.valueOf(reqStatus));
	                	}
	                    
	                } else if (partiallyReceived || (noneReceived && fullyReceived)) {
	                    requisitionHeader.setStatus(DocumentStatus.RCV_PARTIALLYRECEIVED);
	                } else if (fullyReceived) {
	                    requisitionHeader.setStatus(DocumentStatus.RCV_RECEIVED);
	                } else {
	                    requisitionHeader.setStatus(String.valueOf(reqStatus));
	                }
                }
                else if((reqStatus.compareTo(new Integer(9020)) == 0) && poLineCancel.equalsIgnoreCase("Y"))
                {
                	requisitionHeader.setStatus(String.valueOf(reqStatus));
                }               
                
            }
            Log.debug(this, "Updating Requisition : " +
                    requisitionHeader.getRequisitionNumber() + " to status: " + requisitionHeader.getStatus());
            result = requisitionHeader;
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
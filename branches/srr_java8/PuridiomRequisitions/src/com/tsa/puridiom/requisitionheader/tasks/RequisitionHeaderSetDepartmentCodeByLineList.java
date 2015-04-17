package com.tsa.puridiom.requisitionheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class RequisitionHeaderSetDepartmentCodeByLineList extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
            List      reqLineList = (List) incomingRequest.get("requisitionLineList");
    		String    oid         = (String) incomingRequest.get("organizationId");
    		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
    		String departmentCode = HiltonUtility.ckNull(requisitionHeader.getDepartmentCode());

    		if ( reqLineList != null && reqLineList.size() > 0)
            {
	            for (int i = 0; i < reqLineList.size(); i++)
    			{
    				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
    				if (!HiltonUtility.isEmpty(departmentCode))
    				{
    					reqLine.setItemLocation(departmentCode);
    					dbs.update(reqLine);
    				}
    			}

	            Log.debug(this, "Updating Department Code from Header to Lines : " +
	                    requisitionHeader.getRequisitionNumber() + " to status: " + requisitionHeader.getStatus());

            }
            result = requisitionHeader;
            this.status = Status.SUCCEEDED;

        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            Log.error(this, e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return result;
    }
}
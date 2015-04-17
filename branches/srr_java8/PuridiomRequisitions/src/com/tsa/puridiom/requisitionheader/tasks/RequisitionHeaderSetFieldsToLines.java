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

public class RequisitionHeaderSetFieldsToLines extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
            List      reqLineList = (List) incomingRequest.get("requisitionLineList");    		
    		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
    		
    		String updateUdf10Code =  HiltonUtility.ckNull((String) incomingRequest.get("updateUdf10"));
    		String updateDepartmentCode =  HiltonUtility.ckNull((String) incomingRequest.get("updateDepartmentCode"));
    		
    		String departmentCode = HiltonUtility.ckNull(requisitionHeader.getDepartmentCode());
    		String udf10Code = HiltonUtility.ckNull(requisitionHeader.getUdf10Code());
    		
    		if ( reqLineList != null && reqLineList.size() > 0)
            {
	            for (int i = 0; i < reqLineList.size(); i++)
    			{
    				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
    				if (!HiltonUtility.isEmpty(departmentCode) && updateDepartmentCode.equalsIgnoreCase("TRUE"))
    				{
    					reqLine.setDepartmentCode(departmentCode);    					
    				}
    				if (updateUdf10Code.equalsIgnoreCase("TRUE"))
    				{
    					reqLine.setUdf10Code(udf10Code);    					
    				}
    				dbs.update(reqLine);
    			}
	            Log.debug(this, "Updating Fields from Header to Lines  " );
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
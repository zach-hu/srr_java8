package com.tsa.puridiom.po.notifications.tasks;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class CheckReqStatusApproveFromPo extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	RequisitionHeader requisitionHeader = null;
        	DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String icReqHeaderString = HiltonUtility.ckNull((String) incomingRequest.get("poIcReqHeader"));
			BigDecimal icReqHeader = new BigDecimal(0);

			if (!HiltonUtility.isEmpty(icReqHeaderString))
				icReqHeader = new BigDecimal(icReqHeaderString);
			String checkReqStatusApproved = "Y";

            if (icReqHeader.longValue() > 0 )
            {
            	String queryString = "from RequisitionHeader as RequisitionHeader where RequisitionHeader.icReqHeader = ? ";
				List resultList = dbs.query(queryString, new Object[] {icReqHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

				if (resultList != null && resultList.size() > 0)
				{
					requisitionHeader = (RequisitionHeader) resultList.get(0);

					if( !requisitionHeader.getStatus().equalsIgnoreCase("1035")){
						checkReqStatusApproved = "N";
					}
					incomingRequest.put("reqNumber", requisitionHeader.getRequisitionNumber());
		            incomingRequest.put("reqStatus", requisitionHeader.getStatus());
				}
            }
            incomingRequest.put("checkReqStatusApproved", checkReqStatusApproved);
        	result = requisitionHeader;
        	this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	Log.debug(this, "Error on class CheckReqStausApproveFromPo \r\n" + e.getMessage());
        	this.setStatus(Status.FAILED);
        	e.printStackTrace();
        	throw e;
        }
        return result;
    }
}
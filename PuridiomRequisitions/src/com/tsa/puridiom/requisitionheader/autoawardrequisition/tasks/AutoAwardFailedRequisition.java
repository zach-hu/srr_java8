package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.contact.tasks.ContactRetrieveById;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AutoAwardFailedRequisition extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

       	Map incomingRequest = (Map)object;
    	Object result = null;

    	try
    	{
    		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
    		String queryString = "from RequisitionHeader as rh where rh.status = '1035' and rh.rqSubType = 'FDCS'";
    		List resultList = dbs.query(queryString) ;

    		if (resultList != null && resultList.size() > 0)
    		{
    			for(int x = 0; x < resultList.size(); x++)
    			{
    				RequisitionHeader reqHeader = (RequisitionHeader)resultList.get(x);
    	           	String rqSubType = reqHeader.getRqSubType();
    	           	String employeeNo = reqHeader.getAppBy();
    	           	incomingRequest.put("RequisitionHeader_icReqHeader", reqHeader.getIcReqHeader().toString());
    	           	incomingRequest.put("errorStr", "Y");
	        		incomingRequest.put("employeeNo", employeeNo);
	        		incomingRequest.put("newHistoryRequisitionHeader", reqHeader);
	        		incomingRequest.put("requisitionHeader", reqHeader);
	        		incomingRequest.put("newStatus", "1000");
	        		incomingRequest.put("RequisitionLine_icReqHeader", reqHeader.getIcReqHeader().toString());   
	        		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		            PuridiomProcess process = processLoader.loadProcess("requisition-history-and-email.xml");
		
		            process.executeProcess(incomingRequest);
		            reqHeader.setStatus("1000");
	        		
	        		dbs.update(reqHeader);
    			}
    			this.setStatus(dbs.getStatus()) ;
    		}
    	}
    	catch (Exception e)
    	{
    		this.setStatus(Status.FAILED);
    		Log.error(this, "Error Message: " + e.getMessage());
    		throw e;
    	}
    	return result;
    }

}

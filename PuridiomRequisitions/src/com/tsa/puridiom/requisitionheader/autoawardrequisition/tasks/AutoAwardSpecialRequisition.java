package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AutoAwardSpecialRequisition extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

       	Map incomingRequest = (Map)object;
    	Object result = null;

    	try
    	{
    		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
    		String queryString = "from RequisitionHeader as rh where rh.status = '1035' and rh.rqSubType is NOT NULL and rh.requisitionType <> 'C'";
    		List resultList = dbs.query(queryString) ;

    		if (resultList != null && resultList.size() > 0)
    		{
    			for(int x = 0; x < resultList.size(); x++)
    			{
    				RequisitionHeader reqHeader = (RequisitionHeader)resultList.get(x);
    	           	String rqSubType = reqHeader.getRqSubType();
    	           	String udf1Code = HiltonUtility.ckNull(reqHeader.getUdf1Code());           	
    	           	incomingRequest.put("RequisitionHeader_icReqHeader", reqHeader.getIcReqHeader().toString());
    	           	boolean autoAward = false;
    	           	if(rqSubType.equalsIgnoreCase("BA"))
            		{
    	           		autoAward = true;
            		}
            		else if(rqSubType.equalsIgnoreCase("AA"))
            		{
            			autoAward = true;
            		}
            		else if(rqSubType.equalsIgnoreCase("NA"))
            		{
            			autoAward = true;
            		}
            		else if(rqSubType.equalsIgnoreCase("RC"))
            		{
            			autoAward = true;
            		}
            		else if(rqSubType.equalsIgnoreCase("RE"))
            		{
            			autoAward = true;
            		}
    	           	if(autoAward)
    	           	{ 	
		           		incomingRequest.put("NoApprovalNeed", "Y");
	        			incomingRequest.put("RequisitionType", reqHeader.getRequisitionType());
	        			incomingRequest.put("NoApprovalNeedAutoAward", "Y");
		    	        PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		                PuridiomProcess process = processLoader.loadProcess("po-auto-award-create-from-req.xml");
		
		                process.executeProcess(incomingRequest);
		                this.setStatus(process.getStatus());
		                
	        			String errorStr = HiltonUtility.ckNull((String) incomingRequest.get("errorStr"));
	        			String employeeNo = HiltonUtility.ckNull((String) incomingRequest.get("employeeNo"));
	        			if(errorStr.equalsIgnoreCase("Y"))
	        			{
	        				Map incoming = new HashMap();
	        				incoming.put("RequisitionHeader_icReqHeader", reqHeader.getIcReqHeader().toString());
	        				incoming.put("RequisitionHeader_appBy", employeeNo);
	        				incoming.put("RequisitionHeader_rqSubType", "FDCS");
	        					
	        				processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			                process = processLoader.loadProcess("requisitionheader-update-newtransaction.xml");
			                process.executeProcess(incoming);
			                this.setStatus(process.getStatus());
	        			}
    	           	}
    	           	else
    	           	{
    	           		if(rqSubType.equalsIgnoreCase("PO"))
                		{
    	           			incomingRequest.put("RequisitionHeader_requisitionType", reqHeader.getRequisitionType());
    	           			incomingRequest.put("NoApprovalNeed", "Y");
    	           			incomingRequest.put("PoHeader_poType", udf1Code);
    	           			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
    		                PuridiomProcess process = processLoader.loadProcess("po-type-create-from-req.xml");
    		                process.executeProcess(incomingRequest);
    		                this.setStatus(process.getStatus());
                		}
    	           	}
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

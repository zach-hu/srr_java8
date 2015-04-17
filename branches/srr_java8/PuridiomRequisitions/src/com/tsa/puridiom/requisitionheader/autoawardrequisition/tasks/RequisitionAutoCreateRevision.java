package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.HashMap;
import java.util.Iterator;
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

public class RequisitionAutoCreateRevision extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

        try
        {
        	Map incomingRequest = (Map)object;
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

        	String organizationId = HiltonUtility.ckNull((String)incomingRequest.get("organizationId"));
        	List rqhList = (List)incomingRequest.get("requisitionChangeReqList");

            if(rqhList != null && rqhList.size() > 0)
            {
                for (Iterator iterator = rqhList.iterator(); iterator.hasNext();)
                {
                	PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
                	PuridiomProcess process = processLoader.loadProcess("po-auto-award-create-revision-from-req.xml");
                	RequisitionHeader rqh = (RequisitionHeader) iterator.next();
                	String rqSubType = HiltonUtility.ckNull(rqh.getRqSubType());
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
            		else if(rqSubType.equalsIgnoreCase("PA"))
            		{
            			autoAward = true;
            		}
                	if(autoAward)
    	           	{ 	
                		incomingRequest.put("NoApprovalNeed", "Y");
                		incomingRequest.put("NoApprovalNeedAutoAward", "Y");
    	           	}
                	else
                	{
                		incomingRequest.put("NoApprovalNeed", "");
                		incomingRequest.put("NoApprovalNeedAutoAward", "");
                	}
                	incomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());
                	incomingRequest.put("RequisitionLine_icReqHeader", rqh.getIcReqHeader().toString());
                	incomingRequest.put("requisitionHeader", rqh);
                	incomingRequest.put("organizationId", organizationId);
                	//incomingRequest.put("userId", rqh.getRequisitionerCode().toString());
                	
                    process.executeProcess(incomingRequest);
                    this.setStatus(process.getStatus());
	                
        			String errorStr = HiltonUtility.ckNull((String) incomingRequest.get("errorStr"));
        			String employeeNo = HiltonUtility.ckNull((String) incomingRequest.get("employeeNo"));
        			if(errorStr.equalsIgnoreCase("Y"))
        			{
        				
        				Map incoming = new HashMap();
        				incoming.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());
        				incoming.put("RequisitionHeader_appBy", employeeNo);
        				incoming.put("RequisitionHeader_rqSubType", "FDCS");
        					
        				processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		                process = processLoader.loadProcess("requisitionheader-update-newtransaction.xml");
		                process.executeProcess(incoming);
		                this.setStatus(process.getStatus());
        			}
	            }
            }
            else
            {
            	Log.debug(this, "No requisitions to autoaward!");
            }

            Log.debug(this, "AUTOAWARD WORKING!");
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	Log.error(this, "RequisitionAutoAwardRequisitionByLine failed: " + e.getMessage());
            this.setStatus(Status.FAILED);
            throw e;
        }
        return ret;
    }

}
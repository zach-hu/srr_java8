package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionAutoAwardRequisition extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

        try
        {
        	PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
            PuridiomProcess process = null;
        	Map incomingRequest = (Map)object;

            List requisitionHeaderList = (List)incomingRequest.get("requisitionHeaderList");

            if(requisitionHeaderList.size() > 0)
            {
                for (Iterator iterator = requisitionHeaderList.iterator(); iterator.hasNext();)
                {
                	RequisitionHeader requisitionHeader = (RequisitionHeader) iterator.next();
                	incomingRequest.put("RequisitionHeader_icReqHeader", requisitionHeader.getIcReqHeader().toString());

                	incomingRequest.put("autoAwarded", Boolean.TRUE);

        			process = processLoader.loadProcess("requisitionheader-autoawardrequisition.xml") ;
        			process.executeProcess(incomingRequest);
        			if (process.getStatus() != Status.SUCCEEDED)
        			{
        				throw new Exception("requisitionheader-autoawardrequisition.xml failed for requisition " + requisitionHeader.getIcReqHeader());
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
            this.setStatus(Status.FAILED);
            throw new TsaException("", e);
        }
        return ret;
    }

}

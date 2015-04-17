package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Task;

/**
 * Assign PO numbers previously generated in the field UDF_15_CODE of the 
 * Requisition_header, the number will be used later when the PO is generated 
 * for the requisition.
 * 
 * @author Alexander 
 */
public class AssignPoNumberInRequisition extends Task
{

	/**
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
    {
		
		Map incomingRequest = (Map)object;
        RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
        
        String poNumber = (String) incomingRequest.get("PoHeader_poNumber");
        if (!HiltonUtility.isEmpty(poNumber))
        {
        	reqHeader.setUdf15Code(poNumber);
        }
        
		return reqHeader;
    }
}

package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;

/**
 * Class that is responsible for setting the RqSubType property to PA (Po - Awarded),
 * for the AutoAwardRequisitionJob.java.
 *
 * Generate POs with status AWARDED.
 *
 * @author Alexander
 */
public class AutoAwardAllRequisitionSetup extends Task
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
        PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));

        // Awarded is made based in AUTOAWARDALLREQ and AUTOAWARDALLREQ-TYPE
        String autoAwardByRequistionType = propertiesManager.getProperty("AUTOAWARD","AUTOAWARDALLREQ-TYPE","");
        if (!HiltonUtility.isEmpty(autoAwardByRequistionType))
        {
        	boolean enableAutoAward = false;
            String requisition_type[] = autoAwardByRequistionType.split(";");
            String requisitionType = reqHeader.getRequisitionType();
            for (int x = 0; x < requisition_type.length; x++)
    		{
        		String req_type = requisition_type[x];
        		if(requisitionType.equalsIgnoreCase(req_type))
        		{
        			enableAutoAward = true;
        			break;
        		}
    		}
            if(enableAutoAward)
            {
            	reqHeader.setRqSubType("PA");
            }

            String autoAwardTypePO = propertiesManager.getProperty("REQ OPTIONS", "AUTOAWARDTYPEPO", "");
            if (!HiltonUtility.isEmpty(autoAwardTypePO))
            {
                String po_type[] = autoAwardTypePO.split(";");
                Boolean enablePoType = false;
                String udf1Code = reqHeader.getUdf1Code();
                for (int x = 0; x < po_type.length; x++)
        		{
            		String poType = po_type[x];
            		if(poType.equalsIgnoreCase(udf1Code))
            		{
            			enablePoType = true;
            			break;
            		}
        		}

                if (enablePoType)
                {
                	reqHeader.setRqSubType("PA");
                }
                else
                {
                	reqHeader.setRqSubType("PO");
                }
            }
        }

		return reqHeader;
    }
}

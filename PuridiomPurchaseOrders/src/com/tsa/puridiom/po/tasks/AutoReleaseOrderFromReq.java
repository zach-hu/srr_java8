/*
 * Created on Dec 6, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
;

/**
 * @author renzo
 */
public class AutoReleaseOrderFromReq extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            Log.debug(this.getName(), "AutoReleaseOrderFromReq starts");
            PoHeader blanket = (PoHeader)incomingRequest.get("blanket");
            //set right type
            this.setReleaseOrderType(blanket, incomingRequest);

            /* Use the blanket Order to obtain inital setup */
            incomingRequest.put("poHeader", blanket);
            incomingRequest.put("blanketIc", blanket.getIcPoHeader());
            incomingRequest.put("release_poNumber", blanket.getPoNumber());
            incomingRequest.put("PoHeader_poNumber", blanket.getPoNumber());

            /* Copy Credit card information from the Blanket to the Release Order */			
            incomingRequest.put("PoHeader_pcardExpires", blanket.getPcardExpires());
            incomingRequest.put("PoHeader_pcardHolder", blanket.getPcardHolder());
            incomingRequest.put("PoHeader_pcardName", blanket.getPcardName());
            incomingRequest.put("PoHeader_pcardNumber", blanket.getPcardNumber());
            incomingRequest.put("PoHeader_pcardOrder", blanket.getPcardOrder());

            //RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            //incomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());
            UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            //incomingRequest.put("historyIc", rqh.getIcHeaderHistory().toString());
            incomingRequest.put("historyIc", ukg.getUniqueKey().toString());
            incomingRequest.put("release_releaseNumber", blanket.getLastRelease());
            incomingRequest.put("requisitionLines", incomingRequest.get("requisitionLineList"));
            incomingRequest.put("autoReleased", incomingRequest.get("createReleaseFromReq"));
            if(!incomingRequest.containsKey("forwardallowed"))
            {
                incomingRequest.put("forwardallowed", "N");
            }

            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }

    /**
     * @param blanket
     * @param incomingRequest
     */
    private void setReleaseOrderType(PoHeader blanket, Map incomingRequest)
    {
        String blanketType = blanket.getPoType();
        Log.debug(this.getName(), "setReleaseOrderType: " + blanketType);
        boolean bdefault = false;
        if(Utility.isEmpty(blanketType))
        {
            bdefault = true;
        }
        else if(blanketType.equals(OrderType.BLANKET_ORDER))
        {
            bdefault = true;
        }
        else if (blanketType.equals(OrderType.DELIVERY_ORDER))
        {
            incomingRequest.put("releaseType", OrderType.DELIVERY_RELEASE);
            incomingRequest.put("PoHeader_poType", OrderType.DELIVERY_RELEASE);
        }
        else if (blanketType.equals(OrderType.SERVICE_BLANKET))
        {
            incomingRequest.put("releaseType", OrderType.SERVICE_RELEASE);
            incomingRequest.put("PoHeader_poType", OrderType.SERVICE_RELEASE);
        }
        else
        {
        	String poType = (String) incomingRequest.get("PoHeader_poType");
        	if(OrderType.PURCHASE_RELEASE.equals(poType)) {
        		incomingRequest.put("releaseType", OrderType.PURCHASE_RELEASE);
        		incomingRequest.put("releaseType", OrderType.PURCHASE_RELEASE);
        	} else {
        		bdefault = true;
        	}
        }

        if(bdefault)
        {
            incomingRequest.put("releaseType", OrderType.RELEASE_ORDER);
            incomingRequest.put("PoHeader_poType", OrderType.RELEASE_ORDER);
        }
    }

}

/**
 *
 * Created on Jan 28, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderLoadReqSupplier.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException; 
import com.tsagate.foundation.utility.Utility;

public class PoHeaderLoadReLeaseSupplier extends Task
{
    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
        try
        {
            PoHeader blanket = (PoHeader)incomingRequest.get("blanket");

            poHeader.setVendorName(blanket.getVendorName());
            poHeader.setVendContactCode(blanket.getVendContactCode());
           	poHeader.setFobCode(blanket.getFobCode());
           	if (incomingRequest.containsKey("requisitionHeader")) {
           		RequisitionHeader requisition = (RequisitionHeader) incomingRequest.get("requisitionHeader");
           		if (! Utility.isEmpty(requisition.getShippingCode())) {
           			poHeader.setShipViaCode(requisition.getShippingCode());
           		} else {
           			poHeader.setShipViaCode(blanket.getShipViaCode());
           		}
           	} else {
           		poHeader.setShipViaCode(blanket.getShipViaCode());
            }
           	poHeader.setTermsCode(blanket.getTermsCode());
           	poHeader.setVendorClass(blanket.getVendorClass());
        }
        catch (Exception e)
        {
            throw new TsaException(e.toString());
        }
        return poHeader;
    }

}

/**
 * Created on January 19, 2005
 * @author Kelli
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderSetAwarded.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderSetAwarded extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            Boolean createReleaseFromReq = (Boolean)incomingRequest.get("createReleaseFromReq");
            String newStatus = (String) incomingRequest.get("newStatus");
            String autoAwardedRequisition = HiltonUtility.ckNull((String) incomingRequest.get("autoAwardedRequisition"));
            String flag = HiltonUtility.ckNull(poHeader.getVendorId());
            BigDecimal revisionNumber = poHeader.getRevisionNumber();
            if(createReleaseFromReq == null)
            {
                createReleaseFromReq = Boolean.FALSE;
            }
            if (revisionNumber.intValue() == 0)
            {
            	poHeader.setRevisionValue(poHeader.getTotal());
            }
            if(createReleaseFromReq.booleanValue())
            {
                PoHeader blanket = (PoHeader)incomingRequest.get("blanket");
                poHeader.setAppBy(blanket.getAppBy());
                poHeader.setLastChgBy("AUTORELEASE");
            }
            else if ( !autoAwardedRequisition.equalsIgnoreCase("Y"))
            {
                poHeader.setAppBy((String)incomingRequest.get("userId"));
            }

            poHeader.setAppSigned("N");
            poHeader.setApproved("Y");

            if (HiltonUtility.ckNull(newStatus).equals(DocumentStatus.CT_AWARDED)) {
                poHeader.setStatus(DocumentStatus.CT_AWARDED);
            } else if (autoAwardedRequisition.equalsIgnoreCase("Y") && HiltonUtility.isEmpty(flag))
            {
            	poHeader.setStatus(DocumentStatus.PO_INPROGRESS);
            } else {
                poHeader.setStatus(DocumentStatus.PO_AWARDED);
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }

}

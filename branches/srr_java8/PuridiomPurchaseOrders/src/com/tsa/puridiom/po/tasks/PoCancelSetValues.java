/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoCancelSetValues.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

public class PoCancelSetValues extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            if(poHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("We couldn't locate your Order!");
            }

            poHeader.setStatus(DocumentStatus.CANCELLED);
            poHeader.setPyStatus("01");
            poHeader.setTaxAmount(new BigDecimal(0));
            poHeader.setTaxPercent(new BigDecimal(0));
            poHeader.setDiscountPercent(new BigDecimal(0));
            poHeader.setDiscountAmount(new BigDecimal(0));
            poHeader.setShippingCharges(new BigDecimal(0));
            poHeader.setShippingTax(new BigDecimal(0));
            poHeader.setOtherCharges(new BigDecimal(0));
            poHeader.setOtherTax(new BigDecimal(0));
            poHeader.setTotal(new BigDecimal(0));
            poHeader.setSubtotal(new BigDecimal(0));
            poHeader.setLastChgBy((String)incomingRequest.get("userId"));
            poHeader.setLastChgDate(Dates.getCurrentDate(userTimeZone));
            ret = poHeader;

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
/*
 * Created on Dec 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequisitionLineSetShippingTaxCodeFromHeader extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
            String headerShipTaxCode = (String)incomingRequest.get("RequisitionHeader_taxCode");
            if (HiltonUtility.isEmpty(headerShipTaxCode)) {
            	headerShipTaxCode = requisitionHeader.getTaxCode();
            }
            String headerShipTaxPercent = (String)incomingRequest.get("RequisitionHeader_taxPercent");
            if (HiltonUtility.isEmpty(headerShipTaxPercent)) {
            	headerShipTaxPercent = requisitionHeader.getTaxPercent().toString();
            }
           	BigDecimal headerShipTaxRate = new BigDecimal(headerShipTaxPercent);

            for (Iterator iter = requisitionLineList.iterator(); iter.hasNext();)
            {
                RequisitionLine line = (RequisitionLine) iter.next();

                if(!headerShipTaxCode.equals(null))
                {
                	line.setTaxCode(headerShipTaxCode);
                	line.setTaxPercent(headerShipTaxRate);
                }
            }

            ret = requisitionLineList;

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);

        }
        return ret;
    }
}

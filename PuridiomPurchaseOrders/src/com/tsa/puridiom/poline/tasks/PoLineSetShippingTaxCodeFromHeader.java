/*
 * Created on Dec 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoLineSetShippingTaxCodeFromHeader extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            List poLineList = (List)incomingRequest.get("poLineList");
            String headerShipTaxCode = (String)incomingRequest.get("PoHeader_taxCode");
            if (HiltonUtility.isEmpty(headerShipTaxCode)) {
            	headerShipTaxCode = poHeader.getTaxCode();
            }
            String headerShipTaxPercent = (String)incomingRequest.get("PoHeader_taxPercent");
            if (HiltonUtility.isEmpty(headerShipTaxPercent)) {
            	headerShipTaxPercent = poHeader.getTaxPercent().toString();
            }
           	BigDecimal headerShipTaxRate = new BigDecimal(headerShipTaxPercent);

            for (Iterator iter = poLineList.iterator(); iter.hasNext();)
            {
                PoLine line = (PoLine) iter.next();

                if(!headerShipTaxCode.equals(null))
                {
                	line.setTaxCode(headerShipTaxCode);
                	line.setTaxPercent(headerShipTaxRate);
                }
            }

            ret = poLineList;

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);

        }
        return ret;
    }
}

/**
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 */
public class PoHeaderFindRequisitioner extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object ret = null;
        try
        {
            StringBuffer requisitioners = new StringBuffer();
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            BigDecimal revNumber = poHeader.getRevisionNumber();
            List poLineList = (List)incomingRequest.get("poLineList");
            for(int i = 0; i < poLineList.size(); i++)
            {
                PoLine poLine = (PoLine)poLineList.get(i);
                BigDecimal lineRevNo = poLine.getLineRevisionNumber();
                boolean checkRequisitioner = true;
                if((revNumber.compareTo(new BigDecimal(0)) > 0) )
                {//only finf requisitioners where their line items were revised.
                	if (revNumber.compareTo(lineRevNo) == 0)
                	{
                		checkRequisitioner = true;
                	}
                	else
                	{
                		checkRequisitioner = false;
                	}
                }

                if(checkRequisitioner)
                {
                	this.checkRequisitioner(requisitioners, poLine.getRequisitionerCode());
                }
            }

            if (poHeader.getPoType().equalsIgnoreCase(OrderType.CONTRACT)) 
            {
            	this.checkRequisitioner(requisitioners, poHeader.getRequisitionerCode());	
            }
            
            incomingRequest.put("requisitionersToEmail", requisitioners);

            if(requisitioners.length() > 1)
            {
                ret = "true";
            }
            else
            {
                ret = "false";
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Requisitioner for this Order could not be found!");
        }
        return ret;
    }

    private StringBuffer checkRequisitioner(StringBuffer requisitioners, String requisitioner)
    {
        if(!Utility.isEmpty(requisitioner))
        {
            if(!Utility.isEmpty(requisitioners.toString()))
            {
                requisitioners.append(";");
            }
            requisitioners.append(requisitioner);
        }
        return requisitioners;
    }
}

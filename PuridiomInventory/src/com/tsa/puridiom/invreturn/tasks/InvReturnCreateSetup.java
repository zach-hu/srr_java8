/*
 * Created on Oct 21, 2004
 *
 */
package com.tsa.puridiom.invreturn.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class InvReturnCreateSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            String icBin = (String)incomingRequest.get("InvReturn_icBin");
            if(Utility.isEmpty(icBin))
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("NO Bin was selected!");
            }

            String icInvReturn = ukg.getUniqueKey().toString();
            if (icBin.equals("0")) {
            	icBin = ukg.getUniqueKey().toString() ;
            }
            incomingRequest.put("InvReturn_icInvReturn",icInvReturn);
            incomingRequest.put("InvReturn_icBin",icBin);
            incomingRequest.put("InvBinLocation_icRc", icBin);

            incomingRequest.put("qty", incomingRequest.get("InvBinLocation_newQty"));
            incomingRequest.put("InvLocation_itemNumber", incomingRequest.get("InvReturn_itemNumber"));
            incomingRequest.put("InvLocation_itemLocation", incomingRequest.get("InvBinLocation_itemLocation"));
            incomingRequest.put("InvBinLocation_itemNumber", incomingRequest.get("InvReturn_itemNumber"));
            incomingRequest.put("InvBinLocation_cost", incomingRequest.get("InvReturn_unitCost"));
            incomingRequest.put("InvBinLocation_aisle", incomingRequest.get("newAisle"));
            incomingRequest.put("InvBinLocation_qtyOnHand", incomingRequest.get("InvBinLocation_newQty")) ;

    		incomingRequest.put("InvBinLocation.vendorId", "");
    		incomingRequest.put("InvBinLocation.manufactNo", "");
    		incomingRequest.put("InvBinLocation.lot", "");
    		incomingRequest.put("InvBinLocation.cost", "0");
    		incomingRequest.put("InvBinLocation.itemDate", "2003-11-12");
    		incomingRequest.put("InvBinLocation.locrow", "");
    		incomingRequest.put("InvBinLocation.tier", "");
    		incomingRequest.put("InvBinLocation.bin", "");
    		incomingRequest.put("InvBinLocation.udf1Code", "");
    		incomingRequest.put("InvBinLocation.udf2Code", "");
    		incomingRequest.put("InvBinLocation.udf3Code", "");
    		incomingRequest.put("InvBinLocation.udf4Code", "");
    		incomingRequest.put("InvBinLocation.udf5Code", "");
    		incomingRequest.put("InvBinLocation.owner", "") ;
    		incomingRequest.put("InvBinLocation.qtyAlloc", "0");
    		incomingRequest.put("InvBinLocation.hdrIc", "");
    		incomingRequest.put("InvBinLocation.icRecLine", "0");
    		incomingRequest.put("InvBinLocation.physActual", "0");
    		incomingRequest.put("InvBinLocation.physOriginal", "0");
    		incomingRequest.put("InvBinLocation.originalAlloc", "0");
    		incomingRequest.put("InvBinLocation_status", "02");
    		incomingRequest.put("InvBinLocation_tempIc", "0");

            incomingRequest.put("viewAction", "view");

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}

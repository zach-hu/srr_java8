
package com.tsa.puridiom.inspectionheader.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.receiptheader.tasks.RecIcHeaderRetrieveByLine;
import com.tsa.puridiom.receiptline.tasks.ReceiptLineRetrieveByHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class InspectionHeaderListUpdateLocations extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map) object;

            List inspectionHeaderList = (List) incomingRequest.get("inspectionHeaderList") ;
            if (inspectionHeaderList == null) inspectionHeaderList = new ArrayList() ;

            String location = (String) incomingRequest.get("ReceiptLine_inspLocation") ;
            String area = (String) incomingRequest.get("ReceiptLine_inspArea") ;
            String storage = (String) incomingRequest.get("ReceiptLine_inspStorage") ;

            for (int i = 0; i < inspectionHeaderList.size(); i++) {
            	InspectionHeader ih = (InspectionHeader) inspectionHeaderList.get(i) ;
            	ih.setLocation(location) ;
            	ih.setArea(area) ;
            	ih.setStorage(storage) ;
            	incomingRequest.put("inspectionHeader", ih) ;

            	InspectionHeaderUpdate task = new InspectionHeaderUpdate() ;
            	task.executeTask(incomingRequest) ;
			}

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }

        return ret;
    }
}

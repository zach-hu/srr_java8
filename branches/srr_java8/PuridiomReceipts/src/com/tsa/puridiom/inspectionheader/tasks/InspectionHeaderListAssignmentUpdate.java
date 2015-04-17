
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
public class InspectionHeaderListAssignmentUpdate extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map) object;

            List inspectionHeaderList = (List) incomingRequest.get("inspectionHeaderList") ;

            for (int i = 0; i < inspectionHeaderList.size(); i++) {
            	InspectionHeader ih = (InspectionHeader) inspectionHeaderList.get(i) ;
            	incomingRequest.put("inspectionHeader",ih) ;
            	if (ih.getIcRecLine() == null || ih.getIcRecLine().compareTo(new BigDecimal(0)) == 0) {
            		InspectionHeaderAssignmentCreate task = new InspectionHeaderAssignmentCreate() ;
            		task.executeTask(incomingRequest) ;
            	} else {
            		InspectionHeaderAssignmentUpdate task = new InspectionHeaderAssignmentUpdate() ;
            		task.executeTask(incomingRequest) ;
            	}
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

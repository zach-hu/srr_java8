package com.tsa.puridiom.receiptheader.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.catalog.tasks.CatalogRetrieveById;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ReceiptHeaderProcessAutoDelivery extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

        try
        {
        	PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
            PuridiomProcess process = null;
        	Map incomingRequest = (Map)object;
        	Map incomingRequestVin = new HashMap();

        	String organizationId = (String)incomingRequest.get("organizationId");
        	List recList = (List)incomingRequest.get("receiptHeaderList");

            if(recList != null && recList.size() > 0)
            {
                for (Iterator iterator = recList.iterator(); iterator.hasNext();)
                {
                	ReceiptHeader rch = (ReceiptHeader) iterator.next();
                	incomingRequest.put("ReceiptHeader_icRecHeader", rch.getIcRecHeader().toString());
                	incomingRequest.put("ReceiptLine_icRecHeader", rch.getIcRecHeader().toString()) ;
                	incomingRequest.put("PoHeader_icPoHeader", rch.getIcPoHeader().toString()) ;
                	incomingRequest.put("PoLine_icPoHeader", rch.getIcPoHeader().toString()) ;
                	incomingRequest.put("ReceiptHeader_receiptNumber", rch.getReceiptNumber()) ;
                	incomingRequest.put("receiptMethod", "FinalizeReceipt") ;
                	incomingRequest.put("isEndStep", "true") ;
                	System.out.println("Processing Receipt: " + rch.getReceiptNumber()) ;

					process = processLoader.loadProcess("receipt-finalize-auto-delivery.xml") ;
					try {
						process.executeProcess(incomingRequest);

					} catch (Exception e) {
						Log.error(this, e);
					}
                }
            }
            else
            {
            	Log.debug(this, "No receipts to auto deliver!");
            }

            Log.debug(this, "AUTODELIVERY WORKING!");
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	Log.error(this, "ReceiptProcessAutoDelivery failed: " + e.getMessage());
            this.setStatus(Status.FAILED);
            throw e;
        }
        return ret;
    }
}

/*
 * Created on Nov 7, 2005
 */
package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import java.util.*;


/**
 * @author Kelli
 */
public class ReceiptValidationRulesData extends Task {

    public Object executeTask(Object object) throws Exception {
        Object ret = null;

        try {
            Map incomingRequest = (Map)object;
            ReceiptHeader header = (ReceiptHeader)incomingRequest.get("receiptHeader");
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            List receiptLineList = (List) incomingRequest.get("receiptLineList");
			String[] c_gfp = (String[]) incomingRequest.get("gfp");
			if(c_gfp == null)
			{
				c_gfp = new String[0];
			}
			String[] udf3Code = (String[]) incomingRequest.get("PoLine_udf3Code");
			if(udf3Code == null)
			{
				udf3Code = new String[0];
			}

            if (header == null) {
                this.setStatus(Status.FAILED);
                throw new TsaException("Receipt Header was not found!");
            }

            incomingRequest.put("header", header);
            incomingRequest.put("poHeader", poHeader);
            incomingRequest.put("requisitionHeader", requisitionHeader);
            incomingRequest.put("lineitems", receiptLineList);
            incomingRequest.put("itemschecked", this.getItemsChecked(c_gfp, udf3Code));
            incomingRequest.put("Labels_moduleAccess", "RECEIVING");
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return ret;
    }

    private List getItemsChecked(String[] lineItems, String[] udf3Code){
    	List itemsChecked = new ArrayList();
   		for (int a = 0; a < lineItems.length; a++){
   			if(lineItems[a].equals("A")){
   				itemsChecked.add(udf3Code[a]);
   			}
   		}
   		return itemsChecked;
    }
}

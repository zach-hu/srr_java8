package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.receipt.exception.ReceiptRejectedItemException;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.math.BigDecimal;
import java.util.*;

public class ReceiptLineProcessRejectedLine extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("returnReceiptLine_qtyReturned")) {
			    Set keySet = incomingRequest.keySet();
			    String qtyReturned = (String) incomingRequest.get("returnReceiptLine_qtyReturned");
			    String createReturn = (String) incomingRequest.get("createReturn");
			    BigDecimal bdZero = new BigDecimal(0);
			    BigDecimal bdQtyReturned = new BigDecimal(0);
			    
			    try {
			    	bdQtyReturned = new BigDecimal(qtyReturned);
			    } catch (Exception bde) {			    	
			    }
			    
			    if (bdQtyReturned.compareTo(bdZero) > 0 || createReturn.equals("Y")) {
			    	ReceiptHeader originalReceiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
			    	ReceiptLine originalReceiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
                    if ((originalReceiptLine.getQtyRejected().compareTo(bdZero) > 0)) {
                        // Process rejected items for receipt line record
    				    PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
    					processLoader.setApplicationName(this.getApplicationName());
    					PuridiomProcess process = processLoader.loadProcess("receiptline-process-rejected-by-id.xml");		            
    	                Map updateParameters = this.getDefaultUpdateParameters(incomingRequest);

    	                
    	                updateParameters.put("originalReceiptHeader", originalReceiptHeader);
	                    updateParameters.put("originalReceiptLine", originalReceiptLine);
	                    updateParameters.put("poHeader", incomingRequest.get("poHeader"));
	                    updateParameters.put("poLine", incomingRequest.get("poLine"));
	                    updateParameters.put("createReturn", createReturn);
	                    
	                    Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("returnReceiptHeader_") == 0 || key.indexOf("returnReceiptLine_") == 0) {
							    //returnReceiptHeader_ and returnReceiptLine_ attributes are needed for returning rejected items
								Object obj =  incomingRequest.get(key);										
								
								//remove "return" prefix for receipt request values
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key.substring(6), arrayObj[0]);
								} else {
								    updateParameters.put(key.substring(6), obj);
								}
							}
						}
						
						process.executeProcess(updateParameters);
						
						ReceiptHeader returnReceiptHeader = (ReceiptHeader) updateParameters.get("receiptHeader");
						List receiptLineList = (List) updateParameters.get("receiptLineList");
						
						if (returnReceiptHeader != null) {
							returnReceiptHeader.setReceiptLineList(receiptLineList);
						}
					    incomingRequest.put("returnReceiptHeader", returnReceiptHeader);
                    }
	            }			    
			}
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
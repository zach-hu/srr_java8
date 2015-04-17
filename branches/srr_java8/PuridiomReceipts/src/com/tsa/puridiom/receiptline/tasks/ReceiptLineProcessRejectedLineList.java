package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.receipt.exception.ReceiptRejectedItemException;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.math.BigDecimal;
import java.util.*;

public class ReceiptLineProcessRejectedLineList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			if (incomingRequest.containsKey("returnReceiptLine_qtyReturned")) {
			    Set keySet = incomingRequest.keySet();
			    Object qtyReturnedObj = incomingRequest.get("returnReceiptLine_qtyReturned");
			    Object createReturnObj = incomingRequest.get("createReturn");
			    List originalReceiptLineList = (List) incomingRequest.get("receiptLineList"); /* receiptLineList just created for Original Receipt */
			    List returnReceiptHeaderList = new ArrayList();
			    BigDecimal bdZero = new BigDecimal(0);
			    
			    if (originalReceiptLineList != null) {
				    PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					processLoader.setApplicationName(this.getApplicationName());
					PuridiomProcess process = processLoader.loadProcess("receiptline-process-rejected-by-id.xml");

			        if (qtyReturnedObj instanceof String[]) {
			            String	qtyReturnedArray[] = (String[]) qtyReturnedObj;
			            String	createReturnArray[] = (String[]) createReturnObj;
			            
			            if (originalReceiptLineList.size() != qtyReturnedArray.length || createReturnArray.length != qtyReturnedArray.length) {
			                String	msg = "ReceiptLineList size does not match the size of the return parameters array.";
			                Log.error(this, msg);
			                throw new ReceiptRejectedItemException(msg);
			            }
			            
			            for (int irl=0; irl < originalReceiptLineList.size(); irl++) {
			                Map updateParameters = this.getDefaultUpdateParameters(incomingRequest);
		                    ReceiptLine originalReceiptLine = (ReceiptLine) originalReceiptLineList.get(irl);
		                    
		                    if (originalReceiptLine != null && originalReceiptLine.getQtyRejected().compareTo(bdZero) > 0 || createReturnArray[irl].equals("Y")) {
		                        // Process rejected items for receipt line record
		                        updateParameters.put("originalReceiptLine", originalReceiptLine);
			                    updateParameters.put("createReturn", createReturnArray[irl]);
			                    
			                    Iterator iterator = keySet.iterator();
								while (iterator.hasNext()) {
									String key = (String) iterator.next();
									if (key.indexOf("returnReceiptHeader_") == 0 || key.indexOf("returnReceiptLine_") == 0) {
									    //returnReceiptHeader_ and returnReceiptLine_ attributes are needed for returning rejected items
										Object obj =  incomingRequest.get(key);										
										
										//remove "return" prefix for receipt request values
										if (obj instanceof String[]) {
											String arrayObj[] = (String[]) obj;
											updateParameters.put(key.substring(6), arrayObj[irl]);
										} else {
										    updateParameters.put(key.substring(6), obj);
										}
									}
								}
								process.executeProcess(updateParameters);
								
								ReceiptHeader receiptHeader = (ReceiptHeader) updateParameters.get("receiptHeader");
								List receiptLineList = (List) updateParameters.get("receiptLineList");
								
								if (receiptHeader != null) {
								    receiptHeader.setReceiptLineList(receiptLineList);
								    returnReceiptHeaderList.add(receiptHeader);
								}
		                    }
		                }
			        }
			        else if (originalReceiptLineList.size() == 1 && qtyReturnedObj instanceof String) {
			            String	qtyReturned = (String) qtyReturnedObj;
			            String	createReturn = (String) createReturnObj;
			            
		                Map updateParameters = this.getDefaultUpdateParameters(incomingRequest);
	                    ReceiptLine originalReceiptLine = (ReceiptLine) originalReceiptLineList.get(0);
 	                    
	                    if (originalReceiptLine.getQtyRejected().compareTo(bdZero) > 0 || createReturn.equals("Y")) {
	                        // Process rejected items for receipt line record
		                    updateParameters.put("originalReceiptLine", originalReceiptLine);
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
							
							ReceiptHeader receiptHeader = (ReceiptHeader) updateParameters.get("receiptHeader");
							List receiptLineList = (List) updateParameters.get("receiptLineList");
							
							if (receiptHeader != null) {
							    receiptHeader.setReceiptLineList(receiptLineList);
							    returnReceiptHeaderList.add(receiptHeader);
							}
	                    }
		            }
			    }
			    
			    incomingRequest.put("returnReceiptHeaderList", returnReceiptHeaderList);
			}
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
	protected Map getDefaultUpdateParameters(Map incomingRequest) {
		Map updateParameters = new HashMap();
		
	    updateParameters.put("originalReceiptHeader", incomingRequest.get("receiptHeader"));
	    updateParameters.put("originalPoHeader", incomingRequest.get("poHeader"));
		updateParameters.put("userId", incomingRequest.get("userId"));
		   updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		updateParameters.put("organizationId", incomingRequest.get("organizationId"));
		updateParameters.put("dbsession", incomingRequest.get("dbsession"));
	    
		return updateParameters;
	}

}
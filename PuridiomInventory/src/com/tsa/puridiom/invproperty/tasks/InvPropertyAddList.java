package com.tsa.puridiom.invproperty.tasks;

import com.tsa.puridiom.entity.InvProperty;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import java.math.BigDecimal;
import java.util.*;

public class InvPropertyAddList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		List receiptLineList = (List) incomingRequest.get("receiptLineList") ;

		try {
			if (incomingRequest.containsKey("InvProperty_tagNumber")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
//				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("invproperty-add-by-id.xml");
				Object tagNumberObj = incomingRequest.get("InvProperty_tagNumber");
				List invPropertyList = new ArrayList();

				Set keySet = incomingRequest.keySet();
				if (tagNumberObj instanceof String[]) {
					//if the tagNumberObj is an array, all InvProperty values should be an array of the same size
					int	arraySize = ((String[]) tagNumberObj).length;

//					incomingRequest.put("InvProperty_icRecLine", icRecLine) ;
					for (int i=0; i < arraySize; i++) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));

						Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("InvProperty_") == 0 || key.equals("ReceiptHeader_receiptNumber") ) {
								//must be a InvProperty_ attribute and should be an array
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								}
							}
						}

						// Have to find icRecLine by icPoLine
						String icPoLine = (String) updateParameters.get("InvProperty_icPoLine") ;
						String icRecLine = "0" ;
						BigDecimal icPol = new BigDecimal(icPoLine) ;

						for (int ix = 0; ix < receiptLineList.size(); ix++) {
							ReceiptLine rcl = (ReceiptLine) receiptLineList.get(ix) ;
							if (icPol.compareTo(rcl.getIcPoLine()) == 0) {
								icRecLine = rcl.getIcRecLine().toString() ;
								break ;
							}
						}

						// Setup Receipt Line Retrieve
						updateParameters.put("InvProperty_icRecLine", icRecLine) ;
						updateParameters.put("ReceiptLine_icRecHeader", incomingRequest.get("ReceiptHeader_icRecHeader")) ;
						updateParameters.put("ReceiptLine_icPoLine", updateParameters.get("InvProperty_icPoLine")) ;
						process.executeProcess(updateParameters);

                        InvProperty invProperty = (InvProperty) updateParameters.get("invProperty");
                        invPropertyList.add(invProperty);
					}
				} else {
					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));

					Iterator iterator = keySet.iterator();
					while (iterator.hasNext()) {
						String key = (String) iterator.next();
						if (key.indexOf("InvProperty_") == 0 || key.equals("ReceiptHeader_receiptNumber") ) {
							//must be a InvProperty_ attribute and should be an array
							Object obj =  incomingRequest.get(key);
							updateParameters.put(key, obj);
						}
					}

					// Have to find icRecLine by icPoLine
					String icPoLine = (String) updateParameters.get("InvProperty_icPoLine") ;
					String icRecLine = "0" ;
					BigDecimal icPol = new BigDecimal(icPoLine) ;

					for (int ix = 0; ix < receiptLineList.size(); ix++) {
						ReceiptLine rcl = (ReceiptLine) receiptLineList.get(ix) ;
						if (icPol.compareTo(rcl.getIcPoLine()) == 0) {
							icRecLine = rcl.getIcRecLine().toString() ;
							break ;
						}
					}

					updateParameters.put("InvProperty_icRecLine", icRecLine) ;
					updateParameters.put("ReceiptLine_icRecHeader", incomingRequest.get("ReceiptHeader_icRecHeader")) ;
					updateParameters.put("ReceiptLine_icPoLine", updateParameters.get("InvProperty_icPoLine")) ;
					updateParameters.put("ReceiptLine_icRecLine", updateParameters.get("InvProperty_icRecLine")) ;

					process.executeProcess(updateParameters);

					InvProperty invProperty = (InvProperty) incomingRequest.get("invProperty");
                    invPropertyList.add(invProperty);
				}

				result = invPropertyList;
			}
			else {
				Log.debug(this, "The value for InvProperty_tagNumber was not found.  No INV_PROPERTY records will be added");
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
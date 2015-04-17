package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class PoLineListCreate extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
		    String	processXml = "poline-create.xml";
		    if (incomingRequest.containsKey("receiptMethod")) {
		        processXml = "receipt-poline-create.xml";
		    }
		    
			if (incomingRequest.containsKey("PoLine_itemNumber")) {
			    Set keySet = incomingRequest.keySet();
			    Object itemNumberObj = incomingRequest.get("PoLine_itemNumber");
			    List poLineList = new ArrayList();
			    List icPoLineList = new ArrayList();

			    PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			    processLoader.setApplicationName(this.getApplicationName());
			    
			    if (itemNumberObj != null && itemNumberObj instanceof String[]) {
			        String	itemNumberArray[] = (String[]) itemNumberObj;
			            
		            for (int il=0; il < itemNumberArray.length; il++) {
				        PuridiomProcess process = processLoader.loadProcess(processXml);
		                Map updateParameters = this.getDefaultUpdateParameters(incomingRequest);

		                Iterator iterator = keySet.iterator();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							if (key.indexOf("PoHeader_") == 0 || key.indexOf("PoLine_") == 0) {
								Object obj =  incomingRequest.get(key);										
								
								if (obj instanceof String[]) {
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[il]);
								} else {
								    updateParameters.put(key, obj);
								}
							}
						}
						process.executeProcess(updateParameters);
								
						PoLine poLine = (PoLine) updateParameters.get("poLine");
						poLineList.add(poLine);
						icPoLineList.add(poLine.getIcPoLine().toString());
			        }
			    } else if (itemNumberObj != null) {
			        PuridiomProcess process = processLoader.loadProcess(processXml);
	                Map updateParameters = this.getDefaultUpdateParameters(incomingRequest);

	                Iterator iterator = keySet.iterator();
					while (iterator.hasNext()) {
						String key = (String) iterator.next();
						if (key.indexOf("PoHeader_") == 0 || key.indexOf("PoLine_") == 0) {
							Object obj =  incomingRequest.get(key);										
							
							if (obj instanceof String[]) {
							    String arrayObj[] = (String[]) obj;
								updateParameters.put(key, arrayObj[0]);
							} else {
							    updateParameters.put(key, obj);
							}
						}
					}
					updateParameters.put("createAction", "SAVE");
					process.executeProcess(updateParameters);
							
					PoLine poLine = (PoLine) updateParameters.get("poLine");
					poLineList.add(poLine);
					icPoLineList.add(poLine.getIcPoLine().toString());
			    }
			    if (icPoLineList.size() == 1) {
			        incomingRequest.put("PoLine_icPoLine", icPoLineList.get(0));
			        incomingRequest.put("ReceiptLine_icPoLine", icPoLineList.get(0));
			    } else {
			        String[] icPoLineStr = new String[icPoLineList.size()];
			        icPoLineStr = (String[]) icPoLineList.toArray(icPoLineStr);		        

			        incomingRequest.put("PoLine_icPoLine", icPoLineStr);
			        incomingRequest.put("ReceiptLine_icPoLine", icPoLineStr);
			    }
			    result = poLineList;
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
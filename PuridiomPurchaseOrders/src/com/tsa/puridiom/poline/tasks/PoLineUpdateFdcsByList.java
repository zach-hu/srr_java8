/**
 * Created on January 19, 2005
 * @author Kelli
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineSetAwardedByList.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import iseries.wsbeans.addpolinetomiscwo.xsd.POI950Result;
import iseries.wsbeans.updateembo.xsd.POI960Input;
import iseries.wsbeans.updateembo.xsd.POI960Result;
import iseries.wsbeans.updatepolinetomiscwo.xsd.POI951Result;
import iseries.wsbeans.updatestockorder.xsd.POI961Result;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tsa.puridiom.fdcs.tasks.*;

public class PoLineUpdateFdcsByList extends Task {

	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		this.setStatus(Status.SUCCEEDED);

		try {
			List poLineList = (List) incomingRequest.get("poLineList");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			BigDecimal	revNumber = poHeader.getRevisionNumber() ;

			String newStatus = (String) incomingRequest.get("newStatus");
			String poNumber = poHeader.getPoNumber();
			String docNumber = poHeader.getUdf5Code();
			String workNumber = poHeader.getUdf7Code();
			String dbsPoNumber = poHeader.getUdf15Code();
			String tranType = HiltonUtility.ckNull(poHeader.getUdf1Code()) ;
			boolean processTran = (tranType.startsWith("RESALE") || tranType.startsWith("INVENT")) ;
			// Only if awarded
			if (poLineList != null && poHeader.getStatus().equals("3030") && (processTran)) {
				for (int i = 0; i < poLineList.size(); i++) {
					PoLine poLine = (PoLine) poLineList.get(i);
					if (!poLine.getStatus().equals(DocumentStatus.CANCELLED)) {
						Map updateRequest = new HashMap();
						updateRequest.put("organizationId", incomingRequest
								.get("organizationId"));
						updateRequest.put("userId", incomingRequest
								.get("userId"));
						// Remove dash from ponumber and convert to bigdecimal
						String dbsKey = HiltonUtility.ckNull(poLine.getMemoLine()) ;
						updateRequest.put("poNumber", poNumber);
						updateRequest.put("docNumber", docNumber);

//						updateRequest.put("workNumber", workNumber);
//						updateRequest.put("segNumber", poHeader.getUdf8Code());
//						updateRequest.put("opNumber", poHeader.getUdf9Code());
//						updateRequest.put("custNumber", poHeader.getUdf11Code());
//						updateRequest.put("chargeCode", poHeader.getUdf10Code()) ;

						updateRequest.put("workNumber", poLine.getUdf1Code());
						updateRequest.put("segNumber", poLine.getUdf2Code());
						updateRequest.put("opNumber", poLine.getUdf3Code());
						updateRequest.put("custNumber", poLine.getUdf4Code());
						updateRequest.put("chargeCode", poLine.getUdf6Code()) ;

						updateRequest.put("vendorName", poHeader.getVendorName());
						updateRequest.put("employeeNo", poHeader.getUdf12Code()) ;

						updateRequest.put("lineNumber", poLine.getLineNumber());
						updateRequest.put("dbsKey", dbsKey);
						updateRequest.put("description", HiltonUtility.ckNull(poLine.getDescription()).toUpperCase());
						updateRequest.put("quantity", poLine.getQuantity());
						updateRequest.put("cost", poLine.getUnitPrice());
						updateRequest.put("seqNumber",poLine.getUdf9Code()) ;
						updateRequest.put("itemNumber", poLine.getItemNumber()) ;

						if (!HiltonUtility.isEmpty(workNumber) &&  ! workNumber.equalsIgnoreCase("override")) {
							if (HiltonUtility.isEmpty(poLine.getUdf9Code())) {
								FdcsAddPoLineToMisWO resp = new FdcsAddPoLineToMisWO();
								POI950Result res = (POI950Result) resp
										.executeTask(updateRequest);
								if (resp.getErrorStr().length() > 0) {
									// Error Occured
									incomingRequest.put("errorStr", "Y") ;
									incomingRequest.put("employeeNo", poHeader.getUdf12Code()) ;
									this.setStatus(Status.FAILED);
								} else {
									poLine.setUdf9Code(res.get_PO_SQN01()) ;
								}
							} else {
								FdcsUpdatePoLineToMisWO resp = new FdcsUpdatePoLineToMisWO();
								POI951Result res = (POI951Result) resp
										.executeTask(updateRequest);
								if (resp.getErrorStr().length() > 0) {
									// Error Occured
									incomingRequest.put("errorStr", "Y") ;
									incomingRequest.put("employeeNo", poHeader.getUdf12Code()) ;
									this.setStatus(Status.FAILED);
								}
							}
						} else if (!HiltonUtility.isEmpty(docNumber) && ! docNumber.equalsIgnoreCase("override")) {
							String docType = docNumber.substring(0, 1);
							if (docType.equals("S") || docType.equals("M")) {
								// Update FDCS Inventory
								if (! HiltonUtility.isEmpty(poLine.getMemoLine())) {
									FdcsUpdateStockOrder resp = new FdcsUpdateStockOrder();
									POI961Result res = (POI961Result) resp
											.executeTask(updateRequest);
									if (resp.getErrorStr().length() > 0) {
										// Error Occured
										this.setStatus(Status.FAILED);
									}
								}
							} else {
								// Only update Backorders if it came from DBS
								if (! HiltonUtility.isEmpty(dbsKey) && HiltonUtility.isEmpty(poLine.getUdf9Code())) {
									FdcsUpdateEMBO resp = new FdcsUpdateEMBO();
									POI960Result res = (POI960Result) resp
											.executeTask(updateRequest);
									if (resp.getErrorStr().length() > 0) {
										// Error occured
										this.setStatus(Status.FAILED);
									} else {
										// Value in this field indicates it has already been updated
										poLine.setUdf9Code("1") ;
									}
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}

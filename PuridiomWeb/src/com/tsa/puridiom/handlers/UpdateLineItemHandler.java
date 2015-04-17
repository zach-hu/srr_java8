/*
 * Created on Jul 25, 2003 
 */
package com.tsa.puridiom.handlers;

import java.util.*;

/**
 * @author Administrator 
 */
public class UpdateLineItemHandler implements IHandler {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.handlers.IHandler#handleRequest(java.util.Map)
	 */
	public Map handleRequest(Map incomingRequest) throws Exception {
		Map viewObjects = new HashMap();
		try{
//			User user = new User();
//			//Map searchCriteria = new HashMap();
//			String requisitionLine = (String)incomingRequest.get("requisitionLine");
//			LineItem lineItem = new LineItem();
//			lineItem.setRequisitionLine(requisitionLine);
//			String quantity = (String)incomingRequest.get("quantity");			
//			String unitPrice = (String)incomingRequest.get("unitPrice");
//			String unitOfMeasure = (String)incomingRequest.get("uom");
//			lineItem.setUnitOfMeasure(unitOfMeasure);
//			BigDecimal bdQuantity = new BigDecimal(quantity);
//			lineItem.setQuantity(bdQuantity);
//			BigDecimal bdUnitPrice = new BigDecimal(unitPrice);
//			lineItem.setUnitPrice(bdUnitPrice);			
//			BigDecimal bdTotal = new BigDecimal(bdQuantity.multiply(bdUnitPrice).doubleValue());
//			lineItem.setTotal(bdTotal);
//			RequisitionManager requisitionManager = new RequisitionManager();
//			requisitionManager.updateRequisitionLineItem(lineItem, user);
//			Requisition requisition = new Requisition();
//			requisition.setRequisitionNumber((String)incomingRequest.get("requisitionNumber"));
//			requisition.setTotal(bdTotal);
//			requisitionManager.updateRequisition(requisition, user);			
//			
//			requisitionManager.retrieveRequisition(requisition, user);
//			viewObjects.put("requisition", requisition);			
		}
		catch(Exception exception){
			throw exception;
		}
		finally{
			return viewObjects;
		}
	}

}

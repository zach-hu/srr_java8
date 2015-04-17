/*
 * Created on Jul 15, 2003 
 */
package com.tsa.puridiom.handlers;

import java.util.*;
/**
 * @author Administrator 
 */
public class DefaultHandler implements IHandler{
	
	public Map handleRequest(Map requestParameters) throws Exception{
		Map view = new HashMap();
		try{
//			GetPurchaseOrderTask getPurchaseOrderTask = new GetPurchaseOrderTask();
//			PurchaseOrder purchaseOrder = (PurchaseOrder)getPurchaseOrderTask.performTask(requestParameters);
//			////system.out.println(purchaseOrder.getPurchaseOrderNumber());
//			requestParameters.put("puchaseOrder", purchaseOrder);
//			ChangeLineItemTotalTask changeLineItemTotalTask = new ChangeLineItemTotalTask();
//			purchaseOrder = (PurchaseOrder)changeLineItemTotalTask.performTask(requestParameters);
//			////system.out.println(purchaseOrder.getPurchaseOrderNumber());
//			requestParameters.put("puchaseOrder", purchaseOrder);
//			UpdatePurchaseOrderTotalTask updatePurchaseOrderTotalTask = new UpdatePurchaseOrderTotalTask();
//			purchaseOrder = (PurchaseOrder)updatePurchaseOrderTotalTask.performTask(requestParameters);
//			////system.out.println(purchaseOrder.getPurchaseOrderNumber());
			
//			view.put("purchaseOrder", purchaseOrder);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			return view;
		}
	}

}

/*
 * Created on Jul 22, 2003 
 */
package com.tsa.puridiom.handlers;

import java.util.*;

/**
 * @author Administrator 
 */
public class GetRequisitionHandler implements IHandler{

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.handlers.IHandler#handleRequest(java.util.Map)
	 */
	public Map handleRequest(Map incomingRequest) throws Exception {
		Map viewObjects = new HashMap();
		try{
//			User user = new User();
//			//Map searchCriteria = new HashMap();
//			String requisitionNumber = (String)incomingRequest.get("requsitionNumber");
//			Requisition requisition = new Requisition();
//			requisition.setRequisitionNumber(requisitionNumber);
//			RequisitionManager requisitionManager = new RequisitionManager();
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

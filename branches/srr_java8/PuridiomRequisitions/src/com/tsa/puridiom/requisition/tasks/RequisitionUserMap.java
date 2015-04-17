package com.tsa.puridiom.requisition.tasks;
import java.util.Map;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Utility ;

public class RequisitionUserMap extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		String	userId = (String) incomingRequest.get("userId") ;
		String	organizationId = (String) incomingRequest.get("organizationId") ;

		PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

		UserManager userManager = UserManager.getInstance() ;
		UserProfile user = userManager.getUser(organizationId, userId) ;

		String setUsrMappItemLocationInvType= (String)(propertiesManager.getProperty("REQ OPTIONS","SET_USRMAPP_ITEMLOCATION_INVTYPE","N"));

		String f1 = propertiesManager.getProperty("Name Udfs","Udf1","") ;
		String f2 = propertiesManager.getProperty("Name Udfs","Udf2","") ;
		String f3 = propertiesManager.getProperty("Name Udfs","Udf3","") ;
		String f4 = propertiesManager.getProperty("Name Udfs","Udf4","") ;
		String f5 = propertiesManager.getProperty("Name Udfs","Udf5","") ;
		String dept = propertiesManager.getProperty("Name Udfs","DEPT","") ;
		String locale = propertiesManager.getProperty("Name Udfs","LOCALE","") ;

		if (! Utility.isEmpty(f1) && !organizationId.equalsIgnoreCase("wpc08p")) {
			incomingRequest.put(f1,user.getNameUdf1()) ;
		}

		if(setUsrMappItemLocationInvType.equalsIgnoreCase("Y"))
		{
			String requisitionType = (String) incomingRequest.get("RequisitionHeader_requisitionType") ;

			if(requisitionType.equalsIgnoreCase(RequisitionType.SUPPLY_REQUEST)||
					requisitionType.equalsIgnoreCase(RequisitionType.TRANSFER_REQUEST)||
					requisitionType.equalsIgnoreCase(RequisitionType.REPLENISH_REQUEST)||
					requisitionType.equalsIgnoreCase(RequisitionType.INVENTORY_RETURN))
			{
				if (! Utility.isEmpty(f2)) {
					incomingRequest.put(f2,user.getNameUdf2()) ;
				}
			}
		}else
		{
			if (! Utility.isEmpty(f2)) {
				incomingRequest.put(f2,user.getNameUdf2()) ;
			}
		}

		if (! Utility.isEmpty(f3)) {
			incomingRequest.put(f3,user.getNameUdf3()) ;
		}
		if (! Utility.isEmpty(f4)) {
			incomingRequest.put(f4,user.getNameUdf4()) ;
		}
		if (! Utility.isEmpty(f5)) {
			incomingRequest.put(f5,user.getNameUdf5()) ;
		}
		if (! Utility.isEmpty(dept)) {
			incomingRequest.put(dept,user.getDepartment()) ;
		}
		if (! Utility.isEmpty(locale)) {
			incomingRequest.put(locale,user.getLocale()) ;
		}

		incomingRequest.put("requisitionerName",user.getDisplayName()) ;

		String buyerId = null ;
	    if (f1.indexOf("RequisitionHeader_buyerCode") != -1) {
	    	buyerId = user.getNameUdf1() ;
	    } else if (f2.indexOf("RequisitionHeader_buyerCode") != -1) {
	    	buyerId = user.getNameUdf2() ;
	    } else  if (f3.indexOf("RequisitionHeader_buyerCode") != -1) {
	    	buyerId = user.getNameUdf3() ;
	    } else if (f4.indexOf("RequisitionHeader_buyerCode") != -1) {
	    	buyerId = user.getNameUdf4() ;
	    } else if (f5.indexOf("RequisitionHeader_buyerCode") != -1) {
	    	buyerId = user.getNameUdf5() ;
	    }

	    UserProfile buyer = userManager.getUser(organizationId, buyerId) ;
		incomingRequest.put("buyerName",buyer.getDisplayName()) ;

		return null ;
	}

}
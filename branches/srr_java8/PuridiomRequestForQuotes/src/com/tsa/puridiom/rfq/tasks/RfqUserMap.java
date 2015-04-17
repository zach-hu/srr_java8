package com.tsa.puridiom.rfq.tasks;
import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Utility ;

public class RfqUserMap extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			String	userId = (String) incomingRequest.get("userId") ;
			String organizationId = (String)incomingRequest.get("organizationId");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

			UserManager userManager = UserManager.getInstance() ;
			UserProfile user = userManager.getUser(organizationId, userId) ;

			String f1 = propertiesManager.getProperty("Rfq Name Udfs","Udf1","") ;
			String f2 = propertiesManager.getProperty("Rfq Name Udfs","Udf2","") ;
			String f3 = propertiesManager.getProperty("Rfq Name Udfs","Udf3","") ;
			String f4 = propertiesManager.getProperty("Rfq Name Udfs","Udf4","") ;
			String f5 = propertiesManager.getProperty("Rfq Name Udfs","Udf5","") ;
			String dept = propertiesManager.getProperty("Rfq Name Udfs","DEPT","") ;
			String locale = propertiesManager.getProperty("Rfq Name Udfs","LOCALE","") ;

			if (! Utility.isEmpty(f1)) {
				incomingRequest.put(f1,user.getNameUdf1()) ;
			}
			if (! Utility.isEmpty(f2)) {
				incomingRequest.put(f2,user.getNameUdf2()) ;
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

		    incomingRequest.put("buyerName",user.getDisplayName()) ;

		    this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}

		return null ;
	}

}
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;
import com.tsagate.foundation.utility.Dates ;

import com.tsa.puridiom.common.documents.*;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class RequisitionLineSetupWarehouse extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId") ;

		incomingRequest.put("RequisitionLine_udf5Code", UserManager.getInstance().getUser(organizationId, userId).getNameUdf3());

		return null ;
	}
}
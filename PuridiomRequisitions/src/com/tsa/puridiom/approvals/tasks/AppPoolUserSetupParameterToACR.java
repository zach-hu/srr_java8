package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class AppPoolUserSetupParameterToACR extends Task {

	public Object executeTask(Object object) throws Exception	{
		Map incomingRequest = (Map)object;
		Map routingListGroups = new HashMap();

        try {
            incomingRequest.put("AppPooluser_poolid","ADMIN CHECK REV");
            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
			this.status = Status.FAILED;
			throw e;
        }
     
        
		return null ;
	}
}

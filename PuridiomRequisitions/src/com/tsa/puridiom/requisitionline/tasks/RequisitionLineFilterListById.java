package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.rule.operator.OperatorTypes;
import com.tsagate.foundation.utility.*;
import java.util.*;

public class RequisitionLineFilterListById extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	requisitionLineList = (List) incomingRequest.get("requisitionLineList");
			String	icReqLine = (String) incomingRequest.get("icReqLine");
			RequisitionLine reqLine = null;

			FilterList reqLineFilterList = new FilterList(requisitionLineList);
			reqLineFilterList.addFilter("icReqLine", OperatorTypes.equal, icReqLine);
					
			List reqLines = reqLineFilterList.filter();
					
			if (reqLines != null && reqLines.size() > 0) {
				reqLine = (RequisitionLine) reqLines.get(0);
			}
			
			result = reqLine;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
/*
 * Created on Sept 14, 2005
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class PoLineGetCommodityCode extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		PoLine poLine = (PoLine) incomingRequest.get("poLine");
		String commodity = "";
		if (poLine != null)
			commodity = poLine.getCommodity();
		incomingRequest.put("Commodity_commodity", commodity);
		return null;
	}
}

/*
 * Created on Sept 14, 2005
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class PoLineGetDiscount extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		PoLine poLine = (PoLine) incomingRequest.get("poLine");
		BigDecimal discount = new BigDecimal(0);
		if (poLine != null)
			discount = poLine.getDiscountPercent();

		return discount;
	}
}

package com.tsa.puridiom.inspectionline.tasks;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 *
 * @author mdanz
 *
 */

public class InspectionLineDeleteByLine extends Task {

	/* (non-Javadoc)
	 * see com.tsagate.puridiom.process.ITTask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
		String icInspNo = (String)incomingRequest.get("InspectionLine_icInspNo");
		String icInspLine = (String)incomingRequest.get("InspectionLine_icInspLine");
		if (icInspLine == null) {
			icInspLine = "0";
		}

		String queryString = "from InspectionLine as il where il.id.icInspNo = ?";

		BigDecimal bdInspNo = new BigDecimal(icInspNo);
		BigDecimal bdInspLine = new BigDecimal(icInspLine);

		this.setStatus(dbs.delete(queryString, new Object[] {bdInspNo}, new Type[] {Hibernate.BIG_DECIMAL}));

		return object;
	}
}


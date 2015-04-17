package com.tsa.puridiom.inspection.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class InspectionRetrieveListByLineSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null ;

		if (incomingRequest.containsKey("requisitionLine")) {
			RequisitionLine rl = (RequisitionLine) incomingRequest.get("requisitionLine") ;
			incomingRequest.put("InspectionLine_icMsrLine", rl.getIcLineHistory()) ;
		}

		if (incomingRequest.containsKey("poLine")) {
			PoLine pl = (PoLine) incomingRequest.get("poLine") ;
			incomingRequest.put("InspectionLine_icMsrLine", pl.getIcLineHistory()) ;
		}

		if (incomingRequest.containsKey("rfqLine")) {
			RfqLine rfql = (RfqLine) incomingRequest.get("rfqLine") ;
			incomingRequest.put("InspectionLine_icMsrLine", rfql.getIcLineHistory()) ;
		}

		return result ;
	}
}
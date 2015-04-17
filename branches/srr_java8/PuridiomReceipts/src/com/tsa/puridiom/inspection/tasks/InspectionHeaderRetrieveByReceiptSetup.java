package com.tsa.puridiom.inspection.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class InspectionHeaderRetrieveByReceiptSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null ;

		ReceiptLine rl = (ReceiptLine) incomingRequest.get("receiptLine") ;
		incomingRequest.put("InspectionLine_icMsrLine", rl.getIcLineHistory()) ;
		incomingRequest.put("PoLine_icPoLine", rl.getIcPoLine().toString()) ;

		PoLine poLine = (PoLine) incomingRequest.get("poLine") ;
		if (poLine == null) poLine = rl.getPoLine() ;
		if (poLine ==  null) poLine = new PoLine() ;

		return result ;
	}
}
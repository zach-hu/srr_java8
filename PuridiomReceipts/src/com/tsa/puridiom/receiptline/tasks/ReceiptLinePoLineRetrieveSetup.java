package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ReceiptLinePoLineRetrieveSetup extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		ReceiptLine rcLine = (ReceiptLine) incomingRequest.get("receiptLine") ;
		if (rcLine != null) incomingRequest.put("PoLine_icPoLine", rcLine.getIcPoLine().toString()) ;

		return result;
	}

}
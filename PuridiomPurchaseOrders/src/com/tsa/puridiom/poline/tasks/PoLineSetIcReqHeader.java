package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
//import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoLineSetIcReqHeader extends Task{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
			String isPoLineLinkedToReq = "N";

			//PoLine will be used to set LineReq History description.
			if (incomingRequest.containsKey("poLine") ){
				incomingRequest.put("PoLine", incomingRequest.get("poLine") );
			}

			if (incomingRequest.containsKey("PoLine_icReqLineOld") && incomingRequest.containsKey("PoLine_icReqLine"))
            {
				if ( !((String) incomingRequest.get("PoLine_icReqLineOld")).equals( (String)incomingRequest.get("PoLine_icReqLine") ) )
             	{
					isPoLineLinkedToReq = "Y";
             	}
            }

			PoLine poLine = (PoLine) incomingRequest.get("poLine");

			incomingRequest.put("RequisitionLine_icReqLine", poLine.getIcReqLine().toString());
			incomingRequest.put("RequisitionLine_status", poLine.getStatus().toString() );
			incomingRequest.put("isPoLineLinkedToReq", isPoLineLinkedToReq );

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
package com.tsa.puridiom.vendorresponse.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendorResponseRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("VendorResponse_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			String icQuestionString = (String) incomingRequest.get("VendorResponse_icQuestion");
			BigDecimal icQuestion = new BigDecimal ( icQuestionString );
			String vendorId = (String ) incomingRequest.get("VendorResponse_vendorId");

			String queryString = "from VendorResponse as VendorResponse where VendorResponse.id.icRfqHeader = ? and VendorResponse.id.icQuestion = ? and VendorResponse.id.vendorId = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRfqHeader, icQuestion, vendorId, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
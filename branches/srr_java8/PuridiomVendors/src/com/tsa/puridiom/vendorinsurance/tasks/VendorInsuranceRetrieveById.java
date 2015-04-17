package com.tsa.puridiom.vendorinsurance.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendorInsuranceRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoHeaderString = (String ) incomingRequest.get("VendorInsurance_icPoHeader");
			BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );

			String queryString = "from VendorInsurance as VendorInsurance where VendorInsurance.icPoHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPoHeader} , new Type[] {Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			else
			{
				String vendorId = (String) incomingRequest.get("VendorInsurance_vendorId");
				queryString = "from VendorInsuranceDefault as VendorInsurance where VendorInsurance.vendorId = ? ";
				resultList = dbs.query(queryString, new Object[] {vendorId} , new Type[] {Hibernate.STRING}) ;
				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
					incomingRequest.put("VendorInsuranceDefault", "true");
				}
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
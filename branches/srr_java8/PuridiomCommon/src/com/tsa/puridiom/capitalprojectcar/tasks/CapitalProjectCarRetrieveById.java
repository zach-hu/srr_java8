package com.tsa.puridiom.capitalprojectcar.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CapitalProjectCarRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String icProjectCarString = (String) incomingRequest.get("CapitalProjectCar_icProjectCar");
			BigDecimal icProjectCar = new BigDecimal ( icProjectCarString );

			String queryString = "from CapitalProjectCar as CapitalProjectCar where CapitalProjectCar.id.icProjectCar = ? ";
			List resultList = dbs.query(queryString, new Object[] {icProjectCar, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

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
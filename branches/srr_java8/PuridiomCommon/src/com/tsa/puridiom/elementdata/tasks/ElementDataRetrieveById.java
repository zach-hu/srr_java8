package com.tsa.puridiom.elementdata.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ElementDataRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String formId = (String ) incomingRequest.get("ElementData_formId");
			String icHeaderString = (String) incomingRequest.get("ElementData_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("ElementData_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			if (icLineString == null) icLineString = "0";
			String icSequenceString = (String) incomingRequest.get("ElementData_icSequence") ;
			if (icSequenceString == null) icSequenceString = "0";
			BigDecimal icSequence = new BigDecimal ( icSequenceString );

			String elementId = (String ) incomingRequest.get("ElementData_elementId");

			String queryString = "from ElementData as ElementData where ElementData.id.formId = ? and ElementData.id.icHeader = ? and ElementData.id.icLine = ? and ElementData.id.icSequence = ? and ElementData.id.elementId = ? ";
			List resultList = dbs.query(queryString, new Object[] {formId, icHeader, icLine, icSequence, elementId, } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

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
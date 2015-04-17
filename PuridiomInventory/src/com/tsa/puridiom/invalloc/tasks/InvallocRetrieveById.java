package com.tsa.puridiom.invalloc.tasks;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvallocRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String referenceType = (String ) incomingRequest.get("referenceType");
		String icLocString = (String) incomingRequest.get("icLoc");
		BigDecimal icLoc = new BigDecimal ( icLocString );
		String icLineString = (String) incomingRequest.get("icLine");
		BigDecimal icLine = new BigDecimal ( icLineString );

		String queryString = "from InvAlloc as invAlloc where invAlloc.id.referenceType = ?,invAlloc.id.icLoc = ?,invAlloc.id.icLine = ?";
		List result = dbs.query(queryString, new Object[] {referenceType, icLoc, icLine, } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
				
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}

}
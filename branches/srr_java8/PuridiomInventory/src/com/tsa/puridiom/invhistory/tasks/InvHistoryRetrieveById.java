package com.tsa.puridiom.invhistory.tasks;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvHistoryRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String seqNumberString = (String) incomingRequest.get("seqNumber");
		BigDecimal seqNumber = new BigDecimal ( seqNumberString );
		String itemNumber = (String ) incomingRequest.get("itemNumber");

		String queryString = "from InvHistory as invHistory where invHistory.id.seqNumber = ?,invHistory.id.itemNumber = ?";
		List result = dbs.query(queryString, new Object[] {seqNumber, itemNumber, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;
				
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}

}
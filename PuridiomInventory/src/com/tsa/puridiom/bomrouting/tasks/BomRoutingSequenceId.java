package com.tsa.puridiom.bomrouting.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;

public class BomRoutingSequenceId extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String 		id = (String) incomingRequest.get("BomRouting_stageId");
		String		seq = "01" ;
		if(incomingRequest.containsKey("BomRouting_icMethod"))
		{
			BigDecimal icMethod = new BigDecimal((String) incomingRequest.get("BomRouting_icMethod"));
			String sql = "Select MAX(bomRouting.stageId) from BomRouting as bomRouting " +
									"Where bomRouting.icMethod = ?";

			List resultList = dbs.query(sql, new Object[] {icMethod} , new Type[] { Hibernate.BIG_DECIMAL });

			if(resultList != null && resultList.size() > 0) 	{
				if (resultList.get(0) != null) {
					seq = resultList.get(0).toString().substring(0, 2);
					seq = Integer.toString(Integer.parseInt(seq) + 1) ;
					if (seq.length() < 2) seq = "0" + seq ;
				}
			} else {
				seq = "01" ;
			}
			id = seq + "-" + id ;
		}

		this.setStatus(dbs.getStatus()) ;
		return id ;
	}
}
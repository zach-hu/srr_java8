package com.tsa.puridiom.doctext.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class DocTextRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from DocText as doctext where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		
		if(incomingRequest.containsKey("DocText_icText"))
		{			
			String icText = (String) incomingRequest.get("DocText_icText");
			args.add(icText);
			types.add(Hibernate.STRING);
			queryString.append(" AND doctext.id.icText = ?");
		}
		if(incomingRequest.containsKey("DocText_stdText"))
		{			
			String stdText = (String) incomingRequest.get("DocText_stdText");
			args.add(stdText);
			types.add(Hibernate.STRING);
			queryString.append(" AND doctext.stdText = ?");
		}
		if(incomingRequest.containsKey("DocText_referenceType"))
		{			
			String referenceType = (String) incomingRequest.get("DocText_referenceType");
			args.add(referenceType);
			types.add(Hibernate.STRING);
			queryString.append(" AND doctext.referenceType = ?");
		}
		if(incomingRequest.containsKey("DocText_idReference"))
		{			
			String idReference = (String) incomingRequest.get("DocText_idReference");
			args.add(idReference);
			types.add(Hibernate.STRING);
			queryString.append(" AND doctext.idReference = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()]));
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
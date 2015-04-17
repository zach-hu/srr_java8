package com.tsa.puridiom.assetnote.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class AssetNoteRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from AssetNote as assetnote where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("AssetNote_tagNumber"))
		{			
			String tagNumber = (String) incomingRequest.get("AssetNote_tagNumber");
			args.add(tagNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetnote.id.tagNumber = ?");
		}
		if(incomingRequest.containsKey("AssetNote_sequenceNo"))
		{			
			String sequenceNo = (String) incomingRequest.get("AssetNote_sequenceNo");
			args.add(sequenceNo);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetnote.id.sequenceNo = ?");
		}
		if(incomingRequest.containsKey("AssetNote_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("AssetNote_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetnote.dateEntered = ?");
		}
		if(incomingRequest.containsKey("AssetNote_dateChanged"))
		{			
			String dateChanged = (String) incomingRequest.get("AssetNote_dateChanged");
			args.add(dateChanged);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetnote.dateChanged = ?");
		}
		if(incomingRequest.containsKey("AssetNote_userId"))
		{			
			String userId = (String) incomingRequest.get("AssetNote_userId");
			args.add(userId);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetnote.userId = ?");
		}
		if(incomingRequest.containsKey("AssetNote_stdText"))
		{			
			String stdText = (String) incomingRequest.get("AssetNote_stdText");
			args.add(stdText);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetnote.stdText = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
package com.tsa.puridiom.assetlocation.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class AssetLocationRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from AssetLocation as assetlocation where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("AssetLocation_tagNumber"))
		{
			String tagNumber = (String) incomingRequest.get("AssetLocation_tagNumber");
			args.add(tagNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.id.tagNumber = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_sequenceNo"))
		{
			String sequenceNo = (String) incomingRequest.get("AssetLocation_sequenceNo");
			args.add(sequenceNo);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.id.sequenceNo = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_locationType"))
		{
			String locationType = (String) incomingRequest.get("AssetLocation_locationType");
			args.add(locationType);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.locationType = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_division"))
		{
			String division = (String) incomingRequest.get("AssetLocation_division");
			args.add(division);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.division = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_department"))
		{
			String department = (String) incomingRequest.get("AssetLocation_department");
			args.add(department);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.department = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_facility"))
		{
			String facility = (String) incomingRequest.get("AssetLocation_facility");
			args.add(facility);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.facility = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_building"))
		{
			String building = (String) incomingRequest.get("AssetLocation_building");
			args.add(building);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.building = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_room"))
		{
			String room = (String) incomingRequest.get("AssetLocation_room");
			args.add(room);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.room = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_userId"))
		{
			String userId = (String) incomingRequest.get("AssetLocation_userId");
			args.add(userId);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.userId = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_dateAssigned"))
		{
			String dateAssigned = (String) incomingRequest.get("AssetLocation_dateAssigned");
			args.add(dateAssigned);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.dateAssigned = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_telephone"))
		{
			String telephone = (String) incomingRequest.get("AssetLocation_telephone");
			args.add(telephone);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.telephone = ?");
		}
		if(incomingRequest.containsKey("AssetLocation_dateChanged"))
		{
			String dateChanged = (String) incomingRequest.get("AssetLocation_dateChanged");
			args.add(dateChanged);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetlocation.dateChanged = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
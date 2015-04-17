package com.tsa.puridiom.assetcost.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class AssetCostRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from AssetCost as assetcost where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("AssetCost_tagNumber"))
		{
			String tagNumber = (String) incomingRequest.get("AssetCost_tagNumber");
			args.add(tagNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.id.tagNumber = ?");
		}
		if(incomingRequest.containsKey("AssetCost_sequenceNo"))
		{
			String sequenceNo = (String) incomingRequest.get("AssetCost_sequenceNo");
			args.add(sequenceNo);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.id.sequenceNo = ?");
		}
		if(incomingRequest.containsKey("AssetCost_amount"))
		{
			String amount = (String) incomingRequest.get("AssetCost_amount");
			args.add(amount);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.amount = ?");
		}
		if(incomingRequest.containsKey("AssetCost_extendLifeFlag"))
		{
			String extendLifeFlag = (String) incomingRequest.get("AssetCost_extendLifeFlag");
			args.add(extendLifeFlag);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.extendLifeFlag = ?");
		}
		if(incomingRequest.containsKey("AssetCost_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("AssetCost_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.dateEntered = ?");
		}
		if(incomingRequest.containsKey("AssetCost_dateReceived"))
		{
			String dateReceived = (String) incomingRequest.get("AssetCost_dateReceived");
			args.add(dateReceived);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.dateReceived = ?");
		}
		if(incomingRequest.containsKey("AssetCost_poNumber"))
		{
			String poNumber = (String) incomingRequest.get("AssetCost_poNumber");
			args.add(poNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.poNumber = ?");
		}
		if(incomingRequest.containsKey("AssetCost_poVendor"))
		{
			String poVendor = (String) incomingRequest.get("AssetCost_poVendor");
			args.add(poVendor);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.poVendor = ?");
		}
		if(incomingRequest.containsKey("AssetCost_description"))
		{
			String description = (String) incomingRequest.get("AssetCost_description");
			args.add(description);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.description = ?");
		}
		if(incomingRequest.containsKey("AssetCost_dateChanged"))
		{
			String dateChanged = (String) incomingRequest.get("AssetCost_dateChanged");
			args.add(dateChanged);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.dateChanged = ?");
		}
		if(incomingRequest.containsKey("AssetCost_lastChgBy"))
		{
			String lastChgBy = (String) incomingRequest.get("AssetCost_lastChgBy");
			args.add(lastChgBy);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetcost.lastChgBy = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()]));
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
package com.tsa.puridiom.assetservice.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class AssetServiceRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from AssetService as assetservice where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("AssetService_tagNumber"))
		{
			String tagNumber = (String) incomingRequest.get("AssetService_tagNumber");
			args.add(tagNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.id.tagNumber = ?");
		}
		if(incomingRequest.containsKey("AssetService_sequenceNo"))
		{
			String sequenceNo = (String) incomingRequest.get("AssetService_sequenceNo");
			args.add(sequenceNo);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.id.sequenceNo = ?");
		}
		if(incomingRequest.containsKey("AssetService_serviceCallDate"))
		{
			String serviceCallDate = (String) incomingRequest.get("AssetService_serviceCallDate");
			args.add(serviceCallDate);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.serviceCallDate = ?");
		}
		if(incomingRequest.containsKey("AssetService_callInitiatedBy"))
		{
			String callInitiatedBy = (String) incomingRequest.get("AssetService_callInitiatedBy");
			args.add(callInitiatedBy);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.callInitiatedBy = ?");
		}
		if(incomingRequest.containsKey("AssetService_dateInitiated"))
		{
			String dateInitiated = (String) incomingRequest.get("AssetService_dateInitiated");
			args.add(dateInitiated);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.dateInitiated = ?");
		}
		if(incomingRequest.containsKey("AssetService_responseDate"))
		{
			String responseDate = (String) incomingRequest.get("AssetService_responseDate");
			args.add(responseDate);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.responseDate = ?");
		}
		if(incomingRequest.containsKey("AssetService_completionDate"))
		{
			String completionDate = (String) incomingRequest.get("AssetService_completionDate");
			args.add(completionDate);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.completionDate = ?");
		}
		if(incomingRequest.containsKey("AssetService_serviceAction"))
		{
			String serviceAction = (String) incomingRequest.get("AssetService_serviceAction");
			args.add(serviceAction);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.serviceAction = ?");
		}
		if(incomingRequest.containsKey("AssetService_serviceCost"))
		{
			String serviceCost = (String) incomingRequest.get("AssetService_serviceCost");
			args.add(serviceCost);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.serviceCost = ?");
		}
		if(incomingRequest.containsKey("AssetService_lastChgBy"))
		{
			String lastChgBy = (String) incomingRequest.get("AssetService_lastChgBy");
			args.add(lastChgBy);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.lastChgBy = ?");
		}
		if(incomingRequest.containsKey("AssetService_dateChanged"))
		{
			String dateChanged = (String) incomingRequest.get("AssetService_dateChanged");
			args.add(dateChanged);
			types.add(Hibernate.STRING);
			queryString.append(" AND assetservice.dateChanged = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
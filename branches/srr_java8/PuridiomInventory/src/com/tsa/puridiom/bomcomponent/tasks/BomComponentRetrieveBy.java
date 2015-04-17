package com.tsa.puridiom.bomcomponent.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class BomComponentRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from BomComponent as bomcomponent where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("BomComponent_icComponent"))
		{
			String icComponent = (String) incomingRequest.get("BomComponent_icComponent");
			args.add(icComponent);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.icComponent = ?");
		}
		if(incomingRequest.containsKey("BomComponent_parentItem"))
		{
			String parentItem = (String) incomingRequest.get("BomComponent_parentItem");
			args.add(parentItem);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.parentItem = ?");
		}
		if(incomingRequest.containsKey("BomComponent_componentItem"))
		{
			String componentItem = (String) incomingRequest.get("BomComponent_componentItem");
			args.add(componentItem);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.componentItem = ?");
		}
		if(incomingRequest.containsKey("BomComponent_componentType"))
		{
			String componentType = (String) incomingRequest.get("BomComponent_componentType");
			args.add(componentType);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.componentType = ?");
		}
		if(incomingRequest.containsKey("BomComponent_bomLine"))
		{
			String bomLine = (String) incomingRequest.get("BomComponent_bomLine");
			args.add(bomLine);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.bomLine = ?");
		}
		if(incomingRequest.containsKey("BomComponent_description"))
		{
			String description = (String) incomingRequest.get("BomComponent_description");
			args.add(description);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.description = ?");
		}
		if(incomingRequest.containsKey("BomComponent_unitOfMeasure"))
		{
			String unitOfMeasure = (String) incomingRequest.get("BomComponent_unitOfMeasure");
			args.add(unitOfMeasure);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.unitOfMeasure = ?");
		}
		if(incomingRequest.containsKey("BomComponent_usageQty"))
		{
			String usageQty = (String) incomingRequest.get("BomComponent_usageQty");
			args.add(usageQty);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.usageQty = ?");
		}
		if(incomingRequest.containsKey("BomComponent_estCost"))
		{
			String estCost = (String) incomingRequest.get("BomComponent_estCost");
			args.add(estCost);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.estCost = ?");
		}
		if(incomingRequest.containsKey("BomComponent_scrapPerc"))
		{
			String scrapPerc = (String) incomingRequest.get("BomComponent_scrapPerc");
			args.add(scrapPerc);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.scrapPerc = ?");
		}
		if(incomingRequest.containsKey("BomComponent_fromDate"))
		{
			String fromDate = (String) incomingRequest.get("BomComponent_fromDate");
			args.add(fromDate);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.fromDate = ?");
		}
		if(incomingRequest.containsKey("BomComponent_thruDate"))
		{
			String thruDate = (String) incomingRequest.get("BomComponent_thruDate");
			args.add(thruDate);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.thruDate = ?");
		}
		if(incomingRequest.containsKey("BomComponent_methodId"))
		{
			String methodId = (String) incomingRequest.get("BomComponent_methodId");
			args.add(methodId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.methodId = ?");
		}
		if(incomingRequest.containsKey("BomComponent_invtype"))
		{
			String invtype = (String) incomingRequest.get("BomComponent_invtype");
			args.add(invtype);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.invtype = ?");
		}
		if(incomingRequest.containsKey("BomComponent_notes"))
		{
			String notes = (String) incomingRequest.get("BomComponent_notes");
			args.add(notes);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.notes = ?");
		}
		if(incomingRequest.containsKey("BomComponent_stageId"))
		{
			String stageId = (String) incomingRequest.get("BomComponent_stageId");
			args.add(stageId);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.stageId = ?");
		}
		if(incomingRequest.containsKey("BomComponent_descType"))
		{
			String descType = (String) incomingRequest.get("BomComponent_descType");
			args.add(descType);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.descType = ?");
		}
		if(incomingRequest.containsKey("BomComponent_fixOrvar"))
		{
			String fixOrvar = (String) incomingRequest.get("BomComponent_fixOrvar");
			args.add(fixOrvar);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.fixOrvar = ?");
		}
		if(incomingRequest.containsKey("BomComponent_taxAc"))
		{
			String taxAc = (String) incomingRequest.get("BomComponent_taxAc");
			args.add(taxAc);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.taxAc = ?");
		}
		if(incomingRequest.containsKey("BomComponent_inOut"))
		{
			String inOut = (String) incomingRequest.get("BomComponent_inOut");
			args.add(inOut);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.inOut = ?");
		}
		if(incomingRequest.containsKey("BomComponent_unitPrice"))
		{
			String unitPrice = (String) incomingRequest.get("BomComponent_unitPrice");
			args.add(unitPrice);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.unitPrice = ?");
		}
		if(incomingRequest.containsKey("BomComponent_unitCost"))
		{
			String unitCost = (String) incomingRequest.get("BomComponent_unitCost");
			args.add(unitCost);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.unitCost = ?");
		}
		if(incomingRequest.containsKey("BomComponent_primaryRes"))
		{
			String primaryRes = (String) incomingRequest.get("BomComponent_primaryRes");
			queryString.append(" AND bomcomponent.primaryRes = '" + primaryRes + "'");
		}
		if(incomingRequest.containsKey("BomComponent_operName"))
		{
			String operName = (String) incomingRequest.get("BomComponent_operName");
			queryString.append(" AND bomcomponent.operName = '" + operName + "'");
		}
		if(incomingRequest.containsKey("BomComponent_featureSl"))
		{
			String featureSl = (String) incomingRequest.get("BomComponent_featureSl");
			queryString.append(" AND bomcomponent.featureSl = '" + featureSl + "'");
		}
		if(incomingRequest.containsKey("BomComponent_costRatio"))
		{
			String costRatio = (String) incomingRequest.get("BomComponent_costRatio");
			queryString.append(" AND bomcomponent.costRatio = '" + costRatio + "'");
		}
		if(incomingRequest.containsKey("BomComponent_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("BomComponent_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.dateEntered = ?");
		}
		if(incomingRequest.containsKey("BomComponent_owner"))
		{
			String owner = (String) incomingRequest.get("BomComponent_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND bomcomponent.owner = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
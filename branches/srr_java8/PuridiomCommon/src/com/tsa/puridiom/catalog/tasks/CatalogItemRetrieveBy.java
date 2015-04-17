package com.tsa.puridiom.catalog.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

public class CatalogItemRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from CatalogItem as catalogitem where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();

		if(incomingRequest.containsKey("CatalogItem_catalogId"))
		{
			String catalogId = (String) incomingRequest.get("CatalogItem_catalogId");
			args.add(catalogId);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.id.catalogId = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_itemNumber"))
		{
			String itemNumber = (String) incomingRequest.get("CatalogItem_itemNumber");
			args.add(itemNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.id.itemNumber = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_umCode"))
		{
			String umCode = (String) incomingRequest.get("CatalogItem_umCode");
			args.add(umCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.umCode = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_commodity"))
		{
			String commodity = (String) incomingRequest.get("CatalogItem_commodity");
			args.add(commodity);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.commodity = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_cost"))
		{
			String cost = (String) incomingRequest.get("CatalogItem_cost");
			args.add(cost);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.cost = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_icText"))
		{
			String icText = (String) incomingRequest.get("CatalogItem_icText");
			args.add(icText);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.icText = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("CatalogItem_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.dateEntered = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_dateExpires"))
		{
			String dateExpires = (String) incomingRequest.get("CatalogItem_dateExpires");
			args.add(dateExpires);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.dateExpires = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_status"))
		{
			String status = (String) incomingRequest.get("CatalogItem_status");
			args.add(status);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.status = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_owner"))
		{
			String owner = (String) incomingRequest.get("CatalogItem_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.owner = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_source"))
		{
			String source = (String) incomingRequest.get("CatalogItem_source");
			args.add(source);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.source = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_imageFile"))
		{
			String imageFile = (String) incomingRequest.get("CatalogItem_imageFile");
			args.add(imageFile);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.imageFile = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_taxable"))
		{
			String taxable = (String) incomingRequest.get("CatalogItem_taxable");
			args.add(taxable);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.taxable = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_udf1Code"))
		{
			String udf1Code = (String) incomingRequest.get("CatalogItem_udf1Code");
			args.add(udf1Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.udf1Code = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_udf2Code"))
		{
			String udf2Code = (String) incomingRequest.get("CatalogItem_udf2Code");
			args.add(udf2Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.udf2Code = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_udf3Code"))
		{
			String udf3Code = (String) incomingRequest.get("CatalogItem_udf3Code");
			args.add(udf3Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.udf3Code = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_udf4Code"))
		{
			String udf4Code = (String) incomingRequest.get("CatalogItem_udf4Code");
			args.add(udf4Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.udf4Code = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_udf5Code"))
		{
			String udf5Code = (String) incomingRequest.get("CatalogItem_udf5Code");
			args.add(udf5Code);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.udf5Code = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_type"))
		{
			String type = (String) incomingRequest.get("CatalogItem_type");
			args.add(type);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.type = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_kit"))
		{
			String kit = (String) incomingRequest.get("CatalogItem_kit");
			args.add(kit);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.kit = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_mfgName"))
		{
			String mfgName = (String) incomingRequest.get("CatalogItem_mfgName");
			args.add(mfgName);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.mfgName = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_modelNumber"))
		{
			String modelNumber = (String) incomingRequest.get("CatalogItem_modelNumber");
			args.add(modelNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.modelNumber = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_leadtime"))
		{
			String leadtime = (String) incomingRequest.get("CatalogItem_leadtime");
			args.add(leadtime);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.leadtime = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_dscQty1"))
		{
			String dscQty1 = (String) incomingRequest.get("CatalogItem_dscQty1");
			args.add(dscQty1);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.dscQty1 = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_dscCost1"))
		{
			String dscCost1 = (String) incomingRequest.get("CatalogItem_dscCost1");
			args.add(dscCost1);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.dscCost1 = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_dscQty2"))
		{
			String dscQty2 = (String) incomingRequest.get("CatalogItem_dscQty2");
			args.add(dscQty2);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.dscQty2 = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_dscCost2"))
		{
			String dscCost2 = (String) incomingRequest.get("CatalogItem_dscCost2");
			args.add(dscCost2);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.dscCost2 = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_dscQty3"))
		{
			String dscQty3 = (String) incomingRequest.get("CatalogItem_dscQty3");
			args.add(dscQty3);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.dscQty3 = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_dscCost3"))
		{
			String dscCost3 = (String) incomingRequest.get("CatalogItem_dscCost3");
			args.add(dscCost3);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.dscCost3 = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_umConv"))
		{
			String umConv = (String) incomingRequest.get("CatalogItem_umConv");
			args.add(umConv);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.umConv = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_umFactor"))
		{
			String umFactor = (String) incomingRequest.get("CatalogItem_umFactor");
			args.add(umFactor);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.umFactor = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_stockEoq"))
		{
			String stockEoq = (String) incomingRequest.get("CatalogItem_stockEoq");
			args.add(stockEoq);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.stockEoq = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_itemRestricted"))
		{
			String itemRestricted = (String) incomingRequest.get("CatalogItem_itemRestricted");
			args.add(itemRestricted);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.itemRestricted = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_minReqQty"))
		{
			String minReqQty = (String) incomingRequest.get("CatalogItem_minReqQty");
			args.add(minReqQty);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.minReqQty = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_maxReqQty"))
		{
			String maxReqQty = (String) incomingRequest.get("CatalogItem_maxReqQty");
			args.add(maxReqQty);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.maxReqQty = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_receiptRequired"))
		{
			String receiptRequired = (String) incomingRequest.get("CatalogItem_receiptRequired");
			args.add(receiptRequired);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.receiptRequired = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_stdCost"))
		{
			String stdCost = (String) incomingRequest.get("CatalogItem_stdCost");
			args.add(stdCost);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.stdCost = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_description"))
		{
			String description = (String) incomingRequest.get("CatalogItem_description");
			args.add(description);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.description = ?");
		}
		if(incomingRequest.containsKey("CatalogItem_shelfLifeRqd"))
		{
			String shelfLifeRqd = (String)incomingRequest.get("CatalogItem_shelfLifeRqd");
			args.add(shelfLifeRqd);
			types.add(Hibernate.STRING);
			queryString.append(" AND catalogitem.shelfLifeRqd = ?");
		}

		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class AssetRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Asset as asset where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		if(incomingRequest.containsKey("Asset_tagNumber"))
		{			
			String tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			args.add(tagNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.id.tagNumber = ?");
		}
		if(incomingRequest.containsKey("Asset_assetClass"))
		{			
			String assetClass = (String) incomingRequest.get("Asset_assetClass");
			args.add(assetClass);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetClass = ?");
		}
		if(incomingRequest.containsKey("Asset_securityCode"))
		{			
			String securityCode = (String) incomingRequest.get("Asset_securityCode");
			args.add(securityCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.securityCode = ?");
		}
		if(incomingRequest.containsKey("Asset_commodity"))
		{			
			String commodity = (String) incomingRequest.get("Asset_commodity");
			args.add(commodity);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.commodity = ?");
		}
		if(incomingRequest.containsKey("Asset_manufacturer"))
		{			
			String manufacturer = (String) incomingRequest.get("Asset_manufacturer");
			args.add(manufacturer);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.manufacturer = ?");
		}
		if(incomingRequest.containsKey("Asset_model"))
		{			
			String model = (String) incomingRequest.get("Asset_model");
			args.add(model);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.model = ?");
		}
		if(incomingRequest.containsKey("Asset_serialNumber"))
		{			
			String serialNumber = (String) incomingRequest.get("Asset_serialNumber");
			args.add(serialNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.serialNumber = ?");
		}
		if(incomingRequest.containsKey("Asset_assetCost"))
		{			
			String assetCost = (String) incomingRequest.get("Asset_assetCost");
			args.add(assetCost);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetCost = ?");
		}
		if(incomingRequest.containsKey("Asset_dateInService"))
		{			
			String dateInService = (String) incomingRequest.get("Asset_dateInService");
			args.add(dateInService);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.dateInService = ?");
		}
		if(incomingRequest.containsKey("Asset_deprecMethod"))
		{			
			String deprecMethod = (String) incomingRequest.get("Asset_deprecMethod");
			args.add(deprecMethod);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.deprecMethod = ?");
		}
		if(incomingRequest.containsKey("Asset_warrantyStart"))
		{			
			String warrantyStart = (String) incomingRequest.get("Asset_warrantyStart");
			args.add(warrantyStart);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.warrantyStart = ?");
		}
		if(incomingRequest.containsKey("Asset_warrantyEnd"))
		{			
			String warrantyEnd = (String) incomingRequest.get("Asset_warrantyEnd");
			args.add(warrantyEnd);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.warrantyEnd = ?");
		}
		if(incomingRequest.containsKey("Asset_upgradeReqs"))
		{			
			String upgradeReqs = (String) incomingRequest.get("Asset_upgradeReqs");
			args.add(upgradeReqs);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.upgradeReqs = ?");
		}
		if(incomingRequest.containsKey("Asset_contractService"))
		{			
			String contractService = (String) incomingRequest.get("Asset_contractService");
			args.add(contractService);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.contractService = ?");
		}
		if(incomingRequest.containsKey("Asset_purchaseOrder"))
		{			
			String purchaseOrder = (String) incomingRequest.get("Asset_purchaseOrder");
			args.add(purchaseOrder);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.purchaseOrder = ?");
		}
		if(incomingRequest.containsKey("Asset_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("Asset_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.dateEntered = ?");
		}
		if(incomingRequest.containsKey("Asset_assetStatus"))
		{			
			String assetStatus = (String) incomingRequest.get("Asset_assetStatus");
			args.add(assetStatus);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetStatus = ?");
		}
		if(incomingRequest.containsKey("Asset_assetUdf1"))
		{			
			String assetUdf1 = (String) incomingRequest.get("Asset_assetUdf1");
			args.add(assetUdf1);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetUdf1 = ?");
		}
		if(incomingRequest.containsKey("Asset_assetUdf2"))
		{			
			String assetUdf2 = (String) incomingRequest.get("Asset_assetUdf2");
			args.add(assetUdf2);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetUdf2 = ?");
		}
		if(incomingRequest.containsKey("Asset_assetUdf3"))
		{			
			String assetUdf3 = (String) incomingRequest.get("Asset_assetUdf3");
			args.add(assetUdf3);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetUdf3 = ?");
		}
		if(incomingRequest.containsKey("Asset_assetUdf4"))
		{			
			String assetUdf4 = (String) incomingRequest.get("Asset_assetUdf4");
			args.add(assetUdf4);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetUdf4 = ?");
		}
		if(incomingRequest.containsKey("Asset_assetUdf5"))
		{			
			String assetUdf5 = (String) incomingRequest.get("Asset_assetUdf5");
			args.add(assetUdf5);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetUdf5 = ?");
		}
		if(incomingRequest.containsKey("Asset_totalCost"))
		{			
			String totalCost = (String) incomingRequest.get("Asset_totalCost");
			args.add(totalCost);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.totalCost = ?");
		}
		if(incomingRequest.containsKey("Asset_icText"))
		{			
			String icText = (String) incomingRequest.get("Asset_icText");
			args.add(icText);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.icText = ?");
		}
		if(incomingRequest.containsKey("Asset_imageFile"))
		{			
			String imageFile = (String) incomingRequest.get("Asset_imageFile");
			args.add(imageFile);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.imageFile = ?");
		}
		if(incomingRequest.containsKey("Asset_poVendor"))
		{			
			String poVendor = (String) incomingRequest.get("Asset_poVendor");
			args.add(poVendor);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.poVendor = ?");
		}
		if(incomingRequest.containsKey("Asset_contractorName"))
		{			
			String contractorName = (String) incomingRequest.get("Asset_contractorName");
			args.add(contractorName);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.contractorName = ?");
		}
		if(incomingRequest.containsKey("Asset_owner"))
		{			
			String owner = (String) incomingRequest.get("Asset_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.owner = ?");
		}
		if(incomingRequest.containsKey("Asset_deprecTerm"))
		{			
			String deprecTerm = (String) incomingRequest.get("Asset_deprecTerm");
			args.add(deprecTerm);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.deprecTerm = ?");
		}
		if(incomingRequest.containsKey("Asset_dateReceived"))
		{			
			String dateReceived = (String) incomingRequest.get("Asset_dateReceived");
			args.add(dateReceived);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.dateReceived = ?");
		}
		if(incomingRequest.containsKey("Asset_icReceipt"))
		{			
			String icReceipt = (String) incomingRequest.get("Asset_icReceipt");
			args.add(icReceipt);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.icReceipt = ?");
		}
		if(incomingRequest.containsKey("Asset_assetUdf6"))
		{			
			String assetUdf6 = (String) incomingRequest.get("Asset_assetUdf6");
			args.add(assetUdf6);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetUdf6 = ?");
		}
		if(incomingRequest.containsKey("Asset_assetUdf7"))
		{			
			String assetUdf7 = (String) incomingRequest.get("Asset_assetUdf7");
			args.add(assetUdf7);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetUdf7 = ?");
		}
		if(incomingRequest.containsKey("Asset_assetUdf8"))
		{			
			String assetUdf8 = (String) incomingRequest.get("Asset_assetUdf8");
			args.add(assetUdf8);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetUdf8 = ?");
		}
		if(incomingRequest.containsKey("Asset_assetUdf9"))
		{			
			String assetUdf9 = (String) incomingRequest.get("Asset_assetUdf9");
			args.add(assetUdf9);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetUdf9 = ?");
		}
		if(incomingRequest.containsKey("Asset_assetUdf10"))
		{			
			String assetUdf10 = (String) incomingRequest.get("Asset_assetUdf10");
			args.add(assetUdf10);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.assetUdf10 = ?");
		}
		if(incomingRequest.containsKey("Asset_icLineKey"))
		{			
			String icLineKey = (String) incomingRequest.get("Asset_icLineKey");
			args.add(icLineKey);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.icLineKey = ?");
		}
		if(incomingRequest.containsKey("Asset_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("Asset_itemNumber");
			args.add(itemNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.itemNumber = ?");
		}
		if(incomingRequest.containsKey("Asset_itemLocation"))
		{			
			String itemLocation = (String) incomingRequest.get("Asset_itemLocation");
			args.add(itemLocation);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.itemLocation = ?");
		}
		if(incomingRequest.containsKey("Asset_icAccount"))
		{			
			String icAccount = (String) incomingRequest.get("Asset_icAccount");
			args.add(icAccount);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.icAccount = ?");
		}
		if(incomingRequest.containsKey("Asset_icDsbHeader"))
		{			
			String icDsbHeader = (String) incomingRequest.get("Asset_icDsbHeader");
			args.add(icDsbHeader);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.icDsbHeader = ?");
		}
		if(incomingRequest.containsKey("Asset_icDsbLine"))
		{			
			String icDsbLine = (String) incomingRequest.get("Asset_icDsbLine");
			args.add(icDsbLine);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.icDsbLine = ?");
		}
		if(incomingRequest.containsKey("Asset_localCurrencyPrice"))
		{			
			String localCurrencyPrice = (String) incomingRequest.get("Asset_localCurrencyPrice");
			args.add(localCurrencyPrice);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.localCurrencyPrice = ?");
		}
		if(incomingRequest.containsKey("Asset_originalCost"))
		{			
			String originalCost = (String) incomingRequest.get("Asset_originalCost");
			args.add(originalCost);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.originalCost = ?");
		}
		if(incomingRequest.containsKey("Asset_exitValue"))
		{			
			String exitValue = (String) incomingRequest.get("Asset_exitValue");
			args.add(exitValue);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.exitValue = ?");
		}
		if(incomingRequest.containsKey("Asset_dateInactive"))
		{			
			String dateInactive = (String) incomingRequest.get("Asset_dateInactive");
			args.add(dateInactive);
			types.add(Hibernate.STRING);
			queryString.append(" AND asset.dateInactive = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()])) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
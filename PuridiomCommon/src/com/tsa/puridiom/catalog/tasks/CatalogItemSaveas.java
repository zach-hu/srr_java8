package com.tsa.puridiom.catalog.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.CatalogItemPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class CatalogItemSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain catalogItem */
			CatalogItemPK comp_id = new CatalogItemPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String userId = (String)incomingRequest.get("userId");
			String newCatalogId	= (String) incomingRequest.get("newCatalogId");
			CatalogItem	originalCatalogItem = (CatalogItem) incomingRequest.get("catalogItem");
			CatalogItem	catalogItem = new CatalogItem();

			comp_id.setCatalogId(originalCatalogItem.getComp_id().getCatalogId());
			if (!HiltonUtility.isEmpty(newCatalogId))
			{
				comp_id.setCatalogId(newCatalogId);
				catalogItem.setIcAccount(new BigDecimal(ukg.getUniqueKey().toString()));
			}
			comp_id.setItemNumber(originalCatalogItem.getComp_id().getItemNumber());
			catalogItem.setUmCode(originalCatalogItem.getUmCode());
			catalogItem.setCommodity(originalCatalogItem.getCommodity());
			catalogItem.setCost(originalCatalogItem.getCost());
			catalogItem.setIcText(originalCatalogItem.getIcText());
			catalogItem.setDateEntered(originalCatalogItem.getDateEntered());
			catalogItem.setDateExpires(originalCatalogItem.getDateExpires());
			catalogItem.setStatus(originalCatalogItem.getStatus());
			catalogItem.setOwner(userId);
			catalogItem.setSource(originalCatalogItem.getSource());
			catalogItem.setImageFile(originalCatalogItem.getImageFile());
			catalogItem.setAsset(originalCatalogItem.getAsset());
			catalogItem.setTaxable(originalCatalogItem.getTaxable());
			catalogItem.setUdf1Code(originalCatalogItem.getUdf1Code());
			catalogItem.setUdf2Code(originalCatalogItem.getUdf2Code());
			catalogItem.setUdf3Code(originalCatalogItem.getUdf3Code());
			catalogItem.setUdf4Code(originalCatalogItem.getUdf4Code());
			catalogItem.setUdf5Code(originalCatalogItem.getUdf5Code());
			catalogItem.setUdf6Code(originalCatalogItem.getUdf6Code());
			catalogItem.setUdf7Code(originalCatalogItem.getUdf7Code());
			catalogItem.setUdf8Code(originalCatalogItem.getUdf8Code());
			catalogItem.setUdf9Code(originalCatalogItem.getUdf9Code());
			catalogItem.setUdf10Code(originalCatalogItem.getUdf10Code());
			catalogItem.setType(originalCatalogItem.getType());
			catalogItem.setKit(originalCatalogItem.getKit());
			catalogItem.setMfgName(originalCatalogItem.getMfgName());
			catalogItem.setModelNumber(originalCatalogItem.getModelNumber());
			catalogItem.setLeadtime(originalCatalogItem.getLeadtime());
			catalogItem.setDscQty1(originalCatalogItem.getDscQty1());
			catalogItem.setDscCost1(originalCatalogItem.getDscCost1());
			catalogItem.setDscQty2(originalCatalogItem.getDscQty2());
			catalogItem.setDscCost2(originalCatalogItem.getDscCost2());
			catalogItem.setDscQty3(originalCatalogItem.getDscQty3());
			catalogItem.setDscCost3(originalCatalogItem.getDscCost3());
			catalogItem.setUmConv(originalCatalogItem.getUmConv());
			catalogItem.setUmFactor(originalCatalogItem.getUmFactor());
			catalogItem.setStockEoq(originalCatalogItem.getStockEoq());
			catalogItem.setItemRestricted(originalCatalogItem.getItemRestricted());
			catalogItem.setMinReqQty(originalCatalogItem.getMinReqQty());
			catalogItem.setMaxReqQty(originalCatalogItem.getMaxReqQty());
			catalogItem.setReceiptRequired(originalCatalogItem.getReceiptRequired());
			catalogItem.setStdCost(originalCatalogItem.getStdCost());
			catalogItem.setDescription(originalCatalogItem.getDescription());
			catalogItem.setShelfLifeRqd(originalCatalogItem.getShelfLifeRqd());
			catalogItem.setComp_id(comp_id);

			incomingRequest.put("catalogItem", catalogItem);

			CatalogItemAdd addTask = new CatalogItemAdd();
			catalogItem = (CatalogItem) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = catalogItem;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
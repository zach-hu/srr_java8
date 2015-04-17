/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class AssetPoLineCreateSetup extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * return an asset from poLine or ReceiptLine
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Asset asset = new Asset();
		try
		{
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			Map newIncomingRequest = new HashMap();
			String organizationId = (String) incomingRequest.get("organizationId");
			newIncomingRequest.put("userId", (String) incomingRequest.get("userId"));
			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("InvItem_itemNumber", (String) poLine.getItemNumber());
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("invitem-retrieve-by-id.xml");
			process.executeProcess(newIncomingRequest);
			InvItem invItem = (InvItem) newIncomingRequest.get("invItem");

			/*DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String queryString = "from InvItem InvItem where InvItem.itemNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {poLine.getItemNumber()} , new Type[] { Hibernate.STRING});
			InvItem invItem = (InvItem) resultList.get(0);
			/**/
			String userId = (String) incomingRequest.get("userId");
			String tagNumber = UniqueKeyGenerator.getInstance().getUniqueKey().toString();
			asset.setTagNumber(tagNumber);
			asset.setDescription(invItem.getDescription());
			asset.setLastChgBy(userId);
			asset.setDateEntered(new Date());
			asset.setDateChanged(new Date());
			asset.setManufacturer(poLine.getMfgName());
			asset.setAssetCost(poLine.getUnitPrice());
			asset.setCommodity(poLine.getCommodity().toUpperCase());
			asset.setItemNumber(poLine.getItemNumber().toUpperCase());
			asset.setModel(poLine.getModelNumber());
			asset.setPurchaseOrder(poLine.getPoNumber().toUpperCase());
			asset.setPoVendor(poHeader.getVendorId());
			if (poLine.getStatus().equalsIgnoreCase("P") || poLine.getStatus().equalsIgnoreCase("N")) {
				asset.setAssetStatus("Inactive");
			}
			else {
				asset.setAssetStatus("Pending");
			}
			asset.setAssetClass("Tangible");
			asset.setTrackingNumber("N/A");
			asset.setAssetCost(poLine.getLineTotal());
			asset.setIcLineKey(poLine.getIcLineKey());

			// if ReceiptLine also exists it adds it

			if (PropertiesManager.getInstance(organizationId).getProperty("ASSET", "ADDFROM", "P").equalsIgnoreCase("R")) {
				ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
				asset.setIcReceipt(receiptLine.getIcRecLine());
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return asset;
	}
}

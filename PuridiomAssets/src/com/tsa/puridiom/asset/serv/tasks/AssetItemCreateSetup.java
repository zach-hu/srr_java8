/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Asset;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class AssetItemCreateSetup extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * return an assets list of product tha were selected like asset
	 *
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List assetList = new ArrayList();
		try
		{
			assetList = (List) incomingRequest.get("assetList");
			String Asset_number = (String) incomingRequest.get("Asset_number");
			Asset asset = (Asset) assetList.get(0);
			for (int i=1; i<Integer.valueOf(Asset_number).intValue(); i++)
			{
				Asset assetTemp = new Asset(asset.getAssetClass(), asset.getSecurityCode(), asset.getCommodity(), asset.getManufacturer(), asset.getModel(), asset.getSerialNumber(), asset.getAssetCost(), asset.getDateInService(), asset.getDeprecMethod(), asset.getWarrantyStart(), asset.getWarrantyEnd(), asset.getUpgradeReqs(), asset.getContractService(), asset.getPurchaseOrder(), asset.getDateEntered(), asset.getAssetStatus(), asset.getAssetUdf1(), asset.getAssetUdf2(), asset.getAssetUdf3(), asset.getAssetUdf4(), asset.getAssetUdf5(), asset.getTotalCost(), asset.getIcText(), asset.getImageFile(), asset.getPoVendor(), asset.getContractorName(), asset.getOwner(), asset.getDeprecTerm(), asset.getDateReceived(), asset.getIcReceipt(), asset.getAssetUdf6(), asset.getAssetUdf7(), asset.getAssetUdf8(), asset.getAssetUdf9(), asset.getAssetUdf10(), asset.getIcLineKey(), asset.getItemNumber(), asset.getItemLocation(), asset.getIcAccount(), asset.getIcDsbHeader(), asset.getIcDsbLine(), asset.getLocalCurrencyPrice(), asset.getOriginalCost(), asset.getExitValue(), asset.getDateInactive(), asset.getLastChgBy(), asset.getDateChanged(), asset.getDescription(), asset.getLease(),  asset.getLeaseType(), asset.getLeaseTerm(), asset.getMonthlyPayment(), asset.getLeasingCompany(), asset.getRenewalDate(), asset.getFinancing(), asset.getPrinted(), asset.getTrackingNumber(), asset.getFiscalYear());
				String tagNumber = UniqueKeyGenerator.getInstance().getUniqueKey().toString();
				assetTemp.setTagNumber(tagNumber);
				assetList.add(i,assetTemp);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return assetList;
	}
}

/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.*;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class AssetCreateClon extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * returns a copy of asset with tagNumber changed,
	 * trackingNumber "N/A" and status Pending in a list
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List assetList = new ArrayList();
		try
		{
			Asset asset = (Asset) incomingRequest.get("asset");
			Asset assetClon = new Asset(asset.getAssetClass(), asset.getSecurityCode(), asset.getCommodity(), asset.getManufacturer(), asset.getModel(), asset.getSerialNumber(), asset.getAssetCost(), asset.getDateInService(), asset.getDeprecMethod(), asset.getWarrantyStart(), asset.getWarrantyEnd(), asset.getUpgradeReqs(), asset.getContractService(), asset.getPurchaseOrder(), asset.getDateEntered(), asset.getAssetStatus(), asset.getAssetUdf1(), asset.getAssetUdf2(), asset.getAssetUdf3(), asset.getAssetUdf4(), asset.getAssetUdf5(), asset.getTotalCost(), asset.getIcText(), asset.getImageFile(), asset.getPoVendor(), asset.getContractorName(), asset.getOwner(), asset.getDeprecTerm(), asset.getDateReceived(), asset.getIcReceipt(), asset.getAssetUdf6(), asset.getAssetUdf7(), asset.getAssetUdf8(), asset.getAssetUdf9(), asset.getAssetUdf10(), asset.getIcLineKey(), asset.getItemNumber(), asset.getItemLocation(), asset.getIcAccount(), asset.getIcDsbHeader(), asset.getIcDsbLine(), asset.getLocalCurrencyPrice(), asset.getOriginalCost(), asset.getExitValue(), asset.getDateInactive(), asset.getLastChgBy(), asset.getDateChanged(), asset.getDescription(), asset.getLease(),  asset.getLeaseType(), asset.getLeaseTerm(), asset.getMonthlyPayment(), asset.getLeasingCompany(), asset.getRenewalDate(), asset.getFinancing(), asset.getPrinted(), asset.getTrackingNumber(), asset.getFiscalYear());
			assetClon.setTagNumber(UniqueKeyGenerator.getInstance().getUniqueKey().toString());
			assetClon.setAssetStatus("Pending");
			assetClon.setTrackingNumber("N/A");
			assetList.add(assetClon);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return assetList;
	}
}

package com.tsa.puridiom.asset.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AssetSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain asset */
			Asset	originalAsset = (Asset) incomingRequest.get("asset");
			Asset	asset = new Asset();

			asset.setTagNumber(originalAsset.getTagNumber());
			asset.setAssetClass(originalAsset.getAssetClass());
			asset.setSecurityCode(originalAsset.getSecurityCode());
			asset.setCommodity(originalAsset.getCommodity());
			asset.setManufacturer(originalAsset.getManufacturer());
			asset.setModel(originalAsset.getModel());
			asset.setSerialNumber(originalAsset.getSerialNumber());
			asset.setAssetCost(originalAsset.getAssetCost());
			asset.setDateInService(originalAsset.getDateInService());
			asset.setDeprecMethod(originalAsset.getDeprecMethod());
			asset.setWarrantyStart(originalAsset.getWarrantyStart());
			asset.setWarrantyEnd(originalAsset.getWarrantyEnd());
			asset.setUpgradeReqs(originalAsset.getUpgradeReqs());
			asset.setContractService(originalAsset.getContractService());
			asset.setPurchaseOrder(originalAsset.getPurchaseOrder());
			asset.setDateEntered(originalAsset.getDateEntered());
			asset.setAssetStatus(originalAsset.getAssetStatus());
			asset.setAssetUdf1(originalAsset.getAssetUdf1());
			asset.setAssetUdf2(originalAsset.getAssetUdf2());
			asset.setAssetUdf3(originalAsset.getAssetUdf3());
			asset.setAssetUdf4(originalAsset.getAssetUdf4());
			asset.setAssetUdf5(originalAsset.getAssetUdf5());
			asset.setTotalCost(originalAsset.getTotalCost());
			asset.setIcText(originalAsset.getIcText());
			asset.setImageFile(originalAsset.getImageFile());
			asset.setPoVendor(originalAsset.getPoVendor());
			asset.setContractorName(originalAsset.getContractorName());
			asset.setOwner(originalAsset.getOwner());
			asset.setDeprecTerm(originalAsset.getDeprecTerm());
			asset.setDateReceived(originalAsset.getDateReceived());
			asset.setIcReceipt(originalAsset.getIcReceipt());
			asset.setAssetUdf6(originalAsset.getAssetUdf6());
			asset.setAssetUdf7(originalAsset.getAssetUdf7());
			asset.setAssetUdf8(originalAsset.getAssetUdf8());
			asset.setAssetUdf9(originalAsset.getAssetUdf9());
			asset.setAssetUdf10(originalAsset.getAssetUdf10());
			asset.setIcLineKey(originalAsset.getIcLineKey());
			asset.setItemNumber(originalAsset.getItemNumber());
			asset.setItemLocation(originalAsset.getItemLocation());
			asset.setIcAccount(originalAsset.getIcAccount());
			asset.setIcDsbHeader(originalAsset.getIcDsbHeader());
			asset.setIcDsbLine(originalAsset.getIcDsbLine());
			asset.setLocalCurrencyPrice(originalAsset.getLocalCurrencyPrice());
			asset.setOriginalCost(originalAsset.getOriginalCost());
			asset.setExitValue(originalAsset.getExitValue());
			asset.setDateInactive(originalAsset.getDateInactive());
			asset.setLease(originalAsset.getLease());
			asset.setLeaseType(originalAsset.getLeaseType());
			asset.setLeaseTerm(originalAsset.getLeaseTerm());
			asset.setMonthlyPayment(originalAsset.getMonthlyPayment());
			asset.setLeasingCompany(originalAsset.getLeasingCompany());
			asset.setRenewalDate(originalAsset.getRenewalDate());
			asset.setFinancing(originalAsset.getFinancing());
			asset.setPrinted(originalAsset.getPrinted());

			incomingRequest.put("asset", asset);

			AssetAdd addTask = new AssetAdd();
			asset = (Asset) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = asset;
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
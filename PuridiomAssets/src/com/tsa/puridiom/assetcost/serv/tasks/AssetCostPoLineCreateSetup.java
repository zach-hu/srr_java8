/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.assetcost.serv.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.poheader.tasks.PoHeaderRetrieveById;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetCostPoLineCreateSetup extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List assetCostList = new ArrayList();
		try
		{
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			List assetList = (List) incomingRequest.get("assetList");
			String userId = (String) incomingRequest.get("userId");
			BigDecimal icPoHeader = poLine.getIcPoHeader();
			String s_icPoHeader = icPoHeader.toString();
			PoHeader poHeader = new PoHeader();

			PoHeaderRetrieveById retrieveTask = new PoHeaderRetrieveById();
			incomingRequest.put("PoHeader_icPoHeader", s_icPoHeader);
			poHeader = (PoHeader) retrieveTask.executeTask(incomingRequest);
			this.setStatus(retrieveTask.getStatus()) ;

			for (int i=0; i<assetList.size(); i++) {
				AssetCost assetCost = new AssetCost();
				AssetCostPK assetCostPK = new AssetCostPK();
				Asset asset = (Asset) assetList.get(i);
				assetCostPK.setTagNumber(asset.getTagNumber());
				assetCostPK.setSequenceNo(new BigDecimal("1"));
				assetCost.setComp_id(assetCostPK);
				assetCost.setAmount(poLine.getLineTotal());
				assetCost.setDateChanged(new Date());
				assetCost.setDateEntered(new Date());
				assetCost.setLastChgBy(userId);
				assetCost.setPoNumber(poLine.getPoNumber());
				assetCost.setPoVendor(poHeader.getVendorId());
				assetCostList.add(assetCost);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return assetCostList;
	}
}

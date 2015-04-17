/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Administrator
 */
public class SetupValuesFromHeaderToBlankFieldsLines extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");
			String oid = (String)incomingRequest.get("organizationId");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
			String changeSupplierLines = propertiesManager.getProperty("REQ DEFAULTS","CHANGESUPPLIERLINES", "N");
			String updateVendorInfo = propertiesManager.getProperty("REQ OPTIONS", "DEFAULTLINEVENDFROMHEAD", "N");
			System.out.println(updateVendorInfo);
			//String headerCommodity = HiltonUtility.ckNull((String) header.getUdf13Code());
			String headerContactCode = HiltonUtility.ckNull((String) header.getVendContactCode());
			String headerVendorId = HiltonUtility.ckNull((String) header.getVendorId());

			if ((lineItems != null) && (!lineItems.isEmpty()))
			{
				for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
				{
					RequisitionLine reqLine = (RequisitionLine) iterator.next();
					String commodityCode = HiltonUtility.ckNull((String)reqLine.getCommodityCode());
					String contactCode = HiltonUtility.ckNull((String)reqLine.getVendContactCode());
					String vendorId = HiltonUtility.ckNull((String)reqLine.getVendorId());
					if (updateVendorInfo.equalsIgnoreCase("Y"))
					{
						if(changeSupplierLines.equalsIgnoreCase("Y") && header.getRequisitionType().equalsIgnoreCase("C"))
						{
							if(!HiltonUtility.isEmpty(headerContactCode))
							{
								reqLine.setVendContactCode(headerContactCode);
							}
							if(!HiltonUtility.isEmpty(headerVendorId))
							{
								String vendorName = "";
								vendorName = HiltonUtility.ckNull((String) header.getVendorName());
								reqLine.setVendorId(headerVendorId);
								reqLine.setVendorName(vendorName);
							}
						}
						else
						{
							if(HiltonUtility.isEmpty(contactCode))
							{
								if(!HiltonUtility.isEmpty(headerContactCode))
								{
									contactCode = headerContactCode;
								}
								reqLine.setVendContactCode(contactCode);
							}
							if(HiltonUtility.isEmpty(vendorId))
							{
								String vendorName = "";
								if(!HiltonUtility.isEmpty(headerVendorId))
								{
									vendorId = headerVendorId;
									vendorName = HiltonUtility.ckNull((String) header.getVendorName());
								}
								reqLine.setVendorId(vendorId);
								reqLine.setVendorName(vendorName);
							}
						}
					}

					/*if(HiltonUtility.isEmpty(commodityCode))
					{
						if(!HiltonUtility.isEmpty(headerCommodity))
						{
							commodityCode = headerCommodity;
						}
						reqLine.setCommodityCode(commodityCode);
					}*/

					dbs.update(reqLine);
				}
			}


			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "ValidAccounts error " + e.getMessage());

			throw e;
		}
		return result ;
	}

}

/**
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLink;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class PoSupplierAcknowledgmentSetup extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			ApprovalLink approvalLink = (ApprovalLink) incomingRequest.get("approvalLink");
			String[] contactData = approvalLink.getUserId().split("//");
			String vendorId = contactData[0];
			String contactCode = contactData[1];

			if (!HiltonUtility.isEmpty(vendorId))
			{
				Object vendorObject = VendorManager.getInstance().getVendor(organizationId, vendorId);

				if (vendorObject instanceof Vendor)
				{
					Vendor vendor = (Vendor) vendorObject;
					String vendorEmail = this.getVendorEmail(vendorId, contactCode, incomingRequest);
					
					incomingRequest.put("userName", vendor.getVendorName());
					incomingRequest.put("userId", vendorEmail);
				}
			}

			incomingRequest.put("vendorId", vendorId);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "PoSupplierAcknowledgmentSetup error " + e.getMessage());

			throw e;
		}

		return result;
	}

	private String getVendorEmail(String vendorId, String contactCode, Map incomingRequest)
	{
		if (HiltonUtility.isEmpty(contactCode))
		{
			contactCode = "001";
		}

		incomingRequest.put("emailTo", "N");
		incomingRequest.put("Pdf_PoHeader_vendorId", vendorId);
		incomingRequest.put("PoHeader_vendContactCode", contactCode);
		PoEmailUtility poEmailUtility = new PoEmailUtility();

		return poEmailUtility.getToEmail(incomingRequest);
	}
}
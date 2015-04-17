/**
 * 
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.ContactPK;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class RequisitionHeaderSupplierSetupFromLines extends Task
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
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
			String vendorId = "";
			boolean isVendorFromLine = true;

			if ((requisitionLineList != null) && (requisitionLineList.size() > 0))
			{
				for (int i = 0; i < requisitionLineList.size(); i++)
				{
					RequisitionLine reqLine = (RequisitionLine) requisitionLineList.get(i);

					if (i == 0)
					{
						vendorId = reqLine.getVendorId();
					}

					if (HiltonUtility.isEmpty(reqLine.getVendorId()) || (!vendorId.equals(reqLine.getVendorId())))
					{
						isVendorFromLine = false;
						break;
					}
				}
			}

			if (isVendorFromLine)
			{
				this.setVendor(vendorId, incomingRequest, requisitionHeader);
				this.setVendorContact(vendorId, incomingRequest);

			} else
			{
				this.resetVendorFields(incomingRequest, requisitionHeader);
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			Log.error(this, "Failed task: " + e.getMessage());

			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

	private void setVendor(String vendorId, Map incomingRequest, RequisitionHeader requisitionHeader) throws Exception
	{
		String queryString = "from Vendor as vendor where vendor.vendorId = ? ";
		DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		if(!(requisitionHeader.getRequisitionType().equalsIgnoreCase("M")))
		{
			incomingRequest.put("RequisitionHeader_vendorId", vendorId);
		}

		List resultList = dbs.query(queryString, new Object[] { vendorId }, new Type[] { Hibernate.STRING });

		if (resultList != null && resultList.size() > 0)
		{
			Vendor vendor = (Vendor) resultList.get(0);
			if (requisitionHeader != null && HiltonUtility.isEmpty(requisitionHeader.getShippingCode()))
				incomingRequest.put("RequisitionHeader_shippingCode", vendor.getShipVia());
			incomingRequest.put("RequisitionHeader_fobCode", vendor.getFobId());
			incomingRequest.put("RequisitionHeader_currencyCode", vendor.getCurrencyCode());
		}
	}

	private void setVendorContact(String vendorId, Map incomingRequest) throws Exception
	{
		DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String contactType = "DEFAULT";

		String queryString = "from Contact as Contact where Contact.id.contactType = ? AND Contact.id.vendorId = ? ";
		List resultList = dbs.query(queryString, new Object[] { contactType, vendorId }, new Type[] { Hibernate.STRING, Hibernate.STRING });

		if (resultList != null && resultList.size() > 0)
		{
			Contact contact = (Contact) resultList.get(0);
			ContactPK contactPK = contact.getComp_id();
			incomingRequest.put("RequisitionHeader_vendContactCode", contactPK.getContactCode());
			incomingRequest.put("RequisitionHeader_vendorAttn", contact.getDisplayName());
			incomingRequest.put("RequisitionHeader_contactPhone", contact.getPhoneNumber());
			incomingRequest.put("RequisitionHeader_contactMobilePhone", contact.getMobileNumber());
		}
	}

	private void resetVendorFields(Map incomingRequest, RequisitionHeader requisitionHeader)
	{
		if(!(requisitionHeader.getRequisitionType().equalsIgnoreCase("M")))
		{
			incomingRequest.put("RequisitionHeader_vendorId", null);
		}
		if (requisitionHeader != null && HiltonUtility.isEmpty(requisitionHeader.getShippingCode()))
			incomingRequest.put("RequisitionHeader_shippingCode", null);
		incomingRequest.put("RequisitionHeader_fobCode", null);
		incomingRequest.put("RequisitionHeader_vendContactCode", null);
		incomingRequest.put("RequisitionHeader_vendorAttn", null);
		incomingRequest.put("RequisitionHeader_contactPhone", null);
		incomingRequest.put("RequisitionHeader_contactMobilePhone", null);
	}
}

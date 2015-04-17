package com.tsa.puridiom.requisition.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Matthew
 */
public class RequisitionValidRequestCat extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			List<RequisitionLine> lineItems = (List<RequisitionLine>) incomingRequest.get("lineitems");
			List<DocAttachment> attachmentList = (List<DocAttachment>) incomingRequest.get("attachments");

			boolean validRequestCatAttachment = true;
			boolean validAffiliateYes = true;
			boolean validAffiliateNo = true;
			boolean validVendorCheckReq = true;

			if (header.getRequisitionType().equalsIgnoreCase("M") && lineItems != null)
			{
				if ("SA".equalsIgnoreCase(header.getRequestCat()))
				{
					String attachmentTitle = "";
					if (attachmentList.isEmpty())
					{
						validRequestCatAttachment = false;
					}
					else
					{
						boolean attachmentThere = false;
						for (int j = 0; j < attachmentList.size(); j++)
						{
							attachmentTitle = attachmentList.get(j).getDocTitle();
							if (attachmentTitle.indexOf("OSR_") == 0)
							{
								attachmentThere = true;
							}
						}
						if (!attachmentThere)
							validRequestCatAttachment = false;
					}
				}

				if ("SPA".equalsIgnoreCase(header.getRequestCat()))
				{
					for (int i = 0; i < lineItems.size(); i++)
					{
						DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
						RequisitionLine requisitionLine = (RequisitionLine) lineItems.get(i);
						String vendorId = requisitionLine.getVendorId();

						if (HiltonUtility.isEmpty(vendorId)) {
							validAffiliateYes = false;
						}
						else
						{
							String queryString = "from Vendor as Vendor where Vendor.vendorId = ? ";
							List resultList = dbs.query(queryString, new Object[] {vendorId, } , new Type[] { Hibernate.STRING}) ;

							if (resultList != null && resultList.size() > 0)
							{
								result = resultList.get(0);
							}
							Vendor vendor = (Vendor) result;

							if (vendor != null)
							{
								if (!Utility.isEmpty(vendor.getVendUdf8()))
								{
									if (!"Y".equalsIgnoreCase(vendor.getVendUdf8()))
									{
										validAffiliateYes = false;
									}
								}
								else
								{
									validAffiliateYes = false;
								}

								if (!Utility.isEmpty(vendor.getVendUdf7()))
								{
									if ("Y".equalsIgnoreCase(vendor.getVendUdf7()) || vendorId.indexOf("E") == 0)
									{
										validVendorCheckReq = false;
									}
								}
							}
						}
					}
				}
				else
				{
					for (int i = 0; i < lineItems.size(); i++)
					{
						DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
						RequisitionLine requisitionLine = (RequisitionLine) lineItems.get(i);
						String vendorId = requisitionLine.getVendorId();

						if (!HiltonUtility.isEmpty(vendorId))
						{
							String queryString = "from Vendor as Vendor where Vendor.vendorId = ? ";
							List resultList = dbs.query(queryString, new Object[] {vendorId, } , new Type[] { Hibernate.STRING}) ;

							if (resultList != null && resultList.size() > 0)
							{
								result = resultList.get(0);
							}
							Vendor vendor = (Vendor) result;

							if (vendor != null)
							{
								if (!Utility.isEmpty(vendor.getVendUdf8()))
								{
									if (!"N".equalsIgnoreCase(vendor.getVendUdf8()))
									{
										validAffiliateNo = false;
									}
								}

								if (!Utility.isEmpty(vendor.getVendUdf7()))
								{
									if ("Y".equalsIgnoreCase(vendor.getVendUdf7()) || vendorId.indexOf("E") == 0)
									{
										validVendorCheckReq = false;
									}
								}
							}
						}
					}
				}
			}

			incomingRequest.put("validRequestCatAttachment", String.valueOf(validRequestCatAttachment));
			incomingRequest.put("validAffliateYes", String.valueOf(validAffiliateYes));
			incomingRequest.put("validAffliateNo", String.valueOf(validAffiliateNo));
			incomingRequest.put("validVendorCheckReq", String.valueOf(validVendorCheckReq));
		}
		catch(Exception e)
		{
			Log.error(this, "RequisitionValidRequestCat error " + e.getMessage());

			throw e;
		}
		return result;
	}
}
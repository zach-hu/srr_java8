package com.tsa.puridiom.requisition.rules;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class RequisitionValidateAttachmentTitle extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			List<RequisitionLine> lineItems = (List<RequisitionLine>) incomingRequest.get("lineitems");
			List<DocAttachment> attachmentList = (List<DocAttachment>) incomingRequest.get("attachments");

			this.ValidateAttachmentTitle(header, lineItems, attachmentList, incomingRequest);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "RequisitionValidAttachmentTitle error " + e.getMessage());

			throw e;
		}
		return result;
	}

	public void ValidateAttachmentTitle(RequisitionHeader header, List<RequisitionLine> lineItems, List<DocAttachment> attachmentList, Map incomingRequest)
	{
		boolean validAttachmentTitle = true;
		boolean validAttachmentForUdf4 = true;
		boolean validSRID = true;
		boolean validSafety = true;
		String attachmentTitle = "";

		for (int i = 0; i < lineItems.size(); i++)
		{
			//Validate attachment attached to line item if item has chemical item number
			if (!Utility.isEmpty(lineItems.get(i).getChemicalItemNumber()) && ("M").equalsIgnoreCase(lineItems.get(i).getReqType()))
			{
				validAttachmentTitle = false;
				if (!attachmentList.isEmpty())
				{
					for (int y = 0; y < attachmentList.size(); y++)
					{
						if (attachmentList.get(y).getComp_id().getIcLine().equals(lineItems.get(i).getIcReqLine()))
						{
							if (attachmentList.get(y).getDocTitle().indexOf("OSR_1_180") == 0)
							{
								validAttachmentTitle = true;
								break;
							}
						}
					}
				}
				else
				{
					validAttachmentTitle = false;
					break;
				}
			}

		}

		if (header.getUdf4Code().equals("OSM") && ("M").equalsIgnoreCase(header.getRequisitionType()))
		{
			validAttachmentForUdf4 = false;
			validSRID = false;
			validSafety = false;
			if (attachmentList.isEmpty())
			{
				validAttachmentForUdf4 = false;
			}
			else
			{
				for (int i = 0; i < attachmentList.size(); i++)
				{
					attachmentTitle = attachmentList.get(i).getDocTitle().toUpperCase();
					if (attachmentList.get(i).getComp_id().getIcHeader().compareTo(header.getIcReqHeader()) == 0
							&& attachmentList.get(i).getComp_id().getIcLine().compareTo(new BigDecimal(0)) == 0)
					{
						if (attachmentTitle.indexOf("SRID_") == 0)
						{
							validSRID = true;
						}

						if (attachmentTitle.indexOf("SAFETY_") == 0)
						{
							validSafety = true;
						}
					}
				}
				if (validSRID && validSafety)
				{
					validAttachmentForUdf4 = true;
				}
			}
		}

		incomingRequest.put("validAttachmentTitle", String.valueOf(validAttachmentTitle));
		incomingRequest.put("validAttachmentForUdf4", String.valueOf(validAttachmentForUdf4));
	}
}

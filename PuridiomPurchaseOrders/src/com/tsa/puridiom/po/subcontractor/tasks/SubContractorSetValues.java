package com.tsa.puridiom.po.subcontractor.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.SubContractor;
import com.tsa.puridiom.entity.SubContractorPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class SubContractorSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			SubContractorPK comp_id = new SubContractorPK();
			SubContractor subContractor = (SubContractor) incomingRequest.get("subContractor");
			if (subContractor == null)
			{
				subContractor = new SubContractor();
			}

			if (incomingRequest.containsKey("SubContractor_poNumber"))
			{
				String poNumber = (String) incomingRequest.get("SubContractor_poNumber");
				comp_id.setPoNumber(poNumber);
			}
			if (incomingRequest.containsKey("SubContractor_releaseNumber"))
			{
				String releaseNumberString = (String) incomingRequest.get("SubContractor_releaseNumber");
				if (Utility.isEmpty(releaseNumberString))
				{
					releaseNumberString = "0";
				}
				BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );
				comp_id.setReleaseNumber(releaseNumber);
			}
			if (incomingRequest.containsKey("SubContractor_subName"))
			{
				String subName = (String) incomingRequest.get("SubContractor_subName");
				comp_id.setSubName(subName);
			}
			if (incomingRequest.containsKey("SubContractor_contractno"))
			{
				String contractno = (String) incomingRequest.get("SubContractor_contractno");
				subContractor.setContractno(contractno);
			}
			if (incomingRequest.containsKey("SubContractor_addressLine1"))
			{
				String addressLine1 = (String) incomingRequest.get("SubContractor_addressLine1");
				subContractor.setAddressLine1(addressLine1);
			}
			if (incomingRequest.containsKey("SubContractor_addressLine2"))
			{
				String addressLine2 = (String) incomingRequest.get("SubContractor_addressLine2");
				subContractor.setAddressLine2(addressLine2);
			}
			if (incomingRequest.containsKey("SubContractor_addressLine3"))
			{
				String addressLine3 = (String) incomingRequest.get("SubContractor_addressLine3");
				subContractor.setAddressLine3(addressLine3);
			}
			if (incomingRequest.containsKey("SubContractor_addressLine4"))
			{
				String addressLine4 = (String) incomingRequest.get("SubContractor_addressLine4");
				subContractor.setAddressLine4(addressLine4);
			}
			if (incomingRequest.containsKey("SubContractor_city"))
			{
				String city = (String) incomingRequest.get("SubContractor_city");
				subContractor.setCity(city);
			}
			if (incomingRequest.containsKey("SubContractor_state"))
			{
				String state = (String) incomingRequest.get("SubContractor_state");
				subContractor.setState(state);
			}
			if (incomingRequest.containsKey("SubContractor_postalCode"))
			{
				String postalCode = (String) incomingRequest.get("SubContractor_postalCode");
				subContractor.setPostalCode(postalCode);
			}
			if (incomingRequest.containsKey("SubContractor_country"))
			{
				String country = (String) incomingRequest.get("SubContractor_country");
				subContractor.setCountry(country);
			}
			if (incomingRequest.containsKey("SubContractor_contactName"))
			{
				String contactName = (String) incomingRequest.get("SubContractor_contactName");
				subContractor.setContactName(contactName);
			}
			if (incomingRequest.containsKey("SubContractor_contactEmail"))
			{
				String contactEmail = (String) incomingRequest.get("SubContractor_contactEmail");
				subContractor.setContactEmail(contactEmail);
			}
			if (incomingRequest.containsKey("SubContractor_contactPhone"))
			{
				String contactPhone = (String) incomingRequest.get("SubContractor_contactPhone");
				subContractor.setContactPhone(contactPhone);
			}
			if (incomingRequest.containsKey("SubContractor_contactFax"))
			{
				String contactFax = (String) incomingRequest.get("SubContractor_contactFax");
				subContractor.setContactFax(contactFax);
			}
			if (incomingRequest.containsKey("SubContractor_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("SubContractor_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				subContractor.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("SubContractor_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("SubContractor_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				subContractor.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("SubContractor_status"))
			{
				String status = (String) incomingRequest.get("SubContractor_status");
				subContractor.setStatus(status);
			}
			if (incomingRequest.containsKey("SubContractor_continfo"))
			{
				String continfo = (String) incomingRequest.get("SubContractor_continfo");
				subContractor.setContinfo(continfo);
			}
			if (incomingRequest.containsKey("SubContractor_owner"))
			{
				String owner = (String) incomingRequest.get("SubContractor_owner");
				subContractor.setOwner(owner);
			}
			if (incomingRequest.containsKey("SubContractor_affiliateId"))
			{
				String affiliateId = (String) incomingRequest.get("SubContractor_affiliateId");
				subContractor.setAffiliateId(affiliateId);
			}
			subContractor.setComp_id(comp_id);

			result = subContractor;
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
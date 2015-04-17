package com.tsa.puridiom.organizationpackage.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class OrganizationPackageSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			OrganizationPackage organizationPackage = (OrganizationPackage) incomingRequest.get("organizationPackage");
			if (organizationPackage == null)
			{
				organizationPackage = new OrganizationPackage();
			}

			if (incomingRequest.containsKey("OrganizationPackage_icOrgPackage"))
			{
				String icOrgPackageString = (String) incomingRequest.get("OrganizationPackage_icOrgPackage");
				if (Utility.isEmpty(icOrgPackageString))
				{
					icOrgPackageString = "0";
				}
				BigDecimal icOrgPackage = new BigDecimal ( icOrgPackageString );
				organizationPackage.setIcOrgPackage(icOrgPackage);
			}
			if (incomingRequest.containsKey("OrganizationPackage_organizationId"))
			{
				String organizationId = (String) incomingRequest.get("OrganizationPackage_organizationId");
				organizationPackage.setOrganizationId(organizationId);
			}
			if (incomingRequest.containsKey("OrganizationPackage_icPackage"))
			{
				String icPackageString = (String) incomingRequest.get("OrganizationPackage_icPackage");
				if (Utility.isEmpty(icPackageString))
				{
					icPackageString = "0";
				}
				BigDecimal icPackage = new BigDecimal ( icPackageString );
				organizationPackage.setIcPackage(icPackage);
			}
			if (incomingRequest.containsKey("OrganizationPackage_packageType"))
			{
				String packageType = (String) incomingRequest.get("OrganizationPackage_packageType");
				organizationPackage.setPackageType(packageType);
			}
			if (incomingRequest.containsKey("OrganizationPackage_unitPrice"))
			{
				String unitPriceString = (String) incomingRequest.get("OrganizationPackage_unitPrice");
				if (Utility.isEmpty(unitPriceString))
				{
					unitPriceString = "0";
				}
				BigDecimal unitPrice = new BigDecimal ( unitPriceString );
				organizationPackage.setUnitPrice(unitPrice);
			}
			if (incomingRequest.containsKey("OrganizationPackage_quantity"))
			{
				String quantityString = (String) incomingRequest.get("OrganizationPackage_quantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal ( quantityString );
				organizationPackage.setQuantity(quantity);
			}
			if (incomingRequest.containsKey("OrganizationPackage_total"))
			{
				String totalString = (String) incomingRequest.get("OrganizationPackage_total");
				if (Utility.isEmpty(totalString))
				{
					totalString = "0";
				}
				BigDecimal total = new BigDecimal ( totalString );
				organizationPackage.setTotal(total);
			}
			if (incomingRequest.containsKey("OrganizationPackage_buyerCount"))
			{
				String buyerCountString = (String) incomingRequest.get("OrganizationPackage_buyerCount");
				if (Utility.isEmpty(buyerCountString))
				{
					buyerCountString = "0";
				}
				BigDecimal buyerCount = new BigDecimal ( buyerCountString );
				organizationPackage.setBuyerCount(buyerCount);
			}
			if (incomingRequest.containsKey("OrganizationPackage_requisitionerCount"))
			{
				String requisitionerCountString = (String) incomingRequest.get("OrganizationPackage_requisitionerCount");
				if (Utility.isEmpty(requisitionerCountString))
				{
					requisitionerCountString = "0";
				}
				BigDecimal requisitionerCount = new BigDecimal ( requisitionerCountString );
				organizationPackage.setRequisitionerCount(requisitionerCount);
			}
			if (incomingRequest.containsKey("OrganizationPackage_purchasedBy"))
			{
				String purchasedBy = (String) incomingRequest.get("OrganizationPackage_purchasedBy");
				organizationPackage.setPurchasedBy(purchasedBy);
			}
			if (incomingRequest.containsKey("OrganizationPackage_datePurchased"))
			{
				String datePurchasedString = (String) incomingRequest.get("OrganizationPackage_datePurchased");
				Date datePurchased = Dates.getDate(datePurchasedString);
				organizationPackage.setDatePurchased(datePurchased);
			}
			if (incomingRequest.containsKey("OrganizationPackage_datePaid"))
			{
				String datePaidString = (String) incomingRequest.get("OrganizationPackage_datePaid");
				Date datePaid = Dates.getDate(datePaidString);
				organizationPackage.setDatePaid(datePaid);
			}
			if (incomingRequest.containsKey("OrganizationPackage_transactionId"))
			{
				String transactionId = (String) incomingRequest.get("OrganizationPackage_transactionId");
				organizationPackage.setTransactionId(transactionId);
			}
			if (incomingRequest.containsKey("OrganizationPackage_status"))
			{
				String status = (String) incomingRequest.get("OrganizationPackage_status");
				organizationPackage.setStatus(status);
			}
			if (incomingRequest.containsKey("OrganizationPackage_dateActive"))
			{
				String dateActiveString = (String) incomingRequest.get("OrganizationPackage_dateActive");
				Date dateActive = Dates.getDate(dateActiveString);
				organizationPackage.setDateActive(dateActive);
			}
			if (incomingRequest.containsKey("OrganizationPackage_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("OrganizationPackage_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				organizationPackage.setDateExpires(dateExpires);
			}

			result = organizationPackage;
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
package com.tsa.puridiom.poline.tasks;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class PoLineSetupForLookup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			//String	userId = (String) incomingRequest.get("userId") ;
			//String today = Dates.today("") ;

            String  organizationId = (String) incomingRequest.get("organizationId") ;
            String  userDateFormat = (String) incomingRequest.get("userDateFormat") ;
            String  userTimeZone = (String) incomingRequest.get("userTimeZone") ;
            
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("PoLine_icPoLine",	ukg.getUniqueKey().toString());
			incomingRequest.put("PoLine_icLineKey", incomingRequest.get("PoLine_icPoLine"));
			incomingRequest.put("PoLine_icRelKey", incomingRequest.get("PoLine_icPoLine"));

			incomingRequest.put("PoLine_icLineHistory",	ukg.getUniqueKey().toString());

			if (!incomingRequest.containsKey("PoLine_itemNumber"))	{
				incomingRequest.put("PoLine_itemNumber", "");
			}
			if (!incomingRequest.containsKey("PoLine_itemSource"))	{
				incomingRequest.put("PoLine_itemSource", "");
			}
			if (!incomingRequest.containsKey("PoLine_umCode"))	{
				incomingRequest.put("PoLine_umCode", "EA");
			}
			if (!incomingRequest.containsKey("PoLine_umConv"))	{
				incomingRequest.put("PoLine_umConv", "");
			}
			if (!incomingRequest.containsKey("PoLine_umFactor"))	{
				incomingRequest.put("PoLine_umFactor", "1");
			}
			if (!incomingRequest.containsKey("PoLine_quantity"))	{
				incomingRequest.put("PoLine_quantity", "1");
			}
			if (!incomingRequest.containsKey("PoLine_unitPrice"))	{
				incomingRequest.put("PoLine_unitPrice", "0");
			}
			if (!incomingRequest.containsKey("PoLine_lineTotal"))	{
						incomingRequest.put("PoLine_lineTotal", "0");
			}
			if (!incomingRequest.containsKey("PoLine_commodity"))	{
				incomingRequest.put("PoLine_commodity", "");
			}
			if (!incomingRequest.containsKey("PoLine_taxable"))	{
				incomingRequest.put("PoLine_taxable", "Y");
			}
			if (!incomingRequest.containsKey("PoLine_asset"))	{
				incomingRequest.put("PoLine_asset", "N");
			}
			if (!incomingRequest.containsKey("PoLine_discountSource"))	{
				incomingRequest.put("PoLine_discountSource", "");
			}
			if (!incomingRequest.containsKey("PoLine_discountPercent"))	{
				incomingRequest.put("PoLine_discountPercent", "0");
			}
			if (!incomingRequest.containsKey("PoLine_discountAmount"))	{
				incomingRequest.put("PoLine_discountAmount", "0");
			}
			if (!incomingRequest.containsKey("PoLine_shippingCharges"))	{
				incomingRequest.put("PoLine_shippingCharges", "0");
			}
			if (!incomingRequest.containsKey("PoLine_shippingTaxable"))	{
				incomingRequest.put("PoLine_shippingTaxable", "N");
			}
			if (!incomingRequest.containsKey("PoLine_otherCharges"))	{
				incomingRequest.put("PoLine_otherCharges", "0");
			}
			if (!incomingRequest.containsKey("PoLine_otherDescription"))	{
				incomingRequest.put("PoLine_otherDescription", "");
			}
			if (!incomingRequest.containsKey("PoLine_otherTaxable"))	{
				incomingRequest.put("PoLine_otherTaxable", "N");
			}
			if (!incomingRequest.containsKey("PoLine_icPoLine"))	{
				incomingRequest.put("PoLine_icPoLine", "0");
			}
			if (!incomingRequest.containsKey("PoLine_splits"))	{
				incomingRequest.put("PoLine_splits", "N");
			}
			if (!incomingRequest.containsKey("PoLine_status"))	{
				incomingRequest.put("PoLine_status", "1000");
			}
			if (!incomingRequest.containsKey("PoLine_commentFlag"))	{
				incomingRequest.put("PoLine_commentFlag", "N");
			}
			if (!incomingRequest.containsKey("PoLine_taxPercent"))	{
				incomingRequest.put("PoLine_taxPercent", "0");
			}
			if (!incomingRequest.containsKey("PoLine_taxAmount"))	{
				incomingRequest.put("PoLine_taxAmount", "0");
			}
			if (!incomingRequest.containsKey("PoLine_shippingTax"))	{
				incomingRequest.put("PoLine_shippingTax", "0");
			}
			if (!incomingRequest.containsKey("PoLine_otherTax"))	{
				incomingRequest.put("PoLine_otherTax", "0");
			}
			if (!incomingRequest.containsKey("PoLine_allocated"))	{
				incomingRequest.put("PoLine_allocated", "0");
			}
			if (!incomingRequest.containsKey("PoLine_mfgName"))	{
				incomingRequest.put("PoLine_mfgName", "");
			}
			if (!incomingRequest.containsKey("PoLine_modelNumber"))	{
				incomingRequest.put("PoLine_modelNumber", "");
			}
			if (!incomingRequest.containsKey("PoLine_udf1Code"))	{
				incomingRequest.put("PoLine_udf1Code", "");
			}
			if (!incomingRequest.containsKey("PoLine_udf2Code"))	{
				incomingRequest.put("PoLine_udf2Code", "");
			}
			if (!incomingRequest.containsKey("PoLine_udf3Code"))	{
				incomingRequest.put("PoLine_udf3Code", "");
			}
			if (!incomingRequest.containsKey("PoLine_udf4Code"))	{
				incomingRequest.put("PoLine_udf4Code", "");
			}
			if (!incomingRequest.containsKey("PoLine_udf5Code"))	{
				incomingRequest.put("PoLine_udf5Code", "");
			}
			if (!incomingRequest.containsKey("PoLine_udf6Code"))	{
				incomingRequest.put("PoLine_udf6Code", "");
			}
			if (!incomingRequest.containsKey("PoLine_udf7Code"))	{
				incomingRequest.put("PoLine_udf7Code", "");
			}
			if (!incomingRequest.containsKey("PoLine_udf8Code"))	{
				incomingRequest.put("PoLine_udf8Code", "");
			}
			if (!incomingRequest.containsKey("PoLine_udf9Code"))	{
				incomingRequest.put("PoLine_udf9Code", "");
			}
			if (!incomingRequest.containsKey("PoLine_udf10Code"))	{
				incomingRequest.put("PoLine_udf10Code", "");
			}
			if (!incomingRequest.containsKey("PoLine_memoLine"))	{
				incomingRequest.put("PoLine_memoLine", "");
			}


			if (!incomingRequest.containsKey("PoLine_receiptRequired"))	{
				incomingRequest.put("PoLine_receiptRequired", "R");
			}
			if (!incomingRequest.containsKey("PoLine_roFlag"))	{
				incomingRequest.put("PoLine_roFlag", "N");
			}
			if (!incomingRequest.containsKey("PoLine_routing"))	{
				incomingRequest.put("PoLine_routing", "");
			}
			if (!incomingRequest.containsKey("PoLine_description"))	{
				incomingRequest.put("PoLine_description", "");
			}
			if (!incomingRequest.containsKey("PoLine_dateEntered"))	{
	            incomingRequest.put("PoLine_dateEntered", Dates.today("", userTimeZone));
			}
			if (!incomingRequest.containsKey("PoLine_requisitionNumber"))	{
	            incomingRequest.put("PoLine_requisitionNumber", "");
			}
			if (!incomingRequest.containsKey("PoLine_departmentCode"))	{
	            incomingRequest.put("PoLine_departmentCode", "");
			}

			PoHeader  poHeader = (PoHeader) incomingRequest.get("poHeader") ;

			if (poHeader != null)
			{
				// Defaults from header
				incomingRequest.put("PoLine_icPoHeader", poHeader.getIcPoHeader().toString());
				incomingRequest.put("PoLine_poNumber", poHeader.getPoNumber());
				if (!incomingRequest.containsKey("PoLine_itemLocation"))
				{
					incomingRequest.put("PoLine_itemLocation", poHeader.getItemLocation());
				}
				incomingRequest.put("PoLine_reqType",poHeader.getPoType()) ;
				incomingRequest.put("PoLine_routing",poHeader.getRouting()) ;
				if (! (poHeader.getRequiredDate() == null)) {
					incomingRequest.put("PoLine_requiredDate", HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), organizationId, userDateFormat));
				}
				incomingRequest.put("PoLine_vendorId", poHeader.getVendorId());
				incomingRequest.put("PoLine_vendContactCode", poHeader.getVendContactCode());
			}
			else
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("PoHeader entity was not found!");
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}

		return null ;
	}
}
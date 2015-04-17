package com.tsa.puridiom.catalogpricebrk.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class CatalogPriceBrkSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CatalogPriceBrkPK comp_id = new CatalogPriceBrkPK();
			CatalogPriceBrk catalogPriceBrk = (CatalogPriceBrk) incomingRequest.get("catalogPriceBrk");
			if (catalogPriceBrk == null)
			{
				catalogPriceBrk = new CatalogPriceBrk();
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_catalogId"))
			{
				String catalogId = (String ) incomingRequest.get("CatalogPriceBrk_catalogId");
				comp_id.setCatalogId(catalogId);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("CatalogPriceBrk_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_sequence"))
			{
				String sequenceString = (String) incomingRequest.get("CatalogPriceBrk_sequence");
				BigDecimal sequence = new BigDecimal ( sequenceString );
				comp_id.setSequence(sequence);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_unitPrice"))
			{
				String unitPriceString = (String) incomingRequest.get("CatalogPriceBrk_unitPrice");
				BigDecimal unitPrice = new BigDecimal ( unitPriceString );
				catalogPriceBrk.setUnitPrice(unitPrice);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_status"))
			{
				String status = (String ) incomingRequest.get("CatalogPriceBrk_status");
				catalogPriceBrk.setStatus(status);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_breakType"))
			{
				String breakType = (String ) incomingRequest.get("CatalogPriceBrk_breakType");
				catalogPriceBrk.setBreakType(breakType);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_breakFrom"))
			{
				String breakFrom = (String ) incomingRequest.get("CatalogPriceBrk_breakFrom");
				catalogPriceBrk.setBreakFrom(breakFrom);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_breakTo"))
			{
				String breakTo = (String ) incomingRequest.get("CatalogPriceBrk_breakTo");
				catalogPriceBrk.setBreakTo(breakTo);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_breakNote"))
			{
				String breakNote = (String ) incomingRequest.get("CatalogPriceBrk_breakNote");
				catalogPriceBrk.setBreakNote(breakNote);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_qtyFrom"))
			{
				String qtyFromString = (String) incomingRequest.get("CatalogPriceBrk_qtyFrom");
				BigDecimal qtyFrom = new BigDecimal ( qtyFromString );
				catalogPriceBrk.setQtyFrom(qtyFrom);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_qtyTo"))
			{
				String qtyToString = (String) incomingRequest.get("CatalogPriceBrk_qtyTo");
				BigDecimal qtyTo = new BigDecimal ( qtyToString );
				catalogPriceBrk.setQtyTo(qtyTo);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_dateFrom"))
			{
				String dateFromString = (String) incomingRequest.get("CatalogPriceBrk_dateFrom");

				Date dateFrom = Dates.getDate(dateFromString);
				catalogPriceBrk.setDateFrom(dateFrom);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_dateTo"))
			{
				String dateToString = (String) incomingRequest.get("CatalogPriceBrk_dateTo");
				Date dateTo = Dates.getDate(dateToString);
				catalogPriceBrk.setDateTo(dateTo);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_umConv"))
			{
				String umConv = (String ) incomingRequest.get("CatalogPriceBrk_umConv");
				catalogPriceBrk.setUmConv(umConv);
			}
			if (incomingRequest.containsKey("CatalogPriceBrk_umFactor"))
			{
				String umFactorString = (String) incomingRequest.get("CatalogPriceBrk_umFactor");
				BigDecimal umFactor = new BigDecimal ( umFactorString );
				catalogPriceBrk.setUmFactor(umFactor);
			}

			catalogPriceBrk.setComp_id(comp_id);

			result = catalogPriceBrk;
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
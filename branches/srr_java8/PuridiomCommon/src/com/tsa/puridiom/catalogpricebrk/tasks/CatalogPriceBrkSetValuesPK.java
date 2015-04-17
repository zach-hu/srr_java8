package com.tsa.puridiom.catalogpricebrk.tasks;
import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.CatalogPriceBrk;
import com.tsa.puridiom.entity.CatalogPriceBrkPK;

public class CatalogPriceBrkSetValuesPK
{
	public void setValues(Map incomingRequest, CatalogPriceBrk catalogpricebrk )
	{
		String catalogId = (String ) incomingRequest.get("CatalogPriceBrk_catalogId");
		String itemNumber = (String ) incomingRequest.get("CatalogPriceBrk_itemNumber");
		String sequenceString = (String) incomingRequest.get("CatalogPriceBrk_sequence");
		BigDecimal sequence = new BigDecimal ( sequenceString );
		CatalogPriceBrkPK comp_id = new CatalogPriceBrkPK();
		comp_id.setCatalogId(catalogId);
		comp_id.setItemNumber(itemNumber);
		comp_id.setSequence(sequence);
		catalogpricebrk.setComp_id(comp_id);
	}
}
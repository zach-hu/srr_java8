/*
 * Created on Oct 10, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.CatalogComponent;
import com.tsa.puridiom.entity.CatalogComponentPK;

/**
 * @author renzo 
 */
public class CatalogComponentValues
{
	public static CatalogComponent setValues(Object object, CatalogComponent catalogComponent)
	{
		Map incomingRequest = (Map) object;
		String itemNumber = (String)incomingRequest.get("itemNumber");
		String catalogId = (String)incomingRequest.get("catalogId") ;
		BigDecimal sequence = (BigDecimal)incomingRequest.get("sequence");
		String optionId = (String) incomingRequest.get("optionId") ;
		String legendCode = (String) incomingRequest.get("legendCode") ;
		String actionCode = (String) incomingRequest.get("actionCode");
		BigDecimal optionPrice = (BigDecimal) incomingRequest.get("optionPrice");
		String optionDescript = (String) incomingRequest.get("optionDescript");
		
		CatalogComponentPK catalogComponentPK = new CatalogComponentPK();
		catalogComponentPK.setCatalogId(catalogId);
		catalogComponentPK.setItemNumber(itemNumber);
		catalogComponentPK.setSequence(sequence);
		
		catalogComponent.setComp_id(catalogComponentPK);
		catalogComponent.setOptionId(optionId);
		catalogComponent.setLegendCode(legendCode);
		catalogComponent.setActionCode(actionCode);
		catalogComponent.setOptionPrice(optionPrice);
		catalogComponent.setOptionDecript(optionDescript) ;
		
		return catalogComponent;
	}
}

/*
 * Created on Oct 10, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.CatalogPriceBrk;
import com.tsa.puridiom.entity.CatalogPriceBrkPK;

/**
 * @author renzo 
 */
public class CatalogPriceBrkValues
{
	public static CatalogPriceBrk setValues(Object object, CatalogPriceBrk catalogPrcBrk)
	{
		Map incomingRequest = (Map) object;
		String itemNumber = (String)incomingRequest.get("itemNumber");
		String catalogId = (String)incomingRequest.get("catalogId") ;
		BigDecimal sequence = (BigDecimal)incomingRequest.get("sequence");
		BigDecimal unitPrice = (BigDecimal) incomingRequest.get("unitPrice") ;
		String status = (String) incomingRequest.get("status") ;
		String breakType = (String) incomingRequest.get("breakType") ;
		String breakFrom = (String) incomingRequest.get("breakFrom");
		String breakTo = (String) incomingRequest.get("breakTo");
		String breakNote = (String) incomingRequest.get("breakNote");
		BigDecimal qtyFrom = (BigDecimal) incomingRequest.get("qtyFrom");
		BigDecimal qtyTo = (BigDecimal) incomingRequest.get("qtyTo");
		Date dateFrom = (Date) incomingRequest.get("dateFrom");
		Date dateTo = (Date) incomingRequest.get("dateTo");
		String umConv = (String) incomingRequest.get("umConv");
		BigDecimal umFactor = (BigDecimal) incomingRequest.get("umFactor");
		

		CatalogPriceBrkPK catalogPriceBrkPK = new CatalogPriceBrkPK();
		catalogPriceBrkPK.setCatalogId(catalogId);
		catalogPriceBrkPK.setItemNumber(itemNumber);
		catalogPriceBrkPK.setSequence(sequence);
		
		catalogPrcBrk.setComp_id(catalogPriceBrkPK);
		catalogPrcBrk.setUnitPrice(unitPrice);
		catalogPrcBrk.setStatus(status);
		catalogPrcBrk.setBreakType(breakType);
		catalogPrcBrk.setBreakFrom(breakFrom);
		catalogPrcBrk.setBreakTo(breakTo) ;
		catalogPrcBrk.setBreakNote(breakNote) ;
		catalogPrcBrk.setQtyFrom(qtyFrom) ;
		catalogPrcBrk.setQtyTo(qtyTo) ;
		catalogPrcBrk.setDateFrom(dateFrom) ;
		catalogPrcBrk.setDateTo(dateTo) ;
		catalogPrcBrk.setUmConv(umConv) ;
		catalogPrcBrk.setUmFactor(umFactor) ;
	
		return catalogPrcBrk;
	}
}

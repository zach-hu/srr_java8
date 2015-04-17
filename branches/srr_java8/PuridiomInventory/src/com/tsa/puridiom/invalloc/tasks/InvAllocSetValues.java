package com.tsa.puridiom.invalloc.tasks;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.Dates;

public class InvAllocSetValues
{
	public void setValues(Map incomingRequest, InvAlloc invalloc )
	{
		InvAllocPK comp_id = new InvAllocPK();
		if(incomingRequest.containsKey("InvAlloc.referenceType"))
		{
			String referenceType = (String ) incomingRequest.get("InvAlloc.referenceType");
			comp_id.setReferenceType(referenceType);
		}
		if(incomingRequest.containsKey("InvAlloc.itemNumber"))
		{
			String itemNumber = (String ) incomingRequest.get("InvAlloc.itemNumber");
			invalloc.setItemNumber(itemNumber);
		}
		if(incomingRequest.containsKey("InvAlloc.icLoc"))
		{
			String icLocString = (String) incomingRequest.get("InvAlloc.icLoc");
			BigDecimal icLoc = new BigDecimal ( icLocString );
			comp_id.setIcLoc(icLoc);
		}
		if(incomingRequest.containsKey("InvAlloc.icHeader"))
		{
			String icHeaderString = (String) incomingRequest.get("InvAlloc.icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			invalloc.setIcHeader(icHeader);
		}
		if(incomingRequest.containsKey("InvAlloc.icLine"))
		{
			String icLineString = (String) incomingRequest.get("InvAlloc.icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			comp_id.setIcLine(icLine);
		}
		if(incomingRequest.containsKey("InvAlloc.aisle"))
		{
			String aisle = (String ) incomingRequest.get("InvAlloc.aisle");
			invalloc.setAisle(aisle);
		}
		if(incomingRequest.containsKey("InvAlloc.locrow"))
		{
			String locrow = (String ) incomingRequest.get("InvAlloc.locrow");
			invalloc.setLocrow(locrow);
		}
		if(incomingRequest.containsKey("InvAlloc.tier"))
		{
			String tier = (String ) incomingRequest.get("InvAlloc.tier");
			invalloc.setTier(tier);
		}
		if(incomingRequest.containsKey("InvAlloc.bin"))
		{
			String bin = (String ) incomingRequest.get("InvAlloc.bin");
			invalloc.setBin(bin);
		}
		if(incomingRequest.containsKey("InvAlloc.quantity"))
		{
			String quantityString = (String) incomingRequest.get("InvAlloc.quantity");
			BigDecimal quantity = new BigDecimal ( quantityString );
			invalloc.setQuantity(quantity);
		}
		if(incomingRequest.containsKey("InvAlloc.icHeaderHistory"))
		{
			String icHeaderHistoryString = (String) incomingRequest.get("InvAlloc.icHeaderHistory");
			BigDecimal icHeaderHistory = new BigDecimal ( icHeaderHistoryString );
			invalloc.setIcHeaderHistory(icHeaderHistory);
		}
		if(incomingRequest.containsKey("InvAlloc.lastQuantity"))
		{
			String lastQuantityString = (String) incomingRequest.get("InvAlloc.lastQuantity");
			BigDecimal lastQuantity = new BigDecimal ( lastQuantityString );
			invalloc.setLastQuantity(lastQuantity);
		}
		if(incomingRequest.containsKey("InvAlloc.itemLocation"))
		{
			String itemLocation = (String ) incomingRequest.get("InvAlloc.itemLocation");
			invalloc.setItemLocation(itemLocation);
		}
		if(incomingRequest.containsKey("InvAlloc.lotNumber"))
		{
			String lotNumber = (String ) incomingRequest.get("InvAlloc.lotNumber");
			invalloc.setLotNumber(lotNumber);
		}
		if(incomingRequest.containsKey("InvAlloc.itemDate"))
		{
			String itemDateString = (String) incomingRequest.get("InvAlloc.itemDate");
			Date itemDate = Dates.getDate(itemDateString);
			invalloc.setItemDate(itemDate);
		}
		if(incomingRequest.containsKey("InvAlloc.icText"))
		{
			String icTextString = (String) incomingRequest.get("InvAlloc.icText");
			BigDecimal icText = new BigDecimal ( icTextString );
			invalloc.setIcText(icText);
		}

		invalloc.setComp_id(comp_id);
	}
}
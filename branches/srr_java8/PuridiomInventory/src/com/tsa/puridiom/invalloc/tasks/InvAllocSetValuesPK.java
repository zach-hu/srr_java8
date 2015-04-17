package com.tsa.puridiom.invalloc.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvAlloc;
import com.tsa.puridiom.entity.InvAllocPK;

/**
 * @author renzo
 *
 */
public class InvAllocSetValuesPK
{
	public void setValues(Map incomingRequest, InvAlloc invalloc )
	{
		String referenceType = (String ) incomingRequest.get("InvAlloc.referenceType");
		String icLocString = (String) incomingRequest.get("InvAlloc.icLoc");
		BigDecimal icLoc = new BigDecimal ( icLocString );
		String icLineString = (String) incomingRequest.get("InvAlloc.icLine");
		BigDecimal icLine = new BigDecimal ( icLineString );
		InvAllocPK comp_id = new InvAllocPK();
		comp_id.setIcLine(icLine);
		comp_id.setIcLoc(icLoc);
		comp_id.setReferenceType(referenceType);
		invalloc.setComp_id(comp_id);
	}
}
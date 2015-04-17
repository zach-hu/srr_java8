/**
 * 
 * Created on Jan 16, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvBinLocationSetValuesPK.java
 * 
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;

public class InvBinLocationSetValuesPK
{
	public void setValues(Map incomingRequest, InvBinLocation invbinlocation )
	{
		String icRcString = (String) incomingRequest.get("InvBinLocation_icRc");
		BigDecimal icRc = new BigDecimal ( icRcString );
		invbinlocation.setIcRc(icRc);
	}
}

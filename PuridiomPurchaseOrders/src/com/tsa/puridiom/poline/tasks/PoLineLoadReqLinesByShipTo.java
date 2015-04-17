/**
 * Created on Jan 26, 2004
 *
 * @author renzo project: HiltonPurchaseOrders
 *         com.tsa.puridiom.poline.tasks.PoLineLoadReqLines.java
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;

public class PoLineLoadReqLinesByShipTo extends PoLineLoadReqLines
{

	public void setAutoShipToData(Map incomingRequest, RequisitionLineAutoReleaseObject reqLineAutoReleaseObject)
	{
		if (reqLineAutoReleaseObject.getShipTo() != null) {
			incomingRequest.put("autoShipToLine", reqLineAutoReleaseObject.getShipTo());
		}

		incomingRequest.put("isAutoreleaseByReqShipTo", "Y");
	}
}
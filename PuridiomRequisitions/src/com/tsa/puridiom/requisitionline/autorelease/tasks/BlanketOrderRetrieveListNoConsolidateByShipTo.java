/**
 *
 */
package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.Map;

/**
 * @author Johnny Zapana
 */
public class BlanketOrderRetrieveListNoConsolidateByShipTo extends BlanketOrderRetrieveList
{
	public Map getGroup(Map incomingRequest)
	{
		return (Map) incomingRequest.get("groupByReqShipTo");
	}
}

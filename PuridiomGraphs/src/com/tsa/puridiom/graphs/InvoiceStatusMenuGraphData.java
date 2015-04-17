/**
 *
 */
package com.tsa.puridiom.graphs;

import java.util.List;
import java.util.Map;

/**
 * @author renzo
 * Created March 13, 2008
 */
public class InvoiceStatusMenuGraphData extends RequisitionerReqCountGraphData
{

	 public InvoiceStatusMenuGraphData(Map incomingRequest) {
		super(incomingRequest);
		// TODO Auto-generated constructor stub
	}

	public void setData(Map incomingRequest)
    {
    	this.setReqData((List)incomingRequest.get("invoiceStatusData"));
    }



}

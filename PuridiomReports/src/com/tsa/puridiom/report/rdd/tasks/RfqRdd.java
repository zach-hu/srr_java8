package com.tsa.puridiom.report.rdd.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;

public class RfqRdd extends Rdd
{
	public Date getHeaderRequiredDate(Map incomingRequest)
	{
		RfqHeader header = (RfqHeader)incomingRequest.get("rfqHeader");
		return header.getRequiredDate();
	}

	public List getLineList(Map incomingRequest)
	{
		RfqHeader header = (RfqHeader)incomingRequest.get("rfqHeader");
		return header.getRfqLineList();
	}

	public List getShipToList(List lineList, int index)
	{
		RfqLine line = (RfqLine)lineList.get(index);
		return line.getShipToList();
	}
}

package com.tsa.puridiom.report.rdd.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;

public class PoRdd extends Rdd
{
	public Date getHeaderRequiredDate(Map incomingRequest)
	{
		PoHeader header = (PoHeader)incomingRequest.get("poHeader");
		return header.getRequiredDate();
	}

	public List getLineList(Map incomingRequest)
	{
		PoHeader header = (PoHeader)incomingRequest.get("poHeader");
		return header.getPoLineList();
	}

	public List getShipToList(List lineList, int index)
	{
		PoLine line = (PoLine)lineList.get(index);
		return line.getShipToList();
	}
}

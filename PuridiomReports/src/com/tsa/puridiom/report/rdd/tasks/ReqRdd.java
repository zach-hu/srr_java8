package com.tsa.puridiom.report.rdd.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;

public class ReqRdd extends Rdd
{
	public Date getHeaderRequiredDate(Map incomingRequest)
	{
		RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
		return header.getRequiredDate();
	}

	public List getLineList(Map incomingRequest)
	{
		RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
		return header.getRequisitionLineList();
	}

	public List getShipToList(List lineList, int index)
	{
		RequisitionLine line = (RequisitionLine)lineList.get(index);
		return line.getShipToList();
	}
}

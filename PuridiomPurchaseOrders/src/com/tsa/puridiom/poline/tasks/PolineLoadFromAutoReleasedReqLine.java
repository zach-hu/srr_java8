package com.tsa.puridiom.poline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;

public class PolineLoadFromAutoReleasedReqLine extends PoLineLoadReqLines
{
	public RequisitionLine getRequisitionLine(int iter, List reqLines, Map incomingRequest)
	{
		RequisitionLineAutoReleaseObject autoLine = (RequisitionLineAutoReleaseObject) reqLines.get(iter);
		incomingRequest.put("PoLine_AutoQuantity", autoLine.getQuantity());
		return autoLine.getRequistionLine();
	}

	public void setOrder(PoHeader poHeader, PoLine poLine, int iter, List reqLines, List autoLines) {
		RequisitionLineAutoReleaseObject autoLine = (RequisitionLineAutoReleaseObject) reqLines.get(iter);
		autoLine.setPoLine(poLine);
		autoLine.setPoHeader(poHeader);
		autoLines.add(autoLine);
	}

	 public List getAutoReleaseObject(List reqLines, List autoReqLines)
    {
    	return reqLines;
    }

	public PuridiomProcess getProcess(Map incomingRequest) throws Exception
    {
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
        return processLoader.loadProcess("poline-load-from-autoreqline.xml");

    }

}
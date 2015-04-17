package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineAutoReleaseEmailSort extends Task
{
	public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map)object;
        try
        {
        	List lines = (List)incomingRequest.get("autoLines");
        	if(lines != null)
        	{
	        	Collections.sort(lines, new ReqLineAutoByReqNumberComparator());
	        	Map byReqNumber = new HashMap();
	        	for(int i =  0; i < lines.size(); i++)
	        	{
	        		RequisitionLine rql = (RequisitionLine)(((RequisitionLineAutoReleaseObject)lines.get(i)).getRequistionLine());
	        		List reqLines = null;
	        		if(byReqNumber.containsKey(rql.getRequisitionNumber()))
	        		{
	        			reqLines = (List)byReqNumber.get(rql.getRequisitionNumber());
	        		}
	        		else
	        		{
	        			reqLines = new ArrayList();
	        		}
	        		reqLines.add(lines.get(i));

	        		byReqNumber.put(rql.getRequisitionNumber(), reqLines);
	        	}
	        	ret = byReqNumber;
        	}
        	this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
			this.setStatus(Status.FAILED);
			throw new TsaException("Requisition Line List could not be grouped by Requisition Number.", e);
		}
        return ret;
    }

	public PoLine getPoLine(BigDecimal icPoLine, Map incomingRequest)
	{
		PoLine poLine = null;
		try
		{
			Map newIncomingRequest = new HashMap();
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
	        PuridiomProcess process = processLoader.loadProcess("poline-retrieve-by-id.xml");
	        newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
            newIncomingRequest.put("userId", incomingRequest.get("userId"));
            newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
            newIncomingRequest.put("PoLine_icPoLine", icPoLine.toString());

	        process.executeProcess(newIncomingRequest);
	        if(process.getStatus() != Status.SUCCEEDED)
	        {
	        	poLine = (PoLine)newIncomingRequest.get("poLine");
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return poLine;
	}
}

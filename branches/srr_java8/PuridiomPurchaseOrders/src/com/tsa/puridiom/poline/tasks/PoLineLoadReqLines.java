/**
 *
 * Created on Jan 26, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineLoadReqLines.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class PoLineLoadReqLines extends Task
{
	public RequisitionLine getRequisitionLine(int iter, List reqLines, Map incomingRequest)
	{
		return (RequisitionLine) reqLines.get(iter);
	}
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            List reqLines = (List)incomingRequest.get("requisitionLines");
            List autoReqLines = (List)incomingRequest.get("autoLineList");
            List poLineList = new ArrayList();
            String   	assignedOnly = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("ASSIGNMENT","ASSIGNEDONLY", "N");
            String noApprovalNeed = HiltonUtility.ckNull((String)incomingRequest.get("NoApprovalNeed"));
            String noApprovalNeedAutoAward = HiltonUtility.ckNull((String) incomingRequest.get("NoApprovalNeedAutoAward"));
            int line = 1;
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            String newStatus = (String) incomingRequest.get("newStatus");
            Boolean autoReleased = (Boolean)incomingRequest.get("autoReleased");
            List autoLines = (List)incomingRequest.get("autoLines");
            if(autoReleased == null){		autoReleased = Boolean.FALSE;		}
            Boolean autoAwarded = (Boolean)incomingRequest.get("autoAwarded");
            if(autoAwarded == null){		autoAwarded = Boolean.FALSE;		}

            RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            for (int i = 0; i < reqLines.size(); i++)
            {
                RequisitionLine reqLine = this.getRequisitionLine(i, reqLines, incomingRequest);
                //System.out.println("<<<<<<<<<<<<REquisitionLIne: " + reqLine.getLineNumber() + ">>>>>>>>>>>");
                boolean goForIt = true;
                if(assignedOnly.equalsIgnoreCase("Y") && !autoReleased.booleanValue() && !autoAwarded.booleanValue())
                {
                    String reqAssignedBuyer = reqLine.getAssignedBuyer();
                    String userId = (String) incomingRequest.get("userId");
                    //if(!reqAssignedBuyer.equalsIgnoreCase(userId) || !reqLine.getStatus().equals(DocumentStatus.REQ_APPROVED))
                    if(!(reqAssignedBuyer.equalsIgnoreCase(userId) || reqHeader.getAssignedBuyer().equalsIgnoreCase(userId)))
                    {
                        goForIt = false;
                    }

                }

                if(incomingRequest.get("poLine") != null)
                {
                    incomingRequest.put("poLine", null);
                }

                if(goForIt || noApprovalNeed.equalsIgnoreCase("Y"))
                {
                    PuridiomProcess process = this.getProcess(incomingRequest);
                    Map newIncomingRequest = new HashMap();
                    newIncomingRequest.put("requisitionLine", reqLine);
                    newIncomingRequest.put("lineNumber", String.valueOf(line));
                    newIncomingRequest.put("createdfrom", "REQ");
                    newIncomingRequest.put("poHeader", poHeader);
                    newIncomingRequest.put("PoLine_catalogId", incomingRequest.get("PoLine_catalogId"));
                    newIncomingRequest.put("PoLine_releaseNumber", incomingRequest.get("PoHeader_releaseNumber"));
                    newIncomingRequest.put("PoLine_poNumber", poHeader.getPoNumber());
                    newIncomingRequest.put("PoHeader_icPoHeader", poHeader.getIcPoHeader().toString());
                    newIncomingRequest.put("PoHeader_poType", poHeader.getPoType());
                    newIncomingRequest.put("createReleaseFromReq", incomingRequest.get("createReleaseFromReq"));
                    newIncomingRequest.put("PoLine_AutoQuantity", incomingRequest.get("PoLine_AutoQuantity"));
                    newIncomingRequest.put("requisitionHeader", reqHeader);
                    newIncomingRequest.put("taxCode", incomingRequest.get("taxCode"));
                    newIncomingRequest.put("newStatus", newStatus);
                    newIncomingRequest.put("NoApprovalNeed", noApprovalNeed);
                    newIncomingRequest.put("NoApprovalNeedAutoAward", noApprovalNeedAutoAward);

                    if (autoReqLines != null)
					{
						this.setAutoShipToData(newIncomingRequest, (RequisitionLineAutoReleaseObject) autoReqLines.get(i));
					}

                    if(newIncomingRequest.get("poLine") != null)
                    {
                        newIncomingRequest.put("poLine", null);
                    }

                    newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                    newIncomingRequest.put("userId", incomingRequest.get("userId"));
                    newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                    newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

                    process.executeProcess(newIncomingRequest);
                    this.setStatus(process.getStatus());
                    if (this.getStatus() != Status.SUCCEEDED)
                    {
                        Log.error(this, "poline-load-from-req failed with status: " + process.getStatus());
                        throw new TsaException("Error loading PoLine info from RequisitionLine Number: " + reqLine.getLineNumber());
                    }
                    else
                    {
                    	reqLine.setBlanketOrder(poHeader.getDisplayPoNumber(false).toString());
                    	PoLine poLine = (PoLine)newIncomingRequest.get("poLine");
                    	this.setOrder(poHeader, poLine, i, this.getAutoReleaseObject(reqLines, autoReqLines), autoLines);
                        poLineList.add(poLine);
                        line++;

                        if (newIncomingRequest.containsKey("poLineRecalculate"))
						{
                        	incomingRequest.put("recalculate", newIncomingRequest.get("poLineRecalculate"));
						}
                    }
                }
            }
            incomingRequest.put("poLineList", poLineList);
            incomingRequest.put("autoLines", autoLines);
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }

    public List getAutoReleaseObject(List reqLines, List autoReqLines)
    {
    	return autoReqLines;
    }

    public void setOrder(PoHeader poHeader, PoLine poLine, int i, List reqLines, List autoLines) {
		// TODO Auto-generated method stub

	}
	public PuridiomProcess getProcess(Map incomingRequest) throws Exception
    {
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
        return processLoader.loadProcess("poline-load-from-autoreq.xml");

    }

	public void setAutoShipToData(Map incomingRequest, RequisitionLineAutoReleaseObject reqLineAutoReleaseObject)
    {
    }
}
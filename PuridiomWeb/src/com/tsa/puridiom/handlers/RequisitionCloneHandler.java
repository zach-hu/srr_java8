/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.handlers;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Kelli
 */
public class RequisitionCloneHandler extends Handler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("requisition-clone.xml");
			process.executeProcess(incomingRequest);
			//List reqLinesVinimaya = (List)incomingRequest.get("reqLinesVinimaya");
			List reqLinesCloned = (List)incomingRequest.get("requisitionLineCloneMSRList");
			/*
			String replaceVinimayaLine = HiltonUtility.ckNull((String)incomingRequest.get("replaceVinimayaLine"));
			if(replaceVinimayaLine.equals("true")){
				Map vinimayaIncomingRequest = new HashMap();
				vinimayaIncomingRequest.put("punchOutReturnHandler","ExternalItemLookup");
				vinimayaIncomingRequest.put("DocComment_icHeader",((RequisitionHeader)incomingRequest.get("requisitionHeader")).getIcReqHeader());
				vinimayaIncomingRequest.put("successPage","/system/error.jsp");
				vinimayaIncomingRequest.put("Account_icHeader",((RequisitionHeader)incomingRequest.get("requisitionHeader")).getIcReqHeader());
				vinimayaIncomingRequest.put("organizationId",((String)incomingRequest.get("organizationId")));
				vinimayaIncomingRequest.put("userDateFormat",((String)incomingRequest.get("userDateFormat")));
				vinimayaIncomingRequest.put("icHeader",((RequisitionHeader)incomingRequest.get("requisitionHeader")).getIcReqHeader());
				vinimayaIncomingRequest.put("RequisitionLine_icReqHeader",((RequisitionHeader)incomingRequest.get("requisitionHeader")).getIcReqHeader());
				vinimayaIncomingRequest.put("RequisitionHeader_icReqHeader",((RequisitionHeader)incomingRequest.get("requisitionHeader")).getIcReqHeader());
				vinimayaIncomingRequest.put("RequisitionHeader_requisitionNumber",((RequisitionHeader)incomingRequest.get("requisitionHeader")).getRequisitionNumber());
				vinimayaIncomingRequest.put("DocComment_icLine","0");
				vinimayaIncomingRequest.put("RequisitionHeader_status",((RequisitionHeader)incomingRequest.get("requisitionHeader")).getStatus());
				vinimayaIncomingRequest.put("frompage","shopcart");
				vinimayaIncomingRequest.put("failurePage","system/error.jsp");
				vinimayaIncomingRequest.put("punchOutReturnSuccessPage","/requests/req_review.jsp");
				vinimayaIncomingRequest.put("RequisitionHeader_rqSubType",((RequisitionHeader)incomingRequest.get("requisitionHeader")).getRqSubType());
				vinimayaIncomingRequest.put("RequisitionHeader_itemLocation",((RequisitionHeader)incomingRequest.get("requisitionHeader")).getItemLocation());
				vinimayaIncomingRequest.put("s_item_type", "CAT");
				vinimayaIncomingRequest.put("Account_accountType", "RQH");
				vinimayaIncomingRequest.put("redirectPage", null);
				vinimayaIncomingRequest.put("nonStandardItem", "Y");
				vinimayaIncomingRequest.put("punchOutAddAccount", "N");
				vinimayaIncomingRequest.put("moduleType", "M");
				vinimayaIncomingRequest.put("createAction", "SAVE");
				vinimayaIncomingRequest.put("Catalog_catalogId", "VINIMAYA");
				vinimayaIncomingRequest.put("punchOutReturnSuccessPage", "/requests/req_review.jsp");
				for(int v = 0 ; v < reqLinesCloned.size() ; v++){
					RequisitionLine reqLineC = (RequisitionLine)reqLinesCloned.get(v);
					vinimayaIncomingRequest.put("icReqLine_"+v, reqLineC.getIcReqLine());
				}
				vinimayaIncomingRequest.put("lineCount", reqLinesCloned.size());
				PuridiomProcessLoader processLoaderVinimaya = new PuridiomProcessLoader((String)vinimayaIncomingRequest.get("organizationId"));
				PuridiomProcess processVinimaya = processLoaderVinimaya.loadProcess("browse-external-web-catalog.xml");
				processVinimaya.executeProcess(vinimayaIncomingRequest);
				if (processVinimaya.getStatus() == Status.SUCCEEDED)
				{
				    String	redirectUrl = (String) vinimayaIncomingRequest.get("redirectUrl");
					HttpServletResponse response = (HttpServletResponse) vinimayaIncomingRequest.get("HttpServletResponse");
					
					String successPage = response.encodeRedirectURL(redirectUrl);
					//encodeRedirectUrl
					incomingRequest.put("redirectPage", successPage);
					incomingRequest.put("continueHandlers", "N");
				}
				else
				{
					incomingRequest.put("viewPage", vinimayaIncomingRequest.get("failurePage"));
				}
				//com.tsa.puridiom.TOKEN
			}else{
				
			}
			*/
			if (process.getStatus() == Status.SUCCEEDED)
			{
				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			}
			else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			throw exception;
		}
		finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}
}

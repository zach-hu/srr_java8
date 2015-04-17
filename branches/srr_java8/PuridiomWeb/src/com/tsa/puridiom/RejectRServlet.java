/**
 *
 */
package com.tsa.puridiom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsa.puridiom.common.utility.HiltonUtility ;
import com.tsa.puridiom.entity.ApprovalLink;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny Zapana
 */
public class RejectRServlet extends HttpServlet
{

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String requestURI = request.getRequestURI();
		String approvalLinkURL = requestURI.substring(requestURI.lastIndexOf("/") + 1, requestURI.length());
		String[] approvalLinkData = this.getIcApprovalLink(approvalLinkURL);
		String message = "";
		response.setContentType("text/plain");

		try
		{
			ApprovalLink approvalLink = this.retrieveApprovalLink(approvalLinkData[0], approvalLinkData[1]);

			message = this.rejectDocument(approvalLink, approvalLinkData[1]);
			out.println(message);

		} catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request, response);
	}

	private String[] getIcApprovalLink(String approvalLinkURL)
	{
		char tokenChars[] = approvalLinkURL.toCharArray();
		int splitIndex = 0;

		for (int i = 0; i < tokenChars.length; i++)
		{
			if (!((tokenChars[i] >= 48) && (tokenChars[i] <= 57)))
			{
				splitIndex = i;
				break;
			}
		}

		return new String[] { approvalLinkURL.substring(0, splitIndex), approvalLinkURL.substring(splitIndex, approvalLinkURL.length()) };
	}

	private ApprovalLink retrieveApprovalLink(String icApprovalLink, String organizationId) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
		PuridiomProcess process = processLoader.loadProcess("approvallink-retrieve.xml");
		Map incomingRequest = new HashMap();

		incomingRequest.put("organizationId", organizationId);
		incomingRequest.put("ApprovalLink_icApprovalLink", icApprovalLink);

		process.executeProcess(incomingRequest);

		return (ApprovalLink) incomingRequest.get("approvalLink");
	}

	private String rejectDocument(ApprovalLink approvalLink, String organizationId) throws Exception
	{
		String message = "";
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
		PuridiomProcess process = processLoader.loadProcess("requisition-reject-by-email-text.xml");
		Map incomingRequest = new HashMap();
        String userId = approvalLink.getUserId();

		if (approvalLink.getDoctype().equalsIgnoreCase("REQ"))
		{
			incomingRequest.put("RequisitionHeader_icReqHeader", approvalLink.getIcHeader().toString());
			incomingRequest.put("RequisitionHeader_requisitionNumber", approvalLink.getDocumentNumber());
			incomingRequest.put("RequisitionLine_icReqHeader", approvalLink.getIcHeader().toString());
			incomingRequest.put("ApprovalLog_icHeader", approvalLink.getIcHeader().toString());
		}

		incomingRequest.put("organizationId", organizationId);
		incomingRequest.put("userId", userId);
        incomingRequest.put("userTimeZone", UserManager.getInstance().getUser(organizationId, userId).getTimeZone());

		process.executeProcess(incomingRequest);

		RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		message = (String) incomingRequest.get("Message");

		List approvalLogList = (List) incomingRequest.get("approvalLogList");

		for (Iterator iter = approvalLogList.iterator(); iter.hasNext();)
		{
			ApprovalLog approvalLog = (ApprovalLog) iter.next();
			String	alUser = approvalLog.getCallForward();
			String  akUser = approvalLink.getUserId() ;
			String	approved = approvalLog.getApproved();
			if (alUser.equals(akUser) && approved.equals("R"))
			{
				message = "Your Rejection of Requisition #" + requisitionHeader.getRequisitionNumber() + ", has been completed. ";
			}
		}

		if (HiltonUtility.isEmpty(message))
		{
			message = "Requisition #" + requisitionHeader.getRequisitionNumber() + " has already been processed. ";
		}

		Log.debug(this, "Message for reject: ***" + message + "***");

		return message;
	}
}

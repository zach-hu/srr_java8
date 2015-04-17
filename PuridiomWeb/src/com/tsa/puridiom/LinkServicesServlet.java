/**
 *
 */
package com.tsa.puridiom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsa.puridiom.entity.ApprovalLink;
import com.tsa.puridiom.entity.Contact;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Johnny Zapana
 */
public class LinkServicesServlet extends HttpServlet
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
		ApprovalLink approvalLink = new ApprovalLink();
		String approvalLinkURL = requestURI.substring(requestURI.lastIndexOf("/") + 1, requestURI.length());
		String[] approvalLinkData = this.getIcApprovalLink(approvalLinkURL);
		String message = "";
		response.setContentType("text/plain");

		try
		{
			approvalLink = this.retrieveApprovalLink(approvalLinkData[0], approvalLinkData[1]);

			message = this.acknowledgedReceipt(approvalLink, approvalLinkData[1]);

			message += "Thank you";

			out.println(message);

			Log.debug(this, "Receipt Acknowledgement (ApprovalLink = " + approvalLink.getIcApprovalLink() + ") was processing successfully");

		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();

			Log.error(this, "Error processing Receipt Acknowledgement (ApprovalLink = " + approvalLink.getIcApprovalLink() + ") : " + e.getMessage());
		}
		out.close();
	}

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

	private String acknowledgedReceipt(ApprovalLink approvalLink, String organizationId) throws Exception
	{
		String message = "";
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
		PuridiomProcess process = processLoader.loadProcess("po-supplier-acknowledgement.xml");
		Map incomingRequest = new HashMap();
		Contact contact;
		String[] contactData;

		if (approvalLink.getDoctype().equalsIgnoreCase("PO"))
		{
			incomingRequest.put("PoHeader_icPoHeader", approvalLink.getIcHeader().toString());

			contactData = approvalLink.getUserId().split("//");

			contact = this.getVendorContact(contactData[0], contactData[1], organizationId);

			incomingRequest.put("userName", contact.getDisplayName());
			incomingRequest.put("userId", contact.getEmailAddr());
		}

		incomingRequest.put("organizationId", organizationId);

		process.executeProcess(incomingRequest);

		return message;
	}

	private Contact getVendorContact(String vendorId, String contactCode, String organizationId) throws Exception
	{
		if (Utility.isEmpty(vendorId))
		{
			return null;
		}
		if (Utility.isEmpty(contactCode))
		{
			contactCode = "001";
		}

		Map incomingRequest = new HashMap();
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
		PuridiomProcess process = processLoader.loadProcess("contact-retrieve-by-code.xml");
		incomingRequest.put("organizationId", organizationId);
		incomingRequest.put("Contact_contactCode", contactCode);
		incomingRequest.put("Contact_vendorId", vendorId);
		process.executeProcess(incomingRequest);

		return (Contact) incomingRequest.get("contact");
	}
}

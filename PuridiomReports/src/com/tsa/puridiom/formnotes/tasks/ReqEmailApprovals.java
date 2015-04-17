
package com.tsa.puridiom.formnotes.tasks;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.jasperreports.ReportUtils;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

/**
 * Is responsible for creating an email in text format for Lotus Notes user
 * 
 * @author Alexander Angulo
 */
public class ReqEmailApprovals extends Task
{
	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String organizationId = (String) incomingRequest.get("organizationId");
			List routingList = (List) incomingRequest.get("routingList");
			UserProfile userProfile = UserManager.getInstance().getUser(organizationId, reqHeader.getRequisitionerCode());
			String siteUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsUrl");
			siteUrl = PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "URL", siteUrl);
			String userId = (String) incomingRequest.get("userId");
			String mid = (String) incomingRequest.get("mid");
			String fileName = RequisitionType.toString(reqHeader.getRequisitionType(), organizationId) + "[" + reqHeader.getRequisitionNumber() + "].txt";
			String filePath = DictionaryManager.getInstance("host", organizationId).getProperty("reportsOut", "") + fileName;
			RequisitionLine reqLine;
			ApprovalLog approvalLog;
			Account account;
			String idApprovalLink;

			StringBuffer content = new StringBuffer();
			FileOutputStream fo;
			PrintStream ps;

			content.append("<br>");
//			content.append("---------------------------------\r\n");
			content.append("Request Number: " + reqHeader.getRequisitionNumber() + "\r\n");
			content.append("Req Date: " + new SimpleDateFormat("MMM dd, yyyy").format(reqHeader.getRequisitionDate()) + "\r\n");
			content.append("<br>");
			content.append(" Ship To : " + reqHeader.getShipToCode() + "\r\n");

			if (reqHeader.getShipToAddress() != null)
			{
				content.append(" " + reqHeader.getShipToAddress().getAddressLine1() + "\r\n");
				content.append(" " + reqHeader.getShipToAddress().getAddressLine2() + "\r\n");
				content.append(" " + reqHeader.getShipToAddress().getAddressLine3() + "\r\n");
				content.append(" " + reqHeader.getShipToAddress().getCityStatePostal() + "\r\n");
				String country = reqHeader.getShipToAddress().getCountry();
				if(!country.equals("USA") && !country.equals("US"))
					content.append(" " + reqHeader.getShipToAddress().getCountry() + "\r\n");
			}

			content.append("<br>");
			content.append(" Supplier : " + reqHeader.getVendorId() + "\r\n");

			if (reqHeader.getVendorAddress() != null)
			{
				content.append(" " + reqHeader.getVendorAddress().getAddressLine1() + "\r\n");
				content.append(" " + reqHeader.getVendorAddress().getAddressLine2() + "\r\n");
				content.append(" " + reqHeader.getVendorAddress().getAddressLine3() + "\r\n");
				content.append(" " + reqHeader.getVendorAddress().getCityStatePostal() + "\r\n");
				String country = reqHeader.getShipToAddress().getCountry();
				if(!country.equals("USA") && !country.equals("US"))
					content.append(" " + reqHeader.getVendorAddress().getCountry() + "\r\n");
			}

			content.append("<br>");
			content.append(" User Name: " + userProfile.getDisplayName() + "\r\n");
			content.append(" Phone: " + userProfile.getPhoneNumber() + "\r\n");
			content.append("<br>");
			content.append("Line No. \t Description \r\n");
			content.append("--------------------------------\r\n");

			for (Iterator iter = reqHeader.getRequisitionLineList().iterator(); iter.hasNext();)
			{
				reqLine = (RequisitionLine) iter.next();
				content.append(reqLine.getLineNumber() + ". \t" + reqLine.getDescription() + "\r\n");
				content.append("\t\t" + reqLine.getQuantity() + " " + reqLine.getUmCode() + " " + HiltonUtility.getFormattedDollar(reqLine.getUnitPrice(),organizationId) + " = " + HiltonUtility.getFormattedDollar(reqLine.getLineTotal(), organizationId) + "\r\n");
				for (Iterator iter2 = reqLine.getAccountList().iterator(); iter2.hasNext();)
				{
					account = (Account) iter2.next();
					content.append("    Account: "
							+ ReportUtils.getAcctString(account.getFld1(), account.getFld2(), account.getFld3(), account.getFld4(), account.getFld5(), account.getFld6(), account.getFld7(), account
									.getFld8(), account.getFld9(), account.getFld10(), account.getFld11(), account.getFld12(), account.getFld13(), account.getFld14(), account.getFld15(), organizationId)
							+ "\t" + account.getAllocAmount() + "\r\n");
				}
			}

			content.append("<br>");
			content.append("\tSubtotal:\t" + HiltonUtility.getFormattedDollar(reqHeader.getSubtotal(),organizationId) + "\r\n");
			content.append("\tTotal:   \t" + HiltonUtility.getFormattedDollar(reqHeader.getTotal(), organizationId) + "\r\n");

			content.append("<br>");
			content.append(" Account Summary \r\n");

			for (Iterator iter = reqHeader.getAccountList().iterator(); iter.hasNext();)
			{
				account = (Account) iter.next();
				content.append(" "
						+ ReportUtils.getAcctString(account.getFld1(), account.getFld2(), account.getFld3(), account.getFld4(), account.getFld5(), account.getFld6(), account.getFld7(), account
								.getFld8(), account.getFld9(), account.getFld10(), account.getFld11(), account.getFld12(), account.getFld13(), account.getFld14(), account.getFld15(), organizationId)
						+ "\t" + account.getAllocAmount() + "\r\n");
			}

			content.append("<br>");
			content.append("Approval Information \r\n");

			for (Iterator iter = routingList.iterator(); iter.hasNext();)
			{
				approvalLog = (ApprovalLog) iter.next();
				content.append(ReportUtils.getUserName(approvalLog.getUserId(), organizationId) + "\t");

				if (approvalLog.getApproved().equalsIgnoreCase("Y"))
				{
					if(approvalLog.getDateApproved()!= null){
						content.append("[" + new SimpleDateFormat("MM/dd/yyyy").format(approvalLog.getDateApproved()) + "] \r\n");
					}
				} else if (approvalLog.getApproved().equalsIgnoreCase("A"))
				{
					content.append("[Current] \r\n");
				}
			}

			if (!content.toString().endsWith("\r\n")) 
			{
				content.append("\r\n");
			}
			
			idApprovalLink = siteUrl + ReportUtils.getApprovalLink(userId, organizationId, mid, "approve", reqHeader.getRequisitionNumber(), null, reqHeader.getIcReqHeader().toString(), "REQ", "/approval/mail_approve.jsp");
			
			content.append("<br>");
			content.append("To review and approve the request, click below: \r\n");
			content.append(idApprovalLink + "\r\n");
			content.append("\r\n");
			
			content.append("<br>");
			content.append("Copyright @ " + Calendar.getInstance().get(Calendar.YEAR) + " Puridiom.com");

			fo = new FileOutputStream(filePath);
			ps = new PrintStream(fo);
			ps.println(this.getFormattedContent(content));
			ps.close();
			fo.close();

			ret = filePath;
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}

	public String getFormattedContent(StringBuffer content)
	{
		StringBuffer formattedContent = new StringBuffer();
		String lineSkip = "\r\n";
		int numSplitsLine;
		int maxChars = 40;
		String text = "";

		text = content.toString().replaceAll("\r\n(\\s*)\r\n", "\r\n");
		text = text.replaceAll("<br>", "\r\n");

		String lines[] = text.split(lineSkip);

		for (int i = 0; i < lines.length; i++)
		{
			if (lines[i].matches("<http://(.)+>") || lines[i].length() <= 40)
			{
				formattedContent.append(lines[i] + lineSkip);
			} else
			{
				numSplitsLine = (int) Math.ceil((lines[i].length() / ((float) maxChars)));

				for (int j = 0; j < numSplitsLine; j++)
				{
					if ((j + 1) == numSplitsLine)
					{
						formattedContent.append(lines[i].substring(maxChars * j, lines[i].length()) + lineSkip);
					} else
					{
						formattedContent.append(lines[i].substring(maxChars * j, maxChars * (j + 1)) + lineSkip);
					}
				}
			}
		}

		return text;
	}
}

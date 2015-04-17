/*
 * @author ebsGroup mcvz
 */
package com.tsa.puridiom.emails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ApprovalsByEmail extends Task
{
	private String processXml = "Y";

	private int iNumMessagesReceived = 0;

	private int iNumMessagesProcessed = 0;

	private EmailsMisc misc = null;

	public int getNumMessagesProcessed()
	{
		return this.iNumMessagesProcessed;
	}

	// *********************************************Read Inbox
	// ****************************************************
	public void readInbox(String organizationId, String approve)
	{
		Log.debug(this, "getEmails starts");
		Store store = null;
		Folder folder = null;
		try
		{
			Session session = EmailUtilities.getEmailSession(organizationId, EmailUtilities.getAuthenticationType(approve, organizationId), approve, EmailServer.POP3);
			store = session.getStore("pop3");
			store.connect();

			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);

			Message message[] = folder.getMessages();
			Log.debug(this, "we have [" + String.valueOf(message.length) + "] messages");
			misc = EmailsMisc.getInstance(organizationId);
			misc.setFound(message.length);
			for (int i = 0; i < message.length; i++)
			{
				Log.debug(this, "\tProcessing msg [" + String.valueOf(i) + "]: " + this.messageDetails(message[i]));

				if (this.ruleApproval(message[i], organizationId, approve))
				{
					Log.info(this, "Email passed the rule approval");
					// check body and call to Process
					Map moduleAndIcNumber = this.getModuleAndIcNumberApproval(message[i]);
					String module = (String) moduleAndIcNumber.get("module");
					String icNumber = (String) moduleAndIcNumber.get("icNumber");
					icNumber = icNumber.toUpperCase();
					String releaseNumber = (String) moduleAndIcNumber.get("releaseNumber");
					String revisionNumber = (String) moduleAndIcNumber.get("revisionNumber");

					if (!HiltonUtility.isEmpty(module) && !HiltonUtility.isEmpty(icNumber))
					{
						Log.info(this, "Module: " + module + " icNumber: " + icNumber);
						Map approvalProcess = this.getApprovalCallProcess(message[i], organizationId, approve, module);
						String approverNotes = (String) approvalProcess.get("approverNotes");
						String approvalAction = (String) approvalProcess.get("approvalAction");
						String processToCall = (String) approvalProcess.get("processName");

						if (!HiltonUtility.isEmpty(processToCall))
						{
							String processRetrieveToCall = this.processRetrieveToCall(module);
							String icHeader = this.callRetrieveProcess(processRetrieveToCall, icNumber, organizationId, module, releaseNumber);
							if (!HiltonUtility.isEmpty(icHeader))
							{
								UserProfile userProfile = this.getUserProfileFromMessage(message[i], organizationId);
								String userId = userProfile.getUserId();
								String incomingEmail = userProfile.getMailId();
								int approvalProcessStatus = this.callApprovalProcess(processToCall, icHeader, icNumber, organizationId, module, releaseNumber, userId, approverNotes);
								String subject = message[i].getSubject();
								this.sendConfirmationMessage(organizationId, incomingEmail, subject, module, icHeader, icNumber, approvalAction, approvalProcessStatus);
							}
						}
					}
				}

				this.handleMessage(message[i], organizationId);
				this.iNumMessagesReceived++;
			}
			Log.debug(this, "\tincoming inbox processed going to sleep");
		} catch (Exception e)
		{
			Log.error(this, e.getMessage());
			e.printStackTrace();
		} finally
		{
			try
			{
				if (folder != null)
				{
					folder.close(true);
				}
				if (store != null)
				{
					store.close();
				}
			} catch (Exception e)
			{
				Log.error(this, "error closing email inbox" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	// **************************************call
	// process**************************************************
	public int callApprovalProcess(String processToCall, String icHeader, String icNumber, String organizationId, String module, String releaseNumber, String userId, String approverNotes)
	{

		int approvalProcessStatus = 0;

		try
		{

			Map incomingRequest = new HashMap();

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess(processToCall);

			if (module.equalsIgnoreCase("REQ"))
			{
				incomingRequest.put("RequisitionHeader_icReqHeader", icHeader);
				incomingRequest.put("RequisitionHeader_requisitionNumber", icNumber);
				incomingRequest.put("RequisitionLine_icReqHeader", icHeader);
				incomingRequest.put("ApprovalLog_icHeader", icHeader);
			}

			if (module.equalsIgnoreCase("PO"))
			{
				incomingRequest.put("PoHeader_icPoHeader", icHeader);
				incomingRequest.put("PoHeader_poNumber", icNumber);
				incomingRequest.put("PoHeader_releaseNumber", releaseNumber);
				incomingRequest.put("PoLine_icReqHeader", icHeader);
			}

			incomingRequest.put("ApprovalLog_approverNotes", approverNotes);
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("userId", userId);
			process.executeProcess(incomingRequest);
			approvalProcessStatus = process.getStatus();

			Log.debug(this, module + " :: " + icHeader + " :: STATUS CHANGED");

		}

		catch (Exception e)
		{
			e.printStackTrace();
			Log.debug(this, module + " :: " + icHeader + " :: STATUS CHANGED FAILED");

		}
		return approvalProcessStatus;
	}

	// ******************************************************************************************************
	public String processRetrieveToCall(String module)
	{

		String processRetrieveToCall = "";

		try
		{

			if (module.equalsIgnoreCase("REQ"))
			{
				processRetrieveToCall = "requisition-retrieve-by-number.xml";
			}
			if (module.equalsIgnoreCase("PO"))
			{
				processRetrieveToCall = "poheader-retrieve-by-number.xml";
			}

			Log.debug(this, "processRetrieveToCall... " + processRetrieveToCall);
		} catch (Exception e)
		{
			e.printStackTrace();
			Log.debug(this, "processRetrieveToCall...FAILED");
		}
		return processRetrieveToCall;
	}

	// ****************************************************************************************************************
	public String callRetrieveProcess(String processToCall, String icNumber, String organizationId, String module, String releaseNumber)
	{

		String icHeader = "";

		try
		{

			// requisition-retrieve-by-number.xml
			// int cont= Accountant.getCuenta();

			Map incomingRequest = new HashMap();

			String icNumberName = "";
			if (module.equalsIgnoreCase("REQ"))
			{
				icNumberName = "RequisitionHeader_requisitionNumber";
			}
			if (module.equalsIgnoreCase("PO"))
			{
				icNumberName = "PoHeader_poNumber";
			}

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess(processToCall);

			incomingRequest.put(icNumberName, icNumber);
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("PoHeader_releaseNumber", releaseNumber);
			incomingRequest.put("PoHeader_revisionNumber", releaseNumber);

			process.executeProcess(incomingRequest);
			if (module.equalsIgnoreCase("REQ"))
			{
				RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("icHeader");
				if (reqHeader != null)
				{
					icHeader = reqHeader.getIcReqHeader().toString();
				}
			}

			if (module.equalsIgnoreCase("PO"))
			{
				String icPoHeader = (String) incomingRequest.get("poHeader");
				if (!HiltonUtility.isEmpty(icPoHeader))
				{
					icHeader = icPoHeader;
				}
			}
			Log.debug(this, "callRetrieveProcess...SUCCEDED::  " + module + "  icHeader = " + icHeader);
		} catch (Exception e)
		{
			e.printStackTrace();
			Log.debug(this, "callRetrieveProcess...FAILED");
		}
		return icHeader;
	}

	// *****************************************************************************************************************//

	public void sendConfirmationMessage(String organizationId, String incomingEmail, String subject, String module, String icHeader, String icNumber, String approvalAction, int approvalProcessStatus)
	{
		int processStatus = 0;
		String docType = "";

		if (module.equals("REQ"))
			docType = "Requisition";
		else if (module.equals("PO"))
			docType = "Purchase Order";

		if (approvalAction.equals("Approve"))
			approvalAction = "approved";
		else if (approvalAction.equals("Reject"))
			approvalAction = "rejected";

		String messagetext = docType + " #" + icNumber + " has been " + approvalAction + " on " + HiltonUtility.getFormattedDate(new Date(), organizationId) + ".";
		String sendfrom = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.from", "");

		if (approvalProcessStatus != Status.SUCCEEDED)
		{
			messagetext = "Your " + approvalAction + " FAILED";
		}

		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("SendQueue_subject", subject);
			incomingRequest.put("SendQueue_messagetext", messagetext);
			incomingRequest.put("SendQueue_sendfromtype", "E");
			incomingRequest.put("SendQueue_sendfrom", sendfrom);
			incomingRequest.put("SendQueue_sendtotype", "E");
			incomingRequest.put("SendQueue_sendto", incomingEmail);
			incomingRequest.put("SendQueue_doctype", module);
			incomingRequest.put("SendQueue_docic", icHeader);
			incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
			process.executeProcess(incomingRequest);
			processStatus = process.getStatus();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// ***********************************************************************************************************************
	// ******************************************************************************************************************
	public String messageDetails(Message message)
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			sb.append("from: " + this.printfrom(message.getFrom()));
			sb.append("Subject: " + message.getSubject() + "\r\n");
			// message.writeTo(arg0)
		} catch (MessagingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();

	}

	/**
	 * rule to process email
	 *
	 * @param message
	 * @return
	 */
	protected boolean rule(Message message, String organizationId)
	{
		boolean ret = false;
		Log.debug(this, "Message Rules. ");
		try
		{
			ret = this.checkTo(message.getAllRecipients(), organizationId);
		} catch (MessagingException e)
		{
			ret = false;
			e.printStackTrace();
		}
		try
		{
			if (message.getSubject().equalsIgnoreCase("stop"))
			{
				ret = false;
			}
		} catch (MessagingException e)
		{
			// something is wron with message
			Log.error(this, "Message was not processed. " + this.messageDetails(message) + e.getMessage());
			ret = false;
			e.printStackTrace();
		}
		if (!ret)
		{
			Log.error(this, "Message was not processed.");
			SendReplyEmail errorReplyEmail = new SendReplyEmail();
			errorReplyEmail.sendEmail("Message was not processed.", organizationId);
		}
		Log.debug(this, "Message rules returns: " + ret);
		return ret;
	}

	// ******************************************************************************************************************************//
	private boolean checkTo(Address[] to, String organizationId)
	{
		String toDefault = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.app.to", "");
		Log.debug(this, "rules checking email was sent to: " + toDefault);
		for (int j = 0; j < to.length; j++)
		{
			InternetAddress addie = (InternetAddress) to[j];
			Log.debug(this, addie.toString());
			if (addie.toString().toLowerCase().indexOf(toDefault.toLowerCase()) > -1)
			{
				Log.debug(this, addie.toString() + "is a good Address");
				return true;
			}
		}
		Log.debug(this, "checkTo returns false");
		return false;
	}

	/**
	 * @param from
	 */
	private String printfrom(Address[] from)
	{
		StringBuffer sb = new StringBuffer();

		for (int j = 0; j < from.length; j++)
		{
			// System.out.println(this.nowDebug + "\tfrom: " + from[j]);
			InternetAddress fromAddy = (InternetAddress) from[j];
			sb.append(fromAddy.getAddress());
			if (from.length > 1)
			{
				sb.append(";");
			}
		}
		return sb.toString();
	}

	public boolean ruleApproval(Message message, String organizationId, String approve)
	{
		boolean ret = false;

		String ruleSubjectApproval = DictionaryManager.getInstance("emails", organizationId).getProperty(approve + ".rule.subject.approval", "");

		try
		{

			String subject = message.getSubject();
			subject = subject.toLowerCase();
			Log.debug(this, "RULE_SUBJECT_APPROVAL: subject > " + subject);

			if (subject.indexOf(ruleSubjectApproval) > -1)
			{
				ret = true;
			}
		} catch (MessagingException e)
		{
			e.printStackTrace();
		}
		return ret;
	}

	public String getFrom(Message message)
	{

		String emailFrom = null;
		try
		{
			Address[] emailFromAdd;
			emailFromAdd = message.getFrom();

			InternetAddress fromAddFirts = (InternetAddress) emailFromAdd[0];
			emailFrom = fromAddFirts.getAddress();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return emailFrom;
	}

	public UserProfile getUserProfileFromMessage(Message message, String organizationId)
	{

		UserProfile userProfile = null;
		try
		{
			String emailFrom = this.getFrom(message);
			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("UserProfile_mailId", emailFrom);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("userprofile-retrieve-by-mailid.xml");
			process.executeProcess(newIncomingRequest);
			userProfile = (UserProfile) newIncomingRequest.get("userProfile");
			userProfile.getUserId();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return userProfile;
	}

	public Map getModuleAndIcNumberApproval(Message message)
	{

		Map moduleAndIcNumber = new HashMap();
		String module = "";
		String icNumber = "";
		String releaseNumber = "0";
		String revisionNumber = "0";
		int ptr = 0;

		try
		{
			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("UserProfile_mailId", message.getFrom());

			String subject = message.getSubject();
			subject = subject.toLowerCase();
			Log.debug(this, "RULE_SUBJECT_APPROVAL ::subject = " + subject);

			if (subject.indexOf("re:") > -1)
			{
				ptr = ptr + 1;
			}

			String[] subjectParts = subject.split(" ");
			/*
			 * for(int i = 0; i < subjectParts.length; i++) { String subjectPart =
			 * subjectParts[i]; }
			 */

			module = subjectParts[ptr];
			icNumber = subjectParts[ptr + 1];

			module = module.replaceAll(" ", "");
			icNumber = icNumber.replaceAll(" ", "");

			if (subjectParts[ptr + 2].equalsIgnoreCase("revisionNumber") && subjectParts[ptr + 3].equalsIgnoreCase("="))
			{
				revisionNumber = subjectParts[4];
			}

			if (module.equalsIgnoreCase("requisition"))
			{
				module = "REQ";
			}

			if (module.equalsIgnoreCase("order"))
			{
				module = "PO";

				if (icNumber.indexOf("-") > -1)
				{
					String[] array = icNumber.split("-");
					icNumber = array[0];
					releaseNumber = array[1];
				}
			}

			moduleAndIcNumber.put("module", module);
			moduleAndIcNumber.put("icNumber", icNumber);
			moduleAndIcNumber.put("releaseNumber", releaseNumber);
			moduleAndIcNumber.put("revisionNumber", revisionNumber);
		}

		catch (MessagingException e)
		{
			e.printStackTrace();
		}
		return moduleAndIcNumber;
	}

	// **********************************************************************************************************//
	public Map getApprovalCallProcess(Message message, String organizationId, String approve, String module)
	{

		Map approvalProcess = new HashMap();
		String processName = "";
		String approvalAction = "";
		String approverNotes = "";
		String messajeApprove = DictionaryManager.getInstance("emails", organizationId).getProperty(approve + ".rule.message.approve", "");
		String messajeRejected = DictionaryManager.getInstance("emails", organizationId).getProperty(approve + ".rule.message.rejected", "");
		String messajeReroute = DictionaryManager.getInstance("emails", organizationId).getProperty(approve + ".rule.message.reroute", "");

		try
		{
			String messageText = message.getContent().toString();
			int endFirstLine = messageText.indexOf("\r\n");
			if(endFirstLine > -1)
			{
				approvalAction = messageText.substring(0, endFirstLine).toLowerCase();
				if(messageText.indexOf("-----") > 1)
					approverNotes = messageText.substring(endFirstLine + 2, messageText.indexOf("-----"));
			} else
			{
				approvalAction = messageText.toLowerCase();
			}

			if (module.equalsIgnoreCase("REQ"))
			{
				if (approvalAction.indexOf(messajeApprove) > -1)
				{
					approvalAction = "Approve";
					processName = "requisition-approve-by-text-email.xml";
				} else if (approvalAction.indexOf(messajeRejected) > -1)
				{
					approvalAction = "Reject";
					processName = "requisition-reject-by-email-text.xml";
				} else if (approvalAction.indexOf(messajeReroute) > -1)
				{
					approvalAction = "Reroute";
					processName = "requisition-reroute-by-email-text.xml";
				}
			}

			if (module.equalsIgnoreCase("PO"))
			{
				if (approvalAction.indexOf(messajeApprove) > -1)
				{
					approvalAction = "Approve";
					processName = "po-approve.xml";
				}
				if (approvalAction.indexOf(messajeRejected) > -1)
				{
					approvalAction = "Reject";
					processName = "po-reject.xml";
				}
				if (approvalAction.indexOf(messajeReroute) > -1)
				{
					approvalAction = "Reroute";
					processName = "po-reroute.xml";
				}
			}

		} catch (Exception e)
		{
			Log.debug(WirelessEmailUtils.class, " error saving file!");
			e.printStackTrace();
		}
		approvalProcess.put("approvalAction", approvalAction);
		approvalProcess.put("approverNotes", approverNotes);
		approvalProcess.put("processName", processName);
		return approvalProcess;
	}

	// ****************************************************************************************************************************************

	public void printMessageText(Message message, String organizationId)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			System.out.println(message.getFrom() + "\t" + message.getSubject());
			// String line = reader.readLine();
			message.writeTo(System.out);
			// message.
		}

		catch (Exception e)
		{
			Log.error(this, "An error ocurred processing emails " + e.getMessage());
			e.printStackTrace();
		}
	}

	// ****************************************************************************************************************************************
	/**
	 * @param message
	 */
	public void handleMessage(Message message, String organizationId)
	{
		try
		{
			String from = this.printfrom(message.getFrom());
			Object objContent = message.getContent();

			if (objContent instanceof Multipart)
			{
				Multipart multipart = (Multipart) message.getContent();
				// File cxml = this.handleContent(multipart);
				this.handleContent(multipart, from, message.getSubject(), organizationId);
			} else
			{
				this.handleStringContent((String) objContent, from, message.getSubject(), organizationId);
			}

			// EmailUtils.processCxml(cxml, mailProps, this.getProcessXml(),
			// this.nowDebug);
		} catch (Exception e)
		{
			Log.error(this, "An error ocurred processing emails " + e.getMessage());
			e.printStackTrace();
		} finally
		{
			try
			{
				message.setFlag(Flags.Flag.DELETED, true);
				Log.debug(this, "FINALLY HANDLE MESSAGE");
			} catch (MessagingException e1)
			{
				// Log.error("Message could not be deleted", e1);
				e1.printStackTrace();
			}
		}

	}

	/**
	 * @param content
	 * @param from
	 * @param subject
	 * @param organizationId
	 */
	private void handleStringContent(String content, String from, String subject, String organizationId)
	{
		try
		{

			WirelessEmailUtils.saveFile(subject, content, from, organizationId);

		} catch (Exception e)
		{
			// TODO: handle exception
		}
	}

	/**
	 * @param multipart
	 */
	private void handleContent(Multipart multipart, String from, String subject, String organizationId)
	{
		Log.debug(this, "handleContent");

		try
		{
			// String contentType=multipart.getContentType();
			for (int j = 0; j < multipart.getCount(); j++)
			{
				Part part = multipart.getBodyPart(j);
				System.out.println(part.getContent().toString());

				String disposition = part.getDisposition();
				if (disposition != null)
				{
					InputStream in = part.getInputStream();
					if (disposition.equals(Part.ATTACHMENT))
					{
						WirelessEmailUtils.saveFile(part.getFileName(), in, from, organizationId);
					} else if (disposition.equals(Part.INLINE))
					{
						WirelessEmailUtils.saveFile(part.getFileName(), in, from, organizationId);
					} else
					{
						WirelessEmailUtils.saveFile(subject, in, from, organizationId);
					}
					if (in != null)
					{
						try
						{
							in.close();
						} catch (IOException io)
						{
							Log.error(WirelessEmailUtils.class, " error Closing InputStream" + io.getMessage());
							io.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e)
		{
			Log.error(WirelessEmailUtils.class, e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		System.out.println("getting emails");
		ApprovalsByEmail retrieve = new ApprovalsByEmail();

		// retrieve.getEmails("test");

		System.out.println("\tdone getting emails");
		System.out.println("done getting emails");
	}

	public String getProcessXml()
	{
		return processXml;
	}

	public void setProcessXml(String processXml)
	{
		this.processXml = processXml;
	}

	public int getINumMessagesReceived()
	{
		return iNumMessagesReceived;
	}
}

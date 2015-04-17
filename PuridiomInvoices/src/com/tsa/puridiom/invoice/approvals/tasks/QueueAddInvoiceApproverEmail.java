package com.tsa.puridiom.invoice.approvals.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddInvoiceApproverEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	String organizationId = (String) incomingRequest.get("organizationId");
            String newStatus = (String) incomingRequest.get("newStatus");
            String nextUser = (String) incomingRequest.get("forwardedTo");
            String approverPool = HiltonUtility.ckNull((String) incomingRequest.get("approverPool"));
            InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");

            if (invoiceHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " Invoice could not be forwarded for Approval!");
            }
            Log.debug(this, "Writting send_queue record for invoice: " + invoiceHeader.getInvoiceNumber() + ", status " + newStatus);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");

            if (newStatus.compareTo(DocumentStatus.IVC_APPROVING) >= 0 && newStatus.compareTo(DocumentStatus.IVC_APPROVED) < 0 && !Utility.isEmpty(nextUser))
            {
            	String message = "";
                if(incomingRequest.get("docCommentList") != null){
                	List commentList = (List)incomingRequest.get("docCommentList");
                	Iterator it = commentList.iterator() ;
                	while(it.hasNext()){
                		if(HiltonUtility.isEmpty(message)){
                			message += "COMMENTS:\n";
                		}
                		DocComment  docComment = (DocComment) it.next() ;
                		message += docComment.getCommentTitle() +"\n\t\t"+docComment.getDocText().getStdText()+"\n";
                	}

                }

            	if (approverPool.equalsIgnoreCase("Y"))
            	{
            		incomingRequest.put("AppPooluser_poolid", nextUser);
            		AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
	                List poolUsersList = (List)task.executeTask(incomingRequest);

	                for (int i = 0; i < poolUsersList.size(); i++)
	                {
	                	AppPooluser poolUser = (AppPooluser) poolUsersList.get(i);
	                    nextUser = poolUser.getComp_id().getUserId();
	                    UserProfile pUser = UserManager.getInstance().getUser(organizationId, nextUser);

	                    if (pUser.getStatus().equals("02"))
	                    {
	                    	incomingRequest.put("SendQueue_doctype", "IVC");
	                        incomingRequest.put("SendQueue_docic", (String) incomingRequest.get("InvoiceHeader_icIvcHeader"));
	                        String owner = (String) incomingRequest.get("userId");
	                        StringBuffer subject = new StringBuffer("");
	                        subject.append("Invoice #" + invoiceHeader.getInvoiceNumber());
	                        if (organizationId.equalsIgnoreCase("ctb08p")) {
	                        	subject.append(", PO/Contract " + invoiceHeader.getPoNumber()) ;
	                        	subject.append(", Supplier/Contractor " + invoiceHeader.getVendorName()) ;
	                        	if (! HiltonUtility.isEmpty(invoiceHeader.getUdf1Code())) {
	                        		subject.append(", Log " + invoiceHeader.getUdf1Code()) ;
	                        	}
	                        }
	                        subject.append(" submitted for approval");
	                        
	                        String	duplicateNotification = (String) incomingRequest.get("duplicateNotification");
	                        if (!Utility.isEmpty(duplicateNotification) && duplicateNotification.equalsIgnoreCase("Y")) {
	                            subject.append(" [2nd Notice]") ;
	                        }

	                        incomingRequest.put("SendQueue_subject", subject.toString() );
	                        incomingRequest.put("SendQueue_sendfromtype", "E");
	                        incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String) incomingRequest.get("organizationId"), owner).getMailId() );

	                        incomingRequest.put("SendQueue_sendtotype", "E");
	                        incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser((String) incomingRequest.get("organizationId"), nextUser).getMailId() );

	                        incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);
	                        incomingRequest.put("SendQueue_messagetext", message);

	                        process.executeProcess(incomingRequest);

	                    }
	                }
            	}
            	else
            	{
            		incomingRequest.put("SendQueue_doctype", "IVC");
                    incomingRequest.put("SendQueue_docic", (String) incomingRequest.get("InvoiceHeader_icIvcHeader"));
                    String owner = (String) incomingRequest.get("userId");
                    StringBuffer subject = new StringBuffer("");
                    subject.append("Invoice #" + invoiceHeader.getInvoiceNumber());
                    if (organizationId.equalsIgnoreCase("ctb08p")) {
                    	subject.append(", PO/Contract " + invoiceHeader.getPoNumber()) ;
                    	subject.append(", Vendor " + invoiceHeader.getVendorId()) ;
                    	if (! HiltonUtility.isEmpty(invoiceHeader.getUdf1Code())) {
                    		subject.append(", Log " + invoiceHeader.getUdf1Code()) ;
                    	}
                    }
                    subject.append(" submitted for approval");
                    
                    String	duplicateNotification = (String) incomingRequest.get("duplicateNotification");
                    if (!Utility.isEmpty(duplicateNotification) && duplicateNotification.equalsIgnoreCase("Y")) {
                        subject.append(" [2nd Notice]") ;
                    }

                    incomingRequest.put("SendQueue_subject", subject.toString() );
                    incomingRequest.put("SendQueue_sendfromtype", "E");
                    incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String) incomingRequest.get("organizationId"), owner).getMailId() );

                    incomingRequest.put("SendQueue_sendtotype", "E");
                    incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser((String) incomingRequest.get("organizationId"), nextUser).getMailId() );

                    incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);
                    incomingRequest.put("SendQueue_messagetext", message);

                    process.executeProcess(incomingRequest);
            	}
            	this.status = process.getStatus();
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Invoice: " + invoiceHeader.getInvoiceNumber());
            }
        }
        catch (Exception e)
        {
        	this.setStatus(Status.FAILED);
        	throw new TsaException("Invoice Approval Email couldn't be processed.", e);
        }
        return result;
    }
}
package com.tsa.puridiom.approvals;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddReqApproveEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String newStatus = (String)incomingRequest.get("newStatus") ;
            String nextUser = (String)incomingRequest.get("forwardedTo") ;
            String approverType = (String) incomingRequest.get("approverType");
            String requisitionNumber =  (String) incomingRequest.get("RequisitionHeader_requisitionNumber");
            String organizationId = (String)incomingRequest.get("organizationId");
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            String overBudget = HiltonUtility.ckNull((String)incomingRequest.get("overBudget"));
            StringBuffer sendTo = new StringBuffer();
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");

            String requisitionType =  (String) RequisitionType.toString(rqh.getRequisitionType(),organizationId);

            String internalComments = rqh.getInternalComments();

            if(requisitionNumber == null)
            {
                requisitionNumber = rqh.getRequisitionNumber();
                if(requisitionNumber == null)
                {
                    this.setStatus(Status.FAILED);
                    throw new TsaException(this.getName() + " Requisition was not found!");
                }
            }

            if (Utility.isEmpty(approverType)) {
                approverType = "U";
            }

            Log.debug(this, "Writting send_queue record for requisition: " + requisitionNumber + ", status " + newStatus);

            if ((newStatus.equals(DocumentStatus.REQ_APPROVING) || newStatus.equals(DocumentStatus.REQ_PLANNING_APPROVING)) && !Utility.isEmpty(nextUser) && !overBudget.equalsIgnoreCase("[Budget=OverBudget]"))
            {
                incomingRequest.put("SendQueue_doctype", "REQ");
                incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));


                String owner = (String) incomingRequest.get("userId") ;

                StringBuffer subject = new StringBuffer("");
                subject.append(requisitionType + " ");
                subject.append("Requisition ");
                subject.append(requisitionNumber) ;
                subject.append(" submitted for approval") ;

                String	duplicateNotification = (String) incomingRequest.get("duplicateNotification") ;
                if (!Utility.isEmpty(duplicateNotification) && duplicateNotification.equalsIgnoreCase("Y")) {
                    subject.append(" [2nd Notice]") ;
                }

                if(!internalComments.equals("")){
                	subject.append(" - " + internalComments);
                }

                incomingRequest.put("SendQueue_subject",subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                if(Utility.isEmpty(owner))
                {
                	owner = (String) incomingRequest.get("SendQueue_sendfrom") ;
                	incomingRequest.put("SendQueue_sendfrom", owner );
                }
                else
                {
                	incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );
                }

                if (approverType.equals("P") || nextUser.startsWith("@")) {
                    // Retrieve approvers assigned to pool

                    if (nextUser.startsWith("@")) {
                        nextUser = nextUser.substring(1);
                    }

                    incomingRequest.put("AppPooluser_poolid",nextUser) ;
                    AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
                    List poolUsersList = (List)task.executeTask(incomingRequest);

                    if (poolUsersList != null) {
                        for (int ip=0; ip < poolUsersList.size(); ip++) {
                            AppPooluser appPooluser = (AppPooluser) poolUsersList.get(ip);
                            if (appPooluser != null) {
                                String poolUserId = appPooluser.getComp_id().getUserId();
                                sendTo.append(UserManager.getInstance().getUser(organizationId, poolUserId).getMailId() + ";");
                            }
                        }
                    }
                    else {
                    	sendTo.append(PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "AdminEmailAddr", "support@puridiom.com"));
                    	subject.append(" - INVALID POOL **");
                    }
                }

                if (Utility.isEmpty(sendTo.toString())) {
                	this.setSendToEmailFromUser(sendTo, nextUser, organizationId);
                }

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
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", sendTo.toString());
                incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);
                incomingRequest.put("SendQueue_messagetext", message);
                incomingRequest.put("isRequisitionForwardApprovalProcess", "Y");

                process.executeProcess(incomingRequest);
                this.status = process.getStatus() ;
            }
            else if(overBudget.equalsIgnoreCase("[Budget=OverBudget]"))
            {
            	boolean messageText = false;
            	String accountString = "from Account as a where a.id.icHeader = ?" ;

	    		List accountlist = dbs.query(accountString, rqh.getIcReqHeader(), Hibernate.BIG_DECIMAL) ;

			    if(accountlist != null)
			    {
			    	for (int j=0; j< accountlist.size(); j++)
			    	{
			    		Account account = (Account) accountlist.get(j);
			    		String fld4 = account.getFld4();
			    		String balance = "Balance" + j + fld4;
			    		String balanceAmount = HiltonUtility.ckNull((String) incomingRequest.get(balance));
			    		if(!HiltonUtility.isEmpty(balanceAmount))
			    		{
			    			String owner = (String) incomingRequest.get("userId") ;
			    			this.setSendToEmailFromUser(sendTo, nextUser, organizationId);
			    			String  subject = "Over Budget: Please Review CAR " + fld4 + " - (CAR DESCRIPTION)";
			    			String	 message = "\nRequisition #" + rqh.getRequisitionNumber() + " is now in your queue, pending your approval.";
			    			message = message + "\nThis purchase request exceeds the budget for CAR #" + fld4;
			    			message = message + "\n\nCurrent Remaining in Budget for CAR #" + fld4 + " : $" + balanceAmount;
			    			message = message + "\n\nPlease review this CAR and increase budget or reject Requisition.";
			    			incomingRequest.put("SendQueue_doctype", "REQ");
			    			incomingRequest.put("SendQueue_docic", rqh.getIcReqHeader().toString());
			    			incomingRequest.put("SendQueue_subject",subject);
			    			incomingRequest.put("SendQueue_sendfromtype", "E");
			    			incomingRequest.put("SendQueue_sendfrom", HiltonUtility.deleteSendToDuplicates(sendTo.toString()));
			    			incomingRequest.put("SendQueue_sendtotype", "E");
			    			incomingRequest.put("SendQueue_sendto", sendTo.toString());
			    			incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);
			    			incomingRequest.put("SendQueue_messagetext", message);
			    			incomingRequest.put("SendQueue_messagetext2", subject + ";" + message);
			    			incomingRequest.put("SendQueue_owner", owner);
			    			process.executeProcess(incomingRequest);
			    			messageText = true;
			    		}
			    	 }
			    }
	    		if(!messageText)
	    		{
	    			String sqString = "from SendQueue as sq where sq.docic = ?" ;
	    			List sqList = dbs.query(sqString, rqh.getIcReqHeader(), Hibernate.BIG_DECIMAL) ;
	    			if(sqList != null)
				    {
	    				for (int i=0; i< sqList.size(); i++)
	    				{
	    					SendQueue sendqueue = (SendQueue) sqList.get(i);
	    					String messagetext2 = sendqueue.getMessagetext2();
	    					if(!HiltonUtility.isEmpty(messagetext2))
	    					{
		    					String[] subjectMessage = messagetext2.split(";");
		    					String subject = subjectMessage[0];
		    					String message = subjectMessage[1];

		    					if(!HiltonUtility.isEmpty(subject) && !HiltonUtility.isEmpty(message))
		    					{
		    						String owner = (String) incomingRequest.get("userId") ;
		    			    		this.setSendToEmailFromUser(sendTo, nextUser, organizationId);
		    			    		incomingRequest.put("SendQueue_doctype", "REQ");
		    			    		incomingRequest.put("SendQueue_docic", rqh.getIcReqHeader().toString());
		    			    		incomingRequest.put("SendQueue_subject",subject);
		    			    		incomingRequest.put("SendQueue_sendfromtype", "E");
		    			    		incomingRequest.put("SendQueue_sendfrom", HiltonUtility.deleteSendToDuplicates(sendTo.toString()));
		    			    		incomingRequest.put("SendQueue_sendtotype", "E");
		    			    		incomingRequest.put("SendQueue_sendto", sendTo.toString());
		    			    		incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);
		    			    		incomingRequest.put("SendQueue_messagetext", message);
		    			    		incomingRequest.put("SendQueue_owner", owner);
		    			    		process.executeProcess(incomingRequest);
		    			    		messageText = true;
		    			    	}
	    					}
	    				}
	    			}
			    }
	    	}
            else
            {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Req: " + requisitionNumber );
            }
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Error processing HTML approvals.");
        }
        return result;
    }

    private void setSendToEmailFromUser(StringBuffer sendTo, String nextUser, String organizationId) throws Exception
	{
		String[] users = nextUser.split(";");

		for (int i = 0; i < users.length; i++)
		{
			String user = users[i];

			if (!HiltonUtility.isEmpty(user))
			{
				sendTo.append(UserManager.getInstance().getUser(organizationId, user).getMailId() + ";");
			}
		}

	}
}
package com.tsa.puridiom.po.approvals.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddPoApproverEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String newStatus = (String)incomingRequest.get("newStatus") ;
            String nextUser = (String)incomingRequest.get("forwardedTo") ;
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            String internalComments = poHeader.getInternalComments();

            if(poHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " Order could not be forwarded for Approval!");
            }
            Log.debug(this, "Writting send_queue record for requisition: " + poHeader.getDisplayPoNumber() + ", status " + newStatus);

            if(newStatus.equals(DocumentStatus.PO_APPROVING) && !Utility.isEmpty(nextUser))
            {
                incomingRequest.put("SendQueue_doctype", "PO");
                incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("PoHeader_icPoHeader"));
                String owner = (String) incomingRequest.get("userId") ;
                StringBuffer subject = new StringBuffer("");
                subject.append(poHeader.getDisplayPoNumber()) ;
                subject.append(" submitted for approval") ;
                if(!internalComments.equals(""))
                {
                	subject.append(" - " + internalComments ) ;
                }

                String	duplicateNotification = (String) incomingRequest.get("duplicateNotification");
                if (!Utility.isEmpty(duplicateNotification) && duplicateNotification.equalsIgnoreCase("Y")) {
                    subject.append(" [2nd Notice]") ;
                }

                incomingRequest.put("SendQueue_subject", subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                if(Utility.isEmpty(owner))
                {
                	owner = (String) incomingRequest.get("SendQueue_sendfrom") ;
                	incomingRequest.put("SendQueue_sendfrom", owner );
                }
                else
                {
                	incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId());
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
                incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), nextUser).getMailId() );

                incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);
                incomingRequest.put("SendQueue_messagetext", message);

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
                PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
                process.executeProcess(incomingRequest);
                this.status = process.getStatus() ;
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Req: " + poHeader.getDisplayPoNumber() );
            }
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Po Approval Email couldn't be processed.", e);
        }
        return result;
    }
}
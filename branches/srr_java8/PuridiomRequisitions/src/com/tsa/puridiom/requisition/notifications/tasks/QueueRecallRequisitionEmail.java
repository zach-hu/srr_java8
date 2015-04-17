package com.tsa.puridiom.requisition.notifications.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueRecallRequisitionEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        	RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
        	BigDecimal icReqHeader =requisitionHeader.getIcReqHeader();
        	String newStatus = requisitionHeader.getStatus() ;
        	String reqUser = requisitionHeader.getRequisitionerCode() ;
        	String buyerUser = requisitionHeader.getAssignedBuyer() ;
        	/*String reqTotal = "$" + requisitionHeader.getTotal().toString() ;*/
        	String reqTotal = HiltonUtility.getCurrency(requisitionHeader.getTotal(), requisitionHeader.getCurrencyCode(), (String)incomingRequest.get("organizationId"), true);
        	if (reqTotal.indexOf("yen")>0){
        		reqTotal = reqTotal.replaceAll(";", "");
        		reqTotal = reqTotal.replaceAll("&yen", "¥");
        	}
        	String purpose = requisitionHeader.getInternalComments();
        	String nextUser = "";
        	String queryString = "select UserProfile.mailId from ApprovalLog as ApprovalLog, UserProfile as UserProfile where ApprovalLog.id.userId = UserProfile.userId and ApprovalLog.id.icHeader = ? and ApprovalLog.id.userId <> ? ";
			List resultList = dbs.query(queryString, new Object[] {icReqHeader, reqUser } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				for(int x=0; x < resultList.size(); x++ )
				{
					nextUser += (String) resultList.get(x) + "; ";
				}
			}
			this.setStatus(dbs.getStatus()) ;

            nextUser +=	UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), reqUser).getMailId() +
            			"; " +
            			UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), buyerUser).getMailId() ;

            if(requisitionHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " Order could not be forwarded for Approval!");
            }
            Log.debug(this, "Writting send_queue record for requisition: " + requisitionHeader.getRequisitionNumber() + ", status " + newStatus);

            if(newStatus.equals(DocumentStatus.REQ_RECALLED) && !Utility.isEmpty(nextUser))
            {
            	String owner = (String) incomingRequest.get("userId") ;
                StringBuffer subject = new StringBuffer("Requisition ");
                subject.append(requisitionHeader.getRequisitionNumber()) ;
                subject.append(" recalled") ;
                StringBuffer message = new StringBuffer("Requisition ");
                message.append(requisitionHeader.getRequisitionNumber()) ;
                message.append(" was recalled by " + UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getDisplayName() );
                message.append(" on " + Dates.today("") + "\n\n") ;
                message.append("Requisitioner: " + UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), reqUser).getDisplayName() + "\n" ) ;
                message.append("Requisition Total: " + reqTotal + "\n" ) ;
                message.append("Purpose: " + purpose ) ;

            	incomingRequest.put("SendQueue_doctype", "REQ");
                incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));
                incomingRequest.put("SendQueue_subject", subject.toString() );
                incomingRequest.put("SendQueue_messagetext", message.toString() );
                incomingRequest.put("SendQueue_owner", owner );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", nextUser );
                incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
                PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
                process.executeProcess(incomingRequest);
                this.status = process.getStatus() ;
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Req: " + requisitionHeader.getRequisitionNumber() );
            }
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Requisition Approval Email couldn't be processed.", e);
        }
        return result;
    }
}
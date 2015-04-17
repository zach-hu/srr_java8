package com.tsa.puridiom.invoice.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
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

public class QueueRecallInvoiceEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        	InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");
        	BigDecimal icIvcHeader =invoiceHeader.getIcIvcHeader();
        	String newStatus = invoiceHeader.getStatus() ;
        	String invoiceUser = invoiceHeader.getEnteredBy() ;
        	//String buyerUser = invoiceHeader.getAssignedBuyer() ;
        	/*String reqTotal = "$" + requisitionHeader.getTotal().toString() ;*/
        	String invoiceTotal = HiltonUtility.getCurrency(invoiceHeader.getInvoiceTotal(), invoiceHeader.getCurrencyCode(), (String)incomingRequest.get("organizationId"), true);
        	if (invoiceTotal.indexOf("yen")>0){
        		invoiceTotal = invoiceTotal.replaceAll(";", "");
        		invoiceTotal = invoiceTotal.replaceAll("&yen", "¥");
        	}
        	String purpose = invoiceHeader.getInvoiceDesc();
        	String nextUser = "";
        	String queryString = "select UserProfile.mailId from ApprovalLog as ApprovalLog, UserProfile as UserProfile where ApprovalLog.id.userId = UserProfile.userId and ApprovalLog.id.icHeader = ? and ApprovalLog.id.userId <> ? ";
			List resultList = dbs.query(queryString, new Object[] {icIvcHeader, invoiceUser } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				for(int x=0; x < resultList.size(); x++ )
				{
					nextUser += (String) resultList.get(x) + "; ";
				}
			}
			this.setStatus(dbs.getStatus()) ;

            nextUser +=	UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), invoiceUser).getMailId();

            if(invoiceHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " Invoice could not be recalled!");
            }
            
            Log.debug(this, "Writting send_queue record for invoice: " + invoiceHeader.getInvoiceNumber() + ", status " + newStatus);

            if(newStatus.equals(DocumentStatus.IVC_RECALLED) && !Utility.isEmpty(nextUser))
            {
            	String owner = (String) incomingRequest.get("userId") ;
                StringBuffer subject = new StringBuffer("Invoice ");
                subject.append(invoiceHeader.getInvoiceNumber()) ;
                subject.append(" recalled") ;
                StringBuffer message = new StringBuffer("Invoice ");
                message.append(invoiceHeader.getInvoiceNumber()) ;
                message.append(" was recalled by " + UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getDisplayName() );
                message.append(" on " + Dates.today("") + "\n\n") ;
                message.append("Invoice Creator: " + UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), invoiceUser).getDisplayName() + "\n" ) ;
                message.append("Invoice Total: " + invoiceTotal + "\n" ) ;
                message.append("Description: " + purpose ) ;

            	incomingRequest.put("SendQueue_doctype", "IVC");
                incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("InvoiceHeader_icIvcHeader"));
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
                Log.debug(this, "No record will be written for this Invoice: " + invoiceHeader.getInvoiceNumber() );
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
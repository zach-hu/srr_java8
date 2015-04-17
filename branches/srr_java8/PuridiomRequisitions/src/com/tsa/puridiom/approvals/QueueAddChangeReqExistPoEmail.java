package com.tsa.puridiom.approvals;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class QueueAddChangeReqExistPoEmail extends Task
{
	public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

        try
        {
        	PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
            PuridiomProcess process = null;
        	Map incomingRequest = (Map)object;
        	DBSession dbs =(DBSession)incomingRequest.get("dbsession");

        	String organizationId = HiltonUtility.ckNull((String)incomingRequest.get("organizationId"));
        	List rqhList = (List)incomingRequest.get("ChangeReqExistPoList");

            if(rqhList != null && rqhList.size() > 0)
            {
                for (Iterator iterator = rqhList.iterator(); iterator.hasNext();)
                {
                	RequisitionHeader rqh = (RequisitionHeader) iterator.next();
                    BigDecimal icReqHeader = rqh.getIcReqHeader();
                    String sql = "from PoHeader as PoHeader where PoHeader.icReqHeader = ? ";
                    List resultList = dbs.query(sql, new Object[] {icReqHeader} , new Type[] {Hibernate.BIG_DECIMAL});

                    if(resultList != null && resultList.size() > 0)
        			{
                    	PoHeader poHeader = (PoHeader) resultList.get(0);
                        String	requisitionNumber = rqh.getRequisitionNumber();
                        BigDecimal	reqTotal = rqh.getTotal();
                        String	requisitionerCode = rqh.getRequisitionerCode();
                        String	purpose = rqh.getInternalComments();
                        String	poNumber = poHeader.getPoNumber();
                        BigDecimal	poRevNumber = poHeader.getRevisionNumber();
                        String	lastRevision = poHeader.getLastRevision();
                        String messageText = "";
                        messageText = "Requisition Number:" + requisitionNumber + "   Total:" + reqTotal.toString() + "\r\n" +
        							  "Requisitioner:" + requisitionerCode + "\r\n" +
        							  "Purpose:" + purpose + "\r\n" +
        							  "Po Number:" + poNumber + "   Revision #:" + poRevNumber.toString() + "   Last Revision:" + lastRevision;

                        StringBuffer subject = new StringBuffer("");
                        incomingRequest.put("SendQueue_doctype", "REQ");
                        incomingRequest.put("SendQueue_docic", icReqHeader.toString());

                        subject.append("System Alert: Change Request in Approved Status with existing PO.");

                        incomingRequest.put("SendQueue_subject",subject.toString());
                        incomingRequest.put("SendQueue_sendfromtype", "E");
                        incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(organizationId, requisitionerCode).getMailId());
                        incomingRequest.put("SendQueue_sendtotype", "E");
                        incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser(organizationId, requisitionerCode).getMailId());
                        incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);
                        incomingRequest.put("SendQueue_messagetext", messageText);

                        processLoader = new PuridiomProcessLoader();
                        process = processLoader.loadProcess("sendqueue-add.xml");
                        process.executeProcess(incomingRequest);
                        this.status = process.getStatus() ;

                        rqh.setStatus(poHeader.getStatus());
                    	dbs.update(rqh);

                    	sql = "from RequisitionLine as RequisitionLine where RequisitionLine.icReqHeader = ? ";
                        List list = dbs.query(sql, new Object[] {icReqHeader} , new Type[] {Hibernate.BIG_DECIMAL});

                        if(list != null && list.size() > 0)
            			{
                        	RequisitionLine rl = (RequisitionLine) list.get(0);
                        	rl.setStatus(poHeader.getStatus());
                        	dbs.update(rl);
            			}
        			}
	            }
            }
            else
            {
            	Log.debug(this, "No requisitions to autoaward!");
            }

            Log.debug(this, "AUTOAWARD WORKING!");
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	Log.error(this, "RequisitionAutoAwardRequisitionByLine failed: " + e.getMessage());
            this.setStatus(Status.FAILED);
            throw e;
        }
        return ret;
    }
}
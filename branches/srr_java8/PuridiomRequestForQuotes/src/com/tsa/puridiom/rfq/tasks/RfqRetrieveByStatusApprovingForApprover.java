/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class RfqRetrieveByStatusApprovingForApprover extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String rfqStatus = DocumentStatus.RFQ_APPROVING;
		String userId = (String)incomingRequest.get("userId");

        String queryString = "from ApprovalLog as a, RfqHeader as b where b.status = ? and a.id.icHeader = b.icRfqHeader and b.rfqNumber <> 'N/A' and a.callForward = ?  and a.approved = 'A'" ;

		List result = dbs.query(queryString, new Object[] {rfqStatus,userId}, new Type[] {Hibernate.STRING, Hibernate.STRING}) ;

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}

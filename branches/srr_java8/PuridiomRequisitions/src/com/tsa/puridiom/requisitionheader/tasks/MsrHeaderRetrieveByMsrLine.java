/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Administrator
 */
public class MsrHeaderRetrieveByMsrLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        RequisitionLine msrLine = (RequisitionLine)incomingRequest.get("msrLine") ;
        if(msrLine!=null)
        {
        	BigDecimal bdHeader = new BigDecimal(-1) ;
            if (msrLine.getIcReqHeader() != null) {
            	bdHeader = msrLine.getIcReqHeader() ;
            }
            String queryString =  	"from RequisitionHeader as b where b.icReqHeader = ?" ;
            List resultList = dbs.query(queryString,new Object[] {bdHeader},new Type[] {Hibernate.BIG_DECIMAL}) ;

            if (resultList != null && resultList.size() > 0)
    		{
    			result = resultList.get(0);
    		}

        }
        this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}

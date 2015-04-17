/*
 * Created on July 14, 2004
 */
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Administrator 
 */
public class DisbLineRetrieveByLineNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        String icHeader = (String)incomingRequest.get("DisbLine_icDsbHeader") ;
        //String lineNumber = (String)incomingRequest.get("DisbLine_lineNumber") ;
        String lineNumber = (String) incomingRequest.get("lineToRetrieve");
		BigDecimal bdHeader = new BigDecimal(icHeader) ;
		BigDecimal bdNumber = new BigDecimal(lineNumber) ;
		
        String queryString =  	"select d from DisbLine as d where d.icDsbHeader = ? AND d.lineNumber = ?" ;
        
        List resultList = dbs.query(queryString,	
				new Object[] {bdHeader, bdNumber},
				new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;
        
        if (resultList != null && resultList.size() > 0)
		{
			result = resultList.get(0);
		}
					
		this.setStatus(dbs.getStatus()) ;
					
		return result ;
	}
	
}

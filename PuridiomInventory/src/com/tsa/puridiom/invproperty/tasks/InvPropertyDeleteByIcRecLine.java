/*
 * Created on Aug 26, 2005
 */
package com.tsa.puridiom.invproperty.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author
 */
public class InvPropertyDeleteByIcRecLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        Object tempIcObj = incomingRequest.get("InvProperty_icRecLine");
        String	tempIc = "";

        if (tempIcObj instanceof String[]) {
    	    String	tempIcArray[] = (String[]) tempIcObj;
    	    tempIc = (String) tempIcArray[0];
    	} else if(tempIcObj != null){
    	    tempIc = (String) tempIcObj;
    	} else {
    		tempIc = "-1";
    	}
    		
		BigDecimal bdTempIc = new BigDecimal(tempIc) ;

        String queryString = "from InvProperty as InvProperty where " +
        	"InvProperty.icRecLine = ?" ;

		int retval = dbs.delete(queryString, bdTempIc, Hibernate.BIG_DECIMAL) ;

		this.setStatus(retval) ;

		return object  ;
	}
}

/*
 * Created on Aug 26, 2005
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Kelli
 */
public class InvPropertyDeleteItemByTempIc extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        Object itemNumberObj = incomingRequest.get("InvItem_itemNumber");
        Object tempIcObj = incomingRequest.get("InvProperty_tempIc");
        String	itemNumber = "";
        String	tempIc = "";

    	if (itemNumberObj instanceof String[]) {
    	    String	itemNumberArray[] = (String[]) itemNumberObj;
    	    itemNumber = (String) itemNumberArray[0];
    	} else {
    	    itemNumber = (String) itemNumberObj;
    	}
        if (tempIcObj instanceof String[]) {
    	    String	tempIcArray[] = (String[]) tempIcObj;
    	    tempIc = (String) tempIcArray[0];
    	} else {
    	    tempIc = (String) tempIcObj;
    	}
		BigDecimal bdTempIc = new BigDecimal(tempIc) ;

        String queryString = "from Invproperty as InvProperty where " +
        	"InvBinLocation.tempIc = ? and " +
        	"InvBinLocation.itemNumber = '" + itemNumber + "'" ;

		int retval = dbs.delete(queryString, bdTempIc, Hibernate.BIG_DECIMAL) ;

		this.setStatus(retval) ;

		return object  ;
	}
}

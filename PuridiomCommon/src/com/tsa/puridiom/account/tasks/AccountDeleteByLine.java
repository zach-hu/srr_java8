/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

import java.util.*;
import java.math.*;
import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Administrator
 */
public class AccountDeleteByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession");

        String icHeader = (String)incomingRequest.get("Account_icHeader");
		String icLine = (String)incomingRequest.get("Account_icLine") ;
		
		if (icLine == null)
		{
			icLine = "0";
		}
		
		BigDecimal bdHeader = new BigDecimal(icHeader);
		BigDecimal bdLine = new BigDecimal(icLine);

        String queryString = "from Account as a where a.id.icHeader = ? AND a.id.icLine = ?";

        String referenceType = Utility.ckNull((String)incomingRequest.get("referenceType"));
        if (referenceType.equalsIgnoreCase("IV"))
        {
        	String accountType = Utility.ckNull((String)incomingRequest.get("accountType"));
        	accountType = referenceType + accountType;
        	queryString = "from Account as a where a.id.icHeader = ? AND a.id.icLine = ? AND a.accountType = ?";

        	this.setStatus(dbs.delete(queryString,
					new Object[] {bdHeader, bdLine, accountType},
					new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL, Hibernate.STRING})) ;
        }
        else
        {
        	this.setStatus(dbs.delete(queryString,
					new Object[] {bdHeader, bdLine},
					new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL})) ;
        }

		return object ;
	}
}

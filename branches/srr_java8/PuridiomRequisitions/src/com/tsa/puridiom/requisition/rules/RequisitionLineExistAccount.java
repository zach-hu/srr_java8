/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisition.rules;

import java.math.BigDecimal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Administrator
 */
public class RequisitionLineExistAccount extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		String existAccountLine = "succeeded";

		try
        {
	    	// line accounts
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");
			List existAccount = header.getAccountList();

			if (!header.getRequisitionType().equalsIgnoreCase("N")) // Accounts are NOT required on Pricing Requests
			{
				if ((existAccount == null) || (existAccount.isEmpty()))
				{
					if ((lineItems != null) && (!lineItems.isEmpty()))
					{
						for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
						{
							RequisitionLine reqLine = (RequisitionLine) iterator.next();

							List existLineAccount = reqLine.getAccountList();

							if(existLineAccount == null || (existLineAccount.isEmpty()))
							{
								existAccountLine = "failed";
							}
						}
					}
				}
			}
	        incomingRequest.put("existAccountEachLine", existAccountLine);
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at AccountExistRules", e);
		}
		return null;
	}

}

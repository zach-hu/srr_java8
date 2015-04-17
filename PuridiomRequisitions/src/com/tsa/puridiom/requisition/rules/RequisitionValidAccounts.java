/**
 * 
 */
package com.tsa.puridiom.requisition.rules;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class RequisitionValidAccounts extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");

			this.validGlAccountForItRequisition(header, lineItems, incomingRequest);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "RequisitionValidAccounts error " + e.getMessage());

			throw e;
		}

		return result;
	}

	public void validGlAccountForItRequisition(RequisitionHeader header, List lineItems, Map incomingRequest)
	{
		boolean validGLAccount = true;
		boolean validationRequisitionLine = false;
		if(incomingRequest.containsKey("valType") && ((String)incomingRequest.get("valType")).equals("REQUISITIONLINE"))
		{
			validationRequisitionLine = true;
		}
			/* Valid GL Account for IT Requisition */
		if (header.getRequisitionType().equalsIgnoreCase("H"))
		{
			if(!validationRequisitionLine)
			{
				validGLAccount = this.isValidGLAccount(header.getAccountList());
			}
			if (validGLAccount && (lineItems != null) && (!lineItems.isEmpty()))
			{
				for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
				{
					RequisitionLine reqLine = (RequisitionLine) iterator.next();

					validGLAccount = this.isValidGLAccount(reqLine.getAccountList());

					if (!validGLAccount)
					{
						break;
					}
				}
			}
		}

		incomingRequest.put("validGLAccount", String.valueOf(validGLAccount));
	}

	private boolean isValidGLAccount(List accounts)
	{
		boolean validGLAccount = true;

		if ((accounts != null) && (!accounts.isEmpty()))
		{
			for (Iterator iterator = accounts.iterator(); iterator.hasNext();)
			{
				Account account = (Account) iterator.next();

				if (!(account.getFld1().equals("17135") || account.getFld1().equals("64901") || account.getFld1().equals("64424") || account.getFld1().equals("65808")))
				{
					validGLAccount = false;
					break;
				}
			}
		}

		return validGLAccount;
	}

}

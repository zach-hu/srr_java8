/**
 * 
 */
package com.tsa.puridiom.rules;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johann
 */
public class ValidPoAccountsRules extends Task
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
			PoHeader header = (PoHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");

			this.validGlAccountForItPo(header, lineItems, incomingRequest);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "ValidAccounts error " + e.getMessage());

			throw e;
		}

		return result;
	}

	public void validGlAccountForItPo(PoHeader header, List lineItems, Map incomingRequest)
	{
		/* Valid GL Account for IT Requisition */
		String validGLAccount = this.isValidGLAccount(header, header.getAccountList(), incomingRequest);

		if (validGLAccount.equalsIgnoreCase("Y") && (lineItems != null) && (!lineItems.isEmpty()))
		{
			for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
			{
				PoLine reqLine = (PoLine) iterator.next();

				validGLAccount = this.isValidGLAccount(header, reqLine.getAccountList(), incomingRequest);
			}
		}
	}

	private String isValidGLAccount(PoHeader header, List accounts, Map incomingRequest)
	{
		String flagAccount = "Y";
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		if ((accounts != null) && (!accounts.isEmpty()))
		{
			for (Iterator iterator = accounts.iterator(); iterator.hasNext();)
			{
				Account account = (Account) iterator.next();
				String accountFld1 = account.getFld1();
				if (this.isNumeric(accountFld1))
				{
					int fld1=Integer.parseInt(accountFld1.trim());
					if (accountFld1.startsWith("4") || accountFld1.startsWith("5"))
					{
						if(HiltonUtility.isEmpty(account.getFld4()))
						{
							incomingRequest.put("validAccountFld4", "Failed");
							flagAccount = "N";
						}
					}
					else if(!HiltonUtility.isEmpty(account.getFld4()))
					{
						incomingRequest.put("validAccountFld4", "Failed");
						flagAccount = "N";
					}
					if (accountFld1.startsWith("40") || accountFld1.startsWith("45") || accountFld1.startsWith("46") || (fld1 >= 40000 && fld1 <= 44999) || accountFld1.startsWith("50") || accountFld1.startsWith("55") || accountFld1.startsWith("56") || (fld1 >= 50000 && fld1 <= 54999))
					{
						if(HiltonUtility.isEmpty(account.getFld5()))
						{
							incomingRequest.put("validAccountFld5", "Failed");
							flagAccount = "N";
						}
					}
					if (accountFld1.startsWith("40") || accountFld1.startsWith("41") || accountFld1.startsWith("42") || accountFld1.startsWith("43") || accountFld1.startsWith("44") || accountFld1.startsWith("50") || accountFld1.startsWith("51") || accountFld1.startsWith("52") || accountFld1.startsWith("53") || accountFld1.startsWith("54"))
					{
						if(HiltonUtility.isEmpty(account.getFld6()))
						{
							incomingRequest.put("validAccountFld6", "Failed");
							flagAccount = "N";
						}
						if(HiltonUtility.isEmpty(account.getFld7()))
						{
							incomingRequest.put("validAccountFld7", "Failed");
							flagAccount = "N";
						}
					}
					else
					{
						if(!HiltonUtility.isEmpty(account.getFld6()))
						{
							incomingRequest.put("validAccountFld6", "Failed");
							flagAccount = "N";
						}
						if(!HiltonUtility.isEmpty(account.getFld7()))
						{
							incomingRequest.put("validAccountFld7", "Failed");
							flagAccount = "N";
						}
					}
					if (accountFld1.equalsIgnoreCase("151990"))
					{
						if(HiltonUtility.isEmpty(account.getFld8()))
						{
							incomingRequest.put("validAccountFld8", "Failed");
							flagAccount = "N";
						}
					}
					
					String udf10Code = header.getUdf10Code(); 
					if (udf10Code != null && (udf10Code.equals("90") || udf10Code.equals("91") || udf10Code.equals("95"))) {
						if (!accountFld1.equalsIgnoreCase("139400") || !account.getFld3().equalsIgnoreCase("CC")) {
							incomingRequest.put("validAccountUdf10", "Failed");
							flagAccount = "N";
						}
					}
					
					if(!HiltonUtility.isEmpty(account.getFld5()))
					{
						try
						{
							BigDecimal Fld1 = new BigDecimal(accountFld1);
							String Fld5 = account.getFld5();
							String queryString = "from XrefCombo as XrefCombo where XrefCombo.code1 = ? and XrefCombo.xrefAmt = ?";
							List resultList = dbs.query(queryString, new Object[] { Fld5, Fld1 }, new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL });
							if (resultList == null || resultList.size() < 1)
							{
								incomingRequest.put("validXrefComboAccountFld5", "Failed");
							}
							this.setStatus(Status.SUCCEEDED);

						}
						catch (Exception e)
						{
							this.setStatus(Status.FAILED);
							Log.error(this, "XrefComboRetrieveFromCatalogItem error " + e.getMessage());
							e.printStackTrace();
						}
					}
				}
			}
		}
		return flagAccount;
	}
	
	private static boolean isNumeric(String cadena)
	{
	   try {
		   Integer.parseInt(cadena);
		   return true;
	   } 
	   catch (NumberFormatException nfe)
	   {
		   return false;
	   }
	   
	}

}

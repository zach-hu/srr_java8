/*
 * Created on Nov 17, 2004 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.noncapital.tasks;

import java.util.List;
import java.util.Map;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class ValidationCmodCombination extends Task
{

	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");

			List lineList = (List) reqHeader.getRequisitionLineList();
			String accountValid = "Y";
			
			if (lineList != null && lineList.size() > 0)
			{
				for (int index = 0; index < lineList.size(); index++)
				{
					RequisitionLine requisitionLine = (RequisitionLine) lineList.get(index);
					
					List accountList = (List) requisitionLine.getAccountList();
					if (accountList != null && accountList.size() > 0)
					{
						for (int i = 0; i < accountList.size(); i++)
						{
							Account account = (Account) accountList.get(i);
							String accountFld3 = account.getFld3();

							if (! accountFld3.equalsIgnoreCase(""))
							{
								try
								{
									DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
									String sqlWhere = getSqlWhereAccountFld(accountFld3);
									String queryString = "from XrefCombo as XrefCombo " + sqlWhere + ";";
									
									List result = dbs.query(queryString) ;
									
									if(result!= null && result.size() == 0){								
										accountValid = "F";	
										break;
									}

									this.setStatus(dbs.getStatus()) ;
								}
								catch (Exception e)
								{
									this.setStatus(Status.FAILED);
									throw new TsaException("XrefCombo could not be retrieved");
								}
							}
							
						}
					}
				}
			}
			
			incomingRequest.put("isValidAccountCmod", accountValid);
			
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
	
	private String getSqlWhereAccountFld(String accountFld)
	{
		String sqlWhere = "Where 1 = 0 OR XrefCombo.code3 = '*' OR " + "XrefCombo.code3 = '" + String.valueOf(accountFld.charAt(0)) + "'";
				
		for (int index = 1; index < accountFld.length(); index++)
		{
			sqlWhere = sqlWhere + " OR XrefCombo.code3 = '" + accountFld.substring(0,index) + "*' ";			
		}
		
		return  sqlWhere;

	}

}

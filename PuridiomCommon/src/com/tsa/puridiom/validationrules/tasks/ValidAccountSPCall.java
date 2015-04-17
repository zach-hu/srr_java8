package com.tsa.puridiom.validationrules.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.XrefComboBean;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ValidAccountSPCall extends Task
{
	
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			Object header = incomingRequest.get("header");
			String formType = getFormType(header);
			String[] comboResult = new String[2];
			comboResult[0]="";
			comboResult[1]="";
			List accountList = (List)incomingRequest.get("accountLineList");
			List headerAccounts = new ArrayList();
			headerAccounts = (List) incomingRequest.get("accounts");
			boolean validationRequisitionLine = false;
			if(incomingRequest.containsKey("valType") && (HiltonUtility.ckNull((String)incomingRequest.get("valType"))).equals("REQUISITIONLINE"))
			{
				validationRequisitionLine = true;
			}
			if(!validationRequisitionLine)
			{
				if(formType.equals("I")){
					InvoiceHeader invoiceHeader = (InvoiceHeader)header;
					List shipHeaderAccounts = invoiceHeader.getShippingAccountList();
					List otherHeaderAccounts = invoiceHeader.getOtherAccountList();
					List taxHeaderAccounts = invoiceHeader.getTaxAccountList();
					List useTaxHeaderAccounts = invoiceHeader.getUseTaxAccountList();
					if(shipHeaderAccounts != null && shipHeaderAccounts.size()>0){
						headerAccounts.addAll(shipHeaderAccounts);
					}
					if(otherHeaderAccounts != null && otherHeaderAccounts.size()>0){
						headerAccounts.addAll(otherHeaderAccounts);
					}
					if(taxHeaderAccounts != null && taxHeaderAccounts.size()>0){
						headerAccounts.addAll(taxHeaderAccounts);
					}
					if(useTaxHeaderAccounts != null && useTaxHeaderAccounts.size()>0){
						headerAccounts.addAll(useTaxHeaderAccounts);
					}
					invoiceHeader.setAccountList(headerAccounts);
				}
				if(headerAccounts != null && headerAccounts.size() > 0 ){
					accountList.add(headerAccounts);
					
				}
			}
			incomingRequest.put("validAccountCombo", "Y");
			if (accountList == null || accountList.size() == 0)
			{
				accountList = (List<Account>)incomingRequest.get("accounts");
			}

			for (int i = 0; i < accountList.size(); i++)
			{
				if (comboResult[0].equalsIgnoreCase("N")) {
					incomingRequest.put("validAccountCombo", "N");
					incomingRequest.put("validAccountComboDescription", HiltonUtility.ckNull(comboResult[1]));
					break;
				}

				if (accountList.get(i) instanceof ArrayList)
				{
					List innerAccountList = (List)accountList.get(i);
					for (int ix = 0; ix < innerAccountList.size(); ix++)
					{
						Account account = (Account)innerAccountList.get(ix);
						comboResult = getResult(dbs,account, formType);
						if (!HiltonUtility.isEmpty(comboResult[0]))
						{
							if (comboResult[0].equals("Y")) {
								continue;
							} else {
								incomingRequest.put("validAccountCombo", "N");
								incomingRequest.put("validAccountComboDescription", HiltonUtility.ckNull(comboResult[1]));
								break;
							}
						}
					}
				}
				else
				{
					Account account = (Account)accountList.get(i);
					comboResult = getResult(dbs,account, formType);
					if (!HiltonUtility.isEmpty(comboResult[0]))
					{
						if (comboResult[0].equals("Y")) {
							continue;
						} else {
							incomingRequest.put("validAccountCombo", "N");
							incomingRequest.put("validAccountComboDescription", HiltonUtility.ckNull(comboResult[1]));
							break;
						}
					}
				}
			}

			if (comboResult[0].equalsIgnoreCase("Y")) {
				incomingRequest.put("validAccountCombo", "Y");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

	public String getFormType(Object header)
	{
		String formType = "";
		if (header instanceof RequisitionHeader)
		{
			if ("M".equals(((RequisitionHeader) header).getRequisitionType())) {
				formType = "M";
			} else {
				formType = "R";
			}
		}
		else if (header instanceof PoHeader)
		{
			formType = "P";
		}
		else if (header instanceof InvoiceHeader)
		{
			formType = "I";
		}
		return formType;
	}

	public String[] getResult(DBSession dbs, Account account, String formType)
	{
		
		String resultValue = "";
		String resultDescription = "";
		String[] result = new String[2];
		String query = "psp_XrefCombo";
		Query q;

		String ac01 = HiltonUtility.ckNull(account.getFld1());
		String ac02 = HiltonUtility.ckNull(account.getFld2());
		String ac03 = HiltonUtility.ckNull(account.getFld3());
		q = setupStoreProcedureCall(dbs, query, ac01, ac02, ac03, formType);
		List<XrefComboBean> resultList = q.list();

		if (resultList != null && resultList.size() > 0)
		{
			resultValue = resultList.get(0).getCODE_1();
			resultDescription = resultList.get(0).getDESCRIPTION();
		}
		result[0]=resultValue;
		result[1]=resultDescription;
		return result;
	}

	public Query setupStoreProcedureCall(DBSession dbs, String q, String ac01, String ac02, String ac03, String formType) throws HibernateException
	{
		Query query = dbs.getSession().getNamedQuery(q);
		query.setParameter("p0", "",Hibernate.STRING);
		query.setParameter("p1", "",Hibernate.STRING);
		query.setParameter("p2", ac01,Hibernate.STRING);
		query.setParameter("p3", ac02, Hibernate.STRING);
		query.setParameter("p4", ac03,Hibernate.STRING);
		query.setParameter("p5", formType,Hibernate.STRING);
		query.setParameter("p6", "",Hibernate.STRING);
		query.setParameter("p7", "",Hibernate.STRING);
		query.setParameter("p8", "",Hibernate.STRING);
		query.setParameter("p9", "",Hibernate.STRING);
		query.setParameter("p10", "",Hibernate.STRING);
		query.setParameter("pd", "",Hibernate.STRING);
		query.setParameter("pu", "",Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(com.tsa.puridiom.entity.XrefComboBean.class));
		return query;
	}

}

package com.tsa.puridiom.punchout.tasks;

import com.tsa.puridiom.entity.Punchout;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class PunchoutUpdate extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		boolean	error	= false;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			BigDecimal var1 = new BigDecimal((String) incomingRequest.get("icPunchout"));

			String queryString = "from Punchout as Punchout where icPunchout = ? ";
			List resultList = dbs.query(queryString, new Object[] {var1} , new Type[] {Hibernate.BIG_DECIMAL});
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus());

			Object obj = resultList.get(0);
			Punchout punchout = (Punchout) obj;

			String variableOut;
			String variableIn;

			// Update FromDomain
			variableOut = punchout.getFromDomain();
			variableIn = (String) incomingRequest.get("Punchout_FromD");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFromDomain(variableIn);
				}
				else
					error = true;
			else
				punchout.setFromDomain(variableIn);

			// Update FromIdentity
			variableOut = punchout.getFromIdentity();
			variableIn = (String) incomingRequest.get("Punchout_FromID");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFromIdentity(variableIn);
				}
				else
					error = true;
			else
				punchout.setFromIdentity(variableIn);

			// Update ToDomain
			variableOut = punchout.getToDomain();
			variableIn = (String) incomingRequest.get("Punchout_ToD");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setToDomain(variableIn);
				}
				else
					error = true;
			else
				punchout.setToDomain(variableIn);

			// Update ToIdentity
			variableOut = punchout.getToIdentity();
			variableIn = (String) incomingRequest.get("Punchout_ToID");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setToIdentity(variableIn);
				}
				else
					error = true;
			else
				punchout.setToIdentity(variableIn);

			// Update SenderDomain
			variableOut = punchout.getSenderDomain();
			variableIn = (String) incomingRequest.get("Punchout_SenderD");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setSenderDomain(variableIn);
				}
				else
					error = true;
			else
				punchout.setSenderDomain(variableIn);

			// Update SenderIdentity
			variableOut = punchout.getSenderIdentity();
			variableIn = (String) incomingRequest.get("Punchout_SenderID");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setSenderIdentity(variableIn);
				}
				else
					error = true;
			else
				punchout.setSenderIdentity(variableIn);

			// Update SenderSecret
			variableOut = punchout.getSenderSecret();
			variableIn = (String) incomingRequest.get("Punchout_SenderSecret");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setSenderSecret(variableIn);
				}
				else
					error = true;
			else
				punchout.setSenderSecret(variableIn);

			// Update SenderAgent
			variableOut = punchout.getSenderAgent();
			variableIn = (String) incomingRequest.get("Punchout_SenderAgent");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setSenderAgent(variableIn);
				}
				else
					error = true;
			else
				punchout.setSenderAgent(variableIn);

			// Update Field 1
			variableOut = punchout.getFld1();
			variableIn = (String) incomingRequest.get("Punchout_FLD1");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
			if (variableOut.equals(variableIn)){}
			else
				punchout.setFld1(variableIn);
			}
			else
				error = true;
			else
				punchout.setFld1(variableIn);

			// Update Field 2
			variableOut = punchout.getFld2();
			variableIn = (String) incomingRequest.get("Punchout_FLD2");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld2(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld2(variableIn);

			// Update Field 3
			variableOut = punchout.getFld3();
			variableIn = (String) incomingRequest.get("Punchout_FLD3");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld3(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld3(variableIn);

			// Update Field 4
			variableOut = punchout.getFld4();
			variableIn = (String) incomingRequest.get("Punchout_FLD4");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld4(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld4(variableIn);

			// Update Field 5
			variableOut = punchout.getFld5();
			variableIn = (String) incomingRequest.get("Punchout_FLD5");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld5(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld5(variableIn);

			// Update Field 6
			variableOut = punchout.getFld6();
			variableIn = (String) incomingRequest.get("Punchout_FLD6");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld6(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld6(variableIn);

			// Update Field 7
			variableOut = punchout.getFld7();
			variableIn = (String) incomingRequest.get("Punchout_FLD7");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld7(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld7(variableIn);

			// Update Field 8
			variableOut = punchout.getFld8();
			variableIn = (String) incomingRequest.get("Punchout_FLD8");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld8(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld8(variableIn);

			// Update Field 9
			variableOut = punchout.getFld9();
			variableIn = (String) incomingRequest.get("Punchout_FLD9");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld9(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld9(variableIn);

			// Update Field 10
			variableOut = punchout.getFld10();
			variableIn = (String) incomingRequest.get("Punchout_FLD10");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld10(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld10(variableIn);

			// Update Field 11
			variableOut = punchout.getFld11();
			variableIn = (String) incomingRequest.get("Punchout_FLD11");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld11(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld11(variableIn);

			// Update Field 12
			variableOut = punchout.getFld12();
			variableIn = (String) incomingRequest.get("Punchout_FLD12");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld12(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld12(variableIn);

			// Update Field 13
			variableOut = punchout.getFld13();
			variableIn = (String) incomingRequest.get("Punchout_FLD13");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld13(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld13(variableIn);

			// Update Field 14
			variableOut = punchout.getFld14();
			variableIn = (String) incomingRequest.get("Punchout_FLD14");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld14(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld14(variableIn);

			// Update Field 15
			variableOut = punchout.getFld15();
			variableIn = (String) incomingRequest.get("Punchout_FLD15");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setFld15(variableIn);
				}
				else
					error = true;
			else
				punchout.setFld15(variableIn);

			// Update Auxiliary
			variableOut = punchout.getAuxiliary();
			variableIn = (String) incomingRequest.get("Punchout_Aux");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setAuxiliary(variableIn);
				}
				else
					error = true;
			else
				punchout.setAuxiliary(variableIn);

			// Update ShipTo
			variableOut = punchout.getShipTo();
			variableIn = (String) incomingRequest.get("Punchout_ShipTo");
			if (variableOut.equals(compare(variableIn))){}
			else
				punchout.setShipTo(compare(variableIn));

			// Update URL
			variableOut = punchout.getUrl();
			variableIn = (String) incomingRequest.get("Punchout_URL");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setUrl(variableIn);
				}
				else
					error = true;
			else
				punchout.setUrl(variableIn);

			// Update DefaultEmail
			variableOut = punchout.getDefaultEmail();
			variableIn = (String) incomingRequest.get("Punchout_DefaultEmail");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setDefaultEmail(variableIn);
				}
				else
					error = true;
			else
				punchout.setDefaultEmail(variableIn);

			// Update GeneralInfo
			variableOut = punchout.getGeneralInfo();
			variableIn = (String) incomingRequest.get("Punchout_GeneralInfo");
			if (variableOut.equals(compare(variableIn))){}
			else
				punchout.setGeneralInfo(compare(variableIn));

			// Update ShipToEmail
			variableOut = punchout.getShipToEmail();
			variableIn = (String) incomingRequest.get("Punchout_ShipToEmail");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setShipToEmail(variableIn);
				}
				else
					error = true;
			else
				punchout.setShipToEmail(variableIn);

			// Update DefaultDate
			variableOut = punchout.getDefaultDate();
			variableIn = (String) incomingRequest.get("Punchout_DefaultDate");
			if(!variableIn.isEmpty())
				if (variableIn.charAt(0) != ';'){
					if (variableOut.equals(variableIn)){}
					else
						punchout.setDefaultDate(variableIn);
				}
				else
					error = true;
			else
				punchout.setDefaultDate(variableIn);

			incomingRequest.put("Typo", error);
			if(!error)
				dbs.update(punchout);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		this.setStatus(Status.SUCCEEDED) ;

		return result;
	}

	private String compare(String s){
		if (s == null)
			s = "n";
		else if (s.equals("on"))
			s = "y";
		else
			s = "n";
		return s;
	}

}
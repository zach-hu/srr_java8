package com.tsa.puridiom.punchout.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Punchout;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class PunchoutSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

        String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

		try
		{
			Punchout punchout = (Punchout) incomingRequest.get("punchout");
			if (punchout == null)
			{
				punchout = new Punchout();
			}

			// Update IcPunchout
			if (incomingRequest.containsKey("punchoutId"))
			{
				String punchoutIdString = (String) incomingRequest.get("punchoutId");
				BigDecimal punchoutId = new BigDecimal (punchoutIdString);
				punchout.setIcPunchout(punchoutId);
			}
			// Update FromDomain
			if (incomingRequest.containsKey("Punchout_FromD"))
			{
				String fromD = (String) incomingRequest.get("Punchout_FromD");
				punchout.setFromDomain(fromD);
			}
			// Update FromIdentity
			if (incomingRequest.containsKey("Punchout_FromID"))
			{
				String fromID = (String) incomingRequest.get("Punchout_FromID");
				punchout.setFromIdentity(fromID);
			}
			// Update ToDomain
			if (incomingRequest.containsKey("Punchout_ToD"))
			{
				String toD = (String) incomingRequest.get("Punchout_ToD");
				punchout.setToDomain(toD);
			}
			// Update ToIdentity
			if (incomingRequest.containsKey("Punchout_ToID"))
			{
				String toID = (String) incomingRequest.get("Punchout_ToID");
				punchout.setToIdentity(toID);
			}
			// Update SenderDomain
			if (incomingRequest.containsKey("Punchout_SenderD"))
			{
				String senderD = (String) incomingRequest.get("Punchout_SenderD");
				punchout.setSenderDomain(senderD);
			}
			// Update SenderIdentity
			if (incomingRequest.containsKey("Punchout_SenderID"))
			{
				String senderID = (String) incomingRequest.get("Punchout_SenderID");
				punchout.setSenderIdentity(senderID);
			}
			// Update SenderSecret
			if (incomingRequest.containsKey("Punchout_SenderSecret"))
			{
				String senderSecret = (String) incomingRequest.get("Punchout_SenderSecret");
				punchout.setSenderSecret(senderSecret);
			}
			// Update SenderAgent
			if (incomingRequest.containsKey("Punchout_SenderAgent"))
			{
				String senderAgent = (String) incomingRequest.get("Punchout_SenderAgent");
				punchout.setSenderAgent(senderAgent);
			}
			// Update Field 1
			if (incomingRequest.containsKey("Punchout_FLD1"))
			{
				String fld1 = (String) incomingRequest.get("Punchout_FLD1");
				punchout.setFld1(fld1);
			}
			// Update Field 2
			if (incomingRequest.containsKey("Punchout_FLD2"))
			{
				String fld2 = (String) incomingRequest.get("Punchout_FLD2");
				punchout.setFld2(fld2);
			}
			// Update Field 3
			if (incomingRequest.containsKey("Punchout_FLD3"))
			{
				String fld3 = (String) incomingRequest.get("Punchout_FLD3");
				punchout.setFld3(fld3);
			}
			// Update Field 4
			if (incomingRequest.containsKey("Punchout_FLD4"))
			{
				String fld4 = (String) incomingRequest.get("Punchout_FLD4");
				punchout.setFld4(fld4);
			}
			// Update Field 5
			if (incomingRequest.containsKey("Punchout_FLD5"))
			{
				String fld5 = (String) incomingRequest.get("Punchout_FLD5");
				punchout.setFld5(fld5);
			}
			// Update Field 6
			if (incomingRequest.containsKey("Punchout_FLD6"))
			{
				String fld6 = (String) incomingRequest.get("Punchout_FLD6");
				punchout.setFld6(fld6);
			}
			// Update Field 7
			if (incomingRequest.containsKey("Punchout_FLD7"))
			{
				String fld7 = (String) incomingRequest.get("Punchout_FLD7");
				punchout.setFld7(fld7);
			}
			// Update Field 8
			if (incomingRequest.containsKey("Punchout_FLD8"))
			{
				String fld8 = (String) incomingRequest.get("Punchout_FLD8");
				punchout.setFld8(fld8);
			}
			// Update Field 9
			if (incomingRequest.containsKey("Punchout_FLD9"))
			{
				String fld9 = (String) incomingRequest.get("Punchout_FLD9");
				punchout.setFld9(fld9);
			}
			// Update Field 10
			if (incomingRequest.containsKey("Punchout_FLD10"))
			{
				String fld10 = (String) incomingRequest.get("Punchout_FLD10");
				punchout.setFld10(fld10);
			}
			// Update Field 11
			if (incomingRequest.containsKey("Punchout_FLD11"))
			{
				String fld11 = (String) incomingRequest.get("Punchout_FLD11");
				punchout.setFld11(fld11);
			}
			// Update Field 12
			if (incomingRequest.containsKey("Punchout_FLD12"))
			{
				String fld12 = (String) incomingRequest.get("Punchout_FLD12");
				punchout.setFld12(fld12);
			}
			// Update Field 13
			if (incomingRequest.containsKey("Punchout_FLD13"))
			{
				String fld13 = (String) incomingRequest.get("Punchout_FLD13");
				punchout.setFld13(fld13);
			}
			// Update Field 14
			if (incomingRequest.containsKey("Punchout_FLD14"))
			{
				String fld14 = (String) incomingRequest.get("Punchout_FLD14");
				punchout.setFld14(fld14);
			}
			// Update Field 15
			if (incomingRequest.containsKey("Punchout_FLD15"))
			{
				String fld15 = (String) incomingRequest.get("Punchout_FLD15");
				punchout.setFld15(fld15);
			}
			// Update Auxiliary
			if (incomingRequest.containsKey("Punchout_Aux"))
			{
				String aux = (String) incomingRequest.get("Punchout_Aux");
				punchout.setAuxiliary(aux);
			}
			// Update ShipTo
			if (incomingRequest.containsKey("Punchout_ShipTo"))
			{
				String shipTo = (String) incomingRequest.get("Punchout_ShipTo");
				if (punchout.getShipTo().equals(compare(shipTo))){}
				else
					punchout.setShipTo(compare(shipTo));
			}
			// Update URL
			if (incomingRequest.containsKey("Punchout_URL"))
			{
				String url = (String) incomingRequest.get("Punchout_URL");
				punchout.setUrl(url);
			}
			// Update DefaultEmail
			if (incomingRequest.containsKey("Punchout_DefaultEmail"))
			{
				String defaultEmail = (String) incomingRequest.get("Punchout_DefaultEmail");
				punchout.setDefaultEmail(defaultEmail);
			}
			// Update GeneralInfo
			if (incomingRequest.containsKey("Punchout_GeneralInfo"))
			{
				String generalInfo = (String) incomingRequest.get("Punchout_GeneralInfo");
				if (punchout.getGeneralInfo().equals(compare(generalInfo))){}
				else
					punchout.setGeneralInfo(compare(generalInfo));
			}
			// Update ShipToEmail
			if (incomingRequest.containsKey("Punchout_ShipToEmail"))
			{
				String shipToEmail = (String) incomingRequest.get("Punchout_ShipToEmail");
				punchout.setShipToEmail(shipToEmail);
			}
			// Update DefaultDate
			if (incomingRequest.containsKey("Punchout_DefaultDate"))
			{
				String defaultDate = (String) incomingRequest.get("Punchout_DefaultDate");
				punchout.setDefaultDate(defaultDate);
			}

			result = punchout;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
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

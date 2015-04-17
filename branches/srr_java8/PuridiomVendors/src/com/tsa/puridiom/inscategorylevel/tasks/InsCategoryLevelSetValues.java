package com.tsa.puridiom.inscategorylevel.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.InsCategoryLevel;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class InsCategoryLevelSetValues extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        String organizationId = (String)incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");
        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

        try
        {
        	InsCategoryLevel insCategoryLevel = (InsCategoryLevel) incomingRequest.get("insCategoryLevel");
            if (insCategoryLevel == null)
            {
            	insCategoryLevel = new InsCategoryLevel();
            }

            if (incomingRequest.containsKey("InsCategoryLevel_icIcl"))
            {
                String icIclString = (String) incomingRequest.get("InsCategoryLevel_icIcl");
                if (Utility.isEmpty(icIclString))
                {
                	icIclString = "0";
                }
                BigDecimal icIcl = new BigDecimal(icIclString);
                insCategoryLevel.setIcIcl(icIcl);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclLevel"))
            {
                String iclLevelString = (String) incomingRequest.get("InsCategoryLevel_iclLevel");
                if (Utility.isEmpty(iclLevelString))
                {
                	iclLevelString = "0";
                }
                BigDecimal iclLevel = new BigDecimal(iclLevelString);
                insCategoryLevel.setIclLevel(iclLevel);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclDescription"))
            {
                String iclDescription = (String) incomingRequest.get("InsCategoryLevel_iclDescription");
                insCategoryLevel.setIclDescription(iclDescription);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclRequired1"))
            {
                String iclRequired1 = (String) incomingRequest.get("InsCategoryLevel_iclRequired1");
                insCategoryLevel.setIclRequired1(iclRequired1);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclMinimum1"))
            {
                String iclMinimum1String = (String) incomingRequest.get("InsCategoryLevel_iclMinimum1");
                if (Utility.isEmpty(iclMinimum1String))
                {
                	iclMinimum1String = "0";
                }
                BigDecimal iclMinimum1 = new BigDecimal(iclMinimum1String);
                insCategoryLevel.setIclMinimum1(iclMinimum1);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclRequired2"))
            {
                String iclRequired2 = (String) incomingRequest.get("InsCategoryLevel_iclRequired2");
                insCategoryLevel.setIclRequired2(iclRequired2);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclMinimum2"))
            {
                String iclMinimum2String = (String) incomingRequest.get("InsCategoryLevel_iclMinimum2");
                if (Utility.isEmpty(iclMinimum2String))
                {
                	iclMinimum2String = "0";
                }
                BigDecimal iclMinimum2 = new BigDecimal(iclMinimum2String);
                insCategoryLevel.setIclMinimum2(iclMinimum2);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclRequired3"))
            {
                String iclRequired3 = (String) incomingRequest.get("InsCategoryLevel_iclRequired3");
                insCategoryLevel.setIclRequired3(iclRequired3);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclMinimum3"))
            {
                String iclMinimum3String = (String) incomingRequest.get("InsCategoryLevel_iclMinimum3");
                if (Utility.isEmpty(iclMinimum3String))
                {
                	iclMinimum3String = "0";
                }
                BigDecimal iclMinimum3 = new BigDecimal(iclMinimum3String);
                insCategoryLevel.setIclMinimum3(iclMinimum3);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclRequired4"))
            {
                String iclRequired4 = (String) incomingRequest.get("InsCategoryLevel_iclRequired4");
                insCategoryLevel.setIclRequired4(iclRequired4);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclMinimum4"))
            {
                String iclMinimum4String = (String) incomingRequest.get("InsCategoryLevel_iclMinimum4");
                if (Utility.isEmpty(iclMinimum4String))
                {
                	iclMinimum4String = "0";
                }
                BigDecimal iclMinimum4 = new BigDecimal(iclMinimum4String);
                insCategoryLevel.setIclMinimum4(iclMinimum4);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclRequired5"))
            {
                String iclRequired5 = (String) incomingRequest.get("InsCategoryLevel_iclRequired5");
                insCategoryLevel.setIclRequired5(iclRequired5);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclMinimum5"))
            {
                String iclMinimum5String = (String) incomingRequest.get("InsCategoryLevel_iclMinimum5");
                if (Utility.isEmpty(iclMinimum5String))
                {
                	iclMinimum5String = "0";
                }
                BigDecimal iclMinimum5 = new BigDecimal(iclMinimum5String);
                insCategoryLevel.setIclMinimum5(iclMinimum5);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclRequired6"))
            {
                String iclRequired6 = (String) incomingRequest.get("InsCategoryLevel_iclRequired6");
                insCategoryLevel.setIclRequired6(iclRequired6);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_iclMinimum6"))
            {
                String iclMinimum6String = (String) incomingRequest.get("InsCategoryLevel_iclMinimum6");
                if (Utility.isEmpty(iclMinimum6String))
                {
                	iclMinimum6String = "0";
                }
                BigDecimal iclMinimum6 = new BigDecimal(iclMinimum6String);
                insCategoryLevel.setIclMinimum6(iclMinimum6);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_status"))
            {
                String status = (String) incomingRequest.get("InsCategoryLevel_status");
                insCategoryLevel.setStatus(status);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_owner"))
            {
                String owner = (String) incomingRequest.get("InsCategoryLevel_owner");
                insCategoryLevel.setOwner(owner);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_dateEntered"))
            {
            	String dateEnteredString = (String) incomingRequest.get("InsCategoryLevel_dateEntered");
            	Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);
				insCategoryLevel.setDateEntered(dateEntered);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_dateExpires"))
            {
            	String dateExpiresString = (String) incomingRequest.get("InsCategoryLevel_dateExpires");
            	Date dateExpires = Dates.getSqlDate(dateExpiresString, userDateFormat);
				insCategoryLevel.setDateExpires(dateExpires);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_lastChgBy"))
            {
                String lastChgBy = (String) incomingRequest.get("InsCategoryLevel_lastChgBy");
                insCategoryLevel.setLastChgBy(lastChgBy);
            }
            if (incomingRequest.containsKey("InsCategoryLevel_lastChgDate"))
            {
            	String lastChgDateString = (String) incomingRequest.get("InsCategoryLevel_lastChgDate");
            	Date lastChgDate = Dates.getSqlDate(lastChgDateString, userDateFormat);
				insCategoryLevel.setLastChgDate(lastChgDate);
            }

            result = insCategoryLevel;
            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}

package com.tsa.puridiom.apprule.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AppRuleSetValuesPK
{
	public void setValues(Map incomingRequest, AppRule apprule ) throws Exception
	{
		try
		{
			String userId = (String ) incomingRequest.get("AppRule_userId");
			String udf1Code = (String ) incomingRequest.get("AppRule_udf1Code");
			String udf2Code = (String ) incomingRequest.get("AppRule_udf2Code");
			String udf3Code = (String ) incomingRequest.get("AppRule_udf3Code");
			String udf4Code = (String ) incomingRequest.get("AppRule_udf4Code");
			String udf5Code = (String ) incomingRequest.get("AppRule_udf5Code");
			String udf6Code = (String ) incomingRequest.get("AppRule_udf6Code");
			String udf7Code = (String ) incomingRequest.get("AppRule_udf7Code");
			AppRulePK comp_id = new AppRulePK();
			comp_id.setUdf1Code(udf1Code);
			comp_id.setUdf2Code(udf2Code);
			comp_id.setUdf3Code(udf3Code);
			comp_id.setUdf4Code(udf4Code);
			comp_id.setUdf5Code(udf5Code);
			comp_id.setUdf6Code(udf6Code);
			comp_id.setUdf7Code(udf7Code);
			comp_id.setUserId(userId);
			apprule.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
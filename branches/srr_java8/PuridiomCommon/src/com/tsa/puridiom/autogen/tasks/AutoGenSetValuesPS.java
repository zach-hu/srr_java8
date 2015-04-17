package com.tsa.puridiom.autogen.tasks;

import com.tsa.puridiom.entity.AutoGen;
import com.tsa.puridiom.entity.AutoGenPK;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AutoGenSetValuesPS extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			AutoGenPK comp_id = new AutoGenPK();
			AutoGen autoGen = (AutoGen) incomingRequest.get("existingAutoGen");
			if (autoGen == null)
			{
				autoGen = new AutoGen();
			}
			
			if (incomingRequest.containsKey("AutoGen_documentType"))
			{
				String documentType = (String ) incomingRequest.get("AutoGen_documentType");
				comp_id.setDocumentType(documentType);
			}
			if (incomingRequest.containsKey("AutoGen_genYear"))
			{
				String genYear = (String ) incomingRequest.get("AutoGen_genYear");
				comp_id.setGenYear(genYear);
			}
			String nextNumberString = (String) incomingRequest.get("AutoGen_nextNumber");
			if (Utility.isEmpty(nextNumberString))
			{
				nextNumberString = "0";
			}
			if (!Utility.isEmpty(autoGen.getNextNumber().toString()))
			{
				nextNumberString = autoGen.getNextNumber().toString();
			}
			BigDecimal nextNumber = new BigDecimal ( nextNumberString );
			autoGen.setNextNumber(nextNumber.add(new BigDecimal (1)));
			if (incomingRequest.containsKey("AutoGen_activeYear"))
			{
				String activeYear = (String ) incomingRequest.get("AutoGen_activeYear");
				autoGen.setActiveYear(activeYear);
			}
			autoGen.setDateEntered(Dates.getSqlDate(""));
			autoGen.setDateExpires(Dates.getSqlDate(""));
			if (incomingRequest.containsKey("AutoGen_status"))
			{
				String status = (String ) incomingRequest.get("AutoGen_status");
				autoGen.setStatus(status);
			}
			if (incomingRequest.containsKey("AutoGen_owner"))
			{
				String owner = (String ) incomingRequest.get("AutoGen_owner");
				autoGen.setOwner(owner);
			}
			if (incomingRequest.containsKey("AutoGen_char1"))
			{
				String char1 = (String ) incomingRequest.get("AutoGen_char1");
				autoGen.setChar1(char1);
			}
			if (incomingRequest.containsKey("AutoGen_char2"))
			{
				String char2 = (String ) incomingRequest.get("AutoGen_char2");
				autoGen.setChar2(char2);
			}
			if (incomingRequest.containsKey("AutoGen_char3"))
			{
				String char3 = (String ) incomingRequest.get("AutoGen_char3");
				autoGen.setChar3(char3);
			}
			if (incomingRequest.containsKey("AutoGen_char4"))
			{
				String char4 = (String ) incomingRequest.get("AutoGen_char4");
				autoGen.setChar4(char4);
			}
			if (incomingRequest.containsKey("AutoGen_rangeMax"))
			{
				String rangeMaxString = (String) incomingRequest.get("AutoGen_rangeMax");
				if (Utility.isEmpty(rangeMaxString))
				{
					rangeMaxString = "0";
				}
				BigDecimal rangeMax = new BigDecimal ( rangeMaxString );
				autoGen.setRangeMax(rangeMax);
			}
			autoGen.setComp_id(comp_id);

			result = autoGen;
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
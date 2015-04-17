package com.tsa.puridiom.autogen.tasks;

import com.tsa.puridiom.entity.AutoGen;
import com.tsa.puridiom.entity.AutoGenPK;
import java.util.Map;

public class AutoGenSetValuesPK
{
	public void setValues(Map incomingRequest, AutoGen autogen ) throws Exception
	{
		try
		{
			String documentType = (String ) incomingRequest.get("AutoGen_documentType");
			String genYear = (String ) incomingRequest.get("AutoGen_genYear");
			AutoGenPK comp_id = new AutoGenPK();
			comp_id.setDocumentType(documentType);
			comp_id.setGenYear(genYear);
			autogen.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
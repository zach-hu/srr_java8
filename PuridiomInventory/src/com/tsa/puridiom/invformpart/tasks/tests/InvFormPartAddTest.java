package com.tsa.puridiom.invformpart.tasks.tests;

import com.tsa.puridiom.entity.InvFormPart;
import com.tsa.puridiom.entity.InvFormPartPK;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class InvFormPartAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invformpart-add.xml");
			Map incomingRequest = new HashMap();
			InvFormPart ifp = new InvFormPart();
			InvFormPartPK comp_id = new InvFormPartPK();

			comp_id.setItemNumber("test");
			comp_id.setFormPart(new BigDecimal(1));
			
			ifp.setComp_id(comp_id);

			incomingRequest.put("invFormPart", ifp);
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
package com.tsa.puridiom.invbinlocation.tasks.tests;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class InvBinLocationAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invbinlocation-add.xml");
			Map incomingRequest = new HashMap();
			InvBinLocation bin = new InvBinLocation();
			UniqueKeyGenerator uk = UniqueKeyGenerator.getInstance();
			bin.setIcRc(new BigDecimal(uk.getUniqueKey().toString()));
			bin.setItemNumber("test");
			bin.setItemLocation("60");
			bin.setAisle("1");
			bin.setQtyOnHand(new BigDecimal(2));
			incomingRequest.put("invBinLocation", bin);
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
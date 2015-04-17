package com.tsa.puridiom.sungard.keyid.tasks.tests;

import com.tsa.puridiom.entity.sungard.KeyId;
import com.tsa.puridiom.entity.sungard.KeyIdPK;
import com.tsa.puridiom.sungard.keyid.tasks.SungardKeyIdUpdate;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class KeyIdUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("SUNGARDEAS");
			SungardKeyIdUpdate test = new SungardKeyIdUpdate();
			Map incomingRequest = new HashMap();
		
			KeyId keyId = new KeyId();
			KeyIdPK keyIdPK = new KeyIdPK();
		
			keyIdPK.setTableName("VENDOR");
			keyIdPK.setKeyRangeLow(new BigDecimal("10407"));
			keyId.setKeyRangeHigh(new BigDecimal("0"));
			keyId.setLastCheckedTo(new BigDecimal("0"));
			
			keyId.setComp_id(keyIdPK);
			
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "SUNGARDEAS");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("keyId", keyId);
		
			keyId = (KeyId) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("KeyIdUpdateTest SUCCESS");
				dbs.commit();
			} else {
			    System.out.println("KeyIdUpdateTest FAILED; DATABASE STATUS: " + dbs.getStatus());
			}
		
			System.out.println(incomingRequest);
			System.out.println("KeyIdUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
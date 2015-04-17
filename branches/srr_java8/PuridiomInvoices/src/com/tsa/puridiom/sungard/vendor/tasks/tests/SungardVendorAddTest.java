package com.tsa.puridiom.sungard.vendor.tasks.tests;

import com.tsa.puridiom.entity.sungard.Vendor;
import com.tsa.puridiom.entity.sungard.VendorPK;
import com.tsa.puridiom.sungard.vendor.tasks.SungardVendorAdd;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SungardVendorAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("SUNGARDEAS");
			SungardVendorAdd test = new SungardVendorAdd();
			Map incomingRequest = new HashMap();

			Vendor vendor = new Vendor();
			VendorPK vendorPK = new VendorPK();

			vendorPK.setVendorId("TEST");
			vendorPK.setInternalVendorId(new BigDecimal("99999"));

			vendor.setComp_id(vendorPK);
			vendor.setAdvicePrint("A");
			vendor.setCertifiedTin("B");
			vendor.setCombineInd("C");
			vendor.setDfltAddrSeqNum(new BigDecimal("1"));
			vendor.setDiscountDays(new BigDecimal("2"));
			vendor.setDiscountInd("D");
			vendor.setDiscPct(new BigDecimal("3"));
			vendor.setHoldInd("E");
			vendor.setIrsCodeId(new BigDecimal("4"));
			vendor.setMinorityGroup("F");
			vendor.setMinorityInd("G");
			vendor.setNetDueDays(new BigDecimal("5"));
			vendor.setSalesTaxPct(new BigDecimal("6"));
			vendor.setSortCode("H");
			vendor.setStatusInd("I");
			vendor.setTakeDiscount("J");
			vendor.setTin("K");
			vendor.setTinType("L");
			vendor.setUseTaxInd("M");
			vendor.setVendorEndDate(Dates.getDate(Dates.today("MM-dd-yyyy", "")));
			vendor.setVendorName1("TEST VENDOR NAME");
			vendor.setVendorStartDate(Dates.getDate(Dates.today("MM-dd-yyyy", "")));
			vendor.setVendorType("N");
			vendor.setWhPct(new BigDecimal("7"));
			vendor.setWhThresholdAmt(new BigDecimal("8"));
			vendor.setWithholdingInd("O");

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "SUNGARDEAS");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("sungardVendor", vendor);

			vendor = (Vendor) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("SungardVendorAddTest SUCCESS");
				dbs.commit();
			}

			System.out.println(incomingRequest);
			System.out.println("SungardVendorAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
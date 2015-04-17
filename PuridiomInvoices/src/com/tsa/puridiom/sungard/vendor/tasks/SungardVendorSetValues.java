package com.tsa.puridiom.sungard.vendor.tasks;

import com.tsa.puridiom.entity.sungard.VendorPK;
import com.tsa.puridiom.entity.sungard.Vendor;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class SungardVendorSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    VendorPK comp_id = new VendorPK();
			Vendor vendor = (Vendor) incomingRequest.get("sungardVendor");
			if (vendor == null)
			{
				vendor = new Vendor();
			}

			if (incomingRequest.containsKey("SungardVendor_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("SungardVendor_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("SungardVendor_internalVendorId"))
			{
			    String internalVendorIdString = (String) incomingRequest.get("SungardVendor_internalVendorId");
				if (Utility.isEmpty(internalVendorIdString))
				{
				    internalVendorIdString = "0";
				}
				BigDecimal internalVendorId = new BigDecimal ( internalVendorIdString );
				comp_id.setInternalVendorId(internalVendorId);
			}
			if (incomingRequest.containsKey("SungardVendor_statusInd"))
			{
				String statusInd = (String) incomingRequest.get("SungardVendor_statusInd");
				vendor.setStatusInd(statusInd);
			}
			if (incomingRequest.containsKey("SungardVendor_vendorType"))
			{
				String vendorType = (String) incomingRequest.get("SungardVendor_vendorType");
				vendor.setVendorType(vendorType);
			}
			if (incomingRequest.containsKey("SungardVendor_tin"))
			{
				String tin = (String) incomingRequest.get("SungardVendor_tin");
				vendor.setTin(tin);
			}
			if (incomingRequest.containsKey("SungardVendor_tinType"))
			{
				String tinType = (String) incomingRequest.get("SungardVendor_tinType");
				vendor.setTinType(tinType);
			}
			if (incomingRequest.containsKey("SungardVendor_certifiedTin"))
			{
				String certifiedTin = (String) incomingRequest.get("SungardVendor_certifiedTin");
				vendor.setCertifiedTin(certifiedTin);
			}
			if (incomingRequest.containsKey("SungardVendor_dfltAddrSeqNum"))
			{
			    String dfltAddrSeqNumString = (String) incomingRequest.get("SungardVendor_dfltAddrSeqNum");
				if (Utility.isEmpty(dfltAddrSeqNumString))
				{
				    dfltAddrSeqNumString = "0";
				}
				BigDecimal dfltAddrSeqNum = new BigDecimal ( dfltAddrSeqNumString );
				vendor.setDfltAddrSeqNum(dfltAddrSeqNum);
			}
			if (incomingRequest.containsKey("SungardVendor_discPct"))
			{
				String discPctString = (String) incomingRequest.get("SungardVendor_discPct");
				if (Utility.isEmpty(discPctString))
				{
					discPctString = "0";
				}
				BigDecimal discPct = new BigDecimal ( discPctString );
				vendor.setDiscPct(discPct);
			}
			if (incomingRequest.containsKey("SungardVendor_salesTaxPct"))
			{
				String salesTaxPctString = (String) incomingRequest.get("SungardVendor_salesTaxPct");
				if (Utility.isEmpty(salesTaxPctString))
				{
					salesTaxPctString = "0";
				}
				BigDecimal salesTaxPct = new BigDecimal ( salesTaxPctString );
				vendor.setSalesTaxPct(salesTaxPct);
			}
			if (incomingRequest.containsKey("SungardVendor_irsCodeId"))
			{
			    String irsCodeIdString = (String) incomingRequest.get("SungardVendor_irsCodeId");
				if (Utility.isEmpty(irsCodeIdString))
				{
				    irsCodeIdString = "0";
				}
				BigDecimal irsCodeId = new BigDecimal ( irsCodeIdString );
				vendor.setIrsCodeId(irsCodeId);
			}
			if (incomingRequest.containsKey("SungardVendor_takeDiscount"))
			{
				String takeDiscount = (String) incomingRequest.get("SungardVendor_takeDiscount");
				vendor.setTakeDiscount(takeDiscount);
			}
			if (incomingRequest.containsKey("SungardVendor_discountInd"))
			{
				String discountInd = (String) incomingRequest.get("SungardVendor_discountInd");
				vendor.setDiscountInd(discountInd);
			}
			if (incomingRequest.containsKey("SungardVendor_discountDays"))
			{
			    String discountDaysString = (String) incomingRequest.get("SungardVendor_discountDays");
				if (Utility.isEmpty(discountDaysString))
				{
				    discountDaysString = "0";
				}
				BigDecimal discountDays = new BigDecimal ( discountDaysString );
				vendor.setDiscountDays(discountDays);
			}
			if (incomingRequest.containsKey("SungardVendor_useTaxInd"))
			{
				String useTaxInd = (String) incomingRequest.get("SungardVendor_useTaxInd");
				vendor.setUseTaxInd(useTaxInd);
			}
			if (incomingRequest.containsKey("SungardVendor_combineInd"))
			{
				String combineInd = (String) incomingRequest.get("SungardVendor_combineInd");
				vendor.setCombineInd(combineInd);
			}
			if (incomingRequest.containsKey("SungardVendor_holdInd"))
			{
				String holdInd = (String) incomingRequest.get("SungardVendor_holdInd");
				vendor.setHoldInd(holdInd);
			}
			if (incomingRequest.containsKey("SungardVendor_advicePrint"))
			{
				String advicePrint = (String) incomingRequest.get("SungardVendor_advicePrint");
				vendor.setAdvicePrint(advicePrint);
			}
			if (incomingRequest.containsKey("SungardVendor_sortCode"))
			{
				String sortCode = (String) incomingRequest.get("SungardVendor_sortCode");
				vendor.setSortCode(sortCode);
			}
			if (incomingRequest.containsKey("SungardVendor_vendorStartDate"))
			{
				String vendorStartDateString = (String) incomingRequest.get("SungardVendor_vendorStartDate");
				Date vendorStartDate = Dates.getDate(vendorStartDateString);
				vendor.setVendorStartDate(vendorStartDate);
			}
			if (incomingRequest.containsKey("SungardVendor_vendorEndDate"))
			{
				String vendorEndDateString = (String) incomingRequest.get("SungardVendor_vendorEndDate");
				Date vendorEndDate = Dates.getDate(vendorEndDateString);
				vendor.setVendorEndDate(vendorEndDate);
			}
			if (incomingRequest.containsKey("SungardVendor_minorityInd"))
			{
				String minorityInd = (String) incomingRequest.get("SungardVendor_minorityInd");
				vendor.setMinorityInd(minorityInd);
			}
			if (incomingRequest.containsKey("SungardVendor_withholdingInd"))
			{
				String withholdingInd = (String) incomingRequest.get("SungardVendor_withholdingInd");
				vendor.setWithholdingInd(withholdingInd);
			}
			if (incomingRequest.containsKey("SungardVendor_whThresholdAmt"))
			{
				String whThresholdAmtString = (String) incomingRequest.get("SungardVendor_whThresholdAmt");
				if (Utility.isEmpty(whThresholdAmtString))
				{
					whThresholdAmtString = "0";
				}
				BigDecimal whThresholdAmt = new BigDecimal ( whThresholdAmtString );
				vendor.setWhThresholdAmt(whThresholdAmt);
			}
			if (incomingRequest.containsKey("SungardVendor_whPct"))
			{
				String whPctString = (String) incomingRequest.get("SungardVendor_whPct");
				if (Utility.isEmpty(whPctString))
				{
					whPctString = "0";
				}
				BigDecimal whPct = new BigDecimal ( whPctString );
				vendor.setWhPct(whPct);
			}
			if (incomingRequest.containsKey("SungardVendor_netDueDays"))
			{
				String netDueDaysString = (String) incomingRequest.get("SungardVendor_netDueDays");
				if (Utility.isEmpty(netDueDaysString))
				{
				    netDueDaysString = "0";
				}
				BigDecimal netDueDays = new BigDecimal ( netDueDaysString );
				vendor.setNetDueDays(netDueDays);
			}
			if (incomingRequest.containsKey("SungardVendor_minorityGroup"))
			{
				String minorityGroup = (String) incomingRequest.get("SungardVendor_minorityGroup");
				vendor.setMinorityGroup(minorityGroup);
			}
			if (incomingRequest.containsKey("SungardVendor_vendorName1"))
			{
				String vendorName1 = (String) incomingRequest.get("SungardVendor_vendorName1");
				vendor.setVendorName1(vendorName1);
			}

			vendor.setComp_id(comp_id);
			
			result = vendor;
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
package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class VendorSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain vendor */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			Vendor	originalVendor = (Vendor) incomingRequest.get("vendor");
			Vendor	vendor = new Vendor();

			vendor.setVendorId(originalVendor.getVendorId());
			vendor.setVendorName(originalVendor.getVendorName());
			vendor.setPerformance(originalVendor.getPerformance());
			vendor.setVendorClass(originalVendor.getVendorClass());
			vendor.setFobId(originalVendor.getFobId());
			vendor.setVendTerms(originalVendor.getVendTerms());
			vendor.setShipVia(originalVendor.getShipVia());
			vendor.setPrintFaxCode(originalVendor.getPrintFaxCode());
			vendor.setFaxNumber(originalVendor.getFaxNumber());
			vendor.setVendorAccount(originalVendor.getVendorAccount());
			vendor.setEdiVendor(originalVendor.getEdiVendor());
			vendor.setTaxCode(originalVendor.getTaxCode());
			vendor.setCurrencyCode(originalVendor.getCurrencyCode());
			vendor.setVendorSic(originalVendor.getVendorSic());
			vendor.setVendorDuns(originalVendor.getVendorDuns());
			vendor.setVendorEin(originalVendor.getVendorEin());
			vendor.setInspectionReqd(originalVendor.getInspectionReqd());
			vendor.setPoCopies(originalVendor.getPoCopies());
			vendor.setPrintPrices(originalVendor.getPrintPrices());
			vendor.setLeadDays(originalVendor.getLeadDays());
			vendor.setYearsInBusiness(originalVendor.getYearsInBusiness());
			vendor.setYearsAsVendor(originalVendor.getYearsAsVendor());
			vendor.setLastActive(originalVendor.getLastActive());
			vendor.setLastChange(originalVendor.getLastChange());
			vendor.setVendUdf1(originalVendor.getVendUdf1());
			vendor.setVendUdf2(originalVendor.getVendUdf2());
			vendor.setVendUdf3(originalVendor.getVendUdf3());
			vendor.setVendUdf4(originalVendor.getVendUdf4());
			vendor.setVendUdf5(originalVendor.getVendUdf5());
			vendor.setVendUdf6(originalVendor.getVendUdf6());
			vendor.setVendUdf7(originalVendor.getVendUdf7());
			vendor.setVendUdf8(originalVendor.getVendUdf8());
			vendor.setVendUdf9(originalVendor.getVendUdf9());
			vendor.setVendUdf10(originalVendor.getVendUdf10());
			vendor.setDateEntered(originalVendor.getDateEntered());
			vendor.setDateExpires(originalVendor.getDateExpires());
			vendor.setStatus(originalVendor.getStatus());
			vendor.setOwner(originalVendor.getOwner());
			vendor.setNotes(originalVendor.getNotes());
			vendor.setVendor1099(originalVendor.getVendor1099());
			vendor.setApReference(originalVendor.getApReference());
			vendor.setEdiAddress(originalVendor.getEdiAddress());
			vendor.setEmailAddress(originalVendor.getEmailAddress());
			vendor.setWebAddress(originalVendor.getWebAddress());
			vendor.setParentCode(originalVendor.getParentCode());
			vendor.setPcardCode(originalVendor.getPcardCode());
			vendor.setLastChangedBy(originalVendor.getLastChangedBy());
			vendor.setApBatchid(originalVendor.getApBatchid());
			vendor.setRefCompanyName(originalVendor.getRefCompanyName());
			vendor.setRefPhoneNumber(originalVendor.getRefPhoneNumber());
			vendor.setRefContactName(originalVendor.getRefContactName());
			vendor.setVendorNaics(originalVendor.getVendorNaics());
			vendor.setDiversityProgram(originalVendor.getDiversityProgram());
			vendor.setSubcontract(originalVendor.getSubcontract());
			vendor.setOwnershipType(originalVendor.getOwnershipType());
			vendor.setDiverseCertOrgs(originalVendor.getDiverseCertOrgs());
			vendor.setBusinessType(originalVendor.getBusinessType());
			vendor.setVendorType(originalVendor.getVendorType());

			incomingRequest.put("vendor", vendor);

			VendorAdd addTask = new VendorAdd();
			vendor = (Vendor) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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
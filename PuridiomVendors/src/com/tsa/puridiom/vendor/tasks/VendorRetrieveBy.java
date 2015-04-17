package com.tsa.puridiom.vendor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class VendorRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Vendor as vendor where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList<Type>();
		
		if(incomingRequest.containsKey("Vendor_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("Vendor_vendorId");
			args.add(vendorId);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.id.vendorId = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendorName"))
		{			
			String vendorName = (String) incomingRequest.get("Vendor_vendorName");
			args.add(vendorName);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendorName = ?");
		}
		if(incomingRequest.containsKey("Vendor_performance"))
		{			
			String performance = (String) incomingRequest.get("Vendor_performance");
			args.add(performance);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.performance = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendorClass"))
		{			
			String vendorClass = (String) incomingRequest.get("Vendor_vendorClass");
			args.add(vendorClass);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendorClass = ?");
		}
		if(incomingRequest.containsKey("Vendor_fobId"))
		{			
			String fobId = (String) incomingRequest.get("Vendor_fobId");
			args.add(fobId);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.fobId = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendTerms"))
		{			
			String vendTerms = (String) incomingRequest.get("Vendor_vendTerms");
			args.add(vendTerms);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendTerms = ?");
		}
		if(incomingRequest.containsKey("Vendor_shipVia"))
		{			
			String shipVia = (String) incomingRequest.get("Vendor_shipVia");
			args.add(shipVia);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.shipVia = ?");
		}
		if(incomingRequest.containsKey("Vendor_printFaxCode"))
		{			
			String printFaxCode = (String) incomingRequest.get("Vendor_printFaxCode");
			args.add(printFaxCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.printFaxCode = ?");
		}
		if(incomingRequest.containsKey("Vendor_faxNumber"))
		{			
			String faxNumber = (String) incomingRequest.get("Vendor_faxNumber");
			args.add(faxNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.faxNumber = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendorAccount"))
		{			
			String vendorAccount = (String) incomingRequest.get("Vendor_vendorAccount");
			args.add(vendorAccount);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendorAccount = ?");
		}
		if(incomingRequest.containsKey("Vendor_ediVendor"))
		{			
			String ediVendor = (String) incomingRequest.get("Vendor_ediVendor");
			args.add(ediVendor);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.ediVendor = ?");
		}
		if(incomingRequest.containsKey("Vendor_taxCode"))
		{			
			String taxCode = (String) incomingRequest.get("Vendor_taxCode");
			args.add(taxCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.taxCode = ?");
		}
		if(incomingRequest.containsKey("Vendor_currencyCode"))
		{			
			String currencyCode = (String) incomingRequest.get("Vendor_currencyCode");
			args.add(currencyCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.currencyCode = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendorSic"))
		{			
			String vendorSic = (String) incomingRequest.get("Vendor_vendorSic");
			args.add(vendorSic);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendorSic = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendorDuns"))
		{			
			String vendorDuns = (String) incomingRequest.get("Vendor_vendorDuns");
			args.add(vendorDuns);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendorDuns = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendorEin"))
		{			
			String vendorEin = (String) incomingRequest.get("Vendor_vendorEin");
			args.add(vendorEin);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendorEin = ?");
		}
		if(incomingRequest.containsKey("Vendor_inspectionReqd"))
		{			
			String inspectionReqd = (String) incomingRequest.get("Vendor_inspectionReqd");
			args.add(inspectionReqd);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.inspectionReqd = ?");
		}
		if(incomingRequest.containsKey("Vendor_poCopies"))
		{			
			String poCopies = (String) incomingRequest.get("Vendor_poCopies");
			args.add(poCopies);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.poCopies = ?");
		}
		if(incomingRequest.containsKey("Vendor_printPrices"))
		{			
			String printPrices = (String) incomingRequest.get("Vendor_printPrices");
			args.add(printPrices);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.printPrices = ?");
		}
		if(incomingRequest.containsKey("Vendor_leadDays"))
		{			
			String leadDays = (String) incomingRequest.get("Vendor_leadDays");
			args.add(leadDays);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.leadDays = ?");
		}
		if(incomingRequest.containsKey("Vendor_yearsInBusiness"))
		{			
			String yearsInBusiness = (String) incomingRequest.get("Vendor_yearsInBusiness");
			args.add(yearsInBusiness);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.yearsInBusiness = ?");
		}
		if(incomingRequest.containsKey("Vendor_yearsAsVendor"))
		{			
			String yearsAsVendor = (String) incomingRequest.get("Vendor_yearsAsVendor");
			args.add(yearsAsVendor);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.yearsAsVendor = ?");
		}
		if(incomingRequest.containsKey("Vendor_lastActive"))
		{			
			String lastActive = (String) incomingRequest.get("Vendor_lastActive");
			args.add(lastActive);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.lastActive = ?");
		}
		if(incomingRequest.containsKey("Vendor_lastChange"))
		{			
			String lastChange = (String) incomingRequest.get("Vendor_lastChange");
			args.add(lastChange);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.lastChange = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendUdf1"))
		{			
			String vendUdf1 = (String) incomingRequest.get("Vendor_vendUdf1");
			args.add(vendUdf1);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendUdf1 = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendUdf2"))
		{			
			String vendUdf2 = (String) incomingRequest.get("Vendor_vendUdf2");
			args.add(vendUdf2);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendUdf2 = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendUdf3"))
		{			
			String vendUdf3 = (String) incomingRequest.get("Vendor_vendUdf3");
			args.add(vendUdf3);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendUdf3 = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendUdf4"))
		{			
			String vendUdf4 = (String) incomingRequest.get("Vendor_vendUdf4");
			args.add(vendUdf4);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendUdf4 = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendUdf5"))
		{			
			String vendUdf5 = (String) incomingRequest.get("Vendor_vendUdf5");
			args.add(vendUdf5);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendUdf5 = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendUdf6"))
		{			
			String vendUdf6 = (String) incomingRequest.get("Vendor_vendUdf6");
			args.add(vendUdf6);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendUdf6 = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendUdf7"))
		{			
			String vendUdf7 = (String) incomingRequest.get("Vendor_vendUdf7");
			args.add(vendUdf7);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendUdf7 = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendUdf8"))
		{			
			String vendUdf8 = (String) incomingRequest.get("Vendor_vendUdf8");
			args.add(vendUdf8);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendUdf8 = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendUdf9"))
		{			
			String vendUdf9 = (String) incomingRequest.get("Vendor_vendUdf9");
			args.add(vendUdf9);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendUdf9 = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendUdf10"))
		{			
			String vendUdf10 = (String) incomingRequest.get("Vendor_vendUdf10");
			args.add(vendUdf10);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendUdf10 = ?");
		}
		if(incomingRequest.containsKey("Vendor_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("Vendor_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.dateEntered = ?");
		}
		if(incomingRequest.containsKey("Vendor_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("Vendor_dateExpires");
			args.add(dateExpires);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.dateExpires = ?");
		}
		if(incomingRequest.containsKey("Vendor_status"))
		{			
			String status = (String) incomingRequest.get("Vendor_status");
			args.add(status);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.status = ?");
		}
		if(incomingRequest.containsKey("Vendor_owner"))
		{			
			String owner = (String) incomingRequest.get("Vendor_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.owner = ?");
		}
		if(incomingRequest.containsKey("Vendor_notes"))
		{			
			String notes = (String) incomingRequest.get("Vendor_notes");
			args.add(notes);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.notes = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendor1099"))
		{			
			String vendor1099 = (String) incomingRequest.get("Vendor_vendor1099");
			args.add(vendor1099);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendor1099 = ?");
		}
		if(incomingRequest.containsKey("Vendor_apReference"))
		{			
			String apReference = (String) incomingRequest.get("Vendor_apReference");
			args.add(apReference);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.apReference = ?");
		}
		if(incomingRequest.containsKey("Vendor_ediAddress"))
		{			
			String ediAddress = (String) incomingRequest.get("Vendor_ediAddress");
			args.add(ediAddress);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.ediAddress = ?");
		}
		if(incomingRequest.containsKey("Vendor_emailAddress"))
		{			
			String emailAddress = (String) incomingRequest.get("Vendor_emailAddress");
			args.add(emailAddress);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.emailAddress = ?");
		}
		if(incomingRequest.containsKey("Vendor_webAddress"))
		{			
			String webAddress = (String) incomingRequest.get("Vendor_webAddress");
			args.add(webAddress);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.webAddress = ?");
		}
		if(incomingRequest.containsKey("Vendor_parentCode"))
		{			
			String parentCode = (String) incomingRequest.get("Vendor_parentCode");
			args.add(parentCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.parentCode = ?");
		}
		if(incomingRequest.containsKey("Vendor_pcardCode"))
		{			
			String pcardCode = (String) incomingRequest.get("Vendor_pcardCode");
			args.add(pcardCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.pcardCode = ?");
		}
		if(incomingRequest.containsKey("Vendor_lastChangedBy"))
		{			
			String lastChangedBy = (String) incomingRequest.get("Vendor_lastChangedBy");
			args.add(lastChangedBy);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.lastChangedBy = ?");
		}
		if(incomingRequest.containsKey("Vendor_apBatchid"))
		{			
			String apBatchid = (String) incomingRequest.get("Vendor_apBatchid");
			args.add(apBatchid);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.apBatchid = ?");
		}
		if(incomingRequest.containsKey("Vendor_refCompanyName"))
		{			
			String refCompanyName = (String) incomingRequest.get("Vendor_refCompanyName");
			args.add(refCompanyName);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.refCompanyName = ?");
		}
		if(incomingRequest.containsKey("Vendor_refPhoneNumber"))
		{			
			String refPhoneNumber = (String) incomingRequest.get("Vendor_refPhoneNumber");
			args.add(refPhoneNumber);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.refPhoneNumber = ?");
		}
		if(incomingRequest.containsKey("Vendor_refContactName"))
		{			
			String refContactName = (String) incomingRequest.get("Vendor_refContactName");
			args.add(refContactName);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.refContactName = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendorNaics"))
		{			
			String vendorNaics = (String) incomingRequest.get("Vendor_vendorNaics");
			args.add(vendorNaics);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendorNaics = ?");
		}
		if(incomingRequest.containsKey("Vendor_diversityProgram"))
		{			
			String diversityProgram = (String) incomingRequest.get("Vendor_diversityProgram");
			args.add(diversityProgram);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.diversityProgram = ?");
		}
		if(incomingRequest.containsKey("Vendor_subcontract"))
		{			
			String subcontract = (String) incomingRequest.get("Vendor_subcontract");
			args.add(subcontract);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.subcontract = ?");
		}
		if(incomingRequest.containsKey("Vendor_ownershipType"))
		{			
			String ownershipType = (String) incomingRequest.get("Vendor_ownershipType");
			args.add(ownershipType);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.ownershipType = ?");
		}
		if(incomingRequest.containsKey("Vendor_diverseCertOrgs"))
		{			
			String diverseCertOrgs = (String) incomingRequest.get("Vendor_diverseCertOrgs");
			args.add(diverseCertOrgs);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.diverseCertOrgs = ?");
		}
		if(incomingRequest.containsKey("Vendor_businessType"))
		{			
			String businessType = (String) incomingRequest.get("Vendor_businessType");
			args.add(businessType);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.businessType = ?");
		}
		if(incomingRequest.containsKey("Vendor_digitalSig"))
		{			
			String digitalSig = (String) incomingRequest.get("Vendor_digitalSig");
			args.add(digitalSig);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.digitalSig = ?");
		}
		if(incomingRequest.containsKey("Vendor_termsAccepted"))
		{			
			String termsAccepted = (String) incomingRequest.get("Vendor_termsAccepted");
			args.add(termsAccepted);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.termsAccepted = ?");
		}
		if(incomingRequest.containsKey("Vendor_validated"))
		{			
			String validated = (String) incomingRequest.get("Vendor_validated");
			args.add(validated);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.validated = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendorRating"))
		{			
			String vendorRating = (String) incomingRequest.get("Vendor_vendorRating");
			args.add(vendorRating);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendorRating = ?");
		}
		if(incomingRequest.containsKey("Vendor_rated"))
		{			
			String rated = (String) incomingRequest.get("Vendor_rated");
			args.add(rated);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.rated = ?");
		}
		if(incomingRequest.containsKey("Vendor_vendorType"))
		{			
			String vendorType = (String) incomingRequest.get("Vendor_vendorType");
			args.add(vendorType);
			types.add(Hibernate.STRING);
			queryString.append(" AND vendor.vendorType = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()]));
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}

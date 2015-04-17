package com.tsa.puridiom.coda.test;

import com.coda.www.efinance.schemas.elementmaster.Element;
import com.tsa.puridiom.coda.tasks.BrowseRetrieveElementFinder;
import com.tsa.puridiom.coda.tasks.CodaRetrieveElementMaster;
import com.tsa.puridiom.coda.tasks.ElementFinderRetrieve;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class UpdateApReferenceFromCodaTest
{
	public static void  main (String[] args) throws Exception
	{

		boolean moreRecords = true ;
		String	lastRow = "1" ;
		String 	orgId = "TTR09P" ;
		String  uid = "SYSADM" ;
		int		counter = 0 ;
		System.out.println("Starting vendor ap reference update utility") ;
		while (moreRecords) {
			try
			{
				ElementFinderRetrieve ef = new ElementFinderRetrieve() ;
				Map efRequest = new HashMap() ;

				efRequest.put("organizationId", orgId);
				efRequest.put("userId", uid);
				efRequest.put("codaLevel","2");
				efRequest.put("codeFilter","V");
				efRequest.put("nameFilter","");
				efRequest.put("shortNameFilter","");
				efRequest.put("startRow", lastRow) ;
				// 200 at a time
				lastRow = Integer.toString(Integer.parseInt(lastRow) + 50) ;
				efRequest.put("endRow", lastRow) ;

				List vendorList = (List) ef.executeTask(efRequest) ;

				Iterator vit = vendorList.iterator();
				while (vit.hasNext()) {
					String data[] = (String[]) vit.next() ;
					// Get vendor code and
					if (! HiltonUtility.isEmpty(data[0])) {

						Map emRequest = new HashMap() ;
						emRequest.put("organizationId", orgId);
						emRequest.put("userId", uid);
						emRequest.put("codaLevel","2");
						emRequest.put("codeFilter",data[0]);

						CodaRetrieveElementMaster em = new CodaRetrieveElementMaster() ;

						com.coda.www.efinance.schemas.elementmaster.Element el = (com.coda.www.efinance.schemas.elementmaster.Element) em.executeTask(emRequest) ;
						if (el != null) {
							if (! HiltonUtility.isEmpty(el.getMemoStatus())) {
								// Has a puridiom id - Retrive Vendor
								String statutoryCode = el.getMemoStatus() ;
								Map incomingRequest = new HashMap() ;
								incomingRequest.put("organizationId", orgId);
								incomingRequest.put("userId", uid);
								incomingRequest.put("Vendor_vendorId", statutoryCode);
								System.out.println(counter + "-Vendor: " + statutoryCode) ;
								try
								{
									PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
									PuridiomProcess process = processLoader.loadProcess("vendor-retrieve-by-id.xml");
									process.executeProcess(incomingRequest);
								}
								catch (Exception e)
								{
									e.printStackTrace() ;
								}

								Vendor vendor = (Vendor) incomingRequest.get("vendor") ;
								if (vendor != null) {
									String apRef = vendor.getApReference() ;
									if (HiltonUtility.isEmpty(apRef) || ! apRef.equals(data[0])) {
										// Update
										try
										{
											System.out.println(counter + "-Updating AP Reference: " + apRef) ;
											vendor.setApReference(data[0]) ;
											incomingRequest.put("vendor", vendor) ;
											PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
											PuridiomProcess process = processLoader.loadProcess("vendor-update.xml");
											process.executeProcess(incomingRequest);
										}
										catch (Exception e)
										{
											e.printStackTrace();
										}
									}
								}
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			break ;
		}
	}

}
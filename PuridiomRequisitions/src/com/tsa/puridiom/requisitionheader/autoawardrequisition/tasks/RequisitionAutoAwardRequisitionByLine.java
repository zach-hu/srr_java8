package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.catalog.tasks.CatalogRetrieveById;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.contact.tasks.ContactRetrieveById;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.poheader.tasks.PoHeaderRetrieveById;
import com.tsa.puridiom.poheader.tasks.PoHeaderRetrieveByNumber;
import com.tsa.puridiom.poheader.tasks.PoNumberRetrieveContractsByVendorIdFromVinimaya;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class RequisitionAutoAwardRequisitionByLine extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

        try
        {
        	PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
            PuridiomProcess process = null;
        	Map incomingRequest = (Map)object;
        	Map incomingRequestVin = new HashMap();

        	String organizationId = (String)incomingRequest.get("organizationId");
        	List rqhList = (List)incomingRequest.get("requisitionHeaderList");

        	String applicationType = HiltonUtility.ckNull((String)incomingRequest.get("applicationType")).equalsIgnoreCase("WEB") ? "WEB" : "PS";
        	String bySupplier  = "N";
        	String byCatalog  = "N";
        	if ( applicationType.equalsIgnoreCase("WEB") )
        	{
        		bySupplier = HiltonUtility.ckNull((String)incomingRequest.get("bySupplier")).equalsIgnoreCase("on") ? "Y" : "N";
        		byCatalog = HiltonUtility.ckNull((String)incomingRequest.get("byCatalog")).equalsIgnoreCase("on") ? "Y" : "N";; // Catalog or Warehouse
        	}
        	else if (applicationType.equalsIgnoreCase("PS"))
        	{
        		bySupplier = "Y";
    			byCatalog = "Y";
        		if (PropertiesManager.getInstance(organizationId).getProperty("REQ OPTIONS", "AUTOAWARDONLYBYSUPPLIERITEM", "N").equalsIgnoreCase("Y"))
        		{
        			byCatalog = "N";
        		}
        	}

            if(rqhList != null && rqhList.size() > 0)
            {
                for (Iterator iterator = rqhList.iterator(); iterator.hasNext();)
                {
                	RequisitionHeader rqh = (RequisitionHeader) iterator.next();
                	incomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());

                	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

        			String queryString = "from RequisitionLine RequisitionLine " +
        			"WHERE RequisitionLine.icReqHeader = ? AND RequisitionLine.status = ? ";

        			queryString += "ORDER BY RequisitionLine.vendorId ";
        			//queryString += "ORDER BY RequisitionLine.vendorId, RequisitionLine.udf5Code ";

        			List requisitionLineList = dbs.query(queryString, new Object[] {rqh.getIcReqHeader().toString(),DocumentStatus.REQ_APPROVED} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

        			if (requisitionLineList != null &&  requisitionLineList.size() > 0)
        			{
	        			List rqlGrouped = new ArrayList();
	        			List rqlList = new ArrayList();

	        			String previousVendorId = "";
	        			//String previousWarehouse = "";
	        			//String previousEntity = "";
	        			//String currentEntity = "";

	        			for (Iterator iterator2 = requisitionLineList.iterator(); iterator2.hasNext();)
	                    {
	        				RequisitionLine rql = (RequisitionLine) iterator2.next();

	        		        /*String queryString2 = "from Account as a where a.id.icHeader = ? AND a.id.icLine = ?";
	        				List accountList = dbs.query(queryString2, new Object[] {rqh.getIcReqHeader(), rql.getIcReqLine()}, new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;
	        				if (accountList != null && accountList.size() > 0 )
	        				{
	        					currentEntity = HiltonUtility.ckNull(((Account)accountList.get(0)).getFld1());
	        				}*/

	                    	if (rql.getVendorId().equals(previousVendorId) || bySupplier.equalsIgnoreCase("N") || byCatalog.equalsIgnoreCase("N"))
	                    	{
	                    		rqlList.add(rql);
	                    	}
	                    	else
	                    	{
	                    		if(rqlList != null && rqlList.size() > 0 )
	                    		{
	                    			rqlGrouped.add(rqlList);
	                    		}
	                    		rqlList = new ArrayList();
	                    		rqlList.add(rql);

	                    		previousVendorId = rql.getVendorId();
	                			//previousWarehouse = rql.getUdf5Code();
	                			//previousEntity = currentEntity;
	                    	}
	                    }
	        			rqlGrouped.add(rqlList);


	        			for (Iterator iterator3 = rqlGrouped.iterator(); iterator3.hasNext();)
	                    {

	        				List rqlListAutoAwardedByLine = (List) iterator3.next();
	        				RequisitionLine aux = (RequisitionLine)rqlListAutoAwardedByLine.get(0);
	        				String itemSource = HiltonUtility.ckNull(aux.getItemSource());
	        				String catalog = HiltonUtility.ckNull(aux.getCatalogId());

	        				if (itemSource.equalsIgnoreCase("XML") && catalog.equalsIgnoreCase("VINIMAYA"))
	        				{
	        					//incomingRequestVin.put("PoHeader_groupVendorId", aux.getVendorId());
        						String bOrder = aux.getBlanketOrder() ;
        			        	String mString = aux.getMemoLine() + " " ;
        						if (HiltonUtility.isEmpty(bOrder)) {
        			            	if (! HiltonUtility.isEmpty(mString)) {
        			            		int ps = mString.indexOf("SUPPLIER_CNTRCT_ID: ") ;
        			            		if (ps >= 0) {
        			            			ps = ps + 20;
        			            			int pe = mString.indexOf(" ", ps + 1) ;
        			            			bOrder = mString.substring(ps, pe).trim() ;
        			            			Log.debug(this, "SUPPLIER_CNTRCT_ID:  Start=" + ps + " End=" + pe + " Result=" + bOrder) ;
        			            		}
        			            	}
        						}

        						Log.debug(this, "BlanketOrder="  + bOrder) ;

        						incomingRequestVin.put("RequisitionLine_blanketOrder", bOrder);

        						if (HiltonUtility.isEmpty(bOrder)) {
        							process = processLoader.loadProcess("requisition-unable-autoaward.xml") ;
        							try {
    									process.executeProcess(incomingRequest);
    								} catch (Exception e) {
    									Log.error(this, e);
    								}

        						} else {
        							incomingRequestVin.put("dbsession", incomingRequest.get("dbsession"));
        							PoNumberRetrieveContractsByVendorIdFromVinimaya poRetrieveContract = new PoNumberRetrieveContractsByVendorIdFromVinimaya();
        				            String poNumberContract = (String)poRetrieveContract.executeTask(incomingRequestVin);

        				            incomingRequest.put("poNumberContract", poNumberContract);

        							if (HiltonUtility.isEmpty(poNumberContract)) {
        								process = processLoader.loadProcess("requisition-unable-autoaward.xml") ;
        								try {
        									process.executeProcess(incomingRequest);
        								} catch (Exception e) {
        									Log.error(this, e);
        								}

        							} else {
        								if (rqlListAutoAwardedByLine != null && rqlListAutoAwardedByLine.size() > 0) {
        	        						Iterator iterator4 = rqlListAutoAwardedByLine.iterator();
        	        						while (iterator4.hasNext()) {
        	        							RequisitionLine requisitionLineAux = (RequisitionLine) iterator4.next();
        	        							List rqlListItem = new ArrayList();
        	        							rqlListItem.add(requisitionLineAux);

        	        							createOrder(incomingRequest, incomingRequestVin, organizationId, rqh,
        	        									requisitionLineAux, rqlListItem, processLoader);
        	        						}
        	        					}
        							}
        						}
	        				}
	        				else
	        				{
	        					if (PropertiesManager.getInstance(organizationId).getProperty("AUTOAWARD", "ONELINEPERORDER", "N").equalsIgnoreCase("Y")) {
									if (rqlListAutoAwardedByLine != null && rqlListAutoAwardedByLine.size() > 0) {
		        						Iterator iterator4 = rqlListAutoAwardedByLine.iterator();
		        						while (iterator4.hasNext()) {
		        							RequisitionLine requisitionLineAux = (RequisitionLine) iterator4.next();
		        							if (HiltonUtility.isEmpty(requisitionLineAux.getVendorId()) ) {
		        								if (! HiltonUtility.isEmpty(requisitionLineAux.getCatalogId())) {
		        									incomingRequest.put("Catalog_catalogId", requisitionLineAux.getCatalogId()) ;
		        									// Need to lookup catalog to get vendor id (if no vendor, no autoaward)
		        									CatalogRetrieveById catLookup = new CatalogRetrieveById() ;
		        									Catalog cat = (Catalog) catLookup.executeTask(incomingRequest) ;
		        									if (cat != null) {
		        										requisitionLineAux.setVendorId(cat.getVendorId()) ;
		        									}
		        								}
		        							}
		        							List rqlListItem = new ArrayList();
		        							rqlListItem.add(requisitionLineAux);

		        							createOrder(incomingRequest, incomingRequestVin, organizationId, rqh,
		        									requisitionLineAux, rqlListItem, processLoader);
		        						}
		        					}
	        					} else {
	        						createOrder(incomingRequest, incomingRequestVin, organizationId, rqh,
	        								aux, rqlListAutoAwardedByLine, processLoader);
	        					}
	        				}
	                    }
        			}
                }
            }
            else
            {
            	Log.debug(this, "No requisitions to autoaward!");
            }

            Log.debug(this, "AUTOAWARD WORKING!");
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	Log.error(this, "RequisitionAutoAwardRequisitionByLine failed: " + e.getMessage());
            this.setStatus(Status.FAILED);
            throw e;
        }
        return ret;
    }

    /**
     *
     * @param incomingRequest
     * @param incomingRequestVin
     * @param organizationId
     * @param rqh
     * @param aux
     * @param rqlListAutoAwardedByLine
     * @param processLoader
     * @throws Exception
     */
    private void createOrder(Map incomingRequest, Map incomingRequestVin,
    		String organizationId, RequisitionHeader rqh,
    		RequisitionLine aux, List rqlListAutoAwardedByLine,
    		PuridiomProcessLoader processLoader) throws Exception {

    	PuridiomProcess process = null;

    	String itemSource = HiltonUtility.ckNull(aux.getItemSource());
		String catalog = HiltonUtility.ckNull(aux.getCatalogId());

    	Map newIncomingRequest = new HashMap();
        newIncomingRequest.put("userId", incomingRequest.get("userId"));
        newIncomingRequest.put("ipAddress", incomingRequest.get("ipAddress"));
        newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
        newIncomingRequest.put("organizationId", organizationId);
        newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
        newIncomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());
        newIncomingRequest.put("AutoAwardByLine_revisionValue", aux.getLineTotal().toString());

		newIncomingRequest.put("rqlListAutoAwardedByLine", rqlListAutoAwardedByLine);
		newIncomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());

		ContactRetrieveById contactRetrieve = new ContactRetrieveById();
		Map contactIncomingRequest = new HashMap();
		contactIncomingRequest.put("Contact_contactType", "DEFAULT");
		contactIncomingRequest.put("Contact_contactCode", "001");
		contactIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

		if( !HiltonUtility.isEmpty(aux.getVendorId()) && VendorManager.getInstance().getVendor(organizationId, aux.getVendorId()) instanceof Vendor)
		{
			newIncomingRequest.put("AutoAwardByLine_vendorId", aux.getVendorId());
			String vendorTermsCode = HiltonUtility.ckNull(((Vendor)VendorManager.getInstance().getVendor(organizationId, aux.getVendorId())).getVendTerms());
			newIncomingRequest.put("AutoAwardByLine_vendTermsCode", vendorTermsCode);
			String vendorPrintFaxCode = HiltonUtility.ckNull(((Vendor)VendorManager.getInstance().getVendor(organizationId, aux.getVendorId())).getPrintFaxCode());
			newIncomingRequest.put("AutoAwardByLine_vendorPrintFaxCode", vendorPrintFaxCode);

			contactIncomingRequest.put("Contact_vendorId", aux.getVendorId());
			if (!HiltonUtility.isEmpty(aux.getVendContactCode()))
				contactIncomingRequest.put("Contact_contactCode", aux.getVendContactCode());
			else if (!HiltonUtility.isEmpty(rqh.getVendContactCode()))
				contactIncomingRequest.put("Contact_contactCode", rqh.getVendContactCode());

			Contact contact = (Contact)contactRetrieve.executeTask(contactIncomingRequest);
			if (contact != null)
			{
				newIncomingRequest.put("AutoAwardByLine_contactName", contact.getFirstName() + " " + contact.getLastName());
				newIncomingRequest.put("AutoAwardByLine_contactPhone", contact.getPhoneNumber());
			}
		}
		else if( !HiltonUtility.isEmpty(rqh.getVendorId()) && VendorManager.getInstance().getVendor(organizationId, rqh.getVendorId()) instanceof Vendor)
		{
			newIncomingRequest.put("AutoAwardByLine_vendorId", rqh.getVendorId());
			String vendorTermsCode = HiltonUtility.ckNull(((Vendor)VendorManager.getInstance().getVendor(organizationId, rqh.getVendorId())).getVendTerms());
			newIncomingRequest.put("AutoAwardByLine_vendTermsCode", vendorTermsCode);
			String vendorPrintFaxCode = HiltonUtility.ckNull(((Vendor)VendorManager.getInstance().getVendor(organizationId, rqh.getVendorId())).getPrintFaxCode());
			newIncomingRequest.put("AutoAwardByLine_vendorPrintFaxCode", vendorPrintFaxCode);

			contactIncomingRequest.put("Contact_vendorId", rqh.getVendorId());
			if (!HiltonUtility.isEmpty(aux.getVendContactCode()))
				contactIncomingRequest.put("Contact_contactCode", aux.getVendContactCode());
			else if (!HiltonUtility.isEmpty(rqh.getVendContactCode()))
				contactIncomingRequest.put("Contact_contactCode", rqh.getVendContactCode());

			Contact contact = (Contact)contactRetrieve.executeTask(contactIncomingRequest);
			if (contact != null)
			{
				newIncomingRequest.put("AutoAwardByLine_contactName", contact.getFirstName() + " " + contact.getLastName());
				newIncomingRequest.put("AutoAwardByLine_contactPhone", contact.getPhoneNumber());
			}
		}

		if( !HiltonUtility.isEmpty(aux.getVendorName()))
		{
			newIncomingRequest.put("AutoAwardByLine_vendorName", aux.getVendorName());
		}
		else if( !HiltonUtility.isEmpty(rqh.getVendorName()))
		{
			newIncomingRequest.put("AutoAwardByLine_vendorName", rqh.getVendorName());
		}

		if( !HiltonUtility.isEmpty(aux.getVendContactCode()))
		{
			newIncomingRequest.put("AutoAwardByLine_vendContactCode", aux.getVendContactCode());
		}
		else if( !HiltonUtility.isEmpty(rqh.getVendContactCode()))
		{
			newIncomingRequest.put("AutoAwardByLine_vendContactCode", rqh.getVendContactCode());
		}

		if( !HiltonUtility.isEmpty(aux.getUdf5Code()))
		{
			newIncomingRequest.put("AutoAwardByLine_udf5Code", aux.getUdf5Code());
		}

		if ((!rqh.getRequisitionNumber().equals(rqh.getShipToCode())) && organizationId.equalsIgnoreCase("BLY07P"))
		{
			newIncomingRequest.put("AutoAwardByLine_shipToCode", aux.getUdf5Code());
		}

		if(!HiltonUtility.isEmpty(aux.getAssignedBuyer()))
		{
			newIncomingRequest.put("AutoAwardByLine_assignedBuyer", HiltonUtility.ckNull(aux.getAssignedBuyer()));
		}
		else if(!HiltonUtility.isEmpty(rqh.getAssignedBuyer()))
		{
			newIncomingRequest.put("AutoAwardByLine_assignedBuyer", HiltonUtility.ckNull(rqh.getAssignedBuyer()));
		}


		String autoAwardAllReq = PropertiesManager.getInstance(organizationId).getProperty("AUTOAWARD", "AUTOAWARDALLREQ", "N");
		if (autoAwardAllReq.equals("Y") || (itemSource.equalsIgnoreCase("XML") && catalog.equalsIgnoreCase("VINIMAYA"))) {
			incomingRequest.put("NoApprovalNeed", "Y");
			incomingRequest.put("NoApprovalNeedAutoAward", "Y");
		}

		newIncomingRequest.put("autoAwarded", Boolean.TRUE);
		newIncomingRequest.put("autoAwardedRequisition", "Y");
		boolean autoaward = true;
		if(itemSource.equalsIgnoreCase("XML") && catalog.equalsIgnoreCase("VINIMAYA"))
		{
			//incomingRequestVin.put("PoHeader_groupVendorId", aux.getVendorId());
			String bOrder = aux.getBlanketOrder() ;
        	String mString = aux.getMemoLine() + " " ;
			if (HiltonUtility.isEmpty(bOrder)) {
            	if (! HiltonUtility.isEmpty(mString)) {
            		int ps = mString.indexOf("SUPPLIER_CNTRCT_ID: ") ;
            		if (ps >= 0) {
            			ps = ps + 20;
            			int pe = mString.indexOf(" ", ps + 1) ;
            			bOrder = mString.substring(ps, pe).trim() ;
            			Log.debug(this, "SUPPLIER_CNTRCT_ID:  Start=" + ps + " End=" + pe + " Result=" + bOrder) ;
            		}
            	}
			}

			Log.debug(this, "BlanketOrder="  + bOrder) ;

			incomingRequestVin.put("RequisitionLine_blanketOrder", bOrder);

			if(HiltonUtility.isEmpty(bOrder))
			{
				autoaward = false;
				process = processLoader.loadProcess("requisition-unable-autoaward.xml") ;
			} else {
				incomingRequestVin.put("dbsession", incomingRequest.get("dbsession"));
				PoNumberRetrieveContractsByVendorIdFromVinimaya poRetrieveContract = new PoNumberRetrieveContractsByVendorIdFromVinimaya();
	            String poNumberContract = (String)poRetrieveContract.executeTask(incomingRequestVin);

	            incomingRequest.put("poNumberContract", poNumberContract);

				if(HiltonUtility.isEmpty(poNumberContract))
				{
					autoaward = false;
					process = processLoader.loadProcess("requisition-unable-autoaward.xml") ;
				} else {
					// Get iinfo from PO
					incomingRequest.put("PoHeader_poNumber",poNumberContract) ;
					incomingRequest.put("PoHeader_releaseNumber","0") ;
					PoHeaderRetrieveByNumber icPohTask = new PoHeaderRetrieveByNumber() ;
					String icPoHeader = (String) icPohTask.executeTask(incomingRequest) ;
					if (! HiltonUtility.isEmpty(icPoHeader)) {
						incomingRequest.put("PoHeader_icPoHeader", icPoHeader) ;
						PoHeaderRetrieveById pohTask = new PoHeaderRetrieveById() ;
						PoHeader poh = (PoHeader) pohTask.executeTask(incomingRequest) ;
						if (poh != null) {
							// Setup CreateFromReq values
							newIncomingRequest.put("AutoAwardByLine_vendorId", poh.getVendorId()) ;
							newIncomingRequest.put("AutoAwardByLine_vendContactCode", poh.getVendContactCode()) ;
							newIncomingRequest.put("AutoAwardByLine_vendorName", poh.getVendorName()) ;
							newIncomingRequest.put("AutoAwardByLine_contactName", poh.getContactName()) ;
							newIncomingRequest.put("AutoAwardByLine_contactPhone", poh.getContactPhone()) ;
							newIncomingRequest.put("AutoAwardByLine_vendTermsCode", poh.getTermsCode()) ;
							newIncomingRequest.put("AutoAwardByLine_contracNo", poh.getContractNo());
							newIncomingRequest.put("AutoAwardByLine_fobCode", poh.getFobCode());
							newIncomingRequest.put("AutoAwardByLine_shipViaCode", poh.getShipViaCode());
							newIncomingRequest.put("AutoAwardByLine_vendorClass", poh.getVendorClass());
							newIncomingRequest.put("AutoAwardByLine_udf11Code", poh.getUdf11Code());
							newIncomingRequest.put("AutoAwardByLine_udf12Code", poh.getUdf12Code());
							newIncomingRequest.put("AutoAwardByLine_udf13Code", poh.getUdf13Code());
							newIncomingRequest.put("AutoAwardByLine_vendorPrintFaxCode", poh.getEdiOrder());
							newIncomingRequest.put("AutoAwardByLine_poSubType", "04");

						}
					}
				}
			}
		}

		if(autoaward)
		{
			process = processLoader.loadProcess("requisitionheader-autoawardrequisition.xml") ;
		}
		try {
			process.executeProcess(newIncomingRequest);
		} catch (Exception e) {
			Log.error(this, e);
		}
    }
}

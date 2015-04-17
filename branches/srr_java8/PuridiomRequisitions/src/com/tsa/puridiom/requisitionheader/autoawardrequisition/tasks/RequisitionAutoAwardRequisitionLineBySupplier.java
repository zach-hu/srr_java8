package com.tsa.puridiom.requisitionheader.autoawardrequisition.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.contact.tasks.ContactRetrieveById;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class RequisitionAutoAwardRequisitionLineBySupplier extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

        try
        {
        	PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
            PuridiomProcess process = null;
        	Map incomingRequest = (Map)object;

        	String organizationId = (String)incomingRequest.get("organizationId");
        	List rqhList = (List)incomingRequest.get("requisitionHeaderList");

            if(rqhList != null && rqhList.size() > 0)
            {
                for (Iterator iterator = rqhList.iterator(); iterator.hasNext();)
                {
                	RequisitionHeader rqh = (RequisitionHeader) iterator.next();
                	incomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());

                	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

        			String queryString = "from RequisitionLine RequisitionLine " +
        			"WHERE RequisitionLine.icReqHeader = ? AND RequisitionLine.status = ? ";

        			queryString += "ORDER BY RequisitionLine.vendorId, RequisitionLine.udf5Code ";

        			List requisitionLineList = dbs.query(queryString, new Object[] {rqh.getIcReqHeader().toString(),DocumentStatus.REQ_APPROVED} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

        			if (requisitionLineList != null &&  requisitionLineList.size() > 0)
        			{
	        			List rqlGrouped = new ArrayList();
	        			List rqlList = new ArrayList();

	        			String previousVendorId = "";

	        			for (Iterator iterator2 = requisitionLineList.iterator(); iterator2.hasNext();)
	                    {
	        				RequisitionLine rql = (RequisitionLine) iterator2.next();

	                    	if ( rql.getVendorId().equals(previousVendorId) )
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
	                    	}
	                    }
	        			rqlGrouped.add(rqlList);


	        			for (Iterator iterator3 = rqlGrouped.iterator(); iterator3.hasNext();)
	                    {
	        				Map newIncomingRequest = new HashMap();
	        	            newIncomingRequest.put("userId", incomingRequest.get("userId"));
	        	            newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
	        	            newIncomingRequest.put("organizationId", organizationId);
	        	            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
	        	            newIncomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());

	        				List rqlListAutoAwardedByLine = (List) iterator3.next();
	        				newIncomingRequest.put("rqlListAutoAwardedByLine", rqlListAutoAwardedByLine);
	        				newIncomingRequest.put("RequisitionHeader_icReqHeader", rqh.getIcReqHeader().toString());

	        				RequisitionLine aux = (RequisitionLine)rqlListAutoAwardedByLine.get(0);

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

	        				newIncomingRequest.put("autoAwarded", Boolean.TRUE);
	        				newIncomingRequest.put("autoAwardedRequisition", "Y");
	        				process = processLoader.loadProcess("requisitionheader-autoawardrequisition.xml") ;
	            			process.executeProcess(newIncomingRequest);
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

}

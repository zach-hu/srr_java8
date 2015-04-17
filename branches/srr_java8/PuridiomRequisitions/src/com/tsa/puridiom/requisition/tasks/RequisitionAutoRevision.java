/*
 * Created on Dec 18, 2006
 */
package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Kelli
 */
public class RequisitionAutoRevision extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            DBSession dbs =(DBSession)incomingRequest.get("dbsession");
            String organizationId = (String)incomingRequest.get("organizationId") ;
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            String icPoHeaderString = "";
            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");

            if (HiltonUtility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }
            String changeSupplierLines = PropertiesManager.getInstance(organizationId).getProperty("REQ DEFAULTS","CHANGESUPPLIERLINES", "N");
    		String today = Dates.today(userDateFormat, userTimeZone);
            UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            if (("C").equalsIgnoreCase(requisitionHeader.getRequisitionType()))
            {
            	icPoHeaderString = requisitionHeader.getIcRevisedOrder().toString();
            }
            else
            {
            	icPoHeaderString = (String)incomingRequest.get("originalPoHeader_icPoHeader");
            	if (Utility.isEmpty(icPoHeaderString))
            	{
            		icPoHeaderString = "0";
            	}
            }
            BigDecimal icPoHeader = new BigDecimal(icPoHeaderString);
            String sql = "from PoHeader as PoHeader where PoHeader.icPoHeader = ? ";
            List resultList = dbs.query(sql, new Object[] {icPoHeader} , new Type[] {Hibernate.BIG_DECIMAL});

			if(resultList != null && resultList.size() > 0)
			{
			    PoHeader poHeader = (PoHeader) resultList.get(0);
			    ret = poHeader.getPoNumber();
			    incomingRequest.put("PoHeader_poNumber", poHeader.getPoNumber());
			    incomingRequest.put("PoHeader_releaseNumber", String.valueOf(poHeader.getReleaseNumber()));
			    incomingRequest.put("PoHeader_poType", poHeader.getPoType());
			    incomingRequest.put("PoHeader_fobCode", poHeader.getFobCode());
			    incomingRequest.put("PoHeader_inspectionReqd", poHeader.getInspectionReqd());
			    incomingRequest.put("PoHeader_prePaid", poHeader.getPrePaid());
			    incomingRequest.put("PoHeader_termsCode", poHeader.getTermsCode());
			    incomingRequest.put("PoHeader_buyerCode", poHeader.getBuyerCode());
			    incomingRequest.put("PoHeader_contractNo", poHeader.getContractNo());

		        incomingRequest.put("PoHeader_udf1Code", poHeader.getUdf1Code());
		        incomingRequest.put("PoHeader_udf2Code", poHeader.getUdf2Code());
		        incomingRequest.put("PoHeader_udf3Code", poHeader.getUdf3Code());
		        incomingRequest.put("PoHeader_udf4Code", poHeader.getUdf4Code());
		        incomingRequest.put("PoHeader_udf5Code", poHeader.getUdf5Code());
		        incomingRequest.put("PoHeader_udf6Code", poHeader.getUdf6Code());
		        incomingRequest.put("PoHeader_udf7Code", poHeader.getUdf7Code());
		        incomingRequest.put("PoHeader_udf8Code", poHeader.getUdf8Code());
		        incomingRequest.put("PoHeader_udf9Code", poHeader.getUdf9Code());
		        incomingRequest.put("PoHeader_udf10Code", poHeader.getUdf10Code());
		        incomingRequest.put("PoHeader_ediOrder", poHeader.getEdiOrder());
	        	incomingRequest.put("PoHeader_buyerCode", poHeader.getOwner());
	        	incomingRequest.put("PoHeader_lastChgBy", poHeader.getLastChgBy());
	        	if(changeSupplierLines.equalsIgnoreCase("N"))
				{
	        		incomingRequest.put("PoHeader_vendorId", poHeader.getVendorId());
	        		incomingRequest.put("PoHeader_vendorName", poHeader.getVendorName());
		        	incomingRequest.put("PoHeader_vendContactCode", poHeader.getVendContactCode());
		    		incomingRequest.put("PoHeader_contactName", poHeader.getContactName());
		    		incomingRequest.put("PoHeader_contactAddr", poHeader.getContactAddr());
		    		incomingRequest.put("PoHeader_revisionValue", poHeader.getRevisionValue().toString());
		    		incomingRequest.put("PoHeader_contactPhone", poHeader.getContactPhone());
		    		incomingRequest.put("PoHeader_contactMobilePhone", poHeader.getContactMobilePhone());
				}
	    		incomingRequest.put("PoHeader_approved", poHeader.getApproved());
	    		incomingRequest.put("PoHeader_appBy", poHeader.getAppBy());
	    		incomingRequest.put("PoHeader_appDate", today);

			    this.setStatus(Status.SUCCEEDED);
			}
			else
			{
			    this.setStatus(Status.FAILED);
	            throw new TsaException("Original Order was not found!");
			}

            incomingRequest.put("createRevisionFromReq", "true");
            incomingRequest.put("autoReleased", Boolean.TRUE);
            incomingRequest.put("poFromRevision", "Y");
            incomingRequest.put("PoHeader_icPoHeader",ukg.getUniqueKey().toString());

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        return ret;
    }
}

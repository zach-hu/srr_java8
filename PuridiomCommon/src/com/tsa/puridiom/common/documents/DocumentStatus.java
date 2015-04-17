/*
 * Created on Aug 26, 2003
 */
package com.tsa.puridiom.common.documents;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.owasp.esapi.Encoder;
import org.owasp.esapi.reference.DefaultEncoder;

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Administrator
 */
public class DocumentStatus extends GeneralType
{
	/** MSR Status */
	public static final String REQ_PLANNING = "0500" ;
	public static final String REQ_PLANNING_RECALLED = "0505" ;
	public static final String REQ_PLANNING_REJECTED = "0510" ;
	public static final String REQ_PLANNING_APPROVING = "0530" ;
	public static final String REQ_PLANNING_APPROVED = "0535" ;
	public static final String REQ_PLANNING_SOURCING = "0540" ;
	public static final String REQ_PLANNING_SOURCED = "0545" ;
	public static final String REQ_ORDERED = "0600" ;
	public static final String REQ_PARTIALLYRECEIVED = "0650" ;
	public static final String REQ_RECEIVED = "0700" ;
	public static final String REQ_PARTIALLYKITTED = "0705" ;
	public static final String REQ_KITTED = "0710" ;
	public static final String REQ_PARTIALLYISSUED = "0715" ;
	public static final String REQ_ISSUED = "0720" ;
	public static final String REQ_RESERVED = "0800" ;
	public static final String REQ_CLOSED = "0900" ;

	/** Requisition Status */
    public static final String REQ_INPROGRESS = "1000" ;
    public static final String REQ_REJECTED = "1005";
    public static final String REQ_RECALLED = "1010" ;
    public static final String REQ_FORWARDED = "1015";
    public static final String REQ_APPROVING = "1030" ;
    public static final String REQ_APPROVED = "1035" ;
    public static final String REQ_APPROVED_PURCHASING = "1040";
    public static final String REQ_AMMENDED = "1045" ;

    /** Request For Quotes Status */
    public static final String RFQ_INPROGRESS = "2000";
    public static final String RFQ_OPENSOLICITATION = "2005";
    public static final String RFQ_REJECTED = "2010";
    //public static final String RFQ_BIDSRECEIVED = "2010";
    public static final String RFQ_OPENAMENDMENT = "2015";
    public static final String RFQ_APPROVING = "2017";
    //public static final String RFQ_APPROVED = "2019";
    public static final String RFQ_PURCHASING = "2020";
    public static final String RFQ_AMENDED = "2025" ;
    public static final String RFQ_PRICED = "2030" ;

    /*  used for HISTORY ONLY  */
    public static final String BUYER_REMARKS = "2800";
    /*  used for HISTORY ONLY  */

    public static final String CT_INPROGRESS = "2900";
    public static final String CT_REJECTED = "2905";
    public static final String CT_APPROVING = "2920";
    public static final String CT_AWARDED = "2930";
    public static final String CT_CLOSED = "2990";

    /** Order Status */
    public static final String PO_INPROGRESS = "3000";
    public static final String PO_REJECTED = "3005";
    //public static final String PO_PURCHASING = "3005";
    //public static final String PO_RELEASING = "3010";
    public static final String PO_APPROVING = "3020";
    public static final String PO_AWARDED = "3030";
    public static final String PO_SHIPPING = "3035";
    public static final String PO_SHIPPED = "3040";
    public static final String PO_AMENDED = "3045";

    /** Receipt Status */
    public static final String RCV_INPROGRESS = "4000";
    public static final String RCV_PENDINGFINALIZATION = "4003";
    public static final String RCV_STEP_1 = "4010";  				/** INSPECTING */
    public static final String RCV_STEP_2 = "4020";  				/** MARKING */
    public static final String RCV_STEP_3 = "4030";  				/** DELIVERING */
    public static final String RCV_PARTIALLYRECEIVED = "4100";
    public static final String RCV_RECEIVED = "4150";
    public static final String RCV_INVOICED = "4200";
    public static final String RCV_REJECTED = "4300";

    public static final String INSP_PENDING = "4600" ;
    public static final String INSP_FAILED = "4610" ;
    public static final String INSP_ASSIGNED = "4620" ;
    public static final String INSP_TESTING = "4630" ;
    public static final String INSP_DISCREPANCY = "4640" ;
    public static final String INSP_PASSED = "4650";

    public static final String INV_INPROGRESS = "5000";
    public static final String INV_PARTIAL = "5003";
    public static final String INV_BACKORDERED = "5005";
    public static final String INV_DISBURSED = "5010";
    public static final String INV_APPROVING = "5115";
    public static final String INV_APPROVED = "5130";

    public static final String INV_RETURNING = "5155";
    public static final String INV_RETURNED = "5160";

    /** Invoice Status */
    public static final String IVC_INPROGRESS = "6000";
    public static final String IVC_RECALLED = "6001";
    public static final String IVC_APPROVING = "6005";
    public static final String IVC_PENDINGRO = "6006";
    public static final String IVC_PENDINGCO = "6007";
    public static final String IVC_PENDINGPC = "6008";
    public static final String IVC_PENDINGTC = "6009";
    public static final String IVC_APPROVED = "6010";
    public static final String IVC_ONHOLD = "6015";
    public static final String IVC_REJECTED = "6020";
    public static final String IVC_PENDING_PAYMENT = "6025";
    public static final String IVC_PAID = "6030";
    public static final String IVC_HOLDCLOSED = "6035";
    public static final String IVC_RECVDCLOSED = "6040";

    public static final String PY_NOTINVOICED = "6050";
    public static final String PY_PARTIALLYINVOICED = "6055";
    public static final String PY_FULLYINVOICED = "6060";
    public static final String PY_PENDINGAPPROVAL = "6065";
    public static final String PY_PARTIALLYAPPROVED = "6070";
    public static final String PY_FULLYAPPROVED = "6075";
    public static final String PY_PARTIALLYPAID = "6080";
    public static final String PY_FULLYPAID = "6085";

    public static final String AST_ACTIVE = "7000";
    public static final String AST_DISPOSED = "7005";
    public static final String AST_SOLD = "7010";
    public static final String AST_STOLEN = "7015";
    public static final String AST_LOST = "7020";
    public static final String AST_DAMAGED = "7030";
    public static final String AST_SCRAPPED = "7040";

    public static final String SALE_INPROGRESS = "8000";
    public static final String SALE_BIDS_PENDING = "8005";
    public static final String SALE_OPENAMENDMENT = "8015";
    public static final String SALE_AMENDED = "8025" ;
    public static final String SALE_BIDS_RECEIVED = "8030" ;

    public static final String SUP_PERFORMANCE_INCOMPLETE = "10000";
    public static final String SUP_PERFORMANCE_INPROGRESS = "10010";
    public static final String SUP_PERFORMANCE_COMPLETE = "10020";

    public static final String HISTORY = "9000";
    public static final String CLOSED = "9010";
    public static final String CANCELLED = "9020";
    public static final String TEMPLATE = "9050";
    public static final String DELETE_INPROGRESS = "9999";

    public static final String SENDQUEUE_NEW = "00";
    public static final String SENDQUEUE_PROCESSED = "10";
    public static final String SENDQUEUE_ERROR= "99";

    public static final String VENDOR_PREREGISTERED = "00";
    public static final String VENDOR_PREQUALIFIED = "10";
    public static final String VENDOR_REJECTED= "99";

    public static final String EVALUATION_INCOMPLETE = "20000";
    public static final String EVALUATION_PENDING = "20010";
    public static final String EVALUATION_COMPLETE = "20020";

    public static final String USERLOG_SUCCESSFUL_LOGIN = "11";
    public static final String USERLOG_INVALID_PASSWORD = "12";
    public static final String USERLOG_INVALID_USERID = "13";
    public static final String USERLOG_MAXIMUM_LOGINS = "14";
    public static final String USERLOG_IDLE_TIME = "15";
    public static final String USERLOG_SUCCESSFUL_LOGOUT = "16";
    
    private static final Encoder encoder = DefaultEncoder.getInstance();

    public static String toString(String status)
    {
        return DocumentStatus.toString(status, "PURIDIOM");
    }
    public static String toString(String status, String organizationId)
    {
        String docStatus = "";
        try
        {
            if (status == null || status.equals("null"))
            {
                return "";
            }
            else
            {
            	docStatus = DictionaryManager.getInstance("doc-status", organizationId).getProperty(status, status);
            }
        }
        catch (Exception e)
        {
            Log.error("DocumentStatus", "Error loading status: " + status);
            Log.error("DocumentStatus", e);
        }
        return encoder.encodeForHTML(docStatus);
    }
    public static String toValue(String statusString, String organizationId)
    {
        String docStatus = "";
        try
        {
            if (Utility.isEmpty(statusString) || statusString.equals("null"))
            {
                return "";
            }
            else
            {
                Map statusMap = DictionaryManager.getInstance("doc-status", organizationId).getPropertyMap();
                Iterator statusKeys = statusMap.keySet().iterator();

                while (statusKeys.hasNext()) {
                    String	key = (String) statusKeys.next();
                    String	value = (String) statusMap.get(key);

                    if (value.equalsIgnoreCase(statusString)) {
                        docStatus = key;
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log.error("DocumentStatus", "Error getting value for status: " + statusString);
            Log.error("DocumentStatus", e.toString());
        }
        return docStatus;
    }

    public static List getAllValues(String organizationId)
	{
    	return getAllValues("doc-status", organizationId);
	}

	public static Map getPropertyMap(String organizationId, String startCode)
	{
		return getPropertyMapOrderedByIntKey("doc-status", organizationId, startCode);
	}

	public static Map getPropertyMap(String organizationId, String startCode, String lastCode)
	{
		return getPropertyMapOrderedByIntKey("doc-status", organizationId, startCode, lastCode);
	}
}

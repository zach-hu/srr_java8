package com.tsa.puridiom.requisitionmanager;

import com.tsa.puridiom.approvals.tasks.ApprovalLogRetrieveByPendingApproval;
import com.tsa.puridiom.approvals.tasks.ApproverLookup;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RequisitionManager
{
    private static RequisitionManager INSTANCE = new RequisitionManager();
    private HashMap organizations = new HashMap();

    private RequisitionManager()
    {
        super();
    }
    /**
     * @return com.tsa.puridiom.usermanager.UserManager
     */
    public static RequisitionManager getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new RequisitionManager();
        }
        return INSTANCE;
    }

    public RequisitionHeader getRequisitionHeader(String organizationId, String icReqHeader) throws java.lang.Exception
    {
        RequisitionHeader requisitionHeader = null;

        try
        {
            if (Utility.isEmpty(icReqHeader) || Utility.isEmpty(organizationId))
            {
            	//requisitionHeader = new RequisitionHeader();
                return requisitionHeader;
            }
            else
            {
                organizationId = organizationId.toUpperCase();

                Map pos = new HashMap();
                if (this.organizations.containsKey(organizationId))
                {
                    pos = (Map) this.organizations.get(organizationId);
                } else System.out.println("RequistionManager: New Organization: " + organizationId);
                if (pos.containsKey(icReqHeader))
                {
                	requisitionHeader = (RequisitionHeader) pos.get(icReqHeader);
                }
                else
                {
                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", organizationId);
                    //processParameters.put("icPoHeader", icPoHeader.toString());
                    processParameters.put("RequisitionHeader_icReqHeader", icReqHeader);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("requisitionheader-retrieve-by-id.xml");

                    process.executeProcess(processParameters);

                    requisitionHeader = (RequisitionHeader) processParameters.get("requisitionHeader");

                    this.organizations.put(organizationId, pos);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return requisitionHeader;
        }
    }

    public String getCurrentApprover(String organizationId, String icReqHeader) throws java.lang.Exception
    {
        String currentApprover = "";

        try
        {
            if (Utility.isEmpty(icReqHeader) || Utility.isEmpty(organizationId))
            {
            	//requisitionHeader = new RequisitionHeader();
                return currentApprover;
            }
            else
            {
                organizationId = organizationId.toUpperCase();
                HashMap processParameters = new HashMap();
                processParameters.put("organizationId", organizationId);
                //processParameters.put("icPoHeader", icPoHeader.toString());
                processParameters.put("ApprovalLog_icHeader", icReqHeader);

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                PuridiomProcess process = processLoader.loadProcess("approvallog-retrieve-current-approver.xml");
                    
                process.executeProcess(processParameters);

                ApprovalLog approvalLog = (ApprovalLog) processParameters.get("approvalLog");

                if(approvalLog != null){
                	currentApprover = HiltonUtility.ckNull(approvalLog.getUserId());
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return currentApprover;
        }
    }
     /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[ClassName=com.tsa.puridiom.requisitionmanager.RequisitionManager]");
        return sb.toString();
    }
}

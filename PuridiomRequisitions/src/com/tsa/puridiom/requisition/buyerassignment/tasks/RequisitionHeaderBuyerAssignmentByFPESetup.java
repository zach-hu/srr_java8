/*
 * Created on Apr 19, 2005
 */
package com.tsa.puridiom.requisition.buyerassignment.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.VendorBuyerRel;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class RequisitionHeaderBuyerAssignmentByFPESetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            String reqOwner = HiltonUtility.ckNull(reqHeader.getOwner());
            String icReqHeader = HiltonUtility.ckNull(reqHeader.getIcReqHeader()).toString();
            incomingRequest.put("assignTo",reqOwner);
            incomingRequest.put("RequisitionHeader_icReqHeader",icReqHeader);
            Log.debug(this, "Requisition Header FPE Assignment to "+reqOwner);
           
            ret = reqHeader;
            this.setStatus(Status.SUCCEEDED);

        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            Log.error(this, "Exception[" + e.getMessage() + "]");
            throw e;
        }

        return ret;
    }

}

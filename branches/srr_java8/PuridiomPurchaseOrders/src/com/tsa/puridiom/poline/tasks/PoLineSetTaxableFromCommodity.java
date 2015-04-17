/*
 * Created on Mar 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Commodity;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.List;

public class PoLineSetTaxableFromCommodity extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            DBSession dbs = (DBSession)incomingRequest.get("dbsession");
            String organizationId = (String) incomingRequest.get("organizationId");
            String commodityCode = (String) incomingRequest.get("PoLine_commodity");
            String taxable = "Y";
            
            if(!HiltonUtility.isEmpty(commodityCode))
            {
            	Commodity commodity = CommodityManager.getInstance().getCommodity(organizationId, commodityCode);
            	taxable = commodity.getTaxable();
            }
            incomingRequest.put("PoLine_taxable", taxable);
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }

        return ret;
    }
}
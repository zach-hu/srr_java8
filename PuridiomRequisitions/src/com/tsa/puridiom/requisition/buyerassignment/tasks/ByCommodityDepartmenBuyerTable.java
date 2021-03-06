/*
 * Created on Apr 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.buyerassignment.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;


import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.CommodityDepartmentBuyer;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ByCommodityDepartmenBuyerTable extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            List lines = (List)incomingRequest.get("requisitionLineList");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            Date d_today = Dates.getCurrentDate(userTimeZone);
            String departmentCode = header.getDepartmentCode();

            Log.debug(this, "Assign By CommodityDepartmentBuyer table");

            for(int i = 0; i < lines.size(); i++)
            {
                RequisitionLine rql = (RequisitionLine)lines.get(i);
                Log.debug(this, "requisition line: " + rql.getLineNumber().toString());

                if(!rql.getAssigned())
                {
	                String lineBuyer = HiltonUtility.ckNull(this.checkTypes(rql, header));
	                if(Utility.isEmpty(lineBuyer))
	                {
	                    lineBuyer = HiltonUtility.ckNull(this.byCommodityDepartmentBuyer(rql, incomingRequest, departmentCode));
	                }

	                //if(Utility.isEmpty(lineBuyer)){        lineBuyer = "PURCHASING";  }
	                if( ("PURCHASING,UNASSIGNED").indexOf(lineBuyer) < 0  && !Utility.isEmpty(lineBuyer) )
	                {
	                	rql.setAssignedBuyer(lineBuyer);
	 	                rql.setAssignedDate(d_today);
	                	rql.setAssigned(true);
	                }
	                lines.set(i, rql);
                }
            }

            ret = lines;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Assign By DepartmentBuyer-Table Failed!");
        }

        return ret;
    }

    private String checkTypes(RequisitionLine rql, RequisitionHeader header)
    {
        Log.debug(this, "By Commodity checkTypes");
        String sBuyer = "";

        if(header.getRequisitionType().equals(RequisitionType.PRICING_REQUEST) && rql.getStatus().equals(DocumentStatus.REQ_FORWARDED))
        {
            String compare = "PURCHASING,UNASSIGNED";
            if(compare.indexOf(rql.getAssignedBuyer()) < 0)
            {
                sBuyer = HiltonUtility.ckNull(rql.getAssignedBuyer());
            }
        }
        else if(header.getRequisitionType().equals(RequisitionType.SUPPLY_REQUEST) || header.getRequisitionType().equals(RequisitionType.IMPRINT_REQUEST) || header.getRequisitionType().equals(RequisitionType.TRANSFER_REQUEST))
        {
            sBuyer = "INVENTORY";
        }
        Log.debug(this, "checkTypes buyer: " + sBuyer);

        return sBuyer;
    }

    private String byCommodityDepartmentBuyer(RequisitionLine rql, Map incomingRequest, String departmentCode)
    {
        String sBuyer = "";
        //Buyer Assigment by Commodity
        String commodityCode = rql.getCommodityCode();
        //String departmentCode = rql.getDepartmentCode();
        Log.debug(this, "Commodity Code: " + commodityCode);
        Log.debug(this, "Department Code: " + departmentCode);

        if(!Utility.isEmpty(commodityCode)&&!Utility.isEmpty(departmentCode))
        {
            try
    		{
    			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

    			String queryString = "from CommodityDepartmentBuyer as cdb where cdb.commodity = ? AND cdb.departmentCode = ?";
    			List resultList = dbs.query(queryString, new Object[] {commodityCode, departmentCode } , new Type[] { Hibernate.STRING, Hibernate.STRING} ) ;

    			if (resultList != null && resultList.size() > 0)
    			{
    				CommodityDepartmentBuyer objComDepBuyer = (CommodityDepartmentBuyer)resultList.get(0);
    				sBuyer = HiltonUtility.ckNull(objComDepBuyer.getUserId());
    			}

    			if ( HiltonUtility.isEmpty(sBuyer))
    			{
    				String filterDepartment = this.buildWildcardFilters(departmentCode);
    				queryString = "from CommodityDepartmentBuyer as cdb where cdb.commodity = ? AND ( " + filterDepartment + " )";
    				resultList = dbs.query(queryString, new Object[] {commodityCode} , new Type[] {Hibernate.STRING} ) ;

	    			if (resultList != null && resultList.size() > 0)
	    			{
	    				CommodityDepartmentBuyer objComDepBuyer = (CommodityDepartmentBuyer)resultList.get(0);
	    				sBuyer = HiltonUtility.ckNull(objComDepBuyer.getUserId());
	    			}
    			}

    			this.setStatus(dbs.getStatus()) ;

    		}
            catch (Exception e)
            {
                Log.error(this, "Commodity " + commodityCode + " and DepartmentCode " + departmentCode + " does not have a Buyer Associated!");
                sBuyer = "";
            }

            Log.debug(this, "buyer: " + sBuyer);
        }

        return sBuyer;
    }

    String buildWildcardFilters(String filterValue) {
		String filters = " 1 = 0 " ;
		for (int i = 1; i <= filterValue.length(); i++) {
			filters += " OR cdb.departmentCode = '" + filterValue.substring(0, i);
			if (i < filterValue.length()) {
				filters += "%'";
			}
			else{
				filters += "' ";
			}
		}
		return filters;
	   }
}

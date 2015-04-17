/*
 * Created on Apr 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.po.buyerassignment.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.CommodityDepartmentBuyer;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
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
            PoHeader poheader = (PoHeader)incomingRequest.get("poHeader");
            List lines = (List)incomingRequest.get("poLineList");
            RequisitionHeader reqheader = (RequisitionHeader) incomingRequest.get("requisitionHeader");

            if (reqheader != null)
            {
            	poheader.setBuyerCode(reqheader.getBuyer());
            }

            String lineBuyer = HiltonUtility.ckNull(poheader.getBuyerCode());

            Log.debug(this, "Assign By CommodityDepartmentBuyer table");
            
            if(Utility.isEmpty(lineBuyer))
            {
            
	            for(int i = 0; i < lines.size(); i++)
	            {
	                PoLine pol = (PoLine)lines.get(i);
	                Log.debug(this, "po line: " + pol.getLineNumber().toString());
	
	                if(Utility.isEmpty(lineBuyer))
	                {
	                    lineBuyer = this.byCommodityDepartmentBuyer(pol, incomingRequest);
	                }
	                if(!Utility.isEmpty(lineBuyer))
	                {
	                	break;
	                }
	            }

            }
            
            if(Utility.isEmpty(lineBuyer))
            {
            	poheader.setBuyerCode(lineBuyer);
            }
            ret = poheader;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            Log.error(this, "Assign buyer by CommodityDepartmentBuyer-Table Failed : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return ret;
    }

    private String byCommodityDepartmentBuyer(PoLine pol, Map incomingRequest)
    {
        String sBuyer = "";
        //Buyer Assignment by Commodity
        String commodityCode = pol.getCommodity();
        String departmentCode = pol.getDepartmentCode();
        Log.debug(this, "Commodity Code: " + commodityCode);
        Log.debug(this, "Department Code: " + departmentCode);

        if(!Utility.isEmpty(commodityCode)&&!Utility.isEmpty(departmentCode))
        {
            try
    		{
    			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

    			String queryString = "from CommodityDepartmentBuyer as cdb where cdb.commodity = ? and cdb.departmentCode = ? ";
    			List resultList = dbs.query(queryString, new Object[] {commodityCode, departmentCode} , new Type[] {Hibernate.STRING, Hibernate.STRING} ) ;

    			if (resultList != null && resultList.size() > 0)
    			{
    				CommodityDepartmentBuyer objComDepBuy = (CommodityDepartmentBuyer)resultList.get(0);
    				sBuyer = objComDepBuy.getUserId();
    			}
    			if (resultList != null && resultList.size() > 0){
    				String filterDepartment = this.buildWildcardFilters(departmentCode);
    				queryString = "from CommodityDepartmentBuyer as cdb where cdb.commodity = ? AND " + filterDepartment;
    				resultList = dbs.query(queryString, new Object[] {commodityCode} , new Type[] {Hibernate.STRING} ) ;
    				this.setStatus(dbs.getStatus()) ;
    			}
    			if (resultList != null && resultList.size() > 0)
    			{
    				CommodityDepartmentBuyer objComDepBuyer = (CommodityDepartmentBuyer)resultList.get(0);
    				sBuyer = objComDepBuyer.getUserId();
    			}
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
		String filters = " cdb.departmentCode = '" + filterValue + "'";
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

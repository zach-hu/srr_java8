package com.tsa.puridiom.poheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class PoHeaderSetFieldsToLines extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
            List      poLineList = (List) incomingRequest.get("poLineList");
    		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
    		
    		String updateUdf10Code =  HiltonUtility.ckNull((String) incomingRequest.get("updateUdf10"));
    		String udf10Code = HiltonUtility.ckNull(poHeader.getUdf10Code());
    		
    		if ( poLineList != null && poLineList.size() > 0)
            {
	            for (int i = 0; i < poLineList.size(); i++)
    			{
    				PoLine poLine = (PoLine) poLineList.get(i);

    				if (updateUdf10Code.equalsIgnoreCase("TRUE"))
    				{
    					poLine.setUdf10Code(udf10Code); 
    					Log.debug(this, "updated udf10 : " + udf10Code);
    				}
    				dbs.update(poLine);
    			}	            
            }
            result = poHeader;
            this.status = dbs.getStatus();

        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            Log.error(this, e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return result;
    }
}
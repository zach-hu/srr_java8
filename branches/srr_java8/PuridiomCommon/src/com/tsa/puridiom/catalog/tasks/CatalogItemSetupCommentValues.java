/**
 * 
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny
 *
 */
public class CatalogItemSetupCommentValues extends Task
{
    public Object executeTask(Object object) throws Exception
    {
    	Map incomingRequest = (Map) object;
		Object result = null;
		
        try
        {
        	Catalog catalog = (Catalog) incomingRequest.get("catalog");
			CatalogItem catalogItem = (CatalogItem) incomingRequest.get("catalogItem");
			
        	incomingRequest.put("DocComment_icHeader", catalog.getIcHeaderComment().toString());
    		incomingRequest.put("DocComment_icLine", catalogItem.getIcLineComment().toString());
    		
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
        
        return result;
    }

}

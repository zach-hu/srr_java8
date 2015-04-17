/*
 * Created on Nov 30, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.entity.Catalog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CatalogItemAllowReleases extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            Catalog	catalog = (Catalog)incomingRequest.get("catalog");
            ItemLookup item = (ItemLookup)incomingRequest.get("itemLookup");

            if (catalog == null || item == null) {
                return null;
            }
            if(catalog.getAllowReleases().equalsIgnoreCase("Y"))
            {
                item.setAutoRelease(true);
            }
            else
            {
                item.setAutoRelease(false);
            }
            ret = item;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}

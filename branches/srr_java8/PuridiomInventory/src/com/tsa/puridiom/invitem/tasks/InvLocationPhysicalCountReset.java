/*
 * Created on Oct 25, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invitem.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author jeff
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvLocationPhysicalCountReset extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String		s_location = (String)incomingRequest.get("InvLocation_itemLocation") ;
            String updateSql = "UPDATE inv_location SET phys_actual = 0, phys_original = qty_on_hand WHERE " +
            				   "item_location = ?";

            DBSession dbs = (DBSession)incomingRequest.get("dbsession");
            Object [] args = {s_location};
            Integer [] types = {Types.VARCHAR};
            dbs.sqlUpdate(updateSql, args, types);
            incomingRequest.put("successPage", "inventory/physical_menu_standard.jsp?resetStatus=Success") ;
            this.setStatus(dbs.getStatus());

        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }
}

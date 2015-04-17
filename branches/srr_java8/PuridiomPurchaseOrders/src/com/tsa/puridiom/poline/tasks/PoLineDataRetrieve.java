/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class PoLineDataRetrieve extends Task
{
    /*
     * (non-Javadoc)
     *
     * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {

        Map incomingRequest = (Map) object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
        this.setStatus(Status.SUCCEEDED);

        PuridiomProcessLoader processLoader = new PuridiomProcessLoader(
                (String) incomingRequest.get("organizationId"));
        PuridiomProcess process = processLoader
                .loadProcess("polinedata-retrieve.xml");

        List poLineList = (List) incomingRequest.get("poLineList");
        for (Iterator it = poLineList.iterator(); it.hasNext();)
        {
            incomingRequest.put("poLine", (PoLine) it.next());
            process.executeProcess(incomingRequest);
            this.setStatus(process.getStatus());
            if (process.getStatus() == Status.FAILED)
            {
                break;
            }

        }

        return poLineList;
    }

}

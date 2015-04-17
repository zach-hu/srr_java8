/*
 * Created on January 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


/**
 * @author Renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class AssetUnPrintedPdf extends Task
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
            List assetList = (List) incomingRequest.get("assetList");
            String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();

            EntityDataSource ds = new EntityDataSource(assetList); // estuvo comentado
            parameters.put("datasource",ds); // estuvo comentado

            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);

            parameters.put("formType", "BarbecueReport.jasper"); // solo para probar


            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(assetList);
            parameters.put("datasource", itemDS);
            String webreport = (String)incomingRequest.get("webreport");

            if(Utility.isEmpty(webreport)){		webreport = "Y";	}
            parameters.put("webreport", webreport);

          //parameters.put("formType", "passetunprited.jasper");
            parameters.put("namePdf",  "reporte.pdf"); //barcode
            //ret = JasperReportsHelper.printPdf(parameters);
           ret = JasperReportsHelper.printPdf(parameters);
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

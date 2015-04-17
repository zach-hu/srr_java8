/*
 * Created on July 17, 2007
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class PoEmailVendorEvaluation extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            Log.debug(this, "printing po for po: " + poHeader.getPoNumber());

            String organizationId = (String)incomingRequest.get("organizationId");

            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(poHeader);
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId.toUpperCase());
            Log.debug(this, "printing po pdf [organization : "+ organizationId + "]");
            parameters.put("entity", poHeader);
            String imgUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
            parameters.put("imgUrl", imgUrl);
            Log.debug(this, "imgUrl "+ imgUrl);

            parameters.put("formType", "po-email-vendor-evaluation.jasper");

            HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(poHeader.getPoLineList());
            parameters.put("linesDS", itemDS);
            parameters.put("lines", poHeader.getPoLineList());
            EntityDataSource shiptoDS = new EntityDataSource(poHeader.getShipToAddress());
            parameters.put("shipToDS", shiptoDS);
            EntityDataSource vendorDS = new EntityDataSource(poHeader.getVendorAddress());
            parameters.put("vendorDS", vendorDS);
            EntityDataSource billToDS = new EntityDataSource(poHeader.getBillToAddress());
            parameters.put("billToDS", billToDS);
            String siteUrl = DictionaryManager.getInstance("host", organizationId).getProperty("reportsUrl");
            parameters.put("GCS_SITE_URL", siteUrl);
            parameters.put("uid", incomingRequest.get("userId"));
            parameters.put("mid", incomingRequest.get("mid"));

            String webreport = (String)incomingRequest.get("webreport");
            if(webreport == null) {     webreport = "Y";    }
            Log.debug(this, "this is a webreport: " + webreport);

            parameters.put("namePdf", "po" + poHeader.getDisplayPoNumber() + ".html");
            parameters.put("webreport", webreport);
            parameters.put("format", "html");
            ret = JasperReportsHelper.poEmailApp(parameters);

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

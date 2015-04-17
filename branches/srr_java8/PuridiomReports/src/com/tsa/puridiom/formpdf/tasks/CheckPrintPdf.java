package com.tsa.puridiom.formpdf.tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class CheckPrintPdf extends Task
{

	public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String organizationId = (String)incomingRequest.get("organizationId");
            //String webreport = "R";

            List checkList = (List)incomingRequest.get("checkList");

            List jasperPrintList = new ArrayList();
            if(checkList == null)
            {
            	checkList = new ArrayList();
            }
            HibernateQueryResultDataSource checkListDS = new HibernateQueryResultDataSource(checkList);
            HibernateQueryResultDataSource checkListDS2 = new HibernateQueryResultDataSource(checkList);

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date date = new Date();
            String dateString = format.format(date);

            Map parameters = new HashMap();
            Map parameters2 = new HashMap();

            parameters.put("oid", organizationId.toUpperCase());
            parameters2.put("oid", organizationId.toUpperCase());
            parameters.put("organizationId", organizationId.toUpperCase());
            parameters2.put("organizationId", organizationId.toUpperCase());
            parameters.put("checkListDS", checkListDS);
            parameters2.put("checkListDS", checkListDS2);
            parameters.put("jasperPrintList", jasperPrintList);
            parameters2.put("jasperPrintList", jasperPrintList);

            parameters.put("nameTxt", "vchextract/CH_" + dateString + ".txt");
            parameters2.put("nameTxt", "vchextract/RA_" + dateString + ".txt");
            parameters.put("formType", "check-txt.jasper");
            parameters2.put("formType", "remittance-txt.jasper");

            //parameters.put("webreport", webreport);

            ret = JasperReportsHelper.printTxt(parameters);
            ret = JasperReportsHelper.printTxt(parameters2);

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

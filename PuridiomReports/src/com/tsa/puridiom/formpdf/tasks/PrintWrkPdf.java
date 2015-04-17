/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import com.tsa.puridiom.common.documents.RfqType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsa.puridiom.rfq.RfqBidWorksheet;
import com.tsa.puridiom.rfq.worksheet.WorkSheetHeader;
import com.tsa.puridiom.rfq.worksheet.WorkSheetManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PrintWrkPdf extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RfqBidWorksheet rfqBidWorksheet = (RfqBidWorksheet) incomingRequest.get("rfqBidWorksheet");
		    RfqHeader rfqHeader = rfqBidWorksheet.getRfqHeader();
            String organizationId = (String)incomingRequest.get("organizationId");
            String format = HiltonUtility.ckNull((String)incomingRequest.get("format"));

            Map parameters = new HashMap();

            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            parameters.put("entity", rfqHeader);
            String reportName = "worksheet";

            if(format.equalsIgnoreCase("xls"))
            {
            	reportName = reportName + "-xls.jasper";
            }
            else
            {
                reportName = reportName + ".jasper";
            }

            parameters.put("formType", reportName);
            String webreport = HiltonUtility.ckNull((String)incomingRequest.get("webreport"));
            if(Utility.isEmpty(webreport)){		webreport = "Y";	}
            parameters.put("webreport", webreport);

            boolean hasQuestions = (PropertiesManager.getInstance(organizationId).getProperty("RFQ OPTIONS", "SHOW QUESTION RESPONSE", "N").equalsIgnoreCase("Y")) ? true : false;
            List workSheetByPage = WorkSheetManager.getWorkSheetList(rfqBidWorksheet);
            List workSheetHeaderByPage = WorkSheetManager.getWorkSheetHeaderList(rfqBidWorksheet, organizationId);
            List workSheetQuestionByPage = new ArrayList();

            if (hasQuestions)
			{
                parameters.put("sizewq", 1);
				workSheetQuestionByPage = WorkSheetManager.getWorkSheetQuestionList(rfqBidWorksheet);
			}

            List reportsToMerge = new ArrayList();
            List reportsToQuestions = new ArrayList();

            for(int page = 0; page <workSheetByPage.size(); page++)
            {
            	WorkSheetHeader wrkSheetHeader = (WorkSheetHeader)workSheetHeaderByPage.get(page);
            	EntityDataSource ds = new EntityDataSource(wrkSheetHeader);
                parameters.put("datasource", ds);
                parameters.put("formType", reportName);
                parameters.put("linesDS", new HibernateQueryResultDataSource((List)workSheetByPage.get(page)));
                parameters.put("lines", workSheetByPage.get(page));

                if(format.equalsIgnoreCase("xls"))
                {
                	parameters.put("namePdf", RfqType.toString(rfqHeader.getRfqType(), organizationId) + "[" + rfqHeader.getRfqNumber() + "].xls");
                }
                else
                {
                	parameters.put("namePdf", RfqType.toString(rfqHeader.getRfqType(), organizationId) + "[" + rfqHeader.getRfqNumber() + "][" + (page + 1) + "]" + ".pdf");
                }
                parameters.put("groupNumber", String.valueOf(page + 1));
                EntityDataSource shiptoDS = new EntityDataSource(rfqHeader.getShipToAddress());
                parameters.put("shipToDS", shiptoDS);

                reportsToMerge.add(JasperReportsHelper.getJasperPrint(parameters));
                parameters.put("size",((List)((JasperPrint)reportsToMerge.get(0)).getPages()).size());

                if (hasQuestions)
				{
					parameters.put("datasource", new EntityDataSource(wrkSheetHeader));
					parameters.put("questionsDS", new HibernateQueryResultDataSource((List) workSheetQuestionByPage.get(page)));
					if(format.equalsIgnoreCase("xls"))
		            {
						parameters.put("formType", "worksheetquestions-xls.jasper");
		            }
					else
					{
						parameters.put("formType", "worksheetquestions.jasper");
					}

					reportsToQuestions.add(JasperReportsHelper.getJasperPrint(parameters));
				}
            }
            String nameMe = "";

            if(format.equalsIgnoreCase("xls"))
            {
            	nameMe = RfqType.toString(rfqHeader.getRfqType(), organizationId) + "-bid-[" + rfqHeader.getRfqNumber() + "].xls";
            }
            else
            {
            	 nameMe = RfqType.toString(rfqHeader.getRfqType(), organizationId) + "-bid-[" + rfqHeader.getRfqNumber() + "].pdf";
            }
            ret = JasperReportsHelper.fileNameReport(parameters, nameMe);

            if (reportsToMerge.addAll(reportsToQuestions))
			{
				System.out.println("report size es " + reportsToMerge.size());
			}
            if(format.equalsIgnoreCase("xls"))
            {
            	JasperReportsHelper.mergeReportsXls(reportsToMerge, (String)ret, organizationId);
            }
            else
            {
            	JasperReportsHelper.mergeReports(reportsToMerge, (String)ret);
            }
            if(webreport.equalsIgnoreCase("N"))
            {
            	ret = JasperReportsHelper.fileNameReport(parameters, nameMe);
            }
            else
            {
            	ret = JasperReportsHelper.nameWebReport(parameters, nameMe);
            }


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

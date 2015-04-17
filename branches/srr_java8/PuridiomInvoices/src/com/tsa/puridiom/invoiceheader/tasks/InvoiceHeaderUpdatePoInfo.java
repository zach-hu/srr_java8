/*
 * Created on Dec 14, 2006
 *
 * @author  kathleen
 * project: HiltonInvoices
 * package com.tsa.puridiom.invoiceheader.tasks.InvoiceHeaderUpdatePoInfo.java
 *
 */
package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InvoiceHeaderUpdatePoInfo extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
		{
            Map incomingRequest = (Map)object;

            PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
            BigDecimal bdIcPoHeader = poHeader.getIcPoHeader();
            BigDecimal bdTotal = poHeader.getTotal();
            String		fiscalYear = poHeader.getFiscalYear() ;

            if (incomingRequest.containsKey("invoiceHeader"))
            {
            	InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
                if (invoiceHeader != null)
                {
                    String organizationId = (String) incomingRequest.get("organizationId");
                    String userId = (String) incomingRequest.get("userId");
                    DBSession dbsession = (DBSession) incomingRequest.get("dbsession");

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("invoiceheader-update.xml");
					Map requestParameters = new HashMap();

					requestParameters.put("organizationId",organizationId);
					requestParameters.put("userId",userId);
					requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					requestParameters.put("dbsession", dbsession);
					requestParameters.put("InvoiceHeader_icIvcHeader", invoiceHeader.getIcIvcHeader().toString());
					requestParameters.put("InvoiceHeader_icPoHeader", bdIcPoHeader.toString());
					requestParameters.put("InvoiceHeader_poTotal", bdTotal.toString());
					requestParameters.put("InvoiceHeader_fiscalYear", fiscalYear) ;

					process.executeProcess(requestParameters);

					if (process.getStatus() != Status.SUCCEEDED) {
					    throw new Exception("An error ocurred updating the po revisition info for invoice #" + invoiceHeader.getInvoiceNumber());
					}
				}
			}

            this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
		    this.setStatus(Status.FAILED);
		    throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}
}

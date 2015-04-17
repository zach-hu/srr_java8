package com.tsa.puridiom.poheader.tasks;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * Class responsible for setting the fiscal year selected in the Blanket.
 * 
 * @author Alexander
 *
 */
public class PoHeaderApplySelectFiscalYear extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
    @SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
        String saveAsFiscalYear = (String) incomingRequest.get("PoHeader_applyFiscalYear");
        String strFiscalYear = (String) incomingRequest.get("PoHeader_fiscalYear");
        Log.debug(this, "Fiscal Year: " + saveAsFiscalYear);
        //String strFiscalYear = poHeader.getFiscalYear();
        
        int fiscalYear = 0;
        try {
			fiscalYear = Integer.parseInt(strFiscalYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        if (saveAsFiscalYear != null && saveAsFiscalYear.equals("Y")) {
        	
        	// Setup effectiveDate with new year
            Date effectiveDate = poHeader.getEffectiveDate();
            if (effectiveDate != null && fiscalYear > 0) {
            	Calendar calendar = Calendar.getInstance();
            	calendar.setTime(effectiveDate);
            	calendar.set(Calendar.YEAR, fiscalYear);
            	effectiveDate = calendar.getTime();
            	poHeader.setEffectiveDate(effectiveDate);
            }
            
            Date expirationDate = poHeader.getExpirationDate();
            if (expirationDate != null && fiscalYear > 0) {
            	Calendar calendar = Calendar.getInstance();
            	calendar.setTime(expirationDate);
            	calendar.set(Calendar.YEAR, fiscalYear);
            	expirationDate = calendar.getTime();
            	poHeader.setExpirationDate(expirationDate);
            }
            
            Date promisedDate = poHeader.getPromisedDate();
            if (promisedDate != null && fiscalYear > 0) {
            	Calendar calendar = Calendar.getInstance();
            	calendar.setTime(promisedDate);
            	calendar.set(Calendar.YEAR, fiscalYear);
            	promisedDate = calendar.getTime();
            	poHeader.setPromisedDate(promisedDate);
            }
        	
        }
        
        this.setStatus(Status.SUCCEEDED);
        return poHeader;
     }
}

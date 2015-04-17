/*
 * Created on Jan 13, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.TaxCode;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PoNameVerify extends Task
{
    /**
     * Method executeTask.
     *
     * @param object <p>incomingRequest</p>
     */
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;

            PropertiesManager pm =PropertiesManager.getInstance((String)incomingRequest.get("companyId"));
            String	organizationId = (String) incomingRequest.get("organizationId") ;
            String	userId = (String) incomingRequest.get("userId") ;
            UserManager userManager = UserManager.getInstance() ;
            UserProfile user = userManager.getUser(organizationId, userId) ;

            String fieldUdf1 = pm.getProperty("PO NAME UDFS", "UDF1", "");
            this.nameVerify(fieldUdf1, user.getNameUdf1(), incomingRequest);
            String fieldUdf2 = pm.getProperty("PO NAME UDFS", "UDF2", "");
            this.nameVerify(fieldUdf2, user.getNameUdf2(), incomingRequest);
            String fieldUdf3 = pm.getProperty("PO NAME UDFS", "UDF3", "");
            this.nameVerify(fieldUdf3, user.getNameUdf3(), incomingRequest);
            String fieldUdf4 = pm.getProperty("PO NAME UDFS", "UDF4", "");
            this.nameVerify(fieldUdf4, user.getNameUdf4(), incomingRequest);
            String fieldUdf5 = pm.getProperty("PO NAME UDFS", "UDF5", "");
            this.nameVerify(fieldUdf5, user.getNameUdf5(), incomingRequest);

            TaxCode taxCode = (TaxCode)incomingRequest.get("taxCode");
            if(taxCode != null)
            {
                incomingRequest.put("PoHeader_taxRate", taxCode.getTaxRate());
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
        }
        return ret;
    }

    private void nameVerify(String udfFld, String userFld, Map incomingRequest)
    {
        String  validFlds[] = { "po_udf01", "po_udf02", "po_udf03", "po_udf04", "po_udf05", "po_udf06", "po_udf07", "po_udf08", "po_udf09", "po_udf10" };
        String  mapColumns[] = { "udf_1_code", "udf_2_code", "udf_3_code", "udf_4_code", "udf_5_code", "udf_6_code", "udf_7_code", "udf_8_code", "udf_9_code", "udf_10_code" };
        boolean bvalid = false;
        udfFld = Utility.ckNull(udfFld).toLowerCase();
        if(!Utility.isEmpty(userFld))
        {
            for (int i = 0; i < validFlds.length; i++)
            {
                if(udfFld.equals(validFlds[i]))
                {
                    udfFld = mapColumns[i];
                    bvalid = true;
                    break;
                }
            }
            if (bvalid)
            {
                incomingRequest.put("PoHeader_" + udfFld, userFld);
            }
            else
            {
                Log.error(this, "invalid entry: " + udfFld);
            }
        }
    }
}

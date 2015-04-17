/**
 * Created on June 19, 2007
 * @author Kelli
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisition.tasks.RequisitionPoMapUdfs.java
 *
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.requisition.exception.MappingNullException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class RequisitionPoMapUdfs extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;

        try
        {
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            Map requdfs = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getSection("REQ TO PO");
            Log.debug(this.getName(), "Mappings found: " + requdfs.toString());
            Set udfs = requdfs.keySet();
            for (Iterator iter = udfs.iterator(); iter.hasNext();)
            {
                String reqUdf = (String) iter.next();
                String poUdf = (String)requdfs.get(reqUdf);

                try
                {
                    if (!Utility.isEmpty(poUdf)) {
                        incomingRequest.put( this.findRequisitionUdfName(reqUdf), this.getPoUdf(poUdf, poHeader));
                    }
                }
                catch(Exception mne)
                {
                    Log.warn(this.getName(), reqUdf + " was not found") ;
                }
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
        }
        return null;
    }
    private String findRequisitionUdfName(String reqUdf) throws TsaException, MappingNullException
    {
        Log.debug(this, "Looking for: " + reqUdf);
        String code = "";
        if(Utility.isEmpty(reqUdf))
        {
            throw new MappingNullException("REQ UDF " + reqUdf + " was null");
        }
        if(reqUdf.indexOf("10") > 0)
        {
            code = "10";
        }
        else if(reqUdf.indexOf("11") > 0)
        {
            code = "11";
        }
        else if(reqUdf.indexOf("12") > 0)
        {
            code = "12";
        }
        else if(reqUdf.indexOf("13") > 0)
        {
            code = "13";
        }
        else if(reqUdf.indexOf("14") > 0)
        {
            code = "14";
        }
        else if(reqUdf.indexOf("15") > 0)
        {
            code = "15";
        }
        else if(reqUdf.indexOf("01") > 0 || reqUdf.indexOf("1") > 0)
        {
            code = "1";
        }
        else if(reqUdf.indexOf("2") > 0)
        {
            code = "2";
        }
        else if(reqUdf.indexOf("3") > 0)
        {
            code = "3";
        }
        else if(reqUdf.indexOf("4") > 0)
        {
            code = "4";
        }
        else if(reqUdf.indexOf("5") > 0)
        {
            code = "5";
        }
        else if(reqUdf.indexOf("6") > 0)
        {
            code = "6";
        }
        else if(reqUdf.indexOf("7") > 0)
        {
            code = "7";
        }
        else if(reqUdf.indexOf("8") > 0)
        {
            code = "8";
        }
        else if(reqUdf.indexOf("9") > 0)
        {
            code = "9";
        }
        else
        {
            throw new TsaException("Invalid Requisition UDF[" + reqUdf + "]!");
        }
        StringBuffer ret = new StringBuffer("");
        ret.append("RequisitionHeader_udf");
        ret.append(code);
        ret.append("Code");
        return ret.toString();
    }

    private String getPoUdf(String poUdf, PoHeader poHeader) throws TsaException
    {
        String ret = "";
        if(poUdf.indexOf("10") > 0)
        {
            ret = poHeader.getUdf10Code();
        }
        else if(poUdf.indexOf("11") > 0)
        {
            ret = poHeader.getUdf11Code();
        }
        else if(poUdf.indexOf("12") > 0)
        {
            ret = poHeader.getUdf12Code();
        }
        else if(poUdf.indexOf("13") > 0)
        {
            ret = poHeader.getUdf13Code();
        }
        else if(poUdf.indexOf("14") > 0)
        {
            ret = poHeader.getUdf14Code();
        }
        else if(poUdf.indexOf("15") > 0)
        {
            ret = poHeader.getUdf15Code();
        }
        else if(poUdf.indexOf("01") > 0 || poUdf.indexOf("1") >= 0)
        {
            ret = poHeader.getUdf1Code();
        }
        else if(poUdf.indexOf("2") > 0)
        {
            ret = poHeader.getUdf2Code();
        }
        else if(poUdf.indexOf("3") > 0)
        {
            ret = poHeader.getUdf3Code();
        }
        else if(poUdf.indexOf("4") > 0)
        {
            ret = poHeader.getUdf4Code();
        }
        else if(poUdf.indexOf("5") > 0)
        {
            ret = poHeader.getUdf5Code();
        }
        else if(poUdf.indexOf("6") > 0)
        {
            ret = poHeader.getUdf6Code();
        }
        else if(poUdf.indexOf("7") > 0)
        {
            ret = poHeader.getUdf7Code();
        }
        else if(poUdf.indexOf("8") > 0)
        {
            ret = poHeader.getUdf8Code();
        }
        else if(poUdf.indexOf("9") > 0)
        {
            ret = poHeader.getUdf9Code();
        }
        else
        {
            throw new TsaException("Invalid PO UDF[" +  poUdf + "]!");
        }
        return ret;
    }
}

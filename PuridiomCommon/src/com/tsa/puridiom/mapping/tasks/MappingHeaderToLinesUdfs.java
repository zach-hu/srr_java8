package com.tsa.puridiom.mapping.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.mapping.exceptions.MappingNullException;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class MappingHeaderToLinesUdfs
{
    public static String getLineUdfName(String lineUdf, String type) throws TsaException, MappingNullException
    {
        Log.debug(MappingHeaderToLinesUdfs.class, "Looking for: " + lineUdf);

        String code = "";

        if (Utility.isEmpty(lineUdf))
        {
            throw new MappingNullException(type + " UDF " + lineUdf + " was null");
        }
        if (lineUdf.indexOf("10") > 0)
        {
            code = "udf10Code";
        }
        else if (lineUdf.indexOf("11") > 0)
        {
            code = "udf11Code";
        }
        else if (lineUdf.indexOf("12") > 0)
        {
            code = "udf12Code";
        }
        else if (lineUdf.indexOf("13") > 0)
        {
            code = "udf13Code";
        }
        else if (lineUdf.indexOf("14") > 0)
        {
            code = "udf14Code";
        }
        else if (lineUdf.indexOf("15") > 0)
        {
            code = "udf15Code";
        }
        else if (lineUdf.indexOf("01") > 0 || lineUdf.indexOf("1") > 0)
        {
            code = "udf1Code";
        }
        else if (lineUdf.indexOf("2") > 0)
        {
            code = "udf2Code";
        }
        else if (lineUdf.indexOf("3") > 0)
        {
            code = "udf3Code";
        }
        else if (lineUdf.indexOf("4") > 0)
        {
            code = "udf4Code";
        }
        else if (lineUdf.indexOf("5") > 0)
        {
            code = "udf5Code";
        }
        else if (lineUdf.indexOf("6") > 0)
        {
            code = "udf6Code";
        }
        else if (lineUdf.indexOf("7") > 0)
        {
            code = "udf7Code";
        }
        else if (lineUdf.indexOf("8") > 0)
        {
            code = "udf8Code";
        }
        else if (lineUdf.indexOf("9") > 0)
        {
            code = "udf9Code";
        }
        else if (lineUdf.indexOf("commodity") > 0)
        {
        	if (type.equalsIgnoreCase("REQ")) {
        		code = "commodityCode";
            } else if (type.equalsIgnoreCase("PO") || type.equalsIgnoreCase("RFQ")) {
            	code = "commodity";
            }
        }
        else
        {
            throw new TsaException("Invalid " + type + " UDF[" + lineUdf + "]!");
        }

        StringBuffer ret = new StringBuffer("");
        if (type.equalsIgnoreCase("REQ")) {
        	ret.append("RequisitionLine_");
        } else if (type.equalsIgnoreCase("PO")) {
        	ret.append("PoLine_");
        } else if (type.equalsIgnoreCase("RFQ")) {
        	ret.append("RfqLine_");
        }
        ret.append(code);
        return ret.toString();
    }

    public static String getRequisitionHeaderUdfValue(String requisitionHeaderUdf, RequisitionHeader requisitionHeader) throws TsaException
    {
        String ret = "";


        if (requisitionHeaderUdf.indexOf("10") > 0)
        {
            ret = requisitionHeader.getUdf10Code();
        }
        else if (requisitionHeaderUdf.indexOf("11") > 0)
        {
            ret = requisitionHeader.getUdf11Code();
        }
        else if (requisitionHeaderUdf.indexOf("12") > 0)
        {
            ret = requisitionHeader.getUdf12Code();
        }
        else if (requisitionHeaderUdf.indexOf("13") > 0)
        {
            ret = requisitionHeader.getUdf13Code();
        }
        else if (requisitionHeaderUdf.indexOf("14") > 0)
        {
            ret = requisitionHeader.getUdf14Code();
        }
        else if (requisitionHeaderUdf.indexOf("15") > 0)
        {
            ret = requisitionHeader.getUdf15Code();
        }
        else if (requisitionHeaderUdf.indexOf("01") > 0 || requisitionHeaderUdf.indexOf("1") >= 0)
        {
            ret = requisitionHeader.getUdf1Code();
        }
        else if (requisitionHeaderUdf.indexOf("2") > 0)
        {
            ret = requisitionHeader.getUdf2Code();
        }
        else if (requisitionHeaderUdf.indexOf("3") > 0)
        {
            ret = requisitionHeader.getUdf3Code();
        }
        else if (requisitionHeaderUdf.indexOf("4") > 0)
        {
            ret = requisitionHeader.getUdf4Code();
        }
        else if (requisitionHeaderUdf.indexOf("5") > 0)
        {
            ret = requisitionHeader.getUdf5Code();
        }
        else if (requisitionHeaderUdf.indexOf("6") > 0)
        {
            ret = requisitionHeader.getUdf6Code();
        }
        else if (requisitionHeaderUdf.indexOf("7") > 0)
        {
            ret = requisitionHeader.getUdf7Code();
        }
        else if (requisitionHeaderUdf.indexOf("8") > 0)
        {
            ret = requisitionHeader.getUdf8Code();
        }
        else if (requisitionHeaderUdf.indexOf("9") > 0)
        {
            ret = requisitionHeader.getUdf9Code();
        }
        else if (requisitionHeaderUdf.equals("PRIORITY"))
        {
        	ret = requisitionHeader.getPriorityCode();
        }
        else
        {
            throw new TsaException("Invalid REQ UDF[" +  requisitionHeaderUdf + "]!HERE");
        }
        return ret;
    }

    public static String getPoHeaderUdfValue(String poHeaderUdf, PoHeader poHeader) throws TsaException
    {
        String ret = "";

        if (poHeaderUdf.indexOf("10") > 0)
        {
            ret = poHeader.getUdf10Code();
        }
        else if (poHeaderUdf.indexOf("11") > 0)
        {
            ret = poHeader.getUdf11Code();
        }
        else if (poHeaderUdf.indexOf("12") > 0)
        {
            ret = poHeader.getUdf12Code();
        }
        else if (poHeaderUdf.indexOf("13") > 0)
        {
            ret = poHeader.getUdf13Code();
        }
        else if (poHeaderUdf.indexOf("14") > 0)
        {
            ret = poHeader.getUdf14Code();
        }
        else if (poHeaderUdf.indexOf("15") > 0)
        {
            ret = poHeader.getUdf15Code();
        }
        else if (poHeaderUdf.indexOf("01") > 0 || poHeaderUdf.indexOf("1") >= 0)
        {
            ret = poHeader.getUdf1Code();
        }
        else if (poHeaderUdf.indexOf("2") > 0)
        {
            ret = poHeader.getUdf2Code();
        }
        else if (poHeaderUdf.indexOf("3") > 0)
        {
            ret = poHeader.getUdf3Code();
        }
        else if (poHeaderUdf.indexOf("4") > 0)
        {
            ret = poHeader.getUdf4Code();
        }
        else if (poHeaderUdf.indexOf("5") > 0)
        {
            ret = poHeader.getUdf5Code();
        }
        else if (poHeaderUdf.indexOf("6") > 0)
        {
            ret = poHeader.getUdf6Code();
        }
        else if (poHeaderUdf.indexOf("7") > 0)
        {
            ret = poHeader.getUdf7Code();
        }
        else if (poHeaderUdf.indexOf("8") > 0)
        {
            ret = poHeader.getUdf8Code();
        }
        else if (poHeaderUdf.indexOf("9") > 0)
        {
            ret = poHeader.getUdf9Code();
        }
        else
        {
            throw new TsaException("Invalid PO UDF[" +  poHeaderUdf + "]!");
        }
        return ret;
    }

    public static String getRfqHeaderUdfValue(String rfqHeaderUdf, RfqHeader rfqHeader) throws TsaException
    {
        String ret = "";

        if (rfqHeaderUdf.indexOf("10") > 0)
        {
            ret = rfqHeader.getUdf10Code();
        }
        else if (rfqHeaderUdf.indexOf("01") > 0 || rfqHeaderUdf.indexOf("1") >= 0)
        {
            ret = rfqHeader.getUdf1Code();
        }
        else if (rfqHeaderUdf.indexOf("2") > 0)
        {
            ret = rfqHeader.getUdf2Code();
        }
        else if (rfqHeaderUdf.indexOf("3") > 0)
        {
            ret = rfqHeader.getUdf3Code();
        }
        else if (rfqHeaderUdf.indexOf("4") > 0)
        {
            ret = rfqHeader.getUdf4Code();
        }
        else if (rfqHeaderUdf.indexOf("5") > 0)
        {
            ret = rfqHeader.getUdf5Code();
        }
        else if (rfqHeaderUdf.indexOf("6") > 0)
        {
            ret = rfqHeader.getUdf6Code();
        }
        else if (rfqHeaderUdf.indexOf("7") > 0)
        {
            ret = rfqHeader.getUdf7Code();
        }
        else if (rfqHeaderUdf.indexOf("8") > 0)
        {
            ret = rfqHeader.getUdf8Code();
        }
        else if (rfqHeaderUdf.indexOf("9") > 0)
        {
            ret = rfqHeader.getUdf9Code();
        }
        else
        {
            throw new TsaException("Invalid RFQ UDF[" +  rfqHeaderUdf + "]!");
        }
        return ret;
    }
}

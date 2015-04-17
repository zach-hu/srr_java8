/**
 *
 * Created on Jan 29, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.MappingUdfs.java
 *
 */
package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.po.exceptions.MappingNullException;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class MappingUdfs
{
    public static String findPoUdfName(String poUdf) throws TsaException, MappingNullException
    {
        Log.debug(MappingUdfs.class, "Looking for: " + poUdf);
        String code = "";
        if(Utility.isEmpty(poUdf))
        {
            throw new MappingNullException("PO UDF " + poUdf + " was null");
        }
        if(poUdf.indexOf("10") > 0)
        {
            code = "10";
        }
        else if(poUdf.indexOf("11") > 0)
        {
            code = "11";
        }
        else if(poUdf.indexOf("12") > 0)
        {
            code = "12";
        }
        else if(poUdf.indexOf("13") > 0)
        {
            code = "13";
        }
        else if(poUdf.indexOf("14") > 0)
        {
            code = "14";
        }
        else if(poUdf.indexOf("15") > 0)
        {
            code = "15";
        }
        else if(poUdf.indexOf("01") > 0 || poUdf.indexOf("1") > 0)
        {
            code = "1";
        }
        else if(poUdf.indexOf("2") > 0)
        {
            code = "2";
        }
        else if(poUdf.indexOf("3") > 0)
        {
            code = "3";
        }
        else if(poUdf.indexOf("4") > 0)
        {
            code = "4";
        }
        else if(poUdf.indexOf("5") > 0)
        {
            code = "5";
        }
        else if(poUdf.indexOf("6") > 0)
        {
            code = "6";
        }
        else if(poUdf.indexOf("7") > 0)
        {
            code = "7";
        }
        else if(poUdf.indexOf("8") > 0)
        {
            code = "8";
        }
        else if(poUdf.indexOf("9") > 0)
        {
            code = "9";
        }
        else
        {
            throw new TsaException("Invalid Po UDF[" + poUdf + "]!");
        }
        StringBuffer ret = new StringBuffer("");
        ret.append("PoHeader_udf");
        ret.append(code);
        ret.append("Code");
        return ret.toString();
    }

    public static PoHeader mapReqToPoUdf(String poUdf, String reqUdf, RequisitionHeader reqHeader, PoHeader poHeader)
    {
        Log.debug(MappingUdfs.class.getName(), "poudf: " + poUdf + ", reqUdf " + reqUdf);
        if(Utility.isEmpty(poUdf))
        {
            Log.error(MappingUdfs.class.getName(), "poudf: " + poUdf + " NOT FOUND!");
            return poHeader;
        }
        String reqUdfValue = "";
        try
        {
            reqUdfValue = MappingUdfs.getReqUdf(reqUdf, reqHeader);
        }
        catch (TsaException e)
        {
            Log.error(MappingUdfs.class.getName(), "poudf: " + poUdf + " NOT FOUND!");
            reqUdfValue = "";
        }

        if(poUdf.indexOf("10") > 0)
        {
            poHeader.setUdf10Code(reqUdfValue);
        }
        else if(poUdf.indexOf("11") > 0)
        {
            poHeader.setUdf11Code(reqUdfValue);
        }
        else if(poUdf.indexOf("12") > 0)
        {
            poHeader.setUdf12Code(reqUdfValue);
        }
        else if(poUdf.indexOf("13") > 0)
        {
            poHeader.setUdf13Code(reqUdfValue);
        }
        else if(poUdf.indexOf("14") > 0)
        {
            poHeader.setUdf14Code(reqUdfValue);
        }
        else if(poUdf.indexOf("15") > 0)
        {
            poHeader.setUdf15Code(reqUdfValue);
        }
        else if(poUdf.indexOf("01") > 0 || poUdf.indexOf("1") > 0)
        {
            poHeader.setUdf1Code(reqUdfValue);
        }
        else if(poUdf.indexOf("2") > 0)
        {
            poHeader.setUdf2Code(reqUdfValue);
        }
        else if(poUdf.indexOf("3") > 0)
        {
            poHeader.setUdf3Code(reqUdfValue);
        }
        else if(poUdf.indexOf("4") > 0)
        {
            poHeader.setUdf4Code(reqUdfValue);
        }
        else if(poUdf.indexOf("5") > 0)
        {
            poHeader.setUdf5Code(reqUdfValue);
        }
        else if(poUdf.indexOf("6") > 0)
        {
            poHeader.setUdf6Code(reqUdfValue);
        }
        else if(poUdf.indexOf("7") > 0)
        {
            poHeader.setUdf7Code(reqUdfValue);
        }
        else if(poUdf.indexOf("8") > 0)
        {
            poHeader.setUdf8Code(reqUdfValue);
        }
        else if(poUdf.indexOf("9") > 0)
        {
            poHeader.setUdf9Code(reqUdfValue);
        }
        else
        {
            Log.error(MappingUdfs.class.getName(), "poudf: " + poUdf + ", ReqUdf: " + reqUdfValue + " NOT FOUND!");
        }

        return poHeader;
    }
    public static String getReqUdf(String reqUdf, RequisitionHeader reqHeader) throws TsaException
    {
        String ret = "";
        if(reqUdf.indexOf("10") > 0)
        {
            ret = reqHeader.getUdf10Code();
        }
        else if(reqUdf.indexOf("11") > 0)
        {
            ret = reqHeader.getUdf11Code();
        }
        else if(reqUdf.indexOf("12") > 0)
        {
            ret = reqHeader.getUdf12Code();
        }
        else if(reqUdf.indexOf("13") > 0)
        {
            ret = reqHeader.getUdf13Code();
        }
        else if(reqUdf.indexOf("14") > 0)
        {
            ret = reqHeader.getUdf14Code();
        }
        else if(reqUdf.indexOf("15") > 0)
        {
            ret = reqHeader.getUdf15Code();
        }
        else if(reqUdf.indexOf("01") > 0 || reqUdf.indexOf("1") >= 0)
        {
            ret = reqHeader.getUdf1Code();
        }
        else if(reqUdf.indexOf("2") > 0)
        {
            ret = reqHeader.getUdf2Code();
        }
        else if(reqUdf.indexOf("3") > 0)
        {
            ret = reqHeader.getUdf3Code();
        }
        else if(reqUdf.indexOf("4") > 0)
        {
            ret = reqHeader.getUdf4Code();
        }
        else if(reqUdf.indexOf("5") > 0)
        {
            ret = reqHeader.getUdf5Code();
        }
        else if(reqUdf.indexOf("6") > 0)
        {
            ret = reqHeader.getUdf6Code();
        }
        else if(reqUdf.indexOf("7") > 0)
        {
            ret = reqHeader.getUdf7Code();
        }
        else if(reqUdf.indexOf("8") > 0)
        {
            ret = reqHeader.getUdf8Code();
        }
        else if(reqUdf.indexOf("9") > 0)
        {
            ret = reqHeader.getUdf9Code();
        }
        else
        {
            throw new TsaException("Invalid Requisition UDF[" +  reqUdf + "]!");
        }
        return ret;
    }
    /**
     * @param rfqUdf
     * @param reqHeader
     * @return
     */
    public static Object getRfqUdf(String rfqUdf, RfqHeader rfqHeader) throws TsaException
    {
        String ret = "";
        if(rfqUdf.indexOf("10") > 0)
        {
            ret = rfqHeader.getUdf10Code();
        }
        else if(rfqUdf.indexOf("01") > 0 || rfqUdf.indexOf("1") >= 0)
        {
            ret = rfqHeader.getUdf1Code();
        }
        else if(rfqUdf.indexOf("2") > 0)
        {
            ret = rfqHeader.getUdf2Code();
        }
        else if(rfqUdf.indexOf("3") > 0)
        {
            ret = rfqHeader.getUdf3Code();
        }
        else if(rfqUdf.indexOf("4") > 0)
        {
            ret = rfqHeader.getUdf4Code();
        }
        else if(rfqUdf.indexOf("5") > 0)
        {
            ret = rfqHeader.getUdf5Code();
        }
        else if(rfqUdf.indexOf("6") > 0)
        {
            ret = rfqHeader.getUdf6Code();
        }
        else if(rfqUdf.indexOf("7") > 0)
        {
            ret = rfqHeader.getUdf7Code();
        }
        else if(rfqUdf.indexOf("8") > 0)
        {
            ret = rfqHeader.getUdf8Code();
        }
        else if(rfqUdf.indexOf("9") > 0)
        {
            ret = rfqHeader.getUdf9Code();
        }
        else
        {
            throw new TsaException("Invalid Rfq UDF[" +  rfqUdf + "]!");
        }
        return ret;
    }

    public static String getUserUdf(String udf, UserProfile user)
    {
        String ret = "";

        if(MappingUdfs.isUdfX(udf, 1))
        {
            ret = user.getNameUdf1();
        }
        if(MappingUdfs.isUdfX(udf, 2))
        {
            ret = user.getNameUdf2();
        }
        if(MappingUdfs.isUdfX(udf, 3))
        {
            ret = user.getNameUdf3();
        }
        if(MappingUdfs.isUdfX(udf, 4))
        {
            ret = user.getNameUdf4();
        }
        if(MappingUdfs.isUdfX(udf, 5))
        {
            ret = user.getNameUdf5();
        }
        if(MappingUdfs.isUdfX(udf, 6))
        {
            ret = user.getNameUdf1();
        }
        if(MappingUdfs.isUdfX(udf, 7))
        {
            ret = user.getNameUdf1();
        }
        if(MappingUdfs.isUdfX(udf, 8))
        {
            ret = user.getNameUdf1();
        }
        if(MappingUdfs.isUdfX(udf, 9))
        {
            ret = user.getNameUdf1();
        }
        if(MappingUdfs.isUdfX(udf, 10))
        {
            ret = user.getNameUdf1();
        }
        return "";
    }

    public static boolean isUdfX(String udf, int value)
    {
        String sValue = String.valueOf(value);
        if(udf.indexOf(sValue) > 0 || udf.indexOf("0" + sValue) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

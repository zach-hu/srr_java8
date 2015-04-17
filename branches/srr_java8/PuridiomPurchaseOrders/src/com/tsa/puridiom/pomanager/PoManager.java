package com.tsa.puridiom.pomanager;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Creation date: June 2004
 * @author: Kelli Knisely
 */
public class PoManager
{
    private static PoManager INSTANCE = new PoManager();
    private HashMap organizations = new HashMap();

    private PoManager()
    {
        super();
    }
    /**
     * @return com.tsa.puridiom.usermanager.UserManager
     */
    public static PoManager getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new PoManager();
        }
        return INSTANCE;
    }

    /**
     * @return com.tsa.puridiom.entity.UserProfile
     * @param userId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public PoHeader getPoHeader(String organizationId, String icPoHeader) throws java.lang.Exception
    {
        PoHeader poHeader = null;

        try
        {
            if (Utility.isEmpty(icPoHeader) || Utility.isEmpty(organizationId))
            {
                poHeader = new PoHeader();
                return poHeader;
            }
            else
            {
                organizationId = organizationId.toUpperCase();

                Map pos = new HashMap();
                if (this.organizations.containsKey(organizationId))
                {
                    pos = (Map) this.organizations.get(organizationId);
                }
                if (pos.containsKey(icPoHeader))
                {
                    poHeader = (PoHeader) pos.get(icPoHeader);
                }
                else
                {
                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", organizationId);
                    //processParameters.put("icPoHeader", icPoHeader.toString());
                    processParameters.put("PoHeader_icPoHeader", icPoHeader);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("poheader-retrieve-by-id.xml");

                    process.executeProcess(processParameters);

                    poHeader = (PoHeader) processParameters.get("poHeader");

                    this.organizations.put(organizationId, pos);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return poHeader;
        }
    }

     /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[ClassName=com.tsa.puridiom.pomanager.PoManager]");
        return sb.toString();
    }
}

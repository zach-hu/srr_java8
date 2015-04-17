/*
 * Created on Jun 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EmailUtils
{
    public static String replace(String replaceString, String originalString, String replacementString)
    {
        String returnStr = "";
        Log.debug("EmailUtils", "Replacing... ");
        Log.debug("EmailUtils", "replaceString: " + replaceString);
        Log.debug("EmailUtils", "originalString: " + originalString);
        Log.debug("EmailUtils", "replacementString: " + replacementString);
        if(replacementString == null)
        {
            replacementString = "";
        }
        if(originalString.indexOf(replaceString) < 0)
        {
            return originalString;
        }
        try
        {
            // Compile regular expression
            Pattern pattern = Pattern.compile(replaceString);

            // Replace all occurrences of pattern in input
            Matcher matcher = pattern.matcher(originalString);
            
            replacementString = HiltonUtility.quoteReplacement(replacementString);
            returnStr = matcher.replaceAll(replacementString);
        }
        catch (Exception e)
        {
            returnStr = originalString;
            Log.debug("EmailUtils", "replaceString: " + replaceString);
            Log.debug("EmailUtils", "originalString: " + originalString);
            Log.debug("EmailUtils", "replacementString: " + replacementString);
            e.printStackTrace();
        }
        finally
        {
            Log.debug("EmailUtils", "results: " + returnStr);
        }
        return returnStr;
    }

    public static String getFooter()
    {


        return "";
    }
}

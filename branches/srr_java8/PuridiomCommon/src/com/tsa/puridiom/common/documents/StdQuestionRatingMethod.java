/*
 * Created on Oct 30, 2006
 */
package com.tsa.puridiom.common.documents;

import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class StdQuestionRatingMethod 
{
	public static final String MANUAL = "MAN" ;
	public static final String RATING_ONLY = "RO" ;
	public static final String WEIGHTED_RATING = "WR" ;
	
	public static String toString(String type)
	{
		return StdQuestionRatingMethod.toString(type, "");
	}

    /**
     * @param string
     * @param organizationId
     * @return
     */
    public static String toString(String type, String organizationId)
    {
        if (type == null || type.equals("null")) 
		{
			return "";
		}
		else 
		{
		    return DictionaryManager.getInstance("stdquestion-rating-method", organizationId).getProperty(type, type);
		}
    }
}
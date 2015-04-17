/*
 * Created on Aug 10, 2004
 *
 * @author  * renzo
 * project: TsaProperties
 * package com.tsagate.properties;.DictionaryFactory.java
 *
 */
package com.tsagate.properties;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DictionaryFactory
{
    public static Dictionary getDictionary(String type, String organizationId)
    {
        Dictionary dictionary = null;
        if(type.equalsIgnoreCase("host"))
        {
            dictionary = new Dictionary(type, organizationId);
        }
        else
        {
            dictionary = new LabelsDictionary(type, organizationId);
        }

        return dictionary;
    }

    public static LabelsDictionary getLabelsDictionary(String organizationId, String language)
    {
    	return new LabelsDictionary("labels", organizationId, language);
    }
}

/*
 * Created on Jan 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.browse.tasks;

import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GenerateReportObject extends GenerateBrowseObject
{

    protected String getBrowseXmlPath(String organizationId)
    {
        return DictionaryManager.getInstance("host", organizationId).getProperty("reportFilters", "\\xml\\report\\filters\\");
    }
}

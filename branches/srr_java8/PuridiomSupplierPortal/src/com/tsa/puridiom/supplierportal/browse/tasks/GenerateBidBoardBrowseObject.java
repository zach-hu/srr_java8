/*
 * Created on May 19, 2004
 */
package com.tsa.puridiom.supplierportal.browse.tasks;

import com.tsa.puridiom.browse.tasks.GenerateBrowseObjectByEntity;
import com.tsagate.properties.DictionaryManager;

/**
 * @author kelli
 */
public class GenerateBidBoardBrowseObject extends GenerateBrowseObjectByEntity {

	protected String getBrowseXmlPath(String organizationId) {
		//ResourceBundle resources = ResourceBundle.getBundle("tsagate"); 
		//String path = resources.getString("bidboard-browse-xml-path");
		String	path = DictionaryManager.getInstance("host",organizationId).getProperty("bidboard-browse-xml-path", "\\xml\\browse\\");
		return path;		
	}

}

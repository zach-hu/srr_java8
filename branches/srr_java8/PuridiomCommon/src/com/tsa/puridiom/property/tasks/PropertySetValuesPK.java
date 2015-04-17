package com.tsa.puridiom.property.tasks;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;

public class PropertySetValuesPK
{
	public void setValues(Map incomingRequest, Property property )
	{
		String sectionValue = (String ) incomingRequest.get("Property_section");
		String propertyValue = (String ) incomingRequest.get("Property_property");
		PropertyPK comp_id = new PropertyPK();
		comp_id.setProperty(HiltonUtility.ckNull(propertyValue).toUpperCase());
		comp_id.setSection(HiltonUtility.ckNull(sectionValue).toUpperCase());
		property.setComp_id(comp_id);
	}
}
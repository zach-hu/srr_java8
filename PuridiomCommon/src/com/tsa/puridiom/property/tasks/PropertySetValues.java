package com.tsa.puridiom.property.tasks;


import com.tsa.puridiom.entity.Property;
import com.tsa.puridiom.entity.PropertyPK;
import com.tsa.puridiom.common.utility.HiltonUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PropertySetValues
{
	public List setValues(Map incomingRequest, Property property )
	{
		List propertyList = new ArrayList();
		
		if (incomingRequest.containsKey("Property_section") && incomingRequest.containsKey("Property_property") && 
			incomingRequest.containsKey("Property_value"))
		{
			Object sectionObj = incomingRequest.get("Property_section");
			Object propertyObj = incomingRequest.get("Property_property");
			Object valueObj = incomingRequest.get("Property_value");
			String	propertyArray[] = new String[1];
			String	valueArray[] = new String[1];
			String	sectionArray[] = new String[1];
			String	propertyValue = "";
			String	valueValue = "";
			String	sectionValue = "";
			
			if (!(propertyObj instanceof String[] && valueObj instanceof String[]))
			{	
				sectionArray[0] = (String) incomingRequest.get("Property_section");
				propertyArray[0] = (String) incomingRequest.get("Property_property");
				valueArray[0] = (String) incomingRequest.get("Property_value");
			}
			else
			{
				propertyArray = (String[]) incomingRequest.get("Property_property");
				valueArray = (String[]) incomingRequest.get("Property_value");
				
				if (!(sectionObj instanceof String[]))
				{
					sectionArray = new String[valueArray.length];
					sectionValue = (String) incomingRequest.get("Property_section");
					
					for (int i=0; i < valueArray.length; i++)
					{
						sectionArray[i] = sectionValue;
					}
				}
				else
				{
					sectionArray = (String[]) incomingRequest.get("Property_section");
				}
			}

			for (int i=0; i < valueArray.length; i++)
			{
				PropertyPK comp_id = new PropertyPK();
				Property propertyEntity = property;
					
				comp_id.setSection(HiltonUtility.ckNull(sectionArray[i]).toUpperCase());
				comp_id.setProperty(HiltonUtility.ckNull(propertyArray[i]).toUpperCase());
				propertyEntity.setComp_id(comp_id);
				propertyEntity.setValue(valueArray[i]);
					
				propertyList.add(propertyEntity);
			}
		}
		return propertyList;
	}
}
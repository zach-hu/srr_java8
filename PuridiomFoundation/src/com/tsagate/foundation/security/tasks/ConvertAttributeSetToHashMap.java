package com.tsagate.foundation.security.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import netscape.ldap.LDAPAttributeSet;
import netscape.ldap.LDAPAttribute;

/**
 * Creation date: September 2003
 * @author: Kelli Knisely
 */
public class ConvertAttributeSetToHashMap extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		HashMap attributeMap = new HashMap(); 
		
		try 
		{
			LDAPAttributeSet attributeSet = (LDAPAttributeSet) incomingRequest.get("attributeSet");

			if (attributeSet == null)
			{
				throw new Exception("attributeSet was not defined.");
			}
						
			Enumeration enumeration = attributeSet.getAttributes();
			LDAPAttribute	attribute;
			String	attributeName = "";
			Object	attributeValue;
			
			while (enumeration.hasMoreElements())
			{
				attribute = (LDAPAttribute) enumeration.nextElement();
				
				if (attribute != null)
				{
					attributeName = attribute.getName();
					
					String[] values = attribute.getStringValueArray();
					if (values.length > 1)
					{
						List list = new ArrayList();
						for (int i=0; i < values.length; i++) 
						{
							list.add(values[i]);	
						}
						
						attributeValue = list;
						
						attributeMap.put(attributeName, attributeValue);
					}
					else
					{
						attributeValue = values[0];
						attributeMap.put(attributeName, attributeValue);
					}
				}
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		
		return attributeMap;
	}	
}
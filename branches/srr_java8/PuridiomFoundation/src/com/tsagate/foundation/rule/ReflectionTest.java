/*
 * Created on Jul 25, 2003 
 */
package com.tsagate.foundation.rule;
import java.lang.reflect.*;
/**
 * @author Administrator 
 */
public class ReflectionTest 
{	
	public Object getValueFromMethod(Object object, String methodName) throws Exception
	{
		Object result = null;
		try
		{
			Class c = object.getClass();
			//c.getMethod()
			/*
			Field fields[] = c.getDeclaredFields();
			int numberOfFields = fields.length;
			for(int i = 0; i < numberOfFields; i++){
				Field field = fields[i];
				//system.out.println(field.getName() + " - ACCESSIBLE: " + field.isAccessible());
			}
			*/
			Method method = c.getMethod(methodName, null);
			result = method.invoke(object, null);

			////system.out.println(c.getField("com.tsagate.common.rule.Clause.clauseLabel"));
			
		}
		catch(Exception exception)
		{
			//system.out.println(exception.toString());
			throw exception;
		}
		finally
		{
			return result;
		}
	}
}

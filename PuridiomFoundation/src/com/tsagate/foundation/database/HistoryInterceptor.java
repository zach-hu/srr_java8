package com.tsagate.foundation.database;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;

public class HistoryInterceptor extends EmptyInterceptor
{
	Map newValues = new HashMap();
	Map oldValues = new HashMap();

	public int[] findDirty(Object arg0, Serializable arg1, Object[] arg2,
			Object[] arg3, String[] arg4, Type[] arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object instantiate(Class arg0, Serializable arg1)
			throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean isUnsaved(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onDelete(Object arg0, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub

	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types)
	{
		System.out.println("entity: " + entity.getClass().getName());
		if(entity.getClass().getName().indexOf("PoLine") > 0)
		{
			if(!Arrays.equals(previousState, currentState))
			{
				//currentState
				Object temp[] = null;

				if(this.newValues.containsKey(entity.getClass().getName()))
				{
					temp = (Object[])this.newValues.get(entity.getClass().getName());
					if(!Arrays.equals(temp, currentState))
					{
						for (int i = 0; i < temp.length; i++)
						{
							if(temp[i] != null && currentState[i] != null)
							{
								if(!temp[i].equals(currentState[i]))
								{
									temp[i] = currentState[i];
								}
							}
						}
					}
				}
				else
				{
					temp = currentState;
				}

				this.newValues.put(entity.getClass().getName(), temp);
				if(!this.oldValues.containsKey(entity.getClass().getName()))
				{
					this.oldValues.put(entity.getClass().getName(), previousState);
				}
//				return true;
			}
		}
		return false;
	}

	public boolean onLoad(Object arg0, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onSave(Object arg0, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}

	public void postFlush(Iterator arg0) throws CallbackException {
		if(this.newValues.containsKey("com.tsa.puridiom.entity.PoLine"))
		{
			Object newValues[] = (Object[])this.newValues.get("com.tsa.puridiom.entity.PoLine");
			Object oldValues[] = (Object[])this.oldValues.get("com.tsa.puridiom.entity.PoLine");
			if(!Arrays.equals(newValues, oldValues))
			{
				System.out.println("");
				for (int i = 0; i < newValues.length; i++)
				{
					if(newValues[i] != null && oldValues[i] != null)
					{
						if(!newValues[i].equals(oldValues[i]))
						{
							System.out.print("values: " + newValues[i] + "\t");
						}
					}

				}
				this.oldValues = this.newValues;
			}
			System.out.println("");
		}

	}

	public void preFlush(Iterator arg0) throws CallbackException {
		//this.newValues = new HashMap();
		this.oldValues = new HashMap();

	}

}

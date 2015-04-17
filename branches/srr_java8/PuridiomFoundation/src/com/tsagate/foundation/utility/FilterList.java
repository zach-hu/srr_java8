/*
 * Created on Nov 26, 2003
 * @author renzo
 * project: TSACommon
 * com.tsagate.common.utilityFilter.java
 */
 
package com.tsagate.foundation.utility;

import com.tsagate.foundation.rule.operator.IOperator;
import com.tsagate.foundation.rule.operator.LogicOperator;
import com.tsagate.foundation.rule.operator.OperatorFactory;
import java.util.ArrayList;
import java.util.List;

public class FilterList
{
	private List deleted = null;
	private List added = null;
	private List original = null;
	private List filtered = null;
	private List filters = null;
	private List logicValues = null;
	private List order = null;
	
	/**
	 * use this field if you want to keep any changes made to the resulting list
	 */
	public boolean keepChanges = false;
	
	
	public void reset()
	{
		if(this.keepChanges)
		{
			for(int i = 0; i < this.order.size(); i++)
			{
				Integer index = (Integer)this.order.get(i);
				this.original.set(index.intValue(), this.filtered.get(index.intValue()));
			}
		}
		this.filters = new ArrayList();
		this.filtered = new ArrayList();
		this.logicValues = new ArrayList();
		this.order = new ArrayList();
	}
	
	public List filter()
	{
		List toFilter = this.wasFiltered();
		List filterResult = new ArrayList();
				
		for(int i =0; i < toFilter.size(); i++)
		{
			Object element = toFilter.get(i);
			filterResult = this.applyFilters(element);
			if(this.applyLogic(filterResult))
			{
				this.filtered.add(element);
				this.order.add(new Integer(i));
			}
		}
		
		return this.filtered;
	}
	
	/**
	 * wasFiltered
	 * @return
	 */
	private List wasFiltered()
	{
		List toFilter = null;
		if(this.filtered.size() > 0)
		{
			toFilter = this.filtered;
		}
		else
		{
			toFilter = this.original;
		}
		return toFilter;
	}

	/**
	 * applyLogic
	 * @param filterResult
	 */
	private boolean applyLogic(List filterResult)
	{
		boolean match = false;
		if(filterResult.size() == 1)
		{
			return ((Boolean)filterResult.get(0)).booleanValue();
		}
		for(int i = 0, j = 0; i < this.logicValues.size(); i++)
		{
			LogicOperator lo = (LogicOperator)this.logicValues.get(i);
			List partial = null;
			if ((j + 2) <= filterResult.size()) {
				partial = filterResult.subList(j, j + 2);
			} else {
				partial = filterResult.subList(j, j + 1);
			}
			if(!lo.evaluate(partial))
			{
				return false;
			}
			j = j + 1;
			match = true;
		}
		
		return match;
	}

	/**
	 * applyFilter
	 * @param element
	 * @return
	 */
	private List applyFilters(Object element)
	{
		List filterResult = new ArrayList();
		for(int i = 0; i < this.filters.size(); i++)
		{
			boolean bContinue = false;
			Filter filter = (Filter)this.filters.get(i);
			Object found = EntityUtility.invokeGet(filter.getColumnName(), element);
			IOperator oper = OperatorFactory.getOperator(filter.getOper());
			bContinue = oper.compare(found, filter.getValue());
			filterResult.add(new Boolean(bContinue));
		}
		return filterResult;
	}

	public void addFilter(String columnName, int op, Object value)
	{
		Filter filter = new Filter(columnName, op, value);
		this.filters.add(filter);
	}
	
	public void addLogicValue(String op)
	{
		this.logicValues.add(OperatorFactory.getLogicOperator(op));
	}
	/**
	 * getFiltered
	 * @return
	 */
	public List getFiltered()
	{
		return filtered;
	}

	/**
	 * setFiltered
	 * @param filtered
	 */
	public void setFiltered(List filtered)
	{
		this.filtered = filtered;
	}

	/**
	 * getOriginal
	 * @return
	 */
	public List getOriginal()
	{
		return original;
	}

	/**
	 * setOriginal
	 * @param original
	 */
	public void setOriginal(List original)
	{
		this.original = original;
	}
	public FilterList(List original)
	{
		this.filters = new ArrayList();
		this.original = original;
		this.filtered = new ArrayList();
		this.logicValues = new ArrayList();
		this.order = new ArrayList();
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[FilterList:");
		buffer.append(" original: ");
		buffer.append(original);
		buffer.append(" filtered: ");
		buffer.append(filtered);
		buffer.append(" filters: ");
		buffer.append(filters);
		buffer.append(" logicValues: ");
		buffer.append(logicValues);
		buffer.append("]");
		return buffer.toString();
	}
	
	/**
	 * addRow
	 * <p>when a row is added it will always be added to the end of the list
	 * <p>changes are reflected inmediatly over the original list 
	 * @param obj
	 * @return
	 */
	public int addRow(Object obj)
	{
		this.filtered.add(obj);
		this.original.add(obj);
		this.order.add(new Integer(this.original.size() - 1));
		
		return this.filtered.size();
	}	/**
	 * deleteRow
	 * <p> if a row is deleted from te filtered list it is inmediatly deleted from the original.
	 * @param row
	 * @return
	 */
	public int deleteRow(int row)
	{
		this.filtered.remove(row);
		this.original.remove(((Integer)this.order.get(row)).intValue());
		this.order.remove(row);
		return this.filtered.size();
	}

}

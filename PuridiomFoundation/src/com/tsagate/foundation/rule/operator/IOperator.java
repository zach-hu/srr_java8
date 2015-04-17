/*
 * Created on Sep 16, 2003 
 */
package com.tsagate.foundation.rule.operator;

/**
 * @author renzo 
 */
public interface IOperator 
{
	public int value();
	public boolean compare(Object objA, Object B);
}

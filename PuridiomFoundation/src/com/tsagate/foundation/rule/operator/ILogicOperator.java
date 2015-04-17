/*
 * Created on Sep 18, 2003 
 */
package com.tsagate.foundation.rule.operator;

import java.util.List;

/**
 * @author renzo 
 */
public interface ILogicOperator
{
	public boolean logicValue();
	public boolean evaluate(List list);
	public int value();
}

package com.tsa.puridiom.hibernate;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;

/**
 * Class that define a function with no arguments to represent 
 * the Left Outer Join using the notation (+) of Oracle, the parse 
 * of version 3 of Hibernate does not allow the use of notation (+),
 * why is created the new function.
 * 
 * @author Alexander Angulo
 *
 */
public class LeftOuterJoinFunction implements SQLFunction {

	@Override
	public Type getReturnType(Type arg0, Mapping arg1) throws QueryException {
		return null;
	}

	@Override
	public boolean hasArguments() {
		return false;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {
		return false;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String render(List args, SessionFactoryImplementor factory)
			throws QueryException {
		if (args.size() != 1) {
			throw new IllegalArgumentException("the function must be passed 1 argument");
		}
		
		StringBuffer buffer = new StringBuffer(args.get(0).toString());
		buffer.append(" (+) ");
		
		return buffer.toString();
	}
}

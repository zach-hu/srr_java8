package com.tsa.puridiom.hibernate;

/**
 * Class that extends Oracle9iDialect for add left_outer_join function 
 * 
 * @author Alexander Angulo
 * 
 */
public class OracleDialect extends org.hibernate.dialect.Oracle9iDialect {

	/**
	 * 
	 */
	public OracleDialect() {
		super();
		registerFunction("left_outer_join", new LeftOuterJoinFunction());
	}
}

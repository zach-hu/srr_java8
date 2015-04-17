package com.tsa.puridiom.hibernate;

/**
 * Class that extends SQLServerDialect for add
 * left_outer_join function
 * to_date function
 *
 * @author Alexander Angulo
 *
 */
public class SQLServerXDialect extends org.hibernate.dialect.SQLServerDialect {

	/**
	 *
	 */
	public SQLServerXDialect() {
		super();
		registerFunction("left_outer_join", new LeftOuterJoinFunction());
		registerFunction("to_date", new SQLServerToDate()) ;
		registerFunction("TO_DATE", new SQLServerToDate()) ;
		registerFunction("sysdate", new SQLServerSysDate());
		registerFunction("SYSDATE", new SQLServerSysDate());
	}
}

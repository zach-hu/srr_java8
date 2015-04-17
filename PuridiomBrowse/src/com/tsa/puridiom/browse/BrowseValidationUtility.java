package com.tsa.puridiom.browse;

import java.util.HashSet;
import java.util.Set;

public class BrowseValidationUtility {
	public static Set permissibleOperators = new HashSet();
	public static Set permissibleLogicalOperators = new HashSet();
	public static Set permissibleSortOperators = new HashSet();
	
	static
	{
		permissibleOperators.add("=");
		permissibleOperators.add(">");
		permissibleOperators.add(">=");
		permissibleOperators.add("LIKE");
		permissibleOperators.add("like");
		permissibleOperators.add("<");
		permissibleOperators.add("<=");
		permissibleOperators.add("<>");
		permissibleOperators.add("isnull");
		
		permissibleLogicalOperators.add("OR");
		permissibleLogicalOperators.add("or");
		permissibleLogicalOperators.add("AND");
		permissibleLogicalOperators.add("and");
		
		permissibleSortOperators.add("N");
		permissibleSortOperators.add("ASC");
		permissibleSortOperators.add("DESC");
	}
}

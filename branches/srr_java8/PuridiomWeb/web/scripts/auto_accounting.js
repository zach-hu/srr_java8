	function viewCapitalClearingAccount(icHeader)
	{
		var newInputField = "<input type='hidden' name='CapitalClearingAccount_icHeader' value='" + icHeader + "'>";
		setHiddenFields(newInputField);
		doSubmit("/admin/auto_accounting/capital_clearing_account_review.jsp", "CapitalClearingAccountRetrieveById");
	}
		function viewXrefCombo(icXref)
	{
		var newInputField = "<input type='hidden' name='XrefCombo_icXref' value='" + icXref + "'>";
		setHiddenFields(newInputField);
		doSubmit("/admin/auto_accounting/XrefCombo_review.jsp", "XrefComboRetrieveById");
	}
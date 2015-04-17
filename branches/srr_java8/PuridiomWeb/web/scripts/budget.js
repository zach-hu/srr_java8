	function viewBudget(budgetId)
	{
		var newInputField = "<input type='hidden' name='BudgetCenter_budgetId' value='" + budgetId + "'>";
		setHiddenFields(newInputField);
		doSubmit("/budget/budget_review.jsp", "BudgetCenterRetrieveById");
	}

	function formatPrice(fld) {
		fld.value = nformat(nfilter(fld), 2);
	}
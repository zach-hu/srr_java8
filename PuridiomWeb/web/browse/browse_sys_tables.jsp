<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/browse/browse_form.jsp" %>

<tsa:hidden name="browsePage" value="/browse/browse_sys_tables.jsp"/>

<table border=0 width=<%=formWidth%>>
<tr>
	<td width=50% align=center>
<%	if (! (irows == 36 && tableType.equalsIgnoreCase("VSBA"))) {%>
		<div id="pxbutton"><a href="javascript: addSysTable(); void(0);">Add</a></div>
		<tsa:hidden name="tableType" value="<%=tableType%>"/>
<%	} else {%>&nbsp;
<%	}%>
	</td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/systemtables/system_tables.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	hideArea("resetOriginalOption");
	setNavCookie("/browse/browse_sys_tables.jsp", "BrowseRetrieveById", "<%=browseObject.getTitle()%>");
	setBrowseCookie("${esapi:encodeForJavaScript(browseObject.browseName)}");

	frm.browseName.value = "<%=browseObject.getBrowseName()%>";
<%	if (browseObject.getBrowseName().equalsIgnoreCase("stdtable-admin") || browseObject.getBrowseName().indexOf("autoacc")>=0) { %>
			displayArea("viewReportDisplay");
<%	} %>

	function addSysTable(table)
	{
		var title = '<%=browseObject.getTitle()%>';

		if (frm.browseName.value.indexOf("autoacc")>=0) {
			var hiddenFields = "";
			hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_icXref\" value=\"\">";
			if (title.indexOf("Capital Clearing Account") >= 0) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_xrefType\" value=\"CPTL\">";
			}
			if (title.indexOf("CAR / Project Combinations") >= 0) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_xrefType\" value=\"CARP\">";
			}
			if (title.indexOf("Entity / Department Combinations") >= 0) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_xrefType\" value=\"ENDP\">";
			}
			if (title.indexOf("Division Specific Combinations") >= 0) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_xrefType\" value=\"DIVS\">";
			}
			if (title.indexOf("Commodity Account Combinations") >= 0) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_xrefType\" value=\"CMOD\">";
			}
			setHiddenFields(hiddenFields);
			doSubmit('/admin/auto_accounting/autoaccounting_review.jsp','DoNothing');
		}
		if (frm.browseName.value.indexOf("xrefcombo") >= 0) {
			var hiddenFields = "";
			hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_icXref\" value=\"\">";
			if (title.indexOf("Location") >= 0) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_xrefType\" value=\"BINS\">";
			} else {
				hiddenFields = hiddenFields + "<input type=hidden name=\"XrefCombo_xrefType\" value=\"CARP\">";
			}
			setHiddenFields(hiddenFields);
			doSubmit('/admin/xrefcombo.jsp','DoNothing');
		}
		else if (frm.browseName.value.indexOf("timezone")>=0) {
			doSubmit('/admin/systemtables/timezone.jsp','DoNothing');
		}
		else if (frm.browseName.value.indexOf("inscategorylevel-admin") >= 0) {
			doSubmit('admin/systemtables/inscategorylevel.jsp','DoNothing');
		}
		else if (title.indexOf("Address") >= 0)
		{
			doSubmit('admin/systemtables/address.jsp','DoNothing');
		}
		else if (title.indexOf("Checklist") >= 0)
		{
			doSubmit('admin/systemtables/checklistentry.jsp','DoNothing');
		}
		else if (title.indexOf("Review Finalize") >= 0)
		{
			doSubmit('admin/systemtables/reviewfinalizechecklistentry.jsp','DoNothing');
		}
		else if (title.indexOf("Comments") > 0)
		{
			doSubmit('admin/systemtables/stdcomment.jsp','DoNothing');
		}
		else if (title.indexOf("Commodity") >= 0 || frm.browseName.value == "commodity-admin")
		{
			doSubmit('admin/systemtables/commodity.jsp','InsCategoryLevelRetrieveAll');
		}
		else if (title.indexOf("Country") >= 0)
		{
			doSubmit('admin/systemtables/country.jsp','DoNothing');
		}
		else if (title.indexOf("Currency") >= 0)
		{
			doSubmit('admin/systemtables/currcode.jsp','DoNothing');
		}
		else if ( ((title.indexOf("Department") >= 0) || (title.indexOf("Division") >= 0)) && orgId.toLowerCase()!="bly07p" && orgId.toLowerCase()!="hoy08p" && orgId.toLowerCase()!="wpc08p")
		{
			<% if(oid.equalsIgnoreCase("dtn07p")){ %>
				doSubmit('admin/systemtables/stdtable.jsp','DoNothing');
			<% } else {%>
				doSubmit('admin/systemtables/department.jsp','DoNothing');
			<% } %>
		}
		else if (title.indexOf("Cost Center") >= 0 && orgId.toLowerCase()=="bly07p")
		{
			doSubmit('admin/systemtables/department.jsp','DoNothing');
		}
		else if (title.indexOf("Lab Browse") >= 0 && orgId.toLowerCase()=="hoy08p")
		{
			doSubmit('admin/systemtables/department.jsp','DoNothing');
		}
		else if (title.indexOf("Department") >= 0 && orgId.toLowerCase()=="wpc08p" && frm.browseName.value == "department-admin")
		{
			doSubmit('admin/systemtables/department.jsp','DoNothing');
		}
		else if (title.indexOf("Inspection Criteria") >= 0)
		{
			doSubmit('admin/systemtables/inspectioncriteria.jsp','DoNothing');
		}
		else if (title.indexOf("Inspection Standard Criteria") >= 0)
		{
			doSubmit('admin/systemtables/inspectionstd.jsp','DoNothing');
		}
		else if (title.indexOf("Fiscal Year") >= 0)
		{
			doSubmit('admin/systemtables/autogen.jsp','DoNothing');
		}
		else if (title.indexOf("Payment Terms") >= 0)
		{
			doSubmit('admin/systemtables/paymentterm.jsp','DoNothing');
		}
		else if (title.indexOf("Purchase Cards") >= 0)
		{
			doSubmit('admin/systemtables/purchasecard.jsp','DoNothing');
		}
		else if (title.indexOf("Machine") >= 0)
		{
			doSubmit('admin/systemtables/invmachine.jsp','DoNothing');
		}
		else if (title.indexOf("Task") >= 0)
		{
			doSubmit('admin/systemtables/invtask.jsp','DoNothing');
		}
		else if (title.indexOf("Method") >= 0)
		{
			doSubmit('admin/systemtables/invmethod.jsp','DoNothing');
		}
		else if (title.indexOf("Stage") >= 0)
		{
			doSubmit('admin/systemtables/invstage.jsp','DoNothing');
		}
		else if (title.indexOf("Work Center") >= 0)
		{
			doSubmit('admin/systemtables/invworkcenter.jsp','DoNothing');
		}
		else if (title.indexOf("Questions") > 0)
		{
			doSubmit('admin/systemtables/stdquestion.jsp','DoNothing');
		}
		else if (title.indexOf("Standard Account") >= 0)
		{
			doSubmit('admin/systemtables/stdaccount.jsp','DoNothing');
		}
		else if (title.indexOf("Tax Code") >= 0)
		{
			doSubmit('admin/systemtables/taxcode.jsp','DoNothing');
		}
		else if (title.indexOf("Units of Measure") >= 0)
		{
			doSubmit('admin/systemtables/unitofmeasure.jsp','DoNothing');
		}
		else
		{
			doSubmit('admin/systemtables/stdtable.jsp','DoNothing');
		}
	}

	function returnMe(code)
	{
		var title = '<%=browseObject.getTitle()%>';
		var tableType = '<%=tableType%>';
		var myCell = document.getElementById("hiddenFields");
		if ((title.indexOf("Inventory Location")) >= 0)
		{
			var myCell = document.getElementById("hiddenFields");
			myCell.innerHTML = "";
			var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"InvBinLocation_itemLocation\" value=\"" + code + "\" >";
			myCell.innerHTML = newInputField;
			doSubmit('/inventory/physical_count.jsp', 'InvBinLocationRetrieveByLocation');
		}
		else if ((title.indexOf("warehouse")) >= 0)
		{
			popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt=" + code + ";logicalOperator=AND;originalFilter=Y;sort=N;"
			popupParameters = popupParameters + "tableType=" + code + ";";
			popupParameters =  popupParameters + "formField=XrefCombo_code2;browseName=itemwarehouse;allowBrowse=true";
			doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
		}
		else
		{
			myCell.innerHTML = "";
			var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"StdTable_tableType\" value=\"" + tableType + "\" ><INPUT TYPE=\"HIDDEN\" NAME=\"StdTable_tableKey\" value=\"" + code + "\" >";
			myCell.innerHTML = newInputField;
			doSubmit('/admin/systemtables/stdtable.jsp', 'StdTableRetrieveById');
		}
	}

	function returnAddress(code)
	{
		var newInputField = "<input type='hidden' name='Address_addressCode' value='" + code + "'><input type='hidden' name='Address_addressType' value='ADDR'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/address.jsp', 'AddressRetrieveById');
	}

	function returnInsCategoryLevel(code)
	{
		var newInputField = "<input type='hidden' name='InsCategoryLevel_icIcl' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/inscategorylevel.jsp', 'InsCategoryLevelRetrieveById');
	}

	function returnCommodity(code)
	{
		var newInputField = "<input type='hidden' name='Commodity_commodity' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/commodity.jsp', 'CommodityRetrieveById;InsCategoryLevelRetrieveAll');
	}

	function returnSubCommodity(code)
	{
		var newInputField = "<input type='hidden' name='SubCommodity_commodity' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/subcommodity.jsp', 'SubCommodityRetrieveById');
	}

	function returnCountry(code)
	{
		var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"Country_countryCode\" value=\"" + code + "\" >";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/country.jsp', 'CountryRetrieveById');
	}

	function returnCurrCode(code)
	{
		var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"CurrCode_currencyCode\" value=\"" + code + "\" >";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/currcode.jsp', 'CurrCodeRetrieveById');
	}

	function returnDepartment(code)
	{
		var newInputField = "<input type='hidden' name='Department_departmentCode' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/department.jsp', 'DepartmentRetrieveById');
	}

	function returnFiscalYear(doctype, year)
	{
		var newInputField = "<input type='hidden' name='AutoGen_documentType' value='" + doctype + "'><input type='hidden' name='AutoGen_genYear' value='" + year + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/autogen.jsp', 'AutoGenRetrieveById');
	}

	function returnInspectionStd(code)
	{
		var newInputField = "<input type='hidden' name='InspectionStd_standardCode' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/inspectionstd.jsp', 'InspectionStdRetrieveBy');
	}

	function returnInspectionCriteria(inspcode, critcode)
	{
		var newInputField = "<input type='hidden' name='InspectionCrit_critNo' value='" + critcode + "'>" +
		"<input type='hidden' name='InspectionCrit_inspectCode' value='" + inspcode + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/inspectioncriteria.jsp', 'InspectionCritRetrieveById');
	}


	function returnPaymentTerm(code)
	{
		var newInputField = "<input type='hidden' name='PaymentTerm_termsCode' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/paymentterm.jsp', 'PaymentTermRetrieveById');
	}

	function returnPurchaseCard(code)
	{
		var newInputField = "<input type='hidden' name='PurchaseCard_pcardCode' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/purchasecard.jsp', 'PurchaseCardRetrieveById');
	}

	function returnInvMachine(code)
	{
		var newInputField = "<input type='hidden' name='InvMachine_machineId' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/invmachine.jsp', 'InvMachineRetrieveById');
	}
	function returnInvTask(code)
	{
		var newInputField = "<input type='hidden' name='InvTask_taskId' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/invtask.jsp', 'InvTaskRetrieveById');
	}

	function returnInvMethod(code)
	{
		var newInputField = "<input type='hidden' name='InvMethod_methodId' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/invmethod.jsp', 'InvMethodRetrieveById');
	}

	function returnInvWorkCenter(code)
	{
		var newInputField = "<input type='hidden' name='InvWorkCenter_workCenterId' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/invworkcenter.jsp', 'InvWorkCenterRetrieveById');
	}

	function returnInvStage(code)
	{
		var newInputField = "<input type='hidden' name='InvStage_stageId' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/invstage.jsp', 'InvStageRetrieveById');
	}

	function returnStdAccount(accountIc)
	{
		var newInputField = "<input type='hidden' name='Account_icHeader' value='" + accountIc + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/stdaccount.jsp', 'AccountRetrieveByHeader');
	}

	function returnStdComment(commentId)
	{
		var newInputField = "<input type='hidden' name='StdComment_commentId' value='" + commentId + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/stdcomment.jsp', 'StdCommentRetrieveById');
	}

	function returnStdQuestion(icQuestion)
	{
		var newInputField = "<input type='hidden' name='StdQuestion_icQuestion' value='" + icQuestion + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/stdquestion.jsp', 'StdQuestionRetrieveById');
	}

	function returnTaxCode(code)
	{
		var newInputField = "<input type='hidden' name='TaxCode_taxCode' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/taxcode.jsp', 'TaxCodeRetrieveById');
	}

	function returnUmCode(code)
	{
		var newInputField = "<input type='hidden' name='UnitOfMeasure_umCode' value='" + code + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/unitofmeasure.jsp', 'UnitOfMeasureRetrieveById');
	}

	function returnChecklistEntry(ic) {
		var newInputField = "<input type='hidden' name='ChecklistEntry_icQuestion' value='" + ic + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/checklistentry.jsp', 'ChecklistEntryRetrieveById');
	}

	function returnReviewFinalizeChecklistEntry(ic) {
		var newInputField = "<input type='hidden' name='ChecklistEntry_icQuestion' value='" + ic + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/systemtables/reviewfinalizechecklistentry.jsp', 'ChecklistEntryRetrieveById');
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function viewReport(tableType)
	{
		setNavCookie("/browse/browse_sys_tables.jsp", "BrowseRetrieveById", "View Report");
		var newInputField = "<input type='hidden' name='reportModule' value='REPADM'>";
		if (tableType.indexOf("autoacc") >= 0) {
			//newInputField = newInputField + "<input type='hidden' name='StdTable_id_tableType' value='" +tableType + "'>";
			newInputField = newInputField + "<input type='hidden' name='jasperFile' value=''>";
			newInputField = newInputField + "<input type='hidden' name='reportTitleName' value=''>";
		    setHiddenFields(newInputField);
			reportQueueFilter(tableType, tableType, '<%=browseObject.getTitle()%>');
		}
		else {
			newInputField = newInputField + "<input type='hidden' name='StdTable_id_tableType' value='" +tableType + "'>";
			newInputField = newInputField + "<input type='hidden' name='jasperFile' value=''>";
			newInputField = newInputField + "<input type='hidden' name='reportTitleName' value=''>";
		    setHiddenFields(newInputField);
		    reportQueueFilter('r-std-table', 'r-std-table', '<%=browseObject.getTitle(oid, language)%>');
		}
	}

	function reviewXrefComboGeneral(icXref,xrefType) {
		var hiddenFields = "";
		hiddenFields = hiddenFields + "<input type='hidden' name='XrefCombo_icXref' value='" + icXref + "'>";
		hiddenFields = hiddenFields + "<input type='hidden' name='XrefCombo_xrefType' value='" + xrefType + "'>";
		setHiddenFields(hiddenFields);
		doSubmit('/admin/auto_accounting/autoaccounting_review.jsp', 'XrefComboRetrieveById');
	}

	function viewXrefCombo(icXref, xrefType) {
		var hiddenFields = "";
		hiddenFields = hiddenFields + "<input type='hidden' name='XrefCombo_icXref' value='" + icXref + "'>";
		hiddenFields = hiddenFields + "<input type='hidden' name='XrefCombo_xrefType' value='" + xrefType + "'>";
		setHiddenFields(hiddenFields);
		doSubmit('/admin/xrefcombo.jsp', 'XrefComboRetrieveById');
	}

// End Hide script -->
</SCRIPT>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsagate.foundation.utility.Dates"%>
<%
	String reportModule = (String)request.getAttribute("reportModule");
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
<tsa:hidden name="jasperFile" value=""/>
<tsa:hidden name="reportTitleName" value=""/>
<tsa:hidden name="ReportQueue_module" value="<%=reportModule%>"/>

<br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Auto Close Options</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr>
				<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
			</tr>
			<tr>
				<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
			</tr>
		</table>
	</td>
</tr>
</table>
<br>
<br>
<table id="filterOptions" width=680px cellpadding=0 cellspacing=0 border=0>
	<tr height=10px></tr>
	<tr>
		<td valign="middle"><tsa:hidden name="ReportQueue_type" value="PDF"/></td>
	</tr>
	<tr>
		<td width="100px" align="right" nowrap>From:&nbsp;</td>
		<td nowrap>
			<input type="text" name="PoHeader_poFrom" tabindex=5 size=15 maxlength=15 value="">
			<a href="javascript: show_calendar('PoHeader_poFrom', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
		</td>
		<td width="100px" align="right" nowrap>To:&nbsp;</td>
		<td nowrap>
			<input type="text" name="PoHeader_poTo" tabindex=5 size=15 maxlength=15 value="">
			<a href="javascript: show_calendar('PoHeader_poTo', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
		</td>
	</tr>
	<tr>
		<td width="100px" align="right" nowrap>PO Number:&nbsp;</td>
		<td nowrap>
			<input type="text" name="PoHeader_poNumber" tabindex=5 size=15 maxlength=15 value="">
		</td>
	</tr>
		<td width="100px" align="right" nowrap><a href="javascript: browseAccount('Account_fld1', 'AC01'); void(0);" title="Click here to choose the Entity for this order or enter the Entity in the box on the right.">Entity:&nbsp;</a></td>
		<td nowrap>
			<input type="text" name="Account_fld1" tabindex=5 size=15 maxlength=15 value="">
		</td>
	</tr>
	<tr>
		<td width="100px" align="right" nowrap><a href="javascript: browseAccount('Account_fld2', 'AC02'); void(0);" title="Click here to choose the Department for this order or enter the Department in the box on the right.">Department:&nbsp;</td>
		<td nowrap>
			<input type="text" name="Account_fld2" tabindex=5 size=15 maxlength=15 value="">
		</td>
	</tr>
</table>
<br>
<br>
<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>

		<td align=center>
			<a href="javascript: viewPO(); void(0);">
			<img class=button src="<%=contextPath%>/images/button_submit.gif" border=0></a>
		</td>
		<td align=center>
			<a href="javascript: confirmReportAction('R'); void(0);">
			<img class=button src="<%=contextPath%>/images/button_preview.gif" border=0></a>
		</td>
		<td align=center>
			<a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">
			<img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a>
		</td>
	</tr>
</table>

<%@ include file="/system/footer.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>

<script value=JavaScript>
<!-- Hide script

frm = document.purchaseform;

function viewPO() {
	frm.browseName.value = "auto-close";
	setFilterOptions();
	setSort();
	browse('auto-close');
}

function setFilterOptions() {
	var poFrom = "";
	var poTo = "";
	var poNumber = "";
	var fld1 = "";
	var fld2 = "";

	if (frm.PoHeader_poFrom) {
		poFrom = frm.PoHeader_poFrom.value;
	}
	if (frm.PoHeader_poTo) {
		poTo = frm.PoHeader_poTo.value;
	}
	if (frm.PoHeader_poNumber) {
		poNumber = frm.PoHeader_poNumber.value;
	}
	if (frm.Account_fld1) {
		fld1 = frm.Account_fld1.value;
	}
	if (frm.Account_fld2) {
		fld2 = frm.Account_fld2.value;
	}
	setOriginalFilter("PoHeader_poDate", ">=", poFrom);
	setOriginalFilter("PoHeader_poDate", "<=", poTo);
	setOriginalFilter("PoHeader_poNumber", "LIKE", poNumber);
	setOriginalFilter("frm.Account_fld1", "LIKE", fld1);
	setOriginalFilter("frm.Account_fld2", "LIKE", fld2);
}

function setSort() {
	var myCell = document.getElementById("filterFields");
	var filterFields = myCell.innerHTML;

    filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"PoHeader_poNumber\">";
	filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"\">";
	filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"\">";
	filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"\">";
	filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"Y\">";
	filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"ASC\">";

	myCell.innerHTML = filterFields;
}

function confirmReportAction(action)
{
	var timeout = 5000;
	var message = '';
	var functionToEval = '';
	var sentenceToExecute = '';
	var aDivs = new Array('previewReport', 'registerReport', 'deleteReport');

	switch(action)
	{
		case 'U':
			message = '<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "update-report", "Update ") %>';
			functionToEval = 'updateReportQueue();';
			break;
		case 'D':
			message = '<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "delete-report", "Delete ") %>';
			functionToEval = 'deleteReportQueue();';
			break;
		case 'R':
			message = '<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "view-report", "View ") %>';
			functionToEval = 'printPreview();';
			break;
	}

	frm.style.cursor = 'wait';

	for (var i = 0; i < aDivs.length; i++)
	{
		if (document.getElementById(aDivs[i]) != null)
		{
			hideAreaWithBlock(aDivs[i]);
			sentenceToExecute += "displayArea('" + aDivs[i] + "'); ";
		}
	}

	if (verifyAction(message + ' PO Auto-Close Preview?'))
	{
		eval(functionToEval);

	} else {
		timeout = 250;
	}

	setTimeout(sentenceToExecute + "frm.style.cursor = 'default';", timeout);
}


function printPreview() {
	frm.browseName.value = "r-pos-s-acl";
	frm.jasperFile.value = "r-pos-s-acl.jasper";
	frm.reportTitleName.value = "PO Auto-Close Preview";
	var poFrom = "";
	var poTo = "";
	var poNumber = "";
	var fld1 = "";
	var fld2 = "";
	var rqtype="";

	popupParameters = "browseName=r-pos-s-acl;execute=N";
	popupParameters = popupParameters + ";jasperFile=" + frm.jasperFile.value;
	popupParameters = popupParameters + ";reportTitleName=" + frm.reportTitleName.value;

	rqtype = frm.ReportQueue_type.value;
	popupParameters = popupParameters + ";format=" + frm.ReportQueue_type.value;

	popupParameters = popupParameters + ";viewNow=Y";
	popupParameters = popupParameters + ";ReportQueue_type=" + rqtype;
	popupParameters = popupParameters + ";ReportQueue_module=REPPO";
	popupParameters = popupParameters + ";ReportQueue_name=r-pos-s-acl";
	popupParameters = popupParameters + ";ReportQueue_sendFrom=sysadm@dtn.com";
	popupParameters = popupParameters + ";ReportQueue_alias=PO Auto-Close Preview";

	popupParameters = popupParameters + ";colname=PoHeader_icPoHeader";
	popupParameters = popupParameters + ";operator==";
	popupParameters = popupParameters + ";filter_txt=";
	popupParameters = popupParameters + ";logicalOperator=AND";
	popupParameters = popupParameters + ";originalFilter=Y";
	popupParameters = popupParameters + ";sort=N";

	if (frm.PoHeader_poFrom) {
		poFrom = frm.PoHeader_poFrom.value;
		popupParameters = popupParameters + ";colname=PoHeader_poDate";
		popupParameters = popupParameters + ";operator=>=";
		popupParameters = popupParameters + ";filter_txt=" + poFrom;
		popupParameters = popupParameters + ";logicalOperator=AND";
		popupParameters = popupParameters + ";originalFilter=Y";
		popupParameters = popupParameters + ";sort=N";
	}
	if (frm.PoHeader_poTo) {
		poTo = frm.PoHeader_poTo.value;
		popupParameters = popupParameters + ";colname=PoHeader_poDate";
		popupParameters = popupParameters + ";operator=<=";
		popupParameters = popupParameters + ";filter_txt=" + poTo;
		popupParameters = popupParameters + ";logicalOperator=AND";
		popupParameters = popupParameters + ";originalFilter=Y";
		popupParameters = popupParameters + ";sort=N";
	}
	if (frm.PoHeader_poNumber) {
		poNumber = frm.PoHeader_poNumber.value;
		popupParameters = popupParameters + ";colname=PoHeader_poNumber";
		popupParameters = popupParameters + ";operator=LIKE";
		popupParameters = popupParameters + ";filter_txt=" + poNumber;
		popupParameters = popupParameters + ";logicalOperator=AND";
		popupParameters = popupParameters + ";originalFilter=Y";
		popupParameters = popupParameters + ";sort=N";
	}
	if (frm.Account_fld1) {
		fld1 = frm.Account_fld1.value;
		popupParameters = popupParameters + ";colname=Account_fld1";
		popupParameters = popupParameters + ";operator=LIKE";
		popupParameters = popupParameters + ";filter_txt=" + fld1;
		popupParameters = popupParameters + ";logicalOperator=AND";
		popupParameters = popupParameters + ";originalFilter=Y";
		popupParameters = popupParameters + ";sort=N";
	}
	if (frm.Account_fld2) {
		fld2 = frm.Account_fld2.value;
		popupParameters = popupParameters + ";colname=Account_fld2";
		popupParameters = popupParameters + ";operator=LIKE";
		popupParameters = popupParameters + ";filter_txt=" + fld2;
		popupParameters = popupParameters + ";logicalOperator=AND";
		popupParameters = popupParameters + ";originalFilter=Y";
		popupParameters = popupParameters + ";sort=N";
	}

	doSubmitToPopup('', 'PrintReportQueuePdf', 'width=775px', 'height=850px');
}

function browseAccount(frmField, udf) {
	popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=" + udf + ";logicalOperator=AND;originalFilter=Y;sort=N;"
	popupParameters = popupParameters + "tableType=" + udf + ";";
	popupParameters =  popupParameters + "formField=" + frmField +";browseName=stdtable;allowBrowse=true";
	doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
}

// End Hide script -->
</script>
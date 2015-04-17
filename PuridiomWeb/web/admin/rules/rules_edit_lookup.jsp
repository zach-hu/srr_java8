<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="java.lang.reflect.Method" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<script language="JavaScript1.2" src="<%=contextPath%>/scripts/calendar.js"></script>
<script language="JavaScript1.2" src="<%=contextPath%>/scripts/date_check.js"></script>

<%
	String lookupTable = (String)request.getAttribute("lookupTable");
	String lookupSql = (String)request.getAttribute("lookupSql");
	String lookupIndex = (String)request.getAttribute("lookupIndex");

	Map lookupMethods = (Map)request.getAttribute("lookupMethods");
%>

<tsa:hidden name="lookupTable" value="<%=lookupTable%>"/>

<table border=0 cellpadding=0 cellspacing=0 width=655px>
<tr><td><br></td></tr>
<tr>
	<td width=100%>
		<table cellpadding=0 cellspacing=0 border=0 width=100%>
		<tr>
			<td valign=top width=50px height=30px>
				<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
				<tr>
					<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class=hdr12 valign=middle>
						<div style="margin-left:10px; margin-right:10px" class=hdr12><%=lookupTable%> Lookup</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign=bottom align=left><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign=bottom align=right height=30px width=100%>
				<table cellpadding="0" cellspacing="0" border=0 width=100%>
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
	</td>
</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formWidth%>">
<tr>
	<td align="left" valign="top">
		<table id="ruleTable" border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
		</table>

		<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
		<tr>
			<td width="2%" align="right">&nbsp;</td>
			<td width="98%" align="left">
				<a href="javascript: addClause(); void(0);"><font class="reset"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addnew", "Add new")%></b></font></a>
			</td>
		</tr>
		</table>

		<table style="display: none;">
		<tr>
			<td width="2%" align="right">&nbsp;</td>
			<td width="98%" align="left">
				<table id="ruleTableTemplate">
					<tr>
						<td align="left">Field</td>
						<td align="left">Operand</td>
						<td align="left" style="display: none;" id="compareTH_ReplaceIndex">Compare</td>
						<td align="left" style="display: none;" id="dateTH_ReplaceIndex"></td>
					</tr>
					<tr>
						<td>
							<select id="documentAttribute_ReplaceIndex" onchange="setDocumentAttribute(this, '', '_ReplaceIndex');">
								<option value="">-- Select a field --</option>
							</select>
						</td>
						<td>
							<select id="operator_ReplaceIndex">
								<option value="">-- Select a operator --</option>
								<option value="E">Equal</option>
								<option value="NE">Not Equal</option>
								<option value="G">Greater</option>
								<option value="L">Less</option>
								<option value="GE">Greater Equal</option>
								<option value="LE">Less Equal</option>
							</select>
						</td>
						<td style="display: none;" id="compareTD_ReplaceIndex">
							<input type="text" id="compare_ReplaceIndex" name="compare_ReplaceIndex" onchange="addUp(this);">
						</td>
						<td style="display: none;" id="dateTD_ReplaceIndex">
							<a href="javascript: show_calendar('compare_ReplaceIndex', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" border=0></a>
							Blank for current date
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</table>

		<table style="display: none;">
		<tr>
			<td width="2%" align="right"></td>
			<td width="98%" align="left">
				<table id="logOperatorTableTemplate">
					<tr>
						<td align="left">Logical Operator</td>
					</tr>
					<tr>
						<td>
							<select id="logicalOperator_ReplaceIndex">
								<option value="">-- Select a logical --</option>
								<option value="AND">And</option>
								<option value="OR">Or</option>
							</select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
	<tr>
		<td width="50%" align="center"><div id="pxbutton"><a href="javascript: saveMe(); void(0);">Save</a></div></td>
		<td width="50%" align="center"><div id="pxbutton"><a href="javascript: returnMe(); void(0);">Return</a></div></td>
	</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;

	var operatorsArray = new Array();
	operatorsArray['E'] = "=";
	operatorsArray['NE'] = "<>";
	operatorsArray['G'] = ">";
	operatorsArray['L'] = "<";
	operatorsArray['GE'] = ">=";
	operatorsArray['LE'] = "<=";

	var lookupMethods = new Array();

	var currentMethodType = null;

	var currentTotalRows = 0;

	setMethods();

	<%	if (!HiltonUtility.isEmpty(lookupSql)) { %>
		getRuleExpression();
	<%	} else { %>
		addClause();
	<%	} %>

	function setMethods()
	{
	<%	Object[] lookupKeys = lookupMethods.keySet().toArray();
		Arrays.sort(lookupKeys);
		for (int i = 0; i < lookupKeys.length; i++)
		{
			Method method = (Method)lookupMethods.get(lookupKeys[i]); %>
			lookupMethods['<%=method.getName()%>'] = {
				methodType: "<%=method.getReturnType().getSimpleName()%>",
				methodLabel: "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, method.getName().substring(3).toLowerCase(), method.getName().substring(3))%>"
			};
	<%	} %>
	}

	function addClause()
	{
		var contextPath = '<%=contextPath%>';

		var myTable = document.getElementById("ruleTable");

		if (currentTotalRows > 0)
		{
			myRow = createRow(myTable);

			var logOperatorTableTemplate = document.getElementById("logOperatorTableTemplate");
			var logOperatorTableTemplateHtml = logOperatorTableTemplate.innerHTML.replace(/_ReplaceIndex/g, "_" + currentTotalRows);

			myCell = createCell(myRow);
			myCell.width = "2%";
			myCell.align = "right";

			myCell = createCell(myRow);
			myCell.width = "98%";
			myCell.align = "left";
			myCell.innerHTML = "<table>" + logOperatorTableTemplateHtml + "</table>";
		}

		myRow = createRow(myTable);

		var ruleTableTemplate = document.getElementById("ruleTableTemplate");
		var ruleTableTemplateHtml = ruleTableTemplate.innerHTML.replace(/_ReplaceIndex/g, "_" + currentTotalRows);

		myCell = createCell(myRow);
		myCell.width = "2%";
		myCell.align = "right";
		if (currentTotalRows > 0) {
			myCell.innerHTML = "<a href=\"javascript: if (confirm('Are you sure you wish to delete this clause?')) { deleteClause(" + currentTotalRows + "); } void(0);\"><img src=\"" + contextPath + "/images/delete.gif\" border=\"0\"></a>&nbsp;";
		}

		myCell = createCell(myRow);
		myCell.width = "98%";
		myCell.align = "left";
		myCell.innerHTML = "<table>" + ruleTableTemplateHtml + "</table>";

		createDocumentAttribute('', '_' + currentTotalRows);

		currentTotalRows ++;
	}

	function deleteClause(row)
	{
		var contextPath = '<%=contextPath%>';

		var realRow = (row * 2) - 1;

		var myTable = document.getElementById("ruleTable");

		for (var i = (realRow + 2); i < myTable.rows.length; i+=2)
		{
			var rowReplaceOld = (((i + 1)/2));
			var rowReplaceNew = (((i + 1)/2) - 1);

			for (var j = 0; j < myTable.rows[i].cells.length; j++)
			{
				myTable.rows[i].cells[j].innerHTML = myTable.rows[i].cells[j].innerHTML.replace(new RegExp("_" + rowReplaceOld, 'g'), "_" + rowReplaceNew);
			}
			for (var j = 0; j < myTable.rows[i + 1].cells.length; j++)
			{
				myTable.rows[i + 1].cells[j].innerHTML = myTable.rows[i + 1].cells[j].innerHTML.replace(new RegExp("_" + rowReplaceOld, 'g'), "_" + rowReplaceNew);
				if (j == 0) {
					myTable.rows[i + 1].cells[j].innerHTML = "<a href=\"javascript: if (confirm('Are you sure you wish to delete this clause?')) { deleteClause(" + rowReplaceNew + "); } void(0);\"><img src=\"" + contextPath + "/images/delete.gif\" border=\"0\"></a>&nbsp;";
				}
			}
		}

		myTable.deleteRow(realRow);
		myTable.deleteRow(realRow);

		currentTotalRows --;
	}

	function createDocumentAttribute(valueObj, index)
	{
		var documentAttribute = document.getElementById("documentAttribute" + index);

		for (var methodName in lookupMethods)
		{
			var opcionSelect = document.createElement("option");
			opcionSelect.innerHTML = lookupMethods[methodName].methodLabel;
			if (valueObj == methodName) {
				opcionSelect.selected = true;
			}
			opcionSelect.value = methodName;
			documentAttribute.appendChild(opcionSelect);
		}
	}

	function setDocumentAttribute(selectObj, valueObj, index)
	{
		var selectDocumentAttribute = selectObj.value;

		var methodType = lookupMethods[selectDocumentAttribute].methodType;
		currentMethodType = methodType;

		var operator = document.getElementById("operator" + index);
		setOperator(operator, index);
	}

	function setOperator(select, index)
	{
		var selectOperator = select.value;

		var compare = document.getElementById("compare" + index);
		var compareTH = document.getElementById("compareTH" + index);
		var compareTD = document.getElementById("compareTD" + index);
		var dateTH = document.getElementById("dateTH" + index);
		var dateTD = document.getElementById("dateTD" + index);

		addUp(compare);
		compareTH.style.display = "";
		compareTD.style.display = "";
		if (currentMethodType != null && currentMethodType == "Date") {
			dateTH.style.display = "";
			dateTD.style.display = "";
		}
	}

	function addUp(compareObj)
	{
		if (currentMethodType != null && currentMethodType == "BigDecimal") {
			compareObj.value = nformat(eval(nfilter(compareObj)), 0);
		}
	}

	function saveMe() {
		if (validateLookupEdit()) {
			var sql = setRuleExpression();
			alert(sql);
			window.parent.frm.lookupSql<%=lookupIndex%>.value = sql;
			window.top.hidePopWin();
		}
	}

	function returnMe() {
		window.top.hidePopWin();
	}

	function validateLookupEdit() {
		for (var i = 0; i < currentTotalRows; i++)
		{
			var index = "_" + i;

			var documentAttribute = document.getElementById("documentAttribute" + index);
			var operator = document.getElementById("operator" + index);
			var compare = document.getElementById("compare" + index);

			var methodType = "";
			if (documentAttribute.value != "") {
				methodType = lookupMethods[documentAttribute.value].methodType;
			}

			if (documentAttribute.value == "" || operator.value == "") {
				alert("Complete the rule expression.");
				return false;
			}

			if (methodType == "Date" && compare && !chkdate(compare)) {
				compare.focus();
				alert("Invalid date.");
				return false;
			}

			if (i > 0)
			{
				var logicalOperator = document.getElementById("logicalOperator" + index);
				if (logicalOperator.value == "") {
					alert("Complete the rule expression.");
					return false;
				}
			}
		}

		return true;
	}

	function setRuleExpression()
	{
		var sql = "";

		for (var i = 0; i < currentTotalRows; i++)
		{
			var index = "_" + i;

			var documentAttribute = document.getElementById("documentAttribute" + index).value;
			var operator = document.getElementById("operator" + index).value;
			var compare = document.getElementById("compare" + index).value;

			documentAttribute = getDocumentAttribute(documentAttribute);
			operator = operatorsArray[operator];

			var tempSql = "<%=lookupTable%>." + documentAttribute + " " + operator + " '" + compare + "'";

			if (i > 0)
			{
				var logicalOperator = document.getElementById("logicalOperator" + index).value;

				tempSql = " " + logicalOperator + " " + tempSql;
			}

			sql = sql + tempSql;
		}

		return sql;
	}

	function getDocumentAttribute(documentAttribute)
	{
		return documentAttribute.substring(3,4).toLowerCase() + documentAttribute.substring(4);
	}

// End Hide script -->
</script>

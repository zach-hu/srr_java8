<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.GeneralStatus" %>
<%@ page import="java.lang.reflect.Method" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	Rules rule = (Rules) request.getAttribute("rules");
	if (rule == null)
	{
		rule = new Rules();
	}

	String ruleAction = (String)request.getAttribute("ruleAction");
	if (HiltonUtility.isEmpty(ruleAction))
	{
		ruleAction = "addRules";
	}

	String handlerAction = "RulesUpdate";
	if (ruleAction.equalsIgnoreCase("addRules"))
	{
		handlerAction = "RulesAdd";
	}

	String ruleName = rule.getRuleName();
	if (HiltonUtility.isEmpty(ruleName))
	{
		ruleName = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules", "Rules");
	}

	String ruleEnabled = rule.getEnabled();
	if (HiltonUtility.isEmpty(ruleEnabled))
	{
		ruleEnabled = "N";
	}

	String errorMsg = (String)request.getAttribute("errorMsg");
	errorMsg = HiltonUtility.ckNull(errorMsg);
	if (errorMsg.length() > 0) {
		errorMsg = errorMsg + "<br>";
	}

	Map reqMethods = (Map)request.getAttribute("reqMethods");
	Map rfqMethods = (Map)request.getAttribute("rfqMethods");
	Map poMethods = (Map)request.getAttribute("poMethods");
%>

<tsa:hidden name="Rules_icRule" size="15" maxLength="15" value="<%=rule.getIcRule() %>" />

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=ruleName%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom width=* align=right height=30px>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b></b></td>
			<td width=125px></td>
		</tr>
		<tr>
			<td align=right><b></b></td>
			<td width=125px></td>
		</tr>
		</table>
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

<br>
<br>
<table border="0" cellpadding="0" cellspacing="0" width="<%=formWidth%>">
<tr>
	<td class=error align=center>
		<table border=0 cellpadding=1 cellspacing=0 width=90%>
		<tr><td class=error align=center><%=errorMsg%><br></td></tr>
		</table>
	</td>
</tr>
<tr>
	<td align="center" valign="top">
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
		<tr>
			<td width=80% align=center valign=top>
				<table border=0 cellspacing="0" cellpadding="2" width="100%">
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-rulename", "Rule Name")%>:</td>
					<td><input type="text" title="" name="Rules_ruleName" size="50" maxLength="40" value="<%=rule.getRuleName() %>" onChange="this.value=this.value.toUpperCase();"></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-validationmessage", "Validation Message")%>:</td>
					<td><input type="text" title="" name="Rules_validationMessage" size="100" maxLength="255" value="<%=rule.getValidationMessage() %>" ></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-validationseverity", "Validation Severity")%>:</td>
					<td>
						<select name="Rules_validationSeverity">
							<option value="E" <% if (rule.getValidationSeverity().equals("E")) {%>selected<%}%>>Error</option>
							<option value="W" <% if (rule.getValidationSeverity().equals("W")) {%>selected<%}%>>Warning</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-ruleorder", "Order")%>:</td>
					<td><input type="text" title="" name="Rules_ruleOrder" size="25" maxLength="5" value="<%=rule.getRuleOrder() %>" ></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-enabled", "Enabled")%>:</td>
					<td>
						<input type="checkbox" name="c_checkbox" <%if (ruleEnabled.equals("Y")) { %>checked <% } %> onClick="setCheckbox(frm.Rules_enabled, 0)">
						<tsa:hidden name="Rules_enabled" size=15 maxLength=1 value="<%=ruleEnabled%>"/>
					</td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-validationlink", "Validation Link")%>:</td>
					<td><input type="text" title="" name="Rules_validationLink" size="100" maxLength="255" value="<%=rule.getValidationLink() %>" ></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-fieldname", "Field Name")%>:</td>
					<td><input type="text" title="" name="Rules_fieldName" size="50" maxLength="60" value="<%=rule.getFieldName() %>" ></td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-ruleexpression", "Rule Expression")%>:</td>
					<%--td><input type="text" title="" name="Rules_ruleExpression" size="100" maxLegth="255" value='<%=rule.getRuleExpression() %>'></td--%>
					<td><tsa:hidden name="Rules_ruleExpression"/></td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-moduleAccess", "Module Access")%>:</td>
					<td>
						<select name="Rules_moduleAccess">
							<option value="ADMIN" <% if (rule.getModuleAccess().equals("ADMIN")) {%>selected<%}%>>ADMIN</option>
							<option value="APPROVALS" <% if (rule.getModuleAccess().equals("APPROVALS")) {%>selected<%}%>>APPROVALS</option>
							<option value="DOCUMENTS" <% if (rule.getModuleAccess().equals("DOCUMENTS")) {%>selected<%}%>>DOCUMENTS</option>
							<option value="GENERAL" <% if (rule.getModuleAccess().equals("GENERAL")) {%>selected<%}%>>GENERAL</option>
							<option value="INVENTORY" <% if (rule.getModuleAccess().equals("INVENTORY")) {%>selected<%}%>>INVENTORY</option>
							<option value="INVOICE" <% if (rule.getModuleAccess().equals("INVOICE")) {%>selected<%}%>>INVOICE</option>
							<option value="LOGIN" <% if (rule.getModuleAccess().equals("LOGIN")) {%>selected<%}%>>LOGIN</option>
							<option value="PURCHASE ORDERS" <% if (rule.getModuleAccess().equals("PURCHASE ORDERS")) {%>selected<%}%>>PURCHASE ORDERS</option>
							<option value="PURIDIOMX ADMIN" <% if (rule.getModuleAccess().equals("PURIDIOMX ADMIN")) {%>selected<%}%>>PURIDIOMX ADMIN</option>
							<option value="RECEIVING" <% if (rule.getModuleAccess().equals("RECEIVING")) {%>selected<%}%>>RECEIVING</option>
							<option value="REQUISITIONS" <% if (rule.getModuleAccess().equals("REQUISITIONS")) {%>selected<%}%>>REQUISITIONS</option>
							<option value="SCHEDULES" <% if (rule.getModuleAccess().equals("SCHEDULES")) {%>selected<%}%>>SCHEDULES</option>
							<option value="REQUEST FOR QUOTES" <% if (rule.getModuleAccess().equals("REQUEST FOR QUOTES")) {%>selected<%}%>>REQUEST FOR QUOTES</option>
							<option value="SUPPLIER" <% if (rule.getModuleAccess().equals("SUPPLIER")) {%>selected<%}%>>SUPPLIER</option>
							<option value="CONTACT" <% if (rule.getModuleAccess().equals("CONTACT")) {%>selected<%}%>>SUPPLIER CONTACT</option>
							<option value="SUPPLIERPORTAL" <% if (rule.getModuleAccess().equals("SUPPLIERPORTAL")) {%>selected<%}%>>SUPPLIER PORTAL</option>
							<option value="SUPPLIERPORTALCONTACT" <% if (rule.getModuleAccess().equals("SUPPLIERPORTALCONTACT")) {%>selected<%}%>>SUPPLIER PORTAL CONTACT</option>
							<option value="TOTALS" <% if (rule.getModuleAccess().equals("TOTALS")) {%>selected<%}%>>TOTALS</option>
							<option value="USER" <% if (rule.getModuleAccess().equals("USER")) {%>selected<%}%>>USER</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-status", "Status")%>:</td>
					<td>
						<select name="Rules_status">
							<option value="<%=GeneralStatus.STATUS_PERMANENT%>" <%if(rule.getStatus().equals(GeneralStatus.STATUS_PERMANENT)){ %> selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "permanent", "Permanent")%></option>
							<option value="<%=GeneralStatus.STATUS_TEMPORARY%>" <%if(rule.getStatus().equals(GeneralStatus.STATUS_TEMPORARY)){ %> selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "temporary", "Temporary")%></option>
							<option value="<%=GeneralStatus.STATUS_INACTIVE%>" <%if(rule.getStatus().equals(GeneralStatus.STATUS_INACTIVE)){ %> selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inactive", "Inactive")%></option>
						</select>
					</td>
				</tr>
		        <tr>
					<td <%=HtmlWriter.isVisible(oid, "rules-owner")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-owner", "Owner")%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "rules-owner")%>><input type=text name="Rules_owner" value="<%=rule.getOwner()%>" size=20 maxLength=15 disabled></td>
		        </tr>
		        <tr>
					<td <%=HtmlWriter.isVisible(oid, "rules-lastchangeby")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-lastchangeby", "Last Changed By")%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "rules-lastchangeby")%>><input type=text name="Rules_lastChangeBy" value="<%=rule.getLastChangeBy()%>" size=20 maxLength=15 disabled></td>
		        </tr>
		        <tr>
					<td <%=HtmlWriter.isVisible(oid, "rules-lastchangedate")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-lastchangedate", "Last Change Date")%>:</td>
					<td <%=HtmlWriter.isVisible(oid, "rules-lastchangedate")%>><input type=text name="Rules_lastChangeDate" value="<%=HiltonUtility.getFormattedDate(rule.getLastChangeDate(), oid)%>" size=20 maxLength=15 disabled></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<table id="ruleTable" border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
		</table>

		<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
		<tr>
			<td width="10%" align="right">&nbsp;</td>
			<td width="90%" align="left">
				<a href="javascript: addClause(); void(0);"><font class="reset"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addnew", "Add new")%></b></font></a>
			</td>
		</tr>
		</table>

		<table style="display: none;">
		<tr>
			<td width="10%" align="right">&nbsp;</td>
			<td width="90%" align="left">
				<table id="ruleTableTemplate">
					<tr>
						<td align="left">Document</td>
						<td align="left">Field</td>
						<td align="left">Operand</td>
						<td align="left" style="display: none;" id="lookupTableTH_ReplaceIndex">Source</td>
						<td align="left" style="display: none;" id="lookupColumnTH_ReplaceIndex">Edit</td>
						<td align="left" style="display: none;" id="compareTH_ReplaceIndex">Compare</td>
						<td align="left" style="display: none;" id="dateTH_ReplaceIndex"></td>
						<td align="left" style="display: none;" id="booleanTH_ReplaceIndex"></td>
					</tr>
					<tr>
						<td>
							<select id="documentObject_ReplaceIndex" onchange="setDocumentObject(this, '', '_ReplaceIndex');">
								<option value="">-- Select a document --</option>
								<option value="RequisitionHeader">Requisition</option>
								<option value="RfqHeader">Solicitation</option>
								<option value="PoHeader">Order</option>
							</select>
						</td>
						<td>
							<select id="documentAttribute_ReplaceIndex" onchange="setDocumentAttribute(this, '', '_ReplaceIndex');">
								<option value="">-- Select a field --</option>
							</select>
						</td>
						<td>
							<select id="operator_ReplaceIndex" onchange="setOperator(this, '_ReplaceIndex');">
								<option value="">-- Select a operator --</option>
							</select>
						</td>
						<td style="display: none;" id="lookupTableTD_ReplaceIndex">
							<select id="lookupTable_ReplaceIndex" onchange="setLookupTable(this, '', '_ReplaceIndex');">
								<option value="">-- Select a source --</option>
								<option value="UserProfile">User Profile</option>
							</select>
						</td>
						<td style="display: none;" id="lookupColumnTD_ReplaceIndex">
							<a href="javascript: lookupEdit('_ReplaceIndex'); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" border=0></a>
							<tsa:hidden id="lookupSql_ReplaceIndex"/>
							<!--select id="lookupColumn_ReplaceIndex">
								<option value="">-- Select a column --</option>
							</select-->
						</td>
						<td style="display: none;" id="compareTD_ReplaceIndex">
							<input type="text" id="compare_ReplaceIndex" name="compare_ReplaceIndex" onchange="addUp(this);">
						</td>
						<td style="display: none;" id="dateTD_ReplaceIndex">
							<a href="javascript: show_calendar('compare_ReplaceIndex', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" border=0></a>
							Blank for current date
						</td>
						<td style="display: none;" id="booleanTD_ReplaceIndex">
							<select id="booleanSelect_ReplaceIndex">
								<option value="">-- Select a value --</option>
								<option value="true">True</option>
								<option value="false">False</option>
							</select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</table>

		<table style="display: none;">
		<tr>
			<td width="10%" align="right"></td>
			<td width="90%" align="left">
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

		<br><br>
		<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
		<tr>
			<td width="50%" align="center"><div id="pxbutton"><a href="javascript: saveMe(); void(0);">Save</a></div></td>
			<td width="50%" align="center"><div id="pxbutton"><a href="javascript: returnMe(); void(0);">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var reqMethods = new Array();
	var rfqMethods = new Array();
	var poMethods = new Array();

	var stringOperatorsArray = new Array();
	stringOperatorsArray['Empty'] = "Empty";
	stringOperatorsArray['NotEmpty'] = "Not Empty";
	stringOperatorsArray['lookup'] = "Lookup";
	stringOperatorsArray['EQUAL'] = "Equal";
	stringOperatorsArray['NOTEQUAL'] = "Not Equal";
	stringOperatorsArray['STARTSWITH'] = "Starts With";
	stringOperatorsArray['ENDSWITH'] = "Ends With";

	var bigDecimalAndDateOperatorsArray = new Array();
	bigDecimalAndDateOperatorsArray['EQUAL'] = "Equal";
	bigDecimalAndDateOperatorsArray['NOTEQUAL'] = "Not Equal";
	bigDecimalAndDateOperatorsArray['GREATER'] = "Greater";
	bigDecimalAndDateOperatorsArray['LESS'] = "Less";
	bigDecimalAndDateOperatorsArray['GREATEREQUAL'] = "Greater Equal";
	bigDecimalAndDateOperatorsArray['LESSEQUAL'] = "Less Equal";

	var booleanOperatorsArray = new Array();
	booleanOperatorsArray['EQUAL'] = "Equal";
	booleanOperatorsArray['NOTEQUAL'] = "Not Equal";

	var logicalOperatorsArray = new Array();
	logicalOperatorsArray['AND'] = "And";
	logicalOperatorsArray['OR'] = "Or";

	var currentMethodType = null;

	var currentTotalRows = 0;

	setMethods();

	<%	if (handlerAction.equalsIgnoreCase("RulesUpdate")) { %>
		getRuleExpression();
	<%	} else { %>
		addClause();
	<%	} %>

	function setMethods()
	{
	<%	Object[] reqKeys = reqMethods.keySet().toArray();
		Arrays.sort(reqKeys);
		for (int i = 0; i < reqKeys.length; i++)
		{
			Method method = (Method)reqMethods.get(reqKeys[i]); %>
			reqMethods['<%=method.getName()%>'] = {
				methodType: "<%=method.getReturnType().getSimpleName()%>",
				methodLabel: "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, method.getName().substring(3).toLowerCase(), method.getName().substring(3))%>"
			};
	<%	} %>

	<%	Object[] rfqKeys = rfqMethods.keySet().toArray();
		Arrays.sort(rfqKeys);
		for (int i = 0; i < rfqKeys.length; i++)
		{
			Method method = (Method)rfqMethods.get(rfqKeys[i]); %>
			rfqMethods['<%=method.getName()%>'] = {
				methodType: "<%=method.getReturnType().getSimpleName()%>",
				methodLabel: "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, method.getName().substring(3).toLowerCase(), method.getName().substring(3))%>"
			};
	<%	} %>

	<%	Object[] poKeys = poMethods.keySet().toArray();
		Arrays.sort(poKeys);
		for (int i = 0; i < poKeys.length; i++)
		{
			Method method = (Method)poMethods.get(poKeys[i]); %>
			poMethods['<%=method.getName()%>'] = {
				methodType: "<%=method.getReturnType().getSimpleName()%>",
				methodLabel: "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, method.getName().substring(3).toLowerCase(), method.getName().substring(3))%>"
			};
	<%	} %>
	}

	function validateFormRuleEdit() {
		frm.Rules_ruleName.value = trim(frm.Rules_ruleName);
		if (frm.Rules_ruleName.value == "") {
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-rulename", "Rule Name")%> is required.");
			frm.Rules_ruleName.focus();
			return false;
		}
		frm.Rules_ruleOrder.value = trim(frm.Rules_ruleOrder);
		if (frm.Rules_ruleOrder.value == "") {
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-ruleorder", "Order")%> is required.");
			frm.Rules_ruleOrder.focus();
			return false;
		}
		if (!isNumeric(frm.Rules_ruleOrder.value)) {
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rules-ruleorder", "Order")%> is an invalid number.");
			frm.Rules_ruleOrder.focus();
			return false;
		}

		for (var i = 0; i < currentTotalRows; i++)
		{
			var index = "_" + i;

			var documentObject = document.getElementById("documentObject" + index);
			var documentAttribute = document.getElementById("documentAttribute" + index);
			var operator = document.getElementById("operator" + index);
			var lookupTable = document.getElementById("lookupTable" + index);
			//var lookupColumn = document.getElementById("lookupColumn" + index);
			var compare = document.getElementById("compare" + index);
			var booleanSelect = document.getElementById("booleanSelect" + index);

			var methodType = "";
			if (documentObject.value != "" && documentAttribute.value != "")
			{
				if (documentObject.value == "RequisitionHeader") {
					methodType = reqMethods[documentAttribute.value].methodType;
				} else if (documentObject.value == "RfqHeader") {
					methodType = rfqMethods[documentAttribute.value].methodType;
				} else if (documentObject.value == "PoHeader") {
					methodType = poMethods[documentAttribute.value].methodType;
				}
			}

			if (documentObject.value == "" || documentAttribute.value == "" || operator.value == "") {
				alert("Complete the rule expression.");
				return false;
			}

			if (operator.value == "lookup" && (lookupTable.value == ""/* || lookupColumn.value == ""*/)) {
				alert("Complete the rule expression.");
				return false;
			}

			if (methodType == "Date" && compare && !chkdate(compare)) {
				compare.focus();
				alert("Invalid date.");
				return false;
			}

			if (methodType == "boolean" && booleanSelect && booleanSelect.value == "") {
				alert("Complete the rule expression.");
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

	function saveMe() {
		frm.browseName.value = 'rules-admin';
		if (validateFormRuleEdit()) {
			setRuleExpression();
			doSubmit('/admin/rules/rules.jsp', '<%=handlerAction%>;BrowseRetrieve');
		}
	}

	function returnMe() {
		frm.browseName.value = 'rules-admin';
		doSubmit('/admin/rules/rules.jsp', 'BrowseRetrieve');
	}

	function setDocumentObject(selectObj, valueObj, index)
	{
		var selectDocumentObject = selectObj.value;

		var opcionSelectEmpty = null;

		var documentAttribute = document.getElementById("documentAttribute" + index);
		var operator = document.getElementById("operator" + index);

		documentAttribute.options.length = 0;
		opcionSelectEmpty = document.createElement("option");
		opcionSelectEmpty.innerHTML = "-- Select a field --";
		opcionSelectEmpty.value = "";
		documentAttribute.appendChild(opcionSelectEmpty);

		if (selectDocumentObject == "RequisitionHeader") {
			createDocumentAttribute(reqMethods, valueObj, index);
		} else if (selectDocumentObject == "RfqHeader") {
			createDocumentAttribute(rfqMethods, valueObj, index);
		} else if (selectDocumentObject == "PoHeader") {
			createDocumentAttribute(poMethods, valueObj, index);
		}

		operator.options.length = 0;
		opcionSelectEmpty = document.createElement("option");
		opcionSelectEmpty.innerHTML = "-- Select a operator --";
		opcionSelectEmpty.value = "";
		operator.appendChild(opcionSelectEmpty);

		setOperator(operator, index);

		currentMethodType = null;
	}

	function setDocumentAttribute(selectObj, valueObj, index)
	{
		var selectDocumentAttribute = selectObj.value;

		var methodType = "";

		var documentObject = document.getElementById("documentObject" + index);
		var operator = document.getElementById("operator" + index);

		if (selectDocumentAttribute != "")
		{
			if (documentObject.value == "RequisitionHeader") {
				methodType = reqMethods[selectDocumentAttribute].methodType;
			} else if (documentObject.value == "RfqHeader") {
				methodType = rfqMethods[selectDocumentAttribute].methodType;
			} else if (documentObject.value == "PoHeader") {
				methodType = poMethods[selectDocumentAttribute].methodType;
			}
		}

		if (currentMethodType != methodType)
		{
			operator.options.length = 0;

			var opcionSelectEmpty = document.createElement("option");
			opcionSelectEmpty.innerHTML = "-- Select a operator --";
			opcionSelectEmpty.value = "";
			operator.appendChild(opcionSelectEmpty);

			if (methodType == "String")
			{
				for (var operatorName in stringOperatorsArray)
				{
					var opcionSelect = document.createElement("option");
					opcionSelect.innerHTML = stringOperatorsArray[operatorName];
					if (valueObj == operatorName) {
						opcionSelect.selected = true;
					}
					opcionSelect.value = operatorName;
					operator.appendChild(opcionSelect);
				}
			}
			else if (methodType == "BigDecimal" || methodType == "Date")
			{
				for (var operatorName in bigDecimalAndDateOperatorsArray)
				{
					var opcionSelect = document.createElement("option");
					opcionSelect.innerHTML = bigDecimalAndDateOperatorsArray[operatorName];
					if (valueObj == operatorName) {
						opcionSelect.selected = true;
					}
					opcionSelect.value = operatorName;
					operator.appendChild(opcionSelect);
				}
			}
			else if (methodType == "boolean")
			{
				for (var operatorName in booleanOperatorsArray)
				{
					var opcionSelect = document.createElement("option");
					opcionSelect.innerHTML = booleanOperatorsArray[operatorName];
					if (valueObj == operatorName) {
						opcionSelect.selected = true;
					}
					opcionSelect.value = operatorName;
					operator.appendChild(opcionSelect);
				}
			}
			else if (methodType == "List")
			{
			}

			setOperator(operator, index);
		}

		currentMethodType = methodType;
	}

	function createDocumentAttribute(docMethods, valueObj, index)
	{
		var documentAttribute = document.getElementById("documentAttribute" + index);
		for (var methodName in docMethods)
		{
			var opcionSelect = document.createElement("option");
			opcionSelect.innerHTML = docMethods[methodName].methodLabel;
			if (valueObj == methodName) {
				opcionSelect.selected = true;
			}
			opcionSelect.value = methodName;
			documentAttribute.appendChild(opcionSelect);
		}
	}

	function setLookupTable(selectObj, valueObj, index)
	{
		var selectLookupTable = selectObj.value;

		/*var lookupColumn = document.getElementById("lookupColumn" + index);

		lookupColumn.options.length = 0;

		var opcionSelectEmpty = document.createElement("option");
		opcionSelectEmpty.innerHTML = "-- Select a column --";
		opcionSelectEmpty.value = "";
		lookupColumn.appendChild(opcionSelectEmpty);

		if (selectLookupTable == "UserProfile")
		{
			var opcionSelect = document.createElement("option");
			opcionSelect.innerHTML = "User Id";
			if (valueObj == "userId") {
				opcionSelect.selected = true;
			}
			opcionSelect.value = "userId";
			lookupColumn.appendChild(opcionSelect);
		}*/
	}

	function setOperator(select, index)
	{
		var selectOperator = select.value;

		var lookupTable = document.getElementById("lookupTable" + index);
		//var lookupColumn = document.getElementById("lookupColumn" + index);
		var lookupTableTH = document.getElementById("lookupTableTH" + index);
		var lookupColumnTH = document.getElementById("lookupColumnTH" + index);
		var lookupTableTD = document.getElementById("lookupTableTD" + index);
		var lookupColumnTD = document.getElementById("lookupColumnTD" + index);
		var compare = document.getElementById("compare" + index);
		var compareTH = document.getElementById("compareTH" + index);
		var compareTD = document.getElementById("compareTD" + index);
		var dateTH = document.getElementById("dateTH" + index);
		var dateTD = document.getElementById("dateTD" + index);
		var booleanSelect = document.getElementById("booleanSelect" + index);
		var booleanTH = document.getElementById("booleanTH" + index);
		var booleanTD = document.getElementById("booleanTD" + index);

		if (selectOperator == "lookup")
		{
			lookupTableTH.style.display = "";
			lookupColumnTH.style.display = "";
			lookupTableTD.style.display = "";
			lookupColumnTD.style.display = "";

			compare.value = "";
			compareTH.style.display = "none";
			compareTD.style.display = "none";
			dateTH.style.display = "none";
			dateTD.style.display = "none";
			booleanSelect.options[0].selected = true;
			booleanTH.style.display = "none";
			booleanTD.style.display = "none";

			return true;
		}
		else if (selectOperator == "EQUAL" || selectOperator == "NOTEQUAL" || selectOperator == "GREATER" || selectOperator == "LESS" ||
			selectOperator == "GREATEREQUAL" || selectOperator == "LESSEQUAL" || selectOperator == "STARTSWITH" || selectOperator == "ENDSWITH")
		{
			if (currentMethodType != null && currentMethodType == "boolean")
			{
				booleanTH.style.display = "";
				booleanTD.style.display = "";

				compare.value = "";
				compareTH.style.display = "none";
				compareTD.style.display = "none";
				dateTH.style.display = "none";
				dateTD.style.display = "none";
			}
			else
			{
				addUp(compare);
				compareTH.style.display = "";
				compareTD.style.display = "";
				if (currentMethodType != null && currentMethodType == "Date") {
					dateTH.style.display = "";
					dateTD.style.display = "";
				}

				booleanSelect.options[0].selected = true;
				booleanTH.style.display = "none";
				booleanTD.style.display = "none";
			}

			lookupTable.options[0].selected = true;
			//lookupColumn.options[0].selected = true;
			lookupTableTH.style.display = "none";
			lookupColumnTH.style.display = "none";
			lookupTableTD.style.display = "none";
			lookupColumnTD.style.display = "none";

			return true;
		}
		else
		{
			lookupTable.options[0].selected = true;
			//lookupColumn.options[0].selected = true;
			lookupTableTH.style.display = "none";
			lookupColumnTH.style.display = "none";
			lookupTableTD.style.display = "none";
			lookupColumnTD.style.display = "none";

			compare.value = "";
			compareTH.style.display = "none";
			compareTD.style.display = "none";
			dateTH.style.display = "none";
			dateTD.style.display = "none";
			booleanSelect.options[0].selected = true;
			booleanTH.style.display = "none";
			booleanTD.style.display = "none";

			return false;
		}
	}

	function setLogicalOperator(selectObj, valueObj, index)
	{
		var selectLogicalOperator = selectObj.value;

		var opcionSelectEmpty = null;

		var logicalOperator = document.getElementById("logicalOperator" + index);

		logicalOperator.options.length = 0;
		opcionSelectEmpty = document.createElement("option");
		opcionSelectEmpty.innerHTML = "-- Select a logical --";
		opcionSelectEmpty.value = "";
		logicalOperator.appendChild(opcionSelectEmpty);

		for (var logicalOperatorName in logicalOperatorsArray)
		{
			var opcionSelect = document.createElement("option");
			opcionSelect.innerHTML = logicalOperatorsArray[logicalOperatorName];
			if (valueObj == logicalOperatorName) {
				opcionSelect.selected = true;
			}
			opcionSelect.value = logicalOperatorName;
			logicalOperator.appendChild(opcionSelect);
		}
	}

	function setRuleExpression()
	{
		var clauses = "";
		var logicalOperators = "";

		for (var i = 0; i < currentTotalRows; i++)
		{
			var index = "_" + i;

			var documentObject = document.getElementById("documentObject" + index).value;
			var name = document.getElementById("documentAttribute" + index).value;
			var type = document.getElementById("operator" + index).value;
			var lookupTable = document.getElementById("lookupTable" + index).value;
			var lookupSql = document.getElementById("lookupSql" + index).value;
			alert(lookupSql);
			var compare = document.getElementById("compare" + index).value;
			var booleanSelect = document.getElementById("booleanSelect" + index).value;
			var object = "header";
			if (documentObject.indexOf("Header") > 0) {
				object = "header";
			}
			var methodType = "";
			if (documentObject != "" && name != "")
			{
				if (documentObject == "RequisitionHeader") {
					methodType = reqMethods[name].methodType;
				} else if (documentObject == "RfqHeader") {
					methodType = rfqMethods[name].methodType;
				} else if (documentObject == "PoHeader") {
					methodType = poMethods[name].methodType;
				}
			}

			var clause = "";

			if (methodType != "" && methodType == "String")
			{
				if (type == "NotEmpty" || type == "Empty")
				{
					clause =
						'{' +
							'"type":"' + type + '",' +
							'"leftSide":' +
							'{' +
								'"source":"incomingRequestObjectMethod",' +
								'"object":"' + object + '",' +
								'"document":"' + documentObject + '",' +
								'"name":"' + name + '"' +
							'}' +
						'}';
				}
				else if (type == "EQUAL" || type == "NOTEQUAL" || type == "STARTSWITH" || type == "ENDSWITH")
				{
					clause =
						'{' +
							'"type":"StringCompare",' +
							'"leftSide":' +
							'{' +
								'"source":"incomingRequestObjectMethod",' +
								'"object":"' + object + '",' +
								'"document":"' + documentObject + '",' +
								'"name":"' + name + '"' +
							'},' +
							'"operator":"' + type + '",' +
							'"rightSide":' +
							'{' +
								'"source":"constant",' +
								'"value":"' + compare + '"' +
							'}' +
						'}';
				}
				else if (type == "lookup")
				{
					clause =
						'{' +
							'"type":"' + type + '",' +
							'"sql":"from ' + lookupTable + ' as ' + lookupTable + ' where ' + lookupTable + '.userId @E@ \'@' + lookupColumn + '@\' AND ' + lookupTable + '.requisitioner @E@ \'Y\' AND (' + lookupTable + '.status @E@ \'02\' OR (' + lookupTable + '.status @E@ \'01\' AND ' + lookupTable + '.dateExpires >@E@ @today@))",' +
							'"lookupTable":"' + lookupTable + '",' +
							'"lookupColumn":"' + lookupColumn + '",' +
							'"source":"' + object + '",' +
							'"document":"' + documentObject + '",' +
							'"arguments":' +
							'[' +
								'{' +
									'"name":"' + lookupColumn + '",' +
									'"source":"' + object + '",' +
									'"colName":"' + name + '"' +
								'}' +
							']' +
						'}';
				}
			}
			else if (methodType != "" && (methodType == "BigDecimal" || methodType == "Date"))
			{
				clause =
					'{' +
						'"type":"' + currentMethodType + 'Compare",' +
						'"leftSide":' +
						'{' +
							'"source":"incomingRequestObjectMethod",' +
							'"object":"' + object + '",' +
							'"document":"' + documentObject + '",' +
							'"name":"' + name + '"' +
						'},' +
						'"operator":"' + type + '",' +
						'"rightSide":' +
						'{' +
							'"source":"constant",' +
							'"value":"' + compare + '"' +
						'}' +
					'}';
			}
			else if (methodType != "" && methodType == "boolean")
			{
				clause =
					'{' +
						'"type":"BooleanCompare",' +
						'"leftSide":' +
						'{' +
							'"source":"incomingRequestObjectMethod",' +
							'"object":"' + object + '",' +
							'"document":"' + documentObject + '",' +
							'"name":"' + name + '"' +
						'},' +
						'"operator":"' + type + '",' +
						'"rightSide":' +
						'{' +
							'"source":"constant",' +
							'"value":"' + booleanSelect + '"' +
						'}' +
					'}';
			}

			if (clauses == "") {
				clauses = clauses + clause;
			} else {
				clauses = clauses + "," + clause;
			}

			if (i > 0)
			{
				var logicalOperator = document.getElementById("logicalOperator" + index).value;

				if (logicalOperators == "") {
					logicalOperators = logicalOperators + '"' + logicalOperator + '"';
				} else {
					logicalOperators = logicalOperators + ',"' + logicalOperator + '"';
				}
			}
		}

		var ruleExpression =
			'{' +
				'"clauses":' +
				'[' +
					clauses +
				'],' +
				'"logicalOperators":' +
				'[' +
					logicalOperators +
				']' +
			'}';

		frm.Rules_ruleExpression.value = ruleExpression;
	}

	function getRuleExpression()
	{
		var ruleExpression = "";
	<%	if (handlerAction.equalsIgnoreCase("RulesUpdate")) { %>
		ruleExpression = <%=rule.getRuleExpression()%>;
	<%	} %>

		if (ruleExpression.clauses != null)
		{
			for (var i = 0; i < ruleExpression.clauses.length; i++)
			{
				addClause();

				var clause = ruleExpression.clauses[i];

				var type = getType(clause);

				var index = "_" + i;

				var documentObjectObj = document.getElementById("documentObject" + index);
				var documentAttributeObj = document.getElementById("documentAttribute" + index);
				var operatorObj = document.getElementById("operator" + index);
				var lookupTableObj = document.getElementById("lookupTable" + index);
				var booleanSelectObj = document.getElementById("booleanSelect" + index);
				var compareObj = document.getElementById("compare" + index);

				if (type == "NotEmpty" || type == "Empty")
				{
					var documentValue = clause.leftSide.document;
					var name = clause.leftSide.name;

					setSelectedItem(documentObjectObj, documentValue);
					setDocumentObject(documentObjectObj, name, index);
					setDocumentAttribute(documentAttributeObj, type, index);
				}
				else if (type == "lookup")
				{
					var documentValue = clause.document;
					var name = clause.arguments[0].colName;
					var lookupTable = clause.lookupTable;
					var lookupColumn = clause.lookupColumn;

					setSelectedItem(documentObjectObj, documentValue);
					setDocumentObject(documentObjectObj, name, index);
					setDocumentAttribute(documentAttributeObj, type, index);
					if (setOperator(operatorObj, index)) {
						setSelectedItem(lookupTableObj, lookupTable);
						setLookupTable(lookupTableObj, lookupColumn, index);
					}
				}
				else if (type == "EQUAL" || type == "NOTEQUAL" || type == "STARTSWITH" || type == "ENDSWITH" ||
						type == "GREATER" || type == "LESS" || type == "GREATEREQUAL" || type == "LESSEQUAL")
				{
					var documentValue = clause.leftSide.document;
					var name = clause.leftSide.name;
					var value = clause.rightSide.value;

					setSelectedItem(documentObjectObj, documentValue);
					setDocumentObject(documentObjectObj, name, index);
					setDocumentAttribute(documentAttributeObj, type, index);
					if (setOperator(operatorObj, index)) {
						if (currentMethodType != null && currentMethodType == "boolean") {
							if (value == "true") booleanSelectObj.options[1].selected = true;
							if (value == "false") booleanSelectObj.options[2].selected = true;
						} else {
							compareObj.value = value;
						}
					}
				}

				if (i > 0)
				{
					var logicalOperator = ruleExpression.logicalOperators[i - 1];
					var logicalOperatorObj = document.getElementById("logicalOperator" + index);
					setLogicalOperator(logicalOperatorObj, logicalOperator, index);
				}
			}
		}
	}

	function setSelectedItem(selectObj, valueObj)
	{
		for (var i = 0; i < selectObj.options.length; i++) {
			if (selectObj.options[i].value == valueObj) {
				selectObj.options[i].selected = true;
				break;
			}
		}
	}

	function getType(clause)
	{
		var type = clause.type;
		if (type.indexOf("Compare") > 0) {
			type = clause.operator;
		}
		return type;
	}

	function lookupEdit(index)
	{
		var lookupTableObj = document.getElementById("lookupTable" + index);
		var lookupSqlObj = document.getElementById("lookupSql" + index);
		if (lookupTableObj != null && lookupSqlObj != null) {
			var lookupTable = lookupTableObj.value;
			var lookupSql = lookupSqlObj.value;
			if (lookupTable == "") {
				alert("Select a source");
			} else {
				popupParameters = "lookupTable=" + lookupTable + ";lookupSql=" + lookupSql + ";lookupIndex=" + index;
				doSubmitToPopup('/admin/rules/rules_edit_lookup.jsp', 'RulesDataRetrieveForLookup', 'width=650px', 'height=450px');
			}
		}
	}

	function addUp(compareObj)
	{
		if (currentMethodType != null && currentMethodType == "BigDecimal") {
			compareObj.value = nformat(eval(nfilter(compareObj)), 0);
		}
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
			myCell.width = "10%";
			myCell.align = "right";

			myCell = createCell(myRow);
			myCell.width = "90%";
			myCell.align = "left";
			myCell.innerHTML = "<table>" + logOperatorTableTemplateHtml + "</table>";
		}

		myRow = createRow(myTable);

		var ruleTableTemplate = document.getElementById("ruleTableTemplate");
		var ruleTableTemplateHtml = ruleTableTemplate.innerHTML.replace(/_ReplaceIndex/g, "_" + currentTotalRows);

		myCell = createCell(myRow);
		myCell.width = "10%";
		myCell.align = "right";
		if (currentTotalRows > 0) {
			myCell.innerHTML = "<a href=\"javascript: if (confirm('Are you sure you wish to delete this clause?')) { deleteClause(" + currentTotalRows + "); } void(0);\"><img src=\"" + contextPath + "/images/delete.gif\" border=\"0\"></a>&nbsp;";
		}

		myCell = createCell(myRow);
		myCell.width = "90%";
		myCell.align = "left";
		myCell.innerHTML = "<table>" + ruleTableTemplateHtml + "</table>";

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

// End Hide script -->
</SCRIPT>
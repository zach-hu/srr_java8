<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	browseName = (String) request.getAttribute("browseName");
	String	s_current_page = "/admin/rules/rules.jsp";
	String	s_current_method = "RulesUpdates";
	String	s_current_process_method = "";
	String	originalFormWidth = formWidth;
	String	totalFormWidth = formWidth;
	formWidth = "800px";
%>
<div style="width:<%=totalFormWidth%>;">
	<table>
		<tr>
			<td width="<%=formWidth%>">
				<%@ include file="/browse/browse_form.jsp" %>
			</td>
			<td id="allDetailsLink" valign="top">
			<%	if (irows > 0) { %>
				<a href="javascript: viewAllDetails(); void(0);">View All Details</a>
			<%	} %>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<table width="<%=formWidth%>">
		<tr>
			<td align=center>
				<div id="pxbutton"><a href="javascript: addRule(); void(0);">Add</a></div>
			</td>
			<%	if (irows > 0) { %>
			<td align=center>
				<div id="pxbutton"><a href="javascript: saveRules(); void(0);">Save</a></div>
			</td>
			<%	} %>
			<td align=center>
				<div id="pxbutton"><a href="javascript: returnAdmin(); void(0);">Return</a></div>
			</td>
		</tr>
	</table>
</div>
<%
	formWidth = originalFormWidth;
%>
<br>
<br>
<tsa:hidden name="browsePage" value="/admin/rules/rules.jsp"/>
<tsa:hidden name="ruleAction" value=""/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/rules/rules.jsp", "BrowseRetrieveById", "<%=browseObject.getTitle()%>");

	var currentRows = <%=(browse.getRowEnd() - browse.getRowStart()) + 1%>;

	setConditionalLinks();

	function highlightRow(row) {
	//do nothing
	}
	function removeHighlight(row) {
	//do nothing
	}
	function setLabelFilter(moduleAccess) {
		frm.browseName.value = browseName;
		frm.currentProcess.value = moduleAccess;

		document.getElementById("filterFields").innerHTML = "";

		setFilter("Labels_moduleAccess", "=", moduleAccess);
	}
	function setConditionalLinks() {
		if (totalRows > 0) {
			for (var i = 0; i < currentRows; i++) {
				checkConditionalLinks(i, false);
			}
		}
	}
	function checkConditionalLinks(x, toggleDetails) {
		hideConditionalLinksRow = true;
		var links;

		if (browser == "NS6") {
			links = document.getElementById("browse_Link_validationInfo");
		} else {
			links = document.all("browse_Link_validationInfo");
		}
		if (links != undefined && links != null) {
			checkValidationLinks(links, x, toggleDetails);
		}
	}
	function checkValidationLinks(links, x, toggleDetails) {
		var validateFld = "";
		var link;

		if (links[x]) {
			link = links[x];
		} else {
			link = links;
		}
		if (frm.Labels_validation != undefined && frm.Labels_validation != null && frm.Labels_validation[x]) {
			validateFld = frm.Labels_validation[x].value;
		} else if (frm.Labels_validation != undefined && frm.Labels_validation != null && frm.Labels_validation) {
			validateFld = frm.Labels_validation.value;
		}

		if (validateFld != "Y") {
			if (frm.Labels_required != undefined && frm.Labels_required != null && frm.Labels_required[x]) {
				validateFld = frm.Labels_required[x].value;
			} else if (frm.Labels_required) {
				validateFld = frm.Labels_required.value;
			}
		}
		if (validateFld != "Y") {
			hideLink(link);
			if (toggleDetails) {
				hideArea("details" + x);
			}
		} else {
			displayLink(link);
			if (toggleDetails) {
				displayArea("details" + x);
				document.getElementById("img_Link_validationInfo_" + x).src = "<%=contextPath%>/images/minus.gif";
			}
		}
	}
	function hideLink(link) {
		if (browser == "NS") {
			//The option to hide rows is currently not working in this version of Netscape.
		} else if ( (browser == "IE") || (browser == "NS6") ) {
			link.style.visibility="hidden";
			link.style.display="none";
		} else {
			eval(link + ".style.visibility='hidden'");
			eval(link + ".style.display='none'");
		}
	}
	function displayLink(link) {
		if (browser == "NS") {
			//The option to hide/display rows is currently not working in this version of Netscape.
		} else if ( (browser == "IE") || (browser == "NS6") ) {
			link.style.visibility="visible";
			link.style.display="block";
		} else {
			eval(link + ".style.visibility='visible'");
			eval(link + ".style.display='block'");
		}
	}
	function setFlagFromCkbox(ckbox, fld, row) {
		var ckboxName = ckbox.name;
		if (ckbox.checked) {
			fld[row].value = "Y";
		} else {
			fld[row].value = "N";
		}
		if (ckboxName == "c_Labels_required" || ckboxName == "c_Labels_validation") {
			checkConditionalLinks(row, true);
		}
	}

	function validateForm() {
		var handlerValue = frm.handler.value;
		var currentMethod = "<%=s_current_method%>";

		if (frm.rowschanged.value == "N") {
			handlerValue = handlerValue.replace(currentMethod + "Handler;","");
			handlerValue = handlerValue.replace(currentMethod,"Handler");
		}
		if (isEmpty(handlerValue)) {
			handlerValue = "DoNothing";
		}
		frm.handler.value = handlerValue;
		return true;
	}


	function addRule() {
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		frm.ruleAction.value = "addRules";
		doSubmit('/admin/rules/rules_edit.jsp', 'RulesDataRetrieve');
	}

	function saveRules() {
		frm.browseName.value = 'rules-admin';
		doSubmit('/admin/rules/rules.jsp', 'RulesUpdateList;BrowseRetrieve');
	}

	function editRule(ic) {
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		myCell.innerHTML = "<tsa:hidden name=\"Rules_icRule\" value=\"" + ic + "\"/>";
		frm.ruleAction.value = "updateRules";
		doSubmit('/admin/rules/rules_edit.jsp', 'RulesRetrieveById;RulesDataRetrieve');
	}

	function deleteRule(ic, ruleName) {
		if (confirm("Delete rule '" + ruleName + "'?")) {
			var myCell = document.getElementById("hiddenFields");
			myCell.innerHTML = "";
			myCell.innerHTML = "<tsa:hidden name=\"Rules_icRule\" value=\"" + ic + "\"/>";

			frm.browseName.value = 'rules-admin';
			doSubmit('/admin/rules/rules.jsp', 'RulesDeleteById;BrowseRetrieve');
		}
	}

	function returnAdmin() {
		doSubmit('admin/admin_menu.jsp', 'DoNothing');
	}

	function triggerRuleDetails() {
		var d = document.all("details" + rowSelect);
		if (d.style.visibility == "visible") {
			hideArea("details" + rowSelect);
			document.getElementById("img_Link_validationInfo_" + rowSelect).src = "<%=contextPath%>/images/plus.gif";
		} else {
			displayArea("details" + rowSelect);
			document.getElementById("img_Link_validationInfo_" + rowSelect).src = "<%=contextPath%>/images/minus.gif";
		}
	}

	function viewAllDetails() {
		setInnerHTML("allDetailsLink", "<a href=\"javascript: hideAllDetails(); void(0);\">Hide All Details</a>");

		for (var i = 0; i < currentRows; i++) {
			showDetails(i);
			document.getElementById("img_Link_validationInfo_" + i).src = "<%=contextPath%>/images/minus.gif";
		}
	}

	function hideAllDetails() {
		setInnerHTML("allDetailsLink", "<a href=\"javascript: viewAllDetails(); void(0);\">View All Details</a>");

		for (var i = 0; i < currentRows; i++) {
			hideDetails(i);
			document.getElementById("img_Link_validationInfo_" + i).src = "<%=contextPath%>/images/plus.gif";
		}
	}

// End Hide script -->
</SCRIPT>
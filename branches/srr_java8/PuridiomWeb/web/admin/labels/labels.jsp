<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	browseName = (String) request.getAttribute("browseName");
	String	s_current_process = (String) request.getAttribute("currentProcess");
	String	s_current_page = "/admin/labels/labels.jsp";
	String	s_current_method = "LabelsUpdateRefresh";
	String	s_current_process_method = "";
	String	originalFormWidth = formWidth;
	String	totalFormWidth = formWidth;
	
//	if (HiltonUtility.ckNull(browseName).equals("labels-admin")) {
//		formWidth = "980px";
//		totalFormWidth = "1180px";
//	} else {
		formWidth = "780px";
//	}

	if (HiltonUtility.isEmpty(s_current_process)) {
		s_current_process = "ADMIN";
	}
%>
<div style="width:<%=totalFormWidth%>;">
	<div id="labelsBrowse" style="float:left;top:0em;width:82%"><%@ include file="/browse/browse_form.jsp" %></div>
	<div style="float:left;width:1%"></div>
	<div id="labelsSidebar" style="float:left;align:right">
		<table>
<% if (irows > 0) {%>
		<tr><td align=center id="allDetailsLink"><a href="javascript: viewAllDetails(); void(0);">View All Details</a></td></tr>
<%	}%>
		<tr><td><img src="<%=contextPath%>/images/none.gif" border=0 height=60px></td></tr>
		</table>
		<%@ include file="/admin/labels/labels_sidebar.jsp" %>
		</div>
</div>
<%
	formWidth = originalFormWidth;
%>
<br>
<br>
<tsa:hidden name="browsePage" value="/admin/labels/labels.jsp"/>
<tsa:hidden name="currentProcess" value="<%=s_current_process%>"/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/labels/labels.jsp", "BrowseRetrieveById", "<%=browseObject.getTitle()%>");

	var currentRows = <%=(browse.getRowEnd() - browse.getRowStart()) + 1%>;

	setConditionalLinks();

	function highlightRow(row) {
	//do nothing
	}
	function removeHighlight(row) {
	//do nothing
	}
	function triggerLabelDetails() {
		var d = document.all("details" + rowSelect);
		if (d.style.visibility == "visible") {
			hideArea("details" + rowSelect);
			document.getElementById("img_Link_validationInfo_" + rowSelect).src = "<%=contextPath%>/images/plus.gif";
		} else {
			displayArea("details" + rowSelect);
			document.getElementById("img_Link_validationInfo_" + rowSelect).src = "<%=contextPath%>/images/minus.gif";
		}
	}
	function setLabelFilter(moduleAccess) {
		frm.browseName.value = browseName;
		frm.currentProcess.value = moduleAccess;

		document.getElementById("filterFields").innerHTML = "";

		setFilter("Labels_moduleAccess", "=", "'" + moduleAccess + "'");
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

	function editLabel(ic) {
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		myCell.innerHTML = "<input type=hidden name=\"Labels_icLabel\" value=\"" + ic + "\">";
		doSubmit('/admin/labels/labels_edit.jsp', 'LabelsRetrieveByIc');
	}

	function viewAllDetails() {
		//set cursor to hourglass while the system is processing
		document.body.style.cursor = "wait";

		setInnerHTML("allDetailsLink", "<a href=\"javascript: hideAllDetails(); void(0);\">Hide All Details</a>");

		for (var i=0; i < currentRows; i++) {
			showDetails(i);
		}

		//reset cursor - processing complete
		document.body.style.cursor = "";
	}


	function hideAllDetails() {
		//set cursor to hourglass while the system is processing
		document.body.style.cursor = "wait";

		setInnerHTML("allDetailsLink", "<a href=\"javascript: viewAllDetails(); void(0);\">View All Details</a>");

		for (var i=0; i < currentRows; i++) {
			hideDetails(i);
		}

		//reset cursor - processing complete
		document.body.style.cursor = "";
	}
	

	function deleteLabel(ic, moduleAccess, labelCode) {
		if (confirm("Delete label '" + labelCode + "' for " + moduleAccess + "?")) {
			var myCell = document.getElementById("hiddenFields");
			myCell.innerHTML = "";
			myCell.innerHTML = "<input type=hidden name=\"Labels_icLabel\" value=\"" + ic + "\">";
		
			setLabelFilter(frm.currentProcess.value);
			doSubmit('/admin/labels/labels.jsp', 'LabelsDeleteByIdRefresh;BrowseRetrieve');
		}
	}
	
// End Hide script -->
</SCRIPT>
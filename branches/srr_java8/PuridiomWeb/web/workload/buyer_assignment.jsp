<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<tsa:hidden name="browsePage" value="/workload/buyer_assignment.jsp"/>
<%
	String	assignFrom = HiltonUtility.ckNull((String) request.getAttribute("assignFrom"));
	String	assignTo = HiltonUtility.ckNull((String) request.getAttribute("assignTo"));
	String buyerDeafult = HiltonUtility.ckNull((String) request.getAttribute("buyerAssigned"));
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("pagereturn"));
	List	buyerList = UserManager.getInstance().getBuyerList(oid);
	StringBuffer buyerOptionValues = new StringBuffer();


	for (int i=0; i < buyerList.size(); i++) {
		UserProfile buyer = (UserProfile) buyerList.get(i);
		if(buyerDeafult.equalsIgnoreCase(buyer.getUserId())) {
			buyerOptionValues.append("<option value=\"" + buyer.getUserId() + "\" SELECTED>" + buyer.getDisplayName() + "</option>");
		}
		else{
			buyerOptionValues.append("<option value=\"" + buyer.getUserId() + "\">" + buyer.getDisplayName() + "</option>");
		}
	}

	String s_return_page = "javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing');";
	if (!s_from_page.equalsIgnoreCase("") && s_from_page.equalsIgnoreCase("summaryworkload"))
	{
		s_return_page = "javascript: browseWorkloadView()";
	}
%>
<%@ include file="/browse/browse_form.jsp" %>

<table border=0 width=<%=formEntryWidth%>>
	<tr>
		<td align=center width=50%>
			<div id="pxbutton"><a href="javascript: setBuyerAssignment(); void(0);">Assign</a></div>
		</td>
		<td align=center width=50%>
			<div id="pxbutton"><a href="<%=s_return_page%>; void(0);">Cancel</a></div>
		</td>
	</tr>
</table>

<div id="tempInstructions" style="visibility:hidden; display:none;">
<table border=0 cellpadding=2 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=100%>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td class=label align=right>Assign From Queue:</td>
			<td>
				<select name="assignFrom" onchange="getAssignedRequisitions();">
					<option value="UNASSIGNED">-- UNASSIGNED --</option>
					<%=buyerOptionValues.toString()%>
				</select>
			</td>
			<td width=20px>&nbsp;</td>
			<td align=right rowspan=2>
			<%	for (int ip = 0; ip < ipageCount; ip++) {%>
				<div id="reassignAllFlag" style="visibility:hidden; display:none;">
				<input type=checkbox name="reassignAll" onclick="flagAll(this.checked);">
				</div>
			<%	}%>
			</td>
			<td class=label rowspan=2>Re-assign all requisitions listed</div></a></td>
		</tr>
		<tr>
			<td class=label align=right>Assign To Queue:</td>
			<td>
				<select name="assignTo">
					<option value="UNASSIGNED">-- UNASSIGNED --</option>
					<%=buyerOptionValues.toString()%>
				</select>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

		frm = document.purchaseform;

		function checkHiddenMenu()
		{
			hideArea("navTable");
			displayArea("menubarSpacer");
		}

		setDisplay();

		function setDisplay() {
			hideArea("resetOriginalOption");
			hideArea("filterTextDisplay");
			hideArea("tempInstructions");

			document.getElementById("instructions").innerHTML = document.getElementById("tempInstructions").innerHTML;
			document.getElementById("tempInstructions").innerHTML = "";
			displayArea("instructions");

			var assignFrom = "<%=headerEncoder.encodeForJavaScript(assignFrom)%>";
			var assignFromOptions = frm.assignFrom.options;

			for (var i=0; i < assignFromOptions.length; i++) {
				if (assignFrom == assignFromOptions[i].value) {
					frm.assignFrom.selectedIndex = i;
				}
			}

			var assignTo = "<%=assignTo%>";
			var assignToOptions = frm.assignTo.options;

			for (var i=0; i < assignToOptions.length; i++) {
				if (assignTo == assignToOptions[i].value) {
					frm.assignTo.selectedIndex = i;
				}
			}
			setNewPageOptions();
		}

		function flagAll(checkAll) {
			var checkboxes = document.all("c_checkbox");

			if (checkboxes != null) {
				var myObject = document.all("browseRows");
				var myTable;

				if (myObject.length > 1) {
					myTable = myObject(currentPage - 1);
				} else {
					myTable = myObject;
				}
				var pageRows = myTable.rows.length;
				var startingRow = eval((currentPage - 1) * pageSize);
				var endingRow = eval(startingRow + pageSize);

				if (pageRows < pageSize) {
					endingRow = eval(startingRow + pageRows);
				}

				if (checkboxes.length > 1) {
					for (i = startingRow; i < endingRow; i++) {
						var cbox = checkboxes(i);
						cbox.checked = checkAll;
					}
				} else {
					var cbox = checkboxes;
					cbox.checked = checkAll;
				}
			}
		}

		function setNewPageOptions() {
			var pageFlags = document.all("reassignAllFlag");

			if (pageFlags != null && pageFlags != undefined) {
				if (pageFlags.length != null && pageFlags.length != undefined) {
					for (var i = 0; i < pageFlags.length; i++) {
						if (i == (currentPage - 1)) {
							pageFlags[i].style.display="block";
							pageFlags[i].style.visibility="visible";
						} else {
							pageFlags[i].style.visibility="hidden";
							pageFlags[i].style.display="none";
						}
					}
				} else {
					pageFlags.style.display="block";
					pageFlags.style.visibility="visible";
				}
			}
		}

		function setBuyerAssignment() {
			var bw = browserCheck();
			var ckboxElements = frm.elements.c_checkbox;
			var icHeaderElements = frm.elements.RequisitionHeader_icReqHeader;
			var icLineElements = frm.elements.RequisitionLine_icReqLine;
			var recordSelected = false;

			if (isEmpty(frm.assignTo.value)) {
				alert("You must select a buyer in which to assign the requisitions.");
				frm.assignTo.focus();
				return false;
			}
			if (frm.assignTo.value == frm.assignFrom.value) {
				alert("You must select a different buyer in which to re-assign the selected requisitions.");
				frm.assignTo.focus();
				return false;
			}

			var removeFromLine = false;
			if (icHeaderElements != undefined && icLineElements != undefined && icHeaderElements.length == icLineElements.length) {
				removeFromLine = true;
			}

			if (icHeaderElements != undefined && icHeaderElements[icHeaderElements.length - 1]) {
				// Remove last element that was used as a dummy field to prevent javascript errors
				if(bw == 'NS6') {
					//frm.elements.item("RequisitionHeader_icReqHeader")[icHeaderElements.length - 1].removeNode(true);
					var el = document.getElementsByName("RequisitionHeader_icReqHeader")[icHeaderElements.length - 1];
					el.parentNode.removeChild(el) ;
				} else
					icHeaderElements[icHeaderElements.length - 1].removeNode(true);
			}
			if (removeFromLine) {
				if (icLineElements != undefined && icLineElements[icLineElements.length - 1]) {
					// Remove last element that was used as a dummy field to prevent javascript errors
					if(bw == 'NS6') {
						//frm.elements.item("RequisitionLine_icReqLine")[icLineElements.length - 1].removeNode(true);
						var el = document.getElementsByName("RequisitionLine_icReqLine")[icHeaderElements.length - 1];
						el.parentNode.removeChild(el) ;
					} else
						icLineElements[icLineElements.length - 1].removeNode(true);
				}
			}

			if (ckboxElements.length > 1) {
				for (var i=0; i < ckboxElements.length; i++) {
					if (ckboxElements[i].checked)
					{
						recordSelected = true;
						break;
					}
				}
				if (recordSelected) {
					for (var i=ckboxElements.length - 1; i >= 0 ; i--) {
						if (!ckboxElements[i].checked)
						{
							if (icHeaderElements != undefined && icHeaderElements[i]) {
								if(bw == 'NS6')
									icHeaderElements[i].removeChild(icHeaderElements[i].childNodes[0]);
								else
									icHeaderElements[i].removeNode(true);
							}
							if (icLineElements != undefined && icLineElements[i]) {
								if(bw == 'NS6')
									icLineElements[i].removeChild(icLineElements[i].childNodes[0]);
								else
									icLineElements[i].removeNode(true);
							}
						}
					}
				}
			} else {
				var ckbox = ckboxElements;
				if (ckbox.checked) {
					recordSelected = true;
				}
			}

			if (!recordSelected) {
				alert("You have not selected any records to reassign.");
				return false;
			}

			getAssignedRequisitionsSetup("UNASSIGNED");
			frm.assignFrom.value = "Unassigned";

			doSubmit('/workload/buyer_assignment.jsp', 'BrowseSetInputRequestValues;RequisitionManualBuyerAssignment;BrowseRetrieve');
		}

		function checkUnassigned() {
			if (frm.assignFrom.value != 'Unassigned') {
				frm.fromUnassigned.checked = false;
			}
		}

		function setUnassigned() {
			if (frm.fromUnassigned.checked) {
				frm.assignFrom.value = "Unassigned";
				frm.as_assignFromName.value = "Unassigned";
			} else {
				frm.assignFrom.value = "";
				frm.as_assignFromName.value = "";
			}
		}

		function getAssignedRequisitions() {
			var assignFrom = frm.assignFrom[frm.assignFrom.selectedIndex].value;

			getAssignedRequisitionsSetup(assignFrom);

			doSubmit('/workload/buyer_assignment.jsp', 'BrowseRetrieve');
		}

		function getAssignedRequisitionsSetup(assignFrom) {
			clearFilters();

	<%	if (PropertiesManager.getInstance(oid).getProperty("ASSIGNMENT", "AssignByLine", "N").equalsIgnoreCase("Y")) {%>
			frm.browseName.value = "buyer-assignment-wkld-by-line";
			if (assignFrom == "UNASSIGNED") {
				setAdvancedFilter("RequisitionLine_assignedBuyer", "=", "UNASSIGNED", "OR", "Y", "N");
				setAdvancedFilter("RequisitionLine_assignedBuyer", "=", "PURCHASING", "", "Y", "N");
			} else {
				setAdvancedFilter("RequisitionLine_assignedBuyer", "=", assignFrom, "", "Y", "N");
			}
	<%	} else {%>
			frm.browseName.value = "buyer-assignment-wkld";
			if (assignFrom == "UNASSIGNED") {
				setAdvancedFilter("RequisitionHeader_assignedBuyer", "=", "UNASSIGNED", "OR", "Y", "N");
				setAdvancedFilter("RequisitionHeader_assignedBuyer", "=", "PURCHASING", "", "Y", "N");
			} else {
				setAdvancedFilter("RequisitionHeader_assignedBuyer", "=", assignFrom, "", "Y", "N");
			}
	<%	}%>
		}

		function viewReqPreview(icReqHeader) {
			popupParameters = "RequisitionHeader_icReqHeader=" + icReqHeader;
			doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieve', 'WIDTH=<%=formEntryWidth%>', 'HEIGHT=540px');
		}

	function resetDisabledFlds() {
		// overwrite resetDisabledFlds on puridiom.js - this functions takes too much time to loop through all fields if there are a lot of records
		//	so for now do not change display
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('/workload/buyer_assignment.jsp', 'BrowseRetrieve');
	}
	function browseWorkloadView() {
        document.getElementById("originalFilterFields").innerHTML='';
		browse('prm-req-worlkloadview');
		doSubmit('/browse/browse.jsp', 'BrowseRetrieve');
	}
// End Hide script -->
</SCRIPT>

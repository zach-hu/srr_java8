<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<input type=hidden name=browsePage value="/workload/inspector_assignment.jsp">
<input type=hidden name=inspectorAssignedIsSearched value="<%=request.getAttribute("inspectorAssignedIsSearched") %>">
<%
	String	assignFrom = HiltonUtility.ckNull((String) request.getAttribute("assignFrom"));
	String	assignFromEngine = HiltonUtility.ckNull((String) request.getAttribute("assignFromEngine"));
	String	assignTo = HiltonUtility.ckNull((String) request.getAttribute("assignTo"));
	String inspectorDeafult = HiltonUtility.ckNull((String) request.getAttribute("inspectorAssigned"));
	String engineerDeafult = HiltonUtility.ckNull((String) request.getAttribute("engineerAssigned"));
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("pagereturn"));
	List	inspectorList = UserManager.getInstance().getInspectorList(oid);
	List	engineerList = UserManager.getInstance().getEngineerList(oid);
	StringBuffer inspectorOptionValues = new StringBuffer();
	StringBuffer engineerOptionValues = new StringBuffer();


	for (int i=0; i < inspectorList.size(); i++) {
		UserProfile inspector = (UserProfile) inspectorList.get(i);
		if(inspectorDeafult.equalsIgnoreCase(inspector.getUserId())) {
			inspectorOptionValues.append("<option value=\"" + inspector.getUserId() + "\" SELECTED>" + inspector.getDisplayName() + "</option>");
		}
		else{
			inspectorOptionValues.append("<option value=\"" + inspector.getUserId() + "\">" + inspector.getDisplayName() + "</option>");
		}
	}

	for (int i=0; i < engineerList.size(); i++) {
		UserProfile engineer = (UserProfile) engineerList.get(i);
		if(engineerDeafult.equalsIgnoreCase(engineer.getUserId())) {
			engineerOptionValues.append("<option value=\"" + engineer.getUserId() + "\" SELECTED>" + engineer.getDisplayName() + "</option>");
		}
		else{
			engineerOptionValues.append("<option value=\"" + engineer.getUserId() + "\">" + engineer.getDisplayName() + "</option>");
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
			<div id="pxbutton"><a href="javascript: setInspectorAssignment(); void(0);">Assign</a></div>
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
			<td colspan="2" class=label align=center>Inspector</td>
			<td width=20px>&nbsp;</td>
			<td colspan="2" class=label align=center>QA Engineer</td>
		</tr>
		<tr>
			<td class=label align=right>Assign From Queue:</td>
			<td>
				<select name="assignFrom" onchange="getAssignedReceipts();">
					<option value="UNASSIGNED">-- UNASSIGNED --</option>
					<%=inspectorOptionValues.toString()%>
				</select>
			</td>
			<td width=20px>&nbsp;</td>
			<td class=label align=right>Assign From Queue:</td>
			<td>
				<select name="assignFromEngine" onchange="getAssignedReceiptsEngine();">
					<option value="UNASSIGNED">-- UNASSIGNED --</option>
					<%=engineerOptionValues.toString()%>
				</select>
			</td>
			<td width=20px>&nbsp;</td>
						<td align=right rowspan=2>
			<%--	for (int ip = 0; ip < ipageCount; ip++) {--%>
<!--				<div id="reassignAllFlag" style="visibility:hidden; display:none;">-->
				<div id="reassignAllFlag">
				<input type=checkbox name="reassignAll" onclick="flagAll(this.checked);">
				</div>
			<%--	}--%>
			</td>
			<td class=label rowspan=2>Re-assign all receipts listed</div></a></td>
		</tr>
		<tr id="assignToId">
			<td class=label align=right>Assign To Queue:</td>
			<td>
				<select name="assignTo">
					<option value="UNASSIGNED">-- UNASSIGNED --</option>
					<%=inspectorOptionValues.toString()%>
				</select>
			</td>
			<td width=20px>&nbsp;</td>
			<td class=label align=right>Assign To Queue:</td>
			<td>
				<select name="assignToEngine">
					<option value="UNASSIGNED">-- UNASSIGNED --</option>
					<%=engineerOptionValues.toString()%>
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

		if(frm.inspectorAssignedIsSearched.value == 'save'){
			frm.assignFromEngine.value = "Unassigned";
			frm.assignFrom.value = "Unassigned";
			frm.assignToEngine.value = "Unassigned";
			frm.assignTo.value = "Unassigned";
		}

		setDisplay();

		/*function thisLoad(){
			if(frm.inspectorAssignedIsSearched.value == 'save'){
				frm.assignFromEngine.value = "Unassigned";
				frm.assignFrom.value = "Unassigned";
				frm.assignToEngine.value = "Unassigned";
				frm.assignTo.value = "Unassigned";
			}
		}*/

		function checkHiddenMenu()
		{
			hideArea("navTable");
			displayArea("menubarSpacer");
		}


		function setDisplay() {
			reAssignCheck();
			hideArea("resetOriginalOption");
			hideArea("filterTextDisplay");
			hideArea("tempInstructions");

			document.getElementById("instructions").innerHTML = document.getElementById("tempInstructions").innerHTML;
			document.getElementById("tempInstructions").innerHTML = "";
			displayArea("instructions");

			var assignFrom = "<%=headerEncoder.encodeForJavaScript(assignFrom)%>";
			var assignFromEngine = "<%=assignFromEngine%>";

			var assignFromOptions = frm.assignFrom.options;
			var assignFromOptionsEngine = frm.assignFromEngine.options;

			for (var i=0; i < assignFromOptions.length; i++) {
				if (assignFrom == assignFromOptions[i].value) {
					frm.assignFrom.selectedIndex = i;
				}
			}

			for (var i=0; i < assignFromOptionsEngine.length; i++) {

				if (assignFromEngine == assignFromOptionsEngine[i].value) {
					frm.assignFromEngine.selectedIndex = i;
				}
			}

			setNewPageOptions();
		}

		function flagAll(checkAll) {
			reAssignCheck();

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

				if (checkboxes != undefined) {
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
		}

		function reAssignCheck(){
			var checkboxes = document.all("c_checkbox");
			var dd0 = document.all("d_dropdown_0");
			var dd1 = document.all("d_dropdown_1");
			var assignToId = document.all("assignToId");
			var assignAll = frm.elements.item("reassignAll");
			var disable = false;
			if(assignAll.checked){
				//displayArea("c_checkbox");
				//displayArea("assignToId");
				disable = false;
				allowInputEdit(assignToId, true);
			}else{
				disable = true;
				allowInputEdit(assignToId, false);
				//hideArea("c_checkbox");
				//hideArea("assignToId");
			}
			if (checkboxes != undefined) {
				if (checkboxes.length > 1) {
					for (i = 0; i < checkboxes.length; i++) {
						var cbox = checkboxes[i];
						cbox.disabled = disable;
					}
				} else {
					var cbox = checkboxes;
					cbox.disabled = disable;
				}
			}
			if (dd0 != undefined) {
//			if (dd0.length > 1) {
			if (checkboxes.length > 1) {
				for (i = 0; i < dd0.length; i++) {
					var dd = dd0[i];
					dd.disabled = !disable;
					dd = dd1[i];
					dd.disabled = !disable;
				}
			} else {
				var dd = dd0;
				dd.disabled = !disable;
				dd = dd1;
				dd.disabled = !disable;
			}
			}
		}

		function setNewPageOptions() {

			var pageFlags = document.all("reassignAllFlag");

			if (pageFlags != null && pageFlags != undefined) {
				if (pageFlags.length != null && pageFlags.length != undefined) {
					for (var i = 0; i < pageFlags.length; i++) {
						if (i == (currentPage - 1)) {
							pageFlags(i).style.display="block";
							pageFlags(i).style.visibility="visible";
						} else {
							pageFlags(i).style.visibility="hidden";
							pageFlags(i).style.display="none";
						}
					}
				} else {
					pageFlags.style.display="block";
					pageFlags.style.visibility="visible";
				}
			}
		}

		function setInspectorAssignment() {
			var assignAll = frm.elements.item("reassignAll");
			var ckboxElements = frm.elements.item("c_checkbox");
			var drdwnElements0 = frm.elements.item("d_dropdown_0");
			var drdwnElements1 = frm.elements.item("d_dropdown_1");
			var icHeaderElements = frm.elements.item("ReceiptHeader_icRecHeader");
			var recordSelected = false;
			var inputHidden = "";

			if(assignAll.checked){

				if (frm.assignTo.value == "UNASSIGNED" || frm.assignToEngine.value == "UNASSIGNED") {
					alert("You must select an inspector and engineer in which to re-assign the receipts.");
					frm.assignTo.focus();
					return false;
				}


				if (ckboxElements.length > 1) {
					inputHidden = inputHidden + "<input type=\"hidden\" name=\"reAssignAll\" value=\"Y\">";
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
									icHeaderElements[i].removeNode(true);
								}
							}
						}
					}

				} else {
					inputHidden = inputHidden + "<input type=\"hidden\" name=\"reAssignAll\" value=\"Y\">";
					var ckbox = ckboxElements;
					if (ckbox.checked) {
						recordSelected = true;
					}

				}

			} else {

				if (drdwnElements0.length > 1 && drdwnElements0.type == undefined) {
					for (var i=0; i < drdwnElements0.length; i++) {
						if (drdwnElements0[i].value != '' && drdwnElements1[i].value != '')
						{
							recordSelected = true;
							inputHidden = inputHidden + "<input type=\"hidden\" name=\"inspectorToAssign\" value=\""+drdwnElements0[i].value+"\">";
							inputHidden = inputHidden + "<input type=\"hidden\" name=\"engineerToAssign\" value=\""+drdwnElements1[i].value+"\">";
						}
						else
						{
							inputHidden = inputHidden + "<input type=\"hidden\" name=\"inspectorToAssign\" value=\"\">";
							inputHidden = inputHidden + "<input type=\"hidden\" name=\"engineerToAssign\" value=\"\">";
						}
					}
				} else {
					if (drdwnElements0.value != '' && drdwnElements1.value != '')
					{
						recordSelected = true;
						inputHidden = inputHidden + "<input type=\"hidden\" name=\"inspectorToAssign\" value=\""+drdwnElements0.value+"\">";
						inputHidden = inputHidden + "<input type=\"hidden\" name=\"engineerToAssign\" value=\""+drdwnElements1.value+"\">";
					}
					else
					{
						inputHidden = inputHidden + "<input type=\"hidden\" name=\"inspectorToAssign\" value=\"\">";
						inputHidden = inputHidden + "<input type=\"hidden\" name=\"engineerToAssign\" value=\"\">";
					}
				}

			}


			if (recordSelected) {
				addHiddenFields(inputHidden);

				if(frm.assignFrom.value != "UNASSIGNED"){
					getAssignedReceiptsSetup(frm.assignFrom.value);
				} else if(frm.assignFromEngine.value != "UNASSIGNED"){
					getAssignedReceiptsSetupEngine(frm.assignFromEngine.value);
				} else {
					getAssignedReceiptsSetup("UNASSIGNED");
				}

				frm.inspectorAssignedIsSearched.value ='save';
				doSubmit('/workload/inspector_assignment.jsp', 'ReceiptManualInspectorAssignment;BrowseRetrieve');
			}
			else{
				alert("You must select an inspector and engineer in which to assign the receipts.");
			}

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

		function getAssignedReceipts() {
			frm.assignFromEngine.value = "UNASSIGNED";
			frm.assignToEngine.value = "UNASSIGNED";
			var assignFrom = frm.assignFrom[frm.assignFrom.selectedIndex].value;
			//var assignFromEngine = frm.assignFromEngine[frm.assignFromEngine.selectedIndex].value;

			//clearFilters();
			getAssignedReceiptsSetup(assignFrom);
			//getAssignedReceiptsSetupEngine(assignFromEngine);
			frm.assignTo.value="Unassigned";
			frm.assignToEngine.value="Unassigned";
			frm.assignFromEngine.value="Unassigned";
			frm.inspectorAssignedIsSearched.value='find';
			doSubmit('/workload/inspector_assignment.jsp', 'BrowseRetrieve');
		}

		function getAssignedReceiptsEngine() {
			frm.assignFrom.value = "UNASSIGNED";
			frm.assignTo.value = "UNASSIGNED";
			var assignFromEngine = frm.assignFromEngine[frm.assignFromEngine.selectedIndex].value;
			//var assignFrom = frm.assignFrom[frm.assignFrom.selectedIndex].value;

			//clearFilters();
			getAssignedReceiptsSetupEngine(assignFromEngine);
			//getAssignedReceiptsSetup(assignFrom);
			frm.assignTo.value="Unassigned";
			frm.assignToEngine.value="Unassigned";
			frm.assignFrom.value="Unassigned";
			frm.inspectorAssignedIsSearched.value='find';
			doSubmit('/workload/inspector_assignment.jsp', 'BrowseRetrieve');
		}

		function getAssignedReceiptsSetup(assignFrom) {
			clearFilters();
			frm.browseName.value = "inspector-assignment-wkld-by-line";
			if (assignFrom == "UNASSIGNED") {
				setAdvancedFilter("ReceiptLine_inspectorAssigned", "=", "UNASSIGNED", "OR", "Y", "N");
				setAdvancedFilter("ReceiptLine_inspectorAssigned", "=", "''", "OR", "Y", "N");
				setAdvancedFilter("ReceiptLine_inspectorAssigned", "isnull", "null", "OR", "Y", "N");

				setAdvancedFilter("ReceiptLine_engineerAssigned", "=", "UNASSIGNED", "OR", "Y", "N");
				setAdvancedFilter("ReceiptLine_engineerAssigned", "=", "''", "OR", "Y", "N");
				setAdvancedFilter("ReceiptLine_engineerAssigned", "isnull", "null", "OR", "Y", "N");
			} else {
				setAdvancedFilter("ReceiptLine_inspectorAssigned", "=", assignFrom, "", "Y", "N");
			}
		}

		function getAssignedReceiptsSetupEngine(assignFrom) {
			clearFilters();
			frm.browseName.value = "inspector-assignment-wkld-by-line";
			if (assignFrom == "UNASSIGNED") {
				setAdvancedFilter("ReceiptLine_engineerAssigned", "=", "UNASSIGNED", "OR", "Y", "N");
				setAdvancedFilter("ReceiptLine_engineerAssigned", "=", "''", "OR", "Y", "N");
				setAdvancedFilter("ReceiptLine_engineerAssigned", "isnull", "null", "OR", "Y", "N");

				setAdvancedFilter("ReceiptLine_inspectorAssigned", "=", "UNASSIGNED", "OR", "Y", "N");
				setAdvancedFilter("ReceiptLine_inspectorAssigned", "=", "''", "OR", "Y", "N");
				setAdvancedFilter("ReceiptLine_inspectorAssigned", "isnull", "null", "OR", "Y", "N");
			} else {
				setAdvancedFilter("ReceiptLine_engineerAssigned", "=", assignFrom, "", "Y", "N");
			}
		}

		function viewRecPreview(icRecHeader) {
			popupParameters = "ReceiptHeader_icRecHeader=" + icRecHeader;
			doSubmitToPopup('/receipts/rec_preview.jsp', 'ReceiptRetrieve', 'WIDTH=<%=formEntryWidth%>', 'HEIGHT=540px');
		}

	function resetDisabledFlds() {
		// overwrite resetDisabledFlds on puridiom.js - this functions takes too much time to loop through all fields if there are a lot of records
		//	so for now do not change display
	}

	function browse(x) {
		frm.browseName.value = x;
		doSubmit('/workload/inspector_assignment.jsp', 'BrowseRetrieve');
	}
	function browseWorkloadView() {
        document.getElementById("originalFilterFields").innerHTML='';
		browse('prm-rec-worlkloadview');
		doSubmit('/browse/browse.jsp', 'BrowseRetrieve');
	}
// End Hide script -->
</SCRIPT>

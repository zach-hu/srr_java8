<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/ajaxlookup.js"></SCRIPT>

<%
	String s_itemnumber = (String) request.getAttribute("InvItem_itemNumber");
	String invDescription =  HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
	String s_icRouting = (String) request.getAttribute("BomRouting_icRouting");
	String s_icMethod = (String) request.getAttribute("BomMethod_icMethod") ;
	String	errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));
	String bomSource = HiltonUtility.ckNull((String) request.getAttribute("bomSource"));
	if (bomSource == null) bomSource = "I" ;

	String	s_action = (String) request.getAttribute("bomAction");
	String	s_title = "";

	if (s_action == null)
	{
		s_action = "CREATE";
	}

	BigDecimal bd_zero = new BigDecimal(0);
	BigDecimal bd_batchSize = bd_zero ;
	BigDecimal bd_setupHours = bd_zero ;
	BigDecimal bd_persons = bd_zero ;
	BigDecimal bd_partsHour = bd_zero ;
	BigDecimal bd_qtyDays = bd_zero ;
	BigDecimal bd_leadTime = bd_zero ;
	BigDecimal bd_utilization = bd_zero ;
	BigDecimal bd_cCost = bd_zero ;
	BigDecimal bd_timePart = bd_zero ;

	String		  s_stageId = "" ;
	String		  s_description = "" ;
	String		  s_machineId = "" ;
	String		  s_workCenterId = "" ;
	String		  s_unit_of_measure = "" ;
	String		  s_notes = "" ;
	String		  s_vendor_name = "" ;
	String		  s_response = "" ;
	String		  s_ponotes = "" ;
	String			s_backflush = "" ;
	String			s_outside = "N" ;
	String			s_owner = uid ;

	if (s_action.equalsIgnoreCase("CREATE"))
	{
		s_title = "New Routing";
	}
	else
	{
		s_title = "Edit Routing";

		BomRouting bomRouting = (BomRouting) request.getAttribute("bomRouting");

		s_description = HiltonUtility.ckNull(bomRouting.getDescription()) ;
		s_stageId = HiltonUtility.ckNull(bomRouting.getStageId()) ;
		s_workCenterId = HiltonUtility.ckNull(bomRouting.getWorkCenterId()) ;
		s_machineId = HiltonUtility.ckNull(bomRouting.getMachineId()) ;
		s_unit_of_measure = HiltonUtility.ckNull(bomRouting.getUnitOfMeasure()) ;
		s_notes = HiltonUtility.ckNull(bomRouting.getNotes()) ;
		s_vendor_name = HiltonUtility.ckNull(bomRouting.getVendorName()) ;
		s_response = HiltonUtility.ckNull(bomRouting.getRespons()) ;
		s_ponotes = HiltonUtility.ckNull(bomRouting.getPoNotes()) ;
		s_backflush = HiltonUtility.ckNull(bomRouting.getBackflush()) ;
		s_outside = HiltonUtility.ckNull(bomRouting.getOutside()) ;
		s_owner = HiltonUtility.ckNull(bomRouting.getOwner()) ;

		bd_setupHours = bomRouting.getSetupHours() ;
		bd_persons = bomRouting.getPersons() ;
		bd_partsHour = bomRouting.getPartsHour() ;
		bd_qtyDays = bomRouting.getQtyDays() ;
		bd_leadTime = bomRouting.getLeadTime() ;
		bd_cCost = bomRouting.getCcost() ;
		bd_utilization = bomRouting.getUtilization() ;
		bd_timePart = bomRouting.getTimePart() ;
		if (bd_persons == null) bd_persons = bd_zero ;
		if (bd_partsHour == null) bd_partsHour = bd_zero ;
		if (bd_qtyDays == null) bd_qtyDays = bd_zero ;
		if (bd_leadTime == null) bd_leadTime = bd_zero ;
		if (bd_cCost == null) bd_cCost = bd_zero ;
		if (bd_utilization == null) bd_utilization = bd_zero ;
		if (bd_timePart == null) bd_timePart = bd_zero ;
	}
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_itemnumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=invDescription%>"/>

<tsa:hidden name="BomRouting_icRouting" value="<%=s_icRouting%>"/>
<tsa:hidden name="BomRouting_outside" value="<%=s_outside%>"/>
<tsa:hidden name="BomMethod_icMethod" value="<%=s_icMethod%>"/>
<tsa:hidden name="BomRouting_icMethod" value="<%=s_icMethod%>"/>
<tsa:hidden name="BomTask_icRouting" value="<%=s_icRouting%>"/>
<tsa:hidden name="BomTask_icTask" value=""/>
<tsa:hidden name="BomRouting_parentItem" value="<%=s_itemnumber%>"/>
<tsa:hidden name="BomTask_owner" value="${userId}"/>

<tsa:hidden name="bomSource" value="<%=bomSource %>"/>
<tsa:hidden name="bomAction" value="<%=s_action%>"/>
<tsa:hidden name="bomTaskAction" value="<%=s_action%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentPage" value="/inventory/bom_routing.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Parent Item:</b></td>
			<td width=100px nowrap><%=s_itemnumber%></td>
		</tr>
		</table>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td colspan=2 class=error align=center><br><%=errorMsg%><br></td></tr>
<tr>
	<td width=680px align=center>
		<table border=0 cellspacing=0 cellpadding=0>
				<tr>
					<% if (! s_action.equalsIgnoreCase("CREATE")) { %>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmr-stageId", "Routing Id")%>:&nbsp;</td>
					<% } else { %>
					<td align=right><a href="javascript: browseLookup('BomRouting_stageId', 'invstage'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmr-stageId", "Routing Id")%>:</a>&nbsp;</td>
					<% } %>
					<td><input type=text size=15 name="BomRouting_stageId"  tabindex="1" value="<%=s_stageId%>"  <% if (! s_action.equalsIgnoreCase("CREATE")) { %> disabled <% } %> onchange="upperCase(this);lookupRouting(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmr-description")%>>
					<td align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmr-description", "Description", true)%>:&nbsp</td>
					<td><input type=text size=50 name="BomRouting_description" TABINDEX=3 value="<%=s_description%>" style="text-align:left"></td>
				</tr>
				<tr>
					<td align=right><a href="javascript: browseLookup('BomRouting_workCenterId', 'invworkcenter'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmr-workCenterId", "Work Center Id")%>:</a>&nbsp;</td>
					<td><input type=text size=15 name="BomRouting_workCenterId"  tabindex="1" value="<%=s_workCenterId%>"  onchange="upperCase(this);lookupWC(this);"></td>
				</tr>
				<tr>
					<td align=right><a href="javascript: browseLookup('BomRouting_machineId', 'invmachine'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmr-machineId", "Machine Id")%>:</a>&nbsp;</td>
					<td><input type=text size=15 name="BomRouting_machineId"  tabindex="1" value="<%=s_machineId%>"  onchange="upperCase(this);"></td>
				</tr>
		</table>
		<br>
		<hr width=475px align=center class=browseHR>
		<br>
<div id=notOutside style="display:block;visibility:visible;">
		<table border=0 cellpadding=0 cellspacing=0 width=550px align=center>
		<tr>
			<td align=right>Setup Hours:&nbsp;</td>
			<td colspan=2><input type="text" name="BomRouting_setupHours" SIZE="15" MAXLENGTH="15" value="<%=bd_setupHours%>" style="text-align:right"> </td>
			<td align=right>Processed / Hour:&nbsp;</td>
			<td colspan=2><input type="text" name="BomRouting_partsHour" SIZE="15" MAXLENGTH="15" value="<%=bd_partsHour%>" style="text-align:right"></td>
		</tr>
		<tr>
			<td align=right>Items / Process:&nbsp;</td>
			<td colspan=2><input type="text" name="BomRouting_utilization" SIZE="15" MAXLENGTH="15" value="<%=bd_utilization%>" style="text-align:right"></td>
			<td align=right># Persons:&nbsp;</td>
			<td colspan=2><input type="text" name="BomRouting_persons" SIZE="15" MAXLENGTH="15" value="<%=bd_persons%>" style="text-align:right"></td>
		</tr>
		<tr>
			<td align=right>Days to Next:&nbsp;</td>
			<td colspan=2><input type="text" name="BomRouting_qtyDays" SIZE="15" MAXLENGTH="15" value="<%=bd_qtyDays%>" style="text-align:right"></td>
			<td align=right>&nbsp;</td>
			<td align=right>&nbsp;</td>
		</tr>
		</table>
</div>

<div id=outside style="display:block;visibility:visible;">
		<table border=0 cellpadding=0 cellspacing=0 width=550px align=center>
		<tr>
			<td align=right>Suppler:&nbsp;</td>
			<td colspan=2><input type="text" name="BomRouting_vendorName" SIZE="40" MAXLENGTH="40" value="<%=s_vendor_name%>"> </td>
		</tr>
		<tr>
			<td align=right>Cost:&nbsp;</td>
			<td colspan=2><input type="text" name="BomRouting_ccost" SIZE="15" MAXLENGTH="15" value="<%=bd_cCost%>" style="text-align:right"></td>
		</tr>
		<tr>
			<td align=right>Unit Of Measure:&nbsp;</td>
			<td colspan=2><input type="text" name="BomRouting_unitOfMeasure" SIZE="15" MAXLENGTH="15" value="<%=s_unit_of_measure%>" > </td>
		</tr>
		<tr>
			<td align=right>Lead Time:&nbsp;</td>
			<td colspan=2><input type="text" name="BomRouting_leadTime" SIZE="15" MAXLENGTH="15" value="<%=bd_leadTime%>" style="text-align:right"></td>
		</tr>
		</table>
</div>
		<br>
		<hr width=475px align=center class=browseHR>
		<br>
		<table border=0 cellspacing=0 cellpadding=0>
			<tr <%=HtmlWriter.isVisible(oid, "bmr-notes")%>>
					<td align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmr-notes", "Notes", true)%>:&nbsp</td>
					<td colspan=2>
					<textarea name="BomRouting_notes" cols=60 rows=4 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this, 60);">${esapi:encodeForHTML(bomRouting.notes)}</textarea>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<br>
<hr width=475px align=center class=browseHR>
<br>
</tr>
</table>
<% 	if (! s_action.equalsIgnoreCase("CREATE")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=bomTasksTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td width=100%>
					<table border=0 cellspacing=0 cellpadding=2 width=100%>
					<tr>
						<td class=browseHdr  width=80% height=18px nowrap>&nbsp;Tasks</td>
						<td class=browseHdr  width=20% align=center><a href="javascript: addTask(); void(0);" title="Add Task">Add</a></td>

					</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td  align=center valign=bottom width=5%><b>Line</b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmt-taskId")%> align=center valign=bottom width=20%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-taskId", "Task Id")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmt-description")%> align=center valign=bottom width=70%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-description", "Description")%></b></td>
							<td align=center valign=bottom width=10%><b>Delete</b></td>
						</tr>
						</table>
						<table border=0 cellspacing=0 cellpadding=2 width=100% classbrowseRow>
<%
	List bomTaskList = (List)request.getAttribute("bomTaskList");
	if (bomTaskList == null) bomTaskList = new ArrayList() ;
	for(int i=0;i<bomTaskList.size();i++)
	{
		BomTask bomTask = (BomTask) bomTaskList.get(i);
%>
						<tr>
							<td  width=5% align=right nowrap><%=(i + 1)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmt-taskId")%> width=20% align=left nowrap>
								<a href="javascript: viewTask(<%=i%>);" title="Edit Task"><%=bomTask.getTaskId()%></a>
								<tsa:hidden id="bomTask_<%=i%>" name="bomTask_<%=i%>" value="<%=bomTask.getIcTask() %>"/>
								<tsa:hidden id="bomTaskLine_<%=i%>" name="bomTaskLine_<%=i%>" value="<%=(i + 1) %>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "bmt-description")%> width=70% align=left nowrap><%=HiltonUtility.ckNull(bomTask.getDescription())%></td>
							<td width=10% align=center nowrap><a href="javascript: deleteTask(<%=i%>);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Task"></a></td>
						</tr>
<% } %>
						</table>
					</td>
				</tr>
				</table>
			</td>
</tr>
</table>
<% } %>
<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a onclick="javascript: submitThis();void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/bom_master_sub.jsp', 'BomMasterRetrieveByItem');"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var outside = "<%=s_outside%>" ;

	if (outside == "Y") {
		hideArea("notOutside") ;
	} else {
		hideArea("outside") ;
	}

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var myText = document.getElementById(areaName + "Text");
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas(i);


			if (myArea.style.display == "none") {
				myArea.style.display = "block";
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
			}
		}

		if (type == undefined) {
			type = "Details";
		}

		if (hideArea) {
			myImg.src = "<%=contextPath%>/images/plus.gif";
			myImg.alt = "View" + type;
			myText.innerText = "View " + type;
		}
		else {
			myImg.src = "<%=contextPath%>/images/minus.gif";
			myImg.alt = "Hide " + type;
			myText.innerText = "Hide " + type;
		}
	}

	function highlightItem(row) {
		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "selectedRow");
	}

	function removeItemHighlight(row) {
		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "browseRow");
	}

	function validateForm() {
	  var alertMessage = '';
      var handler = frm.handler.value;

      if (handler.indexOf("BomRoutingAdd") >= 0) {
	      if (isEmpty(frm.BomRouting_stageId.value))
	        	alertMessage += 'Routing Id is a required entry.\n';

	      if (alertMessage.length > 0) {
	            alert ('Please fix the following problems:\n\n' + alertMessage);
	            return false;
		  }
	  }

	  return true;
    }

	function submitThis()
	{
		if (frm.bomAction.value == "CREATE")
		{
			frm.bomAction.value="UPDATE";
			doSubmit('/inventory/bom_routing.jsp', 'BomRoutingAdd;BomRoutingRetrieveById');
		}
		else
		{
			doSubmit('/inventory/bom_master_sub.jsp', 'BomRoutingUpdate;BomMasterRetrieveByItem');
		}
	}

	function lookupRouting(id) {

        var idValue = id.value ;
		var url = "<%=requestURLPath%>" + "/TableLookup?uid=${esapi:encodeForJavaScript(userId)}&oid=<%=oid%>&process=invstage-retrieve-by.xml&resultObj=invStage";
        url = url + "&InvStage_stageId=" + escape(idValue) ;
		lookupRequest(url, lookupRoutingResponse) ;
	}

	function lookupRoutingResponse() {
		if (http.readyState == 4) {
			var status = http.status ;
			if (http.status == 200) {
                  // Or for XML formatted response text:
                  var resp = http.responseXML ;
                  setResponseValue(http.responseXML, "Description", "BomRouting_description") ;
                  setResponseValue(http.responseXML, "WorkCenterId", "BomRouting_workCenterId") ;
                  setResponseValue(http.responseXML, "Notes", "BomRouting_notes") ;
                  setResponseValue(http.responseXML, "MachineId", "BomRouting_machineId") ;
                  setResponseValue(http.responseXML, "Outside", "BomRouting_outside") ;


                  var outside = frm.BomRouting_outside.value ;

				  if (outside == "Y") {
	                  setResponseValue(http.responseXML, "VendorName", "BomRouting_vendorName") ;
	                  setResponseValue(http.responseXML, "UnitOfMeasure", "BomRouting_unitOfMeasure") ;
	                  setResponseValue(http.responseXML, "Ccost", "BomRouting_ccost") ;
	                  setResponseValue(http.responseXML, "LeadTime", "BomRouting_leadTime") ;
				  } else {
	                  setResponseValue(http.responseXML, "SetupHours", "BomRouting_setupHours") ;
	                  setResponseValue(http.responseXML, "PartsHour", "BomRouting_partsHour") ;
	                  setResponseValue(http.responseXML, "Utilization", "BomRouting_utilization") ;
	                  setResponseValue(http.responseXML, "Persons", "BomRouting_persons") ;
	                  setResponseValue(http.responseXML, "QtyDays", "BomRouting_qtyDays") ;
				  }
				  setOutside() ;
            } else {
//                  document.getElementById('BomComponent_description').value = "" ;
//                  alert ( "Routing Id not found! ");
            }
		}
	}

	function lookupWC(idObject) {

		var idValue = idObject.value ;
		var url = "<%=requestURLPath%>" + "/TableLookup?uid=${esapi:encodeForJavaScript(userId)}&oid=<%=oid%>&process=invworkcenter-retrieve-by.xml&resultObj=invWorkCenter";
        url = url + "&InvWorkCenter_workCenterId=" + escape(idValue) ;
		lookupRequest(url, lookupWCResponse) ;
	}

	function lookupWCResponse() {
		if (http.readyState == 4) {
			var status = http.status ;
			if (http.status == 200) {
                  // Or for XML formatted response text:
                  setResponseValue(http.responseXML, "Subcontract", "BomRouting_outside") ;
                  setOutside();
				  if (frm.BomRouting_outside.value  == "Y") {
	                  setResponseValue(http.responseXML, "VendorName", "BomRouting_vendorName") ;
    	              setResponseValue(http.responseXML, "UnitOfMeasure", "BomRouting_unitOfMeasure") ;
        	          setResponseValue(http.responseXML, "Cost", "BomRouting_ccost") ;
            	      setResponseValue(http.responseXML, "LeadTime", "BomRouting_leadTime") ;
        	      }

            } else {
//                  document.getElementById('BomComponent_description').value = "" ;
//                  alert ( "Routing Id not found! ");
            }
		}
	}


	function viewTask(row)
	{
		var loc = document.getElementById("bomTask_" + row);
		frm.BomTask_icTask.value = loc.value ;
		frm.bomTaskAction.value = "UPDATE";
		doSubmit('/inventory/bom_task.jsp','BomTaskRetrieveById;BomTaskRetrieveByIcRouting');
	}

	function deleteTask(row) {
		var loc = document.getElementById("bomTask_" + row);
		var line = document.getElementById("bomTaskLine_" + row).value ;
		frm.bomTaskAction.value = "DELETE";
		frm.BomTask_icTask.value = loc.value;
		if (confirm("Are you sure you want to delete task line " + line + "?")) {
			doSubmit('inventory/bom_routing.jsp','BomTaskDelete;BomRoutingRetrieveById;BomTaskRetrieveByIcRouting');
		}
	}

	function addTask()
	{
		frm.bomTaskAction.value = "CREATE";
		doSubmit('/inventory/bom_task.jsp', 'DoNothing');
	}

	function setOutside() {
	   outside = frm.BomRouting_outside.value ;
		if (outside == "N") {
			hideArea("outside") ;
			displayArea("notOutside") ;
		} else {
			displayArea("outside") ;
			hideArea("notOutside") ;
		}
	}


// End Hide script -->
</SCRIPT>
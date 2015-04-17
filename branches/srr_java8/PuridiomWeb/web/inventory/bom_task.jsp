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
	String s_icTask = (String) request.getAttribute("BomTask_icTask");
	String s_stageId = HiltonUtility.ckNull((String) request.getAttribute("BomRouting_stageId"));
	String s_icRouting = HiltonUtility.ckNull((String) request.getAttribute("BomRouting_icRouting"));
	String s_icMethod = (String) request.getAttribute("BomMethod_icMethod");
	String	errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));
	String bomSource = HiltonUtility.ckNull((String) request.getAttribute("bomSource"));
	if (bomSource == null) bomSource = "I" ;

	String	s_action = (String) request.getAttribute("bomAction");
	String	s_task_action = (String) request.getAttribute("bomTaskAction");

	String	s_title = "";

	if (s_task_action == null)
	{
		s_task_action = "CREATE";
	}

	BigDecimal bd_zero = new BigDecimal(0);
	BigDecimal bd_batchSize = bd_zero ;
	String		  s_taskId = "" ;
	String		  s_description = "" ;
	String		  s_dateEntered = "" ;
	String		  s_notes = "" ;
	String		  s_type = "" ;

	if (s_task_action.equalsIgnoreCase("CREATE"))
	{
		s_title = "New Task";
	}
	else
	{
		s_title = "Edit Task";

		BomTask bomTask = (BomTask) request.getAttribute("bomTask");

		s_description = HiltonUtility.ckNull(bomTask.getDescription()) ;
		s_type = HiltonUtility.ckNull(bomTask.getTaskType()) ;
		s_taskId = HiltonUtility.ckNull(bomTask.getTaskId()) ;
		s_notes = HiltonUtility.ckNull(bomTask.getNotes());
	}
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_itemnumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=invDescription%>"/>

<tsa:hidden name="bomSource" value="<%=bomSource %>"/>
<tsa:hidden name="BomRouting_icRouting" value="<%=s_icRouting%>"/>
<tsa:hidden name="BomRouting_stageId" value="<%=s_stageId%>"/>
<tsa:hidden name="BomMethod_icMethod" value="<%=s_icMethod%>"/>
<tsa:hidden name="BomRouting_icMethod" value="<%=s_icMethod%>"/>

<tsa:hidden name="BomTask_icTask" value="<%=s_icTask%>"/>
<tsa:hidden name="BomTask_icRouting" value="<%=s_icRouting%>"/>
<tsa:hidden name="BomTask_owner" value="${userId}"/>
<tsa:hidden name="BomTask_stageId" value="<%=s_stageId%>"/>

<tsa:hidden name="bomAction" value="<%=s_action%>"/>
<tsa:hidden name="bomTaskAction" value="<%=s_task_action%>"/>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentPage" value="/inventory/bom_task.jsp"/>

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
			<td align=right><b>Routing Id:</b>;</td>
			<td width=100px nowrap><%=s_stageId%></td>
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
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=80% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr>
					<% if (! s_task_action.equalsIgnoreCase("CREATE")) { %>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-taskId", "Task Id")%>:&nbsp;</td>
					<% } else { %>
					<td align=right><a href="javascript: browseLookup('BomTask_taskId', 'invtask'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-taskId", "Task Id")%>:</a>&nbsp;</td>
					<% } %>
					<td><input type=text size=15 name="BomTask_taskId"  tabindex="1" value="<%=s_taskId%>"  <% if (! s_task_action.equalsIgnoreCase("CREATE")) { %> disabled <% } %> onchange="upperCase(this);lookupTask(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmm-description")%>>
					<td align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-description", "Description", true)%>:&nbsp</td>
					<td><input type=text size=50 name="BomTask_description" TABINDEX=3 value="<%=s_description%>" style="text-align:left"></td>
				</tr>
				</table>
				<br>
				<hr width=475px align=center class=browseHR>
				<br>
				<table border=0 cellspacing=0 cellpadding=0>
					<tr>
						<td width=140px align=right nowrap>Notes:&nbsp;</td>
						<td colspan=2>
							<textarea name="BomTask_notes" cols=60 rows=4 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this, 60);">${esapi:encodeForHTML(bomTask.notes)}</textarea>
						</td>
					</tr>
				</table>
			</td>
			<td width=20% align=right valign=top>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a onclick="javascript: submitThis();void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/bom_routing.jsp', 'BomRoutingRetrieveById;BomTaskRetrieveByIcRouting');"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
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

      if (handler.indexOf("BomTaskAdd") >= 0) {
	      if (isEmpty(frm.BomTask_taskId.value))
	        	alertMessage += 'Task Id is a required entry.\n';

	      if (alertMessage.length > 0) {
	            alert ('Please fix the following problems:\n\n' + alertMessage);
	            return false;
		  }
	  }

	  return true;
    }

	function submitThis()
	{
		if (frm.bomTaskAction.value == "CREATE")
		{
			frm.bomTaskAction.value="UPDATE";
			doSubmit('/inventory/bom_routing.jsp', 'BomTaskAdd;BomRoutingRetrieveById;BomTaskRetrieveByIcRouting');
		}
		else
		{
			doSubmit('/inventory/bom_routing.jsp', 'BomTaskUpdate;BomRoutingRetrieveById;BomTaskRetrieveByIcRouting');
		}
	}

	function lookupTask(objectId) {

        var idValue = objectId.value;
		var url = "<%=requestURLPath%>" + "/TableLookup?uid=${esapi:encodeForJavaScript(userId)}&oid=<%=oid%>&process=invtask-retrieve-by.xml&resultObj=invTask";
        url = url + "&InvTask_taskId=" + escape(idValue) ;
        lookupRequest(url, lookupTaskResponse) ;
	}

	function lookupTaskResponse() {
		if (http.readyState == 4) {
			var status = http.status ;
			if (http.status == 200) {
                  // Or for XML formatted response text:
                  var resp = http.responseXML ;
                  setResponseValue(http.responseXML, "Description", "BomTask_description") ;
                  setResponseValue(http.responseXML, "Notes", "BomTask_notes") ;
            } else {
//                  document.getElementById('BomComponent_description').value = "" ;
                  alert ( "Task Id not found! ");
            }
		}
	}

// End Hide script -->
</SCRIPT>
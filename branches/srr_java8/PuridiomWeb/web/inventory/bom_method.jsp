<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>
<%@ page import="com.tsagate.foundation.utility.Utility" %>
<%@ page import="com.tsagate.foundation.utility.Dates" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/ajaxlookup.js"></SCRIPT>

<%
	String s_itemnumber = (String) request.getAttribute("InvItem_itemNumber");
	String invDescription =  HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
	String s_icMethod = (String) request.getAttribute("BomMethod_icMethod");
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
	String		  s_methodId = "" ;
	String		  s_description = "" ;
	String		  s_unitOfMeasure = "" ;
	String		  s_dateEntered = Dates.today("", user.getTimeZone()) ;
	String		  s_notes = "" ;

	if (s_action.equalsIgnoreCase("CREATE"))
	{
		s_title = "New Method";
	}
	else
	{
		s_title = "Edit Method";

		BomMethod bomMethod = (BomMethod) request.getAttribute("bomMethod");

		bd_batchSize = bomMethod.getBatchSize() ;
		s_description = HiltonUtility.ckNull(bomMethod.getDescription()) ;
		s_methodId = HiltonUtility.ckNull(bomMethod.getMethodId()) ;
		s_dateEntered = HiltonUtility.getFormattedDate(bomMethod.getDateEntered(),oid, userDateFormat) ;
		s_unitOfMeasure = HiltonUtility.ckNull(bomMethod.getUnitOfMeasure()) ;
		s_notes = HiltonUtility.ckNull(bomMethod.getUnitOfMeasure());
	}
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_itemnumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=invDescription%>"/>
<tsa:hidden name="BomMethod_icMethod" value="<%=s_icMethod%>"/>
<tsa:hidden name="BomMethod_parentItem" value="<%=s_itemnumber%>"/>

<tsa:hidden name="bomSource" value="<%=bomSource %>"/>
<tsa:hidden name="bomAction" value="<%=s_action%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentPage" value="/inventory/bom_method.jsp"/>

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
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=80% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr>
					<% if (! s_action.equalsIgnoreCase("CREATE")) { %>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-methodId", "Method Id")%>:&nbsp;</td>
					<% } else { %>
					<td align=right><a href="javascript: browseLookup('BomMethod_methodId', 'invmethod'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-methodId", "Method Id")%>:</a>&nbsp;</td>
					<% } %>
					<td><input type=text size=15 name="BomMethod_methodId"  tabindex="1" value="<%=s_methodId%>"  <% if (! s_action.equalsIgnoreCase("CREATE")) { %> disabled <% } %> onchange="upperCase(this);lookupMethod(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmm-description")%>>
					<td align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-description", "Description", true)%>:&nbsp</td>
					<td><input type=text size=50 name="BomMethod_description" TABINDEX=3 value="<%=s_description%>" style="text-align:left"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmm-uom")%>>
					<td align=right nowrap><a href="javascript: browseLookup('BomMethod_unitOfMeasure', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-uom", "Unit Of Measure", true)%>:</a>&nbsp</td>
					<td><input type=text size=15 name="BomMethod_unitOfMeasure" TABINDEX=3 value="<%=s_unitOfMeasure%>" style="text-align:left" onchange="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmm-batchSize")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-batchSize", "Batch Size")%>:&nbsp</td>
					<td><input type=text size=15 name="BomMethod_batchSize" TABINDEX=4 value="<%=bd_batchSize%>" style="text-align:right" ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmm-dateEntered")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-dateEntered", "Create Date")%>:&nbsp</td>
					<td><input type=text size=15 name="BomMethod_dateEntered" TABINDEX=5 value="<%=s_dateEntered%>" style="text-align:left" >
					<a href="javascript: show_calendar('BomMethod_dateEntered', 'MM-dd-yyyy');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				</table>
				<br>
				<hr width=475px align=center class=browseHR>
				<br>
				<table border=0 cellspacing=0 cellpadding=0>
					<tr>
						<td width=140px align=right nowrap>Notes:&nbsp;</td>
						<td colspan=2>
							<textarea name="BomMethod_notes" cols=60 rows=4 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this, 60);">${esapi:encodeForHTML(bomMethod.notes)}</textarea>
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
	<td width=50% align=center><a onclick="javascript: submitThis(); void(0);" ><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/bom_master.jsp', 'BomMethodRetrieveByItem');"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
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
	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("bom_method.jsp") < 0)
	{
		setNavCookie("/inventory/bom_method.jsp", "BomMethodRetrieveById", "<%=s_title%>");
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

      if (handler.indexOf("BomMethodAdd") >= 0) {
	      if (isEmpty(frm.BomMethod_methodId.value))
	        	alertMessage += 'Method Id is a required entry.\n';

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
			doSubmit('/inventory/bom_master.jsp', 'BomMethodAdd;BomMethodRetrieveByItem');
		}
		else
		{
			doSubmit('/inventory/bom_master.jsp', 'BomMethodUpdate;BomMethodRetrieveByItem');
		}
	}

	function lookupMethod(objectId) {

		var idValue = objectId.value ;
		var url = "<%=requestURLPath%>" + "/TableLookup?uid=${esapi:encodeForJavaScript(userId)}&oid=<%=oid%>&process=invmethod-retrieve-by.xml&resultObj=invMethod";
        url = url + "&InvMethod_methodId=" + escape(idValue)
		lookupRequest(url, lookupMethodResponse) ;
	}

	function lookupMethodResponse() {
		if (http.readyState == 4) {
			var status = http.status ;
			if (http.status == 200) {
                  // Or for XML formatted response text:
                  var resp = http.responseXML ;
                  setResponseValue(http.responseXML, "Description", "BomMethod_description") ;
                  setResponseValue(http.responseXML, "Notes", "BomMethod_notes") ;
            } else {
//                  document.getElementById('BomComponent_description').value = "" ;
                  alert ( "Method Id not found! ");
            }
		}
	}


// End Hide script -->
</SCRIPT>
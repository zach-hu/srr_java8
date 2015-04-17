<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/ajaxlookup.js"></SCRIPT>

<%
	String s_outside = "N" ;

	InvWorkCenter invWorkCenter = (InvWorkCenter) request.getAttribute("invWorkCenter");
	boolean newInvWorkCenter = false;
	BigDecimal zero = new BigDecimal(0) ;

	if (invWorkCenter == null)
	{

		invWorkCenter = new InvWorkCenter();
		invWorkCenter.setWorkCenterId("") ;
		invWorkCenter.setDescription("") ;
		invWorkCenter.setSubcontract("N") ;
		invWorkCenter.setLaborRate(new BigDecimal(0)) ;
		invWorkCenter.setSetupRate(new BigDecimal(0));
		invWorkCenter.setFixOverHead(new BigDecimal(0)) ;
		invWorkCenter.setVarOverHead(new BigDecimal(0)) ;
		invWorkCenter.setSetuphours(new BigDecimal(0)) ;
		invWorkCenter.setPerDayHours(new BigDecimal(0)) ;
		invWorkCenter.setPerJobHours(new BigDecimal(0)) ;
		invWorkCenter.setProcessTime(new BigDecimal(0)) ;
		invWorkCenter.setProcessItems(new BigDecimal(0)) ;
		invWorkCenter.setBufferDays(new BigDecimal(0)) ;
		invWorkCenter.setLeadTime(new BigDecimal(0)) ;
		invWorkCenter.setVendorName("") ;
		invWorkCenter.setVendorId("") ;
		invWorkCenter.setCost(new BigDecimal(0)) ;
		invWorkCenter.setUnitOfMeasure("") ;
		invWorkCenter.setDateEntered(d_today);
		invWorkCenter.setDateExpires(d_today);
		invWorkCenter.setOwner(uid);
		invWorkCenter.setStatus("02");
		newInvWorkCenter = true;
	}
	else
	{
	    String newInvWorkCenterString = HiltonUtility.ckNull((String) request.getAttribute("newInvWorkCenter"));
		if (newInvWorkCenterString.equals("Y"))
		{
	      newInvWorkCenter = true;
	    }
		s_outside = invWorkCenter.getSubcontract() ;
		if (s_outside == null) s_outside = "N" ;
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, invWorkCenter.getOwner());
	String duplicateInvWorkCenterErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateInvWorkCenterErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateInvWorkCenterFailurePage" value="/admin/systemtables/invworkcenter.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>WorkCenter</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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

<%	if (!HiltonUtility.isEmpty(duplicateInvWorkCenterErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateInvWorkCenterErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=140px align=right nowrap height=18px>WorkCenter Id:&nbsp;</td>
			<td><input type="text" name="InvWorkCenter_workCenterId" value="<%=invWorkCenter.getWorkCenterId()%>" size=25 maxlength=15 <% if (! newInvWorkCenter) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td nowrap align=right>
<%	if (! newInvWorkCenter) { %>
				<a href="javascript: if (verifyAction('Delete this WorkCenter Id?')) { doSubmit('/browse/browse_sys_tables.jsp', 'InvWorkCenterDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete WorkCenter</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Description:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_description" value="<%=HiltonUtility.ckNull(invWorkCenter.getDescription())%>" size=50 maxlength=50></td>
		</tr>
<!--
		<tr>
			<td width=140px align=right  nowrap><a href="javascript: browseLookup('InvWorkCenter_departmentCode', 'department'); void(0);" tabindex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid,"Department", "Department")%></a>:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_departmentCode" value="<%=invWorkCenter.getDepartmentCode()%>" size=15 maxlength=15 ></td>
		</tr>
-->
		<tr>
			<td width=140px align=right nowrap>Subcontract:&nbsp;</td>
			<td>
			<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_outside.equals("Y")){%>CHECKED<%}%> tabindex=80 value="Y" onclick="setCheckbox(frm.InvWorkCenter_subcontract,0);setOutside();">
			<tsa:hidden name="InvWorkCenter_subcontract" value="<%=s_outside%>"/>
			</td>
		</tr>
		</table>

		<br>
		<hr width=550px align=center class=browseHR>
		<br>

<div id=notOutside style="display:block;visibility:visible;">
		<table border=0 cellpadding=0 cellspacing=0 width=550px align=center>
		<tr>
			<td align=left>Hourly Costing Rates</td>
			<td align=right>Labor:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_laborRate" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getLaborRate()%>" style="text-align:right"> </td>
			<td align=right>Setup:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_setupRate" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getSetupRate()%>" style="text-align:right"></td>
		</tr>
		<tr>
		    <td>&nbsp;</td>
			<td align=right>Fixed Overhead:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_fixOverHead" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getFixOverHead()%>" style="text-align:right"></td>
			<td align=right>Variable Overhead:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_varOverHead" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getVarOverHead()%>" style="text-align:right"></td>
		</tr>
		</table>

		<br>
		<hr width=550px align=center class=browseHR>
		<br>

		<table border=0 cellpadding=0 cellspacing=0 width=550px align=center>
		<tr>
			<td align=left>Prod Rate Defaults</td>
			<td align=right>Setup:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_setuphours" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getSetuphours()%>" style="text-align:right"> </td>
			<td align=right>Processes/Hour:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_processTime" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getProcessTime()%>" style="text-align:right"></td>
		</tr>
		<tr>
		    <td>&nbsp;</td>
			<td align=right>Process Items:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_processItems" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getProcessItems()%>" style="text-align:right"></td>
			<td align=right>&nbsp;</td>
			<td align=right>&nbsp;</td>
		</tr>
		</table>

		<br>
		<hr width=550px align=center class=browseHR>
		<br>

		<table border=0 cellpadding=0 cellspacing=0 width=550px align=center>
		<tr>
			<td align=left>Capacity / Scheduling</td>
			<td align=right>Hours Per Day:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_perDayHours" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getPerDayHours()%>" style="text-align:right"> </td>
			<td align=right>Hours Per Job:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_perJobHours" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getPerJobHours()%>" style="text-align:right"></td>
		</tr>
		<tr>
		    <td>&nbsp;</td>
			<td align=right>Buffer Days:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_bufferDays" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getBufferDays()%>" style="text-align:right"></td>
			<td align=right>&nbsp;</td>
			<td align=right>&nbsp;</td>
		</tr>
		</table>
</div>
<div id=outside style="display:block;visibility:visible;">
		<table border=0 cellpadding=0 cellspacing=0 width=550px align=center>
		<tr>
			<td align=left>Sub Contract Details</td>
			<td align=right><a href="javascript: browseLookup('InvWorkCenter_vendorId', 'supplier'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "vendorId", "Supplier Id")%>:</a>&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_vendorId" SIZE="15" MAXLENGTH="15" value="<%=HiltonUtility.ckNull(invWorkCenter.getVendorId())%>"  onchange="upperCase(this);lookupSupplier(this);"> </td>
		</tr>
		<tr>
			<td align=left>&nbsp;</td>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "vendorName", "Supplier Name")%>:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_vendorName" SIZE="40" MAXLENGTH="40" value="<%=HiltonUtility.ckNull(invWorkCenter.getVendorName())%>" > </td>
		</tr>
		<tr>
			<td align=left>&nbsp;</td>
			<td align=right>Cost:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_cost" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getCost()%>" style="text-align:right"></td>
		</tr>
		<tr>
			<td align=left>&nbsp;</td>
			<td align=right nowrap><a href="javascript: browseLookup('InvWorkCenter_unitOfMeasure', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "uom", "Unit Of Measure", true)%>:</a>&nbsp</td>
			<td colspan=2><input type="text" name="InvWorkCenter_unitOfMeasure" SIZE="15" MAXLENGTH="15" value="<%=HiltonUtility.ckNull(invWorkCenter.getUnitOfMeasure())%>" > </td>
		</tr>
		<tr>
			<td align=left>&nbsp;</td>
			<td align=right>Days to Next:&nbsp;</td>
			<td colspan=2><input type="text" name="InvWorkCenter_leadTime" SIZE="15" MAXLENGTH="15" value="<%=invWorkCenter.getLeadTime()%>" style="text-align:right"></td>
		</tr>
		</table>
</div>

		<br>
		<hr width=550px align=center class=browseHR>
		<br>

		<table border=0 cellpadding=1 cellspacing=0 width=475px height=75px align=center>
		<tr>
			<td width=100% valign=top>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
					<td>
						<select name="InvWorkCenter_status" onchange="setStatus();">
							<option value="01" <% if (invWorkCenter.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (invWorkCenter.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (invWorkCenter.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="InvWorkCenter_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invWorkCenter.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="InvWorkCenter_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invWorkCenter.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('InvWorkCenter_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('InvWorkCenter_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="InvWorkCenter_owner" tabindex=51 size=30 maxlength=15 value="<%=invWorkCenter.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
					</td>
				</tr>
				<tr>
					<td nowrap align=right>Owner Name:&nbsp;</td>
					<td>
						<input type=text name="as_ownerName" size=30 value="<%=owner.getDisplayName()%>" disabled>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
<%	if (! newInvWorkCenter) { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'InvWorkCenterUpdate;BrowseRetrieve'); void(0);">
<%	} else { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'InvWorkCenterAdd'); void(0);">
<%	} %>
		<img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a>
	</td>
	<td width=50% align=center><a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "invworkcenter-admin";
	setNavCookie("/admin/systemtables/invworkcenter.jsp", "InvWorkCenterRetrieveById", "WorkCenter Id <%=invWorkCenter.getWorkCenterId()%>");

	var status = "<%=invWorkCenter.getStatus()%>";
	setStatus();

	var outside = "<%=s_outside%>" ;

	if (outside == "Y") {
		hideArea("notOutside") ;
	} else {
	    hideArea("outside") ;
	}

	function setFieldFocus()
	{
<%	if (! newInvWorkCenter) { %>
			frm.InvWorkCenter_description.focus();
<%	} else { %>
			frm.InvWorkCenter_workCenterId.focus();
<%	} %>
	}

	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.InvWorkCenter_status[frm.InvWorkCenter_status.selectedIndex].value;

		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

	function setOutside() {
	   outside = frm.InvWorkCenter_subcontract.value ;
		if (outside == "N") {
			hideArea("outside") ;
			displayArea("notOutside") ;
		} else {
			displayArea("outside") ;
			hideArea("notOutside") ;
		}
	}

	function validateForm() {
	  var alertMessage = '';
      var handler = frm.handler.value;

      if (handler.indexOf("InvWorkCenterAdd") >= 0) {
	      if (isEmpty(frm.InvWorkCenter_workCenterId.value))
	        	alertMessage += 'Work Center Id is a required entry.\n';

	      if (alertMessage.length > 0) {
	            alert ('Please fix the following problems:\n\n' + alertMessage);
	            return false;
		  }
	  }

	  return true;
    }

	function lookupSupplier(objectId) {

        var idValue = objectId.value;
		var url = "<%=requestURLPath%>" + "/TableLookup?uid=${esapi:encodeForJavaScript(userId)}&oid=<%=oid%>&process=vendor-retrieve-by-id.xml&resultObj=vendor";
        url = url + "&Vendor_vendorId=" + escape(idValue) ;
		lookupRequest(url, lookupSupplierResponse) ;
	}

	function lookupSupplierResponse() {
		if (http.readyState == 4) {
			var status = http.status ;
			if (http.status == 200) {
                  // Or for XML formatted response text:
                  setResponseValue(http.responseXML, "VendorName", "InvWorkCenter_vendorName") ;
            } else {
//                  document.getElementById('BomComponent_description').value = "" ;
            }
		}
	}




// End Hide script -->
</SCRIPT>
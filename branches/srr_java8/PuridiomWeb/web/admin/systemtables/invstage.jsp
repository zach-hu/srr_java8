<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/ajaxlookup.js"></SCRIPT>

<%
	String s_outside = "N" ;

	InvStage invStage = (InvStage) request.getAttribute("invStage");
	boolean newInvStage = false;
	BigDecimal zero = new BigDecimal(0) ;

	if (invStage == null)
	{
		invStage = new InvStage();
		invStage.setStageId("") ;
		invStage.setDescription("") ;
		invStage.setWorkCenterId("") ;
		invStage.setMachineId("") ;
		invStage.setNotes("") ;
		invStage.setOutside("N") ;
		invStage.setVendorName("") ;
		invStage.setSetupHours(new BigDecimal(0)) ;
		invStage.setPartsHour(new BigDecimal(0)) ;
		invStage.setTimePart(new BigDecimal(0)) ;
		invStage.setLeadTime(new BigDecimal(0)) ;
		invStage.setCcost(new BigDecimal(0)) ;
		invStage.setPersons(new BigDecimal(0)) ;
		invStage.setQtyDays(new BigDecimal(0)) ;
		invStage.setDateEntered(d_today);
		invStage.setDateExpires(d_today);
		invStage.setOwner(uid);
		invStage.setStatus("02");
		newInvStage = true;
	}
	else
	{
	    String newInvStageString = HiltonUtility.ckNull((String) request.getAttribute("newInvStage"));
		if (newInvStageString.equals("Y"))
		{
	      newInvStage = true;
	    }
		s_outside = invStage.getOutside() ;
		if (s_outside == null) s_outside = "N" ;
		if (invStage.getSetupHours() == null) invStage.setSetupHours(new BigDecimal(0)) ;
		if (invStage.getPartsHour() == null) invStage.setPartsHour(new BigDecimal(0)) ;
		if (invStage.getTimePart() == null) invStage.setTimePart(new BigDecimal(0)) ;
		if (invStage.getLeadTime() == null) invStage.setLeadTime(new BigDecimal(0)) ;
		if (invStage.getCcost() == null) invStage.setCcost(new BigDecimal(0)) ;
		if (invStage.getPersons() == null) invStage.setPersons(new BigDecimal(0)) ;
		if (invStage.getQtyDays() == null) invStage.setQtyDays(new BigDecimal(0)) ;

	}

	UserProfile owner = UserManager.getInstance().getUser(oid, invStage.getOwner());
	String duplicateInvStageErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateInvStageErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateInvStageFailurePage" value="/admin/systemtables/invstage.jsp"/>
<tsa:hidden name="InvStage_outside" value="<%=s_outside%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Stage</div>
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

<%	if (!HiltonUtility.isEmpty(duplicateInvStageErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateInvStageErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=140px align=right nowrap height=18px>Routing Id:&nbsp;</td>
			<td><input type="text" name="InvStage_stageId" value="<%=invStage.getStageId()%>" size=25 maxlength=15 <% if (! newInvStage) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td nowrap align=right>
<%	if (! newInvStage) { %>
				<a href="javascript: if (verifyAction('Delete this Routing Id?')) { doSubmit('/browse/browse_sys_tables.jsp', 'InvStageDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Stage</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Description:&nbsp;</td>
			<td colspan=2><input type="text" name="InvStage_description" value="<%=invStage.getDescription()%>" size=50 maxlength=50></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap><a href="javascript: browseLookup('InvStage_workCenterId', 'invworkcenter'); void(0);" tabindex=-1>Work Center:&nbsp;</a></td>
			<td colspan=2><input type="text" name="InvStage_workCenterId" value="<%=invStage.getWorkCenterId()%>" size=15 maxlength=15 onchange="upperCase(this);lookupWC(this);"></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap><a href="javascript: browseLookup('InvStage_machineId', 'invmachine'); void(0);" tabindex=-1>Machine:&nbsp;</a></td>
			<td colspan=2><input type="text" name="InvStage_machineId" value="<%=invStage.getMachineId()%>" size=15 maxlength=15 ></td>
		</tr>
		</table>

		<br>
		<hr width=550px align=center class=browseHR>
		<br>
<div id=notOutside style="display:block;visibility:visible;">
		<table border=0 cellpadding=0 cellspacing=0 width=550px align=center>
		<tr>
			<td align=right>Setup Hours:&nbsp;</td>
			<td colspan=2><input type="text" name="InvStage_setupHours" SIZE="15" MAXLENGTH="15" value="<%=invStage.getSetupHours()%>" style="text-align:right"> </td>
			<td align=right>Processed / Hour:&nbsp;</td>
			<td colspan=2><input type="text" name="InvStage_partsHour" SIZE="15" MAXLENGTH="15" value="<%=invStage.getPartsHour()%>" style="text-align:right"></td>
		</tr>
		<tr>
			<td align=right>Items / Process:&nbsp;</td>
			<td colspan=2><input type="text" name="InvStage_timePart" SIZE="15" MAXLENGTH="15" value="<%=invStage.getTimePart()%>" style="text-align:right"></td>
			<td align=right># Persons:&nbsp;</td>
			<td colspan=2><input type="text" name="InvStage_persons" SIZE="15" MAXLENGTH="15" value="<%=invStage.getPersons()%>" style="text-align:right"></td>
		</tr>
		<tr>
			<td align=right>Days to Next:&nbsp;</td>
			<td colspan=2><input type="text" name="InvStage_qtyDays" SIZE="15" MAXLENGTH="15" value="<%=invStage.getQtyDays()%>" style="text-align:right"></td>
			<td align=right>&nbsp;</td>
			<td align=right>&nbsp;</td>
		</tr>
		</table>
</div>
<div id=outside style="display:block;visibility:visible;">
		<table border=0 cellpadding=0 cellspacing=0 width=550px align=center>
		<tr>
			<td align=right>Suppler:&nbsp;</td>
			<td colspan=2><input type="text" name="InvStage_vendorName" SIZE="40" MAXLENGTH="40" value="<%=invStage.getVendorName()%>"> </td>
		</tr>
		<tr>
			<td align=right>Cost:&nbsp;</td>
			<td colspan=2><input type="text" name="InvStage_ccost" SIZE="15" MAXLENGTH="15" value="<%=invStage.getCcost()%>" style="text-align:right"></td>
		</tr>
		<tr>
			<td align=right>Unit Of Measure:&nbsp;</td>
			<td colspan=2><input type="text" name="InvStage_unitOfMeasure" SIZE="15" MAXLENGTH="15" value="<%=invStage.getUnitOfMeasure()%>" > </td>
		</tr>
		<tr>
			<td align=right>Lead Time:&nbsp;</td>
			<td colspan=2><input type="text" name="InvStage_leadTime" SIZE="15" MAXLENGTH="15" value="<%=invStage.getLeadTime()%>" style="text-align:right"></td>
		</tr>
		</table>
</div>
		<br>

		<table border=0 cellpadding=0 cellspacing=0 width=550px align=center>
		<tr>
			<td width=140px align=right nowrap>Notes:&nbsp;</td>
			<td colspan=2>
				<textarea name="InvStage_notes" cols=60 rows=4 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this, 60);">${esapi:encodeForHTML(invStage.notes)}</textarea>
			</td>
		</tr>
		</table>

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
						<select name="InvStage_status" onchange="setStatus();">
							<option value="01" <% if (invStage.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (invStage.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (invStage.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="InvStage_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invStage.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="InvStage_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invStage.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('InvStage_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('InvStage_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="InvStage_owner" tabindex=51 size=30 maxlength=15 value="<%=invStage.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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
<%	if (! newInvStage) { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'InvStageUpdate;BrowseRetrieve'); void(0);">
<%	} else { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'InvStageAdd'); void(0);">
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
	frm.browseName.value = "invstage-admin";
	setNavCookie("/admin/systemtables/invstage.jsp", "InvStageRetrieveById", "Routing Id <%=invStage.getStageId()%>");

	// We create the XMLHTTPRequest Object
	var outside = "<%=s_outside%>" ;
	var status = "<%=invStage.getStatus()%>";
	setStatus();
	if (outside == "Y") {
		hideArea("notOutside") ;
	} else {
		hideArea("outside") ;
	}


	function setFieldFocus()
	{
<%	if (! newInvStage) { %>
			frm.InvStage_description.focus();
<%	} else { %>
			frm.InvStage_stageId.focus();
<%	} %>
	}

	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.InvStage_status[frm.InvStage_status.selectedIndex].value;

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
	   outside = frm.InvStage_outside.value ;
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

      if (handler.indexOf("InvStageAdd") >= 0) {
	      if (isEmpty(frm.InvStage_stageId.value))
	        	alertMessage += 'Routing Id is a required entry.\n';

	      if (alertMessage.length > 0) {
	            alert ('Please fix the following problems:\n\n' + alertMessage);
	            return false;
		  }
	  }

	  return true;
    }
	function lookupWCResponse() {
		if (http.readyState == 4) {
			var status = http.status ;
			if (http.status == 200) {
                  // Or for XML formatted response text:
                  setResponseValue(http.responseXML, "Subcontract", "InvStage_outside") ;
                  setOutside();
				  if (outside == "Y") {
	                  setResponseValue(http.responseXML, "VendorName", "InvStage_vendorName") ;
    	              setResponseValue(http.responseXML, "UnitOfMeasure", "InvStage_unitOfMeasure") ;
        	          setResponseValue(http.responseXML, "Cost", "InvStage_ccost") ;
            	      setResponseValue(http.responseXML, "LeadTime", "InvStage_leadTime") ;
        	      }

            } else {
//                  document.getElementById('BomComponent_description').value = "" ;
//                  alert ( "Routing Id not found! ");
            }
		}
	}

	function lookupWC(objectId) {

        var idValue = objectId.value ;
		var url = "<%=requestURLPath%>" + "/TableLookup?uid=${esapi:encodeForJavaScript(uid)}&oid=<%=oid%>&process=invworkcenter-retrieve-by.xml&resultObj=invWorkCenter";
        url = url + "&InvWorkCenter_workCenterId=" + escape(idValue)
        lookupRequest(url, lookupWCResponse) ;
	}




// End Hide script -->
</SCRIPT>
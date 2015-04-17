<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.alerts.Alert" %>
<%@ page import="java.util.*" %>
<%@ page import="com.tsa.puridiom.entity.*" %>


<%
String cualquiercosa = "cualquiervalor";
List alertsList1 = (List)request.getAttribute("alertList");
List alertsList = null;
List listAlertsDesc = null;
Map alertMap = null;
List listAlertsValue = null;
List listyAlertsOffset = null;

if (request.getAttribute("alertList")!=null)
{
	alertsList = (List)request.getAttribute("alertList");

	for(int i = 0; i < alertsList.size(); i++)
	{
		Alert alert = (Alert)alertsList.get(i);
		//out.println("Alert: " + alert.getName());
	}

}

if (request.getAttribute("processfromtype")!=null)
{
	alertMap = (HashMap) request.getAttribute("processfromtype");
	listAlertsDesc = (ArrayList)alertMap.get("nameAlertDescrip");
	listyAlertsOffset = (ArrayList)alertMap.get("alertsOffset");

}
//request.setAttribute("alertMap",alertMap);

 if (request.getAttribute("alertListValue")!=null)
    {

 	   listAlertsValue = (ArrayList)request.getAttribute("alertListValue");
    }

%>


<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<!-- inicio cabecera-->

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Alerts Setup</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "organization", "Organization")%> :</b></td>
			<td width=125px><%=oid%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user", "User")%>:</b></td>
			<td width=125px><%=user.getDisplayName()%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>


<!--  fin title-->


<br>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr><td align="center">
	<table border=1 cellpadding=2 cellspacing=0 width=650px>
	<tr bgcolor="gainsboro"><th align="left">Alerts </th></tr>
	<tr><td align="left" >
	<table border=0 cellspacing=0 width=650px>
	<tr ><td colspan="2">
	<table><tr>
	<td align="left" >
		<a href="javascript: enableAll(this); void(0);"> <b>Enable All</b></a> &nbsp;  /
	</td>

	<td align="left" >
		<a href="javascript: disableAll(this); void(0);"> <b>Disable All</b> </a>
	</td>
	</td></table></td>
	</tr>
	<tr bgcolor="gainsboro">
		<td align=left><b> Enabled  &nbsp; </b></td>
		<td align=left><b> Description </b></td>
		<td align=right><b> Parameters </b></td>

	</tr>

	 <tr>
	      <td  >
	           <%if (alertsList!=null){
						alertsList = (List)request.getAttribute("alertList");
					for (int r=0 ;r<alertsList.size();r++)
	    			{ Alert alertSelect = (Alert)alertsList.get(r);
	    			%>
					<tr bgcolor="#F0F0F0">
					 <td width=100 ><input align="left" type="checkbox" name="<%=alertSelect.getName()%>_chk" value="Y" checked /></td>
					 <tsa:hidden name="<%=alertSelect.getName()%>" value="<%=alertSelect.getName()%>"/>
					 <td nowrap align="left"><%=listAlertsDesc.get(r) %></td>
					 <td  align="right" >
						<table  ><tr>
						 <div id="showSchedule<%=r%>"  >
							<a href="javascript: scheduleModuleShowHide(1,<%=r%>,'<%=alertSelect.getName()%>'); void(0);"><img src="<%=contextPath%>/images/cmd_add_item.gif" alt="Show." valign="bottom" border="0"></a>
						</div>
						</tr> <br>
						</table>
					 <td>
					</tr>

					<tr>
					<td align="center" colspan="3" >
					<div id="ScheduleOptions<%=r%>" style="visibility:hidden; display:none;" >
					<table border=1 width=650px class=hdr12>
						<!--<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>-->
						<tr ><td>
							<table border=0 width=650px class=hdr12>
							<tr bgcolor="gainsboro">
							<td align=left  width=50%><b> Argument Name </b></td>
							<td align=left  width=20%><b> Operator </b></td>
							<td align=left width=20%><b> Offset </b></td>
							<td align="right"><a href="javascript: scheduleModuleShowHide(0,<%=r%>,'<%=alertSelect.getName()%>'); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" alt="Close." valign="bottom" border="0"></a></td>
							</tr>
							</table>
							</td>
						</tr>
						<tr><td align=center>
							<table border=1 width=650px class=hdr12>
							<tr>
							<%if(alertSelect.getName().equalsIgnoreCase("approval_reminder"))
								{%>
									<td align=left width=50%><b> Date Assigned </b></td>
								<%}
								else if( alertSelect.getName().equalsIgnoreCase("approval_escalation"))
								{%>
									<td align=left width=50%><b> Date Assigned </b></td>
								<%}
								else
								{%>
									<td align=left width=50%><b> Expires </b></td>
								<%} %>
							<td align=left width=20%>
								<tsa:hidden name="originalFilter" value="Y"/>
							    <select name="<%=alertSelect.getName()%>_operator" size=1>
								<option value="=">=</option>
								<%if(alertSelect.getName().equalsIgnoreCase("approval_reminder") || alertSelect.getName().equalsIgnoreCase("approval_escalation"))
								{%>
									<option value=">" selected="selected">></option>
								<%}
								else
								{%>
									<option value=">">></option>
								<%}%>
								<option value="<"><</option>
								<option value=">=">>=</option>
								<option value="<="><=</option>
								<option value="<>"><></option>
						    </select>&nbsp;
							</td>
							<td align=left> <input type="text" name="<%=alertSelect.getName()%>_offset" size="7" value="<%=listyAlertsOffset.get(r)%>" style="text-align:right">
								<a href="javascript: show_calendar('Alert_newDate', 'MM-dd-yyyy');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
							</td>

							<!--<td><input type="text" name="Alert_newDate"  maxlength="10" value=""></td>-->
        					</tr>



							</table>
							</td>
						</tr>

						<tr ><td><table class=hdr12><tr>
								<td ><a href="javascript: enableSendTo(this,'<%=alertSelect.getName()%>'); void(0);"> <b>Enable All</b></a>  /</td>
								<td ><a href="javascript: disableSento(this,'<%=alertSelect.getName()%>'); void(0);"> <b>Disable All</b> </a>	</td>
								<td width=20% > <input  align="left" type="checkbox" name="<%=alertSelect.getName()%>_buyer_chk" value="Y" checked />  <b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyer", "Buyer")%> </b> </td>
								<td  width=20%> <input  align="left" type="checkbox" name="<%=alertSelect.getName()%>_requisitioner_chk" value="Y" checked /> <b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioner", "Requisitioner")%> </b> </td>
								<td  width=20%> <input align="left" type="checkbox" name="<%=alertSelect.getName()%>_owner_chk" value="Y"  checked /> <b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%> </b>  </td>
								<%if(alertSelect.getName().equalsIgnoreCase("approval_reminder") || alertSelect.getName().equalsIgnoreCase("approval_escalation"))
								{%>
									<td  width=20%> <input align="left" type="checkbox" name="<%=alertSelect.getName()%>_owner_chk" value="Y"  checked /> <b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprover", "Approver")%></b>  </td>
								<%} %>
							</tr></table></td>
						</tr>

						<tr>
						<td colspan=2 align="center">
							<table border=0 cellspacing=0  class=hdr12>
								<tr>
									<td >&nbsp;</td>
								</tr>

								<tr>
									<td align="right"> <b> User: </b></td>
									<td nowrap><textarea name="<%=alertSelect.getName()%>_manual_sendTo" cols="100" rows="1" onChange="checkemail(this,'<%=alertSelect.getName()%>');"></textarea></td>
									<!-- <td nowrap><textarea name="manual_sendTo" cols="100" rows="1" onChange="checkemail(this,'<%//=alertSelect.getName()%>');"></textarea></td>-->
								</tr>
								<tr>
										<td align="center" colspan=3 nowrap>Separate Email Addresses with ';'</td>
								</tr>

							</table>
						</td>
						</tr>
						<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
					</table>
					</div>

					</td>
					</tr>
			 	<% }} %>
		 	</td>
	    <td>   <%if (listAlertsValue!=null)	{%>
			<select name="AlertListValue" selected size=1" onchange="selectEntity(entityName.value); void(0);">
	         <%	for (int r=0 ;r<listAlertsValue.size();r++){%>
	          <option value="<%=listAlertsValue.get(r)%>" selected><%=listAlertsValue.get(r)%></option>
			 <% }}%>
		    </select>&nbsp;
	    </td>
	    <td><%if (listAlertsValue!=null){%>
		    <input type="text" name="alertChange" size="5" value=" " style="text-align:right">
	    	<%}%>
	    </td>
	</tr>

<br><br>
	<tr >
	<table align="center">
		<tr> <br><br>
		<% if (role.getAccessRights("ALERTS") >= 3 ) { %>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: saveAlert(this); void(0);">Save</a></div></td>
		<% } %>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp','DoNothing'); void(0);">Return</a></div></td>
		</tr>
	</table>
	</tr>
	</table>
	</td></tr>
	</table>
</td></tr>
</table>



<%@ include file="/system/footer.jsp" %>


<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;


function saveAlert(textarea){

	setHiddens(textarea);
 	doSubmit('admin/alerts_configuration.jsp','AlertsSave;AlertsConfiguration');
}

function selectAlerts(alertNameSelected) {
	alert(alertNameSelected);
	 var newInputField = "<input type='hidden' name='alertNameSelected' value='" + alertNameSelected + "'>";
	 newInputField = newInputField +  "<input type='hidden' name='alertMap' value='<%=alertMap%>'>";
	setHiddenFields(newInputField);



	doSubmit('admin/alerts_configuration.jsp','AlertsGetListValue');
}



function scheduleModuleShowHide(flg,ind,nam){

	var alertchk = nam+"_chk";
	for (var i=0;i < frm.elements.length;i++)
	{
		var elemento = frm.elements[i];
		if( (elemento.name == alertchk) && (elemento.checked == true) )
		{
			switch (flg) {
			case 0:
				hideArea('ScheduleOptions'+ind); displayArea('showSchedule'+ind); break;
			case 1:
				displayArea('ScheduleOptions'+ind); hideArea('showSchedule'+ind); break;
			}
		}
		else if ((elemento.name == alertchk) && (elemento.checked == false)) {
		alert("Alerts Disabled");
		}
	}



}

function enableAll(chkbox){

 for (var i=0;i < frm.elements.length;i++)
	{
		var elemento = frm.elements[i];
		if ((elemento.type == "checkbox") && ((elemento.checked == false)))
		{
		elemento.checked = true ;
		}
	}
}

function disableAll(chkbox){
 for (var i=0;i < frm.elements.length;i++)
	{
		var elemento = frm.elements[i];
		if (elemento.type == "checkbox")
		{
		elemento.checked = false;
		}
	}
}

function enableSendTo(chkboxSendto,name){
for (var i=0;i < frm.elements.length;i++)
	{
		var elemento = frm.elements[i];
		if ((elemento.type == "checkbox") && ((elemento.name == name+"_buyer_chk") || (elemento.name == name+"_requisitioner_chk") || (elemento.name == name+"_owner_chk")))
		{
			elemento.checked = true;
		}
	}
}

function disableSento(chkboxSendto,name){
for (var i=0;i < frm.elements.length;i++)
	{
		var elemento = frm.elements[i];
		if ((elemento.type == "checkbox") && ((elemento.name == name+"_buyer_chk") || (elemento.name == name+"_requisitioner_chk") || (elemento.name == name+"_owner_chk")))
		{
			elemento.checked = false;
		}
	}

}


function checkemail(manualTxtArea,name){
var str="";
for (var i=0;i < frm.elements.length;i++)
	{
		var elemento = frm.elements[i];
		if((elemento.type == "textarea") && (elemento.name == name+"_manual_sendTo"))
		{
			 str = elemento.value;
			var emailArray = str.split(";");

			for (x=0; x < emailArray.length; x++)
				{
					if (checkOneEmail(emailArray[x]))
					{
						//return true;
					}
					else
					{
						frm.email.select();
						return false;
					}
				}


		}

	}


	return str;
}

function checkOneEmail(email)
	{
		var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i

		if (filter.test(email))
		{
			return true;
		}
		else
		{
			alert("Please input a valid email address!: " + email);
			return false;
		}
	}



function setHiddens(textarea){

	var myCell = document.getElementById("hiddenFields");
	var newInputField = null;

	for (var i=0;i < frm.elements.length;i++)
	{
		var elemento = frm.elements[i];
		if (elemento.type == "textarea")
		{
			newInputField =  newInputField + "<input type='hidden' name='manualSendTo' value='" + elemento.value + "'>";
		}
	}
	myCell.innerHTML = newInputField;
	//alert(newInputField);

   }




// End Hide script -->
</SCRIPT>
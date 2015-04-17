<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
List alertSet = (List) request.getAttribute("systemAlerts");
AlertConfig alertsConfig = (AlertConfig) alertSet.get(0);

String ec = "EC1";
String bc = "BC1";
String oc = "OC1";
String rc = "RC1";
String ac = "AC1";
String odt = "ODT1";
%>

<tsa:hidden name="systemAlerts" value="<%=alertSet%>"></tsa:hidden>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Email and Alerts Administration</div>
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

<%
try{
Object obj1 = request.getAttribute("ODTerror");
String error = obj1.toString();
if(error.equalsIgnoreCase("true"))
	out.print("<FONT COLOR=DB1010><span style=\"font-weight:bold\" style=\"font-size:10pt\"> *It has not saved. Review your input, make sure there are only numbers in the offset box.</FONT>");
else
	out.print("<span style=\"font-weight:bold\" style=\"font-size:10pt\"> You have successfully saved. Click Return to return to the Admin Menu.");
}
catch (Exception e){}
%>

<table width=<%=formEntryWidth%> border=0 cellspacing=0 cellpadding=2>

	<!--Headings-->
	<tr><td colspan=7 height=30px><b>System Alerts</b></td></tr>

	<tr><td width=6%>&nbsp;</td><td colspan=2 nowrap align=center><b>Enable</b></td><td align=center nowrap><b>Buyer</b></td><td align=center nowrap><b>Owner</b></td><td align=center nowrap><b>Req</b></td><td align=center nowrap><b>Approver</b></td><td align=center nowrap><b>Offset Days</b></td></tr>
	<!--Options-->
	<tr><td colspan=7 height=30px><b>Vendor Alerts</b></td></tr>

<% for (int i=0; i<alertSet.size(); i++) {
		alertsConfig = (AlertConfig) alertSet.get(i);

		if ("VEND".equalsIgnoreCase(alertsConfig.getAlertType())){
			ec = "EC" + (i + 1);
			bc = "BC" + (i + 1);
			oc = "OC" + (i + 1);
			rc = "RC" + (i + 1);
			ac = "AC" + (i + 1);
			odt = "ODT" + (i + 1);

			out.print("<tr><td align=left width=275px nowrap>" + alertsConfig.getDescription()+ "</td><td colspan=2 nowrap align=center><b>");

			out.print("<input type=checkbox name=\"" + ec + "\" ");
			if (alertsConfig.getEnabled().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + bc + "\" ");
			if (alertsConfig.getBuyer().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + oc + "\" ");
			if (alertsConfig.getOwner().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + rc + "\" ");
			if (alertsConfig.getReq().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + ac + "\" ");
			if (alertsConfig.getApprover().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=text name=\"" + odt + "\" ");
			if (alertsConfig.getOffsetDays() != null) out.print("value=" + alertsConfig.getOffsetDays());
			out.print("></b></td></tr>");
		}
	}%>
	<tr><td colspan=7><br></td></tr>

	<tr><td colspan=7 height=30px><b>Requisition/Approval Alerts</b></td></tr>

	<% for (int i=0; i<alertSet.size(); i++) {
		alertsConfig = (AlertConfig) alertSet.get(i);

		if ("REQ".equalsIgnoreCase(alertsConfig.getAlertType()) || "RAE".equalsIgnoreCase(alertsConfig.getAlertType()) || "VSQ".equalsIgnoreCase(alertsConfig.getAlertType())){
			ec = "EC" + (i + 1);
			bc = "BC" + (i + 1);
			oc = "OC" + (i + 1);
			rc = "RC" + (i + 1);
			ac = "AC" + (i + 1);
			odt = "ODT" + (i + 1);

			out.print("<tr><td align=left width=275px nowrap>" + alertsConfig.getDescription()+ "</td><td colspan=2 nowrap align=center><b>");

			out.print("<input type=checkbox name=\"" + ec + "\" ");
			if (alertsConfig.getEnabled().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + bc + "\" ");
			if (alertsConfig.getBuyer().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + oc + "\" ");
			if (alertsConfig.getOwner().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + rc + "\" ");
			if (alertsConfig.getReq().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + ac + "\" ");
			if (alertsConfig.getApprover().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=text name=\"" + odt + "\" ");
			if (alertsConfig.getOffsetDays() != null) out.print("value=" + alertsConfig.getOffsetDays());
			out.print("></b></td></tr>");
		}
	}%>
	<tr><td colspan=7><br></td></tr>

	<tr><td colspan=7 height=30px><b>Purchase Order Alerts</b></td></tr>

<% for (int i=0; i<alertSet.size(); i++) {
		alertsConfig = (AlertConfig) alertSet.get(i);

		if ("PO".equalsIgnoreCase(alertsConfig.getAlertType())){
			ec = "EC" + (i + 1);
			bc = "BC" + (i + 1);
			oc = "OC" + (i + 1);
			rc = "RC" + (i + 1);
			ac = "AC" + (i + 1);
			odt = "ODT" + (i + 1);

			out.print("<tr><td align=left width=275px nowrap>" + alertsConfig.getDescription()+ "</td><td colspan=2 nowrap align=center><b>");

			out.print("<input type=checkbox name=\"" + ec + "\" ");
			if (alertsConfig.getEnabled().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + bc + "\" ");
			if (alertsConfig.getBuyer().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + oc + "\" ");
			if (alertsConfig.getOwner().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + rc + "\" ");
			if (alertsConfig.getReq().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=checkbox name=\"" + ac + "\" ");
			if (alertsConfig.getApprover().equalsIgnoreCase("y")) out.print("checked");
			out.print("></b></td><td align=center nowrap><b>");

			out.print("<input type=text name=\"" + odt + "\" ");
			if (alertsConfig.getOffsetDays() != null) out.print("value=" + alertsConfig.getOffsetDays());
			out.print("></b></td></tr>");
		}
	}%>
</table>

<br>

<!-- Save and Cancel Buttons -->
<table width=<%=formEntryWidth%> border=0 cellspacing=0 cellpadding=2>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/system_alerts.jsp', 'SystemAlerts'); void(0);">Save</a></div></td>

			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/system_alerts.jsp", "DoNothing", "Email and Alerts Menu");

// End Hide script -->
</SCRIPT>
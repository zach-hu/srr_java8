<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String catalogId = request.getParameter("catalogId");
	String punchoutIdString = request.getParameter("punchoutId");
	Punchout punchout = new Punchout();

	boolean	exist	= false;
	Object obj = request.getAttribute("punchout");
	BigDecimal punchoutId = new BigDecimal (punchoutIdString);

	if (obj != null){
		exist = true;
		punchout = (Punchout) obj;
		punchoutId = punchout.getIcPunchout();
		}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Punchout Properties Administration</div>
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

<tsa:hidden name="catalogId" value="<%=catalogId%>"/>
<tsa:hidden name="punchoutId" value="<%=punchoutId%>"/>
<tsa:hidden name="catalog" value="<%=catalogId%>"/>

<%if (!exist){
	out.print("<span style=\"font-weight:bold\" style=\"font-size:10pt\"> Sorry, there are no punchout records for the " + catalogId + " catalog.");
%>
	<table width=<%=formEntryWidth%> border=0 cellspacing=0 cellpadding=2>
		<tr>
		<td>
			<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr><td colspan=4><br></td></tr>
			<td width=25% align=right><div id="pxbutton"><a href="javascript: addPunchout('<%=catalogId%>'); void(0);">Add</a></div></td>

<%	}
	else {%>
<tsa:hidden name="icPunchout" value="<%=punchout.getIcPunchout()%>"/>

<%
try{
Object obj1 = request.getAttribute("Typo");
String error = obj1.toString();
if(error.equals("true"))
	out.print("<FONT COLOR=DB1010><span style=\"font-weight:bold\" style=\"font-size:10pt\"> *It has not saved. Review your input, make sure that no field begins with a ';'.</FONT>");
else
	out.print("<span style=\"font-weight:bold\" style=\"font-size:10pt\"> You have successfully saved. Click Return to return to the Catalog Browse section.");
}
catch (Exception e){}
%>

<table width=<%=formEntryWidth%> border=0 cellspacing=0 cellpadding=2>

	<!--Headings-->
	<tr><td colspan=4 height=30px><b>Punchout Properties</b></td></tr>

	<tr><td align=right width=275px nowrap>From Domain: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FromD" <%if (punchout.getFromDomain().equals("(null)")) {} else out.print("value= \"" + punchout.getFromDomain() + "\"");%>></input></b></td>

	<td align=right width=275px nowrap>From Identity: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FromID" <%if (punchout.getFromIdentity().equals("(null)")) {} else out.print("value= \"" + punchout.getFromIdentity() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>To Domain: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_ToD" <%if (punchout.getToDomain().equals("(null)")) {} else out.print("value= \"" + punchout.getToDomain() + "\"");%>></b></td>

	<td align=right width=275px nowrap>To Identity: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_ToID" <%if (punchout.getToIdentity().equals("(null)")) {} else out.print("value= \"" + punchout.getToIdentity() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>Sender Domain: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_SenderD" <%if (punchout.getSenderDomain().equals("(null)")) {} else out.print("value= \"" + punchout.getSenderDomain() + "\"");%>></b></td>

	<td align=right width=275px nowrap>Sender Identity: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_SenderID" <%if (punchout.getSenderIdentity().equals("(null)")) {} else out.print("value= \"" + punchout.getSenderIdentity() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>Sender Shared Secret: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_SenderSecret" <%if (punchout.getSenderSecret().equals("(null)")) {} else out.print("value= \"" + punchout.getSenderSecret() + "\"");%>></b></td>

	<td align=right width=275px nowrap>Sender User Agent: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_SenderAgent" <%if (punchout.getSenderAgent().equals("(null)")) {} else out.print("value= \"" + punchout.getSenderAgent() + "\"");%>></b></td></tr>

	<tr><td colspan=4><br></td></tr>

	<tr><td align=right width=275px nowrap>Field 1: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD1" <%if (punchout.getFld1().equals("(null)")) {} else out.print("value= \"" + punchout.getFld1() + "\"");%>></b></td>

	<td align=right width=275px nowrap>Field 9: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD9" <%if (punchout.getFld9().equals("(null)")) {} else out.print("value= \"" + punchout.getFld9() + "\"");%>></b></td>

	<tr></tr><td align=right width=275px nowrap>Field 2: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD2" <%if (punchout.getFld2().equals("(null)")) {} else out.print("value= \"" + punchout.getFld2() + "\"");%>></b></td>

	<td align=right width=275px nowrap>Field 10: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD10" <%if (punchout.getFld10().equals("(null)")) {} else out.print("value= \"" + punchout.getFld10() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>Field 3: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD3" <%if (punchout.getFld3().equals("(null)")) {} else out.print("value= \"" + punchout.getFld3() + "\"");%>></b></td>

	<td align=right width=275px nowrap>Field 11: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD11" <%if (punchout.getFld11().equals("(null)")) {} else out.print("value= \"" + punchout.getFld11() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>Field 4: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD4" <%if (punchout.getFld4().equals("(null)")) {} else out.print("value= \"" + punchout.getFld4() + "\"");%>></b></td>

	<td align=right width=275px nowrap>Field 12: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD12" <%if (punchout.getFld12().equals("(null)")) {} else out.print("value= \"" + punchout.getFld12() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>Field 5: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD5" <%if (punchout.getFld5().equals("(null)")) {} else out.print("value= \"" + punchout.getFld5() + "\"");%>></b></td>

	<td align=right width=275px nowrap>Field 13: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD13" <%if (punchout.getFld13().equals("(null)")) {} else out.print("value= \"" + punchout.getFld13() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>Field 6: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD6" <%if (punchout.getFld6().equals("(null)")) {} else out.print("value= \"" + punchout.getFld6() + "\"");%>></b></td>

	<td align=right width=275px nowrap>Field 14: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD14" <%if (punchout.getFld14().equals("(null)")) {} else out.print("value= \"" + punchout.getFld14() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>Field 7: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD7" <%if (punchout.getFld7().equals("(null)")) {} else out.print("value= \"" + punchout.getFld7() + "\"");%>></b></td>

	<td align=right width=275px nowrap>Field 15: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD15" <%if (punchout.getFld15().equals("(null)")) {} else out.print("value= \"" + punchout.getFld15() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>Field 8: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_FLD8" <%if (punchout.getFld8().equals("(null)")) {} else out.print("value= \"" + punchout.getFld8() + "\"");%>></b></td></tr>

	<tr><td colspan=4><br></td></tr>

	<tr><td align=right width=275px nowrap>Supplier Part Auxiliary ID: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_Aux" <%if (punchout.getAuxiliary().equals("(null)")) {} else out.print("value= \"" + punchout.getAuxiliary() + "\"");%>></b></td>

	<td align=right width=275px nowrap>Default Date: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_DefaultDate" <%if (punchout.getDefaultDate().equals("(null)")) {} else out.print("value= \"" + punchout.getDefaultDate() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>Ship to: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=checkbox name="Punchout_ShipTo" <%if (punchout.getShipTo().equals("y")) out.print("checked");%>></b></td>

	<td align=right width=275px nowrap>Ship to Email: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_ShipToEmail" <%if (punchout.getShipToEmail().equals("(null)")) {} else out.print("value= \"" + punchout.getShipToEmail() + "\"");%>></b></td></tr>

	<tr><td align=right width=275px nowrap>General Information: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=checkbox name="Punchout_GeneralInfo" <%if (punchout.getGeneralInfo().equals("y")) out.print("checked");%>></b></td>

	<td align=right width=275px nowrap>Default Email: </td>
	<td colspan=2 nowrap align=center><b>
		<input type=text name="Punchout_DefaultEmail" <%if (punchout.getDefaultEmail().equals("(null)")) {} else out.print("value= \"" + punchout.getDefaultEmail() + "\"");%>></b></td></tr>

</table>

<table width=<%=formEntryWidth%> border=0 cellspacing=0 cellpadding=1>

	<tr><td align=right width=275px nowrap>URL: </td>
	<td colspan=2 nowrap align=left><b>
		<input type=text name="Punchout_URL" <%if (punchout.getUrl().equals("(null)")) {} else out.print("value= \"" + punchout.getUrl() + "\"");%> size=70 maxLength=150></b></td></tr>


</table>

<br>

<!-- Save and Cancel Buttons -->
<table width=<%=formEntryWidth%> border=0 cellspacing=0 cellpadding=2>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=right><div id="pxbutton"><a href="javascript: doSubmit('admin/catalog/punchout.jsp', 'PunchoutUpdate'); void(0);">Save</a></div></td>

			<% }//end else when exist is true %>

			<td width=50% align=center><div id="pxbutton"><a href="javascript: viewCatalog('<%=catalogId%>'); void(0);">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	function viewCatalog(catalog)
	{
	  var myCell = document.getElementById("hiddenFields");
	  myCell.innerHTML = "";
	  var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"Catalog_catalogId\" value=\"" + catalog + "\" ><INPUT TYPE=\"HIDDEN\" NAME=\"CatalogItem_catalogId\" value=\"" + catalog + "\" >";
	  myCell.innerHTML = newInputField;
	  var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"punchout\" value=\" \">";
	  doSubmit('/admin/catalog/catalog.jsp', 'CatalogRetrieveById;CatalogItemRetrieveCountByCatalog');
	}

	function addPunchout(catalog)
	{
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		var newInputField =  "<INPUT TYPE=\"HIDDEN\" NAME=\"Catalog_catalogId\" value=\"" + catalog + "\" ><INPUT TYPE=\"HIDDEN\" NAME=\"CatalogItem_catalogId\" value=\"" + catalog + "\" >";
		myCell.innerHTML = newInputField;
		doSubmit('admin/catalog/punchout.jsp', 'PunchoutAdd');
	}

	frm = document.purchaseform;
	setNavCookie("/admin/system_alerts.jsp", "DoNothing", "Email and Alerts Menu");

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
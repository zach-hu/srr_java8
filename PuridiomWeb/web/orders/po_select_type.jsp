<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></SCRIPT>
<%	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String needBidWaiver = HiltonUtility.ckNull((String)request.getAttribute("needBidWaiver"));
	String bidWaiverMsg = HiltonUtility.ckNull((String)request.getAttribute("bidWaiverMsg"));
	String canCreatePOFromNothing = propertiesManager.getProperty("PO DEFAULTS", "CREATEFROMNOTHING", "Y"); 
%>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="PoHeader_poType" value=""/>
<tsa:hidden name="displayCancel" value="TRUE"/>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="150px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tr>
			<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class="hdr12">Order Type Selection</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" align="right">
		<table border="0" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td width="100px">&nbsp;</td>
			<td align="right">&nbsp;<input type=radio name="as_view_type" value="CLASSIC"></td>
			<td width="100px" nowrap><b>Classic View</b></td>
			<td align="right"><input type=radio name="as_view_type" value="WIZARD" CHECKED></td>
			<td nowrap><b>Wizard View</b></td>
			<td width="10px">&nbsp;</td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr>
				<td width="1000px" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
			</tr>
			<tr>
				<td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
			</tr>
		</table>
	</td>
</tr>
</table>

<br>

<div id="bidWaiverMsg" style="display:none" class="error">
<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td align="center" class="error"><br><%=bidWaiverMsg%><br></td>
	</tr>
</table>
</div>
<br>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="680px" align="right">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
			
<%	if (canCreatePOFromNothing.equalsIgnoreCase("Y") && ((!oid.equalsIgnoreCase("akdoc") && !oid.equalsIgnoreCase("wpc08p"))|| user.isAnAdminBuyer())) {	%>
				<TD><INPUT TYPE="RADIO" NAME="r_from" value="N"  CHECKED></TD>
				<TD width="100px" CLASS="processOn">Nothing</TD>
<%	} else { %>
				<tsa:hidden name="r_from" value=""/>
<%	}	%>
				<TD><INPUT TYPE="RADIO" NAME="r_from" value="req" <% if ((!canCreatePOFromNothing.equalsIgnoreCase("Y")) || (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer())) { %>checked<% } %>></TD>
				<TD width="110px" CLASS="processOn">Requisition</TD>
				<TD><INPUT TYPE="RADIO" NAME="r_from" value="rfq" onclick="createOrder('')"></TD>
				<TD width="110px" CLASS="processOn">Solicitation</TD>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td width="680px" align="center">
	<TABLE BORDER="0" CELLSPACING="2" CELLPADDING="2" WIDTH="680px" border="0" ALIGN="CENTER">
<%	if (role.getAccessRights("POPO") > 2) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('PO'); void(0);" class="formType"><%=OrderType.toString(OrderType.PURCHASE_ORDER , oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "potxt", "Form used to purchase items from a supplier.", false)%></TD>
	</TR>
<%	}
		if (role.getAccessRights("POBO") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('BO'); void(0);" class="formType"><%=OrderType.toString(OrderType.BLANKET_ORDER, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "botxt", "", false)%></TD>
	</TR>
<%	}
	if (role.getAccessRights("PORO") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('RO'); void(0);" class="formType"><%=OrderType.toString(OrderType.RELEASE_ORDER, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rotxt", "", false)%></TD>
	</TR>
<%	}
	if (role.getAccessRights("PODO") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('DO'); void(0);" class="formType"><%=OrderType.toString(OrderType.DELIVERY_ORDER, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dotxt", "", false)%></TD>
	</TR>
<%	}
	if (role.getAccessRights("PODR") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('DR'); void(0);" class="formType"><%=OrderType.toString(OrderType.DELIVERY_RELEASE, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "drtxt", "", false)%></TD>
	</TR>
<%	}
	if (role.getAccessRights("POSO") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('SO'); void(0);" class="formType"><%=OrderType.toString(OrderType.SERVICE_ORDER, oid)%></A></TD>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sotxt", "", false)%></TD>
	</TR>
<%	}
	if (role.getAccessRights("POSB") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('SB'); void(0);" class="formType"><%=OrderType.toString(OrderType.SERVICE_BLANKET, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sbtxt", "", false)%></TD>
	</TR>
<%	}
	if (role.getAccessRights("POSR") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('SR'); void(0);" class="formType"><%=OrderType.toString(OrderType.SERVICE_RELEASE, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "srtxt", "", false)%></TD>
	</TR>
<%	}
	if (role.getAccessRights("POCT") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('CT'); void(0);" class="formType"><%=OrderType.toString(OrderType.CONTRACT, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cttxt", "", false)%></TD>
	</TR>
<%	}
	if (role.getAccessRights("POAK") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('AK'); void(0);" class="formType"><%=OrderType.toString(OrderType.ALASKA_PURCHASE_REQUISITION, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "aktxt", "", false)%></TD>
	</TR>
<%	}
	if (role.getAccessRights("POPR") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class="formType">
			<A HREF="javascript: createOrder('PR'); void(0);" class="formType"><%=OrderType.toString(OrderType.PURCHASE_RELEASE, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "prtxt", "", false)%></TD>
	</TR>
<%	}		%>
	</TABLE>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var userId = '${esapi:encodeForJavaScript(userId)}';
<%	String	s_assigned_only = propertiesManager.getProperty("ASSIGNMENT", "AssignedOnly", "N");
	String	s_assign_by_line = propertiesManager.getProperty("ASSIGNMENT", "AssignByLine", "N");
	String	s_now = HiltonUtility.getFormattedDate(d_today, oid, userDateFormat);
%>
	setNavCookie("/orders/po_select_type.jsp", "DoNothing", "Create New Order", true);

	<%if(needBidWaiver.equalsIgnoreCase("true"))
	{%>
		displayArea('bidWaiverMsg');
	<%}%>

	function createOrder(type)
	{
		setViewType();
		frm.PoHeader_poType.value = type;
		var today = '<%=s_now%>';

		var newInputField;

		if (frm.r_from[1].checked)
		{
 <%			if (s_assigned_only.equals("Y")) {
 				if (s_assign_by_line.equalsIgnoreCase("Y")) {%>
 					browse('po-req-assigned-only');
 <%				}
 				else {%>
 					browse('po-req-assigned-only-hdr');
 <%				}
 			}
 			else
	 		{ %>
					browse('po_reqbrowse');
<%			} %>
			if(type == "PR")
			{
				browse('po_poprbrowse');
			}
		}
		else if (frm.r_from[2].checked)
		{
<%		if (s_assigned_only.equals("Y")) { %>
				newInputField = "<input type=\"hidden\" name=\"filter_txt\" value=\"" + userId + "\">";
				newInputField = newInputField + "<input type=\"hidden\" name=\"colname\" value=\"RfqHeader_owner\">";
				newInputField = newInputField + "<input type=\"hidden\" name=\"originalFilter\" value=\"Y\"><input type=\"hidden\" name=\"logicalOperator\"  value=\"AND\"><input type=\"hidden\" name=\"sort\"  value=\"ASC\"><input type=\"hidden\" name=\"operator\"  value=\"=\">";
				setHiddenFields(newInputField);
<%		} %>

			 if (type == "SR")
			 {
			 	alert("Cannot create a Service Release from a Solicitation!");
			 }
			 else if (type == "DR")
			 {
			 	alert("Cannot create a Delivery Release from a Solicitation!");
			 }
			 else
			 {
				browse('po_rfqbrowse');
			}
		}
		else if (type == "RO" || type == "SR" || type == "DR" || type == "PR")
		{
			if (type == "RO")
			{
				setOriginalFilter("PoHeader_poType", "=", "BO");
			}
			else if (type == "SR")
			{
				setOriginalFilter("PoHeader_poType", "=", "SB");
			}
			else if (type == "DR")
			{
				setOriginalFilter("PoHeader_poType", "=", "DO");
			}
			setOriginalFilter("PoHeader_effectiveDate", "<=", today);
			setOriginalFilter("PoHeader_expirationDate", ">=", today);

			browse('po_blanketbrowse');
		}
		else
		{
			//doSubmit('/browse/item_filter_options.jsp', 'PoCreate;CatalogRetrieve');
			doSubmit('/orders/po_create.jsp', 'DoNothing');
		}
	}

	function setViewType()
	{
		var viewType = "";
		var types = frm.elements.item("as_view_type");

		for (var i = 0; i < types.length; i++)
		{
			if (frm.as_view_type[i].checked) {
				viewType = frm.as_view_type[i].value
			}
		}

		if (viewType == "CLASSIC")
		{
			frm.viewType.value = "CLASSIC";
		}
		else if (viewType == "WIZARD")
		{
			frm.viewType.value = "WIZARD";
		}
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>

<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></SCRIPT>

<%	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);	%>

<tsa:hidden name="formtype" value="RFQ"/>
<tsa:hidden name="RfqHeader_rfqType" value=""/>
<tsa:hidden name="displayCancel" value="TRUE"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Solicitation Type Selection</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<table border=0 cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td width="100px">&nbsp;</td>
			<td align="right">&nbsp;<input type=radio name="as_view_type" value="CLASSIC"></td>
			<td width="100px" nowrap><b>Classic View</b></td>
			<td align="right"><input type=radio name="as_view_type" value="WIZARD" CHECKED></td>
			<td nowrap><b>Wizard View</b></td>
			<td width="10px">&nbsp;</td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr>
				<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
			</tr>
			<tr>
				<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
			</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=right>
		<table border=0 cellspacing=0 cellpadding=0>
			<tr>
<%	if (!oid.equalsIgnoreCase("akdoc") || user.isAnAdminBuyer()) {	%>
				<TD><INPUT TYPE="RADIO" NAME="r_from" value="N"  CHECKED></TD>
				<TD width=80px CLASS="processOn">Nothing</TD>
<%	} else { %>
				<tsa:hidden name="r_from" value=""/>
<%	}	%>
				<TD><INPUT TYPE="RADIO" NAME="r_from" value="req" <% if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer()) { %>checked<% } %>></TD>
				<TD width=130px CLASS="processOn">Requisition</TD>
				<TD width=170px colspan=2>&nbsp;</TD>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td width=680px align=center>
	<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" WIDTH="680px" border="0" ALIGN="CENTER">
<%	if (role.getAccessRights("RFQRQ") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class=formType>
			<A HREF="javascript: createSolicitation('RQ'); void(0);" class=formType><%=RfqType.toString(RfqType.REQUEST_FOR_QUOTATION, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfqtxt-rq", "Request specific pricing and delivery information on listed products or services.", false)%></TD>
	</TR>
<%	}
		if (role.getAccessRights("RFQRI") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class=formType>
			<A HREF="javascript: createSolicitation('RI'); void(0);" class=formType><%=RfqType.toString(RfqType.REQUEST_FOR_INFORMATION, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfqtxt-ri", "Request information from potential vendors about their products and services.", false)%></TD>
	</TR>
<%	}
		if (role.getAccessRights("RFQRP") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class=formType>
			<A HREF="javascript: createSolicitation('RP'); void(0);" class=formType><%=RfqType.toString(RfqType.REQUEST_FOR_PROPOSAL, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfqtxt-rp", "Request a proposal for terms, conditions and other elements of an agreement to deliver specified services.", false)%></TD>
	</TR>
<%	}
		if (role.getAccessRights("RFQIB") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class=formType>
			<A HREF="javascript: createSolicitation('IB'); void(0);" class=formType><%=RfqType.toString(RfqType.INVITATION_TO_BID, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfqtxt-ib", "Request only pricing for clearly defined items or services.  No negotiations required.", false)%></TD>
	</TR>
<%	}
		if (role.getAccessRights("RFQQC") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class=formType>
			<A HREF="javascript: createSolicitation('QC'); void(0);" class=formType><%=RfqType.toString(RfqType.QUOTE_FOR_CONSTRUCTION, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfqtxt-qc", "Request for quotation (for construction).", false)%></TD>
	</TR>
<%	}
		if (role.getAccessRights("RFQIQ") > 0) {	%>
	<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class=formType>
			<A HREF="javascript: createSolicitation('IQ'); void(0);" class=formType><%=RfqType.toString(RfqType.INVITATION_FOR_QUOTE, oid)%></A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfqtxt-iq", "Invitation For Quote", false)%></TD>
	</TR>
<%	}	%>
	</TABLE>
	</td>
</tr>
</table>


<%@ include file="/system/footer.jsp" %>

<script language="JavaScript1.2">
<!-- HIDE SCRIPT

	frm = document.purchaseform;
	var userId = '${esapi:encodeForJavaScript(userId)}';
<%String	s_assigned_only = propertiesManager.getProperty("MISC", "AssignedOnly", "N");%>
	setNavCookie("/rfq/rfq_select_type.jsp", "DoNothing", "Create New Solicitation", true);

	function createSolicitation(type)
	{
		setViewType();
		frm.RfqHeader_rfqType.value = type;

		if (frm.r_from[1].checked)
		{
<%		if (s_assigned_only.equals("Y"))
 		{ %>
 				browse('rfq-req-assigned-only');
<%		}
 		else
	 	{
	 		if (oid.equalsIgnoreCase("QRI06P"))
	 		{ %>
				browseFilter('rfq_createfrom_requisitionheader');
<%			}
	 		else
	 		{ %>
				browse('rfq_createfrom_requisitionheader');
<%			}
	 	} %>
		}
		else
		{
			//doSubmit('rfq/rfq_items.jsp', 'RfqCreate');
			doSubmit('/rfq/rfq_create.jsp', 'DoNothing');
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

//-->
</script>

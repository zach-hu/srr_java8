<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.po.tasks.PoErrors" %>
<%@ page import="com.tsa.puridiom.poheader.tasks.UserErrors" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.util.*" %>

<%
    PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	String rfqAction = HiltonUtility.ckNull((String) request.getAttribute("rfqaction"));
	String s_rfq_status = rfqHeader.getStatus();
	boolean forwardOptionAvailable = true;

	String fromEmail = (String)request.getAttribute("fromEmail");
	if (fromEmail == null) {
		fromEmail = "N";
	}
	Map approvalNotes = new HashMap();
	String backgroundClass = "basic";
%>
<br/>
<table width=<%=formEntryWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center><font class=hdr12><%=rfqHeader.getDisplayRfqNumber().toString()%></font>&nbsp;
		<%	if (s_rfq_status.equals(DocumentStatus.RFQ_APPROVING)) {
			String s_forwardedto = HiltonUtility.ckNull((String) request.getAttribute("forwardedTo"));%>
			has been forwarded to <%=UserManager.getInstance().getUser(oid, s_forwardedto).getDisplayName()%>!
		<%	} else if (rfqAction.equalsIgnoreCase("REJECT")) { %>
			has been Rejected!
		<%	} else { %>
			has been awarded!
		<%	} %>
	</td>
</tr>
<tr>
	<td>&nbsp;</td>
</tr>
</table>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align="center">
		<%@include file="/rfq/rfq_approval_routing_list_amt.jsp" %>
	</td>
</tr>
</table>

<%	if (fromEmail.equalsIgnoreCase("N")) { %>
<table width="<%=formEntryWidth%>" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td>
		<table width="400px" align="center">
		<tr>
			<td align="center"><a href="javascript: doSubmit('/rfq/rfq_select_type.jsp', 'DoNothing'); void(0);"><p><img src="<%=contextPath%>/images/add.gif" border="0" alt="Create New Solicitation"></p>Create New Solicitation</a></td>
			<td align="center"><a href="javascript: doSubmit('/rfq/rfq_print_pdf.jsp', 'DoNothing'); void(0);"><p><img src="<%=contextPath%>/images/print.gif" border="0" alt="Print Solicitation"></p>Print Solicitation</a></td>
			<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
				<td align="center"><a href="javascript: doSubmit('/rfq/rfq_review.jsp', 'RfqRetrieve'); void(0);"><p><img src="<%=contextPath%>/images/returnto.gif" border="0" alt="Return To Solicitation"></p>Return To Solicitation</a></td>
			<%	} else { %>
				<td align="center"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><p><img src="<%=contextPath%>/images/returnto.gif" border="0" alt="Return To Solicitation"></p>Return To Solicitation</a></td>
			<%	} %>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	hideArea("navTable");

// End Hide script -->
</SCRIPT>
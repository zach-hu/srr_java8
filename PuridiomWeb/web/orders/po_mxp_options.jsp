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
<%@ page import="com.tsa.puridiom.asset.checks.AssetChecks" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.util.*" %>

<%
    PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	BigDecimal bd_ic_po_header = poHeader.getIcPoHeader();
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	String	 s_po_number = poHeader.getPoNumber();
	String	 s_po_type = poHeader.getPoType();
	String	 s_po_status = poHeader.getStatus();
	boolean forwardOptionAvailable = true;

	if (s_po_type == null)
	{
		s_po_type = "Purchase Order ";
	}
	else
	{
		s_po_type = OrderType.toString(s_po_type, oid);
	}
	String emailAction = (String)request.getAttribute("emailAction");
	if(emailAction == null){		emailAction = "NONE";		}
	String fromApproval = (String)request.getAttribute("fromApproval");
	  if(fromApproval == null){		fromApproval = "N";		}

	UserErrors errors = (UserErrors)request.getAttribute("userErrors");
	List listErrors = null;
	if(errors == null)
	{
		 listErrors = new ArrayList();
	}
	else
	{
		listErrors = errors.getErrors();
	}
	String classType = "summary";

	String fromEmail = (String)request.getAttribute("fromEmail");
	if(fromEmail == null)
	{
		 fromEmail = "N";
	}
%>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center><font class=hdr12><%=poHeader.getDisplayPoNumber().toString()%></font>&nbsp;
		has been sent to MXP!
	</td>
</tr>
</table>
<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td width="100px">&nbsp;</td>
	<td>
		<table width="400px" align="center">
		<tr>
			<td align="center"><a href="javascript: doSubmit('/orders/po_select_type.jsp', 'DoNothing'); void(0);"><p><img src="<%=contextPath%>/images/add.gif" border="0" alt="Create New Order"></p>Create New Order</a></td>
			<td align="center"><a href="javascript: doSubmit('/orders/po_print_pdf.jsp', 'DoNothing'); void(0);"><p><img src="<%=contextPath%>/images/print.gif" border="0" alt="Print Order"></p>Print Order</a></td>
			<td align="center"><a href="javascript: doSubmit('/orders/po_review.jsp', 'PoRetrieve'); void(0);"><p><img src="<%=contextPath%>/images/returnto.gif" border="0" alt="Return To Order"></p>Return To Order</a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>

<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_displayNumber" value="<%=poHeader.getDisplayPoNumber().toString()%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_vendorId" value="<%=poHeader.getVendorId()%>"/>
<tsa:hidden name="PoHeader_vendorName" value="<%=poHeader.getVendorName()%>"/>
<tsa:hidden name="PoHeader_vendContactCode" value="<%=poHeader.getVendContactCode()%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=poHeader.getReleaseNumber()%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=poHeader.getRevisionNumber()%>"/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

// End Hide script -->
</SCRIPT>
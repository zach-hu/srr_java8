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
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	String poAction = HiltonUtility.ckNull((String) request.getAttribute("poaction"));
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

	if(listErrors.size() > 0)
	{%>
        <tsa:hidden name="PoLine_icPoHeader" value="<%=bd_ic_po_header%>"/>
        <table border="0" cellpadding="0" cellspacing="0" width=<%=formWidth%>>
        <tr><td><br></td></tr>
        <tr>
        	<td width="100%">
        		<table cellpadding="0" cellspacing="0" border="0" width="100%">
        		<tr>
        			<td valign="top" width="50px" height="30px">
        				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
        				<tr>
        					<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
        				</tr>
        				<tr>
        					<td nowrap class="hdr12" valign="middle">
        						<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=OrderType.toString(poHeader.getPoType(), oid)%> </div>
        					</td>
        				</tr>
        				</table>
        			</td>
        			<td valign="bottom" align="left" width=3px><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
        			<td valign="bottom" align="right" height="30px" width=*>
        				<table border=0 cellspacing=0 cellpadding=1 width=100%>
        <%	int i_colspan = 1;%>
        		<tr>
        			<td nowrap align=right><b>Order #:</b></td>
        			<td width=100px><%=s_po_number%></td>
        <%	if (bd_release_number.compareTo(bd_zero) > 0)
        		{
        			i_colspan = i_colspan + 2; %>
        			<td nowrap align=right><b>Release #:</b></td>
        			<td width=100px><%=bd_release_number%></td>
        <%	}
        		if (bd_revision_number.compareTo(bd_zero) > 0)
        		{
        			i_colspan = i_colspan + 2; %>
        			<td nowrap align=right><b>Revision #:</b></td>
        			<td width=100px><%=bd_revision_number%></td>
        <%	} %>
        		</tr>
        		<tr>
        			<td colspan=<%=i_colspan%> nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
        			<td width=100px><%=DocumentStatus.toString(s_po_status, oid)%></td>
        		</tr>
        		</table>
        				<table cellpadding="0" cellspacing="0" border="0" width="100%">
        				<tr>
        					<td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
        				</tr>
        				<tr>
        					<td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
        				</tr>
        				</table>
        			</td>
        		</tr>
        		</table>
        	</td>
        </tr>
        <tr><td><br></td></tr>
        <tr>
        	<td width="100%" align="center" valign="top">
        		<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 455px; height: 100px; align:center; overflow-y:visible; overflow-x:auto;">
        			<table border=0 cellpadding=0 cellspacing=0 width="445px">
                    <%	for (int i = 0; i < listErrors.size(); i++ ) {
                    			if (errors.getErrorSeverity(i) == PoErrors.CRITICAL) {
                    				forwardOptionAvailable = false;
                    			}
                    %>
                    		<tr class="<%=classType%>" height="20px">
        						<td class="<%=classType%>" width="15px">&nbsp;</td>
                    			<td nowrap class="<%=classType%>" align="left"><%=errors.getErrorText(i)%></td>
                    		</tr>
                    		<%if(classType.equalsIgnoreCase(""))
                        		{
                    				classType = "summary";
                        		}
                    			else if(classType.equalsIgnoreCase("summary"))
                    			{
                    				classType = "";
                    			}
                    	}%>
                   	</table>
        		</div>
        	</td>
        </tr>
        </table>
        <br>
	<% if (forwardOptionAvailable) {%>
        <table border=0 cellpadding=0 cellspacing=0 width=655px>
        <tr><td align=center><img src='<%=contextPath%>/images/alert.gif' valign=middle border=0>&nbsp;<b>Do you still want to forward this Purchase Order?</b></td></tr>
        </table>

        <br>

        <table border=0 cellpadding=0 cellspacing=0 width=655px>
       	<tr>
			<td align=center><a href="javascript: orderForward(); void(0);"><img class="button" src="<%=contextPath%>/images/button_forward.gif" border=0></a>&nbsp;</td>
			<td align=center>
				<%if (s_view.equalsIgnoreCase("WIZARD")) {%>
					<a href="javascript: doSubmit('/orders/po_review.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a>
				<%} else {%>
					<a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a>
				<%}%>
			</td>
		</tr>
		</table>
	<% } else {%>
        <table border=0 cellpadding=0 cellspacing=0 width=655px>
       	<tr>
			<td align=center>
				<%if (s_view.equalsIgnoreCase("WIZARD")) {%>
					<a href="javascript: doSubmit('/orders/po_review.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a>
				<%} else {%>
					<a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a>
				<%}%>
			</td>
		</tr>
		</table>
	<%	}%>
		<br>

        <tsa:hidden name="poForwardOption" value=""/>
        <tsa:hidden name="goforward" value=""/>
<%}
else
{%>
<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center><font class=hdr12><%=poHeader.getDisplayPoNumber().toString()%></font>&nbsp;
		<%if (s_po_status.equals(DocumentStatus.PO_APPROVING) || s_po_status.equals(DocumentStatus.CT_APPROVING))
		{
			String s_forwardedto = HiltonUtility.ckNull((String) request.getAttribute("forwardedTo"));%>
			has been forwarded to <%=UserManager.getInstance().getUser(oid, s_forwardedto).getDisplayName()%>!
		<%}
		else if (poAction.equalsIgnoreCase("REJECT"))
		{%>
			has been Rejected!
		<%}
		else
		{%>
			has been awarded!
		<%}%>
	</td>
</tr>
<tr>
	<td>
		<%if (fromApproval.equalsIgnoreCase("Y"))
		{%>
			<%@ include file="/orders/po_waiting_approval_others.jsp" %>
		<%}%>
	</td>
</tr>
</table>
<%if (fromEmail.equalsIgnoreCase("N"))
{%>
<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td width="100px">&nbsp;</td>
	<td>
		<table width="400px" align="center">
		<tr>
			<td align="center"><a href="javascript: doSubmit('/orders/po_select_type.jsp', 'DoNothing'); void(0);"><p><img src="<%=contextPath%>/images/add.gif" border="0" alt="Create New Order"></p>Create New Order</a></td>
			<td align="center"><a href="javascript: doSubmit('/orders/po_print_pdf.jsp', 'DoNothing'); void(0);"><p><img src="<%=contextPath%>/images/print.gif" border="0" alt="Print Order"></p>Print Order</a></td>
			<%if (s_view.equalsIgnoreCase("WIZARD"))
			{%>
				<td align="center"><a href="javascript: doSubmit('/orders/po_review.jsp', 'PoRetrieve'); void(0);"><p><img src="<%=contextPath%>/images/returnto.gif" border="0" alt="Return To Order"></p>Return To Order</a></td>
			<%}
			else
			{%>
				<td align="center"><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><p><img src="<%=contextPath%>/images/returnto.gif" border="0" alt="Return To Order"></p>Return To Order</a></td>
			<%}%>
		</tr>
		</table>
	</td>
</tr>
</table>
<%}
}%>
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

	function orderForward()
	{
			frm.goforward.value = "Y";
<%		if (propertiesManager.getProperty("MODULES", "PO APPROVALS", "N").equalsIgnoreCase("Y") &&
		(!poHeader.getPoType().equals("CT") || propertiesManager.getProperty("PO APPROVALS", "CONTRACTAPPROVALS", "Y").equalsIgnoreCase("Y")))
			{ %>
				doSubmitToPopup('/orders/po_forward_to.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
<%		}
			else
			{ %>
				frm.poForwardOption.value = "F";	/*  award Order*/
				doSubmit('/orders/po_forward.jsp', 'PoForward', 'WIDTH=350', 'HEIGHT=165');
<%		} %>
	}

	function viewOrder() {
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
		doSubmit('/orders/po_summary.jsp', 'PoRetrieve');
<%	} else {%>
		doSubmit('/orders/po_review.jsp', 'PoRetrieve');
<%	}%>
	}

	function retrieveOrderApproval(ic)
  	{
		frm.PoHeader_icPoHeader.value = ic;
		var newInputField = "<input type='hidden' name='emailAction' value='<%=emailAction%>'>";
		newInputField = newInputField +"<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
		setHiddenFields(newInputField);
		doSubmit('/orders/po_approval.jsp', 'PoApprovalRetrieve');
  }

// End Hide script -->
</SCRIPT>
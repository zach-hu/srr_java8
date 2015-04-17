<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="java.text.NumberFormat" %>
<%@ include file="/system/header.jsp" %>
<%
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	String	confirmationMsg = (String) request.getAttribute("confirmationMsg");
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="formType" value="IVH"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=135px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice Extract Confirmation</div>
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
  <td valign=bottom align=middle width=*>
    <table border=0 cellspacing=0 cellpadding=1 width=100%>
    <tr>
      <td align=right><b>Invoice #:</b></td>
      <td width=125px><%=invoiceHeader.getInvoiceNumber()%></td>
    </tr>
    <tr>
      <td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
      <td width=125px><%=DocumentStatus.toString(invoiceHeader.getStatus())%></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
	</table>
  </td>
</tr>
</table>

<br><br>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr><td align=center><b><%=confirmationMsg%></b></td></tr>
<tr><td><br></td></tr>
<tr><td align=center>Click on the return button below to return to your invoice.</td></tr>
<tr><td><br></td></tr>
<tr><td align=center><div id="pxbutton"><a href="javascript: returnMe(); void(0);">Return</a></div></td></tr>
</table>

<br><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	hideArea("navTable");
	displayArea("menubarSpacer");

	function returnMe() {
		doSubmit('invoice/iv_review.jsp', 'InvoiceRetrieve');
	}

// End Hide script -->
</SCRIPT>
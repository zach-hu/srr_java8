<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceVendor_vendorId" value="<%=invoiceHeader.getVendorId()%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="formtype" value="IVC"/>
<tsa:hidden name="formType" value="IVH"/>


<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=135px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice Message Error</div>
      </td>
    </tr>
    </table>
  </td>
  <td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign=bottom align=right height=30px>
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
    <table cellpadding="0" cellspacing="0" border=0>
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
<br>
<%
	List errorMessageList = (List) request.getAttribute("errorMessageList");
%>
	<table>
	<% if(errorMessageList!=null && errorMessageList.size()>0){
			for(int i=0; i<errorMessageList.size(); i++) {
			%>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td align="right"><img src="<%=contextPath%>/images/bullet.gif" border="0">&nbsp;&nbsp;&nbsp;</TD>
				<td class=error align=left><%=errorMessageList.get(i) %></td>
			</tr>
	<%		}
		} %>
	</table>
	<br>
	<br>
	<br>

<table width=655px cellpadding=0 cellspacing=0 border=0 valign=bottom>
<tr>
	<td align=center>
		<a href="javascript: returnMe(); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Invoice"></a>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();


	function returnMe() {
		doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve');
  	}

// end hiding contents -->
</SCRIPT>
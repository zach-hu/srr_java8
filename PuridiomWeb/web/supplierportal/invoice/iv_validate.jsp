<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRule" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRules" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/dynamicTables.js"></SCRIPT>
<%
	ValidationRules rules = (ValidationRules) request.getAttribute("rules");
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("header");
	String invoiceAction = (String) request.getAttribute("invoiceaction");
	boolean bForward = false;

	if (HiltonUtility.isEmpty(invoiceAction)) { invoiceAction = "VALIDATE";	}
%>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceVendor_vendorId" value="<%=invoiceHeader.getVendorId()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="invoiceaction" value="<%=invoiceAction%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=135px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice Validation Results</div>
      </td>
    </tr>
    </table>
  </td>
  <td valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
  <td valign=bottom align=right height=30px>
    <table border=0 cellspacing=0 cellpadding=1 width=100%>
    <tr>
      <td align=right><b>Invoice #:</b></td>
      <td width=125px><%=invoiceHeader.getInvoiceNumber()%></td>
    </tr>
    <tr>
      <td align=right><b>Status:</b></td>
      <td width=125px><%=DocumentStatus.toString(invoiceHeader.getStatus())%></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border=0>
    <tr>
      <td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
    </tr>
    <tr>
      <td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td width="100%" align="center" valign="top">
    <div id="validationrules" style="display:none;">
<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 455px; height: 100px; align:center; overflow-y:visible; overflow-x:auto;">
<table border="0" cellpadding="0" cellspacing="0" width="445px">
	<tr class="mnav" height="18px">
		<td nowrap class="mnav" align="center">&nbsp;</td>
		<td width="15px" class="mnav">&nbsp;</td>
		<td nowrap class="mnav" align="center">Message</td>
		<td width="15px">&nbsp;</td>
		<!--td nowrap class="mnav" align="center" >Status</td>
		<td width="15px" class="mnav">&nbsp;</td-->
		<td nowrap class="mnav" align="center">Severity</td>
		<td width="15px">&nbsp;</td>
	</tr>
<%	List rulesList = rules.getRules();
		String classType = "summary";
		String severity = "";
		int counter = 1;
		for (int i = 0; i < rulesList.size(); i++)
		{
			ValidationRule vRule = (ValidationRule)rulesList.get(i);
			if (vRule.isResult())
			{
				//we dont need to display if it passes
				continue;
			}
			if (vRule.getSeverity().equalsIgnoreCase("E"))
			{
				severity = "Error";
			}
			else if (vRule.getSeverity().equalsIgnoreCase("W"))
			{
				severity = "Warning";
			}
%>
			<tr class="<%=classType%>" height="20px">
				<td nowrap class="<%=classType%>" align="right" valign="top"><%=counter%>.</td>
				<td class="<%=classType%>" width="15px">&nbsp;</td>
				<td class="<%=classType%>" align="left" valign="top"><%=vRule.getMessage()%></td>
				<td class="<%=classType%>" width="15px">&nbsp;</td>
				<!--td class="<%=classType%>" align="center" ><img src="<%=contextPath%>/supplierportal/images/delete.gif" border="0" height="10" width="10"></td>
				<td class="<%=classType%>" width="15px">&nbsp;</td-->
				<td class="<%=classType%>" nowrap align="center" valign="top"><%=severity%></td>
				<td class="<%=classType%>" width="15px">&nbsp;</td>
			</tr>
		<%if(classType.equalsIgnoreCase("browseRow"))
    		{
				classType = "summary";
    		}
			else if(classType.equalsIgnoreCase("summary"))
			{
				classType = "browseRow";
			}
			counter++;
		} %>
	<tr ><td nowrap colspan="7"><hr class=browseHR></td></tr>
	<tr height="20px">
		<td nowrap colspan="7" align="right">Validation Result&nbsp;:&nbsp;<b>
				<%if(rules.getResult() == 1)
			{
			 out.print("Passed");
			}
			else if (rules.getResult() == 0)
			{
			 out.print("Passed With Errors");
			}
			else if (rules.getResult() == -1)
			{
			 out.print("Failed");
			}%>!</b>
		</td>
	</tr>
</table>
</div>
</div>
  </td>
</tr>
</table>

<br>
<br>

<table width=655px cellpadding=0 cellspacing=0 border=0 valign=bottom>
<tr>
<%	if ( rules.getResult() != 1 ) {
			if (rules.getResult() > -1 && invoiceAction.equalsIgnoreCase("FORWARD")) { %>
	<td align=center><a href="javascript: invoiceForward(); void(0);"><img class="button" src="<%=contextPath%>/supplierportal/images/button_forward.gif" border=0 alt="Forward"></a></TD>
	<td align=center><a href="javascript: returnMe(); void(0);"><img class="button" src="<%=contextPath%>/supplierportal/images/button_return.gif" border=0 alt="Return"></a></td>
<%		}
		} %>
</tr>
<tr>
	<td align=center>
<%	if (rules.getResult() == 1 && invoiceAction.equalsIgnoreCase("FORWARD")) { %>
		<div id="novalidationrules" style="display:none;">
			<table align=center>
			<tr>
				<td valign=middle><img src="<%=contextPath%>/supplierportal/images/alert.gif" valign=middle border=0></td>
				<td valign=middle class="basic"><b>Please wait while we validate your invoice.</b></td>
			 </tr>
			</table>
		</div>
<%	}
		if (rules.getResult() == -1 || invoiceAction.equalsIgnoreCase("VALIDATE")) {%>
		<a href="javascript: returnMe(); void(0);"><img class="button" src="<%=contextPath%>/supplierportal/images/button_return.gif" border=0 alt="Return to Invoice"></a>
<%	}%>
	</td>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();
<%	if (rules.getResult() == 1 && invoiceAction.equalsIgnoreCase("FORWARD")) { %>
	displayArea('novalidationrules');
	invoiceForward();
<%	} else {%>
	displayArea('validationrules');
<%	}%>

	function checkHiddenMenu() {
		//hideArea("navTable");
		//displayArea("menubarSpacer");
	}

	function invoiceForward() {
		doSubmit('/invoice/iv_routing_list.jsp', 'InvoiceGetRoutingList');
	}

	function returnMe() {
	doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve');
  }

	function thisUnLoadPopup() {
		opener.showForwardButton();
	}

// end hiding contents -->
</SCRIPT>
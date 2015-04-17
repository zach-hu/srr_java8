<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRule" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRules" %>
<%@ page import="java.math.BigDecimal" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<%
	ValidationRules rules = (ValidationRules) request.getAttribute("rules");
	Vendor vendor = (Vendor) request.getAttribute("vendor");

	String fromSave =  HiltonUtility.ckNull((String) request.getAttribute("fromSave"));

	String vendor_buyer = HiltonUtility.ckNull((String) request.getAttribute("Vendor_buyer"));
	String vendorId = HiltonUtility.ckNull((String) request.getAttribute("Vendor_vendorId"));
	String vendorName = HiltonUtility.ckNull((String) request.getAttribute("Vendor_vendorName"));
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>

<tsa:hidden name="Vendor_validated" value="<%=vendor.getValidated()%>"/>

<%@ include file="/admin/supplier/supplier_hidden_fields.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Supplier Validation Results</div>
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

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td width="100%" align="center" valign="top">
    <%@ include file="/base/validation_rules.jsp" %>
  </td>
</tr>
</table>

<br>
<br>

<table width=655px cellpadding=0 cellspacing=0 border=0 valign=bottom>
<tr>
	<td align=center>
<%	if (rules.getResult() == 1) { %>
		<div id="novalidationrules" style="display:none;">
			<table align=center>
			<tr>
				<td valign=middle><img src="<%=contextPath%>/images/alert.gif" valign=middle border=0></td>
				<td valign=middle class="basic"><b>The supplier information has passed validation.  Please wait while we update the validation status.</b></td>
			 </tr>
			</table>
		</div>
<%	}
	if (rules.getResult() == 0) {%>
		<table align=center>
			<tr>
				<td valign=middle><div id="pxbutton"><a tabindex=50 href="javascript: updateValidationStatus('Y'); void(0);">Continue</a></div></td>
				<td valign=middle class="basic"><div id="pxbutton"><a tabindex=50 href="javascript: returnToVendorInformation(); void(0);">Return</a></div></td>
			 </tr>
		</table>
<%	}
	if (rules.getResult() == -1) {%>
		<div id="pxbutton"><a tabindex=50 href="javascript: returnToVendorInformation(); void(0);">Return</a></div>
<%	}%>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

<%	if (rules.getResult() == 1) { %>

	displayArea('novalidationrules');
	updateValidationStatus('Y');

<%	} else if (fromSave.equalsIgnoreCase("Y")) { %>

	displayArea('novalidationrules');
	updateValidationStatus('N');

<%
} else {%>

	displayArea('validationrules');

<%	}%>

	function updateValidationStatus(validated) {
		frm.Vendor_validated.value = validated;

		<%	if ((vendor.getStatus()).indexOf("05") >= 0) { %>
		if (validated == 'Y') {
			setHiddenFields('<input type="hidden" name="Vendor_status" value="02"/>');
		}
		<%	}%>

		doSubmit('/admin/supplier/supplier_info.jsp', 'VendorUpdate;VendorRetrieveById');
	}

	function returnToVendorInformation() {
		doSubmit('/admin/supplier/supplier_info.jsp', 'VendorRetrieveById');
	}

// end hiding contents -->
</SCRIPT>
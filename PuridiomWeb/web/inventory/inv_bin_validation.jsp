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
	String fromSave =  HiltonUtility.ckNull((String) request.getAttribute("fromSave"));
	String icRc =  HiltonUtility.ckNull((String) request.getAttribute("InvBinLocation_icRc"));
	String itemNumber =  HiltonUtility.ckNull((String) request.getAttribute("InvItem_itemNumber"));
	String binItemNumber =  HiltonUtility.ckNull((String) request.getAttribute("InvBinLocation_itemNumber"));
	String itemLocation =  HiltonUtility.ckNull((String) request.getAttribute("InvBinLocation_itemLocation"));
	String s_inv_item_number = (String) request.getAttribute("InvLocation_itemNumber");
	String s_inv_item_location = (String) request.getAttribute("InvLocation_itemLocation");
	String s_umCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_unitOfOrder"));
	String s_duomUmCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_duomUmCode"));
	String dualUmRequired = HiltonUtility.ckNull((String) request.getAttribute("dualUmRequired")) ;
	String serNoRequired = HiltonUtility.ckNull((String) request.getAttribute("serNoRequired")) ;
	
    String s_itemDate = HiltonUtility.ckNull((String)request.getAttribute("InvBinLocation_itemDate"));
    String s_aisle = HiltonUtility.ckNull((String)request.getAttribute("InvBinLocation_aisle"));
    String s_locrow = HiltonUtility.ckNull((String)request.getAttribute("InvBinLocation_locrow"));
    String s_tier = HiltonUtility.ckNull((String)request.getAttribute("InvBinLocation_tier"));
    String s_bin = HiltonUtility.ckNull((String)request.getAttribute("InvBinLocation_bin"));
    String s_lot = HiltonUtility.ckNull((String)request.getAttribute("InvBinLocation_lot"));
    String s_udf2Code = HiltonUtility.ckNull((String)request.getAttribute("InvBinLocation_udf2Code"));
	
	String icReqHeader = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_icReqHeader")) ;
%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>

<tsa:hidden name="InvBinLocation_icRc" value="<%=icRc%>"/>
<tsa:hidden name="InvItem_itemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="InvBinLocation_itemNumber" value="<%=binItemNumber%>"/>
<tsa:hidden name="InvBinLocation_itemLocation" value="<%=itemLocation%>"/>
<tsa:hidden name="InvBinLocation_itemDate" value="<%=s_itemDate%>"/>
<tsa:hidden name="InvBinLocation_aisle" value="<%=s_aisle%>"/>
<tsa:hidden name="InvBinLocation_locrow" value="<%=s_locrow%>"/>
<tsa:hidden name="InvBinLocation_tier" value="<%=s_tier%>"/>
<tsa:hidden name="InvBinLocation_bin" value="<%=s_bin%>"/>
<tsa:hidden name="InvBinLocation_lot" value="<%=s_lot%>"/>
<tsa:hidden name="InvBinLocation_udf2Code" value="<%=s_udf2Code%>"/>
<tsa:hidden name="InvLocation_itemNumber" value="<%=s_inv_item_number%>"/>
<tsa:hidden name="InvLocation_itemLocation" value="<%=s_inv_item_location%>"/>
<tsa:hidden name="InvItem_unitOfOrder" value="<%=HiltonUtility.ckNull(s_umCode)%>"/>
<tsa:hidden name="InvItem_duomUmCode" value="<%=HiltonUtility.ckNull(s_duomUmCode)%>"/>
<tsa:hidden name="dualUmRequired" value="<%=dualUmRequired%>"/>
<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>
<tsa:hidden name="binAction" value="UPDATE"/>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=icReqHeader%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inv Bin Validation Results</div>
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
				<td valign=middle class="basic"><b>The Inv Bin information has passed validation.</b></td>
			 </tr>
			</table>
			
		</div>
		
<%	}%>
	

	<div id="pxbutton"><a tabindex=50 href="javascript: returnToInvBinInformation(); void(0);">Return</a></div>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	
<%	if (rules.getResult() == 1) { %>
	displayArea('novalidationrules');
	<%	if (fromSave.equalsIgnoreCase("Y")) { %>
	doSubmit('/inventory/inv_bin_locations.jsp', 'InvBinLocationAddKitLocation;InvBinLocationRetrieveByItem');
	<%	} %>
<%	} else { %>
	displayArea('validationrules');
<%	} %>

	function returnToInvBinInformation() {
		doSubmit('/inventory/inv_bin.jsp', 'InvBinLocationRetrieveById;InvItemRetrieveById');
	}

// end hiding contents -->
</SCRIPT>
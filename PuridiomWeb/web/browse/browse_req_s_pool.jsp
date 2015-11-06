<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>

<%
	String	formField = (String) request.getAttribute("formField");
	String	fromPage = (String) request.getAttribute("fromPage");
	String	row = (String) request.getAttribute("index");
	String	addUserRule = (String) request.getAttribute("addUserRule");
	String	fyiApprover = (String) request.getAttribute("fyiApprover");
	String addUserAmount = (String) request.getAttribute("addUserAmount");
	if (formField == null)	{	formField = "";	}
	if (fromPage == null)	{	fromPage = "";	}
	if (row == null)			{	row = "0";			}
	if (addUserRule == null)	{	addUserRule = "";	}
	if (fyiApprover == null)	{	fyiApprover = "N";	}
	if (addUserAmount == null)	{	addUserAmount = "0.00";	}
%>

<%@ include file="/browse/browse_form.jsp" %>

<input type=hidden name=browsePage value="/browse/browse_req_s_pool.jsp">
<input type=hidden name=browseName value="">
<input type=hidden name=formField value="<%=formField%>">
<input type=hidden name=ApprovalLog_icHeader value="">
<input type=hidden name=addUser value="">
<input type=hidden name=addUserRule value="<%=addUserRule%>">
<input type=hidden name=fyiApprover value="<%=fyiApprover%>">
<input type=hidden name=addUserAmount value="<%=addUserAmount%>">
<input type=hidden name=index value="<%=row%>">
<input type=hidden name=fromPage value="<%=fromPage%>">

<table width=680px border=0>
<tr>
	<td id="addDistributionListId" width=50% align=center>
		<div id="pxbutton"><a href="javascript: addDistributionList(); void(0);">Select</a></div>
	</td>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');">Close</a></div>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function thisLoadPopup()
	{

		var radios = document.all("c_radio");

		if (radios == null)
		{
			document.getElementById("addDistributionListId").style.visibility = "hidden";
			document.getElementById("addDistributionListId").style.display    = "none";
		}
		//f_StartIt();
	}

	function addDistributionList()
	{
		var poolId = "";
		var poolDesc = "";
		var radios = document.getElementsByName("c_radio");
		if (radios != null)
		{
			if (radios.length > 1)
			{
				for (var i=0; i<radios.length; i++)
				{
					if (radios[i].checked)
					{
						poolId = radios[i].value;
						poolDesc = document.getElementsByName("AppPool_pooldesc")[i].value;
						break;
					}
				}
			}
			else
			{
				if (radios.checked)
				{
					poolId = radios.value;
					poolDesc = frm.AppPool_pooldesc.value;
				}
			}

			if (poolId == "")
			{
				alert("You must selected a Distribution List!");
			}
			else
			{
				window.parent.frm.AppPooluser_poolid.value = poolId;

				window.parent.frm.addUser.value = poolId;
				window.parent.frm.addUserRule.value = "[Distribution List = " + poolDesc + "]";
				window.parent.frm.fyiApprover.value = frm.fyiApprover.value;
				window.parent.frm.addUserAmount.value = "0.00";
				if (frm.fyiApprover.value == "Y") {
					window.parent.frm.insertBefore.value = "-1";
				}
				frm.browseName.value="app-pooluser-distribution";				
				setOriginalFilter("AppPooluser_id_poolid", "=", poolId);
				doSubmit('browse/browse_req_pool.jsp', 'BrowseRetrieve');
				
			}
		}
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_req_pool.jsp', 'BrowseRetrieve');
	}

// End Hide script -->
</SCRIPT>
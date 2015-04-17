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

<input type=hidden name=browsePage value="/browse/browse_req_pool.jsp">
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
		<div id="pxbutton"><a href="javascript: addDistributionList(); void(0);">Add</a></div>
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

	var buildArraysForAdd = false;

	function thisLoadPopup()
	{
		var radios = document.all("c_checkbox");
		hideArea("AppPooluser_id_userId");
		if (radios == null)
		{
			document.getElementById("addDistributionListId").style.visibility = "hidden";
			document.getElementById("addDistributionListId").style.display    = "none";
		}

		if (window.parent.usersArray && window.parent.poolsArray) {
			var checkbox = document.all("c_checkbox");
			if (checkbox != null) {
				var ckboxElements = frm.elements.c_checkbox;
				var icAppPoolUser = frm.elements.AppPooluser_id_userId;

				if (ckboxElements.length > 1) {
					for (var i=0; i<ckboxElements.length; i++) {
						var index = arrayIndexOf(window.parent.usersArray, icAppPoolUser[i].value);
						if (index != -1) {
							ckboxElements[i].checked = true;
						}
					}
				} else {
					var index = arrayIndexOf(window.parent.usersArray, icAppPoolUser.value);
					if (index != -1) {
						ckboxElements.checked = true;
					}
				}
			}
		}

		//f_StartIt();
	}

	function addDistributionList()
	{
		var poolId = "";
		var poolDesc = "";
		var checkbox = document.all("c_checkbox");
		var ckboxElements = frm.elements.c_checkbox;
		var icAppPool = frm.elements.AppPool_poolid;
		var poolDec = frm.elements.AppPool_pooldesc;
		var icAppPoolUser = frm.elements.AppPooluser_id_userId;
		var cbchecked = 0;
		var recordSelected = false;
		var bw = browserCheck();

		var removeFromLine = false;

		buildArrays();
		buildArraysForAdd = true;

		if (ckboxElements.length > 1) {
			for (var i=0; i < ckboxElements.length; i++) {
				if (ckboxElements[i].checked)
				{
					recordSelected = true;
					break;
				}
			}
			if (recordSelected) {
				for (var i=ckboxElements.length - 1; i >= 0 ; i--) {
					if (!ckboxElements[i].checked)
					{
						if (icAppPool != undefined && icAppPool[i]) {
							if(bw == 'NS6')
								icAppPool[i].removeChild(icAppPool[i].childNodes[0]);
							else
								icAppPool[i].removeNode(true);
						}
						if (icAppPoolUser != undefined && icAppPoolUser[i]) {
							if(bw == 'NS6')
								icAppPoolUser[i].removeChild(icAppPoolUser[i].childNodes[0]);
							else
								icAppPoolUser[i].removeNode(true);
						}
						if (poolDec != undefined && poolDec[i]) {
							if(bw == 'NS6')
								poolDec[i].removeChild(poolDec[i].childNodes[0]);
							else
								poolDec[i].removeNode(true);
						}
					}
				}
			}
		} else {
			var ckbox = ckboxElements;
			if (ckbox.checked) {
				recordSelected = true;
			}
		}

		var icAppPoolNew = frm.elements.AppPool_poolid;
		var icAppPoolUserNew = frm.elements.AppPooluser_id_userId;
		var poolDescNew = frm.elements.AppPooluser_id_userId;
		window.parent.frm.AppPooluser_poolid.value = poolId;
		window.parent.frm.addUser.value = poolId;
		var textPoolDesc = " ";
		var i = 0;
		var textArray = new Array();

		if (poolDec.length > 0) {
			while(poolDec.length > i )
			{
				if(textPoolDesc.indexOf(poolDec[i].value) < 0)
				textPoolDesc = textPoolDesc +poolDec[i].value;
				i++;
			}
		} else {
			textPoolDesc = poolDec.value;
		}

		window.parent.frm.addUserRule.value = "[Distribution List = " + textPoolDesc + "]";
		window.parent.frm.fyiApprover.value = frm.fyiApprover.value;
		window.parent.frm.addUserAmount.value = "0.00";
		if (frm.fyiApprover.value == "Y") {
			window.parent.frm.insertBefore.value = "-1";
		}

		var i = 0;
		var newInputField = "";
		/*if (icAppPoolUserNew.length)
		{
			while (icAppPoolUserNew.length > i )
			{
				newInputField = newInputField + "<input type='hidden' name='AppPooluser_userId' value='" + icAppPoolUserNew[i].value + "'>";
				i++;
			}
			i = 0;
			while (icAppPoolNew.length > i )
			{
				newInputField = newInputField + "<input type='hidden' name='AppPooluser_poolid' value='" + icAppPoolNew[i].value + "'>";
				i++;
			}
		} else if (icAppPoolUserNew.value.length)
		{
			newInputField = newInputField + "<input type='hidden' name='AppPooluser_userId' value='" + icAppPoolUserNew.value + "'>";
			i++;
			newInputField = newInputField + "<input type='hidden' name='AppPooluser_poolid' value='" + icAppPoolNew.value + "'>";
			i++;
		}*/

		var isSelectedAtLeastOne = false;

		if (window.parent.usersArray && window.parent.poolsArray) {
			for (i=0; i<window.parent.usersArray.length; i++) {
				newInputField = newInputField + "<input type='hidden' name='AppPooluser_userId' value='" + window.parent.usersArray[i] + "'>";
				newInputField = newInputField + "<input type='hidden' name='AppPooluser_poolid' value='" + window.parent.poolsArray[i] + "'>";
				isSelectedAtLeastOne = true;
			}
		}

		newInputField = newInputField + "<input type='hidden' name='reqAppPoolSelected' value='Y'>";

		if (isSelectedAtLeastOne) {
			window.parent.setHiddenFields(newInputField);
			window.parent.doSubmit('/requests/req_routinglist_no_popup.jsp', 'AppPooluserApprovalLogAdd', 'width=505px', 'height=450px');
			doSubmit('/system/close_window.jsp', 'BrowseCleanupByBrowseId');
		} else {
			alert("You must select at least an user from the distribution list.");
		}
	}

	function array_unique(inputArr) {
	    var key = '',        tmp_arr2 = {},
	        val = '';

	    var __array_search = function (needle, haystack) {
	        var fkey = '';        for (fkey in haystack) {
	            if (haystack.hasOwnProperty(fkey)) {
	                if ((haystack[fkey] + '') === (needle + '')) {
	                    return fkey;
	                }            }
	        }
	        return false;
	    };
	     for (key in inputArr) {
	        if (inputArr.hasOwnProperty(key)) {
	            val = inputArr[key];
	            if (false === __array_search(val, tmp_arr2)) {
	                tmp_arr2[key] = val;            }
	        }
	    }

	    return tmp_arr2;}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_req_pool.jsp', 'BrowseRetrieve');
	}

	function validateForm() {
		if (!buildArraysForAdd) {
			buildArrays();
		}
		return true;
	}

	function buildArrays() {
		if (window.parent.usersArray && window.parent.poolsArray) {

			var checkbox = document.all("c_checkbox");
			var ckboxElements = frm.elements.c_checkbox;
			var icAppPoolUser = frm.elements.AppPooluser_id_userId;
			var icAppPoolId = frm.elements.AppPool_poolid;

			if (ckboxElements.length > 1) {
				for (var i=0; i<ckboxElements.length; i++) {
					var index = arrayIndexOf(window.parent.usersArray, icAppPoolUser[i].value);
					if (ckboxElements[i].checked) {
						if (index == -1) {
							window.parent.usersArray.push(icAppPoolUser[i].value);
							window.parent.poolsArray.push(icAppPoolId[i].value);
						}
					} else {
						if (index != -1) {
							window.parent.usersArray.splice(i, 1);
							window.parent.poolsArray.splice(i, 1);
						}
					}
				}
			} else {
				var index = arrayIndexOf(window.parent.usersArray, icAppPoolUser.value);
				if (ckboxElements.checked) {
					if (index == -1) {
						window.parent.usersArray.push(icAppPoolUser.value);
						window.parent.poolsArray.push(icAppPoolId.value);
					}
				} else {
					if (index != -1) {
						window.parent.usersArray.splice(i, 1);
						window.parent.poolsArray.splice(i, 1);
					}
				}
			}
		}
	}

// End Hide script -->
</SCRIPT>
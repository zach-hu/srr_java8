<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>

<%
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	List approvers = (List) request.getAttribute("routingList");
	String reqApprover = HiltonUtility.ckNull((String) request.getAttribute("reqApprover"));
	String invoiceAction = (String)request.getAttribute("invoiceaction");
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceType" value="<%=invoiceHeader.getInvoiceType()%>"/>
<tsa:hidden name="ApprovalLog_approvers" value=""/>
<tsa:hidden name="invoiceaction" value="<%=invoiceAction%>"/>
<tsa:hidden name="RequisitionApprover" value="<%=reqApprover%>"/>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
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
							<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "forwardoptions", "Forward Options", false)%></div>
						</td>
					</tr>
				</table>
			</td>
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width="100%">
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
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td align="center">
		<font class="formType">Invoice </font><font class="hdr12">#<%=invoiceHeader.getInvoiceNumber()%></font>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="100%" align="center">

	</td>
</tr>
<tr>
	<td width="100%" align="center">
		<div id="approverList" class="browseHdrDk" style="border:0px; background: #FFFFFF; padding: 2px; width: 575px; height: 150px; align:center; overflow-y:auto; overflow-x:auto; ">
			<table border="0" cellspacing="0" cellpadding="1" width="500px">
				<tr>
					<td width="20%">
						<table align="center" width="75px">
							<tr height="50px">
								<td width="100%" align="center"><a href="javascript: moveUp(document.getElementById('approvers')); void(0);"><img class="processOnImg" src="<%=contextPath%>/images/up_arrows.gif" border="0" alt="Move Up"></a></td>
							</tr>
							<tr height="50px">
								<td width="100%" align="center"><a href="javascript: moveDown(document.getElementById('approvers')); void(0);"><img class="processOnImg" src="<%=contextPath%>/images/down_arrows.gif" border="0" alt="Move Down"></a></td>
							</tr>
						</table>
					</td>
					<td width="60%">
						<table width="100%" cellpadding="0" cellspacing="0" height="18px">
							<tr height="18px">
								<td class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approverlist", "Approver List", false)%></td>
							</tr>
							<tr>
								<td>
									<select size="10" multiple="multiple" name="approvers" style="width: 400px">
<%						String s_approvers = "";
							for (int i = 0; i < approvers.size(); i++) {
								String approverObj[] = (String[]) approvers.get(i);
								String approverId = (String) approverObj[0];
								String approverType = (String) approverObj[1];
								String approverName = (String) approverObj[2];

								UserProfile approver = UserManager.getInstance().getUser(oid, approverId);
								if (HiltonUtility.isEmpty(approverName))
								{
									approverName = approver.getDisplayName();
								}

								if (approverId.equalsIgnoreCase("#Requisitioner")) {
									approverName = "No Requisitioner was found - please select one.";
								}
								if (approverId.equalsIgnoreCase("#Approver")) {
									approverName = "No Requisition Approver was found - please select one.";
								}

%>
										<option value="<%=approverId%>" id="<%=approverType%>"><%=approverName%></option>
<%						} %>
									</select>
								</td>
							</tr>
						</table>
					</td>
					<td width="20%">
						<table align="center">
							<tr>
								<td><a href="javascript: addUser(); void(0);"><img class="button" src="<%=contextPath%>/images/add_user_black.gif" border="0"></a></td>
							</tr>
							<tr>
								<td><a href="javascript: removeUser(document.getElementById('approvers')); void(0);"><img class="button" src="<%=contextPath%>/images/remove_black.gif" border="0"></a></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
	</tr>

</table>
		</div>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td id="buttonForward">
		<table cellpadding="0" cellspacing="0" width="680px">
		<tr>
			<td width="50%" align="center"><div id="pxbutton"><a href="javascript: forwardInvoice(); void(0);">Ok</a></div></td>
			<td width="50%" align="center"><div id="pxbutton"><a href="javascript: returnToInvoice(); void(0);">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	function approveInvoice()
	{
		hideArea('approverList');
		hideArea('buttonForward');
		hideArea('errors');
		displayArea('approvemsg');
		frm.invoiceaction.value = "FORWARD";
		frm.ApprovalLog_approvers.value = "${esapi:encodeForJavaScript(userId)}";
		doSubmit('/invoice/iv_forward.jsp', 'InvoiceForward');
	}

	function addUser()
  	{
  		popupParameters = "browseName=user";
	  	popupParameters = popupParameters + ";formField=invoiceApprovers";
    	doSubmitToPopup('/browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
  	}

	function returnToInvoice()
	{
		if (frm.viewType.value == "CLASSIC")
		{
			doSubmit('/invoice/iv_summary.jsp', 'InvoiceRetrieve')
		}
		else
		{
			doSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve')
		}
	}

	function forwardInvoice()
	{
		var bCanForward = true;
		for (var i = 0; i < frm.approvers.options.length; i++)
		{
			if (frm.approvers.options[i].value == '#Requisitioner' || frm.approvers.options[i].value == '#Approver')
			{
				bCanForward = false;
				alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
				addUser();
				break;
			}
		}
		if (bCanForward)
		{
			getApprovers();
			hideArea("buttonForward");
			hideArea('approverList');
			hideArea('errors');
			displayArea("forwardmsg");
			doSubmit('/invoice/iv_forward.jsp', 'InvoiceApprovals');
		}
	}

	function getApprovers()
	{
		var invoiceApprovers = '';
		for (i = 0; i < frm.approvers.length; i++)
  		{
  			invoiceApprovers = invoiceApprovers + frm.approvers[i].value + ";";
  		}
  		frm.ApprovalLog_approvers.value = invoiceApprovers;
	}

	function addApprover(user, userName)
	{
		var length = frm.approvers.options.length;
		var appType = "";

		for (var i = 0; i < frm.approvers.options.length; i++)
		{
			if (frm.approvers.options[i].value == '#Requisitioner')
			{
				length = i;
				appType = "R";
				break;
			}
			if (frm.approvers.options[i].value == '#Approver')
			{
				length = i;
				frm.RequisitionApprover.value = user;
				appType = "A";
				break;
			}
		}
		frm.approvers.options[length]=new Option(userName, user, false, false);
		frm.approvers.options[length].id = appType;
	}


	/*
		approver types:
		R = requisitioner
		A = requisition approver
		N = not found
		P = pool
	*/
	function removeUser(element)
	{
		var selectedUser = false;

		//first check to see how many requisitioners exist
		var requisitionerCount = checkApprovers("R");
		var reqApproverCount = checkApprovers("A");

  		for (i = element.options.length; i > 0 ; i--)
  		{
    		if(element.options[i - 1].selected == true)
    		{
    			if (element.options[i-1].id == "R")
    			{
    				if (requisitionerCount <= 1)
    				{
    					alert("This approver is required.");
    					return;
    				}
    			}
    			if (element.options[i-1].id == "A")
    			{
    				if (reqApproverCount <= 1)
    				{
    					alert("This approver is required.");
    					return;
    				}
    			}
    			if (element.options[i-1].id == "P" || element.options[i-1].id == "N")
    			{
    				alert("This approver is required.");
    				return;
    			}
    			selectedUser = true;
      			element.options[i - 1] = null;
    		}
  		}
  		if (!selectedUser)
  		{
  			alert("You must first select an approver.");
  		}
	}

	function checkApprovers(type)
	{
		var element = document.getElementById('approvers');
		var count = 0;
		for (i = element.options.length; i > 0 ; i--)
  		{
  			if (element.options[i-1].id == type)
  			{
  				count++;
  			}
  		}
  		return count;
	}


	function moveUp(element)
	{
	    var selectedUser = false;
  		for (i = 0; i < element.options.length; i++)
  		{
    		if (element.options[i].selected == true)
    		{
    			selectedUser = true;
      			if (i != 0)
      			{
			        var temp = new Option(element.options[i-1].text, element.options[i-1].value);
			        var temp2 = new Option(element.options[i].text, element.options[i].value);
			        element.options[i-1] = temp2;
			        element.options[i-1].selected = true;
			        element.options[i] = temp;
      			}
    		}
  		}
  		if (!selectedUser)
  		{
  			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
  		}
	}

	function moveDown(element)
	{
		var selectedUser = false;
  		for (i = (element.options.length - 1); i >= 0; i--)
  		{
    		if(element.options[i].selected == true)
    		{
    				selectedUser = true;
			      if(i != (element.options.length - 1))
			      {
			        var temp = new Option(element.options[i+1].text, element.options[i+1].value);
			        var temp2 = new Option(element.options[i].text, element.options[i].value);
			        element.options[i+1] = temp2;
			        element.options[i+1].selected = true;
			        element.options[i] = temp;
			      }
    		}
  		}
  		if (!selectedUser)
  		{
  			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
  		}
	}

// end hiding contents -->
</SCRIPT>
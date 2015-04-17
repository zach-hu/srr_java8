<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.currcode.CurrencyManager" %>

<%
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	String po_buyer = "";
	String udf_6_code = "";
	String po_sub_type = "";
	if(poHeader == null)
	{
		poHeader = new PoHeader();
	}
	else
	{
		po_buyer = HiltonUtility.ckNull(poHeader.getBuyerCode());
		udf_6_code = HiltonUtility.ckNull(invoiceHeader.getUdf6Code());
		po_sub_type = HiltonUtility.ckNull(poHeader.getSubType());
	}
	String invoiceAction = (String)request.getAttribute("invoiceaction");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	String isWarrantAmountEnough = (String) request.getAttribute("isWarrantAmountEnough");
	Boolean approversEnoughWarrant = (Boolean) request.getAttribute("approversEnoughWarrant");
	if (approversEnoughWarrant == null) {		approversEnoughWarrant = Boolean.TRUE;		}
	Boolean approvalsExceptions = (Boolean)request.getAttribute("approvalsExceptions");
	if (approvalsExceptions == null) {		approvalsExceptions = Boolean.FALSE;		}

	String errorMsg = "";
	if (!approversEnoughWarrant.booleanValue() || !approvalsExceptions.booleanValue())
	{
		errorMsg = (String) request.getAttribute("errorMsg");
	}

	String fromExceptionsPage = (String) request.getAttribute("fromExceptionsPage");
	if(fromExceptionsPage==null)
	{
		fromExceptionsPage="NO";
	}

	String invoiceCurrencyCode = invoiceHeader.getCurrencyCode();
	BigDecimal invoiceTotal = invoiceHeader.getInvoiceTotal();
	if (!invoiceCurrencyCode.equals("USD"))
	{
		invoiceTotal = CurrencyManager.getInstance(oid).convertPrice(invoiceTotal, invoiceCurrencyCode, "USD");
	}

	String invoiceOwnerCanApprove = propertiesManager.getProperty("VOUCHER", "INVOICEOWNERCANAPPROVE", "N");
	String defaultApprover = propertiesManager.getProperty("VOUCHER", "DEFAULTAPPROVER", "N");
	String approversForSubtype = propertiesManager.getProperty("VOUCHER", "POSUBTYPE", "N");

	boolean forwardTo = false;
	String forwardToUsers = propertiesManager.getProperty("VOUCHER", "FORWARDTOUSERS", "");
	if (!HiltonUtility.isEmpty(forwardToUsers))
	{
		forwardTo = true;
	}
	List approverListVsrrProj = (List) request.getAttribute("approverListVsrrProj");
	if(approverListVsrrProj == null)
	{
		approverListVsrrProj = new ArrayList();
	}
	if(!po_sub_type.equalsIgnoreCase("03"))
	{
		approversForSubtype = "N";
	}
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%= invoiceHeader.getIcIvcHeader() %>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceType" value="<%=invoiceHeader.getInvoiceType()%>"/>
<tsa:hidden name="ApprovalLog_approvers" value=""/>
<tsa:hidden name="formtype" value="IVC"/>
<tsa:hidden name="invoiceaction" value="<%=invoiceAction%>"/>

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
		<div id="approvalOptions" class="browseHdrDk" style="border:none; background: #FFFFFF; padding: 2px; width: 255px; height: 20px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
			<tr>
				<td width="50%" valign="top" align="center"><input type="radio" name="approve" checked="checked" value="A" onclick="checkOption();"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-approve", "Approve Invoice", false)%></td>
				<td width="50%" valign="top" align="center"><input type="radio" name="approve" value="F" onclick="checkOption();"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-forward", "Forward For Approval", false)%></td>
			</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td width="100%" align="center">
		<div id="approvemsg" style="display:none" class="label"><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleasewait", "Please wait", false)%>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-approving", "while we approve your invoice", false)%></div>
		<div id="forwardmsg" style="display:none" class="label"><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleasewait", "Please wait", false)%>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-forwarding", "while we forward your invoice", false)%></div>
	</td>
</tr>
<tr>
	<td width="100%" align="center">
		<div id="approverList" class="browseHdrDk" style="border:none; background: #FFFFFF; padding: 2px; width: 555px; height: 150px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
			<table border="0" cellpadding="0" cellspacing="0" width="445px">
				<tr>
					<td width="10%">
						<table align="center">
							<tr>
								<td><a href="javascript: moveUp(document.getElementById('approvers')); void(0);"><img class="button" src="<%=contextPath%>/images/arrow_up.gif" border=0></a></td>
							</tr>
							<tr><td><br></td></tr>
							<tr>
								<td><a href="javascript: moveDown(document.getElementById('approvers')); void(0);"><img class="button" src="<%=contextPath%>/images/arrow_down.gif" border=0></a></td>
							</tr>
						</table>
					</td>
					<td width="60%">
						<table width="100%">
							<tr>
								<td class="mnav" align="center"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approverlist", "Approver List", false)%></td>
							</tr>
							<tr>
								<td><select size="10" multiple="multiple" name="approvers" style="width: 325px">
									</select>
								</td>
							</tr>
						</table>
					</td>
					<td width="30%">
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
			<td width=50% align=center><div id="pxbutton"><a href="javascript: forwardInvoice(); void(0);">Ok</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: returnToInvoice(); void(0);">Return</a></div></td>
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

	var vchAppUid = '<%=UserManager.getInstance().getUser(oid,uid).getVchApp()%>';
	var approvalAmountUid = parseFloat('<%=UserManager.getInstance().getUser(oid,uid).getApprovalAmount()%>');
	var invoiceTotal = parseFloat('<%=invoiceTotal%>');

	setup();

<%	if(forwardTo) { %>
	forwardTo();
<%	} %>
	function forwardTo()
	{
		displayArea('approvalOptions');
		displayArea('approverList');
		frm.approve[1].checked = true;
	<%	String[] userIds = forwardToUsers.split(",");
		for (int i = 0; i < userIds.length; i++)
		{ %>
			addApprover('<%=userIds[i]%>', '<%=UserManager.getInstance().getUserDisplayName(oid, userIds[i])%>');
	<%	} %>
		forwardInvoice();
	}

	function doNothing()
	{
		return;
	}

	function checkOption()
	{
		if (frm.approve[1].checked)
		{
			displayArea('approverList');
		}
		else if (frm.approve[0].checked)
		{
			hideArea('approverList');
		}

	}

	function setup()
	{
<%		if(approversForSubtype.equalsIgnoreCase("Y") || defaultApprover.equalsIgnoreCase("Y")) { 
			if(!HiltonUtility.isEmpty(udf_6_code))
			{
				UserProfile userId = UserManager.getInstance().getUser(oid, udf_6_code);
				if(!HiltonUtility.isEmpty(userId.getMailId())) { %>
					addApprover('<%=udf_6_code%>', '<%=UserManager.getInstance().getUserDisplayName(oid, udf_6_code)%>');
<%				}
			}
			if(!HiltonUtility.isEmpty(po_buyer)) { %>
				addApprover('<%=po_buyer%>', '<%=UserManager.getInstance().getUserDisplayName(oid, po_buyer)%>');			
<%			}
			if(approverListVsrrProj != null && approversForSubtype.equalsIgnoreCase("Y"))
			{
				for (int i=0; i < approverListVsrrProj.size(); i++) {
					String approverUdf = (String) approverListVsrrProj.get(i);
					UserProfile userId = UserManager.getInstance().getUser(oid, approverUdf);
					if(!HiltonUtility.isEmpty(userId.getMailId())) { %>
						addApprover('<%=approverUdf%>', '<%=UserManager.getInstance().getUserDisplayName(oid, approverUdf)%>');
<%					}
				}
			}
		}
	
		if (user.isVchApprover() && !fromExceptionsPage.equals("YES")) {	%>
			displayArea('approvalOptions');
			hideArea('approverList');
<%		}
		else
		{	%>
			displayArea('approverList');
			frm.approve[1].checked = true;
<%		}	%>
	}

	function approveInvoice()
	{
		hideArea('approverList');
		hideArea('buttonForward');
		hideArea('errors');
		displayArea('approvemsg');
		frm.invoiceaction.value = "FORWARD";
<%--//		frm.ApprovalLog_approvers.value = "<%=uid%>"; --%>
		doSubmit('/invoice/iv_forward.jsp', 'InvoiceForward');
	}

	function addUser()
  	{
	  	<%	if (invoiceOwnerCanApprove.equalsIgnoreCase("Y")) { %>
  		popupParameters = "browseName=vchapprover_currentuser";
  		<%	} else { %>
  		popupParameters = "browseName=vchapprover";
  		<%	} 
	  	if(defaultApprover.equalsIgnoreCase("Y") || approversForSubtype.equalsIgnoreCase("Y")) { 	  		
	  		if(!HiltonUtility.isEmpty(po_buyer)) { %>
	  			popupParameters = popupParameters + ";colname=UserProfile_userId;operator=<>;filter_txt=<%=po_buyer%>;logicalOperator=AND;originalFilter=Y;sort=N";
<%	  		}
	  		if(!HiltonUtility.isEmpty(udf_6_code)) { %>
	  			popupParameters = popupParameters + ";colname=UserProfile_userId;operator=<>;filter_txt=<%=udf_6_code%>;logicalOperator=AND;originalFilter=Y;sort=N";
<%	  		}
			if(approversForSubtype.equalsIgnoreCase("Y") && approverListVsrrProj != null) { 
				for (int i=0; i < approverListVsrrProj.size(); i++) {
					String approverUdf = (String) approverListVsrrProj.get(i);%>
					popupParameters = popupParameters + ";colname=UserProfile_userId;operator=<>;filter_txt=<%=approverUdf%>;logicalOperator=AND;originalFilter=Y;sort=N";
<%				}
			}
		}%>
	  	popupParameters = popupParameters + ";amtToApprove="+invoiceTotal;
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
		if (frm.approve[1].checked)	//frm.approve.value = "F"
		{
			getApprovers();
			if (frm.ApprovalLog_approvers.value.length > 0)
			{
				hideArea("buttonForward");
				hideArea('approverList');
				hideArea('errors');
				displayArea("forwardmsg");
				doSubmit('/invoice/iv_forward.jsp', 'InvoiceApprovals');
			}
			else
			{
				alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
				addUser();
			}
		}
		else if (frm.approve[0].checked)	//frm.approve.value = "A"
		{
			if (approvalAmountUid >= invoiceTotal && vchAppUid == 'Y')
			{
				approveInvoice();
			}
			else
			{
				alert("Not Authorized to Approve Amount.");
				return;
			}
		}
	}

	function addApprover(user, userName)
	{
		var length = frm.approvers.options.length;
		frm.approvers.options[length]=new Option(userName, user, false, false);
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


	function removeUser(element)
	{
		var selectedUser = false;
  		for (i = element.options.length; i > 0 ; i--)
  		{
    		if(element.options[i - 1].selected == true)
    		{
    			selectedUser = true;
<%				if(defaultApprover.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(udf_6_code)) { 
					if (!HiltonUtility.isEmpty(udf_6_code) && !HiltonUtility.isEmpty(po_buyer)) { %>
						if((i - 2) > 0 )
	    				{
	    					element.options[i - 1] = null;
	    				}
	<%				} else { %>
	    				if((i - 1) > 0 )
	    				{
	    					element.options[i - 1] = null;
	    				}
<%					}
				} else {	%>
    				element.options[i - 1] = null;
<%				} %>
    		}
  		}
  		if (!selectedUser)
  		{
  			alert("You must first select an approver.");
  		}
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
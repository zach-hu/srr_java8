<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.common.documents.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.Utility" %>

<%  PoHeader poHeader = (PoHeader)request.getAttribute("poHeader");
    List poLineList = (List) request.getAttribute("poLineList");
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	UserProfile lastApproverFromReq = null;
	String s_form_number = poHeader.getDisplayPoNumber().toString();
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String isWarrantAmountEnough = (String)request.getAttribute("isWarrantAmountEnough");
	String isDcAmountEnough = HiltonUtility.ckNull((String)request.getAttribute("isDcAmountEnough"));
	Boolean isPOToAward = (Boolean)request.getAttribute("isPOToAward");
	String poAction = (String)request.getAttribute("poaction");
	int	auto_award_rawa = role.getAccessRights("RAWA");
	String	user_approver = propertiesManager.getProperty("APPROVALS", "BUYERLIKEAPPROVER", "N");
	String	s_buyer_code = poHeader.getBuyerCode();
	BigDecimal poTotal = HiltonUtility.ckNull(poHeader.getTotal()).abs();
	BigDecimal b_revision_value = HiltonUtility.ckNull(poHeader.getRevisionValue()).abs();
	BigDecimal warrantAmount = new BigDecimal(0);
	String autoaward = "";
	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
	
	boolean fromReq = false;

	if (poHeader.getIcReqHeader().compareTo(new BigDecimal(0)) > 0) {
		fromReq = true;
	}

	if(isPOToAward == null) {		isPOToAward = Boolean.FALSE;		}

	Boolean approversEnoughWarrant = (Boolean)request.getAttribute("approversEnoughWarrant");
	if(approversEnoughWarrant == null) {		approversEnoughWarrant = Boolean.TRUE;		}
	Boolean approvalsExceptions = (Boolean)request.getAttribute("approvalsExceptions");
	if(approvalsExceptions == null) {		approvalsExceptions = Boolean.FALSE;		}

	String errorMsg = "";
	if(!approversEnoughWarrant.booleanValue() || !approvalsExceptions.booleanValue())
	{
		errorMsg = (String)request.getAttribute("errorMsg");
	}
	if(auto_award_rawa == 99)
	{
		if(poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) > 0)
		{
			warrantAmount = HiltonUtility.ckNull(buyer.getWarrantAmount()).abs();
			if(b_revision_value.compareTo(warrantAmount) < 0)
			{
				autoaward = "Y";
			}
			else
			{
				autoaward = "N";
			}
		}
	}
	/*
		Douglas Chin  115444
Thomas Kennedy  114950
Marian Luoma  108676
Peter Morabito  115388
	*/
	boolean forwardToDennis = false;
// 	if(poHeader.getTotal().compareTo(new BigDecimal(500000)) < 1)
// 	{
// 		String dennisBuyers = "115444 001787 108676 115388";
// 		if( (!Utility.isEmpty(poHeader.getBuyerCode()) && dennisBuyers.indexOf(poHeader.getBuyerCode()) > -1) 
// 				|| (!Utility.isEmpty(poHeader.getOwner()) && dennisBuyers.indexOf(poHeader.getOwner()) > -1))
// 		{
// 			forwardToDennis = true;
// 		}
// 	}

	boolean requireLastApproverFromReq = false;

	if ((oid.equalsIgnoreCase("QRI06P")) && (requisitionHeader != null) && (poHeader.getTotal().compareTo(requisitionHeader.getTotal()) > 0)) {
		requireLastApproverFromReq = true;
		lastApproverFromReq = (UserProfile) request.getAttribute("lastApprover");
	}
	ChecklistResponse checklistResponse = null;
	String check_response = "";
	List listResponse = (List) request.getAttribute("checklistResponse");
	
	if (listResponse != null && listResponse.size() > 0){
		checklistResponse = (ChecklistResponse)listResponse.get(0);
	}
	if (checklistResponse != null){
		check_response = checklistResponse.getResponse();
	}
%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoHeader_status" value="<%=poHeader.getStatus()%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=poHeader.getPoType()%>"/>
<tsa:hidden name="poaction" value="<%= poAction %>"/>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="newStatus" value=""/>
<tsa:hidden name="awardDirectly" value="Y"/>

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
							<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "forwardoptions", "Forward Options", false)%></div>
						</td>
					</tr>
				</table>
			</td>
			<td valign="bottom" align="left" width=3px><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width=*>
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
	<td align="center">
		<font class="hdr12"><%=s_form_number%></font>
	</td>
</tr>
<tr>
	<td width="100%" align="center" valign="top">
		<div id="initialOptions" class="browseHdrDk" style="border:none; background: #FFFFFF; padding: 2px; width: 255px; height: 20px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
			<table border="0" cellpadding="0" cellspacing="0" width="245px" align="center">
				<tr>
					<td width="50%" valign="top" align="center"><input type="radio" name="award" checked="checked" value="A" onclick="checkOption();"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-award", "Award Order", false)%></td>
					<td width="50%" valign="top" align="center"><input type="radio" name="award" value="F" onclick="checkOption();"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-forward", "Forward Order", false)%></td>
				</tr>
			</table>
		</div>
	</td>
</tr>

<tr><td align="center"><div id="errors" style="display:none" class="error"><span <%=HtmlWriter.isVisible(oid, "po-errormsg")%>><br><%=errorMsg%><span></div><br>
									<div id="awardmsg" style="display:none" class="label"><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleasewait", "Please Wait", false)%><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-awarding", "Award Order", false)%></div>
									<div id="forwardmsg" style="display:none" class="label"><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleasewait", "Please Wait", false)%><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-forwarding", "Forward Order", false)%></div>
	</td>
</tr>
<tr>
	<td width="100%" align="center" valign="top">
		<div id="awardOptions" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 455px; height: 200px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
			<table border="0" cellpadding="0" cellspacing="0" width="445px">
				<tr>
					<td width="10%">
						<table align="center">
							<tr>
								<td><a href="javascript: moveUp(document.getElementById('approvers')); void(0);"><img class="button" src="<%=contextPath%>/images/arrow_up.gif" border=0></a></td>
							</tr>
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
								<td><tsa:hidden name="reqApprover" value="" />
								    <div id="lastApproverFromReq"></div>
								    <select size="10" multiple="multiple" name="approvers" style="width: 325px">
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
				<tr>
					<td width="100%" colspan="3">
						<div id="lastApproverFromReqLink" style="display: none;"><a href="#" onclick="javascript: setLastApproverFromReq();">Add Requisition Approver</a></div>
					</td>
				</tr>
			</table>
	</TR>
	<tr>
		<td align="center" id="buttonForward">
			<table>
				<tr>
					<td><div id="pxbutton"><a href="javascript: forwardOrder(); void(0);">Continue</a></div></td>
					<td><div id="pxbutton"><a href="javascript: returnToOrder(); void(0);">Return</a></div></td>
				</tr>
			</table>
		</td>
 	</tr>
</TABLE>
		</div>
	</td>
</tr>
</table>
<tsa:hidden name="ApprovalLog_approvers" value=""/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="notApproversEnoughWarrantPage" value="orders/po_award_options.jsp"/>

<br>
<br>


<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	setup();

	<%if(forwardToDennis)
	{%>
		forwardToDennis();
	<%}%>
	function forwardToDennis()
	{
		displayArea('initialOptions');
		displayArea('awardOptions');
		frm.award[1].checked = true;
		addApprover('114950', 'Thomas Kennedy');
		forwardOrder()
	}

	function doNothing()
	{
		return;
	}

	function checkOption()
	{
		if(frm.award[1].checked)
		{//frm.award.value = "F"
			//hideArea('initialOptions');
			displayArea('awardOptions');
			frm.awardDirectly.value = "N";
		}
		else if(frm.award[0].checked)
		{//frm.award.value = "A"
			//hideArea('initialOptions');
			hideArea('awardOptions');
			frm.awardDirectly.value = "Y";
			//awardOrder();
		}

	}

	function setLastApproverFromReq() {
		<% if (requireLastApproverFromReq) { %>
		addApprover('<%= lastApproverFromReq.getUserId() %>', '<%= lastApproverFromReq.getDisplayName() %>');
		<% } %>
	}

	function setup()
	{
<%		if(!HiltonUtility.isEmpty(autoaward) && auto_award_rawa == 99) {
			if(autoaward.equalsIgnoreCase("Y"))
			{ %>
				awardOrder();			
<%			}
			else
			{ %>
				displayArea('awardOptions');
				hideArea('initialOptions');
				displayArea('errors');
				frm.award[1].checked = true;
<%			}
		%>
<%		} else {	
			if (requireLastApproverFromReq) {
				if (propertiesManager.getProperty("PO APPROVALS", "ASSIGNLASTAPPROVERFROMREQ", "N").equalsIgnoreCase("Y")) {
			%>
					setLastApproverFromReq();
			<%	} else { %>
					displayArea('lastApproverFromReqLink');
			<%	}
			}
			%>
	
			awardNow = false;
<% 			if (isPOToAward.booleanValue()) { %>
			   	frm.newStatus.value = '<%= DocumentStatus.CT_AWARDED %>';
				awardOrder();
		<%	} else if("Y".equals(propertiesManager.getProperty("PO OPTIONS", "COSTPOINTORDERS", "N"))){
				if(check_response.equalsIgnoreCase("Y")&& approvalsExceptions.booleanValue() && isWarrantAmountEnough.equalsIgnoreCase("true")){%>
					displayArea('initialOptions');
					hideArea('awardOptions');
			<% 	} else if(approvalsExceptions.booleanValue() && isDcAmountEnough.equalsIgnoreCase("true")) {%>
					displayArea('initialOptions');
					hideArea('awardOptions');
			<%	} else { %>
					hideArea('initialOptions');
					displayArea('awardOptions');
					frm.award[1].checked = true;
			<%	}	
			} else if(approvalsExceptions.booleanValue() && isWarrantAmountEnough.equalsIgnoreCase("true"))
			{%>
				displayArea('initialOptions');
				hideArea('awardOptions');
			<%}
			else if(!approvalsExceptions.booleanValue())
			{%>
				displayArea('awardOptions');
				hideArea('initialOptions');
				displayArea('errors');
				frm.award[1].checked = true;
			<%}
			else if(isWarrantAmountEnough.equalsIgnoreCase("false"))
			{%>
				hideArea('initialOptions');
				displayArea('awardOptions');
				frm.award[1].checked = true;
			<%}
			else
			{%>
				displayArea('initialOptions');
				frm.award[1].checked = true;
			<%}
			if(!approversEnoughWarrant.booleanValue())
			{%>
				displayArea('errors');
		<%	}
		}%>
	}

	function awardOrder()
	{
		hideArea('awardOptions');
		hideArea("buttonForward");
		hideArea('errors');
		displayArea("awardmsg");
	<%	if ("Y".equals(propertiesManager.getProperty("PO OPTIONS", "COSTPOINTORDERS", "N")) && (!poHeader.getPoType().equalsIgnoreCase("BO") || !poHeader.getPoType().equalsIgnoreCase("RO"))) { %>
		doSubmit('orders/po_costpoint_award.jsp', 'PoRetrieve');
	<%	} else { %>
		doSubmit('orders/po_forward_options.jsp', 'PoAward');
	<%	} %>
	}

	function addUser()
  	{
  		popupParameters = "formField=poApprovers";
	  	<%String contractTypes = propertiesManager.getProperty("MISC", "CONTRACTTYPES", "CT");
	  	if(contractTypes.indexOf(poHeader.getPoType()) > -1)
	  	{%>
	  		popupParameters = popupParameters + ";browseName=po_ct_approver";
	  		popupParameters = popupParameters + ";colname=UserProfile_contractAmount;operator=>=;filter_txt=<%=poHeader.getTotal() %>;logicalOperator=AND;originalFilter=N;sort=N;"
	  	<%} else if (poHeader.getPoType().equals("BO") || poHeader.getPoType().equals("DO") || poHeader.getPoType().equals("SB"))
	  	{%>
	  		popupParameters = popupParameters + ";browseName=po_ct_approver";
  			popupParameters = popupParameters + ";colname=UserProfile_contractAmount;operator=>=;filter_txt=<%=poHeader.getBlanketLimit() %>;logicalOperator=AND;originalFilter=N;sort=N;"
	  	<%}
	  	else
	  	{ %>
	  		popupParameters = popupParameters + ";browseName=po_approver";
	  	<%	if("Y".equals(propertiesManager.getProperty("PO OPTIONS", "COSTPOINTORDERS", "N"))){
	  			if(check_response.equalsIgnoreCase("Y")){%>
	  		  		popupParameters = popupParameters + ";colname=UserProfile_warrantAmount;operator=>=;filter_txt=<%=poHeader.getTotal() %>;logicalOperator=AND;originalFilter=N;sort=N;"
	  		  	<%} 
	  		} else if (propertiesManager.getProperty("MODULES", "PO APPROVALS", "N").equalsIgnoreCase("Y") && !fromReq) {
	  			if (propertiesManager.getProperty("PO APPROVALS", "FILTERBYLOCALE", "N").equalsIgnoreCase("Y")) { %>
	  				popupParameters = popupParameters + ";colname=UserProfile_locale;operator==;filter_txt=<%=poHeader.getUdf1Code()%>;logicalOperator=AND;originalFilter=Y;sort=N";
	  				popupParameters = popupParameters + ";colname=UserProfile_warrantAmount;operator=>=;filter_txt=<%=poHeader.getTotal() %>;logicalOperator=AND;originalFilter=N;sort=N";
	  				popupParameters = popupParameters + ";colname=UserProfile_buyer;operator==;filter_txt=Y;logicalOperator=AND;originalFilter=N;sort=N";
<%	  			} else { %>
		  			popupParameters = popupParameters + ";colname=UserProfile_department;operator==;filter_txt=<%=poHeader.getDepartmentCode()%>;logicalOperator=OR;originalFilter=Y;sort=N;";
<%	  			}
		 	} else { %>
				popupParameters = popupParameters + ";colname=UserProfile_warrantAmount;operator=>=;filter_txt=<%=poHeader.getTotal() %>;logicalOperator=AND;originalFilter=N;sort=N;"
		<% 	} %>
<%		}
		if(user_approver.equalsIgnoreCase("N")) { %>
			popupParameters = popupParameters + ";colname=UserProfile_userId;operator=<>;filter_txt=<%=uid%>;logicalOperator=AND;originalFilter=N;sort=N";
<% 		} 
		else if(!user.isAnApprover()) { %>
			popupParameters = popupParameters + ";colname=UserProfile_userId;operator=<>;filter_txt=<%=uid%>;logicalOperator=AND;originalFilter=N;sort=N";	
<%		} %>
    	doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
  	}

	function returnToOrder()
	{
		if (frm.viewType.value == "CLASSIC")
		{
			doSubmit('orders/po_summary.jsp', 'PoRetrieve')
		}
		else
		{
			doSubmit('orders/po_review.jsp', 'PoRetrieve')
		}
	}

	function forwardOrder()
	{
		if(frm.award[1].checked)
		{//frm.award.value = "F"
			getApprovers();
			if(frm.ApprovalLog_approvers.value.length > 0)
			{
				var reqApprover = frm.reqApprover.value;

				if (!isEmpty(reqApprover)) {
					frm.ApprovalLog_approvers.value = reqApprover + ';' + frm.ApprovalLog_approvers.value;
				}

				hideArea("buttonForward");
				hideArea('awardOptions');
				hideArea('errors');
				displayArea("forwardmsg");
				doSubmit('orders/po_forward_options.jsp', 'PoApprovals');
				//alert(frm.ApprovalLog_approvers.value );
			}
			else
			{
				alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
			}
		}
		else if(frm.award[0].checked)
		{//frm.award.value = "A"
			awardOrder();
		}
	}

	function addApprover(user, userName)
	{
		//Check if the approver was already added
		var isApproverInList = false;
		for(i = 0; i < frm.approvers.length; i++)
  		{
  			if (user == frm.approvers[i].value)	{
  				isApproverInList = true;
  	  		}
  		}
  		if (!isApproverInList)
  		{
			var length = frm.approvers.options.length;
			frm.approvers.options[length]=new Option(userName, user, false, false);
  		}
  		else
  		{
  	  		alert("Approver '" + userName + "' was already added");
  	  	}
	}

	function getApprovers()
	{
		var poApprovers = '';
		for(i = 0; i < frm.approvers.length; i++)
  		{
  			poApprovers = poApprovers + frm.approvers[i].value + ";";
  		}
  		frm.ApprovalLog_approvers.value = poApprovers;
	}


	function removeUser(element)
	{
		var selectedUser = false;
  		for(i = element.options.length; i > 0 ; i--)
  		{
    		if(element.options[i - 1].selected == true)
    		{
    			selectedUser = true;
      			element.options[i - 1] = null;
    		}
  		}
  		if(!selectedUser)
  		{
  			alert("You must first select an approver.");
  		}
	}

	function moveUp(element)
	{
	    var selectedUser = false;
  		for(i = 0; i < element.options.length; i++)
  		{
    		if(element.options[i].selected == true)
    		{
    			selectedUser = true;
      			if(i != 0)
      			{
			        var temp = new Option(element.options[i-1].text, element.options[i-1].value);
			        var temp2 = new Option(element.options[i].text, element.options[i].value);
			        element.options[i-1] = temp2;
			        element.options[i-1].selected = true;
			        element.options[i] = temp;
      			}
    		}
  		}
  		if(!selectedUser)
  		{
  			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
  		}
	}

	function moveDown(element)
	{
		var selectedUser = false;
  		for(i = (element.options.length - 1); i >= 0; i--)
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
  		if(!selectedUser)
  		{
  			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
  		}
	}

	function updateList(list, textBox) {
		textBox.value = '';
	  	for(i = 0; i < list.options.length; i++) {
	    	if (i == 0) {
	      		textBox.value += list.options[i].value;
	    	} else {
	      		textBox.value += ',' + list.options[i].value;
	    	}
	  	}
	}

	function swap(list) {
		var j = 0;
	  	for(i = 0; i < list.options.length; i++) 
		{
	    	if(list.options[i].selected == true) {
	      		j++;
	      		switch (j) {
	        	case 1:
	        		var i1 = i;
	        		var temp = new Option(list.options[i].text, list.options[i].value);
	        		break;
	        	case 2:
	        		var i2 = i;
	        		var temp2 = new Option(list.options[i].text, list.options[i].value);
	        		break;
	      		}
	    	}
	    }
	   	if (j != 2) {
	    	alert('Only 2 items can be swapped');
	  	} else {
	    	list.options[i1] = temp2;
	    	list.options[i1].selected = true;
	    	list.options[i2] = temp;
	    	list.options[i2].selected = true;
	  	}
	}

// end hiding contents -->
</SCRIPT>
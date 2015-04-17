<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	BigDecimal bd_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqType = rfqHeader.getRfqType();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String isWarrantAmountEnough = (String) request.getAttribute("isWarrantAmountEnough");
	Boolean approversEnoughWarrant = (Boolean)request.getAttribute("approversEnoughWarrant");
	if (approversEnoughWarrant == null) {		approversEnoughWarrant = Boolean.TRUE;		}
	Boolean approvalsExceptions = (Boolean)request.getAttribute("approvalsExceptions");
	if (approvalsExceptions == null) {		approvalsExceptions = Boolean.FALSE;		}

	PropertiesManager	propertiesManager 	= PropertiesManager.getInstance(oid);

	String errorMsg = "";
	if (!approversEnoughWarrant.booleanValue() || !approvalsExceptions.booleanValue()) {
		errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));
	}
%>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
<tsa:hidden name="fromPage" value="rfq_award_options.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=RfqType.toString(s_rfqType)%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "forwardoptions", "Forward Options", false)%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="100%" align="center" valign="top">
		<div id="initialOptions" class="browseHdrDk" style="border:none; background: #FFFFFF; padding: 2px; width: 255px; height: 20px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
			<table border="0" cellpadding="0" cellspacing="0" width="245px" align="center">
				<tr>
					<td width="50%" valign="top" align="center"><input type="radio" name="award" checked="checked" value="A" onclick="checkOption();"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-award", "Award Solicitation", false)%></td>
					<td width="50%" valign="top" align="center"><input type="radio" name="award" value="F" onclick="checkOption();"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-forward", "Forward Solicitation", false)%></td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td align="center">
		<div id="errors" style="display:none" class="error"><br><%=errorMsg%></div><br>
		<div id="awardmsg" style="display:none" class="label"><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleasewait", "Please Wait", false)%><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-awarding", "Award Solicitation", false)%></div>
		<div id="forwardmsg" style="display:none" class="label"><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleasewait", "Please Wait", false)%><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-forwarding", "Forward Solicitation", false)%></div>
	</td>
</tr>
<tr>
	<td width="100%" align="center" valign="top">
		<div id="awardOptions" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 455px; height: 100px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
			<table border="0" cellpadding="0" cellspacing="0" width="445px">
			<tr>
				<td width="10%">
					<table align="center">
						<tr><td><a href="javascript: moveUp(document.getElementById('approvers')); void(0);"><img class="button" src="<%=contextPath%>/images/arrow_up.gif" border=0></a></td></tr>
						<tr><td><a href="javascript: moveDown(document.getElementById('approvers')); void(0);"><img class="button" src="<%=contextPath%>/images/arrow_down.gif" border=0></a></td></tr>
					</table>
				</td>
				<td width="60%">
					<table width="100%">
						<tr><td class="mnav" align="center"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approverlist", "Approver List", false)%></td></tr>
						<tr><td><select size="10" multiple="multiple" name="approvers" style="width: 325px"></select></td></tr>
					</table>
				</td>
				<td width="30%">
					<table align="center">
						<tr><td><a href="javascript: addUser(); void(0);"><img class="button" src="<%=contextPath%>/images/add_user_black.gif" border="0"></a></td></tr>
						<tr><td><a href="javascript: removeUser(document.getElementById('approvers')); void(0);"><img class="button" src="<%=contextPath%>/images/remove_black.gif" border="0"></a></td></tr>
					</table>
				</td>
			</tr>
			</table>
		</div>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align="center" id="buttonForward">
		<table width=100%>
			<tr>
				<td width=50% align=center><a href="javascript: forwardSolicitation(); void(0);"><img class="button" src="<%=contextPath%>/images/button_ok.gif" border="0"></a></td>
				<td width=50% align=center><a href="javascript: returnToSolicitation(); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border="0"></a></td>
			</tr>
		</table>
	</td>
 </tr>
</table>
<tsa:hidden name="ApprovalLog_approvers" value=""/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
<tsa:hidden name="notApproversEnoughWarrantPage" value="orders/rfq_award_options.jsp"/>

<br>
<br>


<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	setup();

	function checkOption() {
		if (frm.award[1].checked) {
			displayArea('awardOptions');
		}
		else if (frm.award[0].checked) {
			hideArea('awardOptions');
		}
	}

	function setup() {
		awardNow = false;
<%	if ((approvalsExceptions.booleanValue() && isWarrantAmountEnough.equalsIgnoreCase("true"))) {%>
			displayArea('initialOptions');
			hideArea('awardOptions');
<%	} else if (!approvalsExceptions.booleanValue()) {%>
			displayArea('awardOptions');
			hideArea('initialOptions');
			displayArea('errors');
			frm.award[1].checked = true;
<% } else {%>
			displayArea('initialOptions');
			frm.award[1].checked = true;
<% }
   if (propertiesManager.getProperty("Rfq Approvals", "Enabled", "N").equalsIgnoreCase("N")) {%>
            awardSolicitation();
<% }
   if (!approversEnoughWarrant.booleanValue()) {%>
			displayArea('errors');
<% } %>
	}

	function awardSolicitation() {
		hideArea('awardOptions');
		hideArea("buttonForward");
		hideArea('errors');
		displayArea("awardmsg");
		doSubmit('rfq/rfq_forward.jsp', 'RfqForward');
	}

	function addUser() {
  		popupParameters = "browseName=rfq_approver";
	  	popupParameters = popupParameters + ";formField=rfqApprovers";
	  	//popupParameters = popupParameters + ";colname=UserProfile_warrantAmount;operator=>;filter_txt=<%//fqHeader.getTotal() %>;logicalOperator=AND;originalFilter=N;sort=N;"
    	doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
  	}

	function returnToSolicitation() {
		if (frm.viewType.value == "CLASSIC") {
			doSubmit('rfq/rfq_summary.jsp', 'RfqRetrieve')
		} else {
			doSubmit('rfq/rfq_review.jsp', 'RfqRetrieve')
		}
	}

	function forwardSolicitation() {
		if (frm.award[1].checked) {
			getApprovers();
			if (frm.ApprovalLog_approvers.value.length > 0) {
				hideArea("buttonForward");
				hideArea('awardOptions');
				hideArea('errors');
				displayArea("forwardmsg");
				doSubmit('rfq/rfq_forward.jsp', 'RfqApprovals');
			} else {
				alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
			}
		} else if (frm.award[0].checked) {
			awardSolicitation();
		}
	}

	function addApprover(user, userName) {
		var length = frm.approvers.options.length;
		frm.approvers.options[length]=new Option(userName, user, false, false);
	}

	function getApprovers() {
		var rfqApprovers = '';
		for (i = 0; i < frm.approvers.length; i++) {
  			rfqApprovers = rfqApprovers + frm.approvers[i].value + ";";
  		}
  		frm.ApprovalLog_approvers.value = rfqApprovers;
	}

	function removeUser(element) {
		var selectedUser = false;

		for (i = element.options.length; i > 0 ; i--) {
			if (element.options[i - 1].selected == true) {
				selectedUser = true;
				element.options[i - 1] = null;
			}
		}
		if (!selectedUser) {
			alert("You must first select an approver.");
		}
	}

	function moveUp(element) {
	    var selectedUser = false;
  		for (i = 0; i < element.options.length; i++) {
    		if (element.options[i].selected == true) {
    			selectedUser = true;
      			if (i != 0) {
			        var temp = new Option(element.options[i-1].text, element.options[i-1].value);
			        var temp2 = new Option(element.options[i].text, element.options[i].value);
			        element.options[i-1] = temp2;
			        element.options[i-1].selected = true;
			        element.options[i] = temp;
      			}
    		}
  		}
  		if (!selectedUser) {
  			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
  		}
	}

	function moveDown(element) {
		var selectedUser = false;
		for (i = (element.options.length - 1); i >= 0; i--) {
			if (element.options[i].selected == true) {
				selectedUser = true;
				if (i != (element.options.length - 1)) {
			 		 var temp = new Option(element.options[i+1].text, element.options[i+1].value);
			 		 var temp2 = new Option(element.options[i].text, element.options[i].value);
			 		element.options[i+1] = temp2;
			 		element.options[i+1].selected = true;
			 		element.options[i] = temp;
			 	}
			 }
		}
		if (!selectedUser) {
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
	  for(i = 0; i < list.options.length; i++) {
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
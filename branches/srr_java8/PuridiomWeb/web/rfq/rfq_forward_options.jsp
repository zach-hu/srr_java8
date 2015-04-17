<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.common.documents.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%  
	RfqHeader rfqHeader = (RfqHeader)request.getAttribute("rfqHeader");
	String s_form_number = rfqHeader.getDisplayRfqNumber().toString();

	String poolid = "";
	String pooldesc = "";
	AppPool appPool = (AppPool)request.getAttribute("appPool");
	if (appPool != null) {
		poolid = appPool.getPoolid();
		pooldesc = appPool.getPooldesc();
	}

	String apUserIdList = "";
	String apPoolidList = "";
	List appPooluserList = (List)request.getAttribute("appPooluserList");
	if (appPooluserList != null && appPooluserList.size() > 0) {
		for (int i = 0; i < appPooluserList.size(); i++) {
			AppPooluser appPooluser = (AppPooluser)appPooluserList.get(i);
			if (appPooluser != null) {
				if (apUserIdList.length() > 0) {
					apUserIdList = apUserIdList + ";" + appPooluser.getComp_id().getUserId();
					apPoolidList = apPoolidList + ";" + poolid;
				} else {
					apUserIdList = appPooluser.getComp_id().getUserId();
					apPoolidList = poolid;
				}
			}
		}
	}
%>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
<tsa:hidden name="formtype" value="RFQ"/>

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
	<td align="center">
		<div id="forwardmsg" style="display:none" class="label"><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleasewait", "Please Wait", false)%><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-forwarding", "Forward Order", false)%></div>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<div id="awardOptions" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 455px; height: 200px; align:center; overflow-y:visible; overflow-x:auto;">
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
		</div>
	</td>
</tr>
<tr>
	<td align="center" id="buttonForward">
		<table>
			<tr>
				<td><div id="pxbutton"><a href="javascript: forwardRfq(); void(0);">Continue</a></div></td>
				<td><div id="pxbutton"><a href="javascript: returnToRfq(); void(0);">Return</a></div></td>
			</tr>
		</table>
	</td>
</tr>
</table>
<tsa:hidden name="ApprovalLog_approvers" value=""/>
<tsa:hidden name="ApprovalLog_poolids" value=""/>
<br>
<br>


<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	var indexAppPool = -1;
<%	if (!HiltonUtility.isEmpty(poolid) && !HiltonUtility.isEmpty(pooldesc)) { %>
	addApprover('$<%=poolid%>$', '<%=pooldesc%>');
	indexAppPool = 0;
<%	} %>

	function addUser()
  	{
		popupParameters = "formField=poApprovers";
		popupParameters = popupParameters + ";browseName=rfq_approver";
		popupParameters = popupParameters + ";colname=UserProfile_department;operator==;filter_txt=<%=rfqHeader.getDepartmentCode()%>;logicalOperator=AND;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";colname=UserProfile_warrantAmount;operator=>=;filter_txt=<%=rfqHeader.getEstimatedCost()%>;logicalOperator=AND;originalFilter=Y;sort=N;";

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
  	}

	function returnToRfq()
	{
		if (frm.viewType.value == "CLASSIC") {
			doSubmit('rfq/rfq_summary.jsp', 'RfqRetrieve')
		} else {
			doSubmit('rfq/rfq_review.jsp', 'RfqRetrieve')
		}
	}

	function forwardRfq()
	{
		getApprovers();
		if (frm.ApprovalLog_approvers.value.length > 0)
		{
			var reqApprover = frm.reqApprover.value;

			if (!isEmpty(reqApprover)) {
				frm.ApprovalLog_approvers.value = reqApprover + ';' + frm.ApprovalLog_approvers.value;
			}

			hideArea("buttonForward");
			hideArea('awardOptions');
			displayArea("forwardmsg");

			doSubmit('rfq/rfq_forward_approval.jsp', 'RfqApprovals;ApprovalLogRetrieveByHeader');
		}
		else
		{
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
		}
	}

	function addApprover(user, userName)
	{
		//Check if the approver was already added
		var message = "Approver '" + userName + "' was already added";

		var isApproverInList = false;
		for (i = 0; i < frm.approvers.length; i++)
  		{
  			if (user == frm.approvers[i].value)	{
  				isApproverInList = true;
  				break;
  	  		}
  	  		if ("<%=apUserIdList%>".indexOf(user) >= 0) {
  	  			isApproverInList = true;
  	  			message = message + " on Approval Pool";
  	  			break;
  	  	  	}
  		}
  		if (!isApproverInList)
  		{
			var length = frm.approvers.options.length;
			frm.approvers.options[length]=new Option(userName, user, false, false);
  		}
  		else
  		{
  	  		alert(message);
  	  	}
	}

	function getApprovers()
	{
		var poApprovers = '';
		var poPoolids = '';
		for (i = 0; i < frm.approvers.length; i++)
  		{
	  		if (frm.approvers[i].value == '$<%=poolid%>$' && <%=!HiltonUtility.isEmpty(apUserIdList)%>) {
	  			poApprovers = poApprovers + "<%=apUserIdList%>" + ";";
	  			poPoolids = poPoolids + "<%=apPoolidList%>" + ";";
		  	} else {
		  		poApprovers = poApprovers + frm.approvers[i].value + ";";
		  		poPoolids = poPoolids + " ;";
			}
  		}
  		frm.ApprovalLog_approvers.value = poApprovers;
  		frm.ApprovalLog_poolids.value = poPoolids;
	}

	function removeUser(element)
	{
		var selectedUser = false;
  		for (i = element.options.length; i > 0 ; i--)
  		{
    		if (element.options[i - 1].selected == true)
    		{
    			selectedUser = true;
        		if ((i - 1) != indexAppPool) {
      				element.options[i - 1] = null;
      				if (indexAppPool > (i - 1)) {
      					indexAppPool --;
          			}
        		} else {
        			alert("Approval Pool cannot be removed.");
            	}
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
			        if (i == indexAppPool) {
			        	indexAppPool --;
				    } else if ((i - 1) == indexAppPool) {
			        	indexAppPool ++;
				    }
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
    		if (element.options[i].selected == true)
    		{
    			selectedUser = true;
			    if (i != (element.options.length - 1))
			    {
			        var temp = new Option(element.options[i+1].text, element.options[i+1].value);
			        var temp2 = new Option(element.options[i].text, element.options[i].value);
			        element.options[i+1] = temp2;
			        element.options[i+1].selected = true;
			        element.options[i] = temp;
			        if (i == indexAppPool) {
			        	indexAppPool ++;
				    } else if ((i + 1) == indexAppPool) {
			        	indexAppPool --;
				    }
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
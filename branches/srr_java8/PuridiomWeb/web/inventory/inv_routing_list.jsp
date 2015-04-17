<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.common.documents.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%  
	InvItem invItem = (InvItem) request.getAttribute("invItem");
	String s_form_item_number = "Inventory Item " + invItem.getItemNumber();
%>
<tsa:hidden name="InvItem_itemNumber" value="<%=invItem.getItemNumber()%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=invItem.getIcHeaderHistory()%>"/>
<tsa:hidden name="InvItem_status" value="<%=invItem.getStatus()%>"/>
<tsa:hidden name="formtype" value="INV"/>
<tsa:hidden name="newStatus" value=""/>
<tsa:hidden name="historyStatus" value=""/>
<tsa:hidden name="itemAction" value=""/>


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
		<font class="hdr12"><%=s_form_item_number%></font>
	</td>
</tr>
<tr>
	<td width="100%" align="center" valign="top">
		<div id="initialOptions" class="browseHdrDk" style="border:none; background: #FFFFFF; padding: 2px; width: 255px; height: 20px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
			<table border="0" cellpadding="0" cellspacing="0" width="245px" align="center">
				<tr>
					<td width="50%" valign="top" align="center"><input type="radio" name="award" checked="checked" value="A" onclick="checkOption();"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-approve", "Approve Item", false)%></td>
					<td width="50%" valign="top" align="center"><input type="radio" name="award" value="F" onclick="checkOption();"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-forward", "Forward Item", false)%></td>
				</tr>
			</table>
		</div>
	</td>
</tr>

<tr>
	<td width="100%" align="center" valign="top">
		<div id="approveOptions" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 455px; height: 200px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
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
								<td><tsa:hidden name="invApprover" value="" />
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
					<td><div id="pxbutton"><a href="javascript: forwardItem(); void(0);">Continue</a></div></td>
					<td><div id="pxbutton"><a href="javascript: returnToItem(); void(0);">Return</a></div></td>
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

<br>
<br>


<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	setup();
	function doNothing()
	{
		return;
	}

	function setup()
	{
		displayArea('initialOptions');
		displayArea('approveOptions');
		frm.award[1].checked = true;
	}
	
	function checkOption()
	{
		if(frm.award[1].checked)
		{//frm.award.value = "F"
			//hideArea('initialOptions');
			displayArea('approveOptions');
		}
		else if(frm.award[0].checked)
		{//frm.award.value = "A"
			//hideArea('initialOptions');
			hideArea('approveOptions');
			//awardOrder();
		}

	}

	function addUser()
  	{
  		popupParameters = "formField=invApprovers";
	  	popupParameters = popupParameters + ";browseName=inv_approver";
    	doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
  	}

	function returnToItem()
	{
		frm.itemAction.value="UPDATE";
		doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById');
	}

	function forwardItem()
	{
		if(frm.award[1].checked)
		{//frm.award.value = "F"
			getApprovers();
			if(frm.ApprovalLog_approvers.value.length > 0)
			{
				hideArea("buttonForward");
				hideArea('approveOptions');
				hideArea('errors');
				displayArea("forwardmsg");
				frm.itemAction.value="UPDATE";
				frm.historyStatus.value="5115";
				doSubmit('/inventory/inv_item.jsp', 'InventoryForward;InvItemRetrieveById');
			}
			else
			{
				alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "selectapprover", "You must first Select an Approver", false)%>!");
			}
		}
		else if(frm.award[0].checked)
		{
			hideArea('approveOptions');
			hideArea("buttonForward");
			hideArea('errors');
			displayArea("awardmsg");
			frm.InvItem_status.value="02";
			frm.itemAction.value="UPDATE";
			frm.historyStatus.value="5130";
			doSubmit('/inventory/inv_item.jsp', 'InventoryForward;InvItemRetrieveById');
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
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%
	String message = HiltonUtility.ckNull((String) request.getAttribute("message"));
	String schedule = HiltonUtility.ckNull((String) request.getAttribute("schedule"));

	String displayUid = (String) request.getAttribute("userId");
	UserProfile displayUser = UserManager.getInstance().getUser(oid, displayUid);
	String mailId = HiltonUtility.ckNull((String) request.getAttribute("email"));

	if (HiltonUtility.isEmpty(mailId)){
		mailId = displayUser.getMailId().toString();
	}

	String runDate = HiltonUtility.ckNull((String) request.getAttribute("rundate"));
	String runTime = HiltonUtility.ckNull((String) request.getAttribute("runtime"));
	String notes = HiltonUtility.ckNull((String) request.getAttribute("notes"));
	String checkscheduleextract = HiltonUtility.ckNull((String) request.getAttribute("checkscheduleextract"));

	if (HiltonUtility.isEmpty(runDate)){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 30);

		runDate = new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime());
		runTime = new SimpleDateFormat("HH:mm:ss a").format(calendar.getTime());
	  }
%>
<tsa:hidden name="emailTo" value="N" />

<table border="0" cellpadding="0" cellspacing="0" width="505px">
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
						<div style="margin-left:10px; margin-right:10px" class="hdr12">Schedule Interface Run</div>
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
					<td height="2px" class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<% if(!message.equalsIgnoreCase("")){ %>
	<td colspan=3 class=error align=center><%=message %></td>
	<%} %>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<table border="1" cellpadding="0" cellspacing="0" width="445px">
			<tr class="mnav" height="18px">
				<td class="mnav" nowrap colspan="2">&nbsp;&nbsp;Interface Run Options&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table border=0>
			  			<tr>
			  				<td nowrap  align="right" colspan="2"><label>Schedule a</label>&nbsp;</td>
				 			<td nowrap="nowrap" align="left">
				 				<table>
				 				<tr id=invoice><td>
				 					<% if(checkscheduleextract.equalsIgnoreCase("")) {%>
				 					<input type="radio" name="checkscheduleextract" value="I" checked />Invoice Interface</td></tr>
				 					<% } else {%>
				 					<input type="radio" name="checkscheduleextract" value="I" <% if(checkscheduleextract.equalsIgnoreCase("I")) { %> checked <% } %> />Invoice Interface</td></tr>
				 					<% } %>
				 				<tr id=payment><td>
		  							<input type="radio" name="checkscheduleextract" value="P" <% if(checkscheduleextract.equalsIgnoreCase("P")) { %> checked <% } %>/>Payments Import</td></tr>
				 				<tr id=supplier><td>
		  							<input type="radio" name="checkscheduleextract" value="S" <% if(checkscheduleextract.equalsIgnoreCase("S")) { %> checked <% } %>/>Supplier Interface</td></tr></table>
		  					</td>
			  			</tr>
						<tr>
							<td nowrap  align="right" colspan="2"><label>Run Date</label>&nbsp;</td>
							<td><input type="text" name="rundate" value="<%=runDate %>"  size="15" maxlength="10" onblur="validDate();"/></td>
						</tr>
						<tr>
							<td nowrap align="right" colspan="2">Run Time</td>
							<td><input type="text" name="runtime" value="<%=runTime%>" size="15" maxlength="11" onblur="validHour();"/></td>
						</tr>
						<tr>
							<td nowrap align="right" colspan="2"><label>Email To</label>&nbsp;</td>
							<td colspan="2"><input type="text" name="email" id="email" value="<%=mailId %>" onfocus="checkemail();" size=60 /></td>
						</tr>
						<tr>
							<td width="15px" align="center"></td>
							<td nowrap valign="top" align="right"><label>Notes</label>&nbsp;</td>
							<td nowrap colspan="2"><textarea name="notes" cols="55" rows="4" id="notes" onfocus="">${esapi:encodeForHTML(notes)}</textarea></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="455px">
<% if(!message.equalsIgnoreCase("")){ %>
	<tr>
		<td align=center><a href="javascript: submitInvSchedule();"><img class="button" src="<%=contextPath%>/images/button_submit.gif" border="0" alt="Submit" ></a></td>
		<td align=center><a href="javascript: returnMe();"><img class="button" src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
	</tr>
<%} else { %>
	<tr>
		<td align=center><a href="javascript: submitInvSchedule();"><img class="button" src="<%=contextPath%>/images/button_submit.gif" border="0" alt="Submit" ></a></td>
		<td align=center><a href="javascript: returnMe();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
	</tr>
<%} %>
</table>
<br/>
<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();
	setNavCookie('/invoice/inv_schedule_run.jsp', 'DoNothing', 'Interface Manual Run');

	function submitInvSchedule()
	{
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";scheduleInvoiceExtractRun='Y'";

		var schedule = "Invoice" ;

		if(frm.checkscheduleextract[0].checked)
		{
		 	popupParameters = popupParameters + ";scheduleRun='Invoice'";
		}
		else if(frm.checkscheduleextract[1].checked)
		{
		 	popupParameters = popupParameters + ";scheduleRun='Payments'";
		 	schedule = "Payments" ;
		}else if (frm.checkscheduleextract[2].checked){
			popupParameters = popupParameters + ";scheduleRun='Supplier'";
		 	schedule = "Supplier" ;
		}

		 if(checkemail())
		{	var newInputField;
			<% if(message.equalsIgnoreCase("")){%>
				newInputField = "<input type=hidden name='runDate' value='<%=runDate %>'>";
				newInputField = newInputField + "<input type=hidden name='runTime' value='<%=runTime %>'>";
				newInputField = newInputField + "<input type=hidden name='schedule' value='" + schedule + "'>";
				setHiddenFields(newInputField);
				doSubmit('invoice/inv_schedule_run.jsp', 'InvoiceScheduleMessageConfirmation');
			<% } else {%>
				newInputField = "<input type=hidden name='schedule' value='" + schedule + "'>";
				setHiddenFields(newInputField);
				doSubmit('menu/main_menu.jsp', 'EmailInvoiceScheduleExtractRun');
			<% }%>
		}
	}

	function checkemail()
	{
		var str = frm.email.value;
		var emailArray = str.split(";");

		for (x=0; x < emailArray.length; x++)
		{
			if (checkOneEmail(emailArray[x]))
			{
				//return true;
			}
			else
			{
				frm.email.select();
				return false;
			}
		}

		return true;
	}
	function checkOneEmail(email)
	{
		var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i

		if (filter.test(email))
		{
			return true;
		}
		else
		{
			alert("Please input a valid email address!");
			return false;
		}
	}

	function enableRundate()
	{ if(frm.edit_rundate.checked)
		{
			frm.rundate.enable = true;
		}
	}

	function validDate()
	{
    	var date = new String(frm.rundate.value);

		var year = new String(date.substring(0,date.indexOf("/")));
    	var month = new String(date.substring(date.indexOf("/")+1,date.lastIndexOf("/")));
    	var day = new String(date.substring(date.lastIndexOf("/")+1,date.length));

    	if (isNaN(year) || year.length<4 || parseFloat(year)<1900){
        	    alert('Please enter Run Date valid [yyyy/mm/dd]');
        	return false;
    	}
    	if (isNaN(month) || parseFloat(month)<1 || parseFloat(month)>12){
        		alert('Please enter Run Date valid [yyyy/mm/dd]');
        	return false;
    	}
    	if (isNaN(day) || parseInt(day, 10)<1 || parseInt(day, 10)>31){
	        	alert('Please enter Run Date valid [yyyy/mm/dd]');
    	    return false;
    	}
    	if (month==4 || month==6 || month==9 || month==11 || month==2) {
        	if (month==2 && day > 28 || day>30) {
	            alert('Please enter day valid for ' + month);
    	        return false;
        	}
    	}
	}

	function validHour()
	{
        var er_fh = /^(1|01|2|02|3|03|4|04|5|05|6|06|7|07|8|08|9|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23)\:([0-5]0|[0-5][1-9])\:([0-5]0|[0-5][1-9])\ (am|pm|AM|PM)$/

        if( frm.runtime.value == '' )
        {
                alert('Please enter Run Time valid [hh:mm:ss PM].')
                return false
        }
        if ( !(er_fh.test(frm.runtime.value)) )
        {
                alert('Please enter Run Time valid [hh:mm:ss PM].')
                return false
        }

        //alert('¡Hour field right!')
        return true
	}

	function returnMe()
	{
		if (frm.viewType.value == "CLASSIC")
		{
			doSubmit('menu/main_menu.jsp', 'DoNothing');
		}
		else
		{
			doSubmit('menu/main_menu.jsp', 'DoNothing');
		}
	}

	function hideSchedule(type){
		if(type==''){
			document.getElementById("invoice").style.display = "block";
			document.getElementById("payment").style.display = "block";
			document.getElementById("supplier").style.display = "block";
		}else
		{
			if(type=='I'){
				document.getElementById("payment").style.display = "none";
				document.getElementById("supplier").style.display = "none";
			}
			if(type=='P'){
				document.getElementById("invoice").style.display = "none";
				document.getElementById("supplier").style.display = "none";
			}
			if(type=='S'){
				document.getElementById("invoice").style.display = "none";
				document.getElementById("payment").style.display = "none";
			}
		}
	}

	function thisLoad()
	{
		f_StartIt();
		var typeSchedule = '<%=checkscheduleextract%>';
		hideSchedule(typeSchedule);
	}

// end hiding contents -->
</SCRIPT>
</BODY>
</HTML>
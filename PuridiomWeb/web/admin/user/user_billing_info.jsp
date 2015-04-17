<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String	s_current_process = "BILLING_INFO";
	String	s_current_page = "/admin/user/user_billing_info.jsp";
	String	s_current_method = "";
	String	s_current_process_method = "";
	String	errorMsg = (String) request.getAttribute("errorMsg");

	errorMsg = HiltonUtility.ckNull(errorMsg);
	if (errorMsg.length() > 0) {
		errorMsg = errorMsg + "<br>";
	}
	UserProfile userProfile = (UserProfile) request.getAttribute("userProfile");
	if (userProfile == null) {
		userProfile = new UserProfile();
	}
%>

<tsa:hidden name="loginFailurePage" value="index.jsp"/>
<tsa:hidden name="registrationFailurePage" value="/user/self_registration.jsp"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="password" value=""/>
<tsa:hidden name="newPassword" value=""/>
<tsa:hidden name="UserProfile_organizationId" value="<%= oid %>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>My Account - Billing Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px width=*>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center width=100%>
		<table border=0 width=80% align=center border=0>
		<tr class=mrow>
			<td colspan="2">
<%	if (orgStatus.equals("02")) {%>
				<table border=0 width=95%>
				<tr><td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingInformationInstructions", "Your credit card will be billed automatically to renew your membership annually so you can continue the service without interruption.") %></td></tr>
				</table>
<%	} else {%>
				<table border=0 width=95%>
				<tr><td class=error><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-trialBillingInformationNotes", "Billing Information provided will only be used AFTER your free trial has expired!") %></td></tr>
				<tr><td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-trialBillingInformationInstructions", "Once your free trial has ended, your membership will automatically be renewed so you can continue the service without interruption.  <b>You will never be charged during the free trial, and you can cancel at any time.</b>") %></td></tr>
				</table>
<%	}%>
				<table border=0 width=100%>
				<tr><td class=error align=center><%=errorMsg%></td></tr>
				</table>

				<br>

				<hr size="0" color="gray" width=90% align="left">

				<br>

				<table border=0 width=100% cellpadding=1 cellspacing=0>
				<tr class=mrow>
					<td width=50% class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardNumber", "Credit Card Number") %><font color="#0000ff">* </font>&nbsp;</td>
					<td width=50% class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardName", "Full Name on Credit Card") %><font color="#0000ff">* </font>&nbsp;</td>
				</tr>
				<tr class=mrow>
					<td><input type=text name="as_creditCardName"  maxLength=45 value="<%=userProfile.getDisplayName()%>" size=30></td>
					<td><input type=text name="as_creditCardNumber"  maxLength=16 value="" size=30></td>
				</tr>
				<tr class=mrow>
					<td class=label><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardType", "Credit Card Type") %><font color="#0000ff">* </font>&nbsp;</td>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardExpires", "Date Expires") %><font color="#0000ff">* </font>&nbsp;</td>
				</tr>
				<tr class=mrow>
					<td>
						<table border=0 cellpaddin=0 cellspacing=0>
						<tr>
							<td vAlign=top>
								<select name="as_creditCardType" maxlength="30">
								<option value=""></option>
<%	Map paymentTypes = DictionaryManager.getInstance("payment-type", oid).getPropertyMap();
	Iterator typeIterator = paymentTypes.keySet().iterator();
	String cardType = "";
	String cardName = "";
	while (typeIterator.hasNext()) {
		cardType = (String) typeIterator.next();
		cardName = (String) paymentTypes.get(cardType);
%>
								<option value="<%=cardType%>"><%=cardName%></option>
<%	}%>
								</select>
							</td>
							<td vAlign=top><img src="<%=contextPath%>/images/credit_cards.gif" border=0></td>
						</tr>
						</table>
					</td>
					<td>
						<select name="as_expMonth" onChange="setExpirationDate();">
							<option value="" selected></option>
							<option value="JAN" >Jan</option>
							<option value="FEB" >Feb</option>
							<option value="MAR" >Mar</option>
							<option value="APR" >Apr</option>
							<option value="MAY" >May</option>
							<option value="JUN" >Jun</option>
							<option value="JUL" >Jul</option>
							<option value="AUG" >Aug</option>
							<option value="SEP" >Sep</option>
							<option value="OCT" >Oct</option>
							<option value="NOV" >Nov</option>
							<option value="DEC" >Dec</option>
						</select>
						<select name="as_expYear" onChange="setExpirationDate();">
							<option value="" selected></option>
							<option value="2009" >2009</option>
							<option value="2010" >2010</option>
							<option value="2011" >2011</option>
							<option value="2012" >2012</option>
							<option value="2013" >2013</option>
							<option value="2014" >2014</option>
						</select>
						<tsa:hidden name="as_cardExpires" size="20" maxLength="30" value=""/>
					</td>
				</tr>
				<!--tr class=mrow>
					<td><img src="<%=contextPath%>/images/credit_cards.gif" border=0></td>
				</tr-->
				</table>

				<br>

				<table border=0 width=100% cellpadding=1 cellspacing=0>
				<tr><td colspan=2><b>Note:</b> We use maximum encryption so that your credit card information is safe and secure.<br><a href="javascript: moreSecurityInfo(); void(0);">Click here for more security information.</a></td></tr>
				</table>

				<br><br>

				<hr size="0" color="gray" width=90% align="left">

				<br>

				<table border=0 width=100% cellpadding=1 cellspacing=0>
				<tr class=mrow>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingAddressLine1", "Billing Address Line 1") %><font color="#0000ff">* </font>&nbsp;</td>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingAddressLine2", "Address Line 2") %>&nbsp;</td>

				</tr>
				<tr class=mrow>
					<td><input type=text name="as_billingAddressLine1"  maxLength=50 value="" size=40></td>
					<td><input type=text name="as_billingAddressLine2"  maxLength=50 value="" size=40></td>
				</tr>
				<tr class=mrow>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingCity", "City") %><font color="#0000ff">* </font>&nbsp;</td>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingState", "State") %><font color="#0000ff">* </font>&nbsp;</td>

				</tr>
				<tr class=mrow>
					<td><input type=text name="as_billingCity"  maxLength=50 value="" size=40></td>
					<td><input type=text name="as_billingState"  maxLength=50 value="" size=40></td>
				</tr>
				<tr class=mrow>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingZipCode", "Zip Code") %><font color="#0000ff">* </font>&nbsp;</td>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingCountry", "Country") %><font color="#0000ff">* </font>&nbsp;</td>

				</tr>
				<tr class=mrow>
					<td><input type=text name="as_billingZip"  maxLength=50 value="" size=40></td>
					<td><input type=text name="as_billingCountry"  maxLength=50 value="" size=40></td>
				</tr>
				</table>

				<br>

				<hr size="0" color="gray" width=90% align="left">

				<br>

				<table border=0 cellspacing=0 cellpadding=0 width=80% align=left>
				<tr align=middle>
					<td>
						<center>
							<font color="#0000ff">*</font>
							<b><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-requiredInformation", "REQUIRED INFORMATION")%>.</b>
						</center>
					</td>
				</tr>
				</table>

				<br><br>

				<table border=0 cellspacing=0 cellpadding=0 width=80% align=left>
				<tr>
					<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/user/user_account_info.jsp', 'DoNothing;PackagePricingRetrieveAll'); void(0);">Save</a></div></td>
					<td width=50% align=center><div id="pxbutton"><a href="javascript: viewMyAccount(); void(0);">Cancel</a></div></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function validateForm() {
		if (frm.handler.value.indexOf("UserUpdateCCInfo") >= 0) {
			var alertMessage = "";
			var w;

			if ( alertMessage.length > 0 ) {
			    	alert ( 'Please fix the following problems:\n\n'+alertMessage );
			    	return false;
			}
		}
		return true;
	}

	function updateCCInfo() {
		doSubmit('user/user_profile.jsp', 'DoNothing');
	}

	function thisLoad() {
		return;
	}

	function cancelUpdate() {
		doSubmit('user/user_profile.jsp', 'DoNothing');
	}
//-->
</SCRIPT>

<script language="javascript" type="text/javascript">
//<![CDATA[
	var cot_loc0=(window.location.protocol == "https:")? "https://secure.comodo.net/trustlogo/javascript/cot.js" :
	"http://www.trustlogo.com/trustlogo/javascript/cot.js";
	document.writeln('<scr' + 'ipt language="JavaScript" src="'+cot_loc0+'" type="text\/javascript">' + '<\/scr' + 'ipt>');
//]]>
</script>

<a href="http://www.instantssl.com" id="comodoTL">SSL</a>

<script language="JavaScript" type="text/javascript">
	COT("http://my.puridiom.com/puridiom/images/secure_site.gif", "SC2", "none");
</script>

<%@ include file="/system/prevent_caching.jsp" %>
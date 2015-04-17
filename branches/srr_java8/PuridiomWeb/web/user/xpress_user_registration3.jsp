<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String errorMsg = 	(String)request.getAttribute("errorMsg");
	String icPackage = (String) request.getAttribute("icPackage");
	String packageName = (String) request.getAttribute("packageName");
	String packagePrice = (String) request.getAttribute("packagePrice");

	errorMsg = HiltonUtility.ckNull(errorMsg);
	if (errorMsg.length() > 0) {
		errorMsg = errorMsg + "<br>";
	}
	UserProfile userProfile = (UserProfile) request.getAttribute("userProfile");
	if (userProfile == null) {
		userProfile = new UserProfile();
		oid = HiltonUtility.ckNull((String) request.getAttribute("UserProfile_organizationId"));
		uid = HiltonUtility.ckNull((String) request.getAttribute("UserProfile_userId"));
	} else {
		oid = userProfile.getOrganizationId();
		uid = userProfile.getUserId();
	}

	String as_creditCardNumber = HiltonUtility.ckNull((String) request.getAttribute("as_creditCardNumber"));
	String as_firstName = HiltonUtility.ckNull((String) request.getAttribute("as_firstName"));
	String as_lastName = HiltonUtility.ckNull((String) request.getAttribute("as_lastName"));
	String as_creditCardType = HiltonUtility.ckNull((String) request.getAttribute("as_creditCardType"));
	String as_expMonth = HiltonUtility.ckNull((String) request.getAttribute("as_expMonth"));
	String as_expYear = HiltonUtility.ckNull((String) request.getAttribute("as_expYear"));
	String as_billingAddressLine1 = HiltonUtility.ckNull((String) request.getAttribute("as_billingAddressLine1"));
	String as_billingAddressLine2 = HiltonUtility.ckNull((String) request.getAttribute("as_billingAddressLine2"));
	String as_billingCity = HiltonUtility.ckNull((String) request.getAttribute("as_billingCity"));
	String as_billingState = HiltonUtility.ckNull((String) request.getAttribute("as_billingState"));
	String as_billingZip = HiltonUtility.ckNull((String) request.getAttribute("as_billingZip"));
	String as_billingCountry = HiltonUtility.ckNull((String) request.getAttribute("as_billingCountry"));


	if (HiltonUtility.isEmpty(as_firstName)) {
		as_firstName = userProfile.getFirstName();
	}
	if (HiltonUtility.isEmpty(as_lastName)) {
		as_lastName = userProfile.getLastName();
	}
%>

<tsa:hidden name="loginFailurePage" value="index.jsp"/>
<tsa:hidden name="registrationFailurePage" value="/user/xpress_user_registration3.jsp"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="password" value=""/>
<tsa:hidden name="newPassword" value=""/>
<tsa:hidden name="loginId" value="<%=userProfile.getMailId()%>"/>
<tsa:hidden name="UserProfile_organizationId" value="<%=oid%>"/>
<tsa:hidden name="UserProfile_userId" value="${userId}"/>
<tsa:hidden name="icPackage" value="<%=icPackage%>"/>
<tsa:hidden name="packageName" value="<%=packageName%>"/>
<tsa:hidden name="packagePrice" value="<%=packagePrice%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "registrationStep3", "Registration Step 3 of 3 - Billing Information", false)%></div>
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
	<td width=100% valign=top>
		<table width=80% align=center border=0>
		<tr class=mrow>
			<td colspan="2">
				<table border=0 width=95%>
<%	if (packageName.toUpperCase().startsWith("FREE")) {%>
				<tr><td class=error><br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingInformationNotes", "Billing Information provided will only be used AFTER your free trial has expired!") %></td></tr>
				<tr><td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingInformationInstructions", "Once your free trial has ended, your membership will automatically be renewed so you can continue the service without interruption.  <b>You will never be charged during the free trial, and you can cancel at any time.</b>") %></td></tr>
<%	}%>
				</table>

				<table border=0 width=100%>
				<tr><td class=error><%=errorMsg%></td></tr>
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
					<td><input type=text name="as_creditCardNumber"  maxLength=16 value="<%=as_creditCardNumber%>" size=30></td>
					<td><input type=text name="as_firstName"  maxLength=45 value="<%=as_firstName%>" size=16><input type=text name="as_lastName"  maxLength=45 value="<%=as_firstName%>" size=24></td>
				</tr>
				<tr class=mrow>
					<td class=label><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardType", "Credit Card Type") %><font color="#0000ff">* </font>&nbsp;</td>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardExpires", "Date Expires") %><font color="#0000ff">* </font>&nbsp;</td>
				</tr>
				<tr class=mrow>
					<td>
						<table border=0 cellpadding=0 cellspacing=0>
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
								<option value="<%=cardType%>" <% if (cardType.equals(as_creditCardType)) {%>selected<%}%>><%=cardName%></option>
<%	}%>
								</select>
							</td>
							<td vAlign=top><img src="<%=contextPath%>/images/credit_cards.gif" border=0></td>
						</tr>
						</table>
					</td>
					<td>
						<select name="as_expMonth">
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
						<select name="as_expYear">
							<option value="" selected></option>
							<option value="2009" >2009</option>
							<option value="2010" >2010</option>
							<option value="2011" >2011</option>
							<option value="2012" >2012</option>
							<option value="2013" >2013</option>
							<option value="2014" >2014</option>
							<option value="2015" >2015</option>
							<option value="2016" >2016</option>
							<option value="2017" >2017</option>
							<option value="2018" >2018</option>
							<option value="2019" >2019</option>
							<option value="2020" >2020</option>
						</select>
						<tsa:hidden name="as_cardExpires" value=""/>
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
					<td>
						<select name="as_billingState" size="1">
							<option value=""></option>
							<option value="AK">AK</option>
							<option value="AL">AL</option>
							<option value="AR">AR</option>
							<option value="AZ">AZ</option>
							<option value="CA">CA</option>
							<option value="CO">CO</option>
							<option value="CT">CT</option>
							<option value="DC">DC</option>
							<option value="DE">DE</option>
							<option value="FL">FL</option>
							<option value="GA">GA</option>
							<option value="HI">HI</option>
							<option value="IA">IA</option>
							<option value="ID">ID</option>
							<option value="IL">IL</option>
							<option value="IN">IN</option>
							<option value="KS">KS</option>
							<option value="KY">KY</option>
							<option value="LA">LA</option>
							<option value="MA">MA</option>
							<option value="MD">MD</option>
							<option value="ME">ME</option>
							<option value="MI">MI</option>
							<option value="MN">MN</option>
							<option value="MO">MO</option>
							<option value="MS">MS</option>
							<option value="MT">MT</option>
							<option value="NC">NC</option>
							<option value="ND">ND</option>
							<option value="NE">NE</option>
							<option value="NH">NH</option>
							<option value="NJ">NJ</option>
							<option value="NM">NM</option>
							<option value="NV">NV</option>
							<option value="NY">NY</option>
							<option value="OH">OH</option>
							<option value="OK">OK</option>
							<option value="OR">OR</option>
							<option value="PA">PA</option>
							<option value="RI">RI</option>
							<option value="SC">SC</option>
							<option value="SD">SD</option>
							<option value="TN">TN</option>
							<option value="TX">TX</option>
							<option value="UT">UT</option>
							<option value="VA">VA</option>
							<option value="VT">VT</option>
							<option value="WA">WA</option>
							<option value="WI">WI</option>
							<option value="WV">WV</option>
							<option value="WY">WY</option>
						</select>
					</td>
				</tr>
				<tr class=mrow>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingZipCode", "Zip Code") %><font color="#0000ff">* </font>&nbsp;</td>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingCountry", "Country") %><font color="#0000ff">* </font>&nbsp;</td>

				</tr>
				<tr class=mrow>
					<td><input type=text name="as_billingZip"  maxLength=50 value="" size=40></td>
					<td>
						<select name=as_billingCountry>
							<option value="" selected></option>
							<option value="AF">Afghanistan</option>
							<option value="AL">Albania</option>
							<option value="DZ">Algeria</option>
							<option value="AS">American Samoa</option>
							<option value="AD">Andorra</option>
							<option value="AO">Angola</option>
							<option value="AI">Anguilla</option>
							<option value="AQ">Antarctica</option>
							<option value="AG">Antigua and Barbuda</option>
							<option value="AR">Argentina</option>
							<option value="AM">Armenia</option>
							<option value="AW">Aruba</option>
							<option value="AU">Australia</option>
							<option value="AT">Austria</option>
							<option value="AZ">Azerbaijan</option>
							<option value="BS">Bahamas</option>
							<option value="BH">Bahrain</option>
							<option value="BD">Bangladesh</option>
							<option value="BB">Barbados</option>
							<option value="BY">Belarus</option>
							<option value="BE">Belgium</option>
							<option value="BZ">Belize</option>
							<option value="BJ">Benin</option>
							<option value="BM">Bermuda</option>
							<option value="BT">Bhutan</option>
							<option value="BO">Bolivia</option>
							<option value="BA">Bosnia and Herzegowina</option>
							<option value="BW">Botswana</option>
							<option value="BV">Bouvet Island</option>
							<option value="BR">Brazil</option>
							<option value="IO">British Indian Ocean Territory</option>
							<option value="BN">Brunei Darussalam</option>
							<option value="BG">Bulgaria</option>
							<option value="BF">Burkina Faso</option>
							<option value="BI">Burundi</option>
							<option value="KH">Cambodia</option>
							<option value="CM">Cameroon</option>
							<option value="CA">Canada</option>
							<option value="CV">Cape Verde</option>
							<option value="KY">Cayman Islands</option>
							<option value="CF">Central African Republic</option>
							<option value="TD">Chad</option>
							<option value="CL">Chile</option>
							<option value="CN">China</option>
							<option value="CX">Christmas Island</option>
							<option value="CC">Cocos (Keeling) Islands</option>
							<option value="CO">Colombia</option>
							<option value="KM">Comoros</option>
							<option value="CG">Congo</option>
							<option value="CD">Congo, the Democratic Republic of the</option>
							<option value="CK">Cook Islands</option>
							<option value="CR">Costa Rica</option>
							<option value="CI">Cote d'Ivoire</option>
							<option value="HR">Croatia (Hrvatska)</option>
							<option value="CU">Cuba</option>
							<option value="CY">Cyprus</option>
							<option value="CZ">Czech Republic</option>
							<option value="DK">Denmark</option>
							<option value="DJ">Djibouti</option>
							<option value="DM">Dominica</option>
							<option value="DO">Dominican Republic</option>
							<option value="TP">East Timor</option>
							<option value="EC">Ecuador</option>
							<option value="EG">Egypt</option>
							<option value="SV">El Salvador</option>
							<option value="GQ">Equatorial Guinea</option>
							<option value="ER">Eritrea</option>
							<option value="EE">Estonia</option>
							<option value="ET">Ethiopia</option>
							<option value="FK">Falkland Islands (Malvinas)</option>
							<option value="FO">Faroe Islands</option>
							<option value="FJ">Fiji</option>
							<option value="FI">Finland</option>
							<option value="FR">France</option>
							<option value="FX">France, Metropolitan</option>
							<option value="GF">French Guiana</option>
							<option value="PF">French Polynesia</option>
							<option value="TF">French Southern Territories</option>
							<option value="GA">Gabon</option>
							<option value="GM">Gambia</option>
							<option value="GE">Georgia</option>
							<option value="DE">Germany</option>
							<option value="GH">Ghana</option>
							<option value="GI">Gibraltar</option>
							<option value="GR">Greece</option>
							<option value="GL">Greenland</option>
							<option value="GD">Grenada</option>
							<option value="GP">Guadeloupe</option>
							<option value="GU">Guam</option>
							<option value="GT">Guatemala</option>
							<option value="GN">Guinea</option>
							<option value="GW">Guinea-Bissau</option>
							<option value="GY">Guyana</option>
							<option value="HT">Haiti</option>
							<option value="HM">Heard and Mc Donald Islands</option>
							<option value="VA">Holy See (Vatican City State)</option>
							<option value="HN">Honduras</option>
							<option value="HK">Hong Kong</option>
							<option value="HU">Hungary</option>
							<option value="IS">Iceland</option>
							<option value="IN">India</option>
							<option value="ID">Indonesia</option>
							<option value="IR">Iran (Islamic Republic of)</option>
							<option value="IQ">Iraq</option>
							<option value="IE">Ireland</option>
							<option value="IL">Israel</option>
							<option value="IT">Italy</option>
							<option value="JM">Jamaica</option>
							<option value="JP">Japan</option>
							<option value="JO">Jordan</option>
							<option value="KZ">Kazakhstan</option>
							<option value="KE">Kenya</option>
							<option value="KI">Kiribati</option>
							<option value="KP">Korea, Democratic People's Republic of</option>
							<option value="KR">Korea, Republic of</option>
							<option value="KW">Kuwait</option>
							<option value="KG">Kyrgyzstan</option>
							<option value="LA">Lao People's Democratic Republic</option>
							<option value="LV">Latvia</option>
							<option value="LB">Lebanon</option>
							<option value="LS">Lesotho</option>
							<option value="LR">Liberia</option>
							<option value="LY">Libyan Arab Jamahiriya</option>
							<option value="LI">Liechtenstein</option>
							<option value="LT">Lithuania</option>
							<option value="LU">Luxembourg</option>
							<option value="MO">Macau</option>
							<option value="MK">Macedonia, The Former Yugoslav Republic of</option>
							<option value="MG">Madagascar</option>
							<option value="MW">Malawi</option>
							<option value="MY">Malaysia</option>
							<option value="MV">Maldives</option>
							<option value="ML">Mali</option>
							<option value="MT">Malta</option>
							<option value="MH">Marshall Islands</option>
							<option value="MQ">Martinique</option>
							<option value="MR">Mauritania</option>
							<option value="MU">Mauritius</option>
							<option value="YT">Mayotte</option>
							<option value="MX">Mexico</option>
							<option value="FM">Micronesia, Federated States of</option>
							<option value="MD">Moldova, Republic of</option>
							<option value="MC">Monaco</option>
							<option value="MN">Mongolia</option>
							<option value="MS">Montserrat</option>
							<option value="MA">Morocco</option>
							<option value="MZ">Mozambique</option>
							<option value="MM">Myanmar</option>
							<option value="NA">Namibia</option>
							<option value="NR">Nauru</option>
							<option value="NP">Nepal</option>
							<option value="NL">Netherlands</option>
							<option value="AN">Netherlands Antilles</option>
							<option value="NC">New Caledonia</option>
							<option value="NZ">New Zealand</option>
							<option value="NI">Nicaragua</option>
							<option value="NE">Niger</option>
							<option value="NG">Nigeria</option>
							<option value="NU">Niue</option>
							<option value="NF">Norfolk Island</option>
							<option value="MP">Northern Mariana Islands</option>
							<option value="NO">Norway</option>
							<option value="OM">Oman</option>
							<option value="PK">Pakistan</option>
							<option value="PW">Palau</option>
							<option value="PA">Panama</option>
							<option value="PG">Papua New Guinea</option>
							<option value="PY">Paraguay</option>
							<option value="PE">Peru</option>
							<option value="PH">Philippines</option>
							<option value="PN">Pitcairn</option>
							<option value="PL">Poland</option>
							<option value="PT">Portugal</option>
							<option value="PR">Puerto Rico</option>
							<option value="QA">Qatar</option>
							<option value="RE">Reunion</option>
							<option value="RO">Romania</option>
							<option value="RU">Russian Federation</option>
							<option value="RW">Rwanda</option>
							<option value="KN">Saint Kitts and Nevis</option>
							 <option value="LC">Saint LUCIA</option>
							<option value="VC">Saint Vincent and the Grenadines</option>
							<option value="WS">Samoa</option>
							<option value="SM">San Marino</option>
							<option value="ST">Sao Tome and Principe</option>
							 <option value="SA">Saudi Arabia</option>
							<option value="SN">Senegal</option>
							<option value="SC">Seychelles</option>
							<option value="SL">Sierra Leone</option>
							<option value="SG">Singapore</option>
							<option value="SK">Slovakia (Slovak Republic)</option>
							<option value="SI">Slovenia</option>
							<option value="SB">Solomon Islands</option>
							<option value="SO">Somalia</option>
							<option value="ZA">South Africa</option>
							<option value="GS">South Georgia and the South Sandwich Islands</option>
							<option value="ES">Spain</option>
							<option value="LK">Sri Lanka</option>
							<option value="SH">St. Helena</option>
							<option value="PM">St. Pierre and Miquelon</option>
							<option value="SD">Sudan</option>
							<option value="SR">Suriname</option>
							<option value="SJ">Svalbard and Jan Mayen Islands</option>
							<option value="SZ">Swaziland</option>
							<option value="SE">Sweden</option>
							<option value="CH">Switzerland</option>
							<option value="SY">Syrian Arab Republic</option>
							<option value="TW">Taiwan, Province of China</option>
							<option value="TJ">Tajikistan</option>
							<option value="TZ">Tanzania, United Republic of</option>
							<option value="TH">Thailand</option>
							<option value="TG">Togo</option>
							<option value="TK">Tokelau</option>
							<option value="TO">Tonga</option>
							<option value="TT">Trinidad and Tobago</option>
							<option value="TN">Tunisia</option>
							<option value="TR">Turkey</option>
							<option value="TM">Turkmenistan</option>
							<option value="TC">Turks and Caicos Islands</option>
							<option value="TV">Tuvalu</option>
							<option value="UG">Uganda</option>
							<option value="UA">Ukraine</option>
							<option value="AE">United Arab Emirates</option>
							<option value="GB">United Kingdom</option>
							<option value="US">United States</option>
							<option value="UM">United States Minor Outlying Islands</option>
							<option value="UY">Uruguay</option>
							<option value="UZ">Uzbekistan</option>
							<option value="VU">Vanuatu</option>
							<option value="VE">Venezuela</option>
							<option value="VN">Viet Nam</option>
							<option value="VG">Virgin Islands (British)</option>
							<option value="VI">Virgin Islands (U.S.)</option>
							<option value="WF">Wallis and Futuna Islands</option>
							<option value="EH">Western Sahara</option>
							<option value="YE">Yemen</option>
							<option value="YU">Yugoslavia</option>
							<option value="ZM">Zambia</option>
							<option value="ZW">Zimbabwe</option>
						</select>
					</td>
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
					<td width=50% align=center>
						<div id="pxbutton"><a href="javascript: register(); void(0);">Continue</a></div>
					</td>
					<td width=50% align=center>
						<div id="pxbutton"><a href="javascript: doSubmit('index.jsp', 'UserCancelSelfRegistration'); void(0);">Cancel</a></div>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr><td align=right><a href="http://www.instantssl.com">
<img src="<%=contextPath%>/images/horz_master_100pixels.gif" alt="Instant SSL Certificate Secure Site" width="100" height="60" style="border: 0px;"><br>Instant SSL Certificate Secured</a>
</td></tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	frm.userId.value = "";
	frm.as_creditCardNumber.value = "<%=as_creditCardNumber%>";
	frm.as_firstName.value = "<%=as_firstName%>";
	frm.as_lastName.value = "<%=as_lastName%>";
	frm.as_creditCardType.value = "<%=as_creditCardType%>";
	frm.as_expMonth.value = "<%=as_expMonth%>";
	frm.as_expYear.value = "<%=as_expYear%>";
	frm.as_billingAddressLine1.value = "<%=as_billingAddressLine1%>";
	frm.as_billingAddressLine2.value = "<%=as_billingAddressLine2%>";
	frm.as_billingCity.value = "<%=as_billingCity%>";
	frm.as_billingState.value = "<%=as_billingState%>";
	frm.as_billingZip.value = "<%=as_billingZip%>";
	frm.as_billingCountry.value = "<%=as_billingCountry%>";

	function register() {
		var expMonth = "";
		var expMonthInd = frm.as_expMonth.selectedIndex;
		var expYear = frm.as_expYear[frm.as_expYear.selectedIndex].value;

		if (expMonthInd > 0 && expMonthInd < 10) {
			expMonth = "0" + new String(expMonthInd);
		} else {
			expMonth = new String(expMonthInd);
		}
		if (expMonthInd > 0 && frm.as_expYear.selectedIndex > 0) {
			frm.as_cardExpires.value = expMonth + expYear;
		}

		//Validate required information
		if (validateRequiredBillingInfo()) {
<%	if (packageName.toUpperCase().startsWith("FREE")) {%>
			doSubmit('user/xpress_user_registration4.jsp', 'UserFinalizeRegistration');
<%	} else {%>
			if (confirm("Your credit card will be billed for <%=packagePrice%>.  Continue?")) {
				doSubmit('user/xpress_user_registration4.jsp', 'UserFinalizeRegistration');
			}
<%	} %>
		}
		return;
	}

	function validateRequiredBillingInfo() {
		var alertMessage = "";
		var w;

		w = frm.as_creditCardNumber.value;
		if ( isEmpty( w ) ) {
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardNumber", "Credit Card Number")%> is required.\n';
		} else if ( w.length < 16 ) {
			alertMessage += 'Full <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardNumber", "Credit Card Number")%> is required.\n';
		}
		w = frm.as_firstName.value;
		if ( isEmpty( w ) ) {
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardName", "Full Name on Credit Card") %> is required.\n';
		} else {
			w = frm.as_lastName.value;
			if ( isEmpty( w ) )
				alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardName", "Full Name on Credit Card") %> is required.\n';
		}
		w = frm.as_creditCardType.value;
		if ( isEmpty( w ) )
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardType", "Credit Card Type")%> is required.\n';
		w = frm.as_cardExpires.value;
		if ( isEmpty( w ) )
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-creditCardExpires", "Date Expires") %> is required.\n';
		w = frm.as_billingAddressLine1.value;
		if ( isEmpty( w ) )
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingAddressLine1", "Billing Address Line 1") %> is required.\n';
		w = frm.as_billingCity.value;
		if ( isEmpty( w ) )
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingCity", "City") %> is required.\n';
		w = frm.as_billingState.value;
		if ( isEmpty( w ) )
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingState", "State") %> is required.\n';
		w = frm.as_billingZip.value;
		if ( isEmpty( w ) )
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingZipCode", "Zip Code") %> is required.\n';
		w = frm.as_billingCountry.value;
		if ( isEmpty( w ) )
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userreg-billingCountry", "Country") %> is required.\n';

		if ( alertMessage.length > 0 ) {
		    	alert ( 'Please fix the following problems:\n\n'+alertMessage );
		    	return false;
		}
		return true;
	}

	function thisLoad() {
		return;
	}
//-->
</SCRIPT>

<!--script language="javascript" type="text/javascript">
//<![CDATA[
	var cot_loc0=(window.location.protocol == "https:")? "https://secure.comodo.net/trustlogo/javascript/cot.js" :
	"http://www.trustlogo.com/trustlogo/javascript/cot.js";
	document.writeln('<scr' + 'ipt language="JavaScript" src="'+cot_loc0+'" type="text\/javascript">' + '<\/scr' + 'ipt>');
//]]>
</script>

<a href="http://www.instantssl.com" id="comodoTL">SSL</a>

<script language="JavaScript" type="text/javascript">
	COT("http://my.puridiom.com/puridiom/images/secure_site.gif", "SC2", "none");
</script-->

<%@ include file="/system/prevent_caching.jsp" %>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="com.tsa.puridiom.common.documents.StdDocumentType" %>
<%@ page import="com.tsa.puridiom.entity.Organization" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%@ page import="com.tsa.puridiom.supplierportal.RegisterUser" %>
<%
	Map urlproperty = DictionaryManager.getInstance("oidurl", "PURIDIOM").getPropertyMap();
	Iterator it = urlproperty.keySet().iterator() ;
	String coid="PURIDIOM";
	while (it.hasNext()) {
		String ikey = (String) it.next();
		String itxt = (String) urlproperty.get(ikey);
		if (requestURLPath.indexOf(itxt)>0){
			coid = ikey;
		}
	}
	coid = coid.toUpperCase();
	String ckoid = (String) request.getAttribute("oid");
	if ((ckoid == null || ckoid.trim().equals("")) && coid != null && !coid.equals("PURIDIOM")) {
		request.setAttribute("organizationId", coid);
	}

	RegisterUser registerUser = (RegisterUser) request.getAttribute("registerUser");
	String	errorMsg = (String) request.getAttribute("registrationErrorMsg");

	String  errorMsg2 = (String) request.getAttribute("passwordErrorMsg");
	if (errorMsg2 == null) {
		errorMsg2 = "";
	}

	PropertiesManager propertiesManager = PropertiesManager.getInstance(coid);

	errorMsg = Utility.ckNull(errorMsg);
	if (!Utility.isEmpty(errorMsg)) {
		errorMsg = errorMsg + "<br>";
	}

	if (registerUser == null) {
		registerUser = new RegisterUser();
	}

	List organizationList = OrganizationManager.getInstance().getOrganizationList();
	if (organizationList == null) {
		organizationList = new ArrayList();
	}
	if (organizationList.size() <= 0) {
		Organization organization = new Organization();
		organization.setOrganizationId("HILTON");
		organization.setOrganizationName("Hilton");
		organizationList.add(organization);
	}
%>

<tsa:hidden name="registrationPage" value="user/self_registration.jsp"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td vAlign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 vAlign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Self Registration</div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px vAlign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td vAlign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=100% valign=top align=center>
		<table border=0 cellSpacing=0 cellPadding=0 width=500px>
		<tr>
			<td colspan=2 class=error align=center>&nbsp;<%=errorMsg%></td>
		</tr>
		<tr>
			<td colspan=2 align=center class="error"><%=errorMsg2%></td>
		</tr>
		<tr>
			<td colspan="2">
				To register, please complete the information below and select
				the &quot;Submit&quot; button. This information can be changed
				by you at any time after initial registration. <br>Note:
				Automatic qualification is possible if your company is listed as
				an existing qualified supplier and a valid EIN Number is provided.
			</td>
		</tr>
		<tr><td><br><br></td></tr>
		<%	if (organizationList.size() > 1) {%>
		<tr>
			<td>Organization Id<font color="#0000ff">* </font> </td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<select name=oidOptions tabindex=1>
		<%		for (int i = 0; i < organizationList.size(); i++) {
						Organization organization = (Organization) organizationList.get(i);%>
					<option value="<%=organization.getOrganizationId()%>" <% if (registerUser.getOrganizationId().equals(organization.getOrganizationId())) {%>selected<%}%>><%=organization.getOrganizationName()%></option>
		<%		}%>
				</select>
				<tsa:hidden name="RegisterUser_organizationId" value="<%=registerUser.getOrganizationId()%>"/></td>
			<td>&nbsp;</td>
		</tr>
		<%	} else {
			Organization organization = (Organization) organizationList.get(0);
		%>
			<td colspan=2><tsa:hidden name="RegisterUser_organizationId" value="<%=organization.getOrganizationId()%>"/></td>
		<%	}%>
		<tr>
			<td>Company Name<font color="#0000ff">* </font> </td>
			<td>EIN Number<font color="#0000ff">* </font> </td>
		</tr>
		<tr>
			<td><input type=text tabindex=4 name="RegisterUser_vendorName" size=27 maxlength=40 value="<%=registerUser.getVendorName()%>"></td>
			<td><input type=text tabindex=6 name="RegisterUser_vendorEin" size=14 maxlength=10 value="<%=registerUser.getVendorEin()%>" onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td>
				<B>Email Address </B>
				<font color="#0000ff">*</font>
				(This will be your Login Id)
			</td>
			<td>User's Last Name<font color="#0000ff">* </font> </td>
		</tr>
		<tr>
			<td vAlign=top><input type=text tabindex=8 name="RegisterUser_emailAddress" size=27 maxlength=50 value="<%=registerUser.getEmailAddress()%>"></td>
			<td><input type=text tabindex=10 name="RegisterUser_lastName" size=27 maxlength=20 value="<%=registerUser.getLastName()%>"></td>
	        </tr>
		<tr>
			<td>First Name<font color="#0000ff">* </font></td>
			<td>MI</td>
		</tr>
		<tr>
			<td><input type=text tabindex=12 name="RegisterUser_firstName" size=27 maxlength=20 value="<%=registerUser.getFirstName()%>"></td>
			<td><input type=text tabindex=14 name="RegisterUser_middleInitial" size=3 maxlength=2 value="<%=registerUser.getMiddleInitial()%>"></td>
		</tr>
		<tr>
			<td>Password <font color="#0000ff">* </font></td>
			<td>Confirm Password <font color="#0000ff">* </font></td>
		</tr>
		<tr>
			<td><input type=password tabindex=16 name="RegisterUser_contactPassword" autocomplete="off" size=27 maxlength=12></td>
			<td><input type=password tabindex=18 name="confirm_password" autocomplete="off" size=27 maxlength=12></td>
		</tr>
		<tr>
			<td>Phone<font color="#0000ff">* </font></td>
			<td>Fax</td>
		</tr>
		<tr>
			<td><input type=text tabindex=20 name="RegisterUser_phoneNumber" size=14 maxlength=30 value="<%=registerUser.getPhoneNumber()%>"></td>
			<td><input type=text tabindex=22 name="RegisterUser_faxNumber" size=14 maxlength=30 value="<%=registerUser.getFaxNumber()%>"></td>
		</tr>
		</table>

		<table border=0 cellSpacing=0 cellPadding=0 width=500px>
		<tr><td colspan=2><br></td></tr>
		<tr align=middle>
			<td colspan=2>
				<center>
					<font color="#0000ff">*</font>
					<b>REQUIRED INFORMATION.</b>
				</center>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/supplierportal/user/recaptcha.jsp" %>
<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=50% align=center>
		<a href="javascript: register(); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_submit.gif" tabindex=24 border=0 alt=Submit></a>
        </TD>
	<td width=50% align=center>
		<a href="javascript: cancel();"><img class=button src="<%=contextPath%>/supplierportal/images/button_cancel.gif" border=0 tabindex=26 alt=Cancel></a>
	</td>
</tr>
</table>

<br>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	frm.userId.value = "";
	frm.organizationId.value = "";

	function register() {
		if (frm.oidOptions) {
			 frm.RegisterUser_organizationId.value = frm.oidOptions[frm.oidOptions.selectedIndex].value.toUpperCase();
		}
		frm.organizationId.value = frm.RegisterUser_organizationId.value;
		frm.userId.value = frm.RegisterUser_emailAddress.value;

		var newInputField = "<input type='hidden' name='StdDocument_docType' value='<%=StdDocumentType.STANDARD_DOCUMENT%>'>";
		setHiddenFields(newInputField);

		<%
		  if (propertiesManager.getProperty("SUPPLIER PORTAL", "FORWARDTOPREQUALIFICATIONAFTERREGISTRATION", "N").equalsIgnoreCase("Y")) { %>
            doSubmit('user/prequalification.jsp',
                    'VendorRegistration;StdDocumentRetrieveBy;VendorRegisterRetrieveByEmail;VendorOptionsRetrieve');
	   <% } else { %>
			doSubmit('user/registration_confirmation.jsp', 'VendorRegistration;StdDocumentRetrieveBy');
	   <% } %>
	}

	function cancel() {
		doSubmit('index.jsp', 'DoNothing');
	}

 function validateForm() {
	if (frm.handler.value.indexOf("VendorRegistration") >= 0) {
			var w = frm.RegisterUser_organizationId.value;
			var alertMessage = "";

			if ( isEmpty( w ) )
				alertMessage += 'The Organization Id is required.\n';

			w = frm.RegisterUser_vendorName.value;
			if ( isEmpty( w ) )
				alertMessage += 'Your Company Name is required.\n';

        	w = frm.RegisterUser_vendorEin.value;
			if ( isEmpty( w ) )
				alertMessage += 'Your company\'s EIN Number is required.\n';

			w = frm.RegisterUser_emailAddress.value;
			if ( isEmpty( w ) ) {
				alertMessage += 'Your Email Address (user-id) is required.\n';
			} else {
				w =(frm.RegisterUser_emailAddress.value).toLowerCase();
		      	if (w.indexOf("@")<2)
					alertMessage += 'The Email Address seems wrong. Please check the prefix and \'@\' sign.\n';

				if ((w.indexOf(".com")<5)&&(w.indexOf(".org")<5)
					&&(w.indexOf(".gov")<5)&&(w.indexOf(".net")<5)
					&&(w.indexOf(".ca")<5)&&(w.indexOf(".mil")<5)&&(w.indexOf(".edu")<5)) {
					alertMessage += 'The Email Address seems wrong. Please check the suffix for accuracy. (It should include a .com, .edu, .net, .org, .gov or .mil)';
				}
			}

	        w = frm.RegisterUser_lastName.value;
			if ( isEmpty( w ) )
				alertMessage += 'Your Last Name is required.\n';

			w = frm.RegisterUser_firstName.value;
			if ( isEmpty( w ) )
				alertMessage += 'Your First Name is required.\n';

			w = frm.RegisterUser_contactPassword.value;
			if ( isEmpty( w ) )
				alertMessage += 'A Password is required.\n';

			w = frm.confirm_password.value;
			if ( isEmpty( w ) )
				alertMessage += 'Confirm Password is required.\n';

			if ( w != frm.RegisterUser_contactPassword.value )
				alertMessage += 'Confirm Password does not match Password.\n';

			w = frm.RegisterUser_phoneNumber.value;
			if ( isEmpty( w ) )
				alertMessage += 'Your Phone Number is required.\n';

			if ( alertMessage.length > 0 ) {
			   	alert ( 'Please fix the following problems:\n\n'+alertMessage );
			   	return false;
			}
		}

		return true;
    }

	function thisLoad() {
		return;
	}
//-->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>

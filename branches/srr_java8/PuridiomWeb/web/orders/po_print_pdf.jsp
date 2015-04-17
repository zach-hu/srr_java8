<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/header.jsp"%>
<%@ page import="com.tsa.puridiom.common.documents.OrderType"%>
<%@ page import="com.tsa.puridiom.property.PropertiesManager"%>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>

<%
	String icPoHeader = (String) request.getAttribute("PoHeader_icPoHeader");
	String s_form_number = (String) request.getAttribute("PoHeader_poNumber");
	String displayNumber = (String) request.getAttribute("PoHeader_displayNumber");
	String s_poType = (String) request.getAttribute("PoHeader_poType");
	String s_vendorId = (String) request.getAttribute("PoHeader_vendorId");
	String s_vendorName = (String) request.getAttribute("PoHeader_vendorName");
	String s_contact_code = (String) request.getAttribute("PoHeader_vendContactCode");
	String s_contact_faxNumber = (String) request.getAttribute("Contact_faxNumber");
	String s_contactFirstName = (String) request.getAttribute("Contact_firstName");
	String s_contactLastName = (String) request.getAttribute("Contact_lastName");
	String s_contacNames = "";
	String printTC = PropertiesManager.getInstance(oid).getProperty("PO OPTIONS", "PRINT T&Cs", "Y");
	String checkedPrintTC = PropertiesManager.getInstance(oid).getProperty("PO OPTIONS", "CHECKED PRINT T&CS", "N");
	String faxEnabled = PropertiesManager.getInstance(oid).getProperty("FAX", "ENABLED", "N");
	String	s_buyer_code = (String) request.getAttribute("Buyer_Code");
	if(HiltonUtility.isEmpty(s_buyer_code))
	{
		s_buyer_code = "";
	}
	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
	String locale = HiltonUtility.ckNull((String) user.getLocale());
	String poFormMultiLanguages = PropertiesManager.getInstance(oid).getProperty("PO OPTIONS", "POFORMMULTIPLELANGUAJE", "N") ;

	if (!HiltonUtility.isEmpty(s_contactFirstName))
	{
		s_contacNames = s_contactFirstName;
	}
	if (!HiltonUtility.isEmpty(s_contactLastName))
	{
		s_contacNames += " " + s_contactLastName;
	}

	if (HiltonUtility.isEmpty(s_contact_faxNumber))
	{
		s_contact_faxNumber = "";
	}

	String s_emailAddress = "";
	String s_faxNumber    = "";
	Object vendorEntity = VendorManager.getInstance().getVendor(oid, s_vendorId);
	if (vendorEntity != null)
	{
		if (vendorEntity instanceof Vendor)
		{
			s_emailAddress = ((Vendor)vendorEntity).getEmailAddress();
			s_faxNumber    = ((Vendor)vendorEntity).getFaxNumber();
		}
	}
%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_poType%>"/>
<tsa:hidden name="PoHeader_displayNumber" value="<%=displayNumber%>"/>
<tsa:hidden name="PoHeader_udf10Code" value="<%=buyer.getLocale()%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="PoHeader_vendContactCode" value="<%=s_contact_code%>"/>
<tsa:hidden name="emailTo" value="N" />
<tsa:hidden name="faxTo" value="N" />
<tsa:hidden name="fax" value="" />
<tsa:hidden name="StdDocument_docType" value="DS" />

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
						<div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="order" defaultString="Order" /></div>
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
					<td height="2px" class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>
<table border="0" cellpadding="0" cellspacing="0" width=680px>
<tr>
	<td align="center"><font class="formType"><%=OrderType.toString(s_poType, oid)%> </font><font class="hdr12">#<%=s_form_number%></font></td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<table border="1" cellpadding="0" cellspacing="0" width="445px">
			<tr class="mnav" height="18px">
				<td class="mnav" nowrap colspan="2">&nbsp;&nbsp;<tsa:label labelName="printEmailOption" defaultString="Print/Email Options" />&nbsp;</td>
			</tr>
			<tr>
				<td>
				<table border=0>
					<tr>
						<td width="15px" align="center"><tsa:input type="checkbox" name="print_option_view" value="pdf" checked="true" onclick="setViewOptions();printPOOptions()" /> <tsa:hidden name="viewNow" value="Y" /></td>
						<td nowrap><tsa:label labelName="viewPdf" defaultString="View Pdf" /></td>
						<td width="40px">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td width="15px" align="center"><tsa:input type="checkbox" name="print_option_supplier" value="es" onclick="setEmailOptions();printPOOptions()" /></td>
						<td nowrap colspan="2"><tsa:label labelName="emailTo" defaultString="Email To" /> <a href="javascript: browseSupplier(); void(0);"><tsa:label labelName="po-supplier" defaultString="Supplier" /></a></td>
						<td><tsa:input type="maxtext" name="Pdf_PoHeader_vendorId" value="<%=s_vendorId%>" /></td>
					</tr>
		            <%
		            if (faxEnabled.equalsIgnoreCase("Y")){
						%>
						<tr>
							<td width="15px" align="center"><tsa:input type="checkbox" name="print_option_contact" value="fs" onclick="setFaxOptions();printPOOptions()" /></td>
							<td nowrap colspan="2"><tsa:label labelName="faxTo" defaultString="Fax To" /> <a href="javascript: browseContact(); void(0);"><tsa:label labelName="po-supplier" defaultString="Supplier" /></a></td>
							<td><tsa:input type="maxtext" name="Pdf_PoHeader_contactId" value="<%=s_vendorId%>" /></td>
						</tr>
					<%}%>
					<tr>
						<td width="15px" align="center">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td><tsa:input type="text" name="PoHeader_vendorName" value="<%=HiltonUtility.ckNull(s_vendorName)%>" size="60" style="border: none" /></td>
					</tr>

					<%
						if (printTC.equals("Y")){
					%>

					<tr>
						<td width="15px" align="center">&nbsp;</td>
						<td colspan=3 nowrap><input type="checkbox" name="TCs" value="Y" <%=(checkedPrintTC.compareTo("Y")==0)?"CHECKED":""%> onclick="setEmailOptions();" />Send T & Cs</td>
					</tr>
					    <%
						if (faxEnabled.equalsIgnoreCase("Y")){
						%>
					<tr>
						<td width="15px" align="center">&nbsp;</td>
						<td colspan=3 nowrap><input type="checkbox" name="TCs" value="Y" <%=(checkedPrintTC.compareTo("Y")==0)?"CHECKED":""%> onclick="setFaxOptions();" />Send T & Cs</td>
					</tr><%}%>
					<%}%>

					<tr>
						<td width="15px" align="center"><tsa:input type="checkbox" name="print_option_email" value="eu" onclick="setEmailOptions();" /></td>
						<td nowrap align="right"><label for="email"><tsa:label labelName="emailTo" defaultString="Email To" /></label>&nbsp;</td>
						<td colspan="2"><tsa:input type="text" name="email" id="email" value="<%=s_emailAddress%>" onfocus="selectEmailTo();" size="35" /></td>
					</tr>
					<%
					if (faxEnabled.equalsIgnoreCase("Y")){
					%>
						<tr id="faxUS" style="visibility: visible; display=block;">
							<td></td><td></td><td><tsa:label labelName="areaCode" defaultString="Area Code" /></td><td>/ <tsa:label labelName="phoneNumber" defaultString="Phone Number" /></td>
						</tr>
						<tr id="faxUS" style="visibility: visible; display=block;">
							<td width="15px" align="center"><tsa:input type="checkbox" name="print_option_fax" value="fu" onclick="setFaxOptions();" /></td>
							<td nowrap align="right"><label for="email"><tsa:label labelName="faxTo" defaultString="Fax To" /></label>&nbsp;</td>
							<td><tsa:input type="text" name="faxu" maxLength="3" value="1" disabled="disabled" onfocus="selectFaxTo();" size="5" onkeypress="javascript: if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" /></td>
							<td colspan=2><tsa:input type="text" name="faxu" maxLength="12" value="<%=s_faxNumber%>" onfocus="selectFaxTo();" size="35" onkeypress="javascript: if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" />&nbsp;i.e. 012.345.6789</td>
						</tr>
						<tr id="faxInter" style="visibility: hidden; display=none;">
							<td></td><td></td><td><tsa:label labelName="idd" defaultString="IDD" /> /</td><td><tsa:label labelName="countryOrAreaOrPhone" defaultString="Country / Area / Phone Number" /></td>
						</tr>
						<tr id="faxInter" style="visibility: hidden; display=none;">
							<td width="15px" align="center"><tsa:input type="checkbox" name="print_option_fax" value="fu" onclick="setFaxOptions();" /></td>
							<td nowrap align="right"><label for="email"><tsa:label labelName="faxTo" defaultString="Fax To" /></label>&nbsp;</td>
							<td colspan="2"><tsa:input type="text" name="faxi" maxLength="3" value="" onfocus="selectFaxTo();" size="3" onkeypress="javascript: if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" />
							<tsa:input type="text" name="faxi" maxLength="3" value="" onfocus="selectFaxTo();" size="3" onkeypress="javascript: if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" />
							<tsa:input type="text" name="faxi" maxLength="3" value="" onfocus="selectFaxTo();" size="3" onkeypress="javascript: if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" />
							<tsa:input type="midtext" name="faxi" maxLength="12" value="<%=s_faxNumber%>" onfocus="selectFaxTo();" onkeypress="javascript: if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" />&nbsp;i.e. 012.345.6789</td>
						</tr>
		                <tr>
							<td valign=middle ><tsa:input type="radio" name="us_number" value="U" onclick="setUs();" checked="true" /></td>
							<td nowrap align="right"><label for="US"><tsa:label labelName="usCanada" defaultString="US/CANADA" /></label>&nbsp;</td>
							<td valign=middle ><tsa:input type="radio" name="us_number" value="I" onclick="setUs();" /></td>
							<td nowrap align="left"><label for="Int"><tsa:label labelName="international" defaultString="International" /></label>&nbsp;</td>
						</tr>
		            <%}%>
					<tr>
						<td width="15px" align="center"></td>
						<td nowrap valign="top" align="right"><label for="notes"><tsa:label labelName="notes" defaultString="Notes" /></label>&nbsp;</td>
						<td nowrap colspan="2"><tsa:input type="textarea" name="notes" cols="55" rows="4" id="notes" onfocus="selectEmailTo();"></tsa:input></td>
					</tr>
					<%--
						if (faxEnabled.equalsIgnoreCase("Y"))
						{
					%>
					<tr>
						<td width="15px" align="center"><input type="checkbox" name="print_option_fax" value="eu" onclick="setFaxOptions();" /></td>
						<td nowrap valign="top" align="right"><label for="faxTo">Fax to</label>&nbsp;</td>
						<td nowrap colspan="2"><input type="text" name="faxTo" id="faxTo" value="<%=s_contacNames %>" onfocus="selectFaxTo();" size=60 /></td>
					</tr>

					<tr>
						<td width="15px" align="center"></td>
						<td nowrap valign="top" align="right"><label for="faxNumber">Fax #</label>&nbsp;</td>
						<td nowrap colspan="2"><input type="text" name="faxNumber" id="faxNumber" value="<%=s_contact_faxNumber %>" onfocus="selectFaxTo();" size=20 /></td>
					</tr>

					<tr>
						<td width="15px" align="center"></td>
						<td nowrap valign="top" align="right"><label for="faxSubject">Subject</label>&nbsp;</td>
						<td nowrap colspan="2"><input type="text" name="faxSubject" id="faxSubject" onfocus="selectFaxTo();" size=60 /></td>
					</tr>
					<%
						} else
						{
					--%>
					<tr>
						<!--input type="hidden" name="print_option_fax" value="N" /-->
						<tsa:hidden name="faxSubject" value="" />
						<tsa:hidden name="faxNumber" value="" />
						<!-- input type="hidden" name="faxTo" value="" /-->
					</tr>
					<%--
					}
					--%>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
	<tr>
		<td align=center><div id="pxbutton"><a href="javascript: pdfOptions();"><tsa:label labelName="submit" defaultString="Submit" /></a></div></td>
		<td align=center><div id="pxbutton"><a href="javascript: returnMe();"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
	</tr>
</table>
<br />
<%@ include file="/system/footer.jsp"%>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

	function selectEmailTo()
	{
		frm.print_option_email.checked = true;
		if (frm.print_option_fax) {
			  frm.print_option_fax.checked = false;
			}
		frm.print_option_view.checked = false;
	}

	function selectFaxTo()
	{	frm.print_option_fax.checked = true;
		frm.print_option_email.checked = false;
		frm.print_option_view.checked = false;
	}
	/*function selectFaxTo()
	{
		frm.print_option_fax.checked = true;
		setFaxOptions();
	}*/

	function setViewOptions()
	{
		if(frm.print_option_view.checked)
		{
			frm.print_option_email.checked = false;
			if (frm.print_option_fax) {
				frm.print_option_fax.checked = false;
				frm.print_option_contact.checked = false;}
			frm.print_option_supplier.checked = false;
			<%if ( printTC.equals("Y") )
			{%>

				frm.TCs.checked = false;

			<%}%>
			frm.viewNow.value = "Y";
		}
	}

	function printPOOptions()
	{
		<%if(oid.equalsIgnoreCase("BSC04P"))
		{%>
			if(frm.print_option_view.checked)
			{
				document.getElementById("printOptions").style.visibility = "visible";
				document.getElementById("printOptions").style.display    = "block";
				document.getElementById("printOptions").style.height     = 50;
			}
			else
			{
				document.getElementById("printOptions").style.visibility = "hidden";
				document.getElementById("printOptions").style.display    = "none";
				document.getElementById("printOptions").style.height     = 0;
			}
		<%}%>
	}

	function setEmailOptions()
	{
		if(frm.print_option_supplier.checked || frm.print_option_email.checked)
		{ 	frm.print_option_view.checked = false;
			frm.viewNow.value = "N";
		}
		if (frm.print_option_fax) {
			frm.print_option_fax.checked = false;
		}
	}

	function setFaxOptions()
	{
		if(frm.print_option_contact.checked || frm.print_option_fax.checked)
		{
		    frm.print_option_view.checked = false;
			frm.viewNow.value = "N";
		}
		frm.print_option_email.checked = false;
	}

	<%--function setFaxOptions()
	{
		if(frm.print_option_fax.checked)
		{
			frm.print_option_email.checked = false;
			frm.print_option_supplier.checked = false;
			frm.print_option_view.checked = false;
			<%if ( printTC.equals("Y") )
			{%>
				frm.TCs.checked = false;
			<%}%>
		}
	}--%>

	function pdfOptions()
	{
		var udf = '<%=buyer.getLocale()%>';
		popupParameters = "PoHeader_icPoHeader=<%=icPoHeader%>";
		popupParameters = popupParameters + ";PoHeader_poNumber=<%=s_form_number%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		<% if (poFormMultiLanguages.equalsIgnoreCase("Y")){%>
			if( udf == "AU" || udf == "EN" || udf =="RU" || udf =="NL" || udf =="DE" || udf =="PL" || udf =="UK" || udf =="CN")
			{
				popupParameters = popupParameters + ";PoHeader_udf10Code=" + document.getElementById("PoHeader_udf10Code").value ;
			}
		<%}%>

		<%if ( printTC.equals("Y") )
		{%>
			if(frm.TCs.checked)
			{
				popupParameters = popupParameters + ";TCs=Y";
			}
		<%}%>


		if(frm.print_option_view.checked)
		{
			<%if(oid.equalsIgnoreCase("BSC04P"))
			{%>
				if(frm.printRevised.checked)
					frm.hiddenPrintRevised.value = 'Y';
				else
					frm.hiddenPrintRevised.value = 'N';
				if(frm.printCancelled.checked)
					frm.hiddenPrintCancelled.value = 'Y';
				else
					frm.hiddenPrintCancelled.value = 'N';

				popupParameters = popupParameters + ";hiddenPrintRevised="   + frm.hiddenPrintRevised.value;
				popupParameters = popupParameters + ";hiddenPrintCancelled=" + frm.hiddenPrintCancelled.value;
			<%}%>

			frm.emailTo.value = 'N';
			frm.faxTo.value = 'N';
			popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
			doSubmitToPopup('/system/popupDocAttachment.jsp', 'PoHeaderUpdate;PrintPoPdf', 'width=775px', 'height=850px');
		}
		else if(frm.print_option_supplier.checked)
		{
			frm.viewNow.value = 'N';
			frm.emailTo.value = 'N';
			doSubmit('orders/po_review.jsp', 'EmailPoPdf;PoRetrieve');
		}
		else if( frm.print_option_contact && frm.print_option_contact.checked)
		{
			frm.viewNow.value = 'N';
			frm.faxTo.value = 'N';
			doSubmit('orders/po_review.jsp', 'FaxPo;PoRetrieve');
		}

		else if(frm.print_option_email.checked)
		{
		   if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('orders/po_review.jsp', 'EmailPoPdf;PoRetrieve');
			}
		}
		else if(frm.print_option_fax[0].checked || frm.print_option_fax[1].checked)
		{
			if(checkfax())
			{
				frm.viewNow.value = 'N';
				//frm.emailTo.value = 'Y';
				frm.faxTo.value = 'Y';
				doSubmit('orders/po_review.jsp', 'FaxPo;PoRetrieve');
			} else {
				alert("Please enter a fax number!");
			}
		}
		else
		{
			alert("You must select an option.");
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

	function checkfax()
	{
		var fax = "";
		var faxec="";

		if (frm.us_number[0].checked)
 		{
			for (x=0; x < frm.faxu.length; x++)
			{
				fax += frm.faxu[x].value;
			}
		}
		else if( frm.faxi[0].value && frm.faxi[1].value && frm.faxi[2].value && frm.faxi[3].value )
		{
       		for (x=0; x < frm.faxi.length; x++)
			{
				fax += frm.faxi[x].value;
			}
		}
		else
		{
			return false;
		}
		for (x=0; x < fax.length; x++) {
        	if (fax.charAt(x) != "(" && fax.charAt(x) != ")" && fax.charAt(x) != "-" && fax.charAt(x) != " " && fax.charAt(x) != ";" && fax.charAt(x) != "."){
            	faxec += fax.charAt(x);
            }
        }
        fax=faxec;
        frm.fax.value=fax;
			return true;
		}

	function returnMe()
	{
		if (frm.viewType.value == "CLASSIC")
		{
			doSubmit('orders/po_summary.jsp', 'PoRetrieve');
		}
		else
		{
			doSubmit('orders/po_review.jsp', 'PoRetrieve');
		}
	}

	function browseSupplier()
	{
		if (frm.print_option_contact == undefined)
		{
			if (frm.print_option_fax)
			{
				frm.print_option_supplier.checked = true;
			}
		}
		else
		{
			if (frm.print_option_fax)
			{
		   		frm.print_option_supplier.checked = true;
			}
			frm.print_option_contact.checked = false;
		}
		setEmailOptions();
		browseLookup('Pdf_PoHeader_vendorId', 'vendor');
	}
	function browseContact()
	{
		frm.print_option_supplier.checked = false;
		frm.print_option_contact.checked = true;
		setFaxOptions();
		browseLookup('Pdf_PoHeader_contactId', 'vendor');
	}
	function setUs()
	{
		if (frm.us_number[0].checked) {
			hideArea("faxInter");
			displayArea("faxUS");
		} else {
			displayArea("faxInter");
		    hideArea("faxUS");
		}
	}
	function removeBlanks(str)
	{
		var emailArray = str.split(String.fromCharCode(32));
		str = "";
		for (x=0; x<emailArray.length; x++)
		{
			str+=emailArray[x];
		}
		return str;
	}


// end hiding contents -->
</SCRIPT>

</BODY>
<%@page import="com.tsa.puridiom.common.utility.HiltonUtility"%>
</HTML>

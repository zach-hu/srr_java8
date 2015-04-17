 <%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	String icRfqHeader = (String)request.getAttribute("RfqHeader_icRfqHeader");
	String s_form_number = (String)request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqType = (String)request.getAttribute("RfqHeader_rfqType");
	String printtype = (String)request.getAttribute("printtype");
	String RfqHeader_status = (String)request.getAttribute("RfqHeader_status");
	String RfqHeader_vendorAwarded = (String)request.getAttribute("RfqHeader_vendorAwarded");
	List vendorList = (List)request.getAttribute("rfqVendorList");
	String faxEnabled = PropertiesManager.getInstance(oid).getProperty("FAX", "ENABLED","N");
	if(HiltonUtility.isEmpty(printtype))
	{
		printtype = "Rfq";
	}

	String printLetters = PropertiesManager.getInstance(oid).getProperty("RFQ OPTIONS", "PRINT LETTERS", "N");
	
	Encoder encoder = DefaultEncoder.getInstance();
%>

<script type="text/javascript">
	function selectAllSuppliers(form) {
		for(var i = 0; i < form.listSuppliers.options.length; i++) {
			form.listSuppliers.options[i].selected = form.chkSuppliers.checked;
		}
			form.listSuppliers.options[0].selected = !form.chkSuppliers.checked
	}
</script>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=icRfqHeader%>" />
<tsa:hidden name="RfqHeader_rfqType" value="${esapi:encodeForHTMLAttribute(RfqHeader_rfqType)}" />
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_form_number%>" />
<tsa:hidden name="allowBrowse" value="true" />
<tsa:hidden name="emailTo" value="N" />
<tsa:hidden name="printTotal" value="N" />
<tsa:hidden name="printtype" value="<%=printtype %>" />
<tsa:hidden name="format" value="" />

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
						<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitation", "Solicitation")%></div>
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
	<td align="center">
		<font class="formType"><%=encoder.encodeForHTML(RfqType.toString(s_rfqType, oid))%> </font><font class="hdr12">#<%=s_form_number%></font>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<table border="0" cellpadding="0" cellspacing="0" width="445px">
			<tr class="mnav" height="18px">
				<td class="mnav" nowrap colspan="2">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "printoptions", "Print Options")%>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table style="text-align: left; width: 100%;" border="1"  cellpadding="0" cellspacing="0">
					  <tbody>
					    <tr>
					      <td style="width: 15%;">
						      <table style="text-align: left; width: 100%;"  border="0" cellpadding="0" cellspacing="0">
						        <tbody>
						          <tr><td height="1px" colspan="2" style="text-align: left; vertical-align: middle; height: 1px;">&nbsp;</td></tr>
						          <tr>
						            <td><input name="printoptions" id="printRFQ" value="P" type="radio" onclick="showPrintOptions()"></td>
						            <td nowrap="nowrap"><label for="printRFQ">RFQ</label>&nbsp;&nbsp;</td>
						          </tr>
						          <tr><td height="1px" colspan="2">&nbsp;</td></tr>
						          <tr>
						            <td><input name="printoptions" id="bidWorkSheet" value="B" type="radio" onclick="showPrintOptions()"></td>
						            <td nowrap="nowrap"><label for="bidWorkSheet">Bid WorkSheet</label>&nbsp;&nbsp;</td>
						          </tr>
								<%	if (printLetters.equalsIgnoreCase("Y")) { %>
						          <tr><td height="1px" colspan="2">&nbsp;</td></tr>
						          <tr>
						            <td><input name="printoptions" id="awardLetter" value="A" type="radio" onclick="showPrintOptions()"></td>
						            <td nowrap="nowrap"><label for="awardLetter">Award Letter</label>&nbsp;&nbsp;</td>
						          </tr>
						          <tr><td height="1px" colspan="2">&nbsp;</td></tr>
						          <tr>
						            <td><input name="printoptions" id="regretsLetter" value="R" type="radio" onclick="showPrintOptions()"></td>
						            <td nowrap="nowrap"><label for="regretsLetter">Regrets Letter</label>&nbsp;&nbsp;</td>
						          </tr>
								<%	} %>
						          <!--<tr>
						            <td style="text-align: left; vertical-align: middle;"><input name="printoptions" value="I" type="radio" onclick="showItaPrintOptions()"></td>
						            <td nowrap="nowrap">Intent To Award&nbsp;&nbsp;</td>
						          </tr>-->
						        </tbody>
						      </table>
					      </td>
					      <td style="width: 65%;" align="center" valign="middle">
							      <table style="text-align: left;" border="0" cellpadding="0" cellspacing="0">
							        <tbody>
										<tr>
											<td>
												<input type="checkbox" value="1" id="chkSuppliers" style="margin-bottom: 0px" onclick="javascript:selectAllSuppliers(this.form)">
												<label for="chkSuppliers">Select All</label><hr>
											</td>
										</tr>
							        	<TR>
							        		<TD>
							        			<div id="suppliersPrint" style="border:none; padding: 0px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
								        			<select size="10" multiple="multiple" name="suppliers" style="border:0; padding:0;" id="listSuppliers" onChange="checkNone(this);">
									        				<option value="none" selected="selected">None</option>
									        				<%for(int vendorIndex = 0; vendorIndex < vendorList.size(); vendorIndex++)
															{
																RfqVendor rfqVendor = (RfqVendor)vendorList.get(vendorIndex);%>
																<option value="<%=rfqVendor.getComp_id().getVendorId() %>"><%=VendorManager.getInstance().getVendorName(oid, rfqVendor.getComp_id().getVendorId()) %></option>
															<%} %>
													</select>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div id="totalsPrint" style="border:none; padding: 0px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
													  <table>
														<tbody>
															<tr>
																<td><input type="checkbox" name="printTotalBx" size="15"/> </td>
																<td>Print Quote Totals</td>
															</tr>
												        </tbody>
												      </table>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div id="suppliersEmail" style="border:none; padding: 0px; align:center; overflow-y:visible; overflow-x:auto; display:none;">
													<table >
														<tbody>
															<tr>
																<td colspan="2"><label for="email">Email To:</label></td>
															</tr>
															<tr>
																<td colspan="2"><input type="text" name="email" id="email" size="43"/> </td>
															</tr>
															<tr>
																<td colspan="2"><label for="notes">Notes:</label>&nbsp;</td>
															</tr>
															<tr>
																<td colspan="2"><textarea name="notes" cols="40" rows="4" id="notes"></textarea></td>
															</tr>
<% if (faxEnabled.equalsIgnoreCase("Y")) { %>
															<tr>
																<td align="right"><label for="faxTo">Fax To:</label>&nbsp;</td>
																<td><input type="text" name="faxTo" id="faxTo" size="35"/></td>
															</tr>
															<tr>
																<td align="right"><label for="faxNumber">Fax #:</label>&nbsp;</td>
																<td><input type="text" name="faxNumber" id="faxNumber" size="35"/></td>
															</tr>
															<tr>
																<td align="right"><label for="faxSubject">Subject:</label>&nbsp;</td>
																<td><input type="text" name="faxSubject" id="faxSubject" size="35"/></td>
															</tr>
<% } else {  %>
															<tr>
																<tsa:hidden name="print_option_fax" value="N" />
																<tsa:hidden name="faxSubject" value="" />
																<tsa:hidden name="faxNumber" value="" />
																<tsa:hidden name="faxTo" value="" />
															</tr>
<% } %>

												        </tbody>
												      </table>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
					      </td>
					      <td style="width: 20%;">
					      <table style="text-align: left; width: 100%;" border="0" cellpadding="0" cellspacing="0">
					        <tbody>
					          <tr>
					            <td nowrap="nowrap"><a href="javascript: emailOption();"><img src="<%=contextPath%>/images/email.gif" border="0" alt="Email" ></a></td>
					            <td><a href="javascript: emailOption();">Email</a></td>
					          </tr>
					          <tr>
					            <td colspan="2" style="width: 100%;">&nbsp;</td>
					          </tr>

<% if (faxEnabled.equalsIgnoreCase("Y")) { %>
					          <tr>
					            <td nowrap="nowrap"><a href="javascript: faxOption();"><img src="<%=contextPath%>/images/fax.gif" border="0" alt="Fax" height="20px"></a></td>
					            <td><a href="javascript: faxOption();">Fax</a></td>
					          </tr>
					          <tr>
					            <td colspan="2" style="width: 100%;">&nbsp;</td>
					          </tr>
  <% } %>
					          <tr>
					            <td nowrap="nowrap"><a href="javascript: printOption('pdf');"><img class="button" src="<%=contextPath%>/images/adobe_pdf.gif" border="0" alt="Print" ></a></td>
					            <td><a href="javascript: printOption('pdf');">Pdf</a></td>
					          </tr>
					          <tr>
					            <td nowrap="nowrap"><a href="javascript: printOption('xls');"><img class="button" src="<%=contextPath%>/images/ms_excel.gif" border="0" alt="Print" ></a></td>
					            <td><a href="javascript: printOption('xls');">Excel</a></td>
					          </tr>

					          <tr>
					            <td colspan="2" style="width: 100%;">&nbsp;</td>
					          </tr>
					          <tr>
					            <td nowrap="nowrap"><a href="javascript: doSubmit('rfq/rfq_review.jsp', 'DoNothing;RfqRetrieve');"><img class="button" src="<%=contextPath%>/images/returnto.gif" border="0" alt="Print" style="background-color: white; float:left"></a></td>
					            <td><a href="javascript: doSubmit('rfq/rfq_review.jsp', 'DoNothing;RfqRetrieve');">Return</a></td>
					          </tr>
					        </tbody>
					      </table>
					      </td>
					    </tr>
					  </tbody>
					</table>

				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<br>
<br>
<tsa:hidden name="viewNow" value="Y" />
<tsa:hidden name="PdfRfq_vendorId" value="<%=RfqHeader_vendorAwarded%>" />

<%--table border="0" cellpadding="0" cellspacing="0" width="455px">
	<TR>
<!--  		<td align="center"><a href="javascript: pdfOptions();"><img class="button" src="<%=contextPath%>/images/button_print.gif" border="0" alt="Print" ></a></td>-->
		<TD align="CENTER"><a href="javascript: doSubmit('rfq/rfq_review.jsp', 'DoNothing;RfqRetrieve');"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0></a></TD>
	</TR>
</TABLE--%>
<br/>
<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

	frm.printoptions[0].checked = true;
	showPrintOptions();

	function printOption(type)
	{
		if(frm.printoptions[1].checked)
		{
			//bidwork
			bidWorkNow(type);
		}
		else if(frm.printoptions[0].checked)
		{
			//print
			if(type == "xls")
			{
				alert("Bid WorkSheet for show the excel file!");
			}
			else
			{
				viewNow();
			}
		}
		else if(frm.printoptions[2].checked)
		{
			//Intent
			//viewItaNow();
			if(type == "xls")
			{
				alert("Bid WorkSheet for show the excel file!");
			}
			else
			{
				letterPdf('Award');
			}
		}
		else if(frm.printoptions[3].checked)
		{
			//Intent
			//viewItaNow();
			if(type == "xls")
			{
				alert("Bid WorkSheet for show the excel file!");
			}
			else
			{
				letterPdf('Regrets');
			}
		}
		else
		{
			alert("Select a Print Option!");
		}
	}

	function emailOption()
	{
		if(frm.printoptions[1].checked)
		{
			//bidwork
			bidWorkEmail();
		}
		else if(frm.printoptions[0].checked)
		{
			//print
			emailPdf();
		}
		else if(frm.printoptions[2].checked)
		{
			//Intent
			//emailItaPdf();
			letterEmail('LTA');
		}
		else if(frm.printoptions[3].checked)
		{
			//Intent
			//emailItaPdf();
			letterEmail('LTR');
		}
		else
		{
			alert("Select a Print Option!");
		}
	}

	function faxOption()
	{
		if(frm.printoptions[1].checked)
		{
			//bidwork
			bidWorkFaxl();
		}
		else if(frm.printoptions[0].checked)
		{
			//print
			faxPdf();
		}
		else if(frm.printoptions[2].checked)
		{
			//Intent
			faxItaPdf();
		}
		else
		{
			alert("Select a Print Option!");
		}
	}

	function pdfOptions()
	{
		popupParameters = "RfqHeader_icRfqHeader=<%=icRfqHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		if(frm.print_option[0].checked)
		{
			frm.emailTo.value = 'N';
			popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
			doSubmitToPopup('', 'Print<%=printtype%>Pdf', 'width=775px', 'height=850px');
		}
		else if(frm.print_option[1].checked)
		{
			if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('rfq/rfq_review.jsp', 'Email<%=printtype%>Pdf;RfqRetrieve');
			}
		}
	}

	function popup()
	{
		popupParameters = "RfqHeader_icRfqHeader=<%=icRfqHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";PdfRfq_vendorId=" + frm.PdfRfq_vendorId.value;
	}

	function viewPdfGetSupplier()
	{
		<%for(int vendorIndex = 0; vendorIndex < vendorList.size(); vendorIndex++)
		{
			RfqVendor rfqVendor = (RfqVendor)vendorList.get(vendorIndex);%>

		<%}%>
	}

	function emailPdf()
	{
		if(selectedSupplier())
		{
			var emailMe = true;
			if(!isEmpty(frm.email.value))
			{
				emailMe = checkemail(frm.email);
			}
			if(emailMe)
			{
				getSuppliers();
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('rfq/rfq_review.jsp', 'EmailRfqPdf;RfqRetrieve');
			}
		}
	}

	function faxPdf()
	{
		if(selectedSupplier())
		{
			if(frm.faxNumber.value.trim().length > 0)
			{
				getSuppliers();
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('rfq/rfq_review.jsp', 'FaxRfqPdf;RfqRetrieve');
			} else {
				alert("Fax number is required to fax this solicitation!");
			}
		}
	}

	function viewNow()
	{
		if(selectedSupplier())
		{
			if(countSuppliers() > 1)
			{
				alert("When viewing Solicitations, only one Supplier can be selected.");
			}
			else
			{
				getSuppliers();
				popup();
				frm.emailTo.value = 'N';
				frm.viewNow.value = 'Y';
				popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
				doSubmitToPopup('', 'PrintRfqPdf', 'width=775px', 'height=850px');
			}
		}
	}

	function bidWorkNow(type)
	{
		if(selectedSupplier("bid"))
		{
			if(countSuppliers() < 1)
			{
				alert("Select at leat one supplier to view.");
			}
			else
			{
				selectedSuppliers = new Array();
				for (var i = 0; i < frm.suppliers.options.length; i++) {
					if (frm.suppliers.options[i].selected)
						selectedSuppliers.push(frm.suppliers.options[i].value);
				}
				getSuppliersSep(",");
				popup();
				frm.emailTo.value = 'N';
				popupParameters = popupParameters + ";format=" + type;
				popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
				popupParameters = popupParameters + ";selectedSuppliers=" + selectedSuppliers;
				doSubmitToPopup('/system/popupDocAttachment.jsp', 'PrintWrkPdf', 'width=775px', 'height=850px');
			}
		}
	}

	function letterPdf(letter)
	{
		if (selectedSupplier("bid"))
		{
			if (countSuppliers() > 1)
			{
				alert("When viewing letters, only one Supplier can be selected.");
			}
			else
			{
				getSuppliers();
				popup();
				frm.emailTo.value = 'N';
				frm.viewNow.value = 'Y';
				popupParameters = popupParameters + "letter=" + letter;
				popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
				doSubmitToPopup('', 'PrintRfqLetter', 'width=775px', 'height=850px');
			}
		}
	}

	function letterEmail(letter)
	{
		if (selectedSupplier("bid"))
		{
			var emailMe = true;
			if (!isEmpty(frm.email.value))
			{
				emailMe = checkemail(frm.email);
			}
			if (emailMe)
			{
				getSuppliers();
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				frm.printtype.value = letter;
				doSubmit('rfq/rfq_review.jsp', 'EmailRfqLetter;RfqRetrieve');
			}
			else
			{
				alert("Please enter a valid Email Address.");
			}
		}
	}

	function bidWorkEmail()
	{
		if(selectedSupplier("bid"))
		{
			var emailMe = true;
			if(!isEmpty(frm.email.value))
			{
				emailMe = checkemail(frm.email);
			}
			else
			{
				alert("Please enter a Valid Email Address.");
				emailMe = false;
			}
			if(emailMe)
			{
				getSuppliers();
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				frm.printtype.value = 'Wrk';
				doSubmit('rfq/rfq_review.jsp', 'EmailWrkPdf;RfqRetrieve');
			}
		}
	}

	function bidWorkFax()
	{
		if(selectedSupplier("bid"))
		{
			if(frm.faxNumber.value.trim().length > 0)
			{
				getSuppliers();
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				frm.printtype.value = 'Wrk';
				doSubmit('rfq/rfq_review.jsp', 'FaxWrkPdf;RfqRetrieve');
			} else {
				alert("Fax number is required to fax this Worksheet!");
			}
		}
	}

	function selectedSupplier(bid)
	{
		var element = frm.suppliers;
		selectedUser = false;
		for(i = element.options.length; i > 0 ; i--)
  		{
    			if(element.options[i - 1].selected == true)
    			{
    				if(bid == "bid")
    				{
    					if(element.options[i - 1].value == "none")
    					{
    						selectedUser = false;
    					}
					else
					{
						selectedUser = true;
					}
    				}
    				else
    				{
    					selectedUser = true;
    				}
    			}
  		}
  		if(!selectedUser)
  		{
  			alert("You must first select a Supplier.");
  			return false;
  		}
		return true;
	}

	function countSuppliers()
	{
		var element = frm.suppliers;
		var count = 0;
		for(i = element.options.length; i > 0 ; i--)
  		{
    		if(element.options[i - 1].selected == true)
    		{
    			count = count + 1;
    		}
  		}

  		return count;
	}

	function getSuppliers()
	{
		var supplierIds = '';
		var element = frm.suppliers;

		for(i = 0; i < element.length; i++)
  		{
  			if(element.options[i].selected == true)
    		{
  				supplierIds = supplierIds + frm.suppliers[i].value + ";";
  			}
  		}
  		frm.PdfRfq_vendorId.value = supplierIds;
	}

	function getItaSuppliers()
	{
		var supplierIds = '';
		var element = frm.suppliers;

		for(i = 0; i < element.length; i++)
  		{
  			supplierIds = supplierIds + frm.suppliers[i].value + ";";
  		}
  		frm.PdfRfq_vendorId.value = supplierIds;
	}

	function getSuppliersSep(sep)
	{
		var supplierIds = '';
		var element = frm.suppliers;

		for(i = 0; i < element.length; i++)
  		{
  			if(element.options[i].selected == true)
    		{
  				supplierIds = supplierIds + frm.suppliers[i].value + sep;
  			}
  		}
  		frm.PdfRfq_vendorId.value = supplierIds;
	}

	function showPrintOptions()
	{
		displayArea('suppliersPrint');
		displayArea('suppliersEmail');
		hideArea('totalsPrint');
	}

	function showItaPrintOptions()
	{
		hideArea('suppliersPrint');
		displayArea('suppliersEmail');
		displayArea('totalsPrint');
	}

	function viewItaNow()
	{
		showPrintTotals();

		popup();
		popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
		popupParameters = popupParameters + ";printTotal=" + frm.printTotal.value;
		frm.printtype.value = 'Ita';
		doSubmitToPopup('', 'PrintItaPdf', 'width=775px', 'height=850px');
	}

	function emailItaPdf()
	{
			var emailMe = true;
			emailMe = checkemail(frm.email);
			if(emailMe)
			{
				getItaSuppliers();
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				frm.printtype.value = 'Ita';
				doSubmit('rfq/rfq_review.jsp', 'EmailItaPdf;RfqRetrieve');
			}
	}

	function faxItaPdf()
	{
			if(frm.faxNumber.value.trim().length > 0)
			{
				getItaSuppliers();
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				frm.printtype.value = 'Ita';
				doSubmit('rfq/rfq_review.jsp', 'FaxItaPdf;RfqRetrieve');
			} else {
				alert("Fax number is required to fax this Intent to award!");
			}
	}

	function showPrintTotals()
	{
		if(frm.printTotalBx.checked){
			frm.printTotal.value = 'Y';
		}
		else
		{
			frm.printTotal.value = 'N';
		}
	}
	
	function checkNone(mySelect)
	{
		if(mySelect.length==1)
		{
			return;
		}		
	
		for(i=1; i<mySelect.length; i++)
   		{
   			if(mySelect[i].selected)
   			{
   				mySelect[0].selected=false;
   				return;
   			}    			
		} 			
	}
														
// end hiding contents -->
</SCRIPT>

<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/header.jsp"%>
<%@ page import="com.tsa.puridiom.steporder.*"%>
<%@ page import="com.tsa.puridiom.common.documents.OrderType"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="com.tsagate.foundation.utility.Utility"%>
<%@page import="com.tsa.puridiom.vendor.VendorManager"%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	String s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	String s_max_length_UDF8 = propertiesManager.getProperty("PO OPTIONS", "MaxLengthUDF8Contract", "6");
	String s_po_number = poHeader.getPoNumber();
	BigDecimal bd_ic_po_header = poHeader.getIcPoHeader();
	String s_ic_po_header = bd_ic_po_header.toString();
	BigDecimal bd_ic_header_history = poHeader.getIcHeaderHistory();
	BigDecimal bd_release_number = poHeader.getReleaseNumber();
	BigDecimal bd_revision_number = poHeader.getRevisionNumber();
	BigDecimal bd_zero = new BigDecimal(0);
	String s_po_status = poHeader.getStatus();
	String s_po_type = poHeader.getPoType();
	String s_poType = (String) request.getAttribute("PoHeader_poType");
	String errorMsg = (String) request.getAttribute("errorMsg");
	String s_current_process = "CONTRACT_NAMING";
	String s_current_page = "/orders/ct_contract_naming.jsp";
//	String s_current_method = "PoContractNamingUpdate";
	String s_current_method = "PoHeaderUpdate";
	//String s_current_method = "DoNothing";
	String s_current_process_method = "";
	String s_buyer_code = poHeader.getBuyerCode();

	String s_vendor_name = VendorManager.getInstance().getVendorName(oid, poHeader.getVendorId());

	String fromRevision = (String)request.getAttribute("fromRevision");

	int i_line_count = 0;
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}

	String s_ct_prefix = PropertiesManager.getInstance(oid).getProperty("PO TYPES", "CT_PREFIX", "");
	String s_ct_number = poHeader.getPoNumber().replaceAll(s_ct_prefix, "");
	String s_ct_vendor = "";
	String s_ct_csa = "";
	String s_ct_secondContract = "";

	if (Utility.isEmpty(s_ct_vendor))
	{
		s_ct_vendor = poHeader.getVendorId();
	}

	String	s_receipt_required = poHeader.getReceiptRequired();


	String isFoundation = poHeader.getUdf7Code();
	if (isFoundation.equals("Y"))
	{
		s_ct_vendor = poHeader.getUdf8Code();
	}
	else
	{
		s_ct_vendor = poHeader.getVendorId();
	}

	if (Utility.isEmpty(s_ct_vendor))
	{
		s_ct_vendor = "";
	}

	s_ct_csa = poHeader.getUdf9Code();

	s_ct_secondContract = poHeader.getUdf10Code();

	if ( fromRevision == null || fromRevision.compareTo("Y")!= 0 )
	{
		if (Utility.isEmpty(s_ct_csa))
		{
			s_ct_csa = "C";
		}
		if (Utility.isEmpty(s_ct_secondContract))
		{
			s_ct_secondContract = "01";
		}
	}


%>

<%@ include file="/orders/po_hidden_fields.jsp"%>

<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
			<tr>
				<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
			</tr>
			<tr>
				<td nowrap class=hdr12 valign=middle>
				<div style="margin-left: 10px; margin-right: 10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contractnaming", "Contract Naming", false)%></div>
				</td>
			</tr>
		</table>
		</td>
		<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
		<td valign=bottom align=right height=30px><%@ include file="/orders/po_display_number.jsp"%></td>
	</tr>
</table>


<table border=0 cellpadding="0" cellspacing="0" width="680px">
	<tr>
		<!-- CONTRACT NAMING CONTENT BEGIN -->
		<td align="left" valign="top" width="520px">
		<div style="clear: both; margin-top: 15px;">
		<table align="center" border="0">
			<tr>
				<td align="right" width="50%"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ct-vendorName", "Vendor#/Name",true)%>:</td>
				<td width="50%"><input type="text" name="PoHeader_udf8Code" value="<%=s_ct_vendor%>" size="9" maxlength="5" onchange="upperCase(this);updateModuleId()" tabindex="10" disabled="disabled" />
				Foundation Supplier: <input type="checkbox" id="check_Vendor" name="check_Vendor" <% if ( isFoundation.equals("Y")){%>CHECKED<%}%> onclick="checkedVendorName(PoHeader_udf8Code,this);setFlagFromCkbox(this,frm.PoHeader_udf7Code)" /></td>
				<tsa:hidden name="PoHeader_udf7Code" value=""/>

			</tr>
			<tr>
				<td align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contract", "Contract #",true)%>:</td>
				<td><input type="text" name="PoHeader_ctNumber" value="<%=s_ct_number%>" size="9" tabindex="20" readonly disabled /></td>
			</tr>
			<tr>
				<td align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ct-csa", "C/S/A/SA/AA",true)%>:</td>
				<td>
				<select tabindex="29" name="PoHeader_udf9Code" onchange="updateModuleId()" tabindex="30">
					<option <% if (s_ct_csa.equals("")) {%> SELECTED <%}%> value=""></option>
					<option <% if (s_ct_csa.equals("C")){%> selected <%}%> value="C">C</option>
					<option <% if (s_ct_csa.equals("S")){%> selected <%}%> value="S">S</option>
					<option <% if (s_ct_csa.equals("A")){%> selected <%}%> value="A">A</option>
					<option <% if (s_ct_csa.equals("SA")){%> selected <%}%> value="SA">SA</option>
					<option <% if (s_ct_csa.equals("AA")){%> selected <%}%> value="AA">AA</option>
				</select></td>

			</tr>
			<tr>
				<td align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ct-secondContractID", "Second Contract ID", true)%>:</td>
				<td><input type="text" name="PoHeader_udf10Code" value="<%=s_ct_secondContract%>" size="6" maxlength="5" onchange="updateModuleId()" tabindex="40" disabled="disabled" /></td>
			</tr>

			<tr>
				<td align="right">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ct-contractRecordsModuleID", "CONTRACTS RECORDS MODULE ID")%>:</td>
				<!--				<td id="ct_moduleId"></td>-->
				<td id=module></td>
				<td><tsa:hidden name="PoHeader_contractNo" value=""/></td>
			</tr>
			<% if ( fromRevision!=null && fromRevision.compareTo("Y")== 0 ) { %>
			<tr>
				<td align="right">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><a href="javascript: poSave();browse('po_reqbrowseimp');">Create from a Requisition</a></td>
			</tr>
			<% } %>
		</table>
		</div>
		</td>
		<!-- CONTRACT NAMING CONTENT END -->

		<!-- SIDE BAR BEGIN -->
		<td align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp"%></td>
		<!-- SIDE BAR END -->
	</tr>
</table>

<%@ include file="/system/footer.jsp"%>

<SCRIPT value=JavaScript>
<!-- Hide script
  frm = document.purchaseform;

  var ponumber = "<%=s_po_number%>";
  var fiscalyear = "<%=poHeader.getFiscalYear()%>";
  var currentpage = "<%=s_current_page%>";
  var currentmethod = "<%=s_current_method%>";
  var currentprocessmethod = "<%=s_current_process_method%>";
  var fromRevision = "<%=fromRevision%>";
  var oid = "<%=oid%>";
  var poType = "<%=s_po_type%>";

  function poSave()
	{
		if (isNA(ponumber))
		{
			popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentmethod + "/" + currentprocessmethod;
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
		else
		{
			if (poType == 'CT' && oid == 'BSC04P' && fromRevision == 'Y')
			{
			   	var newInputField = "<input type='hidden' name='fromRevision' value='Y'>" ;
				setHiddenFields(newInputField);
			}
			//doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
			doSubmit(currentpage, currentmethod);
		}
	}


	function checkedVendorName(inputText, ckbox)
  {
  		if (ckbox.checked == true)
		{
  			inputText.disabled=false;
  		}
  		else
  		{
  			inputText.value="<%=poHeader.getVendorId()%>";
			inputText.disabled=true;
  		}
  }


	function updateModuleId()
	{
  		if (frm.check_Vendor.checked == true)
		{
  			frm.PoHeader_udf8Code.disabled=false;
  		}
  		else
  		{
  			frm.PoHeader_udf8Code.disabled=true;
  		}



		if( frm.PoHeader_udf9Code.value != "C")
		{
				frm.PoHeader_udf10Code.disabled = false;
		}
		else
		{
				frm.PoHeader_udf10Code.disabled = true;
				frm.PoHeader_udf10Code.value = '01';
		}

		var moduleId;
		var poUdf8Code = frm.PoHeader_udf8Code.value;

		moduleId = 	poUdf8Code.substr(0, <%= s_max_length_UDF8 %>) + "-" +
		 	frm.PoHeader_ctNumber.value + "-" +
		 	frm.PoHeader_udf9Code.value + "-" +
		 	frm.PoHeader_udf10Code.value;
			document.getElementById("module").innerHTML = moduleId;
			frm.PoHeader_contractNo.value = moduleId;
	}

	function thisLoad() {
		f_StartIt();
		updateModuleId();
	}




// End Hide script -->
</SCRIPT>

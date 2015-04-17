<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/budget.js"></SCRIPT>

<%
	PropertiesManager	propertiesManager	= PropertiesManager.getInstance(oid);
	BudgetCenter		budgetCenter		= (BudgetCenter) request.getAttribute("budgetCenter");
	List				budgetDrawerList	= (List) request.getAttribute("budgetDrawerList");
	if (budgetDrawerList==null)
		budgetDrawerList = new ArrayList();

	String	labeludf[]	= new String[16];
			labeludf[0]	= propertiesManager.getProperty("BUDGET","LABEL UDF1","");
			labeludf[1]	= propertiesManager.getProperty("BUDGET","LABEL UDF2","");
			labeludf[2]	= propertiesManager.getProperty("BUDGET","LABEL UDF3","");
			labeludf[3]	= propertiesManager.getProperty("BUDGET","LABEL UDF4","");
			labeludf[4]	= propertiesManager.getProperty("BUDGET","LABEL UDF5","");
			labeludf[5]	= propertiesManager.getProperty("BUDGET","LABEL UDF6","");
			labeludf[6]	= propertiesManager.getProperty("BUDGET","LABEL UDF7","");
			labeludf[7]	= propertiesManager.getProperty("BUDGET","LABEL UDF8","");
			labeludf[8]	= propertiesManager.getProperty("BUDGET","LABEL UDF9","");
			labeludf[9]	= propertiesManager.getProperty("BUDGET","LABEL UDF10","");
			labeludf[10]= propertiesManager.getProperty("BUDGET","LABEL UDF11","");
			labeludf[11]= propertiesManager.getProperty("BUDGET","LABEL UDF12","");
			labeludf[12]= propertiesManager.getProperty("BUDGET","LABEL UDF13","");
			labeludf[13]= propertiesManager.getProperty("BUDGET","LABEL UDF14","");
			labeludf[14]= propertiesManager.getProperty("BUDGET","LABEL UDF15","");

	String	BudgetCenter_budget[]	= new String[16];
	for (int x=0; x<15; x++) {
		BudgetCenter_budget[x] = "";
	}

	String action = "new";

	if (budgetCenter!=null) {
		action = "old";
		BudgetCenter_budget[0]	= budgetCenter.getBudget1();
		BudgetCenter_budget[1]	= budgetCenter.getBudget2();
		BudgetCenter_budget[2]	= budgetCenter.getBudget3();
		BudgetCenter_budget[3]	= budgetCenter.getBudget4();
		BudgetCenter_budget[4]	= budgetCenter.getBudget5();
		BudgetCenter_budget[5]	= budgetCenter.getBudget6();
		BudgetCenter_budget[6]	= budgetCenter.getBudget7();
		BudgetCenter_budget[7]	= budgetCenter.getBudget8();
		BudgetCenter_budget[8]	= budgetCenter.getBudget9();
		BudgetCenter_budget[9]	= budgetCenter.getBudget10();
		BudgetCenter_budget[10]	= budgetCenter.getBudget11();
		BudgetCenter_budget[11]	= budgetCenter.getBudget12();
		BudgetCenter_budget[12]	= budgetCenter.getBudget13();
		BudgetCenter_budget[13]	= budgetCenter.getBudget14();
		BudgetCenter_budget[14]	= budgetCenter.getBudget15();
	} else {
		budgetCenter = new BudgetCenter();
	}

	String authority	= propertiesManager.getProperty("BUDGET","AUTHORITY","");
	String authorityudf	= propertiesManager.getProperty("BUDGET","AUTHORITY_UDF","");
	UserProfile owner = UserManager.getInstance().getUser(oid, uid);

	List accountList = (List) request.getAttribute("accountList");
	boolean budgetInUse = false;
	if (accountList != null && accountList.size() > 0)
		budgetInUse = true;
%>

<tsa:hidden name="allowBrowse" value="true"/>

<tsa:hidden name="BudgetCenter_budgetId" value="<%=budgetCenter.getBudgetId()%>"/>
<tsa:hidden name="Deleted_authTypes" value=""/>
<tsa:hidden name="Deleted_authorities" value=""/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<!--In this part we have a function to attach a document-->
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle align="left"><div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budget", "Budget Administration")%></div></td>
<!--
			<td nowrap class=hdr12 valign=middle align="right"><div style="margin-left:10px; margin-right:10px" class=hdr12><%=budgetCenter.getBudgetId()%></div></td>
-->
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="100%" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br/>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="600px">
    <tr>
      <td align="center" valign="top">
      	<%@ include file="/budget/budget_data.jsp" %>
      </td>
	</tr>
	</table>
  </td>
</tr>
</table>

<br/>

<table border=0 cellspacing=0 cellpadding=2 width="680px">
<tr>
		<td align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align="center">
				<% if(action.equalsIgnoreCase("new")) {%>
				<a href="javascript: addBudgetCenter(); void(0);">
				<img class=button src="<%=contextPath%>/images/button_save.gif" border=0>
				</a>
				<% } else if (action.equalsIgnoreCase("old")) {%>
				<a href="javascript: updateBudgetCenter(); void(0);">
				<img class=button src="<%=contextPath%>/images/button_save.gif" border=0>
				</a>
				<% } %>
			</td>
			<td align="center">
				<!-- <a href="javascript: doSubmit('admin/budget/budget_menu.jsp','DoNothing'); void(0);"> -->
				<a href="javascript: browse('budget_center'); void(0);">
				<img class=button src="<%=contextPath%>/images/button_return.gif" border=0>
				</a>
			</td>
		</tr>
		</table>
		</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script value=JavaScript>
<!-- Hide script

var frm = document.purchaseform;
var updPos = -1;

setBalance();

function setBalance() {
	var butgeted	= frm.BudgetCenter_budgeted.value;
	var values		= eval(frm.BudgetCenter_preEncumbered.value) + eval(frm.BudgetCenter_encumbered.value) + eval(frm.BudgetCenter_expensed.value);
	frm.BudgetCenter_balance.value = eval(butgeted)-eval(values);
	formatPrice(frm.BudgetCenter_balance);
}

function setValues(obj) {
	formatPrice(obj);
	setBalance();
}

function addBudgetCenter() {
	doSubmit('/budget/budget_review.jsp', 'BudgetCenterAdd;BudgetCenterRetrieveById');
}

function updateBudgetCenter() {
	if( orgId == "HOY08P" )
	{
		doSubmit('/budget/budget_validate_no_popup.jsp', 'BudgetCenterUpdate;BudgetCenterRetrieveById;BudgetValidate');
	}
	else
	{
		doSubmit('/budget/budget_review.jsp', 'BudgetCenterUpdate;BudgetCenterRetrieveById');
	}
}

function addAuthorities() {
	var lenghtAuthority=10;
	<%if(oid.equalsIgnoreCase("DTN07P")){%>
		lenghtAuthority=100;
	<%}%>
	if (eval(frm.qtyAuthorities.value) < lenghtAuthority) {
			frm.BudgetDrawerOP_type.disabled = false;
			frm.BudgetDrawerOP_authority.disabled = false;
			frm.BudgetDrawerOP_status.disabled = false;
			updPos = -1;
		}

}

function saveAuthority() {
	if (frm.BudgetDrawerOP_authority.value != "") {
		var qty = eval(frm.qtyAuthorities.value);
		if (updPos == -1) {
			updPos = qty;
			qty++;
		}
		frm.BudgetDrawer_type[updPos].value = frm.BudgetDrawerOP_type.value;
		frm.BudgetDrawer_authority[updPos].value = frm.BudgetDrawerOP_authority.value;
		frm.BudgetDrawer_status[updPos].value = frm.BudgetDrawerOP_status.value;
		displayArea('aut'+updPos);
		frm.qtyAuthorities.value = qty;
	}
	frm.BudgetDrawerOP_type.disabled = true;
	frm.BudgetDrawerOP_authority.disabled = true;
	frm.BudgetDrawerOP_status.disabled = true;
	frm.BudgetDrawerOP_type.value = "<%=authority%>";
	frm.BudgetDrawerOP_authority.value = "";
	frm.BudgetDrawerOP_status.value = "01";
 }

function cancelAuthority() {
	frm.BudgetDrawerOP_type.disabled = true;
	frm.BudgetDrawerOP_authority.disabled = true;
	frm.BudgetDrawerOP_status.disabled = true;
	frm.BudgetDrawerOP_type.value = "<%=authority%>";
	frm.BudgetDrawerOP_authority.value = "";
	frm.BudgetDrawerOP_status.value = "01";
}

function updateAuthority(pos) {
	if (eval(frm.qtyAuthorities.value) <= 100) {
		frm.BudgetDrawerOP_type.disabled = false;
		frm.BudgetDrawerOP_authority.disabled = false;
		frm.BudgetDrawerOP_status.disabled = false;
		frm.BudgetDrawerOP_type.value = frm.BudgetDrawer_type[pos].value;
		frm.BudgetDrawerOP_authority.value = frm.BudgetDrawer_authority[pos].value;
		frm.BudgetDrawerOP_status.value = frm.BudgetDrawer_status[pos].value;
		updPos = pos;
	}
}

function deleteAuthority(pos) {
	var qty = eval(frm.qtyAuthorities.value);
	frm.Deleted_authTypes.value = frm.Deleted_authTypes.value + frm.BudgetDrawer_type[pos].value + "," ;
	frm.Deleted_authorities.value = frm.Deleted_authorities.value + frm.BudgetDrawer_authority[pos].value + "," ;
	for(x=pos;x<qty;x++) {
		frm.BudgetDrawer_type[x].value = frm.BudgetDrawer_type[x+1].value;
		frm.BudgetDrawer_authority[x].value = frm.BudgetDrawer_authority[x+1].value;
		frm.BudgetDrawer_status[x].value = frm.BudgetDrawer_status[x+1].value;
	}
	qty--;
	hideArea('aut'+qty);
	frm.qtyAuthorities.value = qty;
}

function browseAuditTtrail()
	{
		popupParameters = "browseName=budget_tran";
		popupParameters = popupParameters + ";formField=";
		popupParameters = popupParameters + ";colname=BudgetTran_budgetId;operator==;filter_txt=<%=budgetCenter.getBudgetId() %>;logicalOperator=AND;originalFilter=N;sort=N;"
		doSubmitToPopup('browse/browse_popup.jsp', 'BudgetTranDeletePre;BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
	}

// End Hide script -->
</script>
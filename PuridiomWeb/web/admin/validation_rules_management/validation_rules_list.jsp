<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.rule.ui.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%@ page import="org.owasp.esapi.Encoder" %>

<%
 String ruleFileName = "";
 if(request.getAttribute("ruleFileName")!=null)
 {
	 ruleFileName = (String)request.getAttribute("ruleFileName");
	 System.out.println("ruleFileName = " + ruleFileName);
 }

 List rulesList = null;
 if (request.getAttribute("rulesList")!=null)
 {
	 rulesList = new ArrayList();
	 rulesList = (List)request.getAttribute("rulesList");
	 System.out.println("rulesList = " + rulesList);
 }

 String formType = "req";
 if (request.getAttribute("formType")!=null)
 {
	 formType = (String)request.getAttribute("formType");
	 System.out.println("formType = " + formType);
 }

 String textLink="";
 if (request.getAttribute("formType").equals("REQ")) {
	 textLink="Requisition";
 }
 else if (request.getAttribute("formType").equals("PO")) {
	 textLink="Purchase Orders";
 }
 else if (request.getAttribute("formType").equals("RFQ")) {
	 textLink="Solicitations";
 }
 else if (request.getAttribute("formType").equals("INV")) {
	 textLink="Invoice";
 }
 else if (request.getAttribute("formType").equals("VEN")) {
	 textLink="Vendor";
 }
 else if (request.getAttribute("formType").equals("RECPKG")) {
	 textLink="Receipt Package";
 }
 else if (request.getAttribute("formType").equals("RECRET")) {
	 textLink="Receipt Return";
 }
 else if (request.getAttribute("formType").equals("REC")) {
	 textLink="Receipt";
 }

%>

<table border=1 cellpadding=2 cellspacing=0 width="680px" >
			<tr>
				<% Encoder encoder = DefaultEncoder.getInstance(); %>
			 	 <%for(int i = 0; i < rulesList.size(); i++){
			 		RuleElement rule = (RuleElement)rulesList.get(i);
			 		String ruleAlias = rule.getAlias();
			 	 %>

					 <tr>
					 	<tsa:hidden name="alias" value="<%=ruleAlias%>"/>
					 	<td>
					 	<div id="divRulesSetupHidden<%=i%>" style="display:block;">
					 	<table border=1 cellpadding=2 cellspacing=0 width="680px" >
					 	<tr>
								<td nowrap width="100%" aling = "left">
									<a href="javascript: showDivRulesSetup(0,<%=i%>); void(0);"><img src="<%=contextPath%>/images/cmd_maximize.gif" alt="Show." valign="bottom" aling = "right" border="0"></a>
									<a href="javascript: showDivRulesSetup(0,<%=i%>); void(0);"><%=ruleAlias%></a>
								</td>
								<td width="33%" aling = "right">
								</td>
							</tr>
					 	</table>
					 	</div>
					 	<div id="divRulesSetupShow<%=i%>" style="display:none;">
							<table border=1 cellpadding=2 cellspacing=0 width="680px" >
							<tr>
								<td nowrap width="100%" align = "left">
									<a href="javascript: showDivRulesSetup(1,<%=i%>); void(0);"><img src="<%=contextPath%>/images/cmd_minimize.gif" alt="Show." valign="bottom" aling = "right" border="0"></a>
									<a href="javascript: showDivRulesSetup(1,<%=i%>); void(0);"><%=ruleAlias%></a>
								</td>
								<td width="33%" align = "right">
								</td>
							</tr>
							</table>
							<table border="1" cellpadding=2 cellspacing=0 width="680px">
						  		<tr>
						  			<td width="20%">File Name:</td>
						  			<td><input type="text" name="filename" value="<%=rule.getFileName()%>" size="30"></td>
						  		</tr>
						  		<tr>
						  			<td width="20%">Mesage:</td>
						  			<td><textarea name="msg" cols="80" rows="3"><%= encoder.encodeForHTML(rule.getMsg()) %></textarea></td>
						  		</tr>
						  		<tr>
						  			<td width="20%">Severity:</td>
						  			<td><select name="severity" size="1">
					                        <option <% if (rule.getSeverity().equals("E")) {%> SELECTED <%}%> value="E">Error</option>
					                        <option <% if (rule.getSeverity().equals("W")) {%> SELECTED <%}%> value="W">Warning</option>
					                    </select>
						  			<!--<tsa:hidden name="severity" value="<%=rule.getSeverity()%>"/></td>-->
						  		</tr>
						  		<tr>
						  			<td width="20%">Order:</td>
						  			<td><input type="text" name="order" value="<%=rule.getOrder()%>"></td>
						  		</tr>
						  		<tr>
						  			<td width="20%">Enabled:</td>
						  			<td><select name="enabled" size="1">
					                        <option <% if (rule.isEnabled()) {%> SELECTED <%}%> value="Y">Yes</option>
					                        <option <% if (!rule.isEnabled()) {%> SELECTED <%}%> value="N">No</option>
					                    </select>
					                    <!--<tsa:hidden name="enabled" value="<%=rule.isEnabled()%>"/></td>-->
					                </td>
						  		</tr>
						  	</table>
					   </div>
    			   </td>
			   </tr>
		   <%}%>
	   </tr>
</table>
<tsa:hidden name="as_organizationId" value="<%=oid%>"/>
<tsa:hidden name="formType" value="<%=formType%>"/>
<tsa:hidden name="ruleFileName" value="<%=ruleFileName%>"/>

<table width=690px cellpadding=0 cellspacing=0 border=1>
<tr width="100%">
	<td width="33%">
		<!--<a href="javascript: addNewRule(); void(0);"><img aling = "center" class=button src="<%//=contextPath%>/images/button_add.gif" alt="Add New Rule"  border="0"></a>-->
	</td>
	<% if (role.getAccessRights("VALIDATIONRULES") >= 3 ) { %>
	<td width="33%" aling = "center">
		<a href="javascript: saveRules(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" alt="Save Rules"  border="0"></a>
	</td>
	<% } %>
	<td width="33%" aling = "center">
		<a href="javascript: returnToAdmin(); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" alt="Return"  border="0"></a>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
  var frm = document.purchaseform;
  setNavCookie("", "DoNothing", "<%=textLink %>");

  function checkOption()
  {
   	frm.organizationId.value = frm.as_organizationId.value;
   	frm.formType.value = frm.form_option.value;
  }

  function updateRules()
  {
    doSubmit('tsaadmin/validation-rules-list.jsp', 'RuleListerUpdater;RuleLister');
  }

  function submitThis()
  {
    doSubmit('/admin/validation_rules_management/validation_rules_list.jsp', 'RuleLister');
  }

  function saveRules()
  {
    doSubmit('/admin/validation_rules_management/validation_rules_list.jsp', 'RuleListerUpdater;RuleLister');
  }


  function showDivRulesSetup(flg,ind){
	switch (flg)
	{
		case 0:
		hideArea('divRulesSetupHidden'+ind); displayArea('divRulesSetupShow'+ind); break;
		case 1:
		displayArea('divRulesSetupHidden'+ind); hideArea('divRulesSetupShow'+ind); break;
	}
  }

  function returnToAdmin()
  {
	doSubmit('admin/validation_rules_management/validation_rules_management_menu.jsp','DoNothing');
  }

//-->
</script>
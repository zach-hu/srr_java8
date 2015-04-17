<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<input type=text name="RequisitionHeader_icReqHeader" value="472282000000">
<input type=text name="RequisitionLine_icReqLine" value="585347111112">
<br>
<a href="javascript: testSingleHandler(); void(0);">TEST SINGLE HANDLER</a>
<br>
<a href="javascript: testMultipleHandler(); void(0);">TEST MULTIPLE HANDLER</a>
<br >
<a href="javascript: testHqlHandler(); void(0);">TEST Hql Query</a>
<a href="javascript: testRuleHandler(); void(0);">TEST New Rule</a>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	function testSingleHandler() {
		doSubmit('temp/req_test.jsp','RequisitionRetrieve');
	}
	
	function testMultipleHandler() {
		doSubmit('temp/req_line_test.jsp','RequisitionHeaderRetrieveById;RequisitionLineRetrieveById');
	}
	
	function testHqlHandler() {
		doSubmit('temp/hqltest.jsp','HqlQuery');
	}
	
	function testHqlHandler() {
		doSubmit('temp/ruletest.jsp','RuleTest');
	}
	
// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>

<%@page import="java.math.BigDecimal"%>
<%@page import="com.tsa.service.budget.BudgetServiceJob"%>
<%response.setContentType("text/plain");
String organizationId = (String)request.getAttribute("organizationId");
if(organizationId == null) organizationId = "DTN07P";
BudgetServiceJob  budgetService = BudgetServiceJob.getInstance();
String budgetKey = (String) request.getParameter("budgetKey");
boolean result = budgetService.unlockBudget(budgetKey, new BigDecimal("14846208400080"), "DTN07P");

out.print(result);%>
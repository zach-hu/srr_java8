<%@page import="com.tsa.service.budget.*"%>
<%@ page language="java" errorPage="/system/error.jsp" %>

<%
out.println("start test");
BudgetServiceJob budgetServiceJob = BudgetServiceJob.getInstance();

BudgetWrapper budgetWrapperGood = null;
try
{
	out.println("get and set budgetA for update");
	budgetWrapperGood = (BudgetWrapper)budgetServiceJob.getBudget(true, "budgetA", "userA", "hilton");
	out.println("<br>");
	out.println("locked: " + budgetWrapperGood.isLocked());
	out.println("<br>");
	out.println("Can I udpate with you? " + budgetServiceJob.updateBudget(budgetWrapperGood, "hilton"));

}
catch (BudgetLockedException e)
{
	out.println("<br>");
	out.println("BUdget was Locked");
	out.println("<br>");
	e.printStackTrace();
}
out.println("<br>");
out.println("can I get it again?");
try {
	BudgetWrapper budgetWrapperBad = (BudgetWrapper)budgetServiceJob.getBudget(true, "budgetA", "userA", "hilton");
	out.println("<br>");
	out.println("wow something is not right!");
} catch (BudgetLockedException e) {
	out.println("<br>");
	out.println("I should be here because budget was locked");
}
//********************************************

out.println("<br>");
out.println("if I unlock it can I get it again?");
try
{
	budgetServiceJob.unlockBudget(budgetWrapperGood, "hilton");
	BudgetWrapper budgetWrapperBad = (BudgetWrapper)budgetServiceJob.getBudget(true, "budgetA", "userA", "hilton");
	out.println("<br>");
	out.println("wow something is not right!");
} catch (BudgetLockedException e) {
	out.println("<br>");
	out.println("I should be here because bdget was locked");
}

//********************************************
out.println("<br>");
out.println("can I just look at it?");
try
{
	BudgetWrapper budgetWrapperRead = (BudgetWrapper)budgetServiceJob.getBudget(false, "budgetA", "userA", "hilton");
	out.println("<br>");
	out.println("I should be able to look at it and it should not be locked");
	out.println("<br>");
	out.println("locked: " + budgetWrapperRead.isLocked());
	out.println("<br>");
	out.println("Can I udpate with you? " + budgetServiceJob.updateBudget(budgetWrapperRead, "hilton"));
} catch (BudgetLockedException e)
{
	out.println("<br>");
	out.println("wow something is not right! I just wanted to look");
}
out.println("<br>");
out.println("end test");
%>
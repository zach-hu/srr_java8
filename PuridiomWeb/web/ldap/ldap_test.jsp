<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import=" com.tsagate.foundation.service.properties.*" %>

<%
   LDAPProperties ldap = LDAPProperties.getInstance() ;
%>

<HTML>

  <FORM METHOD=POST ACTION="ldap_authenticate_test.jsp">
    <table width=100%>
      <tr><td width=12% align="right">Orgainization:</td><td><input type="text" name = "organization" value="<%=ldap.getLDAPOrganization() %>"/></td></tr>
      <tr><td width=12% align="right">Host:</td><td><input type="text" name = "ldaphost" value="<%=ldap.getLDAPHost() %>"/></td></tr>
      <tr><td width=12% align="right">Alternate Host:</td><td><input type="text" name="ldapalternatehost" value="<%=ldap.getLDAPAlternateHost() %>"/></td></tr>
      <tr><td width=12% align="right">Port:</td><td><input type="text" name="ldapport" value="<%=ldap.getLDAPPort() %>"/></td></tr>
      <tr><td width=12% align="right">Search Base:</td><td><input type="text" name="ldapsearch" value="<%=ldap.getLDAPSearchbase() %>" size=120/></td></tr>
      <tr><td></td><td></td></tr>
      <tr><td width=12% align="right">Timeout</td><td><input type="text" name="ldaptimeout" value="<%=ldap.getLDAPTimeOut() %>"/></td></tr>
      <tr><td width=12% align="right">Logging</td><td><input type="text" name="ldaplogging" value="<%=ldap.getLDAPLogging() %>"/></td></tr>
      <br>
      <tr><td width=12% align="right">Connect User:</td><td><input type="text" name="ldapconnectuser" value="<%=ldap.getLDAPConnectUser() %>"/></td></tr>
      <tr><td width=12% align="right">Connect PW:</td><td><input type="password" name="ldapconnectpw" autocomplete="off" value=""/></td></tr>
      <br>
      <tr><td width=12% align="right">LDAP login attribute:</td><td><input type="text" name="ldaploginattr" value="<%=ldap.getLDAPLoginAttr() %>"/></td></tr>
      <br>
      <tr><td width=12% align="right">Login Id:</td><td><input type="text" name="authid"/></td></tr>
      <tr><td width=12% align="right">Password:</td><td><input type="password" name="authpw" autocomplete="off"/></td></tr>
    </table>
    <input type="submit" value="Logon"/>
  </FORM>
</HTML>

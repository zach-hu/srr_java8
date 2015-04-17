<HTML>
  <FORM METHOD=POST ACTION="http://localhost:8080/puridiom/sso_test_login.jsp">
    <table>
      <tr><td>LoginId</td><td><input type="text" name = "LoginID"/></td></tr>
      <tr><td>Organization</td><td><input type="text" name="Organization"/></td></tr>
      <tr><td>ErrorURL</td><td><input type="text" name="ErrorURL"/></td></tr>
      <tr><td>ReturnURL</td><td><input type="text" name="ReturnURL"/></td></tr>
      <tr><td>FirstName</td><td><input type="text" name="FirstName"/></td></tr>
      <tr><td>LastName</td><td><input type="text" name="LastName"/></td></tr>
      <tr><td>Email</td><td><input type="text" name="Email"/></td></tr>
      <tr><td>Title</td><td><input type="text" name="Title"/></td></tr>
      <tr><td>DivisionCode</td><td><input type="text" name="DivisionCode"/></td></tr>
      <tr><td>Location</td><td><input type="text" name="Location"/></td></tr>
      <tr></tr>
      <tr><td>Login Status</td><td><input type="text" name="LoginStatusCode"/></td></tr>
      <tr><td>Login Description</td><td><input type="text" name="LoginStatusDesc"/></td></tr>
    </table>
    <input type="submit" value="Logon"/>
  </FORM>
</HTML>

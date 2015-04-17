<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.xlsdata.XlsUploadType" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String uploadType = XlsUploadType.BUDGET_CENTER + ";" + XlsUploadType.BUDGET_DRAWER;
%>
<tsa:hidden name="uploadType" value="<%= uploadType %>"/>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Upload XLS File for Budget</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
</tr>
</table>
<table border=0 bordercolor=lightShadow cellpadding=0 cellspacing=0 width=680px>
<tr width=100%>
 <td width=40%>
	<table border=1 bordercolor=lightShadow cellpadding=0 cellspacing=0 height=300px width=100%>
	<tr>
		<td valign=top align=left width=100%>
			<table  valign=top border=0 cellpadding=2 cellspacing=0 width=100%>
				<tr>
					<td  valign=top align=left><b>Import .xls file <img src="<%=contextPath%>/images/question_blue.gif"/></b></td>
				</tr>
			</table>
			<br><br><br>
			<table border=0 cellpadding=2 cellspacing=0>
			<tr><td colspan=2><b>Budget XLS file :</b></td></tr>
			<tr>
				<td><input type=file name="xlsFile_1" size=44></td>
			</tr>
			<tr><td colspan=2><br></td></tr>
			</table>
			<br>
			<table border=0 cellpadding=2 cellspacing=0>
			<tr><td colspan=2><b>Authorities XLS file :</b></td></tr>
			<tr>
				<td><input type=file name="xlsFile_2" size=44></td>
			</tr>
			<tr><td colspan=2><br></td></tr>
			</table>
			<table border=1 cellpadding=2 cellspacing=0 width=100%>
				<tr>
					<td class=hdr12 align=center>Download Sample XLS File :
							<b><a href ="javascript: downloadXlsFile('<%= XlsUploadType.BUDGET_CENTER %>'); void(0);">Budget.xls</a> - <a href ="javascript: downloadXlsFile('<%= XlsUploadType.BUDGET_DRAWER %>'); void(0);">Authorities.xls</a></b>
							to use as a template or to view as reference.
                     </td>
				</tr>
			</table>
			<br><br>
			<table border=0 bordercolor=lightShadow cellpadding=0 cellspacing=0 width=100%>
			<tr>
				<td align=center width=50%><a href="javascript: doSubmit('/admin/budget/budget_xls_summary.jsp', 'BudgetCenterXlsUpload'); void(0);"><img src="<%=contextPath%>/images/button_add.gif" border=0 class=button></a></td>
				<td align=center width=50%><a href="javascript: doSubmit('admin/budget/budget_menu.jsp','DoNothing'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button></a></td>
			</tr>
			</table>
	</td>
	<td width=20%>
	</td>
	<td width=40%>
	<table border=6 bordercolor=lightShadow cellpadding=0 cellspacing=0 height=300px width=340px>
		<tr>
			<td valign=top align=left width=100%>
			<table border=0 cellpadding=2 cellspacing=0 width=100%>
					<tr class=hdr12  width=100%>
						<td  align=center><b>How to Import a Budget XLS File</b></td>
					</tr>
			</table>
			<br>
			<table border=0 bordercolor=lightShadow cellpadding=2 cellspacing=0>
						<tr>
						<td align=left valign="top">1.</td>
						<td  align=left><b>The Imported Budget XLS file must be in</b>
							<b><a href ="javascript: showTemplateXls('<%= XlsUploadType.BUDGET_CENTER %>'); void(0);">Budget.xls</a> and <a href ="javascript: showTemplateXls('<%= XlsUploadType.BUDGET_DRAWER %>'); void(0);">Authorities.xls</a> formats</b>
						</td>
						</tr>
						<tr>
						<td align=left valign="top">2.</td>
						<td  align=left><b>Import your .xls file by selecting "Browse"</b></td>
						</tr>
						<tr>
							<td align=left valign="top">3.</td>
							<td  align=left><b>Click On "Add" to import your file.</b>
							</td>
						</tr>
					</table>
					</tr>
				</table>
			</td>
		</tr>
		</table>
		</td>
	</tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	validFileTypes = '.XLS';

	frm.xlsFile_1.contentEditable  = false;
	frm.xlsFile_2.contentEditable  = false;
	
	setNavCookie('admin/budget/budget_xls_upload.jsp', 'DoNothing', 'Budget Upload');

	function validateForm() {
		var handler = frm.handler.value;

		if (handler.indexOf('BudgetCenterXlsUpload') >= 0) {
			var xlsFile1 = frm.xlsFile_1.value;
			var xlsFile2 = frm.xlsFile_2.value;
			var alertMessage = '';

			if (isEmpty(xlsFile1)) {
				alert('Please select a file to attach.');
				return false;
			}
			else if (!isXlsExtValid(xlsFile1)) {
				return false;
			}
			else if (!(isEmpty(xlsFile2) || isXlsExtValid(xlsFile2))) {
				return false;
			}		
			else {
				frm.action ='<%=contextPath%>/HiltonXlsDataUploadServlet';
				frm.action = frm.action + '?' + frm.organizationId.value;
				frm.enctype = 'multipart/form-data';
				frm.encoding = 'multipart/form-data';
			}
		}
		return true;
	}


	function showTemplateXls(fileType){

 		var imgFileName = '';
		directoryName = '';
		var imageUrl = '<%=DictionaryManager.getInstance("host", oid).getProperty("item-image-url", "\\")%>';
        var imageFile;

        if (fileType == '<%= XlsUploadType.BUDGET_CENTER %>') {
        	imageFile = '<%= propertiesManager.getProperty("BUDGETCENTERXLSUPLOAD", "XLSIMAGESAMPLE", "Budget_upload.png") %>';
        } else {
        	imageFile = '<%= propertiesManager.getProperty("BUDGETDRAWERXLSUPLOAD", "XLSIMAGESAMPLE", "Authorities_upload.png") %>';
        }       

		if (imageUrl.substring(imageUrl.length - 1) != '/') {
			imageUrl = imageUrl + '/';
		}

		imageUrl = imageUrl + directoryName + '/';
		imgFileName = imageFile;

		if (imgFileName.indexOf('http://') >= 0)
		{
			doSubmitToPopup(imgFileName, 'DoNothing', 'WIDTH=1000','HEIGHT=700');
		}
		else
		{
			doSubmitToPopup(imageUrl + imgFileName, 'DoNothing', 'WIDTH=1000','HEIGHT=700');
		}
	}

	function downloadXlsFile(fileType) {
	  var filename;
	  var newInputFields;

	  if (fileType == '<%= XlsUploadType.BUDGET_CENTER %>') {
		  filename = '<%= propertiesManager.getProperty("BUDGETCENTERXLSUPLOAD", "XLSFILESAMPLE", "Budget_upload.xls") %>';
      } else {
    	  filename = '<%= propertiesManager.getProperty("BUDGETDRAWERXLSUPLOAD", "XLSFILESAMPLE", "Authorities_upload.xls") %>';
      }

	  openAttachment(filename, 'template');
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
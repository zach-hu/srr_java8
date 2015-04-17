package com.tsa.puridiom.browse.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseGroupFilter;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.BrowseUtility;
import com.tsa.puridiom.browse.ComputedColumn;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.utility.XmlFile;
import com.tsagate.properties.DictionaryManager;

public class GenerateBrowseObject extends Task
{
	protected String getBrowseXmlPath(String organizationId)
	{
	    String property = "browse-xml-path";
		if (!Utility.isEmpty(this.getApplicationName()))
		{
			property = this.getApplicationName() + "-" + property;
		}
		String	path = DictionaryManager.getInstance("host", organizationId).getProperty(property, "\\xml\\browse\\");

		return path;
	}

	public Object  executeTask (Object object) throws Exception
	{
		BrowseObject result;
		try
		{
			Map incomingRequest = (Map)object;
			String browseName = (String) incomingRequest.get("browseName");
			String organizationId = (String) incomingRequest.get("organizationId");
			String pageSize = (String) incomingRequest.get("pageSize");
			String uid = (String) incomingRequest.get("userId");
			BrowseObject b = new BrowseObject();
			b.setBrowseName(browseName);
			UserProfile	user = UserManager.getInstance().getUser(organizationId,uid);
			String language = (String)incomingRequest.get("language");
			if(HiltonUtility.isEmpty(language)){		language = "en";	}

			try
			{
				String filename = this.getBrowseXmlPath((String)incomingRequest.get("organizationId")) + browseName;

				XmlFile xF = new XmlFile(filename + ".xml", organizationId) ;

				String	xArray[] = { "columns" } ;
				List	colList = xF.getElementList(xArray) ;
				List	labelList = new ArrayList();
				int	columnCount = colList.size();

				// Get the columns and attributes
				BrowseColumn[] browseColumns = new BrowseColumn[columnCount] ;
				Map	columnTypes = new HashMap();
				Map	columnLabels = new HashMap();
				String colName = null ;
				Iterator it = colList.iterator() ;
				List	browseKeys = new ArrayList();

				StringBuffer selectSb = new StringBuffer("Select ");

				int ix = 0 ;
				int selectColumnIndex = 0;
				while (it.hasNext())
				{
					Element ea ;
					browseColumns[ix] = new BrowseColumn(organizationId, language) ;
					ea = ((Element)it.next()) ;

					String	columnName = ea.getName();
					boolean selectPart = true;

					int	ind = columnName.indexOf("_");
					String className = columnName.substring(0, ind);

					if (columnName.indexOf("_id") > 0) {
						ind = columnName.indexOf("_id") + 3;
					}

					String methodName = "get" + columnName.substring(ind + 1, ind+2).toUpperCase() + columnName.substring(ind + 2);

					browseColumns[ix].setColumnName(columnName) ;
					browseColumns[ix].setClassName(className) ;
					browseColumns[ix].setMethodName(methodName) ;

					List clist = ea.getChildren() ;
					Iterator itc = clist.iterator() ;
					String   attribType = null ;
					String   attribValue = null ;
					while (itc.hasNext())
					{
						Element ec = ((Element) itc.next()) ;
						attribType = ec.getName() ;
						attribValue = ec.getText() ;

						if (attribType.equalsIgnoreCase("type")) {
							browseColumns[ix].setType(attribValue) ;
							if( ( attribValue.indexOf("Decimal") >= 0 ) || ( attribValue.indexOf("PriceQuantity") >= 0 ) ){ browseColumns[ix].setAlignment("right");		}
						} else if (attribType.equalsIgnoreCase("label")) {
							browseColumns[ix].setLabel(attribValue) ;
						} else if (attribType.equalsIgnoreCase("visible")) {
							if (Utility.ckNull(attribValue).equals("0")) {
								browseColumns[ix].setHidden(true) ;
							}
						} else if (attribType.equalsIgnoreCase("link")) {
							browseColumns[ix].setLink(attribValue) ;
						    BrowseUtility.getColumnNamesFromLink(browseColumns[ix]);
						} else if (attribType.equalsIgnoreCase("linkImage")) {
							browseColumns[ix].setLinkImage(attribValue) ;
						} else if (attribType.equalsIgnoreCase("sort")) {
							browseColumns[ix].setSort(attribValue) ;
						} else if (attribType.equalsIgnoreCase("allowFilter")) {
							browseColumns[ix].setAllowFilter(attribValue) ;
						} else if (attribType.equalsIgnoreCase("trim")) {
							browseColumns[ix].setTrim(Integer.valueOf(attribValue).intValue()) ;
						} else if (attribType.equalsIgnoreCase("size")) {
							browseColumns[ix].setSize(Integer.valueOf(attribValue).intValue()) ;
						} else if (attribType.equalsIgnoreCase("input-size")) {
							browseColumns[ix].setInputSize(Integer.valueOf(attribValue).intValue()) ;
						} else if (attribType.equalsIgnoreCase("hidden")) {
							if (Utility.ckNull(attribValue).equalsIgnoreCase("Y")) {
								browseColumns[ix].setHidden(true) ;
							}
						} else if (attribType.equalsIgnoreCase("hidden-input")) {
							if (Utility.ckNull(attribValue).equalsIgnoreCase("Y")) {
								browseColumns[ix].setHiddenInput(true) ;
							}
						} else if (attribType.equalsIgnoreCase("text-input")) {
							if (Utility.ckNull(attribValue).equalsIgnoreCase("Y")) {
								browseColumns[ix].setTextInput(true) ;
							}
						} else if (attribType.equalsIgnoreCase("select-input")) {
							if (Utility.ckNull(attribValue).equalsIgnoreCase("Y")) {
								browseColumns[ix].setSelectInput(true) ;
							}
						} else if (attribType.equalsIgnoreCase("checkbox")) {
							if (Utility.ckNull(attribValue).equalsIgnoreCase("Y")) {
								browseColumns[ix].setCheckbox(true) ;
							}
						} else if (attribType.equalsIgnoreCase("detail")) {
							if (Utility.ckNull(attribValue).equalsIgnoreCase("Y")) {
								browseColumns[ix].setDetail(true) ;
								b.setDetailIncluded(true);
							}
						}
						else if (attribType.equalsIgnoreCase("alias")) {
							browseColumns[ix].setAlias(attribValue) ;
						}
						else if(attribType.equalsIgnoreCase("selectsql")) {
							if (Utility.ckNull(attribValue).equalsIgnoreCase("N")) {
								selectPart = false;
								browseColumns[ix].setSqlPart(false);
							}
						}
						else if(attribType.equalsIgnoreCase("concatenate") || attribType.equalsIgnoreCase("userName") || attribType.equalsIgnoreCase("arguments")) {
							browseColumns[ix].setComputed(true);
							browseColumns[ix].setComputeType(attribType);
							List cols = ec.getChildren();
							ArrayList columnsToUse = new ArrayList();
							for(int colsIndex = 0; colsIndex < cols.size(); colsIndex++) {
								Element col = (Element)cols.get(colsIndex);
								ComputedColumn computedColumn = new ComputedColumn();
								String	type = col.getAttributeValue("type");

								if (Utility.isEmpty(type)) {
								    type = "BrowseColumn";
								}
								computedColumn.setColumnType(type);
								computedColumn.setValue(col.getText());
								columnsToUse.add(computedColumn);
							}

							if (attribType.equalsIgnoreCase("arguments"))
							{
								browseColumns[ix].setArgumentColumns(columnsToUse);
							} else
							{
								browseColumns[ix].setConcatenateColumns(columnsToUse);
							}
						}
						else if(attribType.equalsIgnoreCase("aggregate")) {
						    String function = ec.getChildText("function");
						    String newName = browseColumns[ix].getColumnName();
						    newName = function + "(" + newName +")";
						    columnName = newName;
						}
						else if(attribType.equalsIgnoreCase("key")) {
						    browseKeys.add(browseColumns[ix]);
						}
						else if(attribType.equalsIgnoreCase("store-request-value")) {
						    if (attribValue.equalsIgnoreCase("Y")) {
						        browseColumns[ix].setStoreRequestValue(true) ;
						    }
						}
						else if (attribType.equalsIgnoreCase("distinct"))
						{
							if (Utility.ckNull(attribValue).equalsIgnoreCase("Y")) {
								browseColumns[ix].setDistinct(true);
							}
						}
						else if (attribType.equalsIgnoreCase("filter-default"))
						{
							if (Utility.ckNull(attribValue).equalsIgnoreCase("Y")) {
								browseColumns[ix].setFilterDefault(true);
							}
						}
						else if (attribType.equalsIgnoreCase("align")) {
							browseColumns[ix].setAlignment(attribValue) ;
						}
						/** DropDown Implementation */
						else if(attribType.equalsIgnoreCase("idname")){
							browseColumns[ix].setIdName(attribValue.trim());
						}
						else if(attribType.equalsIgnoreCase("index")){
							browseColumns[ix].setIndex(Integer.parseInt(attribValue.trim()));
						}
						else if(attribType.equalsIgnoreCase("value")){
							browseColumns[ix].setValue(attribValue);
							BrowseUtility.getValueOption(browseColumns[ix]);
						}
						else if(attribType.equalsIgnoreCase("hql-arguments")){
//							browseColumns[ix].setValue(attribValue);
							browseColumns[ix].setComputed(true);
							browseColumns[ix].setComputeType(attribType);
							List cols = ec.getChildren();

							String attribColType = null ;
							String attribColValue = null ;
							String objectType = null;
							String methodOption = null;

							for(int colsIndex = 0; colsIndex < cols.size(); colsIndex++) {
								Element col = (Element)cols.get(colsIndex);
								attribColType = col.getName() ;
								attribColValue = col.getText() ;
								
								int	idx = attribColValue.indexOf("_");
								
								if(attribColType.equalsIgnoreCase("selecthql")){
									browseColumns[ix].setSelecthql(attribColValue);
								}
								else if(attribColType.equalsIgnoreCase("option-key")){
									objectType = attribColValue.substring(0, idx);
									methodOption = "get" + attribColValue.substring(idx + 1, idx + 2).toUpperCase() + attribColValue.substring(idx + 2);
									browseColumns[ix].setMethodOptionKey(methodOption);
								}
								else if(attribColType.equalsIgnoreCase("option-value")){
									objectType = attribColValue.substring(0, idx);
									methodOption = "get" + attribColValue.substring(idx + 1, idx + 2).toUpperCase() + attribColValue.substring(idx + 2);
									browseColumns[ix].setMethodOptionValue(methodOption);
								}
							}
						}
					}

					b.setBrowseKeys(browseKeys);

					colName = browseColumns[ix].getColumnName() ;

					if(selectPart) {
						if (browseColumns[ix].isDistinct())
						{
							selectSb.append("DISTINCT ");
						}
						if (browseColumns[ix].getType().equals("Sum"))
						{
							selectSb.append(browseColumns[ix].getSelecthql().replace('_', '.'));
							selectSb.append(", ");
						}else{
							selectSb.append(columnName.replace('_', '.'));
							selectSb.append(", ");
						}
						browseColumns[ix].setIndex(selectColumnIndex);
						selectColumnIndex++;
					}

					if (colName.toLowerCase().startsWith("compute.")) {
						/* Compute Column */
					}

					columnTypes.put(colName, browseColumns[ix].getType());
					columnLabels.put(colName, browseColumns[ix].getLabel());
					if (!browseColumns[ix].isHidden() && !browseColumns[ix].isDetail()) {
						labelList.add(browseColumns[ix]);
					}
					ix++ ;
				}

				selectSb.deleteCharAt(selectSb.length() -1);
				selectSb.deleteCharAt(selectSb.length() -1);
				b.setSqlSelect(selectSb.toString());

				b.setBrowseColumns(browseColumns);
				b.setColumnTypes(columnTypes);
				b.setColumnLabels(columnLabels);

				BrowseColumn[] labels = new BrowseColumn[labelList.size()];
				labels = (BrowseColumn[]) labelList.toArray(labels);
				b.setLabels(labels);

				xArray[0] = "group-filters";
				List	groupFilterList = xF.getElementList(xArray) ;

				// Get the group filters and attributes
				List groupFilters = new ArrayList();
				it = groupFilterList.iterator() ;

				ix = 0 ;
				while (it.hasNext()) {
					Element ea = ((Element)it.next()) ;
					BrowseGroupFilter groupFilter = new BrowseGroupFilter();
					String	columnName = ea.getName();
					int	ind = columnName.indexOf("_");

					if (columnName.indexOf("_id") > 0) {
						ind = columnName.indexOf("_id") + 3;
					}

					groupFilter.setColumnName(columnName) ;

					List clist = ea.getChildren() ;
					Iterator itc = clist.iterator() ;
					String   attribType = null ;
					String   attribValue = null ;
					while (itc.hasNext()) {
						Element ec = ((Element) itc.next()) ;
						attribType = ec.getName() ;
						attribValue = ec.getText() ;

						if (attribType.equalsIgnoreCase("label")) {
							groupFilter.setLabel(attribValue) ;
						} else if (attribType.equalsIgnoreCase("type")) {
							groupFilter.setType(attribValue) ;
						} else if (attribType.equalsIgnoreCase("sqlselect")) {
							groupFilter.setSqlSelect(attribValue) ;
						} else if (attribType.equalsIgnoreCase("sqlfrom")) {
							groupFilter.setSqlFrom(attribValue) ;
						} else if (attribType.equalsIgnoreCase("sqlwhere")) {
							groupFilter.setSqlWhere(attribValue) ;
						} else if (attribType.equalsIgnoreCase("sqlorderby")) {
							groupFilter.setSqlOrderBy(attribValue) ;
						} else if (attribType.equalsIgnoreCase("sqlgroupby")) {
							groupFilter.setSqlGroupBy(attribValue) ;
						}
					}
					groupFilters.add(groupFilter);
				}
				b.setGroupFilters(groupFilters);

				xArray[0] = "sqlfrom" ;
				b.setSqlFrom(xF.getText(xArray)) ;

				xArray[0] = "sqlwhere" ;
				String	sqlwhere = xF.getText(xArray);
				//commented out on 10/26/04 - causing errors in the Admin module/system tables/stdTable
/*				if (browseName.equals("stdtable") && incomingRequest.containsKey("StdTable_tableType")) {
					String tableType = (String) incomingRequest.get("StdTable_tableType");
					if (!Utility.isEmpty(tableType)) {
						if (Utility.isEmpty(sqlwhere)) {
							sqlwhere = "StdTable.tableType = '" + tableType + "'";
						}
						else {
							sqlwhere = sqlwhere +  " AND StdTable.tableType = '" + tableType + "'";
						}
					}
				}
*/
				b.setSqlWhere(sqlwhere) ;

				xArray[0] = "sqlorderby" ;
				try
				{
				    Element elOrderBy = xF.getRootChild("sqlorderby");
				    String orderDefault = elOrderBy.getAttributeValue("default");
				    if(orderDefault != null)
				    {
				        if(orderDefault.equalsIgnoreCase("Y"))
				        {
				            b.setOrderByDefault(true);
				        }
				        else
				        {
				            b.setOrderByDefault(false);
				        }
				    }
				    else
				    {
				        b.setOrderByDefault(false);
				    }


					b.setSqlOrderBy(xF.getText(xArray)) ;
				}
				catch (Exception e) {
					b.setSqlOrderBy(null) ;
				}

				xArray[0] = "sqlgroupby";
				String sqlGroupBy = null;
				try
                {
//                    Element elGroupBy = xF.getRootChild("sqlgroupby");
                    sqlGroupBy = xF.getText(xArray);
                }
                catch (Exception e)
                {
                    Log.debug(this, "no groupby for " + browseName);
                }
                b.setSqlGroupBy(sqlGroupBy);

				xArray[0] = "title" ;
				try {
					b.setTitle(xF.getText(xArray)) ;
				}
				catch (Exception e) {
					b.setTitle("Options") ;
				}

				if (!Utility.isEmpty(pageSize)) {
					try {
					    b.setPageSize(Integer.parseInt(pageSize)) ;
					}
					catch (Exception e) {
						b.setPageSize(0) ;
					}
				}
				if (b.getPageSize() == 0) {
					xArray[0] = "pagesize" ;
					try {
						b.setPageSize(Integer.parseInt(xF.getText(xArray))) ;
					}
					catch (Exception e) {
						b.setPageSize(20) ;
					}
				}

				xArray[0] = "maxrows" ;
				try {
					b.setMaxRows(Integer.parseInt(xF.getText(xArray))) ;
				}
				catch (Exception e) {
                    String maxRows = "5000";
                    String execute = (String) incomingRequest.get("execute");
                    if (Utility.ckNull(execute).equals("N")) {
                        //Setting up BrowseObject for reports
                        maxRows = PropertiesManager.getInstance(organizationId).getProperty("REPORTS", "MAXROWS", "5000");
                    } else {
                        maxRows = PropertiesManager.getInstance(organizationId).getProperty("BROWSE", "MAXROWS", "5000");
                    }
                    try {
                        b.setMaxRows(Integer.parseInt(maxRows)) ;
                    }
                    catch (Exception e2) {
                        b.setMaxRows(5000) ;
                    }
				}

				xArray[0] = "detail-visible" ;
				try {
					String	detailVisible = xF.getText(xArray);
					if (Utility.ckNull(detailVisible).equals("Y")) {
						b.setDetailVisible(true) ;
					} else {
						b.setDetailVisible(false) ;
					}
				}
				catch (Exception e) {
					if(Utility.ckNull(user.getExpandBrowse()).equals("Y")){
						b.setDetailVisible(true) ;
					}else
						b.setDetailVisible(false) ;
				}

				xArray[0] = "foreign-database" ;
				try {
					b.setForeignDatabase(xF.getText(xArray)) ;
				}
				catch (Exception e) {
					b.setForeignDatabase(organizationId) ;
				}

				xArray[0] = "no-attribute-error-msg" ;
				try {
					b.setNoAttributeErrorMsg(xF.getText(xArray)) ;
				}
				catch (Exception e) {
					b.setNoAttributeErrorMsg("") ;
				}

				xArray[0] = "require-attribute-values" ;
				try {
				    String	requireAttributeValues = xF.getText(xArray);
				    if (Utility.ckNull(requireAttributeValues).equals("Y")) {
				        b.setRequireAttributeValues(true) ;
				    } else {
				        b.setRequireAttributeValues(false) ;
				    }
				}
				catch (Exception e) {
					b.setRequireAttributeValues(false) ;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			result = b;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			throw e;
		}
		return result ;
	}
}
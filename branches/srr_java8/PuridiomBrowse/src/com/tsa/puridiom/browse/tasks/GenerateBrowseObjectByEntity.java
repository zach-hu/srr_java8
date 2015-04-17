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
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.utility.XmlFile;
import com.tsagate.properties.DictionaryManager;

public class GenerateBrowseObjectByEntity extends Task
{
	protected String getBrowseXmlPath(String organizationId)
	{
        String property = "browse-xml-path";
        if (!Utility.isEmpty(this.getApplicationName()))
        {
            property = this.getApplicationName() + "-" + property;
        }
        String  path = DictionaryManager.getInstance("host", organizationId).getProperty(property, "\\xml\\browse\\");

        return path;
	}

	public Object  executeTask (Object object) throws Exception
	{
		BrowseObject result;
		try
		{
			Map incomingRequest = (Map)object;
			String browseName = (String) incomingRequest.get("browseName");

			BrowseObject b = new BrowseObject();
			b.setBrowseName(browseName);
			String language = (String)incomingRequest.get("language");
			if(HiltonUtility.isEmpty(language)){		language = "en";	}
			String organizationId = (String) incomingRequest.get("organizationId");

			try
			{
				String filename = this.getBrowseXmlPath((String)incomingRequest.get("organizationId")) + browseName;
				XmlFile xF = new XmlFile(filename + ".xml") ;
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

				int ix = 0 ;
				while (it.hasNext()) {
					Element ea ;
					browseColumns[ix] = new BrowseColumn(organizationId, language) ;
					ea = ((Element)it.next()) ;

					String	columnName = ea.getName();
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
					while (itc.hasNext()) {
						Element ec = ((Element) itc.next()) ;
						attribType = ec.getName() ;
						attribValue = ec.getText() ;

						if (attribType.equalsIgnoreCase("type")) {
							browseColumns[ix].setType(attribValue) ;
							if(attribValue.indexOf("Decimal") >= 0){ browseColumns[ix].setAlignment("right");		}
						} else if (attribType.equalsIgnoreCase("label")) {
							browseColumns[ix].setLabel(attribValue) ;
						} else if (attribType.equalsIgnoreCase("visible")) {
							if (Utility.ckNull(attribValue).equals("0")) {
								browseColumns[ix].setHidden(true) ;
							}
						} else if (attribType.equalsIgnoreCase("link")) {
							browseColumns[ix].setLink(attribValue) ;
						} else if (attribType.equalsIgnoreCase("sort")) {
							browseColumns[ix].setSort(attribValue) ;
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
						} else if (attribType.equalsIgnoreCase("detail")) {
							if (Utility.ckNull(attribValue).equalsIgnoreCase("Y")) {
								browseColumns[ix].setDetail(true) ;
								b.setDetailIncluded(true);
							}
						}
					}

					colName = browseColumns[ix].getColumnName() ;
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

				b.setBrowseColumns(browseColumns);
				b.setColumnTypes(columnTypes);
				b.setColumnLabels(columnLabels);

				BrowseColumn[] labels = new BrowseColumn[labelList.size()];
				labels = (BrowseColumn[]) labelList.toArray(labels);
				b.setLabels(labels);

				xArray[0] = "group-filters";
				List	groupFilterList = xF.getElementList(xArray) ;
        		int	groupFilterCount = 0;

        		if (groupFilterList != null) {
        			groupFilterCount = groupFilterList.size();
        		}

				// Get the group filters and attributes
				List groupFilters = new ArrayList();
				it = groupFilterList.iterator() ;

				ix = 0 ;
				while (it.hasNext()) {
					Element ea = ((Element)it.next()) ;
					BrowseGroupFilter groupFilter = new BrowseGroupFilter();
					String	columnName = ea.getName();
					int	ind = columnName.indexOf("_");
					String className = columnName.substring(0, ind);

					if (columnName.indexOf("_id") > 0) {
						ind = columnName.indexOf("_id") + 3;
					}

					String methodName = "get" + columnName.substring(ind + 1, ind+2).toUpperCase() + columnName.substring(ind + 2);

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

				xArray[0] = "title" ;
				try {
					b.setTitle(xF.getText(xArray)) ;
				}
				catch (Exception e) {
					b.setTitle("Options") ;
				}

				xArray[0] = "pagesize" ;
				try {
					b.setPageSize(Integer.parseInt(xF.getText(xArray))) ;
				}
				catch (Exception e) {
					b.setPageSize(20) ;
				}

				xArray[0] = "maxrows" ;
				try {
					b.setMaxRows(Integer.parseInt(xF.getText(xArray))) ;
				}
				catch (Exception e) {
					b.setMaxRows(5000) ;
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

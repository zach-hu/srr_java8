
package com.tsa.puridiom.reports.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.utility.HiltonUtility;

import net.sf.jasperreports.engine.*;

public class ReportDataSource implements JRDataSource
{

    private List data = null;
    private BrowseObject bo = null;
    private int index = -1;

    public ReportDataSource(BrowseObject browseObject, List datasource)
    {
        this.setBo(browseObject);
        this.setData(datasource);
    }

    public boolean next() throws JRException
    {
        index++;

        return (index < data.size());
    }

    public Object getFieldValue(JRField field) throws JRException
    {
        Object value = null;

        String fieldName = field.getName();
        BrowseColumn columns[] = this.getBo().getBrowseColumns();
        for (int i = 0; i < columns.length; i++)
        {
            if(fieldName.equalsIgnoreCase(columns[i].getAlias()))
            {
                Object currentRow[] = (Object[])this.getData().get(index);
                value = currentRow[columns[i].getIndex()];
                HiltonUtility.ckNull(value);
                i = columns.length;
            }
        }


        return value;
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String connectString = "jdbc:oracle:thin:@216.37.169.84:1521:hilton1p";
        String user = "hiltonuser";
        String password = "hiltonuser";

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectString, user, password);
        return conn;
    }

    private BrowseObject getBo()
    {
        return bo;
    }
    private void setBo(BrowseObject bo)
    {
        this.bo = bo;
    }
    private List getData()
    {
        return data;
    }
    private void setData(List data)
    {
        this.data = data;
    }
}

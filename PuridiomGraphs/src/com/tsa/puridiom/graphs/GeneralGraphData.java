package com.tsa.puridiom.graphs;


import java.math.BigDecimal;
import java.util.List;
import org.hibernate.type.Type;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.general.ValueDataset;

import com.tsagate.foundation.database.DBSession;


public class GeneralGraphData {

	private List sqlData;
	private DBSession dbSession;
	private GeneralSqlParams generalSqlParams;
	private String[] typesData;

	public void createTypesData(String typeData)
	{
		if(typeData!=null && !typeData.equalsIgnoreCase(""))
		{
			this.setTypesData(typeData.split(","));
		}
	}

	public void executeQuery(GeneralSqlParams generalSqlParams, String organizationId) throws Exception
	{
		List result = null;
		try
		{
			this.setGeneralSqlParams(generalSqlParams);

			if(this.getGeneralSqlParams().getValues().size() > 0)
			{
				result = this.getDbSession().query(this.getGeneralSqlParams().getSqlQuery(), this.getGeneralSqlParams().getValues().toArray());
			}
			else
			{
				result = this.getDbSession().query(generalSqlParams.getSqlQuery());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		this.setSqlData(result);
	}

	public CategoryDataset createCategoryDataset(GeneralSqlParams generalSqlParams, String organizationId)
	{
		try
		{
			executeQuery(generalSqlParams, organizationId);

			String[] series = {"series"};
			String[] categories = new String[this.getSqlData().size()];
			double[][] data = new double[1][this.getSqlData().size()];

			if (!this.getSqlData().isEmpty())
			{
				for( int i=0; i < this.getSqlData().size(); i++ )
				{
					Object valuesTemp[] = (Object[]) this.getSqlData().get(i);
					data[0][i] =( (Integer) valuesTemp[0]).doubleValue();
					if( this.getTypesData()!=null && this.getTypesData()[1].equalsIgnoreCase("status") )
					{
						valuesTemp[1] = TsaChartUtilities.status((String)valuesTemp[1], organizationId);
					}
	            	categories[i] = ((String) valuesTemp[1]);
				}
			}
			else
			{
				System.out.println("No Data was found!");
			}
			return DatasetUtilities.createCategoryDataset(series, categories, data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}

	public PieDataset createPieDataset(GeneralSqlParams generalSqlParams, String organizationId)
    {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		try
		{
			executeQuery(generalSqlParams, organizationId);
			if (!this.getSqlData().isEmpty())
			{
				for( int i=0; i < this.getSqlData().size(); i++ )
				{
					Object valuesTemp[] = (Object[]) this.getSqlData().get(i);
					if( this.getTypesData()!=null && this.getTypesData()[1].equalsIgnoreCase("poType") )
					{
						valuesTemp[1] = TsaChartUtilities.poType((String)valuesTemp[1], organizationId);
					}
					if( this.getTypesData()!=null && this.getTypesData()[1].equalsIgnoreCase("status") )
					{
						valuesTemp[1] = TsaChartUtilities.status((String)valuesTemp[1], organizationId);
					}
					defaultpiedataset.setValue(((String) valuesTemp[1]), ( (Integer) valuesTemp[0]).doubleValue() );
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
        return defaultpiedataset;
    }

	public ValueDataset createValueDataset(GeneralSqlParams generalSqlParams, String organizationId)
	{
        try
		{
			executeQuery(generalSqlParams, organizationId);

			if (!this.getSqlData().isEmpty())
			{
				if(this.getSqlData().get(0) != null)
				{
					Object valuesTemp = (Object) this.getSqlData().get(0);
					DefaultValueDataset defaultValueDataset = new DefaultValueDataset( ( (BigDecimal) valuesTemp  ).doubleValue() );
					return defaultValueDataset;
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void setSqlData(List sqlData) {
		this.sqlData = sqlData;
	}
	public List getSqlData() {
		return sqlData;
	}

	public void setDbSession(DBSession dbSession) {
		this.dbSession = dbSession;
	}
	public DBSession getDbSession() {
		return dbSession;
	}

	public void setGeneralSqlParams(GeneralSqlParams generalSqlParams) {
		this.generalSqlParams = generalSqlParams;
	}

	public GeneralSqlParams getGeneralSqlParams() {
		return generalSqlParams;
	}

	public void setTypesData(String[] typesData) {
		this.typesData = typesData;
	}

	public String[] getTypesData() {
		return typesData;
	}

}

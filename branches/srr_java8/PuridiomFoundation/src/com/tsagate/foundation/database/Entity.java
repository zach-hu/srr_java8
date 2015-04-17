package com.tsagate.foundation.database;

import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.sql.Date;

public class Entity implements IEntity {

	public String serializeMe()
    {
    	XMLEncoder e = null;
    	String ret = "";
    	ByteArrayOutputStream os = null;
    	//XMLDecoder d = null;
		try
		{

			//FileOutputStream fos = new FileOutputStream("c:\\Test.xml");
			//BufferedOutputStream bos = new BufferedOutputStream(fos);
			os = new ByteArrayOutputStream();
		    e = new XMLEncoder(os);
			e.setPersistenceDelegate( Date.class, new PersistenceDelegate()
	        {
	            protected Expression instantiate( Object oldInstance, java.beans.Encoder out )
	            {
	                Date date = ( Date )oldInstance;
	                Long time =  new Long(date.getTime());
	                return new Expression( date, date.getClass(), "new", new Object[]{time} );
	            }
	        } );

			e.writeObject(this);
			e.close();
			ret = os.toString();
			//byte[] buf = bos.toByteArray();


			/*d = new XMLDecoder(new BufferedInputStream(new FileInputStream("c:\\Test.xml")));
			Object result = d.readObject();
			*/

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally
		{
			e.close();
			//d.close();
		}
		return ret;
    }

}

package com.tsagate.foundation.database;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.*;


import org.hibernate.*;
import org.hibernate.jdbc.Work;
import org.hibernate.type.Type;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author JEFF
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class DBSession {

	private class SessionProps{
		public String	uid;
		public String	oid;
		public String	mid;
		public String	sid;
		public String ipAddress;
		public String  transaction = UniqueKeyGenerator.getInstance().getUniqueKey().toString();
	}
	private SessionFactory sessionFactory = null;
	private Session session = null;
	private Transaction transaction = null;
	private boolean transactionStarted = false ;
	private int	status = Status.SUCCEEDED ;
	private int	processCount = 0;
	private AuditInterceptor auditInterceptor;
	private SessionProps sessionProps;
	private String	dbProduct = "" ;
	private String	dbDriver = "" ;

	/**
	 * Constructor for hibernateSession.
	 */
	public DBSession(String dbcfg) throws Exception {
		this.setSession(null, dbcfg) ;
	}

	public DBSession(String dbcfg, Map request) throws Exception
	{
		this.sessionProps = new SessionProps();
		this.sessionProps.uid = (String) request.get("userId");
		this.sessionProps.oid = (String) request.get("organizationId");
		this.sessionProps.mid = (String) request.get("mailId");
		this.sessionProps.sid = (String) request.get("sessionId");
		this.sessionProps.ipAddress = (String) request.get("ipAddress");

		this.setSession(null, dbcfg);
	}

	public DBSession(Session sess, String dbcfg) throws Exception {
		this.setSession(sess, dbcfg);
	}

	/**
	 * Returns the session.
	 * @return Session
	 */
	public Session getSession() {
		return session;
	}
	/**
	 * Returns the session oid.
	 * @return String
	 */
	public String getSessionOrganizationId() {
		if (sessionProps != null) return this.sessionProps.oid;
		else return null;
	}
	public void noUpdate()
	{
	    this.session.setFlushMode(FlushMode.COMMIT);
	}

	/**
	 * Sets the session.
	 * @param session The session to set
	 */
	private  void setSession(Session sess, String organization) throws Exception {
		if (sess == null) {
			/* If no hibernate session passed, create one */
			try {
				if (Utility.isEmpty(organization)) {
					organization = "puridiom";
				}
				sess = DBConfiguration.getInstance(organization.toLowerCase()).getSession() ;
				this.auditInterceptor = DBConfiguration.getInstance(organization.toLowerCase()).getAuditInterceptor();
	        	dbProduct = sess.connection().getMetaData().getDatabaseProductName() ;
	        	dbDriver = sess.connection().getMetaData().getDriverName() ;
	            Log.debug(this,"New Session: DB Product: " + dbProduct + "  Driver: " + dbDriver);
			}
			catch (HibernateException e) {
				//system.out.println("Error occured getting hibernate session: " + e.toString()) ;
				this.setStatus(Status.FAILED) ;
			}
		}
		this.session = sess;
	}
	/**
	 * Method close.
	 * @throws HibernateException
	 */
	public void close() throws HibernateException {

		if (session != null) {
			if (isTransactionStarted()) {
				this.endTransaction() ;
			}
			session.close();
		}
		//system.out.println("Closed Session ") ;
	}

	/**
	 * Returns the transaction.
	 * @return Transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * Retrieve object, begins and ends its own transaction.
	 * @param clazz Class of object to retrieve.
	 * @param id Id of object to retrieve.
	 * @return Object Object retrieved.
	 * @throws HibernateException Error retrieving object.
	 */
	public Object retrieveId(Serializable id, Class clazz)   {
		Object obj = null;
		try {
			obj = (Object) session.load(clazz, id);
		}
		catch (HibernateException e) {
			this.setStatus(Status.FAILED) ;
			//system.out.println(e.toString());
		}
		return obj ;
	}

	/**
	 * Method delete.
	 * @param id
	 * @param clazz
	 * @return int
	 */
	public int delete(String id, Class clazz) {
		try {
			Object obj = session.load(clazz, id);
			session.delete(obj);
		}
		catch (HibernateException e) {
			String msg =
				"During REMOVE of " + id + "of class " + clazz.getName();
			//system.out.println(msg + " - " + e.toString());
			this.setStatus(Status.FAILED) ;
		}
		return this.getStatus();
	}

	/**
	 * Method delete.
	 * @param obj
	 * @return int
	 */
	public int delete(Object obj) {
			try {
				session.delete(obj) ;
			}
			catch (HibernateException e) {
				//system.out.println(e.toString());
				this.setStatus(Status.FAILED) ;
			}
			return this.getStatus() ;
	}

	/**
	 * Method delete.
	 * @param qry String
	 * @return int
	 */
	public int delete(String qry) {
			try {
				session.delete(qry) ;
			}
			catch (HibernateException e) {
				Log.error(this, e.toString());
				this.setStatus(Status.FAILED) ;
			}
			return this.getStatus() ;
	}

	/**
	 * Method delete.
	 * @param query
	 * @param args
	 * @param types
	 * @return int
	 */
	public int delete(String query, Object[] args, Type[] types) {
		int retval = Status.SUCCEEDED ;
		try {
			if (query.trim().toLowerCase().startsWith("from")) {
				query = "DELETE " + query ;
			}
			Query qry  = session.createQuery(query) ;
			qry.setParameters(args, types);
            Log.debug(this,qry.getQueryString());

			System.out.println(qry.getQueryString()) ;
			int rows = qry.executeUpdate() ;
            Log.debug(this,"Rows deleted: " + rows);
//			session.delete(query, args, types);
		}

		catch (HibernateException e) {
			this.setStatus(Status.FAILED) ;
			//system.out.println("During Delete - " + e.toString());
		}
		return retval ;
	}

	/**
	 * Method update.
	 * @param query
	 * @param args
	 * @param types
	 * @return int
	 */
	public int update(String query, Object[] args, Type[] types) {
		int retval = Status.SUCCEEDED ;
		try {
			Query qry  = session.createQuery(query) ;
			qry.setParameters(args, types);
			Log.debug(this,qry.getQueryString());
			
			System.out.println(qry.getQueryString()) ;
			int rows = qry.executeUpdate() ;
			Log.debug(this,"Rows updated: " + rows);
		}
		
		catch (HibernateException e) {
			this.setStatus(Status.FAILED) ;
		}
		return retval ;
	}

	/**
	 * Method delete.
	 * @param query
	 * @param arg
	 * @param typ
	 * @return int
	 */
	public int delete(String query, Object arg, Type typ) {

			return  delete(query,new Object[]{arg},new Type[]{typ}) ;
	}

	/**
	 * Store object, begins and ends its own transaction.
	 * @param obj
	 * @throws HibernateException
	 */
	public int update(Object obj) {

		try {
			session.update(obj);
			//session.flush();
		}
		catch (HibernateException e) {
			this.setStatus(Status.FAILED) ;
			System.out.println( e.toString());
			e.printStackTrace();
		}
		return this.getStatus() ;
	}

	/**
	 * Store object, begins and ends its own transaction.
	 * @param obj
	 * @throws HibernateException
	 */
	public int add(Object obj) throws Exception{

		try {
			session.save(obj);
			session.flush();
		}
		catch (HibernateException e) {
			this.setStatus(Status.FAILED) ;
			try {
			    Log.error(this, "Error adding " + obj.toString());
			}
			catch (Exception e1) {
			}
			throw e;
		}
		return this.getStatus() ;
	}

	/**
	 * Method query.
	 * @param query
	 * @return List
	 */
	public List query(String query) throws Exception {
		List lst = null ;
		try {
			//lst = session.find(query);
			//lst = session.createQuery(query).setMaxResults(500).list();

		    query = this.replaceSingleQuotes(query);

		    lst = session.createQuery(query).list();
		}
		catch (HibernateException e) {
			this.setStatus(Status.FAILED) ;
			//system.out.println("During QUERY - " + e.toString());
			throw new Exception(e);
		}
		return lst ;
	}

    public List query(String query, Object[] args, Object typeObj, int maxRows) throws Exception {
        List lst = null ;
        try {
            Log.debug(this, "Query - " + query);

            Query q = session.createQuery(query);

            q = setQueryParameters(q, args, typeObj);

            if (maxRows > 0) {
                q.setMaxResults(maxRows);
            }

            Log.debug(this,q.getQueryString());
            lst = q.list();

            if (lst == null) {
                Log.debug(this, "query returned null");
            } else {
                Log.debug(this, "query returned - " + lst.size() +" records");
            }
        }
        catch (HibernateException e)
        {
            this.setStatus(Status.FAILED) ;
            Log.error(this, "HibernateException ERROR in - DBSession.query(String query, Object[] args, Type[] types): " + e.toString());
            Log.error(this, "QUERY - " + e.toString());
            throw new Exception(e);
            //throw new Exception("HibernateException ERROR in - DBSession.query(String query, Object[] args, Type[] types): " + e.toString());
        }
        return lst ;
    }
    
    
    public SearchResult query(String query, String queryCount, Object[] args, Object typeObj, int index, int maxRows) throws Exception {
    	SearchResult searchResult = null;
        try {
            Log.debug(this, "Query - " + query);
            Log.debug(this, "Query - count" + queryCount);
            
            Query qCount = session.createQuery(queryCount);
            qCount = setQueryParameters(qCount, args, typeObj);
            long size = ((Long) qCount.iterate().next()).longValue();
            
            Query q = session.createQuery(query);

            q = setQueryParameters(q, args, typeObj);

            int firstResult = 0;
            if(index > 1){
            	firstResult = maxRows * (index - 1);
            }
            q.setFirstResult(firstResult);
            if (maxRows > 0) {
                q.setMaxResults(maxRows);
            }

            Log.debug(this,q.getQueryString());
            List lst = q.list();

            if (lst == null) {
                Log.debug(this, "query returned null");
            } else {
                Log.debug(this, "query returned - " + lst.size() +" records");
            }
            
            searchResult = new SearchResult();
            searchResult.setList(lst);
            searchResult.setTotalRecords(size);
            searchResult.setIndex(index);
            searchResult.setRecordPerPage(maxRows);
        }
        catch (HibernateException e)
        {
            this.setStatus(Status.FAILED) ;
            Log.error(this, "HibernateException ERROR in - DBSession.query(String query, Object[] args, Type[] types): " + e.toString());
            Log.error(this, "QUERY - " + e.toString());
            throw new Exception(e);
            //throw new Exception("HibernateException ERROR in - DBSession.query(String query, Object[] args, Type[] types): " + e.toString());
        }
        return searchResult;
    }

    /**
	 * Method query.
	 * @param query
	 * @param args
	 * @param types
	 * @return List
	 */
	public List query(String query, Object[] args, Type[] types) throws Exception {
        return this.query(query, args, types, 0);
	}

    public List query(String query, Object[] args, int maxRows) throws Exception {
        Type types[] = null;
        if (args != null) {
            types = new Type[args.length];
            for (int i=0; i < args.length; i++) {
                if (args[i] instanceof java.sql.Date || args[i] instanceof java.util.Date) {
                    types[i] = Hibernate.DATE;
                }
                else if (args[i] instanceof java.math.BigDecimal) {
                    types[i] = Hibernate.BIG_DECIMAL;
                } else {
                    types[i] = Hibernate.STRING;
                }
            }
        }
        return this.query(query, args, types, maxRows);
    }

	/**
	 * Method query.
	 * @param query
	 * @param args
	 * @param types
	 * @return List
	 */
	public List query(String query, Object[] args) throws Exception {
		List lst = null ;
		try {
            Type types[] = null;
            if (args != null) {
                types = new Type[args.length];
				for (int i=0; i < args.length; i++) {
					if (args[i] instanceof java.sql.Date || args[i] instanceof java.util.Date) {
						types[i] = Hibernate.DATE;
					}
					else if (args[i] instanceof java.math.BigDecimal) {
                        types[i] = Hibernate.BIG_DECIMAL;
					} else {
                        types[i] = Hibernate.STRING;
					}
				}
			}
            lst = this.query(query, args, types, 0);
		}
		catch (HibernateException e)
		{
			this.setStatus(Status.FAILED) ;
			Log.error(this, "HibernateException ERROR in - DBSession.query(String query, Object[] args, Type[] types): " + e.toString());
			Log.error(this, "QUERY - " + query);
			throw new Exception(e);
			//throw new Exception("HibernateException ERROR in - DBSession.query(String query, Object[] args, Type[] types): " + e.toString());
		}
		return lst ;
	}

	/**
	 * Method query.
	 * @param query
	 * @param arg
	 * @param type
	 * @return List
	 */
	public List query(String query, Object arg, Type typ) throws Exception {
		try {
			return  query(query,new Object[]{arg},new Type[]{typ}) ;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Method sqlUpdate.
	 * @param sql
	 * @return int
	 */
	public int sqlUpdate(String sql, Object [] args, Integer [] types) {

		int	retval = Status.SUCCEEDED;

		SQLUpdate update = new SQLUpdate(sql, args, types);

		// Execute the Update
		try
		{
			session.doWork(update);
		}
		catch (HibernateException e) {
			Log.error(this, e);
			Log.error(this, "SQLException ERROR in - DBSession.sqlUpdate(String " + sql +") during executeUpdate: " + e.toString());
			retval = Status.FAILED;
		}

		return retval  ;
	}
	
	/**
	 * Method sqlUpdate.
	 * @param sql
	 * @return int
	 */
	public Object sqlQuery(String sql, Object [] args, Integer [] types, Class returnType) {
		SQLQuery query = new SQLQuery(sql, args, types);

		// Execute the Update
		session.doWork(query);

		ResultSet rs = query.getResult();
		Object result = null;
		
		try {
			if (rs.next()) {
				if (returnType.equals(BigDecimal.class)) {
					result = rs.getBigDecimal(1);
				} else {
					result = rs.getString(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (query != null) {
				query.close();
			}
		}
		
		return result;
	}

	/**
	 * Method startTransaction.
	 * @return int
	 */
	public int startTransaction()  {
		Log.debug(this, "Start Database Transaction");

		this.setStatus(Status.SUCCEEDED) ;
		try {
			transaction = session.beginTransaction() ;
			transactionStarted = true ;
		}
		catch (HibernateException e) {
			this.setStatus(Status.FAILED) ;
			Log.error(this, "HibernateException ERROR in - DBSession.startTransaction(): " + e.toString());
		}
		return this.getStatus() ;
	}

	/**
	 * Method endTransaction.
	 * @return int
	 */
	public int endTransaction() {
		Log.debug(this, "End Database Transaction Status = " + this.getStatus());

		if (this.isTransactionStarted()) {
			if (this.getStatus() == Status.SUCCEEDED) {
				if (transactionStarted)
				{
					this.commit() ;
//					this.auditLog();
				}
			} else {
				if (transactionStarted) this.rollback();
			}
			this.transactionStarted = false ;
		}
		return this.getStatus() ;
	}

	private void auditLog()
	{

		for(Iterator it = this.auditInterceptor.entities.iterator(); it.hasNext();)
		{
			AuditLog.logEvent(this.sessionProps.transaction, it.next(), this.sessionProps.uid, this.sessionProps.mid, this.sessionProps.ipAddress, this.sessionProps.oid);
		}

	}
	/**
	 * Method commit.
	 */
	public void commit()  {
		try {
			session.flush() ;
			session.connection().commit();
			transaction.commit() ;
		}
		catch (SQLException e) {
			Log.error(this, "SQLException ERROR in - DBSession.commit(): " + e.toString());
			this.setStatus(Status.FAILED) ;
		}
		catch (HibernateException e) {
			Log.error(this, "HibernateException ERROR in - DBSession.commit(): " + e.toString());
			this.setStatus(Status.FAILED) ;
		}
	}

	/**
	 * Method rollback.
	 */
	public void rollback()  {
		try {
			session.connection().rollback();
			transaction.rollback() ;
		}
		catch (SQLException e) {
			Log.error(this, "SQLException ERROR in - DBSession.rollback(): " + e.toString());
			this.setStatus(Status.FAILED) ;
		}
		catch (HibernateException e) {
			this.setStatus(Status.FAILED) ;
			Log.error(this, "HibernateException ERROR in - DBSession.rollback(): " + e.toString());
		}
	}
	/**
	 * Returns the status.
	 * @return int
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * @param status The status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * Returns the transactionStarted.
	 * @return boolean
	 */
	public boolean isTransactionStarted() {
		return transactionStarted;
	}

	/**
	 * Returns the processCount.
	 * @return int
	 */
	public int getProcessCount() {
		return processCount;
	}

	/**
	 * Sets the processCount.
	 * @param processCount The processCount to set
	 */
	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}

	public Connection getSqlConnection()
	{
	    Connection ret = null;
	    try
        {
            ret = this.session.connection();
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
        return ret;
	}
	
	private int getOperatorIndex(String query, String operator) {
	    operator = operator.toUpperCase();
	    query = query.toUpperCase();

	    char singleQuote = new String("'").charAt(0);
	    int	ind = query.indexOf(operator);

	    if (ind > 0) {
	        char ch = ' ';
	        int	ii = 1;
	        while (ch == ' ' && (query.length() > (ind + operator.length() + ii))) {
	            ch = query.charAt(ind + operator.length() + ii);
	            ii++;
	        }
	        if (ch != singleQuote) {
	            int tempInd = -1;
	            while (tempInd == -1 && ind >= 0 && ind < query.lastIndexOf(operator)) {
	                String tempQuery = query.substring(ind + 1);
	                tempInd = getOperatorIndex(tempQuery, operator);
	                if (tempInd >= 0) {
	                    ind = ind + tempInd;
	                }
		        }
	        }
	    }

	    return ind;
	}

	private String findOperator(String query) {
	    String	operator = "=";

	    int eq = this.getOperatorIndex(query, "=");
	    eq = this.testOperatorIndex(query, "=", eq);

	    int neq = this.getOperatorIndex(query, "<>");
	    neq = this.testOperatorIndex(query, "<>", neq);

	    int like = this.getOperatorIndex(query, "LIKE");
	    like = this.testOperatorIndex(query, "LIKE", like);

	    int gt = this.getOperatorIndex(query, ">");
	    gt = this.testOperatorIndex(query, ">", gt);

	    int lt = this.getOperatorIndex(query, "<");
	    lt = this.testOperatorIndex(query, "<", lt);

	    int firstIndex = eq;

	    if (neq > 0 && (neq < firstIndex || firstIndex < 0)) {
	        firstIndex = neq;
	        operator = "<>";
	    }
	    if (like > 0 && (like < firstIndex || firstIndex < 0)) {
	        firstIndex = like;
	        operator = "LIKE";
	    }
	    if (gt > 0 && (gt < firstIndex || firstIndex < 0)) {
	        firstIndex = gt;
	        operator = ">";
	    }
	    if (lt > 0 && (lt < firstIndex || firstIndex < 0)) {
	        firstIndex = lt;
	        operator = "<";
	    }

	    return operator;
	}

	private String replaceSingleQuotes(String query) {
	    String	operator = this.findOperator(query);
	    int beginValue = query.toUpperCase().indexOf(operator +  " '");
	    if (beginValue < 0) {
	        beginValue = query.toUpperCase().indexOf(operator + "'");
	    }
	    while (beginValue > 0) {
	        String	tmp1 = query.substring(beginValue);
	        while (!tmp1.startsWith("'") && tmp1.length() > 0) {
	            beginValue++;
	            tmp1 = query.substring(beginValue);
	        }
	        beginValue++;

	        operator = this.findOperator(tmp1);
	        int endValue = tmp1.toUpperCase().indexOf(operator);
	        if (endValue < 0) {
	            endValue = tmp1.length();
	        }
	        String	value = tmp1.substring(1, endValue);
	        endValue = value.lastIndexOf("'");
	        value = value.substring(0, endValue);
	        value = value.replaceAll("'", "''");
	        query = query.substring(0, beginValue) + value + query.substring(beginValue + endValue);

	        operator = this.findOperator(query.substring(beginValue + endValue));
	        int nextBeginValue = query.substring(beginValue).toUpperCase().indexOf(operator + " '");
		    if (nextBeginValue < 0) {
		        nextBeginValue = query.substring(beginValue).toUpperCase().indexOf(operator + "'");
		    }
		    if (nextBeginValue >= 0) {
		        beginValue = beginValue + nextBeginValue;
		    } else {
		        beginValue = 0;
		    }
	    }
	    return query;
	}

	/**
	 * tests to make sure the operator index that was returned is followed by a single quote
	 * if not, return -1
	 * @param query
	 * @param operator
	 * @param index
	 * @return
	 */
	private int testOperatorIndex(String query, String operator, int index) {
		char singleQuote = new String("'").charAt(0);
        char ch = ' ';
        int ii = 1;
        while (ch == ' ' && (query.length() > (index + operator.length() + ii))) {
            ch = query.charAt(index + operator.length() + ii);
            ii++;
        }
        if (ch != singleQuote) {
        	index = -1;
        }
        return index;
	}

	public void metadata()
	{

	}

	/**
	 * @return the dbDriver
	 */
	public String getDbDriver() {
		return dbDriver;
	}

	/**
	 * @return the dbProduct
	 */
	public String getDbProduct() {
		return dbProduct;
	}

    private Query setQueryParameters(Query q, Object[] args, Object typeObj) {
        Type types[] = null;
        if (typeObj != null) {
            if (typeObj instanceof Type[]) {
                types =(Type[]) typeObj;
            }
        }
        if (typeObj == null) {
            types = new Type[args.length];
            for (int i=0; i < args.length; i++) {
                if (args[i] instanceof java.sql.Date || args[i] instanceof java.util.Date) {
                    types[i] = Hibernate.DATE;
                }
                else if (args[i] instanceof java.math.BigDecimal) {
                    types[i] = Hibernate.BIG_DECIMAL;
                } else {
                    types[i] = Hibernate.STRING;
                }
            }
        }
        if (args != null && types != null && args.length == types.length) {
            for (int i=0; i < args.length; i++) {
                q.setParameter(i, args[i], (Type) types[i]);
            }
        }

        return q;
    }

    public String getConfigId() {
    	return this.auditInterceptor.getOrganizationId();
    }
    
    public void lock (Object obj) {
    	System.out.println("Zach's Session Identifier PRE: " + session);
    	session.lock(obj, LockMode.UPGRADE);
    	System.out.println("Zach's Session Identifier POST: " + session);
    }

    public void refresh (Object obj) {
    	session.refresh(obj);
    }
    
    private class SQLUpdate implements Work {
    	String sqlUpdate;
    	Object [] args;
    	Integer [] types;
    	
    	public SQLUpdate(String sqlUpdate, Object [] args, Integer [] types) {
    		this.sqlUpdate = sqlUpdate;
    		this.args = args;
    		this.types = types;
    	}

		public void execute(Connection con) throws SQLException {
			PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement(sqlUpdate);
				
				for (int i = 0; i < types.length; i++) {
					stmt.setObject(i+1, args[i], types[i]);
				}
				
				stmt.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
	            if (stmt != null) {
	                try {
	                	stmt.close();
	                } catch( Exception e ) {
	                	e.printStackTrace();
	                }
	            }
	        }
		}
    }
    
    private class SQLQuery implements Work {
    	String sqlUpdate;
    	Object [] args;
    	Integer [] types;
    	ResultSet result;
    	PreparedStatement stmt = null;
    	
    	public SQLQuery(String sqlUpdate, Object [] args, Integer [] types) {
    		this.sqlUpdate = sqlUpdate;
    		this.args = args;
    		this.types = types;
    	}

		public void execute(Connection con) throws SQLException {
			stmt = con.prepareStatement(sqlUpdate);
			
			try {
				for (int i = 0; i < types.length; i++) {
					stmt.setObject(i+1, args[i], types[i]);
				}
				
				result = stmt.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (stmt != null) {
	                try {
	                	stmt.close();
	                } catch( Exception e ) {
	                	e.printStackTrace();
	                }
	            }
			}
		}
		
		public ResultSet getResult() {
			return result;
		}
		
		public void close() {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

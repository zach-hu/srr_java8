package com.tsa.puridiom.requisitionline.autoaddcatalogitem.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AccountPK;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class RequisitionLineListAutoAddCatalogItem extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            DBSession dbs = (DBSession) incomingRequest.get("dbsession");
            List reqList = (List)incomingRequest.get("requisitionLineList");
            RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
    		String	userId = (String) incomingRequest.get("userId") ;

        	PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
            PuridiomProcess process = null;

            String vendorId = HiltonUtility.ckNull(requisitionHeader.getVendorId());

			if ( !HiltonUtility.isEmpty(vendorId) )
			{
				String queryString = "from Catalog as Catalog, Vendor as Vendor where Vendor.vendorId = ? " +
				"AND Catalog.catalogId = Vendor.vendorId ";
				List resultList = dbs.query(queryString, new Object[] {vendorId} , new Type[] { Hibernate.STRING}) ;

				if (resultList != null && resultList.size() > 0)
				{
					String catalogId = vendorId;
					if(reqList == null)
		            {
		                reqList = (List)incomingRequest.get("reqLines");
		            }

		            for (Iterator iter = reqList.iterator(); iter.hasNext();)
		            {
		                RequisitionLine reqLine = (RequisitionLine) iter.next();

		                if ( !HiltonUtility.isEmpty(catalogId) && !HiltonUtility.isEmpty(reqLine.getItemNumber()) && HiltonUtility.isEmpty(reqLine.getCatalogId()) && reqLine.getUdf1Code().equals("Y") )
		                {
		                	incomingRequest.put("Catalog_catalogId", catalogId);

		                	incomingRequest.put("CatalogItem_itemNumber", reqLine.getItemNumber());
		                	incomingRequest.put("CatalogItem_description", reqLine.getDescription());
		                	incomingRequest.put("CatalogItem_umCode", reqLine.getUmCode());
		                	incomingRequest.put("CatalogItem_cost", reqLine.getUnitPrice().toString());
		                	incomingRequest.put("CatalogItem_commodity", "111");
		                	incomingRequest.put("CatalogItem_receiptRequired", reqLine.getReceiptRequired());
		                	incomingRequest.put("CatalogItem_mfgName", reqLine.getMfgName());
		                	incomingRequest.put("CatalogItem_modelNumber", reqLine.getModelNumber());
		                	incomingRequest.put("CatalogItem_taxable", reqLine.getTaxable());
		                	incomingRequest.put("CatalogItem_status", "01");
		                	incomingRequest.put("CatalogItem_owner",(String)incomingRequest.get("userId"));

			    			queryString = "from Account as a where a.id.icHeader = ? AND a.id.icLine = ?";

			    			List accountList = dbs.query(queryString, new Object[] {reqLine.getIcReqHeader(), reqLine.getIcReqLine()}, new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;

			    			if (accountList != null && accountList.size() > 0)
							{
			    				Account rqlAccount = (Account)accountList.get(0);

			    				java.util.Date sysdate = new java.util.Date();
			    				UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
				    			BigDecimal newIcAccount =  new BigDecimal(ukg.getUniqueKey().toString());
				    			Account account = new Account();
				    			AccountPK comp_id = new AccountPK();
				    			comp_id.setIcHeader(newIcAccount);
				    			comp_id.setIcLine(new BigDecimal(0));
				    			comp_id.setSequence(new BigDecimal(1));
				    			account.setComp_id(comp_id);
				    			account.setAccountType("CAT");
				    			account.setFld4(rqlAccount.getFld4());
				    			account.setAllocPercent(new BigDecimal(100));
				    			account.setAllocAmount(new BigDecimal(0));
				    			account.setAccountTitle("Catalog Item: " + catalogId + " " + reqLine.getItemNumber());
				    			account.setDateEntered(sysdate);
				    			account.setDateExpires(sysdate);
				    			account.setStatus("02");
				    			account.setOwner(userId);
				    			account.setAllocMethod("PH");
				    			account.setAllocQty(new BigDecimal(0));
				    			account.setRecQty(new BigDecimal(0));
				    			account.setIcLastRec(new BigDecimal(0));
				    			account.setImisId(new BigDecimal(0));
				    			account.setImisLiquidated("");
				    			account.setImisObmo(new BigDecimal(0));
				    			account.setImisOblig(new BigDecimal(0));
				    			account.setImisSequence(new BigDecimal(0));
				    			dbs.add(account);
				    			incomingRequest.put("CatalogItem_icAccount",newIcAccount.toString());
							}

			    			process = processLoader.loadProcess("catalog-item-create.xml") ;
			    			process.executeProcess(incomingRequest);
			    			if (process.getStatus() == Status.FAILED)
			    			{
			    				throw new Exception("catalog-item-create.xml failed in RequisitionLineListAutoAddCatalogItem for line " + reqLine.getIcReqLine());
			    			}

			    			process = processLoader.loadProcess("catalog-item-add.xml") ;
			    			process.executeProcess(incomingRequest);
			    			if (process.getStatus() == Status.FAILED)
			    			{
			    				throw new Exception("catalog-item-add.xml failed in RequisitionLineListAutoAddCatalogItem for line " + reqLine.getIcReqLine());
			    			}
		                }
		            }
				}
			}
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
           throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}
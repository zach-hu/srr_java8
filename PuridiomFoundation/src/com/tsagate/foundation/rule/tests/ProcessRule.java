/*
 * Created on Sep 11, 2003
 */
package com.tsagate.foundation.rule.tests;

import com.tsagate.foundation.rule.RuleManager;
import java.util.HashMap;
import java.util.Map;


/**
 * @author renzo
 */
public class ProcessRule
{
    public boolean rule(String name)
    {
        RuleManager rules = RuleManager.getInstance();
        Map map = new HashMap();
        /*RequisitionHeader req = new RequisitionHeader();
        req.setIcReqHeader(new BigDecimal("2"));
        req.setDepartmentCode("purchasinger");
        req.setBuyer("alans");
        map.put("RequisitionHeader", req);
        */
        //map.put("rfqNumberValidated", "Y");
/*		RfqHeader rfqHeader = new RfqHeader();
        rfqHeader.setStatus("2000");
        map.put("rfqHeader", rfqHeader);
*/
/*		map.put("RfqHeader_status", "2005");
        map.put("RfqHeader_rfqType", "RQ");
*/
        map.put("DocComment_commentSource", "STD");
        map.put("CreateAction", "SAVE");
        map.put("newstatus", "1030");
        return rules.evaluate(name, map);
    }

    public void testString()
    {
        String a = "1030";
        String b = "1015";
        System.out.println("1030 comparedto 1015: " + a.compareTo(b));
    }
    public static void main(String[] args)
    {
        ProcessRule pr = new ProcessRule();
        ////system.out.println(pr.rule("is-voucher-module-active.xml"));
        ////system.out.println(pr.rule("is-rfq-forward-available.xml"));
        ////system.out.println(pr.rule("is-create-save.xml"));
        ////system.out.println(pr.rule("is-rfq-status-less-than-purchasing.xml"));
        //system.out.println(pr.rule("is-solicitation-active.xml"));
        System.out.println(pr.rule("is-req-approving.xml"));
        System.out.println("compare 2 strings");
        pr.testString();
    }
}

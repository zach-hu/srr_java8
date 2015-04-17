auditRecord = function(oData, userId, oid, ipAddress)
{
    if (oData)
    {
        this.init(oData, oParent, expanded);
        this.setUpLabel(oData);
        this.setUpCheck(checked);
    }

};
AuditRecord.prototype =
{
	userId: null,
	oid: null,
	ipAddress: null,
	data: null,

	    init: function(oData)
	    {
	        this.oData = oData;
	     }

};
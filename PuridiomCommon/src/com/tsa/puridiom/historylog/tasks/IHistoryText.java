/*
 * Created on Jul 19, 2004
 *
 * @author  * renzo
 * package com.tsa.puridiom.historylog.tasks;.IHistoryText.java
 * 
 */
package com.tsa.puridiom.historylog.tasks;

public interface IHistoryText
{
    public abstract String createLineText();
    public abstract String forwardText();
    public abstract String approveText();
    public abstract String headerText(String text, String number);
    public abstract String cancelLineText();
    public abstract String deleteText();
    public abstract String deleteLineText();
    public abstract String getItemText();
}
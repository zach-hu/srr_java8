/*
 * Created on Sep 16, 2003
 */
package com.tsagate.foundation.rule.operator;

/**
 * @author renzo
 */
public class OperatorFactory
{
    public static IOperator getOperator(int op)
    {
        IOperator operator = null;
        if(op == 1)
        {
            operator = new Greater();
        }
        else if(op == -1)
        {
            operator = new Less();
        }
        else if(op == 10)
        {
            operator = new GreaterEquals();
        }
        else if(op == -10)
        {
            operator = new LessEquals();
        }
        else if(op == -99)
        {
            operator = new NotEqual();
        }
        else //default operator
        {
            operator = new Equals();
        }

        return operator;
    }

    public static IOperator getOperator(String op)
    {
        IOperator operator = null;
        if(op.equalsIgnoreCase("GREATER"))
        {
            operator = new Greater();
        }
        else if(op.equalsIgnoreCase("LESS"))
        {
            operator = new Less();
        }
        else if(op.equalsIgnoreCase("GREATEREQUAL"))
        {
            operator = new GreaterEquals();
        }
        else if(op.equalsIgnoreCase("LESSEQUAL"))
        {
            operator = new LessEquals();
        }
        else if(op.equalsIgnoreCase("NOTEQUAL"))
        {
            operator = new NotEqual();
        }
        else if(op.equalsIgnoreCase("INDEXOF"))
        {
            operator = new IndexOf();
        }
        else if(op.equalsIgnoreCase("STARTSWITH"))
        {
            operator = new StartsWith();
        }
        else if(op.equalsIgnoreCase("DOESNOTSTARTWITH"))
        {
            operator = new DoesNotStartWith();
        }
        else if(op.equalsIgnoreCase("ENDSWITH"))
        {
            operator = new EndsWith();
        }
        else //default operator
        {
            operator = new Equals();
        }

        return operator;
    }

    public static ILogicOperator getLogicOperator(int op)
    {
        LogicOperator logOp = null;
        if(op == OperatorTypes.and)
        {
            logOp = new And();
        }
        else
        {
            logOp = new Or();
        }

        return logOp;
    }

    public static ILogicOperator getNewLogicOperator(int op)
    {
        NewLogicOperator logOp = null;
        if(op == OperatorTypes.and)
        {
            logOp = new NewAnd();
        }
        else
        {
            logOp = new NewOr();
        }

        return logOp;
    }

    /** by default will return then <b>"AND"</b> operator
     * @param op
     * @return
     */
    public static ILogicOperator getLogicOperator(String op)
    {
        LogicOperator logOp = null;

        if (op.equalsIgnoreCase("OR"))
        {
            logOp = new Or();
        }
        else if (op.equalsIgnoreCase("IFTHEN"))
        {
            logOp = new IfThen();
        }
        else
        {
            logOp = new And();
        }

        return logOp;
    }
    public static ILogicOperator getNewLogicOperator(String op)
    {
        NewLogicOperator logOp = null;

        if (op.equalsIgnoreCase("OR"))
        {
            logOp = new NewOr();
        }
        else if (op.equalsIgnoreCase("IFTHEN"))
        {
            logOp = new NewIfThen();
        }
        else
        {
            logOp = new NewAnd();
        }

        return logOp;
    }
}

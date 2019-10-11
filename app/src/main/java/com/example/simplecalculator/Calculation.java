package com.example.simplecalculator;

import java.util.ArrayList;

public final class Calculation {
    private Calculation() {
    }

    public static double CalculateExpression(String str) {
        double result = 0.0; //TODO need remove unused variables
        // ArrayList<String> expression = Parser.ParseBySpace(str); //TODO remove unused code
        ArrayList<String> expression = Parser.Parse(str);

        if (expression.contains("(") && expression.contains(")")) //TODO "(" and ")" need extract to constants
            return CalculateExpressionWithBrackets(expression);
        else return CalculateSimpleExpression(expression);
    }

    private static double CalculateExpressionWithBrackets(ArrayList<String> expr) {

        while (expr.contains("(") || expr.contains(")")) {
            if ((expr.indexOf("(") < expr.lastIndexOf(")") && (expr.contains("(") && expr.contains(")")))) {
                ArrayList<String> sub = new ArrayList<String>();
                for (int i = expr.indexOf("(") + 1; i < expr.lastIndexOf(")"); i++)
                    sub.add(expr.get(i));
                ReplaceByCalculationResult(CalculateExpressionWithBrackets(sub), expr, expr.indexOf("("), expr.lastIndexOf(")") - expr.indexOf("(") + 1);
            } else {
                if (expr.contains("(") && expr.contains(")")) {
                    ArrayList<String> sub = new ArrayList<String>();
                    for (int i = 0; i < expr.indexOf(")"); i++)
                        sub.add(expr.get(i));
                    ReplaceByCalculationResult(CalculateExpressionWithBrackets(sub), expr, 0, expr.indexOf(")") + 1);
                } else {
                    ArrayList<String> sub = new ArrayList<String>();
                    for (int i = expr.indexOf("(") + 1; i < expr.size(); i++)
                        sub.add(expr.get(i));
                    ReplaceByCalculationResult(CalculateExpressionWithBrackets(sub), expr, expr.indexOf("("), expr.size() - expr.indexOf("("));
                }
            }


        }
        return CalculateSimpleExpression(expr);

    }

    private static double CalculateSimpleExpression(ArrayList<String> expr) {
        double result = 0.0;
        while (expr.contains("*") || expr.contains("/")) { //TODO "*" and "/" extract to constants
            double res = 0.0;
            int index = expr.indexOf("*");
            if (index != -1) {
if(expr.size()==2) //TODO reformat code
{
    res = Multi(Double.parseDouble(expr.get(1)), 1);
    ReplaceByCalculationResult(res, expr, 0, 2);
}
else{
    res = Multi(Double.parseDouble(expr.get(index - 1)), Double.parseDouble(expr.get(index + 1)));
    ReplaceByCalculationResult(res, expr, index - 1, 3);
}

            } else {
                index = expr.indexOf("/");
                if(expr.size()==2)
                {
                    res = Multi(Double.parseDouble(expr.get(1)), 1);
                    ReplaceByCalculationResult(res, expr, 0, 2);
                }
                else {
                    res = Div(Double.parseDouble(expr.get(index - 1)), Double.parseDouble(expr.get(index + 1)));
                    ReplaceByCalculationResult(res, expr, index - 1, 3);
                }

            }

        }

        double res = 0.0;
        while (expr.size() != 1) {
            switch (expr.get(1)) {
                case "+":
                    res = Add(Double.parseDouble(expr.get(0)), Double.parseDouble(expr.get(2)));
                    ReplaceByCalculationResult(res, expr, 0, 3);
                    break;
                case "-":
                    res = Sub(Double.parseDouble(expr.get(0)), Double.parseDouble(expr.get(2)));
                    ReplaceByCalculationResult(res, expr, 0, 3);
                    break;
                default: {
                    if (expr.get(0).contains("-")) {
                        res = Multi(Double.parseDouble(expr.get(1)), -1);
                        ReplaceByCalculationResult(res, expr, 0, 2);
                    } else {
                        {
                            res = Multi(Double.parseDouble(expr.get(1)), 1);
                            ReplaceByCalculationResult(res, expr, 0, 2);
                        }
                    }
                }

            }
        }

        result = Double.parseDouble(expr.get(0)); //TODO remove result variable and simplify to return Double.parseDouble(expr.get(0))
        return result;

    }

    private static double Add(double a, double b) {
        return a + b;
    }

    private static double Sub(double a, double b) {
        return a - b;
    }

    private static double Div(double a, double b) {
        return a / b;
    }

    private static double Multi(double a, double b) {
        return a * b;
    }

    private static void ReplaceByCalculationResult(double res, ArrayList<String> list, int index, int numbOfSymb) {
        for (int i = 0; i < numbOfSymb; i++)
            list.remove(index);
        list.add(index, String.valueOf(res));
    }
}

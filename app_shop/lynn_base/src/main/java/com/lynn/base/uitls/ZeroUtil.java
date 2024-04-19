package com.lynn.base.uitls;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Administrator on 2017/11/2.
 */

public class ZeroUtil {

    private static DecimalFormat format;

    public static DecimalFormat getFormat() {
        if (format == null) {
            format = getNewDecimalFormat("#0.00");
        }
        return format;
    }

    public static String subZeroAndDot(String s) {
        if (!TextUtils.isEmpty(s)) {
            if (s.indexOf(".") > 0) {
                s = s.replaceAll("0+?$", "");//去掉多余的0
                s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
        }

        return s;
    }

    public static String subZeroAndDotTwo(double s) {
        return subZeroAndDotTwoDown(s);
    }

    public static String keepTwo(double b) {
        getFormat().setRoundingMode(RoundingMode.HALF_UP);//四舍五入必须加这段；*********
        String str = getFormat().format(b);
        return str;
    }

    public static String subZeroAndDotTwoDown(double s) {
        String str = keepTwoDown(s);
        if (!TextUtils.isEmpty(str)) {

            if (str.indexOf(".") > 0) {
                str = str.replaceAll("0+?$", "");//去掉多余的0
                str = str.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
        }

        return str;
    }

    public static String keepTwoDown(double b) {
        getFormat().setRoundingMode(RoundingMode.DOWN);//向下取整
        String str = getFormat().format(b);
        return str;
    }

    public static double keepTwoDown2(double b) {
        getFormat().setRoundingMode(RoundingMode.DOWN);//向下取整
        String str = getFormat().format(b);
        return Double.parseDouble(str);
    }

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double mulDown(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return keepTwoDown2(b1.multiply(b2).doubleValue());
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 高精度减法
     *
     * @param m1
     * @param m2
     * @return
     */
    public static double sub(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.subtract(p2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 除数
     * @param v2 被除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static float div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static float divForDown(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).floatValue();
    }

    public static String toW(String sale) {
        try {
            double temp = Double.parseDouble(sale);
            if (temp < 10000)
                return sale;
            else {
                return div(temp, 10000, 2) + "万";
            }
        } catch (Exception e) {
            return sale;
        }
    }

    public static String formatTosepara(float data) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(data);
    }

    public static DecimalFormat getNewDecimalFormat(String str) {
        DecimalFormat format = new DecimalFormat(str);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        format.setDecimalFormatSymbols(dfs);
        return format;
    }

    public static boolean isZero(String data) {
      if (TextUtils.isEmpty(data)|| TextUtils.equals(data, "0") || TextUtils.equals(data, "0.0") || TextUtils.equals(data, "0.00"))
          return true;
      return false;
    }
}

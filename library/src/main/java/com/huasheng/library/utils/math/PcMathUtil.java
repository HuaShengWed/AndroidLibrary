/**
 * @(#)MathUtil.java 2014-1-16 Copyright 2014 it.kedacom.com, Inc. All rights
 *                   reserved.
 */

package com.huasheng.library.utils.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @date 2014-1-16
 */

public class PcMathUtil {

	public static DecimalFormat df = new DecimalFormat("#############.##");
	public static DecimalFormat df_int = new DecimalFormat("#############");

	/**
	 * 格式化浮点数
	 *
	 * @param v
	 * @return
	 */
	public static String formatNum(float v) {
		return df.format(v);
	}

	public static String formatYuan(float v) {
		if (v <= 0) {
			return new DecimalFormat("￥###,###.##").format(0);
		}

		return new DecimalFormat("￥###,###.00").format(v);
	}

	/**
	 * 格式化浮点数为整数
	 *
	 * @param v
	 * @return 四舍五入法
	 */
	public static int formatNum2Int(float v) {
		return Integer.valueOf(df_int.format(v));

	}

	/**
	 * 格式化浮点数为整数
	 *
	 * @param v
	 * @return 四舍五入法
	 */
	public static int formatNum2Int(double v) {
		return Integer.valueOf(df_int.format(v));

	}

	/**
	 * clamp value
	 *
	 * @param value 比较值
	 * @param max 最大值
	 * @param min 最小值
	 * @return
	 */
	public static float clamp(float value, float max, float min) {
		return Math.max(Math.min(value, min), max);
	}

	/**
	 * 随机数
	 *
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	public static int getRndNum(int min, int max) {
		return (int) Math.round((max - min) * Math.random() + min);
	}

	/**
	 * 随机数
	 *
	 * @param min 最小值
	 * @param max 最大值
	 * @param nomatchs
	 * @return
	 */
	public static int getRndNum(int min, int max, int[] nomatchs) {
		int i = getRndNum(min, max);
		if (nomatchs == null || nomatchs.length == 0) return i;
		int count = 0;
		while (true) {
			if (count > 5000) {
				return i;
			}
			boolean bEquals = false;
			for (int k = 0; k < nomatchs.length; k++) {
				if (nomatchs[k] == i) {
					i = getRndNum(min, max);
					bEquals = true;
					break;
				}
			}
			if (!bEquals) {
				return i;
			}
			count++;
		}
	}

	/**
	* 浮点数截断
	* 
	* @param f 被截断的浮点数
	* @param decimalPlaces 小数位数，必须大于0
	* @return 截断浮点数，采用四舍五入
	*/
	public static float truncate(float f, int decimalPlaces) {
		float decimalShift = (float) Math.pow(10, decimalPlaces);
		return Math.round(f * decimalShift) / decimalShift;
	}

	/**
	 * 四舍五入
	 * 
	 * @param number 原数
	 * @param decimal 保留几位小数
	 * @return 四舍五入后的值
	 */
	public static BigDecimal round(double number, int decimal) {
		return new BigDecimal(number).setScale(decimal, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 一维数组转为二维数组
	 * 
	 * @param m
	 * @param width
	 * @param height
	 * @return
	 */
	public static int[][] arrayToMatrix(int[] m, int width, int height) {
		int[][] result = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int p = j * height + i;
				result[i][j] = m[p];
			}
		}

		return result;
	}

	/**
	 * 二维数组转为一维数组
	 * 
	 * @param m
	 * @return
	 */
	public static double[] matrixToArray(double[][] m) {
		int p = m.length * m[0].length;
		double[] result = new double[p];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				int q = j * m.length + i;
				result[q] = m[i][j];
			}
		}

		return result;
	}

	/**
	 * int数组转换为double数组
	 * 
	 * @param input
	 * @return
	 */
	public static double[] intToDoubleArray(int[] input) {
		int length = input.length;
		double[] output = new double[length];
		for (int i = 0; i < length; i++) {
			output[i] = Double.valueOf(String.valueOf(input[i]));
		}

		return output;
	}

	/**
	 * int二维数组转换为double二维数组
	 * 
	 * @param input
	 * @return
	 */
	public static double[][] intToDoubleMatrix(int[][] input) {
		int height = input.length;
		int width = input[0].length;
		double[][] output = new double[height][width];
		for (int i = 0; i < height; i++) {
			// 列
			for (int j = 0; j < width; j++) {
				// 行
				output[i][j] = Double.valueOf(String.valueOf(input[i][j]));
			}
		}

		return output;
	}

	/**
	 * 计算数组的平均值
	 * 
	 * @param pixels 数组
	 * @return int 平均值
	 */
	public static int average(int[] pixels) {
		float m = 0;
		for (int i = 0; i < pixels.length; ++i) {
			m += pixels[i];
		}
		m = m / pixels.length;

		return (int) m;
	}

	/**
	 * 计算数组的平均值
	 * 
	 * @param pixels 数组
	 * @return int 平均值
	 */
	public static int average(double[] pixels) {
		float m = 0;
		for (int i = 0; i < pixels.length; ++i) {
			m += pixels[i];
		}
		m = m / pixels.length;

		return (int) m;
	}

}

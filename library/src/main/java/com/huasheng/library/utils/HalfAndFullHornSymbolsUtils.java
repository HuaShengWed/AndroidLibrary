package com.huasheng.library.utils;

import java.util.regex.Pattern;

public final class HalfAndFullHornSymbolsUtils {

	/** 半角标点符号开始位置 */
	private static final int SINGLE_BYTE_SYMBOL_START = 0x0020;
	/** 半角标点符号结束位置 */
	private static final int SINGLE_BYTE_SYMBOL_END = 0x007E;
	/** 半角片假名开始位置 */
	private static final int SINGLE_BYTE_KATAKANA_START = 0xFF61;
	/** 半角片假名结束位置 */
	private static final int SINGLE_BYTE_KATAKANA_END = 0xFF9F;
	/** 半角空格 */
	private static final int SINGLE_BYTE_SPACE_END = 0x0020;

	/** 全角标点符号开始位置 */
	private static final int DOUBLE_BYTE_SYMBOL_START = 0xFF01;
	/** 全角标点符号结束位置 */
	private static final int DOUBLE_BYTE_SYMBOL_END = 0xFF5E;
	/** 全角片假名开始位置 */
	private static final int DOUBLE_BYTE_KATAKANA_START = 0x30A0;
	/** 全角片假名结束位置 */
	private static final int DOUBLE_BYTE_KATAKANA_END = 0x30FF;
	/** 全角空格 */
	private static final int DOUBLE_BYTE_SPACE_END = 0x3000;

	private static final String HALFANDFULLHORNSYMBOLS = "[-,/,|,$,+,%,&,',(,),*,"
			+ "\\x20-\\x2f,\\x3a-\\x40,\\x5b-\\x60,\\x7b-\\x7e,\\x80-\\xff,"
			+ "\u3000-\u3002,\u300a,\u300b,\u300e-\u3011,\u2014,\u2018,\u2019,"
			+ "\u201c,\u201d,\u2026,\u203b,\u25ce,\uff01-\uff5e,\uffe5]";

	/**
	 * 判断字符是否是半角/全角（常见）标点
	 * @param c
	 * @return
	 */
	public static boolean isHalfAndFullHornSymbols(char c) {
		return Pattern.matches(HALFANDFULLHORNSYMBOLS, String.valueOf(c));
	}

	/**
	 * 判断String中是否包含半角/全角（常见）标点
	 * @param s
	 * @return
	 */
	public static boolean containsHalfAndFullHornSysmbols(String s) {
		if (s == null || s.trim().length() == 0) return false;

		boolean f = false;
		char[] c = s.toCharArray();
		for (char d : c) {
			if (Pattern.matches(HALFANDFULLHORNSYMBOLS, String.valueOf(d))) {
				f = true;
				break;
			}
		}
		return f;
	}

	/**
	 * 半角标点符号判定
	 * @return 判定结果 true:半角标点符号
	 */

	public static boolean isSingleByteSymbol(final char c) {
		return (SINGLE_BYTE_SYMBOL_START <= c) && (c <= SINGLE_BYTE_SYMBOL_END) && !isSingleByteAlpha(c)
				&& !isSingleByteDigit(c);

	}

	/**
	 * 半角英字判定
	 * @return 判定结果 true:半角英字
	 */
	public static boolean isSingleByteAlpha(final char c) {
		return (('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z'));
	}

	/**
	 * 半角数字判定
	 * @return 判定结果 true:半角数字
	 */
	public static boolean isSingleByteDigit(final char c) {
		return ('0' <= c) && (c <= '9');
	}

	/**
	 * 半角空格判定
	 * @return 判定结果 true:半角空格
	 */

	public static boolean isSingleByteSpace(final char c) {
		boolean bRet = false;
		if (c == SINGLE_BYTE_SPACE_END) {
			bRet = true;
		}
		return bRet;
	}

	/**
	 * 全角标点符号判定
	 * @return 判定结果 true:全角标点符号
	 */

	public static boolean isSingleFullHornSymbol(final char c) {

		return '。' == c
				|| isSingleFullHornSpace(c)
				|| ((DOUBLE_BYTE_SYMBOL_START <= c) && (c <= DOUBLE_BYTE_SYMBOL_END) && !isSingleFullHornAlpha(c) && !isSingleFullHornDigit(c));

	}

	/**
	 * 全角英字判定
	 * @return 判定结果 true:全角英字
	 */
	public static boolean isSingleFullHornAlpha(final char c) {
		return (('ａ' <= c) && (c <= 'ｚ')) || (('Ａ' <= c) && (c <= 'Ｚ'));
	}

	/**
	 * 全角数字判定
	 * @return 判定结果 true:全角数字
	 */
	public static boolean isSingleFullHornDigit(final char c) {
		return ('０' <= c) && (c <= '９');
	}

	/**
	 * 全角空格判定
	 * @return 判定结果 true:全角空格
	 */

	public static boolean isSingleFullHornSpace(final char c) {
		boolean bRet = false;
		if (c == DOUBLE_BYTE_SPACE_END) {
			bRet = true;
		}
		return bRet;
	}

}

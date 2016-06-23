package com.huasheng.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ValidateUtils {

    /**
     * <pre>
     * [\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?
     * </pre>
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (StringUtils.isNull(email) || !email.contains("@")) return false;

        if (email.length() <= 22) {
            String checkemail = "^([a-z0-9A-Z]+[_|-|\\.]?){0,}[a-z0-9A-Z]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            return validate(checkemail, email);
        } else {
            String a[] = email.split("@");
            if (a.length == 2) {
                String checkemailleft = "^([a-z0-9A-Z]+[_|-|\\.]?){0,}[a-z0-9A-Z]+$";
                String checkemailright = "^([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                return validate(checkemailleft, a[0]) && validate(checkemailright, a[1]);
            }
        }
        return false;
    }

    public static boolean isCellphone(String cellphone) {
        String checkcellphone = "^(13[0-9]|15[0-9]|18[0-9])[0-9]{8}$";
        return validate(checkcellphone, cellphone);
    }

    public static boolean isCellphone15(String cellphone) {
        String checkcellphone = "^[0-9]{0,4}(13[0-9]|15[0-9]|18[0-9])[0-9]{8}$";
        return validate(checkcellphone, cellphone);
    }

    public static boolean isPhone(String cellphone) {
        String checkcellphone = "^[0-9\\,\\.\\#\\*\\(\\)\\+-\\;\\/\\s]+$";
        return validate(checkcellphone, cellphone);
    }

    public static boolean isE164(String e164) {
        String checkE164 = "^[0-9]{13}$";
        return validate(checkE164, e164);
    }

    public static boolean isNumber(String number) {
        String checkE164 = "^[0-9]*$";
        return validate(checkE164, number);
    }

    public static boolean isHost(String host) {
        String checkHost = "^([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return validate(checkHost, host);
    }

    public static boolean isIP(String ip) {
        String checkIP = "^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))$";
        return validate(checkIP, ip);
    }

    public static boolean isUrl(String url) {
        if (null == url || url.length() == 0) return false;

        Pattern patt = Pattern
                .compile(
                        "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$",
                        Pattern.CASE_INSENSITIVE);

        Matcher matcher = patt.matcher(url);
        return matcher.matches();
    }

    public static boolean isVersion(String version) {
        String checkVersion = "^([0-9]*\\.)*[0-9]*$";
        return validate(checkVersion, version);
    }

    /***
     * 判断帐号 $代表结束符
     *
     * @param account
     * @return
     */
    public static boolean isKedacomAccount(String account) {
        if (StringUtils.isNull(account)) {
            return false;
        }
        String check = "^.*@.*\\.kedacom\\.com$";
        String check2 = "^.*@kedacom\\.com$";
        return validate(check, account) || validate(check2, account);
    }

    public static boolean validate(String patternStr, String matcherStr) {
        if (StringUtils.isNull(patternStr) || StringUtils.isNull(matcherStr)) {
            return false;
        }

        if (StringUtils.isNull(patternStr) || StringUtils.isNull(matcherStr)) {
            return false;
        }
        try {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(matcherStr);
            return matcher.matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * 判断是否是表情的标志，<\1>或者<\45>,目前数字为0-55
     *
     * @param num
     * @return
     */
    public static boolean isFace(String num) {
        String checkStr = "^\\<\\\\([0-9]|[1-4][0-9]|[5][0-5])\\>$";
        return validate(checkStr, num);
    }

    /**
     * TL规定，200-500为自定义表情，500+为图片 ;这里统一判断是否是图片的标志，<\200>,<\300>,<\503>目前数字为大于200
     *
     * @param num
     * @return
     */
    public static boolean isPic(String num) {
        String checkStr = "^\\<\\\\([1-9][0-9]{3,}|[2-9][0-9][0-9])\\>$";
        return validate(checkStr, num);
    }

    /**
     * 自定义字符串是否为vconf p2p record 格式为<\\VConfP2PRecord>......<\\VConfP2PRecord>
     * 以<\\VConfP2PRecord>开头和结尾
     *
     * @param str
     * @return
     */
    public static boolean isVConfP2PRecord(String str) {
        String checkStr = "^(\\<\\\\VConfP2PRecord\\>).*(<\\\\VConfP2PRecord\\>)$";
        return validate(checkStr, str);
    }

    /**
     * 电话号码
     *
     * @param phoneNum
     * @return
     */
    public static boolean isPhoneNum(String phoneNum) {
        if (null == phoneNum) return false;

        Pattern patt = Pattern.compile("[0-9]{3,4}//-?[0-9]+");
        Matcher matcher = patt.matcher(phoneNum);
        return matcher.matches();
    }

    /**
     * 身份证
     * <p>
     * <pre>
     * 判断一个字符串是不是身份证号码，即是否是15或18位数字。
     * </pre>
     *
     * @param identityCard
     * @return
     */
    public static boolean isIdentityCard(String identityCard) {
        if (null == identityCard) return false;

        Pattern patt = Pattern.compile("^//d{15}|//d{18}$");
        Matcher matcher = patt.matcher(identityCard);
        return matcher.matches();
    }


    /**
     * 功能：身份证的有效验证
     *
     * @param IDStr 身份证号
     * @return 有效：返回"" 无效：返回String信息
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public static String IDCardValidate(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "X", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                || (gc.getTime().getTime() - s.parse(
                strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
            errorInfo = "身份证生日不在有效范围。";
            return errorInfo;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return errorInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return errorInfo;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return errorInfo;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        } else {
            return "";
        }
        // =====================(end)=====================
        return "";
    }

    @SuppressWarnings("unchecked")
    public static String isBirthday(String IDStr) {
        String errorInfo = "";// 记录错误信息
//		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
//				"3", "2" };
//		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
//				"9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份

        return strYear + "-" + strMonth + "-" + strDay;


    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 验证日期字符串是否是YYYY-MM-DD格式
     *
     * @param str
     * @return
     */
    public static boolean isDataFormat(String str) {
        boolean flag = false;
        // String
        // regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }
}

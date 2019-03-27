package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangyh on 2018/1/28.
 */

public class StringUtil {
    public static boolean isNotEmpty(String str) {
        return str != null && ! "".equals(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNumber(String str){
        String reg = "^[0-9]+$";
        return str.matches(reg);
    }

    public static String getCurTimeString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    public static String join(CharSequence delimeter, Object[] elements) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < elements.length; i++) {
            buffer.append(elements[i]);
            if (i < elements.length - 1) buffer.append(delimeter);
        }
        return buffer.toString();
    }

    public static boolean same(String str1, String str2) {
        return str1 != null && str2 != null && str1.equals(str2);
    }
}

package club.sdll.ptc.spring.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegx {

    public static void main(String[] args) {
        String high = "高温 -16℃";
        String low = "低温 6℃";
        String pattern = "-?\\d+";
        Pattern pa = Pattern.compile(pattern);
        Matcher matcher = pa.matcher(low);
//        Matcher matcher = pa.matcher(high);
        if (matcher.find()) {
            System.out.println("high = " + matcher.group());
        }

    }

}

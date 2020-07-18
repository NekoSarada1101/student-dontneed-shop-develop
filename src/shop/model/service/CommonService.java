package shop.model.service;

public class CommonService {

    public boolean checkLength(String inputText, int maxLength, int minLength) {
        if (inputText.length() <= maxLength && inputText.length() >= minLength)
            return true;
        return false;
    }

    public boolean checkNull(Object input) {
        if (input == null)
            return false;
        return true;
    }

    public String escapeProcess(String str) {
        str = str.replace("&", "&amp");
        str = str.replace("<", "&lt");
        str = str.replace(">", "&gt");
        str = str.replace("\"", "&quot");
        str = str.replace("\'", "&#39");
        str = str.replace(" ", "&nbsp");
        return str;
    }

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}

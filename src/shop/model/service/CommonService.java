package shop.model.service;

public class CommonService {

    public boolean checkLength(Object input, int maxLength, int minLength) {
        String inputText = (String) input;
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
}

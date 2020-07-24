package shop.model.service;

public class ErrorCheckService {

    public static boolean checkLength(String inputText, int maxLength, int minLength) {
        if (inputText.length() <= maxLength && inputText.length() >= minLength) return true;
        return false;
    }

    public static boolean checkStringIsNumber(String num) {
        try {
            int a = Integer.parseInt(num);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean checkAllowedSortColumn(String sortColumn) {
        if (sortColumn.equals("product_id") || sortColumn.equals("product_name") || sortColumn.equals("price"))
            return true;
        return false;
    }

    public static boolean checkAllowedSortOrder(String sortOrder) {
        if (sortOrder.equals("asc") || sortOrder.equals("desc")) return true;
        return false;
    }

    public static String escapeProcess(String str) {
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

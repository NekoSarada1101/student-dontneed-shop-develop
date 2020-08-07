package shop.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ErrorCheckService {

    private static Logger logger = LogManager.getLogger();

    public static boolean checkLength(String inputText, int maxLength, int minLength) {
        if (inputText.length() <= maxLength && inputText.length() >= minLength) return true;
        logger.debug("inputText={}, maxLength={}, minLength={}", inputText, maxLength, minLength);
        return false;
    }

    public static boolean checkStringIsNumber(String num) {
        try {
            Long.parseLong(num);
        } catch (Exception e) {
            logger.debug(e);
            return false;
        }
        return true;
    }

    public static boolean checkAllowedSortColumn(String sortColumn) {
        if (sortColumn.equals("product_id") || sortColumn.equals("product_name") || sortColumn.equals("price"))
            return true;
        logger.debug("sortColumn={}", sortColumn);
        return false;
    }

    public static boolean checkAllowedSortOrder(String sortOrder) {
        if (sortOrder.equals("asc") || sortOrder.equals("desc")) return true;
        logger.debug("sortOrder={}", sortOrder);
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

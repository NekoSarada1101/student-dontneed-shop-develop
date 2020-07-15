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
}

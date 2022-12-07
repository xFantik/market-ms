package market.exceptions;

import java.util.List;

public class ValidateException extends  RuntimeException{
    public ValidateException(List<String> errors) {
        super(String.join(", ", errors));
    }
}

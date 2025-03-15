
import java.util.regex.*;

public class PhoneNumberValidator implements RegexPattern {

    private static final String PHONE_REGEX = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";

    public void testCases() {
        String[] testNumbers = {
            "+1-1234567890", // Valid
            "9876543210", // Valid
            "12345", // Invalid
            "phone1234" // Invalid
        };

        System.out.println("Phone Number Validation:");
        validate(testNumbers, PHONE_REGEX);
    }

    private void validate(String[] testStrings, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        for (String testString : testStrings) {
            Matcher matcher = regex.matcher(testString);
            System.out.println(testString + " -> " + matcher.matches());
        }
        System.out.println();
    }
}

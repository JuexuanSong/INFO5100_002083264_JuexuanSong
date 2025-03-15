
import java.util.regex.*;

public class ZipCodeValidator implements RegexPattern {

    private static final String ZIPCODE_REGEX = "^\\d{5}(-\\d{4})?$"; // US ZIP code

    public void testCases() {
        String[] testZipCodes = {
            "12345", // Valid
            "98765-4321", // Valid
            "ABCDE", // Invalid
            "1234" // Invalid
        };

        System.out.println("ZIP Code Validation:");
        validate(testZipCodes, ZIPCODE_REGEX);
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

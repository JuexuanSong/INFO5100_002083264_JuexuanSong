
import java.util.regex.*;

public class URLValidator implements RegexPattern {

    private static final String URL_REGEX = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-z]{2,6}(/.*)?$";

    public void testCases() {
        String[] testURLs = {
            "https://example.com", // Valid
            "www.google.com", // Valid
            "http:/wrong.com", // Invalid
            "randomtext" // Invalid
        };

        System.out.println("URL Validation:");
        validate(testURLs, URL_REGEX);
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

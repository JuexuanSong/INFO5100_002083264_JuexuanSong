
import java.util.regex.*;

public class EmailValidator implements RegexPattern {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public void testCases() {
        String[] testEmails = {
            "test@example.com", // Valid
            "user.name@domain.com", // Valid
            "user@.com", // Invalid
            "user@domain", // Invalid
            "@nodomain.com" // Invalid
        };

        System.out.println("Email Validation:");
        validate(testEmails, EMAIL_REGEX);
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

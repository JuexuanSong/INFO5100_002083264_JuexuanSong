
import java.util.regex.*;

public class DateValidator implements RegexPattern {

    private static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$"; // YYYY-MM-DD

    public void testCases() {
        String[] testDates = {
            "2023-12-31", // Valid
            "2024-02-29", // Valid (Leap year)
            "99-99-9999", // Invalid
            "2023-13-01" // Invalid
        };

        System.out.println("Date Validation:");
        validate(testDates, DATE_REGEX);
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

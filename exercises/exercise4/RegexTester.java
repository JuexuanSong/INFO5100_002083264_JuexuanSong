
public class RegexTester {

    public static void main(String[] args) {
        RegexPattern[] validators = {
            new EmailValidator(),
            new PhoneNumberValidator(),
            new DateValidator(),
            new URLValidator(),
            new ZipCodeValidator()
        };

        for (RegexPattern validator : validators) {
            validator.testCases();
        }
    }
}

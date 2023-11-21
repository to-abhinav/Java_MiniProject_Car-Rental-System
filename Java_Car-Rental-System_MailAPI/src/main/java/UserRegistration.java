import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserRegistration {

    private static Map<String, String> userDatabase = new HashMap<>();

    public static boolean verifyUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your email: ");
        String email = scanner.next();


        String otp = OtpGenerator.generateOtp();


        EmailService.sendOtpEmail(email, otp);

        System.out.print("Enter the OTP received: ");
        String enteredOtp = scanner.next();

        if (enteredOtp.equals(otp)) {
            System.out.print("Enter your username: ");
            String username = scanner.next();

            System.out.print("Enter your password: ");
            String password = scanner.next();


            userDatabase.put(username, password);

            System.out.println("Registration successful!");

            return true;
        } else {
            System.out.println("Invalid OTP. Registration failed.");

            return false;
        }


    }
}


package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Mã hóa mật khẩu
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Kiểm tra mật khẩu
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}

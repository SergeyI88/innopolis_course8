package labs.lab_1_1;

import java.io.File;

public class ClassContainsMethodsForChecking {
        public static boolean mayBeUrl(String r) {//extract class
            return r.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        }

        public static boolean whatIsIT(String s) {
            File file = new File(s);
            return file.canRead();
        }

        public static boolean readResource(String string) {
            return string.matches("[А-Яа-я0-9 ]+");
        }
}

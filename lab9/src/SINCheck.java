public class SINCheck {
    
    public static boolean check(String str) {
        if (str == "") {
            return false;
        }
        
        for (int i=0; i < str.length(); i++) {
            Character currentChar = str.charAt(i);
            if (!Character.isDigit(currentChar)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkRecursivly(String str) {
        if (str.length() == 0) {
            return true;
        }

        Character currentChar = str.charAt(0);

        if (Character.isDigit(currentChar)) {
            return checkRecursivly(str.substring(1));   
        } else {
            return false;
        }
    }
    
    public static int hash(String str) {
        if (str.length() == 0) {
            return 0;
        }
        int currentDigit = Integer.parseInt(str.substring(0,1));
        return currentDigit + hash(str.substring(1));    
    }

    public static boolean haveSameHash(String str1, String str2) {
        if (checkRecursivly(str1) && checkRecursivly(str2)) {
            if (hash(str1) == hash(str2)) {
                return true;
            }
        }
        return false;
    }
}

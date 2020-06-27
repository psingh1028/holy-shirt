package Validate;

public class ValidateGUI
{
    public static boolean hasNoNumbers(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) >= 48 && str.charAt(i) <=  58)
            {
                return false;
            }
        }
        return true;
    }

    public static boolean hasOnlyNumbers(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) < 48 || str.charAt(i) > 58)
            {
                return false;
            }
        }
        return true;
    }

    public static boolean hasOnlyLettersNumbers(String str)
    {
        int[][] asciiCode = new int[][] {{32, 47}, {58, 64}, {91, 96}, {123, 126}, {128, 128}, {130, 140}, {142, 142}, {145, 156}, {158, 255}};
        for (int i = 0; i < str.length(); i++)
        {
            for (int j = 0; j < asciiCode.length; j++)
            {
                if (str.charAt(i) >= asciiCode[j][0] && str.charAt(i) <= asciiCode[j][1])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hasOnlyLettersNumbersSpaces(String str)
    {
        int[][] asciiCode = new int[][] {{33, 47}, {58, 64}, {91, 96}, {123, 126}, {128, 128}, {130, 140}, {142, 142}, {145, 156}, {158, 255}};
        for (int i = 0; i < str.length(); i++)
        {
            for (int j = 0; j < asciiCode.length; j++)
            {
                if (str.charAt(i) >= asciiCode[j][0] && str.charAt(i) <= asciiCode[j][1])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hasOnlyLettersSpaces(String str)
    {
        int[][] asciiCode = new int[][] {{33, 64}, {91, 96}, {123, 126}, {128, 128}, {130, 140}, {142, 142}, {145, 156}, {158, 255}};
        for (int i = 0; i < str.length(); i++)
        {
            for (int j = 0; j < asciiCode.length; j++)
            {
                if (str.charAt(i) >= asciiCode[j][0] && str.charAt(i) <= asciiCode[j][1])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hasOnlyLetters(String str)
    {
        int[][] asciiCode = new int[][] {{32, 64}, {91, 96}, {123, 126}, {128, 128}, {130, 140}, {142, 142}, {145, 156}, {158, 255}};
        for (int i = 0; i < str.length(); i++)
        {
            for (int j = 0; j < asciiCode.length; j++)
            {
                if (str.charAt(i) >= asciiCode[j][0] && str.charAt(i) <= asciiCode[j][1])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isEmail(String str)
    {
        boolean hasAtSymbol = false;
        boolean hasDotCom = false;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == '@')
            {
                hasAtSymbol = true;
                break;
            }
        }
        if (str.substring(str.length()-4, str.length()).equalsIgnoreCase(".com"))
        {
            hasDotCom = true;
        }
        return (hasAtSymbol && hasDotCom);
    }

    public static boolean isCorrectLength(String str, int targetLength)
    {
        return (str.length() == targetLength);
    }

    public static boolean isLongEnough(String str, int minLength) { return (str.length() >= minLength);}
}

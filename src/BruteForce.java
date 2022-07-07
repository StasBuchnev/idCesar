import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

public class BruteForce {

    public static void bruteForce() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите файл для разшифровки");
        String pathEncrypted = scanner.nextLine();

        String suffixString = pathEncrypted.substring(0, pathEncrypted.lastIndexOf(".")); //из пути зашифрованного файла мы получим новый путь с суфиксом где ,будет находится расшифрованый файл.
        String prefixString = pathEncrypted.substring(pathEncrypted.lastIndexOf("."));
        String pathNotEncrypted = suffixString + "_notEncrypted" + prefixString;
        CaesarCipher caesarCipher = new CaesarCipher();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathEncrypted));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(pathNotEncrypted))
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            while (reader.ready()) {
                String string = reader.readLine();
                stringBuilder.append(string);
            }
            for (int i = 0; i < caesarCipher.maxSize; i++) {
                String deEncryptString = caesarCipher.deEncrypt(stringBuilder.toString(), i);
                if (isValidateText(deEncryptString)) {
                    bufferedWriter.write(deEncryptString + System.lineSeparator());
                    System.out.println("Содержимое файла разшифрованно, ключ  разшифровки = " + i);
                    break;
                }
            }
        }

    }

    private static boolean isValidateText(String text) {

        boolean flag = false;
        int stringStart = new Random().nextInt(text.length() / 2);
        String subString = text.substring(stringStart, stringStart + (int) Math.sqrt(text.length()));

        String[] stringArray = subString.split(" ");
        for (String string : stringArray) {
            if (string.length() > 24) {
                return false;
            }
        }
        if (subString.contains(". ") || subString.contains(", ") || subString.contains("! ") || subString.contains("? ")) {
            flag = true;
        }
        if (flag) {
            System.out.println(subString);
            System.out.println("\n" + "Понятен ли разшифровка текста? YES/NO");
            Scanner scanner = new Scanner(System.in);
            String user = scanner.nextLine();
            if (user.equalsIgnoreCase("YES")) {
                return true;
            } else if (user.equalsIgnoreCase("NO")) {
                flag = false;
            }
        }
        return flag;
    }
}


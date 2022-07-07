import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DeEncryptDate {
    public static void deEncryptDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите файл для разшифровки");
        String pathEncrypted = scanner.nextLine();
        System.out.println("Введите целое число - это будет ключ для разшифрования");
        int key = scanner.nextInt();


        String suffixString = pathEncrypted.substring(0, pathEncrypted.lastIndexOf("."));
        String prefixString = pathEncrypted.substring(pathEncrypted.lastIndexOf("."));
        String pathNotEncrypted = suffixString + "_notEncrypted" + prefixString;

        CaesarCipher caesarCipher = new CaesarCipher();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncrypted));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathNotEncrypted))) {
            while (reader.ready()) {
                String string = reader.readLine();
                String deEncryptString = caesarCipher.deEncrypt(string, key);
                writer.write(deEncryptString + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Путь к файлу не найден");
        }
        System.out.println("Файл разшифрован. Путь к файлу " + pathNotEncrypted);

    }
}

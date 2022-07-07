import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class EncryptDate {

    public static void encryptDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите файл для зашифровки");
        String pathNotEncrypted = scanner.nextLine();
        System.out.println("Введите целое число - это будет ключ шифрования");
        int key = scanner.nextInt();

        String suffixString = pathNotEncrypted.substring(0, pathNotEncrypted.lastIndexOf("."));
        String prefixString = pathNotEncrypted.substring(pathNotEncrypted.lastIndexOf("."));
        String pathEncrypted = suffixString + "_encrypt" + prefixString;

        CaesarCipher caesarCipher = new CaesarCipher();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathNotEncrypted));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathEncrypted))) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encrypt = caesarCipher.encrypt(string, key);
                writer.write(encrypt + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Путь к файлу не найден");
        }
        System.out.println("Файл зашифрован. Путь к файлу " + pathEncrypted);
    }

}

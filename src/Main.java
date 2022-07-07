import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Вы находитесь в меню шифратор-Цезаря");
            System.out.println("Вам доступны команды меню от 1-до 5");
            System.out.println("Назначение клавиш - 1 -> зашифровать текст");
            System.out.println("Назначение клавиш - 2 -> разшифровать текст");
            System.out.println("Назначение клавиш - 3 -> Brute force");
            System.out.println("Назначение клавиш - 4 -> .............");
            System.out.println("Назначение клавиш - 5 -> Выход из меню");

            System.out.println("Выберите действие: ");
            String stringSwitch = reader.readLine();
            switch (stringSwitch) {
                case ("1") -> EncryptDate.encryptDate();
                case ("2") -> DeEncryptDate.deEncryptDate();
                case ("3") -> BruteForce.bruteForce();
                case ("4") -> System.out.println();

            }
            if (stringSwitch.equals("5")) {
                break;
            }
        }
    }
}

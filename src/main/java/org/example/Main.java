package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime start = LocalDateTime.now();
        // читаємо вміст файлу
        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\dima1\\OneDrive\\Рабочий стол\\encryption.txt")));

        // очищуємо текст від небуквених символів та перетворюємо його в нижній регістр
        content = content.replaceAll("[^A-Za-z ]", " ").toLowerCase(Locale.ROOT);

       /* int lastWordsCount = Math.min(words.length, 10);
        System.out.println("\nLast " + lastWordsCount + " words:");
        for (int i = words.length - lastWordsCount; i < words.length; i++) {
            System.out.print(words[i] + " ");
        }*/


        String[] new_content = new String[20];
        String[] num_abc = new String[20];

        System.out.println("Enter the encryption key");
        int key = scanner.nextInt();

        // Шифрування тексту
        String encryptedText = encryptText(content, key);

        // Запис зашифрованого тексту у файл
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\dima1\\OneDrive\\Рабочий стол\\encrypted_output.txt"));
        writer.write(encryptedText);
        writer.close();
    }

    private static String encryptText(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                // Визначення базового значення (A для великих літер, a для малих)
                char base = Character.isUpperCase(character) ? 'A' : 'a';

                // Шифрування символу за допомогою формули Цезаря
                result.append((char) ((character - base + shift) % 26 + base));
            } else {
                // Якщо символ не є літерою, залишаємо його без змін
                result.append(character);
            }
        }

        return result.toString();
    }
}
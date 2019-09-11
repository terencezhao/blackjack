package Yoda;

import java.util.Scanner;

/**
 * YodaSpeak : Create a console program that takes a sentence and reverses the order of the words.
 * For example, if the sentence is "The force is strong with you",
 * The output would be "you with strong is force The". Implement this program iteratively.
 * Created by terencezhao on 11/10/15.
 */
public class YodaSpeak {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence to convert to YodaSpeak(Iterative): ");
        String sentence = scanner.nextLine();
        System.out.println(reverseOrder(sentence));
    }

    public static String reverseOrder(String sentence) {
        String[] words = sentence.split(" ");
        String[] reverseWords = new String[words.length];
        for(int i = 0; i < words.length; i++) {
            reverseWords[i] = words[words.length - (i + 1)];
        }
        String reversedSentence = "";
        for(String word : reverseWords) {
            reversedSentence += word + " ";
        }
        return reversedSentence;
    }
}

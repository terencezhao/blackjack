package Yoda;

import java.util.Scanner;

/**
 * YodaSpeakRecursive : Create console program that takes a sentence and reverses the order of the words.
 * For example, if the sentence is "The force is strong with you",
 * The output would be "you with strong is force The". Implement this program recursively.
 * Created by terencezhao on 11/10/15.
 */
public class YodaSpeakRecursive {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence to convert to YodaSpeak(Recursive): ");
        String sentence = scanner.nextLine();
        System.out.println(reverseOrderRecursive(sentence));
    }

    public static String reverseOrderRecursive(String sentence) {
        String[] words = sentence.split(" ");
        String[] reverse = reverseWords(words, 0, words.length-1);
        return convertToSentence(reverse, 0);
    }

    public static String[] reverseWords(String[] words, int left, int right) {
        if(left >= right) {
            return words;
        } else {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            reverseWords(words, left + 1, right - 1);
        }
        return words;
    }

    public static String convertToSentence(String[] words, int index) {
        if(index == words.length - 1) {
            return words[index];
        } else {
            return words[index] + " " + convertToSentence(words, index + 1);
        }
    }
}

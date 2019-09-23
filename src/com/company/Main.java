package com.company;

public class Main {

    private static String s1 = "приеду шестого";
    private static String s2 = " пгро ш  ес  и  ет од у  ";

    static int[][] key = {
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0}
    };

    public static void main(String[] args) {
        System.out.println(encrypt(s1));
        System.out.println(decrypt(s2));
    }

    private static int[][] rotate90(int[][] arr) {
        int[][] newArr = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                newArr[j][arr.length - 1 - i] = arr[i][j];
            }
        }
        return newArr;
    }

    private static String encrypt(String s) {
        char[] phrase = s.toCharArray();
        char[][] grille = new char[key.length][key[0].length];
        byte index = 0;
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key[0].length; j++) {
                    if (key[i][j] == 1) {
                        if (index < phrase.length) grille[i][j] = phrase[index];
                        else grille[i][j] = '\u0000';
                        index++;
                    }
                }
            }
            key = rotate90(key);
        }

        StringBuilder encryptedPhrase = new StringBuilder();
        for (char[] chars : grille) {
            encryptedPhrase.append(new String(chars));
        }
        return encryptedPhrase.toString();
    }

    private static String decrypt(String s) {
        char[] phrase = s.toCharArray();
        char[][] grille = new char[key.length][key[0].length];
        for (int i = 0; i < grille.length; i++) {
            System.arraycopy(phrase, i * grille.length, grille[i], 0, grille[i].length);
        }
        StringBuilder decryptedPhrase = new StringBuilder();
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key[0].length; j++) {
                    if (key[i][j] == 1 && grille[i][j] != '\u0000') {
                        decryptedPhrase.append(grille[i][j]);
                    }
                }
            }
            key = rotate90(key);
        }
        return decryptedPhrase.toString();
    }
}

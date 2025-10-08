package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RLE {

    public static void main(String[] args) throws FileNotFoundException {
        String decompressedString = textToString("src/main/resources/COVID-19");
        String compressedString = compress(decompressedString);
        System.out.println(compressedString);
    }

    /** This method converts the information stored in a text file into a String. */
    public static String textToString(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            String subSeq = sc.next();
            for (int i = 0; i < subSeq.length(); i++) {
                sb.append(subSeq.charAt(i));
            }
        }
        return sb.toString();
    }

    /** TODO 1: Given a String (a genome sequence of COVID-19) implement the RLE algorithm that will use RLE to compress a String. Returns the compressed String. */
    public static String compress(String uncompressed) {
        String comped="";
        char prev=uncompressed.charAt(0);
        int count=1;
        for(int i=1;i<uncompressed.length();i++){
            char curr=uncompressed.charAt(i);
            if(curr==prev) {
                count++;
            }
            else {
                comped=comped+prev+count;
                prev=curr;
                count=1;
            }
        }
        comped=comped+prev+count;
        return comped;
    }

    /** TODO 2: Given a String (a genome sequence of COVID-19) implement the RLE algorithm that will use RLE to decompress a String. Returns the uncompressed String. */
    public static String decompress(String compressed) {
        String decomped="";
        char letter=0;
        String countStr="";
        for (int i=0; i<compressed.length();i++) {
            char c=compressed.charAt(i);
            if (Character.isLetter(c)) {
                if (letter!=0 && !countStr.isEmpty()) {
                    int count=Integer.parseInt(countStr);
                    for (int k=0;k<count;k++) {
                        decomped=decomped+letter;
                    }
                    countStr="";
                }
                letter=c;
            }
            else if (Character.isDigit(c)) {
                countStr += c;
            }
        }
        if (letter!=0 && !countStr.isEmpty()) {
            int count=Integer.parseInt(countStr);
            for (int k=0;k<count;k++) {
                decomped=decomped+letter;
            }
        }
        return decomped;
    }
}

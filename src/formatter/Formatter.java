package formatter;

import java.io.*;
import java.util.Scanner;

import static java.lang.Character.isLetter;
import static java.lang.Integer.parseInt;

public class Formatter {
    private static String text;
    private static String finalText;


    public static void main(String[] args) throws FileNotFoundException {
        StringBuilder builder = new StringBuilder();
        File obj = new File("rawText.txt");

        Scanner myReader = new Scanner(obj);
        while (myReader.hasNext()) {
            String data = myReader.next();
            builder.append(data);
            builder.append(' ');
        }
        myReader.close();
        text = new String(builder.toString());
        formatString();
        writeToFile(finalText);
        //System.out.print(finalText);



    }

    private static void writeToFile(String text){
        try {
            FileWriter myWriter = new FileWriter("result.txt");
            myWriter.write(text);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

//    public static void clearTheFile() {
//        try {
//            FileWriter fwOb = new FileWriter("FileName", false);
//            PrintWriter pwOb = new PrintWriter(fwOb, false);
//            pwOb.flush();
//            pwOb.close();
//            fwOb.close();
//        } catch (IOException e){
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }

    private static boolean checkForSectionHeading(String word){
        return word.charAt(0) == 'S' &&
                word.charAt(1) == 'e' &&
                word.charAt(2) == 'c' &&
                word.charAt(3) == 't' &&
                word.charAt(4) == 'i' &&
                word.charAt(5) == 'o' &&
                word.charAt(6) == 'n' &&
                word.charAt(7) == ' ' &&
                Character.isDigit(word.charAt(8)) &&
                word.charAt(9) == '.' &&
                Character.isDigit(word.charAt(10));
    }

    private static void formatString(){
        StringBuilder newBuilder = new StringBuilder();
        for(int i = 0; i < text.length(); i++){
            if (text.charAt(i) == 'S' && checkForSectionHeading(text.substring(i, i+11))){
                //TODO: add to new builder and advance i
                newBuilder.append("\nSection ");
                newBuilder.append(text.charAt(i+8));
                newBuilder.append('.');
                newBuilder.append(text.charAt(i+10));
                i += 10;
            }
            else if (java.lang.Character.isLetter(text.charAt(i))){
                newBuilder.append(text.charAt(i));
            }
            else if (text.charAt(i) == ' '){
                newBuilder.append(" ");
            }
            else if (text.charAt(i) == '1' && text.charAt(i+1) == '0'){
                newBuilder.append("\n10.\n");
                i += 2;
            }
            else if (java.lang.Character.isDigit(text.charAt(i))){
                if (text.charAt(i+1) == '.'){
                    if (java.lang.Character.isDigit(text.charAt(i+2))){
                        newBuilder.append(text.charAt(i) + "." + text.charAt(i+2) + "\n");
                        i += 2;
                    }
                    else {
                        newBuilder.append("\n" + text.charAt(i) + ".\n");
                        i++;
                    }
                }
                else if (java.lang.Character.isDigit(text.charAt(i+1))){
                    newBuilder.append(text.charAt(i) + text.charAt(i+1));
                    i++;
                }
                else {
                    newBuilder.append(text.charAt(i));
                }
            }
            else if (text.charAt(i) == '.'){
                newBuilder.append(".\n");
                if (text.charAt(i+1) == ' '){
                    i++;
                }
            }
            else if (text.charAt(i) == '?'){
                newBuilder.append("?\n");
                if (text.charAt(i+1) == ' '){
                    i++;
                }
            }
        }
        finalText = newBuilder.toString();
    }



}
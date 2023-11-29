import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {
    static String inputPath = "crypted_message.txt";
    static String outputPath = "decrypted_message.txt";
    
    public static void decipher(String message, int shift) {
        char[] messageAsChar = message.toCharArray();
        int length = messageAsChar.length;

        for(int i = 0 ; i < length ; i++) {
            if(Character.isLetter(messageAsChar[i])) {
                if(Character.isLowerCase(messageAsChar[i])) {
                    if((char) (messageAsChar[i] - shift) < 'a') {
                        messageAsChar[i] = (char) (messageAsChar[i] - shift + 26);
                    } else {
                        messageAsChar[i] = (char) (messageAsChar[i] - shift);
                    }
                } else if (Character.isUpperCase(messageAsChar[i])) {
                    if((char) (messageAsChar[i] - shift) < 'A') {
                        messageAsChar[i] = (char) (messageAsChar[i] - shift + 26);
                    } else {
                        messageAsChar[i] = (char) (messageAsChar[i] - shift);
                    }
                }
            }
        }

        String line = "Shift: " + shift + " | " + String.valueOf(messageAsChar) + '\n';
        
        try {
            Files.writeString(Paths.get(outputPath), line, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        try {
            Files.deleteIfExists(Paths.get(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String message = Files.readString(Paths.get(inputPath));
            
            for(int shift = 1 ; shift <= 26 ; shift++) {
                decipher(message, shift);
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
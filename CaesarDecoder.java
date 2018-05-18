import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

import java.io.File;
import java.io.FileNotFoundException;
public class CaesarDecoder{
    private static final String LETTERS= "abcdefghijklmnopqrstuvwxyz";
    private static ArrayList<String> words;
    private static final String POSITION = "words_dictionary.json";
    
    
    public static void setupDecoder() throws FileNotFoundException{
        Scanner file = new Scanner(new File(POSITION));
        StringBuilder jsonBuilder = new StringBuilder();    
        while(file.hasNext()){
            jsonBuilder.append(file.nextLine());
            jsonBuilder.append("\n");
        }
        String json=jsonBuilder.toString();
        json.replace("{","");
        json.replace("}","");
        json.replace("\"","");
        words = new ArrayList<>(Arrays.asList(json.split(": 1, ")));
    }
    
    public static String decode(String toDecode){
        for(int i = 0; i<26; i++){
            String text = decode(toDecode,i);
            String[] sentWords = text.split(" ");
            double minWords = sentWords.length / 1.75;
            int engWords = 0;
            for(String word : sentWords){
                if(words.contains("\""+word+"\""){
                    engWords ++;
                }
            }
            if(engWords>=minWords)return text;
        }
        return "Fail";
    }
    
    public static String decode(String toDecode, int encodeValue){
        return encode(toDecode, 26-encodeValue);
    }
    
    public static String encode(String plainText,int shiftKey){
            plainText = plainText.toLowerCase();
            StringBuilder cipherText= new StringBuilder();
            for(char c : plainText.toCharArray()){
                if(LETTERS.contains(Character.toString(c))){
                    cipherText.append(LETTERS.charAt((shiftKey+LETTERS.indexOf(c))%26));
                }else{
                    cipherText.append(c);
                }
        }
        return cipherText.toString();
    }
}

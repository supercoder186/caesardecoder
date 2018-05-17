import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

import java.io.File;
import java.io.FileNotFoundException;
public class CaesarDecoder{
    private static final String LETTERS= "abcdefghijklmnopqrstuvwxyz";
    private static ArrayList<String> words;
    private static final String position = "words_dictionary.json";
    
    
    public static void setupDecoder() throws FileNotFoundException{
        Scanner file = new Scanner(new File(position));
        StringBuilder jsonBuilder = new StringBuilder();    
        String json;
        while(file.hasNext()){
            jsonBuilder.append(file.nextLine());
            jsonBuilder.append("\n");
        }
        json=jsonBuilder.toString();
        json.replace("{","");
        json.replace("}","");
        json.replace("\"","");
    }
    
    private static String decode(String toDecode){
        ArrayList<String> decodeList = new ArrayList<>();
        for(int i = 0;i<26;i++){
            decodeList.add(decode(toDecode,i));
        }
        for(String toCheck : decodeList){
            String[] sentWords = toCheck.split(" ");
            double minWords = sentWords.length/1.75;
            int engWords = 0;
            for(String thingy : sentWords){
                thingy.replace(" ","");
                thingy.replace(".","");
                if(words.contains("\""+thingy+"\"")){
                    engWords ++;
                }
            }
            if(engWords >= minWords)return toCheck;        
        }
        return "Fail";
    }
    
    private static String decode(String toDecode, int encodeValue){
        return encode(toDecode, 26-encodeValue);
    }
    
    private static String encode(String plainText,int shiftKey){
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
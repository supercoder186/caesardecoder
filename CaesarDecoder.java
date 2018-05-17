import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

import java.io.File;
import java.io.FileNotFoundException;
public class CaesarDecoder{
    private static final String LETTERS= "abcdefghijklmnopqrstuvwxyz";
    private static ArrayList<String> words;
    
    
    public static void main(String[] args) throws FileNotFoundException{
        Scanner file = new Scanner(new File("words_dictionary.json"));
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
        words = new ArrayList<>(Arrays.asList(json.split(": 1, ")));
        System.out.println(decode("Vszzc am boas wg hca w oa o voddm pcm vck ofs mci w vcds mci ofs rcwbu kszz am tfwsbr oorwh. Mci ofs o bwqs dsfgcb oorwh."));
        System.out.println(words.contains("hello"));
    }
    
    private static String decode(String toDecode){
        ArrayList<String> decodeList = new ArrayList<>();
        for(int i = 0;i<26;i++){
            decodeList.add(decode(toDecode,i));
        }
        for(String toCheck : decodeList){
            String[] sentWords = toCheck.split(" ");
            double minWords = sentWords.length/1.75;
            System.out.println(minWords);
            int engWords = 0;
            for(String thingy : sentWords){
                thingy.replace(" ","");
                thingy.replace(".","");
                System.out.println("Checking word:"+thingy);
                if(words.contains("\""+thingy+"\"")){
                    engWords ++;
                    System.out.println("Words contains: "+thingy);
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
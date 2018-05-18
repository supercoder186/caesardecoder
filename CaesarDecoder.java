//Import required libraries
//Video documentary at https://www.youtube.com/watch?v=ZLrbJph_Jb0
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

import java.io.File;
import java.io.FileNotFoundException;
public class CaesarDecoder{
    //String of letters - used to get an index of a letter and a letter at a particular index
    private static final String LETTERS= "abcdefghijklmnopqrstuvwxyz";
    //Stores all words from dictionary - around 4.5 MB of data
    private static ArrayList<String> words;
    //Stores position of dictionary file. If the .class file is in the same place, you can leave it the same. Else, supply the path before the String
    private static final String POSITION = "words_dictionary.json";
    
    
    public static void setupDecoder() throws FileNotFoundException{
        //Setup Scanner to read file
        Scanner file = new Scanner(new File(POSITION));
        StringBuilder jsonBuilder = new StringBuilder();
        //This loop checks if there is more data in the file. While there is, it will append it to the StringBuilder
        while(file.hasNext()){
            jsonBuilder.append(file.nextLine());
            jsonBuilder.append("\n");
        }
        String json=jsonBuilder.toString();
        //removes the start and end braces in the json file
        json.replace("{","");
        json.replace("}","");
        //This basically splits the words according to the json format. check the dictionary file if you don't understand
        words = new ArrayList<>(Arrays.asList(json.split(": 1, ")));
        //Now words is the full list of words in the English dictionary
    }
    
    public static String encode(String plainText,int shiftKey){
        //Turns plainText into lower case-makes ciphering easier.
            plainText = plainText.toLowerCase();
            StringBuilder cipherText= new StringBuilder();
            for(char c : plainText.toCharArray()){
                //Checks if letter is a english cheracter
                if(LETTERS.contains(Character.toString(c))){
                    //LETTERS.indexOf(c) calculates the index of c. 'a' would be 0, b would be '1' and so on.
                    //Adding LETTERS.indexOf(c) to shiftKey would give us the index of the new letter. 
                    //For example, a with shift of 1 would give us 0+1=1, which is the index for b
                    //Next, we calculate the number's modulus of 26. Therefore, if the number is more than 26,
                    //it will subtract 26
                    //Then it gets the char at the specified index, and appends it to the String
                    cipherText.append(LETTERS.charAt((shiftKey+LETTERS.indexOf(c))%26));
                }else{
                    //Otherwise, it simply appends the symbol
                    cipherText.append(c);
                }
            }
        //It returns the ciphered String
        return cipherText.toString();
    }
    
    public static String decode(String toDecode, int encodeValue){
        //This function does the inverse of the encode function
        //If you encoded something by shift 22, decoding it by shift 22 will give you the original value.
        return encode(toDecode, 26-encodeValue);
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
}

# caesardecoder
The words_dictionary file MUST be in the same folder as the CaesarDecoder class file for this to work UNLESS YOU: 
Change the value of the position variable to adjust the location of the file.
This is a caesar encryptor/decryptor class in java. It has two methods: encode and decode
In the start of your program, you must call the setupDecoder() method for the decode(String) method to work. It only needs to be called once
The encode method is of type String. It takes in an input of a String and an int.
The String is the text to be shifted, and the int specifies the shift value.
The decode method has two types: one that takes in a String and int, and the other only takes in a String.
The decode(String,int) method is like the inverse of the encode method. The String is the encoded text, and the int is is the shift of the encode. If you had encoded a message by 22, if you decode by 22, you will get the original value.
The decode(String) method is the coolest one. The decoder uses the dictionary file to guess the shift key, and it's usually correct!

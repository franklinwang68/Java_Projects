// Franklin Wang

// A class with methods to manipulate strings
public class HW2 {  
  
  // Capitalizes senetences in a String
  public static String capitalizeSentences(String s){
    StringBuilder builder = new StringBuilder(); //makes the new string
    boolean afterPunctuation = true; //checks whether the next character should be capitalized or not
    
    /* Capitalizes letters if after punctuation and is lowercase, append it as is otherwise
     * Checks if there is a punctuation 
     */ 
    for(int index = 0; index < s.length(); index++){
      if(afterPunctuation){
        if(Character.isLetter(s.charAt(index)) && s.charAt(index) > 96){
          builder.append(Character.toUpperCase(s.charAt(index)));
          afterPunctuation = false;
        }
        else 
          if(!Character.isWhitespace(s.charAt(index))){
            builder.append(s.charAt(index));
            afterPunctuation = false;
          }
          else
            builder.append(s.charAt(index));
      }
      else
        builder.append(s.charAt(index));
      
      if(s.charAt(index) == '?' || 
           s.charAt(index) ==  '.' ||
         s.charAt(index) ==  '!' ){
          afterPunctuation = true;
      }
    }
    return builder.toString();
  }
  
  // Checks if the sequence is in the string
  public static boolean subSequence(String sequence, String string){
    int sequence_index = 0; //keeps track of the sequence index
    
    if(sequence.equals("") || string.equals("")) //returns "" if empty Strings
      return false;
      
    //returns true if all chars of sequence were found in chronological order in string
    for(int string_index = 0; string_index < string.length(); string_index++){
      if(sequence_index == sequence.length())
        return true;
      if(string.charAt(string_index) == sequence.charAt(sequence_index))
        sequence_index++;
    }
    if(sequence_index == sequence.length())
        return true;
    
    return false;
  }
  
  //Removes additional spaces in a String
  public static String removeExtraSpaces(String s){
    StringBuilder builder = new StringBuilder(); //makes the new string
    int countWhitespace = 0; // keeps track of consecutive whitespaces
    int numberOfSpacesAtEnd = 0; //removes the spaces at the end of string
    
    if(s.equals(""))//returns empty string if s is empty
      return "";
    
    //removes the spaces at the end of the string
    for(int index = s.length() - 1; s.charAt(index) == ' ' || s.charAt(index) == '\t'; index--){
      numberOfSpacesAtEnd++;
      if(index <= 0)//returns empty string if s is all whitespace
        return "";
    }
    
    //appends a whitespace when it sees one, appends nothing if there are more white spaces
    //does this until the last non-whitespace character
    for(int index = 0; index < s.length() - numberOfSpacesAtEnd; index++){
      if(s.charAt(index) != ' ' && s.charAt(index) != '\t'){
        builder.append(s.charAt(index));
        countWhitespace = 0;
      }
      else{
        countWhitespace++;
      }
      if(countWhitespace == 1 &&
         countWhitespace != index + 1) //appends nothing if the first indexes are white space
        builder.append(' ');
    }
    return builder.toString();
  }
  
  //Checks if the any of the words in the wordList is in s
  public static boolean containsWord(String s, String wordList){
    int wordStart; //determines the start of a word
    int wordEnd = 0; //determines the end of a word
    int wordList_index; //used to compare letters
    
    wordList = removeExtraSpaces(wordList) + ' '; //cleans up wordList
    if(wordList.equals(" "))//returns false if wordList has no nonwhitespace characters
      return false;
    
    //run until all the words in wordList is checked
    while(wordEnd < wordList.length()){
      //determine where the index of word starts and ends
      for(wordStart = wordEnd; wordList.charAt(wordEnd) != ' '; wordEnd++){
      }
      wordList_index = wordStart;
      
      //checks char by char if word is in s
      for(int s_index = 0; s_index < s.length(); s_index++){
        if(wordList.charAt(wordList_index) == s.charAt(s_index)){
          wordList_index++;
        }
        else
          wordList_index = wordStart;
        if(wordList_index == wordEnd)
          return true;
      }
    
      //move on to next word
      wordEnd++;
    }
    return false;
  }
    
  //returns all the words found in a wordList in an array of Strings 
  public static String wordSearch(String[] board, String wordList){
    StringBuilder builder = new StringBuilder(); //makes the new String
    int wordStart; //determines the start of a word
    int wordEnd = 0; //determines the end of a word
    int wordList_index; //used to compare letters
    int count = 0; //used to keep track of the first space
    
    wordList = removeExtraSpaces(wordList) + ' ';
    
    if(wordList.equals(" ")){ //returns "" if the wordList is empty
      return "";
    }
    
    //while I haven't gone through all the words to look for
    while(wordEnd < wordList.length()){
      //determine the word to look for
      for(wordStart = wordEnd; wordList.charAt(wordEnd) != ' '; wordEnd++){
      }
      
      //append the word if found forwards
      if(findWordForward(board, wordList, wordStart, wordEnd)){
        count++;
        if(count != 1)
          builder.append(' ');
        builder.append(appendTheWordFound(wordList, wordStart, wordEnd));
      }
      else {
        //append the word if found backwards
        if(findWordBackward(board, wordList, wordStart, wordEnd)){
          count++;
          if(count != 1)
            builder.append(' ');
          builder.append(appendTheWordFound(wordList, wordStart, wordEnd));
        }
      }
      
      //move on to the next word
      wordEnd++;
    }
    return builder.toString();
  }
  
  //A method that finds the word in the board fowards
  public static boolean findWordForward(String[] board, String wordList, int wordStart, int wordEnd){
    boolean wordFound = false; //tells whether the word is in the board or not
    int wordList_index = wordStart; //used to compare letters
    
    //for the whole board
    for(int i = 0; i < board.length; i++){
      //look for the word in each line
      for(int s_index = 0; s_index < board[i].length(); s_index++){
        if(wordList.charAt(wordList_index) == board[i].charAt(s_index)){
          wordList_index++;
        }
        else
          wordList_index = wordStart;
        //say yes if I found it
        if(wordList_index == wordEnd)
          wordFound = true;
      }
    }
    return wordFound;
  }
  
  //A method that finds the word in the board backwards
  public static boolean findWordBackward(String[] board, String wordList, int wordStart, int wordEnd){
    boolean wordFound = false; //tells whether the word is in the board or not
    int wordList_index = wordStart; //used to compare letters
    
    //for the whole board
    for(int i = 0; i < board.length; i++){
      //look for the word in each line backwards
      for(int s_index = board[i].length() - 1; s_index >= 0; s_index--){
        if(wordList.charAt(wordList_index) == board[i].charAt(s_index)){
          wordList_index++;
        }
        else
          wordList_index = wordStart;
        //say yes if I found it
        if(wordList_index == wordEnd)
          wordFound = true;
      }
    }
    return wordFound;
  }
  
  //A method that returns the word found
  public static String appendTheWordFound(String wordList, int wordStart, int wordEnd){
    StringBuilder builder = new StringBuilder();//makes the word that is found

    //for the start of the end to the end of it, append each character going up by one
    for(int i = wordStart; i < wordEnd; i++){
      builder.append(wordList.charAt(i));
    }
    return builder.toString();
  }
}
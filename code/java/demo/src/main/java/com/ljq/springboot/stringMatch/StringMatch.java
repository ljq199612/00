package com.ljq.springboot.stringMatch;

import java.util.*;
import java.util.stream.Collectors;

public class StringMatch {

    /**
     *
     * @param size
     * @param wordMixLen
     * @param wordMaxLen
     * @return
     */
    public static String[] randomStringList(int size, int wordMixLen, int wordMaxLen ){

        Set<String> wordList = new HashSet<>();
        while(wordList.size() != size){
            String word = randomWord(wordMixLen, wordMaxLen);
            wordList.add(word);
        }

        return wordList.toArray( new String[wordList.size()] );
    }

    /**
     *
     * @param minLength
     * @param maxLength
     * @return
     */
    public static String randomWord(int minLength, int maxLength){

        char[] match = match();
        StringBuffer s = new StringBuffer();
        s.append(match[(int)Math.random()*match.length]);
        Random random = new Random();
        int wordLength = (int)(Math.random()*(maxLength-minLength))+minLength;

        return
            random.ints(wordLength, 0,match.length)
                .mapToObj(i->match[i]+"")
                .collect(Collectors.joining());

    }


    // TODO
    private static char[] match(){
        String str = "abcdefghijklmnopqrstuvwxyz";
        str += str.toUpperCase();
        return str.toCharArray();
    }




}

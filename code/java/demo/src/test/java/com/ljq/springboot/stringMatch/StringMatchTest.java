package com.ljq.springboot.stringMatch;

//import org.junit.Test;
import com.ljq.springboot.healthStatus.Device;
import com.ljq.springboot.healthStatus.Person;
import com.ljq.springboot.healthStatus.Record;
import com.ljq.springboot.tools.CustomLogger;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)

@SpringBootTest

public class StringMatchTest {

    @Test
    public void main() {

        String[] str = StringMatch.randomStringList(10, 3, 10);




       /*
        HashSet<String> wordList = new HashSet<>();
        while(wordList.size() != 3){
            String word = StringMatch.randomWord(3, 10);
            wordList.add(word);
        }

*/
            /*   //  ArrayUtils.toString(
                    random.ints(wordLength, 0,match.length)
                            .mapToObj(i->match[i]+"")
                            .collect(Collectors.joining());
                            //.forEach(System.out::println);
              //      ""
         //       );

        //String word = s.toString();

*/

    }

}

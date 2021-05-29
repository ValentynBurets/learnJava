package com.company;

import org.junit.jupiter.api.Test;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextManagerTest {

    @org.junit.jupiter.api.Test
    public void textInBrackets() throws Exception{

        String text = "gdhtd (  rgrg ) gdhdr r(gdrh )";
        ArrayList<String> ShouldText = new ArrayList<String>();
        ShouldText.add(new String("  rgrg "));
        ShouldText.add(new String("gdrh "));

        TextManager test = new TextManager(text);
        ArrayList<String> RetStrings = new ArrayList<String>();


        RetStrings = test.TextInBrackets();

        assertEquals(ShouldText, RetStrings);

    }

    @org.junit.jupiter.api.Test
    public void textInDoubleBrackets() throws Exception{

        String text = "gdhtd ( test(  rgrg ) gdhdr rgdrh )";
        ArrayList<String> ShouldText = new ArrayList<String>();
        ShouldText.add(new String(" test gdhdr rgdrh "));

        TextManager test = new TextManager(text);
        ArrayList<String> RetStrings = new ArrayList<String>();


        RetStrings = test.TextInBrackets();

        assertEquals(ShouldText, RetStrings);

    }

    @Test
    void findOuterBrackets() {

        Map<Integer,Integer> testPairs = new HashMap<Integer, Integer>();

        testPairs.put(1, 5);
        testPairs.put(2, 3);

        Map<Integer,Integer> shouldTestPairs = new HashMap<Integer, Integer>();
        shouldTestPairs.put(1, 5);


        assertEquals(shouldTestPairs, TextManager.FindOuterBrackets(testPairs));
    }

    @Test
    void findOuterBracketsDoubleBrackets() {

        Map<Integer,Integer> testPairs = new HashMap<Integer, Integer>();

        testPairs.put(1, 10);
        testPairs.put(2, 7);
        testPairs.put(4, 5);

        Map<Integer,Integer> shouldTestPairs = new HashMap<Integer, Integer>();
        shouldTestPairs.put(1, 10);


        assertEquals(shouldTestPairs, TextManager.FindOuterBrackets(testPairs));
    }

    @Test
    void deleteEngLexem() {
        String text = " this is a test. Or something else. the or on ";
        String shouldText = " this is  test. something else.  ";
        TextManager manager = new TextManager(text);

        manager.DeleteEngLexem();

        assertEquals(shouldText, manager.toString());
    }
}
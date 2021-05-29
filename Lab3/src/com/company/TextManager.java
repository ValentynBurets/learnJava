package com.company;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextManager {
    static public String text;
    private ArrayList <HashMap<Integer,Integer>>BracketIndexes;
    public Map<Integer,Integer> IndexPair;
    public TextManager(String text){
        this.text=text;
        BracketIndexes=new ArrayList <HashMap<Integer,Integer>>();
    }
    public void  setText(String text){
        this.text=text;
    }





    public ArrayList<String> TextInBrackets(){
        FindIndexes();

        removeTextFromInnerBrackets(IndexPair);

        FindIndexes();

        Map<Integer, Integer> OuterBrackets = new HashMap<Integer, Integer>();

        OuterBrackets = FindOuterBrackets(IndexPair);

        ArrayList<String>StringsInBrackets = new ArrayList<String>();

        for (Map.Entry<Integer, Integer> entry : OuterBrackets.entrySet()) {
            StringsInBrackets.add(text.substring(entry.getKey() + 1, entry.getValue()));
        }

        ArrayList<String> RetStrings = new ArrayList<String>();

        for(var item: StringsInBrackets)
        {
            RetStrings.add(ReplaysNubersInText(item));
        }

        return RetStrings;
    }

    private int nearestIndex(int index, ArrayList<Integer> openIndex)
    {
        int ret_i = index;
        for(var i: openIndex)
        {
            if(i < index)
            {
                ret_i = i;
            }
            else
            {
                break;
            }
        }

        return ret_i;
    }

    static public Map<Integer,Integer> FindOuterBrackets(Map<Integer,Integer> IndexPair)
    {
        Map<Integer, Integer> OuterBrackets = new HashMap<Integer, Integer>();
        OuterBrackets.putAll(IndexPair);

        ArrayList<Integer> RemoveIndex = new ArrayList<Integer>();
        boolean exit = false;
        int i = 0;

        while(i < OuterBrackets.size()) {


            for (Map.Entry<Integer, Integer> entry : OuterBrackets.entrySet()) {
                for (Map.Entry<Integer, Integer> nextEntry : OuterBrackets.entrySet()) {
                    if (entry != nextEntry) {
                        if ( entry.getKey() < nextEntry.getKey()  && nextEntry.getValue() < entry.getValue()) {
                            RemoveIndex.add(nextEntry.getKey());
                            exit = true;
                            break;
                        }
                    }
                }
                if (exit)
                    break;
                i++;
            }

            for (var item : RemoveIndex) {
                OuterBrackets.remove(item);
            }

            RemoveIndex.clear();
            exit = false;

        }
        return OuterBrackets;
    }

    private void removeTextFromInnerBrackets(Map<Integer,Integer> brackets)
    {
        for(Map.Entry<Integer, Integer> entry : brackets.entrySet())
        {
            findBrackets(brackets, entry.getKey());
        }
    }

    private void findBrackets(Map<Integer,Integer> brackets, int openBracket)
    {
        for (Map.Entry<Integer, Integer> entry : brackets.entrySet()) {
            if (openBracket != entry.getKey()) {
                if (openBracket < entry.getKey() && entry.getValue() < brackets.get(openBracket)){
                    findBrackets(brackets, entry.getKey());

                    StringBuilder sb = new StringBuilder(text);
                    sb.delete(entry.getKey(), entry.getValue() + 1);
                    text = sb.toString();

                    //String tempString = text.substring(entry.getKey(), entry.getValue() + 1);
                    //String temp = "";
                    //text = text.replaceAll(tempString, temp);
                }
            }
        }
    }





    private void FindIndexes() {
        ArrayList<Integer> openIndex = new ArrayList<Integer>();;
        ArrayList<Integer> closeIndex  = new ArrayList<Integer>();;

        int j = 0;
        if (text != null) {
            if (text.length() > 0) {
                openIndex = new ArrayList<Integer>(IntStream
                        .range(0, text.length())
                        .filter(i -> (text.toCharArray()[i] == '('))
                        .boxed().collect(Collectors.toList()));
                closeIndex = new ArrayList<Integer>(IntStream
                        .range(0, text.length())
                        .filter(i -> (text.toCharArray()[i] == ')'))
                        .boxed().collect(Collectors.toList()));
            }
        }
        if(IndexPair == null)
        {
            IndexPair = new HashMap<Integer, Integer>();
        }
        if(!(IndexPair.isEmpty()) )
        {
            IndexPair.clear();
        }

        for(var closeItem: closeIndex){
            int openI = nearestIndex(closeItem, openIndex);

            IndexPair.put(openI, closeItem);
            openIndex.remove(new Integer(openI));
        }
    }



    private String ReplaysNubersInText(String text){
        String out= new String(text);
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ((Character.isDigit(c)||((c=='.')&&(number.length()>0)))) {
                number.append(c);
            } else if (number.length() > 0){
                out=out.replace(number,"#");
                number= new StringBuilder();
            }
        }
        return out;
    }

    public void DeleteEngLexem(){

        //(a, the), а також слова or, are, on, in, out

        Pattern pattern = Pattern.compile(" o.+?| O.+?");
        Matcher matcher = pattern.matcher(text);
        text = matcher.replaceAll("");

        replace_OR_ON();
        //text=text.replaceAll("\\ba\\b|\\bis\\b|\\bon\\b|\\bor\\b|\\bare\\b|\\bof\\b|\\bin\\b|\\bor\\b|\\bout\\b","");
        //text=text.replaceAll("\\b   \\b|\\b  \\b"," ");
    }


    static public void replace_OR_ON() {
        StringBuilder sb = new StringBuilder(text);

        Pattern pattern1 = Pattern.compile(" a | the | on | out ");

        Matcher matcher = pattern1.matcher(text);

        while (matcher.find()) {
            int start=matcher.start();
            int end=matcher.end();
            sb.delete(++start, --end);
            matcher = pattern1.matcher(sb.toString());
        }

        text = sb.toString();

    }

    @Override
    public String toString() {
        return text;
    }
}
package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    protected Dictionary dictionary = new Dictionary();
    private static final String PATH = "dictionary.txt";
    private ArrayList<Word> wordAL = new ArrayList<>();

    public void insertFromCommandline() {
        Scanner input = new Scanner(System.in);
        String newword = input.nextLine();
        String definition = input.nextLine();
        dictionary.addNewWord(newword, definition);
    }

    public void insertFromFile() {
        String line = null;
        String[] strings;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(PATH);
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                strings = line.split("\t");
                dictionary.addNewWord(strings[0], strings[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteWord(String rmword) {
        for (Word word : dictionary.getWordArrayList()) {
            if (rmword.equals(word.getWordTarget())) {
                dictionary.removeWord(word);
                break;
            }
        }
        this.dictionaryExportToFile();
    }

    public void dictionaryExportToFile() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(PATH, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary.getWordArrayList()) {
                bufferedWriter.write(word.getWordTarget() + "\t" + word.getDefinition());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addToFile(String newWord, String definition) {
        dictionary.addNewWord(newWord, definition);
        this.dictionaryExportToFile();
    }

    public void showAllWords() {
        this.insertFromFile();
        for (Word word : this.dictionary.getWordArrayList()) {
            System.out.println(word.getWordTarget() + "\t" + word.getDefinition());
        }
    }

    public String dictionaryLookup(String word) {
        for(int i = 0; i < dictionary.getWordArrayList().size(); i++) {
            if (dictionary.getWordArrayList().get(i).getWordTarget().
                    equals(word)) {
                return dictionary.getWordArrayList().get(i).getDefinition();
            }
        }
        return "Not found";
    }

    public Word search(String wordTarget) {
        return dictionary.binarySearch(0, dictionary.getWordArrayList().size() - 1, wordTarget);
    }

    /*public void markToFile(String newWord) {
        for (Word word : this.dictionary.getWordArrayList()) {
            newWord = dictionaryManagement.search();


            }
        }*/
}

package sample;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> wordArrayList = new ArrayList<>();

    public void addNewWord(String newWord, String definition) {
        Word word = new Word(newWord, definition);
        wordArrayList.add(word);
    }

    public void removeWord(Word word) {
        wordArrayList.remove(word);
    }

    public ArrayList<Word> getWordArrayList() {
        return wordArrayList;
    }

    public void setWordArrayList(ArrayList<Word> wordArrayList) {
        this.wordArrayList = wordArrayList;
    }

    public Word binarySearch(int left, int right, String wordTarget) {
        int mid = left + (right - left) / 2;
        Word midWord = wordArrayList.get(mid);
        int temp = midWord.getWordTarget().compareTo(wordTarget);
        if (temp == 0) {
            return midWord;
        }
        if (temp > 0) {
            return binarySearch(left, mid - 1, wordTarget);
        }
        return binarySearch(mid + 1, right, wordTarget);
    }
}

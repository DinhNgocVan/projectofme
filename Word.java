package sample;

public class Word {
    private String wordTarget;
    private String definition;

    public Word(String wordTarget, String definition) {
        this.wordTarget = wordTarget;
        this.definition = definition;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String word) {
        this.wordTarget = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}

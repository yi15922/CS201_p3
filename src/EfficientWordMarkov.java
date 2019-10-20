import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov{

    private Map<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov (int order){
        super(order);
        myMap = new HashMap<>();
    }

    public EfficientWordMarkov(){
        this(2);
    }

    @Override
    public void setTraining(String text) {
        myWords = text.split("\\s+");
        myMap.clear();
        WordGram theGram = new WordGram(myWords, 0, myOrder);


        for (int i = 0; i < myWords.length; i++){
            //System.out.println(theGram + " " + myMap.get(theGram));
            myMap.putIfAbsent(theGram, new ArrayList<>());
            if (i+myOrder >= myWords.length) {
                //System.out.println("Found end with " + theGram);
                myMap.get(theGram).add(PSEUDO_EOS);
                break;
            }
            myMap.get(theGram).add(myWords[i+myOrder]);
            theGram = theGram.shiftAdd(myWords[i+myOrder]);
        }
    }

    @Override
    public ArrayList<String> getFollows(WordGram kGram) {
        if (!myMap.containsKey(kGram)) {
            throw new NoSuchElementException(kGram + " not in map");
        }
        return myMap.get(kGram);
    }

}

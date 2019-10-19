import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


        for (int i = 1; i < myWords.length - myOrder; i++){
            //System.out.println(theGram + " " + myMap.get(theGram));
            myMap.putIfAbsent(theGram, new ArrayList<>());
            myMap.get(theGram).add(myWords[i+1]);
            theGram = theGram.shiftAdd(myWords[i+1]);
        }
    }

}

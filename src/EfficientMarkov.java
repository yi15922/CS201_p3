import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;

	public EfficientMarkov (int order) {
	super(order);
	myMap = new HashMap<>();
	}

	public EfficientMarkov(){
		this(3);
	}

	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();

		for (int i = 0; i < myText.length() - myOrder; i++){
			String theGram = myText.substring(i, i+myOrder);
			//System.out.println(theGram + " " + myMap.get(theGram));
			myMap.putIfAbsent(theGram, new ArrayList<>());

			if (i+myOrder+1 >= myText.length()) {
				//System.out.println("Found end with " + theGram);
				myMap.get(theGram).add(PSEUDO_EOS);
				break;
			}
			myMap.get(theGram).add(String.valueOf(myText.charAt(i+myOrder)));
		}
	}

	@Override
	public ArrayList<String> getFollows(String key) {
		if (!myMap.containsKey(key)) {
			throw new NoSuchElementException(key + " not in map");
		}
		return myMap.get(key);


	}




}	

package eus.healthit.bchef.core.stt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

/**
 * A simple example that shows how to transcribe a continuous audio file that
 * has multiple utterances in it.
 */
public class Test {

	Configuration config;
	LiveSpeechRecognizer recognizer;
	SpeechResult result;
	List<WordResult> resultWords;
	
	public Test() {
		config = new Configuration();
		resultWords = new ArrayList<>();
	}
	
	public void startListening() {
		config.setDictionaryPath("resources/voiceRecognition/es.dict");
		
		try {
			recognizer = new LiveSpeechRecognizer(config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result = recognizer.getResult();
		resultWords = result.getWords();
		
		
		for (WordResult w : resultWords) {
			System.out.println(w.toString());
		}
	}
}
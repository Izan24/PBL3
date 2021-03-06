package eus.healthit.bchef.core.assistant.implementations.input;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.StreamingRecognitionResult;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;

public class RealtimeResponseObserver implements ResponseObserver<StreamingRecognizeResponse> {
	PropertyChangeSupport connector;
	ArrayList<StreamingRecognizeResponse> responses = new ArrayList<>();
	AudioInputController parent;

	public RealtimeResponseObserver(AudioInputController parent) {
		this.parent = parent;
		connector = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListeners(PropertyChangeListener listener) {
		connector.addPropertyChangeListener(listener);
	}

	public void onStart(StreamController controller) {
		System.out.println("Voice Recon Started");
	}

	public void onResponse(StreamingRecognizeResponse response) {
		sendText(response);

	}

	public void sendText(StreamingRecognizeResponse response) {
		try {
			StreamingRecognitionResult result = response.getResultsList().get(0);
			SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
			connector.firePropertyChange("NEW_COMMAND", null, alternative.getTranscript());
			System.out.println("Input: "+alternative.getTranscript());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onComplete() {
		for (StreamingRecognizeResponse response : responses) {
			StreamingRecognitionResult result = response.getResultsList().get(0);
			SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
			System.out.printf("Final Transcript : %s\n", alternative.getTranscript());
		}
	}

	public void onError(Throwable t) {
		t.printStackTrace();
	}

}
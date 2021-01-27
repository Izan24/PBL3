package eus.healthit.bchef.core.assistant.implementations.input;

import java.beans.PropertyChangeListener;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.TargetDataLine;

import com.google.api.gax.rpc.ClientStream;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.StreamingRecognitionConfig;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.protobuf.ByteString;

import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.assistant.implementations.OutputController;

public class AudioInputController extends Thread {

	private boolean reconOn;

	private static AudioInputController instance = new AudioInputController();
	private RealtimeResponseObserver responseObserver;

	private AudioInputController() {

	}

	public static AudioInputController getInstance() {
		return instance;
	}

	public void run() {
		while (true)
			try {
				streamingMicRecognize();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void stopRecon() {
		reconOn = false;
	}

	public void startRecon() {
		reconOn = true;
	}

	@Deprecated
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		if (responseObserver != null) {
			responseObserver.addPropertyChangeListeners(listener);
		}
	}

	@SuppressWarnings("static-access")
	private void streamingMicRecognize() {
		while (true) {
			if (!OutputController.getInstance().isComplete())
				continue;
			TargetDataLine targetDataLine = null;
			System.out.println("out again");
			try (SpeechClient client = SpeechClient.create()) {

				responseObserver = new RealtimeResponseObserver(this);
				responseObserver.addPropertyChangeListeners(BChefController.getInstance());

				ClientStream<StreamingRecognizeRequest> clientStream = client.streamingRecognizeCallable()
						.splitCall(responseObserver);

				RecognitionConfig recognitionConfig = RecognitionConfig.newBuilder()
						.setEncoding(RecognitionConfig.AudioEncoding.LINEAR16).setLanguageCode("es-ES")
						.setSampleRateHertz(16000).build();
				StreamingRecognitionConfig streamingRecognitionConfig = StreamingRecognitionConfig.newBuilder()
						.setConfig(recognitionConfig).build();

				StreamingRecognizeRequest request = StreamingRecognizeRequest.newBuilder()
						.setStreamingConfig(streamingRecognitionConfig).build();
				clientStream.send(request);
				AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, false);

				DataLine.Info targetInfo = new Info(TargetDataLine.class, audioFormat);
				if (!AudioSystem.isLineSupported(targetInfo)) {
					System.out.println("Microphone not supported");
					System.exit(0);
				}
				targetDataLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
				targetDataLine.open(audioFormat);
				targetDataLine.start();
				System.out.println("Start speaking");

				AudioInputStream audio = new AudioInputStream(targetDataLine);
				while (true) {
					if (!OutputController.getInstance().isComplete())
						break;
					byte[] data = new byte[6400];
					audio.read(data);
					if (reconOn) {
						request = StreamingRecognizeRequest.newBuilder().setAudioContent(ByteString.copyFrom(data))
								.build();

						clientStream.send(request);

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (targetDataLine != null) {
					targetDataLine.stop();
					targetDataLine.close();
					responseObserver.onComplete();
				}
			}
		}
	}
}
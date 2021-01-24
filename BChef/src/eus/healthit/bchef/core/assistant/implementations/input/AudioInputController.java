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

public class AudioInputController extends Thread {

	private boolean reconOn;
	private boolean broken;

	private static AudioInputController instance = new AudioInputController();
	private RealtimeResponseObserver responseObserver;

	private AudioInputController() {

	}

	public static AudioInputController getInstance() {
		return instance;
	}
	
	public void repair() {
		broken = true;
	}

	public void run() {
		while (true)
			try {
				streamingMicRecognize();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void stopRecon() {
		reconOn = false;
	}

	public void startRecon() {
		reconOn = true;
//		try {
//			streamingMicRecognize();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Deprecated
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		if (responseObserver != null) {
			responseObserver.addPropertyChangeListeners(listener);
		}
	}

	private void streamingMicRecognize() {
		while (true) {
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
						.setStreamingConfig(streamingRecognitionConfig).build(); // The first request in a streaming
																					// call
				clientStream.send(request);
				// SampleRate:16000Hz, SampleSizeInBits: 16, Number of channels: 1, Signed:
				// true,
				// bigEndian: false
				AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, false);

				DataLine.Info targetInfo = new Info(TargetDataLine.class, audioFormat); // Set the system information to
																						// read from the microphone
				if (!AudioSystem.isLineSupported(targetInfo)) {
					System.out.println("Microphone not supported");
					System.exit(0);
				}
				// Target data line captures the audio stream the microphone produces.

//			TargetDataLine targetDataLine = (TargetDataLine) mixer.getLine(targetInfo);
				targetDataLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
				targetDataLine.open(audioFormat);
				targetDataLine.start();
				System.out.println("Start speaking");
//			long startTime = System.currentTimeMillis();
				// Audio Input Stream
				AudioInputStream audio = new AudioInputStream(targetDataLine);
				while (!broken) {
//					if (OutputController.isComplete() && reconOn) {
					byte[] data = new byte[6400];
					audio.read(data);
						if (reconOn) {
//				long estimatedTime = System.currentTimeMillis() - startTime;
//						if (reconOn && OutputController.isComplete()) {
						request = StreamingRecognizeRequest.newBuilder().setAudioContent(ByteString.copyFrom(data))
								.build();
						
						clientStream.send(request);

//						}
					}

//				if (estimatedTime > 60000) { // 60 seconds

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
			try {
				this.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			broken = false;
		}
	}
}
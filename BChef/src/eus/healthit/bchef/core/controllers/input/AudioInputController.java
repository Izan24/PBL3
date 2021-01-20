package eus.healthit.bchef.core.controllers.input;

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

import eus.healthit.bchef.core.controllers.BChefController;
import eus.healthit.bchef.core.controllers.implementations.OutputController;
import io.grpc.StatusRuntimeException;

public class AudioInputController extends Thread {

	private static boolean reconOn;

	private static AudioInputController instance = new AudioInputController();
	private RealtimeResponseObserver responseObserver;

	private AudioInputController() {
	}

	public static AudioInputController getInstance() {
		return instance;
	}

	public void stopRecon() {
		reconOn = false;
	}

	public void startRecon() {
		reconOn = true;
		try {
			streamingMicRecognize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Deprecated
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		if (responseObserver != null) {
			responseObserver.addPropertyChangeListeners(listener);
		}
	}

	private void streamingMicRecognize() {
		while (reconOn) {
			TargetDataLine targetDataLine = null;
			System.out.println("out again");
			try (SpeechClient client = SpeechClient.create()) {

				responseObserver = new RealtimeResponseObserver();
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
																					// has to be a config

				clientStream.send(request);
				// SampleRate:16000Hz, SampleSizeInBits: 16, Number of channels: 1, Signed:
				// true,
				// bigEndian: false
				AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, false);
				DataLine.Info targetInfo = new Info(TargetDataLine.class, audioFormat); // Set the system information to
																						// read from the microphone
																						// audio
																						// stream

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
				while (true) {
					if (!reconOn) {
						System.out.println("Stop speaking.");
						break;
					}

					if (!OutputController.isComplete()) {
						Thread.sleep(100);
						continue;
					}
//				long estimatedTime = System.currentTimeMillis() - startTime;
					byte[] data = new byte[6400];
					audio.read(data);
					request = StreamingRecognizeRequest.newBuilder().setAudioContent(ByteString.copyFrom(data)).build();

					clientStream.send(request);

//				if (estimatedTime > 60000) { // 60 seconds

				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("this bitch in'ere");
			} finally {
				if (targetDataLine != null) {
					targetDataLine.stop();
					targetDataLine.close();
				}
			}
			responseObserver.onComplete();
		}
	}
}
package eus.healthit.bchef.core.assistant.implementations;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;

import eus.healthit.bchef.core.assistant.interfaces.IOutputController;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class OutputController implements IOutputController {

	private static OutputController instance = new OutputController();
	static Player playMP3;

	private OutputController() {

	}

	public static OutputController getInstance() {
		return instance;
	}

	public static boolean isComplete() {
		if (playMP3 == null) {
			return true;
		}
		return playMP3.isComplete();
	}

	@Override
	public void send(String text) {
		System.out.println("Audio output: " + text);
		try {
			String inputText = text;

			if (inputText != null && inputText != "" && inputText != " ") {
				try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
					SynthesisInput input = SynthesisInput.newBuilder().setText(inputText).build();

					VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode("es-ES")
							.setSsmlGender(SsmlVoiceGender.MALE).build();

					AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();

					SynthesizeSpeechResponse res = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
					InputStream targetStream = new ByteArrayInputStream(res.getAudioContent().toByteArray());
					playMP3 = new Player(targetStream);
					playMP3.play();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void soundAlarm() {
		InputStream targetStream;
		try {
			targetStream = new FileInputStream("resources/audio/beep.mp3");
			Player playMP3;
			playMP3 = new Player(targetStream);
			playMP3.play();
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}
	}

}

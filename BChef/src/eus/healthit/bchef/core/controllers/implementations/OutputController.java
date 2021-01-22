package eus.healthit.bchef.core.controllers.implementations;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.print.attribute.PrintServiceAttribute;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.Empty;

import eus.healthit.bchef.core.controllers.interfaces.IOutputController;
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
		// Instantiates a client
		try {
			String inputText = text;

			if (inputText != null && inputText != "" && inputText != " ") {
				try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
					// Set the text input to be synthesized
					SynthesisInput input = SynthesisInput.newBuilder().setText(inputText).build();

					// Build the voice request, select the language code ("en-US") and the ssml
					// voice gender
					// ("neutral")
					VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode("es-ES")
							.setSsmlGender(SsmlVoiceGender.MALE).build();

					// Select the type of audio file you want returned
					AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();

					// Perform the text-to-speech request on the text input with the selected voice
					// parameters and
					// audio file type
//					SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

					// Get the audio contents from the response
					// ByteString audioContents = response.getAudioContent();

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

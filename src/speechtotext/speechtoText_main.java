package speechtotext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;


public class speechtoText_main {
		public static void main(String[] args) {
			SpeechToText service = new SpeechToText();
			service.setUsernameAndPassword("e1c2bfb1-d8ac-4a0f-b717-568705cd4aca", "pGfQPiANftj1");

			File audio = new File("audio/output.wav");
			RecognizeOptions options = null;
			try {
				options = new RecognizeOptions.Builder()
						.model("ja-JP_BroadbandModel")
						.audio(audio).contentType(RecognizeOptions.ContentType.AUDIO_WAV)
						.build();
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			SpeechRecognitionResults transcript = service.recognize(options).execute();

			System.out.println(transcript);

			//階層構造の結果を表示するよ
			String s = String.valueOf(transcript);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = null;
			try {
				node = mapper.readTree(s);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}


			System.out.println("transcript:" + node.get("results").get(0).get("alternatives").get(0)
					.get("transcript").toString());

			System.out.println("confidence:" + node.get("results").get(0).get("alternatives").get(0)
					.get("confidence").asDouble());

		}


	}


package operation;

import org.springframework.web.bind.annotation.*;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.client5.http.fluent.Request;

@RestController
@RequestMapping("/api")
public class AIServiceController {

    private static final String API_KEY = "AIzaSyDK6yoCQ2d2CC0_nyiEk9RUk_dNxsOcmU4";
    private static final String GEMINI_ENDPOINT_BASE =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";


    @GetMapping("/check")
    public String checkAI(@RequestParam String textToAI,
                          @RequestParam String wordToCheck) throws Exception {

        String jsonBody = String.format(
                "{\n" +
                        "  \"contents\": [\n" +
                        "    {\n" +
                        "      \"parts\": [\n" +
                        "        {\n" +
                        "          \"text\": \"%s\"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}", textToAI.replace("\"", "\\\""));

        String fullUrl = GEMINI_ENDPOINT_BASE + API_KEY;

        String rawResponse = Request.post(fullUrl)
                .bodyString(jsonBody, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

        if (rawResponse.contains(wordToCheck)) {
            return rawResponse;
        } else {
            return "Word not found in AI response. Raw response: " + rawResponse;
        }
    }
}
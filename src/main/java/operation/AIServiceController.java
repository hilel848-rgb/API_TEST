package operation;

import org.springframework.web.bind.annotation.*;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.client5.http.fluent.Request;

@RestController
@RequestMapping("/api")
public class AIServiceController {

    @GetMapping("/check")
    public String checkAI(@RequestParam String textToAI,
                          @RequestParam String wordToCheck) throws Exception {

        String aiResponse = Request.post("https://api.example.com/ai")
                .bodyString("{\"text\": \"" + textToAI + "\"}", ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

        if(aiResponse.contains(wordToCheck)) {
            return aiResponse;
        } else {
            return "Word not found in AI response.";
        }
    }
}

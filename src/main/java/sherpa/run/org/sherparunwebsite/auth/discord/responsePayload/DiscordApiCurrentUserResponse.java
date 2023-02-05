package sherpa.run.org.sherparunwebsite.auth.discord.responsePayload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscordApiCurrentUserResponse {
    @JsonProperty("id")
    private String id;

    public String getId() {
        return id;
    }
}

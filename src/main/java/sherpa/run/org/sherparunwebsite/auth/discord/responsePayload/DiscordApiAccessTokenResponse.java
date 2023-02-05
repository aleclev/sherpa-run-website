package sherpa.run.org.sherparunwebsite.auth.discord.responsePayload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscordApiAccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}

package sherpa.run.org.sherparunwebsite.auth.discord;

public interface DiscordAuthenticator {

    String getAccessToken(String code);
    String getDiscordId(String accessToken);
}

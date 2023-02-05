package sherpa.run.org.sherparunwebsite.auth.discord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sherpa.run.org.sherparunwebsite.auth.discord.responsePayload.DiscordApiAccessTokenResponse;
import sherpa.run.org.sherparunwebsite.auth.discord.responsePayload.DiscordApiCurrentUserResponse;

@Component
public class DiscordAuthenticatorApi implements DiscordAuthenticator {

    @Value("${discord.auth.tokenAccessURL}")
    private String tokenAuthURL;

    @Value("${discord.api.getCurrentUserURL}")
    private String currentUserURL;

    @Value("${discord.auth.client.id}")
    private String clientId;

    @Value("${discord.auth.client.secret}")
    private String clientSecret;

    @Value("${discord.auth.client.redirectURI}")
    private String redirectURI;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getAccessToken(String code) {

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("redirect_uri", redirectURI);
        requestBody.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);

        return restTemplate
                .exchange(tokenAuthURL, HttpMethod.POST, requestEntity, DiscordApiAccessTokenResponse.class)
                .getBody()
                .getAccessToken();
    }

    @Override
    public String getDiscordId(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", accessToken));

        HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);

        return restTemplate.exchange(currentUserURL, HttpMethod.GET, requestEntity, DiscordApiCurrentUserResponse.class)
                .getBody()
                .getId();
    }
}

package sherpa.run.org.sherparunwebsite.auth.discord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/discord/")
public class DiscordController {

    @Autowired
    private DiscordAuthenticator discordAuthenticator;

    @GetMapping("/login")
    public String login(@RequestParam String code) {
         String accessToken = discordAuthenticator.getAccessToken(code);
         String userId = discordAuthenticator.getDiscordId(accessToken);

         return userId;
    }
}

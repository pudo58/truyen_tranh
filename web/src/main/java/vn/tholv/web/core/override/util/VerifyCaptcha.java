package vn.tholv.web.core.override.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import vn.tholv.web.core.base.dto.CaptchaData;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class VerifyCaptcha {
    @Value("${recaptcha.secret}")
    private String SECRET;

    @Value("${recaptcha.url}")
    private String URL;

    public boolean verify(String response) throws MalformedURLException {
        java.net.URL url = new URL(URL);
        String params = "secret=" + SECRET + "&response=" + response;
        HttpURLConnection con =  null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(HttpMethod.POST.name());
            con.setDoOutput(true);
            con.getOutputStream().write(params.getBytes(StandardCharsets.UTF_8));
            String result = new String(con.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            CaptchaData captchaData = mapper.readValue(result, CaptchaData.class);
            return captchaData.getSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) con.disconnect();
        }
    }

}

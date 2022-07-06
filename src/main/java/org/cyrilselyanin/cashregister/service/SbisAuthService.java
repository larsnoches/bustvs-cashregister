package org.cyrilselyanin.cashregister.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.cyrilselyanin.cashregister.dto.TokenRequestDto;
import org.cyrilselyanin.cashregister.dto.TokenResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Auth service of the Sbis-adapter
 * author Cyril Selyanin
 */
@Service
public class SbisAuthService {
    private final OkHttpClient okHttpClient;
    private final MediaType JSON_MEDIA = MediaType.get("application/json; charset=utf-8");

    public SbisAuthService(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    /**
     * Get token from the Sbis service
     * @param tokenUrl Sbis auth service url string
     * @param requestDto Request dto
     * @return Token response
     * @throws IOException
     */
    public TokenResponseDto getToken(
            String tokenUrl,
            TokenRequestDto requestDto
    ) throws IOException {
        String json = new ObjectMapper().writeValueAsString(requestDto);
        RequestBody requestBody = RequestBody.create(json, JSON_MEDIA);
        Request request = new Request.Builder()
                .url(tokenUrl)
                .post(requestBody)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            String respJson = response.body().string();
            TokenResponseDto tokenResponseDto = new ObjectMapper()
                    .readerFor(TokenResponseDto.class)
                    .readValue(respJson);
            return tokenResponseDto;
        }
    }

}

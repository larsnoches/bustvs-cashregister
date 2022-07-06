package org.cyrilselyanin.cashregister.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.cyrilselyanin.cashregister.dto.RegCashRequestDto;
import org.cyrilselyanin.cashregister.dto.RegCashResponseDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Retail service of the Sbis-adapter
 * author Cyril Selyanin
 */
@Service
public class SbisRetailService {
    private final OkHttpClient okHttpClient;
    private final MediaType JSON_MEDIA = MediaType.get(
            "application/json; charset=utf-8"
    );

    public SbisRetailService(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    /**
     * Reistering cash
     * @param regCashUrl Sbis retail string url
     * @param token Sbis service token
     * @param sid Sbis session id
     * @param regCashRequestDto Request dto
     * @return Response dto
     * @throws NullPointerException
     * @throws IOException
     */
    public RegCashResponseDto regCash(
            String regCashUrl,
            String token,
            String sid,
            RegCashRequestDto regCashRequestDto
    ) throws NullPointerException, IOException {
        String json = new ObjectMapper().writeValueAsString(regCashRequestDto);
        RequestBody requestBody = RequestBody.create(json, JSON_MEDIA);
        Request request = new Request.Builder()
                .url(regCashUrl)
                .post(requestBody)
                .addHeader("X-SBISAccessToken", token)
                .addHeader("X-SBISSessionId", sid)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            String respJson = response.body().string();
            return new ObjectMapper()
                    .readerFor(RegCashResponseDto.class)
                    .readValue(respJson);
        }
    }
}
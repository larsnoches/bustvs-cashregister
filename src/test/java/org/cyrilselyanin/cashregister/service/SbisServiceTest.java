package org.cyrilselyanin.cashregister.service;

import okhttp3.*;
import org.cyrilselyanin.cashregister.dto.TicketDto;
import org.cyrilselyanin.cashregister.dto.TokenRequestDto;
import org.cyrilselyanin.cashregister.exception.RegCashException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

//import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.*;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class SbisServiceTest {
    @Autowired
    SbisServiceImpl sbisService;

    @MockBean
    OkHttpClient okHttpClient;

    String token;
    String sid;

    @Test
    public void getToken_thenCorrect() throws IOException {
        TokenRequestDto requestDto = TokenRequestDto.create(
                "appClientId_123",
                "appSecret_123",
                "secretKey_123"
        );

        final Response response = new Response.Builder()
                .request(new Request.Builder().url("http://url.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200).message("").body(
                        ResponseBody.create(
                            "{" +
                                "\"token\": \"val\"," +
                                "\"sid\": \"val\"" +
                            "}",
                            MediaType.parse("application/json")
                        ))
                .build();
        when(okHttpClient.newCall(any())).thenReturn(mock(Call.class));
        when(okHttpClient.newCall(any()).execute()).thenReturn(response);

        try {
            sbisService.requestToken(requestDto);
            token = sbisService.getToken();
            sid = sbisService.getSid();

            assertNotNull(token);
            assertNotNull(sid);
            assertThat(token, instanceOf(String.class));
            assertThat(sid, instanceOf(String.class));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

//    private static OkHttpClient mockHttpClient(final String serializedBody) throws IOException {
//        final OkHttpClient okHttpClient = mock(OkHttpClient.class);
//
//        final Call remoteCall = mock(Call.class);
//
//        final Response response = new Response.Builder()
//                .request(new Request.Builder().url("http://url.com").build())
//                .protocol(Protocol.HTTP_1_1)
//                .code(200).message("").body(
//                        ResponseBody.create(
//                                serializedBody,
//                                MediaType.parse("application/json")
//                        ))
//                .build();
//
//        when(remoteCall.execute()).thenReturn(response);
//        when(okHttpClient.newCall(any())).thenReturn(remoteCall);
//
//        return okHttpClient;
//    }

    // @Test
    // public void getToken_thenCorrect() {
    //     assertNotNull(token);
    //     assertNotNull(sid);
    //     assertThat(token, instanceOf(String.class));
    //     assertThat(sid, instanceOf(String.class));
    // }

    @Test
    public void regCash_thenCorrect() {
        // stab
        TicketDto ticketDto = new TicketDto(
                "Petrov",
                "Ivan",
                "Ivanovich",
                "105",
                "Center",
                null,
                BigDecimal.valueOf(500)
        );

        final Response response = new Response.Builder()
                .request(new Request.Builder().url("http://url.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200).message("").body(
                        ResponseBody.create(
                            "{" +
                                "\"Result\":" +
                                    "{" +
                                        "\"payId\": \"val\"" +
                                    "}" +
                            "}",
                            MediaType.parse("application/json")
                        ))
                .build();

        // okHttpClient resets after first test ran
        when(okHttpClient.newCall(any())).thenReturn(mock(Call.class));
        when(okHttpClient.newCall(any()).execute()).thenReturn(response);

        sbisService.regCash(ticketDto);
    }

//    @Test
//    public void regCash_theCorrectException() {
//        TicketDto ticketDto = new TicketDto(
//                "Petrov",
//                "Ivan",
//                "Ivanovich",
//                "105",
//                "Center",
//                null,
//                BigDecimal.valueOf(500)
//        );
//        assertThrows(RegCashException.class, () -> sbisService.regCash(ticketDto));
//    }
}
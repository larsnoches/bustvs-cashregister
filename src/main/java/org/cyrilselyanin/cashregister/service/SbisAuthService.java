package org.cyrilselyanin.cashregister.service;

import org.cyrilselyanin.cashregister.dto.SbisTokenRequestDto;
import org.cyrilselyanin.cashregister.dto.SbisTokenResponseDto;

import java.io.IOException;

/**
 * Auth service of the Sbis-adapter
 * author Cyril Selyanin
 */
public interface SbisAuthService {
    /**
     * Get token from the Sbis service
     * @param tokenUrl Sbis auth service url string
     * @param requestDto Request dto
     * @return Token response
     * @throws IOException
     */
    SbisTokenResponseDto getToken(
        String tokenUrl,
        SbisTokenRequestDto requestDto
    ) throws IOException;
}

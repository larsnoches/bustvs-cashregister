package org.cyrilselyanin.cashregister.service;

import org.cyrilselyanin.cashregister.dto.TicketDto;
import org.cyrilselyanin.cashregister.dto.TokenRequestDto;
import java.io.IOException;

/**
 * Sbis-adapter service
 * author Cyril Selyanin
 */
public interface SbisService {
    /**
     * Requesting token from Sbis service
     * @param requestDto Dto with clientId, secret and key
     * @throws IOException Checked IoException throws
     */
    void requestToken(TokenRequestDto requestDto) throws IOException;

    /**
     * Ticked dto sending to Sbis service
     * @param ticketDto
     */
    void regCash(TicketDto ticketDto);
}

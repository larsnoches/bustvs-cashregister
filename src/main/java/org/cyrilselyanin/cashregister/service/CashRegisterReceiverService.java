package org.cyrilselyanin.cashregister.service;

import org.cyrilselyanin.cashregister.dto.TicketDto;
import org.cyrilselyanin.cashregister.dto.TokenRequestDto;
import org.cyrilselyanin.cashregister.exception.RegCashException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Cash register receiver, annotated as rabbit listener.
 */
@Service
@RabbitListener(queues = "#{autoDeletingQueue.name}")
public class CashRegisterReceiverService {
    private final SbisServiceImpl sbisService;
    private final Logger logger = LoggerFactory.getLogger(CashRegisterReceiverService.class);

    /**
    * Properties for token requesting
    */
    @Value("#{cashregister.sbis-auth.appClientId}")
    private String appClientId;
    @Value("#{cashregister.sbis-auth.appSecret}")
    private String appSecret;
    @Value("#{cashregister.sbis-auth.secretKey}")
    private String secretKey;

    public CashRegisterReceiverService(SbisServiceImpl sbisService) {
        this.sbisService = sbisService;
    }

    /**
     * Receive method
     * @param in Some ticketDto object
     */
    @RabbitHandler
    public void receive(TicketDto in) {
        logger.debug("Incoming ticket dto");
        logger.debug("{}", in);
        logger.debug("...printed ticket dto");

        // reg cash with old token props
        Optional<String> token = Optional.of(sbisService.getToken());
        Optional<String> sid = Optional.of(sbisService.getSid());
        if (token.isPresent() && sid.isPresent()) {
            try {
                sbisService.regCash(in);
                logger.debug("token and sid are present, regcash is called");
            } catch (RegCashException ex) {
                logger.error("With already set auth props regCash throws exception. {}", ex);
            }
            return;
        }

        // new dto for token request
        TokenRequestDto requestDto = TokenRequestDto.create(
                appClientId,
                appSecret,
                secretKey
        );

        try {
            sbisService.requestToken(requestDto);
            token = Optional.of(sbisService.getToken());
            sid = Optional.of(sbisService.getSid());
            if (token.isPresent() && sid.isPresent()) {
                sbisService.regCash(in);
                logger.debug("token and sid are present, regcash is called");
            }
        } catch (RegCashException ex) {
            logger.error("With new auth props regCash throws exception. {}", ex);
        } catch (IOException ex) {
            logger.error("Trying to auth and got exception. {}", ex);
        }
    }
}

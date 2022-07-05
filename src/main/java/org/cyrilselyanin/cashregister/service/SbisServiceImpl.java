package org.cyrilselyanin.cashregister.service;

import lombok.Getter;
import org.cyrilselyanin.cashregister.dto.TicketDto;
import org.cyrilselyanin.cashregister.dto.RegCashRequestDto;
import org.cyrilselyanin.cashregister.dto.RegCashResponseDto;
import org.cyrilselyanin.cashregister.dto.TokenRequestDto;
import org.cyrilselyanin.cashregister.dto.TokenResponseDto;
import org.cyrilselyanin.cashregister.exception.RegCashException;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Sbis service implementation
 */
public class SbisServiceImpl implements SbisService {
    private final String authUrl = "https://online.sbis.ru/oauth/service";
    private final String regCashUrl = "https://api.sbis.ru/retail/sale/create";
    private final SbisAuthService sbisAuthService = new SbisAuthService();
    private final SbisRetailService sbisRetailService = new SbisRetailService();

    @Getter
    private String token;

    @Getter
    private String sid;

    @Override
    public void requestToken(TokenRequestDto requestDto) throws IOException {
        TokenResponseDto responseDto = sbisAuthService.getToken(authUrl, requestDto);
        TokenResponseDtoAdapter tokenResponseDtoAdapter = new TokenResponseDtoAdapter();
        tokenResponseDtoAdapter.adapt(responseDto);
    }

    @Override
    public void regCash(TicketDto ticketDto) {
        RegCashRequestDtoAdapter regCashRequestDtoAdapter = new RegCashRequestDtoAdapter();
        RegCashRequestDto regCashRequestDto = regCashRequestDtoAdapter.adapt(ticketDto);
        try {
            RegCashResponseDto regCashResponseDto = sbisRetailService.regCash(
                    regCashUrl, token, sid, regCashRequestDto
            );
        } catch (IOException | NullPointerException ex) {
            throw new RegCashException(ex.getMessage());
        }

    }

    public class TokenResponseDtoAdapter {
        public void adapt(TokenResponseDto dto) {
            token = dto.getToken();
            sid = dto.getSid();
        }
    }

    public class RegCashRequestDtoAdapter {
        public RegCashRequestDto adapt(TicketDto ticketDto) {
            RegCashRequestDto dto = new RegCashRequestDto();
            dto.setCompanyID(1L);
            dto.setCashierFIO("Wow wow wow");
            dto.setOperationType(1);
            dto.setCashSum(null);
            dto.setBankSum(ticketDto.getPrice());
            dto.setInternetSum(ticketDto.getPrice());
            dto.setAccountSum(ticketDto.getPrice());
            dto.setPostpaySum(null);
            dto.setPrepaySum(ticketDto.getPrice());

            var vat20 = ticketDto.getPrice().divide(BigDecimal.valueOf(100));
            dto.setVatNone(ticketDto.getPrice().subtract(vat20));
            dto.setVatSum0(null);
            dto.setVatSum10(null);
            dto.setVatSum20(vat20);

            dto.setNameNomenclature(String.format(
                    "Маршрут %s, %s - %s, %s",
                    ticketDto.getBusRouteNumber(),
                    ticketDto.getDepartureBuspointName(),
                    ticketDto.getDepartureBuspointName(),
                    ticketDto.getDepartureDateTime().toString()
            ));

            //

            dto.setBarcodeNomenclature(BigDecimal.valueOf(12345));
            dto.setPriceNomenclature(ticketDto.getPrice());
            dto.setQuantityNomenclature(1);
            dto.setMeasureNomenclature("шт");
            dto.setKindNomenclature("у");
            dto.setTotalPriceNomenclature(ticketDto.getPrice());
            dto.setTaxRateNomenclature(vat20);
            dto.setTotalVat(vat20);
            dto.setCustomerFIO(
                    String.format(
                            "%s %s %s",
                            ticketDto.getPassengerLastname(),
                            ticketDto.getPassengerFirstname(),
                            ticketDto.getPassengerMiddlename()
                    )
            );
            dto.setCustomerEmail(null);
            dto.setCustomerPhone(null);
            dto.setCustomerExtId(null);
            dto.setTaxSystem(4);
            dto.setSendEmail(null);
            dto.setSendPhone(null);
            dto.setPropName(null);
            dto.setPropVa(null);
            dto.setComment("");
            dto.setPayMethod(4);
            dto.setExternalId("123-123-123");

            return dto;

        }
    }
}
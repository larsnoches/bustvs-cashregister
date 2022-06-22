package org.cyrilselyanin.cashregister.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenResponseDto {
    private String token;
    private String sid;
}

package org.cyrilselyanin.cashregister.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Dto for response in communication between services.
 * It will say if operation was done or not.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AfterRegCashDto {
    private Status status;
    private String statusText;

    public AfterRegCashDto(Status status) {
        this.status = status;
        this.statusText = "";
    }

    public enum Status {
        DONE("done"),
        ERROR("error");

        private String label;

        Status(String label) {
            this.label = label;
        }

        @JsonValue
        public String getLabel() {
            return label;
        }
    }
}

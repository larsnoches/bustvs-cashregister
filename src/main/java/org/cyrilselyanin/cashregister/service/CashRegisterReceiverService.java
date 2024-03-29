package org.cyrilselyanin.cashregister.service;

import org.cyrilselyanin.cashregister.dto.TicketDto;

/**
 * Cash register receiver
 */
public interface CashRegisterReceiverService {
    /**
     * Receive method
     * @param in Some ticketDto object
     */
    void receive(TicketDto in);
}

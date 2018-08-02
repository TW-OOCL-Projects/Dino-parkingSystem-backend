package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.entities.Receipt;
import org.springframework.stereotype.Component;

import static com.oocl.dino_parking_system.constants.Constants.STATUS_INUSE;

@Component
public class ReceiptService {
    public Receipt generateReceipt() {
        return new Receipt();
    }
}

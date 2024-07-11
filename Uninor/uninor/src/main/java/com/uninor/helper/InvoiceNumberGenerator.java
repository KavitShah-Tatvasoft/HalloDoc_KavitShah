package com.uninor.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class InvoiceNumberGenerator {

    private static InvoiceNumberGenerator instance;
    private AtomicInteger counter;
    private static final String PREFIX = "IN";
    private char prefixSuffix = 'A';



    private InvoiceNumberGenerator() {
        this.counter = new AtomicInteger(0);
    }

    public static synchronized InvoiceNumberGenerator getInstance() {
        if (instance == null) {
            instance = new InvoiceNumberGenerator();
        }
        return instance;
    }

    public synchronized String generateInvoiceNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String datePart = dateFormat.format(new Date());
        int sequence = counter.incrementAndGet();

        if (sequence > 9999) {
            sequence = 1;
            counter.set(1);
            if (prefixSuffix < 'Z') {
                prefixSuffix++;
            } else {
                prefixSuffix = 'A';
            }
        }

        return String.format("%s%s%s%04d", PREFIX, prefixSuffix, datePart, sequence);
    }

}

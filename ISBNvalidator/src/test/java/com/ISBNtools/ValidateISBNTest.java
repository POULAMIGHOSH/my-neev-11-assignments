package com.ISBNtools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateISBNTest {
    @Test
    void check10digitvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0984782869");
        assertTrue(result,"Fist result");
        result = validator.checkISBN("8131722422");
        assertTrue(result,"Second result");
    }

    @Test
    void check13digitvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result=validator.checkISBN("9788193245279");
        assertTrue(result);
    }

    @Test
    void TendigitISBNendingwithXisValid() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("819324527X");
        assertTrue(result);


    }

    @Test
    void check10digitInvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0201633611");
        assertFalse(result);
    }
    @Test
    void check13digitInvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9788193245278");
        assertFalse(result);
    }

    @Test
    void ninDigitInvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class,()->{validator.checkISBN("813172242");});
    }

    @Test
    void nonNumericISBNnotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class,()->{validator.checkISBN("helloworld");});

    }
}
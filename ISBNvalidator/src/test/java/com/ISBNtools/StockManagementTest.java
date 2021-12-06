package com.ISBNtools;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockManagementTest {
    ExternalISBNDataService testwebservice;
    ExternalISBNDataService testdatabaseservice;
    StockManager stockManager;
    @BeforeEach
    void setUp() {
        System.out.println("setup running");
        testwebservice = mock(ExternalISBNDataService.class);
        testdatabaseservice = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testwebservice);
        stockManager.setDatabaseservice(testdatabaseservice);
    }

    @Test
    void getACorrectLocatorCode() {
        when(testwebservice.lookup(anyString())).thenReturn(new Book("0984782869", "Cracking the Coding Interview ", "Gayle Laakmann McDowell"));

        when(testdatabaseservice.lookup(anyString())).thenReturn(null);
        String isbn = "0984782869";
        String locatercode = stockManager.getLocatorCode(isbn);
        assertEquals("2869G4", locatercode);

    }

    @Test
    void databaseisusedifdataispresent() {
        String isbn = "0984782869";
        when(testdatabaseservice.lookup(isbn)).thenReturn(new Book(isbn,"abc","abc"));
        String locatercode = stockManager.getLocatorCode(isbn);

        verify(testdatabaseservice,times(1)).lookup("0984782869");
        verify(testwebservice,times(0)).lookup(anyString());
    }

    @Test
    void webserviceisusedifdataisnotpresentindatabase() {
        String isbn = "0984782869";
        when(testdatabaseservice.lookup(isbn)).thenReturn(null);
        when(testwebservice.lookup(isbn)).thenReturn(new Book(isbn,"abc","abc"));
        String locatercode = stockManager.getLocatorCode(isbn);

        verify(testdatabaseservice,times(1)).lookup(anyString());
        verify(testwebservice,times(1)).lookup("0984782869");
    }
}

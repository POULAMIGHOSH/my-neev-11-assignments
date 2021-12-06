package com.ISBNtools;

public class StockManager {
    private ExternalISBNDataService Webservice;
    private ExternalISBNDataService databaseservice;



    public void setWebService(ExternalISBNDataService service) {
        this.Webservice = service;
    }
    public void  setDatabaseservice(ExternalISBNDataService service){
        this.databaseservice=service;
    }


    public String getLocatorCode(String isbn) {
        Book book = databaseservice.lookup(isbn);
        if (book==null) book=Webservice.lookup(isbn);
        StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length() - 4));
        locator.append(book.getAuthor().substring(0, 1));
        locator.append(book.getTitle().split(" ").length);
        return locator.toString();

    }
}

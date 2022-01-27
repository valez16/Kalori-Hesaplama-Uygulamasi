

package com.kalori.hesapla.sqlite;


public class DatabaseException extends Exception {
    public DatabaseException(String s) {
        super(s);
    }

    public DatabaseException(Exception e) {
        super(e);
    }
}

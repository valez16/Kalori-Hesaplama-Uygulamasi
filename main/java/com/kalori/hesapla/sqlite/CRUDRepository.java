package com.kalori.hesapla.sqlite;


import java.util.List;


public interface CRUDRepository<K, T> {


    K create(T element) throws DatabaseException;


    T read(K key) throws DatabaseException;


    List<T> readAll() throws DatabaseException;


    boolean update(T element) throws DatabaseException;


    boolean delete(T element) throws DatabaseException;
}

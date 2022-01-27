

package com.kalori.hesapla.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public abstract class Table<T extends Identifiable<Long>> implements CRUDRepository<Long, T> {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private final SQLiteOpenHelper dbh;

    private final String name;
    private final List<Column> columns;


    public Table(SQLiteOpenHelper dbh, String name) {
        this.name = name;
        this.dbh = dbh;
        columns = new LinkedList<>();
        columns.add(new Column("_id", Column.Type.INTEGER).primaryKey().autoincrement());
    }


    public SQLiteOpenHelper getDatabaseHandler() {
        return dbh;
    }


    public String getName() {
        return name;
    }

    public void addColumn(Column column) {
        columns.add(column);
    }


    private String getCreateTableStatement() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("CREATE TABLE %s (", name));

        boolean first = false;
        for(Column column : columns) {
            if(!first)
                first = true;
            else
                sb.append(", ");
            sb.append(column.toString());
        }
        sb.append(");");
        return sb.toString();
    }


    private String getDropTableStatement() {
        return String.format("DROP TABLE IF EXISTS %s;", name);
    }


    public String[] getSelectAll() {
        String[] selection = new String[columns.size()];
        for(int i = 0; i < selection.length; i++)
            selection[i] = columns.get(i).getName();
        return selection;
    }


    public boolean hasInitialData() {
        return false;
    }


    public void initialize(SQLiteDatabase database) {
    }


    protected abstract ContentValues toContentValues(T element) throws DatabaseException;


    protected abstract T fromCursor(Cursor cursor) throws DatabaseException;



    public void createTable(SQLiteDatabase database) {
        database.execSQL(getCreateTableStatement());
        if(hasInitialData())
            initialize(database);
    }


    public void dropTable(SQLiteDatabase database) {
        database.execSQL(getDropTableStatement());
    }


    @Override
    public Long create(T element) throws DatabaseException  {

        SQLiteDatabase database = dbh.getWritableDatabase();

        // ID'yi veritabanına işleme
        long insertId = -1;

        // veritabanına giriş
        try {
            ContentValues values = toContentValues(element);
            insertId = database.insertOrThrow(name, null, values);
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
        finally {
            database.close();
        }

        element.setId(insertId);

        return insertId;
    }

    @Override
    public T read(Long id) throws DatabaseException {

        SQLiteDatabase database = dbh.getReadableDatabase();

        String[] projection = getSelectAll();
        Cursor cursor = database.query(name, projection, "_id =?", new String[] { String.valueOf(id) }, null, null, null, null);

        // sonuç kontrolü
        if(cursor == null)
            throw new DatabaseException("Operation read(" + id + "): no element with that id");


        cursor.moveToFirst();
        if(!cursor.isLast())
            throw new DatabaseException("Operation read(" + id + "): more than one row matches. Aborting.");

        T element = fromCursor(cursor);
        element.setId(id);
        return element;
    }

    @Override
    public List<T> readAll() throws DatabaseException {
        SQLiteDatabase database = dbh.getReadableDatabase();

        List<T> elements = new ArrayList<>();

        String[] selection = getSelectAll();
        Cursor cursor = database.query(name, selection, null, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                T element = fromCursor(cursor);
                element.setId(cursor.getLong(0));
                elements.add(element);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return elements;
    }

    @Override
    public boolean update(T element) throws DatabaseException {

        SQLiteDatabase database = dbh.getWritableDatabase();

        ContentValues values = toContentValues(element);
        String idStr = String.valueOf(element.getId());
        int rows = database.update(name, values, "_id = ?", new String[]{idStr});
        database.close();

        return rows == 1;
    }

    @Override
    public boolean delete(T element) throws DatabaseException {
        SQLiteDatabase database = dbh.getWritableDatabase();
        String idStr = String.valueOf(element.getId());
        int rows = database.delete(name, "_id = ?", new String[]{idStr});
        database.close();
        if(rows > 1)
            throw new DatabaseException("1'den fazla satır silindi bir hata ile karşılaşılabilir.");
        return rows == 1;
    }

}

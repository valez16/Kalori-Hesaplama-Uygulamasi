
package com.kalori.hesapla.sqlite;


public class Column {

    public enum Type { TEXT, REAL, INTEGER, BLOB }

    private String name;
    private Type type;
    private boolean notNull;
    private boolean unique;
    private boolean primaryKey;
    private boolean autoincrement;


    public Column(String name, Type type) {
        this.name = name;
        this.type = type;


        notNull = false;
        unique = false;
        primaryKey = false;
        autoincrement = false;
    }



    public String getName() {
        return name;
    }

    public Column setName(String name) {
        this.name = name;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Column setType(Type type) {
        this.type = type;
        return this;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public Column notNull() {
        this.notNull = true;
        return this;
    }

    public boolean isUnique() {
        return unique;
    }

    public Column unique() {
        this.unique = true;
        return this;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public Column primaryKey() {
        this.primaryKey = true;
        return this;
    }

    public boolean isAutoincrement() {
        return autoincrement;
    }

    public Column autoincrement() {
        this.autoincrement = true;
        return this;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(type);
        if(primaryKey)
            sb.append(" PRIMARY KEY");
        if(notNull)
            sb.append(" NOT NULL");
        if(unique)
            sb.append(" UNIQUE");
        if(autoincrement)
            sb.append(" AUTOINCREMENT");
        return sb.toString();
    }
}
package com.kalori.hesapla.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.kalori.hesapla.sqlite.Identifiable;

public class food_Item implements Identifiable<Long>, Parcelable {
    private long id;
    private String UUID;
    private String name;
    private Category category;
    private int serving_Size;
    private int calories;

    public long getMacro_Id() {
        return macro_Id;
    }

    public void setMacro_Id(long macro_Id) {
        this.macro_Id = macro_Id;
    }

    private long macro_Id;

    //Yapı
    public food_Item(){
        this.UUID= java.util.UUID.randomUUID().toString();
    }

    public food_Item(String name,Category category, int serving_Size, int calories, long macro_Id) {
        this.UUID= java.util.UUID.randomUUID().toString();
        this.name = name;
        this.category=category;
        this.serving_Size = serving_Size;
        this.calories = calories;
        this.macro_Id = macro_Id;
    }


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }

    public void setId(int id) {this.id = id; }

    public String getUUID() {return UUID; }

    public void setUUID(String UUID) {this.UUID = UUID; }


    public String getName() {return name;}

    public void setName(String name) { this.name = name; }

    public Category getCategory() { return category;  }

    public void setCategory(Category category) {this.category = category;}

    public int getServing_Size() {return serving_Size;}

    public void setServing_Size(int serving_Size) {this.serving_Size = serving_Size;}

    public int getCalories() {return calories;}

    public void setCalories(int calories) {this.calories = calories;}


    protected food_Item(Parcel in) {
        id = in.readLong();
        UUID = in.readString();
        name = in.readString();
        category = Category.values()[in.readInt()];
        serving_Size = in.readInt();
        calories = in.readInt();
        macro_Id = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(UUID);
        dest.writeString(name);
        dest.writeInt(category.ordinal());
        dest.writeInt(serving_Size);
        dest.writeInt(calories);
        dest.writeLong(macro_Id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<food_Item> CREATOR = new Parcelable.Creator<food_Item>() {
        @Override
        public food_Item createFromParcel(Parcel in) {
            return new food_Item(in);
        }

        @Override
        public food_Item[] newArray(int size) {
            return new food_Item[size];
        }
    };
}
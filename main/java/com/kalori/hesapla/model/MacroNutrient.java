package com.kalori.hesapla.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.kalori.hesapla.sqlite.Identifiable;

public class MacroNutrient implements Parcelable, Identifiable<Long> {

    private long id;
    private double protein;
    private double fiber;
    private double sugar;
    private double unsaturatedFat;
    private double saturatedFat;
    private double trans_fat;
    private double cholesterol;
    private double sodium;

    public MacroNutrient() {

    }

    public MacroNutrient(double protein, double fiber, double sugar, double unsaturatedFat, double saturatedFat, double trans_fat, double cholesterol, double sodium) {
        this.protein = protein;
        this.fiber = fiber;
        this.sugar = sugar;
        this.unsaturatedFat = unsaturatedFat;
        this.saturatedFat = saturatedFat;
        this.trans_fat = trans_fat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getUnsaturatedFat() {
        return unsaturatedFat;
    }

    public void setUnsaturatedFat(double unsaturatedFat) {
        this.unsaturatedFat = unsaturatedFat;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public double getTrans_fat() {
        return trans_fat;
    }

    public void setTrans_fat(double trans_fat) {
        this.trans_fat = trans_fat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }





    protected MacroNutrient(Parcel in) {
        id = in.readLong();
        protein = in.readDouble();
        fiber = in.readDouble();
        sugar = in.readDouble();
        unsaturatedFat = in.readDouble();
        saturatedFat = in.readDouble();
        trans_fat = in.readDouble();
        cholesterol = in.readDouble();
        sodium = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeDouble(protein);
        dest.writeDouble(fiber);
        dest.writeDouble(sugar);
        dest.writeDouble(unsaturatedFat);
        dest.writeDouble(saturatedFat);
        dest.writeDouble(trans_fat);
        dest.writeDouble(cholesterol);
        dest.writeDouble(sodium);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MacroNutrient> CREATOR = new Parcelable.Creator<MacroNutrient>() {
        @Override
        public MacroNutrient createFromParcel(Parcel in) {
            return new MacroNutrient(in);
        }

        @Override
        public MacroNutrient[] newArray(int size) {
            return new MacroNutrient[size];
        }
    };

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }

    public boolean sufficientProtein(double protein){
        if(protein >= 40 && protein <= 60)
            return true;
        else
            return false;
    }
    public boolean sufficientFiber(double fiber){
        if(fiber >= 25 && fiber <= 30)
            return true;
        else
            return false;
    }
    public boolean sufficientSugar(double sugar){
        if(sugar >= 25 && sugar <= 36)
            return true;
        else
            return false;
    }
    public boolean sufficientUnsaturatedFat(double unsaturatedFat){
        if(unsaturatedFat >= 16 && unsaturatedFat <= 22)
            return true;
        else
            return false;
    }
    public boolean sufficientSaturatedFat(double saturatedFat){
        if(saturatedFat >= 16 && saturatedFat <= 22)
            return true;
        else
            return false;
    }
    public boolean sufficientTrans_fat(double trans_fat){
        if(trans_fat >= 2 && trans_fat <= 4)
            return true;
        else
            return false;
    }
    public boolean sufficientCholesterol(double cholesterol){
        if(cholesterol >= 0.3 && cholesterol <= 0.5)
            return true;
        else
            return false;
    }
    public boolean sufficientSodium(double sodium){
        if(sodium >= 2 && sodium <= 2.3)
            return true;
        else
            return false;
    }
}

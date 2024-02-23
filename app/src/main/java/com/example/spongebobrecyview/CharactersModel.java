package com.example.spongebobrecyview;

import androidx.annotation.NonNull;

public class CharactersModel {

    String cName;
    String cShortTxt;
    String cLongTxt;
    int image;

    public CharactersModel(String cName, String cShortTxt, String cLongTxt, int image) {
        this.cName = cName;
        this.cShortTxt = cShortTxt;
        this.cLongTxt = cLongTxt;
        this.image = image;
    }

    public String getcName() {
        return cName;
    }

    public String getcShortTxt() {
        return cShortTxt;
    }

    public String getcLongTxt() {
        return cLongTxt;
    }

    public int getImage() {
        return image;
    }
}

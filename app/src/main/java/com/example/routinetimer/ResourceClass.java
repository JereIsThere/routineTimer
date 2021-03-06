package com.example.routinetimer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;

import com.maltaisn.icondialog.pack.IconPack;
import com.maltaisn.icondialog.pack.IconPackLoader;
import com.maltaisn.iconpack.defaultpack.IconPackDefault;

class ResourceClass {

    //----------------------------------------random---------------------------------------------//
    public static int convertColorDayNight(boolean isNightMode, int oldColor) {
        float[] hsvValues = new float[3];
        int red = Color.red(oldColor);
        int green = Color.green(oldColor);
        int blue = Color.blue(oldColor);
        Color.RGBToHSV(red, green, blue, hsvValues);

        if (isNightMode) {
            hsvValues[1] = 0.5F;
        } else {
            hsvValues[1] = 1F;
        }

        return Color.HSVToColor(hsvValues);
    }

    public static int calculateContrast(int bgColor){
        Color bgColor_cl = Color.valueOf(bgColor);
        double average = (bgColor_cl.red() + bgColor_cl.blue() + bgColor_cl.green()) / 3;

        int contrastColor;

        if (average > 0.5) {
            contrastColor = Color.BLACK;
        } else {
            contrastColor = Color.WHITE;
        }

        return contrastColor;
    }

    //--------------------------------------icon handling-----------------------------------------//
    @Nullable
    private static IconPack iconPack;
    private static Context context;
    private static Tile tmpTile = new Tile();

    public static void init(Context c) {
        context = c;

        // Load the icon pack on application start.
        loadIconPack();
    }

    @Nullable
    public static IconPack getIconPack() {
        return iconPack != null ? iconPack : loadIconPack();
    }

    private static IconPack loadIconPack() {
        // Create an icon pack loader with application context.
        IconPackLoader loader = new IconPackLoader(context);

        // Create an icon pack and load all drawables.
        iconPack = IconPackDefault.createDefaultIconPack(loader);
        iconPack.loadDrawables(loader.getDrawableLoader());

        return iconPack;
    }

    //------------------------------temp tile handling--------------------------------------------//

    public static void resetTmpTile() {
        tmpTile = new Tile();
    }

    public static Tile getTmpTile() {
        return tmpTile;
    }
}
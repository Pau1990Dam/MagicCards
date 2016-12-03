package com.pau.a14270729b.magiccards.provider.cards;

// @formatter:off
import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pau.a14270729b.magiccards.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code cards} table.
 */
@SuppressWarnings({"ConstantConditions", "unused"})
public class CardsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CardsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable CardsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The context to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable CardsSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CardsContentValues putCardId(@Nullable String value) {
        mContentValues.put(CardsColumns.CARD_ID, value);
        return this;
    }

    public CardsContentValues putCardIdNull() {
        mContentValues.putNull(CardsColumns.CARD_ID);
        return this;
    }

    public CardsContentValues putColors(@Nullable String value) {
        mContentValues.put(CardsColumns.COLORS, value);
        return this;
    }

    public CardsContentValues putColorsNull() {
        mContentValues.putNull(CardsColumns.COLORS);
        return this;
    }

    public CardsContentValues putRarity(@Nullable String value) {
        mContentValues.put(CardsColumns.RARITY, value);
        return this;
    }

    public CardsContentValues putRarityNull() {
        mContentValues.putNull(CardsColumns.RARITY);
        return this;
    }

    public CardsContentValues putName(@Nullable String value) {
        mContentValues.put(CardsColumns.NAME, value);
        return this;
    }

    public CardsContentValues putNameNull() {
        mContentValues.putNull(CardsColumns.NAME);
        return this;
    }

    public CardsContentValues putText(@Nullable String value) {
        mContentValues.put(CardsColumns.TEXT, value);
        return this;
    }

    public CardsContentValues putTextNull() {
        mContentValues.putNull(CardsColumns.TEXT);
        return this;
    }

    public CardsContentValues putFlavor(@Nullable String value) {
        mContentValues.put(CardsColumns.FLAVOR, value);
        return this;
    }

    public CardsContentValues putFlavorNull() {
        mContentValues.putNull(CardsColumns.FLAVOR);
        return this;
    }

    public CardsContentValues putToughness(@Nullable String value) {
        mContentValues.put(CardsColumns.TOUGHNESS, value);
        return this;
    }

    public CardsContentValues putToughnessNull() {
        mContentValues.putNull(CardsColumns.TOUGHNESS);
        return this;
    }

    public CardsContentValues putType(@Nullable String value) {
        mContentValues.put(CardsColumns.TYPE, value);
        return this;
    }

    public CardsContentValues putTypeNull() {
        mContentValues.putNull(CardsColumns.TYPE);
        return this;
    }

    public CardsContentValues putPower(@Nullable String value) {
        mContentValues.put(CardsColumns.POWER, value);
        return this;
    }

    public CardsContentValues putPowerNull() {
        mContentValues.putNull(CardsColumns.POWER);
        return this;
    }

    public CardsContentValues putImageurl(@Nullable String value) {
        mContentValues.put(CardsColumns.IMAGEURL, value);
        return this;
    }

    public CardsContentValues putImageurlNull() {
        mContentValues.putNull(CardsColumns.IMAGEURL);
        return this;
    }

    public CardsContentValues putCmc(@Nullable Integer value) {
        mContentValues.put(CardsColumns.CMC, value);
        return this;
    }

    public CardsContentValues putCmcNull() {
        mContentValues.putNull(CardsColumns.CMC);
        return this;
    }
}

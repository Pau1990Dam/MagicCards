package com.pau.a14270729b.magiccards.provider.cards;

// @formatter:off
import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pau.a14270729b.magiccards.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code cards} table.
 */
@SuppressWarnings({"WeakerAccess", "unused", "UnnecessaryLocalVariable"})
public class CardsCursor extends AbstractCursor implements CardsModel {
    public CardsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    @Override
    public long getId() {
        Long res = getLongOrNull(CardsColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code card_id} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getCardId() {
        String res = getStringOrNull(CardsColumns.CARD_ID);
        return res;
    }

    /**
     * Get the {@code colors} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getColors() {
        String res = getStringOrNull(CardsColumns.COLORS);
        return res;
    }

    /**
     * Get the {@code rarity} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getRarity() {
        String res = getStringOrNull(CardsColumns.RARITY);
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getName() {
        String res = getStringOrNull(CardsColumns.NAME);
        return res;
    }

    /**
     * Get the {@code text} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getText() {
        String res = getStringOrNull(CardsColumns.TEXT);
        return res;
    }

    /**
     * Get the {@code flavor} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getFlavor() {
        String res = getStringOrNull(CardsColumns.FLAVOR);
        return res;
    }

    /**
     * Get the {@code toughness} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getToughness() {
        String res = getStringOrNull(CardsColumns.TOUGHNESS);
        return res;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getType() {
        String res = getStringOrNull(CardsColumns.TYPE);
        return res;
    }

    /**
     * Get the {@code power} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getPower() {
        String res = getStringOrNull(CardsColumns.POWER);
        return res;
    }

    /**
     * Get the {@code imageurl} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getImageurl() {
        String res = getStringOrNull(CardsColumns.IMAGEURL);
        return res;
    }

    /**
     * Get the {@code cmc} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public Integer getCmc() {
        Integer res = getIntegerOrNull(CardsColumns.CMC);
        return res;
    }
}

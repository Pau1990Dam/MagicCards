package com.pau.a14270729b.magiccards.provider.cards;

// @formatter:off
import com.pau.a14270729b.magiccards.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code cards} table.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface CardsModel extends BaseModel {

    /**
     * Primary key.
     */
    long getId();

    /**
     * Get the {@code card_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getCardId();

    /**
     * Get the {@code colors} value.
     * Can be {@code null}.
     */
    @Nullable
    String getColors();

    /**
     * Get the {@code rarity} value.
     * Can be {@code null}.
     */
    @Nullable
    String getRarity();

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getName();

    /**
     * Get the {@code text} value.
     * Can be {@code null}.
     */
    @Nullable
    String getText();

    /**
     * Get the {@code flavor} value.
     * Can be {@code null}.
     */
    @Nullable
    String getFlavor();

    /**
     * Get the {@code toughness} value.
     * Can be {@code null}.
     */
    @Nullable
    String getToughness();

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    String getType();

    /**
     * Get the {@code power} value.
     * Can be {@code null}.
     */
    @Nullable
    String getPower();

    /**
     * Get the {@code imageurl} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImageurl();

    /**
     * Get the {@code cmc} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getCmc();
}

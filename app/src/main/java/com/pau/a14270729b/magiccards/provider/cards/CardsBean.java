package com.pau.a14270729b.magiccards.provider.cards;

// @formatter:off
import com.pau.a14270729b.magiccards.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Bean for the {@code cards} table.
 */
@SuppressWarnings({"WeakerAccess", "unused", "ConstantConditions"})
public class CardsBean implements CardsModel {
    private long mId;
    private String mCardId;
    private String mColors;
    private String mRarity;
    private String mName;
    private String mText;
    private String mFlavor;
    private String mToughness;
    private String mType;
    private String mPower;
    private String mImageurl;
    private Integer mCmc;

    /**
     * Primary key.
     */
    @Override
    public long getId() {
        return mId;
    }

    /**
     * Primary key.
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * Get the {@code card_id} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getCardId() {
        return mCardId;
    }

    /**
     * Set the {@code card_id} value.
     * Can be {@code null}.
     */
    public void setCardId(@Nullable String cardId) {
        mCardId = cardId;
    }

    /**
     * Get the {@code colors} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getColors() {
        return mColors;
    }

    /**
     * Set the {@code colors} value.
     * Can be {@code null}.
     */
    public void setColors(@Nullable String colors) {
        mColors = colors;
    }

    /**
     * Get the {@code rarity} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getRarity() {
        return mRarity;
    }

    /**
     * Set the {@code rarity} value.
     * Can be {@code null}.
     */
    public void setRarity(@Nullable String rarity) {
        mRarity = rarity;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getName() {
        return mName;
    }

    /**
     * Set the {@code name} value.
     * Can be {@code null}.
     */
    public void setName(@Nullable String name) {
        mName = name;
    }

    /**
     * Get the {@code text} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getText() {
        return mText;
    }

    /**
     * Set the {@code text} value.
     * Can be {@code null}.
     */
    public void setText(@Nullable String text) {
        mText = text;
    }

    /**
     * Get the {@code flavor} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getFlavor() {
        return mFlavor;
    }

    /**
     * Set the {@code flavor} value.
     * Can be {@code null}.
     */
    public void setFlavor(@Nullable String flavor) {
        mFlavor = flavor;
    }

    /**
     * Get the {@code toughness} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getToughness() {
        return mToughness;
    }

    /**
     * Set the {@code toughness} value.
     * Can be {@code null}.
     */
    public void setToughness(@Nullable String toughness) {
        mToughness = toughness;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getType() {
        return mType;
    }

    /**
     * Set the {@code type} value.
     * Can be {@code null}.
     */
    public void setType(@Nullable String type) {
        mType = type;
    }

    /**
     * Get the {@code power} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getPower() {
        return mPower;
    }

    /**
     * Set the {@code power} value.
     * Can be {@code null}.
     */
    public void setPower(@Nullable String power) {
        mPower = power;
    }

    /**
     * Get the {@code imageurl} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public String getImageurl() {
        return mImageurl;
    }

    /**
     * Set the {@code imageurl} value.
     * Can be {@code null}.
     */
    public void setImageurl(@Nullable String imageurl) {
        mImageurl = imageurl;
    }

    /**
     * Get the {@code cmc} value.
     * Can be {@code null}.
     */
    @Nullable
    @Override
    public Integer getCmc() {
        return mCmc;
    }

    /**
     * Set the {@code cmc} value.
     * Can be {@code null}.
     */
    public void setCmc(@Nullable Integer cmc) {
        mCmc = cmc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardsBean bean = (CardsBean) o;
        return mId == bean.mId;
    }

    @Override
    public int hashCode() {
        return (int) (mId ^ (mId >>> 32));
    }

    /**
     * Instantiate a new CardsBean with specified values.
     */
    @NonNull
    public static CardsBean newInstance(long id, @Nullable String cardId, @Nullable String colors, @Nullable String rarity, @Nullable String name, @Nullable String text, @Nullable String flavor, @Nullable String toughness, @Nullable String type, @Nullable String power, @Nullable String imageurl, @Nullable Integer cmc) {
        CardsBean res = new CardsBean();
        res.mId = id;
        res.mCardId = cardId;
        res.mColors = colors;
        res.mRarity = rarity;
        res.mName = name;
        res.mText = text;
        res.mFlavor = flavor;
        res.mToughness = toughness;
        res.mType = type;
        res.mPower = power;
        res.mImageurl = imageurl;
        res.mCmc = cmc;
        return res;
    }

    /**
     * Instantiate a new CardsBean with all the values copied from the given model.
     */
    @NonNull
    public static CardsBean copy(@NonNull CardsModel from) {
        CardsBean res = new CardsBean();
        res.mId = from.getId();
        res.mCardId = from.getCardId();
        res.mColors = from.getColors();
        res.mRarity = from.getRarity();
        res.mName = from.getName();
        res.mText = from.getText();
        res.mFlavor = from.getFlavor();
        res.mToughness = from.getToughness();
        res.mType = from.getType();
        res.mPower = from.getPower();
        res.mImageurl = from.getImageurl();
        res.mCmc = from.getCmc();
        return res;
    }

    public static class Builder {
        private CardsBean mRes = new CardsBean();

        /**
         * Primary key.
         */
        public Builder id(long id) {
            mRes.mId = id;
            return this;
        }

        /**
         * Set the {@code card_id} value.
         * Can be {@code null}.
         */
        public Builder cardId(@Nullable String cardId) {
            mRes.mCardId = cardId;
            return this;
        }

        /**
         * Set the {@code colors} value.
         * Can be {@code null}.
         */
        public Builder colors(@Nullable String colors) {
            mRes.mColors = colors;
            return this;
        }

        /**
         * Set the {@code rarity} value.
         * Can be {@code null}.
         */
        public Builder rarity(@Nullable String rarity) {
            mRes.mRarity = rarity;
            return this;
        }

        /**
         * Set the {@code name} value.
         * Can be {@code null}.
         */
        public Builder name(@Nullable String name) {
            mRes.mName = name;
            return this;
        }

        /**
         * Set the {@code text} value.
         * Can be {@code null}.
         */
        public Builder text(@Nullable String text) {
            mRes.mText = text;
            return this;
        }

        /**
         * Set the {@code flavor} value.
         * Can be {@code null}.
         */
        public Builder flavor(@Nullable String flavor) {
            mRes.mFlavor = flavor;
            return this;
        }

        /**
         * Set the {@code toughness} value.
         * Can be {@code null}.
         */
        public Builder toughness(@Nullable String toughness) {
            mRes.mToughness = toughness;
            return this;
        }

        /**
         * Set the {@code type} value.
         * Can be {@code null}.
         */
        public Builder type(@Nullable String type) {
            mRes.mType = type;
            return this;
        }

        /**
         * Set the {@code power} value.
         * Can be {@code null}.
         */
        public Builder power(@Nullable String power) {
            mRes.mPower = power;
            return this;
        }

        /**
         * Set the {@code imageurl} value.
         * Can be {@code null}.
         */
        public Builder imageurl(@Nullable String imageurl) {
            mRes.mImageurl = imageurl;
            return this;
        }

        /**
         * Set the {@code cmc} value.
         * Can be {@code null}.
         */
        public Builder cmc(@Nullable Integer cmc) {
            mRes.mCmc = cmc;
            return this;
        }

        /**
         * Get a new CardsBean built with the given values.
         */
        public CardsBean build() {
            return mRes;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}

package com.pau.a14270729b.magiccards.provider.cards;

// @formatter:off
import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.pau.a14270729b.magiccards.provider.base.AbstractSelection;

/**
 * Selection for the {@code cards} table.
 */
@SuppressWarnings({"unused", "WeakerAccess", "Recycle"})
public class CardsSelection extends AbstractSelection<CardsSelection> {
    @Override
    protected Uri baseUri() {
        return CardsColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CardsCursor} object, which is positioned before the first entry, or null.
     */
    public CardsCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CardsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CardsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CardsCursor} object, which is positioned before the first entry, or null.
     */
    public CardsCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CardsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CardsCursor query(Context context) {
        return query(context, null);
    }


    public CardsSelection id(long... value) {
        addEquals("cards." + CardsColumns._ID, toObjectArray(value));
        return this;
    }

    public CardsSelection idNot(long... value) {
        addNotEquals("cards." + CardsColumns._ID, toObjectArray(value));
        return this;
    }

    public CardsSelection orderById(boolean desc) {
        orderBy("cards." + CardsColumns._ID, desc);
        return this;
    }

    public CardsSelection orderById() {
        return orderById(false);
    }

    public CardsSelection cardId(String... value) {
        addEquals(CardsColumns.CARD_ID, value);
        return this;
    }

    public CardsSelection cardIdNot(String... value) {
        addNotEquals(CardsColumns.CARD_ID, value);
        return this;
    }

    public CardsSelection cardIdLike(String... value) {
        addLike(CardsColumns.CARD_ID, value);
        return this;
    }

    public CardsSelection cardIdContains(String... value) {
        addContains(CardsColumns.CARD_ID, value);
        return this;
    }

    public CardsSelection cardIdStartsWith(String... value) {
        addStartsWith(CardsColumns.CARD_ID, value);
        return this;
    }

    public CardsSelection cardIdEndsWith(String... value) {
        addEndsWith(CardsColumns.CARD_ID, value);
        return this;
    }

    public CardsSelection orderByCardId(boolean desc) {
        orderBy(CardsColumns.CARD_ID, desc);
        return this;
    }

    public CardsSelection orderByCardId() {
        orderBy(CardsColumns.CARD_ID, false);
        return this;
    }

    public CardsSelection colors(String... value) {
        addEquals(CardsColumns.COLORS, value);
        return this;
    }

    public CardsSelection colorsNot(String... value) {
        addNotEquals(CardsColumns.COLORS, value);
        return this;
    }

    public CardsSelection colorsLike(String... value) {
        addLike(CardsColumns.COLORS, value);
        return this;
    }

    public CardsSelection colorsContains(String... value) {
        addContains(CardsColumns.COLORS, value);
        return this;
    }

    public CardsSelection colorsStartsWith(String... value) {
        addStartsWith(CardsColumns.COLORS, value);
        return this;
    }

    public CardsSelection colorsEndsWith(String... value) {
        addEndsWith(CardsColumns.COLORS, value);
        return this;
    }

    public CardsSelection orderByColors(boolean desc) {
        orderBy(CardsColumns.COLORS, desc);
        return this;
    }

    public CardsSelection orderByColors() {
        orderBy(CardsColumns.COLORS, false);
        return this;
    }

    public CardsSelection rarity(String... value) {
        addEquals(CardsColumns.RARITY, value);
        return this;
    }

    public CardsSelection rarityNot(String... value) {
        addNotEquals(CardsColumns.RARITY, value);
        return this;
    }

    public CardsSelection rarityLike(String... value) {
        addLike(CardsColumns.RARITY, value);
        return this;
    }

    public CardsSelection rarityContains(String... value) {
        addContains(CardsColumns.RARITY, value);
        return this;
    }

    public CardsSelection rarityStartsWith(String... value) {
        addStartsWith(CardsColumns.RARITY, value);
        return this;
    }

    public CardsSelection rarityEndsWith(String... value) {
        addEndsWith(CardsColumns.RARITY, value);
        return this;
    }

    public CardsSelection orderByRarity(boolean desc) {
        orderBy(CardsColumns.RARITY, desc);
        return this;
    }

    public CardsSelection orderByRarity() {
        orderBy(CardsColumns.RARITY, false);
        return this;
    }

    public CardsSelection name(String... value) {
        addEquals(CardsColumns.NAME, value);
        return this;
    }

    public CardsSelection nameNot(String... value) {
        addNotEquals(CardsColumns.NAME, value);
        return this;
    }

    public CardsSelection nameLike(String... value) {
        addLike(CardsColumns.NAME, value);
        return this;
    }

    public CardsSelection nameContains(String... value) {
        addContains(CardsColumns.NAME, value);
        return this;
    }

    public CardsSelection nameStartsWith(String... value) {
        addStartsWith(CardsColumns.NAME, value);
        return this;
    }

    public CardsSelection nameEndsWith(String... value) {
        addEndsWith(CardsColumns.NAME, value);
        return this;
    }

    public CardsSelection orderByName(boolean desc) {
        orderBy(CardsColumns.NAME, desc);
        return this;
    }

    public CardsSelection orderByName() {
        orderBy(CardsColumns.NAME, false);
        return this;
    }

    public CardsSelection text(String... value) {
        addEquals(CardsColumns.TEXT, value);
        return this;
    }

    public CardsSelection textNot(String... value) {
        addNotEquals(CardsColumns.TEXT, value);
        return this;
    }

    public CardsSelection textLike(String... value) {
        addLike(CardsColumns.TEXT, value);
        return this;
    }

    public CardsSelection textContains(String... value) {
        addContains(CardsColumns.TEXT, value);
        return this;
    }

    public CardsSelection textStartsWith(String... value) {
        addStartsWith(CardsColumns.TEXT, value);
        return this;
    }

    public CardsSelection textEndsWith(String... value) {
        addEndsWith(CardsColumns.TEXT, value);
        return this;
    }

    public CardsSelection orderByText(boolean desc) {
        orderBy(CardsColumns.TEXT, desc);
        return this;
    }

    public CardsSelection orderByText() {
        orderBy(CardsColumns.TEXT, false);
        return this;
    }

    public CardsSelection flavor(String... value) {
        addEquals(CardsColumns.FLAVOR, value);
        return this;
    }

    public CardsSelection flavorNot(String... value) {
        addNotEquals(CardsColumns.FLAVOR, value);
        return this;
    }

    public CardsSelection flavorLike(String... value) {
        addLike(CardsColumns.FLAVOR, value);
        return this;
    }

    public CardsSelection flavorContains(String... value) {
        addContains(CardsColumns.FLAVOR, value);
        return this;
    }

    public CardsSelection flavorStartsWith(String... value) {
        addStartsWith(CardsColumns.FLAVOR, value);
        return this;
    }

    public CardsSelection flavorEndsWith(String... value) {
        addEndsWith(CardsColumns.FLAVOR, value);
        return this;
    }

    public CardsSelection orderByFlavor(boolean desc) {
        orderBy(CardsColumns.FLAVOR, desc);
        return this;
    }

    public CardsSelection orderByFlavor() {
        orderBy(CardsColumns.FLAVOR, false);
        return this;
    }

    public CardsSelection toughness(String... value) {
        addEquals(CardsColumns.TOUGHNESS, value);
        return this;
    }

    public CardsSelection toughnessNot(String... value) {
        addNotEquals(CardsColumns.TOUGHNESS, value);
        return this;
    }

    public CardsSelection toughnessLike(String... value) {
        addLike(CardsColumns.TOUGHNESS, value);
        return this;
    }

    public CardsSelection toughnessContains(String... value) {
        addContains(CardsColumns.TOUGHNESS, value);
        return this;
    }

    public CardsSelection toughnessStartsWith(String... value) {
        addStartsWith(CardsColumns.TOUGHNESS, value);
        return this;
    }

    public CardsSelection toughnessEndsWith(String... value) {
        addEndsWith(CardsColumns.TOUGHNESS, value);
        return this;
    }

    public CardsSelection orderByToughness(boolean desc) {
        orderBy(CardsColumns.TOUGHNESS, desc);
        return this;
    }

    public CardsSelection orderByToughness() {
        orderBy(CardsColumns.TOUGHNESS, false);
        return this;
    }

    public CardsSelection type(String... value) {
        addEquals(CardsColumns.TYPE, value);
        return this;
    }

    public CardsSelection typeNot(String... value) {
        addNotEquals(CardsColumns.TYPE, value);
        return this;
    }

    public CardsSelection typeLike(String... value) {
        addLike(CardsColumns.TYPE, value);
        return this;
    }

    public CardsSelection typeContains(String... value) {
        addContains(CardsColumns.TYPE, value);
        return this;
    }

    public CardsSelection typeStartsWith(String... value) {
        addStartsWith(CardsColumns.TYPE, value);
        return this;
    }

    public CardsSelection typeEndsWith(String... value) {
        addEndsWith(CardsColumns.TYPE, value);
        return this;
    }

    public CardsSelection orderByType(boolean desc) {
        orderBy(CardsColumns.TYPE, desc);
        return this;
    }

    public CardsSelection orderByType() {
        orderBy(CardsColumns.TYPE, false);
        return this;
    }

    public CardsSelection power(String... value) {
        addEquals(CardsColumns.POWER, value);
        return this;
    }

    public CardsSelection powerNot(String... value) {
        addNotEquals(CardsColumns.POWER, value);
        return this;
    }

    public CardsSelection powerLike(String... value) {
        addLike(CardsColumns.POWER, value);
        return this;
    }

    public CardsSelection powerContains(String... value) {
        addContains(CardsColumns.POWER, value);
        return this;
    }

    public CardsSelection powerStartsWith(String... value) {
        addStartsWith(CardsColumns.POWER, value);
        return this;
    }

    public CardsSelection powerEndsWith(String... value) {
        addEndsWith(CardsColumns.POWER, value);
        return this;
    }

    public CardsSelection orderByPower(boolean desc) {
        orderBy(CardsColumns.POWER, desc);
        return this;
    }

    public CardsSelection orderByPower() {
        orderBy(CardsColumns.POWER, false);
        return this;
    }

    public CardsSelection imageurl(String... value) {
        addEquals(CardsColumns.IMAGEURL, value);
        return this;
    }

    public CardsSelection imageurlNot(String... value) {
        addNotEquals(CardsColumns.IMAGEURL, value);
        return this;
    }

    public CardsSelection imageurlLike(String... value) {
        addLike(CardsColumns.IMAGEURL, value);
        return this;
    }

    public CardsSelection imageurlContains(String... value) {
        addContains(CardsColumns.IMAGEURL, value);
        return this;
    }

    public CardsSelection imageurlStartsWith(String... value) {
        addStartsWith(CardsColumns.IMAGEURL, value);
        return this;
    }

    public CardsSelection imageurlEndsWith(String... value) {
        addEndsWith(CardsColumns.IMAGEURL, value);
        return this;
    }

    public CardsSelection orderByImageurl(boolean desc) {
        orderBy(CardsColumns.IMAGEURL, desc);
        return this;
    }

    public CardsSelection orderByImageurl() {
        orderBy(CardsColumns.IMAGEURL, false);
        return this;
    }

    public CardsSelection cmc(Integer... value) {
        addEquals(CardsColumns.CMC, value);
        return this;
    }

    public CardsSelection cmcNot(Integer... value) {
        addNotEquals(CardsColumns.CMC, value);
        return this;
    }

    public CardsSelection cmcGt(int value) {
        addGreaterThan(CardsColumns.CMC, value);
        return this;
    }

    public CardsSelection cmcGtEq(int value) {
        addGreaterThanOrEquals(CardsColumns.CMC, value);
        return this;
    }

    public CardsSelection cmcLt(int value) {
        addLessThan(CardsColumns.CMC, value);
        return this;
    }

    public CardsSelection cmcLtEq(int value) {
        addLessThanOrEquals(CardsColumns.CMC, value);
        return this;
    }

    public CardsSelection orderByCmc(boolean desc) {
        orderBy(CardsColumns.CMC, desc);
        return this;
    }

    public CardsSelection orderByCmc() {
        orderBy(CardsColumns.CMC, false);
        return this;
    }
}

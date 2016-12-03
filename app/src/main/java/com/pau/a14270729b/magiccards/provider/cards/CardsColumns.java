package com.pau.a14270729b.magiccards.provider.cards;

// @formatter:off
import android.net.Uri;
import android.provider.BaseColumns;

import com.pau.a14270729b.magiccards.provider.CardsProvider;
import com.pau.a14270729b.magiccards.provider.base.AbstractSelection;
import com.pau.a14270729b.magiccards.provider.cards.CardsColumns;

/**
 * Columns for the {@code cards} table.
 */
@SuppressWarnings("unused")
public class CardsColumns implements BaseColumns {
    public static final String TABLE_NAME = "cards";
    public static final Uri CONTENT_URI = Uri.parse(CardsProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String CARD_ID = "card_ID";

    public static final String COLORS = "colors";

    public static final String RARITY = "rarity";

    public static final String NAME = "name";

    public static final String TEXT = "text";

    public static final String FLAVOR = "flavor";

    public static final String TOUGHNESS = "toughness";

    public static final String TYPE = "type";

    public static final String POWER = "power";

    public static final String IMAGEURL = "imageUrl";

    public static final String CMC = "cmc";


    public static final String DEFAULT_ORDER = null;

    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            CARD_ID,
            COLORS,
            RARITY,
            NAME,
            TEXT,
            FLAVOR,
            TOUGHNESS,
            TYPE,
            POWER,
            IMAGEURL,
            CMC
    };

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(CARD_ID) || c.contains("." + CARD_ID)) return true;
            if (c.equals(COLORS) || c.contains("." + COLORS)) return true;
            if (c.equals(RARITY) || c.contains("." + RARITY)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(TEXT) || c.contains("." + TEXT)) return true;
            if (c.equals(FLAVOR) || c.contains("." + FLAVOR)) return true;
            if (c.equals(TOUGHNESS) || c.contains("." + TOUGHNESS)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
            if (c.equals(POWER) || c.contains("." + POWER)) return true;
            if (c.equals(IMAGEURL) || c.contains("." + IMAGEURL)) return true;
            if (c.equals(CMC) || c.contains("." + CMC)) return true;
        }
        return false;
    }

}

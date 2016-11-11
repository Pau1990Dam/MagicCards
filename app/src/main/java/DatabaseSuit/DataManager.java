package DatabaseSuit;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;

import ContentProvider.MagicContentProvider;
import Pojos.Card;
import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 14270729b on 11/11/16.
 */

public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with(MagicContentProvider.AUTHORITY);
    private static Uri CARD_URI = URI_HELPER.getUri(Card.class);

    public static void saveCards(ArrayList<Card> cards, Context context) {
        cupboard().withContext(context).put(CARD_URI, Card.class, cards);
    }

    public static void deleteCards(Context context) {
        cupboard().withContext(context).delete(CARD_URI, "_id > ?", "1");
    }
}

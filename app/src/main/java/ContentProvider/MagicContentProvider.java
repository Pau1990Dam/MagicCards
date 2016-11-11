package ContentProvider;

import Pojos.Card;
import nl.littlerobots.cupboard.tools.BuildConfig;
import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 14270729b on 11/11/16.
 */

public class MagicContentProvider extends CupboardContentProvider {

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Card.class);
    }

    public MagicContentProvider(){
        super(AUTHORITY,1);
    }
}

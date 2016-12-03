package com.pau.a14270729b.magiccards.provider;

// @formatter:off
import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.pau.a14270729b.magiccards.BuildConfig;
import com.pau.a14270729b.magiccards.provider.cards.CardsColumns;

public class CardsSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = CardsSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "Cards.db";
    private static final int DATABASE_VERSION = 1;
    private static CardsSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final CardsSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    public static final String SQL_CREATE_TABLE_CARDS = "CREATE TABLE IF NOT EXISTS "
            + CardsColumns.TABLE_NAME + " ( "
            + CardsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CardsColumns.CARD_ID + " TEXT, "
            + CardsColumns.COLORS + " TEXT, "
            + CardsColumns.RARITY + " TEXT, "
            + CardsColumns.NAME + " TEXT, "
            + CardsColumns.TEXT + " TEXT, "
            + CardsColumns.FLAVOR + " TEXT, "
            + CardsColumns.TOUGHNESS + " TEXT, "
            + CardsColumns.TYPE + " TEXT, "
            + CardsColumns.POWER + " TEXT, "
            + CardsColumns.IMAGEURL + " TEXT, "
            + CardsColumns.CMC + " INTEGER "
            + " );";


    public static CardsSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static CardsSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static CardsSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new CardsSQLiteOpenHelper(context);
    }

    private CardsSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new CardsSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static CardsSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new CardsSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private CardsSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new CardsSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_CARDS);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}

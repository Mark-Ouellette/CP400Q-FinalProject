package ca.wlu.markouellette.cp400q_finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dalton on 2017-04-03.
 */

public class PaymentDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "payments.sqllite";
    private static final int VERSION = 1;

    private static final String TABLE_PAYMENTS = "payment";
    private static final String COLUMN_PAYMENT_CONTACT_NAME = "contact_name";
    private static final String COLUMN_PAYMENT_PAY_TO = "pay_to";
    private static final String COLUMN_PAYMENT_AMOUNT = "amount";
    private static final String COLUMN_PAYMENT_DESCRIPTION = "description";
    private static final String COLUMN_PAYMENT_PAID = "paid";

    public PaymentDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_PAYMENTS + "(" +
                "payment_id integer primary key autoincrement, " +
                COLUMN_PAYMENT_CONTACT_NAME + " text not null, " +
                COLUMN_PAYMENT_PAY_TO + " boolean not null, " +
                COLUMN_PAYMENT_AMOUNT + " double not null, " +
                COLUMN_PAYMENT_DESCRIPTION + " text, " +
                COLUMN_PAYMENT_PAID + " boolean not null" +");");
    }

    public int insertPayment (Payment payment){
        return -1;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

package ca.wlu.markouellette.cp400q_finalproject;

import java.util.Date;

/**
 * Created by Mark on 2017-03-29.
 */

public class Payment {

    private String mLabel;
    private Date mDate;
    private double mBillAmount;
    private int mNumPeople;
    private Date mReminderDate;
    private double mAmountPaid;
    private boolean mPaid;

    public Payment(String l, Date d, double bill, int num, Date remDate, double amount, boolean paid){
        mLabel = l;
        mDate = new Date();
        mBillAmount = bill;
        mNumPeople = num;
        mReminderDate = remDate;
        mAmountPaid = amount;
        mPaid = paid;
    }

    public Payment(String l, boolean paid){
        mLabel = l;
        mPaid = paid;

    }

    public String getLabel(){
        return this.mLabel;
    }
    public boolean isChecked(){
        return mPaid;
    }
}

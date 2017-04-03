package ca.wlu.markouellette.cp400q_finalproject;

/**
 * Created by Mark on 2017-03-29.
 */

public class Payment {

    private String mLabel;
    private long mDate;
    private double mBillAmount;
    private int mNumPeople;
    private long mReminderDate;
    private double mAmountPaid;
    private boolean mPaid;

    public Payment(String l, long date, double bill, int num, long remDate, double amount, boolean paid){
        mLabel = l;
        mDate = date;
        mBillAmount = bill;
        mNumPeople = num;
        mReminderDate = remDate;
        mAmountPaid = amount;
        mPaid = paid;
    }
    public Payment(String l, long date, int num, long remDate){

        mLabel = l;
        mDate = date ;
        mNumPeople = num;
        mReminderDate = remDate;
        mPaid = false;
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

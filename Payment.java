package ca.wlu.markouellette.cp400q_finalproject;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Mark on 2017-03-29.
 */

public class Payment {
    private UUID mId;
    private String mContactName;
    // true if the user owes money to the contact, false if the user is owed money by the contact
    private boolean mPayTo;
    private double mAmount;
    private long mPaymentDate;
    private String mDescription;
    //private int mNumContributors;
    private HashMap<String, Double> mContributors;

    private boolean mPaid;

    public Payment(String contactName, boolean payTo, double amount, long paymentDate, String description) {
        mId = UUID.randomUUID();
        mContactName = contactName;
        mPayTo = payTo;
        mAmount = Math.round(amount * 100) / 100;
        mPaymentDate = paymentDate;
        mDescription = description;
        mPaid = false;
    }

    public Payment(String contactName, boolean payTo, double amount) {
        mId = UUID.randomUUID();
        mContactName = contactName;
        mPayTo = payTo;
        mAmount = amount;
        mPaid = false;
    }

    public UUID getId() {
        return mId;
    }

    public String getContactName() {
        return mContactName;
    }

    public boolean isPayTo() {
        return mPayTo;
    }

    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
    }

    public long getPaymentDate() {
        return mPaymentDate;
    }

    public void setPaymentDate(long paymentDate) {
        mPaymentDate = paymentDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isPaid() {
        return mPaid;
    }

    public void setPaid(boolean paid) {
        mPaid = paid;
    }
}

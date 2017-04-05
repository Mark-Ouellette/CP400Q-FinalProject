package ca.wlu.markouellette.cp400q_finalproject;

import java.util.UUID;

/**
 * Created by Mark on 2017-03-29.
 */

public class Payment {
    private UUID mId;

    private String mLabel;
    // true if the user owes money to the contact, false if the user is owed money by the contact
    private boolean mIsPayTo;
    private double mAmount;
    private long mPaymentDate;
    private long mReminderDate;
    private long mReminderTime;
    private String mDescription;
    private boolean mIsPaid;

    public Payment(String label, boolean isPayTo, double amount, long paymentDate, long reminderDate, long reminderTime, String description) {
        mId = UUID.randomUUID();
        mLabel = label;
        mIsPayTo = isPayTo;
        mAmount = Math.round(amount * 100) / 100;
        mPaymentDate = paymentDate;
        mReminderDate = reminderDate;
        mReminderTime = reminderTime;
        mDescription = description;
        mIsPaid = false;
    }

    public Payment(String label, boolean isPayTo, double amount) {
        mId = UUID.randomUUID();
        mLabel = label;
        mIsPayTo = isPayTo;
        mAmount = amount;
        mIsPaid = false;
    }

    public UUID getId() {
        return mId;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public boolean isPayTo() {
        return mIsPayTo;
    }

    public void setPayTo(boolean payTo) {
        mIsPayTo = payTo;
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

    public long getReminderDate() {
        return mReminderDate;
    }

    public void setReminderDate(long reminderDate) {
        mReminderDate = reminderDate;
    }

    public long getReminderTime() {
        return mReminderTime;
    }

    public void setReminderTime(long reminderTime) {
        mReminderTime = reminderTime;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isPaid() {
        return mIsPaid;
    }

    public void setPaid(boolean paid) {
        mIsPaid = paid;
    }
}

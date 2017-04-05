package ca.wlu.markouellette.cp400q_finalproject;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentLog {
    private static PaymentLog sPaymentLog;
    private ArrayList<Payment> mPayments;

    public static PaymentLog get(Context context) {
        if (sPaymentLog == null) {
            sPaymentLog = new PaymentLog(context);
        }
        return sPaymentLog;
    }

    private PaymentLog(Context context) {
        mPayments = new ArrayList<>();
        mPayments.add(new Payment("Ian", false, 15.00));
        mPayments.add(new Payment("Erica", false, 15.00));
    }

    public void addPayment(Payment p) {
        mPayments.add(p);
    }

    public void removePayment(Payment p){
        mPayments.remove(p);
    }

    public List<Payment> getPayments() {
        return mPayments;
    }

    public Payment getPayment(UUID id) {
        for (Payment p : mPayments) {
            if (p.getId().compareTo(id) == 0) {
                return p;
            }
        }
        return null;
    }



}

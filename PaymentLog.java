package ca.wlu.markouellette.cp400q_finalproject;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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



}

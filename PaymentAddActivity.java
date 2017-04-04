package ca.wlu.markouellette.cp400q_finalproject;


import android.support.v4.app.Fragment;

/**
 * Created by Dalton on 2017-04-04.
 */

public class PaymentAddActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        PaymentAddFragment p = new PaymentAddFragment();
        p.setArguments(this.getIntent().getBundleExtra(PaymentAddFragment.EXISTING_PAYMENT_BUNDLE));
        return p;
    }
}

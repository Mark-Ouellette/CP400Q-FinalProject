package ca.wlu.markouellette.cp400q_finalproject;

import android.support.v4.app.Fragment;

public class PaymentListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        return new PaymentListFragment();
    }

}

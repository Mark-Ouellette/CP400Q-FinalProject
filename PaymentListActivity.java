package ca.wlu.markouellette.cp400q_finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class PaymentListActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    @Override
    protected Fragment createFragment() {
        return new PaymentListFragment();
    }

}

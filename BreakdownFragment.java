package ca.wlu.markouellette.cp400q_finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BreakdownFragment extends Fragment {

    String mName;
    String mPhone;
    String mPhoto;
    String mtitleBarname = "";
    ImageView mPhotoView;
    PaymentListFragment fragment = new PaymentListFragment();
    String packagename = fragment.getClass().getPackage().getName();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*mtitleBarname = getActivity().getIntent().getStringExtra(PaymentListFragment.EXTRA_NAME);
        getActivity().setTitle(mtitleBarname);

        mName = getActivity().getIntent().getStringExtra(PaymentListFragment.EXTRA_NAME);
        mPhone = getActivity().getIntent().getStringExtra(PaymentListFragment.EXTRA_PHONE);
        mPhoto = getActivity().getIntent().getStringExtra(PaymentListFragment.EXTRA_PHOTO);*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_payment_child, container, false);
        /*TextView name = (TextView) v.findViewById(R.id.list_child_contact_name);
        name.setText(mName);
        TextView number = (TextView) v.findViewById(R.id.list_child_phone_number);
        number.setText(mPhone);
        int resID = getResources().getIdentifier(mPhoto,"drawable",packagename);
        mPhotoView = (ImageView) v.findViewById(R.id.child_photo);
        mPhotoView.setImageResource(resID);*/
        return v;

    }

}

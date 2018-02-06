package ch.watchmaker.watchmakerhelper.fragments.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ch.watchmaker.watchmakerhelper.activities.base.BaseActivity;

/**
 * Basic implementation for a fragment. The following functionality is included:
 * - start an Activity with class.
 * - check permissions.
 * - change fragment.
 *
 * Created by SIK on 31.06.17.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * Every basic Bundle data should use this key.
     */
    public static final String BUNDEL_DATA = "data";
    /**
     * If there are more than one Parcelable to send.
     */
    public static final String BUNDEL_DATA_SECOND = "data_SECOND";
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Rx", "onDestroy " + this.getClass().getSimpleName() + "Fragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Rx", "onDetach");
    }

    protected void startActivity(Class activity) {
        Intent intent = new Intent(getActivity().getApplicationContext(), activity);
        startActivity(intent);
    }

    /**
     * hides the keyboard
     */
    protected void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            Log.d(BaseFragment.class.getName(), "Null Pointer while trying to hide the keyboard");
        }
    }

    public boolean checkPermissions(String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), permission);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    public void changeFragment(Class<? extends BaseFragment> fragment, boolean addToBackStack, String tag) {
        ((BaseActivity) getActivity()).changeFragment(fragment, addToBackStack, tag);
    }

    public void changeFragment(Class<? extends BaseFragment> fragment, boolean addToBackStack, String tag, Bundle bundle) {
        ((BaseActivity) getActivity()).changeFragment(fragment, addToBackStack, tag, bundle);
    }

    public void displaySimpleError(View view, @StringRes int id) {
        Snackbar.make(view, id, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Override this method for explanations if required.
     */
    public void explainPermission() {
    }

    public abstract
    @LayoutRes
    int getLayout();
}

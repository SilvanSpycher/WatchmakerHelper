package ch.watchmaker.watchmakerhelper.activities.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ch.watchmaker.watchmakerhelper.R;
import ch.watchmaker.watchmakerhelper.fragments.base.BaseFragment;

/**
 * Created by silva on 06.02.2018.
 */

public abstract class BaseActivity extends AppCompatActivity {


    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
    }

    public abstract
    @LayoutRes
    int getLayout();

    public void startIntent(Class c, boolean finish) {
        if (!c.getName().equals(getClass().getName())) {
            Intent intent = new Intent(this, c);
            startActivity(intent);

            if (finish) {
                finish();
            }
        }
    }

    public void startActivityForResult(Class c, int requestCode) {
        if (!c.getName().equals(getClass().getName())) {
            Intent intent = new Intent(this, c);
            startActivityForResult(intent, requestCode);
        }
    }

    public void changeFragment(Class<? extends BaseFragment> fragment, boolean addToBackStack, String tag) {
        this.changeFragment(fragment, addToBackStack, tag, null);
    }

    public void changeFragment(Class<? extends BaseFragment> fragment, boolean addToBackStack, String tag, @Nullable Bundle bundle) {

        if (!isFinishing()) {
            Fragment foundFragment = null;

            foundFragment = getSupportFragmentManager().findFragmentByTag(tag);

            //Do we already have an instance?
            if (foundFragment == null) {
                //If not create a new instance
                try {
                    foundFragment = fragment.newInstance();
                    //When we have to add a bundle add it.
                    if (bundle != null) {
                        foundFragment.setArguments(bundle);
                    }

                } catch (InstantiationException | IllegalAccessException e) {
                    return;
                }
            } else {
                //We have to add it in another way than a new instance.
                if (bundle != null) {
                    //Override all properties.
                    foundFragment.getArguments().putAll(bundle);
                }
            }

            //Add it to the back stack?
            if (addToBackStack) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, foundFragment, tag).addToBackStack(tag).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, foundFragment, tag).commit();
            }

        } else {
            Log.d("Base", "Activity is finishing");
        }

        if (isDestroyed()) {
            Log.d("Base", "Activity is destroyed");
        }

    }


}

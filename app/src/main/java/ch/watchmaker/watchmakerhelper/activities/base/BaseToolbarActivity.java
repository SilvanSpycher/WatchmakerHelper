package ch.watchmaker.watchmakerhelper.activities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import ch.watchmaker.watchmakerhelper.R;
import ch.watchmaker.watchmakerhelper.presenters.base.ToolbarPresenter;
import ch.watchmaker.watchmakerhelper.presenters.base.ToolbarView;

/**
 * Created by silva on 06.02.2018.
 */

public abstract class BaseToolbarActivity extends BaseActivity implements ToolbarView {

    @BindView(R.id.toolbar)Toolbar toolbar;
    //The presenter to call when a action happens
    private ToolbarPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //toolbar.setLogo(R.drawable.logo_small);
        toolbar.setTitle(R.string.cogwheel_calculator_name);
    }

    /**
     * Set the presenter for the toolbar.
     * @param toolbarPresenter The presenter to call when an interaction happens.
     */
    public void setToolbarPresenter(ToolbarPresenter toolbarPresenter) {
        this.presenter = toolbarPresenter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (presenter == null) {
            throw  new IllegalStateException("There is no ToolbarPresenter. Please hook it in on the activity start");
        }

        //Send the action the presenter so we can react on the action.
        switch (item.getItemId()) {
            case R.id.menu_settings:
                presenter.onMenuItemSelected(ToolbarPresenter.MENU_ITEM_OPTIONS);
                //Consume the action
                return true;
            default:
                //Return false so the system will handel the click
                return false;
        }
    }

    @Override
    public void onMenuItemSelected(int menuItemInternalId) {
        switch (menuItemInternalId) {
            case ToolbarPresenter.MENU_ITEM_OPTIONS:
                //startIntent(MenuActivity.class, false);
                break;
            default:
                break;
        }
    }

    @Override
    public void changeToolbarToSignatureStyle() {
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

}
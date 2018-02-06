package ch.watchmaker.watchmakerhelper.presenters.base;

/**
 * The ToolbarPresenter Interface is used for basic interactions for all Presenters which must support a toolbar.
 */

public interface ToolbarPresenter {

    /**
     * Menu items which are selected.
     */
    int MENU_ITEM_OPTIONS = 0;

    /**
     * A menu item was selected inform the presenter about it, so we can react to the action.
     */
    void onMenuItemSelected(int menuItemInternalId);

}
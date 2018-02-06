package ch.watchmaker.watchmakerhelper.presenters.base;

/**
 * The ToolbarView Interface must be implemented by all views which have a toolbar.
 */

public interface ToolbarView {

    /**
     * A menu item was selected react on the item.
     * @param menuItemInternalId The internal id. Which is on the {@link ToolbarPresenter}
     */
    void onMenuItemSelected(int menuItemInternalId);

    /**
     * Changes the toolbar background color and font to the signature style.
     */
    void changeToolbarToSignatureStyle();
}

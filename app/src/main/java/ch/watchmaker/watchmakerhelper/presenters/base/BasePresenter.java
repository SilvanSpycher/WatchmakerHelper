package ch.watchmaker.watchmakerhelper.presenters.base;

/**
 * Basic implementation of a presenter. Every presenter interface should extends it.
 */

public interface BasePresenter {

    /**
     * This method is called from the Activity or Fragment when it goes in the stopped state.
     */
    void stopSubscriptionsOnPause();
}

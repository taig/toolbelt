package io.taig.android.mosby;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.delegate.MvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;
import io.taig.android.mosby.delegate.DialogFragmentMvpDelegate;
import io.taig.android.mosby.delegate.DialogFragmentMvpDelegateImpl;
import io.taig.android.mosby.delegate.DialogFragmentMvpViewStateDelegateImpl;

/**
 * This is a enhancement of {@link com.hannesdorfmann.mosby3.mvp.MvpFragment} that introduces the
 * support of {@link ViewState}.
 * <p>
 * You can change the behaviour of what to do if the viewstate is empty (usually if the fragment
 * creates the viewState for the very first time and therefore has no state / data to restore) by
 * overriding {@link #onNewViewStateInstance()}
 * </p>
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class MvpViewStateDialogFragment<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
    extends MvpDialogFragment<V, P> implements MvpViewStateDelegateCallback<V, P, VS> {

    /**
     * The viewstate will be instantiated by calling {@link #createViewState()} in {@link
     * #onViewCreated(View, Bundle)}. Don't instantiate it by hand.
     */
    protected VS viewState;

    /**
     * A simple flag that indicates if the restoring ViewState  is in progress right now.
     */
    private boolean restoringViewState = false;

    @Override protected DialogFragmentMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new DialogFragmentMvpViewStateDelegateImpl<>( this, this, true, true );
        }

        return mvpDelegate;
    }

    @Override public VS getViewState() {
        return viewState;
    }

    @Override public void setViewState(VS viewState) {
        this.viewState = viewState;
    }

    @Override public void setRestoringViewState(boolean restoringViewState) {
        this.restoringViewState = restoringViewState;
    }

    @Override public boolean isRestoringViewState() {
        return restoringViewState;
    }

    @Override public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        // not needed. You could override this is subclasses if needed
    }
}
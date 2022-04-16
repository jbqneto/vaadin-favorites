package io.jbqneto.favorites.application.presentation.views.event;

import com.vaadin.flow.component.ComponentEvent;
import io.jbqneto.favorites.application.domain.model.business.Favorite;
import io.jbqneto.favorites.application.presentation.views.component.FavoriteForm;

public class FavoriteFormEvent extends ComponentEvent<FavoriteForm> {

    private Favorite favorite;

    public FavoriteFormEvent(FavoriteForm source, Favorite favorite) {
        super(source, false);
        this.favorite = favorite;
    }

    public Favorite getFavorite() {
        return this.favorite;
    }

    public static class SaveEvent extends FavoriteFormEvent {

        public SaveEvent(FavoriteForm source, Favorite favorite) {
            super(source, favorite);
        }
    }

    public static class DeleteEvent extends FavoriteFormEvent {

        public DeleteEvent(FavoriteForm source, Favorite favorite) {
            super(source, favorite);
        }
    }

    public static class CloseEvent extends FavoriteFormEvent {

        public CloseEvent(FavoriteForm source, Favorite favorite) {
            super(source, favorite);
        }
    }

}

import { Routes } from '@angular/router';
import {DeckOverviewComponent} from "./component/deck/deck-overview/deck-overview.component";
import {DeckDetailsComponent} from "./component/deck/deck-details/deck-details.component";
import {CardDetailsComponent} from "./component/card/card-details/card-details.component";
import {ImageManagerComponent} from "./component/image-manager/image-manager.component";

export const routes: Routes = [
    { path: "decks", component: DeckOverviewComponent, title: "All Decks" },
    { path: "deck/:id", component: DeckDetailsComponent, title: "Decks Details" },

    { path: "deck/:deckId/card/:cardId", component: CardDetailsComponent, title: "Card Details" },

    { path: "image", component: ImageManagerComponent, title: "Image Upload" },
];

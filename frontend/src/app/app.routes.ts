import { Routes } from '@angular/router';
import {DeckOverviewComponent} from "./component/deck/deck-overview/deck-overview.component";
import {DeckDetailsComponent} from "./component/deck/deck-details/deck-details.component";
import {CardDetailsComponent} from "./component/card/card-details/card-details.component";
import {ImageManagerComponent} from "./component/ui/image-manager/image-manager.component";
import {ReviewComponent} from "./component/review/review.component";

export const routes: Routes = [
    { path: "decks", component: DeckOverviewComponent, title: "All Decks" },
    { path: "deck/:deckId", component: DeckDetailsComponent, title: "Decks Details" },
    { path: "deck/:deckId/review", component: ReviewComponent, title: "Review" },
    { path: "deck/:deckId/card/:cardId", component: CardDetailsComponent, title: "Card Details" },
];

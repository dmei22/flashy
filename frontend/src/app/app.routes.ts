import { Routes } from '@angular/router';
import {DeckOverviewComponent} from "./component/deck/deck-overview/deck-overview.component";
import {DeckDetailsComponent} from "./component/deck/deck-details/deck-details.component";

export const routes: Routes = [
    { path: "decks", component: DeckOverviewComponent, title: "All Decks" },
    { path: "deck/:id", component: DeckDetailsComponent, title: "Decks Details" },
];

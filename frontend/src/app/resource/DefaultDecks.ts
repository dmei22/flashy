import {DeckDetails, DeckOverview} from "../model/Deck";

export const DefaultDeckOverviews: DeckOverview[] = [
    { id: 0, name: "Science", description: "Science stuff"},
    { id: 1, name: "Arts", description: "Artsy stuff"},
    { id: 2, name: "Calculus", description: "Calculating stuff"},
    { id: 3, name: "History", description: "Historical stuff"},
    { id: 4, name: "Geography", description: "Geographical stuff"},
    { id: 5, name: "Programming", description: "Coding stuff"},
    { id: 6, name: "Biology", description: "Living things stuff"},
    { id: 7, name: "Music", description: "Musical stuff"},
]


export function defaultDeck(): DeckDetails {
    return { id: 0, name: "Science", description: "Science stuff", cards: [
            { id: 0, front: "Frontside", back: "Backside", },
            { id: 0, front: "Frontside", back: "Backside", },
            { id: 0, front: "Frontside", back: "Backside", },
        ]};
}
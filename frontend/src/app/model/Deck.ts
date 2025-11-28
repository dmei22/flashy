import {CardOverview} from "./Card";

export interface DeckOverview {
    id: number
    name: string
    description: string
}

export interface DeckDetails {
    id: number
    name: string
    description: string
    cards: CardOverview[]
}

export interface CreateDeckRequest {
    name: string
    description: string
}
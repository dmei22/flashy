import {Review} from "./Review";

export interface CardOverview {
    id: number,
    front: string,
    back: string,
}

export interface Card {
    id: number,
    front: string,
    back: string,
    reviews: Review[]
}

export interface CardCreateRequest {
    deckId: number
    front: string
    back: string
}
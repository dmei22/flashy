import {CardOverview} from "./Card";

// DTO's
export class DeckOverview {
    public id!: number
    public name!: string
    public description!: string
    public imageVersion!: number

    constructor() {
    }
}

export class DeckDetails {
    public id!: number
    public name!: string
    public description!: string
    public cards!: CardOverview[]
    public imageVersion!: number

    constructor() {
    }
}

// Requests & Responses
export interface DeckCreateRequest {
    name: string
    description: string
}

export interface DeckUpdateRequest {
    id: number
    name: string
    description: string
}
export interface CardOverview {
    id: number
    front: string
    back: string
    due: boolean
}

export interface CardDetails {
    id: number
    front: string
    back: string
}

export interface CardCreateRequest {
    front: string
    back: string
}

export interface CardUpdateRequest {
    front: string
    back: string
}
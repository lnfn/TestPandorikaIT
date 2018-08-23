package com.eugenetereshkov.testpandorikait.entity


class SearchResponse(
        override val results: List<Result>
) : ApiResponse<List<Result>>()

package org.lotka.xenonx.presentation.ui.screen.home

/**
 * @author Android Devs Academy (Arman Sherwanii)
 */
sealed interface MovieListUiEvent {
    data class Paginate(val category: String) : MovieListUiEvent
//    data class ShowSnakeBare(val message: String) : MovieListUiEvent
    object Navigate : MovieListUiEvent
}
# Pieces d'échec

Les pièces d'échec doivent toutes implémenter l'interface `IPiece` du package `game`.

## Interface `IPiece`

Cette interface permet d'implémenter différentes prototypes de méthodes dont :

`inCheck()`

Cette méthode permet de savoir si une pièce met ou non en échec le joueur qui la possède.

Par défault dans la classe abstraite Piece, cette méthode renvoit faux, mais dans le cas où l'implémentation de 
l'interface est faite pour une pièce comme le roi, cette méthode devra retourner vrai lorque la pièce est attaquée.
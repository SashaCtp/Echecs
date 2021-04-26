# Notes de travail

Détection du pat
---

**Le pat**

- Le roi n'est pas attaqué
- Il ne peut pas bouger : *toutes les cases autour de lui sont défendues par des pièces de l'opposant*
- Aucune pièce ne permet 

Détection du mat
---

**Le mat**

- Le roi est attaqué
- Il ne peut pas bouger : *toutes les cases voisines sont défendues par des pièces de l'opposant*

Déplacer une pièce
---

Pour déplacer une pièce, le joueur indique textuellement la case de départ (il sélectionne la pièce), et la case d'arrivée en suivant ce format: `a1b2`

*Avec `a1` la case de départ et `b2` celle de destination.*

Lorsqu'un joueur veut déplacer une pièce, il faut effectuer plusieurs vérifications permettant de savoir si le coup est légal ou non :

- Le déplacement correspond à sa manière de se déplacer
  
- Le déplacement n'est pas bloqué par une autre pièce (à l'exception du roque)
  
- La pièce, une fois déplacée, ne peut mettre en danger le Roi (situation d'échec)
  
  *Pour effectuer cette vérification, la classe abstraite `Piece` comporte la méthode `isCheck()`, 
  vérifiant si la pièce est en échec : cette dernière retourne faux pour toutes les pièces sauf pour le roi, 
  celle-ci doit retourner vrai si le roi est attaqué.*
  
  Ainsi, avec une boucle on peut savoir si le coup est légal.
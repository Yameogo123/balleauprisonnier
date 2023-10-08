# titre: balleauprisonnier

#description du projet

Projet en JavaFX d'un jeu de balle au prisonnier. 

Le principe est qu'il y a deux camps le Player contre les Machines (Automatisme gérer par l'ordinateur)

Le player lance des projectiles sur les machines et si une machine est touché il perd et donc disparait de l'arène. 

Pour ce faire on a utilisé le modèle MVC avec 
<p></p>
Model (Player, Machine, Projectile, Sprite) 
<p></p>
View (Field) 
<p></p>
Controller (MachineController: pour controller les actions de la machine, 
PlayerController celles du player principal et ProjectileController pour gérer les projectiles). 
<p></p>
Le main programme permettant de gérer (lancer) 
le code est le App.java.


#mode d'utilisation
<p></p>
Après avoir téléchargé ou cloné le projet:
Pour le lancer avec intelliJ il faut ouvrir le terminal et lancer la commande 
<p></p>
mvn javafx:run tout en maintenant la touche 'ctrl' (controller)

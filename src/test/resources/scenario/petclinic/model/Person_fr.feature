# language: fr
Fonctionnalité: Test sur la validation de l'objet persone

Contexte:
    Etant donné la langue est 'français'

  Scénario: Créons une personne avec le prénom vide et vérifions que l'on a un message de violation sur lui
    Etant donné on crée une nouvelle personne
    Et on valorise le prénom à 'John'
    Et on valorise le nom à ''
    Quand on applique la validation
    Alors on devrait avoir 1 violation
    Et on devrait avoir le message d'erreur sur 'lastName' qui dit 'ne peut pas être vide'
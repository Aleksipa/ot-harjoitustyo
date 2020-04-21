# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria. Koodin pakkausrakenne koostuu pakkauksesta pomodoroapp.ui, joka sisältää JavaFx:llä toteutetun käyttöliittymän. Pomodoroapp.domain pakkaus vastaa ohjelman sovelluslogiikasta ja pomodoroapp.dao tietojen pysyväistallenneuksesta.

## Sovelluslogiikka

Pomodoroservisen ja muiden osien suhdetta kuvaa seuraava luokkakaavio:

<img src="https://github.com/Aleksipa/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/PomodoroApp.png" width="400">

## Päätoiminnallisuudet

Seuraavassa on kuvattu sekvenssikaavioina muutamia sovelluksen päätoiminnallisuuksia

<img src="https://github.com/Aleksipa/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pomodoro.png" width="400">
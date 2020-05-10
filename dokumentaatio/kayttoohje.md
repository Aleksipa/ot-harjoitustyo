# Käyttöohje

Lataa tiedosto [pomodoroApp.jar](https://github.com/Aleksipa/ot-harjoitustyo/releases/tag/loppupalautus)

## Konfigurointi

Käynnistyshakemistossa täytyy olla konfiguraatiotiedosto _config.properties_, jossa määritellään pomodorojen ja käyttäjien tallentamiseen käytettävien tiedostojen nimet. Tiedoston muoto on seuraava

```
userFile=users.txt
doneFile=done.txt
```

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar pomodoroApp.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään, jonne syötetään olemassaoleva käyttäjätunnus syötekenttään ja painetaan _login_.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä voi siirtyä uuden käyttäjän luomisnäkymään panikkeella _create new user_.

Uusi käyttäjä luodaan syöttämällä tiedot syötekenttiin ja painamalla _create_ Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään.

## Pomodoron käynnistäminen ja pysäyttäminen

Onnistuneen kirjautumisen myötä siirrytään näkymään, jossa käyttäjä näkee suorittamansa työskentelyjaksot ja jossa käyttäjä voi käynnistää uuden työskentelyjakson painikkeella _start_.

Painamalla _pause_ painiketta käyttäjä voi pysäyttää työskentelyjakson. Sitten kun käyttäjä on taas valmis jatkamaan työskentelyjaksoa, ajastimen saa päälle painamalla uudestaan _start_ painiketta.

## Uloskirjautuminen ja ohjelman sammuttaminen käytön jälkeen

Klikkaamalla näkymän oikean ylänurkan painiketta _logout_ käyttäjä kirjautuu ulos sovelluksesta. Jos olet suorittanut pomodoron ajastimen loppuun ennen kuin kirjauduit ulos, voi seuraava käyttäjä kirjautua nyt sisään. Jos kuitenkin jätit pomodoron kesken, tulee sovellus sammuttaa ennen kuin seuraava käyttäjä voi kirjautua sisään.

## Ajastimen pituuden muuttaminen

Ajastimen kestoa voi lyhentää tai pidentää muokkaamalla pomodoroapp.ui kansiossa olevan PomodoroUi.java tiedoston riviä 248 sisältöä. Tällä hetkellä ajastin on säädetty 25 minuutin pituiseksi.

```
pomodoro.setTime(LocalTime.of(0, 25));
```

Tämän lisäksi tulee samassa tiedostossa rivillä 57 oleva timeToDisplay arvo muokata vastaamaan haluttua aikaa. 

```
private static String timeToDisplay = "25:00";
```
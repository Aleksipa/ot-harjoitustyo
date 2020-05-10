# Käyttöohje

Lataa tiedosto [pomodoroapp.jar](https://github.com/Aleksipa/ot-harjoitustyo/releases)

## Konfigurointi

Käynnistyshakemistossa täytyy olla konfiguraatiotiedosto _config.properties_, jossa määritellään pomodorojen ja käyttäjien tallentamiseen käytettävien tiedostojen nimet. Tiedoston muoto on seuraava

```
userFile=users.txt
doneFile=done.txt
```

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar pomodoroapp.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään, jonne syötetään olemassaoleva käyttäjätunnus syötekenttään ja painetaan _login_.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on voi siirtyä uuden käyttäjän luomisnäkymään panikkeella _create new user_.

Uusi käyttäjä luodaan syöttämällä tiedot syötekenttiin ja painamalla _create_ Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään.

## Pomodoron käynnistäminen ja pysäyttäminen

Onnistuneen kirjautumisen myötä siirrytään näkymään, jossa käyttäjä näkee suorittamansa työskentelyjaksot ja jossa käyttäjä voi käynnistää uuden työskentelyjakson painikkeella _start_.

Painamalla _pause_ painiketta käyttäjä voi pysäyttää työskentelyjakson. Sitten kun käyttäjä on taas valmis jatkamaan työskentelyjaksoa, sen saa päälle painamalla _start_ painiketta.

Klikkaamalla näkymän oikean ylänurkan painiketta _logout_ käyttäjä kirjautuu ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään.
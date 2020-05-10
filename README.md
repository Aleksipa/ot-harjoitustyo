# PomodoroApp

Sovelluksen avulla käyttäjien on mahdollista jaksottaa omaa työskentelyään 25 minuutin jaksoihin. Ohjelma pitää kirjaa siitä, miten monta työskentelyjaksoa käyttäjä on suorittanut. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttäjän, joilla kaikilla on oma yksilöllinen suorituslistansa.

## Dokumentaatio

[Käyttöohje](https://github.com/Aleksipa/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/Aleksipa/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Aleksipa/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/Aleksipa/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/Aleksipa/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

## Releaset

[Viikko 5](https://github.com/Aleksipa/ot-harjoitustyo/releases)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston pomodoroApp-1.0-SNAPSHOT.jar

### Checkstyle

Tiedostoon checkstyle.xml määritellyt tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
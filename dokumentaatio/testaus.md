# Testausdokumentti

Ohjelman testaaminen on suoritettu automatisoiduilla yksikkö- ja integraatiotesteillä Junittia käyttäen. Tämän lisäksi ohjelmaa on testattu manuaalisesti järjestelmätasolla.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Pakkausta pomodoroapp.domain luokkia testataan PomodoroServiceUserTest ja PomodoroServicePomodoroTest integraatiotesteillä. Tämän lisäksi luokille User ja Todo on tehty joitakin yksikkötestejä.

### DAO-luokat

DAO-luokat on testattu JUnitin automaatiotesteillä.

### Testauskattavuus

Sovelluksen testauksen rivikattavuus on 84% ja haarautumakattavuus 92%

<img src="https://github.com/Aleksipa/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.png" width="800">

## Järjestelmätestaus

### Asennus ja konfigurointi

Järjestelmätasolla sovellusta on testattu manuaalisesti, sekä OSX-, että Linux ympäristöissä. 

## Toiminnallisuudet

Sovelluksen kaikki toiminnallisuudet on testattu erilaisissa skenaarioissa. 

## Sovellukseen jääneet laatuongelmat

Yksikkötestauksen kattavuutta tulisi vielä kehittää. Sovellusta ei myöskään ole testattu Windows ympäristössä.


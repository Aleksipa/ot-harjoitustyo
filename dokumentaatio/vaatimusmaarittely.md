#Vaatimusmäärittely

##Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista jaksottaa omaa työskentelyään haluamansa pituisiin jaksoihin ja seurata miten monta esim. 30 minuutin työskentelyjaksoa käyttäjä on suorittanut. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttäjän, joilla kaikilla on oma yksilöllinen suorituslistansa.

##Käyttäjät

Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli normaali käyttäjä. Myöhemmin sovellukseen saatetaan lisätä suuremmilla oikeuksilla varustettu pääkäyttäjä.

##Perusversion tarjoama toiminnallisuus

###Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen. Käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 5 merkkiä.

- Käyttäjä voi kirjautua järjestelmään. Kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus kirjautumislomakkeelle. Jos käyttäjää ei olemassa, ilmoittaa järjestelmä tästä.

###Kirjautumisen jälkeen

- Käyttäjä näkee suorittamansa työskentelyjaksot

- Käyttäjä voi muokata yhden työskentelyjakson kestoa ja käynnistää ajastimen

- Käyttäjä voi käynnistää työskentelyjakson, jolloin ajastin käynnistyy ja näyttää miten paljon työskentelyjaksoa on jäljellä. Työskentelyjakson päätyttyä järjestelmä hälyttää, jolloin käyttäjä tietää työskentelyjakson päättyneen.

- Käyttäjä voi kirjautua ulos järjestelmästä

###Jatkokehitysideoita

- Päivä-, viikko- ja kuukausikohtainen raportti käyttäjän suorittamista työskentelyjaksoista
- Käyttäjien yhteyteen salasana, joka vaaditaan kirjautuessa
- Käyttäjätunnuksen ja siihen liittyvien tietojen poisto

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aleksipaavola
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
   @Test
    public void rahaMaaraOikeinKassassa() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void lounaitaMyytyKpl() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty() + paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisOstoToimiiKunsyoMaukkaasti() {
        assertEquals(200, paate.syoMaukkaasti(600));
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void josMaksuOnRiittavaMaukkaidenMyytyjenMaaraKasvaa() {
        paate.syoMaukkaasti(600);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josMaksuOnRiittavaEdullistenMyytyjenMaaraKasvaa() {
        paate.syoEdullisesti(600);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josMaksuEiRiittavaMaukkaidenMyytyjenMaaraKasvaa() {
        paate.syoMaukkaasti(300);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josMaksuEiRiittavaEdullistenMyytyjenMaaraKasvaa() {
        paate.syoEdullisesti(150);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josMaksuEiRiitaKassaEiMuutuMaukkaasti() {
        assertEquals(200, paate.syoMaukkaasti(200));
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void josMaksuEiRiitaKassaEiMuutuEdullisesti() {
        assertEquals(100, paate.syoEdullisesti(100));
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void korttiOstoToimiiEdullisissa() {
        assertTrue(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void korttiOstoToimiiMaukkaissa() {
        assertTrue(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void josKortillaONTarpeeksiRahaaEdullistenLounaidenMyyntimaaraKasvaa() {
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaONTarpeeksiRahaaMaukkaidenLounaidenMyyntimaaraKasvaa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaEdullistaLounastaEiMyyda() {
        kortti.otaRahaa(900);
        paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertFalse(paate.syoEdullisesti(kortti));
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaMaukastaLounastaEiMyyda() {
        kortti.otaRahaa(900);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertFalse(paate.syoMaukkaasti(kortti));
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
    @Test
    public void kassassaOlevaRahamaaraEimuutKortillaOstettaessaMaukkaasti() {
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kassassaOlevaRahamaaraEimuutKortillaOstettaessaEdullisesti() {
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void rahaaLadattaessaKortinSaldoJaKassanSaldoKasvaa() {
        paate.lataaRahaaKortille(kortti, 400);
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals("saldo: 14.0", kortti.toString());
    }
    
    @Test
    public void rahaaLadattaessaKortinSaldoJaKassanSaldoEiKasvaJosSummaNegatiivinen() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals("saldo: 10.0", kortti.toString());
    }
}

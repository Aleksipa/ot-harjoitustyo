package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
    kortti.lataaRahaa(2500);
        assertEquals("saldo: 35.0", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeJosRahaaOnTarpeeksi() {
    kortti.otaRahaa(100);
        assertEquals("saldo: 9.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
    kortti.otaRahaa(10000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void metodiPalauttaaTrueJosRahatRiittivat() {
    //kortti.otaRahaa(100);
        assertTrue(kortti.otaRahaa(100));
        assertFalse(kortti.otaRahaa(10000));
    }
    @Test
    public void saldoMetodiPalauttaaOikeanSumman() {
    kortti.otaRahaa(100);
    assertEquals(900, kortti.saldo());
    }
}

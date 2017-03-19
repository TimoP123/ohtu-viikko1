package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriNegatiivisellaParametrillaLuoNollavaraston() {
        varasto = new Varasto(-3);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriLuoTyhjanVaraston() {
        varasto = new Varasto(10, 0);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriTekeeNollavarastonNegatiivisellaTilavuusparametrilla() {
        varasto = new Varasto(-1, 0);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriAntaaSaldoksiNollanNegatiivisellaAlkusaldolla() {
        varasto = new Varasto(4, -2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test public void toinenKonstruktoriEiLaitaSaldoaIsommaksiKuinVarastonKoko() {
        varasto = new Varasto(4, 5);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriAsettaaAlkusaldonOikein() {
        varasto = new Varasto(10, 5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenSaldonLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(-3);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liikaLisaaminenEiVieSaldoaYliMaksimin() {
        varasto.lisaaVarastoon(12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenMaaranOttaminenPalauttaaNollan() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(-2);
        assertEquals(1, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void varastoEiAnnaEnepaaKuinSiellaOn() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(9);
        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(8);
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }

}
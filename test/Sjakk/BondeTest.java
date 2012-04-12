/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sjakk;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class BondeTest {
    
    public BondeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sjekkLovligeTrekk method, of class Bonde.
     */
    @Test
    public void testSjekkLovligeTrekk() {
        System.out.println("sjekkLovligeTrekk");
        Bonde hvitBonde = new Bonde(true);
        Bonde svartBonde = new Bonde(false);
        Rute r1 = new Rute(5, 1);
        Rute r2 = new Rute(5, 6);
        ArrayList<Rute> svartExpResult = new ArrayList();
        ArrayList<Rute> hvitExpResult = new ArrayList();
        hvitExpResult.add(new Rute(5, 2));
        hvitExpResult.add(new Rute(5, 3));
        hvitExpResult.add(new Rute(6, 2));
        hvitExpResult.add(new Rute(4, 2));
        svartExpResult.add(new Rute(5, 5));
        svartExpResult.add(new Rute(6, 5));
        svartExpResult.add(new Rute(4, 5));
        svartExpResult.add(new Rute(5, 4));
        ArrayList<Rute> hvitResult = hvitBonde.sjekkLovligeTrekk(r1);
        ArrayList<Rute> svartResult = svartBonde.sjekkLovligeTrekk(r2);
        assertEquals(svartExpResult, svartResult);
        assertEquals(hvitExpResult, hvitResult);
    }
}

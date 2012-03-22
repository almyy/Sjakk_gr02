/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Sjakk.Brett;
import Sjakk.Rute;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Rino
 */
public class BrettTest {
    
    public BrettTest() {
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
     * Test of getIcon method, of class Brett.
     */
    @Test
    public void testGetIcon() {
        System.out.println("getIcon");
        int i = 0;
        int j = 0;
        Brett instance = new Brett();
        ImageIcon expResult = null;
        ImageIcon result = instance.getIcon(i, j);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sjekkLovligeTrekk method, of class Brett.
     */
    @Test
    public void testSjekkLovligeTrekk() {
        System.out.println("sjekkLovligeTrekk");
        Brett instance = new Brett();
        Rute rute = instance.getRute(5,0);
        ArrayList expResult = null;
        ArrayList result = instance.sjekkLovligeTrekk(rute);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of flyttBrikke method, of class Brett.
     */
    @Test
    public void testFlyttBrikke() {
        System.out.println("flyttBrikke");
        Rute startRute = null;
        ArrayList<Rute> lovligeTrekk = null;
        Rute flyttRute = null;
        Brett instance = new Brett();
        instance.flyttBrikke(startRute, lovligeTrekk, flyttRute);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

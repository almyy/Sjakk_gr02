/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
     * Test of getRuter method, of class Brett.
     */
    @Test
    public void testGetRuter() {
        System.out.println("getRuter");
        Brett instance = new Brett();
        Rute[][] expResult = null;
        Rute[][] result = instance.getRuter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getRute method, of class Brett.
     */
    @Test
    public void testGetRute() {
        System.out.println("getRute");
        int x = 0;
        int y = 0;
        Brett instance = new Brett();
        Rute expResult = null;
        Rute result = instance.getRute(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSvartMoves method, of class Brett.
     */
    @Test
    public void testGetSvartMoves() {
        System.out.println("getSvartMoves");
        Brett instance = new Brett();
        ArrayList expResult = null;
        ArrayList result = instance.getSvartMoves();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHvitMoves method, of class Brett.
     */
    @Test
    public void testGetHvitMoves() {
        System.out.println("getHvitMoves");
        Brett instance = new Brett();
        ArrayList expResult = null;
        ArrayList result = instance.getHvitMoves();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        Rute rute = null;
        Brett instance = new Brett();
        ArrayList expResult = null;
        ArrayList result = instance.sjekkLovligeTrekk(rute);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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

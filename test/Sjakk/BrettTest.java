/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sjakk;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
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
        fail("The test case is a prototype.");
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
    }

    /**
     * Test of flyttBrikke method, of class Brett.
     */
    @Test
    public void testFlyttBrikke() {
        System.out.println("flyttBrikke");
        Brett brett = new Brett();
        Rute r1 = new Rute(3, 1);
        Rute r2 = new Rute(3, 3);
        brett.flyttBrikke(brett.getRute(r1.getX(), r1.getY()), brett.getRute(r2.getX(), r2.getY()));
        assertTrue(brett.getRute(r2.getX(), r2.getY()).isOccupied() && brett.getRute(r2.getX(), r2.getY()).getBrikke() instanceof Bonde && brett.getRute(r2.getX(), r2.getY()).getBrikke().isHvit());
        r1 = new Rute(6, 7);
        r2 = new Rute(7, 5);
        brett.flyttBrikke(r1, r2);
        assertTrue(brett.getRute(r2.getX(), r2.getY()).isOccupied() && brett.getRute(r2.getX(), r2.getY()).getBrikke() instanceof Springer && !brett.getRute(r2.getX(), r2.getY()).getBrikke().isHvit());
        r1 = new Rute(2, 0);
        r2 = new Rute(7, 5);
        brett.flyttBrikke(r1, r2);
        assertTrue(brett.getRute(r2.getX(), r2.getY()).isOccupied() && brett.getRute(r2.getX(), r2.getY()).getBrikke() instanceof Loper && brett.getRute(r2.getX(), r2.getY()).getBrikke().isHvit());
        
    }

    /**
     * Test of registrateMove method, of class Brett.
     */
    @Test
    public void testRegistrateMove() {
        System.out.println("registrateMove");
        String move = "";
        String move2 = "";
        boolean s = false;
        Brett instance = new Brett();
        instance.registrateMove(move, move2, s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSjakk method, of class Brett.
     */
    @Test
    public void testIsSjakk() {
        System.out.println("isSjakk");
        Boolean isWhite = null;
        Brett instance = new Brett();
        boolean expResult = false;
        boolean result = instance.isSjakk(isWhite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Sjakk.Brett;
import Sjakk.Rute;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Rino
 */
public class SpringerTest {
    
    public SpringerTest() {
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
     * Test of sjekkLovligeTrekk method, of class Springer.
     */
    @Test
    public void testSjekkLovligeTrekk() {
        System.out.println("sjekkLovligeTrekk");
        Brett instance = new Brett();
        Rute r = null;
        ArrayList expResult = null;
        ArrayList result = instance.sjekkLovligeTrekk(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
}

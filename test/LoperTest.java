/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class LoperTest {
    
    public LoperTest() {
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
     * Test of sjekkLovligeTrekk method, of class Loper.
     */
    @Test
    public void testSjekkLovligeTrekk() {
        System.out.println("sjekkLovligeTrekk");
        Loper instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.sjekkLovligeTrekk();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

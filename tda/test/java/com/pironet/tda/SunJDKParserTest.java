/*
 * SunJDKParserTest.java
 *
 * This file is part of TDA - Thread Dump Analysis Tool.
 *
 * Foobar is free software; you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with Foobar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * $Id: SunJDKParserTest.java,v 1.9 2008-11-21 09:20:19 irockel Exp $
 */
package com.pironet.tda;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import junit.framework.*;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * test parsing of log files from sun vms.
 * @author irockel
 */
public class SunJDKParserTest extends TestCase {
    
    public SunJDKParserTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(SunJDKParserTest.class);
        
        return suite;
    }

    /**
     * Test of hasMoreDumps method, of class com.pironet.tda.SunJDKParser.
     */
    public void testDumpLoad() throws FileNotFoundException, IOException {
        System.out.println("dumpLoad");
        FileInputStream fis = null;
        DumpParser instance = null;
        
        try {
            fis = new FileInputStream("test/none/test.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(fis, dumpMap, false, 0);
            
            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if three dumps are in it.
            assertEquals(3, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(fis != null) {
                fis.close();
            }
        }
    }

    /**
     * Test of isFoundClassHistograms method, of class com.pironet.tda.SunJDKParser.
     */
    public void testIsFoundClassHistograms() throws FileNotFoundException, IOException {
        System.out.println("isFoundClassHistograms");
        DumpParser instance = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("test/none/testwithhistogram.log");
            Map dumpMap = new HashMap();
            instance = DumpParserFactory.get().getDumpParserForLogfile(fis, dumpMap, false, 0);
            
            Vector topNodes = new Vector();
            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }
            
            boolean expResult = true;
            boolean result = instance.isFoundClassHistograms();
            assertEquals(expResult, result);        
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(fis != null) {
                fis.close();
            }
        }
    }
    
    public void test64BitDumpLoad() throws FileNotFoundException, IOException {
        System.out.println("64BitDumpLoad");
        FileInputStream fis = null;
        DumpParser instance = null;
        
        try {
            fis = new FileInputStream("test/none/test64bit.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(fis, dumpMap, false, 0);
            
            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if one dump was found.
            assertEquals(1, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(fis != null) {
                fis.close();
            }
        }
    }
    
    public void testSAPDumps()  throws FileNotFoundException, IOException {
        System.out.println("SAPDumpLoad");
        FileInputStream fis = null;
        DumpParser instance = null;
        
        try {
            fis = new FileInputStream("test/none/sapdump.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(fis, dumpMap, false, 0);
            
            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if two dump were found.
            assertEquals(2, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(fis != null) {
                fis.close();
            }
        }
    }
    
    public void testHPDumps()  throws FileNotFoundException, IOException {
        System.out.println("HPDumpLoad");
        FileInputStream fis = null;
        DumpParser instance = null;
        
        try {
            fis = new FileInputStream("test/none/hpdump.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(fis, dumpMap, false, 0);
            
            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if two dump were found.
            assertEquals(2, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(fis != null) {
                fis.close();
            }
        }
    }
    
    public void testRemoteVisualVMDumps()  throws FileNotFoundException, IOException {
        System.out.println("VisualVMDumpLoad");
        FileInputStream fis = null;
        DumpParser instance = null;

        try {
            fis = new FileInputStream("test/none/visualvmremote.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(fis, dumpMap, false, 0);

            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if two dump were found.
            assertEquals(1, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(fis != null) {
                fis.close();
            }
        }
    }

    public void testURLThreadNameDumps()  throws FileNotFoundException, IOException {
        System.out.println("URLThreadNameDumpLoad");
        FileInputStream fis = null;
        DumpParser instance = null;
        
        try {
            fis = new FileInputStream("test/none/urlthread.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(fis, dumpMap, false, 0);
            
            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if two dump were found.
            assertEquals(1, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(fis != null) {
                fis.close();
            }
        }
    }
    
        
    public static String[] threadHeaders =  {
        "\"AppKit Thread\" #11 daemon prio=5 os_prio=31 tid=0x00007ff5520e2000 nid=0x707 runnable [0x0000000000000000]",       
        "\"GC task thread#0 (ParallelGC)\" os_prio=31 tid=0x00007ff552006800 nid=0x2903 runnable",
        "\"Timer-0\" #17 daemon prio=6 os_prio=31 tid=0x00007ff5520e1000 nid=0xd803 in Object.wait() [0x000000012caa0000]",
        "\"AWT-Shutdown\" #12 prio=5 os_prio=31 tid=0x00007ff5520fa000 nid=0x8407 in Object.wait() [0x00000001282ac000]",
        "\"Attach Listener\" #21 daemon prio=9 os_prio=31 tid=0x00007ff55480d000 nid=0x9507 waiting on condition [0x0000000000000000]",
        "\"AWT-EventQueue-0\" #13 prio=6 os_prio=31 tid=0x00007ff551916000 nid=0xc10b waiting on condition [0x000000012ac80000]",
        "\"VM Periodic Task Thread\" os_prio=31 tid=0x00007ff552055800 nid=0x5c03 waiting on condition",
            

        "\"AppKit Thread\" daemon prio=5 tid=0x00007fb349196000 nid=0x507 runnable [0x0000000000000000]",
        "\"VM Thread\" prio=5 tid=0x00007fb3490ea800 nid=0x4d03 runnable",
        "\"Java2D Queue Flusher\" daemon prio=5 tid=0x00007fb3491a3800 nid=0xd907 in Object.wait() [0x000000012a2f5000]",
        "\"AWT-Shutdown\" prio=5 tid=0x00007fb34984d800 nid=0x8f0b in Object.wait() [0x0000000127229000]",
        "\"Attach Listener\" daemon prio=5 tid=0x00007fb34b318800 nid=0xec0f waiting on condition [0x0000000000000000]",
        "\"DestroyJavaVM\" prio=5 tid=0x00007fb34a08d800 nid=0x1903 waiting on condition [0x0000000000000000]",
        "\"VM Periodic Task Thread\" prio=5 tid=0x00007fb349107800 nid=0x6f03 waiting on condition"
    };
    
        
    public static String[] threadNames =  {
        "AppKit Thread",
        "GC task thread#0 (ParallelGC)",
        "Timer-0",
        "AWT-Shutdown",
        "Attach Listener",
        "AWT-EventQueue-0",
        "VM Periodic Task Thread",           
        "AppKit Thread",
        "VM Thread",
        "Java2D Queue Flusher",
        "AWT-Shutdown",
        "Attach Listener",
        "DestroyJavaVM",
        "VM Periodic Task Thread"
    };
            
    public void testForThreadNames() {                         
        for ( int i = 0; i < threadHeaders.length; i++) {
            Matcher threadName = SunJDKParser.THREAD_NAME.matcher( threadHeaders[ i]);
            assertTrue( threadName.find());
            assertTrue( threadName.group( 1).equals( threadNames[ i]));            
        }        
    }
    
    public static String[][] threadDetails = {
        
        {  "daemon",  "5", "00007ff5520e2000",  "707",             "runnable", "[0x0000000000000000]" },
        {      null, null, "00007ff552006800", "2903",             "runnable",                   null },
        { "daemon",   "6", "00007ff5520e1000", "d803",     "in Object.wait()", "[0x000000012caa0000]" },
        {     null,   "5", "00007ff5520fa000", "8407",     "in Object.wait()", "[0x00000001282ac000]" },
        { "daemon",   "9", "00007ff55480d000", "9507", "waiting on condition", "[0x0000000000000000]" },
        {     null,   "6", "00007ff551916000", "c10b", "waiting on condition", "[0x000000012ac80000]" },
        {     null,  "31", "00007ff552055800", "5c03", "waiting on condition",                   null },

        { "daemon",   "5", "00007fb349196000",  "507",             "runnable", "[0x0000000000000000]" },
        {     null,   "5", "00007fb3490ea800", "4d03",             "runnable",                   null },
        { "daemon",   "5", "00007fb3491a3800", "d907",     "in Object.wait()", "[0x000000012a2f5000]" },
        {     null,   "5", "00007fb34984d800", "8f0b",     "in Object.wait()", "[0x0000000127229000]" },
        { "daemon",   "5", "00007fb34b318800", "ec0f", "waiting on condition", "[0x0000000000000000]" },
        {     null,   "5", "00007fb34a08d800", "1903", "waiting on condition", "[0x0000000000000000]" },
        {     null,   "5", "00007fb349107800", "6f03", "waiting on condition",                   null }
    };
    
        
    public void testForThreadAttributes() {        
        for ( int i = 0; i < threadHeaders.length; i++) {
            Matcher threadAttributes = SunJDKParser.THREAD_ATTRIBUTES.matcher( threadHeaders[ i]);
            assertTrue( threadAttributes.find());
                for ( int j = 0; j < threadDetails[ i].length; j++) {
                    if ( threadDetails[ i][ j] == null)
                        assertTrue( threadDetails[ i][ j] == threadAttributes.group( j + 1));
                    else
                        assertTrue( threadDetails[ i][j].equals(threadAttributes.group( j +1)));
                }
        }        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pironet.tda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kirk
 */
public class Sandbox {
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
    
    public static void main(String[] args) {
        threadNames();
        otherStuff();        
    }
    
    public static void otherStuff() {
        
        Pattern threadCharacteristicsPattern = Pattern.compile( "(daemon)?( prio=(\\d+))?( os_prio=(\\d+))?( tid=0x(\\p{XDigit}+))( nid=0x(\\p{XDigit}+))( [ \\(\\)a-zA-Z[^\\[]]+)?(?:\\[0x(\\p{XDigit}+))?");// 0x(\\p{XDigit}+)
        System.out.println("Matching " + threadHeaders.length + " headers");
        for ( int i = 0; i < threadHeaders.length; i++) {
            Matcher threadCharacteristics = threadCharacteristicsPattern.matcher( threadHeaders[ i]);
            if ( threadCharacteristics.find()) {
                System.out.println( threadCharacteristics.group(0));
                for ( int j = 1; j <= threadCharacteristics.groupCount(); j++) {
                    System.out.println((i + 1) + " thread characteristics: " + threadCharacteristics.group( j));
                }
            } else {
                System.out.println((i + 1) + " failed to find thread characteristics in " + threadHeaders[ i]);
            }            
        }        
    }
    
        
    private static void threadNames() {                           
        System.out.println("Matching " + threadHeaders.length + " headers");
        for ( int i = 0; i < threadHeaders.length; i++) {
            Matcher threadName = threadNamePattern.matcher( threadHeaders[ i]);
            if ( threadName.find()) {
                System.out.println((i + 1) + " thread name: " + threadName.group(0));                
            } else {
                System.out.println((i + 1) + " failed to find thread name.");
            }            
        }        
    }
            
    private static final Pattern threadNamePattern = Pattern.compile("\"(.+)\"");
    private static final Pattern threadPriority = Pattern.compile( " prio=(\\d+)");
    private static final Pattern osThreadPriority = Pattern.compile( " os_prio=(\\d+)");
    private static final Pattern threadID = Pattern.compile( " tid=0x(\\p{XDigit}+)");
    private static final Pattern nativeThreadID = Pattern.compile( " nid=0x(\\p{XDigit}+)");
    private static final Pattern threadState = Pattern.compile( " ");
    private static final Pattern addressRange = Pattern.compile( " \\[0x(\\p{XDigit}+)\\]");
    
    public static boolean isDaemonThread( String threadDumpHeader) {
        return threadDumpHeader.contains( "daemon");
    }
    
    public static String threadPriority( String threadDumpHeader) {                
        Matcher matcher = threadPriority.matcher( threadDumpHeader);
        if ( matcher.find()) {
            return matcher.group( 1);
        }        
        return "no-thread-priority";
    }
    
        
    public static String osThreadPriority( String threadDumpHeader) {              
        Matcher matcher = osThreadPriority.matcher( threadDumpHeader);
        if ( matcher.find()) {
            return matcher.group( 1);
        }        
        return "no-os-thread-priority";
    }
    
    public static String threadID( String threadDumpHeader) {              
        Matcher matcher = threadID.matcher( threadDumpHeader);
        if ( matcher.find()) {
            return matcher.group( 1);
        }        
        return "no-thread-ID";
    }
    
    public static String nativeThreadID( String threadDumpHeader) {              
        Matcher matcher = nativeThreadID.matcher( threadDumpHeader);
        if ( matcher.find()) {
            return matcher.group( 1);
        }        
        return "no-thread-ID";
    }
    
    public static String threadState( String threadDumpHeader) {
        Matcher matcher = threadState.matcher( threadDumpHeader);
        if ( matcher.find()) {
            return matcher.group( 1);
        }        
        return "no-thread-state";
    }
    
    public static String addressRange( String threadDumpHeader) {
        Matcher matcher = addressRange.matcher( threadDumpHeader);
        if ( matcher.find()) {
            return matcher.group( 1);
        }        
        return "no-address-range";
    }
}

package com.mxb.inventory;

import org.sikuli.basics.Settings;   // SikuliX, Java

// import org.sikuli.script.App;
import org.sikuli.script.Screen;
import org.sikuli.script.Pattern;
import org.sikuli.script.Key;

import org.sikuli.script.Region;
// import org.sikuli.script.Match;

import org.sikuli.script.FindFailed;

// ---------------------------------
// import org.sikuli.script.Finder;
// import org.sikuli.script.ImagePath;

// ---------------------------------
// import java.net.URISyntaxException;
// import java.net.URL;
// import java.net.MalformedURLException;

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.IOException;

// import java.util.*;
// import java.util.List;
// import java.util.ArrayList;
// import java.util.Iterator;

// ---------------------------------

/**
 * Hello world!
 *
 */

public class Inventory_01_port
{

    // private App myApp;

    private Screen scr;
    private String basePath;

    private String equipment;
    private String slot;
    private String description;

    private Region r1Top_GenID;
    private Region r2Bottom;


    //constructor to initialize the variable
    // https://tom.mcqueeney.tech/blog/the-terribly-misunderstood-super/
    public Inventory_01_port()
    {
        // General Settings and Access to Environment Information
        // https://sikulix-2014.readthedocs.io/en/latest/globals.html
        // Settings.AutoWaitTimeout = 2;  // (default 3 seconds)

        Settings.AutoDetectKeyboardLayout = false;
        scr = new Screen(0);
        scr.setAutoWaitTimeout(120);

        r1Top_GenID = new Region(0,0,1920,180);
        r2Bottom = new Region(0,939,1920,141);

        // https://www.baeldung.com/java-current-directory
        // Get the Current Working Directory in Java | Baeldung
        String currentWorkingDir = System.getProperty("user.dir");
        basePath = currentWorkingDir + "/images/";

    }


    //instance method
    private void startApp() throws FindFailed
    {
        m1MaximizeApp();
        m2OpenSlot();
        m3OpenPort();
    }


    //instance method
    private void m1MaximizeApp() throws FindFailed
    {
        // Java 1.8
        // By default, float numbers are double in java.
        // In order to store them into float variable, you need to explicitly add the suffix 'f' or 'F'.
        // scr.wait(p3Search_For_Path.similar(0.7F));

        try {
            // INV app, Click on the icon
            // Pattern p1AppIcon = new Pattern("C:\\xxx\\INV_01_ikona.png");
            Pattern p1AppIcon = new Pattern(basePath + "p1AppIkona.png");
            r2Bottom.click(p1AppIcon.similar(0.8));

            // myApp = new App("Inventory");
            // myApp.focus();

            // INV app, Wait for it to open
            Pattern p2AppWait = new Pattern(basePath + "p2AppWait.png");
            r1Top_GenID.wait(p2AppWait.similar(0.8));

        } catch(FindFailed e) {
            e.printStackTrace();
            System.out.println("Find failed " + e.getMessage());
            System.exit(1);
        }

    }


    //instance method
    private void m2OpenSlot() throws FindFailed
    {

        try {

            // Čekaj da se otvori INV aplikacija
            Pattern p2AppWait = new Pattern(basePath + "p2AppWait.png");
            r1Top_GenID.wait(p2AppWait.similar(0.8));

            // INV: Equipment - Slot/Card
            scr.type("o", Key.ALT);  // Menu - Object
            scr.wait(0.1);   // wait 0.1 seconds
            scr.type("e"); // Equipment
            scr.wait(0.1);
            scr.type("a"); // Slot/Card
            scr.wait(0.1);

            // INV - Equipment - Slot/Card, Wait on
            Pattern p3EquipSlotSearch = new Pattern(basePath + "p3EquipSlotSearch.png");
            scr.wait(p3EquipSlotSearch.similar(0.7));

// ---------------------------------
            // Pattern p4SlotClearAll = new Pattern(basePath + "p4SlotClearAll.png");
            // final Match match = scr.exists(p4SlotClearAll.similar(0.95), 3);

            // if(match != null)
            // {
            //     match.highlight(2);
            //     scr.type(Key.F5);   // Clear All
            //     scr.waitVanish(p4SlotClearAll.similar(0.95));
            // }

// -----------------------------------
            // Slot - type
            scr.click(p3EquipSlotSearch.targetOffset(100, 42));
            // s.wait(0.5);
            scr.wait(0.1);

            scr.type("a", Key.CTRL);
            scr.wait(0.1);
            scr.type(Key.DELETE);
            scr.wait(0.1);

            scr.paste(slot);
            // s.wait(0.5);
            scr.wait(0.1);

// -----------------------------------
            // Equipment ID
            scr.hover(p3EquipSlotSearch.targetOffset(100, 108));
            scr.click(p3EquipSlotSearch.targetOffset(100, 108));
            scr.wait(0.1);   // wait 0.1 seconds

            scr.type("a", Key.CTRL);
            scr.wait(0.1);   // wait 0.1 seconds
            scr.type(Key.DELETE);
            scr.wait(0.1);

            scr.paste(equipment);
            // s.wait(0.5);
            scr.wait(0.1);
            // scr.waitVanish(p3EquipSlotSearch.similar(0.7));

// -----------------------------------


            // INV: binoculars (find a slot!)
            // s.type("f", Key.CTRL);  // Begin Search
            Pattern p5Dvogled = new Pattern(basePath + "p5Dvogled.png");
            r1Top_GenID.click(p5Dvogled.similar(0.85));
            scr.wait(0.1);

            // INV: open the slot
            Pattern p6EquipSlotPronajden = new Pattern(basePath + "p6EquipSlotPronajden.png");
            scr.wait(p6EquipSlotPronajden.similar(0.7));
            scr.hover(p6EquipSlotPronajden.similar(0.7).targetOffset(100, 50));
            scr.doubleClick(p6EquipSlotPronajden.similar(0.7).targetOffset(100, 50));


        } catch(FindFailed e) {
            // e.printStackTrace();
            System.out.println("Find failed " + e.getMessage());

        } catch(Exception e) {
            // e.printStackTrace();
            System.out.println("Some other exception occured " + e.getMessage());

        }


    }


    //instance method
    private void m3OpenPort() throws FindFailed
    {

        try {

            // INV: sPort
            Pattern p7Port_opened = new Pattern(basePath + "p7PortOtvoren.png");
            scr.wait(p7Port_opened.similar(0.7));
            scr.type("m", Key.CTRL);  // Modify

            scr.setAutoWaitTimeout(3);
            // Settings.AutoWaitTimeout = 3;  // (default 3 seconds)

            Pattern p8PortModify = new Pattern(basePath + "p8PortModify.png");
            scr.wait(p8PortModify.similar(0.7));

            // INV: Port Status
            scr.click(p8PortModify.targetOffset(170, 40));
            scr.wait(0.5);    // wait 0.5 seconds
            // s.wait(0.1);
            scr.type(Key.HOME);
            scr.wait(0.5);
            scr.type("r"); // Rezerviran (Reserved)
            scr.wait(0.5);
            scr.type(Key.ESC);
            // s.wait(0.1);
            scr.wait(0.5);

            // INV: Port, odaberi Suplemental info
            scr.click(p7Port_opened.targetOffset(-180, 600));
            scr.wait(0.1);
            scr.type(Key.HOME);
            // s.type("s");
            scr.wait(0.5);


            // INV: Port Desciption
            Pattern p9SuplementalInfo = new Pattern(basePath + "p9SuplementalInfo.png");
            scr.wait(p9SuplementalInfo.similar(0.7));
            // scr.click(p9SuplementalInfo.targetOffset(350, 125));
            scr.wait(0.1); // čekaj 0.1 sekundi
            scr.click(p9SuplementalInfo.targetOffset(190,112));
            scr.wait(0.1); // čekaj 0.1 sekundi

            scr.type("a", Key.CTRL);  // Select all
            scr.wait(0.1);
            scr.type(Key.DELETE);
            scr.wait(0.1);
            scr.paste(description);
            scr.wait(0.1);
            // s.type("hello!");

            // scr.wait(15.5);


        } catch(FindFailed e) {
            e.printStackTrace();

        }

	}


    //this is a static method and cannot call an instance method without a object
    public static void main(String[] args) throws FindFailed
    {
        System.out.println( "Hello World!" );

        //create instance of object
        Inventory_01_port portPortObj = new Inventory_01_port();

        // Windows bat file:
        // set EQUIPMENT=%1
        // set SLOT=%2
        // set DESCRIPTION=%3

        portPortObj.equipment = args[0].toString();
        portPortObj.slot = args[1].toString();
        portPortObj.description = args[2].toString();

        portPortObj.startApp();

    }


}


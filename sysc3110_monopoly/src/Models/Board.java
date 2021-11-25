package Models;

import java.util.HashMap;

/**
 * Author: Dana El Sherif Date: 2021-10-18
 */

public class Board {

    static final Property mayfair = new Property("MAYFAIR", 39, "Indigo", 400, false, "none");
    static final Property tax2 = new Property("SUPER TAX", 38, "None", 100, false, "none");
    static final Property park = new Property("Park Lane", 37, "Indigo", 350, false, "none");
    static final Property chance3 = new Property("CHANCE", 36, "None", 0, false, "none");
    static final Property station4 = new Property("LIVERPOOL ST. STATION", 35, "None", 200, false, "none");
    static final Property bond = new Property("BOND STREET", 34, "Green", 320, false, "none");
    static final Property chest3 = new Property("COMMUNITY CHEST", 33, "None", 0, false, "none");
    static final Property oxford = new Property("OXFORD STREET", 32, "Green", 300, false, "none");
    static final Property regent = new Property("REGENT STREET", 31, "Green", 300, false, "none");

    static final Property go_jail = new Property("GO TO JAIL", 30, "0", 0, false, "none");
    static final Property picadilly = new Property("PICADILLY", 29, "Yellow", 280, false, "none");
    static final Property water = new Property("WATER WORKS", 28, "None", 150, false, "none");
    static final Property coventry = new Property("COVENTRY STREET", 27, "Yellow", 260, false, "none");
    static final Property leicester = new Property("LEICESTER SQUARE", 26, "Yellow", 260, false, "none");
    static final Property station3 = new Property("FENCHURCH ST. STATION", 25, "None", 200, false, "none");
    static final Property trafalgar = new Property("TRAFALGAR SQUARE", 24, "Red", 240, false, "none");
    static final Property fleet = new Property("FLEET STREET", 23, "Red", 220, false, "none");
    static final Property chance2 = new Property("CHANCE", 22, "None", 220, false, "none");
    static final Property strand = new Property("STRAND", 21, "Red", 220, false, "none");

    static final Property parking = new Property("FREE PARKING", 20, "None", 0, false, "none");
    static final Property vine = new Property("VINE STREET", 19, "Orange", 200, false, "none");
    static final Property marlborough = new Property("MARLBOROUGH STREET", 18, "Orange", 180, false, "none");
    static final Property chest2 = new Property("COMMUNITY CHEST", 17, "None", 0, false, "none");
    static final Property bow = new Property("BOW STREET", 16, "Orange", 180, false, "none");
    static final Property station2 = new Property("MARYLEBONE STATION", 15, "None", 200, false, "none");
    static final Property northave = new Property("NORTHUMRL'D AVENUE", 14, "Pink", 160, false, "none");
    static final Property whitehall = new Property("WHITEHALL", 13, "Pink", 140, false, "none");
    static final Property company = new Property("ELECTRIC COMPANY", 12, "None", 150, false, "none");
    static final Property mall = new Property("PALL MALL", 11, "Pink", 140, false, "none");

    static final Property jail = new Property("JAIL/VISITING", 10, "None", 0, false, "none");
    static final Property pentonville = new Property("PENTONVILLE ROAD", 9, "Blue", 120, false, "none");
    static final Property euston = new Property("EUSTON ROAD", 8, "Blue", 100, false, "none");
    static final Property chance1 = new Property("CHANCE", 7, "None", 0, false, "none");
    static final Property angel = new Property("THE ANGEL ISLINGTON", 6, "Blue", 100, false, "none");
    static final Property station1 = new Property("KINGS CROSS STATION", 5, "None", 200, false, "none");
    static final Property tax1 = new Property("INCOME TAX", 4, "None", 200, false, "none");
    static final Property chapel = new Property("WHITECHAPEL ROAD", 3, "Brown", 60, false, "none");
    static final Property chest1 = new Property("COMMUNITY CHEST", 2, "None", 0, false, "none");
    static final Property kent = new Property("OLD KENT ROAD", 1, "Brown", 60, false, "none");
    static final Property go = new Property("GO ", 0, "None", 0, false, "none");
    
    /**
     * Turns the properties into a hashmap with the key being the
     * index on the board and the value being the property name
     * @return hashmap of properties
     */
    public static HashMap createBoard() {
        HashMap<Integer, Property> properties = new HashMap<>();
        properties.put(39, mayfair);
        properties.put(38, tax2);
        properties.put(37, park);
        properties.put(36, chance3);
        properties.put(35, station4);
        properties.put(34, bond);
        properties.put(33, chest3);
        properties.put(32, oxford);
        properties.put(31, regent);
        properties.put(30, go_jail);
        properties.put(29, picadilly);
        properties.put(28, water);
        properties.put(27, coventry);
        properties.put(26, leicester);
        properties.put(25, station3);
        properties.put(24, trafalgar);
        properties.put(23, fleet);
        properties.put(22, chance2);
        properties.put(21, strand);
        properties.put(20, parking);
        properties.put(19, vine);
        properties.put(18, marlborough);
        properties.put(17, chest2);
        properties.put(16, bow);
        properties.put(15, station2);
        properties.put(14, northave);
        properties.put(13, whitehall);
        properties.put(12, company);
        properties.put(11, mall);
        properties.put(10, jail);
        properties.put(9, pentonville);
        properties.put(8, euston);
        properties.put(7, chance1);
        properties.put(6, angel);
        properties.put(5, station1);
        properties.put(4, tax1);
        properties.put(3, chapel);
        properties.put(2, chest1);
        properties.put(1, kent);
        properties.put(0, go);

        return properties;

    }

}

package Models;

import java.util.*;

/**
 * Author: Dana El Sherif Date: 2021-10-18
 */

public class Board {

    HashMap<Integer, Property> properties;

    static final Property mayfair = new Street("MAYFAIR", 39, "Indigo", 400, null);
    static final Property tax2 = new Tax("SUPER TAX", 38, 100, null);
    static final Property park = new Street("Park Lane", 37, "Indigo", 350, null);
    static final Property chance3 = new Property("CHANCE", 36, 0, null);
    static final Property station4 = new Railroad("LIVERPOOL ST. STATION", 35, 200, null);
    static final Property bond = new Street("BOND STREET", 34, "Green", 320, null);
    static final Property chest3 = new Property("COMMUNITY CHEST", 33, 0, null);
    static final Property oxford = new Street("OXFORD STREET", 32, "Green", 300, null);
    static final Property regent = new Street("REGENT STREET", 31, "Green", 300, null);

    static final Property go_jail = new Property("GO TO JAIL", 30, 0, null);
    static final Property picadilly = new Street("PICADILLY", 29, "Yellow", 280, null);
    static final Property water = new Utility("WATER WORKS", 28, 150, null);
    static final Property coventry = new Street("COVENTRY STREET", 27, "Yellow", 260, null);
    static final Property leicester = new Street("LEICESTER SQUARE", 26, "Yellow", 260, null);
    static final Property station3 = new Railroad("FENCHURCH ST. STATION", 25, 200, null);
    static final Property trafalgar = new Street("TRAFALGAR SQUARE", 24, "Red", 240, null);
    static final Property fleet = new Street("FLEET STREET", 23, "Red", 220, null);
    static final Property chance2 = new Property("CHANCE", 22, 220, null);
        static final Property strand = new Street("STRAND", 21, "Red", 220, null);

    static final Property parking = new Property("FREE PARKING", 20, 0, null);
    static final Property vine = new Street("VINE STREET", 19, "Orange", 200, null);
    static final Property marlborough = new Street("MARLBOROUGH STREET", 18, "Orange", 180, null);
    static final Property chest2 = new Property("COMMUNITY CHEST", 17, 0, null);
    static final Property bow = new Street("BOW STREET", 16, "Orange", 180, null);
    static final Property station2 = new Railroad("MARYLEBONE STATION", 15, 200, null);
    static final Property northave = new Street("NORTHUMRL'D AVENUE", 14, "Pink", 160, null);
    static final Property whitehall = new Street("WHITEHALL", 13, "Pink", 140, null);
    static final Property company = new Utility("ELECTRIC COMPANY", 12, 150, null);
    static final Property mall = new Street("PALL MALL", 11, "Pink", 140, null);

    static final Property jail = new Property("JAIL/VISITING", 10, 0, null);
    static final Property pentonville = new Street("PENTONVILLE ROAD", 9, "Blue", 120, null);
    static final Property euston = new Street("EUSTON ROAD", 8, "Blue", 100, null);
    static final Property chance1 = new Property("CHANCE", 7, 0, null);
    static final Property angel = new Street("THE ANGEL ISLINGTON", 6, "Blue", 100, null);
    static final Property station1 = new Railroad("KINGS CROSS STATION", 5,  200, null);
    static final Property tax1 = new Tax("INCOME TAX", 4, 200, null);
    static final Property chapel = new Street("WHITECHAPEL ROAD", 3, "Brown", 60, null);
    static final Property chest1 = new Property("COMMUNITY CHEST", 2, 0, null);
    static final Property kent = new Street("OLD KENT ROAD", 1, "Brown", 60, null);
    static final Property go = new Property("GO ", 0, 0, null);


    public Board() {
        properties = new HashMap<>();
        this.createBoard();
    }

    /**
     * Turns the properties into a hashmap with the key being the
     * index on the board and the value being the property name
     *
     * @return hashmap of properties
     */
    public HashMap createBoard() {

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

    /**
     * Return a list of properties belonging to the argument player
     *
     * @param player
     * @return
     */
    public List<Property> getPlayerProperties(Player player) {
        List<Property> playerProperties = new ArrayList<>();

        for (Property p : this.properties.values() ) {
            if (p.getOwner() != null && p.getOwner().equals(player)) {
                playerProperties.add(p);
            }
        }

        return playerProperties;
    }

    public String printPlayerProperties(Player player) {
        return this.getPlayerProperties(player).toString();
    }

    public Player getPropertyOwner(int position) {
        return this.getProperty(position).getOwner();
    }

    public boolean setPropertyOwner(Player player, Property property) {
        Property prop = this.properties.get(property.getPosition());

        return prop.setOwner(player);
    }

    public boolean removePropertyOwner(Player player) {
        Collection<Property> properties = this.properties.values();

        for (Property p : this.properties.values()) {
            if (p.getOwner().equals(player)) {
                p.removeOwner(player);
            }
        }

        return true;
    }

    public boolean addHouse(Property property) {
        if (property instanceof Street) {
            ((Street) property).addHouse();
            return true;
        }
        return false;
    }

    public boolean addHotel(Property property) {
        if (property instanceof Street) {
            ((Street) property).addHotel();
            return true;
        }
        return false;
    }

    public int getMaxPosition() {
        Set<Integer> positions = this.properties.keySet();

        return Collections.max(positions);
    }

    public Property getProperty(int position) {
        return this.properties.get(position);
    }

    public void setProperty(int position, Property property) {
        this.properties.put(position, property);
    }

    public int getGoJailPosition() {
        return 30;
    }

    public int getGoPosition() {
        return 0;
    }



}

package game.engine.dataloader;

import java.io.IOException;
import java.util.HashMap;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.WeaponRegistry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
public class DataLoader {

  private static final String TITANS_FILE_NAME = "titans.csv";
  private static final String WEAPONS_FILE_NAME = "weapons.csv";

  public static HashMap < Integer, TitanRegistry > readTitanRegistry() throws IOException {
    //Initializing Required Data Structures
    HashMap < Integer, TitanRegistry > allTitanRegistryData = new HashMap < Integer, TitanRegistry > ();
    FileReader titanFileReader = new FileReader(TITANS_FILE_NAME);
    BufferedReader titanBufferedReader = new BufferedReader(titanFileReader);
    ArrayList < String > titanList = new ArrayList < String > ();
    String line;
      
    //Reading lines from CSV using BufferedReader and adding them to a 
    while ((line = titanBufferedReader.readLine()) != null && !line.isEmpty()) {
      titanList.add(line);
    }
    //Closing BufferedReader after all lines are read
    titanBufferedReader.close();

    int code = 0;
    int baseHealth = 0;
    int baseDamage = 0;
    int heightInMeters = 0;
    int speed = 0;
    int resourcesValue = 0;
    int dangerLevel = 0;
    String currentString = "";
    int currentNumber = 0;

    //Mapping values in titanList Array to their adjacent variables in TitanRegistry
    for (int i = 0; i < titanList.size(); i++) {
      currentString = titanList.get(i);
      //Stack Overflow Conversion for a comma-separated String to List
      List < String > allData = Arrays.asList(currentString.split("\\s*,\\s*"));
      for (int j = 0; j < allData.size(); j++) {
        currentNumber = Integer.parseInt(allData.get(j));
        switch (j) {
        case 0:
          code = currentNumber;
          break;
        case 1:
          baseHealth = currentNumber;
          break;
        case 2:
          baseDamage = currentNumber;
          break;
        case 3:
          heightInMeters = currentNumber;
          break;
        case 4:
          speed = currentNumber;
          break;
        case 5:
          resourcesValue = currentNumber;
          break;
        case 6:
          dangerLevel = currentNumber;
          break;
        }
      }

      /* System.out.println("code: " + code + "baseHealth: " + baseHealth + "Base Damage: " + baseDamage + "heightInMeters: " +heightInMeters + " speed: " + speed
					 +"resourcesValue: " + resourcesValue + " dangerLevel: " + dangerLevel ); */

      TitanRegistry currentTitanRegistry = new TitanRegistry(code, baseHealth, baseDamage, heightInMeters, speed, resourcesValue, dangerLevel);
      allTitanRegistryData.put(code, currentTitanRegistry);
    }
    //System.out.println(allTitanRegistryData.get(1).getBaseDamage());
    return allTitanRegistryData;
  }

  public static HashMap < Integer, WeaponRegistry > readWeaponRegistry() throws IOException {
    HashMap < Integer, WeaponRegistry > allWeaponRegistryData = new HashMap < Integer, WeaponRegistry > ();
    FileReader weaponsFileReader = new FileReader(WEAPONS_FILE_NAME);
    BufferedReader weaponsBufferedReader = new BufferedReader(weaponsFileReader);
    ArrayList < String > weaponsList = new ArrayList < String > ();
    String line;
    while ((line = weaponsBufferedReader.readLine()) != null && !line.isEmpty()) {
      weaponsList.add(line);
    }
    weaponsBufferedReader.close();

    int code = 0;
    int price = 0;
    int damage = 0;
    String name = "";
    int minRange = 0;
    int maxRange = 0;

    String currentString = "";
    String currentName = "";
    int currentNumber = 0;

    for (int i = 0; i < weaponsList.size(); i++) {
      minRange = 0;
      maxRange = 0;
      currentString = weaponsList.get(i);

      //Stack Overflow Conversion for a comma-separated String to List
      List < String > allData = Arrays.asList(currentString.split("\\s*,\\s*"));

      for (int j = 0; j < allData.size(); j++) {
        if (j == 3) {
          currentName = allData.get(j);
          name = currentName;
        } else {
          currentNumber = Integer.parseInt(allData.get(j));
          switch (j) {
          case 0:
            code = currentNumber;
            break;
          case 1:
            price = currentNumber;
            break;
          case 2:
            damage = currentNumber;
            break;
          case 4:
            minRange = currentNumber;
            break;
          case 5:
            maxRange = currentNumber;
            break;
          }
        }
      }
      if (allData.size() == 4) {
        WeaponRegistry currentWeaponRegistry = new WeaponRegistry(code, price, damage, name);
        allWeaponRegistryData.put(code, currentWeaponRegistry);
        //System.out.println("Code: " + code + " Price: "+ price + " Damage: " + damage + " Name: " + name);
      }
      if (allData.size() == 6) {
        WeaponRegistry currentWeaponRegistry = new WeaponRegistry(code, price, damage, name, minRange, maxRange);
        allWeaponRegistryData.put(code, currentWeaponRegistry);
        //System.out.println("Code: " + code + " Price: "+ price + " Damage: " + damage + " Name: " + name + " Min Range: " + minRange + " Max Range: " + maxRange);
      }

    }
    return allWeaponRegistryData;
  }
  /*
  public static void main(String[] args) throws Exception {
    System.out.println("WEAPONS DATA: ");
    HashMap < Integer, WeaponRegistry > t = readWeaponRegistry();
    for (int i = 1; i < t.size()+1; i++) {
      WeaponRegistry wp = t.get(i);
      System.out.println("Code: " + wp.getCode() + " Price: " + wp.getPrice() + " Damage: " + wp.getDamage() + " Name: " + wp.getName() + " Min Range: " + wp.getMinRange() + " Max Range: " + wp.getMaxRange());
    }
    System.out.println("");
    System.out.println("TITANS DATA: ");
    HashMap < Integer, TitanRegistry > t1 = readTitanRegistry();
    for (int j = 1; j < t1.size()+1; j++) {
      TitanRegistry tr = t1.get(j);
      System.out.println("code: " + tr.getCode() + " baseHealth: " + tr.getBaseHealth() + " Base Damage: " + tr.getBaseDamage() + " heightInMeters: " + tr.getHeightInMeters() + " speed: " + tr.getSpeed() +
        " resourcesValue: " + tr.getResourcesValue() + " dangerLevel: " + tr.getDangerLevel());
    
  
  }
  }*/
}
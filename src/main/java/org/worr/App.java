package org.worr;

import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static HashMap<String, String> romanValues = new HashMap<>();


    public static void main(String[] args) {
        romanValues.put("1small", "I");
        romanValues.put("1big", "V");
        romanValues.put("10small", "X");
        romanValues.put("10big", "L");
        romanValues.put("100small", "C");
        romanValues.put("100big", "D");
        romanValues.put("1000small", "M");
        //todo kolla hur man skriver bokstäver med en linje över (t.ex linje över V = 5000, linje över X = 10000)

        int tal;
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Skriv ett heltal (1-3999): ");
            tal = sc.nextInt();
            if(tal > 0 && tal < 4000) {
                System.out.print("Romerska siffror: ");
                convertDecimaltoRoman(tal);
                System.out.println("\n");
            } else if (tal > 4000) {
                System.out.println("Talet är för stort.");
            } else {
                System.out.println("Talet är för litet.");
            }

        }
    }

    public static void convertDecimaltoRoman(int decimalNumeral) {
        //räknar ut om talets största beståndsdel är till exempel 1000-tal, 100-tal eller 10-tal.
        int highestBaseTen = (int) Math.pow(10.0, (double) Integer.toString(decimalNumeral).length() - 1); //100
        recursiveDecimaltoRoman(decimalNumeral, highestBaseTen);
    }

    public static void recursiveDecimaltoRoman(int decimalNumeral, int highestTenBase) {
        //beräknar bara till ental, stannar vid tiondelar
        if (highestTenBase < 1) {
            return;
        }

        int numValueCurrentPos = decimalNumeral / highestTenBase;
        //om 0 printa inget
        if (numValueCurrentPos >= 1 && numValueCurrentPos <= 3) {               //om 1 till 3 printa X gånger från small
            for (int i = 0; i < numValueCurrentPos; i++) {
                System.out.print(romanValues.get(highestTenBase + "small"));
            }
        } else if (numValueCurrentPos == 4) {                                   //om 4 printa 1 gång SMALL, 1 gång BIG
            System.out.println(romanValues.get(highestTenBase + "small") + romanValues.get(highestTenBase + "big"));
        } else if (numValueCurrentPos == 5) {                                   //om 5 printa 1 gång BIG
            System.out.print(romanValues.get(highestTenBase + "big"));
        } else if (numValueCurrentPos >= 6 && numValueCurrentPos <= 8) {        //om 6-8 printa  1 gång BIG, X-5 gånger från SMALL
            System.out.print(romanValues.get(highestTenBase + "big"));
            for (int i = 0; i < numValueCurrentPos-5; i++) {
                System.out.print(romanValues.get(highestTenBase + "small"));
            }
        } else if(numValueCurrentPos == 9) {                                    //om 9 printa 1 gång SMALL, 1 gång nästkommande big
            System.out.print(romanValues.get(highestTenBase + "small"));
            System.out.print(romanValues.get((10 * highestTenBase) + "small"));
        }

        //nästa beräkning
        decimalNumeral = decimalNumeral - highestTenBase * (decimalNumeral / highestTenBase);
        highestTenBase = highestTenBase / 10;

        recursiveDecimaltoRoman(decimalNumeral, highestTenBase);
    }
}

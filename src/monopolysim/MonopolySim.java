package monopolysim;
import java.util.Random;

public class MonopolySim {
    
    public static void main(String[] args) {
        Random rand = new Random();
        
        boolean realistic = true; // enable jail, backwards movement, etc.
        int loops = 1000000000; // repetitions
        int current = 0; // current spot on the gameboard
        int[] landingCount = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};
        String[] gameboard = {"go", "mediterranean", "community", "baltic", "income", "readingrr", "oriental", "chance", "vermont", "connecticut", "jail", "charles", "electric", "states", "virginia", "pennsylvaniarr", "james", "community", "tennessee", "newyork", "parking", "kentucky", "chance", "indiana", "illinois", "bnorr", "atlantic", "ventnor", "water", "marvin", "gotojail", "pacific", "northcarolina", "community", "pennsylvania", "shortrr", "chance", "park", "luxury", "boardwalk"};
        
        for (int i = 0; i <= loops; i++) {  // game loop
            
            // roll two "dice" to simulate normal die odds
            int rolla = rand.nextInt(6) + 1;
            int rollb = rand.nextInt(6) + 1;
            current += rolla + rollb;  // move those spaces
            if (current >= 40) {  // pass go and compensate
                current -= 40;
            }
            
            landingCount[current] += 1;
            //System.out.println(current + " " + gameboard[current]);
            
            
            
            if (realistic) {
                if (current == 30) {  // go to jail
                    current = 10;
                    landingCount[current] += 1;
                }
                
                
                // chance cards
                else if ("chance".equals(gameboard[current])) {
                    int chanceroll = rand.nextInt(15) + 1;
                    if (chanceroll == 1) {  // advance to go
                        current = 0;
                        landingCount[current] += 1;
                    }
                    else if (chanceroll == 2) {  // advance to illinois
                        current = 24;
                        landingCount[current] += 1;
                    }
                    else if (chanceroll == 3) {  // advance to st charles
                        current = 11;
                        landingCount[current] += 1;
                    }
                    else if (chanceroll == 4) {  // advance to nearest utility
                        if (current == 7 || current == 36) {
                            current = 12;
                        }
                        else if (current == 22) {
                            current = 28;
                        }
                    landingCount[current] += 1;
                    }
                    else if (chanceroll == 5) {  // advance to nearest railroad
                        if (current == 7) {
                            current = 15;
                            landingCount[current] += 1;
                        }
                        else if (current == 22) {
                            current = 25;
                            landingCount[current] += 1;
                        }
                        else if (current == 36) {
                            current = 5;
                            landingCount[current] += 1;
                        }
                    }
                    else if (chanceroll == 6) {  // go back 3 spaces
                        current -= 3;
                        landingCount[current] += 1;
                    }
                    else if (chanceroll == 7) {  // go to jail
                        current = 10;
                        landingCount[current] += 1;
                    }
                    else if (chanceroll == 8) {  // go to reading railroad
                        current = 5;
                        landingCount[current] += 1;
                    }
                    else if (chanceroll == 9) {  // go to boardwalk
                        current = 39;
                        landingCount[current] += 1;
                    }
                }
                
                
                // community chest cards
                else if ("community".equals(gameboard[current])) {
                    int chanceroll = rand.nextInt(17) + 1;
                    if (chanceroll == 1) {  // advance to go
                        current = 0;
                        landingCount[current] += 1;
                    }
                    else if (chanceroll == 2) {  // go to jail
                        current = 10;
                        landingCount[current] += 1;
                    }
                }
                
                
                
                if (rolla == rollb) {  // first doubles
                    int rollc = rand.nextInt(6) + 1;
                    int rolld = rand.nextInt(6) + 1;
                    current += rollc + rolld;
                    if (current >= 40) {  // pass go and compensate
                        current -= 40;
                    }
                    landingCount[current] += 1;
                    
                    if (rollc == rolld) {  // second doubles
                        int rolle = rand.nextInt(6) + 1;
                        int rollf = rand.nextInt(6) + 1;
                        current += rolle + rollf;
                        if (current >= 40) {  // pass go and compensate
                            current -= 40;
                        }
                        landingCount[current] += 1;
                        
                        if (rolle == rollf) {  // third doubles; go to jail
                            current = 10;
                            landingCount[current] += 1;
                        }
                    }
                }
            }
            
            
            
        }
        int total = 0;
        for (int i = 0; i < 40; i++) {
            System.out.println(gameboard[i] + ": " + landingCount[i]);
            total += landingCount[i];
        }
        int average = total / 40;
        System.out.println("total: " + total);
        System.out.println("average: " + average);
    }
    
}

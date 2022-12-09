import java.io.*;
import java.util.*;

public class Database {

    private ArrayList<Field> fields = new ArrayList<Field>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Integer> dices = new ArrayList<Integer>();

    public void read(String filename) throws IOException, InvalidInputException, NoSuchElementException {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
            int numFields = sc.nextInt();

            for(int i = 0; i < numFields; i++) {
                Field field;
                String type = sc.next();
                switch (type) {
                    case "Property":
                        field = new Property(type, 0);
                        fields.add(field);
                        break;
                    case "Lucky":
                        field = new Lucky(type, sc.nextInt());
                        fields.add(field);
                        break;
                    case "Service":
                        field = new Service(type, sc.nextInt());
                        fields.add(field);
                        break;
                    default:
                        throw new InvalidInputException();
                }
            }


            int numPlayers = sc.nextInt();

            for (int i = 0; i < numPlayers; i++) {
                    Player player;
                    String playerName = sc.next();

                    switch(sc.next()) {
                        case "Greedy":
                            player = new Greedy(playerName, "Greedy");
                            players.add(player);
                            break;
                        case "Careful":
                            player = new Careful(playerName, "Careful");
                            players.add(player);
                            break;
                        case "Tactical":
                            player = new Tactical(playerName, "Tactical");
                            players.add(player);
                            break;
                        default:
                            throw new InvalidInputException();
                    }
            }

            while(sc.hasNextInt()) {
                dices.add(sc.nextInt());
            }
    } catch(IOException ex) {
        System.out.println("Wrong input file provided!");
    }
}
        

    
    public void report() {
        int playerIterator = -1;

        for(int dice : dices) {
            playerIterator++;
            int thrownDice = dice;
            if(playerIterator > players.size() - 1) { playerIterator = 0; }
            Player currentPlayer = players.get(playerIterator);
            
            if((thrownDice + currentPlayer.currentPosition) <= fields.size()) {
                currentPlayer.currentPosition = thrownDice + currentPlayer.currentPosition; 
            } else if ((thrownDice + currentPlayer.currentPosition) % fields.size() == 0) {
                currentPlayer.currentPosition = fields.size();
            } else {
                currentPlayer.currentPosition = (thrownDice + currentPlayer.currentPosition) % fields.size(); 
            }

            Field currentField = fields.get(currentPlayer.currentPosition - 1);
            String playerStrategy = currentPlayer.getPlayerType();

            switch(currentField.getFieldType()) {
                case "Property":
                    if(currentField.getFieldOwner() == null) {

                        switch(playerStrategy) {
                            case "Greedy":
                                if(currentPlayer.getMoney() >= currentField.fieldPrice) {
                                    currentPlayer.buyProperty();
                                    currentField.setFieldOwner(currentPlayer);
                                    currentPlayer.setOwnedProperty();
                                } else {
                                    currentField.setFieldOwner(null);
                                    players.remove(currentPlayer);
                                }
                            case "Careful":
                                if(currentPlayer.getMoney() / 2 >= currentField.fieldPrice) {
                                    currentPlayer.buyProperty();
                                    currentField.setFieldOwner(currentPlayer);
                                    currentPlayer.setOwnedProperty();
                                } else {
                                    continue;
                                }
                            case "Tactical":
                                if(currentPlayer.getTimesOfPurchase() % 2 == 1) {
                                    if(currentPlayer.getMoney() >= currentField.fieldPrice) {
                                        currentPlayer.buyProperty();
                                        currentField.setFieldOwner(currentPlayer);
                                        currentPlayer.setOwnedProperty();
                                        currentPlayer.setTimesOfPurchase();
                                    } else {
                                        currentField.setFieldOwner(null);
                                        players.remove(currentPlayer);
                                    }
                                } else {
                                    continue;
                                }
                        }

                    } else {
                        switch(playerStrategy) {
                            case "Greedy":
                                if(currentField.getFieldOwner() == currentPlayer && !currentField.isHasHouse()) {
                                    if(currentPlayer.getMoney() >= 4000) {
                                        currentPlayer.buildHouse();
                                        currentField.setHasHouse();
                                        currentPlayer.setHouse();
                                    } else {
                                        currentField.setFieldOwner(null);
                                        players.remove(currentPlayer);
                                    }
                                } else if (currentField.getFieldOwner() == currentPlayer && currentField.isHasHouse()) {
                                    continue;
                                } else if (currentField.getFieldOwner() != currentPlayer && !currentField.isHasHouse()) {
                                    if(currentPlayer.getMoney() >= 500) {
                                        currentPlayer.stepWithoutOwner();
                                        currentField.getFieldOwner().setMoney(500);
                                    } else {
                                        currentField.setFieldOwner(null);
                                        players.remove(currentPlayer);
                                    }
                                } else {
                                    if(currentPlayer.getMoney() >= 2000) {
                                        currentPlayer.stepWithOwner();
                                        currentField.getFieldOwner().setMoney(2000);
                                    } else {
                                        currentField.setFieldOwner(null);
                                        players.remove(currentPlayer);
                                    }
                                }
                            
                            case "Careful":
                                if(currentField.getFieldOwner() == currentPlayer && !currentField.isHasHouse()) {
                                    if(currentPlayer.getMoney() / 2 >= 4000) {
                                        currentPlayer.buildHouse();
                                        currentField.setHasHouse();
                                        currentPlayer.setHouse();
                                    } else {
                                        continue;
                                    }
                                } else if (currentField.getFieldOwner() == currentPlayer && currentField.isHasHouse()) {
                                    continue;
                                } else if (currentField.getFieldOwner() != currentPlayer && !currentField.isHasHouse()) {
                                    if(currentPlayer.getMoney() >= 500) {
                                        currentPlayer.stepWithoutOwner();
                                        currentField.getFieldOwner().setMoney(500);
                                    } else {
                                        currentField.setFieldOwner(null);
                                        players.remove(currentPlayer);
                                    }
                                } else {
                                    if(currentPlayer.getMoney() >= 2000) {
                                        currentPlayer.stepWithOwner();
                                        currentField.getFieldOwner().setMoney(2000);
                                    } else {
                                        currentField.setFieldOwner(null);
                                        players.remove(currentPlayer);
                                    }
                                }
                            
                            case "Tactical":
                                if(currentField.getFieldOwner() == currentPlayer && !currentField.isHasHouse()) {
                                    if(currentPlayer.getMoney() >= 4000 && currentPlayer.getTimesOfPurchase() % 2 == 1) {
                                        currentPlayer.buildHouse();
                                        currentField.setHasHouse();
                                        currentPlayer.setTimesOfPurchase();
                                        currentPlayer.setHouse();
                                    } else if( currentPlayer.getTimesOfPurchase() % 2 == 1 && currentPlayer.getMoney() < 4000) {
                                        currentField.setFieldOwner(null);
                                        players.remove(currentPlayer);
                                    } else {
                                        continue;
                                    }
                                } else if (currentField.getFieldOwner() == currentPlayer && currentField.isHasHouse()) {
                                    continue;
                                } else if (currentField.getFieldOwner() != currentPlayer && !currentField.isHasHouse()) {
                                    if(currentPlayer.getMoney() >= 500) {
                                        currentPlayer.stepWithoutOwner();
                                        currentField.getFieldOwner().setMoney(500);
                                    } else {
                                        currentField.setFieldOwner(null);
                                        players.remove(currentPlayer);
                                    }
                                } else {
                                    if(currentPlayer.getMoney() >= 2000) {
                                        currentPlayer.stepWithOwner();
                                        currentField.getFieldOwner().setMoney(2000);
                                    } else {
                                        currentField.setFieldOwner(null);
                                        players.remove(currentPlayer);
                                    }
                                }
                            }                                
                    }

                case "Service":
                    if(currentPlayer.getMoney() >= currentField.getFieldPrice()) {
                        currentPlayer.setMoney(-1 * currentField.fieldPrice);
                    } else {
                        currentField.setFieldOwner(null);
                        players.remove(currentPlayer);
                    }

                case "Lucky":
                    currentPlayer.setMoney(currentField.getFieldPrice());

            } 
        }
        
        int maxMoney = -100;
        int maxIndex = 0;

        for(int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName() + ": " + players.get(i).getMoney() + "  Owned Property: " + players.get(i).getOwnedProperty() + "  Owned House: " + players.get(i).getHouse());
            if(players.get(i).getMoney() > maxMoney) {
                maxMoney = players.get(i).getMoney(); 
                maxIndex = i;
            }
        }

        System.out.println();

        System.out.println(players.get(maxIndex).getName() + ": " + players.get(maxIndex).getMoney() + "  Owned Property: " + players.get(maxIndex).getOwnedProperty() + "  Owned House: " + players.get(maxIndex).getHouse());
    }

    public void clear() {
        fields.clear();
        players.clear();
        dices.clear();
    }

}

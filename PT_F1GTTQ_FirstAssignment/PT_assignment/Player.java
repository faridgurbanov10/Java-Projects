public abstract class Player {
    private String name;
    private int money;
    private String playerType;
    private int timesOfPurchase = 1;
    int currentPosition = 0;
    private int ownedProperty = 0;
    private int ownedHouse = 0;

    int getOwnedProperty() { return ownedProperty; }
    int getHouse() { return ownedHouse; }

    void setOwnedProperty() { ownedProperty++; }
    void setHouse() { ownedHouse++; }

    int getTimesOfPurchase() { return timesOfPurchase; }
    void setTimesOfPurchase() { timesOfPurchase++; }


    Player(String name, String playerType) { this.name = name; this.money = 10000; this.playerType = playerType; }

    int getMoney() { return money; }
    String getName() { return name; }

    void setMoney(int price) { money = money + price; }

    String getPlayerType() { return playerType; }

    void buyProperty() { setMoney(-1000); }
    void buildHouse() { setMoney(-4000); }

    void stepWithOwner() { setMoney(-2000); }
    void stepWithoutOwner() { setMoney(-500); }
}

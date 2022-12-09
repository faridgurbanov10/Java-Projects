public abstract class Field {
    private String fieldType;
    int fieldPrice;
    private Player fieldOwner;
    private boolean hasHouse;

    Field(String fieldType, int fieldPrice) { this.fieldPrice = fieldPrice; this.fieldType = fieldType;  this.hasHouse = false; }

    String getFieldType() { return fieldType; }
    int getFieldPrice() { return fieldPrice; }
    Player getFieldOwner() { return fieldOwner; }
    boolean isHasHouse() { return hasHouse; }


    void setHasHouse() { this.hasHouse = true; }
    void setFieldOwner(Player player) { fieldOwner = player; }
}
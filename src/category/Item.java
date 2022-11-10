package category;
public class Item {
    String itemName;
    String status;
    String permitStatus;
    String size;
    float mediumSizePrice;
    float largeSizePrice;
    ItemName itemName1;

    public Item( String itemName, String status, String permitStatus, String size, float mediumSizePrice, float largeSizePrice) {
        this.itemName = itemName;
        this.status = status;
        this.permitStatus = permitStatus;
        this.size = size;
        this.mediumSizePrice = mediumSizePrice;
        this.largeSizePrice = largeSizePrice;
    }

//    public Item(ItemName itemName) {
//        this.itemName1 = itemName;
//    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPermitStatus() {
        return permitStatus;
    }

    public void setPermitStatus(String permitStatus) {
        this.permitStatus = permitStatus;
    }

    public float getMediumSizePrice() {
        return mediumSizePrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getLargeSizePrice() {
        return largeSizePrice;
    }

    public void setLargeSizePrice(float largeSizePrice) {
        this.largeSizePrice = largeSizePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMediumSizePrice(float mediumSizePrice) {
        this.mediumSizePrice = mediumSizePrice;
    }
}

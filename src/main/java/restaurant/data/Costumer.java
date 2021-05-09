package restaurant.data;

public class Costumer {
    private int costumerId;
    private String email;


    public Costumer(int costumerId, String email) {
        this.costumerId = costumerId;
        this.email = email;
    }


    public int getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(int costumerId) {
        this.costumerId = costumerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

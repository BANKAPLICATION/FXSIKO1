package sample;

public class Schets {
    private int id;
    private int userid;
    private String valuta;
    private int amount;

    public Schets(int id, int userid, String valuta, int amount) {
        this.id = id;
        this.userid = userid;
        this.valuta = valuta;
      this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Schets{" +
                "id=" + id +
                ", userid=" + userid +
                ", valuta='" + valuta + '\'' +
                ", amount=" + amount +
                '}';
    }


}
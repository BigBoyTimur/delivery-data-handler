public class Client extends User{
    private int id;
    private String phone;
    private String addres;
    static int macLevel = 1;
    public Client(int id, String userName, String passwordHash, String phone, String address) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.addres = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getAddres() {
        return addres;
    }

    public String getPhone() {
        return phone;
    }
}

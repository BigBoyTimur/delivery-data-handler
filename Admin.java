public class Admin extends User{
    public Admin(int id, String userName, String passwordHash){
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.macLevel = 3;
    }
}

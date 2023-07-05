import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignUp {
    private String name;
    private String phone;
    private String address;
    private String pass;

    public SignUp(String name, String phone, String address, String pass) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.pass = pass;
    }

    public boolean checkUserName(Clients clients) {
        ArrayList<Client> clientsArray = clients.getClients();
        if (name.equals("")) return false;
        for (Client client : clientsArray) {
            if (client.userName.equals(this.name)) {
                return false;
            }
        }
        return true;
    }

    public void addClient(Clients clients) throws UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {
        int id;
        if (clients.getClients().size() > 0) id = clients.getClients().get(clients.getClients().size() -1).getId() + 1;
        else id = 1;
        Client newClient = new Client(id, name, pass, phone, address);
        System.out.println(newClient.getPasswordHash());
        clients.addClient(newClient);
        LinkController.setCurrentUser(newClient);
    }

}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PackagesCouriers {
    private ArrayList<PackageCourier> packagesCouriers;
    private static Connection connection = Link.getConnection();

    public PackagesCouriers() {
        packagesCouriers = new ArrayList<PackageCourier>();
    }

    public ArrayList<PackageCourier> getPackagesCouriers() {
        return packagesCouriers;
    }

    public void addPackageCourier(PackageCourier packageCourier) throws SQLException {
        packagesCouriers.add(packageCourier);
        String sql = ("INSERT INTO package_courier (package_id, courier_id) VALUES (?, ?)");
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, packageCourier.getPackageId());
        statement.setInt(2, packageCourier.getCourierId());

        statement.executeUpdate();

    }

    public void delete(int packageIndex) throws SQLException {
        for(int i = 0; i < packagesCouriers.size(); i++) {
            if (packagesCouriers.get(i).getPackageId() == packageIndex) {
                packagesCouriers.remove(i);
                break;
            }
        }
        String sql = ("DELETE FROM package_courier WHERE package_id = ?");
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, packageIndex);

        statement.executeUpdate();
    }
}

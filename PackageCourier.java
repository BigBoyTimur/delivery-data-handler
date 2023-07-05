public class PackageCourier {
    private int packageId;
    private int CourierId;

    public PackageCourier(int packageId, int courierId) {
        this.packageId = packageId;
        this.CourierId = courierId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getCourierId() {
        return CourierId;
    }

    public void setCourierId(int courierId) {
        CourierId = courierId;
    }
}

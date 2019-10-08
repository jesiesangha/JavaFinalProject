package Package;

import java.util.Date;

public class Package {
    private int packageId;
    private String pkgName;
    private Date pkgStartDate;
    private Date pkgEndDate;
    private String pkgDesc;
    private float pkgBasePrice;
    private float pkgAgencyCommission;
    private String tripTypeId;

    public Package(int packageId, String pkgName, Date pkgStartDate, Date pkgEndDate, String pkgDesc, float pkgBasePrice, float pkgAgencyCommission, String tripTypeId) {
        this.packageId = packageId;
        this.pkgName = pkgName;
        this.pkgStartDate = pkgStartDate;
        this.pkgEndDate = pkgEndDate;
        this.pkgDesc = pkgDesc;
        this.pkgBasePrice = pkgBasePrice;
        this.pkgAgencyCommission = pkgAgencyCommission;
        this.tripTypeId = tripTypeId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Date getPkgStartDate() {
        return pkgStartDate;
    }

    public void setPkgStartDate(Date pkgStartDate) {
        this.pkgStartDate = pkgStartDate;
    }

    public Date getPkgEndDate() {
        return pkgEndDate;
    }

    public void setPkgEndDate(Date pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return pkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    public float getPkgBasePrice() {
        return pkgBasePrice;
    }

    public void setPkgBasePrice(float pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }

    public float getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    public void setPkgAgencyCommission(float pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    public String getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    @Override
    public String toString() {
        return packageId +"";
    }
}

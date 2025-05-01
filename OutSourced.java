package mypackage.managementsystem;

/**
 * This Class inherets from Part and contains all of the OutSourced-specific class members.
 *
 * @author Jorge Ramirez
 */
public class OutSourced extends Part {
    private String companyName;
    /**
     * Creates outsourced part
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    /**
     * Sets the company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**
     * @return string of company name
     */
    public String getCompanyName() {
        return this.companyName;
    }

}

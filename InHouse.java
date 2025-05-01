package mypackage.managementsystem;

/**
 * This Class inherets from Part and contains all of the InHouse-specific class members.
 *
 * @author Jorge Ramirez
 */
public class InHouse extends Part{
    private int machineId;

    /**
     * @return create an InHouse object
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name,price,stock,min,max);
        this.machineId = machineId;
    }
    /**
     * @param machineId - the new machine id
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
    /**
     * @return the machine id
     */
    public int getMachineId(){
        return this.machineId;
    }

}

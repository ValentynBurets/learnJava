import jdk.jfr.Category;

public class Memory  extends Item{
    private int memorySize;
    private memoryType type;

    public int getMemorySize() {
        return memorySize;
    }

    public memoryType getType() {
        return type;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public void setType(memoryType type) {
        this.type = type;
    }

    public Memory()
    {
        this(100, "firm", 1000, memoryType.none, 1);
    }

    public Memory(int price,
                 String Firm,
                 int memorySize,
                 memoryType type,
                  int quantity)
    {
        this.price = price;
        this.memorySize = memorySize;
        this.type = type;
        this.Firm = Firm;
        this.quantity = quantity;
    }

    @Override
    public int hashCode(){
        return super.hashCode() + memorySize + type.hashCode();
    }

    @Override
    public String toString(){
        return type.toString()+Firm + " price - $"+ price +
                " Specifications: type of memory - " + type.toString() + ", memory size - " + memorySize + "MB\n";
    }
}

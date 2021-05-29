public class CPU extends Item{
    private int cashSize;
    private cpuType coreType;
    private int frequency;

    public void setCashSize(int cashSize) {
        this.cashSize = cashSize;
    }

    public void setCoreType(cpuType coreType) {
        this.coreType = coreType;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getCashSize() {
        return cashSize;
    }

    public int getFrequency() {
        return frequency;
    }

    public cpuType getCoreType() {
        return coreType;
    }

    public CPU()
    {
        this(cpuType.none, 100, 10, 100, itemCategory.none, 1);
    }


    public CPU(cpuType coreType,
               int frequency,
               int cashSize,
               int price,
               itemCategory cat,
               int quantity)
    {
        this.coreType = coreType;
        this.frequency = frequency;
        this.cashSize = cashSize;
        this.price = price;
        this.cat = cat;
        this.quantity = quantity;
    }


    @Override
    public int hashCode(){
        return super.hashCode() + cashSize + coreType.hashCode();
    }

    @Override
    public String toString(){
        String s = Firm + " " + Name.toString() + " price - " + price +
                " Specifications: Core - " + coreType.toString() + ", cash size - " + cashSize +
                "MB frequency - " + frequency + "Hz\n";
        return s;
    }

}

//import jdk.jfr.Category;

public class Video extends Item {
    private int memorySize;
    private VideoType graphicProcessor;

    public Video(String Name,
                 int price,
                 String Firm,
                 int memorySize,
                 VideoType graphicProcessor,
                 itemCategory cat,
                 int quantity)
    {
        this.price = price;
        this.memorySize = memorySize;
        this.graphicProcessor = graphicProcessor;
        this.Firm = Firm;
        this.Name = Name;
        this.cat = cat;
        this.quantity = quantity;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public VideoType getGraphicProcessor() {
        return graphicProcessor;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public void setGraphicProcessor(VideoType graphicProcessor) {
        this.graphicProcessor = graphicProcessor;
    }

    @Override
    public int hashCode(){
        return super.hashCode() + this.price + graphicProcessor.hashCode();
    }

    @Override
    public String toString(){
        return graphicProcessor.toString() + " " + Firm + " " + Name.toString() + " price - $"+ price + " Videocard for "+cat.toString() +
                " Specifications: graphcsProcessor - " + graphicProcessor.toString() + ", memory size - " + memorySize + " MB\n";
    }
}

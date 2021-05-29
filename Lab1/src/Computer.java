public class Computer {
    Memory memory;
    Video videoCard;
    CPU processor;

    public Computer(Memory memory,
            Video videoCard,
            CPU processor)
    {
        this.memory = memory;
        this.processor = processor;
        this.videoCard = videoCard;
    }

    public CPU getProcessor() {
        return processor;
    }

    public Memory getMemory() {
        return memory;
    }

    public Video getVideoCard() { return videoCard; }

    public void setMemory(Memory m) { this.memory = m; }

    public void setProcessor(CPU c) { this.processor = c; }

    public void setVideoCard(Video v) { this.videoCard = v; }

    @Override
    public String toString(){
        if(memory == null || processor == null || videoCard == null){
            String s = " We can`t pick up a computer for you\n";
            return s;
        }
        else{
            return " We picked up a computer for you \n technical characteristic is \n" + memory.toString() + "\n" +
                    processor.toString() + "\n" + videoCard.toString();
        }
    }
}
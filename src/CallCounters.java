import java.util.Arrays;

public class CallCounters {
    private int[] lines;
    private int links_IN_USE;
    private int clock = 1000;
    private int processed_call=0,completed_call=0,blocked_call=0,busy_call=0;

    public CallCounters() {
        lines = new int[9];
    }

    public int[] getLines() {
        return lines;
    }

    public void setLines(int[] lines) {
        System.out.println(Arrays.toString(lines));
        for(int i = 0; i<lines.length; i++){
            if(this.lines[i]==1){
                continue;
            }
            this.lines[i] = lines[i];
        }
    }

    public int getLinks_MAX_NO() {
        return 3;
    }

    public int getLinks_IN_USE() {
        return links_IN_USE;
    }

    public void setLinks_IN_USE(int links_IN_USE) {
        if(links_IN_USE==-1){
            this.links_IN_USE--;
        }else {this.links_IN_USE += links_IN_USE;}
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock += clock;
    }

    public int getProcessed_call() {
        return processed_call;
    }

    public void setProcessed_call(int processed_call) {
        this.processed_call += processed_call;
    }

    public int getCompleted_call() {
        return completed_call;
    }

    public void setCompleted_call(int completed_call) {
        this.completed_call += completed_call;
    }

    public int getBlocked_call() {
        return blocked_call;
    }

    public void setBlocked_call(int blocked_call) {
        this.blocked_call += blocked_call;
    }

    public int getBusy_call() {
        return busy_call;
    }

    public void setBusy_call(int busy_call) {
        this.busy_call += busy_call;
    }
}

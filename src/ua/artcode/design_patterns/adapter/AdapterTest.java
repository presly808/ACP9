package ua.artcode.design_patterns.adapter;

/**
 * Created by serhii on 10/2/15.
 */
public class AdapterTest {

    public static void main(String[] args) {
        Notebook notebook = new Notebook();


        AdapterVGA adapter = new AdapterVGA(new VGA());

        notebook.setHdmi(adapter);

        notebook.start();
    }

    /**
     * Created by serhii on 10/2/15.
     */

}

class AdapterVGA extends HDMI {

    private VGA vga;

    public AdapterVGA(VGA vga) {
        this.vga = vga;
    }

    @Override
    void getStream() {
        vga.process();
    }
}



class HDMI {
    void getStream(){
        System.out.println("hdmi stream");
    }
}

class VGA {

    void process(){
        System.out.println("vga stream");
    }
}

class Notebook {

    private HDMI hdmi;

    public void start(){
        hdmi.getStream();
    }

    public void setHdmi(HDMI hdmi) {
        this.hdmi = hdmi;
    }
}


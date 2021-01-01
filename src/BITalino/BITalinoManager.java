package BITalino;

import Interface.FXMLVariables3Controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BITalinoManager {

    private boolean connected;
    private List<Float> EMG;

    public static Frame[] frame;

    public ArrayList<Integer> connectReadBitalino(String MAC) {
        BITalino bitalino = null;
        List<Integer> coso = new ArrayList<Integer>();
        try {
            bitalino = new BITalino();

            //we set the MAC address
            String macAddress = MAC;

            //Sampling rate, should be 10, 100 or 1000
            int SamplingRate = 100;
            bitalino.open(macAddress, SamplingRate); //we open the bitalino with the mac address and the sampling rate

            // Start acquisition with the channel A1 that is for EMG so we use {0}
            int[] channelsToAcquire = {0}; //We only want Electromyogram channel, that is A1
            bitalino.start(channelsToAcquire);

            //Read in total 10000000 times
            for (int j = 0; j < 10000; j++) { //Record 10 seconds

                //Each time read a block of 10 samples 
                int block_size = 10;
                frame = bitalino.read(block_size);

                System.out.println("size block: " + frame.length);

                //we add the samples of the EMG to the arraylist
                for (int i = 0; i < frame.length; i++) {
                    coso.add(frame[i].analog[0]);
                }
            }
            //stop acquisition
            //we create an iterator to print the EMG values each second 
            Iterator it = coso.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }

            bitalino.stop();
        } catch (BITalinoException ex) {
            Logger.getLogger(FXMLVariables3Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(FXMLVariables3Controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close bluetooth connection
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (BITalinoException ex) {
                Logger.getLogger(FXMLVariables3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (ArrayList<Integer>) coso;
    }

}

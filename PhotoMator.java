/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package photomator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class PhotoMator {

    /**
     * @param args the command line arguments
     */
    static int test = 0;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        if(test == 1){
            
            Photo test = new Photo("C:\\Users\\User\\Desktop\\pea_solved.pdf");
            System.out.println(test.check_for_duplicates_e("C:\\Users\\User\\Desktop"));

            System.exit(0);
        }
        
        JFrame background_frame = new background_window();
        
        if(!new File("configuration_file.txt").exists()){
            new welcome_window(background_frame,true);
        }
        else{
            
        }      
    }
    
}

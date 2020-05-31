/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package photomator;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Copier {
    
    ArrayList<Photo> to_copy;           //lista plikow do kopiowania      
    
    String path_to_copy;                //sciezka do skopiowania plikow
    
    ArrayList<String> log;              //zapis logu z calego procesu kopiowania
    
    boolean path_exists;                //test czy dana sciezka istnieje
    
    int licznik = 0;                        //test czy wszystkie pliki zostaly skopiowane;
    
    
    Copier(ArrayList<Photo> copy_list,String dist){
        
        //kopiowanie danych
        to_copy = copy_list;
        path_to_copy = dist;
        
        
    }
    
    
    
    
    
}

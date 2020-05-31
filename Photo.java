/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package photomator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *Klasa Obiektu Photo.
 * Tworzy ona obiekt przechowujący dane dotyczące pojedynczego zdjęcia.
 * @author User
 */
public class Photo {
    
    String photo_src;       //zawiera dokładną scieżkę dostępu do pliku
    String name;
    String photo_date;      //dokładna data pliku
    File photo;             //plik zdjęcia
    String enl;             //rozrzerzenie pliku
    
    String dzien,miesiac,rok;   //stringi potrzebne do tworzenia specyficznych kombinacji folderow
    String kod_daty;            //kod potrzeby do porownywania dat utworzenia plikow
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
    
    boolean exists;
    
    Photo(String src){
        try{
            photo = new File(src);
            exists = true;
            photo_src = src;
            photo_date = dateFormat.format(photo.lastModified());
            String[] splitter = photo_date.split("/");
            dzien = splitter[0];
            miesiac = splitter[1];
            rok = splitter[2].split(" ")[0];
            enl = src.substring(src.length()-3);
            name = photo.getName();
            kod_daty = dzien+miesiac+rok;
            
        }catch(Exception e){
            System.out.println("PHOTO: ERROR "+e.toString());
            exists = false;
        }   
    }
    boolean same(Photo to_compare){
        return this.kod_daty.equals(to_compare.kod_daty);
    }
    
    boolean real_same(Photo to_compare) throws IOException{
        //z jakiegoś powodu nie działa
        //byte[] f1 = Files.readAllBytes(photo.toPath());
        //byte[] f2 = Files.readAllBytes(to_compare.photo.toPath());
        //return f1.equals(f2);
        return to_compare.name.equals(this.name);
        
    }
    
    boolean check_for_duplicates(String path_to_find) throws IOException{
        System.out.println("AUTOMATOR: ROZPOCZYNAM POSZUKIWANIA W "+path_to_find);
        File files_in_path = new File(path_to_find);
        for(File element : files_in_path.listFiles()){
            if(element.isDirectory()){
                System.out.println("AUTOMATOR: PRZESZEDŁEM DO "+element.getAbsolutePath());
                check_for_duplicates(element.getAbsolutePath());
            }
            else{
                
                Photo to_check = new Photo(element.getAbsolutePath());
                System.out.println("AUTOMATOR: SPRAWDZAM PLIK: "+element.getAbsolutePath());
                if(real_same(to_check)){// PRZETESTOWANIE CZY PLIKI SA TE SAME
                    if(!element.getAbsolutePath().equals(photo.getAbsolutePath())){
                        System.out.println("AUTOMATOR: PLIK JEST DUPLIKATEM");
                        return true;
                    }
                    System.out.println("AUTOMATOR: ZNALAZLEM PLIK GŁÓWNY");
                }
                System.out.println("AUTOMATOR: PLIK NIE JEST DUPLIKATEM");
            }
        }
        return false;
    }
    
    boolean check_for_duplicates_e(String path_to_find){
        for(int i = 1;i<4;i++){
            String full_path = path_to_find+"\\"+dir_name(i);
            System.out.println("AUTOMATOR: SPRAWDZAM SCIEZKE:"+full_path);
            File t = new File(full_path);
            if(t.exists()){
                return true;
            }
        }
        System.out.println("AUTOMATOR: BRAK DUPLIKATU");
        return false;
    }
    
    String dir_name(int option){
        if(option == 1){
            return dzien+"/"+miesiac+"/"+rok;
            
        }
        if(option == 2){
            return dzien+"."+miesiac+"."+rok;
        }
        if(option == 3){
            return miesiac+"/"+dzien+"/"+rok;
        }
        return ""; 
    }
}

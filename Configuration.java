/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package photomator;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *Klasa Configruration
 * Object stores all kinds of information from User like presets or name.
 * Going to be updated for new features.
 * 
 * mozliwosci zapisu formatu obslugiwane przez configurator
 * 1 - > dzien/miesiac/rok
 * 2 - > dzien.miesiac.rok
 * 3 - > miesiac/dzien/rok
 * @author User
 */
public class Configuration {
    
    DictReader configuration_file;
    boolean exist;
    String wersja = "v1.0beta2";
    
    Configuration(){
        configuration_file = new DictReader(1);
        exist = configuration_file.exist;
    }
    
    void make_configuration(ArrayList<String> presets,String name,String date_format) throws FileNotFoundException{
        for(String camera: presets){
            configuration_file.dodaj("%presets", camera);
        }
        configuration_file.dodaj("%name", name);
        configuration_file.dodaj("%date_format",date_format);
        
        configuration_file.dodaj("%save_log", "yes");
        configuration_file.dodaj("%show_more", "yes");
        
        
        //always in the end
        configuration_file.dodaj("%warning", "please leave this file unchanged.");
    }
    
    boolean save_log(){
        ArrayList<String> ans = configuration_file.szukaj("%save_log");
        return ans.get(0).equals("yes\n");
    }
    boolean show_more(){
        ArrayList<String> ans = configuration_file.szukaj("%show_more");
        return ans.get(0).equals("yes\n");
    }
    
    
    ArrayList<ArrayList<String>> get_camera_presets(){
        ArrayList<String> aparaty = configuration_file.szukaj("%presets");
        ArrayList<String> ret_sciezki = new ArrayList<>();
        ArrayList<String> ret_nazwy = new ArrayList<>();
        for(String aparat: aparaty){
            String[] splited = aparat.split(" ");
            ret_nazwy.add(splited[0]);
            ret_sciezki.add(splited[1]);
        }
        ArrayList<ArrayList<String>> to_ret = new ArrayList<>();
        
        to_ret.add(ret_nazwy);
        to_ret.add(ret_sciezki);
        return to_ret;
    }
    
    ArrayList<String> get_presets_names(){
        return configuration_file.szukaj("%presets");
    }
    
    String get_camera_path(String camera_name){
        ArrayList<String> aparaty = configuration_file.szukaj("%presets");
        for(String aparat:aparaty){
            String[] splited = aparat.split(" ");
            if(splited[0].equals(camera_name)){
                return splited[1];
            }
        }
        return "";
    }
    
    int get_date_format(){
        ArrayList<String> format = configuration_file.szukaj("%date_format");
        return Integer.parseInt(format.get(0));
    }
    
    String get_username(){
        if(!configuration_file.szukaj("%imie").isEmpty()){
            return configuration_file.szukaj("%imie").get(0);
        }
        return "";
    }
    String get_date_format_label(){
        if(configuration_file.szukaj("%date_format").get(0).equals("1")){
            return "dzien/miesiac/rok";
        }
        if(configuration_file.szukaj("%date_format").get(0).equals("2")){
            return "dzien.miesiac.rok";
        }
        if(configuration_file.szukaj("%date_format").get(0).equals("3")){
            return "miesiac/dzien/rok";
        }
        return "";    
    }
    
}

package it.dax.klefkey.offline.control;

import com.google.gson.Gson;
import it.dax.klefkey.offline.model.PasswordJsonDb;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main{

    public static void main(String args[]){
        List<PasswordJsonDb> db = new ArrayList<PasswordJsonDb>();
        Gson gson = new Gson();

        PasswordJsonDb entry1 = new PasswordJsonDb();
        entry1.setPasswordKey("Prova 1");
        entry1.setPasswordValue("ProvaOne1");

        PasswordJsonDb entry2 = new PasswordJsonDb();
        entry2.setPasswordKey("Prova 2");
        entry2.setPasswordValue("ProvaTwo2");

        db.add(entry1);
        db.add(entry2);

        String dbJson = gson.toJson(db);
        System.out.println(dbJson);

        List<PasswordJsonDb> input = new ArrayList<PasswordJsonDb>();
        input = gson.fromJson(dbJson, ArrayList.class);

        System.out.println(input.toString());

        Map<String,String> ciao = new HashMap<String,String>();

        ciao.put("prova","cazzo");
        ciao.put("prova","figa");

        ciao.isEmpty();

        String aus = new String();

        aus.


        System.out.println(ciao.get((String)"prova"));




    }









}
package com.andreslab.tools_blind.models;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/18/17.
 */

public class LocalCommandsModel {
    private ArrayList<String> listLocalCommands;
    private String GlobalCommand;

    public LocalCommandsModel(String gc){
        this.GlobalCommand = gc;
    }

    public void addLocalCommand(String lc){
        listLocalCommands.add(lc);
    }


    public ArrayList<String> getListLocalCommands() {
        return listLocalCommands;
    }

    public void setListLocalCommands(ArrayList<String> listLocalCommands) {
        this.listLocalCommands = listLocalCommands;
    }

    public String getGlobalCommand() {
        return GlobalCommand;
    }

    public void setGlobalCommand(String globalCommand) {
        GlobalCommand = globalCommand;
    }
}

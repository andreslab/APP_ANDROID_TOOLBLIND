package com.andreslab.tools_blind.commands;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.andreslab.tools_blind.CameraActivity;
import com.andreslab.tools_blind.actions.utilities.UT_newPhoto;
import com.andreslab.tools_blind.models.LocalCommandsModel;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/18/17.
 */

public class ControllerCommands extends CommandList {

    public static ArrayList<String> listCommands;
    public static String GlobalCommands;
    public static String LastLocalCommand;
    public static ArrayList<LocalCommandsModel> listLocalCommands;
    public static Boolean isListeningArgument = false;

    public ControllerCommands(){
        this.listCommands = new ArrayList<String>();
        this.GlobalCommands = "";
        this.LastLocalCommand = "";
        this.isListeningArgument = false;

    }

    public String executeAndAddCommand(String c){
        String commandType = identifyCommandGlobalOrLocal(c);
        if(commandType == "GLOBAL"){
            if(GlobalCommands == ""){
                GlobalCommands = c; //guarda el comando global sin modificar
                c = "0" + c;
                this.listCommands.add(c);
            }else{
                //voz: por favor cierre edcion de [COMANDO GLOBAL]
            }
            return commandType;
        }else{
            LastLocalCommand = c;
            this.listCommands.add(c);
            return commandType;
        }
    }

    private String identifyCommandGlobalOrLocal(String c){
        String lastCommand = c;
        for (int i = 0; i <= CommandList.GlobalCommands.length-1; i++){
            if(CommandList.GlobalCommands[i].toString().equals(lastCommand)){
                return "GLOBAL";
            }
        }
        return "LOCAL";
    }

    private void deleteDataList(){

    }


}

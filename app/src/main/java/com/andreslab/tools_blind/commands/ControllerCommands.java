package com.andreslab.tools_blind.commands;

import android.util.Log;
import android.widget.Toast;

import com.andreslab.tools_blind.models.LocalCommandsModel;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/18/17.
 */

public class ControllerCommands extends CommandList {

    static ArrayList<String> listCommands;
    private String GlobalCommands;
    private ArrayList<LocalCommandsModel> listLocalCommands;

    public ControllerCommands(){
        this.listCommands = new ArrayList<String>();
        this.GlobalCommands = "";
    }

    public void executeAndAddCommand(String c){
        String commandType = identifyCommandGlobalOrLocal(c);
        if(commandType == "GLOBAL"){
            if(GlobalCommands == ""){
                GlobalCommands = c; //guarda el comando global sin modificar
                c = "0" + c;
                this.listCommands.add(c);
            }else{
                //voz: por favor cierre edcion de [COMANDO GLOBAL]
            }
        }else{
            this.listCommands.add(c);
        }
        executeCommandFunction(commandType);
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

    private void executeCommandFunction(String ct){

        Log.d("LIST COMMANDS", "tamaño: "+this.listCommands.size());

        switch (ct){
            case "GLOBAL":
                Log.d("EJECUCIÓN","tipo de comando global");

                break;
            case "LOCAL":
                Log.d("EJECUCIÓN","tipo de comando local");
                break;
            default:
                Log.d("EJECUCIÓN"," ninguno");
        }

    }

    private void deleteDataList(){

    }
}
